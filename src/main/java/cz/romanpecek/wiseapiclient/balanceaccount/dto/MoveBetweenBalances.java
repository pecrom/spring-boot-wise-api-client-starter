package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import lombok.Data;

@Data
public class MoveBetweenBalances {
    private Amount amount;
    private Long sourceBalanceId;
    private Long targetBalanceId;
    private Long quoteId;
}
