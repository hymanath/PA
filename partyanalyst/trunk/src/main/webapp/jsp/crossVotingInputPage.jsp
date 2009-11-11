<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cross Voting Report</title>
	<!-- Dependencies --> 
	<script src="http://yui.yahooapis.com/2.7.0/build/yahoo/yahoo-min.js"></script> 
	 
	<!-- Source file --> 
	<script src="http://yui.yahooapis.com/2.7.0/build/json/json-min.js"></script>

<style type="text/css">
#crossVotingInputDiv
{
	text-align:left;
	margin-left:100px;
}
#labelspan
{
	color:#724400;
	text-decoration:underline;
}
.crossVotingInputTable td
{
	color:#724400;
	padding:3px;
}
</style>
<script type="text/javascript">
	function getParliament()
	{
		var elecYearElmt = document.getElementById("electionYearField");
		var partyElmt = document.getElementById("partyListField");

		if(!elecYearElmt || !partyElmt)
			return;

		var elecValue =  elecYearElmt.options[elecYearElmt.selectedIndex].value;
		var partyValue =  partyElmt.options[partyElmt.selectedIndex].value;
					
		if(elecValue == -1 || partyValue == -1)
			return;
		else
		{
			var jsObj={
						election:elecValue,
						party:partyValue,
						task:"Parliament"
					  }

			var bparam="election="+jsObj.election+"&party="+jsObj.party;
			callAjax(jsObj,bparam);
		}
	}

	function getAssembly()
	{
		var parliamentSelectElmt =  document.getElementById("ParliamentSelect");
		var parliamentValue =  parliamentSelectElmt.options[parliamentSelectElmt.selectedIndex].value;
		var jsObj={
						parliamentValue : parliamentValue,
						task:"Assembly"
				  }
			
			var bparam="parliamentValue="+jsObj.parliamentValue;
			callAjax(jsObj,bparam);
	}

	function callAjax(jObj,param)
	{
		var myResults;		
		var boothUrl = "<%=request.getContextPath()%>/crossVotingReportAjaxAction.action?"+param; 
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 
								//console.log(myResults);
								buildParliamemtSelect(jObj,myResults);
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', boothUrl , callback);
	}

	function buildParliamemtSelect(jObj,results)
	{	
		if(jObj.task=="Parliament")
		{			
			var pSelectElmt = document.getElementById("ParliamentSelect");	

			for(var i in results)
			{
				var childElmt = document.createElement('option');
				childElmt.value = results[i].id;
				childElmt.text = results[i].name;

				try
				{
					pSelectElmt.add(childElmt,null);	
				}
				catch (e)
				{
					pSelectElmt.add(childElmt);
				}				
			}
		}
		else(jObj.task=="Assembly")
		{
			var pSelectElmt = document.getElementById("AssemblySelect");	

			for(var i in results)
			{
				var childElmt = document.createElement('option');
				childElmt.value = results[i].id;
				childElmt.text = results[i].name;

				try
				{
					pSelectElmt.add(childElmt,null);	
				}
				catch (e)
				{
					pSelectElmt.add(childElmt);
				}				
			}
		}
	}

</script>
</head>
<body>
		<h3><u>Cross Voting Report</u></h3>
		<div id="crossVotingInputDiv">
			<table class="crossVotingInputTable">
				<tr>
					<td colspan="2"><h4><span id="labelspan">Select Election year and party :</span></h4></td>
				</tr>
				<tr>
					<td align="left"><s:label theme="simple" for="electionYearField" id="electionYearLabel" value="Election Year"></s:label></td>
					<td align="left">
						<s:select theme="simple" id="electionYearField" name="electionYearField" list="electionYearList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Year"></s:select>
					</td>
				</tr>
				<tr>
					<td align="left"><s:label theme="simple" for="partyListField" id="partyListLabel" value="Party"></s:label></td>
					<td align="left">
						<s:select theme="simple" id="partyListField" name="partyListField" list="partyList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Year" onchange="getParliament()"></s:select>
					</td>
				</tr>
				<tr>
					<td align="left">Parliament Constituency</td>
					<td align="left">
							<select id="ParliamentSelect" onchange="getAssembly()">
								<option value="-1">Select </option>
								
							</select>						
					</td>
				</tr>
				<tr>
					<td align="left">Assembly Constituency</td>
					<td align="left">
						<select id="AssemblySelect">
							<option value="-1">Select</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>