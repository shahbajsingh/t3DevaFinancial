package basic;

import java.sql.Timestamp;

import static basic.CONSTANTS.*;

public class Card {

    // CARD ATTRIBUTES

    private long card_no;
    private Timestamp issue_date, expiry_date, date_last_payment;
    private float balance, amt_last_payment;


    // CONSTRUCTORS

    // All cards are issued with a balance of 0.0f, and no last payment date or amount


    // Following constructor takes three parameters: card_no, issue_date, and expiry_date

    public Card(long card_no, Timestamp issue_date, Timestamp expiry_date){

        this.card_no = card_no;
        this.issue_date = issue_date;
        this.expiry_date = expiry_date;
        this.balance = 0.00f;
        this.date_last_payment = null;
        this.amt_last_payment = 0.00f;

    }

    // Following constructor takes two parameters: card_no and expiry date
    // Issue date is set to current date

    public Card(long card_no, Timestamp expiry_date){

            this.card_no = card_no;
            this.issue_date = CURRENT_TIME;
            this.expiry_date = expiry_date;
            this.balance = 0.00f;
            this.date_last_payment = null;
            this.amt_last_payment = 0.00f;

    }

    // Following constructor takes one parameter: card_no
    // Issue date is set to current date and expiry date is set to 1 year from now

    public Card(long card_no){

        this.card_no = card_no;
        this.issue_date = CURRENT_TIME;
        this.expiry_date = ONE_YEAR_FROM_NOW;
        this.balance = 0.00f;
        this.date_last_payment = null;
        this.amt_last_payment = 0.00f;

    }

    // Following constructor takes no parameters and sets all attributes to null or default
    // Note that card_no, issue_date, and expiry_date are defined as NON-NULL in the database

    public Card(){

        this.card_no = 0;
        this.issue_date = null;
        this.expiry_date = null;
        this.balance = 0.00f;
        this.date_last_payment = null;
        this.amt_last_payment = 0.00f;

    }


    // GETTERS

    public long getCardNo() {
        return card_no;
    }

    public Timestamp getIssueDate() {
        return issue_date;
    }

    public Timestamp getExpiryDate() {
        return expiry_date;
    }

    public float getBalance() {
        return balance;
    }

    public Timestamp getDateLastPayment() {
        return date_last_payment;
    }

    public float getAmtLastPayment() {
        return amt_last_payment;
    }


    // SETTERS

    public void setCardNo(long card_no) {
        this.card_no = card_no;
    }

    public void setIssueDate(Timestamp issue_date) {
        this.issue_date = issue_date;
    }

    public void setExpiryDate(Timestamp expiry_date) {
        this.expiry_date = expiry_date;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setDateLastPayment(Timestamp date_last_payment) {
        this.date_last_payment = date_last_payment;
    }

    public void setAmtLastPayment(float amt_last_payment) {
        this.amt_last_payment = amt_last_payment;
    }


    // METHODS

    @Override
    public String toString() {

        String ret =
                """
                \nCARD
                Card No.: %d
                Issue Date: %s
                Expiry Date: %s
                Balance: ₹%.2f
                Last Payment Date: %s
                Last Payment Amount: ₹%.2f
                """;

        return String.format(ret, card_no, issue_date, expiry_date, balance, date_last_payment, amt_last_payment);

    }

}
