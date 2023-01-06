package dao;

import basic.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public interface PaymentDAO {

    void addPayment(long card_no, float payment_value) throws SQLException;

    void deletePayment(long payment_id) throws SQLException;

    boolean checkPaymentExists(long payment_id) throws SQLException;

    // GETTERS

    Timestamp getPaymentDate(long payment_id) throws SQLException;

    long getPaymentCardNo(long payment_id) throws SQLException;

    float getPaymentValue(long payment_id) throws SQLException;

    long getPaymentLoanID(long payment_id) throws SQLException;

    // SETTERS

    void setPaymentInfo(Payment payment) throws SQLException;

    void setPaymentDate(Timestamp payment_date, long payment_id) throws SQLException;

    void setPaymentCardNo(long card_no, long payment_id) throws SQLException;

    void setPaymentValue(float payment_value, long payment_id) throws SQLException;

    void setPaymentLoanID(long loan_id, long payment_id) throws SQLException;

    // RESULT SET METHODS

    ResultSet getPaymentInfo(long payment_id) throws SQLException;

    ResultSet getPaymentsByCardNo(long card_no) throws SQLException;

    ResultSet getPaymentsInDateRangeByCardNo(long card_no, String start_date, String end_date) throws SQLException;

    ResultSet getPaymentsBeforeDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getPaymentsAfterDateByCardNo(long card_no, String date) throws SQLException;

    ResultSet getPaymentsByLoanID(long loan_id) throws SQLException;

    ResultSet getPaymentsInDateRangeByLoanID(long loan_id, String start_date, String end_date) throws SQLException;

    ResultSet getPaymentsBeforeDateByLoanID(long loan_id, String date) throws SQLException;

    ResultSet getPaymentsAfterDateByLoanID(long loan_id, String date) throws SQLException;

    ResultSet getAllPayments() throws SQLException;

    ResultSet getAllPaymentsInDateRange(String start_date, String end_date) throws SQLException;

    ResultSet getAllPaymentsBeforeDate(String date) throws SQLException;

    ResultSet getAllPaymentsAfterDate(String date) throws SQLException;

    // STRING METHODS

    String getPaymentDateString(long payment_id) throws SQLException;

    String getPaymentCardNoString(long payment_id) throws SQLException;

    String getPaymentValueString(long payment_id) throws SQLException;

    String getPaymentLoanIDString(long payment_id) throws SQLException;

    // HELPER METHODS

    void connectPaymentToLoan(float payment_value, long card_no,
                                long oldest_loan_id, float amt_remaining) throws SQLException;

    Payment convertRowToPayment(ResultSet rs) throws SQLException;

}
