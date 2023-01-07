package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
