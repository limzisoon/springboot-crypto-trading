/**
 * 
 */
package com.project.trading.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@Table(name = "cryptos")
public class Crypto {

	@Id
	private String symbol;
	@Column
	private String fullDesc;
	@Column
	private Double bidPrice;
	@Column
	private Double bidQty;
	@Column
	private Double askPrice;
	@Column
	private Double askQty;
}
