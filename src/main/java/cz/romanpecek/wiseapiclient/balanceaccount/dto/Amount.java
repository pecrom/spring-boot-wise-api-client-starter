package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Amount {
    private BigDecimal amount;
    private CurrencyCode currency;
}
