function getPublicationDate()
	{
		var value=constituencyId;
		
		var jsObj=
		{	
			selected:value,
			task:"getPublicationDate"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;	

		$('#publicationAjaxImage').css('display','block');
		callAjax(jsObj,url);
	}
//Ended by sasi
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
								
								else if(jsObj.task == "getPublicationDate")
										buildSelectBoxes(myResults,jsObj);		
								
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

//Created by sasi for populating the selectbox for publication
function buildSelectBoxes(results,jsObj){
	debugger;
	var selectedElmt=document.getElementById("prespublicationDateList");
		removeSelectElements(selectedElmt);
		
		for(var val in results)
		{
		
			var opElmt = document.createElement('option');
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
		  if(results[val].id==toPublicationDateId){	
			$(opElmt).attr("selected","selected") ;		  
		  }
		}
		
		
		var selectedElmt2=document.getElementById("prevpublicationDateList");
		removeSelectElements(selectedElmt2);
		
		for(var val in results)
		{
		
			var opElmt2 = document.createElement('option');
			opElmt2.value=results[val].id;
			opElmt2.text=results[val].name;

			try
			{
				selectedElmt2.add(opElmt2,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt2.add(opElmt2); // IE only
			}
		  if(results[val].id==fromPublicationDateId){	
			$(opElmt).attr("selected","selected") ;		  
		  }
		}
}

function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
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
		 if(results[i].previousPublicationName != null)
		  str +='<td>'+results[i].previousPublicationName+'</td>';
		 else
		  str +='<td>N/A</td>';
		 if(results[i].publicationName != null)
			str +='<td>'+results[i].publicationName+'</td>';
		 else
			str +='<td>N/A</td>';
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