package cz.romanpecek.wiseapiclient.clients;

import com.neovisionaries.i18n.CurrencyCode;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.BalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.ConversionResult;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.DeletedBalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.MoveBetweenBalances;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.NewBalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.StatementType;
import cz.romanpecek.wiseapiclient.clients.interceptor.BearerRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;
import java.util.Collection;

@FeignClient(name = "balanceAccount", url = "#{ ${wise.sandbox} ? '${wise.sandboxUrl}' : '${wise.prodUrl}'}", configuration = BearerRequestInterceptor.class)
public interface BalanceAccountClient {

    @PostMapping(path = "/v3/profiles/{profileId}/balances")
    BalanceAccount create(@PathVariable Long profileId, @RequestBody NewBalanceAccount newBalanceAccount);

    @GetMapping(path = "/v4/profiles/{profileId}/balances?types={types}")
    ResponseEntity<Collection<BalanceAccount>> getAllByProfileIdAndTypes(@PathVariable Long profileId, @RequestParam String types);

    @GetMapping(path = "/v4/profiles/{profileId}/balances/{balanceId}")
    ResponseEntity<BalanceAccount> getByProfileIdAndId(@PathVariable Long profileId, @PathVariable Long balanceId);

    @DeleteMapping(path = "/v3/profiles/{profileId}/balances/{balanceId}")
    DeletedBalanceAccount deleteByProfileIdAndId(@RequestParam Long profileId, @RequestParam Long balanceId);

    @GetMapping(path = "/v1/profiles/{profileId}/balance-statements/{balanceId}/{responseType}")
    ResponseEntity<byte[]> getStatementByProfileIdAndIdAndType(@PathVariable Long profileId, @PathVariable Long balanceId, @PathVariable String responseType, @RequestParam CurrencyCode currency, @RequestParam OffsetDateTime intervalStart, @RequestParam OffsetDateTime intervalEnd, @RequestParam StatementType type);

    @PostMapping(path = "/v2/profiles/{profileId}/balance-movements")
    ConversionResult convertBetweenBalances(@PathVariable Long profileId, @RequestHeader(name = "X-idempotence-uuid") String idempotenceUUID, @RequestBody Long quoteId);

    @PostMapping(path = "/v2/profiles/{profileId}/balance-movements")
    ConversionResult moveBetweenBalances(@PathVariable Long profileId, @RequestHeader(name = "X-idempotence-uuid") String idempotenceUUID, @RequestBody MoveBetweenBalances moveBetweenBalances);
}
