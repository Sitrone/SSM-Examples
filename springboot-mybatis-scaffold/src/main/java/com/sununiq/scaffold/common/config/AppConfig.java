package com.sununiq.scaffold.common.config;

import com.sununiq.scaffold.common.domain.HttpProfile;
import org.apache.http.HttpHeaders;
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
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new HeaderRequestInterceptor(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));
		interceptors.add(new HeaderRequestInterceptor(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		restTemplate.setRequestFactory(factory);

		// 统一添加http header的两种方式之一，使用interceptor，这个是统一设置
		// 另一种方式使用HTTPEntity，里面设置HTTPHeader，这个灵活，每个请求可以单独设置
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}



	public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

		private final String headerName;

		private final String headerValue;

		public HeaderRequestInterceptor(String headerName, String headerValue) {
			this.headerName = headerName;
			this.headerValue = headerValue;
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws
				IOException {
			request.getHeaders().set(headerName, headerValue);
			return execution.execute(request, body);
		}
	}

}
