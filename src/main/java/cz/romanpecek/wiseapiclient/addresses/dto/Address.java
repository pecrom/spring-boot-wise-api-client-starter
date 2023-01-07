package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Address extends NewAddress {
    /**
     * Address id
     */
    private Long id;

}
