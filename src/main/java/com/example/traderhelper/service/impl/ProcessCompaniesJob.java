package com.example.traderhelper.service.impl;

import com.example.traderhelper.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ProcessCompaniesJob {
	private final CompanyService companyService;

	@Scheduled(fixedRateString = "${bot.updateDB}")
	public void dbUpdateJob() {
		companyService.updateCompaniesAndStocksInfo();
	}

	@Scheduled(fixedRateString = "${bot.notyfiJob}")
	public void notifyJob() {
		companyService.getResult();
	}
}
