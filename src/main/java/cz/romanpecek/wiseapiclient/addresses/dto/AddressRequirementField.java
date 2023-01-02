package cz.romanpecek.wiseapiclient.addresses.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class AddressRequirementField {
    private String name;
    private Collection<AddressRequirementGroup> group;

}
