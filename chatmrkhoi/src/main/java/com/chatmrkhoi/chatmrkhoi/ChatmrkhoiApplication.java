package com.chatmrkhoi.chatmrkhoi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
@SpringBootApplication
@EnableConfigurationProperties
public class ChatmrkhoiApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ChatmrkhoiApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ứng dụng đã khởi chạy");
	}
}