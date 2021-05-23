package service;

import dto.Borrower;
import dto.LedgerInfo;
import lombok.AllArgsConstructor;
import repository.LedgerInMemory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class PaymentService implements CommandBase {

    LedgerInMemory ledgerInMemory;

    public void execute(String command) throws Exception{
        String[] parameters = command.split(" ");
        List<String> params = Arrays.asList(parameters);
        this.takePayment(params.get(1), params.get(2), Integer.parseInt(params.get(3)), Integer.parseInt(params.get(4)));
    }

    public void takePayment(String bankName, String borrowerName, int lumpSumpAmount, int emiNumber) throws Exception {
        HashMap<String, HashMap<String, Borrower>> ledgerRecord = ledgerInMemory.getLoans();
        if (ledgerRecord.containsKey(bankName)) {
            if (ledgerRecord.get(bankName).containsKey(borrowerName)) {
                LedgerInfo ledgerInfo = ledgerRecord.get(bankName).get(borrowerName).getLedgerInfo();
                ledgerInfo.lumpSumpPayment(lumpSumpAmount, emiNumber);
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

}
