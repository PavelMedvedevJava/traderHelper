package com.example.traderhelper.storage.repository;

import com.example.traderhelper.storage.entity.CompanyEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

	@Query(value = "WITH cte AS (\n"
		+ "    SELECT row_number() over (partition by company_id order by ((s.close - s.latest_Price) / s.latest_Price * 100) DESC),\n"
		+ "           (s.close - s.latest_Price) / s.latest_Price * 100 AS Volatility\n"
		+ "            ,\n"
		+ "           *\n"
		+ "    from company c\n"
		+ "             INNER join stock s on c.company_id = s.symbol\n"
		+ "    WHERE COALESCE(s.latest_price, 0) <> 0\n"
		+ "      AND s.close IS NOT NULL\n"
		+ "    ORDER BY Volatility DESC\n"
		+ ")\n"
		+ "SELECT company_id,*\n"
		+ "FROM cte\n"
		+ "WHERE row_number = 1\n"
		+ "LIMIT 5", nativeQuery = true)
	List<CompanyEntity> getTopVolatileCompany();
}

