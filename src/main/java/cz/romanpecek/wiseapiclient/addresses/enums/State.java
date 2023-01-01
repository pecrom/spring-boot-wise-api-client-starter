package cz.romanpecek.wiseapiclient.addresses.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class State {

    private final String name;
    private final String alpha2;

    public static State getInstance(String name, String alpha2) {
        return new State(name, alpha2);
    }
}

//   AL("Alabama", "AL"),
//    AK("Alaska", "AK"),
//    AS("American Samoa", "AS"),
//    AZ("Arizona", "AZ"),
//    AR("Arkansas", "AR"),
//    CA("California", "CA"),
//    CO("Colorado", "CO"),
//    CT("Connecticut", "CT"),
//    DE("Delaware", "DE"),
//    DC("District of Columbia", "DC"),
//    FM("Federated States of Micronesia", "FM"),
//    FL("Florida", "FL"),
//    GA("Georgia", "GA"),
//    GU("Guam", "GU"),
//    HI("Hawaii", "HI"),
//    ID("Idaho", "ID"),
//    IL("Illinois", "IL"),
//    IN("Indiana", "IN"),
//    IA("Iowa", "IA"),
//    KS("Kansas", "KS"),
//    KY("Kentucky", "KY"),
//    LA("Louisiana", "LA"),
//    ME("Maine", "ME"),
//    MD("Maryland", "MD"),
//    MH("Marshall Islands", "MH"),
//    MA("Massachusetts", "MA"),
//    MI("Michigan", "MI"),
//    MN("Minnesota", "MN"),
//    MS("Mississippi", "MS"),
//    MO("Missouri", "MO"),
//    MT("Montana", "MT"),
//    NE("Nebraska", "NE"),
//    NV("Nevada","NV"),
//    NH("New Hampshire", "NH"),
//    NJ("New Jersey", "NJ"),
//    NM("New Mexico", "NM"),
//    NY("New York", "NY"),
//    NC("North Carolina", "NC"),
//    ND("North Dakota", "ND"),
//    MP("Northern Mariana Islands", "MP"),
//    OH("Ohio", "OH"),
//    OK("Oklahoma", "OK"),
//    OR("Oregon", "OR"),
//    PW("Palau", "PW"),
//    PA("Pennsylvania", "PA"),
//    PR("Puerto Rico", "PR"),
//    RI("Rhode Island", "RI"),
//    SC("South Carolina", "SC"),
//    SD("South Dakota", "SD"),
//    TN("Tennessee", "TN"),
//    TX("Texas", "TX"),
//    UT("Utah", "UT"),
//    VT("Vermont", "VT"),
//    VI("Virgin Islands", "VI"),
//    VA("Virginia", "VA"),
//    WA("Washington", "WA"),
//    WV("West Virginia", "WV"),
//    WI("Wisconsin", "WI"),
//    WY("Wyoming", "WY");
