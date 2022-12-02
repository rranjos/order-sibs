package pt.com.sibs.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

	List<UserDTO> getUsers();

	UserDTO addUser(UserDTO userDTO);

	UserDTO getUserById(Long id);

	UserDTO updateUser(UserDTO userDTO);

	void deleteUser(Long id);

}
