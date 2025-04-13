package com.kelaskoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.kelaskoding.webclient.CustomerClient;
import com.kelaskoding.webclient.ProductClient;

@SpringBootApplication
@EnableDiscoveryClient // mendaftak ke registry serve
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	// @Bean
	// @LoadBalanced
	// RestTemplate restTemplate() {
	// 	return new RestTemplate();
	// }

	@Autowired
	private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

	@Autowired
	private WebClient.Builder webClientBuilderCustomer;

	@Autowired
	private WebClient.Builder webClientBuilderProduct;

	@Bean
	WebClient webClientCustomer() {
		return webClientBuilderCustomer
				.baseUrl("http://customer-service")
				.filter(loadBalancedExchangeFilterFunction)
				.build();
	}

	@Bean
	WebClient webClientProduct() {
		return webClientBuilderProduct
				.baseUrl("http://product-service")
				.filter(loadBalancedExchangeFilterFunction)
				.build();
	}


	@Bean
	CustomerClient customerClient() {
		// Membuat HttpServiceProxyFactory menggunakan WebClient
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builderFor(WebClientAdapter.create(webClientCustomer()))
				.build();

		// Mengembalikan instance CustomerClient
		return factory.createClient(CustomerClient.class);
	}

	@Bean
	ProductClient productClient() {
		// Membuat HttpServiceProxyFactory menggunakan WebClient
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builderFor(WebClientAdapter.create(webClientProduct()))
				.build();

		// Mengembalikan instance ProductClient
		return factory.createClient(ProductClient.class);
	}
}
