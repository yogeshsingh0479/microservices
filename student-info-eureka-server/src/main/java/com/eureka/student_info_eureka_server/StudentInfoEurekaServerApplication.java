package com.eureka.student_info_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StudentInfoEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentInfoEurekaServerApplication.class, args);
	}

}
