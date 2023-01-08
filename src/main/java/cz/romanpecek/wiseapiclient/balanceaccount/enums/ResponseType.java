package cz.romanpecek.wiseapiclient.balanceaccount.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseType {
    JSON("statement.json"),
    CSV("statement.csd"),
    PDF("statement.pdf");

    private final String response;
}
