package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
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
    protected AddressDetail details;
}
