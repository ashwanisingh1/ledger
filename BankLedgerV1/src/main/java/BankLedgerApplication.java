import service.CommandBase;
import service.CommandFactory;
import repository.LedgerInMemory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BankLedgerApplication {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) throws IOException {
        File file=new File("src/main/resources/input.txt");
        FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        StringBuffer command=new StringBuffer();
        String line;
        CommandFactory factory = CommandFactory.getInstance();
        LedgerInMemory ledgerInMemory = new LedgerInMemory();
        try {
            while ((line = br.readLine()) != null) {
                command.append(line);
                CommandBase commandBase = factory.getCommand(command.toString(), ledgerInMemory);
                commandBase.execute(command.toString());
                command.delete(0, 100);

            }
        } catch (Exception e) {
            throw new IOException();
        }

    }
}