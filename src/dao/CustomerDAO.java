package dao;

import basic.Customer;

import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface CustomerDAO {

    long addCustomer(String first_name, String middle_name, String last_name, long aadhaar,
                     String house_no, String street_name, String city, String state, String country,
                     String zip_code, String phone, String email) throws SQLException; // returns generated card_no

    void deleteCustomer(long card_no) throws SQLException;

    long getCustomerCardNoByAadhaar(long aadhaar) throws SQLException;

    boolean checkCustomerExists(long card_no) throws SQLException;

    boolean checkCustomerExistsByAadhaar(long aadhaar) throws SQLException;

    // GETTERS

    String getCustomerFirstName(long card_no) throws SQLException;

    String getCustomerMiddleName(long card_no) throws SQLException;

    String getCustomerLastName(long card_no) throws SQLException;

    long getCustomerAadhaar(long card_no) throws SQLException;

    String getCustomerHouseNo(long card_no) throws SQLException;

    String getCustomerStreetName(long card_no) throws SQLException;

    String getCustomerCity(long card_no) throws SQLException;

    String getCustomerState(long card_no) throws SQLException;

    String getCustomerCountry(long card_no) throws SQLException;

    String getCustomerZipCode(long card_no) throws SQLException;

    String getCustomerPhone(long card_no) throws SQLException;

    String getCustomerEmail(long card_no) throws SQLException;

    // SETTERS

    void setCustomerInfo(Customer customer) throws SQLException;

    void setCustomerName(String first_name, String middle_name, String last_name, long card_no) throws SQLException;

    void setCustomerAadhaar(long aadhaar, long card_no) throws SQLException;

    void setCustomerAddress(String house_no, String street_name, String city, String state,
                               String country, String zip_code, long card_no) throws SQLException;

    void setCustomerPhone(String phone, long card_no) throws SQLException;

    void setCustomerEmail(String email, long card_no) throws SQLException;

    // RESULT SET METHODS

    ResultSet getCustomerInfo(long card_no) throws SQLException; // customer schema details

    ResultSet getCustomerAccountInfo(long card_no) throws SQLException; // customer + card schema details

    ResultSet getCustomerInfoByAadhaar(long aadhaar) throws SQLException;

    ResultSet getCustomerAccountInfoByAadhaar(long aadhaar) throws SQLException;

    ResultSet getAllCustomersInfo() throws SQLException; // customer schema details for all customers

    ResultSet getAllCustomersAccountInfo() throws SQLException; // customer + card schema details for all customers

    // TABLE MODEL METHODS

    TableModel getCustomerInfoTableModel(long card_no) throws SQLException;

    TableModel getCustomerAccountInfoTableModel(long card_no) throws SQLException;

    TableModel getCustomerInfoByAadhaarTableModel(long aadhaar) throws SQLException;

    TableModel getCustomerAccountInfoByAadhaarTableModel(long aadhaar) throws SQLException;

    TableModel getAllCustomersInfoTableModel() throws SQLException;

    TableModel getAllCustomersAccountInfoTableModel() throws SQLException;

    // STRING METHODS

    String getCustomerFirstNameString(long card_no) throws SQLException;

    String getCustomerMiddleNameString(long card_no) throws SQLException;

    String getCustomerLastNameString(long card_no) throws SQLException;

    String getCustomerAadhaarString(long card_no) throws SQLException;

    Map<String, String> getCustomerAddressMap(long card_no) throws SQLException;

    String getCustomerPhoneString(long card_no) throws SQLException;

    String getCustomerEmailString(long card_no) throws SQLException;

    // HELPER METHODS

    Customer convertRowToCustomer(ResultSet rs) throws SQLException;

}
