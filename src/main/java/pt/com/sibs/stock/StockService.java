package pt.com.sibs.stock;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface StockService {

	List<StockDTO> getStock();

	StockDTO addStock(StockDTO stockDTO) throws Exception;

	StockDTO getStockById(Long id) throws Exception;

	StockDTO updateStock(StockDTO stockDTO) throws Exception;

	void deleteStock(Long id) throws Exception;

	void updateItemsStock(Long id, Integer quantity);

	Optional<Stock> findByItemId(Long id);

}
