package com.rawchen.mall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.rawchen"})
public class MallCouponApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallCouponApplication.class, args);
	}

}
