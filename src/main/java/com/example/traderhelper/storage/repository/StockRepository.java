package com.example.traderhelper.storage.repository;

import com.example.traderhelper.storage.entity.StockEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

	List<StockEntity> getDistinctTopBy();

	@Query(value = "SELECT * from stock ORDER BY volume desc LIMIT 5", nativeQuery = true)
	List<StockEntity> getTopPrice();

	Page<StockEntity> findAll(Pageable pageable);

	List<StockEntity> findByOrderByVolumeDesc(Pageable pageable);
}
