package com.example.traderhelper.storage.mapper;

import com.example.traderhelper.model.Company;
import com.example.traderhelper.storage.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

	public Company map(CompanyEntity entity) {
		return Company.builder()
			.name(entity.getName())
			.symbol(entity.getSymbol())
			.build();
	}
}
