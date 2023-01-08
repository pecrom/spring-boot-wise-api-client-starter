package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance extends Amount {
    private Long id;
}
