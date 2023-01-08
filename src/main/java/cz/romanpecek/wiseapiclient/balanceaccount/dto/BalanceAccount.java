package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import com.neovisionaries.i18n.CurrencyCode;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.BalanceAccountType;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.InvestmentState;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BalanceAccount {

    /**
     * Balance id
     */
    private Long id;

    /**
     * Balance currency
     */
    private CurrencyCode currency;

    /**
     * Balance type
     */
    private BalanceAccountType type;

    /**
     * Balance name
     */
    private String name;

    /**
     * Emoji string
     */
    private String icon;

    /**
     * Investment state of balance
     */
    private InvestmentState investmentState;

    /**
     * Available balance
     */
    private Amount amount;

    /**
     * Amount reserved for transactions
     */
    private Amount reservedAmount;

    /**
     * Cash amount in the account
     */
    private Amount cashAmount;

    /**
     * Current total worth
     */
    private Amount totalWorth;

    /**
     * Date when the balance was created
     */
    private OffsetDateTime creationTime;

    /**
     * Date when the balance was last modified
     */
    private OffsetDateTime modificationTime;

    /**
     * If balance is visible for the user or not
     */
    private boolean visible;

}
