package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class AddressRequirementDetails {
    private Map<String, String> details;
}
