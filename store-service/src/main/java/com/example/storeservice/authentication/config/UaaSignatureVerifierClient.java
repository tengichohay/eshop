package com.example.storeservice.authentication.config;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Client fetching the public key from UAA to create a SignatureVerifier.
 */
@Component
public class UaaSignatureVerifierClient implements OAuth2SignatureVerifierClient {
	private final Logger log = LoggerFactory.getLogger(UaaSignatureVerifierClient.class);
	private final RestTemplate restTemplate;
	protected final OAuth2Properties oAuth2Properties;

	@Value("${oauth2.oauth.clientId}")
	private String clientId;

	@Value("${oauth2.oauth.secret}")
	private String secret;

	@Value("${oauth2.oauth.publicKeyEndpointUri}")
	private String publicKeyEndpointUri;

	public UaaSignatureVerifierClient(DiscoveryClient discoveryClient,
									  @Qualifier("loadBalancedRestTemplate") RestTemplate restTemplate, OAuth2Properties oAuth2Properties) {
		this.restTemplate = restTemplate;
//		this.restTemplate = new RestTemplate();
		this.oAuth2Properties = oAuth2Properties;
		discoveryClient.getServices();
	}

	/**
	 * Fetches the public key from the UAA.
	 *
	 * @return the public key used to verify JWT tokens; or null.
	 */
	@Override
	public SignatureVerifier getSignatureVerifier() throws Exception {
		try {
			String key = (String) restTemplate.exchange(publicKeyEndpointUri, HttpMethod.GET,
					new HttpEntity<Void>(createHeaders(clientId, secret)), Map.class).getBody().get("value");
			return new RsaVerifier(key);
		} catch (IllegalStateException ex) {
			log.warn("could not contact UAA to get public key");
			return null;
		}
	}

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;

			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
}
