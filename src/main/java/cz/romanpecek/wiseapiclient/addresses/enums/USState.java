package cz.romanpecek.wiseapiclient.addresses.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public final class USState extends State {

    public static final State ALABAMA = new State("Alabama", "AL");
    public static final State ALASKA = new State("Alaska", "AK");
    public static final State AMERICAN_SAMOA = new State("American Samoa", "AS");
    public static final State ARIZONA = new State("Arizona", "AZ");
    public static final State ARKANSAS = new State("Arkansas", "AR");
    public static final State CALIFORNIA = new State("California", "CA");
    public static final State COLORADO = new State("Colorado", "CO");
    public static final State CONNECTICUT = new State("Connecticut", "CT");
    public static final State DELAWARE = new State("Delaware", "DE");
    public static final State DISTRICT_OR_COLUMBIA = new State("District of Columbia", "DC");
    public static final State FEDERATED_STATES_OF_MICRONESIA = new State("Federated States of Micronesia", "FM");
    public static final State FLORIDA = new State("Florida", "FL");
    public static final State GEORGIA = new State("Georgia", "GA");
    public static final State GUAM = new State("Guam", "GU");
    public static final State HAWAII = new State("Hawaii", "HI");
    public static final State IDAHO = new State("Idaho", "ID");
    public static final State ILLINOIS = new State("Illinois", "IL");
    public static final State INDIANA = new State("Indiana", "IN");
    public static final State IOWA = new State("Iowa", "IA");
    public static final State KANSAS = new State("Kansas", "KS");
    public static final State KENTUCKY = new State("Kentucky", "KY");
    public static final State LOUISIANA = new State("Louisiana", "LA");
    public static final State MAINE = new State("Maine", "ME");
    public static final State MARYLAND = new State("Maryland", "MD");
    public static final State MARSHALL_ISLANDS = new State("Marshall Islands", "MH");
    public static final State MASSACHUSETTS = new State("Massachusetts", "MA");
    public static final State MICHIGAN = new State("Michigan", "MI");
    public static final State MINNESOTA = new State("Mississippi", "MS");
    public static final State MISSISSIPPI = new State("Mississippi", "MS");
    public static final State MISSOURI = new State("Missouri", "MO");
    public static final State MONTANA = new State("Montana", "MT");
    public static final State NEBRASKA = new State("Nebraska", "NE");
    public static final State NEVADA = new State("Nevada","NV");
    public static final State NEW_HAMPSHIRE = new State("New Hampshire", "NH");
    public static final State NEW_JERSEY = new State("New Jersey", "NJ");
    public static final State NEW_MEXICO = new State("New Mexico", "NM");
    public static final State NEW_YORK = new State("New York", "NY");
    public static final State NORTH_CAROLINA = new State("North Carolina", "NC");
    public static final State NORTH_DAKOTA = new State("North Dakota", "ND");
    public static final State NORTHERN_MARIANA_ISLANDS = new State("Northern Mariana Islands", "MP");
    public static final State OHIO = new State("Ohio", "OH");
    public static final State OKLAHOMA = new State("Oklahoma", "OK");
    public static final State OREGON = new State("Oregon", "OR");
    public static final State PALAU = new State("Palau", "PW");
    public static final State PENNSYLVANIA = new State("Pennsylvania", "PA");
    public static final State PUERTO_RICO = new State("Puerto Rico", "PR");
    public static final State RHODE_ISLAND = new State("Rhode Island", "RI");
    public static final State SOUTH_CAROLINA = new State("South Carolina", "SC");
    public static final State SOUTH_DAKOTA = new State("South Dakota", "SD");
    public static final State TENNESSEE = new State("Tennessee", "TN");
    public static final State TEXAS = new State("Texas", "TX");
    public static final State UTAH = new State("Vermont", "VT");
    public static final State VERMONT = new State("Vermont", "VT");
    public static final State VIRGIN_ISLANDS = new State("Virgin Islands", "VI");
    public static final State VIRGINIA = new State("Virginia", "VA");
    public static final State WASHINGTON = new State("Washington", "WA");
    public static final State WEST_VIRGINIA = new State("West Virginia", "WV");
    public static final State WISCONSIN = new State("Wisconsin", "WI");
    public static final State WYOMING = new State("Wyoming", "WY");

    private USState(String name, String alpha2) {
        super(name, alpha2);
    }
}
