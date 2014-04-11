package com.itgrids.eliteclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.eliteclub.dao.ILocationScopesDAO;
import com.itgrids.eliteclub.model.LocationScopes;
import com.itgrids.eliteclub.service.WebserviceHandlerSevice;

@Service("webserviceHandlerSeviceImpl")
public class WebserviceHandlerSeviceImpl implements   WebserviceHandlerSevice
{

	@Autowired
	private ILocationScopesDAO  locationScopesDAO;
	
	public void loadObject(long id) {
		LocationScopes l=locationScopesDAO.get(id);
		System.out.println(l.getLocationScope());
	}

}
