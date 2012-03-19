<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ResourceBundle;" %>
<HTML>
 <HEAD>
	<TITLE>Party Performance Report</TITLE>

	<!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<!-- OPTIONAL: JSON (enables JSON validation) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
  	<!-- Dependencies --> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	 <style type="text/css">

		table.partyPerformanceCriteriaTable {
			border-collapse: collapse;
			color: #333333;
			font-family: verdana,arial,sans-serif;
			font-size: 13px;
		}

		table.partyPerformanceCriteriaTable th {
			background-color: #CFDCE3;
			border-color: #666666;
			border-style: solid;
			border-width: 1px;
			padding: 11px;
		}

		table.partyPerformanceCriteriaTable td {
			background-color: #FFFFFF;
			border-color: #666666;
			border-style: solid;
			border-width: 1px;
			padding: 11px;
		}

    </style>
	<script type="text/javascript">
	var labelResources = { <%		
	ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
	String electionType = rb.getString("electionType");
	String state = rb.getString("state");
	String electionYear  = rb.getString("electionYear");
	String party  = rb.getString("party");
	String dist = rb.getString("dist");
	String viewReport = rb.getString("viewReport");
	String alliances = rb.getString("alliances");
	String inclAlliances = rb.getString("inclAlliances");
	
	ResourceBundle pprRb = ResourceBundle.getBundle("ppr_Labels");
	String reportLevel = pprRb.getString("reportLevel");
	
	%> }
	
 	function pprCallAjax(param, url){
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

   function callAjax(jsObj, url){
		var resultVO;			
		var callback = {			
				   success : function( o ) {
						try {								
								resultVO = YAHOO.lang.JSON.parse(o.responseText);										

								if(jsObj.task == "getElectionYears")
								{								
									clearOptionsListForSelectElmtId(jsObj.elmtId);
									fillElectionYears(jsObj.elmtId, resultVO);
									//createOptionsForSelectElmtIdWithSelectOption(jsObj.elmtId,resultVO);		
																		
								}		
								if(jsObj.task == "getStatesListAjax")
							    {
                                   clearOptionsListForSelectElmtId(jsObj.elmtId);
								   fillPartiesInState(jsObj.elmtId, resultVO);
							    }
						}catch (e)  {   
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



	function fillPartiesInState(element, results)
	{
		var elmt = document.getElementById(element);
		
	
	if( !elmt || results == null)
		return;
	
	var option = document.createElement('option');
	
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards complaint
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}

	for(var i in results)
	{
		var option = document.createElement('option');
		option.value=results[i].id;
		option.text=results[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}

	}
 	
	function fillElectionYears(element, results)
	{
		var elmt = document.getElementById(element);
		
	
	if( !elmt || results == null)
		return;
	
	var option = document.createElement('option');
	
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}

	for(var i in results)
	{
		var option = document.createElement('option');
		option.text=results[i];
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}

	}

    function processResponse(param, response)
	{		
		if(param.substring(0, 4) == "type")
		{
	    	//fillDropDown(document.getElementById("stateList"), response.STATES);
		 	//fillDropDown(document.getElementById("yearList"), response.YEARS);
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

		var stateListEl = document.getElementById("stateList");
		stateListEl.disabled=false;
		var partyListEl = document.getElementById("partyList");
		var yearListEl = document.getElementById("yearList");
		if(stateListEl.options.length > 0)
			stateListEl.selectedIndex = '0';
		if(partyListEl.options.length > 0)
			partyListEl.selectedIndex = '0';
		if(yearListEl.options.length > 0)
			yearListEl.selectedIndex = '0';
		document.getElementById("alliances").disabled=false;
		document.getElementById("alliances").checked = false;
 		var url = "<%=request.getContextPath()%>/partyPerformanceElectionTypeFilterData.action?";
 		pprCallAjax("type="+param, url);
 	}

 	function getDistricts(level, flag){
 	 	
 	 	if(level == 2){
 	 		var stateListEl = document.getElementById("stateList");
 	 		var partyListEl = document.getElementById("partyList");
			var yearListEl = document.getElementById("yearList");
			
	 	 	var index = document.getElementById("stateList").selectedIndex;
	 	 	var stateId = document.getElementById("stateList").options[index].value;
	 	 	
	 	 	var url = "<%=request.getContextPath()%>/partyPerformanceDistrict.action?";
			pprCallAjax("stateId="+stateId, url);
 	 	}
		if(level == 3){
			document.getElementById("alliances").disabled=true;
			document.getElementById("alliances").checked = false;
			
			var stateListEl = document.getElementById("stateList");
			stateListEl.disabled=true;
			
			var yearListEl = document.getElementById("yearList");
			fetchPartiesInState('0');
			if(yearListEl.options.length > 0)
				yearListEl.selectedIndex = '0';
			
			
		}else {
 	 		document.getElementById("districtList").disabled= true; 
			document.getElementById("alliances").disabled=false;
			var stateListEl = document.getElementById("stateList");
			stateListEl.disabled=false;
				
			
 	 	}
		
 	}

 	function fetchDistricts(id,val)
	{
 		
		var elmt = document.getElementById("stateNameHiddenId");
		if(!elmt)
			return;

		elmt.value=val;		
		var yearsSelectEl = document.getElementById("yearList");
		var yearsSelectElOptions = yearsSelectEl.options; 
		if(yearsSelectElOptions.length > 0)
		{
			yearsSelectEl.selectedIndex = '0';
		}
 	 	var reportLevels = document.getElementsByName("1");
 	 	for(var i=0; i < reportLevels.length; i++){
 	 	 	if(reportLevels[i].checked){
 	 	 		getDistricts(i+1);
 	 	 	}
 	 	}
		fetchPartiesInState(id);
 	}

    function fetchPartiesInState(id)
	{

		var elecType = getSelectedValue(document.getElementsByName("electionType"));
		var reportLevel = getSelectedValue(document.getElementsByName("1"));
		var electionType;
		if(elecType == "1")
		{
			electionType = "Parliament";
		}
		else
		{
			electionType = "Assembly";
		}

		    var jsObj=
			{       elmtId:"partyList",
					stateId:id,
				    elecTypeId:electionType,
				    reportLevel:reportLevel,
					task:"getStatesListAjax"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getStaticPartyDetailsAjax.action?"+rparam;						
			
			callAjax(jsObj,url);

	}

 	function hasAllianceParties(){
 		var index = document.getElementById("partyList").selectedIndex;
 	 	var partyId = document.getElementById("partyList").options[index].value;
 	 	index = document.getElementById("yearList").selectedIndex;
 	 	var year = document.getElementById("yearList").options[index].value;
 	 	var radObj = document.getElementsByName("electionType"); 
 	 	var elecType = getSelectedValue(document.getElementsByName("electionType"));
 	 	var url = "<%=request.getContextPath()%>/partyPerformanceAllianceAjax.action?";		
 	 	pprCallAjax("allianceWith="+partyId+"&year="+year+"&elecType="+elecType, url);
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

	function getElectionYears(id,name)
	{
            setPartyNameHidden(name);
            var elecTypeVal='';
				
		   
			var stateElmt = document.getElementById("stateList");
			if(stateElmt == '' || stateElmt == null)
				alert("Please Select State ..")
			var stateVal = stateElmt.options[stateElmt.selectedIndex].value;
			
			var elecTypeElmts = document.getElementsByName("electionType");
           
			for(var i=0;i<elecTypeElmts.length;i++)
		    {
				if(elecTypeElmts[i].id == 'assemblyRadio' && elecTypeElmts[i].checked == true)
                   elecTypeVal = 'Assembly';
				else if(elecTypeElmts[i].id == 'parliamentRadio' && elecTypeElmts[i].checked == true)
                   elecTypeVal = 'Parliament';
		    }
			
			var jsObj=
			{       elmtId:"yearList",
					partyId:id,
				    elecTypeId:elecTypeVal,
				    stateId:stateVal,
					task:"getElectionYears"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsForParty.action?"+rparam;						
			
			callAjax(jsObj,url);
	}
	function validateClientSide()
	{
		
		var flag;
		var	electionYearElValuesSelected;
		var electionYearEl = document.getElementById("yearList");
		var electionYearElValues = electionYearEl.options;
		if(electionYearElValues.length > 0 )
		electionYearElValuesSelected = electionYearElValues[electionYearEl.selectedIndex].value;
		var errorMsg = document.getElementById("errorsDiv");
		errorMsg.innerHTML = '';
		
		if(electionYearEl == null || electionYearElValues.length == 0 || electionYearElValuesSelected == 'Select')
		{
			errorMsg.innerHTML = 'Invalid Input Selection';
			flag= false;
		} 
		else  
		{
			flag = true;
		}
		return flag;
	}
	window.history.forward(1);
  </script>
</head> 
<body>
  
   <div id="partyPerformanceMainDiv" style="margin-left: auto; margin-right: auto; float: none; height: 500px; clear: both; width: 360px;">
		<div id="errorsDiv" style="font-weight:bold;color:red;font-size:12px;margin:10px;">
		</div>
		<s:form name="performanceReport" action="partyPerformanceReport" onsubmit="return validateClientSide()" method="post">
		<s:hidden name="country" value="1" id="country"/>
			<table class="partyPerformanceCriteriaTable">
				<tr>
					<th colspan="2">
						<span style="margin: 0px; text-align: center;">Party Performance</span>
					</th>
				</tr>
				<tr>
					<th align="left"><%=electionType%></th>
					<td>
						<input id="assemblyRadio" type="radio" name="electionType" value="2" checked="checked" onclick="doAjax(this.value);"/>Assembly
					<input id="parliamentRadio" type="radio" name="electionType" value="1" onclick="doAjax(this.value);"/>Parliament
					</td>
				</tr>
				<tr>
					<th align="left"><%=reportLevel%></th>
					<td><div id="reportLevelRadio"><s:radio  theme="simple" name="1" list="levels" listKey="id" listValue="name" onclick="getDistricts(this.value);"/></div></td>
			</tr>	
			<tr>
				<th align="left"><%=state%></th>
				<td>
					<s:select theme="simple" label="State" name="state" id="stateList" list="states" cssStyle="width:120px;" listKey="id" listValue="name" onchange="fetchDistricts(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);"/>
					<input type="hidden" id="stateNameHiddenId" name="stateNameHidden"/>
				</td>
			</tr>
			
			<tr>
				<th align="left"><%=party%></th>
				<td>
					<!--<s:select theme="simple" label="Party" name="party" onchange="setPartyNameHidden(this.options[this.selectedIndex].text)" id="partyList" list="parties" listKey="id" listValue="name" />-->
					<s:select theme="simple" label="Party" name="party" onchange="getElectionYears(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)" id="partyList" list="{}" listKey="id" listValue="name" cssStyle="width:120px;" />
					<input type="hidden" id="partyNameHiddenId" name="partyNameHidden"/>
				</td>
			</tr>

			<tr>
				<th align="left"><%=electionYear%></th>
				<td><s:select theme="simple" label="Year" name="year" id="yearList" list="{}" cssStyle="width:120px;"/></td>
			</tr>
			
			<!--<tr>
				<th align="left"><%=reportLevel%></th>
				<td><div id="reportLevelRadio"><s:radio  theme="simple" name="1" list="levels" listKey="id" listValue="name" onclick="getDistricts(this.value);"/></div></td>
			</tr>-->	
			<tr>		
				<th align="left"><%=dist%></th>
				<td>
					<s:select theme="simple" name="district" id="districtList" list="districts"  disabled="true" listKey="id" listValue="name" cssStyle="width:120px;"/>	
				</td>
			</tr>	
			<tr id="allianceRow">
				<th align="left"><%=alliances%></th>
				<td>
					<s:checkbox theme="simple" id="alliances" disabled="false" name="alliances" value="hasAllianceParties"></s:checkbox><%=inclAlliances%>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><s:submit theme="simple" action="partyPerformanceReport" type="submit" value="View Report" /></td>
			</tr>
		</table>
   </s:form>
 </div>

</body>
</html>