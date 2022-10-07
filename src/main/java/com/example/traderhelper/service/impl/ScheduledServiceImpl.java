package com.example.traderhelper.service.impl;

import com.example.traderhelper.service.CompanyService;
import com.example.traderhelper.service.ScheduledService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledServiceImpl implements ScheduledService {
	private final CompanyService companyService;

	@Scheduled(fixedRateString = "${bot.updateDB}")
	public void dbUpdateJob() {
		companyService.updateDB();
	}

	@Scheduled(fixedRateString = "${bot.notyfiJob}")
	public void notifyJob() {
		companyService.getResult();
	}
}
