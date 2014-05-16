package com.itgrids.partyanalyst.utils;


import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.service.IConstituencyWiseElectionResultsService;


public class MultiThreadRunner  {
	@Autowired
   private IConstituencyWiseElectionResultsService constituencyWiseElectionResultsService;
  
   public static Timer timer  = new Timer();
   public static Timer timer1  = new Timer();
   public  void  startTask (int seconds,int period,String res) {
      timer.scheduleAtFixedRate(new IntiateRemoteCall(res), seconds*1000,period*1000);
    }
   public  void  startTaskForParliament (int seconds,int period,String res) {
        timer1.scheduleAtFixedRate(new IntiateRemoteCall(res), seconds*1000,period*1000);
      }

    class IntiateRemoteCall extends TimerTask {
    private String resultFor;    	
	public IntiateRemoteCall() {}
	public IntiateRemoteCall(String result) 
	 {
		 this.resultFor=result;
	 }
        @Override
        public void run() {
            System.out.println("call For Every 10 minutes"+resultFor);
            if(!resultFor.equalsIgnoreCase("Parliament"))
            constituencyWiseElectionResultsService.bulkDataInsertUsingRemotingWebserviceCall(resultFor);
            else {
            	for(long i=1;i<=35;i++)
            	constituencyWiseElectionResultsService.bulkDataInsertUsingRemotingWebserviceCallParliament(resultFor,i );
            }
        }
    }
    
    
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Java timer is about to start");
       
        System.out.println("Remindertask is scheduled with Java timer.");
        MultiThreadRunner runner =new MultiThreadRunner();
        runner.startTask(2, 5,"parliament");
        Thread.sleep(10000);
        //timer.cancel();
        System.out.println("now");
        //timer=new Timer();
        
        runner.startTaskForParliament(2, 5,"hello");
        Thread.sleep(10000);
        timer.cancel();
        //timer.purge();
    }
    
	
}
