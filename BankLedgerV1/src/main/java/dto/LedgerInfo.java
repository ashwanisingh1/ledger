package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents info about a loan given to a person from a bank
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LedgerInfo {

    private String bankName;
    private String borrowerName;
    private double principalAmount;
    private double interest;
    private int years;
    private int totalAmount;
    private int lastEmiPaid;
    private int emiAmount;
    private List<Integer> emiPayment = new ArrayList<Integer>();

    public LedgerInfo(String bankName, String borrowerName, double principalAmount, double interest, int years) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principalAmount = principalAmount;
        this.interest = interest;
        this.years = years;
        this.totalAmount = (int)Math.ceil(1.0*this.principalAmount+this.principalAmount*this.years*(this.interest/100));;
        this.lastEmiPaid = 0;
        this.emiPayment = new ArrayList<Integer>();
        this.emiAmount = (int)Math.ceil(1.0*this.totalAmount/(this.years*12));
        for (int arrSize = 0; arrSize < this.years*12 ; arrSize++) {
            emiPayment.add(this.emiAmount);
        }
    }

    /**
     * This function takes account of lump sump payment and changes the list of emi
     * @param amount
     * @param emiNumber
     * @throws Exception
     */
    public void lumpSumpPayment(int amount, int emiNumber) throws Exception {
        if (this.lastEmiPaid>=emiNumber) {
            throw new Exception("Invalid payment");
        }
        this.lastEmiPaid = emiNumber;
        int oldEmiScheme = emiPayment.get(emiNumber-1);
        int totalAmountPaid = amountPaid(emiNumber);
        if (totalAmountPaid + amount > this.totalAmount) {
            throw new Exception("Invalid payment as it exceeds total payment");
        }
        emiPayment.add(emiNumber-1, oldEmiScheme + amount);
    }

    /**
     * This function is for total amount paid till now this emi number
     * @param emiNumber
     * @return
     */
    public int amountPaid(int emiNumber) {
        int paidAmount = 0;
        for (int arrSize = 0; arrSize < emiNumber ; arrSize++) {
            paidAmount = paidAmount + this.emiPayment.get(arrSize);
        }
        return Math.min(paidAmount, this.totalAmount);
    }

    /**
     * This function tells the number of emi left uptill this emi number
     * @param emiNumber
     * @return
     */
    public int emiLeft(int emiNumber) {
        int totalAmountPaid = amountPaid(emiNumber);
        int emiLeft = (int)Math.ceil((1.0*(this.totalAmount - totalAmountPaid))/(this.emiAmount));
        return emiLeft;
    }

}
