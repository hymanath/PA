$(document).ready(function(){
	
	$("#electionTypeId").change(function(){

	 $("#eleErrorMsgDiv").html("");
	 var electionId = $("#electionTypeId").val();
	 if(electionId == 0)
	  {
		$("#eleErrorMsgDiv").html("Please Select Election Type");
		return;
	  }

	 if(electionId == 2)
	 {
	   $('#statesDiv').css("display","block");
	   getStates();
	 }

	if(electionId == 1)
	{
	  $('#statesDiv').css("display","none");
	  getElectionYears('Parliament');
	}
	 
  });


  $("#stateId").change(function(){
	 getElectionYears('Assembly'); 
  });


  $("#eleBtnId").click(function(){

	$("#eleErrorMsgDiv").html('');
	var electionId = $("#electionYear").val();
	var electionType = $("#electionTypeId :selected").text();

	var electionTypeId = $("#electionTypeId").val();
	if(electionTypeId == 0)
	{
		$("#eleErrorMsgDiv").html('Please Select Election Type.');
		return;
	}
	if(electionTypeId == 2)
	{
	  var stateId = $("#stateId").val();
	  if(stateId == 0)
	  {
		$("#eleErrorMsgDiv").html('Please Select State.');
		return;
	  }
	}
	if(electionId == 0)
	{
	  $("#eleErrorMsgDiv").html('Please Select Election Year.');
	  return;
	}

	
	$("#eleResAjaxImg").css("display","inline-block");
	var jsObj=
		{						
		  electionId:electionId,
		  electionType:electionType,
		  task:"validateEleResults"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "validateEleResultsAction.action?"+rparam;
	callAjax(jsObj,url);
	  
  });


  $("#panchayatMappingId").click(function(){
	  
	$("#panchayatErrorMsgDiv").html('');
	
	$("#votersBasicInfo").css('display','block');
	
	
	var constituencyId = $("#constituencyList1").val();
	var publicationDateId = $("#publicationList").val();
	if(constituencyId == 0 || constituencyId == "0")
	{
      $('#panchayatErrorMsgDiv').html("Please Select Constituency.");
	  return;
	}
	else if(publicationDateId == 0 || publicationDateId == "0")
	{
      $('#panchayatErrorMsgDiv').html("Please Select Publication Date.");
	  return;
	}
	var name = $('#constituencyList1 :selected').text();
	$("#ajaxImg").show();

	 var jsObj=
		{		
			constituencyId:constituencyId,
			publicationDateId:publicationDateId,
			name			 :name,
			task:"validatePanchayatHamletData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "validatePanchayatHamletMappingDataAction.action?"+rparam;						
		callAjax(jsObj,url);
		
  });

});//End Ready


function getStates()
  {
	var electionType = $("#electionTypeId").val();
	var jsObj=
		{						
		  electionType:electionType,
		  task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;
	callAjax(jsObj,url);
  }

function getElectionYears(electionType)
{

	var stateEle = document.getElementById("stateId");
	if(electionType == 'Assembly')
	var stateId = stateEle.options[stateEle.selectedIndex].value;
	if(electionType == 'Parliament')
		stateId = 1;
	document.getElementById("electionYears").length = 0;
	
	if(electionType == null || electionType == 'Select Type' || stateId == 0)
		return;
	
	var jObj = {
			stateId : stateId,
		electionType: electionType,
				task: 'getElectionYearsForAState'
				};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
	callAjax(jObj,url);
}

function buildElectionYears(myResult)
{
	if(myResult == null || myResult.length == 0)
		return;

	var electionYearsElmt = document.getElementById("electionYear");
	electionYearsElmt.options.length=0;

	for(var i in myResult)
	{
		var option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionYearsElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionYearsElmt.add(option); // IE only
		}
	}	

}

function buildStates(myResult)
{	
	 
  if(myResult == null || myResult.length == 0)
	return;
	
	var electionTypeElmt = document.getElementById("stateId");
	electionTypeElmt.options.length=0;

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select State";
	try
	{
		electionTypeElmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		electionTypeElmt.add(option); // IE only
	}
	
	for(var i in myResult)
	{
		var option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionTypeElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionTypeElmt.add(option); // IE only
		}
	}	

	//hideBusyImgWithId("states");

}

function showConstituencyWiseEleResults(result,jsObj)
{
	$("#constResMainDiv").html('');
	$("#conEleHeading").css('display','block');
	$("#conEleHeading").html(''+jsObj.electionType+' Constituency Wise Election Results Verification');
	
	var validVotesList = result.consValidVotesVO;
	var totVotesList = result.conTotalVotesVO;
	var validVotGreaterTotVotesList = result.consValidVotesGreaterTotVotesVO;

	 var str = '';

	 str +='<table id="constituencyVotesTab" class="table table-bordered" style="margin-top: 15px;">';
	 str +='<tr>';
	 str +='<th>Zero Or Null Total Votes Constituencies</th>';
	 str +='<th>Zero Or Null Valid Votes Constituencies</th>';
	 str +='</tr>';
	 str+='<tr>';

	 str +='<td class="tdCls">';

	 str +='<div id="conTotalVotesDiv" class="constituencyDiv">';
	 if(totVotesList != null && totVotesList.length > 0)
	 {
		for(var j=0;j<totVotesList.length;j++)
		 str+='<span>'+totVotesList[j].constituencyName+'</span><br>';
	 }
	 else
	   str +='No Data Found.';
	 str +='</div>';
	  
	 str +='</td>';

	 str +='<td class="tdCls">';
	 str +='<div id="conValidVotesDiv" class="constituencyDiv">';
	 if(validVotesList != null && validVotesList.length > 0)
	 {
	   for(var i=0;i<validVotesList.length;i++)
	    str +='<span>'+validVotesList[i].constituencyName+'</span><br>';
	 }
	 else
	  str += 'No Data Found.';
	  str +='</div>';
	   
	  str +='</td>';
	  str +='</tr>';
	  str +='</table>';

    str +='<h3 class="headingSpan">Total Votes < Valid Votes Constituencies</h3><div>';
    if(validVotGreaterTotVotesList != null && validVotGreaterTotVotesList.length > 0)
	{
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Constituency</th>';
		str +='<th>Total Votes</th>';
		str +='<th>Valid Votes</th>';
		str +='</tr>';
		for(var k=0;k<validVotGreaterTotVotesList.length;k++)
		{
		 str +='<tr>';
		 str +='<td>'+validVotGreaterTotVotesList[k].constituencyName+'</td>';
		 str +='<td>'+validVotGreaterTotVotesList[k].totalVotes+'</td>';
		 str +='<td>'+validVotGreaterTotVotesList[k].validVotes+'</td>';
		 str +='</tr>';
		}
		str +='</table>';

	}
	else
     str +='<p class="paraCls">No Data Found</p>';
	str +='</div>';

	$("#constResMainDiv").html(str);
	
}

function showBoothWiseEleResults(result,jsObj)
{
  $("#boothResMainDiv").html('');
  $("#boothEleHeading").css('display','block');
  $("#boothEleHeading").html('Booth Wise Election Results in '+jsObj.electionType+' Constituency Verification');
   var validVotesList = result.boothValidVotesVO;
   var totVotesList = result.boothTotalVotesVO;
   var validVotGreaterTotVotesList = result.boothValidVotesGreaterTotVotesVO;
   var maleAndFemaleVotesList = result.boothTotWithMaleAndFemaleVO;

   var str = '';
	
	  str +='<h3 class="headingSpan">Zero Or Null Total Votes Constituencies</h3>';
      if(totVotesList != null && totVotesList.length > 0)
	  {
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Constituency</th>';
		str +='<th>Part No</th>';
		str +='</tr>';
		
		for(var j=0;j<totVotesList.length;j++)
		{
		 str +='<tr>';
		 str+='<td>'+totVotesList[j].constituencyName+'</td>';
		 str+='<td>'+totVotesList[j].partNo+'</td>';
		 str +='</tr>';
		}
		
		str +='</table>';

	 }
	 else
      str +='<p class="paraCls">No Data Found</p>';
		

     str +='<h3 class="headingSpan">Zero Or Null Valid Votes Constituencies</h3>';
      if(validVotesList != null && validVotesList.length > 0)
	  {
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Constituency</th>';
		str +='<th>Part No</th>';
		str +='</tr>';
		
		for(var j=0;j<validVotesList.length;j++)
		{
		 str +='<tr>';
		 str+='<td>'+validVotesList[j].constituencyName+'</td>';
		 str+='<td>'+validVotesList[j].partNo+'</td>';
		 str +='</tr>';
		}
		
		str +='</table>';

	 }
	 else
      str +='<p class="paraCls">No Data Found</p>';
		

    str +='<h3 class="headingSpan">Total Votes < Valid Votes Constituencies</h3><div>';
    if(validVotGreaterTotVotesList != null && validVotGreaterTotVotesList.length > 0)
	{
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Constituency</th>';
		str +='<th>Part No</th>';
		str +='<th>Total Votes</th>';
		str +='<th>Valid Votes</th>';
		str +='</tr>';
		for(var k=0;k<validVotGreaterTotVotesList.length;k++)
		{
		 str +='<tr>';
		 str +='<td>'+validVotGreaterTotVotesList[k].constituencyName+'</td>';
		 str +='<td>Booth- '+validVotGreaterTotVotesList[k].partNo+'</td>';
		 str +='<td>'+validVotGreaterTotVotesList[k].totalVotes+'</td>';
		 str +='<td>'+validVotGreaterTotVotesList[k].validVotes+'</td>';
		 str +='</tr>';
		}
		str +='</table>';

	}
	else
     str +='<p class="paraCls">No Data Found</p>';
	str +='</div>';

	str +='<h3 class="headingSpan">Male And Female Votes is Not Equal to Total votes</h3><div>';
    if(maleAndFemaleVotesList != null && maleAndFemaleVotesList.length > 0)
	{
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Constituency</th>';
		str +='<th>Part No</th>';
		str +='<th>Total Votes</th>';
		str +='<th>Male Votes</th>';
		str +='<th>FemaleVotes</th>';
		str +='</tr>';
		for(var k=0;k<maleAndFemaleVotesList.length;k++)
		{
		 str +='<tr>';
		 str +='<td>'+maleAndFemaleVotesList[k].constituencyName+'</td>';
		 str +='<td>Booth- '+maleAndFemaleVotesList[k].partNo+'</td>';
		 str +='<td>'+maleAndFemaleVotesList[k].totalVotes+'</td>';
		 str +='<td>'+maleAndFemaleVotesList[k].maleVotes+'</td>';
		 str +='<td>'+maleAndFemaleVotesList[k].feMaleVotes+'</td>';
		 str +='</tr>';
		}
		str +='</table>';

	}
	else
     str +='<p class="paraCls">No Data Found</p>';
	str +='</div>';
   $("#boothResMainDiv").html(str);
}

function showMappingDataForPanchayat(result,jsObj)
{
  $("#ajaxImg").css("display","none");
  $("#panchayatMappingInnerDiv").html('');
  if(result == null || result.length == 0)
  {
	$("#panchayatMappingInnerDiv").html('No Data Found.');
	return;
  }
  if(result != null && result.length > 0)
  {
	 var str = '';
	 if(result[0].areaType == "URBAN")
	 {
		$("#panchayatMappingInnerDiv").html(''+jsObj.name+' Constituency is URBAN Area.There are no Panchayats Exists.').css("color","#676A67");
		return;
	 }
	 
	 $('#panchayatHideAndShow').css("display","inline-block");
	 str +='<table id="" class="table table-bordered table-striped table-hover">';
	 str +='<tr>';
	 str +='<th>Mandal</th>';
	 str +='<th>Status</th>';
	 str +='<th>Total Panchayats</th>';
	 str +='<th>Mapped Panchayats</th>';
	 str +='<th>UnMapped Panchayats</th>';
	 str +='</tr>';
	 for(var i in result)
	 {
		str +='<tr>';
		str +='<td>'+result[i].name+'</td>';
		str +='<td>'+result[i].status+'</td>';
		str +='<td>'+result[i].totalCount+'';
		if(result[i].totalCount > 0)
		{
		  if(result[i].totalList != null && result[i].totalList.length > 0)
		  {
			str +='<br>----------------';
			for(var j=0;j<result[i].totalList.length;j++)
			 str +='<br><span>'+result[i].totalList[j].name+'</span>';
		  }
		}
		str +='</td>';

		str +='<td>'+result[i].mappedCount+'';
		if(result[i].mappedCount > 0)
		{
		  if(result[i].mappedList != null && result[i].mappedList.length > 0)
		  {
			str +='<br>----------------';
			for(var j=0;j<result[i].mappedList.length;j++)
			 str +='<br><span>'+result[i].mappedList[j].name+'</span>';
		  }
		}
		str +='</td>';

		str +='<td>'+result[i].unMappedCount+'';
		if(result[i].unMappedCount > 0)
		{
		  if(result[i].unMappedList != null && result[i].unMappedList.length > 0)
		  {
			str +='<br>----------------';
			for(var j=0;j<result[i].unMappedList.length;j++)
			 str +='<br><span>'+result[i].unMappedList[j].name+'</span>';
		  }
		}
		str +='</td>';

		str +='</tr>';
	 }
	 str +='</table>';
	$("#panchayatMappingInnerDiv").html(str);
  }
	

}