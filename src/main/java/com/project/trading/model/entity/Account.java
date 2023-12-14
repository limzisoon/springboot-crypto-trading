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
@Table(name="accounts")
public class Account {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String dob;
	@Column
	private String email;
	@Column
	private Double walletBalance;
}
	

	