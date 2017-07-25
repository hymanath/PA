package com.itgrids.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.itgrids.dto.InputVO;
import com.itgrids.utils.NREGSCumulativeThread;
 

public class NREGSCumulativeThreadTest { 
	private List reponseList =null;
	private InputVO inputVO=null;
	private List  callWebService(){
		inputVO=new InputVO();
		inputVO.setYear("2017");
		inputVO.setFromDate("2017-04-01");
		inputVO.setToDate("2017-07-30");
		inputVO.setLocationType("state");
		inputVO.setLocationId(-1L);
		inputVO.setSublocaType( "district");
		reponseList=new ArrayList();
		ExecutorService executor = Executors.newFixedThreadPool(20);
		String url= "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService_new/IHHLData_new";
		for (int i = 0; i < 10; i++) {
			Runnable worker = new NREGSCumulativeThread(url,reponseList,inputVO);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			
		}
		System.out.println("Finished all threads"+reponseList);

		return reponseList;
	}

	public static void main(String[] args) {
		  List reponses =null;new NREGSCumulativeThreadTest().callWebService();
	}

}
