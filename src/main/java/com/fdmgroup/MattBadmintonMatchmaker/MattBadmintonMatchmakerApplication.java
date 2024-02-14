package com.fdmgroup.MattBadmintonMatchmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.MattBadmintonMatchmaker.security.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class MattBadmintonMatchmakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MattBadmintonMatchmakerApplication.class, args);
		System.out.println("program has started");
	}

}
