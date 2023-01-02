package cz.romanpecek.wiseapiclient.addresses;

import com.neovisionaries.i18n.CountryCode;
import cz.romanpecek.wiseapiclient.addresses.dto.Address;
import cz.romanpecek.wiseapiclient.addresses.dto.AddressDetail;
import cz.romanpecek.wiseapiclient.addresses.dto.NewAddress;
import cz.romanpecek.wiseapiclient.addresses.dto.UserOccupation;
import cz.romanpecek.wiseapiclient.addresses.enums.State;
import cz.romanpecek.wiseapiclient.clients.AddressClient;
import cz.romanpecek.wiseapiclient.config.AddressConfig;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Collection;
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

        NewAddress address = NewAddress.builder()
                        .profile(profileId)
                        .detail(detail)
                        .build();

        return addressClient.create(address);
    }

    /**
     * Updates address info to user profile.
     * @return {@link Address}
     */
    public Address update(@Nonnull Long profileId, @Nonnull CountryCode country, @Nonnull String firstLine, @Nonnull String postCode, @Nonnull String city, @Nullable State state, @Nullable Collection<UserOccupation> occupations) {
        return create(profileId, country, firstLine, postCode, city, state, occupations);
    }

    public Optional<Address> getById(@Nonnull Long addressId) {
        return Optional.empty();
//        addressClient.getById(addressId)
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
