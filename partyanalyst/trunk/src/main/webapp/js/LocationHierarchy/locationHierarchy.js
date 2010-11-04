/*
 selectId = locationId like country, state, district, constituency, mandal, hamlet etc.
 task  
 use the following task names for various functionalities
 statesInCountry = to fetch all states in country
 districtsInState =  to fetch all districts in state
 constituenciesInDistrict =  to fetch all assembly constituencies in district 
 subRegionsInConstituency = to fetch all types of regions like tehsils, municipalities, corporations, grater corporations in a constituency
 hamletsOrWardsInRegion = to get hamlets if the selected area is of type rural , to get wards if the selected area type is urban, both(hamlets and wards) if the selected area is of type urban-rural
 localElectionBodiesOfDistrict = to get all local election bodies in a  district
 wardsInALocalElectionBody = to get all wards in a local election body 
 boothsInWard = to retrieve booth's part numbers in a ward for a Corporation /GHMC
 boothsInTehsilOrMunicipality = booth's part numbers in a tehsil or municipality
 
 */
function getLocationHierarchies(selectedId, task, module, elementId, addressType, areaType, constituencyId)
{		
	var jsObj=
		{				
			id: selectedId,
			task: task,
			taskType:module,
			selectElementId: elementId ,
			address: addressType,
			areaType: areaType,
			constId: constituencyId 
			
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	callAjaxForLocations(jsObj,url);
}

function callAjaxForLocations(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							clearOptionsListForSelectElmtId(jsObj.selectElementId);
							fillOptionsForSelectedElmt(jsObj.selectElementId, myResults);
							
						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

/**
 * Function to clear options for the HTML select element.
 * @method clearOptionsListForSelectElmtId
 * @param {String} elmtId .The select element id for which the options should be cleared. 
 */

function clearOptionsListForSelectElmtId(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}
function fillOptionsForSelectedElmt(elmtId, optionsList)
{	

	var elmt = document.getElementById(elmtId);
	
	if( !elmt)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
}
function getSubRegionsInDistrict(distId, module, elementId, addressType)
{	
	var scopeSelectEl = document.getElementById("scopeLevel");
	
	var scopeSelected;
	
	var areaType = '';
	if(module == 'cadreSearch')
	{
		var regionalRadioBtns = document.getElementsByName("region_type_radio");
		for (i=0; i< regionalRadioBtns.length; i++)
		{
			if(regionalRadioBtns[i].checked == true)
			{
				scopeSelected = regionalRadioBtns[i].value.toUpperCase();
			}
		}		
	} else {
		scopeSelected = scopeSelectEl.options[scopeSelectEl.selectedIndex].text;
	}
	if(scopeSelected == 'WARD' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'RURAL';
		getLocationHierarchies(distId, 'getConstNotInGivenAreaType', module, elementId, addressType, areaType,null);
		//getConstNotInGivenAreaType(distId, module, elementId, addressType, areaType);
		
	} else if(scopeSelected == 'HAMLET' ||  scopeSelected == 'MANDAL' || scopeSelected == 'VILLAGE')
	{
		areaType = 'URBAN';
		getLocationHierarchies(distId, 'getConstNotInGivenAreaType', module, elementId, addressType, areaType,null);
		//getConstNotInGivenAreaType(distId, module, elementId, addressType, areaType);
	} else if(scopeSelected == 'STATE' || scopeSelected == 'DISTRICT' || scopeSelected == 'CONSTITUENCY' || scopeSelected == 'TEHSIL' || scopeSelected == 'BOOTH')
	{
		areaType = '';
		getLocationHierarchies(distId, 'constituenciesInDistrict', module, elementId, addressType, null)
	}	
}

function getSubRegionsInConstituency(id, module, elementId, addressType)
{	
	var scopeSelectEl = document.getElementById("scopeLevel");
	var scopeSelected;
	var areaType = '';
	if(module == 'cadreSearch')
	{
		var regionalRadioBtns = document.getElementsByName("region_type_radio");
		for (i=0; i< regionalRadioBtns.length; i++)
		{
			if(regionalRadioBtns[i].checked == true)
			{
				scopeSelected = regionalRadioBtns[i].value.toUpperCase();
			}
		}		
	} else {
		scopeSelected = scopeSelectEl.options[scopeSelectEl.selectedIndex].text;
	}		
	
	if(scopeSelected == 'WARD' || scopeSelected == 'LOCAL ELECTION BODY' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'URBAN';
		getLocationHierarchies(id, 'subRegionsInConstituency', module, elementId, addressType, areaType,null);
		
		
	} else if(scopeSelected == 'HAMLET' || scopeSelected == 'VILLAGE' || scopeSelected == 'MANDAL')
	{
		areaType = 'RURAL';
		getLocationHierarchies(id, 'subRegionsInConstituency', module, elementId, addressType, areaType,null);
		
	} else if(scopeSelected == 'STATE' || scopeSelected == 'DISTRICT' || scopeSelected == 'CONSTITUENCY' || scopeSelected == 'TEHSIL' || scopeSelected == 'BOOTH')
	{
		areaType = '';
		getLocationHierarchies(id, 'subRegionsInConstituency', module, elementId, addressType, null,null)
	}
}

function getSubRegionsInTehsilOrLocalElecBody(id, module, addressType, areaType, constituencyField)
{
	var scopeSelectEl = document.getElementById("scopeLevel");
	var scopeSelected;
	var constituencyEl = document.getElementById(constituencyField);
	var constituencyElVal = constituencyEl.options[constituencyEl.selectedIndex].value;
	if(constituencyElVal == 0)
	{
		alert("Invalid Constituency Selection");
		return;
	}
	if(module == 'cadreSearch')
	{
		var regionalRadioBtns = document.getElementsByName("region_type_radio");
		for (i=0; i< regionalRadioBtns.length; i++)
		{
			if(regionalRadioBtns[i].checked == true)
			{
				scopeSelected = regionalRadioBtns[i].value.toUpperCase();
			}
		}		
	} else {
		scopeSelected = scopeSelectEl.options[scopeSelectEl.selectedIndex].text;
	}
	
	if(scopeSelected == 'BOOTH')
	{
		getLocationHierarchies(id,'boothsInTehsilOrMunicipality','cadreReg','boothField_s','cadreLevel', null,constituencyElVal);
	} else {
		getLocationHierarchies(id,'hamletsOrWardsInRegion','cadreReg','hamletField_s','cadreLevel', null,null);
	}
	
}

function getConstNotInGivenAreaType(distId, module, elementId, addressType, areaType)
{
	var jsObj=
	{				
		id: distId,
		task: 'getConstNotInGivenAreaType',
		taskType:module,
		selectElementId: elementId ,
		address: addressType,
		areaType: areaType
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
callAjaxForLocations(jsObj,url);
}
