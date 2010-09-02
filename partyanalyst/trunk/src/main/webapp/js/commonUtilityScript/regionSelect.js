// JavaScript Document

var CLICKTYPE = '';
var REPORTLEVEL = '';
var REPORTLOCATIONVALUE = '';

var SOCIALSTATUS = false;
var SOCIALSTATUSARRAY = new Array();

var CADRETYPE = 'all';
var SEARCHTYPE = 'location';
var SEARCHCRITERIA = 'all';
var SEARCHCRITERIAVALUE = '0';
var SEARCHCRITERIAARRAY = new Array();

var PERFORMSEARCH = 'and';

var SMSTEXTAREAVALUE = '';
var SMSINCLUDECADRENAME = 'NO';

var searchCriteriaArr = ["committe","skills","trainingCamps"];
var socialStatus = new Array();
var eduStatus = new Array();
var partyCommitte = new Array();
var cadreSkills = new Array();
var partyTrainingCamps = new Array();
var occupations = new Array();
var clickIndex = '';

function addSocialStatusValue(elmt)
{	
	var value = elmt.value;	
	var selectElmt = document.getElementById("socialStatus_"+value);
	if(!selectElmt)
		return;
	
	if(elmt.checked == false)
	{
		selectElmt.disabled=true;
		for(var i=0;i<SOCIALSTATUSARRAY.length;i++)
		{
			if(SOCIALSTATUSARRAY[i].statusValue == value)
				SOCIALSTATUSARRAY.splice(i,1);
		}		
	}
	else
	{
		selectElmt.disabled=false;	
		
		if(!elmt)
			return;

		var sdValues = [];
		
		for(var i = 0; i < selectElmt.options.length; i++)
		{
			if(selectElmt.options[i].selected == true)			
				sdValues.push(selectElmt.options[i].value);
		}
		

		var obj = {
					statusValue:""+value,
					ElmtValue:sdValues
				  };

		SOCIALSTATUSARRAY.push(obj);
	}
	
}	

function getSearchOptions(elmt)
{
	var categorySelectElmt = document.getElementById(elmt.value+"_Select");
	SEARCHCRITERIA = elmt.value;

	if(!categorySelectElmt)
		return;	
	
	if(elmt.checked == false)
	{
		categorySelectElmt.disabled = true;
		for(var i=0;i<SEARCHCRITERIAARRAY.length;i++)
		{
			if(SEARCHCRITERIAARRAY[i].statusValue == elmt.value)
				SEARCHCRITERIAARRAY.splice(i,1);
		}		
		for(var i = 0; i < categorySelectElmt.options.length; i++)
		{
			if(i == 0)			
				categorySelectElmt.options[i].selected = true;
			else
				categorySelectElmt.options[i].selected = false;
		}
	}
	else
	{
		categorySelectElmt.disabled = false;
		if(!elmt)
			return;

		var sdValues = [];

		for(var i = 0; i < categorySelectElmt.options.length; i++)
		{
			if(categorySelectElmt.options[i].selected == true)			
				sdValues.push(categorySelectElmt.options[i].value);
		}
		
		var obj = {
					statusValue:""+elmt.value,
					ElmtValue:sdValues
				  };

		SEARCHCRITERIAARRAY.push(obj);
	}

	
}



function getCriteriaValue(criteriaValue,elmtId)
{
	CADRETYPE = criteriaValue;
	var labelSpanElmt = document.getElementById(elmtId+"_label");
	var dataSpanElmt = document.getElementById(elmtId+"_data");
	var fiterOptionsElmt = document.getElementById("filterOptionsCadresSearch");

	if(!labelSpanElmt || !dataSpanElmt || !fiterOptionsElmt)
		return;
	
	var labelStr = '',dataStr = '';
	labelSpanElmt.innerHTML = labelStr;
	dataSpanElmt.innerHTML = dataStr;
	

	if(CADRETYPE == "active")
	{
		if(fiterOptionsElmt.style.height != 0)
			callYUIAnim(420,"filterOptionsCadresSearch");

		labelStr += ' <font color="#FF0000"> * </font> Search Criteria';
		labelSpanElmt.innerHTML = labelStr;

		dataStr += '<table>';		
		dataStr += '	<tr>';
		dataStr += '		<td><input type="checkbox" name="criteriaValue" onclick="getSearchOptions(this)" value="committe"/>Committe Wise</td>';
		dataStr += '		<td>';
		dataStr += '			<select id="committe_Select" onclick="changeSearchCriteriaValue(this)" multiple="multiple" size="3" disabled="disabled" class="searchcriteriaSelect" onchange="javascript:{SEARCHCRITERIAVALUE = this.options[this.selectedIndex].value;}">';		
		for(var i in partyCommitte)
		{
			if(i == 0)
				dataStr += '			<option onclick="javascript:{clickIndex = this.index;}" value="'+partyCommitte[i].id+'" selected="selected">'+partyCommitte[i].name+'</option>';
			else
				dataStr += '			<option onclick="javascript:{clickIndex = this.index;}" value="'+partyCommitte[i].id+'">'+partyCommitte[i].name+'</option>';
		}		
		dataStr += '			</select>';
		//dataStr += '			<span id="committe_Select"></span>';
		dataStr += '		</td>';
		dataStr += '	</tr>';
		dataStr += '	<tr>';
		dataStr += '		<td><input type="checkbox" name="criteriaValue" onclick="getSearchOptions(this)" value="skills"/> Skills Wise</td>';
		dataStr += '		<td>';		
		dataStr += '			<select id="skills_Select" onclick="changeSearchCriteriaValue(this)" multiple="multiple" size="3" disabled="disabled" class="searchcriteriaSelect" onchange="javascript:{SEARCHCRITERIAVALUE = this.options[this.selectedIndex].value;}">';		
		for(var i in cadreSkills)
		{
			if(i == 0)
				dataStr += '			<option onclick="javascript:{clickIndex = this.index;}" value="'+partyCommitte[i].id+'" selected="selected">'+partyCommitte[i].name+'</option>';
			else
				dataStr += '			<option onclick="javascript:{clickIndex = this.index;}" value="'+cadreSkills[i].id+'">'+cadreSkills[i].name+'</option>';
		}		
		dataStr += '			</select>';
		//dataStr += '	<span id="skills_Select"></span>';
		dataStr += '	</td>';
		dataStr += '	</tr>';
		dataStr += '	<tr>';
		dataStr += '		<td><input type="checkbox" name="criteriaValue" onclick="getSearchOptions(this)" value="trainingCamps"/>';
		dataStr += '		Training Camps Wise</td>';
		dataStr += '		<td>';
		dataStr += '			<select id="trainingCamps_Select" onclick="changeSearchCriteriaValue(this)" multiple="multiple" size="3" disabled="disabled" class="searchcriteriaSelect" onchange="javascript:{SEARCHCRITERIAVALUE = this.options[this.selectedIndex].value;}">';		
		for(var i in partyTrainingCamps)
		{
			if(i == 0)
				dataStr += '			<option onclick="javascript:{clickIndex = this.index;}" value="'+partyCommitte[i].id+'" selected="selected">'+partyCommitte[i].name+'</option>';
			else
				dataStr += '			<option onclick="javascript:{clickIndex = this.index;}" value="'+partyTrainingCamps[i].id+'">'+partyTrainingCamps[i].name+'</option>';
		}		
		dataStr += '			</select>';
		//dataStr += '<span id="trainingCamps_Select"></span></td>';
		dataStr += '	</tr>';
		dataStr += '</table>';
		
		dataSpanElmt.innerHTML = dataStr;
	}
	else if(CADRETYPE == "normal")
	{	
		if(fiterOptionsElmt.style.height != 0)
			callYUIAnim(300,"filterOptionsCadresSearch");

		
		dataSpanElmt.innerHTML = '';
	}
	else if(CADRETYPE == "all")
	{	
		if(fiterOptionsElmt.style.height != 0)
			callYUIAnim(300,"filterOptionsCadresSearch");

		
		dataSpanElmt.innerHTML = '';
	}

}

function changeSearchCriteriaValue(elmt)
{	

	var elmtValue = elmt.id.substring(0,elmt.id.indexOf('_'));
	
	var sdValues = [];

	if(clickIndex != 0)
	{
		elmt.options[0].selected = false;
	}
	else 
	{
		for(var i = 1; i < elmt.options.length; i++)
		{
			elmt.options[i].selected = false;
		}
	}

	for(var i = 0; i < elmt.options.length; i++)
	{		
		if(elmt.options[i].selected == true)			
		{		
			sdValues.push(elmt.options[i].value);
		}
			
	}	

	for(var i =0;i<SEARCHCRITERIAARRAY.length;i++)
	{
		if(SEARCHCRITERIAARRAY[i].statusValue == elmtValue)
			SEARCHCRITERIAARRAY[i].ElmtValue = sdValues;
	}
}

function changeSocialStatus(elmt)
{	
	var elmtValue = elmt.id.substring(elmt.id.indexOf('_')+1,elmt.id.length);
	
	var sdValues = [];

	if(clickIndex != 0)
	{
		elmt.options[0].selected = false;
	}
	else 
	{
		for(var i = 1; i < elmt.options.length; i++)
		{
			elmt.options[i].selected = false;
		}
	}

	for(var i = 0; i < elmt.options.length; i++)
	{		
		if(elmt.options[i].selected == true)			
		{		
			sdValues.push(elmt.options[i].value);
		}
			
	}	

	for(var i =0;i<SOCIALSTATUSARRAY.length;i++)
	{
		if(SOCIALSTATUSARRAY[i].statusValue == elmtValue)
			SOCIALSTATUSARRAY[i].ElmtValue = sdValues;
	}


}

function createOptionsForId(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		if(i == 0)
			option.selected = "selected";
		option.setAttribute("onclick","javascript:{clickIndex = this.index;}");
		

		
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

function searchBased(value)
{
	var rowElmt = document.getElementById("locationRangeRow");
	if(!rowElmt)
		return;

	if(value == "location")
	{
		SEARCHTYPE = "location";
		rowElmt.style.visibilty = 'visible';
	}
	else if(value == "level")
	{
		SEARCHTYPE = "level";
		rowElmt.style.visibility = 'hidden';
	}
}

function expandFilterOptions()
{
	var elmts = document.getElementsByName("cadreTypeRadio");
	var cType = '';
	var height = '';
	if(!elmts || elmts.length == 0)
		return;

	for(var i=0;i<elmts.length;i++)
	{
		if(elmts[i].checked==true)
			cType = elmts[i].value; 
	}
	
	if(cType == "all" || cType == "normal")
		height = 300;
	else if(cType == "active")
		height = 420;

	callYUIAnim(height,"filterOptionsCadresSearch");
}

function callYUIAnim(height,elmtId)
{
	var cadreAnim = new YAHOO.util.Anim(elmtId, {
		height: {
			to: height 
		} 
	}, 1, YAHOO.util.Easing.easeOut);

	cadreAnim.animate();

}






function showSocialStatus(elmt)
{	
	var checkElmts = document.getElementsByName("socialStatus");
	var status;

	if(elmt.checked == true)
	{
		SOCIALSTATUS = true;
		status = false;
	}
	else if(elmt.checked == false)
	{
		SOCIALSTATUS = false;
		SOCIALSTATUSARRAY = [];
		status = true;
	}

	if(checkElmts.length == 0)
		return;
	
	for(var i in checkElmts)
	{		
		checkElmts[i].disabled = status;		
	}

	
}


function limitText(limitField, limitCount, limitNum)
{		
	var limitFieldElmt = document.getElementById(limitField);
	var limitCountElmt = document.getElementById(limitCount);

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
	}
}
function sendSMSWithoutSearch()
{
	var smsTxtLabelElmt = document.getElementById("smsTxtArea_label");
	var smsTxtdataElmt = document.getElementById("smsTxtArea_data");
	var includeUserNameLabelElmt = document.getElementById("includeUserName_label");
	var includeUserNameDataElmt = document.getElementById("includeUserName_data");
	var smsSendSpanButtonElmt = document.getElementById("smsSendSpan_button");

	if(smsTxtLabelElmt)
		smsTxtLabelElmt.innerHTML="SMS Text";
	
	var smsStr='';
	smsStr+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText(\'smsTextArea\',\'maxcount\',200)" ></textarea></div> ';
	smsStr+='<div id="limitDiv">';
	smsStr+='<table><tr>';
	smsStr+='<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
	smsStr+='<td style="width:50%;"><div>Should not exceed 200 chars</div></td>';
	smsStr+='</tr></table>';
	smsStr+='</div>';			
	
	if(smsTxtdataElmt)
		smsTxtdataElmt.innerHTML=smsStr;

	if(includeUserNameLabelElmt)
		includeUserNameLabelElmt.innerHTML="Include User Name";
	
	var smsUserIncludeStr='<input type="radio" id="include_user_name" name="include_user_name" value="YES" /> Yes';
	smsUserIncludeStr+='<input type="radio" id="no_user_name" name="include_user_name" value="NO" checked="checked"/> No    ';
	
	if(includeUserNameDataElmt)
		includeUserNameDataElmt.innerHTML=smsUserIncludeStr;
	
	if(smsSendSpanButtonElmt)
		smsSendSpanButtonElmt.innerHTML = '<input type="button" value="Send SMS" onclick="getCadresResults(\'sms\')">';
	 
}


function getRegionsForAccessLevel(accessValue,regionElmtId)
{
	REPORTLEVEL = accessValue;

	
	var countrySelectBoxElmt = document.getElementById("countrySelectBox");
	var stateSelectBoxElmt = document.getElementById("stateSelectBox");
	var districtSelectBoxElmt = document.getElementById("districtSelectBox");
	var constituencySelectBoxElmt = document.getElementById("constituencySelectBox");
	var mandalSelectBoxElmt = document.getElementById("mandalSelectBox");
	var villageSelectBoxElmt = document.getElementById("villageSelectBox");

	countrySelectBoxElmt.style.visibility="hidden";
	stateSelectBoxElmt.style.visibility="hidden";
	districtSelectBoxElmt.style.visibility="hidden";
	constituencySelectBoxElmt.style.visibility="hidden";
	mandalSelectBoxElmt.style.visibility="hidden";
	villageSelectBoxElmt.style.visibility="hidden";

	if(REPORTLEVEL == '1')
	{
		countrySelectBoxElmt.style.visibility = "visible";
	}
	else if(REPORTLEVEL == '2')
	{
		countrySelectBoxElmt.style.visibility = "visible";
		stateSelectBoxElmt.style.visibility = "visible";
	}
	else if(REPORTLEVEL == '3')
	{
		countrySelectBoxElmt.style.visibility = "visible";
		stateSelectBoxElmt.style.visibility = "visible";
		districtSelectBoxElmt.style.visibility = "visible";
	}
	else if(REPORTLEVEL == '4')
	{
		countrySelectBoxElmt.style.visibility = "visible";
		stateSelectBoxElmt.style.visibility = "visible";
		districtSelectBoxElmt.style.visibility = "visible";
		constituencySelectBoxElmt.style.visibility = "visible";
	}
	else if(REPORTLEVEL == '5')
	{
		countrySelectBoxElmt.style.visibility = "visible";
		stateSelectBoxElmt.style.visibility = "visible";
		districtSelectBoxElmt.style.visibility = "visible";
		constituencySelectBoxElmt.style.visibility = "visible";
		mandalSelectBoxElmt.style.visibility = "visible";
	}
	else if(REPORTLEVEL == '6')
	{
		countrySelectBoxElmt.style.visibility = "visible";
		stateSelectBoxElmt.style.visibility = "visible";
		districtSelectBoxElmt.style.visibility = "visible";
		constituencySelectBoxElmt.style.visibility = "visible";
		mandalSelectBoxElmt.style.visibility = "visible";
		villageSelectBoxElmt.style.visibility = "visible";
	}

	
	/*var labelSpanElmt = document.getElementById(regionElmtId+"_label");
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
	buttonStr += '<span><input type="button" onclick="getCadresResults(\'search\')" value="Search"/></span>';
	buttonStr += '<span><input type="button" onclick="sendSMSWithoutSearch()" value="Click To Send SMS"/></span>';
	buttonStr += '</div>';

	
	labelSpanElmt.innerHTML = labelStr;
	buttonSpanElmt.innerHTML = buttonStr;
	
	if(REPORTLEVEL == "1")
	{		
		dataStr += '<select id="countrySelectBox" onchange="javascript:{REPORTLOCATIONVALUE = 1}">';
		dataStr += '<option id="0"> Select </option>';
		dataStr += '<option id="1"> India </option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;		
	}
	else if(REPORTLEVEL == "2")
	{
		dataStr += '<select id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,\'stateSelectBox\')">';
		dataStr += '<option value="0"> Select Country</option>';
		dataStr += '<option value="1"> India </option>';
		dataStr += '</select>';

		dataStr += '<select id="stateSelectBox" onchange="javascript:{REPORTLOCATIONVALUE = this.options[this.selectedIndex].value}">';
		dataStr += '<option value="0"> Select State</option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;
		
	}
	else if(REPORTLEVEL == "3")
	{
		dataStr += '<select id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,\'stateSelectBox\')">';
		dataStr += '<option value="0"> Select Country</option>';
		dataStr += '<option value="1"> India </option>';
		dataStr += '</select>';

		dataStr += '<select id="stateSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'districtSelectBox\')">';
		dataStr += '<option value="0"> Select State</option>';
		dataStr += '</select>';

		dataStr += '<select id="districtSelectBox" onchange="javascript:{REPORTLOCATIONVALUE = this.options[this.selectedIndex].value}">';
		dataStr += '<option value="0"> Select District</option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;
	}
	else if(REPORTLEVEL == "4")
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

		dataStr += '<select id="constituencySelectBox" onchange="javascript:{REPORTLOCATIONVALUE = this.options[this.selectedIndex].value}">';
		dataStr += '<option value="0"> Select Constituency</option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;
	}
	else if(REPORTLEVEL == "5")
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
		
		dataStr += '<select id="mandalSelectBox" onchange="javascript:{REPORTLOCATIONVALUE = this.options[this.selectedIndex].value}">';
		dataStr += '<option value="0"> Select Mandal</option>';
		dataStr += '</select>';		
		
		dataSpanElmt.innerHTML = dataStr;
	}
	else if(REPORTLEVEL == "6")
	{
		dataStr += '<select class="regionsSelectBox" id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,\'stateSelectBox\')">';
		dataStr += '<option value="0"> Select Country </option>';
		dataStr += '<option value="1"> India </option>';
		dataStr += '</select>';

		dataStr += '<select class="regionsSelectBox" id="stateSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'districtSelectBox\')">';
		dataStr += '<option value="0"> Select State</option>';
		dataStr += '</select>';

		dataStr += '<select class="regionsSelectBox" id="districtSelectBox" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,\'constituencySelectBox\')">';
		dataStr += '<option value="0"> Select District</option>';
		dataStr += '</select>';

		dataStr += '<select class="regionsSelectBox" id="constituencySelectBox" onchange="getMandalsComboBoxForAConstituency(this.options[this.selectedIndex].value,\'mandalSelectBox\')">';
		dataStr += '<option value="0"> Select Constituency</option>';
		dataStr += '</select>';
		
		dataStr += '<select class="regionsSelectBox" id="mandalSelectBox" onchange="getVillagesComboBoxForAMandal(this.options[this.selectedIndex].value,\'villageSelectBox\')">';
		dataStr += '<option value="0"> Select Mandal</option>';
		dataStr += '</select>';

		dataStr += '<select class="regionsSelectBox" id="villageSelectBox" onchange="javascript:{REPORTLOCATIONVALUE = this.options[this.selectedIndex].value}">';
		dataStr += '<option value="0"> Select Village</option>';
		dataStr += '</select>';
		
		dataSpanElmt.innerHTML = dataStr;
	}*/
}

function getCadresResults(btnType)
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
		
	
	if(REPORTLEVEL == '') 
	{
		elmt.innerHTML = 'Please Select Range';
		return;		
	}

	if(REPORTLEVEL == "1")
	{
		if(!countrySelectElmt || countrySelectElmt.options[countrySelectElmt.selectedIndex].value == "0")
		{
			elmt.innerHTML = 'Select Country Location';
			return;
		}
		else
		{
			elmt.innerHTML = '';
			locationValue = countrySelectElmt.options[countrySelectElmt.selectedIndex].value;
			
		}
	}	
	else if(REPORTLEVEL == "2")
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
	else if(REPORTLEVEL == "3")
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
	else if(REPORTLEVEL == "4")
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
	else if(REPORTLEVEL == "5")
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
	else if(REPORTLEVEL == "6")
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
	
	REPORTLOCATIONVALUE = locationValue;

	if(SOCIALSTATUS)
	{	
		if(SOCIALSTATUSARRAY.length == 0)
		{
			elmt.innerHTML = 'Please Select Any one of the social status or Unselect social status.';
			return;
		}
	}
	
	if(btnType == "sms")
	{
		var textAreaElmt = document.getElementById("smsTextArea");
		textAreaElmtValue = textAreaElmt.value;

		if(textAreaElmtValue == '')
		{
			elmt.innerHTM = 'Please Type A Message!';
			return;
		}
		else
			SMSTEXTAREAVALUE = textAreaElmtValue;

	}
	

	var jsObj=
		{		
			reportLevel:REPORTLEVEL,
			reportLocationValue:REPORTLOCATIONVALUE,
			socialStatus:SOCIALSTATUS,
			socialStatusArray:SOCIALSTATUSARRAY,
			cadreType:CADRETYPE,
			searchType:SEARCHTYPE,
			searchCriteria:SEARCHCRITERIA,
			searchCriteriaArray:SEARCHCRITERIAARRAY,
			searchCriteriaValue:SEARCHCRITERIAVALUE,
			performSearch:PERFORMSEARCH,
			txtAreaValue:SMSTEXTAREAVALUE,
			includeCadreName:SMSINCLUDECADRENAME,
			taskType:btnType,
			task:"cadreSearch"		
		}
	
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);

	if(btnType == "search")
		var url = "getCadresDetailsAjaxAction.action?"+rparam;						
	else if(btnType == "sms")
		var url = "sendSMSForCadresAction.action?"+rparam;						

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

function buildCadresDatatable(results,divId)
{
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
	var myDataTable = new YAHOO.widget.DataTable("searchResult",resultsColumnDefs, resultsDataSource,myConfigs);  
	
}

function showSMSResults(jsObj,results)
{
	var headElmt = document.getElementById("searchResultsDiv_head");
	var bodySearchElmt = document.getElementById("searchResult");
	var bodySMSElmt = document.getElementById("smsResult");
	var footerElmt = document.getElementById("searchResultsDiv_footer");

	if(!headElmt || !bodySearchElmt || !bodySMSElmt || !footerElmt)
		return;

	headElmt.innerHTML = 'SMS Results';

	
	bodySMSElmt.innerHTML = 'SMS successfullty sent to '+results.totalSmsSent+' cadres';
	bodySMSElmt.innerHTML += '<a href="javascript:{}" onclick="showSentSMSCadres()"> View cadres</a>';

	buildCadresDatatable(results.smsSentCadreInfo,"searchResult");
}

function showSentSMSCadres()
{
	var elmt = document.getElementById("searchResult");

	if(elmt)
		elmt.style.display = 'block';
}


function showCadreSearchResults(jsObj,results)
{
	var headElmt = document.getElementById("searchResultsDiv_head");
	var bodySearchElmt = document.getElementById("searchResult");
	var footerElmt = document.getElementById("searchResultsDiv_footer");

	if(!headElmt || !bodySearchElmt || !footerElmt)
		return;
	
	bodySearchElmt.style.display = 'block';

	headElmt.innerHTML = 'Search Results';
	if(!results || results.length == 0)
	{
		bodySearchElmt.innerHTML = '<div style="color:#C0566F;font-size:12px;">No Search results found.</div>';
		return;
	}
	
	buildCadresDatatable(results,"searchResult");
	

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
							{
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtIdWithSelectOption(jsObj.elmtId,myResults);
							}
							else if(jsObj.task == "cadreSearch" && jsObj.taskType == "search")
							{
								showCadreSearchResults(jsObj,myResults);
							}
							else if(jsObj.task == "cadreSearch" && jsObj.taskType == "sms")
							{
								showSMSResults(jsObj,myResults);
							}
							else if(jsObj.task == "getUserLocation")
								buildRegionsSelectBoxes(jsObj,myResults);


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

function displayRegionsSelect(val,regTask)
{
	var stateSelectElmt = document.getElementById(regTask+"_StateSelect");
	var districtSelectElmt = document.getElementById(regTask+"_DistrictSelect");
	var constituencySelectElmt = document.getElementById(regTask+"_ConstituencySelect");
	var mandalSelectElmt = document.getElementById(regTask+"_MandalSelect");
	var villageSelectElmt = document.getElementById(regTask+"_VillageSelect");
	
	stateSelectElmt.disabled=true;
	districtSelectElmt.disabled=true;	
	constituencySelectElmt.disabled=true;	
	mandalSelectElmt.disabled=true;	
	villageSelectElmt.disabled=true;	

	if(val == "District")
	{
		stateSelectElmt.disabled=false;
		districtSelectElmt.disabled=false;			
	}
	if(val == "Constituency")
	{
		stateSelectElmt.disabled=false;
		districtSelectElmt.disabled=false;	
		constituencySelectElmt.disabled=false;	
	}
	if(val == "Mandal")
	{
		stateSelectElmt.disabled=false;
		districtSelectElmt.disabled=false;
		constituencySelectElmt.disabled=false;	
		mandalSelectElmt.disabled=false;	
	}
	if(val == "Village")
	{
		stateSelectElmt.disabled=false;
		districtSelectElmt.disabled=false;	
		constituencySelectElmt.disabled=false;	
		mandalSelectElmt.disabled=false;	
		villageSelectElmt.disabled=false;	
	}		
}

function buildRegionsSelectBoxes(jsObj,results)
{
	var selectElmt = document.getElementById("rangeRegionsSelectElmtDiv");
	var radioElmt = document.getElementById("rangeRegionsRadioElmtDiv");
	if(!radioElmt || !selectElmt)
		return;

	regTask = "cadreSearch";
	var str='';
		for(var i in results.regions)
		{
			str+='<input type="radio" name="region_type_radio" value="'+results.regions[i].name+'" onclick="displayRegionsSelect(this.value,\''+regTask+'\')" /> '+results.regions[i].name+'';
		}		
	if(radioElmt)
		radioElmt.innerHTML=str;
	
	//Filling up select box...

	var regionStr='';
		
	regionStr+='<select id="'+regTask+'_StateSelect" class="selectBox" onchange="getNextRegions(this.id,\'STATE\',\''+regTask+'\')" disabled="true">';
	if(results.states != "")
	{
		for(var state in results.states)
		{
			regionStr+='<option value="'+results.states[state].id+'">'+results.states[state].name+'</option>';
		}
	}
	else
	{
		regionStr+='<option value="0"> Select State</option>';
	}
	regionStr+='</select>';	

	
	regionStr+='<select id="'+regTask+'_DistrictSelect" class="selectBox" onchange="getNextRegions(this.id,\'DISTRICT\',\''+regTask+'\')" disabled="true">';
	if(results.districts != "")
	{
		for(var district in results.districts)
		{
			regionStr+='<option value="'+results.districts[district].id+'">'+results.districts[district].name+'</option>';
		}
	}
	else
	{
		regionStr+='<option value="0"> Select District</option>';
	}
	regionStr+='</select>';
	
	
	regionStr+='<select id="'+regTask+'_ConstituencySelect" class="selectBox" onchange="getNextRegions(this.id,\'CONSTITUENCY\',\''+regTask+'\')" disabled="true">';
	if(results.constituencies != "")
	{
		for(var consti in results.constituencies)
		{
			regionStr+='<option value="'+results.constituencies[consti].id+'">'+results.constituencies[consti].name+'</option>';
		}
	}
	else
	{
		regionStr+='<option value="0"> Select Constituency</option>';
	}
	regionStr+='</select>';



	regionStr+='<select id="'+regTask+'_MandalSelect" class="selectBox" onchange="getNextRegions(this.id,\'MANDAL\',\''+regTask+'\')" disabled="true">';
	if(results.mandals != "")
	{
		for(var mandal in results.mandals)
		{
			regionStr+='<option value="'+results.mandals[mandal].id+'">'+results.mandals[mandal].name+'</option>';
		}
	}
	else
	{	
		regionStr+='<option value="0"> Select Mandal</option>';
	}
	regionStr+='</select>';

	
	regionStr+='<select id="'+regTask+'_VillageSelect" class="selectBox" disabled="true">';
	if(results.villages != "")
	{
		for(var village in results.villages)
		{
			regionStr+='<option value="'+results.villages[village].id+'">'+results.villages[village].name+'</option>';
		}
	}
	else
	{	
		regionStr+='<option value="0"> Select Village</option>';
	}
	regionStr+='</select>';

	if(selectElmt)
		selectElmt.innerHTML=regionStr;

}

function getNextRegions(id,val,regTask)
{
	var selectElmt = document.getElementById(id);
	var selectValue = selectElmt.options[selectElmt.selectedIndex].value;
	
	if(selectValue=="0")
		return;

	var jsObj={
				value:selectValue,
				type:val,
				taskType:regTask,
				task:"fillSelectElements"
			  };
	var url = "regionsByCadreScope.action?REGION="+val+"&REGION_ID="+selectValue;
	callAjax(jsObj,url);

}



function buildselectBoxes()
{	
	var jsObj={
				value:"locationWise",
				taskType:"search",
				task:"getUserLocation"
			  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "cadreSMSLocationWiseData.action?";
	//callAjax(jsObj,url);
	createOptionsForId("socialStatus_resevation",socialStatus);
	createOptionsForId("socialStatus_education",eduStatus);
	createOptionsForId("socialStatus_occupation",occupations);
}
