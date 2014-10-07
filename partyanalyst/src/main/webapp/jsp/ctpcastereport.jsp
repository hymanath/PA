<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voters Search</title>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
 <script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
 
<style type="text/css">

.labelClass{
float:left;
margin:3px 7px 0px 0px;
}
.titleHeading{
    color: steelblue;
    font-family: verdana;
    font-size: 13px;
    font-weight: bold;
    margin-bottom: 20px;
	font-size: 16px;
	text-align:center;
	margin-top: 20px;
 }
 .selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-family: verdana;
	 font-size: 12px;
	 margin-left:auto;
	 margin-right:auto;
	 font-weight:bold;
	 color:#333333;
 }
 
  fieldset{
    border: 3px solid #CFD6DF;
    margin-bottom: 10px;
    padding: 10px;
}
body {
    color: #000000;
}

 #topButtons,#bottomButtons{
   margin-top:10px;
 }

   .requiredFont{
	color:red;
	font-size:13px;
}
#mainFieldset{
 margin:20px;
}

input[type="text"]{
		border-radius: 4px 4px 4px 4px;
		color: #000000;
		display: inline-block;
		font-size: 13px;
		line-height: 18px;
		padding: 4px;
	}
		
		
</style>

<script type="text/javascript">
var presentPublication = 11;

 
function getDistrictsInAState(){

	var jsObj=
			{
				id:1,
				task:"districtsInState"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);
}

function getConstituenciesInDistrict(){

	var jsObj=
			{
				id:$('#districtSelectId').val(),
				task:"constituenciesInDistrict"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);
}


function getMandalsOrMuncipalities1(areaType){

	var jsObj=
			{
				id:$('#constituencySelectId').val(),
				task:"subRegionsInConstituency",
				areaType:areaType
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}

function getHamletsOrRegions(){

	var jsObj=
			{
				id:$('#mandalSelectId').val(),
				task:"hamletsOrWardsInRegion"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}


function getBoothsInTehsilOrMunicipality(){

	var jsObj=
			{
				id:$('#mandalSelectId').val(),
				constId:$('#constituencySelectId').val(),
				task:"boothsInTehsilOrMunicipality"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}

function callAjaxForLocations(jsObj,url)
{
			
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								if(jsObj.task == "districtsInState")
								{
									buildDistricts(myResults);										
								}
								else if(jsObj.task == "constituenciesInDistrict")
								{
                                     buildConstituencies(myResults);
								}
								else if(jsObj.task == "subRegionsInConstituency")
								{
									buildMandalsOrMuncipalities(myResults);
								}
								else if(jsObj.task == "hamletsOrWardsInRegion")
								{
									buildHamletsOrWardsInRegion(myResults);

								}
								else if(jsObj.task == "boothsInTehsilOrMunicipality")
								{
									buildBooths(myResults);
								}
							}catch (e) {
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}


function buildDistricts(results)
{

	$('#districtSelectId').find('option').remove();
	$('#districtSelectId').append('<option value="0">Select</option>');

	for(var i=0;i<results.length;i++)
		$('#districtSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}

function buildConstituencies(results)
{
  $('#constituencySelectId').find('option').remove();
  $('#constituencySelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#constituencySelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}

function buildMandalsOrMuncipalities(results)
{
 $('#mandalSelectId').find('option').remove();
 $('#mandalSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#mandalSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}


function buildHamletsOrWardsInRegion(results){

	$('#wardSelectId').find('option').remove();
	$('#wardSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#wardSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');


}

function buildBooths(results){

	$('#boothSelectId').find('option').remove();
	$('#boothSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#boothSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}
function showLocationsDiv(){

		if($('#scopeId').val() == "0")
		{
			$('#regionstitleDiv').css('display','none');
			$('#locationsDiv').css('display','none');
		}
		else
	    {
			$('#regionstitleDiv').css('display','block');
			$('#locationsDiv').css('display','block');
		}

		$('#constituencySelectId ,#mandalSelectId , #wardSelectId ,#wardSelectId,#boothSelectId').find('option').remove();

		$('#constituencySelectId ,#mandalSelectId , #wardSelectId ,#wardSelectId,#boothSelectId').append('<option value="0">Select</option>');

		$('#districtSelectId').val("0");

	if($('#scopeId').val() == "2")
	{
		$('#stateSelect').css('display','block');
		$('#districtSelect , #constituencySelect , #mandalSelect , #wardSelect , #boothSelect').css('display','none');
		
	}
	else if($('#scopeId').val() == "3")
	{
		$('#stateSelect , #districtSelect').css('display','block');

		$('#constituencySelect , #mandalSelect ,#wardSelect ,#boothSelect').css('display','none');
		
	}
	else if($('#scopeId').val() == "4")
	{
		$('#stateSelect , #districtSelect , #constituencySelect').css('display','block');

		$('#mandalSelect , #wardSelect , #boothSelect').css('display','none');
	
	}
	else if($('#scopeId').val() == "5" || $('#scopeId').val() == "7")
	{
		$('#stateSelect , #districtSelect , #constituencySelect ,#mandalSelect').css('display','block');
		
		$('#wardSelect , #boothSelect').css('display','none');
	}
	else if( $('#scopeId').val() == "6" || $('#scopeId').val() == "8")
	{
		$('#stateSelect , #districtSelect , #constituencySelect , #mandalSelect , #wardSelect ').css('display','block');
		
		$('#boothSelect').css('display','none');
	}
	else if( $('#scopeId').val() == "9")
	{
		$('.locationDivClass').css('display','block');
	}

}

function getMandalsOrMuncipalities()
{
	if($('#scopeId').val() == "5" || $('#scopeId').val() == "6")
      getMandalsOrMuncipalities1("RURAL");
	else if($('#scopeId').val() == "7" || $('#scopeId').val() == "8")
	 getMandalsOrMuncipalities1("URBAN");
	else if($('#scopeId').val() == "9")
	 getMandalsOrMuncipalities1("");

}

function getHamletsOrWards(){

	if($('#scopeId').val() == "9"){
 	 getHamletsOrRegions();
	 getBoothsInTehsilOrMunicipality();
	}else{
		  getHamletsOrRegions();
  
	}

}

function clearFieldsData(){
		$("#errorMsgAlert").html("");
		var mandalId=document.getElementById("mandalField");
	    var panchayatFieldId=document.getElementById("panchayatField");
		var pollingStationFieldId=document.getElementById("pollingStationField");
		removeSelectElements(pollingStationFieldId);
		removeSelectElements(mandalId);
		removeSelectElements(panchayatFieldId);
		$('#constituencyList').val(0);
	}
	function clearErrDiv(){
	$("#errorMsgAlert").html("");
	}
</script>
</head>
<body>

   

  <div style="width:960px;margin-left:auto;margin-right:auto;">

	
      <div class="titleHeading">VOTER SEARCH
	  <input style="float:right;" onclick="buildPopup();" class="btn btn-success" type="button" value="Status Report"></input></div>
	   
    <div id="statuspopup"><div id="popupInnerContent"></div></div>
	  <fieldset id="mainFieldset">
      <div id="AlertMsg" style="font-family: verdana;font-size: 13px;color:red;"></div>
	  <div id="errorMsgAlert" style="font-family: verdana;font-size:14px;color:red;margin-left:100px;height: 40px;"></div>
   
	  <div id="reportLevelDiv" class="selectDiv">Select Level<font class="requiredFont">*</font><select id="reportLevel" class="selectWidth" style="margin-left:76px;width:165px;" name="constituencyList" onchange="clearFieldsData(),showReportLevel1(this.options[this.selectedIndex].value);">
		<option value=1>Constituency</option>
		<option value=2>Mandal</option>
	    <option value=3>Panchayat</option>
		<option value=4>PollingStation</option>
		</select>
      </div>
	
	
	  <div id="ConstituencyDiv" class="selectDiv">
	    
		 Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;width:165px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="clearErrDiv(),getMandalOrMuncipalityList();"/>
		 
	  <span style='display:none;float: right;' id='ajaxLoad'><img src='./images/icons/search.gif' /></span>		
	  </div>
	  
	 

	<div id="mandalDiv" class="selectDiv" style="display:none;">
	     <span id="mandalSpan">Select Mandal</span><font class="requiredFont">*</font>
		 <select id="mandalField" class="selectWidth" name="state" onchange="clearErrDiv(),getPanchayatOrWardsList('panchayat','panchayatField');getPanchayatOrWardsList('pollingstationByPublication','pollingStationField');" style="margin-left:60px;width:165px;"></select>
	  </div>
	  
	  <div id="panchayatDiv" class="selectDiv" style="display:none;">
	    Select Panchayat<font class="requiredFont">*</font> 	
	    <select id="panchayatField" class="selectWidth" name="state"  onchange="clearErrDiv();" style="margin-left:39px;width:165px;"></select>
	  </div>
	  
	
	  <div id="pollingStationDiv" class="selectDiv" style="display:none;">
	    Select PollingStation<font class="requiredFont">*</font><select id="pollingStationField" class="selectWidth" name="state"  style="margin-left:20px;width:165px;" onchange="clearErrDiv();"></select>
	  </div>
	  
	  
	  <div class="selectDiv">
	   VoterId<input style="width:154px;margin-left: 116px;" type="text" id="voterId" />
	  </div>
	  
	  <div class="selectDiv">
	    House No<input style="width:154px;margin-left: 102px;" type="text" id="reqHouseNo" />
	  </div>
	  
	  <div class="selectDiv">
	   Name<input style="width:154px;margin-left: 127px;" type="text" id="voterName" />
	   <div class="row pull-right">
	   <div class="span2">
	   <label class="radio"  style="margin-left: 9px;">
	   <input type="radio"name="voterNameChkBox" checked="true" id="startWith"  value="start" /><b style="font-size: 12px; font-family: verdana;"> Start With</b>
         </label>
		 </div>
		 <div class="span3">
		<label class="radio"style="margin-left: -53px;">
	   <input type="radio"  id="anyWhere" name="voterNameChkBox" value="any"/><b style="font-size: 12px; font-family: verdana;"> Any Where</b>
	 </label>
	 </div>
	 </div>
	  </div>
	  
	  <div class="selectDiv">
	   Guardian Name<input style="width:154px;margin-left:62px;" type="text" id="gaurdianName" />
	  </div>
	  
	  <div class="selectDiv">
	   Gender 
	    <div class="row "style="padding: 0px 0px 0px 162px; margin-top: -22px; ">
		   <div class="span">
		   <label class="radio">
	   <input type="radio" checked="true"  value="all" name="genderChkBox" /><b style="font-size: 12px; font-family: verdana;"> All </b>
	    </label>
		 </div>
		 	   <div class="span">
		 <label class="radio">
	   <input type="radio" id="maleSelect" name="genderChkBox"  value="male"  /><b style="font-size: 12px; font-family: verdana;"> Male </b>
	    </label>
			 </div>
			 	   <div class="span">
			 <label class="radio">
	   <input type="radio" id="femaleSelect" name="genderChkBox"  value="female" /><b style="font-size: 12px; font-family: verdana;"> Female</b>
	       </label>
		    </div>
	  </div>
	    </div>
	  <div class="selectDiv">
	   Age Between <input style="width:67px;margin-left:73px;" type="text" id="fromAge" /> <input style="width:67px;" type="text" id="toAge" />
	  </div>
	  <div class="selectDiv">
	   Serial No Between <input style="width:67px;margin-left:38px;" type="text" id="fromSno" /> <input style="width:67px;" type="text" id="toSno" />
	  </div>
  
	  <input style="margin-left:240px;margin-bottom:10px;" onclick="getVotersInfoForCTP();" class="btn btn-success" type="button" id="searchbtnId" value="Search"/>
  </fieldset>
<div id="errorMessageDiv" style="display:none;font-weight:bold;color:red" align="center"></div>
	<div id="voterDetailsDiv"></div>
</div>
<script>
function getMandalOrMuncipalityList()
{
    var tempVar = "mandalList";
    var selectElmt = "mandalField";
    var reportLevelValue = $("#reportLevel").val();
	var constituencyId = $("#constituencyList").val(); 

	if(reportLevelValue == 5)
     tempVar = "muncipalityList";
			
		var jsObj=
			{
				constituencyId:constituencyId,
				tempVar:tempVar,
				selectElmt:selectElmt,
				task:"getMandalOrMuncipalityList"
					
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMandalOrMuncipalityListForVotersAnalysisAction.action?"+rparam;						
		callAjax(jsObj,url);
		
}

function getWardsForMuncipality()
{
  var constituencyId = $("#constituencyList").val();
  var localEleBodyId = $("#mandalField").val();
  var publicationDateId = presentPublication;
  
  var jsObj=
			{
				constituencyId:constituencyId,
				localEleBodyId:localEleBodyId,
				publicationDateId:publicationDateId,
				selectElmt:"wardField",
				task:"getWardsListForMuncipality"
					
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getWardsListForMuncipalityAction.action?"+rparam;						
		callAjax(jsObj,url);
  
}
	function enableButton(id)
{

	document.getElementById(id).disabled  = false;
}

function disableButton(id)
{
	
	document.getElementById(id).disabled  = true;
}
function showReportLevel1(value)
	{
		if(value == 1)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'none';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'none';
			
		}
		else if(value == 2)
		{
			$("#mandalSpan").html('Select Mandal');
			$("#mandalField").css({ "margin-left" : "60px"} );
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'none';
			
			getMandalList('mandalField');

		}
		else if(value == 3)
		{
			
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'block';
			document.getElementById('pollingStationDiv').style.display = 'none';
			
			getPanchayatList1('panchayat','panchayatField');

		}
		else if(value == 4)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'block';
			
			getPanchayatList1('pollingstationByPublication','pollingStationField');
		}
		
	}
function getPanchayatList1(checkedele,selectedEle)
	{
		
		var constituencyId = $("#constituencyList").val();
		var reportLevel = $("#reportLevel").val();
		var mandalId=document.getElementById("mandalField");
		var value1 = '';
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var name=mandalId.options[mandalId.selectedIndex].name;
			var value1=mandalId.options[mandalId.selectedIndex].value;
		}
		var type = "mandal";
		if(value1.charAt(0) =="1"){
		 type = "muncipality";
		}
		var value = value1.substring(1);
		var publicationValue = presentPublication;
		var alertEl = document.getElementById("AlertMsg");
		alertEl.innerHTML = '';
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var selectname = mandalId.options[mandalId.selectedIndex].text;
			var flag= selectname.search("MUNCIPALITY");
		}
		if(flag == -1){
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var selectname = mandalId.options[mandalId.selectedIndex].text;
			var flag= selectname.search("Corp");
		}
		}
		 if(value1 !='' && value1 == 0)
		{
			alertEl.innerHTML ='<P>Please Select Mandal</P>';
			return;
		}
		if(flag != -1 && checkedele == "panchayat")
		{
		document.getElementById('panchayatDiv').style.display='none';
		}
		else if(flag != -1 && checkedele == "pollingstationByPublication" && reportLevel == 4)
		{
		 flag = -1;
		 document.getElementById('panchayatDiv').style.display='none';
		}
		if(flag == -1)
		{
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				constituencyId:$("#constituencyList").val(),
				flag:flag,
				type:type,
				publicationValue : publicationValue,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}
	}
	function getPanchayatOrWardsList(checkedele,selectedEle)
	{
     
	  var publicationValue = presentPublication;
		var constituencyId = $("#constituencyList").val();
		var reportLevel = $("#reportLevel").val();
		var mandalId=document.getElementById("mandalField");
		var value1 = '';
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var name=mandalId.options[mandalId.selectedIndex].name;
			var value1=mandalId.options[mandalId.selectedIndex].value;
		}
		var type = "mandal";
		if(value1.charAt(0) =="1"){
		 type = "muncipality";
		}
		var value = value1.substring(1);
		
		var alertEl = document.getElementById("AlertMsg");
		if(alertEl !=null)
			alertEl.innerHTML = '';
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var selectname = mandalId.options[mandalId.selectedIndex].text;
			var flag= selectname.search("MUNCIPALITY");
		}
		if(flag == -1){
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var selectname = mandalId.options[mandalId.selectedIndex].text;
			var flag= selectname.search("Corp");
		}
		}
		 if(value1 !='' && value1 == 0)
		{
			alertEl.innerHTML ='<P>Please Select Mandal</P>';
			return;
		}
		if(flag != -1 && checkedele == "panchayat")
		{
		document.getElementById('panchayatDiv').style.display='none';
		}
		else if(flag != -1 && checkedele == "pollingstationByPublication" && reportLevel == 4)
		{
		 flag = -1;
		 document.getElementById('panchayatDiv').style.display='none';
		}
		if(flag == -1 )
		{
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				constituencyId:$("#constituencyList").val(),
				flag:flag,
				type:type,
				publicationValue : publicationValue,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}

	}
	var isMuncipality;
	var type;
	function getVotersInfoForCTP()
	{

	$("#voterDetailsDiv").html('');
	$('#errorMessageDiv').hide();
	$("#errorMsgAlert").html("");
	var id='';
	var publicationDateId ='';
	var flag =true;
	var str ='';
	isMuncipality=false;	
	var constituencyId = $("#constituencyList").val();
	var level = $("#reportLevel").val();
	id = $("#constituencyList").val();
	if(id == 0 ||id == null)
	{
		str +='<div>Please Select Constituency</div>';
		$("#errorMsgAlert").html(str);
		return;
	}
	
	if(level == 2 || level == 3 || level == 4 ){
	id = $("#mandalField").val();
			if(id == 0 || id == null)
			{
				str +='<div>Please Select Mandal</div>';
				$("#errorMsgAlert").html(str);
				return;
			}	
    }
    if(level == 3 ){
	  id = $("#panchayatField").val();
		if(id == 0 || id == null)
		{
			str +='<div>Please Select Panchayat</div>';
			$("#errorMsgAlert").html(str);
			return;
		}
	}
	if(level == 4){
	  id = $("#pollingStationField").val();
        if(id == 0 || id == null)
		{
			str +='<div>Please Select Polling Station</div>';
			$("#errorMsgAlert").html(str);
			return;
		}
	}
	
		
		if(level == 1){
		type = 'constituency';
		id = $("#constituencyList").val();
		selectedType=type;
		selectedTypeId=id;	
	}
	else if(level == 2){
		type = 'mandal';
		id = $("#mandalField").val();
			if(id.slice(0,1)==1){
				isMuncipality=true;
			}
		selectedType=type;
		selectedTypeId=id;
			if(id.charAt(0) =="1"){
				 selectedType = "muncipality";
				madalType = "muncipality";
			}		
   }
   else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
	  selectedType=type;
	  selectedTypeId=id;	
	}
	else if(level == 4){
		 type = 'booth';
		 id = $("#pollingStationField").val();
		 selectedType=type;
	     selectedTypeId=id;	
	}
	var voterCardId = '';
	var voterName = '';
	var voterNameType = '';
	var guardianName = '';
	var gender = '';
	var startAge = 0;
	var endAge = 0;
	var fromSno = 0;
	var toSno = 0;
	var houseNo ='';
	voterCardId = $.trim($("#voterId").val());
	voterName = $.trim($("#voterName").val());
	houseNo = $.trim($("#reqHouseNo").val());
	if($("#startWith").is(':checked')){
	   voterNameType = 'start';
	}else{
	  voterNameType = 'anywhere';
	}
	guardianName = $.trim($("#gaurdianName").val());

	if($("#maleSelect").is(':checked')){
	   gender = 'M';
	}else if($("#femaleSelect").is(':checked')){
	  gender = 'F';
	}
	var ageStart = $.trim($("#fromAge").val());
	var ageEnd = $.trim($("#toAge").val());
	if(ageStart.length > 0){
	   if(isNaN(ageStart)){
	        str +='<div>Please Enter Valid Start Age</div>';
			flag =false;
	   }else{
	     startAge = ageStart;
	   }
	}
	if(ageEnd.length > 0){
	   if(isNaN(ageEnd)){
	        str +='<div>Please Enter Valid End Age</div>';
			flag =false;
	   }else{
	     endAge = ageEnd;
	   }
	}
	
	if(ageStart.length > 0 && ageEnd.length > 0){
		if(ageStart > ageEnd || (ageStart == 0 && ageEnd == 0)){
			str +='<div>Please Enter Valid Ege Range</div>';
			flag =false;
		}
	}
	if(ageStart.length > 0 && ageEnd.length == 0){
		str +='<div>Please Enter Ege Age</div>';
		flag =false;
	}
	var startSno = $.trim($("#fromSno").val());
	var endSno = $.trim($("#toSno").val());
	if(startSno.length > 0){
	   if(isNaN(startSno)){
	        str +='<div>Please Enter Valid Start Sno</div>';
			flag =false;
	   }else{
	     fromSno = startSno;
	   }
	}
	if(endSno.length > 0){
	   if(isNaN(endSno)){
	        str +='<div>Please Enter Valid End Sno</div>';
			flag =false;
	   }else{
	     toSno = endSno;
	   }
	}
if(!flag){
	  $("#errorMsgAlert").html(str);
	 
	}
	else
	{
 var arr = [];
 var obj = {
	 voterCardId: voterCardId,
	  voterName:voterName,
	  voterNameType:voterNameType,
	  guardianName:guardianName,
	  gender:gender,
	  startAge:startAge,
	  endAge:endAge,
	  fromSno:fromSno,
	  toSno:toSno,
	  houseNo:houseNo,
	  locationLvl:selectedType,
	  id:selectedTypeId
 }
	 
 arr.push(obj);
 var jObj = {
	 searchArr : arr,
		 task:"ctpSearch"
 }
 $.ajax({
	          type:'POST',
	          url: 'getctpSearchVoterDetails.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jObj)},
			  success: function(result){ 
			  if(result.votersList== null || result.votersList.length == 0)
			  $('#errorMessageDiv').show().html('No Data Avalible For Given Search Details');
			  else
				  buildVoterDetails(result.votersList);
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
		}
	}
	function getVotersCountDetails()
	{
		$("#popupInnerContent").html('');
		var jObj ={
			task : "getVoterCount"
		}
	$.ajax({
	          type:'POST',
	          url: 'getctpVoterCountDetails.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jObj)},
			  success: function(result){ 
			  buildVoterCount(result.votersList);
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
		
	}
	function buildVoterCount(result)
	{
		var str = '';
		str+='<table class="table table-bordered">';
		str+='<th>District</th>';
		str+='<th>Constituency</th>';
		str+='<th>Total Voters</th>';
		str+='<th>Caste</th>';
		str+='<th>Caste Percentage</th>';
		for(var i in result)
		{
		str+='<tr>';
		str+='<td>'+result[i].districtName+'</td>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].count+'</td>';
		str+='<td>'+result[i].casteCount+'</td>';
		str+='<td>'+result[i].percentage+'</td>';
		str+='</tr>';
		}
		str+='</table>';
		$("#popupInnerContent").html(str);
		$("#statuspopup").dialog({                   
		    modal: true,
            title: "<b>Voter Count Details</b>",
			width: 750,
            height: 550
     });

	}
	function buildVoterDetails(result)
	{
		var str = '';
		str+='<table class="table table-bordered" id="voterDataTable">';
		str+='<thead>';
		str+='<th>Name</th>';
		str+='<th>VoterId</th>';
		str+='<th>booth</th>';
		str+='<th>Serial Number</th>';
		str+='<th>HNO</th>';
		str+='<th>gender</th>';
		str+='<th>age</th>';
		str+='<th>caste</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
		
		str+='<tr>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].voterIdCardNo+'</td>';
		str+='<td>'+result[i].boothName+'</td>';
		str+='<td>'+result[i].fromSno+'</td>';
		str+='<td>'+result[i].houseNo+'</td>';
		str+='<td>'+result[i].gender+'</td>';
		str+='<td>'+result[i].age+'</td>';
		str+='<td>'+result[i].casteName+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#voterDetailsDiv").html(str);
		$("#voterDataTable").dataTable();
	}

	function buildPopup()
	{
		getVotersCountDetails();
		
	}
</script>
</body>
</html>