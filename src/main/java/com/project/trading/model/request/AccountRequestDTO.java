package com.project.trading.model.request;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountRequestDTO implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private Double walletBalance;
}
