package dao;

import basic.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PaymentDAO {

    void addPayment(long card_no, float payment_value) throws SQLException;

    void deletePayment(long payment_id) throws SQLException;

    boolean checkPaymentExists(long payment_id) throws SQLException;

    ResultSet getPaymentInfoByID(long payment_id) throws SQLException;

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

    String getPaymentDate(long payment_id) throws SQLException;

    String getPaymentCardNo(long payment_id) throws SQLException;

    String getPaymentValue(long payment_id) throws SQLException;

    String getPaymentLoanID(long payment_id) throws SQLException;

    void connectPaymentToLoan(float payment_value, long card_no) throws SQLException;

    Payment convertRowToPayment(ResultSet rs) throws SQLException;

}
