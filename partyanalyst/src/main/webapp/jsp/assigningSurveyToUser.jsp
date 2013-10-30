<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Survey To User</title>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<style type="text/css">
.headingStyle
	{
	background:#06ABEA;
    border-radius: 5px 5px 5px 5px;
    color: #FFFFFF;
    font-size: 17px;
    margin-left: auto;
	margin-right: auto;
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
p{
 margin-bottom: -5px;
}
</style>
<script type="text/Javascript">
function saveSurveyInfo(){
	var userId = $('#userId').val();
	var surveyId = $('#surveyId').val();
	if(userId == 0){
		$("#errorDiv").html("User field is mandatory..").css({"display":"block","color":"red","font":"bold"});
		return;
	}
	if(surveyId == 0){
		$("#errorDiv").html("Survey field is mandatory..").css({"display":"block","color":"red","font":"bold"});
		return;
	}
	var jsObj =
		{ 
            userId : userId,
			surveyId:surveyId,
			task:"saveSurveyDetailsAction"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSurveyDetailsAction.action?"+rparam;						
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
		if(jsObj.task == "saveSurveyDetailsAction")
			  {
			if(myResults.resultCode == 121){
				$("#errorDiv").html("Survey already assigned..").css({"display":"block","color":"red","font":"bold"});
			}else if(myResults.resultCode == 0){
				$("#errorDiv").html("Survey assigned successfully..").css({"display":"block","color":"green","font":"bold"});
			}else if(myResults.resultCode == 1){
				$("#errorDiv").html("Data could not be saved due to some exception..").css({"display":"block","color":"red","font":"bold"});
			}
		  }
		}catch (e)
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

</script>
</head>
<body>
   <!-- <h4 style="padding-top:10px;">Assign Survey To User</h4> -->

<div id="headingDIv" style="margin-bottom: 20px; margin-top: 20px;">
		<h4 class="headingStyle">Assign Survey To A User</h4>
		
</div>
<div id="contentDiv" style="background:#FFF;margin-bottom: 20px; padding-top: 20px; margin-left: 0px; padding-left: 0px; padding-bottom: 25px;">
<div id="errorDiv" style="display:none;margin-left: 296px;">

</div>
<div style="margin-left: 296px;width: 400px;">
<p><b>PartyAnalyst Users :</b>
			    <select id="userId" class="selectWidth" style="width: 233px;margin-left: 10px;">
				<option value="0"> Select User </option>	
				  <c:forEach var="allUsers" varStatus="stat" items="${userList}" >		
					<option value=${allUsers.id}> ${allUsers.name} </option>	
				  </c:forEach>
			    </select></p>
<p><b>Survey :</b>
  <select id="surveyId" class="selectWidth" style="width: 233px;margin-left: 90px;">
				<option value="0"> Select Survey </option>	
				  <c:forEach var="allUsers" varStatus="stat" items="${surveyList}" >		
					<option value=${allUsers.id}> ${allUsers.name} </option>	
				  </c:forEach>
			    </select></p>
					<input type="button" class="btn btn-info" value="Save" style="margin-left: 125px;" onclick="saveSurveyInfo()">
				</div>
</div>
 </body>
</html>