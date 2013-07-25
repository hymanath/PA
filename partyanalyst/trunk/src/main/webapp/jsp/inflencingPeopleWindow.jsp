<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<title>Influencing People Details</title>
<script>
function getRespectiveDetails()
{
	var level = $('#selLevelId option:selected').val();
	if(level == 2 || level == 3 || level == 4 || level == 7 || level == 5)
	{
		$('#successMsg').html('');
		$('#constituencyLable').hide();
		$('#constituencyList').hide();
		getInfluencingPeopleDetails();
	}
	else if( level == 6 || level == 9 || level == 8)
	{
		$('#successMsg').html('');
		$('#constituencyLable').show();
		$('#constituencyList').show();
		getConstituencysList()
	}
	else
	{
		$('#successMsg').html('<b style="color:red">Please select level</b>');
		$('#constituencyLable').hide();
		$('#constituencyList').hide();
	}
}

function getInfluencingPeopleDetails()
{
	var type = $('#selLevelId option:selected').text();
	var jsObj=
	{		
		type : type,	
		task : "getInfluencingPeople"				
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getInfluencingPeopleListAction.action?"+rparam;						
	callAjax(jsObj,url);
	  
}
function callAjax(jObj,url)
{
    var myResults;
	var callback = 
	{			
		success : function( o ) 
		{
			try 
			{												
				myResults = YAHOO.lang.JSON.parse(o.responseText);		
				if(jObj.task == "getInfluencingPeople")
				{
					showAllPeopleRecords(myResults);
				}
				
			}catch (e) 
			{
				
			}  
		},
		scope : this,
		failure : function( o )
		{
					//alert( "Failed to load result" + o.status + " " + o.statusText);
		}
    };

    YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

function getConstituencysList()
{
	var jsObj=
	{		
		task : "getConstituencys"				
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getUserConstituencesListAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showAllPeopleRecords(myResults)
{
	var elmt = document.getElementById("influencePeopleMainDataDiv");
	var str = '';
	str += '<table id="allPeopleDataTable" class="table">';	
	for(var i in myResults)
	{
	str += '<tr>';
	str += '<td><input type="checkbox"  id="checkBoxId"></input></td>';
	str += '<td>'+myResults[i].firstName+'</td>';
	str += '<td>'+myResults[i].lastName+'</td>';
	str += '<td>'+myResults[i].email+'</td>';
	str += '<td>'+myResults[i].mobileNo+'</td>';
	str += '<td>'+myResults[i].gender+'</td>';
	str += '<td>'+myResults[i].cast+'</td>';
	str += '<td>'+myResults[i].constituency+'</td>';
	str += '<td>'+myResults[i].district+'</td>';
	str += '<td>'+myResults[i].state+'</td>';
	str += '<td><a href="javascript:{}" onclick="getPersonDetails()">More Details</a></td>';
	str += '<td>';
	str += '<a href="javascript:{}" onclick="editPersonDetails()">';
	str += '<img style="text-decoration: none; border: 0px none;" src="images/icons/edit.png">';
	str += '</a>';
	str += '</td>';
	str += '<td>';
	str += '<a href="javascript:{}" onclick="deletePersonDetails()">';
	str += '<img style="text-decoration: none; border: 0px none;" src="images/icons/delete.png">';
	str += '</a>';
	str += '</td>';
	str += '</tr>';	
	}
							
	str += '</table>';

	elmt.innerHTML = str;

	buildYUIDTTable("allPeopleDataTable","influencePeopleMainDataDiv");
}
function buildYUIDTTable(tableId,divId)
		{
				var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get(tableId));
				resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
				resultsDataSource.responseSchema = {
					fields : [
					{
						key : ""
					},{
						key : "firstName"
					}, {
						key : "lastName"
					}, {
						key : "email"
					}, {
						key : "mobileNo",parser:"number"
					}, {
						key : "gender"
					}, {
						key : "cast"
					} , {
						key : "constituency"
					}, {
						key : "district"
					}, {
						key : "state"
					},{
						key : "moreDetails"
					},{
						key : "edit"
					},{
						key : "delete"
					} ]
				};

				var resultsColumnDefs = [{
					key : "",
					label : "All <input type='checkbox' name='regionHeaderCheckBox' onclick='selectAllPeopleInRegion()'></input>",
					sortable : false
				},
				{
					key : "firstName",
					label : "First Name",
					sortable : true
				}, {
					key : "lastName",
					label : "Second Name",
					sortable : true
				}, {
					key : "email",
					label : "Email",
					sortable : true
				}, {
					key : "mobileNo",
					parser:"number",
					label : "Mobile",
					sortable : true
				}, {
					key : "gender",
					label : "Gender",
					sortable : true
				}, {
					key : "cast",
					label : "Caste",
					sortable : true
				}, {
					key : "constituency",
					label : "Constituency",
					sortable : true
				} , {
					key : "district",
					label : "District / Parliament",
					sortable : true
				} , {
					key : "state",
					label : "State",
					sortable : true
				},{
					key : "moreDetails",
					label : "More Details",
					sortable : false
				} ,{
					key : "edit",
					label : "Edit",
					sortable : false
				},{
					key : "delete",
					label : "Delete",
					sortable : false
				}];

				var myConfigs = {
				paginator : new YAHOO.widget.Paginator({
					rowsPerPage: 50
				})
			};


			var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,myConfigs); 
		}



</script>
</head>
<body>
<div id="successMsg"></div>
<div id="selectionDiv" style="float: left; margin-bottom: 13px; margin-left: 10px; margin-top: 19px;">
<table>
<tr>
<td>
<span style="font-family: verdana; font-size: 15px; font-weight: bold;">Select Level : </span>
</td>
<td>
<select id="selLevelId" onchange="getRespectiveDetails();">
    <option value="0">Select Influence Scope</option>
    <option value="2">STATE</option>
    <option value="3">DISTRICT</option>
    <option value="4">CONSTITUENCY</option>
    <option value="5">MANDAL</option>
    <option value="6">VILLAGE</option>
    <option value="7">MUNICIPAL-CORP-GMC</option>
    <option value="8">WARD</option>
    <option value="9">BOOTH</option>
</select>
</td>
<td id="constituencyLable" style="display:none;">
<span style="font-family: verdana; font-size: 15px; font-weight: bold;">Select Constituency : </span>
</td>
<td id="constituencyList" style="display:none;">
<select id="constituencyField" onChange="getInfluencingPeopleDetails();">
<option value="0">Select Constituency</option>
</select>
</td>
</tr>
</table>
</div><br>
<div id="influencePeopleMainDataDiv" class="yui-skin-sam" style="width:28.28cm;"></div>
</body>
</html>