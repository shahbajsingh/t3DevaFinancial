package basic;

import java.sql.Timestamp;

public class Payment {

    // Note that interest rate is calculated as a percentage of the loan amount by the employee
    // and total accrued interest will either be calculated at time of payment or per unit of time

    private final Timestamp CURRENT_TIME = new Timestamp(System.currentTimeMillis());

    // PAYMENT ATTRIBUTES

    private long payment_id, card_no, loan_id;
    private Timestamp payment_date;
    private float payment_value;


    // CONSTRUCTORS

    // Following constructor takes all parameters

    public Payment(long payment_id, Timestamp payment_date, long card_no, float payment_value, long loan_id){

        this.payment_id = payment_id;
        this.payment_date = payment_date;
        this.card_no = card_no;
        this.payment_value = payment_value;
        this.loan_id = loan_id;

    }

    // Following constructor takes four parameters: payment_id, card_no, payment_value and loan_id
    // payment_date is set to current date

    public Payment(long payment_id, long card_no, float payment_value, long loan_id){

        this.payment_id = payment_id;
        this.payment_date = CURRENT_TIME;
        this.card_no = card_no;
        this.payment_value = payment_value;
        this.loan_id = loan_id;

    }

    // Following constructor takes payment_id and card_no as parameters
    // Payment is set to current date, amount is set to 0.0f, and loan_id is set to 0

    public Payment(long payment_id, long card_no){

        this.payment_id = payment_id;
        this.payment_date = CURRENT_TIME;
        this.card_no = card_no;
        this.payment_value = 0.00f;
        this.loan_id = 0;

    }

    // Following constructor takes no parameters and sets all attributes to null or default
    // Note that all attributes are defined as NON-NULL in the database

    public Payment(){

        this.payment_id = 0;
        this.payment_date = CURRENT_TIME;
        this.card_no = 0;
        this.payment_value = 0.00f;
        this.loan_id = 0;

    }


    // GETTERS

    public long getPaymentID(){
        return this.payment_id;
    }

    public long getCardNo(){
        return this.card_no;
    }

    public Timestamp getPaymentDate(){
        return this.payment_date;
    }

    public float getPaymentValue(){
        return this.payment_value;
    }

    public long getLoanID(){
        return this.loan_id;
    }


    // SETTERS

    public void setPaymentID(long payment_id){
        this.payment_id = payment_id;
    }

    public void setPaymentDate(Timestamp payment_date){
        this.payment_date = payment_date;
    }

    public void setCardNo(long card_no){
        this.card_no = card_no;
    }

    public void setPaymentValue(float payment_value){
        this.payment_value = payment_value;
    }

    public void setLoanID(long loan_id){
        this.loan_id = loan_id;
    }


    // METHODS

    public String toString(){

        String ret =
                """
                \nPAYMENT
                Payment ID: %d
                Payment Date: %s
                Card Number: %d
                Payment Value: ₹%.2f
                Loan ID: %d
                """;

        return String.format(ret, this.payment_id, this.payment_date, this.card_no, this.payment_value, this.loan_id);

    }

}
