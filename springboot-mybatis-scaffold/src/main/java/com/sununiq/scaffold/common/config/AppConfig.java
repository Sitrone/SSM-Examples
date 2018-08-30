package com.sununiq.scaffold.common.config;

import com.sununiq.scaffold.common.domain.HttpProfile;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLInitializationException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: 应用配置
 *
 * @author: sununiq
 *
 * @create: 2018-08-30 22:16
 **/
@Configuration
public class AppConfig {

	@Bean
	public HttpProfile httpProfile() {
		return HttpProfile.newDefaultHttpProfile();
	}

	@Bean
	public RequestConfig requestConfig(HttpProfile httpProfile) {
		return RequestConfig.custom()
				.setConnectionRequestTimeout(httpProfile.getMaxReadTimeout())
				.setConnectTimeout(httpProfile.getMaxConnTimeout()).setSocketTimeout(httpProfile.getMaxReadTimeout())
				.build();
	}

	@Bean
	public HttpClient httpClient(HttpProfile httpProfile, RequestConfig requestConfig) {
		LayeredConnectionSocketFactory ssl = null;
		try {
			ssl = SSLConnectionSocketFactory.getSystemSocketFactory();
		} catch (final SSLInitializationException ex) {
			final SSLContext sslcontext;
			try {
				sslcontext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
				sslcontext.init(null, null, null);
				ssl = new SSLConnectionSocketFactory(sslcontext);
			} catch (final SecurityException | KeyManagementException | NoSuchAlgorithmException ignore) {
			}
		}

		final Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", ssl != null ? ssl : SSLConnectionSocketFactory.getSocketFactory()).build();

		PoolingHttpClientConnectionManager connmgr = new PoolingHttpClientConnectionManager(sfr);
		connmgr.setDefaultMaxPerRoute(httpProfile.getMaxPerRoute());
		connmgr.setMaxTotal(httpProfile.getMaxTotal());
		connmgr.setValidateAfterInactivity(1000);
		return HttpClientBuilder.create()
				.setDefaultRequestConfig(requestConfig)
				.setConnectionManager(connmgr)
				.build();
	}

	@Bean
	public RestTemplate restTemplate(HttpClient httpClient) {
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		restTemplate.setRequestFactory(factory);
		return restTemplate;
	}


}
