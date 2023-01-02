package cz.romanpecek.wiseapiclient.addresses.dto;

import cz.romanpecek.wiseapiclient.addresses.enums.AddressFieldTypeEnum;
import lombok.Data;

import java.util.Collection;

@Data
public class AddressRequirementGroup {
    private String key;
    private AddressFieldTypeEnum type;
    private Boolean refreshRequirementsOnChange;
    private Boolean required;
    private String displayFormat;
    private String example;
    private Integer minLength;
    private Integer maxLength;
    private String validationRegexp;
    private String validationAsync;
    private Collection<AddressFieldAllowedValue> valuesAllowed;
}
