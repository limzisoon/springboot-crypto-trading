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
@Table(name = "trade")
public class Trade
{
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    @Column
    private Double individualPrice;

    @Column
    private String transId;

    @Column
    private Integer quantity;

    @Column
    private String buySell;


    @ManyToOne(fetch = FetchType.EAGER , cascade=CascadeType.ALL)
    private Transaction transaction;
}