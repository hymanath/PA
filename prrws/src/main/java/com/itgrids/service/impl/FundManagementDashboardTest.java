package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IFundSanctionLocationDAO;
import com.itgrids.service.IFundManagementDashboardTest;
import com.itgrids.utils.FundManagementThreadService;
@Service
@Transactional
public class FundManagementDashboardTest implements IFundManagementDashboardTest {
	@Autowired
	private IFundSanctionLocationDAO fundSanctionLocationDAO;
	public void getResult(){
		try{
			List<Long> resultList = new ArrayList<Long>(0);
			List<Long> dataList = new ArrayList<Long>(){{add(1L);add(2L);add(3L);add(4L);add(5L);}};
			ExecutorService executor = Executors.newFixedThreadPool(30);
			for(Long param : dataList){
				Runnable worker = new FundManagementThreadService(fundSanctionLocationDAO,param,resultList);
				System.out.println(1);
				executor.execute(worker);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
					
			}
			System.out.println(resultList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
