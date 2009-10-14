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
	<script type="text/javascript" src="js/partyPerformance.js" ></script>

	<link href="styles/partyPerformance.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function callAjax(param){
 		var myResults;
 		var url = "<%=request.getContextPath()%>/partyPerformanceAjax.action?"+param;		
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
 	
    function processResponse(param, response){
		if(param.substring(0, 4) == "type"){
	    	fillDropDown(document.getElementById("stateList"), response.states);
		 	fillDropDown(document.getElementById("yearList"), response.years);
		 	fillDropDown(document.getElementById("partyList"), response.parties);
		} else {
	 		fillDropDown(document.getElementById("districtList"), response.districts);			
	 		document.getElementById("districtList").disabled= false;  	 			 		
		}
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
 		callAjax("type="+param);
 	}

 	function getDistricts(level){
 	 	if(level == 2){
	 	 	var index = document.getElementById("stateList").selectedIndex;
	 	 	var stateId = document.getElementById("stateList").options[index].value;
			callAjax("stateId="+stateId);
 	 	} else {
 	 		document.getElementById("districtList").disabled= true;  
 	 	}
 	}

 	function fetchDistricts(){
 	 	var reportLevels = document.getElementsByName("1");
 	 	for(var i=0; i < reportLevels.length; i++){
 	 	 	if(reportLevels[i].checked){
 	 	 		getDistricts(i+1);
 	 	 	}
 	 	}
 	}
	</script>
	
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
			<input type="radio" name="electionType" value="2" onclick="doAjax(this.value);"/>Assembly
			<input type="radio" name="electionType" value="1" onclick="doAjax(this.value);"/>Parliament
		</td>
	</tr>
	<tr>
		<th> State</th>
		<td>
			<s:select theme="simple" label="State" name="state" id="stateList" list="states" listKey="id" listValue="name" onchange="fetchDistricts();"/>
		</td>
	</tr>
	<tr>
		<th> Year</th>
		<td><s:select theme="simple" label="Year" name="year" id="yearList" list="years"  /></td>
	</tr>
	<tr>
		<th>Party</th>
		<td><s:select theme="simple" label="Party" name="party" id="partyList" list="parties" listKey="id" listValue="name"/></td>
	</tr>
	<tr>
		<th>Report Level</th>
		<td><s:radio  theme="simple" name="1" list="levels" listKey="id" listValue="name" onclick="getDistricts(this.value);"/></td>
	</tr>	
	<tr>		
		<th>			
			<s:label theme="simple" labelposition="top" for="districtList" labelSeparator="" id="distLabel" disabled="true" value="District:"/>
		</th>
		<td>
			<s:select theme="simple" name="district" id="districtList" list="districts"  disabled="true" listKey="id" listValue="name"/>	
		</td>
	</tr>	
	<tr>
		<td colspan="2" align="center"><s:submit theme="simple" action="partyPerformanceReport" type="submit" value="View Report" /></td>
	</tr>
</table>
</s:form>
</body>
</html> 