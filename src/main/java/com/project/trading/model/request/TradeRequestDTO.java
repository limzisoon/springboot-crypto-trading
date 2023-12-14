package com.project.trading.model.request;

import com.project.trading.model.entity.Crypto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TradeRequestDTO implements Serializable {

    private Long id;
    private String symbol;
    private Double individualPrice;
    private String transId;
    private Integer quantity;
    private String buySell;
}
