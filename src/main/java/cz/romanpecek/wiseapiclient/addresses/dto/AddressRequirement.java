package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class AddressRequirement {
    private String type;
    private Collection<AddressRequirementField> fields;
}
