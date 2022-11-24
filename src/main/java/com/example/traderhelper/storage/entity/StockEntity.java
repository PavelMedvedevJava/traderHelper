package com.example.traderhelper.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "stocks")
public class StockEntity {

	@Id
	@GeneratedValue
	@Column(name = "stock_id")
	private Long stockId;

	@Column(name = "symbol")
	private String symbol;

	@MapsId("symbol")
	@JoinColumn(name = "symbol")
	@ManyToOne(fetch = FetchType.LAZY)
	private CompanyEntity company;

	private String companyName;
	private String primaryExchange;
	private String calculationPrice;
	private Double open;
	private Long openTime;
	private String openSource;
	private Double close;
	private Long closeTime;
	private String closeSource;
	private Double high;
	private Long highTime;
	private String highSource;
	private Double low;
	private Long lowTime;
	private String lowSource;
	private Double latestPrice;
	private String latestSource;
	private String latestTime;
	private Long latestUpdate;
	private Long latestVolume;
	private Double iexRealtimePrice;
	private Long iexRealtimeSize;
	private Long iexLastUpdated;
	private Double delayedPrice;
	private Long delayedPriceTime;
	private Double oddLotDelayedPrice;
	private Long oddLotDelayedPriceTime;
	private Double extendedPrice;
	private Double extendedChange;
	private Double extendedChangePercent;
	private Long extendedPriceTime;
	private Double previousClose;
	private Long previousVolume;
	private Double change;
	private Double changePercent;
	private Long volume;
	private Double iexMarketPercent;
	private Long iexVolume;
	private Long avgTotalVolume;
	private Long iexBidPrice;
	private Long iexBidSize;
	private Long iexAskPrice;
	private Long iexAskSize;
	private Double iexOpen;
	private Long iexOpenTime;
	private Double iexClose;
	private Long iexCloseTime;
	private Long marketCap;
	private Long peRatio;
	private Double week52High;
	private Double week52Low;
	private Double ytdChange;
	private Long lastTradeTime;
	private String currency;
	private boolean isUSMarketOpen;
}
