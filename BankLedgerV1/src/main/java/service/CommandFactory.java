package service;

import lombok.NoArgsConstructor;
import repository.LedgerInMemory;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class CommandFactory {

    private static CommandFactory INSTANCE = null;

    public static CommandFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (CommandFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CommandFactory();
                }
            }
        }
        return INSTANCE;
    }

    public CommandBase getCommand(String command, LedgerInMemory ledgerInMemory) throws Exception {
        String[] parameters = command.split(" ");
        List<String> params = Arrays.asList(parameters);
        String firstParam = params.get(0);
        if (firstParam.equalsIgnoreCase("loan")) {
            return new LoanService(ledgerInMemory);
        }
        if (firstParam.equalsIgnoreCase("payment")) {
            return new PaymentService(ledgerInMemory);
        }
        if (firstParam.equalsIgnoreCase("balance")) {
            return new BalanceService(ledgerInMemory);
        }
        throw new Exception("Invalid command");
    }

}
