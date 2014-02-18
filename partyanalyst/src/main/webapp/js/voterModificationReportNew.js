var scopeId = 0; //selected scopeId

$(document).ready(function() {
        $('#example').dataTable();
		

		$('#scopeSelectId').change(function(){

			scopeId = $(this).val();

			   if(scopeId == "1")
			   {
				 $('#mandalDiv').show('slow');
				 $('#panchayatDiv ,#boothDiv').hide('slow');			 
					 
			   }
			   else if(scopeId == "2")
			   {
				   $('#mandalDiv, #panchayatDiv ').show('slow');
				   $('#boothDiv').hide('slow');
			   }
			   else if(scopeId == "3")
			   {
				   $('#mandalDiv, #panchayatDiv ,#boothDiv').show('slow');
			   }
			   else if(scopeId == "0")
			   {
				   $('#mandalDiv, #panchayatDiv ,#boothDiv').hide('slow');
			   }

			   if(scopeId != "0")
				   callAjaxToGetTehsilAndMuncipalities();
	  });

	  $('#mandalSelectId').change(function(){

		if(scopeId == "2" || scopeId == "3")
		{
			var temp = $('#mandalSelectId').val();

		   if(temp.substring(0,1) == "1")
			   callAjaxToGetWardsOrPanchayatis("Wards");
		   else if(temp.substring(0,1) == "2")
			   callAjaxToGetWardsOrPanchayatis("Panchayatis");
		}
	 });

	 $('#panchayatSelectId').change(function(){

		if(scopeId == "2" || scopeId == "3")
		{
			var temp = $('#mandalSelectId').val();

		   if(temp.substring(0,1) == "1")
			  callAjaxToGetBoothsInPanchayatOrWard("Wards");
		   else if(temp.substring(0,1) == "2")
			  callAjaxToGetBoothsInPanchayatOrWard("Panchayat");


		}
	});

	$('#scopeSelectId').change(function(){

	   $('#mandalSelectId, #panchayatSelectId , #boothSelectId').find('option').remove();
	   $('#mandalSelectId , #panchayatSelectId , #boothSelectId').append('<option value="0">SELECT</option>');

	});

} );

getAddedOrDeletedVoterDetails();
function getAddedOrDeletedVoterDetails()
{
	 var jObj=
		{
			constituencyId : 232,
			publicationId  : 8,
			task:"getAddedOrDeletedVoterDetails"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getAddedOrDeletedVotersListNewAction.action?"+rparam;	

	callAjax(jObj,url);
}

function getPublicationDate()
	{
			var value=constituencyId;
		if(value == null || value == '')
			return;

		$('#voterAgeInfoAjaxImg').css('display','block');
		
		var jsObj=
		{	
			selected:value,
			task:"getPublicationDate"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;	

		
		callAjax(jsObj,url);
	}

	function getVoterInfo(){
		
		if(constituencyId == null || constituencyId == '' || fromPublicationDateId == null || fromPublicationDateId == ''
		|| toPublicationDateId == null || toPublicationDateId == '' || locationType == '' || locationType == null || 
			locationValue == null || locationValue == '')
			return;
		
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
	var url = "getVoterInfoNewAction.action?"+rparam;	

	callAjax(jObj,url);
	
	}

	function getAddedDeletedVoterInfoInALocation()
	{
		var locationVal = locationValue;

		if(constituencyId == null || constituencyId == '' || fromPublicationDateId == ''
		|| toPublicationDateId == null || toPublicationDateId == '' || locationType == '' || locationType == null || 
			locationValue == null || locationValue == '')
			return;
		/* if(locationType == 'localElectionBody')
			locationVal = localElectionBodyId;*/

		$("#voterAgeInfoAjaxImg").css("display","block");
		var jObj=
		{
			constituencyId			: constituencyId,
			fromPublicationDateId	: fromPublicationDateId,
			toPublicationDateId		: toPublicationDateId,
			locationType			: locationType,
			locationValue			: locationVal,
			task:"getAddedOrDeletedVoterInfoInALocation"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getAddedOrDeletedVoterInfoInALocationNewAction.action?"+rparam;	

	callAjax(jObj,url);
	}

	function getGenderWiseVoterModificationsBetweenPublications()
	{
		var locationVal = locationValue;
		if(constituencyId == null || constituencyId == '' || fromPublicationDateId == ''
		|| toPublicationDateId == null || toPublicationDateId == '' || locationType == '' || locationType == null || 
			locationValue == null || locationValue == '')
			return;
		/* if(locationType == 'localElectionBody')
			locationVal = localElectionBodyId;*/

		$('#voterGenderInfoDivAjaxImg').css('display','block');
		var jObj=
		{
			constituencyId			: constituencyId,
			fromPublicationDateId	: fromPublicationDateId,
			toPublicationDateId		: toPublicationDateId,
			locationType			: locationType,
			locationValue			: locationVal,
			task:"getNewlyAddedOrDeletedVoterInfo"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getGenderWiseVoterModifiBetweenPublicationsNewAction.action?"+rparam;	

	callAjax(jObj,url);
	}

	function getGenderWiseVoterModificationsForEachPublication()
	{
		var locationVal = locationValue;

		if(constituencyId == null || constituencyId == '' || fromPublicationDateId == ''
		|| toPublicationDateId == null || toPublicationDateId == '' || locationType == '' || locationType == null || 
			locationValue == null || locationValue == '')
			return;

		$('#genderWiseVoterModifiAjaxImg').css('display','block');
		
		/* if(locationType == 'localElectionBody')
			locationVal = localElectionBodyId;*/

		var jObj=
		{
			constituencyId			: constituencyId,
			fromPublicationDateId	: fromPublicationDateId,
			toPublicationDateId		: toPublicationDateId,
			locationType			: locationType,
			locationValue			: locationVal,
			task:"getGenderWiseVoterModifiForEachPublic"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getGenderWiseVoterModifiForEachPublicNewAction.action?"+rparam;	

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
								else if(jsObj.task == "getAllVoterInformationInALocation")
								{
									$('#allvotersDetails').css('display','none');
									showAllVoterInformationInALocation(myResults);
									
								}
								else if(jsObj.task == "subRegionsInConstituency")
									buildMandalOrMuncipalities(myResults);
								else if(jsObj.task == "hamletsOrWardsInRegion" || jsObj.task ==  "panchayitiesInAMandal")
									buildWardsOrPanchaytis(myResults);
								else if(jsObj.task == "boothsInWard" || jsObj.task == "getBoothsInPanchayat")
									buildBooths(myResults);
								else if(jsObj.task == "getSubLevelInformation")
								{
								$('#subLevelAjaxImageDiv').css('display','none');
								buildSubLevelInformation(jsObj,myResults);
								
								}

								else if(jsObj.task == "getVotersInAFamilyByConstituencyId")
								{
									setTimeout($.unblockUI, 200);
									buildFamilyInfo(myResults,jsObj.partNo);
								}
								else if(jsObj.task == "getGenderBasedVoterDetails")
								{
									setTimeout($.unblockUI, 200);
									buildGenderOrAddedVotersTable(myResults,jsObj);
								}
								else if(jsObj.task == "createPdfs")
								{
									$.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });
									if(myResults.name == 'success')
									{
										$.unblockUI();
										buildPdf(myResults);
									}
									else
									{
										$.unblockUI();
										alert("Error occured while creating the pdf");
									}
									
								}
									
									
								
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
//
function buildGenderOrAddedVotersTable(myResults,jsObj){
var title='';
$('#GenderOrAddedVotersTable').html('');
var str='';
var booth='';
str+='<table id="dt"><thead><tr>';

if(jsObj.locationType!='booth'){
	str+='<th>BoothNo</th>';}

str+='<th>Name</th><th>Age</th>';


if(jsObj.forGender=='true'){
	if(jsObj.gender=='TOTAL'){
		str+='<th>Gender</th>';
	}
}
else
	str+='<th>Gender</th>';

//str+='<th>Guardian</th><th>RelationShip</th></tr></thead><tbody>';
str+='<th>House No</th><th>Village</th></tr></thead><tbody>';
for(var i in myResults){
booth=myResults[0].boothNo; 

str+='<tr id="checkForEmpty">';
if(jsObj.locationType!='booth'){

str+='<td>'+myResults[i].boothNo+'</td>';}

str+='<td>'+myResults[i].firstName+'</td><td>'+myResults[i].age+'</td>';

if(jsObj.forGender=='true'){
	if(jsObj.gender=='TOTAL'){
		str+='<td class="center">'+myResults[i].gender+'</td>';
	}
}
else
	str+='<td class="center">'+myResults[i].gender+'</td>';


//str+='<td>'+myResults[i].relativeFirstName+'</td><td>'+myResults[i].relationshipType+'</td></tr>';
str+='<td><a style="cursor:pointer;color:#3392C2;" onclick="getVotersInAFamilyByPublication('+myResults[i].boothNo+','+fromPublicationDateId+','+toPublicationDateId+','+constituencyId+',\''+myResults[i].houseNo+'\')">'+myResults[i].houseNo+'</a></td><td>'+myResults[i].villagesCovered+'</td></tr>';
}
str+='</tbody></table>';
$('#GenderOrAddedVotersTable').html(str);

if(jsObj.forGender=='false'){
	var range='';
	switch (jsObj.ageRangeId) {
	    case 1: range="Young Voters";break;
		case 2: range="18-25";break;
		case 3: range="26-35";break;
		case 4: range="36-45";break;
		case 5: range="46-60";break;
		case 6: range="60-Above";break;
	}
	
	title= range+" Age "+jsObj.status+" Voters Details";
}

else{
	if(jsObj.gender=='TOTAL')
		gend='Total';
		
	else
		gend=(jsObj.gender=='M')?"Male":"Female";
	
	title= gend+" Voters "+jsObj.status+" Details";
	
	if(jsObj.locationType=='booth'){
		title=gend+" Voters "+jsObj.status+" Details in Booth - "+booth;
	}
}


$('#GenderOrAddedVotersPopup').dialog({ 
	title:title,
	height: 'auto',
	width: 950,
	show: "blind",
	modal: true,
	overlay: { opacity: 0.5, background: 'black'},
	buttons: {
	   "Close":function() {$(this).dialog("close")}
	}	
});

$('#dt').dataTable();
}
//
//Created by sasi for populating the selectbox for publication
function buildSelectBoxes(results,jsObj){
	$("#voterAgeInfoAjaxImg").css("display","none");
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

		var selectedElmt3=document.getElementById("fromPublicationDateId");
		removeSelectElements(selectedElmt3);
		
		for(var val in results)
		{

		
			var opElmt3 = document.createElement('option');
			opElmt3.value=results[val].id;
			opElmt3.text=results[val].name;

			try
			{
				selectedElmt3.add(opElmt3,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt3.add(opElmt3); // IE only
			}
		  if(results[val].id==fromPublicationDateId){	
			$(opElmt).attr("selected","selected") ;		  
		  }
		}

		var selectedElmt4=document.getElementById("toPublicationDateId");
		removeSelectElements(selectedElmt4);
		
		for(var val in results)
		{

		
			var opElmt4 = document.createElement('option');
			opElmt4.value=results[val].id;
			opElmt4.text=results[val].name;

			try
			{
				selectedElmt4.add(opElmt4,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt4.add(opElmt4); // IE only
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
	str +='<div class="voterinfoHeading"><h2 style="height: 35px;padding-bottom: 12px;">Voters Basic Information In '+locationName+' '+locationTypeVar+'';
	str +='<button  id="helpbutt" value="Help" style="float: right;width: 30px;" onClick="PopUpForHelp()" title="Click To View Help"><img alt="" src="./images/help.jpeg"></button>';
	
	if(jsObj.locationType == 'panchayat' || jsObj.locationType == 'booth')
		str +='<div style="float: right;margin-right: 10px;"><input type="button" style="" id="boothWiseCompleteInfo" href="javascript:{}" value="Booth Wise Relocated Info" class="btn btn-info"></div>';
	else 
	{
		
		str +='<input type="button" style="float: right;" id="boothWiseVoterModificationInfo" href="javascript:{}" value="Booth Wise Voter Details" class="btn btn-info">';
		
		if(jsObj.locationType == 'constituency')
		{
			str += '<div id="pdfDiv">';
			str +='<input type="button" style="float: right; margin-top: -20px; margin-right: 10px;" id="pdfGeneraion" href="javascript:{}" onClick = "genereatePdf();" value="Generate Pdf" class="btn btn-info">';
			str += '</div>';
			
		}
	}
		
	str +='</h2>';
	str +='</div>';	
	if(results != null)
	{
		
		str +='<table id="voterInfoTab" class="table table-bordered table-hover">';
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
	  $('#voterInfoDiv').html('<div class="voterinfoHeading"><h2>Voters Basic Information</h2></div>');
	  $('#voterInfoDiv').append('<div>No Data Available.</div>');
	  return;
	}
}

function showAddedDeletedVoterInfoInALocation(results,jsObj)
{
	$("#voterAgeInfoAjaxImg").css("display","none");
	$('#voterAgeInfoDiv').html('');

	var str = '';
	str +='<div class="voterinfoHeading"><h2>Age Wise Newly Added / Deleted Info ';
	/*if((fromPublicationName != '' || fromPublicationName != null) && (toPublicationName !='' || toPublicationName != null))
		str +='From '+fromPublicationName+' to '+toPublicationName+'';
	else if(fromPublicationName != '' || fromPublicationName != null)
		str +=': '+fromPublicationName+'';
	else if(toPublicationName != '' || toPublicationName != null)
		str +=': '+toPublicationName+'';*/
	str +='</h2></div>';
	
	
	if(results != null)
	{
		if(jsObj.locationType=='constituency'){
		str +='<table id="voterAgeInfoTab" border="1" class="table table-bordered table-hover">';
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
		else{
		str +='<table id="voterAgeInfoTab" border="1" class="table table-bordered table-hover">';
		str +='<tr>';
		str +='<th>Age Range</th>';
		 var range ="";
		for(var i in results){
		  str +='<td>'+results[i].range+'</td>';
		 range = results[i].range;
		}
		 
		str +='</tr>';
		str +='<tr>';
		str +='<th>Added</th>';
		;
		for(var i in results){
		
		if(results[i].addedCount!=0)
		 str +='<td><a href="javaScript:{getVoterDetailsByAge(\''+results[i].range+'\',\'1\',\'Added\');}">'+results[i].addedCount+'</a></td>';
		 
		else
		 str +='<td>'+results[i].addedCount+'</td>';
		 
		}

		str +='</tr>';
		str +='<tr>';
		str +='<th>Deleted</th>';
		for(var i in results)
		{
		if(results[i].deletedCount!=0)
		 str +='<td><a href="javaScript:{getVoterDetailsByAge(\''+results[i].range+'\',\'2\',\'Deleted\');}">'+results[i].deletedCount+'</a></td>';
		
		else
		 str +='<td>'+results[i].deletedCount+'</td>';
		}
		str +='</tr>';
		
		if(jsObj.locationType == "booth")
		{
			str +='<tr><th>Moved</th>';
			for(var i in results)
			{
			  if(results[i].movedCount != 0)
			    str +='<td><a href="javaScript:{getVoterDetailsByAge(\''+results[i].range+'\',\'3\',\'Moved\');}">'+results[i].movedCount+'</a></td>';
			  else
				str +='<td>'+results[i].movedCount+'</td>';
			}
			str +='</tr><tr><th>Relocated</th>';
			for(var i in results)
			{
			  if(results[i].relocatedCount != 0)
			    str +='<td><a href="javaScript:{getVoterDetailsByAge(\''+results[i].range+'\',\'4\',\'Relocated\');}">'+results[i].relocatedCount+'</a></td>';
			  else
				str +='<td>'+results[i].relocatedCount+'</td>';
			}
			str +='</tr>';

		}


		str +='</table>';
		$('#voterAgeInfoDiv').html(str);
		
		}
	}
	else 
	{
	  $('#voterAgeInfoDiv').html('<div class="voterinfoHeading"><h2>Age Wise Newly Added / Deleted Info </h2></div>');
	  $('#voterAgeInfoDiv').append('<div>No Data Available.</div>');
	  return;
	}
}

function showGenderWiseVoterModifiBetweenPublications(results,jsObj)
{
	
	$('#voterGenderInfoDiv').html('');
	$('#voterGenderInfoDivAjaxImg').css('display','none');
	var str = '';
	str +='<div class="voterinfoHeading"><h2>Newly Added / Deleted Info </h2>';
	
	/* if((fromPublicationName != '' || fromPublicationName != null) && (toPublicationName !='' || toPublicationName != null))
		str +='From '+fromPublicationName+' to '+toPublicationName+'';
	else if(fromPublicationName != '' || fromPublicationName != null)
		str +=': '+fromPublicationName+'';
	else if(toPublicationName != '' || toPublicationName != null)
		str +=': '+toPublicationName+''; */
	
	if(results != null)
	{
		str +='<table id="voterGenderInfoTab" border="1" class="table table-bordered table-hover">';
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
		
		if(jsObj.locationType == "booth")
		{
			str +='<tr>';
			str +='<th>Moved</th>';
			str +='<td>'+results.movedTotal+'</td>';
			str +='<td>'+results.movedMale+'</td>';
			str +='<td>'+results.movedFemale+'</td>';
			str +='</tr>';
			str +='<tr>';
			str +='<th>Relocated</th>';
			str +='<td>'+results.relocatedTotal+'</td>';
			str +='<td>'+results.relocatedMale+'</td>';
			str +='<td>'+results.relocatedFemale+'</td>';
			str +='</tr>';
		}

		str +='</table>';
		$('#voterGenderInfoDiv').html(str);
	}

	else 
	{
	  $('#voterGenderInfoDiv').html('<div class="voterinfoHeading"><h2>Newly Added / Deleted Info </h2></div>');
	  $('#voterGenderInfoDiv').append('<div>No Data Available.</div>');
	  return;
	}
}

function showGenderWiseVoterModifiForEachPublic(results,jsObj)
{
	
	$('#genderWiseVoterModifiDiv').html('');
	$('#genderWiseVoterModifiAjaxImg').css('display','none');
	var str = '';

	str +='<div class="voterinfoHeading"><h2>Gender Wise Voter Modifications </h2></div>';

	if(results != null)
	{
		str +='<table class="voterInfoTable" class="table table-bordered table-hover">';
		str +='<tr>';
		str +='<th rowspan="2">Publication Name</th>';
		str +='<th rowspan="2">Previous Publication Name</th>';
		str +='<th COLSPAN="3">Added</th>';
		str +='<th COLSPAN="3">Deleted</th>';
		if(jsObj.locationType == "booth")
		{
		   str +='<th COLSPAN="3">Moved</th>';
		   str +='<th COLSPAN="3">Relocated</th>';
		}
		str +='</tr>';
		str +='<tr>';
		str +='<th>Total</th>';
		str +='<th>Male</th>';
		str +='<th>Female</th>';
		str +='<th>Total</th>';
		str +='<th>Male</th>';
		str +='<th>Female</th>';

		if(jsObj.locationType == "booth")
		{
		   str +='<th>Total</th>';
		   str +='<th>Male</th>';
		   str +='<th>Female</th>';
		   str +='<th>Total</th>';
		   str +='<th>Male</th>';
		   str +='<th>Female</th>';
		}
		str +='</tr>';
		for(var i in results)
		{
		 str +='<tr>';
		 if(results[i].publicationName != null)
			str +='<td>'+results[i].publicationName+'</td>';
		 else
			str +='<td>N/A</td>';

		 if(results[i].previousPublicationName != null)
		  str +='<td>'+results[i].previousPublicationName+'</td>';
		 else
		  str +='<td>N/A</td>';
 		 str +='<td>'+results[i].addedTotal+'</td>';
		 str +='<td>'+results[i].addedMale+'</td>';
		 str +='<td>'+results[i].addedFemale+'</td>';
		 str +='<td>'+results[i].deletedTotal+'</td>';
		 str +='<td>'+results[i].deletedMale+'</td>';
		 str +='<td>'+results[i].deletedFemale+'</td>';
		 if(jsObj.locationType == "booth")
		{
		  str +='<td>'+results[i].movedTotal+'</td>';
		  str +='<td>'+results[i].movedMale+'</td>';
		  str +='<td>'+results[i].movedFemale+'</td>';
		  str +='<td>'+results[i].relocatedTotal+'</td>';
		  str +='<td>'+results[i].relocatedMale+'</td>';
		  str +='<td>'+results[i].relocatedFemale+'</td>';
		}
		 str +='</tr>';

		}
		str +='</table>';
		$("#genderWiseVoterModifiDiv").html(str);
	}
	else 
	{
	  $('#genderWiseVoterModifiDiv').html('<div class="voterinfoHeading"><h2>Gender Wise Voter Modifications </h2></div>');
	  $('#genderWiseVoterModifiDiv').append('<div>No Data Available.</div>');
	  return;
	}
	
}




function callAjaxToGetTehsilAndMuncipalities()
{

	  var jObj=
		{
			    id:constituencyId,
				task:"subRegionsInConstituency",
				areaType:""
	    };
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getLocationsById.action?"+rparam;	

	callAjax(jObj,url);

}

function buildMandalOrMuncipalities(results)
{


	$('#mandalSelectId').find('option').remove();
	$('#mandalSelectId').append('<option value="0">SELECT</option>');


	for(var i in results)	
	 $('#mandalSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
	

}


function buildWardsOrPanchaytis(results)
{
	$('#panchayatSelectId').find('option').remove();
	$('#panchayatSelectId').append('<option value="0">SELECT</option>');


	for(var i in results)	
	 $('#panchayatSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}


function callAjaxToGetWardsOrPanchayatis(type){
	var task;

	if(type == "Wards")
	 task = "hamletsOrWardsInRegion";
	else if(type == "Panchayatis")
	 task = "panchayitiesInAMandal";
	    var jObj=
		{
			id:$('#mandalSelectId').val(),
			task:task
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getLocationsById.action?"+rparam;	

	callAjax(jObj,url);
}

function callAjaxToGetBoothsInPanchayatOrWard(type)
{
	var task;

	if(type == "Wards")
	 task = "boothsInWard";
	else if(type == "Panchayat")
	 task = "getBoothsInPanchayat";
	    var jObj=
		{
			id:$('#panchayatSelectId').val(),
            constId:constituencyId,
			publicationId:7,
			task:task
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getLocationsById.action?"+rparam;	

	callAjax(jObj,url);

}


function buildBooths(results)
{
  $('#boothSelectId').find('option').remove();
	$('#boothSelectId').append('<option value="0">SELECT</option>');


	for(var i in results)	
	 $('#boothSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
}

function getAllVotersModificationDetailsBetweenPublications(type,loadingstatus)
{
	

	   var status = "";

		if(type == "default")
	    {
			status = "all";
		getAllVotersModificationDetailsBetweenPublications1(status,loadingstatus);

	    }
		else if(type == "search")
		{
           $('#topubErrMsg').html('');
			status = $('input:radio[name=status]:checked').val();

			if($('#toPublicationDateId').val() == "0"){
			  $('#topubErrMsg').html('To publication date is required');
			}else{
				getAllVotersModificationDetailsBetweenPublications1(status,loadingstatus);
			}
		}

}

var titleString = "";
function getAllVotersModificationDetailsBetweenPublications1(status,loadingstatus)
	{
		
		if(locationType == 'constituency' || locationType =='mandal' || locationType =='localElectionBody')
		{
			$('#mainDiv').css('display','none');
			return;
		}
			
		$('#mainDiv').css('display','inline-block');
		$('#allVoterDetailsForALocation').html('');
		
   var locationScope = "";
   var locationValue1 = 0;
   var frmPblCtnId = 0;
   var toPblCtnId = 0;
    titleString = "";

   if(loadingstatus == "onload"){
	   locationScope = locationType;
	   locationValue1 = locationValue;
	   frmPblCtnId =  fromPublicationDateId;
	   toPblCtnId = toPublicationDateId;

   }else{

	   frmPblCtnId =  $('#fromPublicationDateId').val();
	   toPblCtnId = $('#toPublicationDateId').val();

   }


    if(scopeId == "1")
	{
		locationValue1 = $('#mandalSelectId').val();
		if(locationValue1.substring(0,1) == "1")
		{
			locationScope = "localElectionBody";
			titleString += $('#mandalSelectId :selected').text();
		}
		else
		{
			locationScope = "mandal";
			titleString += $('#mandalSelectId :selected').text();
		}
		locationValue1 = $('#mandalSelectId').val().substring(1);
	}
	else if(scopeId == "2")
    {
      locationValue1 = $('#panchayatSelectId').val();

	  if(locationValue1.substring(0,1) == "1")
		{
			locationScope = "ward";
			titleString += $('#panchayatSelectId :selected').text()+" WARD";
		}
		else
		{
			locationScope = "panchayat";
			titleString += $('#panchayatSelectId :selected').text()+" PANCHAYAT";

		}

		locationValue1 = $('#panchayatSelectId').val().substring(1);
	}
	else if(scopeId == "3")
	{
		locationValue1 = $('#boothSelectId').val();
		locationScope = "booth";
		titleString += $('#boothSelectId :selected').text();


	}
	$('#allvotersDetails').css('display','block');
		
		/* if(locationType == 'localElectionBody')
			locationValue1 = localElectionBodyId;*/

		var jObj=
		{
			constituencyId			: constituencyId,
			fromPublicationDateId	: 7,
			toPublicationDateId		: 8,
			locationType			: locationScope,
			locationValue			: locationValue1,
			status					: status,
			task:"getAllVoterInformationInALocation"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getAllVoterInformationInALocation.action?"+rparam;	

	callAjax(jObj,url);
}


function showAllVoterInformationInALocation(results)
{
	
	$('#titleDiv').html(titleString);
	var str='';
	str+='<div  class="whitegloss" style="margin:5px;"></div>';
	
	str+='<div>';
	str+='<table id="voterDetails" class="gridtable1">';
	str+='<thead>';
	str+='<tr>';
	str+='<th>NAME</th>';
	str+='<th>GENDER</th>';
	str+='<th>AGE</th>';
	str+='<th>House No</th>';
	str+='<th>STATUS</th>';
	str+='<th>BOOTH</th>';
	str+=' <th>Places Covered</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';

	for(var i in results)
	{
		str+='<tr>';
		str+='<td>'+results[i].firstName+'</td>';
		str+='<td style="text-align:center;">'+results[i].gender+'</td>';
		str+='<td style="text-align:center;">'+results[i].age+'</td>';
		str+='<td style="text-align:center;"><a style="cursor:pointer;color:#63AABF"  title="Click Here to know All the Family Members In this House" onclick="getVotersInAFamilyByPublication('+results[i].boothNo+','+fromPublicationDateId+','+toPublicationDateId+','+constituencyId+',\''+results[i].houseNo+'\')">'+results[i].houseNo+'</a></td>';
		str+='<td style="text-align:center;">'+results[i].status+'</td>';
		str+='<td>'+results[i].boothName+'</td>';
		str+='<td>'+results[i].panchayatName+'</td>';
	 	str+='</tr>';
	}
  
	str+='</tbody>';
	str+='</table>';
	str+='</div>';

	$('#allVoterDetailsForALocation').html(str);

	$('#voterDetails').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength":50,
		"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null
		] 
		});
}


function buildSubLevelInformation(jsObj,myResults)
{
var subLevelName = "";
var colSpan = 2;
if(jsObj.locationType == "constituency")
    subLevelName = "Mandal/Muncipality";
else if(jsObj.locationType == "mandal")
    subLevelName = "Panchayat";
else if(jsObj.locationType == "panchayat" || jsObj.locationType == "ward")
    subLevelName = "Booth";
else if(jsObj.locationType == "localbody" ||jsObj.locationType == "Local Election Body" || jsObj.locationType == "localElectionBody")
    subLevelName = "Ward";

if(myResults.modifiedVotersList == null || myResults.modifiedVotersList.length == 0)
{
	$('#subLevelsMainDiv').css('display','none');
	return;
}

	$('#subLevelsMainDiv').css('display','inline-block');
	$('#subLevelDiv').html('');
if(myResults.modifiedVotersList.length == 0 && myResults.modifiedLocalBodyVotersList.length == 0 && myResults.modifiedVotersList != null && myResults.modifiedLocalBodyVotersList != null)
	return false;


if(jsObj.locationType == "panchayat" || jsObj.locationType == "ward")
	colSpan = 4;

var str='';

	str +='<h4>'+subLevelName+' Wise Newly added / deleted voters info</h4>';
	 str+='<table class="voterInfoTable" id="subLevelVotersTable">';
	 str+='<thead>';
	 str +='<tr>';
	 
	 if(myResults.modifiedVotersList[0].locationType == 'booth')
		str +='<th rowSpan="2">'+subLevelName+' Part No</th>';
	 else
		str +='<th rowSpan="2">'+subLevelName+'</th>';
		str +='<th colspan="'+myResults.modifiedVotersList[0].selectOptionVOsList.length+'">Voters</th>';
		str +='<th colspan="'+colSpan+'">Total Voters </th>';
		str +='<th colspan="'+colSpan+'">Male Voters </th>';
		str +='<th colspan="'+colSpan+'">Female Voters</th>';
		/*str +='<th>Total Voters  Deleted</th>';
		str +='<th>Male Voters  Deleted</th>';
		str +='<th>Female Voters  Deleted</th>';*/		
	str +='</tr>';
	str +='<tr>';
		
		var publicationName = myResults.modifiedVotersList[0].selectOptionVOsList;
			for(var j in publicationName)
			  str +='<th>'+publicationName[j].name+'</th>';
			
		if(jsObj.locationType == "panchayat" || jsObj.locationType == "ward")
	   {
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		str +='<th>Moved</th>';
		str +='<th>Relocated</th>';
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		str +='<th>Moved</th>';
		str +='<th>Relocated</th>';
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		str +='<th>Moved</th>';
		str +='<th>Relocated</th>';
	   }
	   else
	{
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		
	}
	str +='</tr>';
    str+='</thead>';
	 str+='<tbody>';
    
	if(jsObj.locationType == "constituency"){
	for(var i=0;i<myResults.modifiedVotersList.length;i++)
 	 {
		var publicationList = myResults.modifiedVotersList[i].selectOptionVOsList; 
		  str+='<tr>';
		  if(myResults.modifiedVotersList[i].locationType == 'localElectionBody')
		    str+='<td style="text-align:left;"><a class="voterInfoLinksCLS" title="Click Here to View '+myResults.modifiedVotersList[i].name+' wise Added / Deleted Voters Info " href="javascript:{}" onclick="openNewWindow(\''+myResults.modifiedVotersList[i].locationType+'\',\''+myResults.modifiedVotersList[i].id+'\')">'+myResults.modifiedVotersList[i].name+'</a></td>';
		  else
			 str+='<td style="text-align:left;"><a class="voterInfoLinksCLS" title="Click Here to View '+myResults.modifiedVotersList[i].name+' '+myResults.modifiedVotersList[i].locationType+' wise Added / Deleted Voters Info " href="javascript:{}" onclick="openNewWindow(\''+myResults.modifiedVotersList[i].locationType+'\',\''+myResults.modifiedVotersList[i].id+'\')">'+myResults.modifiedVotersList[i].name+'</a></td>'; 
		
			for(var k in publicationList)
			  str +='<td>'+publicationList[k].id+'</td>';
		
		    str+='<td>'+myResults.modifiedVotersList[i].addedCount+'</td>';
		    str+='<td>'+myResults.modifiedVotersList[i].deletedCount+'</td>';
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
		    str+='<td>'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		    str+='<td>'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';
			str+='</tr>';
	 }
	}
	 
	 else{
	for(var i=0;i<myResults.modifiedVotersList.length;i++)
 	 {
		var publicationList = myResults.modifiedVotersList[i].selectOptionVOsList; 

		var locationType1 = myResults.modifiedVotersList[i].locationType;
		var locationId1 =  myResults.modifiedVotersList[i].id;
		  str+='<tr>';
		  if(myResults.modifiedVotersList[i].locationType == 'localElectionBody')
		    str+='<td style="text-align:left;"><a class="voterInfoLinksCLS" title="Click Here to View '+myResults.modifiedVotersList[i].name+' wise Added / Deleted Voters Info " href="javascript:{}" onclick="openNewWindow(\''+myResults.modifiedVotersList[i].locationType+'\',\''+myResults.modifiedVotersList[i].id+'\')">'+myResults.modifiedVotersList[i].name+'</a></td>';
		  else
			 str+='<td style="text-align:left;"><a class="voterInfoLinksCLS" title="Click Here to View '+myResults.modifiedVotersList[i].name+' '+myResults.modifiedVotersList[i].locationType+' wise Added / Deleted Voters Info " href="javascript:{}" onclick="openNewWindow(\''+myResults.modifiedVotersList[i].locationType+'\',\''+myResults.modifiedVotersList[i].id+'\')">'+myResults.modifiedVotersList[i].name+'</a></td>'; 
		
			for(var k in publicationList)
			  str +='<td>'+publicationList[k].id+'</td>';
		
		
		if(myResults.modifiedVotersList[i].addedCount!=0)
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'1\',\'Added\')}">'+myResults.modifiedVotersList[i].addedCount+'</a></td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].addedCount+'</td>';
		    
		if(myResults.modifiedVotersList[i].deletedCount!=0)
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'2\',\'Deleted\')}">'+myResults.modifiedVotersList[i].deletedCount+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].deletedCount+'</td>';
		
		if(myResults.modifiedVotersList[i].locationType == 'booth')
		 {
			 if(myResults.modifiedVotersList[i].movedCount!=0)
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'3\',\'Moved\')}">'+myResults.modifiedVotersList[i].movedCount+'</td>';
			else 
				str+='<td>'+myResults.modifiedVotersList[i].movedCount+'</td>';

			 if(myResults.modifiedVotersList[i].relocatedCount!=0)
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'4\',\'Relocated\')}">'+myResults.modifiedVotersList[i].relocatedCount+'</td>';
			else 
			str+='<td>'+myResults.modifiedVotersList[i].relocatedCount+'</td>';
		 }
			 
		if(myResults.modifiedVotersList[i].maleVotersAdded!=0)	 
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'1\',\'Added\')}">'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
			
		if(myResults.modifiedVotersList[i].maleVotersDeleted!=0)	
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'2\',\'Deleted\')}">'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
		
		if(myResults.modifiedVotersList[i].locationType == 'booth')
		 {
			 if(myResults.modifiedVotersList[i].maleVotersMoved!=0)	 
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'3\',\'Moved\')}">'+myResults.modifiedVotersList[i].maleVotersMoved+'</td>';
			else 
				str+='<td>'+myResults.modifiedVotersList[i].maleVotersMoved+'</td>';
			
			 if(myResults.modifiedVotersList[i].maleVotersRelocated!=0)	
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'4\',\'Relocated\')}">'+myResults.modifiedVotersList[i].maleVotersRelocated+'</td>';
			else 
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersRelocated+'</td>';
		 }
		
		if(myResults.modifiedVotersList[i].femaleVotersAdded!=0)
            str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'1\',\'Added\')}">'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		
		if(myResults.modifiedVotersList[i].femaleVotersDeleted!=0)
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'2\',\'Deleted\')}">'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';

		if(myResults.modifiedVotersList[i].locationType == 'booth')
		 {

			 if(myResults.modifiedVotersList[i].femaleVotersMoved!=0)
            str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'3\',\'Moved\')}">'+myResults.modifiedVotersList[i].femaleVotersMoved+'</td>';
			else 
				str+='<td>'+myResults.modifiedVotersList[i].femaleVotersMoved+'</td>';
		
			 if(myResults.modifiedVotersList[i].femaleVotersRelocated!=0)
				str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'4\',\'Relocated\')}">'+myResults.modifiedVotersList[i].femaleVotersRelocated+'</td>';
			else 
				str+='<td>'+myResults.modifiedVotersList[i].femaleVotersRelocated+'</td>';
		 }
		  str+='</tr>';
	 }
	 }

	 if(myResults.modifiedLocalBodyVotersList != "null" && myResults.modifiedLocalBodyVotersList != null)
	  for(var i=0;i<myResults.modifiedLocalBodyVotersList.length;i++)
 	 {
		
		  str+='<tr>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].name+'</td>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].addedCount+'</td>';
			str+='<td>'+myResults.modifiedLocalBodyVotersList[i].deletedCount+'</td>';
			str+='<td>'+myResults.modifiedLocalBodyVotersList[i].movedCount+'</td>';
			str+='<td>'+myResults.modifiedLocalBodyVotersList[i].relocatedCount+'</td>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].maleVotersAdded+'</td>';
			str+='<td>'+myResults.modifiedLocalBodyVotersList[i].maleVotersDeleted+'</td>';
			str+='<td>'+myResults.modifiedLocalBodyVotersList[i].maleVotersMoved+'</td>';
			str+='<td>'+myResults.modifiedLocalBodyVotersList[i].maleVotersRelocated+'</td>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].femaleVotersAdded+'</td>';
			str+='<td>'+myResults.modifiedLocalBodyVotersList[i].femaleVotersDeleted+'</td>';
			 str+='<td>'+myResults.modifiedLocalBodyVotersList[i].femaleVotersMoved+'</td>';
			  str+='<td>'+myResults.modifiedLocalBodyVotersList[i].femaleVotersRelocated+'</td>';
		  str+='</tr>';
	 }
	  str+='</tbody>';

	str+='</table>';
	
	
	$('#subLevelDiv').html(str);

	$('#subLevelVotersTable').dataTable({
		"aaSorting": [[ 1, "desc" ]] 
		});
}

function callAjaxForSubLevelInformation()
{
	var locationVal = locationValue;
	/* if(locationType == 'localElectionBody')
			locationVal = localElectionBodyId;*/

	 if(locationType == "booth")
	{
		$("#subLevelsMainDiv").css('display','none')
		return;
	}

	var jsObj=
		{	
			constituencyId:constituencyId,
			fromPublicationDateId:fromPublicationDateId,
			toPublicationDateId:toPublicationDateId,
			locationType:locationType,
			locationValue:locationVal,
			status:"",
			task:"getSubLevelInformation"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSubLevelsVoterModificationDetailsNew.action?"+rparam;	

		callAjax(jsObj,url);
		$('#subLevelAjaxImageDiv').css('display','block');

}

function openNewWindow(locationType, locationId)
{
  var urlStr ='voterModificationReportAction.action?constituencyId='+constituencyId+'&fromPublicationDateId='+fromPublicationDateId+'&toPublicationDateId='+toPublicationDateId+'&locationType='+locationType+'&locationValue='+locationId+'&';

  var updateBrowser = window.open(urlStr,'',"scrollbars=yes,height=600,width=750,left=200,top=200", '_blank');	
	updateBrowser.focus();
}

function getVotersInAFamilyByPublication(partNo,fromPublication,ToPublication,constituencyId,hNo){

	$.blockUI({ message: '<h4><img src="./images/icons/search.gif" />  Request Processing...</h4>',baseZ: 5000 }); 

//	$("#popupAjaxImage").show();
    var jsObj=
			{
					
				hno:hNo,
				fromPublication:fromPublication,
				ToPublication:ToPublication,
				partNo:partNo,
				constituencyId:constituencyId,
				task:"getVotersInAFamilyByConstituencyId"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInAFamilyByConstituencyId.action?"+rparam;						
		callAjax(jsObj,url);

}

function buildFamilyInfo(results,partNo)
{
		$("#popupAjaxImage").hide();
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
	  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	  {
		var name = oData;
		var id= oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
					
	  };
     var votersResultColumnDefs = [ 		    	             
		    	           // {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"boothName",label:"Booth",sortable:true},
							{key:"mobileNo",label:"MobileNo",sortable:true}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothName"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("voterimpFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    $("#voterimpFamDtlsOuterPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 910,
            height: 450
           
        });
}
function getVoterDetailsForGender(locationId1,locationType1,gender,voterStatusId,status)
{
$.blockUI({ message: '<h4><img src="./images/icons/search.gif" />  Request Processing...</h4>' }); 
	var jObj=
	{
			locationType:locationType1,
		    locationValue:locationId1,
			constituencyId:constituencyId,
			fromPublicationDateId:fromPublicationDateId,
			toPublicationDateId:toPublicationDateId,
			forGender:"true",
			status:status,
			voterStatusId:voterStatusId,
			ageRangeId:"0",
			gender:gender,	 
			task:"getGenderBasedVoterDetails"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getSelectedGenderBasedVoterDetails.action?"+rparam;	

	callAjax(jObj,url);


}

function getVoterDetailsByAge(range,voterStatusId,status)
{
$.blockUI({ message: '<h4><img src="./images/icons/search.gif"/> Request Processing...</h4>' }); 
	 var ageRangeId = 0;
	 if(range == "Young Voters")
		ageRangeId = 1; 
	 else if(range == "18-25")
		  ageRangeId = 2;
	 else if (range == "26-35")
		 ageRangeId = 3;
	 else if (range == "36-45")
		 ageRangeId = 4;
	 else if (range == "46-60")
		 ageRangeId = 5;
	 else 
		 ageRangeId = 6;
 

	var jObj=
	{
			locationType:locationType,
		    locationValue:locationValue,
			constituencyId:constituencyId,
			fromPublicationDateId:fromPublicationDateId,
			toPublicationDateId:toPublicationDateId,
			forGender:"false",
			status:status,
			voterStatusId:voterStatusId,
			ageRangeId:ageRangeId,
			gender:'M',	 
			task:"getGenderBasedVoterDetails"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getSelectedGenderBasedVoterDetails.action?"+rparam;	

	callAjax(jObj,url);
}

function PopUpForHelp(){

var str='';
str+='<div>'; 
str+='<div><span>ADDED:Newly Added Voters</span></div></br>';
str+='<div><span>DELETED:Deleted voters</span></div>';
str+='</div>';
$("#help_window").html(str);

$("#help_window").dialog({
				resizable:false,
				title:'Help',
				height: 'auto',
				width:'600',
				top:250,
				left:100,
				modal: true
				
	});
			
}
