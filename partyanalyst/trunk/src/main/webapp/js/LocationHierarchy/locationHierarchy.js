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
 */
function getLocationHierarchies(selectedId, task, module, elementId, addressType, areaType)
{		
	var jsObj=
		{				
			id: selectedId,
			task: task,
			taskType:module,
			selectElementId: elementId ,
			address: addressType,
			areaType: areaType
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
	
	var scopeSelected = scopeSelectEl.options[scopeSelectEl.selectedIndex].text;
	
	var areaType = '';
	if(scopeSelected == 'WARD' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'RURAL';
		getLocationHierarchies(distId, 'getConstNotInGivenAreaType', module, elementId, addressType, areaType);
		//getConstNotInGivenAreaType(distId, module, elementId, addressType, areaType);
		
	} else if(scopeSelected == 'HAMLET' ||  scopeSelected == 'MANDAL')
	{
		areaType = 'URBAN';
		getLocationHierarchies(distId, 'getConstNotInGivenAreaType', module, elementId, addressType, areaType);
		//getConstNotInGivenAreaType(distId, module, elementId, addressType, areaType);
	} else if(scopeSelected == 'STATE' || scopeSelected == 'DISTRICT' || scopeSelected == 'CONSTITUENCY' || scopeSelected == 'TEHSIL')
	{
		areaType = '';
		getLocationHierarchies(distId, 'constituenciesInDistrict', module, elementId, addressType, null)
	}	
}

function getSubRegionsInConstituency(id, module, elementId, addressType)
{
	var scopeSelectEl = document.getElementById("scopeLevel");
	var scopeSelected = scopeSelectEl.options[scopeSelectEl.selectedIndex].text;
	var areaType = '';
	if(scopeSelected == 'WARD' || scopeSelected == 'LOCAL ELECTION BODY' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'URBAN';
		getLocationHierarchies(id, 'subRegionsInConstituency', module, elementId, addressType, areaType);
		
		
	} else if(scopeSelected == 'HAMLET' || scopeSelected == 'VILLAGE' || scopeSelected == 'MANDAL')
	{
		areaType = 'RURAL';
		getLocationHierarchies(id, 'subRegionsInConstituency', module, elementId, addressType, areaType);
		
	} else if(scopeSelected == 'STATE' || scopeSelected == 'DISTRICT' || scopeSelected == 'CONSTITUENCY' || scopeSelected == 'TEHSIL')
	{
		areaType = '';
		getLocationHierarchies(id, 'subRegionsInConstituency', module, elementId, addressType, null)
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
