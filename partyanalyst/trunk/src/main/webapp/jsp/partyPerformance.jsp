<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
 <HEAD>
	<TITLE>Party Performance Report</TITLE>

	<!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<!-- OPTIONAL: JSON (enables JSON validation) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
  	<!-- Dependencies --> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript">
 	function callAjax(param, url){
 		var myResults;
 		url = url + param;
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);								
								processResponse(param, myResults);
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
 	
    function processResponse(param, response)
	{		
		if(param.substring(0, 4) == "type")
		{
	    	fillDropDown(document.getElementById("stateList"), response.STATES);
		 	fillDropDown(document.getElementById("yearList"), response.YEARS);
			buildRadioButton(response.LEVELS);

		 	//fillDropDown(document.getElementById("partyList"), response.parties);
		}
		if(param.substring(0, 8) == "alliance")
		{		
			if(response == true) 
			{		
				if(navigator.appName == 'Microsoft Internet Explorer')				
					document.getElementById("allianceRow").style.display="block";										
				else
					document.getElementById("allianceRow").style.display="table-row";	
				
			}else
			{
				document.getElementById("alliances").checked = false;
				document.getElementById("allianceRow").style.display="none";
			}
		}
		if(param.substring(0, 7) == "stateId")
		{
	 		fillDropDown(document.getElementById("districtList"), response);			
	 		document.getElementById("districtList").disabled= false;  	 			 		
		}
    }
	
	function buildRadioButton(results)
	{		
		var divElmt = document.getElementById("reportLevelRadio");
		var distElmt = document.getElementById("districtList").disabled = true;

		var str='';
		
		str+='<input type="radio" checked="checked" name="1" value="'+results[0].id+'" onclick="getDistricts(this.value);">'+results[0].name+'</input>';
		str+='<input type="radio" name="1" value="'+results[1].id+'" onclick="getDistricts(this.value);">'+results[1].name+'</input>';
				
		if(divElmt)
			divElmt.innerHTML=str;

	}
	
    function fillDropDown(selectbox, data){
          selectbox.options.length = 0;
          for( var counter = 0 ; counter < data.length ; counter++ ) {
        	  if(data[counter].id == undefined){
                  addOption(selectbox, data[counter], data[counter]);
              } else {
          		  addOption(selectbox, data[counter].name, data[counter].id);
              }
     	}
    }
    
 	function addOption(selectbox,text,value){
 			var optn = document.createElement("OPTION");
 			optn.text = text;
 			optn.value = value;
 			selectbox.options.add(optn);
 	}

 	function doAjax(param){
 		var url = "<%=request.getContextPath()%>/partyPerformanceElectionTypeFilterData.action?";
 		callAjax("type="+param, url);
 	}

 	function getDistricts(level){
 	 	if(level == 2){
	 	 	var index = document.getElementById("stateList").selectedIndex;
	 	 	var stateId = document.getElementById("stateList").options[index].value;
	 	 	var url = "<%=request.getContextPath()%>/partyPerformanceDistrict.action?";
			callAjax("stateId="+stateId, url);
 	 	}
		if(level == 3){
			document.getElementById("alliances").disabled=true;
			document.getElementById("alliances").checked = false;
		}else {
 	 		document.getElementById("districtList").disabled= true; 
			document.getElementById("alliances").disabled=false;
			
 	 	}
		
 	}

 	function fetchDistricts(val)
	{
		var elmt = document.getElementById("stateNameHiddenId");
		if(!elmt)
			return;

		elmt.value=val;		

 	 	var reportLevels = document.getElementsByName("1");
 	 	for(var i=0; i < reportLevels.length; i++){
 	 	 	if(reportLevels[i].checked){
 	 	 		getDistricts(i+1);
 	 	 	}
 	 	}
 	}

 	function hasAllianceParties(){
 		var index = document.getElementById("partyList").selectedIndex;
 	 	var partyId = document.getElementById("partyList").options[index].value;
 	 	index = document.getElementById("yearList").selectedIndex;
 	 	var year = document.getElementById("yearList").options[index].value;
 	 	var radObj = document.getElementsByName("electionType"); 
 	 	var elecType = getSelectedValue(document.getElementsByName("electionType"));
 	 	var url = "<%=request.getContextPath()%>/partyPerformanceAllianceAjax.action?";		
 	 	callAjax("allianceWith="+partyId+"&year="+year+"&elecType="+elecType, url);
 	}
 	function getSelectedValue(radiobuttonlist) {
 	 	
 		for (i=0;i<radiobuttonlist.length;i++) {
 		           if(radiobuttonlist[i].checked == true)  {
 		                radiocheckedvalue = radiobuttonlist[i].value;
 		             }
 		   }
 		return radiocheckedvalue;
 	}

	function setPartyNameHidden(val)
	{
		var elmt = document.getElementById("partyNameHiddenId");
		if(!elmt)
			return;

		elmt.value=val;		
	}

	</script>
	<link href="styles/partyPerformance.css" rel="stylesheet" type="text/css" /> 

</head> 
<body>
<s:form name="performanceReport" action="partyPerformanceReport" onsubmit="javascript:{}" method="post">
<s:hidden name="country" value="1" id="country"/>
<table class="partyPerformanceCriteriaTable">
	<tr>
		<th colspan="2">
			<span style="margin: 0px; text-align: center;">Party Performance</span>
		</th>
	</tr>
	<tr>
		<th>Election Type</th>
		<td>
			<input type="radio" name="electionType" value="2" checked="checked" onclick="doAjax(this.value);"/>Assembly
			<input type="radio" name="electionType" value="1" onclick="doAjax(this.value);"/>Parliament
		</td>
	</tr>
	<tr>
		<th> State</th>
		<td>
			<s:select theme="simple" label="State" name="state" id="stateList" list="states" listKey="id" listValue="name" onchange="fetchDistricts(this.options[this.selectedIndex].text);"/>
			<input type="hidden" id="stateNameHiddenId" name="stateNameHidden"/>
		</td>
	</tr>
	<tr>
		<th> Year</th>
		<td><s:select theme="simple" label="Year" name="year" id="yearList" list="years" /></td>
	</tr>
	<tr>
		<th>Party</th>
		<td>
			<s:select theme="simple" label="Party" name="party" onchange="setPartyNameHidden(this.options[this.selectedIndex].text)" id="partyList" list="parties" listKey="id" listValue="name" />
			<input type="hidden" id="partyNameHiddenId" name="partyNameHidden"/>
		</td>
	</tr>
	
	<tr>
		<th>Report Level</th>
		<td><div id="reportLevelRadio"><s:radio  theme="simple" name="1" list="levels" listKey="id" listValue="name" onclick="getDistricts(this.value);"/></div></td>
	</tr>	
	<tr>		
		<th>			
			<s:label theme="simple" labelposition="top" for="districtList" labelSeparator="" id="distLabel" disabled="true" value="District:"/>
		</th>
		<td>
			<s:select theme="simple" name="district" id="districtList" list="districts"  disabled="true" listKey="id" listValue="name"/>	
		</td>
	</tr>	
	<tr id="allianceRow">
		<th>Alliance Parties</th>
		<td>
			<s:checkbox theme="simple" id="alliances" disabled="false" name="alliances" value="hasAllianceParties"></s:checkbox> Include in the Report
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><s:submit theme="simple" action="partyPerformanceReport" type="submit" value="View Report" /></td>
	</tr>
</table>
</s:form>
</body>
</html> 