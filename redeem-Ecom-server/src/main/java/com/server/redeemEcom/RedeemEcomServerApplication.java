package com.server.redeemEcom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RedeemEcomServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedeemEcomServerApplication.class, args);
	}

}
