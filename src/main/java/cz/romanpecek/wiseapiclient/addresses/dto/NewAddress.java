package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewAddress {

    /**
     * User profile id.
     */
    @NonNull
    protected Long profile;

    /**
     * Address details
     */
    @NonNull
    protected AddressDetail detail;
}
