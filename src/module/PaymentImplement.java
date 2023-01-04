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

    float money_used;

    public PaymentImplement() {
        super();
    }

    @Override
    public void addPayment(long card_no, float payment_value) throws SQLException {



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




    // GETTERS
    // These methods return the value of the specified column
    // in the actual data type specified in the database

    @Override
    public Timestamp getPaymentDate(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentDate, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        Timestamp date = null;

        if (rs.next()) {
            date = rs.getTimestamp("payment_date");
        }

        c.closeConnection();

        return date;

    }

    @Override
    public long getPaymentCardNo(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentCardNo, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        long card_no = 0L;

        if (rs.next()) {
            card_no = rs.getLong("card_no");
        }

        c.closeConnection();

        return card_no;

    }

    @Override
    public float getPaymentValue(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentValue, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        float payment_value = 0.00f;

        if (rs.next()) {
            payment_value = rs.getFloat("payment_value");
        }

        c.closeConnection();

        return payment_value;

    }

    @Override
    public long getPaymentLoanID(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentLoanID, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        long loan_id = 0L;

        if (rs.next()) {
            loan_id = rs.getLong("loan_id");
        }

        c.closeConnection();

        return loan_id;

    }




    // SETTERS
    // These methods update the value of the specified column
    // in the database using the passed value

    @Override
    public void setPaymentInfo(Payment payment) throws SQLException {

        long payment_id = payment.getPaymentID(); Timestamp payment_date = payment.getPaymentDate();
        long card_no = payment.getCardNo(); float payment_value = payment.getPaymentValue();
        long loan_id = payment.getLoanID();

        String sql = String.format(QUERY.setPaymentInfo, payment_date, card_no, payment_value, loan_id, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(
                String.format("Payment with ID %d updated in database\n", payment_id)
        );

        c.closeConnection();

    }

    @Override
    public void setPaymentDate(Timestamp payment_date, long payment_id) throws SQLException {

        String sql = String.format(QUERY.setPaymentDate, payment_date, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setPaymentCardNo(long card_no, long payment_id) throws SQLException {

        String sql = String.format(QUERY.setPaymentCardNo, card_no, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setPaymentValue(float payment_value, long payment_id) throws SQLException {

        String sql = String.format(QUERY.setPaymentValue, payment_value, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setPaymentLoanID(long loan_id, long payment_id) throws SQLException {

        String sql = String.format(QUERY.setPaymentLoanID, loan_id, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }




    // RESULT SET METHODS
    // These methods are used to return a ResultSet object to the calling method for further processing
    // and will be used to construct table models to populate the GUI

    @Override
    public ResultSet getPaymentInfo(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentInfo, payment_id);

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
    public String getPaymentDateString(long payment_id) throws SQLException {

        Timestamp payment_date = getPaymentDate(payment_id);
        return payment_date.toString();
    }

    @Override
    public String getPaymentCardNoString(long payment_id) throws SQLException {

        long card_no = getPaymentCardNo(payment_id);
        return Long.toString(card_no);

    }

    @Override
    public String getPaymentValueString(long payment_id) throws SQLException {


        float payment_value = getPaymentValue(payment_id);
        return Float.toString(payment_value);

    }

    @Override
    public String getPaymentLoanIDString(long payment_id) throws SQLException {

        long loan_id = getPaymentLoanID(payment_id);
        return Long.toString(loan_id);

    }

    public Payment getPaymentInfoString(long payment_id) throws SQLException {

        Payment tempPayment = new Payment();
        String sql = String.format(QUERY.getPaymentInfo, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()){
            tempPayment = convertRowToPayment(rs);
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


        return 0;

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
