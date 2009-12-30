<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<link  href="css/main.css" rel="stylesheet" type="text/css" />
<TITLE>Party Performance Report In Each Booth</TITLE>
<!-- Dependencies -->
<script src="http://yui.yahooapis.com/2.7.0/build/yahoo/yahoo-min.js"></script>

<!-- Source file -->
<script src="http://yui.yahooapis.com/2.7.0/build/json/json-min.js"></script>
<script src="js/partyBoothResults/boothResults.js" ></script>

<script type="text/javascript">
	
	function showAjaxDiv()
	{
		var elmt = document.getElementById("ajaxImg");
		if(elmt)
			elmt.style.display = "block";

		return true;
	}
</script>

</HEAD>
<body>
<s:url action="partyBoothResult1AjaxAction" id="getConsituencyURL" />
<s:url action="partyBoothResultPartyAjaxAction" id="getPartyURL" />
<h4>Party Booth Results</h4>
<s:form action="partyBoothResult2Action" name="BoothPerformanceReport" onsubmit="showAjaxDiv()" cssClass="inputTable">
	
	<s:select label="Election Type" name="electionType"
		list="electionTypes" listKey="id" listValue="name" headerKey="0"
		headerValue="Select"/>

	<s:select label="Election Year" name="electionYear"
		list="electionYears" headerKey="0" headerValue="Select"
		onchange="getConstituenciesList(this.form,'%{getConsituencyURL}')" />


	<tr id="constituencyRow" style="display: none">
		<th>Constituency</th>
		<td><s:select label="Constituency" name="constituencyName"
			list="%{#{'0':'Select'}}" theme="simple" onchange="getConstituenciesList(this.form,'%{getPartyURL}')"/></td>
	</tr>

	<tr id="partyRow" style="display: none">
		<th>Party</th>
		<td><s:select label="Party" name="partyName"
			list="%{#{'0':'Select'}}" theme="simple"/>			
		</td>
		<td style="border:none;">
			<img id="ajaxImg" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
		</td>

	</tr>
	<s:submit value="Get Booth Results" />

	

</s:form>
</body>
</HTML>
