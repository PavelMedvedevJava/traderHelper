package com.example.traderhelper.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;

import com.example.traderhelper.storage.repository.CompanyRepository;
import com.example.traderhelper.storage.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private StockRepository stockRepository;

	@Test
	void updateCompaniesAndStocksInfo() {
		verify(companyRepository, atMost(2)).saveAllAndFlush(anyList());
			}

	@Test
	void getResult() {
		verify(companyRepository, atMost(1)).getTopVolatileCompany();
	}

	@Test
	void getTopValCompany() {
		verify(stockRepository, atMost(1)).findByOrderByVolumeDesc(any());
	}


}