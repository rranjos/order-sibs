package pt.com.sibs.stock;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.com.sibs.item.Item;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
	
	private Long id;
	@JsonIgnore
	private LocalDate dataCreation;
	private Item item;
	private Integer quantity;

}
