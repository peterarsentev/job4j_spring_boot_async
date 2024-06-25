package ru.job4j.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.job4j.rest.service.NotifyService;

@EnableAsync
@SpringBootApplication
public class Main {
	@Bean("notificationPool")
	public ThreadPoolTaskExecutor initNotificationPool() {
		var pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(1);
		return pool;
	}

	@Bean("reportPool")
	public ThreadPoolTaskExecutor initReportPool() {
		var pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(1);
		return pool;
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			var service = ctx.getBean(NotifyService.class);
			service.asyncOperation();
			service.asyncOperation();
			service.asyncOperationByReportPool();
			service.asyncOperationByReportPool();
			var reports = service.report(-1L);
			System.out.println("Wait 1 second.");
			reports.get().forEach(System.out::println);
		};
	}
}
