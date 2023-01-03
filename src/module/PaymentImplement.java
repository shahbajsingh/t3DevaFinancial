package module;

import basic.DatabaseConnection;
import basic.Payment;
import basic.QUERY;
import dao.PaymentDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static basic.CONSTANTS.*;

public class PaymentImplement implements PaymentDAO {

    static float money_used;

    public PaymentImplement() {
        super();
    }

    @Override
    public void addPayment(long card_no, float payment_value) throws SQLException {

        Timestamp payment_date = CURRENT_TIME;
        float full_payment_value = payment_value;
        float leftover = full_payment_value;
        long loan_id = 0L;
        float loan_remaining = 0.00f;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        String sql1 = String.format(QUERY.getOldestActiveLoanIDByCardNo, card_no);

        ResultSet rs = c.selectSQL(sql1);

        if(rs.next()) {
            loan_id = rs.getLong("loan_id");
            loan_remaining = rs.getFloat("amt_remaining");

            if(payment_value > loan_remaining) {
                full_payment_value = loan_remaining;
            }

            String sql2 = String.format(QUERY.addPayment, payment_date, card_no, full_payment_value, loan_id);

            c.executeSQL(sql2);
        }

        money_used += full_payment_value;

        leftover = connectPaymentToLoan(payment_value, card_no, loan_id, loan_remaining);

        if(leftover > 0.00f && loan_id != 0L) {
            addPayment(card_no, leftover);
        }

        CardImplement tempCard = new CardImplement();
        tempCard.setDateAndAmountLastPayment(payment_date, payment_value, card_no);

        System.out.println("Payment of ₹" + money_used + " made under card number " + card_no);

        c.closeConnection();

    }

    @Override
    public void deletePayment(long payment_id) throws SQLException {

        String sql = String.format(QUERY.deletePayment, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println("Payment with ID " + payment_id + " deleted from database");

        c.closeConnection();

    }

    @Override
    public boolean checkPaymentExists(long payment_id) throws SQLException {

        String sql = String.format(QUERY.checkPaymentExists, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        boolean exists = rs.next();

        c.closeConnection();

        return exists;

    }

    // RESULT SET METHODS
    // These methods are used to return a ResultSet object to the calling method for further processing
    // and will be used to construct table models to populate the GUI

    @Override
    public ResultSet getPaymentInfoByID(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentInfoByID, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;


    }

    @Override
    public ResultSet getPaymentsByCardNo(long card_no) throws SQLException {

        String sql = String.format(QUERY.getPaymentsByCardNo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getPaymentsInDateRangeByCardNo(long card_no, String start_date,
                                                    String end_date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsInDateRangeByCardNo, card_no, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getPaymentsBeforeDateByCardNo(long card_no, String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getPaymentsBeforeDateByCardNo, card_no, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getPaymentsAfterDateByCardNo(long card_no, String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getPaymentsAfterDateByCardNo, card_no, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getPaymentsByLoanID(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentsByLoanID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getPaymentsInDateRangeByLoanID(long loan_id, String start_date,
                                                    String end_date) throws SQLException {

    String sql = String.format(QUERY.getPaymentsInDateRangeByLoanID, loan_id, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getPaymentsBeforeDateByLoanID(long loan_id, String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getPaymentsBeforeDateByLoanID, loan_id, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getPaymentsAfterDateByLoanID(long loan_id, String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getPaymentsAfterDateByLoanID, loan_id, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllPayments() throws SQLException {

        String sql = QUERY.getAllPayments;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllPaymentsInDateRange(String start_date, String end_date) throws SQLException {

        String sql = String.format(QUERY.getAllPaymentsInDateRange, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllPaymentsBeforeDate(String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getAllPaymentsBeforeDate, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllPaymentsAfterDate(String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getAllPaymentsAfterDate, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    // STRING METHODS
    // These methods are used in command line tests and populating GUI text fields
    // They return a String or string representation of an object to the calling method

    @Override
    public String getPaymentDate(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentDateByID, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String date = rs.getTimestamp("payment_date").toString();

        c.closeConnection();

        return date;
    }

    @Override
    public String getPaymentCardNo(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentCardNoByID, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String card_no = String.valueOf(rs.getLong("card_no"));

        c.closeConnection();

        return card_no;

    }

    @Override
    public String getPaymentValue(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentValueByID, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String value = String.valueOf(rs.getFloat("payment_value"));

        c.closeConnection();

        return value;

    }

    @Override
    public String getPaymentLoanID(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentLoanIDByID, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String loan_id = String.valueOf(rs.getLong("loan_id"));

        c.closeConnection();

        return loan_id;

    }

    public Payment getPaymentInfoByIDString(long payment_id) throws SQLException {

        Payment tempPayment = new Payment();
        String sql = String.format(QUERY.getPaymentInfoByID, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (!rs.next()) {
            System.out.println("No payment found with ID: " + payment_id);
        } else {
            do {
                tempPayment = convertRowToPayment(rs);
            } while (rs.next());
        }

        c.closeConnection();

        return tempPayment;

    }

    public String getPaymentsByCardNoString(long card_no) throws SQLException {

        String sql = String.format(QUERY.getPaymentsByCardNo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToPayment(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getPaymentsInDateRangeByCardNoString(long card_no, String start_date,
                                                       String end_date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsInDateRangeByCardNo, card_no, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToPayment(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getPaymentsByLoanIDString(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentsByLoanID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToPayment(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getPaymentsInDateRangeByLoanIDString(long loan_id, String start_date,
                                                       String end_date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsInDateRangeByLoanID, loan_id, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToPayment(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getAllPaymentsString() throws SQLException {

        String sql = QUERY.getAllPayments;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToPayment(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getAllPaymentsInDateRangeString(String start_date, String end_date) throws SQLException {

        String sql = String.format(QUERY.getAllPaymentsInDateRange, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToPayment(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    // HELPER METHODS

    @Override
    public float connectPaymentToLoan(float payment_value, long card_no,
                                       long oldest_loan_id, float amt_remaining) throws SQLException {

        CardImplement tempCard = new CardImplement();
        LoanImplement tempLoan = new LoanImplement();

        long loan_id = oldest_loan_id;
        float loan_remaining = amt_remaining;

        float leftover = payment_value;

            if (leftover >= loan_remaining){ // if the payment value is greater than the remaining loan amount

                leftover -= loan_remaining; // subtract the remaining loan amount from the payment value (leftover)

                tempCard.subtractBalanceFromCard(loan_remaining, card_no); // subtract from outstanding balance
                tempLoan.setLoanAmtRemaining(0.00f, loan_id); // set remaining loan amount to 0.00
                tempLoan.setLoanIsActive(false, loan_id); // set loan to inactive

            } else if (leftover < loan_remaining){ // if the payment value is less than the remaining loan amount

                tempCard.subtractBalanceFromCard(leftover, card_no);
                tempLoan.setLoanAmtRemaining(loan_remaining - leftover, loan_id);

                leftover = 0.00f;

            }

        return leftover;

    }

    @Override
    public Payment convertRowToPayment(ResultSet rs) throws SQLException {

        Payment tempPayment = new Payment();

        tempPayment.setPaymentID(rs.getLong("payment_id"));
        tempPayment.setPaymentDate(rs.getTimestamp("payment_date"));
        tempPayment.setCardNo(rs.getLong("card_no"));
        tempPayment.setPaymentValue(rs.getFloat("payment_value"));
        tempPayment.setLoanID(rs.getLong("loan_id"));

        return tempPayment;

    }
}
