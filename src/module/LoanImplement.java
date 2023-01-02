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
        boolean is_active = true;
        float dec_interest_rate = interest_rate / 100; // Convert interest rate to decimal

        String sql = String.format(QUERY.addLoan, loan_date, card_no, loan_value, dec_interest_rate, is_active);

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

        c.executeSQL(sql);

        System.out.println("Loan with ID " + loan_id + " deleted from database");

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
    public Loan convertRowToLoan(ResultSet rs) throws SQLException {

        Loan tempLoan = new Loan();

        tempLoan.setLoanID(rs.getLong("loan_id"));
        tempLoan.setLoanDate(rs.getTimestamp("loan_date"));
        tempLoan.setCardNo(rs.getLong("card_no"));
        tempLoan.setLoanValue(rs.getFloat("loan_value"));
        tempLoan.setInterestRate(rs.getFloat("interest_rate") * 100); // convert to percentage
        tempLoan.setIsActive(rs.getBoolean("is_active"));

        return tempLoan;

    }
}
