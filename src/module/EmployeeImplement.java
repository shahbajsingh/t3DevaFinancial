package module;

import basic.DatabaseConnection;
import basic.Employee;
import basic.QUERY;
import dao.EmployeeDAO;
import net.proteanit.sql.DbUtils;

import javax.swing.table.TableModel;
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

        System.out.println(
                String.format("Employee %s %s %s added to the database\n", first_name, middle_name, last_name)
        );

        c.closeConnection();

    }

    @Override
    public void deleteEmployee(int employee_id) throws SQLException {

        String sql = String.format(QUERY.deleteEmployee, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(
                String.format("Employee with employee ID %d deleted from the database\n", employee_id)
        );

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
    public boolean checkEmployeeExistsByLastName(String last_name) throws SQLException {

        String sql = String.format(QUERY.checkEmployeeExistsByLastName, last_name);

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

        boolean correct = rs.next();

        c.closeConnection();

        return correct;

    }




    // GETTERS
    // These methods return the value of the specified column
    // in the actual data type specified in the database

    @Override
    public String getEmployeeFirstName(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeName, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String first_name = null;

        if(rs.next()){
            first_name = rs.getString("first_name");
        }

        c.closeConnection();

        return first_name;

    }

    @Override
    public String getEmployeeMiddleName(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeName, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String middle_name = null;

        if (rs.getString("middle_name") != null) {
            middle_name = rs.getString("middle_name");
        }

        c.closeConnection();

        return middle_name;

    }

    @Override
    public String getEmployeeLastName(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeName, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String last_name = null;

        if(rs.next()){
            last_name = rs.getString("last_name");
        }

        c.closeConnection();

        return last_name;

    }

    @Override
    public long getEmployeeAadhaar(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAadhaar, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        long aadhaar = 0L;

        if(rs.next()){
            aadhaar = rs.getLong("aadhaar");
        }

        c.closeConnection();

        return aadhaar;

    }

    @Override
    public String getEmployeeHouseNo(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAddress, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String house_no = null;

        if(rs.next()){
            house_no = rs.getString("house_no");
        }

        c.closeConnection();

        return house_no;

    }

    @Override
    public String getEmployeeStreetName(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAddress, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String street_name = null;

        if(rs.next()){
            street_name = rs.getString("street_name");
        }

        c.closeConnection();

        return street_name;

    }

    @Override
    public String getEmployeeCity(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAddress, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String city = null;

        if(rs.next()){
            city = rs.getString("city");
        }

        c.closeConnection();

        return city;

    }

    @Override
    public String getEmployeeState(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAddress, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String state = null;

        if(rs.next()){
            state = rs.getString("state");
        }

        c.closeConnection();

        return state;

    }

    @Override
    public String getEmployeeCountry(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAddress, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String country = null;

        if(rs.next()){
            country = rs.getString("country");
        }

        c.closeConnection();

        return country;

    }

    @Override
    public String getEmployeeZipCode(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeAddress, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String zip_code = null;

        if (rs.next()) {
            zip_code = rs.getString("zip_code");
        }

        c.closeConnection();

        return zip_code;

    }

    @Override
    public String getEmployeePhone(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeePhone, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String phone = null;

        if (rs.next()) {
            phone = rs.getString("phone");
        }

        c.closeConnection();

        return phone;

    }

    @Override
    public String getEmployeeEmail(int employee_id) throws SQLException {

        String sql = String.format(QUERY.getEmployeeEmail, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        String email = null;

        if (rs.next()) {
            email = rs.getString("email");
        }

        c.closeConnection();

        return email;

    }




    // SETTERS
    // These methods update the value of the specified column
    // in the database using the passed value

    @Override
    public void setEmployeeInfo(Employee employee) throws SQLException { // excludes password
        int employee_id = employee.getEmployeeID(); String first_name = employee.getFirstName();
        String middle_name = employee.getMiddleName(); String last_name = employee.getLastName();
        long aadhaar = employee.getAadhaar(); String house_no = employee.getHouseNo();
        String street_name = employee.getStreetName(); String city = employee.getCity();
        String state = employee.getState(); String country = employee.getCountry();
        String zip_code = employee.getZipCode(); String phone = employee.getPhone();
        String email = employee.getEmail();

        String sql = String.format(QUERY.setEmployeeInfo, first_name, middle_name, last_name, aadhaar,
                house_no, street_name, city, state, country, zip_code, phone, email, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(
                String.format("Employee with ID %d updated in database\n", employee_id)
        );

        c.closeConnection();
    }

    @Override
    public void setEmployeePassword(String password, int employee_id) throws SQLException {

        String sql = String.format(QUERY.setEmployeePassword, password, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setEmployeeName(String first_name, String middle_name,
                                   String last_name, int employee_id) throws SQLException {

        String sql = String.format(QUERY.setEmployeeName, first_name, middle_name, last_name, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setEmployeeAadhaar(long aadhaar, int employee_id) throws SQLException {

        String sql = String.format(QUERY.setEmployeeAadhaar, aadhaar, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setEmployeeAddress(String house_no, String street_name, String city, String state,
                                      String country, String zip_code, int employee_id) throws SQLException {

        String sql = String.format(QUERY.setEmployeeAddress, house_no, street_name, city,
                state, country, zip_code, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setEmployeePhone(String phone, int employee_id) throws SQLException {

        String sql = String.format(QUERY.setEmployeePhone, phone, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setEmployeeEmail(String email, int employee_id) throws SQLException {

        String sql = String.format(QUERY.setEmployeeEmail, email, employee_id);

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




    // TABLE MODEL METHODS
    // These methods construct table models from passed ResultSet objects
    // in order to populate GUI tables with connection-independent data captures

    @Override
    public TableModel getEmployeeInfoTableModel(int employee_id) throws SQLException {
        // TO-DO: Refactor query to not use * which returns password info as well
        String sql = String.format(QUERY.getEmployeeInfo, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    @Override
    public TableModel getEmployeeInfoByLastNameTableModel(String last_name) throws SQLException {

        String sql = String.format(QUERY.getEmployeeInfoByLastName, last_name);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    @Override
    public TableModel getAllEmployeesInfoTableModel() throws SQLException {

        String sql = QUERY.getAllEmployeesInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }




    // STRING METHODS
    // These methods are used in command line tests and populating GUI text fields
    // They return a String or string representation of an object to the calling method

    @Override
    public String getEmployeeFirstNameString(int employee_id) throws SQLException {

        String first_name = getEmployeeFirstName(employee_id);
        return first_name;

    }

    @Override
    public String getEmployeeMiddleNameString(int employee_id) throws SQLException {

        String middle_name = getEmployeeMiddleName(employee_id);
        return middle_name;

    }

    @Override
    public String getEmployeeLastNameString(int employee_id) throws SQLException {

        String last_name = getEmployeeLastName(employee_id);
        return last_name;

    }

    @Override
    public String getEmployeeAadhaarString(int employee_id) throws SQLException {

        long aadhaar = getEmployeeAadhaar(employee_id);
        return String.valueOf(aadhaar);

    }

    @Override
    public Map<String, String> getEmployeeAddressMap(int employee_id) throws SQLException {

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

        c.closeConnection();

        return addressMap;

    }

    @Override
    public String getEmployeePhoneString(int employee_id) throws SQLException {

        String phone = getEmployeePhone(employee_id);
        return phone;

    }

    @Override
    public String getEmployeeEmailString(int employee_id) throws SQLException {

        String email = getEmployeeEmail(employee_id);
        return email;

    }

    public Employee getEmployeeInfoString(int employee_id) throws SQLException {

        Employee tempEmployee = new Employee();
        String sql = String.format(QUERY.getEmployeeInfo, employee_id);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()) {
            tempEmployee = convertRowToEmployee(rs);
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
