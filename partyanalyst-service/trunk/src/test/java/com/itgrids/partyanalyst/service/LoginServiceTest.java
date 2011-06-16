package cardinfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.clovetech.esgtms.cardinfo:
//            CardLoginDetailsBean

public class CardDB {
    
    public CardDB(String url, String dsnName, String userName, String password) {
        this.url = null;
        this.dsnName = null;
        this.userName = null;
        this.password = null;
        this.url = url;
        this.dsnName = dsnName;
        this.userName = userName;
        this.password = password;
    }
    
    public HashMap getCardDbDetails(String ioDate)
    throws Exception {
        System.out.println( "****************"  +ioDate );
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = DriverManager.getConnection( "jdbc:odbc:" +dsnName );
        PreparedStatement st = con.prepareStatement("select IOStatus,CardNo,IODate,IOTime,HolderName from  IODATA where IODate=? ORDER BY CardNo asc,IOTime");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = format.parse(ioDate);
        Date sqlDate = new Date(date.getTime());
        System.out.println( "date" +sqlDate );
        st.setDate(1, sqlDate);
        ResultSet rs = st.executeQuery();
        Timestamp ioTime = null;
        java.util.Date loginTime = null;
        java.util.Date logoutTime = null;
        cardLoginDetailsMap = new HashMap();
        Date loginDate = null;
        int counter = 0;
        int total = 0;
        boolean isLastEntry = false;
        String holderName = null;
        int prevCardNo = 0;
        int entrySize = 0;
        int exitSize = 0;
        List entryList = new ArrayList();
        List exitList = new ArrayList();
        do
        {
            if(!rs.next())
                break;
            String cardNum = rs.getString(2);
            if(!cardNum.startsWith("XX")) {
                String status = rs.getString(1);
                loginDate = rs.getDate(3);
                if(prevCardNo != Integer.parseInt(cardNum) && counter != 0) {
                    entrySize = entryList.size();
                    exitSize = exitList.size();
                    if(entrySize > 1 || entrySize == 1 && exitSize == 1) {
                        loginTime = (java.util.Date)entryList.get(0);
                        logoutTime = (java.util.Date)exitList.get(exitList.size() - 1);
                        String effectiveHours = calculateEffectiveHours(entryList, exitList);
                        System.out.println( "Card NO :   " +prevCardNo +" LoginTime : " +loginTime +"     LogoutTime : " +logoutTime +"  Effective Hours :  " +effectiveHours );
                        cardLoginDetailsMap.put(new Integer(prevCardNo), new CardLoginDetailsBean(prevCardNo, new
                                java.util.Date(loginDate.getTime()), loginTime, logoutTime, effectiveHours));
                        
                        System.out.println("--------------------------------------------------------------------------------------------------------");
                    }
                    total++;
                    entryList.clear();
                    exitList.clear();
                    isLastEntry = false;
                    counter = 0;
                }
                prevCardNo = Integer.parseInt(cardNum);
                counter++;
                ioTime = rs.getTimestamp(4);
                if(!isLastEntry) {
                    if("Entry".equals(status)) {
                        entryList.add(ioTime);
                        isLastEntry = true;
                    }
                } else
                    if(isLastEntry)
                        if("Exit".equals(status)) {
                    exitList.add(ioTime);
                    isLastEntry = false;
                        } else {
                    entryList.set(entryList.size() - 1, ioTime);
                        }
            }
        } while(true);
        entrySize = entryList.size();
        exitSize = exitList.size();
        if(entrySize > 1 || entrySize == 1 && exitSize == 1) {
            loginTime = (java.util.Date)entryList.get(0);
            logoutTime = (java.util.Date)exitList.get(exitList.size() - 1);
            String effectiveHours = calculateEffectiveHours(entryList, exitList);
            System.out.println( "Card NO :  " +prevCardNo +"    LoginTime : " +loginTime +"     LogoutTime : " +logoutTime +"  Effective Hours :  " +effectiveHours );
            cardLoginDetailsMap.put(new Integer(prevCardNo), new CardLoginDetailsBean(prevCardNo, new java.util.Date(loginDate.getTime()), loginTime, logoutTime, effectiveHours));
            
            System.out.println("--------------------------------------------------------------------------------------------------------");
        }
        System.out.println( "Total Records " +total );
        con.close();
        return cardLoginDetailsMap;
    }
    
    public String calculateEffectiveHours(List entryList, List exitList) {
        long effectiveHours = 0;
        long minutes = 0;
        long hours = 0;
        java.util.Date login = null;
        java.util.Date logout = null;
        for(int i = 0; i < exitList.size(); i++) {
            login = (java.util.Date)entryList.get(i);
            logout = (java.util.Date)exitList.get(i);
            effectiveHours += logout.getTime() - login.getTime();
        }
        
        hours = effectiveHours / (1000*60*60);
        minutes = (effectiveHours / (1000*60))%60;
        return  hours +":" +minutes ;
    }
    
    public void saveAppLoginDetails(HashMap cardLoginDetailsMap, String ioDate)
    throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, password);
        PreparedStatement st = con.prepareStatement("select card_no,login_date,login_time,logout_time,effective_hours,isfromcard from app_login_details where login_date = ? FOR UPDATE",
                ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = format.parse(ioDate);
        Date sqlDate = new Date(date.getTime());
        st.setDate(1, sqlDate);
        ResultSet rs = st.executeQuery();
        int counter = 0;
        do
        {
            if(!rs.next())
                break;
            counter++;
            Integer cardNo = new Integer(rs.getInt(1));
            if(cardLoginDetailsMap.containsKey(cardNo)) {
                CardLoginDetailsBean cardBean = (CardLoginDetailsBean)cardLoginDetailsMap.get(cardNo);
                rs.updateTimestamp("login_time", new Timestamp(cardBean.getLoginTime().getTime()));
                rs.updateTimestamp("logout_time", new Timestamp(cardBean.getLogoutTime().getTime()));
                rs.updateString("effective_hours", cardBean.getEffectiveHours());
                rs.updateString("isFromCard", "Y");
                cardLoginDetailsMap.remove(cardNo);
                rs.updateRow();
            }
        } while(true);
        System.out.println( counter +" records Updated in app_login_details" );
        Set cardDetailsKeys = cardLoginDetailsMap.keySet();
        PreparedStatement st2;
        for(Iterator cardDetailsIterator = cardDetailsKeys.iterator(); cardDetailsIterator.hasNext(); st2.executeUpdate()) {
            Integer cardNum = (Integer)cardDetailsIterator.next();
            CardLoginDetailsBean cardBean = (CardLoginDetailsBean)cardLoginDetailsMap.get(cardNum);
            st2 = con.prepareStatement("insert into app_login_details (card_no, login_date, login_time, logout_time,effective_hours,isFromcard)  values (?, ?, ?, ?, ? ,?)");
            st2.setInt(1, cardBean.getCardNo());
            st2.setDate(2, new Date(cardBean.getLoginDate().getTime()));
            st2.setTimestamp(3, new Timestamp(cardBean.getLoginTime().getTime()));
            st2.setTimestamp(4, new Timestamp(cardBean.getLogoutTime().getTime()));
            st2.setString(5, cardBean.getEffectiveHours());
            st2.setString(6, "Y");
        }
        
        System.out.println( cardLoginDetailsMap.size() +" records Inserted in app_login_details" );
       
        PreparedStatement stmt = con.prepareStatement("select cardno,id from at_user where cardno not in(select card_no from app_login_details where login_date = ? ) and is_enabled = 1 and cardno  between 0001 and 9999 order by id  ");
        stmt.setDate(1, sqlDate);
        List cardNos = new ArrayList();
        Set cardNosSet = new HashSet();
        ResultSet cardnores = stmt.executeQuery();
        int count = 0;
        while(cardnores.next())
        {
             cardNos.add(count,""+cardnores.getInt(1));
             //cardNosSet.add(new Integer(rs.getInt(1)));
             count++;
        }
        
        PreparedStatement emptyRecordsStmt = null;
        //con.prepareStatement("insert into app_login_details (card_no, login_date, login_time, logout_time,effective_hours,isFromcard)  values (?, ?,' ', ' ', ' ' ,' ')");
        for(Iterator cardNosIterator = cardNos.iterator(); cardNosIterator.hasNext(); emptyRecordsStmt.executeUpdate()) 
        {
               int cardNo =Integer.parseInt((String)cardNosIterator.next());
               emptyRecordsStmt =con.prepareStatement("insert into app_login_details (card_no, login_date, login_time, logout_time,effective_hours,isFromcard)  values (?, ?, ?, ?, ? ,?)");   
               emptyRecordsStmt.setInt(1,cardNo);
               emptyRecordsStmt.setDate(2,sqlDate);
               emptyRecordsStmt.setTimestamp(3,null);
               emptyRecordsStmt.setTimestamp(4,null);
               emptyRecordsStmt.setString(5,"");
               emptyRecordsStmt.setString(6," ");
               
              
               System.out.println("Added empty Record For Card No  "+cardNo);
        }
         System.out.println((cardNos.size()+1) + "  Empty Login Records Inserted" );
         con.close();
    }
    
    private String url;
    private String dsnName;
    private String userName;
    private String password;
    private String currentDate;
    HashMap cardLoginDetailsMap;
}
