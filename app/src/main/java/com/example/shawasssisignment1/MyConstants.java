package com.example.shawasssisignment1;

import java.util.regex.Pattern;

public class MyConstants {

    public MyConstants() {

    }

    static final int MIN_AGE = 18;

    //Keys for bundled extras
    static final String KEY_NAME = "name";
    static final String KEY_BDAY_BTN_TXT = "bday_btn_txt";
    static final String KEY_IMG = "profile_img";
    static final String KEY_AGE = "user_age";
    static final String KEY_DESC = "user_description";
    static final String KEY_OCC = "user_occupation";
    static final String KEY_RV_STATE = "recycleview_state";

    //messages
    static final String OCC_MSG = "You gotta have a job to get a date.";
    static final String DOB_MSG = "You must 18 or older, kiddo.";
    static final String EMAIL_MSG = "Re-enter a valid email!";
    static final String NAME_MSG = "Please enter a name!";
    static final String AGE_MSG = "Your age is: ";
    static final String IMG_MSG = "Please select a photo";
    static final String MINMAX_MSG = "Please enter a proper range";
    static final String PEDO_MSG = "Adults should date adults\nMinimum Age must be 18 or older";

    //db
    static final String DB_PRIMARYKEY = "clint_app";

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
}
