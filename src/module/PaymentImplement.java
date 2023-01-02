package module;

import basic.DatabaseConnection;
import basic.Payment;
import basic.QUERY;
import dao.PaymentDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentImplement implements PaymentDAO {


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

    // RESULT SET METHODS
    // These methods are used to return a ResultSet object to the calling method for further processing
    // and will be used to construct table models to populate the GUI

    @Override
    public ResultSet getPaymentInfoByID(long payment_id) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPaymentsByCardNo(long card_no) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPaymentsInDateRangeByCardNo(long card_no, String start_date, String end_date) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPaymentsBeforeDateByCardNo(long card_no, String date) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPaymentsAfterDateByCardNo(long card_no, String date) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPaymentsByLoanID(long loan_id) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPaymentsInDateRangeByLoanID(long loan_id, String start_date, String end_date) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPaymentsBeforeDateByLoanID(long loan_id, String date) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPaymentsAfterDateByLoanID(long loan_id, String date) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getAllPayments() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getAllPaymentsInDateRange(String start_date, String end_date) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getAllPaymentsBeforeDate(String date) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getAllPaymentsAfterDate(String date) throws SQLException {
        return null;
    }

    // STRING METHODS
    // These methods are used in command line tests and populating GUI text fields
    // They return a String or string representation of an object to the calling method

    @Override
    public String getPaymentDate(long payment_id) throws SQLException {
        return null;
    }

    @Override
    public String getPaymentCardNo(long payment_id) throws SQLException {
        return null;
    }

    @Override
    public String getPaymentValue(long payment_id) throws SQLException {
        return null;
    }

    @Override
    public String getPaymentLoanID(long payment_id) throws SQLException {
        return null;
    }

    // HELPER METHODS

    @Override
    public void connectPaymentToLoan(float payment_value, long card_no) throws SQLException {

    }

    @Override
    public Payment convertRowToPayment(ResultSet rs) throws SQLException {
        return null;
    }
}
