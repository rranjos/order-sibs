package pt.com.sibs.stock;

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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dataCreation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_item", referencedColumnName = "id")
	private Item item;
	private Integer quantity;

	public Stock(Long id) {
		this.id = id;
	}

}
