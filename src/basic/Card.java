package basic;

import java.sql.Timestamp;

public class Card {

    private final long SECONDS_IN_A_MONTH = 2592000L;

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
        this.balance = 0.0f;
        this.date_last_payment = null;
        this.amt_last_payment = 0.0f;

    }

    // Following constructor takes two parameters: card_no and expiry date
    // Issue date is set to current date

    public Card(long card_no, Timestamp expiry_date){

            this.card_no = card_no;
            this.issue_date = new Timestamp(System.currentTimeMillis());
            this.expiry_date = expiry_date;
            this.balance = 0.0f;
            this.date_last_payment = null;
            this.amt_last_payment = 0.0f;

    }

    // Following constructor takes one parameter: card_no
    // Issue date is set to current date and expiry date is set to 1 year from now

    public Card(long card_no){

        this.card_no = card_no;
        this.issue_date = new Timestamp(System.currentTimeMillis());
        this.expiry_date = new Timestamp(System.currentTimeMillis() + (SECONDS_IN_A_MONTH * 12));
        this.balance = 0.0f;
        this.date_last_payment = null;
        this.amt_last_payment = 0.0f;

    }


    // GETTERS

    public long getCard_no() {
        return card_no;
    }

    public Timestamp getIssue_date() {
        return issue_date;
    }

    public Timestamp getExpiry_date() {
        return expiry_date;
    }

    public float getBalance() {
        return balance;
    }

    public Timestamp getDate_last_payment() {
        return date_last_payment;
    }

    public float getAmt_last_payment() {
        return amt_last_payment;
    }


    // SETTERS

    public void setCard_no(long card_no) {
        this.card_no = card_no;
    }

    public void setIssue_date(Timestamp issue_date) {
        this.issue_date = issue_date;
    }

    public void setExpiry_date(Timestamp expiry_date) {
        this.expiry_date = expiry_date;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setDate_last_payment(Timestamp date_last_payment) {
        this.date_last_payment = date_last_payment;
    }

    public void setAmt_last_payment(float amt_last_payment) {
        this.amt_last_payment = amt_last_payment;
    }


    // METHODS

    public String toString() {

        String ret = 
                """
                CARD NUMBER: %d
                Issue Date: %s
                Expiry Date: %s
                Balance: %.2f
                Last Payment Date: %s
                Last Payment Amount: %.2f
                ----------------------------------------
                """;

        return String.format(ret, card_no, issue_date, expiry_date, balance, date_last_payment, amt_last_payment);

    }

}
