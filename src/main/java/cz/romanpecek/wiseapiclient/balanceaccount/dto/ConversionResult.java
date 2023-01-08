package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import cz.romanpecek.wiseapiclient.balanceaccount.enums.TransactionState;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Data
public class ConversionResult {
    private Long id;
    private TransactionType type;
    private TransactionState state;
    private Collection<Balance> balancesAfter;
    private OffsetDateTime creationTime;
    private List<Step> steps;
    private Amount sourceAmount;
    private Amount targetAmount;
    private BigDecimal rate;
    private Collection<Amount> feeAmounts;
}
