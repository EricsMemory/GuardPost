package org.eric.guardpost.guardpostapi.util;

/** Utility class with non-specific methods */
public class EmailUtils {

    /** Method for extracting domain out of a full email address */
    public static String extractDomain(String email){
        if (email == null || !email.contains("@")){
            return null;
        }
        return email.substring(email.indexOf("@")+1);
    }
}
