package cz.romanpecek.wiseapiclient.config;

import com.neovisionaries.i18n.CountryCode;
import cz.romanpecek.wiseapiclient.addresses.enums.State;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Data
public class AddressConfig {

    private Set<CountryCode> requiresStateForCountry = Collections.emptySet();
    private Set<CountryCode> requiresOccupationForCountry = Collections.emptySet();
    private Map<CountryCode, Set<String>> requiresOccupationForCountryStates = Collections.emptyMap();

    public boolean requiresOccupationForCountryStates(@NotNull CountryCode countryCode, @NotNull State state) {
        return requiresOccupationForCountryStates.getOrDefault(countryCode, Collections.emptySet())
                                                    .contains(state.getAlpha2());
    }
}
