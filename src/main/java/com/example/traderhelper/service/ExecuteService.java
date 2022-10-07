package com.example.traderhelper.service;

import com.example.traderhelper.storage.entity.CompanyEntity;
import com.example.traderhelper.storage.entity.StockEntity;
import java.util.List;

public interface ExecuteService {

	List<CompanyEntity> getCompanies();

	StockEntity getStock(String symbol);
}
