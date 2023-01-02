package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Setter
@Accessors(chain = true)
public class AddressRequirementDetails {
    private Map<String, String> details;
}
