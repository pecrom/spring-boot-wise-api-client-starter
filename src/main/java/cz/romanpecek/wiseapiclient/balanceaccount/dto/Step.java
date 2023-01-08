package cz.romanpecek.wiseapiclient.balanceaccount.dto;

import cz.romanpecek.wiseapiclient.balanceaccount.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;

@Data
public class Step {
    private Long id;
    private TransactionType type;
    private OffsetDateTime creationTime;
    private Collection<Balance> balancesAfter;
    private String channelName;
    private String channelReferenceId;
    private String tracingReferenceCode;
    private Amount sourceAmount;
    private Amount targetAmount;
    private Amount fee;
    private BigDecimal rate;
}
