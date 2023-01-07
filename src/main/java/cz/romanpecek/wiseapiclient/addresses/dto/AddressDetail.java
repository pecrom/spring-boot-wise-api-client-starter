package cz.romanpecek.wiseapiclient.addresses.dto;

import com.neovisionaries.i18n.CountryCode;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class AddressDetail {
    /**
     * 2 digit ISO country code.
     */
    private CountryCode country;
    /**
     * Address line: street, house, apartment.
     */
    private String firstLine;
    /**
     * Zip code
     */
    private String postCode;
    /**
     * City name
     */
    private String city;
    /**
     * State code. Required if country is US, CA, AU, BR
     */
    private String state;
    /**
     * User occupations
     */
    private Collection<UserOccupation> occupations;
}
