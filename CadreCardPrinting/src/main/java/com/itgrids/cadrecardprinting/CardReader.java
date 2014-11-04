package com.itgrids.cadrecardprinting;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.PrintException;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


 
public class CardReader {
 
final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
 
public static String bytesToHex(byte[] bytes) {
char[] hexChars = new char[bytes.length * 2];
int v;
for ( int j = 0; j < bytes.length; j++ ) {
v = bytes[j] & 0xFF;
hexChars[j * 2] = hexArray[v >>> 4];
hexChars[j * 2 + 1] = hexArray[v & 0x0F];
}
return new String(hexChars);
}
 
/*public static void main(String[] args) throws Exception 
{
	//String name = getUniCode();
}*/


public static String callWebService()
{
	JerseyClientGet client = new JerseyClientGet();
	 String[] args = null;
	 String  obj = client.getCadreDetailsForPrinting("AP1420608154");
	 System.out.println(obj);
	 return null;
}


public static String getUniCode()
{

	String hexNumber = null;
	try {
		
		TerminalFactory factory = TerminalFactory.getInstance("PC/SC", null);
		List<CardTerminal> terminals = factory.terminals().list();
		System.out.println("Terminals: " + terminals);
		if (terminals.isEmpty())
		{
			throw new Exception("No card terminals available");
		}
		 
		CardTerminal terminal = terminals.get(0);

			terminal.waitForCardPresent( 0 );
			
				Card card = terminal.connect("*");
				CardChannel channel = card.getBasicChannel();
				 
				CommandAPDU command = new CommandAPDU(new byte[]{(byte)0xFF,(byte)0xCA,(byte)0x00,(byte)0x00,(byte)0x04});
				ResponseAPDU response = channel.transmit(command);
				 
				byte[] byteArray = response.getBytes();
				 hexNumber = bytesToHex( byteArray );

	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return hexNumber;
}
}
