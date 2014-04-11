package com.itgrids.eliteclub.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.itgrids.eliteclub.dao.ILocationScopesDAO;
import com.itgrids.eliteclub.model.LocationScopes;



@Repository
@Component("locationScopesDAO")
public class LocationScopesDAO  extends AbstractDaoImpl<LocationScopes, Long>  implements ILocationScopesDAO{

public LocationScopesDAO() {
		super(LocationScopes.class);
		
			}






}
