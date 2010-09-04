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
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

<script type="text/javascript">
	
	function showAjaxDiv()
	{
		var elmt = document.getElementById("ajaxImg");
		if(elmt)
			elmt.style.display = "block";

		return true;
	}


	function showImage2()
	{
		var img = document.getElementById("ajaxImg2");
		img.style.display = 'block';
		setTimeout('stopImage2()',1000);
	}

	function stopImage2()
	{
		var img = document.getElementById("ajaxImg2");
		img.style.display='none';
	}

	function hideSubmit()
	{
		 var ElectionTypeElmt =  document.getElementById("electionType1");
	     var ElectionTypeValue=  parseInt(ElectionTypeElmt.options[ElectionTypeElmt.selectedIndex].value);

		 var electionYearElmt =  document.getElementById("electionYear1");
	     var electionYearValue=  parseInt(electionYearElmt.options[electionYearElmt.selectedIndex].value);

	    var ConstituencyNameElmt =document.getElementById("ConstituencyName1");
	    var ConstituencyNameValue=(ConstituencyNameElmt.options[ConstituencyNameElmt.selectedIndex].value);
		
		var partyNameElmt =  document.getElementById("partyName1");
	    var partyNameValue=  parseInt(partyNameElmt.options[partyNameElmt.selectedIndex].value);

		var continue_button = document.getElementById("subbutton");
	
		if(ElectionTypeValue==0 || electionYearValue ==0 || ConstituencyNameValue==0 || partyNameValue ==0)
		{
		  continue_button.style.visibility ='hidden';  
		}

		else
		{
		   continue_button.style.visibility ='visible'; 
		}

	}

	function clearConstituencys()
	{
			clearOptionsListForSelectElmtId("ConstituencyName1");
			createSelectOptionsForSelectElmtId("ConstituencyName1");
		
	}

	function clearpartyNames()
	{
			clearOptionsListForSelectElmtId("partyName1");
			createSelectOptionsForSelectElmtId("partyName1");
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
		headerValue="Select" id="electionType1" onchange="hideSubmit()" />

	<s:select label="Election Year" name="electionYear"
		list="electionYears" headerKey="0" headerValue="Select" id="electionYear1"
	onchange="getConstituenciesList(this.form,'%{getConsituencyURL}'),hideSubmit(),clearConstituencys()"/>
		
	<tr id="constituencyRow" >
	<th>Constituency</th>

	<td><s:select label="Constituency" name="constituencyName" 
list="%{#{'0':'Select'}}" theme="simple" id="ConstituencyName1"  onchange="showImage2(),clearpartyNames(),hideSubmit(),getConstituenciesList(this.form,'%{getPartyURL}')"/></td>

		<td style="border:none;">
		<img id="ajaxImg2" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
		</td>

	</tr>

	<tr id="partyRow">
		<th>Party</th>
		<td><s:select label="Party" name="partyName" id="partyName1" onchange="hideSubmit()"
			list="%{#{'0':'Select'}}" theme="simple"/>			
		</td>
		<td style="border:none;">
			<img id="ajaxImg" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
		</td>

	</tr>
	<s:submit id="subbutton" value="Get Booth Results" />
</s:form>
</body>
</HTML>
