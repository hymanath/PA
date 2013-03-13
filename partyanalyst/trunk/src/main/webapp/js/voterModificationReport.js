var scopeId = 0; //selected scopeId

$(document).ready(function() {


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
	var url = "getVoterInfoAction.action?"+rparam;	

	callAjax(jObj,url);
	
	}

	function getAddedDeletedVoterInfoInALocation()
	{
		var locationVal = locationValue;

		if(constituencyId == null || constituencyId == '' || fromPublicationDateId == ''
		|| toPublicationDateId == null || toPublicationDateId == '' || locationType == '' || locationType == null || 
			locationValue == null || locationValue == '')
			return;
		if(locationType == 'localElectionBody')
			locationVal = localElectionBodyId;

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
	var url = "getAddedOrDeletedVoterInfoInALocationAction.action?"+rparam;	

	callAjax(jObj,url);
	}

	function getGenderWiseVoterModificationsBetweenPublications()
	{
		var locationVal = locationValue;
		if(constituencyId == null || constituencyId == '' || fromPublicationDateId == ''
		|| toPublicationDateId == null || toPublicationDateId == '' || locationType == '' || locationType == null || 
			locationValue == null || locationValue == '')
			return;
		if(locationType == 'localElectionBody')
			locationVal = localElectionBodyId;

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
	var url = "getGenderWiseVoterModifiBetweenPublicationsAction.action?"+rparam;	

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
		
		if(locationType == 'localElectionBody')
			locationVal = localElectionBodyId;

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
								else if(jsObj.task == "getAllVoterInformationInALocation")
								{
									showAllVoterInformationInALocation(myResults);
									$('#allvotersDetails').css('display','none');
								}
								else if(jsObj.task == "subRegionsInConstituency")
									buildMandalOrMuncipalities(myResults);
								else if(jsObj.task == "hamletsOrWardsInRegion" || jsObj.task ==  "panchayitiesInAMandal")
									buildWardsOrPanchaytis(myResults);
								else if(jsObj.task == "boothsInWard" || jsObj.task == "getBoothsInPanchayat")
									buildBooths(myResults);
								else if(jsObj.task == "getSubLevelInformation")
								{
								buildSubLevelInformation(jsObj,myResults);
								$('#subLevelAjaxImageDiv').css('display','none');
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
	str +='<div class="voterinfoHeading"><h2>Voters Basic Information In '+locationName+' '+locationType+'</h2></div>';
	
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
	if((fromPublicationName != '' || fromPublicationName != null) && (toPublicationName !='' || toPublicationName != null))
		str +='From '+fromPublicationName+' to '+toPublicationName+'';
	else if(fromPublicationName != '' || fromPublicationName != null)
		str +=': '+fromPublicationName+'';
	else if(toPublicationName != '' || toPublicationName != null)
		str +=': '+toPublicationName+'';
	str +='</h2></div>';
	
	if(results != null)
	{
		
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

	else 
	{
	  $('#voterAgeInfoDiv').html('<div class="voterinfoHeading"><h2>Age Wise Newly Added / Deleted Info From '+fromPublicationName+' to '+toPublicationName+'</h2></div>');
	  $('#voterAgeInfoDiv').append('<div>No Data Available.</div>');
	  return;
	}
}

function showGenderWiseVoterModifiBetweenPublications(results,jsObj)
{
	
	$('#voterGenderInfoDiv').html('');
	$('#voterGenderInfoDivAjaxImg').css('display','none');
	var str = '';
	str +='<div class="voterinfoHeading"><h2>Newly Added / Deleted Info ';
	
	if((fromPublicationName != '' || fromPublicationName != null) && (toPublicationName !='' || toPublicationName != null))
		str +='From '+fromPublicationName+' to '+toPublicationName+'';
	else if(fromPublicationName != '' || fromPublicationName != null)
		str +=': '+fromPublicationName+'';
	else if(toPublicationName != '' || toPublicationName != null)
		str +=': '+toPublicationName+'';
	
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
		str +='</table>';
		$('#voterGenderInfoDiv').html(str);
	}

	else 
	{
	  $('#voterGenderInfoDiv').html('<div class="voterinfoHeading"><h2>Newly Added / Deleted Info From '+jsObj.fromPublicationDateId+' to '+jsObj.toPublicationDateId+'</h2></div>');
	  $('#voterGenderInfoDiv').append('<div>No Data Available.</div>');
	  return;
	}
}

function showGenderWiseVoterModifiForEachPublic(results,jsObj)
{
	$('#genderWiseVoterModifiDiv').html('');
	$('#genderWiseVoterModifiAjaxImg').css('display','none');
	var str = '';

	str +='<div class="voterinfoHeading"><h2>Gender Wise Voter Modifications Between '+fromPublicationName+' to '+toPublicationName+'</h2></div>';

	if(results != null)
	{
		str +='<table class="voterInfoTable" class="table table-bordered table-hover">';
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
		 str +='</tr>';

		}
		str +='</table>';
		$("#genderWiseVoterModifiDiv").html(str);
	}
	else 
	{
	  $('#genderWiseVoterModifiDiv').html('<div class="voterinfoHeading"><h2>Gender Wise Voter Modifications Between '+fromPublicationName+' to '+toPublicationName+'</h2></div>');
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
		
		if(locationType == 'localElectionBody')
			locationValue1 = localElectionBodyId;

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
		str+='<td style="text-align:center;">'+results[i].houseNo+'</td>';
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

if(jsObj.locationType == "constituency")
    subLevelName = "Mandal/Muncipality";
else if(jsObj.locationType == "mandal")
    subLevelName = "Panchayat";
else if(jsObj.locationType == "panchayat" || jsObj.locationType == "ward")
    subLevelName = "booth";
else if(jsObj.locationType == "localbody" ||jsObj.locationType == "Local Election Body")
    subLevelName = "ward";

$('#subLevelDiv').html('');
if(myResults.modifiedVotersList.length == 0 && myResults.modifiedLocalBodyVotersList.length == 0 && myResults.modifiedVotersList != null && myResults.modifiedLocalBodyVotersList != null)
	return false;

var str='';
	 str+='<table class="voterInfoTable" id="subLevelVotersTable">';
	 str+='<thead>';
	 str +='<tr>';
		str +='<th>'+subLevelName+'</th>';
		str +='<th>Total Voters Added</th>';
		str +='<th>Male Voters Added</th>';
		str +='<th>Female Voters  Added</th>';
		str +='<th>Total Voters  Deleted</th>';
		str +='<th>Male Voters  Deleted</th>';
		str +='<th>Female Voters  Deleted</th>';		
	str +='</tr>';
     str+='</thead>';
	  str+='<tbody>';

	 for(var i=0;i<myResults.modifiedVotersList.length;i++)
 	 {
		
		  str+='<tr>';
		    str+='<td style="text-align:left;">'+myResults.modifiedVotersList[i].name+'</td>';
		    str+='<td>'+myResults.modifiedVotersList[i].addedCount+'</td>';
		    str+='<td>'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
		    str+='<td>'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		    str+='<td>'+myResults.modifiedVotersList[i].deletedCount+'</td>';
            str+='<td>'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
		    str+='<td>'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';
		  str+='</tr>';
	 }

	 if(myResults.modifiedLocalBodyVotersList != "null" && myResults.modifiedLocalBodyVotersList != null)
	  for(var i=0;i<myResults.modifiedLocalBodyVotersList.length;i++)
 	 {
		
		  str+='<tr>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].name+'</td>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].addedCount+'</td>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].maleVotersAdded+'</td>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].femaleVotersAdded+'</td>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].deletedCount+'</td>';
            str+='<td>'+myResults.modifiedLocalBodyVotersList[i].maleVotersDeleted+'</td>';
		    str+='<td>'+myResults.modifiedLocalBodyVotersList[i].femaleVotersDeleted+'</td>';
		  str+='</tr>';
	 }
	  str+='</tbody>';

	str+='</table>';
	
	
	$('#subLevelDiv').html(str);

	$('#subLevelVotersTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength":50,
		"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null
		] 
		});
}

function callAjaxForSubLevelInformation()
{
	var locationVal = locationValue;
	if(locationType == 'localElectionBody')
			locationVal = localElectionBodyId;

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
		var url = "getSubLevelsVoterModificationDetails.action?"+rparam;	

		callAjax(jsObj,url);
		$('#subLevelAjaxImageDiv').css('display','block');

}