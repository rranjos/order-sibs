package pt.com.sibs.stock;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long>{
	
	Optional<Stock> findByItemId(Long id);

}
