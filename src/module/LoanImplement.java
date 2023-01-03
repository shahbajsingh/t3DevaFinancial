package module;

import basic.DatabaseConnection;
import basic.Loan;
import basic.QUERY;
import dao.LoanDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static basic.CONSTANTS.*;

public class LoanImplement implements LoanDAO {

    public LoanImplement() {
        super();
    }


    @Override
    public void addLoan(long card_no, float loan_value, float interest_rate) throws SQLException {

        Timestamp loan_date = CURRENT_TIME;
        float dec_interest_rate = interest_rate / 100; // Convert interest rate to decimal
        float amt_remaining = loan_value;

        String sql = String.format(QUERY.addLoan, loan_date, card_no, loan_value, dec_interest_rate, amt_remaining);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        connectLoanToCard(loan_value, card_no);

        System.out.println("Loan of ₹" + loan_value + " added to card number " +
                card_no + " at " + interest_rate + "% interest rate");

        c.closeConnection();

    }

    @Override
    public void deleteLoan(long loan_id) throws SQLException {

        String sql = String.format(QUERY.deleteLoan, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        disconnectLoanFromCard(loan_id);
        c.executeSQL(sql);

        System.out.println("Loan with ID " + loan_id + " deleted from database");

        c.closeConnection();

    }


    public float handlePaidInFull(float payment_value, long loan_id) throws SQLException { // returns leftover money

        float leftover = 0.00f;

        String sql = String.format(QUERY.getLoanInfoByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()) {
            float amt_remaining = rs.getFloat("amt_remaining");
            if (payment_value > amt_remaining) {
                leftover = payment_value - amt_remaining;
                setLoanAmtRemaining(0.00f, loan_id);
                setLoanIsActive(false, loan_id);
            } else {
                setLoanAmtRemaining(amt_remaining - payment_value, loan_id);
            }
        }

        c.closeConnection();

        return leftover;

    }

    @Override
    public float getLoanAmtRemainingFloat(long loan_id) throws SQLException {

        float amt_remaining = 0.00f;

        String sql = String.format(QUERY.getLoanInfoByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()) {
            amt_remaining = rs.getFloat("amt_remaining");
        }

        c.closeConnection();

        return amt_remaining;

    }

    @Override
    public float getLoanValueFloat(long loan_id) throws SQLException {

        float loan_value = 0.00f;

        String sql = String.format(QUERY.getLoanInfoByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()) {
            loan_value = rs.getFloat("loan_value");
        }

        c.closeConnection();

        return loan_value;

    }

    @Override
    public long getOldestActiveLoanIDByCardNo(long card_no) throws SQLException {

        long loan_id = 0L;
        String sql = String.format(QUERY.getOldestActiveLoanIDByCardNo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if(rs.next()) {
            loan_id = rs.getLong("loan_id");
        }

        c.closeConnection();

        return loan_id;

    }

    @Override
    public void setLoanAmtRemaining(float amt_remaining, long loan_id) throws SQLException {

        String sql = String.format(QUERY.setLoanAmtRemaining, amt_remaining, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setLoanIsActive(boolean isActive, long loan_id) throws SQLException {

        String sql = String.format(QUERY.setLoanIsActive, isActive, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    public boolean checkLoanExists(long loan_id) throws SQLException {

        String sql = String.format(QUERY.checkLoanExists, loan_id);

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
    public ResultSet getLoanInfoByID(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanInfoByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getLoansByCardNo(long card_no) throws SQLException {

            String sql = String.format(QUERY.getLoansByCardNo, card_no);

            DatabaseConnection c = new DatabaseConnection();
            Connection newConnection = c.getConnection();

            ResultSet rs = c.selectSQL(sql);

            return rs;

    }

    @Override
    public ResultSet getLoansInDateRangeByCardNo(long card_no, String start_date,
                                                  String end_date) throws SQLException {

        String sql = String.format(QUERY.getLoansInDateRangeByCardNo, card_no, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getLoansBeforeDateByCardNo(long card_no, String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getLoansBeforeDateByCardNo, card_no, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getLoansAfterDateByCardNo(long card_no, String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getLoansAfterDateByCardNo, card_no, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getActiveLoansByCardNo(long card_no) throws SQLException {

        String sql = String.format(QUERY.getActiveLoansByCardNo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getActiveLoansInDateRangeByCardNo(long card_no, String start_date,
                                                      String end_date) throws SQLException {

        String sql = String.format(QUERY.getActiveLoansInDateRangeByCardNo, card_no, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getActiveLoansBeforeDateByCardNo(long card_no, String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getActiveLoansBeforeDateByCardNo, card_no, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getActiveLoansAfterDateByCardNo(long card_no, String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getActiveLoansAfterDateByCardNo, card_no, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllLoans() throws SQLException {

        String sql = QUERY.getAllLoans;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllLoansInDateRange(String start_date, String end_date) throws SQLException {

        String sql = String.format(QUERY.getAllLoansInDateRange, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllLoansBeforeDate(String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getAllLoansBeforeDate, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllLoansAfterDate(String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getAllLoansAfterDate, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllActiveLoans() throws SQLException {

        String sql = QUERY.getAllActiveLoans;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllActiveLoansInDateRange(String start_date, String end_date) throws SQLException {

        String sql = String.format(QUERY.getAllActiveLoansInDateRange, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllActiveLoansBeforeDate(String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getAllActiveLoansBeforeDate, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllActiveLoansAfterDate(String date) throws SQLException { // For GUI use only

        String sql = String.format(QUERY.getAllActiveLoansAfterDate, date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    // STRING METHODS
    // These methods are used in command line tests and populating GUI text fields
    // They return a String or string representation of an object to the calling method

    @Override
    public String getLoanDate(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanDateByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String loan_date = rs.getTimestamp("loan_date").toString();

        return loan_date;

    }

    @Override
    public String getLoanCardNo(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanCardNoByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String card_no = String.valueOf(rs.getLong("card_no"));

        return card_no;

    }

    @Override
    public String getLoanValue(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanValueByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String loan_value = String.valueOf(rs.getFloat("loan_value"));

        return loan_value;

    }

    @Override
    public String getLoanInterestRate(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanInterestRateByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String interest_rate = String.valueOf(rs.getFloat("interest_rate"));

        return interest_rate;

    }

    @Override
    public String getLoanAmtRemaining(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanAmtRemainingByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String amt_remaining = String.valueOf(rs.getFloat("amt_remaining"));

        return amt_remaining;

    }

    @Override
    public String getLoanInterestAccrued(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanInterestAccruedByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String interest_accrued = String.valueOf(rs.getFloat("interest_accrued"));

        return interest_accrued;

    }

    @Override
    public String getLoanIsActive(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanIsActiveByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        String is_active = rs.getBoolean("is_active") ? "Yes" : "No";

        return is_active;

    }

    public Loan getLoanInfoByIDString(long loan_id) throws SQLException {

        Loan tempLoan = new Loan();
        String sql = String.format(QUERY.getLoanInfoByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (!rs.next()){
            System.out.println("No loan found with ID: " + loan_id);
        } else {
            do {
                tempLoan = convertRowToLoan(rs);
            } while (rs.next());
        }

        c.closeConnection();

        return tempLoan;

    }

    public String getLoansByCardNoString(long card_no) throws SQLException {

        String sql = String.format(QUERY.getLoansByCardNo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToLoan(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getLoansInDateRangeByCardNoString(long card_no, String start_date,
                                                    String end_date) throws SQLException {

        String sql = String.format(QUERY.getLoansInDateRangeByCardNo, card_no, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToLoan(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getActiveLoansByCardNoString(long card_no) throws SQLException {

        String sql = String.format(QUERY.getActiveLoansByCardNo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToLoan(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getActiveLoansInDateRangeByCardNoString(long card_no, String start_date,
                                                          String end_date) throws SQLException {

        String sql = String.format(QUERY.getActiveLoansInDateRangeByCardNo, card_no, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToLoan(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getAllLoansString() throws SQLException {

        String sql = QUERY.getAllLoans;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToLoan(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getAllLoansInDateRangeString(String start_date, String end_date) throws SQLException {

        String sql = String.format(QUERY.getAllLoansInDateRange, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToLoan(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getAllActiveLoansString() throws SQLException {

        String sql = QUERY.getAllActiveLoans;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToLoan(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getAllActiveLoansInDateRangeString(String start_date, String end_date) throws SQLException {

        String sql = String.format(QUERY.getAllActiveLoansInDateRange, start_date, end_date);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToLoan(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    // HELPER METHODS

    @Override
    public void connectLoanToCard(float loan_value, long card_no) throws SQLException {

        CardImplement tempCard = new CardImplement();
        tempCard.addBalanceToCard(loan_value, card_no);

    }

    @Override
    public void disconnectLoanFromCard(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanInfoByID, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if(rs.next()){
            CardImplement tempCard = new CardImplement();
            tempCard.subtractBalanceFromCard(rs.getFloat("loan_value"), rs.getLong("card_no"));
        }

        c.closeConnection();

    }

    @Override
    public Loan convertRowToLoan(ResultSet rs) throws SQLException {

        Loan tempLoan = new Loan();

        tempLoan.setLoanID(rs.getLong("loan_id"));
        tempLoan.setLoanDate(rs.getTimestamp("loan_date"));
        tempLoan.setCardNo(rs.getLong("card_no"));
        tempLoan.setLoanValue(rs.getFloat("loan_value"));
        tempLoan.setInterestRate(rs.getFloat("interest_rate") * 100); // convert to percentage
        tempLoan.setAmtRemaining(rs.getFloat("amt_remaining"));
        tempLoan.setInterestAccrued(rs.getFloat("interest_accrued"));
        tempLoan.setIsActive(rs.getBoolean("is_active"));

        return tempLoan;

    }
}
