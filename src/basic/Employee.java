package basic;

public class Employee {

    // EMPLOYEE ATTRIBUTES

    private int employee_id;
    private long aadhaar;
    private String password, first_name, middle_name, last_name, house_no,
            street_name, city, state, country, zip_code, phone, email;


    // CONSTRUCTORS

    // Following constructor takes all parameters

    public Employee(int employee_id, String password, String first_name,
                    String middle_name, String last_name, long aadhaar,
                    String house_no, String street_name, String city, String state,
                    String country, String zip_code, String phone, String email) {

        this.employee_id = employee_id;
        this.password = password;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.aadhaar = aadhaar;
        this.house_no = house_no;
        this.street_name = street_name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip_code = zip_code;
        this.phone = phone;
        this.email = email;

    }

    // Following constructor takes employee_id as parameter

    public Employee(int employee_id) {
        this.employee_id = employee_id;
    }

    // Following constructor takes no parameters and initializes all attributes to null
    // Note that employee_id, password, first_name, last_name, city, state, and country
    // are defined as NOT-NULL in the database

    public Employee() {

        this.employee_id = 0;
        this.password = null;
        this.first_name = null;
        this.middle_name = null;
        this.last_name = null;
        this.aadhaar = 0;
        this.house_no = null;
        this.street_name = null;
        this.city = null;
        this.state = null;
        this.country = null;
        this.zip_code = null;
        this.phone = null;
        this.email = null;

    }


    // GETTERS

    public int getEmployeeID() {
        return employee_id;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getMiddleName() {
        return middle_name;
    }

    public String getLastName() {
        return last_name;
    }

    public long getAadhaar() {
        return aadhaar;
    }

    public String getHouseNo() {
        return house_no;
    }

    public String getStreetName() {
        return street_name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zip_code;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }


    // SETTERS

    public void setEmployeeID(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setMiddleName(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setAadhaar(long aadhaar) {
        this.aadhaar = aadhaar;
    }

    public void setHouseNo(String house_no) {
        this.house_no = house_no;
    }

    public void setStreetName(String street_name) {
        this.street_name = street_name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(String zip_code) {
        this.zip_code = zip_code;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // METHODS

    public String toString(){

        String ret =
                """
               EMPLOYEE
               Employee ID: %d
               Name: %s %s %s
               Aadhaar: %d
               Address: %s %s, %s, %s, %s %s
               Phone: %s
               Email: %s
                """;

        return String.format(ret, employee_id, first_name, middle_name, last_name, aadhaar,
                house_no, street_name, city, state, country, zip_code, phone, email);

    }



}
