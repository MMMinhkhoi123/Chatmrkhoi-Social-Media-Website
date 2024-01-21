package com.chatmrkhoi.chatmrkhoi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ChatmrkhoiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChatmrkhoiApplication.class, args);
	}
}
