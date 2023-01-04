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

        System.out.println(
                String.format("Loan of ₹%.2f added to card number %d at %.2f%% interest rate\n",
                        loan_value, card_no, interest_rate)
        );

        c.closeConnection();

    }

    @Override
    public void deleteLoan(long loan_id) throws SQLException {

        String sql = String.format(QUERY.deleteLoan, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        disconnectLoanFromCard(loan_id);
        c.executeSQL(sql);


        System.out.println(
                String.format("Loan with ID %d deleted from database\n", loan_id)
        );

        c.closeConnection();

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
    public boolean checkLoanExists(long loan_id) throws SQLException {

        String sql = String.format(QUERY.checkLoanExists, loan_id);

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
    public Timestamp getLoanDate(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanDate, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        Timestamp loan_date = null;

        if(rs.next()) {
            loan_date = rs.getTimestamp("loan_date");
        }

        c.closeConnection();

        return loan_date;

    }

    @Override
    public long getLoanCardNo(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanCardNo, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        long card_no = 0L;

        if(rs.next()) {
            card_no = rs.getLong("card_no");
        }

        c.closeConnection();

        return card_no;

    }

    @Override
    public float getLoanValue(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanValue, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        float loan_value = 0.00f;

        if(rs.next()) {
            loan_value = rs.getFloat("loan_value");
        }

        c.closeConnection();

        return loan_value;

    }

    @Override
    public float getLoanInterestRate(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanInterestRate, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        float interest_rate = 0.00f;

        if(rs.next()) {
            interest_rate = rs.getFloat("interest_rate");
        }

        c.closeConnection();

        return interest_rate;

    }

    @Override
    public float getLoanAmtRemaining(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanAmtRemaining, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        float amt_remaining = 0.00f;

        if(rs.next()) {
            amt_remaining = rs.getFloat("amt_remaining");
        }

        c.closeConnection();

        return amt_remaining;

    }

    @Override
    public float getLoanInterestAccrued(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanInterestAccrued, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        float interest_accrued = 0.00f;

        if(rs.next()) {
            interest_accrued = rs.getFloat("interest_accrued");
        }

        c.closeConnection();

        return interest_accrued;

    }

    @Override
    public boolean getLoanIsActive(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanIsActive, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        boolean is_active = false;

        if(rs.next()) {
            is_active = rs.getBoolean("is_active");
        }

        c.closeConnection();

        return is_active;

    }




    // SETTERS
    // These methods update the value of the specified column
    // in the database using the passed value

    @Override
    public void setLoanInfo(Loan loan) throws SQLException {

        long loan_id = loan.getLoanID(); Timestamp loan_date = loan.getLoanDate();
        long card_no = loan.getCardNo(); float loan_value = loan.getLoanValue();
        float interest_rate = loan.getInterestRate(); float amt_remaining = loan.getAmtRemaining();
        float interest_accrued = loan.getInterestAccrued(); boolean is_active = loan.getIsActive();

        String sql = String.format(QUERY.setLoanInfo, loan_date, card_no, loan_value,
                interest_rate, amt_remaining, interest_accrued, is_active, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(
                String.format("Loan with ID %d updated in database\n", loan_id)
        );

        c.closeConnection();

    }

    @Override
    public void setLoanDate(Timestamp loan_date, long loan_id) throws SQLException {

        String sql = String.format(QUERY.setLoanDate, loan_date, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setLoanCardNo(long card_no, long loan_id) throws SQLException {

        String sql = String.format(QUERY.setLoanCardNo, card_no, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setLoanValue(float loan_value, long loan_id) throws SQLException {

        String sql = String.format(QUERY.setLoanValue, loan_value, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setLoanInterestRate(float interest_rate, long loan_id) throws SQLException {

        String sql = String.format(QUERY.setLoanInterestRate, interest_rate, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

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
    public void setLoanInterestAccrued(float interest_accrued, long loan_id) throws SQLException {

        String sql = String.format(QUERY.setLoanInterestAccrued, interest_accrued, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setLoanIsActive(boolean is_active, long loan_id) throws SQLException {

        String sql = String.format(QUERY.setLoanIsActive, is_active, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }




    // RESULT SET METHODS
    // These methods are used to return a ResultSet object to the calling method for further processing
    // and will be used to construct table models to populate the GUI

    @Override
    public ResultSet getLoanInfo(long loan_id) throws SQLException {

        String sql = String.format(QUERY.getLoanInfo, loan_id);

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




    // STRING METHODS
    // These methods are used in command line tests and populating GUI text fields
    // They return a String or string representation of an object to the calling method

    @Override
    public String getLoanDateString(long loan_id) throws SQLException {

        Timestamp loan_date = getLoanDate(loan_id);
        return loan_date.toString();

    }

    @Override
    public String getLoanCardNoString(long loan_id) throws SQLException {

        long card_no = getLoanCardNo(loan_id);
        return Long.toString(card_no);

    }

    @Override
    public String getLoanValueString(long loan_id) throws SQLException {

        float loan_value = getLoanValue(loan_id);
        return Float.toString(loan_value);

    }

    @Override
    public String getLoanInterestRateString(long loan_id) throws SQLException {

        float interest_rate = getLoanInterestRate(loan_id);
        return Float.toString(interest_rate);

    }

    @Override
    public String getLoanAmtRemainingString(long loan_id) throws SQLException {

        float amt_remaining = getLoanAmtRemaining(loan_id);
        return Float.toString(amt_remaining);

    }

    @Override
    public String getLoanInterestAccruedString(long loan_id) throws SQLException {

        float interest_accrued = getLoanInterestAccrued(loan_id);
        return Float.toString(interest_accrued);

    }

    @Override
    public String getLoanIsActiveString(long loan_id) throws SQLException {

        boolean is_active = getLoanIsActive(loan_id);
        return is_active ? "Yes" : "No";

    }

    public Loan getLoanInfoString(long loan_id) throws SQLException {

        Loan tempLoan = new Loan();
        String sql = String.format(QUERY.getLoanInfo, loan_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()) {
            tempLoan = convertRowToLoan(rs);
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

        String sql = String.format(QUERY.getLoanInfo, loan_id);

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
