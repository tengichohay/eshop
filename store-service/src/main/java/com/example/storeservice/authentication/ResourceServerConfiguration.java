package com.example.storeservice.authentication;


import com.example.storeservice.authentication.config.OAuth2SignatureVerifierClient;
import com.example.storeservice.authentication.config.OAuth2Properties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private String RESOURCE_ID = "restservice";

	private final OAuth2Properties oAuth2Properties;

	public ResourceServerConfiguration(OAuth2Properties oAuth2Properties) {
		this.oAuth2Properties = oAuth2Properties;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/v2/api-docs", "/instances**", "/actuator/**", "/v2/api-docs", "/v2/syn-sub/**", "/swagger-ui").permitAll()
				.antMatchers("/**").authenticated();

	}

	@Bean
	public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
		return new JwtTokenStore(jwtAccessTokenConverter);
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID);
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(OAuth2SignatureVerifierClient signatureVerifierClient) {
		return new OAuth2JwtAccessTokenConverter(oAuth2Properties, signatureVerifierClient);
	}

	@Bean
	@Qualifier("loadBalancedRestTemplate")
	public RestTemplate loadBalancedRestTemplate(RestTemplateCustomizer customizer) {
		RestTemplate restTemplate = new RestTemplate();
		customizer.customize(restTemplate);
		return restTemplate;
	}

	@Bean
	@Qualifier("vanillaRestTemplate")
	public RestTemplate vanillaRestTemplate() {
		return new RestTemplate();
	}
}
