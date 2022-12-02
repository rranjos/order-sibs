package pt.com.sibs.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<UserDTO> getUsers() {
		return repository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());

	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		User entity = modelMapper.map(userDTO, User.class);
		repository.save(entity);
		return userDTO;
	}

	@Override
	public UserDTO getUserById(Long id) {
		return modelMapper.map(repository.getReferenceById(id), UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		User entity = modelMapper.map(userDTO, User.class);
		repository.save(entity);
		return userDTO;
	}

	@Override
	public void deleteUser(Long id) {
		repository.delete(new User(id));
	}

}
