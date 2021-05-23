package repository;

import dto.Borrower;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;


/**
 * This is a class to imitate in memory data for bank and borrower data which stores ledger info
 */
@NoArgsConstructor
@Getter
@Setter
public class LedgerInMemory {

    private HashMap<String, HashMap<String, Borrower>> loans = new HashMap<String, HashMap<String, Borrower>>();

}
