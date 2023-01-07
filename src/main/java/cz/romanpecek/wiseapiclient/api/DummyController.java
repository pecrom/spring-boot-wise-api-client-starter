package cz.romanpecek.wiseapiclient.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.i18n.CountryCode;
import cz.romanpecek.wiseapiclient.addresses.AddressService;
import cz.romanpecek.wiseapiclient.addresses.dto.OccupationFormatEnum;
import cz.romanpecek.wiseapiclient.addresses.dto.UserOccupation;
import cz.romanpecek.wiseapiclient.addresses.enums.USState;
import cz.romanpecek.wiseapiclient.clients.AddressClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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



    @GetMapping
    public String dummyTest() throws JsonProcessingException {
        return objectMapper.writeValueAsString(addressService.getAddressRequirements(Map.of("country", "us")));
    }
}