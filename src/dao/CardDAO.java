package dao;

import basic.Card;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CardDAO {

    long addCard() throws SQLException; // returns generated card_no

    void deleteCard(long card_no) throws SQLException;

    float addBalanceToCard(float amount, long card_no) throws SQLException;

    boolean checkCardExists(long card_no) throws SQLException;

    long createUniqueCardNo();

    ResultSet getCardInfo(long card_no) throws SQLException;

    ResultSet getAllCardInfo() throws SQLException;

    String getCardIssueDate(long card_no) throws SQLException;

    String getCardExpiryDate(long card_no) throws SQLException;

    String getCardBalance(long card_no) throws SQLException;

    String getDateLastPayment(long card_no) throws SQLException;

    String getAmountLastPayment(long card_no) throws SQLException;

    Card convertRowToCard(ResultSet rs) throws SQLException;


}
