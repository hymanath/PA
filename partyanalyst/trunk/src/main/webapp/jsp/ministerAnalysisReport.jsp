<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ministers Analysis</title>
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

<style type="text/css">
.main-mbg {
    background-color: #06ABEA;
    border-radius: 7px 7px 7px 7px;
    color: #FFFFFF;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 977px;
}


</style>
<script type="text/javascript">
  function showHidsState()
    {
      if(document.getElementById("state").checked == true)
	      document.getElementById("stateListId").style.display ="block";
	  else
	      document.getElementById("stateListId").style.display ="none";
    }
 function callAjax(param,jsObj,url)
   {
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									if(jsObj.task == "getRegionWisePartyElectionResults")
									{
										
									}
									
								}
							catch (e) {   
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
 function buildGenderCountResultsDataTable(dtSourceArray)
   {	
  
	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var Party = oRecord.getData("partyName");
		var partyIds = oRecord.getData("partyId");
		if(oData !='IND' && partyIds!=null){
		elLiner.innerHTML =
		"<a href='partyPageAction.action?partyId="+partyIds+"' >"+Party+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}">'+Party+"</a>";
			
	};
	var partywiseResultsWithGenderColumnDefs = [
								{key: "partyName", label: "Party", sortable:true,
								formatter:YAHOO.widget.DataTable.partyLink},		
								{key: "totalParticipated", label: "TP*", sortable:true},	
		              	 	    {key: "totalSeatsWon", label: "Seat Won",formatter:"number", sortable:true},
		              	 	 	{key: "completeVotesPercent", label: "CV* %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
		              	 	 	{key: "PVotesPercent", label: "PV* %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
		              	 	 	{key: "malePerticipated", label: "Male Participants",formatter:"number", sortable:true},
		              	 	 	{key: "maleWon", label: "Male Won",formatter:"number", sortable:true}, 
								{key: "MVotesPercent", label: "Male Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},   	
		              	 	 	{key: "femalePerticipated", label:"Female Participants", formatter:"number",sortable: true},
		              	 	 	{key: "femaleWon", label:"Female Won",formatter:"number", sortable: true},
								{key: "FVotesPercent", label: "Female Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true}  	
		              	 	    ];                	 	    

		var partywiseResultsWithGenderDataSource = new YAHOO.util.DataSource(dtSourceArray); 
		partywiseResultsWithGenderDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		partywiseResultsWithGenderDataSource.responseSchema = {
                fields: ["partyName", {key: "totalParticipated", parser:"number"},
                         		  {key: "totalSeatsWon", parser:"number"},
                         		  {key: "completeVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key:  "PVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key:  "malePerticipated", parser:"number"},
                         		  {key: "maleWon", parser:"number"},
								  {key: "MVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "femalePerticipated", parser:"number"},
								  {key: "femaleWon", parser:"number"},
								  {key: "FVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
								{key: "partyId", parser:"number"}
									] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15			        
			    }),
			    caption:"Partywise Male And Female Candidates Participation And Results" 
				};
		
		partywiseResultsWithGenderDataTable = new YAHOO.widget.DataTable("showData", partywiseResultsWithGenderColumnDefs, partywiseResultsWithGenderDataSource,myConfigs);
					   	
 
}

</script>
</head>
<body>
   <div style="padding-left:5px;"><div class="main-mbg"></div></div>
   <div style="padding-top:10px;padding-left:350px;width:100%;text-align:center;">
     <table>  
	  <tr>
	      <td><input type="radio" name="selectScope" checked="true" onclick="showHidsState();" />&nbsp;&nbsp;<b>Country</b>&nbsp;&nbsp;</td>
	      <td><input type="radio" id="state" name="selectScope" onclick="showHidsState();" />&nbsp;&nbsp;<b>State</b>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	      <td><s:select theme="simple"  id="stateListId" list="statesList" listKey="id" listValue="name" style="display:none;" onchange=""/></td>
      </tr>
	 </table>
   </div>
   <div id="showData" />
</body>
</html>