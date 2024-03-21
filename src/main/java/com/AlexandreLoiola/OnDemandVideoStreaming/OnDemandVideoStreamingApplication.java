package com.AlexandreLoiola.OnDemandVideoStreaming;

import com.AlexandreLoiola.OnDemandVideoStreaming.service.S3Service;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log4j2
public class OnDemandVideoStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnDemandVideoStreamingApplication.class, args);
	}

}