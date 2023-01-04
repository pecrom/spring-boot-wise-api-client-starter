package cz.romanpecek.wiseapiclient.clients;

import cz.romanpecek.wiseapiclient.balanceaccount.dto.BalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.DeletedBalanceAccount;
import cz.romanpecek.wiseapiclient.balanceaccount.dto.NewBalanceAccount;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(name = "balanceAccount", url = "abc")
public interface BalanceAccountClient {

    @PostMapping(path = "/v1/addresses")
    BalanceAccount create(@RequestParam Long profileId, @RequestBody NewBalanceAccount newBalanceAccount);

    @GetMapping(path = "/v1/addresses")
    ResponseEntity<Collection<BalanceAccount>> getAllByProfileIdAndTypes(@RequestParam Long profileId, @RequestParam String types);

    @GetMapping(path = "/v1/addresses")
    ResponseEntity<BalanceAccount> getByProfileIdAndId(@RequestParam Long profileId, @RequestParam Long accountId);

    @GetMapping(path = "/v1/addresses")
    DeletedBalanceAccount deleteByProfileIdAndId(@RequestParam Long profileId, @RequestParam Long accountId);
}
