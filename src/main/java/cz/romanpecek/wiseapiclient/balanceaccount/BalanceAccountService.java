package cz.romanpecek.wiseapiclient.balanceaccount;

import com.neovisionaries.i18n.CurrencyCode;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.Amount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.BalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.ConversionResult;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.DeletedBalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.MoveBetweenBalances;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.NewBalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.BalanceAccountType;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.ResponseType;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.StatementType;
import cz.romanpecek.wiseapiclient.clients.BalanceAccountClient;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceAccountService {

    private static final int MAX_STATEMENT_PERIOD_DAYS = 469;
    private final BalanceAccountClient balanceAccountClient;

    /**
     * Opens a balance within the specified profile, in the currency and type specified in the request.
     * For STANDARD balances, only one can be created for a currency. For SAVINGS balances, multiples in the same currency can be opened.
     * When sending the request, the currency and type are required. If creating a SAVINGS type balance, a name is also required.
     *
     * @param profileId for which profile the account should be opened
     * @param name name of the account
     * @param currency currency of the account
     * @param type type of the account STANDARD or SAVINGS
     * @return {@link BalanceAccount} created balance account
     */
    public BalanceAccount create(@Nonnull Long profileId, String name, @NonNull UUID idempotenceUUID, @Nonnull CurrencyCode currency, @Nonnull BalanceAccountType type) {
        if (BalanceAccountType.SAVINGS.equals(type) && StringUtils.isEmpty(name)) {
            throw new ValidationException("For SAVING account type, name is also required");
        }

        NewBalanceAccount newBalanceAccount = NewBalanceAccount.builder().name(name)
                                                                            .currency(currency)
                                                                            .type(type)
                                                                            .build();

        return balanceAccountClient.create(profileId, idempotenceUUID, newBalanceAccount);
    }

    /**
     * Returns all balance accounts the profile has in the types specified.
     *
     * @param profileId for which profile the balance accounts should be returned
     * @param types types of the balance accounts which should be returned
     * @return {@link Collection<BalanceAccount>} collection of Balance accounts
     */
    public Collection<BalanceAccount> getAllByProfileIdAndTypes(@Nonnull Long profileId, @Nonnull BalanceAccountType... types) {
        String commaTypes = commaSeparatedTypes(types);

        ResponseEntity<Collection<BalanceAccount>> response = balanceAccountClient.getAllByProfileIdAndTypes(profileId, commaTypes);

        return HttpStatus.OK.equals(response.getStatusCode()) ? response.getBody() : CollectionUtils.emptyCollection();
    }

    /**
     * Returns a balance based on the specified profile id and account id
     *
     * @param profileId for which profile the balance account should be returned
     * @param accountId id of the balance account which should be returned
     * @return {@link Optional<BalanceAccount>} found balance account
     */
    public Optional<BalanceAccount> getByProfileIdAndId(@Nonnull Long profileId, @Nonnull Long accountId) {
        ResponseEntity<BalanceAccount> response = balanceAccountClient.getByProfileIdAndId(profileId, accountId);

        Optional<BalanceAccount> balanceAccount = Optional.empty();
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            balanceAccount = Optional.ofNullable(response.getBody());
        }

        return balanceAccount;
    }

    /**
     * Close a balance account for the users profile.
     * Balance accounts must have a zero balance in order for it to be closed.
     * Bank account details for a balance account will also be deactivated and may not be restored in the future.
     *
     * @param profileId for which profile the balance account should be closed
     * @param accountId id of the balance account which should be closed
     * @return {@link DeletedBalanceAccount} closed account info
     */
    public void deleteByProfileIdAndId(@Nonnull Long profileId, @Nonnull Long accountId) {
        // @TODO add check that the amount is 0
        balanceAccountClient.deleteByProfileIdAndId(profileId, accountId);
    }

    /**
     * Generates statement for the provided balanceId, with the response in specified type. Note that the PDF type includes Wise branding.
     *
     * The period between intervalStart and intervalEnd cannot exceed 469 days (around 1 year 3 months).
     * @param profileId for which profile the statement should be generated
     * @param accountId id of the balance account for which the statement should be generated
     * @param intervalStart start date for statement generation
     * @param intervalEnd end date for statement generation
     * @param statementType COMPACT or FLAT
     * @param responseType specifies whether CSV, JSON or PDF should be returned
     * @return {@link byte[]} Response in byte array
     */
    public byte[] getBalanceAccountStatement(@Nonnull Long profileId, @Nonnull Long accountId, @Nonnull OffsetDateTime intervalStart, @Nonnull OffsetDateTime intervalEnd, @Nonnull StatementType statementType, @Nonnull ResponseType responseType)  {
        if (Period.between(intervalStart.toLocalDate(), intervalEnd.toLocalDate()).getDays() > MAX_STATEMENT_PERIOD_DAYS) {
            throw new ValidationException("Difference between start and end date is more than " + MAX_STATEMENT_PERIOD_DAYS);
        }

        ResponseEntity<byte[]> bb = balanceAccountClient.getStatementByProfileIdAndIdAndType(profileId, accountId, responseType.getResponse(), intervalStart, intervalEnd, statementType);
        return new byte[5];
    }

    /**
     * Allows the conversion of funds between two STANDARD balance accounts in different currencies. You first must generate a quote with "payOut": "BALANCE".
     *
     * @param profileId used for conversion
     * @param idempotenceUUID used for any subsequent retry call in the case that the initial call fails
     * @param quoteId id of quote for which the conversion should apply
     * @return {@link ConversionResult} result of conversion
     */
    public ConversionResult convertBetweenBalances(@Nonnull Long profileId, @Nonnull UUID idempotenceUUID, @Nonnull Long quoteId)  {
        return balanceAccountClient.convertBetweenBalances(profileId, idempotenceUUID, quoteId);
    }

    /**
     * Allows the conversion of funds between two STANDARD balance accounts in different currencies. You first must generate a quote with "payOut": "BALANCE".
     *
     * @param profileId used for conversion
     * @param idempotenceUUID used for any subsequent retry call in the case that the initial call fails
     * @param quoteId id of quote for which the conversion should apply
     * @return {@link ConversionResult} result of conversion
     */
    public ConversionResult moveBetweenBalances(@Nonnull Long profileId, @Nonnull UUID idempotenceUUID, @Nonnull Long sourceBalanceId, @Nonnull Long targetBalanceId, @Nonnull Amount amount)  {
        MoveBetweenBalances moveBetweenBalances = new MoveBetweenBalances().setSourceBalanceId(sourceBalanceId)
                .setTargetBalanceId(targetBalanceId)
                .setAmount(amount);
        return balanceAccountClient.moveBetweenBalances(profileId, idempotenceUUID, moveBetweenBalances);
    }

    private String commaSeparatedTypes(BalanceAccountType... types) {
        return Arrays.stream(types).map(Enum::name).collect(Collectors.joining(","));
    }
}
