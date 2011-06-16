package cardreader;

import cardinfo.CardDB;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{

    public Main()
    {
    }

    public static void main(String args[])
    {
        OutputStream fos = null;
        Date date = new Date();
        System.out.println( "[DEBUG :] date   "+date );
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = dateFormat.format(date);
        System.out.println( "[DEBUG :] currentDate   "+currentDate );
        try
        {
            System.out.println( "[DEBUG :] currentDate@@@@@   "+currentDate );
            String fileName =  "cardreader_"+currentDate+".log" ;
            File file = new File("./logs");
              if(!file.exists())
            file.mkdir();
            fos = new BufferedOutputStream(new FileOutputStream(file.getPath()+"/"+fileName ));
            System.setOut(new PrintStream(fos));
        }
        catch(FileNotFoundException ex)
        {
            System.out.println( "[ERROR]: Exception : "+ex );
            ex.printStackTrace();
        }
        Main main = new Main();
        main.configureDBParameters();
        main.startReading(currentDate);
        try
        {
            fos.close();
        }
        catch(IOException ex)
        {
            System.out.println( "[ERROR]: Exception : "+ex );
            ex.printStackTrace();
        }
    }

    private void startReading(String ioDate) 
    {
        CardDB dbUser = new CardDB(url, dsn_name, userName, password);
//     try{
//        
//        //CardDB dbUser = new CardDB(url, dsn_name, userName, password);
//        if(fromDate == null && toDate == null)
//        {
//            cardLoginDetailsMap = dbUser.getCardDbDetails(ioDate);
//            System.out.println( "[DEBUG :] cardLoginDetailsMap"+cardLoginDetailsMap );
//            dbUser.saveAppLoginDetails(cardLoginDetailsMap, ioDate);
//        } 
//         else
//        {
//            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//            Date from_Date = format.parse(fromDate);
//            Date to_Date = format.parse(toDate);
//            to_Date.setTime(to_Date.getTime() );
//            System.out.println("befor for");
//            for(; to_Date.after(from_Date); from_Date.setTime(from_Date.getTime()))
//            {
//                System.out.println("in for");
//                cardLoginDetailsMap = dbUser.getCardDbDetails(format.format(from_Date));
//                System.out.println( "[DEBUG :] cardLoginDetailsMap"+cardLoginDetailsMap );
//                dbUser.saveAppLoginDetails(cardLoginDetailsMap, format.format(from_Date));
//               
//           }
//
//        }
//       }
//           catch(Exception e)
//           {
//                   e.printStackTrace();
//           }
        try {
            if( fromDate == null && toDate == null )
            {
        
                 cardLoginDetailsMap = dbUser.getCardDbDetails(ioDate);
                  System.out.println( "[DEBUG :] cardLoginDetailsMap"+cardLoginDetailsMap );
                 dbUser.saveAppLoginDetails( cardLoginDetailsMap,ioDate );
            } else 
            {
        
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date from_Date = format.parse( fromDate );
                Date to_Date = format.parse( toDate );
                to_Date.setTime( to_Date.getTime() + 24*60*60*1000 );
                while( to_Date.after( from_Date ) ) 
                {
        
                    cardLoginDetailsMap = dbUser.getCardDbDetails(format.format( from_Date ));
                    System.out.println( "[DEBUG :] cardLoginDetailsMap"+cardLoginDetailsMap );
                    dbUser.saveAppLoginDetails( cardLoginDetailsMap,format.format( from_Date ) );
                    from_Date.setTime( from_Date.getTime() + 24*60*60*1000 );
                }
            }
        
        } catch (Exception ex) 
        {
           System.out.println( "[ERROR]: Exception : "+ex);
           ex.printStackTrace();
        }
        finally
        {

            dbUser = null;
            ioDate = null;
        }

      }

    public void configureDBParameters()
    {
        props = new Properties();
        try
        {
            props.load(new FileInputStream("properties/Card.properties"));
        }
        catch(IOException e)
        {
            System.out.println( "[ERROR]: Exception : "+e );
            e.printStackTrace();
        }
        dsn_name = props.getProperty("card.dsn_name");
        System.out.println( "[DEBUG :] dsn_name:  "+dsn_name );
        url = props.getProperty("mysql.url");
        System.out.println( "[DEBUG :] url:  "+url );
        userName = props.getProperty("mysql.user");
        System.out.println( "[DEBUG :] userName :  "+userName );
        password = props.getProperty("mysql.password");
        System.out.println( "[DEBUG :]password:  "+password );
        fromDate = props.getProperty("card.from_date");
        System.out.println( "[DEBUG :] fromDate:  "+fromDate );
        toDate = props.getProperty("card.to_date");
        System.out.println( "[DEBUG :] toDate:  "+toDate );
    }

    public String url;
    public Properties props;
    public String dsn_name;
    public String userName;
    public String password;
    public String fromDate;
    public String toDate;
    HashMap cardLoginDetailsMap;
}

