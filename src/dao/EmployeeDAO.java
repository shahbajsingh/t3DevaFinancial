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

    void changeEmployeePassword(String password, int employee_id) throws SQLException;

    void changeEmployeeName(String first_name, String middle_name, String last_name, int employee_id) throws SQLException;

    void changeEmployeeAadhaar(long aadhaar, int employee_id) throws SQLException;

    void changeEmployeeAddress(String house_no, String street_name, String city, String state,
                               String country, String zip_code, int employee_id) throws SQLException;

    void changeEmployeePhone(String phone, int employee_id) throws SQLException;

    void changeEmployeeEmail(String email, int employee_id) throws SQLException;

    ResultSet getEmployeeInfo(int employee_id) throws SQLException; // employee schema details

    ResultSet getEmployeeInfoByLastName(String last_name) throws SQLException; // access schema by last name

    ResultSet getAllEmployeeInfo() throws SQLException; // employee schema details for all customers

    String getEmployeeName(int employee_id) throws SQLException;

    String getEmployeeAadhaar(int employee_id) throws SQLException;

    Map<String, String> getEmployeeAddress(int employee_id) throws SQLException;

    String getEmployeePhone(int employee_id) throws SQLException;

    String getEmployeeEmail(int employee_id) throws SQLException;


    Employee convertRowToEmployee(ResultSet rs) throws SQLException;

}
