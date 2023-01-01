package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.Data;

@Data
public class UserOccupation {
    /**
     * User occupation
     */
    private String code;
    /**
     * Occupation type
     */
    private OccupationFormatEnum format;
}
