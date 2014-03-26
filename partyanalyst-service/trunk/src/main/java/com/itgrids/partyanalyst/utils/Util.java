//PartyAnalyst
// Source File Name:   Util.java

package com.itgrids.partyanalyst.utils;

import java.security.MessageDigest;import java.security.NoSuchAlgorithmException;import javax.mail.internet.AddressException;import javax.mail.internet.InternetAddress;

// Referenced classes of package com.clovetech.esgtms.util:
//            TimeUtils

public class Util
{

    public Util()
    {
    }

    public static String MD5(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byteToHexString(md.digest(input.getBytes()));
        }
        catch(NoSuchAlgorithmException ex) { }
        catch(NullPointerException ex) { }
        return null;
    }

    public static String byteToHexString(byte hash[])
    {
        StringBuffer buf = new StringBuffer(hash.length * 2);
        for(int i = 0; i < hash.length; i++)
        {
            if((hash[i] & 0xff) < 16)
                buf.append("0");
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }

        return buf.toString();
    }

    public static boolean sameStrings(String str1, String str2)
    {
        return str1 != null ? str1.equals(str2) : str2 == null;
    }

    public static boolean isEmpty(String str)
    {
        return str == null || "".equals(str.trim());
    }

    public static boolean isEmailValid(String email)
    {
        int dogPos = email.indexOf('@');
        if(dogPos == -1 || dogPos >= email.lastIndexOf('.') || email.charAt(dogPos + 1) == '.')
            return false;
        try
        {
            InternetAddress.parse(email);
        }
        catch(AddressException ex)
        {
            return false;
        }
        return true;
    }

    public static String nl2br(String value)
    {
        if(value == null)
            return null;
        char content[] = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for(int i = 0; i < content.length; i++)
            if(content[i] == '\n')
                result.append("<br />\n");
            else
                result.append(content[i]);

        return result.toString();
    }
}
