package com.itgrids.voterdata.service;

import com.onbarcode.barcode.Code128;



public class Kamal2 {

	public static void main(String[] args) throws Exception
	{
		Code128 barcode = new Code128(); 
		barcode.setData("12345678-53618263"); 
		barcode.setX(2); 
		barcode.drawBarcode("D://barcode-code128.jpg"); 
	}
}