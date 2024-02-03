package com.chatmrkhoi.chatmrkhoi;
import com.chatmrkhoi.chatmrkhoi.design.Signgleton;
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
		Signgleton signgleton = Signgleton.getInstance();
		System.out.println(signgleton.getUrlFile());
	}
}
