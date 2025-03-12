package com.vkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.vkr.auth.repository") // ✅ Добавляем вручную
@EntityScan("com.vkr.auth.models") // ✅ Сканируем модели
public class VkrCorpAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkrCorpAggregatorApplication.class, args);
	}

}
