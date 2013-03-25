<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Booth Results</TITLE>

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
		
	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	
	<!-- YUI Dependency files (End) -->	

<STYLE>
	#boothInfoDiv_head {
	color:#747E84;
	font-weight:bold;
	padding:5px;
	text-decoration:underline;
	text-align:left;
	margin-bottom:10px;
	}
	#boothInfoTable {
	border:2px solid #EFEFEF;
}
#boothInfoTable th {
	background-color:#567AAF;
	color:#FFFFFF;
	padding:5px;
	width:20%;
	text-align:left;
	font-size:12px;
}
#boothInfoTable td {
	background-color:#F2F6F9;
	padding:5px;
}
fieldset {
	background-color:#F3F6F7;
	border:4px solid #CFD6DF;
	margin-bottom:10px;
	
}
legend
{
	background-color:#567AAF;
	color:#FFFFFF;
	font-size:12px;
	padding:5px;
	font-weight: bold;
}
.yui-skin-sam .yui-dt td {
	border-color:-moz-use-text-color #CBCBCB -moz-use-text-color -moz-use-text-color;
	border-style:none solid none none;
	border-width:medium 1px medium medium;
	font-size:12px;
	margin:0;
	padding:0;
	text-align:left;
}
</STYLE>
<SCRIPT type="text/javascript"> 

	
	var boothResultsObj = {
		partyResultsArr:[]
			};
	function buildPartyResultsDT(divId,dtSource)
	{
		var myDataSource = new YAHOO.util.DataSource(dtSource); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		 myDataSource.responseSchema = { 
		            fields: [
								{
									key : "partyName"
								},{
									key : "candidateName"
								},{
									key : "votesEarned",parser:"number"
								}
							]    
		        }; 
		
		 var myColumnDefs = [ 
		            {key:"partyName",label:'Party Name', sortable:true, resizeable:true}, 
		            {key:"candidateName", label:'Candidate Name', sortable:true, resizeable:true}, 
		            {key:"votesEarned", label:'Votes Earned',sortable:true, resizeable:true}		         
		        ]; 
		 
		var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource);
	}
	
</SCRIPT>
</HEAD>
<BODY>
	<DIV id="boothInfoDiv_main">
		<DIV id="boothInfoDiv_head"> Booth Details</DIV>
		<TABLE id="boothInfoTable" width="100%">
		<TR><TH>Part No:</TH>
		<TD>${boothPanelVO.partNo}</TD>
		<TH>Location:</TH>
		<TD>${boothPanelVO.location}</TD></TR>
		
		<TR><TH>Villages Covered:</TH>
		<TD>${boothPanelVO.villagesCovered}</TD>
		<TH>Male Voters:</TH>
		<TD>${boothPanelVO.maleVoters}</TD></TR>
		
		<TR><TH>Female Voters:</TH>
		<TD>${boothPanelVO.femaleVoters}</TD>
		<TH>Total Voters:</TH>
		<TD>${boothPanelVO.totalVoters}</TD></TR>
		
		<TR><TH>Mandal/Local Body:</TH>
		<TD>${boothPanelVO.mandal}</TD>
		<c:if test="${! empty boothPanelVO.wardInfo}">
			<TH>Ward:</TH>
			<TD>${boothPanelVO.wardInfo}</TD>
		</c:if></TR>
		</TABLE>
	</DIV>
	<DIV style="margin-top:10px;" class="yui-skin-sam">
		<DIV id="boothInfoDiv_head"> Booth Results</DIV>
		<TABLE width="100%" align="center">
			<TR>
				<c:forEach var="electionsList" varStatus="status" items="${boothPanelVO.elections}">
					<TD valign="top">
						<FIELDSET>
							<LEGEND>${electionsList.constituencyName}  &nbsp;${electionsList.electionTypeYear} </LEGEND>
							<DIV id="div_${status.index}" class="boothDatatable"></DIV>
													
						</FIELDSET>
					</TD>
				</c:forEach>			
			</TR>
		</TABLE>
	</DIV>
	<SCRIPT type="text/javascript">
	<c:forEach var="electionsList" varStatus="status" items="${boothPanelVO.elections}">
	
	var partyResultsArr = new Array();
	<c:forEach var="partyResults" varStatus="status1" items="${electionsList.partyResults}">
			var partyResultsObj = {
					 partyName: "${partyResults.partyName}",
					 candidateName: "${partyResults.candidateName}",
					 votesEarned: "${partyResults.votesEarned}"
								};
			partyResultsArr.push(partyResultsObj);			
		</c:forEach>
		buildPartyResultsDT("div_${status.index}",partyResultsArr);
	</c:forEach>		
	</SCRIPT>	
</BODY>
</HTML>