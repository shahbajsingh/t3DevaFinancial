package module;

import basic.DatabaseConnection;
import basic.Payment;
import basic.QUERY;
import dao.PaymentDAO;

import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static basic.CONSTANTS.*;

public class PaymentImplement implements PaymentDAO { // TO-DO: Add helper methods to add and delete payments

    public PaymentImplement() {
        super();
    }

    @Override
    public float addPayment(long card_no, float payment_value) throws SQLException { // returns payment value leftover

        LoanImplement tempLoan = new LoanImplement();
        CardImplement tempCard = new CardImplement();
        Timestamp payment_date = CURRENT_TIME;

        float total_payment_value = 0.00f;
        float leftover = payment_value;

        long loan_id = 0; // loan_id of the loan that is being paid off (oldest active)
        float loan_remaining = 0.00f; // balance remaining on oldest active loan
        float loan_interest = 0.00f; // interest accrued on oldest active loan

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        while (leftover > 0.005f && tempCard.getCardBalance(card_no) > 0.005f) {

            loan_id = tempLoan.getOldestActiveLoanIDByCardNo(card_no);
            loan_remaining = tempLoan.getLoanAmtRemaining(loan_id);
            loan_interest = tempLoan.getLoanInterestAccrued(loan_id);

            if (leftover >= loan_remaining + loan_interest) { // leftover can pay off loan principal AND interest

                leftover -= (loan_remaining + loan_interest);
                total_payment_value += (loan_remaining + loan_interest);

                tempLoan.setLoanAmtRemaining(0.00f, loan_id);
                tempLoan.setLoanIsActive(false, loan_id);

                tempCard.subtractBalanceFromCard((loan_remaining + loan_interest), card_no);

                // add payment to database

                String sql = String.format(QUERY.addPayment, payment_date,
                        card_no, (loan_remaining + loan_interest), loan_id);

                c.executeSQL(sql);

            } else if (leftover >= loan_remaining){ // leftover can pay off loan principal but NOT interest

                leftover -= loan_remaining;
                total_payment_value += loan_remaining;

                tempLoan.setLoanAmtRemaining(0.00f, loan_id);

                tempCard.subtractBalanceFromCard(loan_remaining, card_no);

                // add payment to database

                String sql = String.format(QUERY.addPayment, payment_date, card_no, loan_remaining, loan_id);

                c.executeSQL(sql);

            } else { // leftover can only pay off PART of loan principal

                tempLoan.setLoanAmtRemaining(loan_remaining - leftover, loan_id);
                tempCard.subtractBalanceFromCard(leftover, card_no);

                total_payment_value += leftover;

                // add payment to database

                String sql = String.format(QUERY.addPayment, payment_date, card_no, leftover, loan_id);

                c.executeSQL(sql);

                leftover = 0.00f;

            }
        }

        tempCard.setCardAmtLastPayment(total_payment_value, card_no);
        tempCard.setCardDateLastPayment(payment_date, card_no);

        c.closeConnection();

        System.out.println(
                String.format("Payment of ₹%.2f made under card no. %d with ₹%.2f left over",
                        total_payment_value, card_no, leftover)
        );

        return leftover;

    }

    @Override
    public void deletePayment(long payment_id) throws SQLException {

        LoanImplement tempLoan = new LoanImplement();
        CardImplement tempCard = new CardImplement();

        long card_no = getPaymentCardNo(payment_id);
        float payment_value = getPaymentValue(payment_id);
        long loan_id = getPaymentLoanID(payment_id);
        float loan_remaining = tempLoan.getLoanAmtRemaining(loan_id);

        tempCard.addBalanceToCard(payment_value, card_no);
        tempLoan.setLoanIsActive(true, loan_id);
        tempLoan.setLoanAmtRemaining(loan_remaining + payment_value, loan_id);


        String sql = String.format(QUERY.deletePayment, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(
                String.format("Payment of ₹%.2f with ID %d deleted from card no. %d in database\n",
                        payment_value, payment_id, card_no)
        );

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




    // TABLE MODEL METHODS
    // These methods construct table models from passed ResultSet objects
    // in order to populate GUI tables with connection-independent data captures

    public TableModel getPaymentInfoTableModel(long payment_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentInfo, payment_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getPaymentsByCardNoTableModel(long card_no) throws SQLException {

        String sql = String.format(QUERY.getPaymentsByCardNo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getPaymentsInDateRangeByCardNoTableModel(long card_no, String start_date,
                                                        String end_date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsInDateRangeByCardNo, card_no, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getPaymentsBeforeDateByCardNoTableModel(long card_no, String date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsBeforeDateByCardNo, card_no, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getPaymentsAfterDateByCardNoTableModel(long card_no, String date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsAfterDateByCardNo, card_no, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getPaymentsByLoanIDTableModel(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getPaymentsByLoanID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getPaymentsInDateRangeByLoanIDTableModel(long loan_id, String start_date,
                                                        String end_date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsInDateRangeByLoanID, loan_id, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getPaymentsBeforeDateByLoanIDTableModel(long loan_id, String date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsBeforeDateByLoanID, loan_id, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getPaymentsAfterDateByLoanIDTableModel(long loan_id, String date) throws SQLException {

        String sql = String.format(QUERY.getPaymentsAfterDateByLoanID, loan_id, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getAllPaymentsTableModel() throws SQLException {

        String sql = QUERY.getAllPayments;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getAllPaymentsInDateRangeTableModel(String start_date, String end_date) throws SQLException {

        String sql = String.format(QUERY.getAllPaymentsInDateRange, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getAllPaymentsBeforeDateTableModel(String date) throws SQLException {

        String sql = String.format(QUERY.getAllPaymentsBeforeDate, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    public TableModel getAllPaymentsAfterDateTableModel(String date) throws SQLException {

        String sql = String.format(QUERY.getAllPaymentsAfterDate, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

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
    public void connectPaymentToLoan(float payment_value, long card_no,
                                       long oldest_loan_id, float amt_remaining) throws SQLException {




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
