package com.example.traderhelper.service.impl;

import com.example.traderhelper.service.ExecuteService;
import com.example.traderhelper.storage.entity.CompanyEntity;
import com.example.traderhelper.storage.entity.StockEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import lombok.Data;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Data
public class ExecuteServiceImpl extends AsyncConfigurerSupport implements ExecuteService {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(80);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(1);
		executor.setThreadNamePrefix("AsyncTaskThread::");
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.initialize();
		return executor;
	}

	private final String COMPANY_URL = "%s/stable/ref-data/symbols?token=%s";
	private final String STOCK_URL = "%s/stable/stock/%s/quote?token=%s";

	private final RestTemplate restTemplate;

	public List<CompanyEntity> getCompanies() {
		var url = String.format(COMPANY_URL,"${boot.host}","${bot.token}");
		var companies = restTemplate.getForObject(url, CompanyEntity[].class);
		return Arrays.asList(Objects.requireNonNull(companies));
	}

	public StockEntity getStock(String symbol){
		var url = String.format(STOCK_URL,"${bot.host}", symbol,"${bot.token}");
		return restTemplate.getForObject(url, StockEntity.class);
	}
}
