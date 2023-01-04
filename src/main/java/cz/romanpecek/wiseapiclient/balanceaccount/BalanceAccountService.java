package cz.romanpecek.wiseapiclient.balanceaccount;

import com.neovisionaries.i18n.CurrencyCode;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.BalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.DeletedBalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.NewBalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.BalanceAccountType;
import cz.romanpecek.wiseapiclient.clients.BalanceAccountClient;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceAccountService {

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
    public BalanceAccount create(@Nonnull Long profileId, String name, @Nonnull CurrencyCode currency, @Nonnull BalanceAccountType type) {
        if (BalanceAccountType.SAVINGS.equals(type) && StringUtils.isEmpty(name)) {
            throw new ValidationException("For SAVING account type, name is also required");
        }

        NewBalanceAccount newBalanceAccount = NewBalanceAccount.builder().name(name)
                                                                            .currency(currency)
                                                                            .type(type)
                                                                            .build();

        return balanceAccountClient.create(profileId, newBalanceAccount);
    }

    /**
     * Returns all balance accounts the profile has in the types specified.
     *
     * @param profileId for which profile the balance accounts should be returned
     * @param types types of the balance accounts which should be returned
     * @return {@link Collection<BalanceAccount>} collection of Balance accounts
     */
    public Collection<BalanceAccount> getAllByProfileIdAndTypes(@Nonnull Long profileId, @Nonnull Collection<BalanceAccountType> types) {
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
    public DeletedBalanceAccount deleteByProfileIdAndId(@Nonnull Long profileId, @Nonnull Long accountId) {
        // @TODO add check that the amount is 0
        return balanceAccountClient.deleteByProfileIdAndId(profileId, accountId);
    }

    private String commaSeparatedTypes(Collection<BalanceAccountType> types) {
        return types.stream().map(Enum::name).collect(Collectors.joining(","));
    }
}
