package com.example.shawasssisignment1;


import org.junit.Test;

import static org.junit.Assert.*;

public class JunitTest {

    @Test
    public void testConstants() {
        assertEquals("name", MyConstants.KEY_NAME);
        assertEquals("bday_btn_txt", MyConstants.KEY_BDAY_BTN_TXT);
        assertEquals("profile_img", MyConstants.KEY_IMG);
        assertEquals("user_age", MyConstants.KEY_AGE);
        assertEquals("user_description", MyConstants.KEY_DESC);
        assertEquals("user_occupation", MyConstants.KEY_OCC);
        assertEquals("recycleview_state", MyConstants.KEY_RV_STATE);
        assertEquals("You gotta have a job to get a date.", MyConstants.OCC_MSG);
        assertEquals("You must 18 or older, kiddo.", MyConstants.DOB_MSG);
        assertEquals("Re-enter a valid email!", MyConstants.EMAIL_MSG);
        assertEquals("Please enter a name!", MyConstants.NAME_MSG);
        assertEquals("Your age is: ", MyConstants.AGE_MSG);
        assertEquals("Please select a photo", MyConstants.IMG_MSG);
    }

}
