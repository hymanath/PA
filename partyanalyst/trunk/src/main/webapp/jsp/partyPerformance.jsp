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
			padding: 5px;
		}

		table.partyPerformanceCriteriaTable td {
			background-color: #FFFFFF;
			border-color: #666666;
			border-style: solid;
			border-width: 1px;
			padding: 5px;
		}
		.ajaxImageStyle
		{
		   float:left;
		   margin-left:125px;
		   margin-top:-19px;
		}
		label
		{
			display:inline !important;
		}
		#partyPerformanceReport_partyPerformanceReport
		{
		background-color: #4B74C6; 
		font-weight: bold; 
		padding: 5px;
		border-radius: 5px 5px 5px 5px; 
		color: #FFF;
		}
		#partyPerformanceReport_11,#partyPerformanceReport_12
		{
			margin-right: 3px;
		}
		.marginClass{

	margin:5px;
}
	.selectBoxWidth {
	    padding: 2px;
	    width: 250px;
	}
	.span2{
	    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		font-weight: bold;
		margin-top: 8px;	
	}
	select {
	    background-color: #FFFFFF;
	    border: 1px solid #CCCCCC;
	    width: 250px;
	}
	
	select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
	    border-radius: 4px 4px 4px 4px;
		color: #000000;
	    display: inline-block;
	    font-size: 13px;
	    line-height: 18px;
	    padding: 4px;
	}
	input, button, select, textarea {
	    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
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
							   	//alert("Invalid JSON result" + e);   
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
									hidePartySelectAjaxImage("party");
																		
								}		
								if(jsObj.task == "getStatesListAjax")
							    {
                                   clearOptionsListForSelectElmtId(jsObj.elmtId);
								   fillPartiesInState(jsObj.elmtId, resultVO);
								   hidePartySelectAjaxImage("state");
							    }
								/*if(jsObj.task == "checkPartyPerformance")
							    {
									var errorMsg = document.getElementById("errorsDiv");
									errorMsg.innerHTML = '';								
									if(!resultVO)	
										errorMsg.innerHTML ='<span style="font-size: 13px;">'+ partyName+' party doesnot have any seats,So we cannot analyse on this view </span>';
									else {	
											document.performanceReport.submit();
										}
							    }*/
						}catch (e)  {   
							//alert("Invalid JSON result" + e);   
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
		
		str+='<input type="radio" id="stateRadio" checked="checked" name="1" value="'+results[0].id+'" onclick="getDistricts(this.value);">'+results[0].name+'</input>';
		str+='<input type="radio" id="countryRadio"" name="1" value="'+results[1].id+'" onclick="getDistricts(this.value);">'+results[1].name+'</input>';
				
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
 	var partyId = $('#partyList').val();
	var stateVal = $('#stateList').val();
	var electionYear = $('#yearList').val();
 	 	if(level == 2){
 	 		var stateListEl = document.getElementById("stateList");
 	 		var partyListEl = document.getElementById("partyList");
			var yearListEl = document.getElementById("yearList");
			
	 	 	var index = document.getElementById("stateList").selectedIndex;
	 	 	var stateId = document.getElementById("stateList").options[index].value;
	 	 	
	 	 	var url = "<%=request.getContextPath()%>/partyPerformanceDistrict.action?";
			pprCallAjax("stateId="+stateVal+"&partyId="+partyId+"&electionYear="+electionYear, url);
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

	function isEnable(value){
		if(value == 1)
 	 	document.getElementById("districtList").disabled= true; 
		if(value == 2)
		document.getElementById("districtList").disabled= false; 
	}
 	function fetchDistricts(id)
	{
 		
		var elmt = document.getElementById("stateNameHiddenId");
		if(!elmt)
			return;

		//elmt.value=val;		
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
	//	fetchPartiesInState(id);
 	}

    function fetchPartiesInState(id)
	{

		var elecType = getSelectedValue(document.getElementsByName("electionType"));
		var reportLevel = getSelectedValue(document.getElementsByName("1"));
		var electionType;
		showPartySelectAjaxImage("state");
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
				
		    showPartySelectAjaxImage("party");
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
			//checkPartyPerformanceDetails();
		}
		return flag;
		//return false;
	}
	
	/* var partyName = '';
	function checkPartyPerformanceDetails(){
		
		var flag = false;
		var electionType='';
		var reportLevel=3;
		var countryId = 1;		
		var stateId = null;
		var districtId =null;
		var index = document.getElementById("partyList").selectedIndex;
		partyName = document.getElementById('partyList').options[index].text;
		var partyId =$('#partyList').val();
		var year = $('#yearList').val();
		var remember = document.getElementById('alliances');
			stateId = $('#stateList').val();
			districtId=$('#districtList').val();
			if(assemblyRadio.checked){
				electionType = 2;
				}
			if(parliamentRadio.checked){
					electionType = 1;
				}
			if(stateId == null)
				stateId = 0;				
			if(districtId == null)
				districtId = 0;
			if(assemblyRadio.checked){
				if(partyPerformanceReport_11.checked)
							reportLevel=1;
				else if(partyPerformanceReport_12.checked)
							reportLevel=2;
			}
			if(parliamentRadio.checked){
				if(stateRadio.checked)
							reportLevel=1;
			}
			 if (remember.checked)
				flag = true;	
				
		var jsObj={     
				district:districtId,
				country:countryId,
				reportLevel:reportLevel,
				party:partyId,
				electionType:electionType,
				year:year,
				state:stateId,
				alliances:flag,
				task:"checkPartyPerformance"						
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);		
		var url = "<%=request.getContextPath()%>/partyPerformanceReportAction.action?"+rparam;	
		callAjax(jsObj,url);
	}
	*/
	function showPartySelectAjaxImage(decide)
	{
         if(decide=="state")
		 {
		    var ajaxImageDivEle = document.getElementById("stateSelectAjaxImgId");
		    ajaxImageDivEle.style.display = 'block';
		 }
		 else if(decide=="party")
		 {
			 var ajaxImageDivEle = document.getElementById("partySelectAjaxImgId");
		     ajaxImageDivEle.style.display = 'block';
		 }
	}

	function hidePartySelectAjaxImage(decide)
	{
		
         //var stateEle = document.getElementById("stateList");
		 //var partyEle = document.getElementById("partyList");
		 if(decide=="state")
		 {
		     var ajaxImageDivEle = document.getElementById("stateSelectAjaxImgId");
		     ajaxImageDivEle.style.display = 'none';
		 }
		 else if(decide=="party")
		 {
			 var ajaxImageDivEle = document.getElementById("partySelectAjaxImgId");
		     ajaxImageDivEle.style.display = 'none';
		 }
		
	}
	window.history.forward(1);	
  </script>
</head> 
<body>
  
   <div id="partyPerformanceMainDiv" style="margin-left: auto; margin-right: auto; float: none; height: 500px; clear: both; width: 360px;margin-top:50px;">
		<div id="errorsDiv" style="font-weight:bold;color:red;font-size:12px;margin:10px;">
		</div>
		<s:form name="performanceReport" action="partyPerformanceReport" onsubmit="return validateClientSide()" method="post">
		<s:hidden name="country" value="1" id="country"/>
			<table class="partyPerformanceCriteriaTable">
				<tr>
					<th colspan="2" style="padding: 10px;">
						<span style="margin: 0px; text-align: center;">Party Performance</span>
					</th>
				</tr>
				<tr>
					<th align="left"><%=electionType%></th>
					<td>
						<input id="assemblyRadio" type="radio" name="electionType" value="2" checked="checked" onclick="doAjax(this.value);" style="margin-right: 3px;"/>Assembly
					<input id="parliamentRadio" type="radio" name="electionType" value="1" onclick="doAjax(this.value);" style="margin-right: 3px;"/>Parliament
					</td>
				</tr>
				<!-- <tr>
					<th align="left"><%=reportLevel%></th>
					<td><div id="reportLevelRadio"><s:radio  theme="simple" name="1" list="levels" listKey="id" listValue="name" onchange="isEnable(this.value);"/></div></td>
			</tr>	-->
			<tr>
				<th align="left"><%=state%></th>
				<td>
					<div><s:select theme="simple" label="State" name="state" id="stateList" list="states" cssStyle="width:150px;" listKey="id" listValue="name" onchange="fetchPartiesInState(this.options[this.selectedIndex].value);"/></div><!-- // onchange="fetchDistricts(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);"/>-->
					<input type="hidden" id="stateNameHiddenId" name="stateNameHidden" style="margin-right: 3px;"/>
					<div class="ajaxImageStyle"><img id="stateSelectAjaxImgId" class="ajaxImgClass" style="display: none;" src="images/icons/search.gif"></div>
				</td>
			</tr>
			
			<tr>
				<th align="left"><%=party%></th>
				<td>
					<!--<s:select theme="simple" label="Party" name="party" onchange="setPartyNameHidden(this.options[this.selectedIndex].text)" id="partyList" list="parties" listKey="id" listValue="name" />-->
					<div><s:select theme="simple" label="Party" name="party" onchange="getElectionYears(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)" id="partyList" list="{}" listKey="id" listValue="name" cssStyle="width:150px;" /></div>
					<input type="hidden" id="partyNameHiddenId" name="partyNameHidden"/>
					<div class="ajaxImageStyle"><img id="partySelectAjaxImgId" class="ajaxImgClass" style="display: none;" src="images/icons/search.gif"></div>
				</td>
			</tr>

			<tr>
				<th align="left"><%=electionYear%></th>
				<td>
				    <div><s:select theme="simple" label="Year" name="year" id="yearList" list="{}" cssStyle="width:150px;"/></div>
				</td>
			</tr>
			
			<tr>
				<th align="left"><%=reportLevel%></th>
				<td><div id="reportLevelRadio"><s:radio  theme="simple" name="1" list="levels" listKey="id" listValue="name" onclick="getDistricts(this.value);"/></div></td>
			</tr>	
			<tr>		
				<th align="left"><%=dist%></th>
				<td>
					<s:select theme="simple" name="district" id="districtList" list="districts"  disabled="true" listKey="id" listValue="name" cssStyle="width:150px;"/>	
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