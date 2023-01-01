package cz.romanpecek.wiseapiclient.clients;

import cz.romanpecek.wiseapiclient.addresses.dto.Address;
import cz.romanpecek.wiseapiclient.addresses.dto.NewAddress;

public interface AddressClient {

    Address create(NewAddress newAddress);

    Address update(NewAddress updateAddress);
}
