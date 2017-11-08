package com.itgrids.utils;

import java.util.List;

import com.itgrids.dao.IFundSanctionLocationDAO;

public class FundManagementThreadService implements Runnable {

	private IFundSanctionLocationDAO fundSanctionLocationDAO = null;
	private Long count = null;
	private List<Long> countList = null;
	
	public FundManagementThreadService(IFundSanctionLocationDAO fundSanctionLocationDAO,Long count,List<Long> countList){
		this.fundSanctionLocationDAO = fundSanctionLocationDAO;
		this.count = count;
		this.countList = countList;
	}
	
	@Override
	public void run() {
		executeService();
	}
	
	public void executeService(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countList.add(count);
	}

}
