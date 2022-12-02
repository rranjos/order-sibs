package pt.com.sibs.item;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {

	List<ItemDTO> getItems();

	ItemDTO addItem(ItemDTO itemDTO) throws Exception;

	ItemDTO getItemById(Long id);

	ItemDTO updateItem(ItemDTO itemDTO) throws Exception;

	void deleteItem(Long id) throws Exception;

}
