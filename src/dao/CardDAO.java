package dao;

import basic.Card;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CardDAO {

    long addCard() throws SQLException; // returns generated card_no

    void deleteCard(long card_no) throws SQLException;

    boolean checkCardExists(long card_no) throws SQLException;

    long createUniqueCardNo();

    ResultSet getCardInfo(long card_no) throws SQLException;

    ResultSet getAllCardInfo() throws SQLException;

    Card convertRowToCard(ResultSet rs) throws SQLException;


}
