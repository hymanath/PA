<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.ResourceBundle;" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></SCRIPT> 
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></SCRIPT>

<LINK rel="stylesheet" type="text/css" href="styles/ElectionsReslutsPage/electionResultsPage.css">
<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable.css">
<TITLE>${stateName} ${electionType} ${selectedElectionYear} Election Districtwise Results</TITLE>
</HEAD>
<SCRIPT type="text/javascript">
var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String party = rb.getString("party");
		String totalParticipated = rb.getString("totalParticipated"); 
		String seatsWon = rb.getString("seatsWon");
		String seatsLost = rb.getString("seatsLost");
		String votesEarned = rb.getString("votesEarned");
		String percentage  = rb.getString("percentage");
		//String address  = rb.getString("address");
		//String location  = rb.getString("location");
		//String name = rb.getString("name");
		//String email = rb.getString("email");
		//String telephoneNo = rb.getString("telephoneNo");
		//String designation = rb.getString("designation"); 
		
%> }
function buildAllDistrictResultsDataTable()
{	
	
	var allDistrictResultsColumnDefs = [
								{key: "distName", label: "District", sortable:true},		
								{key: "inc", label: "INC",formatter:"number", sortable:true},	
		              	 	    {key: "mahakutami", label: "Maha Kutami", formatter:"number", sortable:true},
		              	 	 	{key: "prpManaParty", label: "PRP ManaParty",formatter:"number", sortable:true},
		              	 	 	{key: "aimim", label: "aimim",formatter:"number", sortable:true},
		              	 	 	{key: "ind", label: "IND",formatter:"number", sortable:true},
		              	 	 	{key: "bjp", label: "BJP",formatter:"number", sortable:true},   	
		              	 	 	{key: "lsp", label:"LSP", formatter:"number",sortable: true}
		              	 	 			              	 	 	
		              	 	    ];                	 	    

		var allDistrictResultsDataSource = new YAHOO.util.DataSource(); 
		allDistrictResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		allDistrictResultsDataSource.responseSchema = {
                fields: ["partyName", {key: "distName", parser:"number"},
                					  {key:  "inc", parser:"number"},
                					  {key:  "mahakutami", parser:"number"},
                					  {key:  "prpManaParty", parser:"number"},
                					  {key:  "aimim", parser:"number"},
                					  {key: "ind", parser:"number"},
                					  {key: "bjp", parser:"number"},
                					  {key: "lsp", parser:"number"}]
                					   
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10			        
			    }),
			    caption:"All Districts Results"
				};
		
		var allDistrictResultsDataTable = new YAHOO.widget.DataTable("districtResults", allDistrictResultsColumnDefs, allDistrictResultsDataSource,{caption:"All Districts Results"});
					
        return { 
            oDS: allDistrictResultsDataSource, 
            oDT: allDistrictResultsDataTable            
      };	
	
}


function buildAllianceDistrictResultsDataTable()
{	
	
	var allianceDistrictResultsColumnDefs = [
								{key: "partyName", label: "<%=party%>", sortable:true},		
								{key: "totalConstiParticipated", label: "TP*",formatter:"number", sortable:true},	
		              	 	    {key: "totalSeatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "secondPosWon", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "thirdPosWon", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourthPosWon", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nthPosWon", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "votesPercentage", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "completeVotesPercent", label:"Overall %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true}		              	 	 	
		              	 	    ];                	 	    

		var allianceDistrictResultsDataSource = new YAHOO.util.DataSource(); 
		allianceDistrictResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		allianceDistrictResultsDataSource.responseSchema = {
                fields: ["partyName", {key: "totalConstiParticipated", parser:"number"},
                					  {key:  "totalSeatsWon", parser:"number"},
                					  {key:  "secondPosWon", parser:"number"},
                					  {key:  "thirdPosWon", parser:"number"},
                					  {key:  "fourthPosWon", parser:"number"},
                					  {key: "nthPosWon", parser:"number"},
                					  {key: "votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
                					  {key: "completeVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber}] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10			        
			    })			    
				};
		
		var allianceDistrictResultsDataTable = new YAHOO.widget.DataTable("allianceDistResults", allianceDistrictResultsColumnDefs, allianceDistrictResultsDataSource,{caption:"Alliance Results"});
					
        return { 
            oDS: allianceDistrictResultsDataSource, 
            oDT: allianceDistrictResultsDataTable            
      };	
	
}
</SCRIPT>
<BODY>
<H5>${stateName} ${electionType} ${selectedElectionYear} Election Districtwise Results</H5>
<DIV class="yui-skin-sam">
	<TABLE border="0" width="95%" >
		<TR>
			<TD valign="top" align="left"><DIV id="districtResults"></DIV></TD>
			<TD valign="top"><DIV id="allianceDistResults"></DIV></TD>
		</TR>
		<TR>
			<TD colspan="2" align="left"><SPAN style="color:#006221;font-size:13px;font-weight:bold;">TP* =Total Participation, PC* %=Participated Constituencies Percentage </SPAN></TD>
		</TR>		
	</TABLE>
</DIV>
<SCRIPT type="text/javascript">
buildAllDistrictResultsDataTable();
buildAllianceDistrictResultsDataTable();
</SCRIPT>
</BODY>
</HTML>