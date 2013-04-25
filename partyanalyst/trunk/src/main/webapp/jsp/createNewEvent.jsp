<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
	<link href="styles/assets/css/bootstrap.css" rel="stylesheet">

	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>	
<title>Create New Event</title>
<style>
#pop_header {
    background-attachment: scroll;
    background-clip: border-box;
    background-color: #06ABEA;
    background-image: none;
    background-origin: padding-box;
    background-position: 0 0;
    background-repeat: repeat;
    background-size: auto auto;
    border-radius: 3px;
    color: #FFFFFF;
    font-size: 15px;
    font-weight: bold;
    padding: 7px;
    text-align: left;
	text-align: center;
    width: 180px;
}
.titleStyle{
    color: #2B4E70;
    padding-bottom: 5px;
    padding-left: 5px;
    padding-right: 5px;
    padding-top: 5px;
    width: 170px;
	font-weight:bold;
}
.topspace{
  margin-top:10px;
}
.ui-datepicker table {
    font-size: 0.6em;
}
.ui-datepicker {
    height: 11.8em;
    width: 12em;
}
.ui-datepicker .ui-datepicker-title select {
    font-size: 0.7em;
}
.selectboxstyle{
  width:200px;
}
.hidediv{
 display:none;
}
form{
margin:0px auto;
width:600px;
background-color: #F2F2F2;
border-radius: 5px;
padding: 10px;
display:table;
}
div{float:left;margin-top:10px;}
span{float:left;}
#locationLvl{
  width:200px;
}
.cadresDivForPanel {
    background-color: #EBF5FF;
    margin-bottom: 5px;
    padding-bottom: 5px;
    padding-left: 5px;
    padding-right: 5px;
    padding-top: 5px;
}
#errorMsgDiv,#actionPlanError {
    color: #E92B2B;
    font-weight: bold;
}
#successmsg{
  color: green;
    font-weight: bold;
}
.requiredFont {
    color: red;
	font-size: 17px;
}

</style>
<script type="text/javascript">
var allOrgnisers = new Array();
var planOrgnisers = new Array();
var actionPlanArray = new Array();
var actionPlanArrayId = 0;
var edited = false;
var prevLocationVal;

 $(document).ready(function(){
        $( "#startDateText_new" ).datepicker({
            changeMonth: true,
            changeYear: true,
			dateFormat: 'dd/mm/yy',
			minDate: new Date()
        });
		$( "#endDateText_new" ).datepicker({
            changeMonth: true,
            changeYear: true,
			dateFormat: 'dd/mm/yy',
			minDate: new Date()
        });
		$( "#actionPlanDate" ).datepicker({
            changeMonth: true,
            changeYear: true,
			dateFormat: 'dd/mm/yy',
			minDate: new Date()
        });
	var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
	  $("#startDateText_new").val(date);
	  $("#endDateText_new").val(date);
	   $("#actionPlanDate").val(date);
	  
	    $("#actionPlanButton").click(function(){
		    $("#addactionbutton").html('<input type="button" onclick="addNewAction();" class="btn" value="Add Action Plan " />');
            planOrgnisers = new Array();
	        $("#actionPlanName").val("");
	        $("#addedPlanOrganisers").html("");
	        var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
	        $("#actionPlanDate").val(date);
	        $("#showactionplan").toggle("slow");
	    });
		$("#planOrgButton").click(function(){
	        $("#showplan").toggle("slow");
	    });
		
    });
	
function showReqSelection(){
  edited = true;
  $("#locationSelValue").html("");
  clearSelectItems(true,true,true,true,true,true);
  var selId = $("#locationLvl option:selected").val();
  if(selId != 0)
	//getStates();
    getStatesInPS();
  if(selId == 0){
    $("#stateDiv").hide();
    $("#districtDiv").hide();
    $("#constituencyDiv").hide();
    $("#mandMunCorpGmcDiv").hide();
    $("#vilWrdDivDiv").hide();
    $("#boothNoDiv").hide();
  }
  else if(selId == 2 || selId == 3){
    $("#stateDiv").show();
	if(selId == 3)
	 $("#districtDiv").show();
	else
     $("#districtDiv").hide();
    $("#constituencyDiv").hide();
    $("#mandMunCorpGmcDiv").hide();
    $("#vilWrdDivDiv").hide();
    $("#boothNoDiv").hide();
  }
  else if(selId == 4 || selId == 10){
    $("#stateDiv").show();
	 if(selId == 10)
        $("#districtDiv").hide();
	 else
	    $("#districtDiv").show();
    $("#constituencyDiv").show();
    $("#mandMunCorpGmcDiv").hide();
    $("#vilWrdDivDiv").hide();
    $("#boothNoDiv").hide();
  }
  else if(selId > 4 && selId < 10){
    $("#stateDiv").show();
    $("#districtDiv").show();
    $("#constituencyDiv").show();
    $("#mandMunCorpGmcDiv").show();
	if(selId == 5 || selId == 7 || selId == 9)
      $("#vilWrdDivDiv").hide();
	else
	  $("#vilWrdDivDiv").show();
	if(selId == 9)
      $("#boothNoDiv").show();
	else
      $("#boothNoDiv").hide();
  }
}
function getStates()
	{	    
		var jsObj=
			{
				task:'getStates'
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/candidatePhotoGallaryAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
	}
function getStatesInPS(selectedElmt)
	{	
		
		var jsObj=
		{		
				electionType :'Assembly',		
				task:"getStatesAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getStatesAjaxAction.action?"+rparam;						
		callAjaxToGetData(jsObj,url);
	}
function getPariamentConstituencies()
	{	    
		var jsObj=
			{
				task:'getPariamentConstituencies',
				stateId:$("#stateSel option:selected").val()
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getParliamentConstituencies.action?"+rparam;

		callAjaxToGetData(jsObj, url);
	}
function getDistrictsInAState()
	{	    
		var jsObj=
			{
				task:'getDistrictsByStateId',
				stateId:$("#stateSel option:selected").val()
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/candidatePhotoGallaryAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
	}
function getConstituencies(){
			var jsObj=
             {
			     id:$("#districtSel option:selected").val(),
				 task:"constituenciesInDistrict",
				 taskType:"",
				 address:"",
				 areaType:"",
				 isParliament:"true"
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
}
function getOtherConstituencies(areaType){
  var jsObj=
             {
			     id:$("#districtSel option:selected").val(),
				 task:"getConstNotInGivenAreaType",
				 taskType:"",
				 address:"",
				 areaType:areaType,
				 isParliament:"true"
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
}
function getConstituencieSubs(selId){
      if(selId == 5 || selId == 6)
	    areaType = "RURAL";
	  else if(selId == 7 || selId == 8)
	    areaType = "URBAN";
	  else if(selId == 9)
       areaType = "";
	   
 var jsObj=
             {
			     id:$("#constituencySel option:selected").val(),
				 task:"subRegionsInConstituency",
				 taskType:"",
				 address:"",
				 areaType:areaType,
				 isParliament:"true"
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);

}
function getVillageWard(desc){
  var jsObj=
             {
			     id:$("#mandMunCorpGmcSel option:selected").val(),
				 task:"hamletsOrWardsInRegion",
				 taskType:"",
				 address:"",
				 areaType:"",
				 isParliament:"true",
				 desc:desc
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
}
function getBooth(){
  var jsObj=
             {
			     id:$("#mandMunCorpGmcSel option:selected").val(),
				 task:"boothsInTehsilOrMunicipality",
				 taskType:"",
				 address:"",
				 areaType:"",
				 isParliament:"true",
				 constId:$("#constituencySel option:selected").val()
				
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
}
function callAjaxToGetData(jsObj,url)
	{			
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								if(jsObj.task == "getStates")
								{
									buildOptions(myResults,"stateSel","Select State");
								}
								else if(jsObj.task == "getStatesAjaxAction")
								{
									buildStates(myResults);
								}
								else if(jsObj.task == "getPariamentConstituencies"){
								    buildSelectOptions(myResults,"constituencySel","Select Parliament Constituency")
								}
								else if(jsObj.task == "getDistrictsByStateId"){
									buildOptions(myResults,"districtSel","Select District");
								}
								else if(jsObj.task == "constituenciesInDistrict" || jsObj.task == "getConstNotInGivenAreaType"){
									buildSelectOptions(myResults,"constituencySel","Select Assembly Constituency");
								}
								else if(jsObj.task == "subRegionsInConstituency"){
									buildSelectOptions(myResults,"mandMunCorpGmcSel","Select Mandal/Municipality/Corp/GMC");
								}
								else if(jsObj.task == "hamletsOrWardsInRegion"){
									buildSelectOptions(myResults,"vilWrdDivSel",jsObj.desc);
								}
								else if(jsObj.task == "boothsInTehsilOrMunicipality"){
									buildSelectOptions(myResults,"boothNoSel","Select Booth");
								}
								else if(jsObj.task == "showSelectedDateEvent"){
									showUpdateData(myResults);
								}  
								else if(jsObj.task == "createEvent"){
									 $("#successmsg").html("Event created Successfully");
									 $('html, body').animate({ scrollTop: $("#successmsg").offset().top }, "slow");
									 
										window.opener.refreshParent();
									
								}
								else if(jsObj.task == "updateCreateEvent"){
									 $("#successmsg").html("Event updated Successfully");
									 $('html, body').animate({ scrollTop: $("#successmsg").offset().top }, "slow");
									 window.opener.refreshParent();
								}
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
function buildOptions(result,id,initialVal){
  $("#"+id).find("option").remove();
  if(initialVal != null)
  $("#"+id).append('<option value=0>'+initialVal+'</option>');
  if(result != null && result.length >0){
    for(var i in result){
	  if(result[i].ids != 0)
       $("#"+id).append('<option value='+result[i].ids+'>'+result[i].names+'</option>');
     }
  }
}
function buildSelectOptions(result,id,initialVal){
  $("#"+id).find("option").remove();
  if(initialVal != null)
  $("#"+id).append('<option value=0>'+initialVal+'</option>');
  if(result != null && result.length >0){
    for(var i in result){
	   if(result[i].id != 0)
          $("#"+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
	}
}
function buildStates(myResults)
{
	$('#stateSel').append('<option value=0>Select State</option>');
	for( i in myResults)
	{
	$('#stateSel').append('<option value='+myResults[i].id+'>'+myResults[i].name+'</option>');
	}
}
function getDistParlConsti(){
  clearSelectItems(false,true,true,true,true,true);
  var stateId = $("#stateSel option:selected").val();
  if(stateId == 0)
  return ;
  var selId = $("#locationLvl option:selected").val();
   if(selId == 10){
     getPariamentConstituencies();
   }else{
     getDistrictsInAState();
   }
}
function getAssemblyConstis(){
  clearSelectItems(false,false,true,true,true,true);
  var districtId = $("#districtSel option:selected").val();
  if(districtId == 0)
  return ;
  var selId = $("#locationLvl option:selected").val();
   if(selId == 4 || selId == 9)
     getConstituencies();
	else{
	 if(selId == 5 || selId == 6)
	   getOtherConstituencies("URBAN");
	  if(selId == 7 || selId == 8)
	   getOtherConstituencies("RURAL");
	} 
}
function getSubEleInConstis(){
  clearSelectItems(false,false,false,true,true,true);
  var constituencyId = $("#constituencySel option:selected").val();
  if(constituencyId == 0)
  return ;
  var selId = $("#locationLvl option:selected").val();
       getConstituencieSubs(selId);

}
function selectVillgWardBooth(){
   clearSelectItems(false,false,false,false,true,true);
   var mandMunCorpGmcId = $("#mandMunCorpGmcSel option:selected").val();
  if(mandMunCorpGmcId == 0)
  return ;
  var selId = $("#locationLvl option:selected").val();
    if(selId == 6){
       getVillageWard("Select Village");
	  }
	else if(selId == 8){
	  getVillageWard("Select Ward");
	 }
	else if(selId == 9){
       getBooth();
	  }
}
var cadreType;	
function getCadreDetails(type)
{	
    cadreType = type;
	var urlStr = "cadreSearchAction.action?windowTask=Search&addProblem=false";
	var cadreSearchForProblem = window.open(urlStr,"cadreSearchAndSMSPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");	
	cadreSearchForProblem.focus();
}
 function setSelectedOrganizers(data){
     if(cadreType == "organisor")
       setOrganizers(data);
	 else
       setPlanOrganizers(data);
      
 }
 function setOrganizers(data){

    if(data != null && data.length > 0){
	  if(allOrgnisers != null && allOrgnisers.length > 0){
	     for(var k in data){
		   var count = 0;
	       for(var l in allOrgnisers){
		     if(data[k].id == allOrgnisers[l].id)
			   count++;
		    }
			if(count == 0)
			 allOrgnisers.push(data[k]);
	     }
	   }else{
	     allOrgnisers = data;
	   }
	 }

	 if(allOrgnisers != null && allOrgnisers.length > 0){
	   var str = "";
	   str+="<table class='table table-bordered addedOrganisersTable' >";
	   str+="  <tr>";
	   str+="    <th>Organiser Name</th>";
	   str+="    <th>Remove Organiser</th>";
	   str+="  </tr>";
       for(var i in allOrgnisers){
	       str+="<tr>";
           str+="  <td>"+allOrgnisers[i].name+"</td>";
		   str+="  <td><input id='organiser"+allOrgnisers[i].id+"' class='btn' type='button' onclick='removeOrganiser(this.id,"+allOrgnisers[i].id+",\"org\");' value='Remove' class='removeOrganiser'/></td>";
		   str+="</tr>";
       }
	   str+="</table>";
	   $("#addedOrganisers").html(str);
     }
 }
 function setPlanOrganizers(data){
    
    if(data != null && data.length > 0){
	  if(planOrgnisers != null && planOrgnisers.length > 0){
	     for(var k in data){
		   var count = 0;
	       for(var l in planOrgnisers){
		     if(data[k].id == planOrgnisers[l].id)
			   count++;
		    }
			if(count == 0)
			 planOrgnisers.push(data[k]);
	     }
	   }else{
	     planOrgnisers = data;
	   }
	 }
	 if(planOrgnisers != null && planOrgnisers.length > 0){
	   var str = "";
	   str+="<table class='table table-bordered addedPlanOrganisersTable'>";
	   str+="  <tr>";
	   str+="    <th>Action Plan Organiser Name</th>";
	   str+="    <th>Remove Organiser</th>";
	   str+="  </tr>";
       for(var i in planOrgnisers){
	       str+="<tr>";
           str+="  <td>"+planOrgnisers[i].name+"</td>";
		   str+="  <td><input id='planorganiser"+planOrgnisers[i].id+"' type='button' onclick='removeOrganiser(this.id,"+planOrgnisers[i].id+",\"plan\");' value='Remove' class='removePlanOrganiser'/></td>";
		   str+="</tr>";
       }
	   str+="</table>";
	   $("#addedPlanOrganisers").html(str);
     }
 }
 function removeOrganiser(id,useId,type){
    if(confirm("Do you want to Delete this Organizer?")){
        $("#"+id).closest("tr").remove();
	  if(type == 'org'){
        for(var i in allOrgnisers){
	      if(allOrgnisers[i].id == useId)
	       allOrgnisers.splice(i,1);
	    }
	  }else{
	     for(var i in planOrgnisers){
	      if(planOrgnisers[i].id == useId)
	       planOrgnisers.splice(i,1);
	    }
	  }
     }
 }
function addNewAction(){
         $("#actionPlanError").html("");
        var actionPlanValue = $("#actionPlanName").val();
		if($.trim(actionPlanValue).length == 0){
		   $("#actionPlanError").html("Action Plan is required");
		   return;
		}
		var targetDateValue = $("#actionPlanDate").val();
        actionPlanArrayId = actionPlanArrayId+1;
	    var planId = "planId"+actionPlanArrayId;
        var actionObj = {
		                  userEventsPlanId:'',
		                  planId:planId,
					      action:actionPlanValue,
					      targetDate:targetDateValue,
					      actionPlanOrganizers:planOrgnisers			
						};
		actionPlanArray.push(actionObj);
		var str = '<div id="'+planId+'" style="clear:both;font-weight:bold;padding:5px;background-color:#F2DEDE;width:480px;height:18px;">';
		   str += '  <span style="float:left;margin-left:10px;">'+actionPlanValue+' - '+targetDateValue+'</span>';
		   str += '  <span style="float:right;margin-right:10px;">';
		   str += '     <a href="javascript:{}" onclick ="editActionPlan(\''+planId+'\');">Edit</a> |';
		   str += '     <a href="javascript:{}" onclick ="deleteActionPlan(\''+planId+'\');">Delete</a>';
		   str += '  </span>';
		   str += '</div>';
		$("#actionPlanPlans").append(str);
     planOrgnisers = new Array();
	 $("#actionPlanName").val("");
	 $("#addedPlanOrganisers").html("");
	 var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
	 $("#actionPlanDate").val(date);
} 
function deleteActionPlan(planId){
 if(confirm("Do you want to Delete this Action Plan?")){
   for(var i in actionPlanArray){
     if(actionPlanArray[i].planId == planId){
	   actionPlanArray.splice(i,1);
	   $("#"+planId).remove();
	 } 
   }
      $("#addactionbutton").html('<input type="button" onclick="addNewAction();" class="btn" value="Add Action Plan " />');
      planOrgnisers = new Array();
	  $("#actionPlanName").val("");
	  $("#addedPlanOrganisers").html("");
	  var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
	  $("#actionPlanDate").val(date);
  }
}
function editActionPlan(planId){
  for(var i in actionPlanArray){
   if(actionPlanArray[i].planId == planId){
         $("#addedPlanOrganisers").html("");
		 planOrgnisers = new Array();
	     $("#actionPlanName").val(actionPlanArray[i].action);
		 $("#actionPlanDate").val(actionPlanArray[i].targetDate);
		 setPlanOrganizers(actionPlanArray[i].actionPlanOrganizers);
		 $("#addactionbutton").html('<input type="button" onclick="updateActionPlan(\''+planId+'\');" class="btn" value="Update Action Plan " />');
		 $("#actionPlanName").focus();
	} 
  }

}
function updateActionPlan(planId){
      $("#actionPlanError").html("");
        var actionPlanValue = $("#actionPlanName").val();
		if($.trim(actionPlanValue).length == 0){
		   $("#actionPlanError").html("Action Plan is required");
		   return;
		}
  for(var i in actionPlanArray){
   if(actionPlanArray[i].planId == planId){
	     actionPlanArray[i].action = $("#actionPlanName").val();
		 actionPlanArray[i].targetDate = $("#actionPlanDate").val();
		 actionPlanArray[i].actionPlanOrganizers = planOrgnisers;
		  var str = '';
		   str += '  <span style="float:left;margin-left:10px;">'+$("#actionPlanName").val()+' - '+$("#actionPlanDate").val()+'</span>';
		   str += '  <span style="float:right;margin-right:10px;">';
		   str += '     <a href="javascript:{}" onclick ="editActionPlan(\''+planId+'\');">Edit </a> |';
		   str += '     <a href="javascript:{}" onclick ="deleteActionPlan(\''+planId+'\');">Delete</a>';
		   str += '  </span>';
		 
		$("#"+planId).html(str);
	} 
  }
      
  $("#addactionbutton").html('<input type="button" onclick="addNewAction();" class="btn" value="Add Action Plan " />');
     planOrgnisers = new Array();
	 $("#actionPlanName").val("");
	 $("#addedPlanOrganisers").html("");
	 var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
	 $("#actionPlanDate").val(date);
}

function handleSubmit(type)
	{
	     $("#errorMsgDiv").html("");
		 var errorReq = false;
		 var str = '';
		var eventNameVal = document.getElementById("eventNameText").value;
		var startDateVal = document.getElementById("startDateText_new").value;
		var endDateVal = document.getElementById("endDateText_new").value;
		var startDateValArry = startDateVal.split("/");
		var endDateValArry = endDateVal.split("/");
		var startdate = new Date(startDateValArry[2], parseInt(startDateValArry[1])-1, startDateValArry[0]);
		var startNewDate = startdate.getTime();
		var enddate = new Date(endDateValArry[2], parseInt(endDateValArry[1])-1, endDateValArry[0]);
		var endNewDate = enddate.getTime();
		var startTimeHrs = document.getElementById("startTimeHrs");
		var startTimeHrsVal = startTimeHrs.options[startTimeHrs.selectedIndex].text;
		//alert(startTimeHrsVal);
		var startNewTimeHrsVal = parseInt(startTimeHrsVal);
		//alert(startNewTimeHrsVal)
		var startTimeMin = document.getElementById("startTimeMin");
		var startTimeMinVal = startTimeMin.options[startTimeMin.selectedIndex].text;
		var endTimeHrs = document.getElementById("endTimeHrs");	
		var endTimeHrsVal = endTimeHrs.options[endTimeHrs.selectedIndex].text;
		var endNewTimeHrsVal = parseInt(endTimeHrsVal);
		var endTimeMin = document.getElementById("endTimeMin");
		var endTimeMinVal = endTimeMin.options[endTimeMin.selectedIndex].text;
		var descVal = document.getElementById("descTextArea").value;
		descVal = removeEnterStrokeForString(descVal);
        var errorMsg = document.getElementById("errorMsgDiv");
		var scopeId = $('#locationLvl :selected').val();
		var locationLevelFieldval = $('#locationLvl :selected').text();
        var stateValue = $('#stateSel :selected').val();
        var districtValue = $('#districtSel :selected').val();
        var constituencyValue = $('#constituencySel :selected').val();
        var mandalValue = $('#mandMunCorpGmcSel :selected').val();
        var villageValue = $('#vilWrdDivSel :selected').val();
        var boothVal = $('#boothNoSel :selected').val();
		var locationValue;
		var targetDateVal = document.getElementById("endDateText_new").value;
		var targetDateValArry = targetDateVal.split("/");
		var targetdate = new Date(targetDateValArry[2], parseInt(targetDateValArry[1])-1, targetDateValArry[0]);
		 
		//validation code
		if(eventNameVal == '' ){
			str+= "Please enter Event Name.<br/>";
			 errorReq = true;
		}
		else if ( /[^A-Za-z\d\s]/.test(eventNameVal))
		{
		  str+=  " Event Name should not contain special characters & numbers.</br>";
		   errorReq = true;
		}
		/*else if(startdate > enddate){
		  str+= "Please select A valid date for End date.<br/>";
		   errorReq = true;
		}*/
		if(startNewDate == endNewDate)
		{
			if(startNewTimeHrsVal > endNewTimeHrsVal)
			{
				str+= "Please Select A Valid End Time<br/>";
				errorReq = true;
		    }
			else if(startTimeHrsVal == endTimeHrsVal)
			{
				if(startTimeMinVal >= endTimeMinVal)
				{
					str+= "Please Select A Valid End Time<br/>";
					errorReq = true;
				}
			}
		}
		if(targetdate < startdate)
		{
			str+= "Please Select A Valid End Date.<br/>";
			errorReq = true;
		}
		if(targetdate > enddate)
		{
			str+= "Please Select A Valid End Date.<br/>";
			errorReq = true;
		}
		if(scopeId == 0 ){
			str+= "Please select Location Level.<br/>";
			errorReq = true;
		}
	 if(type == "create" || (type == "update" && edited))
	 {
		if(scopeId == 2 && (stateValue == 0 || stateValue == undefined))
		{			
			str+= 'Please select State.<br/>';
			 errorReq = true;
		}
		if(scopeId == 3 && (districtValue == 0 || districtValue == undefined))
		{
			str+='Please select District.<br/>';
			errorReq = true;
		}
		if((scopeId == 4 || scopeId == 10) && (constituencyValue == 0 || constituencyValue == undefined))
		{
			str+='Please select Constituency.<br/>';
			errorReq = true;
		}
		if((scopeId == 5 || scopeId == 7) && (mandalValue == 0 || mandalValue == undefined))
		{
			str+='Please select Mandal/Municipality/Corp/GMC.<br/>';
			errorReq = true;
		}
		if((scopeId == 6 || scopeId == 8) && (villageValue == 0 || villageValue == undefined))
		{
			str+='Please select Village/Ward/Division.<br/>';
			errorReq = true;
		}
		if(scopeId == 9 && (boothVal == 0 || boothVal == undefined))
		{
			str+='Please select Booth No.<br/>';
			errorReq = true;
		}
		
		
		if(scopeId == 2)
		{			
			locationValue = stateValue;
		}
		else if(scopeId == 3)
		{
			locationValue = districtValue
		}
		else if(scopeId == 4 || scopeId == 10)
		{
			locationValue = constituencyValue;
		}
		else if(scopeId == 5 || scopeId == 7)
		{
			locationValue = mandalValue;
		}
		else if(scopeId == 6 || scopeId == 8)
		{
			locationValue = villageValue;
		}
		else if(scopeId == 9)
		{
			locationValue = boothVal;
		}
    }else{
	  locationValue = prevLocationVal;
	}
	    if(descVal == '')
		{
		  str += "Please enter Description.";
		  errorReq = true;
		}
		if(errorReq){
		  errorMsg.innerHTML = str;
          $('html, body').animate({ scrollTop: $("#errorMsgDiv").offset().top }, "slow");
		  return;
		}
		var selectedEventObj={
							userEventsId:"",
							eventName:"",
							startDate:"",
							endDate:"",
							startTimeHrs:"",
							startTimeMin:"",					
							endTimeHrs:"",
							endTimeMin:"",
							locationType:"",
							locaitonId:"",
							desc:"",
							organizers:"",
							actionPlans:"",
							isDeleted:"",
							task:""
						};
		var selectedEventObj
		if(type == 'create')
		 selectedEventObj.userEventsId="";
		else
		 selectedEventObj.userEventsId='${updateEventId}';
		selectedEventObj.eventName=eventNameVal;
		selectedEventObj.startDate=startDateVal;
		selectedEventObj.endDate=endDateVal;
		selectedEventObj.startTimeHrs=startTimeHrsVal;
		selectedEventObj.startTimeMin=startTimeMinVal;					
		selectedEventObj.endTimeHrs=endTimeHrsVal;
		selectedEventObj.endTimeMin=endTimeMinVal;
		selectedEventObj.locationType=locationLevelFieldval;
		selectedEventObj.locationId=locationValue;
		selectedEventObj.desc=descVal;
		selectedEventObj.organizers=allOrgnisers;
		selectedEventObj.actionPlans=actionPlanArray;
		selectedEventObj.isDeleted="NO";
		if(type == 'create')
		  selectedEventObj.task="createEvent";
		else
		  selectedEventObj.task="updateCreateEvent";
         $("#eventcreationid").attr('disabled','disabled');
		var rparam ="task="+YAHOO.lang.JSON.stringify(selectedEventObj);
		var url = "<%=request.getContextPath()%>/createEventAction.action?"+rparam;		

		callAjaxToGetData(selectedEventObj,url);
	}
   function showSelectedDateEvent()
	{	
		var jsObj={
					eventId:'${updateEventId}',
					eventType:'',	
					taskType:'impEvent',					
					task:"showSelectedDateEvent"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/showImpDateEvent.action?"+rparam;		
		callAjaxToGetData(jsObj,url);
	}
	function showUpdateData(result){
	  if(!result.valid){
	     $("#finalupdatebtns").html('<div style="clear:both;margin-top:40px;margin-bottom:30px;margin-left:100px"> <input id="eventcreationid" style="clear:both;" class="btn" onclick="handleSubmit(\'create\');" type="button" value="Create Event" /> <input style="clear:both;" class="btn" onclick="closepopup();" type="button" value="Cancel" /></div>');
		 return;
	  }
	  $("#pop_header").html("Update Event");
	  $("#eventNameText").val(result.title);
	   $("#descTextArea").val(result.description);
	  var sDayobj = getDateTime(result.startDate); 	  
	  var eDayobj = getDateTime(result.endDate);
	  $("#startDateText_new").val(sDayobj.day+"/"+sDayobj.month+"/"+sDayobj.year);
	$("#endDateText_new").val(eDayobj.day+"/"+eDayobj.month+"/"+eDayobj.year);
	//alert(sDayobj.hours);
	$("#startTimeHrs").val(sDayobj.hours);
	//alert(parseInt(sDayobj.hours));
	$("#startTimeMin").val(parseInt(sDayobj.minutes));
	$("#endTimeHrs").val(parseInt(eDayobj.hours));
	$("#endTimeMin").val(parseInt(eDayobj.minutes));
	$("#locationLvl").val(getId(result.locationType));
	$("#locationSelValue").html("&nbsp;&nbsp;<b>"+result.location+"</b> <input class='btn' type='button' value='Edit' onclick='showReqSelection();'/>");
	  prevLocationVal = result.locationId;
	  if(result.organizers!= null)
	    setOrganizers(result.organizers);
	  if(result.actionPlans != null){
	    for(var i in result.actionPlans){
		  var actionPlanValue = result.actionPlans[i].action;
		  var plnaDate = getDateTime(result.actionPlans[i].targetDate);
		  var targetDateValue = plnaDate.day+"/"+plnaDate.month+"/"+plnaDate.year;
		  var editPlanOrgnisers = new Array();
          actionPlanArrayId = actionPlanArrayId+1;
	      var planId = "planId"+actionPlanArrayId;
		  if(result.actionPlans[i].actionPlanOrganizers != null){
		    for(var k in result.actionPlans[i].actionPlanOrganizers)
		     { 
			  var obj1 = {};
	          obj1["id"] = result.actionPlans[i].actionPlanOrganizers[k].id;
	          obj1["name"] = result.actionPlans[i].actionPlanOrganizers[k].name;
	          editPlanOrgnisers.push(obj1);
			 }
		  }
          var actionObj = {
		                  userEventsPlanId:result.actionPlans[i].eventActionPlanId,
		                  planId:planId,
					      action:actionPlanValue,
					      targetDate:targetDateValue,
					      actionPlanOrganizers:editPlanOrgnisers			
						};
		  actionPlanArray.push(actionObj);
		   var str = '<div id="'+planId+'" style="clear:both;font-weight:bold;padding:5px;background-color:#F2DEDE;width:480px;height:18px;">';
		   str += '  <span style="float:left;margin-left:10px;">'+actionPlanValue+' - '+targetDateValue+'</span>';
		   str += '  <span style="float:right;margin-right:10px;">';
		   str += '     <a href="javascript:{}" onclick ="editActionPlan(\''+planId+'\');">Edit</a> |';
		   str += '     <a href="javascript:{}" onclick ="deleteActionPlan(\''+planId+'\');">Delete</a>';
		   str += '  </span>';
		   str += '</div>';
		$("#actionPlanPlans").append(str);
		}
	  }
	  $("#planOrgButton").click();
	  $("#actionPlanButton").click();
	}
	function getDateTime(date)
	{		
		var startDayStr = date.substring(8,10);		
		var startMonStr = date.substring(5,7);
		var startYearStr = date.substring(0,4);	

		var startTimeHrs = date.substring(11,13);
			//alert(startTimeHrs);
		var startTimeMin = date.substring(14,16);	

		var dateTimeObj={
							day:startDayStr,
							month:startMonStr,
							year:startYearStr,
							hours:startTimeHrs,
							minutes:startTimeMin
						};

		return dateTimeObj;
	}
	
	function getId(textData){
	       if($.trim(textData).toUpperCase(textData) == "STATE")
		    return 2;
		   else if($.trim(textData).toUpperCase(textData) == "DISTRICT")
		    return 3;
		   else if($.trim(textData).toUpperCase(textData) == "ASSEMBLY CONSTITUENCY")
		    return 4;
		   else if($.trim(textData).toUpperCase(textData) == "PARLIAMENT CONSTITUENCY")
		    return 10;
		   else if($.trim(textData).toUpperCase(textData) == "MANDAL/TEHSIL")
		    return 5;
		   else if($.trim(textData).toUpperCase(textData) == "VILLAGE")
		    return 6;
		   else if($.trim(textData).toUpperCase(textData) == "MUNICIPAL-CORP-GMC")
		    return 7;
		   else if($.trim(textData).toUpperCase(textData) == "WARD")
		    return 8;
		   else if($.trim(textData).toUpperCase(textData) == "BOOTH")
		    return 9;
	}
	function clearSelectItems(state,district,consti,mandal,village,booth){
	  if(state)
	    $("#stateSel").find("option").remove();
	  if(district)
        $("#districtSel").find("option").remove();
	  if(consti)
		$("#constituencySel").find("option").remove();
	  if(mandal)
		$("#mandMunCorpGmcSel").find("option").remove();
	  if(village)
		$("#vilWrdDivSel").find("option").remove();
	  if(booth)
		$("#boothNoSel").find("option").remove();
	}
	function closepopup(){
	   window.close();
	}
</script>
</head>
<body>
<c:if test="${!notLogged}">
<form>
<center><div id="pop_header" style="clear:both; margin-left: 180px;margin-bottom:14px;">Create New Event</div></center>
    <div style="clear:both;" id="successmsg"></div>
    <div style="clear:both;" id="errorMsgDiv"></div>
    <div style="clear:both;">
		<span class="titleStyle">Event Name<font class="requiredFont"> * </font> : </span> <input type="text" id="eventNameText" />
	</div>
	<div>
	    <span class="titleStyle">Start Date<font class="requiredFont"> * </font> : </span>
		<input type="text" id="startDateText_new" readonly="readonly" name="startDateText" value="" style= "cursor:text"/>
		</div>
		<div>
	    
		<span class="titleStyle" style="margin-top:10px;">End Date<font class="requiredFont"> * </font> : </span>
		<input type="text" id="endDateText_new" readonly="readonly" name="endDateText" value="" style= "cursor:text"/>
		
	</div>
	
	<div class="">
		<span class="titleStyle">Start Time<font class="requiredFont"> * </font> : </span>
	
		   <select id="startTimeHrs" class="timeSelect">	
		   </select>
        
		
		   <select id="startTimeMin" class="timeSelect">
		      <option>00</option>		
		      <option>15</option>
		      <option>30</option>
		      <option>45</option>		
		    </select>
	</div><br/>
	<div>
		
		<span style="margin-left:10px;width:86px;" class="titleStyle">End Time<font class="requiredFont"> * </font> : </span>
		
		
		   <select id="endTimeHrs" class="timeSelect">	
		   </select>
        
		
		   <select id="endTimeMin" class="timeSelect">
		      <option>00</option>		
		      <option>15</option>
		      <option>30</option>
		      <option>45</option>		
		    </select>
		
	</div>
	<div  style="width:90%">
	  <span class="titleStyle">Location Level<font class="requiredFont"> * </font> : </span>
	  
		   <select id="locationLvl" onchange="showReqSelection();" class="locationLvl">
		      <option value="0">Select Location Level</option>
              <option value="2">STATE</option>
              <option value="3">DISTRICT</option>
              <option value="4">ASSEMBLY CONSTITUENCY</option>
			  <option value="10">PARLIAMENT CONSTITUENCY</option>
              <option value="5">MANDAL/TEHSIL</option>
              <option value="6">VILLAGE</option>
              <option value="7">MUNICIPAL-CORP-GMC</option>
              <option value="8">WARD</option>
              <option value="9">BOOTH</option>	
		   </select>
	</div>
	<div style="clear:both;">
	   <span style="margin-left:170px;" id="locationSelValue"></span>
	</div>
		<br/>
	<div id="stateDiv" class="hidediv topspace"  style="clear:both;">
	  <span class="titleStyle">State<font class="requiredFont"> * </font> : </span>
	
		   <select id="stateSel" onchange="getDistParlConsti();" class="selectboxstyle stateSel">
		   </select><br/>
	 
	</div>
	<div id="districtDiv" class="hidediv topspace">
	  <span class="titleStyle">District<font class="requiredFont"> * </font> : </span>
	  
		   <select id="districtSel" onchange="getAssemblyConstis();" class="selectboxstyle districtSel">
		   </select><br/>
	 
	</div>
	<div id="constituencyDiv" class="hidediv topspace">
	  <span class="titleStyle">Constituency<font class="requiredFont"> * </font>  : </span>
	  
		   <select id="constituencySel" onchange="getSubEleInConstis();" class="selectboxstyle constituencySel">
		   </select><br/>
	 
	</div>
	<div id="mandMunCorpGmcDiv" class="hidediv topspace">
	  <span class="titleStyle">Mandal/Municipality/Corp/GMC<font class="requiredFont"> * </font> : </span>
	  
		   <select id="mandMunCorpGmcSel" onchange="selectVillgWardBooth();" class="selectboxstyle mandMunCorpGmcSel">
		   </select><br/>
	  
	</div>
	<div id="vilWrdDivDiv" class="hidediv topspace">
	  <span class="titleStyle">Village/Ward/Division<font class="requiredFont"> * </font> : </span>
	
		   <select id="vilWrdDivSel" class="selectboxstyle vilWrdDivSel">
		   </select><br/>
	  
	</div>
	<div id="boothNoDiv" class="hidediv topspace">
	  <span class="titleStyle">Booth No<font class="requiredFont"> * </font> : </span>
	  
		   <select id="boothNoSel" class="selectboxstyle boothNoSel">
		   </select>
	  
	</div>
	<div style="clear:both;">
	     <span class="titleStyle">Description<font class="requiredFont"> * </font> : </span>
	     <textarea id="descTextArea"></textarea>
	</div>
	<div  style="background:#ffffff;clear:both;width:100%;">
	<a id="planOrgButton" href="javascript:{}"><div style="clear:both;font-weight:bold;margin-top:0px;padding-left:5px;padding-top:5px;background-color:#DBE4E9;width:99%;height:30px;">Add Organisers</div></a>
	<div id="showplan" style="display:none;clear:both;padding-bottom:10px;">
	  <div id="addedOrganisers" style="margin-left:10px;"></div>
	  <div style="clear:both;float:left;">
	   <input type="button" onclick="getCadreDetails('organisor');" class="btn" value="Add Organizers" />
      </div>
	</div>
   </div>
	<div style="background:#ffffff;clear:both;width:100%;">
	<a id="actionPlanButton" href="javascript:{}"><div style="clear:both;font-weight:bold;margin-top:0px;padding-left:5px;padding-top:5px;background-color:#DBE4E9;width:99%;height:30px;">Add Action Plan</div></a>
	
	<div id="showactionplan" style="display:none;clear:both;padding-bottom:20px;padding-left: 10px;">
	  <div id="actionPlanPlans" style="clear:both;margin-top:10px;margin-left:37px;width:100%"></div>
	  <div id="actionPlanError"></div>
	  <div style="clear:both;">
		<span class="titleStyle">Action Plan<font class="requiredFont"> * </font> : </span> <input type="text" id="actionPlanName" />
	  </div>
	  <div style="clear:both;">
		<span class="titleStyle">Target Date<font class="requiredFont"> * </font> : </span> <input type="text" id="actionPlanDate" readonly="readonly" name="planDateText" value="" />
	  </div>
	  <div id="addedPlanOrganisers"  style="margin-left:10px;"></div>
	  <div style="clear:both;margin-top:10px;margin-left:37px">
	     <span id="addactionbutton"><input type="button" onclick="addNewAction();" class="btn" value="Add Action Plan " /></span>
	     <input type="button" style="margin-left:10px;" onclick="getCadreDetails('plan');" class="btn" value="Add Organisers To Action Plan " />
	  </div>
	</div>
    </div>
	<div id="finalupdatebtns">
	<c:if test="${!updateEvent}">
	  <div style="clear:both;margin-top:40px;margin-bottom:30px;margin-left:100px"> <input id="eventcreationid" style="clear:both;" class="btn" onclick="handleSubmit('create');" type="button" value="Create Event" /> <input style="clear:both;" class="btn" onclick="closepopup();" type="button" value="Cancel" /></div>
	</c:if>
	<c:if test="${updateEvent}">
	  <div style="clear:both;margin-top:40px;margin-bottom:30px;margin-left:100px"> <input id="eventcreationid" style="clear:both;" class="btn" onclick="handleSubmit('update');" type="button" value="Update Event" /> <input style="clear:both;" class="btn" type="button" onclick="closepopup();" value="Cancel" /></div>
	</c:if>
    </div>
	</form>
</c:if>
<c:if test="${notLogged}">
  <div style="font-weight:bold;margin-left: 200px;margin-top: 100px;">Your Session Expired. Please Log In To Proceed </div>
</c:if>
</body>
</html>
<script>
 for(var i=0;i<=23;i++)
  {
	if(i<=9)
	{
	$("#startTimeHrs").append('<option value=0'+i+'>0'+i+'</option>');
    $("#endTimeHrs").append('<option value=0'+i+'>0'+i+'</option>');
	}
	else
	{
    $("#startTimeHrs").append('<option value='+i+'>'+i+'</option>');
    $("#endTimeHrs").append('<option value='+i+'>'+i+'</option>');
	}
  }
  
<c:if test="${updateEvent}">
 showSelectedDateEvent();
</c:if>
 <c:if test="${!updateEvent}">
				$("#startTimeHrs").val(9);
				$("#endTimeHrs").val(17);				
	            $("#startTimeMin").val(30);	
	            $("#endTimeMin").val(30);
</c:if>
</script>