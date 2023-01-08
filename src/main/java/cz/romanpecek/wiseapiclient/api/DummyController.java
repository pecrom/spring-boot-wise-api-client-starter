package cz.romanpecek.wiseapiclient.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.i18n.CurrencyCode;
import cz.romanpecek.wiseapiclient.addresses.AddressService;
import cz.romanpecek.wiseapiclient.balanceaccount.BalanceAccountService;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.BalanceAccountType;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.ResponseType;
import cz.romanpecek.wiseapiclient.balanceaccount.enums.StatementType;
import cz.romanpecek.wiseapiclient.clients.AddressClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/dummy")
@RequiredArgsConstructor
@Slf4j
public class DummyController {

    @Value("${wise.api.defaultProfile}")
    private Long profileId;
    private final AddressService addressService;

    private final AddressClient addressClient;

    private final ObjectMapper objectMapper;

    private final BalanceAccountService balanceAccountService;


    @GetMapping
    public String dummyTest() throws JsonProcessingException {

        return objectMapper.writeValueAsString(balanceAccountService.getBalanceAccountStatement(profileId,  133669L, OffsetDateTime.now().minusDays(365), OffsetDateTime.now(), StatementType.COMPACT, ResponseType.JSON));
    }
}