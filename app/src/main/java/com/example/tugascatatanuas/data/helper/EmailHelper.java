package com.example.tugascatatanuas.data.helper;

public class EmailHelper {
    public static String usernameFromEmail(java.lang.String email){
        if (!email.contains("@")) return email;
        return email.split("@")[0];
    }
}
