<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Party To User</title>
<style type="text/css">
	#assignPartyMainDiv
	{
	margin-left:auto;
	margin-right:auto;
	float:none;
	width:960px;
	}
	.headingStyle
	{
	background:#06ABEA;
    border-radius: 5px 5px 5px 5px;
    color: #FFFFFF;
    font-size: 17px;
    margin-left: 354px;
    text-align: center;
    width: 250px;
	}
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
.f3 {
-moz-border-radius:4px 4px 4px 4px;
border:2px solid #CFD6DF;
margin-bottom:10px;
padding:10px;
width:552px;
padding-bottom: 10px;
margin-left: -16px;
}
.l2 {
color:navy;
font-size:12px;
font-weight:bold;
padding:5px;
border-bottom-width: 0;
margin-bottom: 0px;
padding-top: 5px; 
border-bottom-width: 0px;

}
.buttonsStyle
{
	margin-left: 300px;
	margin-top: 20px;
}
</style>
<script type="text/javascript">
function showParties()
{
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
   document.getElementById("userErrorMessageDiv").innerHTML='';
    
   if(userId !=0)
   {
	   document.getElementById("partyDiv").style.display='block';
	   document.getElementById("UpdatespartyDiv").style.display='none';
    
   }
   else
   {
     document.getElementById("userErrorMessageDiv").innerHTML='<b><font color="red">Pleace Select A User</font></b>';
   }
}
function clearPartyResults()
{
	//document.getElementById("partyDiv").innerHTML='';
	document.getElementById("partyDiv").style.display ='none';
}
function clearAllData()
{
  document.getElementById("partyDeleteErrorMessage").innerHTML='';
  // document.getElementById("partyDiv").style.display='none';
   document.getElementById("UpdatespartyDiv").style.display='none';
   
}

function showUpdateDeleteParty()
 { 
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
   document.getElementById("userErrorMessageDiv").innerHTML='';
   if(userId!=0)
   {
     document.getElementById("UpdatespartyDiv").style.display='block';
     document.getElementById("partyDiv").style.display='none';
   	 document.getElementById("partyDeleteErrorMessage").innerHTML=''; 
     getPartiesAssignedToAUser();
   }
   else
   {
     document.getElementById("userErrorMessageDiv").innerHTML='<b><font color="red">Pleace Select A User</font></b>';
   }
 }
function  getPartiesAssignedToAUser()
{   
    var  userE = document.getElementById("userId");
    var userId =  userE.options[userE.selectedIndex].value;
    var timeST = new Date().getTime();
    var jsObj=
	{		
            time : timeST,	
			task: "getAllPartyDetailsAssignedToAUser",
            userId:userId			
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getAllPartyDetailsAssignedToAUserAction.action?"+rparam;						
   callAjax(jsObj,url);
}
function callAjax(jsObj,url)
   {
	var myResults;	
	
	var callback = {			
	
	success : function( o )
	{
		try {
			 if(o.responseText)
				myResults =YAHOO.lang.JSON.parse(o.responseText);	
			 if(jsObj.task == "getAllPartyDetailsAssignedToAUser")
			 {   
				showPartyDetails(myResults);
			 }
			 else if(jsObj.task == "deleteUserPartyRelation")
			{
				 showPartyDeleteResult(myResults);
			}
			else if(jsObj.task == "saveDataToUserPartyRelation")
			 {
			   assignedMessgae(myResults);
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
function showPartyDetails(results)
  {   
     var str='';
	 str+='<table>';
	 if(results.length<=0)
	 {
	   str+='    <tr>';
	   str+='       <td><b>No Party Assigned To This User</b></td>';
	   str+='    </tr>';
	 }
	else
	{ 
	   str+='    <tr>';
	   str+='       <td class="textAlignStyle"><font color="navy"><b>Unselect To Delete Party</b></font></td>';       
	   str+='    </tr>';	 
	 
     for(var i in results)
	 {
	   str+='    <tr>';
	   str+='       <td class="textAlignStyle">';
	    str+='        <input type="checkbox" id=\''+i+'\' checked="checked" value=\''+results[i].ids+'\' name="userPartyCheckBox" />'+results[i].names;
	   str+='       </td>';
	   str+='    </tr>';
	 }
	  str+='</table>';
	  str+='<table>';
	  str+='    <tr>';
	  str+='       <td><input type="button" class="buttonStyle" value="Update" onclick="updateUserPartyRelation();" style="margin-left: 69px; border-right-width: 0px; margin-right: 15px;" /></td>';
	  str+='       <td><input type="button" class="buttonStyle" value="Cancel" onclick="clearUpdateDeleteCandidateDiv();" /></td>';
	  str+='    </tr>';
	  str+='</table>';
    }	  
	  document.getElementById("updateDeletePartyDiv").innerHTML=str;
  }

  function updateUserPartyRelation()
  {
    
	  var timeST = new Date().getTime();
    var relationIds = "";
		var elements = document.getElementsByName("userPartyCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == false){
				relationIds = relationIds+elements[i].value;
				relationIds =relationIds+",";
			}
		}
	if(relationIds != ""){
	var jsObj =
		{ 
            time : timeST,
			userPartyRelationIds:relationIds,
			task:"deleteUserPartyRelation"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveUserPartyRelationAction.action?"+rparam;						
	callAjax(jsObj,url);
	}
	
  }

  function showPartyDeleteResult(result)
 {
   showUpdateDeleteParty();
   var str='';
   if(result.resultCode==0)
   {
   str+='<b><font color="green">Records Deleted Successfully</font></b>';
   document.getElementById("partyDeleteErrorMessage").innerHTML=str;
   }
   else if(result.resultCode==1)
   {
   str+='<b><font color="red">Unable To Delete The Records</font></b>';
  document.getElementById("partyDeleteErrorMessage").innerHTML=str;
   }
 }
  function clearUpdateDeleteCandidateDiv()
 {
      document.getElementById("updateDeletePartyDiv").innerHTML='';
	  document.getElementById("UpdatespartyDiv").style.display='none';
	  
 }
 function assignData()
 {
   var timeST = new Date().getTime();
   var  userEle = document.getElementById("userId");
   var userId =  userEle.options[userEle.selectedIndex].value;
   var  partyEle = document.getElementById("partyId");
   var partyId =  partyEle.options[partyEle.selectedIndex].value;
    if(partyId!=0)
	{
     var jsObj =
		{ 
            time : timeST,
			userId:userId,
			partyId:partyId,
			task:"saveDataToUserPartyRelation"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "saveDataToUserPartyRelationAction.action?"+rparam;						
	 callAjax(jsObj,url);
     }
	 else
	 {
	      document.getElementById("userErrorMessageDiv").innerHTML='<b><font color="red">Pleace Select A Party</font></b>';
	 }
	 
 }
 function assignedMessgae(results) 
  {   if(results.resultCode == 0)
            document.getElementById("userErrorMessageDiv").innerHTML='<b><font color="blue">Successfuly Assigned</font></b>';
	  else if(results.resultCode == 121)
             document.getElementById("userErrorMessageDiv").innerHTML='<b><font color="blue">Already Assigned</font></b>';	  
  }
  

</script>
</head>
<body>
<div id="assignPartyMainDiv"> 
	<div id="headingDIv" style="margin-bottom: 20px;">
		<h2 class="headingStyle">Assign Party To A User</h2>
		
	</div>

	<div id="contentDiv" style="background:#FFF;margin-bottom: 20px; padding-top: 20px; margin-left: 0px; padding-left: 0px; padding-bottom: 25px;">
	<table>
            <tr><td><div id="userErrorMessageDiv" style="margin-left: 350px; margin-bottom: 11px;"></div></td></tr>
    </table>
		<div style="margin-left: 296px;">
			<span style="font-weight: bold; padding-left: 0px; margin-left: 0px; padding-right: 20px;">All Users</span>

			 <select id="userId" onchange="clearAllData();" >
				<option value="0">select a user</option>
				  <c:forEach var="allUsers" varStatus="stat" items="${userList}">	
					  <option value="${allUsers.id}"> ${allUsers.name} </option>	
				  </c:forEach>
			    </select>
		</div>
		<div class="buttonsStyle">
			<table>
		   <tr>
			  <td><input type="button" class="buttonStyle" value="Add A Party" onClick="showParties();" style="padding-right: 17px; padding-left: 17px; margin-right: 13px; margin-left: 81px;"></td>
			  <td><input type="button" class="buttonStyle" value="Update Parties"  onClick="showUpdateDeleteParty();"></td>
		   </tr>
		</table>	
	</div>

	<div id="partyDiv" style="margin-left: 200px;display:none" >
	   <fieldset class="f3">
		  <legend class="l2" style="width:68px;">Add A Party</legend> 
		 
			<div style="margin-left: 140px;">
				<span style="font-weight: bold; padding-left: 0px; margin-left: 0px; padding-right: 20px;">Select Party</span>

				<select id="partyId" onchange="clearAllData();" >
					<option value="0">select a party</option>
					  <c:forEach var="allParties" varStatus="stat" items="${partyList}">
					  <option value="${allParties.id}"> ${allParties.name} </option>	
					  </c:forEach>
				 </select>

				<div class="buttonsStyle" style="margin-left: 35px;">
					<table>
					   <tr>
						  <td><input type="button" class="buttonStyle" value="Assign" onClick="assignData();" style="padding-right: 10px; margin-right: 13px; margin-left: 66px;"></td>
						  <td><input type="button" class="buttonStyle" value="Cancel" onClick="clearPartyResults();"></td>
					   </tr>
					 </table>	
				</div>
			</div>
	  </fieldset>
	</div>

	
	<div id="UpdatespartyDiv" style="margin-left: 200px;display:none;"> 
 <fieldset class="f3">
  <legend class="l2" style="width:82px;">Update Parties</legend>
   <table>
     <tr>
	   <td><div id="partyDeleteErrorMessage" /></td>
	 </tr>
  </table>
   <table>
     <tr>
	   <td>
         <div id="updateDeletePartyDiv" style="margin-left: 148px; margin-bottom: 9px;"></div>
       </td>
	 </tr>
   </table> 
 </fieldset>
</div>
	

	</div>

</div>


</body>
</html>