package cz.romanpecek.wiseapiclient.addresses;

import com.neovisionaries.i18n.CountryCode;
import cz.romanpecek.wiseapiclient.addresses.dto.Address;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressDetail;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressRequirement;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressRequirementDetails;
import cz.romanpecek.wiseapiclient.addresses.dto.NewAddress;
import cz.romanpecek.wiseapiclient.addresses.dto.UserOccupation;
import cz.romanpecek.wiseapiclient.addresses.enums.State;
import cz.romanpecek.wiseapiclient.clients.AddressClient;
import cz.romanpecek.wiseapiclient.config.AddressConfig;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressClient addressClient;
    private final AddressConfig addressConfig;


    /**
     * Adds address info to user profile.
     * @return {@link Address}
     */
    public Address create(@Nonnull Long profileId, @Nonnull CountryCode country, @Nonnull String firstLine, @Nonnull String postCode, @Nonnull String city, @Nullable State state, @Nullable Collection<UserOccupation> occupations) {
        if (isStateRequired(country) && state == null) {
            throw new ValidationException("State is required for country: " + country);
        }

        if (isOccupationRequired(country, state)) {
            throw new ValidationException("Occupation is required for country: " + country.getName() + " and state " + state.getName());
        }

        AddressDetail detail = createAddressDetail(country, state, city, firstLine, postCode, occupations);

        return addressClient.create(new NewAddress(profileId, detail));
    }

    /**
     * Updates address info to user profile.
     * @return {@link Address}
     */
    public Address update(@Nonnull Long profileId, @Nonnull CountryCode country, @Nonnull String firstLine, @Nonnull String postCode, @Nonnull String city, @Nullable State state, @Nullable Collection<UserOccupation> occupations) {
        return create(profileId, country, firstLine, postCode, city, state, occupations);
    }

    /**
     * Get address info by id.
     * @param addressId id of address
     * @return {@link Optional<Address>} if address is not found then Optional.empty() is returned
     */
    public Optional<Address> getById(@Nonnull Long addressId) {
        ResponseEntity<Address> response = addressClient.getById(addressId);

        Optional<Address> address = Optional.empty();
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            address = Optional.ofNullable(response.getBody());
        }

        return address;
    }

    /**
     * List of addresses belonging to user profile.
     * @param profileId id of profile
     * @return {@link Collection<Address>} Collection of addresses belonging to user profile
     */
    public Collection<Address> getAllByProfileId(@Nonnull Long profileId) {
        ResponseEntity<Collection<Address>> response = addressClient.getAllByProfileId(profileId);

        return HttpStatus.OK.equals(response.getStatusCode()) ? response.getBody() : CollectionUtils.emptyCollection();
    }

    /**
     * List of requirements for address fields
     * @return {@link Collection<AddressRequirement>} requirements for address fields
     */
    public Collection<AddressRequirement> getAddressRequirements() {
        return addressClient.getAddressRequirements();
    }

    /**
     * List of requirements for address fields based on provided fields and its values
     * @param fieldValues {@link Map<String, String>} provided fields and values
     * @return {@link Collection<AddressRequirement>} requirements for address fields
     */
    public Collection<AddressRequirement> getAddressRequirements(@Nonnull Map<String, String> fieldValues) {
        var addressRequirementDetails = new AddressRequirementDetails().setDetails(fieldValues);
        return addressClient.getAddressRequirements(addressRequirementDetails);
    }

    private AddressDetail createAddressDetail(CountryCode country, State state, String city, String firstLine, String postCode, Collection<UserOccupation> occupations) {

        return new AddressDetail().setCountry(country)
                                    .setState(state != null ? state.getAlpha2() : null)
                                    .setCity(city)
                                    .setFirstLine(firstLine)
                                    .setPostCode(postCode)
                                    .setOccupations(occupations);
    }

    private boolean isOccupationRequired(CountryCode countryCode, State state) {
        return addressConfig.getRequiresOccupationForCountry().contains(countryCode) ||
                (state != null && addressConfig.requiresOccupationForCountryStates(countryCode, state));
    }

    private boolean isStateRequired(CountryCode countryCode) {
        return addressConfig.getRequiresStateForCountry().contains(countryCode);
    }
}
