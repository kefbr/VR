package com.vr.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.vr.projeto")
@EntityScan("com.vr.projeto.model")
public class VrApplication {

	public static void main(String[] args) {
		SpringApplication.run(VrApplication.class, args);
	}

}
