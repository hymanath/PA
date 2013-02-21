<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voters analysis</title>
<style>
#voterModReportMainDiv{margin-left:auto;margin-right:auto;float:none;width:960px;margin-top: 30px; margin-bottom: 30px;}
#voterInfoTab, #voterAgeInfoTab,#voterGenderInfoTab{border: 1px solid #D3D3D3;
    border-collapse: collapse;
    padding: 10px;width:50%}

	#voterInfoTab th,#voterAgeInfoTab th,#voterGenderInfoTab th {
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}

#voterInfoTab td ,#voterAgeInfoTab td,#voterGenderInfoTab td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}
.voterinfoHeading h2{ color: #4682B4;
    font-family: arial;
    font-size: 15px;}
.voterInfoTable{border-collapse: collapse;
    border-color: #666666;
    border-width: 1px;
    color: #333333;
    font-family: arial,sans-serif;
    font-size: 11px; 
    min-width: 97%;
    text-align: center;}

	.voterInfoTable th {
    background-color: #DBEBFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
    padding: 8px;
}

.voterInfoTable td {
    background-color: #FFFFFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
    padding: 8px;
}

</style>

 <script type="text/javascript">
	var constituencyId = "${constituencyId}";
	var fromPublicationDateId = "${fromPublicationDateId}";
	var toPublicationDateId = "${toPublicationDateId}";
	var locationType = "${locationType}";
	var locationValue = "${locationValue}";
 </script>
 
</head>
<body>

<div id="voterModReportMainDiv">
  <div id="voterModReportInnerDiv">
		<div id="voterInfoDiv">
			<span id="voterInfoAjaxImg" style="display:none;float: right;clear:both;">
			   <img src="images/icons/search.gif" />
			</span>
		</div>
		<div id="voterAgeInfoDiv">
			<span id="voterAgeInfoAjaxImg" style="display:none;float: right;clear:both;">
			   <img src="images/icons/search.gif" />
			</span>
		</div>
		
		<div id="voterGenderInfoMainDiv">
			<img src="images/icons/search.gif" style="display:none;float: right;clear:both;" id="voterGenderInfoDivAjaxImg"/>
			<div id="voterGenderInfoDiv"></div>
		</div>

		<div id="genderWiseVoterModifiMainDiv">
			<img src="images/icons/search.gif" style="display:none;float: right;clear:both;" id="genderWiseVoterModifiAjaxImg"/>
			<div id="genderWiseVoterModifiDiv"></div>
		</div>

  </div>
</div>

<script type="text/javascript">

	function getVoterInfo(){
		
		$("#voterInfoAjaxImg").css("display","block");
	    var jObj=
		{
			constituencyId			: constituencyId,
			fromPublicationDateId	: fromPublicationDateId,
			toPublicationDateId		: toPublicationDateId,
			locationType			: locationType,
			locationValue			: locationValue,
			task:"getVoterInfo"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getVoterInfoAction.action?"+rparam;	

	callAjax(jObj,url);
	
	}

	function getAddedDeletedVoterInfoInALocation()
	{
		$("#voterAgeInfoAjaxImg").css("display","block");
		var jObj=
		{
			constituencyId			: constituencyId,
			fromPublicationDateId	: fromPublicationDateId,
			toPublicationDateId		: toPublicationDateId,
			locationType			: locationType,
			locationValue			: locationValue,
			task:"getAddedOrDeletedVoterInfoInALocation"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getAddedOrDeletedVoterInfoInALocationAction.action?"+rparam;	

	callAjax(jObj,url);
	}

	function getGenderWiseVoterModificationsBetweenPublications()
	{
		$('#voterGenderInfoDivAjaxImg').css('display','block');
		var jObj=
		{
			constituencyId			: constituencyId,
			fromPublicationDateId	: fromPublicationDateId,
			toPublicationDateId		: toPublicationDateId,
			locationType			: locationType,
			locationValue			: locationValue,
			task:"getNewlyAddedOrDeletedVoterInfo"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getGenderWiseVoterModifiBetweenPublicationsAction.action?"+rparam;	

	callAjax(jObj,url);
	}

	function getGenderWiseVoterModificationsForEachPublication()
	{
		$('#genderWiseVoterModifiAjaxImg').css('display','block');
		
		var jObj=
		{
			constituencyId			: constituencyId,
			fromPublicationDateId	: fromPublicationDateId,
			toPublicationDateId		: toPublicationDateId,
			locationType			: locationType,
			locationValue			: locationValue,
			task:"getGenderWiseVoterModifiForEachPublic"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getGenderWiseVoterModifiForEachPublicAction.action?"+rparam;	

	callAjax(jObj,url);
	}
	
	function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "getVoterInfo")
								
									showVoterInfo(myResults,jsObj);
										
								
								else if(jsObj.task == "getAddedOrDeletedVoterInfoInALocation")
										showAddedDeletedVoterInfoInALocation(myResults,jsObj);

								else if(jsObj.task == "getNewlyAddedOrDeletedVoterInfo")
										showGenderWiseVoterModifiBetweenPublications(myResults,jsObj);

								else if(jsObj.task == "getGenderWiseVoterModifiForEachPublic")
										showGenderWiseVoterModifiForEachPublic(myResults,jsObj);
								
								
							}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}


function showVoterInfo(results,jsObj)
{

	$("#voterInfoAjaxImg").css("display","none");
	$('#voterInfoDiv').html('');

	var str = '';
	str +='<div class="voterinfoHeading"><h2>Voters Basic Information</h2></div>';
	
	if(results != null)
	{
		
		str +='<table id="voterInfoTab">';
		str +='<tr>';
		str +='<th>Publication Date</th>';
		str +='<th>Total Voters</th>';
		str +='<th>Male Voters</th>';
		str +='<th>Female Voters</th>';
		str +='</tr>';
		for(var i in results)
		{
			str +='<tr>';
			str +='<td>'+results[i].publicationDate+'</td>';
			str +='<td>'+results[i].totalVoters+'</td>';
			str +='<td>'+results[i].maleVoters+'</td>';
			str +='<td>'+results[i].femaleVoters+'</td>';
			str +='</tr>';
		}
		str +='</table>';
		$('#voterInfoDiv').html(str);
	}

	else 
	{
	  $('#voterInfoDiv').html('<div>No Data Available.</div>');
	  return;
	}
}

function showAddedDeletedVoterInfoInALocation(results,jsObj)
{
	$("#voterAgeInfoAjaxImg").css("display","none");
	$('#voterAgeInfoDiv').html('');

	var str = '';
	str +='<div class="voterinfoHeading"><h2>Age Wise Newly Added/Deleted Info From '+jsObj.fromPublicationDateId+' to '+jsObj.toPublicationDateId+'</h2></div>';
	
	if(results != null)
	{
		
		str +='<table id="voterAgeInfoTab" border="1">';
		str +='<tr>';
		str +='<th>Age Range</th>';
		for(var i in results)
		  str +='<td>'+results[i].range+'</td>';
		str +='</tr>';
		str +='<tr>';
		str +='<th>Added</th>';
		for(var i in results)
		 str +='<td>'+results[i].addedCount+'</td>';
		str +='</tr>';
		str +='<tr>';
		str +='<th>Deleted</th>';
		for(var i in results)
		 str +='<td>'+results[i].deletedCount+'</td>';
		str +='</tr>';
		
		str +='</table>';
		$('#voterAgeInfoDiv').html(str);
	}

	else 
	{
	  $('#voterAgeInfoDiv').html('<div>No Data Available.</div>');
	  return;
	}
}

function showGenderWiseVoterModifiBetweenPublications(results,jsObj)
{
	
	$('#voterGenderInfoDiv').html('');
	$('#voterGenderInfoDivAjaxImg').css('display','none');
	var str = '';
	str +='<div class="voterinfoHeading"><h2>Newly Added/Deleted Info From '+jsObj.fromPublicationDateId+' to '+jsObj.toPublicationDateId+'</h2></div>';
	
	if(results != null)
	{
		str +='<table id="voterGenderInfoTab" border="1">';
		str +='<tr>';
		str +='<th>Status</th>';
		str +='<th>Total</th>';
		str +='<th>Male</th>';
		str +='<th>Female</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<th>Added</th>';
		str +='<td>'+results.addedTotal+'</td>';
		str +='<td>'+results.addedMale+'</td>';
		str +='<td>'+results.addedFemale+'</td>';
		str +='</tr>';
		str +='<tr>';
		str +='<th>Deleted</th>';
		str +='<td>'+results.deletedTotal+'</td>';
		str +='<td>'+results.deletedMale+'</td>';
		str +='<td>'+results.deletedFemale+'</td>';
		str +='</tr>';
		str +='</table>';
		$('#voterGenderInfoDiv').html(str);
	}

	else 
	{
	  $('#voterGenderInfoDiv').html('<div>No Data Available.</div>');
	  return;
	}
}

function showGenderWiseVoterModifiForEachPublic(results,jsObj)
{
	$('#genderWiseVoterModifiDiv').html('');
	$('#genderWiseVoterModifiAjaxImg').css('display','none');
	var str = '';

	str +='<div class="voterinfoHeading"><h2>Gender Wise Voter Modifications For Each Publication</h2></div>';

	if(results != null)
	{
		str +='<table class="voterInfoTable">';
		str +='<tr>';
		str +='<th rowspan="2">Publication Name</th>';
		str +='<th rowspan="2">Previous Publication Name</th>';
		str +='<th COLSPAN="3">Added</th>';
		str +='<th COLSPAN="3">Deleted</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<th>Total</th>';
		str +='<th>Male</th>';
		str +='<th>Female</th>';
		str +='<th>Total</th>';
		str +='<th>Male</th>';
		str +='<th>Female</th>';
		str +='</tr>';
		for(var i in results)
		{
		 str +='<tr>';
		 str +='<td>'+results[i].previousPublicationName+'</td>';
		 str +='<td>'+results[i].publicationName+'</td>';
		 str +='<td>'+results[i].addedTotal+'</td>';
		 str +='<td>'+results[i].addedMale+'</td>';
		 str +='<td>'+results[i].addedFemale+'</td>';
		 str +='<td>'+results[i].deletedMale+'</td>';
		 str +='<td>'+results[i].deletedMale+'</td>';
		 str +='<td>'+results[i].deletedFemale+'</td>';
		 str +='</tr>';

		}
		str +='</table>';
		$("#genderWiseVoterModifiDiv").html(str);
	}
	else 
	{
	  $('#genderWiseVoterModifiDiv').html('<div>No Data Available.</div>');
	  return;
	}
	
}
getVoterInfo();
getAddedDeletedVoterInfoInALocation();
getGenderWiseVoterModificationsBetweenPublications();
getGenderWiseVoterModificationsForEachPublication();

</script>
</body>
</html>