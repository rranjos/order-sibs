package pt.com.sibs.item;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

	private final ItemRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ItemServiceImpl(ItemRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ItemDTO> getItems() {
		return repository.findAll().stream().map(user -> modelMapper.map(user, ItemDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ItemDTO addItem(ItemDTO itemDTO) throws Exception {
		if (StringUtils.isBlank(itemDTO.getName())) {
			throw new Exception("Invalid name.");
		}
		Item entity = modelMapper.map(itemDTO, Item.class);
		repository.save(entity);
		return itemDTO;
	}

	@Override
	public ItemDTO getItemById(Long id) {
		return modelMapper.map(repository.getReferenceById(id), ItemDTO.class);
	}

	@Override
	public ItemDTO updateItem(ItemDTO itemDTO) throws Exception {

		try {
			Item entity = repository.getReferenceById(itemDTO.getId());
			entity.setName(itemDTO.getName());
			repository.save(entity);
		} catch (Exception e) {
			throw new Exception("Item not found!");
		}

		return itemDTO;
	}

	@Override
	public void deleteItem(Long id)  throws Exception{
		try {
			repository.delete(new Item(id));
		} catch (Exception e) {
			throw new Exception("Item not found!");
		}
	}

}
