// JavaScript Document

var REPORTLEVEL = '';
var REPORTLOCATIONVALUE = '';
var CADRETYPE = 'active';
var SEARCHCRITERIA = '';
var SEARCHCRITERIAVALUE = 'Select';

var searchCriteriaArr = ["committe","category","age","occupation"];
var committeArr = ["Mahila Morcha","SC Morcha","ST Morcha","Kisan Morcha","Minority Morcha"];
var categoryArr = ["SC","ST","BC","Minority","General","Female"];
var ageArr = ["19-25","26-30","31-35","36-50","51-65",">66"];
var occupationArr = [];

function getCriteriaValue(criteriaValue,elmtId)
{
	CADRETYPE = criteriaValue;
	var labelSpanElmt = document.getElementById(elmtId+"_label");
	var dataSpanElmt = document.getElementById(elmtId+"_data");

	if(!labelSpanElmt || !dataSpanElmt)
		return;
	
	var labelStr = '',dataStr = '';
	labelSpanElmt.innerHTML = labelStr;
	dataSpanElmt.innerHTML = dataStr;
	

	if(CADRETYPE == "active")
	{
		labelStr += ' <font color="#FF0000"> * </font> Search Criteria';
		labelSpanElmt.innerHTML = labelStr;
		
		dataStr += '<div>';
		dataStr += '	<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="all"/>All</span>';		
		dataStr += '</div>';
		dataStr += '<div>';
		dataStr += '	<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="committe"/>Committe Wise</span>';
		dataStr += '	<span id="committe_Select"></span>';
		dataStr += '</div>';
		dataStr += '<div>';
		dataStr += '	<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="category"/>Category Wise</span>';
		dataStr += '	<span id="category_Select"></span>';
		dataStr += '</div>';
		dataStr += '<div>';
		dataStr += '	<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="age"/>Age Wise</span>';
		dataStr += '	<span id="age_Select"></span>';
		dataStr += '</div>';
		dataStr += '<div>';
		dataStr += '	<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="occupation"/>Occupation Wise</span>';
		dataStr += '	<span id="occupation_Select"></span>';
		dataStr += '</div>';		
		
		dataSpanElmt.innerHTML = dataStr;
	}
	else if(CADRETYPE == "normal")
	{	
		dataSpanElmt.innerHTML = '';
	}

}

function getSearchOptions(value)
{
	if(value == "all")
	{
		SEARCHCRITERIA = "all";
	}

	var categorySelectElmt = document.getElementById(value+"_Select");
	SEARCHCRITERIA = value;

	if(!categorySelectElmt)
		return;
	for(var i in searchCriteriaArr)
	{
		var searchCriteriaElmt = document.getElementById(searchCriteriaArr[i]+"_Select");
		searchCriteriaElmt.innerHTML = '';
	}
	
	var str = '';
	var searchCriteriaArrValue = '';
	if(value == "committe")
	{
		searchCriteriaArrValue = committeArr;
	}
	else if(value == "category")
	{
		searchCriteriaArrValue = categoryArr;
	}
	else if(value == "age")
	{
		searchCriteriaArrValue = ageArr;
	}
	else if(value == "occupation")
	{
		searchCriteriaArrValue = occupationArr;
	}
	
	if(searchCriteriaArrValue.length>0)
	{
		str += '<select onchange="javascript:{SEARCHCRITERIAVALUE = this.options[this.selectedIndex].text;}">';
		str += '<option value="0">Select</option>';
		for(var i in searchCriteriaArrValue)
		{
			str += '<option>'+searchCriteriaArrValue[i]+'</option>';
		}		
		str += '</select>';

		categorySelectElmt.innerHTML = str;
	}	
	

	

}

function showSocialStatus(elmt)
{
	var checkElmts = document.getElementsByName("socialStatus");
	var status;

	if(elmt.checked == true)
		status = false;
	else if(elmt.checked == false)
		status = true;

	if(checkElmts.length == 0)
		return;
	
	for(var i in checkElmts)
	{
		checkElmts[i].disabled = status;
		var elmt = document.getElementById("socialStatus_"+checkElmts[i].value);

		if(!elmt)
			continue;

		elmt.disabled=status;
	}

	
}

function sendSMSWithoutSearch()
{

}

function getRegionsForAccessLevel(accessValue,regionElmtId)
{
	REPORTLEVEL = accessValue;
	
	var labelSpanElmt = document.getElementById(regionElmtId+"_label");
	var dataSpanElmt = document.getElementById(regionElmtId+"_data");
	var buttonSpanElmt = document.getElementById(regionElmtId+"_button");
	
	if(!labelSpanElmt || !dataSpanElmt || !buttonSpanElmt)
		return;
	
	var labelStr = '',dataStr = '',buttonStr = '';

	labelSpanElmt.innerHTML = labelStr;
	dataSpanElmt.innerHTML = dataStr;
	buttonSpanElmt.innerHTML = buttonStr;
	
	labelStr += ' <font color="#FF0000"> * </font> Select Location';
	buttonStr += '<div>';
	buttonStr += '<span><input type="button" onclick="getCadresResults()" value="Search"/></span>';
	buttonStr += '<span><input type="button" onclick="sendSMSWithoutSearch()" value="Send SMS"/></span>';
	buttonStr += '</div>';

	
	labelSpanElmt.innerHTML = labelStr;
	buttonSpanElmt.innerHTML = buttonStr;
	
	if(REPORTLEVEL == "Country")
	{		
		dataStr += '<select id="countrySelectBox">';
		dataStr += '<option id="1"> India </option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;		
	}
	else if(REPORTLEVEL == "State")
	{
		dataStr += '<select id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,\'stateSelectBox\')">';
		dataStr += '<option value="0"> Select Country</option>';
		dataStr += '<option value="1"> India </option>';
		dataStr += '</select>';

		dataStr += '<select id="stateSelectBox">';
		dataStr += '<option value="0"> Select State</option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;
		
	}
	else if(REPORTLEVEL == "District")
	{
		dataStr += '<select id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,\'stateSelectBox\')">';
		dataStr += '<option value="0"> Select Country</option>';
		dataStr += '<option value="1"> India </option>';
		dataStr += '</select>';

		dataStr += '<select id="stateSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'districtSelectBox\')">';
		dataStr += '<option value="0"> Select State</option>';
		dataStr += '</select>';

		dataStr += '<select id="districtSelectBox">';
		dataStr += '<option value="0"> Select District</option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;
	}
	else if(REPORTLEVEL == "Constituency")
	{
		dataStr += '<select id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,\'stateSelectBox\')">';
		dataStr += '<option value="0"> Select Country</option>';
		dataStr += '<option value="1"> India </option>';
		dataStr += '</select>';

		dataStr += '<select id="stateSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'districtSelectBox\')">';
		dataStr += '<option value="0"> Select State</option>';
		dataStr += '</select>';

		dataStr += '<select id="districtSelectBox" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,\'constituencySelectBox\')">';
		dataStr += '<option value="0"> Select District</option>';
		dataStr += '</select>';

		dataStr += '<select id="constituencySelectBox">';
		dataStr += '<option value="0"> Select Constituency</option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;
	}
	else if(REPORTLEVEL == "Mandal")
	{
		dataStr += '<select id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,\'stateSelectBox\')">';
		dataStr += '<option value="0"> Select Country</option>';
		dataStr += '<option value="1"> India </option>';
		dataStr += '</select>';

		dataStr += '<select id="stateSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'districtSelectBox\')">';
		dataStr += '<option value="0"> Select State</option>';
		dataStr += '</select>';

		dataStr += '<select id="districtSelectBox" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,\'constituencySelectBox\')">';
		dataStr += '<option value="0"> Select District</option>';
		dataStr += '</select>';

		dataStr += '<select id="constituencySelectBox" onchange="getMandalsComboBoxForAConstituency(this.options[this.selectedIndex].value,\'mandalSelectBox\')">';
		dataStr += '<option value="0"> Select Constituency</option>';
		dataStr += '</select>';
		
		dataStr += '<select id="mandalSelectBox">';
		dataStr += '<option value="0"> Select Mandal</option>';
		dataStr += '</select>';		
		
		dataSpanElmt.innerHTML = dataStr;
	}
	else if(REPORTLEVEL == "Village")
	{
		dataStr += '<select id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,\'stateSelectBox\')">';
		dataStr += '<option value="0"> Select Country </option>';
		dataStr += '<option value="1"> India </option>';
		dataStr += '</select>';

		dataStr += '<select id="stateSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'districtSelectBox\')">';
		dataStr += '<option value="0"> Select State</option>';
		dataStr += '</select>';

		dataStr += '<select id="districtSelectBox" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,\'constituencySelectBox\')">';
		dataStr += '<option value="0"> Select District</option>';
		dataStr += '</select>';

		dataStr += '<select id="constituencySelectBox" onchange="getMandalsComboBoxForAConstituency(this.options[this.selectedIndex].value,\'mandalSelectBox\')">';
		dataStr += '<option value="0"> Select Constituency</option>';
		dataStr += '</select>';
		
		dataStr += '<select id="mandalSelectBox" onchange="getVillagesComboBoxForAMandal(this.options[this.selectedIndex].value,\'villageSelectBox\')">';
		dataStr += '<option value="0"> Select Mandal</option>';
		dataStr += '</select>';

		dataStr += '<select id="villageSelectBox">';
		dataStr += '<option value="0"> Select Village</option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;
	}
}

function getCadresResults()
{	 
	var elmt = document.getElementById("errorMsgDiv");
	var locationValue = '';
	if(!elmt)
		return;
	
	var countrySelectElmt = document.getElementById("countrySelectBox");
	var stateSelectElmt = document.getElementById("stateSelectBox");
	var districtSelectElmt = document.getElementById("districtSelectBox");
	var constituencySelectElmt = document.getElementById("constituencySelectBox");
	var mandalSelectElmt = document.getElementById("mandalSelectBox");
	var villageSelectElmt = document.getElementById("villageSelectBox");
		
	
	if(REPORTLEVEL == '' || SEARCHCRITERIA == '')
	{
		elmt.innerHTML = 'Fields marked with * are compulsory';
		return;
		
	}
	
	if(SEARCHCRITERIAVALUE == 'Select')
	{
		elmt.innerHTML = 'Select search criteria value';
		return;
	}

	if(REPORTLEVEL == "Country")
	{
		if(!countrySelectElmt || countrySelectElmt.options[countrySelectElmt.selectedIndex].value == "0")
		{
			elmt.innerHTML = 'Select Country Location';
			return;
		}
	}	
	else if(REPORTLEVEL == "State")
	{
		if(!stateSelectElmt || stateSelectElmt.options[stateSelectElmt.selectedIndex].value == "0")
		{
			elmt.innerHTML = 'Select State Location';
			return;
		}
		else
		{
			elmt.innerHTML = '';
			locationValue = stateSelectElmt.options[stateSelectElmt.selectedIndex].value;
		}
	}	
	else if(REPORTLEVEL == "District")
	{
		if(!districtSelectElmt || districtSelectElmt.options[districtSelectElmt.selectedIndex].value == "0")
		{
			elmt.innerHTML = 'Select District Location';
			return;
		}
		else
		{
			elmt.innerHTML = '';
			locationValue = districtSelectElmt.options[districtSelectElmt.selectedIndex].value;
		}
	}	
	else if(REPORTLEVEL == "Constituency")
	{
		if(!constituencySelectElmt || constituencySelectElmt.options[constituencySelectElmt.selectedIndex].value == "0")
		{
			elmt.innerHTML = 'Select Constituency Location';
			return;
		}
		else
		{
			elmt.innerHTML = '';
			locationValue = constituencySelectElmt.options[constituencySelectElmt.selectedIndex].value;
		}
	}	
	else if(REPORTLEVEL == "Mandal")
	{
		if(!mandalSelectElmt || mandalSelectElmt.options[mandalSelectElmt.selectedIndex].value == "0")
		{
			elmt.innerHTML = 'Select Mandal Location';
			return;
		}
		else
		{
			elmt.innerHTML = '';
			locationValue = mandalSelectElmt.options[mandalSelectElmt.selectedIndex].value;
		}
	}	
	else if(REPORTLEVEL == "Village")
	{
		if(!villageSelectElmt || villageSelectElmt.options[villageSelectElmt.selectedIndex].value == "0")
		{
			elmt.innerHTML = 'Select Village Location';
			return;
		}
		else
		{
			elmt.innerHTML = '';
			locationValue = villageSelectElmt.options[villageSelectElmt.selectedIndex].value;
		}
	}
	
	
	var jsObj=
		{				
				reportLevel:REPORTLEVEL,
				locationValue:locationValue,
				cadreType:CADRETYPE,
				searchCriteria:SEARCHCRITERIA,
				searchCriteriaValue:SEARCHCRITERIAVALUE,
				task:"cadreSearch"		
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCadresDetailsAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getVillagesComboBoxForAMandal(value,elmtId)
{
	var jsObj=
		{				
				mandalId:value,
				elmtId:elmtId,
				task:"findVillagesForAMandal",
				taskType:"getRegions"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getVillagesForAMandalAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getMandalsComboBoxForAConstituency(value,elmtId)
{
	var jsObj=
		{				
				constituencyId:value,
				elmtId:elmtId,
				task:"findMandalsForAConstituency",
				taskType:"getRegions"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMandalsForAConstituencyAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getConstituenciesComboBoxForADistrict(value,elmtId)
{
	var jsObj=
		{				
				districtId:value,
				elmtId:elmtId,
				task:"findConstituenciesForADistrict",
				taskType:"getRegions"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getConstituenciesForADistrictAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getDistrictsComboBoxForAState(value,elmtId)
{
	var jsObj=
		{				
				stateId:value,
				elmtId:elmtId,
				task:"findDistrictsForAState",
				taskType:"getRegions"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDistrictsForAStateAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getStatesComboBoxForACountry(value,elmtId)
{		
	var jsObj=
		{				
				countryId:value,
				elmtId:elmtId,
				task:"findStateForACountry",
				taskType:"getRegions"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForACountryAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function showCadreSearchResults(jsObj,results)
{
	var headElmt = document.getElementById("searchResultsDiv_head");
	var bodyElmt = document.getElementById("searchResultsDiv_body");
	var footerElmt = document.getElementById("searchResultsDiv_footer");

	if(!headElmt || !bodyElmt || !footerElmt)
		return;
	
	headElmt.innerHTML = 'Search Results';
	if(results.length == 0)
	{
		bodyElmt.innerHTML = '<div>No Search results found.</div>';
		return;
	}
	

	var jsArray = new Array();

	for(var i in results)
	{
		var obj={
					checkBox:'<input type="checkbox" name="cadreResult_check" value="'+results[i].cadreID+'">',
					fname:results[i].firstName+' '+results[i].middleName,
					lname:results[i].lastName,
					gender:results[i].gender,
					mobile:results[i].mobile,
					landline:results[i].landLineNo,
					email:results[i].email,
					moreDetails:'<a href="javascript:{}" onclick="getCadreInfo(\''+results[i].cadreID+'\')">More Details</a>',
		        };
		jsArray.push(obj);
	}
	
	var resultsDataSource = new YAHOO.util.DataSource(jsArray);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "checkBox"
		}, {
			key : "fname"
		}, {
			key : "lname"
		}, {
			key : "gender"
		}, {
			key : "mobile",parser:"number"
		} , {
			key : "landline",parser:"number"
		} , {
			key : "email"
		} , {
			key : "moreDetails"
		} 
		]
	};

	var resultsColumnDefs = [ {
		key : "checkBox",
		label : "Select",
		sortable : false
	}, {
		key : "fname",
		label : "First Name",
		sortable : true
	}, {
		key : "lname",
		label : "Last Name",
		sortable : true
	}, {
		key : "gender",
		label : "Gender",
		sortable : true
	}, {
		key : "mobile",
		label : "Mobile",
		sortable : true
	}, {
		key : "landline",
		label : "Landline",
		sortable : true
	}, {
		key : "email",
		label : "Email",
		sortable : true
	}, {
		key : "moreDetails",
		label : "More Details",
		sortable : false
	}
	];

    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,		        
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
				rowsPerPageOptions: [20,40,60], 
			    pageLinks: 20
			    }) 
				};	
	var myDataTable = new YAHOO.widget.DataTable("searchResultsDiv_body",resultsColumnDefs, resultsDataSource,myConfigs);  
	

	var fStr = '';
	fStr += '<span><input type="button" onclick="selectCheckBox()" value="Select All"/></span>';
	fStr += '<span><input type="button" onclick="deSelectCheckBox()" value="DeSelect All"/></span>';
	fStr += '<span><input type="button" onclick="sendCadreSMS()" value="Send SMS"/></span>';
	fStr += '<span id="smsStatusTextSpan"></span>';

	footerElmt.innerHTML = fStr;
}

function selectCheckBox()
{
	var elements = document.getElementsByName("cadreResult_check");
	
	for(var i in elements)
	{
		elements[i].checked = "true";
	}	
}

function deSelectCheckBox()
{
	var elements = document.getElementsByName("cadreResult_check");
	
	for(var i in elements)
	{
		elements[i].checked = "false";
	}
}

function sendCadreSMS()
{
	var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("smsStatusTextSpan");

	if(!elements || !errorSpanElmt)
		return;

	var selectedCadresArray = new Array();

	for(var i in elements)
	{
		if(elements[i].checked == true)
			selectedCadresArray.push(elements[i].value);
	}

	if(selectedCadresArray.length == 0)
	{
		errorSpanElmt.innerHTML = 'Atleast One cadre need to selected to send SMS';
		return;
	}

	var jsObj=
		{				
				cadreIds:selectedCadresArray,
				task:"sendSMSForCadreIds"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendSMSForCadresAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getCadreInfo(cadreId)
{
	var urlStr = "getCadreInfoAction.action?windowTask=cadreInfoPopup&cadreId="+cadreId;
	var browser2 = window.open(urlStr,"cadreInfoPopup","scrollbars=yes,height=500,width=600,left=200,top=200");	
	browser2.focus();
}

function callAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.taskType == "getRegions")
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
							else if(jsObj.task == "cadreSearch")
								showCadreSearchResults(jsObj,myResults);


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

