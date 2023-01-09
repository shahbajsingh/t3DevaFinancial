package module;

import static basic.CONSTANTS.*;

import basic.Card;
import basic.DatabaseConnection;
import basic.QUERY;
import dao.CardDAO;
import net.proteanit.sql.DbUtils;

import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CardImplement implements CardDAO {

    // By default a card is created with:
    // A randomized card number
    // An issue date of the current time
    // An expiry date of one year from now
    // A balance of 0.00f
    // No last payment date (null from the database)
    // No last payment amount (null from the database)

    public CardImplement() {
        super();
    }

    @Override
    public long addCard() throws SQLException {

        long card_no = createUniqueCardNo();
        Timestamp issue_date = CURRENT_TIME;
        Timestamp expiry_date = ONE_YEAR_FROM_NOW;
        float balance = 0.00f;

        String sql = String.format(QUERY.addCard, card_no, issue_date, expiry_date, balance);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

        return card_no;

    }

    @Override
    public void deleteCard(long card_no) throws SQLException {

        String sql = String.format(QUERY.deleteCard, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public float addBalanceToCard(float amount, long card_no) throws SQLException {

        String sql = String.format(QUERY.addBalanceToCard, amount, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

        return amount;

    }

    @Override
    public float subtractBalanceFromCard(float amount, long card_no) throws SQLException {

        String sql = String.format(QUERY.subtractBalanceFromCard, amount, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

        return amount;

    }

    @Override
    public boolean checkCardExists(long card_no) throws SQLException { // check if a customer-card pair exists

        String sql = String.format(QUERY.checkCustomerCardPaired, card_no);

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
    public Timestamp getCardIssueDate(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardIssueDate, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        Timestamp issue_date = null;

        if (rs.next()) {
            issue_date = rs.getTimestamp("issue_date");
        }

        c.closeConnection();

        return issue_date;

    }

    @Override
    public Timestamp getCardExpiryDate(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardExpiryDate, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        Timestamp expiry_date = null;

        if (rs.next()) {
            expiry_date = rs.getTimestamp("expiry_date");
        }

        c.closeConnection();

        return expiry_date;

    }

    @Override
    public float getCardBalance(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardBalance, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        float balance = 0.00f;

        if (rs.next()) {
            balance = rs.getFloat("balance");
        }

        c.closeConnection();

        return balance;

    }

    @Override
    public Timestamp getCardDateLastPayment(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardDateLastPayment, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        Timestamp date_last_payment = null;

        if (rs.next()) {
            date_last_payment = rs.getTimestamp("date_last_payment");
        }

        c.closeConnection();

        return date_last_payment;
    }

    @Override
    public float getCardAmtLastPayment(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardAmtLastPayment, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        float amount_last_payment = 0.00f;

        if (rs.next()) {
            amount_last_payment = rs.getFloat("amt_last_payment");
        }

        c.closeConnection();

        return amount_last_payment;

    }




    // SETTERS
    // These methods update the value of the specified column
    // in the database using the passed value

    @Override
    public void setCardInfo(Card card) throws SQLException {

        long card_no = card.getCardNo();
        Timestamp issue_date = card.getIssueDate(); Timestamp expiry_date = card.getExpiryDate();
        float balance = card.getBalance(); Timestamp date_last_payment = card.getDateLastPayment();
        float amt_last_payment = card.getAmtLastPayment();

        String sql = String.format(QUERY.setCardInfo, issue_date, expiry_date,
                balance, date_last_payment, amt_last_payment, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        System.out.println(
                String.format("Card number %d updated in database\n", card_no)
        );

        c.closeConnection();

    }

    @Override
    public void setCardIssueDate(Timestamp issue_date, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCardIssueDate, issue_date, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setCardExpiryDate(Timestamp expiry_date, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCardExpiryDate, expiry_date, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setCardBalance(float balance, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCardBalance, balance, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public void setCardDateLastPayment(Timestamp date_last_payment, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCardDateLastPayment, date_last_payment, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

    }

    @Override
    public void setCardAmtLastPayment(float amt_last_payment, long card_no) throws SQLException {

        String sql = String.format(QUERY.setCardAmtLastPayment, amt_last_payment, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }




    // RESULT SET METHODS
    // These methods are used to return a ResultSet object to the calling method for further processing
    // and will be used to construct table models to populate the GUI

    @Override
    public ResultSet getCardInfo(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        return c.selectSQL(sql);

    }

    @Override
    public ResultSet getAllCardsInfo() throws SQLException {

        String sql = QUERY.getAllCardsInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        return c.selectSQL(sql);

    }




    // TABLE MODEL METHODS
    // These methods construct table models from passed ResultSet objects
    // in order to populate GUI tables with connection-independent data captures

    @Override
    public TableModel getCardInfoTableModel(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);
        TableModel tm = DbUtils.resultSetToTableModel(rs);

        c.closeConnection();

        return tm;

    }

    @Override
    public TableModel getAllCardsInfoTableModel() throws SQLException {

        String sql = QUERY.getAllCardsInfo;

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
    public String getCardIssueDateString(long card_no) throws SQLException {

        Timestamp issue_date = getCardIssueDate(card_no);
        return issue_date.toString();

    }

    @Override
    public String getCardExpiryDateString(long card_no) throws SQLException {

        Timestamp expiry_date = getCardExpiryDate(card_no);
        return expiry_date.toString();

    }

    @Override
    public String getCardBalanceString(long card_no) throws SQLException {

        float balance = getCardBalance(card_no);
        return Float.toString(balance);

    }

    @Override
    public String getCardDateLastPaymentString(long card_no) throws SQLException {

        Timestamp date_last_payment = getCardDateLastPayment(card_no);
        return date_last_payment.toString();

    }

    @Override
    public String getCardAmtLastPaymentString(long card_no) throws SQLException {

        float amt_last_payment = getCardAmtLastPayment(card_no);
        return Float.toString(amt_last_payment);

    }

    public Card getCardInfoString(long card_no) throws SQLException {

        Card tempCard = null;
        String sql = String.format(QUERY.getCardInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (rs.next()) {
            tempCard = convertRowToCard(rs);
        }

        c.closeConnection();

        return tempCard;

    }

    public String getAllCardsInfoString() throws SQLException {

        String sql = QUERY.getAllCardsInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        StringBuilder sb = new StringBuilder();

        while (rs.next()){
            sb.append(convertRowToCard(rs).toString());
        }

        c.closeConnection();

        return sb.toString();

    }




    // HELPER METHODS

    @Override
    public long createUniqueCardNo() { // TO-DO: Fix complexity to reduce queries

        long card_no = 0L;

        try {
            do {
                card_no = (long) (Math.random() * 100000000L);
            } while (checkCardExists(card_no));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return card_no;

    }

    @Override
    public Card convertRowToCard(ResultSet rs) throws SQLException {

        Card tempCard = new Card();

        tempCard.setCardNo(rs.getLong("card_no"));
        tempCard.setIssueDate(rs.getTimestamp("issue_date"));
        tempCard.setExpiryDate(rs.getTimestamp("expiry_date"));
        tempCard.setBalance(rs.getFloat("balance"));
        tempCard.setDateLastPayment(rs.getTimestamp("date_last_payment"));
        tempCard.setAmtLastPayment(rs.getFloat("amt_last_payment"));

        return tempCard;

    }

}

