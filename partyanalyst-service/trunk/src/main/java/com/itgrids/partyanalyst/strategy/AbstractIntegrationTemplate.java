package com.itgrids.partyanalyst.strategy;

import com.itgrids.partyanalyst.dto.SIInput;
import com.itgrids.partyanalyst.dto.SIResult;

@Deprecated
public abstract class AbstractIntegrationTemplate implements
		IIntegrationTemplate<SIInput, SIResult> {
	
	//Attach services / dao here
	
	public SIResult execute(SIInput sIInput){
		return null;
	}

	//Do common tasks
	
	//Have abstact methods for concrete implementation.
}
