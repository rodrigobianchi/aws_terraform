package io.github.awsconect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;

@SpringBootApplication
@EnableSqs
public class AwsConectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsConectApplication.class, args);
	}

}
