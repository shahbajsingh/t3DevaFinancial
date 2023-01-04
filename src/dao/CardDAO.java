package dao;

import basic.Card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public interface CardDAO {

    long addCard() throws SQLException; // returns generated card_no

    void deleteCard(long card_no) throws SQLException;

    float addBalanceToCard(float amount, long card_no) throws SQLException; // returns amount added

    float subtractBalanceFromCard(float amount, long card_no) throws SQLException; // returns amount subtracted

    boolean checkCardExists(long card_no) throws SQLException;

    // GETTERS

    Timestamp getCardIssueDate(long card_no) throws SQLException;

    Timestamp getCardExpiryDate(long card_no) throws SQLException;

    float getCardBalance(long card_no) throws SQLException;

    Timestamp getCardDateLastPayment(long card_no) throws SQLException;

    float getCardAmtLastPayment(long card_no) throws SQLException;

    // SETTERS

    void setCardInfo(Card card) throws SQLException;

    void setCardIssueDate(Timestamp issue_date, long card_no) throws SQLException;

    void setCardExpiryDate(Timestamp expiry_date, long card_no) throws SQLException;

    void setCardBalance(float balance, long card_no) throws SQLException;

    void setCardDateLastPayment(Timestamp date_last_payment, long card_no) throws SQLException;

    void setCardAmtLastPayment(float amt_last_payment, long card_no) throws SQLException;

    // RESULT SET METHODS

    ResultSet getCardInfo(long card_no) throws SQLException;

    ResultSet getAllCardsInfo() throws SQLException;

    // STRING METHODS

    String getCardIssueDateString(long card_no) throws SQLException;

    String getCardExpiryDateString(long card_no) throws SQLException;

    String getCardBalanceString(long card_no) throws SQLException;

    String getCardDateLastPaymentString(long card_no) throws SQLException;

    String getCardAmtLastPaymentString(long card_no) throws SQLException;

    // HELPER METHODS

    long createUniqueCardNo(); // returns a unique card_no

    Card convertRowToCard(ResultSet rs) throws SQLException;


}
