package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import com.neovisionaries.i18n.CurrencyCode;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.BalanceAccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewBalanceAccount {
    /**
     * Balance currency
     */
    private CurrencyCode currency;

    /**
     * Type of Balance
     */
    private BalanceAccountType type;

    /**
     * Balance name
     */
    private String name;
}
