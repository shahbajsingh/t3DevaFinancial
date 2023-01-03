package dao;

import basic.Loan;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoanDAO {

    void addLoan(long card_no, float loan_value, float interest_rate) throws SQLException;

    void deleteLoan(long loan_id) throws SQLException;

    float handlePaidInFull(float payment_value, long loan_id) throws SQLException; // returns money leftover

    float getLoanAmtRemainingFloat(long loan_id) throws SQLException;

    float getLoanValueFloat(long loan_id) throws SQLException;

    long getOldestActiveLoanIDByCardNo(long card_no) throws SQLException; // used for payment identification logic

    void setLoanAmtRemaining(float payment_value, long loan_id) throws SQLException;

    // void setInterestAccrued() throws SQLException;

    void setLoanIsActive(boolean is_active, long loan_id) throws SQLException;

    boolean checkLoanExists(long loan_id) throws SQLException;

    // RESULT SET METHODS

    ResultSet getLoanInfoByID(long loan_id) throws SQLException;

    ResultSet getLoansByCardNo(long card_no) throws SQLException;

    ResultSet getLoansInDateRangeByCardNo(long card_no, String start_date, String end_date) throws SQLException;

    ResultSet getLoansBeforeDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getLoansAfterDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getActiveLoansByCardNo(long card_no) throws SQLException;

    ResultSet getActiveLoansBeforeDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getActiveLoansAfterDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getActiveLoansInDateRangeByCardNo(long card_no, String start_date, String end_date) throws SQLException;

    ResultSet getAllLoans() throws SQLException;

    ResultSet getAllLoansInDateRange(String start_date, String end_date) throws SQLException;

    ResultSet getAllLoansBeforeDate(String date) throws SQLException;

    ResultSet getAllLoansAfterDate(String date) throws SQLException;

    ResultSet getAllActiveLoans() throws SQLException;

    ResultSet getAllActiveLoansInDateRange(String start_date, String end_date) throws SQLException;

    ResultSet getAllActiveLoansBeforeDate(String date) throws SQLException;

    ResultSet getAllActiveLoansAfterDate(String date) throws SQLException;

    // STRING METHODS

    String getLoanDate(long loan_id) throws SQLException;

    String getLoanCardNo(long loan_id) throws SQLException;

    String getLoanValue(long loan_id) throws SQLException;

    String getLoanInterestRate(long loan_id) throws SQLException;

    String getLoanAmtRemaining(long loan_id) throws SQLException;

    String getLoanInterestAccrued(long loan_id) throws SQLException;

    String getLoanIsActive(long loan_id) throws SQLException;

    // HELPER METHODS

    void connectLoanToCard(float loan_value, long card_no) throws SQLException;

    void disconnectLoanFromCard(long loan_id) throws SQLException;

    Loan convertRowToLoan(ResultSet rs) throws SQLException;

}
