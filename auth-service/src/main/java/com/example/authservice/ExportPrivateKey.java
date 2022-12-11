package com.example.authservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

public class ExportPrivateKey {
	private File keystoreFile = new File("E:\\microservice-base\\microservice-base\\auth-service\\src\\main\\resources\\myKeyStore.jks");
	private String keyStoreType = "jks";
	private char[] keyStorePassword = "Truong99".toCharArray();
	private char[] keyPassword = "Truong99".toCharArray();
	private String alias = "myKeyStore";
	private File exportedFile = new File("E:\\microservice-base\\microservice-base\\auth-service\\src\\main\\resources\\private_key.pem");

	public void export() throws Exception {
		KeyStore keystore = KeyStore.getInstance(keyStoreType);
		Base64.Encoder encoder = Base64.getEncoder();
		keystore.load(new FileInputStream(keystoreFile), keyStorePassword);
		Key key = keystore.getKey(alias, keyPassword);
		String encoded = new String(encoder.encode(key.getEncoded()));
		FileWriter fw = new FileWriter(exportedFile);
		fw.write("---BEGIN PRIVATE KEY---\n");
		fw.write(encoded);
		fw.write("\n");
		fw.write("---END PRIVATE KEY---");
		fw.close();
	}

	public static void main(String args[]) throws Exception {
		ExportPrivateKey export = new ExportPrivateKey();
		export.export();
	}
}