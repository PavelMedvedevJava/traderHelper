package com.example.traderhelper.storage.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Table(name = "companies")
public class CompanyEntity {

	@Id
	@Column(name = "company_id")
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

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<StockEntity> stockEntityList;
}
