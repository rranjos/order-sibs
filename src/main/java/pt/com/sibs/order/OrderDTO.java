package pt.com.sibs.order;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.com.sibs.item.Item;
import pt.com.sibs.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
	
	private Long id;
	private Item item;
	private Integer quantity;
	@JsonIgnore
	private Date dataCreation;
	private User user;
	private boolean completed;

}
