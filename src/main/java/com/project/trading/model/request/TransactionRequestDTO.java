package com.project.trading.model.request;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionRequestDTO implements Serializable {

    private AccountRequestDTO accountRequestDTO;
    private List<TradeRequestDTO> tradeRequestDTOs;
}
