package com.project.trading.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@Table(name = "transaction")
public class Transaction
{
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date timestampdate;

    @Column
    private double totalPrice;

    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER, mappedBy = "transaction")
    private Set<Trade> allTrades;

    @Column
    private Integer accountId;
}