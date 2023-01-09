package dao;

import basic.Loan;

import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public interface LoanDAO {

    void addLoan(long card_no, float loan_value, float interest_rate) throws SQLException;

    void deleteLoan(long loan_id) throws SQLException;

    void updateLoanInterestAccrued(long loan_id) throws SQLException;

    void updateAllLoansInterestAccrued() throws SQLException;

    long getOldestActiveLoanIDByCardNo(long card_no) throws SQLException; // used for payment identification logic

    boolean checkLoanExists(long loan_id) throws SQLException;

    // GETTERS

    Timestamp getLoanDate(long loan_id) throws SQLException;

    long getLoanCardNo(long loan_id) throws SQLException;

    float getLoanValue(long loan_id) throws SQLException;

    float getLoanInterestRate(long loan_id) throws SQLException;

    float getLoanAmtRemaining(long loan_id) throws SQLException;

    float getLoanInterestAccrued(long loan_id) throws SQLException;

    boolean getLoanIsActive(long loan_id) throws SQLException;

    // SETTERS

    void setLoanInfo(Loan loan) throws SQLException;

    void setLoanDate(Timestamp loan_date, long loan_id) throws SQLException;

    void setLoanCardNo(long card_no, long loan_id) throws SQLException;

    void setLoanValue(float loan_value, long loan_id) throws SQLException;

    void setLoanInterestRate(float interest_rate, long loan_id) throws SQLException;

    void setLoanAmtRemaining(float amt_remaining, long loan_id) throws SQLException;

    void setLoanInterestAccrued(float interest_accrued, long loan_id) throws SQLException;

    void setLoanIsActive(boolean is_active, long loan_id) throws SQLException;

    // RESULT SET METHODS

    ResultSet getLoanInfo(long loan_id) throws SQLException;

    ResultSet getLoansByCardNo(long card_no) throws SQLException;

    ResultSet getLoansInDateRangeByCardNo(long card_no, String start_date, String end_date) throws SQLException;

    ResultSet getLoansBeforeDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getLoansAfterDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getActiveLoansByCardNo(long card_no) throws SQLException;

    ResultSet getActiveLoansInDateRangeByCardNo(long card_no, String start_date, String end_date) throws SQLException;

    ResultSet getActiveLoansBeforeDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getActiveLoansAfterDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getAllActiveLoans() throws SQLException;

    ResultSet getAllActiveLoansInDateRange(String start_date, String end_date) throws SQLException;

    ResultSet getAllActiveLoansBeforeDate(String date) throws SQLException;

    ResultSet getAllActiveLoansAfterDate(String date) throws SQLException;

    ResultSet getAllLoans() throws SQLException;

    ResultSet getAllLoansInDateRange(String start_date, String end_date) throws SQLException;

    ResultSet getAllLoansBeforeDate(String date) throws SQLException;

    ResultSet getAllLoansAfterDate(String date) throws SQLException;

    // TABLE MODEL METHODS

    TableModel getLoanInfoTableModel(long loan_id) throws SQLException;

    TableModel getLoansByCardNoTableModel(long card_no) throws SQLException;

    TableModel getLoansInDateRangeByCardNoTableModel(long card_no, String start_date,
                                                     String end_date) throws SQLException;

    TableModel getLoansBeforeDateByCardNoTableModel(long card_no, String date) throws SQLException;

    TableModel getLoansAfterDateByCardNoTableModel(long card_no, String date) throws SQLException;

    TableModel getActiveLoansByCardNoTableModel(long card_no) throws SQLException;

    TableModel getActiveLoansInDateRangeByCardNoTableModel(long card_no, String start_date,
                                                           String end_date) throws SQLException;

    TableModel getActiveLoansBeforeDateByCardNoTableModel(long card_no, String date) throws SQLException;

    TableModel getActiveLoansAfterDateByCardNoTableModel(long card_no, String date) throws SQLException;

    TableModel getAllActiveLoansTableModel() throws SQLException;

    TableModel getAllActiveLoansInDateRangeTableModel(String start_date, String end_date) throws SQLException;

    TableModel getAllActiveLoansBeforeDateTableModel(String date) throws SQLException;

    TableModel getAllActiveLoansAfterDateTableModel(String date) throws SQLException;

    TableModel getAllLoansTableModel() throws SQLException;

    TableModel getAllLoansInDateRangeTableModel(String start_date, String end_date) throws SQLException;

    TableModel getAllLoansBeforeDateTableModel(String date) throws SQLException;

    TableModel getAllLoansAfterDateTableModel(String date) throws SQLException;

    // STRING METHODS

    String getLoanDateString(long loan_id) throws SQLException;

    String getLoanCardNoString(long loan_id) throws SQLException;

    String getLoanValueString(long loan_id) throws SQLException;

    String getLoanInterestRateString(long loan_id) throws SQLException;

    String getLoanAmtRemainingString(long loan_id) throws SQLException;

    String getLoanInterestAccruedString(long loan_id) throws SQLException;

    String getLoanIsActiveString(long loan_id) throws SQLException;

    // HELPER METHODS

    float calculateLoanSimpleInterestAccrued(long loan_id) throws SQLException;

    float calculateLoanCompoundInterestAccrued(long loan_id) throws SQLException;

    void connectLoan(float loan_value, long card_no) throws SQLException;

    void disconnectLoan(long loan_id) throws SQLException;

    Loan convertRowToLoan(ResultSet rs) throws SQLException;

}
