<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ministers Analysis</title>

<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/button/button-min.js"></script> 
	<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript"></script>
<script type="text/javascript"> 
$(document).ready(function(){
$(".flip").click(function(){
    $(".panel").slideToggle("slow");
  });
});
</script>
<style type="text/css">
.main-mbg {
    background-color: #06ABEA;
    border-radius: 7px 7px 7px 7px;
    color: #FFFFFF;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 977px;


}

div.panel,#toggleid.flip
{
margin:0px;
padding:5px;
text-align:center;
background:#EEF4F6;
border:solid 1px #c3c3c3;
}
div.panel
{
height:auto;
display:none;
}

#DataTable > table * th{background-color:#ceedf0 !important;}
</style>
<script type="text/javascript">


var qtype = "minis";
function getElectionYears(electionType)
{
   stateId = 1;
   if(electionType == "Assembly")
   {
	var stateEle = document.getElementById("stateListId");
    stateId = stateEle.options[stateEle.selectedIndex].value;
	document.getElementById("showData").innerHTML = "";
    document.getElementById("keyCandidatesData").innerHTML = "";
	if(stateId == 0)
	  {
	    removeData("yearSelId");
        addData("yearSelId");
	    return ;
	  }
   }
	removeData("yearSelId");
	var jObj = {
			stateId : stateId,
		electionType: electionType,
				task: 'getElectionYearsForAState'
				};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
	callAjax(jObj,url);
}
  function showHidsState()
    {
      if(document.getElementById("state").checked == true)
	      document.getElementById("showHideState").style.display ="block";
	  else
	      document.getElementById("showHideState").style.display ="none";
    }
 function getAllStates()
   {    
    var jsObj =
		{ 
            time : new Date().getTime(),
			eleType: 2,
			task:"getStatesForAssign"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
	callAjax(jsObj,url);
  }
  
  
function navigateToMinisterPage()
{
	var electionSelectEl = document.getElementById("yearSelId");
	var electionIdVal = electionSelectEl.options[electionSelectEl.selectedIndex].value
	window.location="ministersPageAction.action?electionId="+electionIdVal; 
}
  function getStatesForAssembly()
  {
    var jsObj =
		{ 
            time : new Date().getTime(),
			task:"getAllStatesForParliamentMinisters"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
	callAjax(jsObj,url);
  }
  function getYearsForAssembly()
  {
  
    var stateEle = document.getElementById("stateListId");
    var stateId = stateEle.options[stateEle.selectedIndex].value;
	removeData("yearSelId");
    addData("yearSelId");
    if(stateId == 0)
     return;	
    var jsObj =
		{ 
            time : new Date().getTime(),
			stateId: stateId,
			task:"getAllYearsAndElecIdsForAssembly"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
	callAjax(jsObj,url);
  }
  function getYearsForParliament()
  {
	var jsObj =
		{ 
            time : new Date().getTime(),
			task:"getAllYearsAndElecIdsForParliament"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsForParliamentAction.action?"+rparam;						
	callAjax(jsObj,url);
  }

 function callAjax(jsObj,url)
   {
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									
									if(jsObj.task == "getMinistryYears")
									{
									      removeData("yearSelId");
									      buildData(myResults,"yearSelId");
									}
									else if(jsObj.task == "getStatesForAssign")
									{
									      removeData("stateListId");
										  addState("stateListId");
									      buildData(myResults,"stateListId");
									}
									else if(jsObj.task == "getAllStatesForParliamentMinisters")
									{
									      removeData("stateListId");
										  addState("stateListId");
									      buildData(myResults,"stateListId");
									}
									else if(jsObj.task == "getAllYearsAndElecIdsForAssembly")
									{
									      removeData("yearSelId");
										  addData("yearSelId");										  
									      buildData(myResults,"yearSelId");
									}
									else if(jsObj.task == "getAllYearsAndElecIdsForParliament")
									{
									       removeData("yearSelId");
										   //addData("yearSelId");
									      buildData(myResults,"yearSelId");
									}
									/*else if(jsObj.task == "getMinsKeyCandAnalysisDetails")
									{
									   document.getElementById("select_ImgSpan").style.display="none";
									   if(jsObj.reqtype == "ImportantCandidates")
									      buildDataTable(myResults,"keyCandidatesData");
									   else
									      buildDataTable(myResults,"showData");
									}*/
								}
							catch (e) {   
							   //	alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
  }
 function buildData(results,id)
 {
   var elmt = document.getElementById(id);
   
       for(var i in results)
	  {
		var option = document.createElement('option');     
		
		    option.value=results[i].id;
		    option.text=results[i].name;       
        
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
 function showOthers()
 {
	removeData("yearSelId");
	addData("yearSelId");
	document.getElementById("stateListId").value = 0;
 }
 function getDetails(reqtype)
 {
       document.getElementById("showData").innerHTML = "";
       document.getElementById("keyCandidatesData").innerHTML = "";
     var yearEle =  document.getElementById("yearSelId");
	 var eleId = yearEle.options[yearEle.selectedIndex].value;
	 if(eleId == 0)
	  return;
	 document.getElementById("select_ImgSpan").style.display="block";
	 var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			reqtype:reqtype,
			task:"getMinsKeyCandAnalysisDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
 }
 function removeData(elmtId)
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
 function addData(id)
 {
  
    var elmt = document.getElementById(id);
   
		var option = document.createElement('option');     
		
		    option.value= 0;
		    option.text= "Select Year";       
        
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		} 
 
 
 }
 function addState(id)
 {
  
    var elmt = document.getElementById(id);
   
		var option = document.createElement('option');     
		
		    option.value= 0;
		    option.text= "Select State";       
        
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		} 
 
 
 }

function showMinisPerf(id)
{
   qtype = "minis";
  $("#impCandPerf").removeClass("dashBoardtabsDivSelected");
  $("#minisPerf").addClass("dashBoardtabsDivSelected");
  document.getElementById("showData").innerHTML = "";
  document.getElementById("keyCandidatesData").innerHTML = "";
  document.getElementById("state").checked = true;  
  document.getElementById("showHideState").style.display = "block";
  getStatesForAssembly();
}
function showImpCandPerf(id)
{
   qtype = "impCand";
  $("#minisPerf").removeClass("dashBoardtabsDivSelected");
  $("#impCandPerf").addClass("dashBoardtabsDivSelected");
  document.getElementById("showData").innerHTML = "";
  document.getElementById("keyCandidatesData").innerHTML = "";
  document.getElementById("state").checked = true;
  document.getElementById("showHideState").style.display = "block";
  getAllStates();
}
function getYears(type)
{
  if(qtype == "minis")
   { 
    if(type == "Assembly")
	getMinistryYears();
	else
      getYearsForParliament();
   }
   /*else 
    getElectionYears(type);*/
}
function getMinistryYears()
{

	var selectScopeRadio = document.getElementsByName("selectScope");
	var electionType;
	var stateIdEle = document.getElementById("stateListId");
	var stateId = stateIdEle.options[stateIdEle.selectedIndex].value;
	
	for(var i=0;i<selectScopeRadio.length;i++)
	{
		if(selectScopeRadio[i].checked == true)
			electionType = selectScopeRadio[i].value;
		
	}
		
	
	var jsObj =
		{ 
            time : new Date().getTime(),
			electionType:electionType,
			stateId : stateId,
			task:"getMinistryYears"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMinisterDataAvailableYearsForAState.action?"+rparam;						
	callAjax(jsObj,url);
}

function getDeltailForMinisImpCand()
{
   if(qtype == "minis")
     getDetails('ministers');
   else 
    getDetails('ImportantCandidates');
}
function removeDataDIV()
{
    document.getElementById("showData").innerHTML = "";
	document.getElementById("keyCandidatesData").innerHTML = "";
}


</script>
</head>
<body>


<div style="width:998px;padding-left:5px;">
 <s:iterator status="stat" value="electionGoverningBodyVO" var="ministerDetails">
   <div style="padding-left:5px;"><div class="main-mbg">

		
			  
		<s:property value="stateName"/> Ministers Of <s:property value="electionYear"/> </s:iterator>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span> SELECT OTHER OPTIONS : <input id="toggleid" type="button" class="flip" value="View" style=" -moz-border-radius: 5px;cursor:pointer;font-weight:bold;font-size:14px;"/></span>
 <span style="margin-top:10px;margin-right:30px;float:right">
<g:plusone size="medium"></g:plusone>

<script type="text/javascript">
  (function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
  })();
</script>
</span>
<span style="margin-top:10px;float:right">
<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/ministersPageAction.action?electionId=${electionId}">
Tweet</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
</script>
</span>

 
		<span style="margin-top:10px;margin-right:18px;float:right">
 <a name="fb_share" share_url="www.partyanalyst.com/ministersPageAction.action?electionId=${electionId}" type="button_count">Share in Facebook</a> 
<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript">
</script>
</span>
		</div>
			 
	</div>

        
   <div style="padding-top:10px;padding-left:250px;text-align:center;background:#EEF4F6;width: 732px;
margin-left:5px;" class="panel">
     <table>  
	  <tr>
	      <td><input type="radio" id="state" checked="true" name="selectScope" value="Assembly" onclick="removeDataDIV();showHidsState();showOthers();" />&nbsp;&nbsp;<b>Assembly</b></td>
	      <td><input type="radio" name="selectScope"  id="parlSel" value="Parliament" onchange="removeDataDIV();showHidsState(); getMinistryYears();"/>&nbsp;&nbsp;<b>Parliament</b>&nbsp;&nbsp;</td>
		  <td><div id="showHideState" style="display:none;"><b>&nbsp;&nbsp;Select State :</b>&nbsp;&nbsp;<select  id="stateListId" value="2"  onchange="getMinistryYears();"><option value="0">Select State</option></select></div></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Year :</b>&nbsp;&nbsp;<select id="yearSelId"  onchange="removeDataDIV();getDeltailForMinisImpCand();" ><option value="0">Select Year</option></select></td>
		  
		<td>&nbsp;&nbsp;&nbsp;&nbsp;<b><a onclick="navigateToMinisterPage()"><input type="button" value="View" style="-moz-border-radius: 5px;cursor:pointer;font-weight:bold;font-size:14px;color:#ffffff;background:#06ABEA;border:solid 1px #c3c3c3;"/></a></b></td>
      </tr>
	 </table>
	 <table>
	   <tr>
	     <td><span id="select_ImgSpan" style="padding-left:206px;padding-top:5px;display:none;"><img src="images/icons/partypositions.gif"></span></td>
	   </tr>
	 </table>
   </div>
   <div style="padding-top:10px;">
     <div style="width:980px;" id="showData" />
   </div>
   <div style="padding-bottom:30px;">
     <div style="width:980px;" id="keyCandidatesData" />
  



 <div id="DataTable" style="background:#ffffff;">
   <table border="1" style="border-collapse: collapse; margin-top: 9px; width: 97%; margin-left: 7px;text-align:center;">

<th width="120px">
Candidate Name
</th>
<th width="80px">
Party
</th>
<th width="300px">
Ministry
</th>
<th width="90px">
From Date
</th>
<th width="90px">
To Date
</th>
</tr>

<c:forEach var="ministerData" varStatus="stat" items="${candidateMinistriesVO}">
<tr>

<td rowspan="${ministerData.noOfMinistries}"><b><a href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}"> <img src="images/candidates/${ministerData.candidateName}.jpg" width="113px" height="85px"/><br> ${ministerData.candidateName}</a></b>

</td>

<td rowspan="${ministerData.noOfMinistries}"><b><a href="partyPageAction.action?partyId=${ministerData.partyId}"><img src="images/party_flags/${ministerData.partyName}.png" width="80px" height="60px"/><br>  ${ministerData.partyName}</a></b>
</td>

<c:forEach var="ministry" varStatus="stat" items="${ministerData.ministries}">
<td style="text-align:left;">
<b> ${ministry.ministry}</b>
</td>

<td>
<b> ${ministry.fromDate}</b>

</td>

<td>
<b>
<c:if test="${ministry.toDate == null}">
Working
</c:if>
<c:if test="${ministry.toDate != null}">
${ministry.toDate}
</c:if>



</b>
</td>

</tr>

</c:forEach>
</c:forEach>

</table>
</div></div>
<script type="text/javascript">
	getStatesForAssembly();
	$("#impCandPerf").removeClass("dashBoardtabsDivSelected");
     $("#minisPerf").addClass("dashBoardtabsDivSelected");
	 showHidsState();
   </script>

   </div>
</body>
</html>