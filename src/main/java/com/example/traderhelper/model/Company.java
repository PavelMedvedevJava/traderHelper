package com.example.traderhelper.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Company {

	private String symbol;
	private String exchange;
	private String name;
	private String date;
	private boolean isEnabled;
	private String type;
	private String region;
	private String currency;
	private String iexId;
	private String figi;
	private String cik;
}
