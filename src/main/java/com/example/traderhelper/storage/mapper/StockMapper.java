package com.example.traderhelper.storage.mapper;

import com.example.traderhelper.model.Stock;
import com.example.traderhelper.storage.entity.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

	public Stock map(StockEntity entity) {
		return Stock.builder()
			.companyName(entity.getCompanyName())
			.symbol(entity.getSymbol())
			.build();
	}
}
