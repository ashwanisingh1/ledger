package service;

import dto.Borrower;
import dto.LedgerInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import repository.LedgerInMemory;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
public class LoanService implements CommandBase {

    LedgerInMemory ledgerInMemory;

    public void execute(String command) throws Exception {
        String[] parameters = command.split(" ");
        List<String> params = Arrays.asList(parameters);
        this.addLoan(params.get(1), params.get(2), Integer.parseInt(params.get(3)), Integer.parseInt(params.get(4)), Integer.parseInt(params.get(5)));
    }

    public void addLoan(String bankName, String borrowerName, int principal, int years, int interest) throws Exception {
        HashMap<String, HashMap<String, Borrower>> ledgerRecord = ledgerInMemory.getLoans();
        if (ledgerRecord.containsKey(bankName)) {
            if (ledgerRecord.get(bankName).containsKey(borrowerName)) {
                throw new Exception();
            } else {
                HashMap<String, Borrower> ledgerInfo = new HashMap<String, Borrower>();
                ledgerInfo.put(borrowerName, new Borrower(borrowerName, new LedgerInfo(bankName, borrowerName, principal, years, interest)));
                ledgerRecord.put(bankName, ledgerInfo);
            }
        } else {
            HashMap<String, Borrower> ledgerInfo = new HashMap<String, Borrower>();
            ledgerInfo.put(borrowerName, new Borrower(borrowerName, new LedgerInfo(bankName, borrowerName, principal, interest, years)));
            ledgerRecord.put(bankName, ledgerInfo);
        }
    }

}
