package module;

import basic.Card;
import basic.Customer;
import basic.DatabaseConnection;
import basic.QUERY;
import dao.CustomerDAO;

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
    public void addCustomer(String first_name, String middle_name, String last_name, long aadhaar,
                            String house_no, String street_name, String city, String state, String country,
                            String zip_code, String phone, String email) throws SQLException {

        CardImplement tempCardImplement = new CardImplement(); // Create a new card for the customer
        long card_no = tempCardImplement.addCard(); // Get the new card number and add it to CARDS schema

        String sql = String.format(QUERY.addCustomer, card_no, first_name, middle_name, last_name, aadhaar,
                                   house_no, street_name, city, state, country, zip_code, phone, email);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(String.format("Customer '%s %s %s' added to the database with card number %d",
                first_name, middle_name, last_name, card_no));

        c.closeConnection();

    }

    @Override
    public void deleteCustomer(long card_no) throws SQLException {

        CardImplement tempCardImplement = new CardImplement();
        String sql = String.format(QUERY.deleteCustomer, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);
        tempCardImplement.deleteCard(card_no);

        System.out.println(String.format("Customer with card number %d deleted from the database", card_no));

        c.closeConnection();

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
    public void changeCustomerName(String first_name, String middle_name,
                                   String last_name, long card_no) throws SQLException {

        String sql = String.format(QUERY.changeCustomerName, first_name, middle_name, last_name, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeCustomerAadhaar(long aadhaar, long card_no) throws SQLException {

        String sql = String.format(QUERY.changeCustomerAadhaar, aadhaar, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeCustomerAddress(String house_no, String street_name, String city, String state,
                                      String country, String zip_code, long card_no) throws SQLException {

        String sql = String.format(QUERY.changeCustomerAddress, house_no,
                street_name, city, state, country, zip_code, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeCustomerPhone(String phone, long card_no) throws SQLException {

        String sql = String.format(QUERY.changeCustomerPhone, phone, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void changeCustomerEmail(String email, long card_no) throws SQLException {

        String sql = String.format(QUERY.changeCustomerEmail, email, card_no);

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

    // STRING METHODS
    // These methods are used in command line tests and populating GUI text fields
    // They return a String or string representation of an object to the calling method

    @Override
    public String getCustomerName(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerName, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return String.format("%s %s %s", rs.getString("first_name"),
                rs.getString("middle_name"), rs.getString("last_name"));

    }

    @Override
    public String getCustomerAadhaar(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerAadhaar, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return String.valueOf(rs.getLong("aadhaar"));

    }

    @Override
    public Map<String, String> getCustomerAddress(long card_no) throws SQLException {

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

        rs.close();

        return addressMap;

    }

    @Override
    public String getCustomerPhone(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerPhone, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.getString("phone");

    }

    @Override
    public String getCustomerEmail(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCustomerEmail, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.getString("email");

    }

    public Customer getCustomerInfoString(long card_no) throws SQLException {

        Customer tempCustomer = new Customer();
        String sql = String.format(QUERY.getCustomerInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (!rs.next()){
            System.out.println("No customer found with card number: " + card_no);
        } else {
            do {
                tempCustomer = convertRowToCustomer(rs);
            } while (rs.next());
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

        if (!rs.next()){
            System.out.println("No customer found with card number: " + card_no);
        } else {
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

