package basic;

public class QUERY {

    // CUSTOMER RELATED QUERIES

    public static final String addCustomer = "INSERT INTO TDF2.CUSTOMERS " +
            "(card_no, first_name, middle_name, last_name, aadhaar, house_no, " +
            "street_name, city, state, country, zip_code, phone, email) " +
            "VALUES (%d, '%s', '%s', '%s', %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";

    public static final String deleteCustomer = "DELETE FROM TDF2.CUSTOMERS " +
            "WHERE card_no = %d";

    public static final String getCustomerInfo = "SELECT * FROM TDF2.CUSTOMERS " +
            "WHERE card_no = %d";

    public static final String getAllCustomerInfo = "SELECT * FROM TDF2.CUSTOMERS";

    public static final String getCustomerAccountInfo = "SELECT * FROM TDF2.CUSTOMERS " +
            "INNER JOIN TDF2.CARDS ON TDF2.CUSTOMERS.card_no = TDF2.CARDS.card_no " +
            "WHERE TDF2.CUSTOMERS.card_no = %d";

    public static final String getAllCustomerAccountInfo = "SELECT * FROM TDF2.CUSTOMERS " +
            "INNER JOIN TDF2.CARDS ON TDF2.CUSTOMERS.card_no = TDF2.CARDS.card_no";

    public static final String getCustomerName = "SELECT first_name, middle_name, last_name " +
            "FROM TDF2.CUSTOMERS WHERE card_no = %d";

    public static final String getCustomerAadhaar = "SELECT aadhaar FROM TDF2.CUSTOMERS " +
            "WHERE card_no = %d";

    public static final String getCustomerAddress = "SELECT house_no, street_name, city, state, country, zip_code " +
            "FROM TDF2.CUSTOMERS WHERE card_no = %d";

    public static final String getCustomerPhone = "SELECT phone FROM TDF2.CUSTOMERS " +
            "WHERE card_no = %d";

    public static final String getCustomerEmail = "SELECT email FROM TDF2.CUSTOMERS " +
            "WHERE card_no = %d";

    public static final String changeCustomerName = "UPDATE TDF2.CUSTOMERS " +
            "SET first_name = '%s', middle_name = '%s', last_name = '%s' " +
            "WHERE card_no = %d";

    public static final String changeCustomerAadhaar = "UPDATE TDF2.CUSTOMERS " +
            "SET aadhaar = %d WHERE card_no = %d";

    public static final String changeCustomerAddress = "UPDATE TDF2.CUSTOMERS " +
            "SET house_no = '%s', street_name = '%s', city = '%s', state = '%s', country = '%s', zip_code = '%s' " +
            "WHERE card_no = %d";

    public static final String changeCustomerPhone = "UPDATE TDF2.CUSTOMERS " +
            "SET phone = '%s' WHERE card_no = %d";

    public static final String changeCustomerEmail = "UPDATE TDF2.CUSTOMERS " +
            "SET email = '%s' WHERE card_no = %d";

    public static final String checkCustomerCardPaired = "SELECT * FROM TDF2.CUSTOMERS " +
            "INNER JOIN TDF2.CARDS ON TDF2.CUSTOMERS.card_no = TDF2.CARDS.card_no " +
            "WHERE TDF2.CUSTOMERS.card_no = %d";


    // EMPLOYEE RELATED QUERIES

    public static final String addEmployee = "INSERT INTO TDF2.EMPLOYEES " + // employee_id is auto-incremented
            "(password, first_name, middle_name, last_name, aadhaar, " +
            " house_no, street_name, city, state, country, zip_code, phone, email) " +
            "VALUES ('%s', '%s', '%s', '%s', %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";

    public static final String deleteEmployee = "DELETE FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d";

    public static final String checkEmployeePassword = "SELECT password FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d AND password = '%s'";

    public static final String getEmployeeInfo = "SELECT (employee_id, first_name, middle_name, last_name, " +
            "aadhaar, house_no, street_name, city, state, country, zip_code, phone, email) " +
            "FROM TDF2.EMPLOYEES WHERE employee_id = %d";

    public static final String getAllEmployeeInfo = "SELECT * FROM TDF2.EMPLOYEES";

    public static final String getEmployeeInfoByLastName = "SELECT (employee_id, first_name, middle_name, " +
            "last_name, aadhaar, house_no, street_name, city, state, country, zip_code, phone, email) " +
            "FROM TDF2.EMPLOYEES WHERE last_name = '%s'";

    public static final String getEmployeeID = "SELECT employee_id FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d";

    public static final String getEmployeePassword = "SELECT password FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d";

    public static final String getEmployeeName = "SELECT first_name, middle_name, last_name " +
            "FROM TDF2.EMPLOYEES WHERE employee_id = %d";

    public static final String getEmployeeAadhaar = "SELECT aadhaar FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d";

    public static final String getEmployeeAddress = "SELECT house_no, street_name, city, state, country, zip_code " +
            "FROM TDF2.EMPLOYEES WHERE employee_id = %d";

    public static final String getEmployeePhone = "SELECT phone FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d";

    public static final String getEmployeeEmail = "SELECT email FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d";

    public static final String changeEmployeePassword = "UPDATE TDF2.EMPLOYEES " +
            "SET password = '%s' " +
            "WHERE employee_id = %d";

    public static final String changeEmployeeName = "UPDATE TDF2.EMPLOYEES " +
            "SET first_name = '%s', middle_name = '%s', last_name = '%s' " +
            "WHERE employee_id = %d";

    public static final String changeEmployeeAadhaar = "UPDATE TDF2.EMPLOYEES " +
            "SET aadhaar = %d WHERE employee_id = %d";

    public static final String changeEmployeeAddress = "UPDATE TDF2.EMPLOYEES " +
            "SET house_no = '%s', street_name = '%s', city = '%s', state = '%s', country = '%s', zip_code = '%s' " +
            "WHERE employee_id = %d";

    public static final String changeEmployeePhone = "UPDATE TDF2.EMPLOYEES " +
            "SET phone = '%s' WHERE employee_id = %d";

    public static final String changeEmployeeEmail = "UPDATE TDF2.EMPLOYEES " +
            "SET email = '%s' WHERE employee_id = %d";


    // CARD RELATED QUERIES

    public static final String addCard = "INSERT INTO TDF2.CARDS " + // last payment date and amount default to null
            "(card_no, issue_date, expiry_date, balance) " +
            "VALUES (%d, '%s', '%s', %.2f)";

    public static final String deleteCard = "DELETE FROM TDF2.CARDS " +
            "WHERE card_no = %d";

    public static final String getAllCardInfo = "SELECT * FROM TDF2.CARDS";

    public static final String getCardInfo = "SELECT * FROM TDF2.CARDS " +
            "WHERE card_no = %d";


    // LOAN RELATED QUERIES

    public static final String addLoan = "INSERT INTO TDF2.LOANS " +
            "(loan_id, loan_date, card_no, loan_value, interest_rate, is_active) " +
            "VALUES (%d, '%s', %d, %.2f, .2f, %b)";

    public static final String deleteLoan = "DELETE FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoanInfo = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getAllLoanInfo = "SELECT * FROM TDF2.LOANS";



    // PAYMENT RELATED QUERIES

    public static final String addPayment = "INSERT INTO TDF2.PAYMENTS " +
            "(payment_id, date_payment, amt_payment card_no, loan_id) " +
            "VALUES (%d, '%s', %.2f, %d, %d)";

    public static final String deletePayment = "DELETE FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String getPaymentInfo = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String getAllPaymentInfo = "SELECT * FROM TDF2.PAYMENTS";




}
