package org.paulobichara.cas.x509;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {

	private CloseableHttpClient httpclient;

	private static final String CAS_LOGIN_URL = "https://localhost:8443/cas/login";
	private static final File TRUSTSTORE_FILE = new File("[PATH_TO_TRUSTSTORE]");
	private static final String TRUSTSTORE_PASS = "[TRUSTSTOREPASS]";
	private static final File KEYSTORE_FILE = new File("[PATH_TO_KEYSTORE]");
	private static final String KEYSTORE_PASS = "[KEYSTOREPASS]";

	public void initClientSession() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom()
				.loadTrustMaterial(TRUSTSTORE_FILE, TRUSTSTORE_PASS.toCharArray(),
						new TrustSelfSignedStrategy())
						.loadKeyMaterial(KEYSTORE_FILE, KEYSTORE_PASS.toCharArray(), KEYSTORE_PASS.toCharArray())
						.build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext,
				new String[] { "TLSv1" },
				null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		this.httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.build();
	}

	public void closeClientSession() throws IOException {
		this.httpclient.close();
	}

	public void sendSimpleGetRequest(String url) throws ClientProtocolException, IOException {
		HttpGet httpget = new HttpGet(url);

		System.out.println("executing request " + httpget.getRequestLine());

		CloseableHttpResponse response = this.httpclient.execute(httpget);
		try {
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}		
	}

	public final static void main(String[] args) throws Exception {
		ClientCustomSSL client = new ClientCustomSSL();
		try {
			client.initClientSession();
			client.sendSimpleGetRequest(CAS_LOGIN_URL);
			client.sendSimpleGetRequest(CAS_LOGIN_URL);        	
		} finally {
			client.closeClientSession();
		}
	}

}