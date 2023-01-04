package cz.romanpecek.wiseapiclient.clients;

import cz.romanpecek.wiseapiclient.addresses.dto.Address;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressRequirement;
import cz.romanpecek.wiseapiclient.addresses.dto.NewAddress;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressRequirementDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@FeignClient(name = "addresses", url = "abc")
public interface AddressClient {

    @PostMapping(path = "/v1/addresses")
    Address create(NewAddress newAddress);

    @PostMapping(path = "/v1/addresses")
    ResponseEntity<Address> getById(Long addressId);

    @PostMapping(path = "/v1/addresses")
    ResponseEntity<Collection<Address>> getAllByProfileId(Long profileId);

    @PostMapping(path = "/v1/addresses")
    Collection<AddressRequirement> getAddressRequirements();

    @PostMapping(path = "/v1/addresses")
    Collection<AddressRequirement> getAddressRequirements(AddressRequirementDetails addressRequirementDetails);
}
