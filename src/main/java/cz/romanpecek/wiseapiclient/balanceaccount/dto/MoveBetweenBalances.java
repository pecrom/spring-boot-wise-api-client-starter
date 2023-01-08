package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MoveBetweenBalances {
    private Amount amount;
    private Long sourceBalanceId;
    private Long targetBalanceId;
    private Long quoteId;
}
