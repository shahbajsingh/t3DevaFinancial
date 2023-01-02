package module;

import basic.DatabaseConnection;
import basic.Employee;
import basic.QUERY;
import dao.EmployeeDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeImplement implements EmployeeDAO {

    public EmployeeImplement(){
        super();
    }


    @Override
    public void addEmployee(String password, String first_name, String middle_name, String last_name, long aadhaar,
                            String house_no, String street_name, String city, String state, String country,
                            String zip_code, String phone, String email) throws SQLException {

        String sql = String.format(QUERY.addEmployee, password, first_name, middle_name, last_name, aadhaar,
                                   house_no, street_name, city, state, country, zip_code, phone, email);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(String.format("Employee %s %s %s added to the database", first_name, middle_name, last_name));

        c.closeConnection();

    }

    @Override
    public void deleteEmployee(int employee_id) throws SQLException {

        String sql = String.format(QUERY.deleteEmployee, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(String.format("Employee with employee ID %d deleted from the database", employee_id));

        c.closeConnection();

    }

    @Override
    public boolean checkEmployeeExists(int employee_id) throws SQLException {

        String sql = String.format(QUERY.checkEmployeeExists, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        boolean exists = rs.next();

        c.closeConnection();

        return exists;

    }

    @Override
    public boolean checkEmployeePassword(int employee_id, String password) throws SQLException {

        String sql = String.format(QUERY.checkEmployeePassword, employee_id, password);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.next();

    }

    @Override
    public void changeEmployeePassword(String password, int employee_id) throws SQLException {

        String sql = String.format(QUERY.changeEmployeePassword, password, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeEmployeeName(String first_name, String middle_name,
                                   String last_name, int employee_id) throws SQLException {

        String sql = String.format(QUERY.changeEmployeeName, first_name, middle_name, last_name, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeEmployeeAadhaar(long aadhaar, int employee_id) throws SQLException {

        String sql = String.format(QUERY.changeEmployeeAadhaar, aadhaar, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeEmployeeAddress(String house_no, String street_name, String city, String state,
                                      String country, String zip_code, int employee_id) throws SQLException {

        String sql = String.format(QUERY.changeEmployeeAddress, house_no, street_name, city,
                state, country, zip_code, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeEmployeePhone(String phone, int employee_id) throws SQLException {

        String sql = String.format(QUERY.changeEmployeePhone, phone, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeEmployeeEmail(String email, int employee_id) throws SQLException {

        String sql = String.format(QUERY.changeEmployeeEmail, email, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    // RESULT SET METHODS
    // These methods are used to return a ResultSet object to the calling method for further processing
    // and will be used to construct table models to populate the GUI

    @Override
    public ResultSet getEmployeeInfo(int employee_id) throws SQLException {
    // TO-DO: Refactor query to not use * which returns password info as well
        String sql = String.format(QUERY.getEmployeeInfo, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getEmployeeInfoByLastName(String last_name) // For GUI use only
            throws SQLException {                               // No CLI string access equivalent

        String sql = String.format(QUERY.getEmployeeInfoByLastName, last_name);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllEmployeesInfo() throws SQLException {

        String sql = QUERY.getAllEmployeesInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    // STRING METHODS
    // These methods are used in command line tests and populating GUI text fields
    // They return a String or string representation of an object to the calling method

    @Override
    public String getEmployeeName(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeName, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return String.format("%s %s %s", rs.getString("first_name"),
                rs.getString("middle_name"), rs.getString("last_name"));

    }

    @Override
    public String getEmployeeAadhaar(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAadhaar, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return String.valueOf(rs.getLong("aadhaar"));

    }

    @Override
    public Map<String, String> getEmployeeAddress(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAddress, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        Map<String, String> addressMap = new HashMap<>() {{
            put("house_no", rs.getString("house_no"));
            put("street_name", rs.getString("street_name"));
            put("city", rs.getString("city"));
            put("state", rs.getString("state"));
            put("country", rs.getString("country"));
            put("zip_code", rs.getString("zip_code"));
        }};

        rs.close();

        return addressMap;

    }

    @Override
    public String getEmployeePhone(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeePhone, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.getString("phone");

    }

    @Override
    public String getEmployeeEmail(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeEmail, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.getString("email");

    }

    public Employee getEmployeeInfoString(int employee_id) throws SQLException {

        Employee tempEmployee = new Employee();
        String sql = String.format(QUERY.getEmployeeInfo, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (!rs.next()){
            System.out.println("No employee found with ID number: " + employee_id);
        } else {
            do {
                tempEmployee = convertRowToEmployee(rs);
            } while (rs.next());
        }

        c.closeConnection();

        return tempEmployee;

    }

    public String getAllEmployeesInfoString() throws SQLException {

        String sql = QUERY.getAllEmployeesInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToEmployee(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    // HELPER METHODS

    @Override
    public Employee convertRowToEmployee(ResultSet rs) throws SQLException {

        Employee tempEmployee = new Employee();

        tempEmployee.setEmployeeID(rs.getInt("employee_id"));
        tempEmployee.setFirstName(rs.getString("first_name"));
        tempEmployee.setMiddleName(rs.getString("middle_name"));
        tempEmployee.setLastName(rs.getString("last_name"));
        tempEmployee.setAadhaar(rs.getLong("aadhaar"));
        tempEmployee.setHouseNo(rs.getString("house_no"));
        tempEmployee.setStreetName(rs.getString("street_name"));
        tempEmployee.setCity(rs.getString("city"));
        tempEmployee.setState(rs.getString("state"));
        tempEmployee.setCountry(rs.getString("country"));
        tempEmployee.setZipCode(rs.getString("zip_code"));
        tempEmployee.setPhone(rs.getString("phone"));
        tempEmployee.setEmail(rs.getString("email"));

        return tempEmployee;

    }
}
