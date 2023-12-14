/**
 * 
 */
package com.project.trading.repository;

import com.project.trading.model.entity.Crypto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CryptoRepository extends CrudRepository<Crypto, Integer> {
	@Nullable
	public Crypto findBySymbol(String symbol);

	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE cryptos", nativeQuery = true)
	void truncateTable();
}
