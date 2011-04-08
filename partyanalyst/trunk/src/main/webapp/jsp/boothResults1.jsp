<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Dependencies --> 
	<script src="http://yui.yahooapis.com/2.7.0/build/yahoo/yahoo-min.js"></script> 
	 
	<!-- Source file --> 
	<script src="http://yui.yahooapis.com/2.7.0/build/json/json-min.js"></script>
	 
<title>Choose Constituency</title>
<script type="text/javascript">
	function getBoothResults()
	{
		var consElmt=document.getElementById('constituencyNameSelect');

		var partyName=document.getElementById('partyNameId').value;
		var elecyear=document.getElementById('electionYearId').value;
		var consElmtValue=consElmt.options[consElmt.selectedIndex].value;		
	
		var boothjsObj=
			{
				party:partyName,
				electionYear:elecyear,
				constituency:consElmtValue
			}
		
		//boothResultsajaxCall(boothjsObj);	
	}
	

	function boothResultsajaxCall(boothParam)
	{
		var myResults;
		var bparam="partyName="+boothParam.party+"&electionYear="+boothParam.electionYear+"&constituencyName="+boothParam.constituency;		
		var boothUrl = "<%=request.getContextPath()%>/partyBoothResult2AjaxAction.action?"+bparam; 
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 
								alert("Process completed successfully");								
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', boothUrl , callback);
	}
	window.history.forward(1);
</script>
</head>
<body>
		<s:form action="partyBoothResult2Action">
		<s:select label="Select Constituency" name="constituencyName" id="constituencyNameSelect" list="constituencyVOs"  listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getBoothResults()"/>
		<s:hidden name="electionYear" id="electionYearId"></s:hidden>
		<s:hidden name="partyName" id="partyNameId"></s:hidden>
		<s:submit value="Get Booth Results"/>
		</s:form>
</body>
</html>