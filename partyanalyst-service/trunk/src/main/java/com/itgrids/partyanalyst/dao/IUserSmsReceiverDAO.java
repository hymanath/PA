package com.itgrids.partyanalyst.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserSmsReceiver;

public interface IUserSmsReceiverDAO extends GenericDao<UserSmsReceiver, Serializable>{
	
	public List<Object[]> getSmsDetails(Long userId,Long typeId);
	public Integer deleteSmsDetails(List<Long> smsResponseid);
	public Long getReceiverIdById(Long userSmsReceiverId);
	public List<Object[]> getDatewiseSortingDetails(Long userId,Long typeId,Date date);
	public List<Object[]> getSmsDetailsBySearch(Long userId,Long typeId,String namesearchText,String mobileSearch);
}
