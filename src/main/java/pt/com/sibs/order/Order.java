package pt.com.sibs.order;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.com.sibs.item.Item;
import pt.com.sibs.stock.Stock;
import pt.com.sibs.user.User;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_item", referencedColumnName = "id")
	private Item item;
	private Integer quantity;
	private Date dataCreation;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private User user;
	
	private boolean completed;	
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_stock", referencedColumnName = "id")
	private Stock stock;
	
	public Order(Long id) {
		this.id = id;
	}

}
