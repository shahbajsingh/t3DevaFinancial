package basic;

import java.sql.Timestamp;

import static basic.CONSTANTS.*;

public class Loan {

    // Note that interest rate is calculated as a percentage of the loan value by the employee
    // and total accrued interest will either be calculated at time of payment or per unit of time

    // LOAN ATTRIBUTES

    private long loan_id, card_no;
    private Timestamp loan_date;
    private float loan_value, interest_rate, amt_remaining, interest_accrued;
    private boolean is_active;


    // CONSTRUCTORS

    // Following constructor takes all parameters

    public Loan(long loan_id, Timestamp loan_date, long card_no, float loan_value,
                float interest_rate, float amt_remaining, float interest_accrued, boolean is_active){

        this.loan_id = loan_id;
        this.loan_date = loan_date;
        this.card_no = card_no;
        this.loan_value = loan_value;
        this.interest_rate = interest_rate;
        this.amt_remaining = amt_remaining;
        this.interest_accrued = interest_accrued;
        this.is_active = is_active;

    }

    // Following constructor takes six parameters:
    // loan_id, card_no, loan_date, loan_value, amt_remaining, interest_accrued and interest rate
    // is_active is set to true

    public Loan(long loan_id, Timestamp loan_date, long card_no, float loan_value,
                float interest_rate, float amt_remaining, float interest_accrued){

        this.loan_id = loan_id;
        this.loan_date = loan_date;
        this.card_no = card_no;
        this.loan_value = loan_value;
        this.interest_rate = interest_rate;
        this.amt_remaining = amt_remaining;
        this.interest_accrued = interest_accrued;
        this.is_active = true;

    }

    // Following constructor takes loan_id and card_no as parameters
    // Issue date is set to current date, loan value is set to 0.0f,
    // interest rate is set to 0.0f, and loan is set to active

    public Loan(long loan_id, long card_no){

        this.loan_id = loan_id;
        this.loan_date = CURRENT_TIME;
        this.card_no = card_no;
        this.loan_value = 0.00f;
        this.interest_rate = 0.00f;
        this.amt_remaining = 0.00f;
        this.interest_accrued = 0.00f;
        this.is_active = true;

    }

    // Following constructor takes no parameters and sets all attributes to null or default
    // Note that loan_id, loan_date, card_no, loan_value, and interest_rate are defined as NON-NULL in the database

    public Loan(){

        this.loan_id = 0;
        this.loan_date = null;
        this.card_no = 0;
        this.loan_value = 0.00f;
        this.interest_rate = 0.00f;
        this.amt_remaining = 0.00f;
        this.interest_accrued = 0.00f;
        this.is_active = true;

    }


    // GETTERS

    public long getLoanID(){
        return this.loan_id;
    }

    public Timestamp getLoanDate(){
        return this.loan_date;
    }

    public long getCardNo(){
        return this.card_no;
    }

    public float getLoanValue(){
        return this.loan_value;
    }

    public float getInterestRate(){
        return this.interest_rate;
    }

    public float getAmtRemaining(){
        return this.amt_remaining;
    }

    public float getInterestAccrued(){
        return this.interest_accrued;
    }

    public boolean getIsActive(){
        return this.is_active;
    }


    // SETTERS

    public void setLoanID(long loan_id){
        this.loan_id = loan_id;
    }

    public void setLoanDate(Timestamp loan_date){
        this.loan_date = loan_date;
    }

    public void setCardNo(long card_no){
        this.card_no = card_no;
    }

    public void setLoanValue(float loan_value){
        this.loan_value = loan_value;
    }

    public void setInterestRate(float interest_rate){
        this.interest_rate = interest_rate;
    }

    public void setAmtRemaining(float amt_remaining){
        this.amt_remaining = amt_remaining;
    }

    public void setInterestAccrued(float interest_accrued){
        this.interest_accrued = interest_accrued;
    }

    public void setIsActive(boolean is_active){
        this.is_active = is_active;
    }


    // METHODS

    public String toString(){

        String ret =
                """
                \nLOAN
                Loan ID: %d
                Loan Date: %s
                Card No: %d
                Loan Value: ₹%.2f
                Interest Rate: %.2f
                Amount Remaining: ₹%.2f
                Interest Accrued: ₹%.2f
                Is Active: %s
                """;

        return String.format(ret, this.loan_id, this.loan_date, this.card_no, this.loan_value,
                this.interest_rate, this.amt_remaining, this.interest_accrued, (this.is_active ? "Yes" : "No"));

    }

}
