<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message Center</title>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/messageCenter.js"></script>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>

<script>
$(document).ready(function() {
	$('#historyHeading').live("click",function(){
		$('#smsHistory').slideToggle();
	});
});
</script>

 <style>
  .datagrid table {
	  border-collapse: collapse; 
	  text-align: left; 
	  width: 100%;
   } 
   .datagrid {
	   font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 1px solid #006699; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;
  }
  .datagrid table td, .datagrid table th {
	  padding: 3px 10px;
	  text-align:center;
  }
  .datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );#006699;
	  background-color:#006699; 
	  color:#FFFFFF; 
	  font-size: 15px;
	  font-weight: bold;
	  border-left: 1px solid #0070A8;
  } 
  .datagrid table thead th:first-child {
	  border: none; 
  }
  .datagrid table tbody td {
	  color: #00496B; 
	  border-left: 1px solid #E1EEF4;
	  font-size: 12px;
	  font-weight: normal;
   }
   .datagrid table tbody .alt td {
	   background: #E1EEF4;
	   color: #00496B;
	}
	.datagrid table tbody td:first-child {
		border-left: none;
	}
	.datagrid table tbody tr:last-child td {
		border-bottom: none;
	}

	.datagrid table tr:nth-child(even) {background: #E1EEF4}
    .datagrid table tr:nth-child(odd) {background: #FFF}

	
  </style>
<style>
	.left_container{padding:10px;font-family:arial;font-size:14px;}
	input[type="radio"], input[type="checkbox"] {cursor: pointer;line-height: normal;margin: 4px;;}
	.typeRadio{clear:both;}
	.mainContainer{width:950px;}
	#radioSelectionId{font-family:verdana;font-size:15px;margin:15px;}
	.selectBoxes{padding-left:80px;}
	.filter{border:1px solid #cccccc;background:#f3f3f3;}

textarea{
	background-color:#fff;
}
</style>

</head>
<body>

<!--Sending Voice Sms Block Start-->

<div id='cnstHeading'  class='alert alert-info' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;margin-bottom:-45px;'><h4>SEND VOICE SMS</h4></div>
<div class="thumbinal" style="margin-top:62px;">

 <div style="text-align:center;"><span>Enter Mobile Numbers To Send Voice Sms:</span><textarea></textarea></div>

  <div style="text-align:center;margin-top:41px;margin-left:110px;"><span>Enter Description:</span><textarea></textarea></div>

<div class='alert alert-info' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;margin-bottom:-45px;margin-top:12px;'><h4>AUDIO FILES AVAILABLE</h4></div>
 <div id="audioFilesDiv"></div>

<div id='cnstHeading'  class='alert alert-info' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;margin-bottom:-45px;'><h4>VERIFIED NUMBERS TO SEND VOICE SMS</h4></div>
<div class="thumbinal" style="margin-top:56px;"> 
 <div id="verifiedNumbersDiv"></div>
</div>

<div style="margin:14px 15px 13px 827px;">
<input type="button" class="btn btn-success" value="Send Voice Sms" onClick="ajaxToSendSms()"/>
</div>

<div id='historyHeading'  class='alert alert-info' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;margin-bottom:-45px;'><a href="javascript:{}"><h4>SHOW / HIDE SMS HISTORY</h4></a></div>

<div class="breadcrumb" style="margin-top:40px;"> 
 <div id="smsHistory"></div>
</div>

<div id="responseDetailsDiv">
 <div id="responseDetailsInnerDiv">
 </div>
</div>




<!--Sending Voice Sms Block End-->

<!--<input type="button" onClick="ajaxToGetRecordingDetails();" value="GET AUDIOS"/>
<input type="button" onClick="getVerifiedNumbersOfUser();" value="GET VERIFIED NUMBERS"/>-->



<script>
function ajaxToGetRecordingDetails(){
	var jsObj=
			{
				task:"getRecordingsOfUser",
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getAllTheRecordedFilesOfAUser.action?"+rparam;	
			callAjax(rparam,jsObj,url);
}
function getVerifiedNumbersOfUser(){
	var jsObj=
			{
				task:"getVerifiedNumbersOfUser",
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVerifiedNumbersOfUser.action?"+rparam;	
			callAjax(rparam,jsObj,url);
}
function getVoiceSmsHistoryOfUser(){
	var jsObj=
			{
				task:"getVoiceSmsHistoryForAuser",
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVoiceSmsHistoryForAuser.action?"+rparam;	
			callAjax(rparam,jsObj,url);
}
function showMessageResponseDetails(responseCode){
	var jsObj=
			{
				task:"getResponseDetails",
                messageResponseCode:responseCode
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getResponseDetailsForSms.action?"+rparam;	
			callAjax(rparam,jsObj,url);
}
</script>



<script>
$('#mobilesId').html("");

$('input[name=type][value=ipeople]').prop("checked",true);
removeOptions();

function removeOptions(){
	for(var i=0;i<=9;i++){
			if(i>4){
				$("#subLevelsId option[value="+i+"]").remove();
			}
		}
}

$('#subLevelsId').val(2);
$('#stateField_s').val(1);

var sublevelArr=[];


$("#subLevelsId option").each(function()
{
	var obj={};
	var value=$(this).val();
	var name=$(this).text();
   
	obj[name] = value;
	sublevelArr.push(obj);
});
	
	
	

$("input:radio[name=type]").click(function() {
    var value = $(this).val();
	
	if(value=="ipeople"){
		
		$('.selectBoxes,#subLevelsId').css('display','block');
	
		for(var i=0;i<=9;i++){
			if(i>4){
				$("#subLevelsId option[value="+i+"]").remove();
			}
		}
	}else{
		$("#subLevelsId").html('');
		var str='';
		$.each(sublevelArr, function(idx, obj){ 
			$.each(obj, function(name, value){
				str+='<option value="'+ value +'">'+ name +'</option>';
			});
		});
		$("#subLevelsId").html(str);
		
		$('.selectBoxes,#subLevelsId').css('display','none');
		
		buildSearchPagePopup("Search");
	}
	
});

function getConstituencies(){
	var districtId=$('#districtField_s option:selected').val();
		getConstituenciesOfDistrict(districtId);
}
function getDistricts(){
	var stateId=$('#stateField_s option:selected').val();
		getDistrictsOfState(stateId);
}
function getSubRegions(){
	var constituencyId=$('#constituencyField_s option:selected').val();
		getSubRegionsOfConsti(constituencyId);
}
function getSubRegionsInMandal(){
	var localId=$('#mandalField_s option:selected').val();
	var constituencyId=$('#constituencyField_s option:selected').val();
	var name=$('#mandalField_s option:selected').text();
		getSubRegionsOfLocalBody(localId,name,constituencyId);
}
function getSubRegionsInMuncipal(){
	var localId=$('#muncipalField option:selected').val();
	var name=$('#muncipalField option:selected').text();
	var constituencyId=$('#constituencyField_s option:selected').val();
		getSubRegionsOfLocalBody(localId,name,constituencyId);
}


var areaType='';
$('.selectBoxes > :not(#stateField_s)').hide();
var scopeSelected='';
function getScopes(){
scopeSelected=$('#subLevelsId option:selected').text();
if(scopeSelected=="STATE"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s)').hide();
}
if(scopeSelected=="DISTRICT"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s)').hide();
}
if(scopeSelected=="CONSTITUENCY"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s)').hide();
}
if(scopeSelected=="MANDAL"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s)').hide();
}
if(scopeSelected=="VILLAGE"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s,#hamletField_s)').hide();
}
if(scopeSelected=="MUNICIPAL-CORP-GMC"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s)').hide();
}
if(scopeSelected=="WARD"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s,#hamletField_s)').hide();
}
if(scopeSelected=="BOOTH"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s,#boothField_s)').hide();
}

	if(scopeSelected == 'WARD' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'RURAL';
	}
	if(scopeSelected == 'HAMLET' ||  scopeSelected == 'MANDAL' || scopeSelected == 'VILLAGE')
	{
		areaType = 'URBAN';
	}
}



function getDistrictsOfState(stateId){
	var task="getDistricts";
	var jsObj=
			{
					locationId:stateId,
					task:task						
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getSublevelsOfMessageCenterAction.action?"+rparam;	
			callAjax(rparam,jsObj,url);
}
function getConstituenciesOfDistrict(districtId){
	if(scopeSelected == 'WARD' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'RURAL';
	}
	var task="getConstituencies";
	var jsObj=
			{
					locationId:districtId,
					task:task,
					areaType:areaType
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getSublevelsOfMessageCenterAction.action?"+rparam;	
			callAjax(rparam,jsObj,url);
}
function getSubRegionsOfConsti(constId){
	var task="subRegionsInConstituency";
	if(scopeSelected == 'WARD' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'URBAN';
	}
	else if(scopeSelected == 'HAMLET' ||  scopeSelected == 'MANDAL' || scopeSelected == 'VILLAGE'){
		areaType= 'RURAL';
	}
	else{
		areaType='';
	}
	var jsObj=
			{
					locationId:constId,
					task:task,
					areaType:areaType
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getSublevelsOfMessageCenterAction.action?"+rparam;	
			callAjax(rparam,jsObj,url);
}
function getSubRegionsOfLocalBody(localId,name,constId){
	var task ="";
	var flag = name.search("Greater Municipal Corp");
	
	if(scopeSelected == 'BOOTH')
	{
		if(flag == '-1')
		{
			task="boothsInTehsilOrMunicipality";
		}else{
			task="hamletsOrWardsInRegion";
		}
	}
	if(scopeSelected == 'WARD' || scopeSelected == 'VILLAGE' ) {
		task="hamletsOrWardsInRegion";
	}
	
	var jsObj=
			{
					locationId:localId,
					task:task,
					name:name,
					constId:constId,
					areaType:''
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getSublevelsOfMessageCenterAction.action?"+rparam;	
			callAjax(rparam,jsObj,url);
	
}

$('#stateField_s').change(function(){
	var stateId=$('#stateField_s option:selected').val();
	var state=$('#stateField_s option:selected').text();
	
	getSubLevelInfluenceData(stateId,state,"STATE","VILLAGE/WARD","",0,true);
});

getSubLevelInfluenceData(1,"Andhra Pradesh","STATE","VILLAGE/WARD","",0,true);


$('#districtField_s').change(function(){
	var districtId=$('#districtField_s option:selected').val();
	var district=$('#districtField_s option:selected').text();
	
	reGetInfluencingPeopleInAConstituency('DISTRICT',districtId,district);
});

$('#constituencyField_s').change(function(){
	var constId=$('#constituencyField_s option:selected').val();
	var consti=$('#constituencyField_s option:selected').text();
	
	reGetInfluencingPeopleInAConstituency('CONSTITUENCY',constId,consti);
});
//getSubLevelInfluenceData(regionId,regionName,regionType,areaType,"",0,false)

function getSubLevelInfluenceData(regionId,regionName,regionType,areaType,regionTitle,regionTitleId,status)
		{
		
			var jsObj= 
			{	
				regionId:regionId,
				regionName:regionName,	
				regionType:regionType,
				regionTitle:regionTitle,
				regionTitleId:regionTitleId,
				areaType:areaType,
				status:status,
				task: "getSubLevelInfluencePeople"						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/subLevelInfluenceAction.action?"+param;
			
			callAjax(param,jsObj,url);		
		}
	

function callAjax(param,jsObj,url){
	var myResults;	
	var callback = {			
	    success : function( o ) {
			try {	
					if(o.responseText.length!=0){
						myResults = YAHOO.lang.JSON.parse(o.responseText);	
					}						
					if(jsObj.task == "getStates")
					{
						clearOptionsListForSelectElmtId("stateId");
						createOptionsForSelectElmtId("stateId",myResults);
					}
					else if(jsObj.task == "getDistricts")
					{
						clearOptionsListForSelectElmtId("districtField_s");
						createOptionsForSelectElmtId("districtField_s",myResults);	
					}	
					else if(jsObj.task == "getConstituencies")
					{
						clearOptionsListForSelectElmtId("constituencyField_s");
						createOptionsForSelectElmtId("constituencyField_s",myResults);
					}		
					else if(jsObj.task == "subRegionsInConstituency" )
					{
						clearOptionsListForSelectElmtId("mandalField_s");
						createOptionsForSelectElmtId("mandalField_s",myResults);
					}		
					
					else if(jsObj.task == "hamletsOrWardsInRegion")
					{
						var name=jsObj.name;
						var flag = name.search("Greater Municipal Corp");
						if(flag != '-1'){
							$('.selectBoxes #hamletField_s').show();
						}
						
						clearOptionsListForSelectElmtId("hamletField_s");
						createOptionsForSelectElmtId("hamletField_s",myResults);
					}
					else if(jsObj.task == "boothsInTehsilOrMunicipality")
					{
						clearOptionsListForSelectElmtId("boothField_s");
						createOptionsForSelectElmtId("boothField_s",myResults);
					}
					else if(jsObj.task == "addNewPosition"){

					clearOptionsListForSelectElmtId("position");
					createOptionsForSelectElmtId1("position",myResults);
	                document.getElementById("positionId").innerHTML='';
					}else if(jsObj.task=="getSubLevelInfluencePeople"){
						buildRegionWiseInfluencePeople(myResults,jsObj)
					}else if(jsObj.task == "reGetInfluencingPeopleInAConstituency")
					{
						//getSubLevelInfluenceData(jsObj.regionId,jsObj.region,regionType,"VILLAGE/WARD","",0,true);
						
						buildRegionWiseInfluencePeopleForDistrictAndContsi(myResults,jsObj)
					}else if(jsObj.task=="getMobileNumbersOfSelectedId"){
						buildMobileNos(myResults,jsObj);
					}else if(jsObj.task == "getRecordingsOfUser")
				        buildResultForAudioFiles(myResults);
					else if(jsObj.task == "getVerifiedNumbersOfUser")
						 buildVerifiedNumbersForUser(myResults);
					else if(jsObj.task == "getVoiceSmsHistoryForAuser")
						buildVoiceSmsHistory(myResults);
					else if(jsObj.task == "getResponseDetails")
						buildResponseDetails(myResults);
					
			}catch (e) {   		
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

function buildMobileNos(results,jsObj){
	var str="";
	//str+='<h4 style="padding:15px;">Contacts for Sending SMS</h4>';
	for(var i in results){
	str+='<div class="span12 row-fluid mainClass thumbnail" id="removeThis'+i+'">';
	//str+='<span class="btn bnt-mini">'+results[i].mobileNO+'<span></span></span>';	
	str+='<div class="span6">'+results[i].cadreName+'</div><div class="span4">'+results[i].mobileNO+'</div><div class="span1"><img src="images/icons/delete.png"  style="margin:5px;height:12px;width:12px;" onclick="removeThis('+i+')"/></div></div>';
	}
	
	$('#mobilesId').append(str);
}

function removeThis(val){
	$('#removeThis'+i).html('');
}

function reGetInfluencingPeopleInAConstituency(regionType,regionId,region)
		{
			var jsObj= 
			{	
				regionId:regionId,
				regionType:regionType,
				region:region,
				task: "reGetInfluencingPeopleInAConstituency"
						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/influencingPeopleInConstituencyAction.action?"+param;
			
			callAjax(param,jsObj,url);	
		}
		
function buildRegionWiseInfluencePeople(data,jsObj){
	$('#tableDiv').html('');
	var results = data.regionWiseOverview;
	
	str="<table class='table table-bordered'>";
	for(var i=0; i<results.length; i++)
	{
	
		var availableRegions = new Array();
		var zeroRegions = new Array();
		
		
		if(results[i].subRegionWiseOverview != null)
		{
			
			for(var k=0; k<results[i].subRegionWiseOverview.length; k++)
			{	
				
				if(results[i].subRegionWiseOverview[k].countValue == 0)
					zeroRegions.push(results[i].subRegionWiseOverview[k]);
				else 
					availableRegions.push(results[i].subRegionWiseOverview[k]);
			}

		}
		
		if(results[i].countValue!=0){
		str+="<tr><td>"+results[i].regionName+"("+results[i].regionType+")--";
		str += '<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+jsObj.regionId+'\',\''+results[i].regionId+'\',\''+results[i].regionName+'\',\''+results[i].regionType+'\',\'region\')">'+results[i].countValue+'</a></td><td>';
		
			for(var j=0; j<availableRegions.length; j++)
			{
				str+=""+availableRegions[j].subRegionName+"--";
				str+='<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+results[i].regionId+'\',\''+availableRegions[j].subRegionId+'\',\''+availableRegions[j].subRegionName+'\',\''+availableRegions[j].subRegionType+'\',\'region\')">'+availableRegions[j].countValue+'</a><br>';
			}
		}
		
	}
	str+="</td></tr></table>";
	
	
	$('#tableDiv').html(str);

}
function buildRegionWiseInfluencePeopleForDistrictAndContsi(data,jsObj){
	$('#tableDiv').html('');
	var reslts = data.regionWiseOverview;
	
	var results=[];
	results.push(reslts);
	str="<table class='table table-bordered'>";
	for(var i=0; i<results.length; i++)
	{
	
		var availableRegions = new Array();
		var zeroRegions = new Array();
		
		
		if(results[i].subRegionWiseOverview != null)
		{
			
			for(var k=0; k<results[i].subRegionWiseOverview.length; k++)
			{	
				
				if(results[i].subRegionWiseOverview[k].countValue == 0)
					zeroRegions.push(results[i].subRegionWiseOverview[k]);
				else 
					availableRegions.push(results[i].subRegionWiseOverview[k]);
			}

		}
		
		if(results[i].countValue!=0){
		str+="<tr><td>"+results[i].regionName+"("+results[i].regionType+")--";
		str += '<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+jsObj.regionId+'\',\''+results[i].regionId+'\',\''+results[i].regionName+'\',\''+results[i].regionType+'\',\'region\')">'+results[i].countValue+'</a></td><td>';
		
			for(var j=0; j<availableRegions.length; j++)
			{
				str+=""+availableRegions[j].subRegionName+"--";
				str+='<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+results[i].regionId+'\',\''+availableRegions[j].subRegionId+'\',\''+availableRegions[j].subRegionName+'\',\''+availableRegions[j].subRegionType+'\',\'region\')">'+availableRegions[j].countValue+'</a><br>';
			}
		}else{
		str+='<tr><td>No data Avialable'	
		}
		
	}
	str+="</td></tr></table>";
	
	
	$('#tableDiv').html(str);

}

function openCandidatesPopup(parentRegionId,regionId,regionName,regionType,scopeType)
{
	var fromParent="messageCenter";
	var urlStr = "influencingPeopleDataActionForMessageCenter.action?windowTask=influencingPersonInfoPopup&parentRegionId="+parentRegionId+"&regionId="+regionId+"&regionName="+regionName+"&regionType="+regionType+"&scopeType="+scopeType+"&fromParent="+fromParent;
	var browser2 = window.open(urlStr,"influencingPersonInfoPopup2","scrollbars=yes,height=570,width=1300,left=200,top=50");	
	browser2.focus();
}

function buildSearchPagePopup(type)
{	
	var fromParent="messageCenter";
	var urlStr = "cadreSearchActionForMessageCenter.action?windowTask="+type+"&fromParent="+fromParent;
	var browser2 = window.open(urlStr,"cadreSearchAndSMSPopup2","scrollbars=yes,height=650,width=1100,left=150,top=100");	
	browser2.focus();

}

window.receiveFromChild = function(data) {

	var jsObj= 
		{	
			ids:data,
			task: "getMobileNumbersOfSelectedId"
		};
			
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getMobileNumbersOfSelectedId.action?"+param;
		callAjax(param,jsObj,url);
};

window.receiveFromCadreChild = function(data) {
	var str='';
	//str+='<h4 style="padding:15px;">Contacts for Sending SMS</h4>';
	for( var i=0, l=data.length; i<l; i++ ) {
		console.log( data[i] );
		str+='<div class="span12 row-fluid mainClass" id="removeThis'+i+'">';
		
		str+='<div class="span6">'+data[i].cadreName+'</div><div class="span4">'+data[i].cadreMobile+'</div><div class="span1"><img src="images/icons/delete.png"  style="margin:5px;height:12px;width:12px;" onclick="removeThis('+i+')"/></div></div>';
		
	}
	$('#mobilesId').append(str);
}
</script>

<script>
function buildResultForAudioFiles(results)
{

	if(results == null || results.length == 0)
	{
		$('#audioFilesDiv').html("No Files Exist");
		return false;
	}
	var str='';
	str+='<l><a href="javascript:{ajaxToGetRecordingDetails()}"><img src="images/icons/refreshImg.png" alt="Processing Image" title="Click here to refresh audio files" style="float:right;padding:5px;"/></a></div>';


	str+='<div style="margin:54px 0px 0px 265px;">';


      var i=0;
	
	$.each(results,function(key,value){

		   str+='<label><input name="audio" type="radio" value="'+i+'" id="'+key+'"/><a href="javascript:{showAudio(\''+key+'\','+i+')}" title="'+value+'">'+key+'</a> -- '+value+'</label>';
                  i++;
	});
str+='<label><a href="uploadAudioFile.action" target="blank" style="margin:0px 0px 0px 300px;"><b>Click Here To Record Audio And To Upload Audio</b></a></label>';
	str+='</div>';

	$('#audioFilesDiv').html(str);
}
function buildVerifiedNumbersForUser(results)
{ 
	var str='';

	  if(results == null || results.length == 0)
	  {
		  str +='<b>You Dont Have Any Verified Numbers.Please Contact Us To verify Mobile Number.</b>';
		  	$('#verifiedNumbersDiv').html(str);

		  return false;
					  
	  }

    str+='<div style="margin:10px 0px 0px 265px;">';
	$.each(results,function(index,value){
		str+='<label style="font-weight:bold;"><input type="radio" name="senderNumber" value="'+value+'"/>'+value+'</label>';
	});
	str+='</div>';
	$('#verifiedNumbersDiv').html(str);
}

function buildVoiceSmsHistory(results)
{
	var str='';
    str+='<div class="datagrid">';
    str+='<table border="1">';
	str+='<thead>'; 
	str+='<tr>';
	 str+='<th>Messagee Id</th>';
 	 str+='<th>Date Sent</th>';
 	 str+='<th>Mobile Numbers</th>';
	 str+='<th>Check details</th>';
     str+='<th>Description</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	$.each(results,function(index,value){

	 str+='<tr>';
	  str+='<td>'+value.responseCode+'</td>';
	  str+='<td>'+value.dateSent+'</td>';
	  str+='<td>'+value.numbers+'</td>';
	  str+='<td><a href="javascript:{showMessageResponseDetails('+value.responseCode+');}">Click here For Details</a></td>';
	   // str+='<td><a href="http://dnd.smschilly.com/api/check_voice_dlr.php?user=voicedemo1&password=abcd1234&msgid='+value.responseCode+'" target="blank">'+value.responseCode+'</a></td>';
		 str+='<td>'+value.description+'</td>';
	 str+='</tr>';
	});
	str+='</tbody>';
	str+='</table>';
	str+='</div>';

$('#smsHistory').html(str);

$('#smsHistory').hide();
}

function buildResponseDetails(results)
{

	var str='';

	$.each(results,function(key,value){

		str+=key+"-"+value;

	});

	$('#responseDetailsInnerDiv').html(str);
	$('#responseDetailsDiv').dialog({
		title:"Response Details" ,
		buttons: {
				
				"Ok":function(){$(this).dialog("close");} 
			}
	});

}

ajaxToGetRecordingDetails();
getVerifiedNumbersOfUser();
getVoiceSmsHistoryOfUser();
</script>

</body>
</html>