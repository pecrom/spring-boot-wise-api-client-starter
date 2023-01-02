package cz.romanpecek.wiseapiclient.clients;

import cz.romanpecek.wiseapiclient.balanceaccount.dto.BalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.DeletedBalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.NewBalanceAccount;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface BalanceAccountClient {

    BalanceAccount create(Long profileId, NewBalanceAccount newBalanceAccount);

    ResponseEntity<Collection<BalanceAccount>> getAllByProfileIdAndTypes(Long profileId, String types);

    ResponseEntity<BalanceAccount> getByProfileIdAndId(Long profileId, Long accountId);

    DeletedBalanceAccount deleteByProfileIdAndId(Long profileId, Long accountId);
}
