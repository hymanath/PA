<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script>  
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script>  
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script>  
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>   
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css -->  
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<!-- YUI Dependency files (End) -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ASSIGN ${name} To POLITICAN,INFLUENCING PEOPLE,CADRE</title>
<style type="text/css">
 .buttonStyle {
	-moz-border-radius:5px 5px 5px 5px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}
.divInfo
{
 background-color:#FFFFFF;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;
}
.l2 {
color:navy;
font-size:12px;
font-weight:bold;
padding:5px;
}
.f3 {
-moz-border-radius:4px 4px 4px 4px;
border:2px solid #CFD6DF;
margin-bottom:10px;
padding:10px;
width:591px;
}
.textAlignStyle{

 text-align:left;

}
.f3 {
    border: 2px solid #CFD6DF;
    font-family: verdana;
    margin-bottom: 10px;
    margin-left: 9px;
    padding-bottom: 10px;
    padding-top: 10px;
    width: 553px;
}
#candidateName{
	width: 220px;
	position: static;
	margin-left: 40px;
}
#candidateGender
{
	margin-left: 41px;
	padding: 1px;
	width: 225px;
	height:25px;
	margin-bottom:5px;"
	cssstyle="width: 145px;
}
.yui-dt0-col-name .yui-dt-liner {
    overflow: hidden;
    width: 172px;
}
.yui-skin-sam .yui-dt-liner {
    font-family: fantasy;
    font-size: 14px;
    margin: 0;
    padding: 3px 22px;
}
.yui-skin-sam .yui-pg-container {
    display: block;
    margin: 4px 63px;
    white-space: pre-wrap;
}
#mainDiv
{
	color: green;
    font-weight: bold;
    margin-bottom: 20px;
    margin-top: 20px;
}

select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 225px;
}

select, textarea,input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input {
border-radius: 4px 4px 4px 4px;
	color: #000000;
    display: inline-block;
    font-size: 13px;
    line-height: 18px;
    padding: 4px;
}
input, button, select, textarea {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
}
#constituencySelectDiv
{
	margin-left: -10px;
}
</style>
<script type="text/javascript">
var name = '${name}';
/*
	This is a Method used for Ajax Action
*/
function callAjax(jsObj,url)
   {
	var myResults;	
	
	var callback = {			
	
	success : function( o )
	{
		try {
			 if(o.responseText)
				myResults =YAHOO.lang.JSON.parse(o.responseText);	
			 if(jsObj.task == "getStates")
			  {    
				buildResults(myResults,"stateSelect");
			  }
			else if(jsObj.task == "getConstituencies")
			 {   
				showResults(myResults,"constituencySelect");
			 }
			else if(jsObj.task == "getAllParliamentConstituencies")
			 {   
				showResults(myResults,"constituencySelect");
			 }
			else if(jsObj.task == "getCandidateDetailsBySearchCriteria")
			 {   
				showSearchResults(myResults);
			 }
			/* else if(jsObj.task == "getAllCandidateDetailsAssignedToAUser")
			 {   
				showCandidateDetails(myResults);
			 }*/
			/* else if(jsObj.task == "deleteUserCandidateRelation")
			 {   
				showCandidateDeleteResult(myResults);
			 }*/
			 else if(jsObj.task == "saveUserCandidateRelation")
			 {   
				showCandidateSaveDetails(myResults);
			 }
		  }
		  catch (e)
		  { 
   		   	alert(e);
	      }  
    },
    scope : this,
    failure : function( o ) {
     	alert( "Failed to load result" + o.status + " " + o.statusText);
         }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);	
}
/*
	This Method Is Used To Get All The Assembly Names Based On State
*/
function showAssemblyData()
 {
 $('#errorDiv').hide();
   var str='';
   str+=' <table style="margin-left: 10px;">';
   str+='   <tr>';
   str+='     <td><span>State</span></td>';
   str+='     <td>';
   str+='       <select id="stateSelect"  onchange="clearAll(\'constituencySelect\');getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,\'constituency\')" style="margin-left: 13px;font-family: verdana;font-size: 15px;"/>';
   str+='     </td>';
   str+='   </tr>';
   str+='   <tr>';
   str+='     <td><span>Constituency</span></td>';
   str+='     <td>';
   str+='       <select id="constituencySelect" style="margin-left: 13px;font-family: verdana;font-size: 15px;"/>';
   str+='     </td>';
   str+='   </tr>';
   str+=' <table>';
   document.getElementById("constituencySelectDiv").innerHTML=str;
   getStatesForAssignCanToUser();
   getAllConstituenciesInStateByType(2,1,"constituency");
 }
 /*
	This Method Is Used To Get All The Parliment Names
*/
 function showParliamentData()
 {
 $('#errorDiv').hide();
   var str='';
   str+=' <table>';
   str+='   <tr>';
   str+='     <td><span>Constituency</span></td>';
   str+='     <td>';
   str+='       <select id="constituencySelect" />';
   str+='     </td>';
   str+='   </tr>';
   str+=' <table>';
   document.getElementById("constituencySelectDiv").innerHTML=str;
   getAllParliamentConstInCountry("constituency"); 
 }
 /*
	This Method is Used To Make A Ajax Call To Display All States
 */
 function getStatesForAssignCanToUser()
 {
  var timeST = new Date().getTime();
  var jsObj =
		{ 
            time : timeST,
			task:"getStates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 }
 /*
	This Method is Used To Make a Ajax Call For Displaying All Constituences Based On State Id
 */
 function getAllConstituenciesInStateByType(electionType, stateId, element)
 {
 
    var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,	
			electionTypeId: electionType,
			stateId: stateId,
			task: "getConstituencies",
			elmtId: element 	
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getAllConstituenciesInState.action?"+rparam;						
   callAjax(jsObj,url);
 }
/*
	This Method is Used To Make a Ajax Call For Displaying All Parliments in India
 */
 function getAllParliamentConstInCountry(element)
 {
    var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,		
			task: "getAllParliamentConstituencies",
			elmtId: element 	
	}

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
    var url = "getAllParliamentConstInCountry.action?"+rparam;						
    callAjax(jsObj,url);
 }
 /*
	This Method Is used To Build All States In a Select Box
 */
 function buildResults(results,divId)
 {
  var elmt = document.getElementById(divId);
	for(var i in results)
	  {
		var option = document.createElement('option');
		  option.value=results[i].ids;
		  option.text=results[i].names;
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
 /*
	This Method Is Used To Build Constituences And Parliments In a Select Box
 */
  function showResults(results,divId)
 {
  var elmt = document.getElementById(divId);
   if(results.length<=0 && divId=="searchResultId")
      {
   	   var option1 = document.createElement('option');
		option1.value= 0;
		option1.text= "Select Candidate";
		 try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
	  }
   if(results.length<=0 && divId=="constituencySelect")
     {
   	   var option1 = document.createElement('option');
		option1.value= 0;
		option1.text= "Select Constituency";
		 try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
	  }
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
 /*
	This Method Is Used To Clear All The Fields
 */
 function clearAll(elmtId)
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
/*
	This Method is Used To Clear the showAssignCandidateErrorDiv Error messages
*/  
function clearErrorData()
{
   document.getElementById("showAssignCandidateErrorDiv").innerHTML='';
}
/*
	This Method is Used To Make A Ajax Call For Candidate Details Based On Search Criteria Made By The Used
*/
function getCandidatesBySearchCriteria()
 { 
	validationCheck();
    var timeST = new Date().getTime();
   document.getElementById("searchImage").style.display='block';
   var name=' ';
   var nameVal = document.getElementById("candidateName").value;
    if(nameVal.length>0)
	{
	  name = nameVal;
	}
   var genderElm = document.getElementById("candidateGender");
   var gender = genderElm.options[genderElm.selectedIndex].value;
   var constituencyIdElm = document.getElementById("constituencySelect")
   var constituencyId = constituencyIdElm.options[constituencyIdElm.selectedIndex].value;
   var stateId ='';
   if(document.getElementById("assemblyCons").checked==true || document.getElementById("mptc").checked==true || document.getElementById("zptc").checked==true)
     {
	   var stateElm = document.getElementById("stateSelect")
       stateId = stateElm.options[stateElm.selectedIndex].value;
	 }
   if(document.getElementById("parliamentCons").checked==true)	
     {
       stateId ='0';
     }	
      var jsObj=
	{		
            time : timeST,	
			stateId:stateId,
			name: name,
			gender: gender,
            selectedType:$('input:radio[name=constituency]:checked').val(), 
			task: "getCandidateDetailsBySearchCriteria",
			constituencyId: constituencyId		
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getCandidateDetailsBySearch.action?"+rparam;						
   callAjax(jsObj,url);
 }
 /*
	This Method Is Used To Check For Validation Results Weather Leavel Is Selected Or Not
 */
 function validationCheck()
 {
	var selected = $('.radioClass').is(':checked');
	if(selected == false)
	{
		$('#errorDiv').html('<b>Please Select The Level</b>');
		$('#image').hide();
		$('#featch').hide();
		return false;
	}
	var constituencyId = $('#constituencySelect option:selected').val();
	if(constituencyId == 0)
	{
		$('#errorDiv').show();
		$('#errorDiv').html('<b>Please Select The Constituency</b>');
		$('#image').hide();
		$('#featch').hide();
		return false;
	}
	else
	{
		return true;
	}
	
 }
 /*
	This Method Is Used To Build The YUI Table For Search Results For A Candidate Made By The User
 */
 function showSearchResults(result)
 {
	$('#errorDiv').hide();
	if(result.length>0)
	{
		YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
		{
			var select = '';
			var id=oRecord.getData("id");
			elLiner.innerHTML ='<input type="radio" class="selectedValue" name= "selectedValue" value='+id+'></input>';
		}
			var votersResultColumnDefs = [ 	
				{key:"select", label: "Select",sortable:true,width:25,formatter:YAHOO.widget.DataTable.NameLink},
				{key:"name", label: "Name",sortable:true,width:290},
				{key:"type", label: "Constituency",sortable:true,width:90}
			 ]; 
			var myConfigs = { 
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10
						}) 
						};

			var myDataSource = new YAHOO.util.DataSource(result);
							myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
							myDataSource.responseschema = {
								 fields : ["name","type","id"]
							};

				var familesDataSource = new YAHOO.widget.DataTable("showSearchResultsDiv", votersResultColumnDefs,myDataSource, myConfigs);
     document.getElementById("showHideSearchResultsDiv").style.display='block';	
		var str='';
		str+='            <input type="button" class="buttonStyle" value="Save" onclick="validationCkeck();" >';
		str+='         </td>';
		str+='         <td>';
		str+='            <input type="button" class="buttonStyle" value="Cancel" onclick="clearSearchResults();" >';
		 document.getElementById("buttonDiv").innerHTML=str;
	}
	else
	{
	var str=''; 
	str+='<table>';
	str+='     <tr>';
	str+='         <td><b>No Records Found With This Search Criteria</b></td>';
	str+='     </tr>';
	str+='</table>';
	document.getElementById("showSearchResultsDiv").innerHTML=str;
	document.getElementById("buttonDiv").innerHTML='';
	}
	document.getElementById("searchImage").style.display='none';
 }
 /*
	This Method is Used To Make A Ajax Call To Save The Candidate Details
 */
 function validationCkeck()
 {
	var str = '';
	var isChecked = $('.selectedValue').is(':checked');
	if(isChecked == false)
	{
		str+='<font color="Red">Please Select Candidate To Assign </font>';
		document.getElementById("showAssignCandidateErrorDiv").innerHTML=str;
	}
	else
	{
		assignCandidateToUser();
	}
 }
 function assignCandidateToUser()
 { 
 $('#errorDiv').hide();
   var timeST = new Date().getTime();
   var candidateId = $('input:radio[name=selectedValue]:checked').val();
   var voterId = '${voterId}';
  
     var jsObj =
		{ 
            time : timeST,
			candidateId:candidateId,
			voterId:voterId,
			task:"saveUserCandidateRelation"
		};

	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "saveCandidateDetailsAction.action?"+rparam;						
	 callAjax(jsObj,url);   
 }
 function refreshingParentWindow()
 {
	window.opener.refreshingchildWindowWindow();
    return false;
 }
 /*
	This Method Is Used To Make Sure That Weather The Data Save Or Not
 */
 function showCandidateSaveDetails(result)
 {
 $('#errorDiv').hide();
 	var str='';
   if(result.resultCode==0)
   {
   str+='<b><font color="green">Candidate Assigned Successfully</font></b>';
   document.getElementById("showAssignCandidateErrorDiv").innerHTML=str;
   }
   else if(result.resultCode==1)
   {
   str+='<b><font color="red">Unable To Save</font></b>';
   document.getElementById("showAssignCandidateErrorDiv").innerHTML=str;
   }
   getCandidatesBySearchCriteria();
   refreshingParentWindow();
 }
 /*
	This Method Is User To Clear The showSearchResultsDiv When Clear Button is Clicked
 */
 function clearSearchResults()
 {
	$('#errorDiv').hide();
   document.getElementById("showSearchResultsDiv").innerHTML='';
   document.getElementById("showAssignCandidateErrorDiv").innerHTML='';
 }
</script>
</head>
<body >
    <div id="mainDiv"align="center" >ASSIGN ${name} TO POLITICAN</div> 
	<table>
            <tr><td><div id="userErrorMessageDiv" /></td></tr>
    </table>
	<s:hidden id="voterId" name="voterId" value="%{voterId}" /> 
	
<div id="addCandidateDiv"  align="center">	
  <fieldset class="f3">
   <legend class="span12" style="color: blue;
    font-size: 13px;">SEARCH AND SELECT POLITICAN TO MAP FOR ${name}</legend> 
	<div id="errorDiv" style="color:red"></div>   
   <table class="textAlignStyle">
      <tr>
	      <td><span>Name</span></td>
		  <td><input type="text" id="candidateName"  /></td>
	  </tr>
	  <tr>
	      <td><span>Gender</span></td>
		  <td><select id="candidateGender" >
		        <option value="M">Male</option>
				<option value="F">Female</option>
		      </select>
		  </td>
	  </tr>
   </table>
   <table>
	  <tr>
			<td><span style="margin-left: -59px;">Select Level</span><font style="color:red"> *</font></td>
	      <td><input type="radio" name="constituency" id="assemblyCons" value="assembly" onclick="showAssemblyData();" class="radioClass"/>Assembly</td>
	      <td><input type="radio" name="constituency" id="parliamentCons"  value="parliament"  onclick="showParliamentData();" class="radioClass"/>Parliament</td>

	<td><input type="radio" name="constituency" id="mptc"  value="mptc"  onclick="showAssemblyData();" class="radioClass"/>Mptc</td>

	<td><input type="radio" name="constituency" id="zptc"  value="zptc"  onclick="showAssemblyData();" class="radioClass"/>Zptc</td>
	  </tr>
   </table>
   <table>
      <tr>
	     <td>
           <div id="constituencySelectDiv" />
         </td>
      </tr>
   </table>
   <table>
      <tr>
		  <td><input type="button" class="buttonStyle" value="Search Candidate" onclick="clearErrorData();getCandidatesBySearchCriteria();" /></td>
	  </tr>
	  <tr>
		  <td colspan="2"><div id="searchImage" style="display:none;"><b id="featch">Fetching Records...</b><img id="image" height="11" width="16" src="images/icons/partypositions.gif"></img></div></td>
	  </tr>
   </table>
 </fieldset>
   <div id="showHideSearchResultsDiv"  style="display:none;" >
   <fieldset class="f3">
     <legend class="span12" style="color: blue;
    font-size: 15px;font-family: verdana;">Search Results</legend>
     <table>
	    <tr>
	        <td><div id="showAssignCandidateErrorDiv" /></td>
	    </tr>
	 </table>
     <div id="showSearchResultsDiv" class="yui-skin-sam yui-dt-sortable"/>	
	 </div>
	 <div id="buttonDiv"></div>
   </fieldset>
   </div>
</div> 
<div id="showUpdateDeleteDiv" style="display:none;"  align="center"> 
 <fieldset class="f3">
  <legend class="span12" style="color: blue;
    font-weight: bold">Update Candidates</legend>
   <table>
     <tr>
	   <td><div id="candidateDeleteErrorMessage" /></td>
	 </tr>
  </table>
   <table>
     <tr>
	   <td>
         <div id="updateDeleteCandidateDiv" ></div>
       </td>
	 </tr>
   </table> 
 </fieldset>
</div>
 </body>
</html>