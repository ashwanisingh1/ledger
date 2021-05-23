package service;

import dto.Borrower;
import dto.LedgerInfo;
import lombok.AllArgsConstructor;
import repository.LedgerInMemory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class BalanceService implements CommandBase {

    LedgerInMemory ledgerInMemory;

    public void execute(String command) throws Exception{
        String[] parameters = command.split(" ");
        List<String> params = Arrays.asList(parameters);
        this.getBalance(params.get(1), params.get(2), Integer.parseInt(params.get(3)));
    }

    public void getBalance(String bankName, String borrowerName, int emiNumber) throws Exception{
        HashMap<String, HashMap<String, Borrower>> ledgerRecord = ledgerInMemory.getLoans();
        if (ledgerRecord.containsKey(bankName)) {
            if (ledgerRecord.get(bankName).containsKey(borrowerName)) {
                LedgerInfo ledgerInfo = ledgerRecord.get(bankName).get(borrowerName).getLedgerInfo();
                int amountPaid = ledgerInfo.amountPaid(emiNumber);
                int emiLeft = ledgerInfo.emiLeft(emiNumber);
                System.out.println(bankName + " " + borrowerName + " " + amountPaid + " " + emiLeft);
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

}
