// JavaScript Document

var CLICKTYPE = '';
var REPORTLEVEL = '';
var REPORTLOCATIONVALUE = '';
var USERTYPE = '';
var partyHasCommitees = '';
var partyHasCamps = '';
var partyHasSkills = '';

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
var selectedCadresArray = new Array();
var clickIndex = '';
var smsDialog = '';
var errorMsglabel = '';

function addSocialStatusValue(elmt)
{	
	
	var value = elmt.value;
	var selectElmt = document.getElementById("socialStatus_"+value);
	//alert('selectElmt:'+selectElmt);
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
		
        if(partyHasCommitees == 'true' || partyHasSkills == 'true' || partyHasCamps == 'true')
		{

			if(fiterOptionsElmt.style.height != 0)
			callYUIAnim(420,"filterOptionsCadresSearch");

			labelStr += ' <font color="#FF0000"> * </font> Search Criteria';
			labelSpanElmt.innerHTML = labelStr;
		}

		dataStr += '<table>';	
		if(partyHasCommitees == 'true')
		{
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
		}
		
		if(partyHasSkills == 'true')
		{
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
		}
		
		if(partyHasCamps == 'true')
		{
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
		}
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
	clickIndex = elmt.selectedIndex;
	
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
	var rowElmt = document.getElementById("rangeRegionsSelectElmtDiv");

	if(!rowElmt)
		return;

	if(value == "location")
	{
		SEARCHTYPE = "location";
		rowElmt.style.display = '';

		getLocationWiseRangeDetails();
	}
	else if(value == "level")
	{
		SEARCHTYPE = "level";
		rowElmt.style.display = 'none';

		getLevelWiseRangeDetails();
	}
}

function getLocationWiseRangeDetails()
{
   var jsObj={
				value:"locationWise",
				taskType:"search",
				task:"getUserLocation"
			  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "cadreSMSLocationWiseData.action?";
	callAjaxForRegionSelect(jsObj,url);

}

function getLevelWiseRangeDetails()
{
   
   var divElmt = document.getElementById("rangeRegionsRadioElmtDiv");
   

   if(!divElmt)
	   return;

  
   var rangeStr='';
   //rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="1"/> Country';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="2"/> State';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="3"/> District';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="10"/> Parliament Constituency';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="4"/> Assembly Constituency';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="5"/> Mandal';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="6"/> Village';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="7"/> MUNICIPAL-CORP-GMC';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="8"/> WARD';
   rangeStr+='<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,\'accessRegion\')" value="9"/> BOOTH';

   divElmt.innerHTML=rangeStr;

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
	
	if(cType == "All" || cType == "normal")
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
	var  selectvalue;
	var  socialStatusvalue;
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

    for(var i=0; i<checkElmts.length; i++)
	{	
		checkElmts[i].disabled = status;
		checkElmts[i].checked = false;
		selectvalue=checkElmts[i].value;
		socialStatusvalue =  document.getElementById("socialStatus_"+selectvalue);
		if(checkElmts[i].disabled){
		socialStatusvalue.disabled = status;
		socialStatusvalue.value = '0';
		
		}
		
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
	
	/*
	//var countrySelectBoxElmt = document.getElementById("countrySelectBox");
	var stateSelectBoxElmt = document.getElementById("stateSelectBox");
	var districtSelectBoxElmt = document.getElementById("districtSelectBox");
	var constituencySelectBoxElmt = document.getElementById("constituencySelectBox");
	var mandalSelectBoxElmt = document.getElementById("mandalSelectBox");
	var villageSelectBoxElmt = document.getElementById("villageSelectBox");

	//countrySelectBoxElmt.style.display="none";
	stateSelectBoxElmt.style.display="none";
	districtSelectBoxElmt.style.display="none";
	constituencySelectBoxElmt.style.display="none";
	mandalSelectBoxElmt.style.display="none";
	villageSelectBoxElmt.style.display="none";

	if(REPORTLEVEL == '1')
	{
		//countrySelectBoxElmt.style.display="";
	}
	else if(REPORTLEVEL == '2')
	{
		//countrySelectBoxElmt.style.display="";
		stateSelectBoxElmt.style.display="";
	}
	else if(REPORTLEVEL == '3')
	{
		//countrySelectBoxElmt.style.display="";
		stateSelectBoxElmt.style.display="";
		districtSelectBoxElmt.style.display="";
	}
	else if(REPORTLEVEL == '4')
	{
		//countrySelectBoxElmt.style.display="";
		stateSelectBoxElmt.style.display="";
		districtSelectBoxElmt.style.display="";
		constituencySelectBoxElmt.style.display="";
	}
	else if(REPORTLEVEL == '5')
	{
		//countrySelectBoxElmt.style.display="";
		stateSelectBoxElmt.style.display="";
		districtSelectBoxElmt.style.display="";
		constituencySelectBoxElmt.style.display="";
		mandalSelectBoxElmt.style.display="";
	}
	else if(REPORTLEVEL == '6')
	{
		//countrySelectBoxElmt.style.display="";
		stateSelectBoxElmt.style.display="";
		districtSelectBoxElmt.style.display="";
		constituencySelectBoxElmt.style.display="";
		mandalSelectBoxElmt.style.display="";
		villageSelectBoxElmt.style.display="";
	}
	*/

}

function getCadresResults(btnType)
{	
	var elmt = document.getElementById("errorMsgDiv");
	var locationValue = '';
	if(!elmt)
		return;

	//var REGISTRATIONNO = document.getElementById("registrationNoText").value;
	var MEMBERSHIPNO = $("#memberShipNoText").val();
	var countrySelectElmt = document.getElementById("countrySelectBox");
	var stateSelectElmt = document.getElementById("stateField_s");
	var districtSelectElmt = document.getElementById("districtField_s");
	var constituencySelectElmt = document.getElementById("constituencyField_s");
	var mandalSelectElmt = document.getElementById("mandalField_s");
	var villageSelectElmt = document.getElementById("hamletField_s");
	var boothSelectElmt = document.getElementById("boothField_s");
	var genderRadioEls = document.getElementsByName("genderTypeRadio");
	var parliamentConstituencyEls = document.getElementById("parliamentConstituencyField_s");
	var genderOption;
	var bloodGroupEle = document.getElementById("bloodGroupId");
	var bloodGroupId = bloodGroupEle.options[bloodGroupEle.selectedIndex].value;
	var nameSelectedRadioEle = document.getElementsByName("nameBasedRadio");
	var cadreRegisterTypeRadioEle = document.getElementsByName("cadreRegisterTypeRadio");
	var nameSelectedRadioValue;
	var cadreRegisterTypeRadioValue;
	for(var i=0; i<nameSelectedRadioEle.length;i++){
	   if(nameSelectedRadioEle[i].checked == true)
	        nameSelectedRadioValue = nameSelectedRadioEle[i].value;
    }
	if(REPORTLEVEL == '') 
	{
		elmt.innerHTML = 'Please Select Range';
		return;		
	}
	
	if(SEARCHTYPE == "location")
	{
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
		else if(REPORTLEVEL == "7")
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
		else if(REPORTLEVEL == "8")
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
		else if(REPORTLEVEL == "9")
		{
			if(!boothSelectElmt || boothSelectElmt.options[boothSelectElmt.selectedIndex].value == "0")
			{
				elmt.innerHTML = 'Select Village Location';
				return;
			}
			else
			{
				elmt.innerHTML = '';
				locationValue = boothSelectElmt.options[boothSelectElmt.selectedIndex].value;
			}
		}
		else if(REPORTLEVEL == "10")
		{
			if(!parliamentConstituencyEls || parliamentConstituencyEls.options[parliamentConstituencyEls.selectedIndex].value == "0")
			{
				elmt.innerHTML = 'Select Constituency';
				return;
			}
			else
			{
				elmt.innerHTML = '';
				locationValue = parliamentConstituencyEls.options[parliamentConstituencyEls.selectedIndex].value;
			}
		}
		
		REPORTLOCATIONVALUE = locationValue;
	}
	else
	{
		REPORTLOCATIONVALUE = "0";
	}
	
	

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
			elmt.innerHTML = 'Please Type A Message!';
			return;
		}
		else
			SMSTEXTAREAVALUE = textAreaElmtValue;

	}
	for(var i=0; i<genderRadioEls.length;i++)
	{
		if(genderRadioEls[i].checked == true)
			genderOption = genderRadioEls[i].value;
	}
	for(var i=0; i<cadreRegisterTypeRadioEle.length;i++)
	{
		if(cadreRegisterTypeRadioEle[i].checked == true)
		cadreRegisterTypeRadioValue = cadreRegisterTypeRadioEle[i].value;
	}
	var includeSenderElmtCheck = document.getElementById("smsIncludeSenderName");
	var senderNameTextElmt = document.getElementById("senderNameText");
	var CADRENAME = document.getElementById("cadreNameText").value;
	
	var SENDERNAME = '';

	if(includeSenderElmtCheck && includeSenderElmtCheck.checked == true)
		SENDERNAME = senderNameTextElmt.value;

	var taskName = '';
	
	/*if(isProblemAdding != null && isProblemAdding == true)
		taskName = "problemAdding";
	else*/
		taskName = "cadreSearch";
	
	var jsObj=
		{		
			reportLevel				: REPORTLEVEL,
			reportLocationValue		: REPORTLOCATIONVALUE,
			socialStatus			: SOCIALSTATUS,
			socialStatusArray		: SOCIALSTATUSARRAY,
			cadreType				: CADRETYPE,
			cadreName				: CADRENAME,
			memberShipNo			: MEMBERSHIPNO,
			searchType				: SEARCHTYPE,
			gender					: genderOption,
			searchCriteria			: SEARCHCRITERIA,
			searchCriteriaArray		: SEARCHCRITERIAARRAY,
			searchCriteriaValue		: SEARCHCRITERIAVALUE,
			performSearch			: PERFORMSEARCH,
			txtAreaValue			: SMSTEXTAREAVALUE,
			includeCadreName		: SMSINCLUDECADRENAME,
			bloodGroupId			: bloodGroupId,
			taskType				: btnType,
			senderName				: SENDERNAME,
			nameSearchTYpe			: nameSelectedRadioValue, 
			cadreRegTypeRadioValue	: cadreRegisterTypeRadioValue,	
			task					: taskName	
		}
	
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	
	var elmtSr = document.getElementById("smsResult");

	if(elmtSr && btnType == "search")
		elmtSr.style.display = 'none';
	
	if(btnType == "sms")
		elmtSr.style.display = '';

	if(btnType == "search")
	{
		var search = "forTotalCount";
		var url = "getCadreDetailsForSMSAjaxAction.action?"+rparam+"&windowTask="+winTask+"&sort=total&startIndex=0&results=-5";
		callAjaxForRegionSelect(jsObj,url);
		buildCadreSearchResultDataTable(rparam);
		//var url = "getCadresDetailsAjaxAction.action?"+rparam;
	}

	else if(btnType == "sms")
	{
		var url = "sendSMSForCadresAction.action?"+rparam;						
		callAjaxForRegionSelect(jsObj,url);
	}
	
}

function getCadresResults1(btnType)
{
	var jsObj1=
		{	extra				:"one",
			reportLevel				: 2,
			reportLocationValue		: "1",
			socialStatus			: false,
			socialStatusArray		: [],
			cadreType				: "all",
			cadreName				: "",
			searchType				: "location",
			gender					: "allGenders",
			searchCriteria			: "all",
			searchCriteriaArray		: [],
			searchCriteriaValue		: "0",
			performSearch			: "and",
			txtAreaValue			: "",
			includeCadreName		: "NO",
			bloodGroupId			: "0",
			taskType				: "search",
			senderName				: "",
			nameSearchTYpe			: "StartingWith", 
			cadreRegTypeRadioValue	: "allCadres",	
			task					: "cadreSearch"	
		}
	
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);

	if(btnType == "search")
	{
		var search = "forTotalCount";
		var url = "getCadreDetailsForSMSAjaxAction.action?"+rparam1+"&windowTask=Search&sort=total&startIndex=0&results=-5";
		callAjaxForRegionSelect(jsObj1,url);
		//alert(jsObj1.extra);
		buildCadreSearchResultDataTable1(rparam1);
		//var url = "getCadresDetailsAjaxAction.action?"+rparam;
	}
}

function buildCadreSearchResultDataTable(rparam)
{
	var searchResult1 = document.getElementById("searchResult");
	YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("cadreId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='openRegistrationForm("+id+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/edit.png'></a>";
		
  };

  YAHOO.widget.DataTable.deleteCadre = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("cadreId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='deleteCadre("+id+",\"search\")'><img style='text-decoration: none; border: 0px none;' src='images/icons/delete.png'></a>";
		
  };

   YAHOO.widget.DataTable.viewDetails = function(elLiner, oRecord, oColumn, oData) 
  {
	var fname = oData;
	var id= oRecord.getData("cadreId");
	var lname= oRecord.getData("lastName");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='getCadreInfo("+id+")'>"+fname+" "+lname+" </a>";
  };

  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
  {
	var name = oData;
	var id= oRecord.getData("cadreId");
	var mobile= oRecord.getData("mobile");
	var firstName= oRecord.getData("firstName");
	elLiner.innerHTML="<input type='checkbox' onClick='getCadreId(this)' name='cadreResult_check' value='"+id+"_"+mobile+"_"+firstName+"'>";
				
  };

  YAHOO.widget.DataTable.image = function(elLiner, oRecord, oColumn, oData) 
  {
	elLiner.innerHTML ="<img height='85px' width='85px' src='images/cadre_images/"+oData+"'/>";
  };
  
  var CadreSearchResultColumnDefs = [ 
		    	            {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							{key:"firstName", label: "Name",sortable: true, formatter:YAHOO.widget.DataTable.viewDetails} ,
							{key:"image", label: "Image",formatter:YAHOO.widget.DataTable.image}, 
		    	            {key:"mobile", label: "Mobile", sortable: true}, 
		    	           	{key:"strCadreLevel", label: "Cadre Level", sortable: true},
							{key:"email", label: "Address"},
		    				{key:"memberType", label: "Cadre Type",sortable:true},
							{key:"educationStr", label: "Education",sortable:true},
		    				{key:"professionStr", label: "Occupation",sortable:true},
							{key:"casteCategoryStr", label: "Caste Category",sortable:true},
							{key:"edit", label: "Edit",formatter:YAHOO.widget.DataTable.edit},
							{key:"Delete", label: "Delete",formatter:YAHOO.widget.DataTable.deleteCadre}
		    	        ]; 
	var CadreSearchResultDataSource = new YAHOO.util.DataSource("getCadreDetailsForSMSAjaxAction.action?"+rparam+"&windowTask=Search&"); 
	CadreSearchResultDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON; 
	
	CadreSearchResultDataSource.responseSchema = { 
            resultsList:"cadreInfo", 
		fields: [
				{key:"firstName"},
				"lastName","image","mobile", "strCadreLevel","memberType",
				"educationStr","professionStr","casteCategoryStr","cadreId","email","pincode"
				],
		metaFields: {
			totalRecords: "totalSearchCount" // Access to value in the server response
		}         
        };


    var myConfigs = {
			        initialRequest: "sort=firstName&dir=asc&startIndex=0&results=20", // Initial request for first page of data
			        dynamicData: true, // Enables dynamic server-driven data
			        sortedBy : {key:"firstName", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
			        paginator: new YAHOO.widget.Paginator({ rowsPerPage:20 }) // Enables pagination 
		};

		var CadreSearchResultDataTable = new YAHOO.widget.DataTable(searchResult1, CadreSearchResultColumnDefs,CadreSearchResultDataSource, myConfigs);

		CadreSearchResultDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) 
		{		
		        oPayload.totalRecords = oResponse.meta.totalRecords;
		        return oPayload;
		}
		//function calling to build Result
		return {
			oDS: CadreSearchResultDataSource,
			oDT: CadreSearchResultDataTable,
    	};
	}

function buildCadreSearchResultDataTable1(rparam)
{
	var searchResult1 = window.opener.document.getElementById("searchResult");
	YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("cadreId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='openRegistrationForm("+id+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/edit.png'></a>";
		
  };

  YAHOO.widget.DataTable.deleteCadre = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("cadreId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='deleteCadre("+id+",\"search\")'><img style='text-decoration: none; border: 0px none;' src='images/icons/delete.png'></a>";
		
  };

   YAHOO.widget.DataTable.viewDetails = function(elLiner, oRecord, oColumn, oData) 
  {
	var fname = oData;
	var id= oRecord.getData("cadreId");
	var lname= oRecord.getData("lastName");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='getCadreInfo("+id+")'>"+fname+" "+lname+" </a>";
  };

  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
  {
	var name = oData;
	var id= oRecord.getData("cadreId");
	var mobile= oRecord.getData("mobile");
	var firstName= oRecord.getData("firstName");
	elLiner.innerHTML="<input type='checkbox' onClick='getCadreId(this)' name='cadreResult_check' value='"+id+"_"+mobile+"_"+firstName+"'>";
				
  };

  YAHOO.widget.DataTable.image = function(elLiner, oRecord, oColumn, oData) 
  {
	elLiner.innerHTML ="<img height='85px' width='85px' src='images/cadre_images/"+oData+"'/>";
  };
  
  var CadreSearchResultColumnDefs = [ 
		    	            {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							{key:"firstName", label: "Name",sortable: true, formatter:YAHOO.widget.DataTable.viewDetails} ,
							{key:"image", label: "Image",formatter:YAHOO.widget.DataTable.image}, 
		    	            {key:"mobile", label: "Mobile", sortable: true}, 
		    	           	{key:"strCadreLevel", label: "Cadre Level", sortable: true},
							{key:"email", label: "Address"},
		    				{key:"memberType", label: "Cadre Type",sortable:true},
							{key:"educationStr", label: "Education",sortable:true},
		    				{key:"professionStr", label: "Occupation",sortable:true},
							{key:"casteCategoryStr", label: "Caste Category",sortable:true},
							{key:"edit", label: "Edit",formatter:YAHOO.widget.DataTable.edit},
							{key:"Delete", label: "Delete",formatter:YAHOO.widget.DataTable.deleteCadre}
		    	        ]; 
	var CadreSearchResultDataSource = new YAHOO.util.DataSource("getCadreDetailsForSMSAjaxAction.action?"+rparam+"&windowTask=Search&"); 
	CadreSearchResultDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON; 
	
	CadreSearchResultDataSource.responseSchema = { 
            resultsList:"cadreInfo", 
		fields: [
				{key:"firstName"},
				"lastName","image","mobile", "strCadreLevel","memberType",
				"educationStr","professionStr","casteCategoryStr","cadreId","email","pincode"
				],
		metaFields: {
			totalRecords: "totalSearchCount" // Access to value in the server response
		}         
        };


    var myConfigs = {
			        initialRequest: "sort=firstName&dir=asc&startIndex=0&results=20", // Initial request for first page of data
			        dynamicData: true, // Enables dynamic server-driven data
			        sortedBy : {key:"firstName", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
			        paginator: new YAHOO.widget.Paginator({ rowsPerPage:20 }) // Enables pagination 
		};

		var CadreSearchResultDataTable = new YAHOO.widget.DataTable(searchResult1, CadreSearchResultColumnDefs,CadreSearchResultDataSource, myConfigs);

		CadreSearchResultDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) 
		{		
		        oPayload.totalRecords = oResponse.meta.totalRecords;
		        return oPayload;
		}
		//function calling to build Result
		return {
			oDS: CadreSearchResultDataSource,
			oDT: CadreSearchResultDataTable,
    	};
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
	callAjaxForRegionSelect(jsObj,url);
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
	callAjaxForRegionSelect(jsObj,url);
}

function getAllConstituenciesInStateByType(electionType, stateId, element)
{
	clearOptionsListForSelectElmtId(element);
	showBusyImgWithId(element);
	
	var jsObj=
	{				
			electionTypeId: electionType,
			stateId: stateId,
			task: "getConstituencies",
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllConstituenciesInState.action?"+rparam;						
callAjaxForRegionSelect(jsObj,url);
}

function getAllParliamentConstInCountry(element)
{
	var jsObj=
	{				
			task: "getAllParliamentConstituencies",
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllParliamentConstInCountry.action?"+rparam;						
callAjaxForRegionSelect(jsObj,url);
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
	callAjaxForRegionSelect(jsObj,url);
}

function getDistrictsComboBoxForAState(value,elmtId)
{
	showBusyImgWithId(elmtId);
	clearOptionsListForSelectElmtId(elmtId);
	clearOptionsListForSelectElmtId("districtSelectBox");
	createSelectOptionsForSelectConstituencyElmtId("districtSelectBox");
	clearOptionsListForSelectElmtId("constituencySelectBox");
	createSelectOptionsForSelectConstituencyElmtId("constituencySelectBox");
	var jsObj=
		{				
				stateId:value,
				elmtId:elmtId,
				task:"findDistrictsForAState",
				taskType:"getRegions"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDistrictsForAStateAjaxAction.action?"+rparam;						
	callAjaxForRegionSelect(jsObj,url);
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
	callAjaxForRegionSelect(jsObj,url);
}

function buildCadresDatatable(results,divId)
{
	var jsArray = new Array();

	for(var i in results)
	{
		var obj={
					checkBox:'<input type="checkbox" name="cadreResult_check" value="'+results[i].cadreID+'_'+results[i].mobile+'_'+results[i].firstName+'">',
					fname:results[i].firstName+' '+results[i].middleName+' '+results[i].lastName,
					mobile:results[i].mobile,
					cadreLevel: results[i].strCadreLevel+'-'+results[i].strCadreLevelValue,
					address: results[i].villageName+', '+results[i].mandalName+', '+results[i].districtName,
					memberType: results[i].memberType,
					education: results[i].educationStr,
					occupation: results[i].professionStr,						
					casteCategory: results[i].casteCategoryStr,
					moreDetails:'<a href="javascript:{}" onclick="getCadreInfo(\''+results[i].cadreID+'\')">More Details</a>',
					update:'<A href="javascript:{}" onclick="openRegistrationForm('+results[i].cadreID+')"><img src="images/icons/edit.png" style="text-decoration:none;border:0px;"></A>'
					
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
			key : "cadreLevel"
		}, {
			key : "address"
		}, {
			key : "mobile",parser:"number"
		} , {
			key : "memberType"
		} , {
			key : "education"
		} , {
			key : "occupation"
		} , {
			key : "casteCategory"
		},	{
			key : "moreDetails"
		} , {
			key : "update"
		} 
		]
	};

	var resultsColumnDefs = [ {
		key : "checkBox",
		label : "Select",
		sortable : false
	}, {
		key : "fname",
		label : "Name",
		sortable : true
	}, {
		key : "mobile",
		label : "Mobile",
		sortable : true
	},  {
		key : "cadreLevel",
		label : "Cadre Level",
		sortable : true
	}, {
		key : "address",
		label : "Address",
		sortable : true
	},	{
		key : "memberType",
		label : "Cadre Type",
		sortable : true
	}, {
		key : "education",
		label : "Education",
		sortable : true
	},  {
		key : "occupation",
		label : "Occupation",
		sortable : true
	},  {
		key : "casteCategory",
		label : "Caste Category",
		sortable : true
	},  {
		key : "moreDetails",
		label : "More Details",
		sortable : false
	},  {
		key : "update",
		label : "Edit",
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

	if(results != null && results.resultStatus != null)
	{
		if(results.resultStatus.exceptionMsg != null)
		{
			bodySMSElmt.innerHTML = '<div style="color:#C0566F;font-size:12px;">'+errorMsglabel+'</div>';
			return;
		}
	}
	
	bodySMSElmt.innerHTML = 'SMS successfullty sent to '+results.totalSmsSent+' cadres';
	bodySMSElmt.innerHTML += '<a href="javascript:{}" onclick="showSentSMSCadres()"> View cadres</a>';

	//buildCadresDatatable(results.smsSentCadreInfo,"searchResult");
}

function showSentSMSCadres()
{
	var elmt = document.getElementById("searchResult");

	if(elmt)
		elmt.style.display = 'block';

	getCadresResults('search');
}


/*function showCadreSearchResults(jsObj,results)
{
	var headElmt = document.getElementById("searchResultsDiv_head");
	var bodySearchElmt = document.getElementById("searchResult");
	var footerElmt = document.getElementById("searchResultsDiv_footer");
	var resultsCountEl = document.getElementById("resultsCount");
	if(!headElmt || !bodySearchElmt || !footerElmt)
		return;
	
	bodySearchElmt.style.display = 'block';
	resultsCountEl.innerHTML ='';
	headElmt.innerHTML = 'Search Results';
		
	if(!results || results.length == 0)
	{
		bodySearchElmt.innerHTML = '<div style="color:#C0566F;font-size:12px;">No Search results found.</div>';
		
		return;
	} else 
		resultsCountEl.innerHTML = '<span>'+results.length+'</span> cadres found with this selection criteria';
	
	if(results[0] != null && results[0].resultStatus != null)
	{
		if(results[0].resultStatus.exceptionMsg != null)
		{
			bodySearchElmt.innerHTML = '<div style="color:#C0566F;font-size:12px;">'+errorMsglabel+'</div>';
			return;
		}
	}


	buildCadresDatatable(results,"searchResult");
	

	var fStr = '';
	fStr += '<span><input type="button" class="btnClass" onclick="selectCheckBox()" value="Select All"/></span>';
	fStr += '<span><input type="button" class="btnClass" onclick="deSelectCheckBox()" value="DeSelect All"/></span>';
	fStr += '<span><input type="button" class="btnClass" onclick="sendCadreSMS()" value="Send SMS"/></span>';
	fStr += '<span id="smsStatusTextSpan"></span>';

	footerElmt.innerHTML = fStr;
}*/

function selectCheckBox()
{
	var elements = document.getElementsByName("cadreResult_check");
	
	for(var i in elements)
	{
		elements[i].checked = true;
	}	
}

function deSelectCheckBox()
{
	var elements = document.getElementsByName("cadreResult_check");
	
	for(var i in elements)
	{
		elements[i].checked = false;
	}
}

function sendCadreSMS()
{
	var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("smsStatusTextSpan");
	selectedCadresArray = [];


	if(!elements || !errorSpanElmt)
		return;

	

	for(var i=0; i<elements.length; i++)
	{
		if(elements[i].checked == false)
			continue;
		
		var cId  = elements[i].value.substring(0,elements[i].value.indexOf('_'));
		var cMobile = elements[i].value.substring(elements[i].value.indexOf('_')+1,elements[i].value.lastIndexOf('_'));
		var cName = elements[i].value.substring(elements[i].value.lastIndexOf('_')+1,elements[i].value.length);
		var obj = {
					cadreId:cId,
					cadreMobile:cMobile,
					cadreName:cName
		          };
		if(elements[i].checked == true)
			selectedCadresArray.push(obj);
	}
	
	
	if(selectedCadresArray.length == 0)
	{
		errorSpanElmt.innerHTML = 'Atleast One cadre need to selected to send SMS';
		return;
	}
	else
		errorSpanElmt.innerHTML = '';
	
	if(CLICKTYPE == "Sms")
		handleSubmit();
	else
		buildSmsTextDialog();
	

}

function buildSmsTextDialog()
{
	var dialogElmt = document.getElementById("smsDialogBox");

	if(!dialogElmt)
		return;

	var str = '';
	str += '<div id="dialog1" class="yui-pe-content">';
	str += '	<div class="hd">Please enter SMS Text</div>';
	str += '	<div class="bd">';
	str += '	<table>';
	str += '	<tr>';
	str += '	<td colspan="2"><span id="smsPopupSpan"></span></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '	<th valign="top"> SMS Text : </th>';
	str += '	<td valign="top">';
	str+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText(\'smsTextArea\',\'maxcount\',200)" ></textarea></div> ';
	str+='<div id="limitDiv">';
	str+='<table><tr>';
	str+='<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
	str+='<td style="width:50%;"><div>Should not exceed 200 chars</div></td>';
	str+='</tr></table>';
	str+='</div>';	
	str += '	</td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '	<th valign="top"> Include Cadre Name : </th>';
	str += '	<td valign="top"><input type="radio" id="include_user_name" onclick="javascript:{SMSINCLUDECADRENAME = this.value;}" name="include_user_name" value="YES" /> Yes';
	str += '	<input type="radio" id="no_user_name" onclick="javascript:{SMSINCLUDECADRENAME = this.value;}" name="include_user_name" value="NO" checked="checked"/> No';	
	str += '	</td>';
	str += '	</tr>';
	str += '	<table>';
	str += '	</div>';
	str += '</div>';
	
	if(dialogElmt)
		dialogElmt.innerHTML = str;

	smsDialog = new YAHOO.widget.Dialog("dialog1",  
	            { width : "500px", 
	              fixedcenter : true, 
	              visible : true,  
	              constraintoviewport : true, 
	              buttons : [ { text:"Send SMS", handler:handleSubmit, isDefault:true }, 
	                          { text:"Cancel", handler:handleCancel } ] 
	             } ); 

	smsDialog.render();
}

function handleSubmit()
{	
	var elmt;
	
	if(CLICKTYPE == "Sms")
		elmt = document.getElementById("errorMsgDiv");	
	else
		elmt = document.getElementById("smsPopupSpan");
		

	var textAreaElmt = document.getElementById("smsTextArea");
	if(textAreaElmt)
		var txtElmtValue = textAreaElmt.value;
	if(txtElmtValue == '')
	{
		elmt.innerHTML = '<font color="red">Enter Text Message...</font>';
		return;
	}
	else
		elmt.innerHTML = '';

	var jsObj=
		{						
				txtAreaValue:txtElmtValue,
				includeName:SMSINCLUDECADRENAME,
				cadreIds:selectedCadresArray,
				task:"sendSMSForCadreIds"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendSMSForSelectedCadresCadresAction.action?"+rparam;						
	callAjaxForRegionSelect(jsObj,url);
}

function handleCancel()
{
	smsDialog.hide();
}


function getCadreInfo(cadreId)
{
	var urlStr = "getCadreInfoAction.action?windowTask=cadreInfoPopup&cadreId="+cadreId;
	var browser2 = window.open(urlStr,"cadreInfoPopup","scrollbars=yes,height=500,width=600,left=200,top=200");	
	browser2.focus();
}

function showSMSStatus(jsObj,results)
{
	var elmt;
	if(CLICKTYPE == "Sms")
	{
		elmt = document.getElementById("smsResult");
	}
	else if(CLICKTYPE == "Search")
	{
		elmt = document.getElementById("smsPopupSpan");
	}
	
	if(results != null && results.resultStatus != null)
	{
		if(results.resultStatus.exceptionMsg != null)
		{
			elmt.innerHTML = '<div style="color:#C0566F;font-size:12px;">'+errorMsglabel+'</div>';
			return;
		}
	}

	if(elmt)
	{
		elmt.innerHTML = '<font color="green"><blink>SMS sent successfull to '+results.totalSmsSent+' Cadre</blink></font>';
		elmt.style.display = '';
	}

	if(CLICKTYPE == "Search")
		var t=setTimeout("smsDialog.hide();",5000);	
}

function callAjaxForRegionSelect(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.taskType == "getRegions")
							{
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
								hideBusyImgWithId(jsObj.elmtId);
							}
							else if(jsObj.task == "cadreSearch" && jsObj.taskType == "search")
							{
								//showCadreSearchResults(jsObj,myResults);
								showCadreSearchResults(myResults.totalSearchCount,jsObj);
							}
							else if(jsObj.task == "cadreSearch" && jsObj.taskType == "sms")
							{
								showSMSResults(jsObj,myResults);
							}
							else if(jsObj.task == "getUserLocation")
								buildRegionsSelectBoxes(jsObj,myResults);
							else if(jsObj.task == "sendSMSForCadreIds")
							{
								selectedCadresArray = [];
								showSMSStatus(jsObj,myResults);
							}
							else if(jsObj.task == "getConstituencies")
							{
								//clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
								hideBusyImgWithId(jsObj.elmtId);
							}else if(jsObj.task == "getAllParliamentConstituencies")
							{
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
							}
							

						}
						catch(e)
						{   
							//alert("Invalid JSON result" + e);  
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function showCadreSearchResults(searchCount,jsObj)
 {
	var totalSearchCount = searchCount;
	//For refreshing the response in parent window
	if(jsObj.extra=="one"){
	var headElmt = window.opener.document.getElementById("searchResultsDiv_head");
	var bodySearchElmt = window.opener.document.getElementById("searchResult");
	var footerElmt =  window.opener.document.getElementById("searchResultsDiv_footer");
	var resultsCountEl = window.opener.document.getElementById("resultsCount");
	}else{
	var headElmt = document.getElementById("searchResultsDiv_head");
	var bodySearchElmt = document.getElementById("searchResult");
	var footerElmt =  document.getElementById("searchResultsDiv_footer");
	var resultsCountEl = document.getElementById("resultsCount");
	}
	/*if(!headElmt || !bodySearchElmt || !footerElmt)
		return;*/
	
	bodySearchElmt.style.display = 'block';
	resultsCountEl.innerHTML ='';
	headElmt.innerHTML = 'Search Results';
		
	if(totalSearchCount == 0)
	{
		bodySearchElmt.innerHTML = '<div style="color:#C0566F;font-size:12px;">No Search results found.</div>';
		footerElmt.style.display = 'none';
		return;
		
	} 
	else 
	{
	resultsCountEl.innerHTML = '<span>'+totalSearchCount+'</span> cadres found with this selection criteria';

	var fStr = '';
	fStr += '<span><input type="button" class="btnClass" onclick="selectCheckBox()" value="Select All"/></span>';
	fStr += '<span><input type="button" class="btnClass" onclick="deSelectCheckBox()" value="DeSelect All"/></span>';
	fStr += '<span><input type="button" class="btnClass" onclick="sendCadreSMS()" value="Send SMS"/></span><BR><BR><BR>';
	fStr += '<span id="smsStatusTextSpan"></span>';
	footerElmt.innerHTML = fStr;
	footerElmt.style.display = '';
	}
	
	if(isProblemAdding != null && isProblemAdding == true)
	{
		footerElmt.style.display = 'none';
		if(jsObj.extra=="one"){
		var cadreProbDivEle =  window.opener.document.getElementById("cadreProblemSelectDiv");
		}else{
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		}
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setCadreIdToProblem()" value="Add Selected cadre"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
	if(isProblemAdding != null && isProblemAdding == false)
	{
		footerElmt.style.display = 'none';
		if(jsObj.extra=="one"){
		var cadreProbDivEle = window.opener.document.getElementById("cadreProblemSelectDiv");
		}else{
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		}
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setOrganizers()" value="Add Organizers"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
	if(voterId !== ''){
	   footerElmt.style.display = 'none';
		if(jsObj.extra=="one"){
		var cadreProbDivEle = window.opener.document.getElementById("cadreProblemSelectDiv");
		}else{
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		}
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setCadreToVoter()" value="Add Selected cadre"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
}

function displayRegionsSelect(val,regTask)
{
	var id = val.substring(val.indexOf('_')+1,val.length);
	var name = val.substring(0,val.indexOf('_'));
	REPORTLEVEL = id;

	var stateSelectElmt = document.getElementById("stateSelectBox");
	var districtSelectElmt = document.getElementById("districtSelectBox");
	var constituencySelectElmt = document.getElementById("constituencySelectBox");
	var mandalSelectElmt = document.getElementById("mandalSelectBox");
	var villageSelectElmt = document.getElementById("villageSelectBox");
	
	stateSelectElmt.disabled=true;
	districtSelectElmt.disabled=true;	
	constituencySelectElmt.disabled=true;	
	mandalSelectElmt.disabled=true;	
	villageSelectElmt.disabled=true;	
	
	if(name == "State")
	{
		stateSelectElmt.disabled=false;
	}
	if(name == "District")
	{
		stateSelectElmt.disabled=false;
		districtSelectElmt.disabled=false;			
	}
	if(name == "Constituency")
	{
		stateSelectElmt.disabled=false;
		districtSelectElmt.disabled=false;	
		constituencySelectElmt.disabled=false;	
	}
	if(name == "Mandal")
	{
		stateSelectElmt.disabled=false;
		districtSelectElmt.disabled=false;
		constituencySelectElmt.disabled=false;	
		mandalSelectElmt.disabled=false;	
	}
	if(name == "Village")
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
	
	var firstValue = '';
	regTask = "cadreSearch";
	var str='';
		/*for(var i in results.regions)
		{setCadreValue(this.options[this.selectedIndex].value,'onChange')"
			if(i == 0)
			{
				str+='<input type="radio" checked="checked" name="region_type_radio" value="'+results.regions[i].name+'_'+results.regions[i].id+'" onclick="displayRegionsSelect(this.value,\''+regTask+'\')" /> '+results.regions[i].name+'';
				REPORTLEVEL = results.regions[i].id;
				firstValue = results.regions[i].name+'_'+results.regions[i].id;				
			}
			else
				str+='<input type="radio" name="region_type_radio" value="'+results.regions[i].name+'_'+results.regions[i].id+'" onclick="displayRegionsSelect(this.value,\''+regTask+'\')" /> '+results.regions[i].name+'';
		}*/
	for(var i in results.regions)
	{
		if(i == 0)
		{
			str+='<input type="radio" checked="checked" name="region_type_radio" id="'+results.regions[i].id+'" value="'+results.regions[i].name+'" onclick="populateLocations(this.id,\'onChange\')" /> '+results.regions[i].name+'';
			REPORTLEVEL = results.regions[i].id;
			firstValue = results.regions[i].name+'_'+results.regions[i].id;				
		}
	    else if(results.regions[i].name == 'MANDAL/TEHSIL'){
		  str+='<input type="radio" name="region_type_radio" id="'+results.regions[i].id+'" value="MANDAL" onclick="populateLocations(this.id,\'onChange\')"/>'+results.regions[i].name+'';
		}
		else if(results.regions[i].name == 'ASSEMBLY CONSTITUENCY'){
		  str+='<input type="radio" name="region_type_radio" id="'+results.regions[i].id+'" value="CONSTITUENCY" onclick="populateLocations(this.id,\'onChange\')"/>'+results.regions[i].name+'';
		}
		else if(i == 10)
			str+='<input type="radio" name="region_type_radio" id="'+results.regions[i].id+'" value="'+results.regions[i].name+'" onclick="populateLocations(this.id,\'onChange\'),getLocationHierarchies(results.states[0].id,\'districtsInState\',\'cadreSearch\',\'districtField_s\',\'cadreSearch\', \'null\')"/>'+results.regions[i].name+'';

		else
			str+='<input type="radio" name="region_type_radio" id="'+results.regions[i].id+'" value="'+results.regions[i].name+'" onclick="populateLocations(this.id,\'onChange\')"/>'+results.regions[i].name+'';
	}
	if(radioElmt)
		radioElmt.innerHTML=str;
	
	//Filling up select box...

	var regionStr='';
	

	regionStr+='<select id="stateSelectBox" class="regionsSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'districtSelectBox\')">';
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

		
	regionStr+='<select id="districtSelectBox" class="regionsSelectBox" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,\'constituencySelectBox\')">';
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
	if(results.parliamentConstituencys != "" && results.parliamentConstituencys.length > 0 && document.getElementById("parliamentConstituencyField_s") != null)
	{
	     
		for(var consti in results.parliamentConstituencys)
		{
		     var elmt = document.getElementById("parliamentConstituencyField_s");
		     var option = document.createElement('option');
		     option.value=results.parliamentConstituencys[consti].id;
		     option.text=results.parliamentConstituencys[consti].name;
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
		
	regionStr+='<select id="constituencySelectBox" class="regionsSelectBox" onchange="getMandalsComboBoxForAConstituency(this.options[this.selectedIndex].value,\'mandalSelectBox\')">';
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


   	regionStr+='<select id="mandalSelectBox" class="regionsSelectBox" onchange="getVillagesComboBoxForAMandal(this.options[this.selectedIndex].value,\'villageSelectBox\')">';
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

	regionStr+='<select id="villageSelectBox" class="regionsSelectBox" onchange="javascript:{REPORTLOCATIONVALUE = this.options[this.selectedIndex].value}">';
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

	//if(selectElmt)
		//selectElmt.innerHTML=regionStr;

	//displayRegionsSelect(firstValue,regTask);
	
	if(results.parliamentConstituencies != null)
	{
		for(var parliamentConstituencyList in results.parliamentConstituencies)
		{
			regionStr+='<option value="'+results.parliamentConstituencies[parliamentConstituencyList].id+'">'+results.parliamentConstituencies[parliamentConstituencyList].name+'</option>';
		}
	}

	populateLocations(results.regions[0].id,'onLoad');

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
	callAjaxForRegionSelect(jsObj,url);

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
	//callAjaxForRegionSelect(jsObj,url);
	createOptionsForId("socialStatus_resevation",socialStatus);
	createOptionsForId("socialStatus_education",eduStatus);
	createOptionsForId("socialStatus_occupation",occupations);
}

function openRegistrationForm(cadreId)
{
	var task = "update_existing";
	var urlStr = "cadreRegisterPageAction.action?cadreId="+cadreId+"&windowTask="+task;
	var browser2 = window.open(urlStr,"cadreRegistration","scrollbars=yes,left=200,top=200");	
	browser2.focus();				
}

function deleteCadre(cadreId,place)
{
	var ask = confirm("Do You want to delete");
	if (ask ==  true)
	  {
		var jsObj = {
				id: cadreId,
				task: "deleteCadre",
				place:place
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
			var url = "deleteCadreInfoAjaxAction.action?"+rparam; 
			deleteCadreAjax(rparam,jsObj,url);	  	
	  }
	else
	  {
	  		return;	
	  }
}

function deleteCadreAjax(param,jsObj,url){
		var myResults;
		var callback = {			
 		               success : function( o ) 
						  {
							try {												
									if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
									
									if(jsObj.place == "search")
									{
										showConfirm(myResults);
										getCadresResults("search");
									}
								}
							catch (e)
								{   
								   	//alert("Invalid JSON result" + e);   
								}	  
				              },
				               scope : this,
				               failure : function( o ) {
				                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
				                         }
				               };

				YAHOO.util.Connect.asyncRequest('GET', url, callback);
		}
	
function enableSenderName()
{
	var elmt = document.getElementById("senderNameText");
	if(elmt.disabled == true)
		elmt.disabled = false;
	else
		elmt.disabled = true;
}

function showConfirmation(result)
{
	if(result != null && result>0)
	{
		alert("Cadre Details Successfully Deleted!");
		cadreDetailsPanel.hide();
		window.location.reload(true);
	}
		
}

function showConfirm(result)
{
	if(result != null && result>0)
	{
		alert("Cadre Details Successfully Deleted!");
		return;
	}
		
}
