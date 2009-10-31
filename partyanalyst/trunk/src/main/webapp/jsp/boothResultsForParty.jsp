<%@taglib prefix="s" uri="/struts-tags" %>
<HTML>
 <HEAD>
	<TITLE>Party Performance Report In Each Booth</TITLE>
	 <!-- Dependencies --> 
	<script src="http://yui.yahooapis.com/2.7.0/build/yahoo/yahoo-min.js"></script> 
	 
	<!-- Source file --> 
	<script src="http://yui.yahooapis.com/2.7.0/build/json/json-min.js"></script>

	<script type="text/javascript">
	
	function getConstituenciesList()
	{
		var partyNameElmt=document.getElementById("partyNameSelect");
		var partyNamevalue=partyNameElmt.options[partyNameElmt.selectedIndex].value;

		var electionTypeElmt=document.getElementById("electionTypeSelect");
		var electionTypevalue=electionTypeElmt.options[electionTypeElmt.selectedIndex].value;

		var electionYearElmt=document.getElementById("electionYearSelect");
		var electionYearvalue=electionYearElmt.options[electionYearElmt.selectedIndex].text;

		var constituencyElmt=document.getElementById("constituencySelect");
		if(constituencyElmt==null)
			constituencyvalue="empty";
		else
			var constituencyvalue=constituencyElmt.options[constituencyElmt.selectedIndex].value;
		
		

		if(partyNamevalue==0 || electionTypevalue==0 || electionYearvalue=="Select")
			return;
			
		var jsObj=
		{
			partyId:partyNamevalue,
			electionType:electionTypevalue,
			electionYear:electionYearvalue,
			cValue:constituencyvalue
		}		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);

		callAjaxForBoothResults(rparam);
	}

	function callAjaxForBoothResults(rparam)
	{
		
		var url = "<%=request.getContextPath()%>/partyBoothResult1AjaxAction.action?"+rparam;		
 		var callback = {			
 		               success : function( o ) {
							try {
								var myResults = YAHOO.lang.JSON.parse(o.responseText); 
								
								buildConstituencySelect(myResults.constituencyVOs);								
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
		
	function buildConstituencySelect(value)
	{
		var elmt=document.getElementById("constituencyRow");
		var str='';
		str+='<td><b>Constituency </b></td>';
		str+='<td><b> : </b></td>';
		str+='<td>';
		str+='<select id="constituencySelect" onchange="getConstituenciesList()">';
		str+='<option value="0">Select</option>';
		for (var item in value)
		{
			str+='<option value='+value[item].id+'>'+value[item].name+'</option>';
		}
		str+='</select>';
		str+='</td>';

		elmt.innerHTML=str;
	}

	</script>
	<style type="text/css">
	#partyBoothResultsDiv
	{
		text-align:left;
		font-size:12px;
		margin-left:150px;
	}
	</style>
</HEAD>
<body>
<s:form action="partyBoothResult1Action" name="BoothPerformanceReport" method="post" theme="simple">
<h4>Party Booth Results</h4>
<div id="partyBoothResultsDiv">
<table>
<tr>
	<td ><b>Party </b></td>
	<td><b> : </b></td>
	<td><s:select name="partyName" id="partyNameSelect" list="partyVOs"  listKey="id" listValue="name" headerKey="0" headerValue="Select"/></td>
</tr>
<tr>
	<td><b>Election Type </b></td>
	<td><b> : </b></td>
	<td><s:select label="Election Type" name="electionType" id="electionTypeSelect" list="electionTypes" listKey="id" listValue="name" headerKey="0" headerValue="Select" /></td>
</tr>
<tr>
	<td><b>Election Year </b></td>
	<td><b> : </b></td>
	<td><s:select label="Election Year" name="electionYear" id="electionYearSelect" list="electionYears" headerKey="0" headerValue="Select" /></td>
</tr>
<tr id="constituencyRow">
	
</tr>
</table>
	<s:submit value="Get Constituencies"/><input type="button" value="Get Constituency With AJAX" onclick="getConstituenciesList()"/>
</div>
</s:form>
</body>
</HTML>
