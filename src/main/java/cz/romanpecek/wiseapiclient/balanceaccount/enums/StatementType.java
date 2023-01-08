package cz.romanpecek.wiseapiclient.balanceaccount.enums;

public enum StatementType {

    /**
     * Single statement line per transaction
     */
    COMPACT,

    /**
     * Accounting statements where transaction fees are on a separate line.
     */
    FLAT
}
