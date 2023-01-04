package dao;

import basic.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface EmployeeDAO {

    void addEmployee(String password, String first_name, String middle_name, String last_name, long aadhaar,
                     String house_no, String street_name, String city, String state, String country,
                     String zip_code, String phone, String email) throws SQLException;

    void deleteEmployee(int employee_id) throws SQLException;

    boolean checkEmployeeExists(int employee_id) throws SQLException;

    boolean checkEmployeePassword(int employee_id, String password) throws SQLException;

    // GETTERS

    String getEmployeeFirstName(int employee_id) throws SQLException;

    String getEmployeeMiddleName(int employee_id) throws SQLException;

    String getEmployeeLastName(int employee_id) throws SQLException;

    long getEmployeeAadhaar(int employee_id) throws SQLException;

    String getEmployeeHouseNo(int employee_id) throws SQLException;

    String getEmployeeStreetName(int employee_id) throws SQLException;

    String getEmployeeCity(int employee_id) throws SQLException;

    String getEmployeeState(int employee_id) throws SQLException;

    String getEmployeeCountry(int employee_id) throws SQLException;

    String getEmployeeZipCode(int employee_id) throws SQLException;

    String getEmployeePhone(int employee_id) throws SQLException;

    String getEmployeeEmail(int employee_id) throws SQLException;

    // SETTERS

    void setEmployeeInfo(Employee employee) throws SQLException;

    void setEmployeePassword(String password, int employee_id) throws SQLException;

    void setEmployeeName(String first_name, String middle_name, String last_name, int employee_id) throws SQLException;

    void setEmployeeAadhaar(long aadhaar, int employee_id) throws SQLException;

    void setEmployeeAddress(String house_no, String street_name, String city, String state,
                               String country, String zip_code, int employee_id) throws SQLException;

    void setEmployeePhone(String phone, int employee_id) throws SQLException;

    void setEmployeeEmail(String email, int employee_id) throws SQLException;

    // RESULT SET METHODS

    ResultSet getEmployeeInfo(int employee_id) throws SQLException; // employee schema details

    ResultSet getEmployeeInfoByLastName(String last_name) throws SQLException; // access schema by last name

    ResultSet getAllEmployeesInfo() throws SQLException; // employee schema details for all customers

    // STRING METHODS

    String getEmployeeFirstNameString(int employee_id) throws SQLException;

    String getEmployeeMiddleNameString(int employee_id) throws SQLException;

    String getEmployeeLastNameString(int employee_id) throws SQLException;

    String getEmployeeAadhaarString(int employee_id) throws SQLException;

    Map<String, String> getEmployeeAddressMap(int employee_id) throws SQLException;

    String getEmployeePhoneString(int employee_id) throws SQLException;

    String getEmployeeEmailString(int employee_id) throws SQLException;

    // HELPER METHODS

    Employee convertRowToEmployee(ResultSet rs) throws SQLException;

}
