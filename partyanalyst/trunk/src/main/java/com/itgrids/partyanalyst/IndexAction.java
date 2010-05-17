/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itgrids.partyanalyst;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

/**
 * 
 */
@Conversion()
public class IndexAction extends ActionSupport {
    
    private Date now = new Date(System.currentTimeMillis());
    private List<SelectOptionVO> mlaConstituenciesList;
    private List<SelectOptionVO> mpConstituenciesList;
    private IStaticDataService staticDataService;
    
    
    public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getMlaConstituenciesList() {
		return mlaConstituenciesList;
	}

	public void setMlaConstituenciesList(List<SelectOptionVO> mlaConstituenciesList) {
		this.mlaConstituenciesList = mlaConstituenciesList;
	}

	public List<SelectOptionVO> getMpConstituenciesList() {
		return mpConstituenciesList;
	}

	public void setMpConstituenciesList(List<SelectOptionVO> mpConstituenciesList) {
		this.mpConstituenciesList = mpConstituenciesList;
	}

	@TypeConversion(converter = "com.itgrids.partyanalyst.DateConverter")
    public Date getDateNow() { return now; }
    
    public String execute() throws Exception
    {
        now = new Date(System.currentTimeMillis());
       
        mlaConstituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(new Long(2), new Long(1));
        
        mpConstituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(new Long(1), new Long(1));
        
        return SUCCESS;
    }
}
