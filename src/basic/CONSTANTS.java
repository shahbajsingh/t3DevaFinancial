package basic;

import java.sql.Timestamp;

public class CONSTANTS {

    public static final Timestamp CURRENT_TIME = new Timestamp(System.currentTimeMillis());
    public static final Timestamp ONE_MONTH_FROM_NOW = new Timestamp(System.currentTimeMillis() + 2592000000L);
    public static final Timestamp THREE_MONTHS_FROM_NOW = new Timestamp(System.currentTimeMillis() + 7776000000L);
    public static final Timestamp SIX_MONTHS_FROM_NOW = new Timestamp(System.currentTimeMillis() + 15552000000L);
    public static final Timestamp ONE_YEAR_FROM_NOW = new Timestamp(System.currentTimeMillis() + 31536000000L);


}
