package com.example.traderhelper.service.impl;

import com.example.traderhelper.model.Company;
import com.example.traderhelper.model.Stock;
import com.example.traderhelper.service.CompanyService;
import com.example.traderhelper.service.ExecuteService;
import com.example.traderhelper.storage.entity.CompanyEntity;
import com.example.traderhelper.storage.entity.StockEntity;
import com.example.traderhelper.storage.mapper.CompanyMapper;
import com.example.traderhelper.storage.mapper.StockMapper;
import com.example.traderhelper.storage.repository.CompanyRepository;
import com.example.traderhelper.storage.repository.StockRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;

	private final StockRepository stockRepository;

	private final ExecuteService executeService;

	private final StockMapper stockMapper;

	private final CompanyMapper companyMapper;

	private final Executor executor = Executors.newFixedThreadPool(40);

	public void updateDB() {
		var companies = getCompany();
		var stocks = getStock(companies);

		updateCompany(companies);
		updateStock(stocks);
	}

	public void getResult() {
		printResult(getTopValCompany());
		printResult(getTopStocks());
	}

	private List<StockEntity> getStock(List<CompanyEntity> companies) {

		var stocks = new CopyOnWriteArrayList<StockEntity>();

		companies.parallelStream().forEach(company -> {
			try {
				stocks.add(
					CompletableFuture.supplyAsync(() -> executeService.getStock(company.getSymbol()), executor).get());
			} catch (Exception ignored) {
			}
		});

		return stocks;
	}

	private List<CompanyEntity> getCompany() {
		return executeService.getCompanies();
	}

	private void updateCompany(List<CompanyEntity> companyEntityList) {
		companyRepository.saveAllAndFlush(companyEntityList);
	}

	private void updateStock(List<StockEntity> stockEntityList) {
		stockRepository.saveAllAndFlush(stockEntityList);
	}

	public List<Company> getTopValCompany() {
		var valCompanies= companyRepository.getTopVolatileCompany();
		return valCompanies.stream().map(companyMapper::map).collect(Collectors.toList());
	}

	private List<Stock> getTopStocks() {
		var topStocks = stockRepository.findByOrderByVolumeDesc(Pageable.ofSize(5).withPage(1));
		return topStocks.stream().map(stockMapper::map).collect(Collectors.toList());
	}

	private <T> void printResult(@NotNull List<T> list) {
		for (T ob : list) {
			System.out.println(ob);
		}
	}
}