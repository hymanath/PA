//PartyAnalyst
// Source File Name:   Util.java

package com.itgrids.partyanalyst.utils;

import java.math.BigDecimal;import java.security.MessageDigest;import java.security.NoSuchAlgorithmException;import java.text.SimpleDateFormat;import java.util.ArrayList;import java.util.Date;import java.util.List;import javax.mail.internet.AddressException;import javax.mail.internet.InternetAddress;

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
    }    public static List<Date> getDates(String startDateString,String endDateString,SimpleDateFormat sdf){      	List<Date> datesList = new ArrayList<Date>();      	Date startDate = null;      	Date endDate = null;      	try{      		if(startDateString != null && !startDateString.isEmpty()){     	    	 startDate = sdf.parse(startDateString);      		}    	   	    if(endDateString != null && !endDateString.isEmpty()){    	   	    	 endDate = sdf.parse(endDateString);    	   	    }    		}catch(Exception e){    			e.printStackTrace();    		}    	    datesList.add(0,startDate);    	    datesList.add(1,endDate);    	    return datesList;      }    public static Double calculatePercantage(Long subCount,Long totalCount){		Double d=0.0d;		if(subCount.longValue()>0l && totalCount.longValue()==0l)		System.out.println("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);		if(totalCount.longValue() > 0l){			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 		}		return d;	}
}
