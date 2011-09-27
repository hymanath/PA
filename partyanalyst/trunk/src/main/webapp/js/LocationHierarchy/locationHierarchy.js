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
	if(selectedId ==0){
		alert("Please Select Valid Location");
	 return;
	 }
	showBusyImgWithId('ajaxImgId');
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
							if(jsObj.task == "getBoothsCompleteDetails")	
							{
								buildBoothsTable(myResults);
								//buildBoothCompleteDetailsPanel(myResults);
							} else {
								clearOptionsListForSelectElmtId(jsObj.selectElementId);
								fillOptionsForSelectedElmt(jsObj.selectElementId, myResults);
								hideBusyImgWithId('ajaxImgId');
							}
						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
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
	if(distId == 0)
	{
		alert("Select Valid District");
		return;
	}
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
	if(id == 0)
	{
		alert("Select Valid Contituency");
		return;
	}
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

function getSubRegionsInTehsilOrLocalElecBody(id,name,  module, addressType, areaType, constituencyField, rowId1, rowId2)
{
	if(id == 0)
	{
		alert("Select valid Mandal/Municipality/Corp/GMC");
		return;
	}	
	
	var scopeSelectEl = document.getElementById("scopeLevel");
	var scopeSelected;
	var constituencyEl = document.getElementById(constituencyField);
	var row5El = document.getElementById(rowId2);
	var row6El = document.getElementById(rowId1);
	var constituencyElVal = constituencyEl.options[constituencyEl.selectedIndex].value;
	if(constituencyElVal == 0)
	{
		alert("Invalid Constituency Selection");
		return;
	}
	var flag = name.search("Greater Municipal Corp");
	
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
	
	
	//getLocationHierarchies(id,task,taskType,boothField,address, null,constiId);

	if(scopeSelected == 'BOOTH')
	{
		if(flag == '-1')
		{
			if(row6El.style.display == 'none')
				row6El.style.display = '';
			getLocationHierarchies(id,'boothsInTehsilOrMunicipality',module,'boothField_s',addressType, null,constituencyElVal);
		} else {
			if(row5El.style.display == 'none')
				row5El.style.display = '';
			if(row6El.style.display == 'none')
				row6El.style.display = '';
			clearOptionsListForSelectElmtId('boothField_s');
			getLocationHierarchies(id,'hamletsOrWardsInRegion',module,'hamletField_s',addressType, null,null);
		}	
	}
	
	else if(scopeSelected == 'WARD' || scopeSelected == 'VILLAGE' ) {
		getLocationHierarchies(id,'hamletsOrWardsInRegion',module,'hamletField_s',addressType, null,null);
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

function getBooths(address, constituencyField, boothField, id,name, taskType,task )
{
	
	if(id == 0){
		clearOptionsListForSelectElmtId(boothField);
		return;
	}
	var constituencyEl = document.getElementById(constituencyField);
	var constiId = constituencyEl.options[constituencyEl.selectedIndex].value;
	if(constiId == 0)
	{
		alert("Invalid Constituency Selection");
		return;
	}
	var flag = name.search("Greater Municipal Corp");
	
	if(flag == '-1')
	{
		getLocationHierarchies(id,task,taskType,boothField,address, null,constiId);
	} else 
	{	
		clearOptionsListForSelectElmtId(boothField);
		return;
	}	
}

function getBoothsInWard(address, constituencyField, boothField, id, module, municipalField )
{
	var constituencyEl = document.getElementById(constituencyField);
	var constiId = constituencyEl.options[constituencyEl.selectedIndex].value;
	var municipalEl = document.getElementById(municipalField);
	var municipalName = municipalEl.options[municipalEl.selectedIndex].text;
	
	if(constiId == 0)
	{
		alert("Invalid Constituency Selection");
		return;
	}
	var flag = municipalName.search("Greater Municipal Corp");
	
	if(flag == '-1')
	{
		return;
	} else {
		getLocationHierarchies(id,'boothsInWard',module,boothField,address, null,constiId);
	}	
}
/*
 * this method retrieves the booths complete info by providing the booth ids. 
 */
function showBoothsCompleteDetails(boothSelectEl, mdlSelectEl)
{  
	var boothsSelectEl = document.getElementById(boothSelectEl);
	var boothsSelectElOptions = boothsSelectEl.options; 
	var boothIdsStr = '';	
	var mdlsSelectEl = document.getElementById(mdlSelectEl);
	var mdlsSelectElSelected;
	if(mdlsSelectEl.options.length == 0 || boothsSelectElOptions.length == 0)
		return;
	mdlsSelectElSelected = mdlsSelectEl.options[mdlsSelectEl.selectedIndex].value;
	
	var areaType; 
	if(mdlsSelectElSelected != 0 && mdlsSelectElSelected.substring(0,1) == '1')
	{
		areaType = '1';// for URBAN_TYPE
	} else if(mdlsSelectElSelected != 0 && mdlsSelectElSelected.substring(0,1) == '2')
	{
		areaType = '2';//// for RURAL_TYPE
	}  
	if(boothsSelectElOptions.length > 0)
	{
		for(var i = 0; i < boothsSelectElOptions.length; i++)
		{
			if(boothsSelectElOptions[i].value != 0)
			{		
				boothIdsStr += boothsSelectElOptions[i].value;
				boothIdsStr += ',';
			}	
		}
		
		boothIdsStr = boothIdsStr.substring(0,boothIdsStr.length-1);
	}
	
	if(boothIdsStr.length > 0 && boothIdsStr != '')
	{
		var jsObj=
		{				
			boothIds: boothIdsStr,
			task: 'getBoothsCompleteDetails',
			areaType: areaType 
			
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getBoothsCompleteDetailsAction.action?"+rparam;						
	callAjaxForLocations(jsObj,url);
	}
	
}
	
	function buildBoothsTable(results)
	{
		var localArr = new Array();
		
		for(var i in myResults)
		{
			var obj = {				
						mandal : results[i].mandalName,
						partNo: results[i].partNo,
						location: results[i].location,
						villagesCovered: results[i].villagesCovered						
					  }
			localArr.push(obj);
		}

		var myColumnDefs = [
		
		{key:"partNo",label:"Booth Part No",sortable:true, resizeable:true},	
		{key:"location",label:"Booth Location", resizeable:true},
		{key:"villagesCovered",label:"Villages Covered", resizeable:true},
		{key:"mandal",label:"Mandal/Municipal/Corp/GMC",sortable:true, resizeable:true},
		];
		
		var configs = {
							paginator: new YAHOO.widget.Paginator({ 
							rowsPerPage    : 10			        
							})
					   };
		var myDataSource = new YAHOO.util.DataSource(localArr);
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		myDataSource.responseSchema = {
			fields: ["mandal","partNo","location", "villagesCovered"]
		};					

		buildBoothCompleteDetailsPanel(myColumnDefs,myDataSource,configs);

		
	}
	
	
	 function buildBoothCompleteDetailsPanel(myColumnDefs,myDataSource,configs)
	 {
		  	var contentStr = '';
		 	contentStr +='<div class="yui-skin-sam"><div id="boothsDetailsDiv"></div></div>';
		 	 var myPanel = new YAHOO.widget.Dialog("boothDetailsPopup", {             
		    
		 		 fixedcenter : true, 
		 		 visible : true,  
		 		 constraintoviewport : true, 
		 		 iframe :true,
		 		 modal :true,
		 		 hideaftersubmit:true,
		 		 close:true
		 	   });
		 	   myPanel.setHeader("Booth Complete Details");
		 	   myPanel.setBody(contentStr);
		 	   myPanel.render();

		 	   var myDataTable = new YAHOO.widget.DataTable("boothsDetailsDiv",
		 				myColumnDefs, myDataSource,configs);
		 
	 }

