package dao;

import basic.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface CustomerDAO {

    void addCustomer(String first_name, String middle_name, String last_name, long aadhaar,
                     String house_no, String street_name, String city, String state, String country,
                     String zip_code, String phone, String email) throws SQLException;

    void deleteCustomer(long card_no) throws SQLException;

    boolean checkCustomerExists(long card_no) throws SQLException;

    void changeCustomerName(String first_name, String middle_name, String last_name, long card_no) throws SQLException;

    void changeCustomerAadhaar(long aadhaar, long card_no) throws SQLException;

    void changeCustomerAddress(String house_no, String street_name, String city, String state,
                               String country, String zip_code, long card_no) throws SQLException;

    void changeCustomerPhone(String phone, long card_no) throws SQLException;

    void changeCustomerEmail(String email, long card_no) throws SQLException;

    ResultSet getCustomerInfo(long card_no) throws SQLException; // customer schema details

    ResultSet getCustomerAccountInfo(long card_no) throws SQLException; // customer + card schema details

    ResultSet getAllCustomerInfo() throws SQLException; // customer schema details for all customers

    ResultSet getAllCustomerAccountInfo() throws SQLException; // customer + card schema details for all customers

    String getCustomerName(long card_no) throws SQLException;

    String getCustomerAadhaar(long card_no) throws SQLException;

    Map<String, String> getCustomerAddress(long card_no) throws SQLException;

    String getCustomerPhone(long card_no) throws SQLException;

    String getCustomerEmail(long card_no) throws SQLException;

    Customer convertRowToCustomer(ResultSet rs) throws SQLException;

}
