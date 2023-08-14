package com.elearn.app.elearnapi.utilities;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtilities {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean isPasswordCorrect(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
