<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mandal Voting Report</title>

	<!-- Combo-handled YUI CSS files: -->
<link rel="stylesheet" type="text/css"
	href="http://yui.yahooapis.com/combo?2.8.0r4/build/datatable/assets/skins/sam/datatable.css">
<!-- Combo-handled YUI JS files: -->
<script type="text/javascript"
	src="http://yui.yahooapis.com/combo?2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js&2.8.0r4/build/element/element-min.js&2.8.0r4/build/datasource/datasource-min.js&2.8.0r4/build/datatable/datatable-min.js"></script>


	<script type="text/javascript" src="js/json/json-min.js"></script> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript">
		function getList(name,value)
		{ 
			var jsObj=
			{
				type:name,
				value:value
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/mandalPageDetailAction.action?"+rparam;			
			callAjax(jsObj,url, name);
		}


		function callAjax(jsObj,url,name){
			var myResults;	 		
	 		var callback = {			
               success : function( o ) {
				try {
					myResults = YAHOO.lang.JSON.parse(o.responseText);
					
					if(jsObj.task=="mandalVoting")
					{
						buildMandalVoting(myResults);
					}
					else
					{
						buildSelectOption(myResults,name);
					}							
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
		
		function buildMandalVoting(myResult)
		{
			var result = myResult.mandalAllElectionDetailsVO;
			
			if(result == "")
			{
				alert("Result empty or not found");
				return;
			}
 	 		var elmt= document.getElementById("mandalVotingResultsDiv");
 	 		var elmtHead= document.getElementById("mandalVotingResultsDivHead");
 	 		var elmtBody= document.getElementById("mandalVotingResultsDivBody");
			var elmtBodyGraph= document.getElementById("mandalVotingResultsDivGraph");

			var mandalElmt = document.getElementById("mandalField");
			var mandalValue = mandalElmt.options[mandalElmt.selectedIndex].text;

 	 		if(elmtHead)
 	 	 		elmtHead.innerHTML="Election Results for "+result[0].partyShortName+" party in "+mandalValue+" mandal";
			
			var imgStr='';
			imgStr+='<IMG id="chartImg" SRC="charts/'+myResult.chart+'" WIDTH="450" HEIGHT="400">';

			if(elmtBodyGraph)
				elmtBodyGraph.innerHTML=imgStr;

 	 	 	var str='';
 	 	 	str+='<table id="mandalVotingTable">';
 	 	 	for(var i in result)
			{
 	 	 		str+='<tr>';
				str+='<td>'+result[i].candidateName+'</td>';
				str+='<td>'+result[i].electionType+'</td>';
				str+='<td>'+result[i].electionYear+'</td>';
				str+='<td>'+result[i].partyShortName+'</td>';
				str+='<td align="right">'+result[i].partyVotesPercentage+'</td>';				
				str+='<td align="right">'+result[i].totalVoters+'</td>';
				str+='<td align="right">'+result[i].validVoters+'</td>';
				str+='</tr>';	
			}
			str+='</table>';
			
			if(elmtBody)
				elmtBody.innerHTML=str;

			buildMandalDataTable();
		}
		
		function buildMandalDataTable()
		{
			var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("mandalVotingTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "candidateName"
		}, {
			key : "electionType"
		}, {
			key : "electionYear",parser:"number"
		}, {
			key : "partyShortName"
		}, {
			key : "partyVotesPercentage",parser:"number"
		}, {
			key : "totalVoters",parser:"number"
		} , {
			key : "validVoters",parser:"number"
		} ]
	};

	var resultsColumnDefs = [ {
		key : "candidateName",		
		label : "Name",
		sortable : true
	}, {
		key : "electionType",
		label : "Election",
		sortable : true
	}, {
		key : "electionYear",
		label : "Year",
		sortable : true
	}, {
		key : "partyShortName",
		label : "Party",
		sortable : true
	}, {
		key : "partyVotesPercentage",
		label : "Votes %",
		sortable : true
	},{
		key : "totalVoters",
		label : "Total Voters",
		sortable : true
	}, {
		key : "validVoters",
		label : "Valid Voters",
		sortable : true
	} ];

	var myDataTable = new YAHOO.widget.DataTable("mandalVotingResultsDivBody",resultsColumnDefs, resultsDataSource,{});  
		}

		function buildSelectOption(results,selectedValue)
		{
			var selectedElmt;
			selectOption='';
			if(selectedValue=="STATE")
			{
				selectOption='District';
				selectedElmt=document.getElementById("districtField");
			}
			else if(selectedValue=="DISTRICT")
			{
				selectOption='Constituency';
				selectedElmt=document.getElementById("constituencyField");
			}
			else if(selectedValue=="CONSTITUENCY")
			{
				selectOption='Mandal';
				selectedElmt=document.getElementById("mandalField");
			}
			
			var len=selectedElmt.length;			
			for(i=len-1;i>=0;i--)
			{
				selectedElmt.remove(i);
			}
			var opElmt1=document.createElement('option');
			opElmt1.value=0;
			opElmt1.text='Select '+selectOption;
			
			try
			{
				selectedElmt.add(opElmt1,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt1); // IE only
			}	

			for(var val in results)
			{			
				var opElmt=document.createElement('option');
				opElmt.value=results[val].id;
				opElmt.text=results[val].name;
				
				try
				{
					selectedElmt.add(opElmt,null); // standards compliant
				}
				catch(ex)
				{
					selectedElmt.add(opElmt); // IE only
				}			
			}
		}

		function getMandalVotingReport()
		{
			var stateElmt = document.getElementById("stateField");
			var districtElmt = document.getElementById("districtField");
			var constituencyElmt = document.getElementById("constituencyField");
			var mandalElmt = document.getElementById("mandalField");
			var partyElmt = document.getElementById("partyField");

			if(!stateElmt || !districtElmt || !constituencyElmt || !mandalElmt || !partyElmt)
				return;

			var stateValue = stateElmt.options[stateElmt.selectedIndex].value;
			var districtValue = districtElmt.options[districtElmt.selectedIndex].value;
			var constituencyValue = constituencyElmt.options[constituencyElmt.selectedIndex].value;
			var mandalValue = mandalElmt.options[mandalElmt.selectedIndex].value;
			var partyValue = partyElmt.options[partyElmt.selectedIndex].value;

			if(stateValue == "0" || districtValue == "0" || constituencyValue == "0" || mandalValue == "0" || partyValue == "0")
				return;
			
			var jsObj={
					state:stateValue,
					district:districtValue,
					constituency:constituencyValue,
					mandal:mandalValue,
					party:partyValue,
					"task":"mandalVoting"
				};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/mandalVotingResultAction.action?"+rparam;			
			callAjax(jsObj,url,"");
		}
	</script>
	<style type="text/css">
		.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
		{
			background:none;
		}

		.yui-skin-sam thead .yui-dt-sortable {

			background-color:#B5BDCA;
			color:#3F546F;
		}
		.searchresultsTable td {
			background-color:#F8FBFF;
		}
		.searchresultsTable th {
			background-color:#C4DEFF;
		}
		.yui-skin-sam .yui-dt-liner {
			padding:4px 8px;
		}	
		.yui-skin-sam tr.yui-dt-odd
		{
			background-color:#F3F6FC;
		}
		.yui-skin-sam tr.yui-dt-odd td.yui-dt-asc, .yui-skin-sam tr.yui-dt-odd td.yui-dt-desc 
		{
			background-color:#E8EDF5;
		}
		.yui-skin-sam tr.yui-dt-even td.yui-dt-asc, .yui-skin-sam tr.yui-dt-even td.yui-dt-desc 
		{
			background-color:#F8FAFF;
		}
		#mandalVotingResultsDiv
		{
			margin-top:25px;
			margin-left:40px;
		}
		#mandalVotingResultsDivHead
		{
			text-decoration:underline;
			text-align:left;
			font-weight:bold;
			padding:10px;
			color:#233B7A;
		}
		#mandalVotingResultsDivBody
		{
			padding:5px;
			text-align:left;
		}
		#mandalVotingTable
		{
			border:1px solid #AFC9DC;
		}
		
		#mainHeadingDiv
		{
			font-weight:bold;
			padding:10px;
			color:#23318B;
			font-size:17px;
			text-decoration:underline;
		}
		#mandalSelectTableDiv
		{
			background-color:#E8E9EE;
			margin-left:50px;
			margin-right:50px;
			text-align:left;
			border:2px solid #DADADA;
		}
		
		#mandalSelectTableDivHead
		{
			padding:10px;
			color:#59629C;
			text-decoration:underline;
			font-weight:bold;
		}
		#mandalSelectTable th
		{
			padding:5px;
			color:#2B4A77;
		}
		.mySelectBox
		{
			width:150px;
			padding:2px;
			color:#334C72;
		}
		.tdLeftBorder
		{
			border-left:1px solid #8E9AAE;			
		}
	</style>

</head>
<body>
	<s:form action="mandalPageDetailAction" method="POST" theme="simple">
		<div id="mainHeadingDiv">Mandal / Tehsil Page Info</div>
		<div id="mandalSelectTableDiv">
			<div id="mandalSelectTableDivHead">Select Criteria ..</div>
			<div id="mandalSelectTableDivBody">
				<table id="mandalSelectTable" width="100%">
					<tr>
						<th align="left"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /></th>
						<td align="left">
							<s:select id="stateField" name="state" list="states" listKey="id" listValue="name" onchange="getList('STATE',this.options[this.selectedIndex].value)" cssClass="mySelectBox"></s:select>					
						</td>

						
					
						<th align="left" class="tdLeftBorder" style="padding-left:30px;"><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}" /></th>
						<td align="left">
							<select class="mySelectBox" id="districtField" name="district" onchange="getList('DISTRICT',this.options[this.selectedIndex].value)">
								<option value="0">Select District</option>
							</select>					
						</td>
					</tr>
					<tr>
						<th align="left"><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}" /></th>
						<td align="left">
							<select class="mySelectBox" id="constituencyField" name="constituency" onchange="getList('CONSTITUENCY',this.options[this.selectedIndex].value)">
								<option value="0">Select Constituency</option>
							</select> 
						</td>
					
						
						<th align="left" class="tdLeftBorder" style="padding-left:30px;"><s:label for="mandalField" id="mandalLabel"  value="%{getText('MANDAL')}" /></th>
						<td align="left">
							<select class="mySelectBox" id="mandalField" name="mandal">
								<option value="0">Select Mandal</option>
							</select>	
							
						</td>							
					</tr>
					<tr>
						<th align="left"><s:label for="partyField" id="mandalLabel"  value="%{getText('PARTY')}" /></th>
						<td align="left">
							<s:select cssClass="mySelectBox" id="partyField" name="party" list="partyList" listKey="id" listValue="name" onchange="getMandalVotingReport()"></s:select>	
						</td>
					</tr>
				</table>
				</div>
		</div>
	</s:form>
	<div id="mandalVotingResultsDiv">
		<div id="mandalVotingResultsDivHead"></div>
		<div id="mandalVotingResultsDivGraph"></div>
		<div id="mandalVotingResultsDivBody" class="yui-skin-sam"></div>
	</div>
</body>
</html>