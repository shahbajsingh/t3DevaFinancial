package module;

import basic.Card;
import basic.Customer;
import basic.DatabaseConnection;
import basic.QUERY;
import dao.CustomerDAO;
import net.proteanit.sql.DbUtils;

import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerImplement implements CustomerDAO {

    public CustomerImplement(){
        super();
    }

    @Override
    public long addCustomer(String first_name, String middle_name, String last_name, long aadhaar, String house_no,
                             String street_name, String city, String state, String country, String zip_code,
                             String phone, String email) throws SQLException { // returns generated card_no

        CardImplement tempCardImplement = new CardImplement(); // Create a new card for the customer
        long card_no = tempCardImplement.addCard(); // Get the new card number and add it to CARDS schema

        String sql = String.format(QUERY.addCustomer, card_no, first_name, middle_name, last_name, aadhaar,
                                   house_no, street_name, city, state, country, zip_code, phone, email);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(
                String.format("Customer '%s %s %s' added to database with card number %d\n",
                first_name, middle_name, last_name, card_no)
        );

        c.closeConnection();

        return card_no;

    }

    @Override
    public void deleteCustomer(long card_no) throws SQLException {

        CardImplement tempCardImplement = new CardImplement();
        String sql = String.format(QUERY.deleteCustomer, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);
        tempCardImplement.deleteCard(card_no);

        System.out.println(
                String.format("Customer with card number %d deleted from the database\n", card_no)
        );

        c.closeConnection();

    }

    @Override
    public long getCustomerCardNoByAadhaar(long aadhaar) throws SQLException {

        String sql = String.format(QUERY.getCustomerCardNoByAadhaar, aadhaar);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        long card_no = 0L;

        if (rs.next()) {
            card_no = rs.getLong("card_no");
        }

        c.closeConnection();

        return card_no;

    }

    @Override
    public boolean checkCustomerExists(long card_no) throws SQLException { // Check if a customer-card pair exists

        String sql = String.format(QUERY.checkCustomerCardPaired, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        boolean exists = rs.next();

        c.closeConnection();

        return exists;

    }

    @Override
    public boolean checkCustomerExistsByAadhaar(long aadhaar) throws SQLException {

        String sql = String.format(QUERY.checkCustomerExistsByAadhaar, aadhaar);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        boolean exists = rs.next();

        c.closeConnection();

        return exists;

    }




    // GETTERS
    // These methods return the value of the specified column
    // in the actual data type specified in the database

    @Override
    public String getCustomerFirstName(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerName, card_no);

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
    public String getCustomerMiddleName(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerName, card_no);

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
    public String getCustomerLastName(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerName, card_no);

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
    public long getCustomerAadhaar(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAadhaar, card_no);

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
    public String getCustomerHouseNo(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAddress, card_no);

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
    public String getCustomerStreetName(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAddress, card_no);

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
    public String getCustomerCity(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAddress, card_no);

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
    public String getCustomerState(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAddress, card_no);

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
    public String getCustomerCountry(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAddress, card_no);

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
    public String getCustomerZipCode(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAddress, card_no);

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
    public String getCustomerPhone(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerPhone, card_no);

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
    public String getCustomerEmail(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerEmail, card_no);

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
    public void setCustomerInfo(Customer customer) throws SQLException {

        long card_no = customer.getCardNo(); String first_name = customer.getFirstName();
        String middle_name = customer.getMiddleName(); String last_name = customer.getLastName();
        long aadhaar = customer.getAadhaar(); String house_no = customer.getHouseNo();
        String street_name = customer.getStreetName(); String city = customer.getCity();
        String state = customer.getState(); String country = customer.getCountry();
        String zip_code = customer.getZipCode(); String phone = customer.getPhone();
        String email = customer.getEmail();

        String sql = String.format(QUERY.setCustomerInfo, first_name, middle_name, last_name, aadhaar,
                                   house_no, street_name, city, state, country, zip_code, phone, email, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(
                String.format("Customer with card number %d updated in database\n", card_no)
        );

        c.closeConnection();

    }

    @Override
    public void setCustomerName(String first_name, String middle_name,
                                   String last_name, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCustomerName, first_name, middle_name, last_name, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setCustomerAadhaar(long aadhaar, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCustomerAadhaar, aadhaar, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setCustomerAddress(String house_no, String street_name, String city, String state,
                                      String country, String zip_code, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCustomerAddress, house_no,
                street_name, city, state, country, zip_code, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setCustomerPhone(String phone, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCustomerPhone, phone, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setCustomerEmail(String email, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCustomerEmail, email, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();


    }




    // RESULT SET METHODS
    // These methods are used to return a ResultSet object to the calling method for further processing
    // and will be used to construct table models to populate the GUI

    @Override
    public ResultSet getCustomerInfo(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getCustomerAccountInfo(long card_no) throws SQLException { // Customer AND card details

        String sql = String.format(QUERY.getCustomerAccountInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getCustomerInfoByAadhaar(long aadhaar) throws SQLException { // GUI use only

        String sql = String.format(QUERY.getCustomerInfoByAadhaar, aadhaar);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getCustomerAccountInfoByAadhaar(long aadhaar) throws SQLException { // GUI use only

        String sql = String.format(QUERY.getCustomerAccountInfoByAadhaar, aadhaar);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllCustomersInfo() throws SQLException {

        String sql = QUERY.getAllCustomersInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }

    @Override
    public ResultSet getAllCustomersAccountInfo() throws SQLException { // Customer AND card details

        String sql = QUERY.getAllCustomersAccountInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs;

    }




    // TABLE MODEL METHODS
    // These methods construct table models from passed ResultSet objects
    // in order to populate GUI tables with connection-independent data captures

    @Override
    public TableModel getCustomerInfoTableModel(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    @Override
    public TableModel getCustomerAccountInfoTableModel(long card_no) throws SQLException { // Customer AND card details

        String sql = String.format(QUERY.getCustomerAccountInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    @Override
    public TableModel getCustomerInfoByAadhaarTableModel(long aadhaar) throws SQLException { // GUI use only

        String sql = String.format(QUERY.getCustomerInfoByAadhaar, aadhaar);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    @Override
    public TableModel getCustomerAccountInfoByAadhaarTableModel(long aadhaar) throws SQLException { // GUI use only

        String sql = String.format(QUERY.getCustomerAccountInfoByAadhaar, aadhaar);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    @Override
    public TableModel getAllCustomersInfoTableModel() throws SQLException {

        String sql = QUERY.getAllCustomersInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    @Override
    public TableModel getAllCustomersAccountInfoTableModel() throws SQLException { // Customer AND card details

        String sql = QUERY.getAllCustomersAccountInfo;

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
    public String getCustomerFirstNameString(long card_no) throws SQLException {

        String first_name = getCustomerFirstName(card_no);
        return first_name;

    }

    @Override
    public String getCustomerMiddleNameString(long card_no) throws SQLException {

        String middle_name = getCustomerMiddleName(card_no);
        return middle_name;

    }

    @Override
    public String getCustomerLastNameString(long card_no) throws SQLException {

        String last_name = getCustomerLastName(card_no);
        return last_name;

    }

    @Override
    public String getCustomerAadhaarString(long card_no) throws SQLException {

        long aadhaar = getCustomerAadhaar(card_no);
        return String.valueOf(aadhaar);

    }

    @Override
    public Map<String, String> getCustomerAddressMap(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAddress, card_no);

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
    public String getCustomerPhoneString(long card_no) throws SQLException {

        String phone = getCustomerPhone(card_no);
        return phone;

    }

    @Override
    public String getCustomerEmailString(long card_no) throws SQLException {

        String email = getCustomerEmail(card_no);
        return email;

    }

    public Customer getCustomerInfoString(long card_no) throws SQLException {

        Customer tempCustomer = new Customer();
        String sql = String.format(QUERY.getCustomerInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()) {
            tempCustomer = convertRowToCustomer(rs);
        }

        c.closeConnection();

        return tempCustomer;

    }

    public String getCustomerAccountInfoString(long card_no) throws SQLException {

        StringBuilder sb = new StringBuilder();
        CardImplement tempCardImplement = new CardImplement();

        Customer tempCustomer = new Customer();
        Card tempCard = new Card();

        String sql = String.format(QUERY.getCustomerAccountInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()){
            do {
                tempCustomer = convertRowToCustomer(rs);
                tempCard = tempCardImplement.convertRowToCard(rs);
            } while (rs.next());
        }

        sb.append(tempCustomer.toString());
        sb.append(tempCard.toString());

        c.closeConnection();

        return sb.toString();

    }

    public String getAllCustomersInfoString() throws SQLException {

        String sql = QUERY.getAllCustomersInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToCustomer(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }

    public String getAllCustomersAccountInfoString() throws SQLException {

        String sql = QUERY.getAllCustomersAccountInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(getCustomerAccountInfoString(rs.getLong("card_no")));
        }

        c.closeConnection();

        return sb.toString();

    }




    // HELPER METHODS

    @Override
    public Customer convertRowToCustomer(ResultSet rs) throws SQLException {

        Customer tempCustomer = new Customer();

        tempCustomer.setCardNo(rs.getLong("card_no"));
        tempCustomer.setFirstName(rs.getString("first_name"));
        tempCustomer.setMiddleName(rs.getString("middle_name"));
        tempCustomer.setLastName(rs.getString("last_name"));
        tempCustomer.setAadhaar(rs.getLong("aadhaar"));
        tempCustomer.setHouseNo(rs.getString("house_no"));
        tempCustomer.setStreetName(rs.getString("street_name"));
        tempCustomer.setCity(rs.getString("city"));
        tempCustomer.setState(rs.getString("state"));
        tempCustomer.setCountry(rs.getString("country"));
        tempCustomer.setZipCode(rs.getString("zip_code"));
        tempCustomer.setPhone(rs.getString("phone"));
        tempCustomer.setEmail(rs.getString("email"));

        return tempCustomer;

    }
}

