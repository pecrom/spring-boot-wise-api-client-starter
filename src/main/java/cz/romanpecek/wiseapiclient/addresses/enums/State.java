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