package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import com.neovisionaries.i18n.CurrencyCode;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.BalanceAccountType;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class DeletedBalanceAccount {
    private Long id;
    private CurrencyCode currency;
    private BalanceAccountType type;
    private String name;
    private String icon;
    private Amount amount;
    private Amount reservedAmount;
    private OffsetDateTime creationTime;
    private OffsetDateTime modificationTime;
    private boolean visible;
}
