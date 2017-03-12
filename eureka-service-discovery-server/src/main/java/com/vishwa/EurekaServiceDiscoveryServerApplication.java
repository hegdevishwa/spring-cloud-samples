package com.vishwa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceDiscoveryServerApplication {

	public static void main(String[] args) {
		// SpringApplication.run(EurekaServiceDiscoveryServerApplication.class,
		// args);

		new SpringApplicationBuilder(EurekaServiceDiscoveryServerApplication.class).web(true).run(args);

	}
}
