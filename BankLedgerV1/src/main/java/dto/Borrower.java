package dto;

import lombok.Getter;

/**
 * This class is for borrowers which stores name and Ledger info about the loan information
 */
@Getter
public class Borrower {

    private String name;
    private LedgerInfo ledgerInfo;

    public Borrower(String name, LedgerInfo ledgerInfo) {
        this.name = name;
        this.ledgerInfo = ledgerInfo;
    }

}