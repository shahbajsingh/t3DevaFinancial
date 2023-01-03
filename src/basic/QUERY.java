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

    public static final String getAllCustomersInfo = "SELECT * FROM TDF2.CUSTOMERS";

    public static final String getCustomerAccountInfo = "SELECT * FROM TDF2.CUSTOMERS " +
            "INNER JOIN TDF2.CARDS ON TDF2.CUSTOMERS.card_no = TDF2.CARDS.card_no " +
            "WHERE TDF2.CUSTOMERS.card_no = %d";

    public static final String getAllCustomersAccountInfo = "SELECT * FROM TDF2.CUSTOMERS " +
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

    public static final String checkEmployeeExists = "SELECT * FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d";

    public static final String getEmployeeInfo = "SELECT * FROM TDF2.EMPLOYEES " +
            "WHERE employee_id = %d"; // explicitly defined duplicate query for clarity

    public static final String getAllEmployeesInfo = "SELECT * FROM TDF2.EMPLOYEES";

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

    public static final String addBalanceToCard = "UPDATE TDF2.CARDS " +
            "SET balance = balance + %.2f " +
            "WHERE card_no = %d";

    public static final String subtractBalanceFromCard = "UPDATE TDF2.CARDS " +
            "SET balance = balance - %.2f " +
            "WHERE card_no = %d";

    public static final String setDateAndAmountLastPayment = "UPDATE TDF2.CARDS " +
            "SET date_last_payment = '%s', amt_last_payment = %.2f " +
            "WHERE card_no = %d";

    public static final String getCardInfo = "SELECT * FROM TDF2.CARDS " +
            "WHERE card_no = %d";

    public static final String getAllCardInfo = "SELECT * FROM TDF2.CARDS";

    public static final String getCardIssueDate = "SELECT issue_date FROM TDF2.CARDS " +
            "WHERE card_no = %d";

    public static final String getCardExpiryDate = "SELECT expiry_date FROM TDF2.CARDS " +
            "WHERE card_no = %d";

    public static final String getCardBalance = "SELECT balance FROM TDF2.CARDS " +
            "WHERE card_no = %d";

    public static final String getCardLastPaymentDate = "SELECT last_payment_date FROM TDF2.CARDS " +
            "WHERE card_no = %d";

    public static final String getCardLastPaymentAmount = "SELECT last_payment_amount FROM TDF2.CARDS " +
            "WHERE card_no = %d";




    // LOAN RELATED QUERIES

    public static final String addLoan = "INSERT INTO TDF2.LOANS " + // loan_id is auto-incremented
            "(loan_date, card_no, loan_value, interest_rate, amt_remaining) " + // interest_accrued defaults to 0
            "VALUES ('%s', %d, %.2f, %.2f, %.2f)"; // is_active defaults to true

    public static final String deleteLoan = "DELETE FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String checkLoanExists = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String setLoanAmtRemaining = "UPDATE TDF2.LOANS " +
            "SET amt_remaining = %.2f " +
            "WHERE loan_id = %d";

    public static final String setLoanInterestAccrued = "UPDATE TDF2.LOANS " +
            "SET interest_accrued = %.2f " +
            "WHERE loan_id = %d";

    public static final String setLoanIsActive = "UPDATE TDF2.LOANS " +
            "SET is_active = %b " +
            "WHERE loan_id = %d";

    public static final String getOldestActiveLoanIDByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d AND is_active = TRUE " +
            "ORDER BY loan_date ASC LIMIT 1";

    public static final String getLoanInfoByID = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoanDateByID = "SELECT loan_date FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoanCardNoByID = "SELECT card_no FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoanValueByID = "SELECT loan_value FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoanInterestRateByID = "SELECT interest_rate FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoanAmtRemainingByID = "SELECT amt_remaining FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoanInterestAccruedByID = "SELECT interest_accrued FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoanIsActiveByID = "SELECT is_active FROM TDF2.LOANS " +
            "WHERE loan_id = %d";

    public static final String getLoansByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d";

    public static final String getLoansInDateRangeByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d AND loan_date BETWEEN '%s' AND '%s'";

    public static final String getLoansBeforeDateByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d AND loan_date <= '%s'";

    public static final String getLoansAfterDateByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d AND loan_date >= '%s'";

    public static final String getActiveLoansByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d AND is_active = TRUE";

    public static final String getActiveLoansInDateRangeByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d AND loan_date BETWEEN '%s' AND '%s' AND is_active = TRUE";

    public static final String getActiveLoansBeforeDateByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d AND loan_date <= '%s' AND is_active = TRUE";

    public static final String getActiveLoansAfterDateByCardNo = "SELECT * FROM TDF2.LOANS " +
            "WHERE card_no = %d AND loan_date >= '%s' AND is_active = TRUE";

    public static final String getAllLoans = "SELECT * FROM TDF2.LOANS";

    public static final String getAllLoansInDateRange = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_date BETWEEN '%s' AND '%s'";

    public static final String getAllLoansBeforeDate = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_date <= '%s'";

    public static final String getAllLoansAfterDate = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_date >= '%s'";

    public static final String getAllActiveLoans = "SELECT * FROM TDF2.LOANS " +
            "WHERE is_active = TRUE";

    public static final String getAllActiveLoansInDateRange = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_date BETWEEN '%s' AND '%s' AND is_active = TRUE";

    public static final String getAllActiveLoansBeforeDate = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_date <= '%s' AND is_active = TRUE";

    public static final String getAllActiveLoansAfterDate = "SELECT * FROM TDF2.LOANS " +
            "WHERE loan_date >= '%s' AND is_active = TRUE";




    // PAYMENT RELATED QUERIES

    public static final String addPayment = "INSERT INTO TDF2.PAYMENTS " + // payment_id is auto-incremented
            "(payment_date, card_no, payment_value, loan_id) " +
            "VALUES ('%s', %d, %.2f, %d)";

    public static final String deletePayment = "DELETE FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String checkPaymentExists = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String getPaymentInfoByID = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String getPaymentDateByID = "SELECT payment_date FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String getPaymentCardNoByID = "SELECT card_no FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String getPaymentValueByID = "SELECT payment_value FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String getPaymentLoanIDByID = "SELECT loan_id FROM TDF2.PAYMENTS " +
            "WHERE payment_id = %d";

    public static final String getPaymentsByCardNo = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE card_no = %d";

    public static final String getPaymentsInDateRangeByCardNo = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE card_no = %d AND payment_date BETWEEN '%s' AND '%s'";

    public static final String getPaymentsBeforeDateByCardNo = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE card_no = %d AND payment_date <= '%s'";

    public static final String getPaymentsAfterDateByCardNo = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE card_no = %d AND payment_date >= '%s'";

    public static final String getPaymentsByLoanID = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE loan_id = %d";

    public static final String getPaymentsInDateRangeByLoanID = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE loan_id = %d AND payment_date BETWEEN '%s' AND '%s'";

    public static final String getPaymentsBeforeDateByLoanID = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE loan_id = %d AND payment_date <= '%s'";

    public static final String getPaymentsAfterDateByLoanID = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE loan_id = %d AND payment_date >= '%s'";

    public static final String getAllPayments = "SELECT * FROM TDF2.PAYMENTS";

    public static final String getAllPaymentsInDateRange = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE payment_date BETWEEN '%s' AND '%s'";

    public static final String getAllPaymentsBeforeDate = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE payment_date <= '%s'";

    public static final String getAllPaymentsAfterDate = "SELECT * FROM TDF2.PAYMENTS " +
            "WHERE payment_date >= '%s'";



}
