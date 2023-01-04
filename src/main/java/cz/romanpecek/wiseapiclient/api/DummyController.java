package cz.romanpecek.wiseapiclient.api;

import cz.romanpecek.wiseapiclient.addresses.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
@RequiredArgsConstructor
@Slf4j
public class DummyController {
    private final AddressService addressService;

    @GetMapping
    public String dummyTest() {
        log.info("test");
        return "Test";
    }
}
