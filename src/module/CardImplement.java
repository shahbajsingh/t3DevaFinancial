package module;

import static basic.CONSTANTS.*;

import basic.CONSTANTS;
import basic.Card;
import basic.DatabaseConnection;
import basic.QUERY;
import dao.CardDAO;

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

        System.out.println("Card has been deleted from the database: " + card_no);

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
    public void setDateAndAmountLastPayment(Timestamp date, float amount, long card_no) throws SQLException {

        String sql = String.format(QUERY.setDateAndAmountLastPayment, date, amount, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        c.executeSQL(sql);

        c.closeConnection();

    }

    @Override
    public boolean checkCardExists(long card_no) throws SQLException {

        String sql = String.format(QUERY.checkCustomerCardPaired, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.next();

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
    public ResultSet getAllCardInfo() throws SQLException {

        String sql = QUERY.getAllCardInfo;

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        return c.selectSQL(sql);

    }

    // STRING METHODS
    // These methods are used in command line tests and populating GUI text fields
    // They return a String or string representation of an object to the calling method

    @Override
    public String getCardIssueDate(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardIssueDate, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.getTimestamp("issue_date").toString();

    }

    @Override
    public String getCardExpiryDate(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardExpiryDate, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.getTimestamp("expiry_date").toString();

    }

    @Override
    public String getCardBalance(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardBalance, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return String.valueOf(rs.getFloat("balance"));

    }

    @Override
    public String getDateLastPayment(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardLastPaymentDate, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return rs.getTimestamp("last_payment_date").toString();

    }

    @Override
    public String getAmountLastPayment(long card_no) throws SQLException {

        String sql = String.format(QUERY.getCardLastPaymentAmount, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        return String.valueOf(rs.getFloat("last_payment_amount"));

    }

    public Card getCardInfoString(long card_no) throws SQLException {

        Card tempCard = null;
        String sql = String.format(QUERY.getCardInfo, card_no);

        DatabaseConnection c = new DatabaseConnection();
        Connection newConnection = c.getConnection();

        ResultSet rs = c.selectSQL(sql);

        if (!rs.next()){
            System.out.println("No card found with card number: " + card_no);
        } else {
            do {
                tempCard = convertRowToCard(rs);
            } while (rs.next());
        }

        c.closeConnection();

        return tempCard;

    }

    public String getAllCardInfoString() throws SQLException {

        String sql = QUERY.getAllCardInfo;

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
                card_no = (long) (Math.random() * 10000000000000000L);
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

