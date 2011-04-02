<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
 <HEAD>
	<TITLE>Add Rebel Candidates to Party Page</TITLE>

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
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
 	}
 	
    function processResponse(param, response){

		if(param.substring(0, 4) == "type"){
	    	fillDropDown(document.getElementById("stateList"), response.states);
		 	fillDropDown(document.getElementById("yearList"), response.years);
		 	fillDropDown(document.getElementById("partyList"), response.parties);
		} else if(param.substring(0, 5) == "state") {
	 		fillDropDown(document.getElementById("candidateList"), response.candidates);	
	 		fillDropDown(document.getElementById("rebelList"), response.rebelCandidates);					 		
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

 	function removeOptions(selectbox)
 	{
	 	var i;
	 	for(i=selectbox.options.length-1;i>=0;i--)
	 	{
		 	if(selectbox.options[i].selected)
		 	selectbox.remove(i);
	 	}
 	}
	
 	function doAjax(param){
 		var url = "<%=request.getContextPath()%>/addRebelsAction.action?";
 		callAjax("type="+param, url);
 	}

 	function getCandidates(){
	 	 	var sIndex = document.getElementById("stateList").selectedIndex;
	 	 	var pIndex = document.getElementById("partyList").selectedIndex;
	 	 	var eIndex = document.getElementById("yearList").selectedIndex;
	 	 	var stateId = document.getElementById("stateList").options[sIndex].value;
	 	 	var partyId = document.getElementById("partyList").options[pIndex].value;
	 	 	var electionId = document.getElementById("yearList").options[eIndex].value;
	 	 	var url = "<%=request.getContextPath()%>/addRebelsAjax.action?";
	 	 	var params = "stateId="+stateId+"&partyId="+partyId+"&electionId="+electionId;
			callAjax(params, url);
 	
 	}

function addRebel()
{
	var candList = document.getElementById("candidateList");
	var index = candList.selectedIndex;
	var candidateId = candList.options[index].value;
	var candidateName = candList.options[index].text;

	var data =
	 		{
	 			"id":candidateId,
	 			"name":candidateName
	 		} 
	var selectBox = document.getElementById("rebelList");
	 addOption(selectBox, data.name, data.id);
	 candList.remove(index); 
	
}

function removeRebel()
{
	var selectBox = document.getElementById("rebelList");
	var i;
 	for(i=selectBox.options.length-1;i>=0;i--)
 	{
 	 	var option = selectBox.options[i];
	 	if(option.selected)	{
			addOption(document.getElementById("candidateList"), option.text, option.value);
			selectBox.remove(i);
	 	}
 	}
}

function submitRebels() {
	var rebels;
	var selectBox = document.getElementById("rebelList");
	var i;
 	for(i=selectBox.options.length-1;i>=0;i--)
 	{
 	 	var option = selectBox.options[i];
 	 	if(i == selectBox.options.length-1) {
	 		rebels = option.value;
 	 	} else {
	 		rebels = rebels + "," + option.value;
 	 	}
 	}
	document.getElementById("rebels").value = rebels;
	
}
	</script>
		<script type="text/javascript" src="js/cncSearch.js"></script>
	<link href="styles/partyPerformance.css" rel="stylesheet" type="text/css" /> 
	
</head> 
<body >
<s:form name="addRebels" action="addRebelsSubmit"  method="GET">
<s:hidden name="country" value="1" id="country"/>
<table class="partyPerformanceCriteriaTable">
	<tr>
		<th colspan="2">
			<span style="margin: 0px; text-align: center;">Add Rebels</span>
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
			<s:select theme="simple" label="State" name="state" id="stateList" list="states" listKey="id" listValue="name" onchange="getCandidates()"/>
		</td>
	</tr>
	<tr>
		<th> Year</th>
		<td><s:select theme="simple" label="Year" name="year" id="yearList" list="years" listKey="id" listValue="name" onchange="getCandidates()"/></td>
	</tr>
	<tr>
		<th>Constituency</th>
		<td><s:select theme="simple"  label="Constituency" name="constituency" id="constList" list="constituencies" listKey="id" listValue="name" onchange="getCandidates()" /></td>
	</tr>
	<tr>
		<th>Party</th>
		<td><s:select theme="simple"  label="Party" name="party" id="partyList" list="parties" listKey="id" listValue="name" onchange="getCandidates()" /></td>
	</tr>
	
	<tr>
		<th>Candidate</th>
		<td><s:select theme="simple" id="candidateList"  list="candidates" listKey="id" listValue="name"/>
         <button type="button" name="add" onclick="addRebel()">Add</button> 	
		</td>
	</tr>
	<tr>
		<th>Rebels</th>
		<td><s:select theme="simple" id="rebelList" list="rebelCandidates" listKey="id" listValue="name"/>
			  <button type="button" name="remove" onclick="removeRebel()">Remove</button>   
		</td>
	</tr>
	<tr>
	<th><input type="hidden" name="rebels" id="rebels" /></th>
	<td><button type="submit" name="submit" value="Submit" onclick="submitRebels()">Submit</button>
	</td>
	</tr>
</table>
</s:form>
</body>
</html> 