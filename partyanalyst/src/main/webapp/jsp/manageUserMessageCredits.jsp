<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage User Sms Credits</title>
<style type="text/css">
 
 .button {
    background: none repeat scroll 0 0 #335291;
    color: #FFFFFF;
    font-weight: bold;
    margin-bottom: 5px;
    margin-top: 5px;
    padding: 2px;
    width: 110px;
  }
</style>
<script type="text/Javascript">
  var userSmsId ;
var smsUserName;
var smsPassword;
var smsSenderId;
var smsCredits;
function showUserSmsDetails(result){
 if(result.sessionStatus==0)
   {
    document.getElementById("userErrorMessageDiv").innerHTML='<font color="red"><b>Your Session Expired</b></font>';
   }
   userSmsId = result.userSmsTrackId;
   smsUserName = result.smsUserName;
   smsPassword = result.smsPassword;
   smsSenderId = result.senderId;
   smsCredits =  result.renewalSmsCount;
  document.getElementById("showUserName").innerHTML='<div style="font-weight:bold;color:navy">'+result.firstName+' '+result.lastName+'</div>';
  document.getElementById("smsUserName").value= smsUserName;
  document.getElementById("smsPassword").value= smsPassword;
  document.getElementById("smsSenderId").value= smsSenderId;
  document.getElementById("smsCredits").value= smsCredits;
  document.getElementById("totalVal").value= smsCredits;
  document.getElementById("addVal").value= 0;
  }
  function showResuleMessage(result){
  if(result.sessionStatus==0)
    document.getElementById("userErrorMessageDiv").innerHTML='<font color="red">Your Session Expired </font>';
   if(result.updateStatus==0)
    document.getElementById("showResuleMessage").innerHTML='<font color="red">Unable to Updated </font>';
   if(result.updateStatus==1)
    document.getElementById("showResuleMessage").innerHTML='<font color="green"><b>Updated Successfully</b></font>';
 }
 function ShowTotal(){
   var val1 = document.getElementById("smsCredits").value;
   var val2 = document.getElementById("addVal").value;
   var total = parseInt(val1,10) + parseInt(val2,10);
   document.getElementById("totalVal").value=total;
 }
  function updateUserMessageCredits(){
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
  if(userId!=0)
  {
   var smsUserName = document.getElementById("smsUserName").value;
   var smsPassword=document.getElementById("smsPassword").value;
   var smsSenderId=document.getElementById("smsSenderId").value;
   var smsCredit = document.getElementById("totalVal").value;
   if(!isNaN(smsCredit))
    {
      var jsObj=
		{					
				task:"updateSmsDetails",				
				smsUserName:smsUserName,
				smsPassword:smsPassword,
				smsSenderId:smsSenderId,
				userSmsTrackId :userSmsId,
				userId:userId,
				smsCredit:smsCredit
		};
      var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "getUserMessageCreditDetailsAction.action?"+rparam;	
      callAjax(jsObj,url);
	 }
	 else{
	  document.getElementById("showResuleMessage").innerHTML='<font color="red">Please Enter Valid Number in Sms Credits</font>';
	 }
   }
 }
 
 function getAllUserMessageCredits()
  { 
     document.getElementById("userErrorMessageDiv").innerHTML='';
	 document.getElementById("showResuleMessage").innerHTML='';
     var  userE = document.getElementById("userId");
     var userId =  userE.options[userE.selectedIndex].value;
	if(userId!=0)
	{
     var jsObj=
		{					
				task:"getUserMessageCreditDetails",				
				userId : userId
		};
     var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
     var url = "getUserMessageCreditDetailsAction.action?"+rparam;	
     callAjax(jsObj,url);
	}
	else
	{ 
	  document.getElementById("showUserName").innerHTML= '';
	  document.getElementById("smsUserName").value= '';
	  document.getElementById("smsPassword").value= '';
      document.getElementById("smsSenderId").value= '';
	  document.getElementById("userErrorMessageDiv").innerHTML='<font color="red">Please Select A User</font>';
	  
	  document.getElementById("smsCredits").value='';
	  document.getElementById("addVal").value='';
	  document.getElementById("totalVal").value='';
	  
	}
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
			if(jsObj.task == "getUserMessageCreditDetails")
			{    
				showUserSmsDetails(myResults);
			}
			if(jsObj.task == "updateSmsDetails")
			{   
				getAllUserMessageCredits();
				showResuleMessage(myResults);
			}
			

		}catch (e) { 
               		
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

</script>
</head>
<body>
   <h2 style="padding-top:10px;">Manage User Sms Credits</h2> 
  <table>
    <tr><div id="userErrorMessageDiv" /> </tr>
  </table>
  <table style="padding-bottom:20px;">
	<tr>
	    <div >
		  <td style="font-weight:bold;font-size:12px;text-align:left;size:">
				All Users &nbsp;&nbsp;		
		  </td>
		  <td >
			<select id="userId" onchange="getAllUserMessageCredits()" >
				<c:forEach var="allUsers" varStatus="stat" items="${allRegisteredUsersData.listOfUsers}">		
						<option value="${allUsers.id}"> ${allUsers.name} </option>	
				</c:forEach>
			</select>
		  </td>
		</div>
	</tr>
 </table>
 <table><tr><td align="left"><span id="showResuleMessage" /></td></tr> </table>
 <table>
     
     <tr>
	    <td style="font-weight:bold;font-size:12px;text-align:left;">User Is :</td>
	   <td><div id="showUserName" /></td>
	 </tr>
	<tr>
	   <td style="font-weight:bold;font-size:12px;text-align:left;">Sms User Name :</td>
	   <td><input type="text" id="smsUserName" /></div></td>
	</tr>   
	<tr>   
	   <td style="font-weight:bold;font-size:12px;text-align:left;">Sms Password :</td> 
	   <td><input type="text" id="smsPassword" /></td>
	</tr>		   	      	   	   
	<tr>    		
	   <td style="font-weight:bold;font-size:12px;text-align:left;">Sms Sender Id :</td>
	   <td><input type="text" id="smsSenderId" /></td>
	</tr>
 </table>
 <table style="padding-left:2.79cm;padding-top:10px;"> 
     <tr>	
       <td style="font-weight:bold;font-size:12px;text-align:left;">Sms Credits :</td>	 
	   <td><input type="text" size="5" id="smsCredits" onkeyup="ShowTotal();"/> </td>   
	   <td style="font-weight:bold;font-size:12px;text-align:left;">
		 &nbsp;Add :&nbsp; <input type="text" id="addVal" size="4"  onkeyup="ShowTotal();"/>
	   </td>
       <td style="font-weight:bold;font-size:12px;text-align:left;">
	     &nbsp;Total :&nbsp;<input type="text" size="6" id="totalVal" readonly="readonly" value="'+value+'" />
	   </td>
	 </tr>
 </table>
<table>
    <input type="button" class="button" value="Save/Update" onclick="updateUserMessageCredits();"/>
</table> 
</body>
</html>