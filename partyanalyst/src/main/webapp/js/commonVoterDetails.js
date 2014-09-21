function getUserCategories(){

	var jsObj=
			{
			 task:"getUserCategories"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getUserCategoriesAction.action?"+rparam;						
		callAjax(jsObj,url);

}


function buildVoterToSelectedType(result,jsObj)
{
	if(jsObj.type == "influencingPeople")
	{
		if(result.resultCode == 1)
		{
			addInfluencingPeople(jsObj.voterId,jsObj.name);
		}
		else 
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a Influencing People</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
	if(jsObj.type == "cadre")
	{
		if(result.resultCode == 1)
		{
			openRegistrationForm(jsObj.voterId,jsObj.name);
		}
		else
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a Cadre</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
	if(jsObj.type == "candidate")
	{
		if(result.resultCode == 1)
		{
		addToPolitician(jsObj.voterId,jsObj.name);
		}
		else
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a politician</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
}

function buildCategories(results){

    var str='';

	for(var i in results){
      
       str+='<label style="float:left;margin:3px;"><input type="checkbox" style="margin:0px 7px 4px 0px;" class="attributeTypeClassIni1" value="'+results[i].id+','+results[i].name+'"/>'+results[i].name+'</label>';
	}

	$('#impFamilySelectedDetails1').html(str);

}

function checkForVoter(voterId,type,name)
{
	var jsObj = {
			voterId:voterId,
			type : type,
			name : name,
			task:"ckeckForVoterId"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "ckeckForVoterIdAction.action?"+rparam;
	callAjax(jsObj, url);
}


function addInfluencingPeople(voterId,name)
{
	var type='edit';
	var urlStr = "influencingPeopleAction.action?windowTask="+type+"&voterId="+voterId+"&name="+name;
	var browser2 = window.open(urlStr,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	browser2.focus();
}


function openRegistrationForm(voterId,name)
{
	var task = "update_existing";
	var urlStr = "cadreRegisterPageAction.action?voterId="+voterId+"&windowTask="+task+"&name="+name;
	var updateBrowser = window.open(urlStr,"cadreRegistration","scrollbars=yes,left=200,top=200");	
	updateBrowser.focus();				
}
function addToPolitician(voterId,name)
{
	var urlStr = "assigningCandidatesToVoterAction.action?voterId="+voterId+"&name="+name;
	var browser2 = window.open(urlStr,"assigningCandidatesToVoterAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	browser2.focus();
}
  function openCadreSearchWindow(clickedId){
  
	var cadreWindow = window.open("cadreSearchAction.action?windowTask=Search&voterId="+clickedId,"cadreSearch","scrollbars=yes,height=600,width=750,left=200,top=200");
	cadreWindow.focus();
}
function getInfluencePeopleOfAnUser(voterId){

  showInfluencePeopleDialog(voterId)
}



function showInfluencePeopleDialog(voterId){

	$('#searchResultsDiv').html('');
	$('#influencePeopleInnerDiv').show();
	$('#totalCountId').hide();
	$('#searchResultsDiv').hide();
	var str='';
    str+='<form class="form-horizontal">';
   str+='<div class="control-group">';
	 str+='<span><label class="control-label" style="font-size: 15px;margin-left: 5px;">Enter Name :</label></span><div class="controls"><input id="nameId" type="text" name="name" style="margin-left: 49px;width: 169px;"/></div><span id="nameErrMsg" class="locationErrorMsg" style="float: right;margin-right: 90px;margin-top: -24px;"></span></div>';
	 str+='<div class="control-group"><span><label class="control-label" style="font-size: 15px;margin-left: 10px;">Father Name :</label></span><div class="controls"><input id="fatherNameId" type="text" name="name" style="margin-left: 49px;width: 169px;"/></div></div>';
    
	// str+='<h5>Select Scope</h5>';
    str+='<div class="control-group">';
	  str+='<span><label class="control-label" style="font-size: 15px;margin-left: 2px;">Select Scope</label></span>';
	  str+='<div class="controls">';
	  str+='<select id="scopeId" onChange="showLocationsDiv();" style="font-size:14px;font-family:helvetica;width:185px;margin-left: 50px;">';
	        str+='<option value="0" >Select</option>';
			str+='<option value="2">STATE</option>';
			str+='<option value="3">DISTRICT</option>';
			str+='<option value="4">CONSTITUENCY</option>';
			str+='<option value="5">MANDAL</option>';
			str+='<option value="6">VILLAGE</option>';
			str+='<option value="7">MUNCIPAL-CORP-GMC</option>';
			str+='<option value="8">WARD</option>';
			str+='<option value="9">BOOTH</option>';
	  str+='</select>';
	  str+='</div>';
    str+='</div>';

    str+='<div id="locationsDiv" style="display:none;padding:10px;margin:5px;width:525px;">';
	  str+='<div  id="regionstitleDiv" style="display:none;margin-left: 60px;"><h5>Select region</h5></div>';

	  str+='<div id="stateSelect" style="display:none;margin-left: 39px;margin-top: 14px" class="locationDivClass "><div class="control-group"><label class="control-label" style="font-size: 12px;width: 59px;">STATE</label><div class="controls"><select id="stateSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="1">ANDHRA PRADESH</option></select></div></div></div>';

	  str+='<div id="districtSelect" style="display:none;margin-left: 39px;" class="locationDivClass control-group"><label class="control-label" style="font-size: 12px;width: 78px;">DISTRICT</label><div class="controls"><select id="districtSelectId" onChange="getConstituenciesInDistrict();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="districtErrMsg" style="float: right; margin-right: -32px; margin-top: -23px;" class="locationErrorMsg" ></span></div>';

	  str+='<div id="constituencySelect" style="display:none;margin-left: 39px" class="locationDivClass control-group" ><label class="control-label" style="font-size: 12px; width: 114px;">CONSTITUENCY</label><div class="controls"><select id="constituencySelectId" onChange="getMandalsOrMuncipalities();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="constituencyErrMsg" class="locationErrorMsg" style="float: right; margin-right: -73px; margin-top: -21px;"></span></div>';

	  str+='<div id="mandalSelect"  class="locationDivClass control-group" style="display:none;margin-left: 39px"><label class="control-label" style="font-size: 12px; width: 141px;">TEHSIL/MUNCIPALITY</label><div class="controls"><select id="mandalSelectId" onChange="getHamletsOrWards();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="tehsilOrMuncipalityErrMsg" class="locationErrorMsg" style="float: right; margin-right: -62px; margin-top: -23px;"></span></div>';

	  str+='<div id="wardSelect"style="display:none;margin-left: 39px"  class="locationDivClass control-group" ><label class="control-label" style="font-size: 12px;">VILLAGE/WARD/DIVISION</label><div class="controls"><select id="wardSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="villageOrWardErrMsg" class="locationErrorMsg" style="float: right; margin-right: -20px; margin-top: -21px;"></span></div>';

	  str+='<div id="boothSelect" class="locationDivClass control-group" style="display:none;margin-left: 39px"><label class="control-label" style="font-size: 12px; width: 59px;">BOOTH</label><div class="controls"><select id="boothSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="boothErrMsg" class="locationErrorMsg" style="float: right; margin-right: -28px; margin-top: -24px;"></span></div>';

	str+='</div>';
	  str+='</form >';
	str+='<div><a class="btn btn-primary" id="searchButtonId" style="float:left; margin-left: 264px;margin-top: -25px;display:none; color: white;" href="javaScript:{callAjaxToSearchInfluencingPeople('+voterId+');}">Search</a></div>';
	str+='<div id="ajaxImageDiv1" style="display:none;"><img style="margin-left:244px;" src="images/icons/ajaxImg.gif"></div>';


	$('#influencePeopleInnerDiv').html(str);
	
	getDistrictsInAState();

	$('#influencePeopleOuterDiv').dialog({ 
	                            title:'Search Influence People',
	                            height: 'auto',
								width: 750,
								height:750,
								closeOnEscape: false,
								show: "blind",
								hide: "explode",
								modal: true,
	                             buttons: {
							   "Close":function() {$(this).dialog("close")}
								   }	

     });


	 

}




function getVoterModifivationReport()
{
	//var districtId = ().val();
	
	$.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });
	var jObj=
	{
	constituencyId : $('#districtList option:selected').val(),
	locationType   : "constituency",
	locationValue  : $('#districtList option:selected').val(),
	publicationId  : $('#presentPublicationId').val(),
	type           : "district",
	task           : "createPdfs"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "createPdfAction.action?"+rparam;

	callAjax(jObj,url);
}

function constituencyOptions(type){
	
	var electionIdsArr = new Array();
		$("#electionYearsId option").each(function() {
		var val = $(this).val();
		electionIdsArr.push(val);
		});
	var largest = Math.max.apply(Math, electionIdsArr);
		
	var stateId=1;
	var electionType=$("input:radio[name=electionType]:checked").val();
	var electionId=$("#electionYearsId option:selected").val();
	
	if($("#electionYearsId").val() == 0)
		electionId = largest;

	var jsObj =
		{  	
			stateId:stateId,
			electionType:electionType,
			electionId:electionId,
            type:type,
			task:'forConstituencies'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "optionsForBoothAction.action?"+rparam;
		callAjax(jsObj, url);
}


function buildConstituencies(myResult){
	if(myResult == null || myResult.length == 0)
		return;
	
	$('#errorDiv').html("");
	
	var electionYearsElmt = document.getElementById("constiId");
	electionYearsElmt.options.length=0;

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select Constituency";
	electionYearsElmt.add(option);
	
	
	for(var i in myResult)
	{
		option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionYearsElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionYearsElmt.add(option); // IE only
		}
	}
	
}


function getImpFamilyDetails()
{
	var constituencyId = $("#districtListForImp").val();
	var publicationId = $("#prevPublicationIdForImp").val();
	var fromValue =  $.trim($("#fromAgeRange").val());
	var toValue =  $.trim($("#toAgeRange").val());
	var maxr = null;
	/* //var str ='<font color="red">';
	var flag = true;
	alert(constituencyId);
	alert(publicationId);alert(fromValue);alert(toValue);
	if(constituencyId == 0)
	{
		str+='Select Constituency<br/>';
		flag =false;
	}
	if(publicationId == 0)
	{
		str+='Select Publication<br/>';
		flag =false;
	}
	if(fromValue == "" || isNaN(fromValue))
	{
		str+='Enter From Value Number<br/>';
		flag =false;
	}
	else if(isNaN(toValue))
	{
		str+='Enter To Value Number<br/>';
		flag =false;
	}
	if(toValue == "")
	{
     toValue = 0;
	}
	else
	{
	 if(toValue < fromValue)
	{
        str+='From Value must be greter than To value<br/>';
		flag =false;
	}
	}
	alert(flag); */
	/* if(flag == false)
	{
			//errorDiv.html(str);
			return;
	}
	
	else
	{ */
		$.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });
		var jsObj= 
		{	
				constituencyId:constituencyId,
				publicationId:publicationId,
				minVal:fromValue,
				maxVal:toValue,
				startIndex:0,
				results:200000,
				type : "constituency",
				output : "Excel",
				task:"getFamilyDetails"		
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getFamilyDetailsAction.action?"+rparam;

		callAjax(jsObj,url);
	//}
}




var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()



