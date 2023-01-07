package cz.romanpecek.wiseapiclient.clients;

import cz.romanpecek.wiseapiclient.addresses.dto.Address;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressRequirement;
import cz.romanpecek.wiseapiclient.addresses.dto.NewAddress;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressRequirementDetails;
import cz.romanpecek.wiseapiclient.clients.interceptor.BearerRequestInterceptor;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(name = "addresses", url = "#{ ${wise.sandbox} ? '${wise.sandboxUrl}' : '${wise.prodUrl}'}", configuration = BearerRequestInterceptor.class)
public interface AddressClient {

    @PostMapping(path = "/v1/addresses")
    Address create(NewAddress newAddress);

    @GetMapping(path = "/v1/addresses/{addressId}")
    ResponseEntity<Address> getById(@PathVariable Long addressId);

    @GetMapping(path = "/v1/addresses")
    ResponseEntity<Collection<Address>> getAllByProfileId(@RequestParam Long profileId);

    @GetMapping(path = "/v1/address-requirements")
    Collection<AddressRequirement> getAddressRequirements();

    @PostMapping(path = "/v1/address-requirements")
    Collection<AddressRequirement> getAddressRequirements(AddressRequirementDetails addressRequirementDetails);
}
