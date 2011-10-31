<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Candidates To User</title>
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
</style>
<script type="text/Javascript">
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
			 else if(jsObj.task == "getAllCandidateDetailsAssignedToAUser")
			 {   
				showCandidateDetails(myResults);
			 }
			 else if(jsObj.task == "deleteUserCandidateRelation")
			 {   
				showCandidateDeleteResult(myResults);
			 }
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
 function showCandidateSaveDetails(result)
 {
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
 }
 function showCandidateDeleteResult(result)
 {
   showUpdateDeleteCandidate();
   var str='';
   if(result.resultCode==0)
   {
   str+='<b><font color="green">Records Deleted Successfully</font></b>';
   document.getElementById("candidateDeleteErrorMessage").innerHTML=str;
   }
   else if(result.resultCode==1)
   {
   str+='<b><font color="red">Unable To Delete The Records</font></b>';
   document.getElementById("candidateDeleteErrorMessage").innerHTML=str;
   }
 }
 function showCandidateDetails(results)
  {   
     var str='';
	 str+='<table>';
	 if(results.length<=0)
	 {
	   str+='    <tr>';
	   str+='       <td><b>No Candidates Assigned To This User</b></td>';
	   str+='    </tr>';
	 }
	else
	{ 
	   str+='    <tr>';
	   str+='       <td class="textAlignStyle"><font color="navy"><b>Unselect To Delete Candidates</b></font></td>';       
	   str+='    </tr>';	 
	 
     for(var i in results)
	 {
	   str+='    <tr>';
	   str+='       <td class="textAlignStyle">';
	    str+='        <input type="checkbox" id=\''+i+'\' checked="checked" value=\''+results[i].ids+'\' name="userCandidatesCheckBox" />'+results[i].names;
	   str+='       </td>';
	   str+='    </tr>';
	 }
	  str+='</table>';
	  str+='<table>';
	  str+='    <tr>';
	  str+='       <td><input type="button" class="buttonStyle" value="Update" onclick="updateUserCandidatesRelation();" /></td>';
	  str+='       <td><input type="button" class="buttonStyle" value="Cancel" onclick="clearUpdateDeleteCandidateDiv();" /></td>';
	  str+='    </tr>';
	  str+='</table>';
    }	  
	  document.getElementById("updateDeleteCandidateDiv").innerHTML=str;
  }
 function clearUpdateDeleteCandidateDiv()
 {
      document.getElementById("updateDeleteCandidateDiv").innerHTML='';
	  document.getElementById("showUpdateDeleteDiv").style.display='none';
	  
 }
 function updateUserCandidatesRelation()
  {
    var timeST = new Date().getTime();
    var relationIds = "";
		var elements = document.getElementsByName("userCandidatesCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == false){
				relationIds = relationIds+elements[i].value;
				relationIds =relationIds+",";
			}
		}
	var jsObj =
		{ 
            time : timeST,
			userCandidateRelationIds:relationIds,
			task:"deleteUserCandidateRelation"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveUserCandidateRelationAction.action?"+rparam;						
	callAjax(jsObj,url);
  }
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
function showSearchResults(result)
 {
   if(result.length>0)
   {
    var str='';
	str+='<table>';
	str+='     <tr>';
	str+='         <td><b>Select Candidate</b></td>';
	str+='         <td>';
	str+='            <select id="searchResultId">';
	str+='         </td>';
	str+='     </tr>';
	str+='</table>';
	str+='<table>';
	str+='     <tr>';
	str+='         <td>';
	str+='            <input type="button" class="buttonStyle" value="Save" onclick="assignCandidateToUser();" >';
	str+='         </td>';
	str+='         <td>';
	str+='            <input type="button" class="buttonStyle" value="Cancel" onclick="clearSearchResults();" >';
	str+='         </td>';
	str+='     </tr>';
	str+='</table>';
	str+='</fieldset>';
     document.getElementById("showSearchResultsDiv").innerHTML=str;
     document.getElementById("showHideSearchResultsDiv").style.display='block';
	 showResults(result,"searchResultId");
	}
	else
	{
	  var str='';
	str+='<fieldset class="f3">';
    str+='<legend class="l2">Search Results</legend>';  
	str+='<table>';
	str+='     <tr>';
	str+='         <td><b>No Records Found With This Search Criteria</b></td>';
	str+='     </tr>';
	str+='</table>';
	str+='</fieldset>';
	document.getElementById("showSearchResultsDiv").innerHTML=str;
	}
	document.getElementById("searchImage").style.display='none';
 } 
function clearSearchResults()
 {
   document.getElementById("showSearchResultsDiv").innerHTML='';
 }
function assignCandidateToUser()
 { 
   var timeST = new Date().getTime();
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
   var  candidateE = document.getElementById("searchResultId");
   var candidateId =  candidateE.options[candidateE.selectedIndex].value;
   if(userId!=0)
   {
     var jsObj =
		{ 
            time : timeST,
			userId:userId,
			candidateId:candidateId,
			task:"saveUserCandidateRelation"
		};

	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "saveUserCandidateRelationAction.action?"+rparam;						
	 callAjax(jsObj,url);
   }
   else
   {
      document.getElementById("userErrorMessageDiv").innerHTML='<b><font color="red">Pleace Select A User</font></b>';
   }   
 }
function getStates()
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
function getCandidatesBySearchCriteria()
 {  
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
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
   var stateId ='';
   if(document.getElementById("assemblyCons").checked==true)
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
			task: "getCandidateDetailsBySearchCriteria",
			constituencyId: constituencyId,
            userId:userId			
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getCandidateDetailsBySearchCriteriaAction.action?"+rparam;						
   callAjax(jsObj,url);
 }
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
function showAddCandidate()
 {
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
   document.getElementById("userErrorMessageDiv").innerHTML='';
   document.getElementById("showAssignCandidateErrorDiv").innerHTML='';
   document.getElementById("showHideSearchResultsDiv").style.display='none';
   if(userId!=0)
   {
     document.getElementById("addCandidateDiv").style.display='block';
     document.getElementById("showUpdateDeleteDiv").style.display='none';
     document.getElementById("assemblyCons").checked='true';
     showAssemblyData();
   }
   else
   {
     document.getElementById("userErrorMessageDiv").innerHTML='<b><font color="red">Pleace Select A User</font></b>';
   }
 }
function showUpdateDeleteCandidate()
 { 
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
   document.getElementById("userErrorMessageDiv").innerHTML='';
   if(userId!=0)
   {
     document.getElementById("showUpdateDeleteDiv").style.display='block';
     document.getElementById("addCandidateDiv").style.display='none';
     document.getElementById("constituencySelectDiv").innerHTML='';
     document.getElementById("showSearchResultsDiv").innerHTML='';  
	 document.getElementById("candidateDeleteErrorMessage").innerHTML=''; 
     getCandidatesAssignedToAUser();
   }
   else
   {
     document.getElementById("userErrorMessageDiv").innerHTML='<b><font color="red">Pleace Select A User</font></b>';
   }
 }
function  getCandidatesAssignedToAUser()
{   
    var  userE = document.getElementById("userId");
    var userId =  userE.options[userE.selectedIndex].value;
    var timeST = new Date().getTime();
    var jsObj=
	{		
            time : timeST,	
			task: "getAllCandidateDetailsAssignedToAUser",
            userId:userId			
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getAllCandidateDetailsAssignedToAUserAction.action?"+rparam;						
   callAjax(jsObj,url);
}
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
function showAssemblyData()
 {
   var str='';
   str+=' <table>';
   str+='   <tr>';
   str+='     <td><b>State</b></td>';
   str+='     <td>';
   str+='       <select id="stateSelect"  onchange="clearAll(\'constituencySelect\');getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,\'constituency\')" />';
   str+='     </td>';
   str+='   </tr>';
   str+='   <tr>';
   str+='     <td><b>Constituency</b></td>';
   str+='     <td>';
   str+='       <select id="constituencySelect" />';
   str+='     </td>';
   str+='   </tr>';
   str+=' <table>';
   document.getElementById("constituencySelectDiv").innerHTML=str;
   getStates();
   getAllConstituenciesInStateByType(2,1,"constituency");
 }
function showParliamentData()
 {
   var str='';
   str+=' <table>';
   str+='   <tr>';
   str+='     <td><b>Constituency</b></td>';
   str+='     <td>';
   str+='       <select id="constituencySelect" />';
   str+='     </td>';
   str+='   </tr>';
   str+=' <table>';
   document.getElementById("constituencySelectDiv").innerHTML=str;
   getAllParliamentConstInCountry("constituency"); 
 }
function clearAllData()
{
   document.getElementById("candidateDeleteErrorMessage").innerHTML='';
   document.getElementById("addCandidateDiv").style.display='none';
   document.getElementById("showUpdateDeleteDiv").style.display='none';
   document.getElementById("showSearchResultsDiv").innerHTML='';
}
function clearErrorData()
{
   document.getElementById("showAssignCandidateErrorDiv").innerHTML='';
}
</script>
</head>
<body>
    <h2 style="padding-top:10px;">Assign Candidates To User</h2> 
	<table>
            <tr><td><div id="userErrorMessageDiv" /></td></tr>
    </table>
    <table style="padding-bottom:20px;padding-top:10px">
	   <tr>	       
		     <td style="font-weight:bold;font-size:12px;text-align:left;">All Users &nbsp;&nbsp;</td>
		     <td>
			    <select id="userId" onchange="clearAllData();" >
				  <c:forEach var="allUsers" varStatus="stat" items="${allRegisteredUsersData.listOfUsers}">		
					<option value="${allUsers.id}"> ${allUsers.name} </option>	
				  </c:forEach>
			    </select>
		     </td>		   
	   </tr>
    </table>
    <table>
       <tr>
          <td><input type="button" class="buttonStyle" value="Add Candidate" onClick="showAddCandidate();"></td>
		  <td><input type="button" class="buttonStyle" value="Update/Delete Candidates" onClick="showUpdateDeleteCandidate();"></td>
       </tr>
   </table>	
  
<div id="addCandidateDiv" style="display:none;" >	
  <fieldset class="f3">
   <legend class="l2">Search Candidate</legend>  
   <table class="textAlignStyle">
      <tr>
	      <td><b>Name</b></td>
		  <td><input type="text" id="candidateName"  /></td>
	  </tr>
	  <tr>
	      <td><b>Gender</b></td>
		  <td><select id="candidateGender" >
		        <option value="M">Male</option>
				<option value="F">Female</option>
		      </select>
		  </td>
	  </tr>
   </table>
   <table>
	  <tr>
	      <td><input type="radio" name="constituency" id="assemblyCons" value="assembly" checked="checked" onclick="showAssemblyData();" />Assembly</td>
	      <td><input type="radio" name="constituency" id="parliamentCons"  value="parliament"  onclick="showParliamentData();" />Parliament</td>
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
		  <td colspan="2"><div id="searchImage" style="display:none;"><b>Fetching Records...</b><img height="11" width="16" src="images/icons/partypositions.gif"></img></div></td>
	  </tr>
   </table>
 </fieldset>
   <div id="showHideSearchResultsDiv"  style="display:none;" >
   <fieldset class="f3">
     <legend class="l2">Search Results</legend>
     <table>
	    <tr>
	        <td><div id="showAssignCandidateErrorDiv" /></td>
	    </tr>
	 </table>
     <div id="showSearchResultsDiv" /></div>
   </fieldset>
   </div>
</div> 
<div id="showUpdateDeleteDiv" style="display:none;" > 
 <fieldset class="f3">
  <legend class="l2">Update Candidates</legend>
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