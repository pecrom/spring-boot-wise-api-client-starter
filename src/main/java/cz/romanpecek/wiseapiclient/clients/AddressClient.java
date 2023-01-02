package cz.romanpecek.wiseapiclient.clients;

import cz.romanpecek.wiseapiclient.addresses.dto.Address;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressRequirement;
import cz.romanpecek.wiseapiclient.addresses.dto.NewAddress;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressRequirementDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@FeignClient(name = "addresses", url = "${randomjokeapi.url}")
public interface AddressClient {

    @PostMapping()
    Address create(NewAddress newAddress);

    ResponseEntity<Address> getById(Long addressId);

    ResponseEntity<Collection<Address>> getAllByProfileId(Long profileId);

    Collection<AddressRequirement> getAddressRequirements();

    Collection<AddressRequirement> getAddressRequirements(AddressRequirementDetails addressRequirementDetails);
}
