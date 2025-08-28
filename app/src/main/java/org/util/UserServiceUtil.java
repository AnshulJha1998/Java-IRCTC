package org.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {

    public static String hashPassword(String pass){
        return BCrypt.hashpw(pass,BCrypt.gensalt());
    }

    public static Boolean checkPassword(String pass,String hashedPass){
        return BCrypt.checkpw(pass,hashedPass);
    }
}
