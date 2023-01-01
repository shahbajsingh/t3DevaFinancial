package basic;

import java.sql.Timestamp;

public class Payment {

    // Note that interest rate is calculated as a percentage of the loan amount by the employee
    // and total accrued interest will either be calculated at time of payment or per unit of time

    private final Timestamp CURRENT_TIME = new Timestamp(System.currentTimeMillis());

    // PAYMENT ATTRIBUTES

    private long payment_id, card_no, loan_id;
    private Timestamp date_payment;
    private float amt_payment;


    // CONSTRUCTORS

    // Following constructor takes all parameters

    public Payment(long payment_id, Timestamp date_payment, float amt_payment, long card_no, long loan_id){

        this.payment_id = payment_id;
        this.date_payment = date_payment;
        this.amt_payment = amt_payment;
        this.card_no = card_no;
        this.loan_id = loan_id;

    }

    // Following constructor takes four parameters: payment_id, amt_payment, card_no, and loan_id
    // date_payment is set to current date

    public Payment(long payment_id, float amt_payment, long card_no, long loan_id){

        this.payment_id = payment_id;
        this.date_payment = CURRENT_TIME;
        this.amt_payment = amt_payment;
        this.card_no = card_no;
        this.loan_id = loan_id;

    }

    // Following constructor takes payment_id and card_no as parameters
    // Payment is set to current date, amount is set to 0.0f, and loan_id is set to 0

    public Payment(long payment_id, long card_no){

        this.payment_id = payment_id;
        this.date_payment = CURRENT_TIME;
        this.amt_payment = 0.00f;
        this.card_no = card_no;
        this.loan_id = 0;

    }

    // Following constructor takes no parameters and sets all attributes to null or default
    // Note that all attributes are defined as NON-NULL in the database

    public Payment(){

        this.payment_id = 0;
        this.date_payment = CURRENT_TIME;
        this.amt_payment = 0.00f;
        this.card_no = 0;
        this.loan_id = 0;

    }


    // GETTERS

    public long getPaymentID(){
        return this.payment_id;
    }

    public Timestamp getDatePayment(){
        return this.date_payment;
    }

    public float getAmtPayment(){
        return this.amt_payment;
    }

    public long getCardNo(){
        return this.card_no;
    }

    public long getLoanID(){
        return this.loan_id;
    }


    // SETTERS

    public void setPaymentID(long payment_id){
        this.payment_id = payment_id;
    }

    public void setDatePayment(Timestamp date_payment){
        this.date_payment = date_payment;
    }

    public void setAmtPayment(float amt_payment){
        this.amt_payment = amt_payment;
    }

    public void setCardNo(long card_no){
        this.card_no = card_no;
    }

    public void setLoanID(long loan_id){
        this.loan_id = loan_id;
    }


    // METHODS

    public String toString(){

        String ret =
                """
                PAYMENT
                Payment ID: %d
                Payment Date: %s
                Payment Amount: ₹%.2f
                Card Number: %d
                Loan ID: %d
                """;

        return String.format(ret, this.payment_id, this.date_payment, this.amt_payment, this.card_no, this.loan_id);

    }

}
