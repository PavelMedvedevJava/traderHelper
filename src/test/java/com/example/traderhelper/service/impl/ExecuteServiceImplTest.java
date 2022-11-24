package com.example.traderhelper.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;

import com.example.traderhelper.storage.entity.StockEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExecuteServiceImplTest {

	@Test
	void getCompanies() {
		assertThat(anyList());
	}

	@Test
	void getStock() {
		assertThat(Mockito.mock(StockEntity.class));
	}
}