function getPublicationDateForCaste()
	{
	var constituencyID = $("#casteConstituencyList").val();
	
	var jsObj=
	{
		selected:constituencyID,
		selectedEle:"castePublicationDateList",
		task:"getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	

	
	callAjax(jsObj,url);
}

function getHamletPublicationDate()
{
 var constituencyID = $("#constituencyList").val();
	
	var jsObj=
	{
		selected:constituencyID,
		selectedEle:"publicationDateList",
		task:"getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	

	
	callAjax(jsObj,url);
}

function getSubLevelInfo()
{
$("#ajaxImgId").show();
$("#subLevelDataId").show();
var constituencyId = $("#constituencyList").val();
var publicationDateId = $("#publicationDateList").val();
var jsObj=
		{		
			constituencyId:constituencyId,
			publicationDateId:publicationDateId,
            task:"getSubLevelInfo"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSubLevelInfoAction.action?"+rparam;						
		callAjax(jsObj,url);

}

function getCasteAssignedInfo()
{
  $("#casteErrorMsgDiv").html('');
  var constituencyId = $("#casteConstituencyList").val();
  var publicationId = $("#castePublicationDateList").val();
  
  if(constituencyId == 0)
  {
	$("#casteErrorMsgDiv").html('Please Select Constituency.');
	return;
  }
  if(publicationId == 0)
  {
	$("#casteErrorMsgDiv").html('Please Select Publication Date.');
	return;
  }
  var type = $("input[name='casteAssignedRadio']:checked").val();
  
  $("#CasteAjaxImgId").css("display","inline-block");
  
  var jsObj=
  {
	constituencyId:constituencyId,
	publicationId:publicationId,
    type:type,
	task:"getVotersCasteDetails"
  }

  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
  var url = "getCasteAssignedAndNotAssignedVotersCountAction.action?"+rparam;	
  callAjax(jsObj,url);

}


function callAjax(jsObj,url)
{
 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "getSubLevelInfo")
								{
								buildData(myResults);	
								}
								else if(jsObj.task == "getPublicationDate")
								{
								 //buildHamletPublicationDateList(myResults);
								 buildCastePublicationDateList(myResults,jsObj);
								}
								
								else if(jsObj.task == "getVotersCasteDetails")
								 buildCasteAssignedVoters(myResults,jsObj);

							}catch (e) {
							    //alert(Exception);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 }
 
 function buildCastePublicationDateList(results,jsObj)
	{

	var selectedElmt=document.getElementById(''+jsObj.selectedEle+'');
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);

	var  publicationIdsArray = new Array();

	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);

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
		

	}

}


function buildHamletPublicationDateList(results)
	{

	var selectedElmt=document.getElementById("publicationDateList");
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);

	var  publicationIdsArray = new Array();

	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);

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
		

	}

}




var publicationDatesList;
function buildPublicationDateList(results)
	{
	publicationDatesList=results;
	var selectedElmt=document.getElementById("publicationDateList");
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);

	var  publicationIdsArray = new Array();

	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);

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
	}

	var largest = Math.max.apply(Math, publicationIdsArray);

	$('#publicationDateList').val(largest);
	$('#publicationDateList').trigger("change");

}


function buildData(results)
{
	$("#ajaxImgId").hide();
	$("#votersBasicInfo").css("display","none");
	var str = '';
	var divele = document.getElementById("subLevelInfo");
	
	str +='<table id="sublevel" class="table table-bordered table-striped table-hover">';
	str += '<tr>'
	str += '<th>Mandal</th>';
	str += '<th>Panchayat</th>';
	str += '<th>Total Voters</th>';
	str += '<th>Assigned Voters</th>';
	str += '<th>Not Assigned Voters</th>';
	str += '<th>Total Hamlets</th>';
	str += '<th>Hamlets Assigned</th>';
	str += '<th>Hamlets Not Assigned</th>';
	str += '</tr>';

	for(var i in results)
	{
		str += '<tr>';	
		str += '<td>'+results[i].tehsilName+'</td>';
		str += '<td>'+results[i].panchayatName+'</td>';
		str += '<td>'+results[i].totalVoters+'</td>';
		str += '<td>'+results[i].hamletAssignedVoters+'</td>';
		str += '<td>'+results[i].hamletsNotAssignedVoters+'</td>';

		str += '<td>';
		for(var j in results[i].hamletsList)
			str += ''+results[i].hamletsList[j].name+'<br>';
		str += '</td>';
	
		str += '<td>';
		for(var k in results[i].assignedHamletsList)
			str += ''+results[i].assignedHamletsList[k].name+' ('+results[i].assignedHamletsList[k].populateId+')<br>';
		str += '</td>';

		str += '<td>';
		for(var l in results[i].notAssignedHamletsList)
			str += ''+results[i].notAssignedHamletsList[l].name+'<br>';
		str += '</td>';

		str += '</tr>';	
	}
	str +='</table>';
	divele.innerHTML = str;
}

function buildCasteAssignedVoters(results,jsObj)
{
	$("#CasteAjaxImgId").css("display","none");
  $("#casteAssignedVotersInnerDiv").html('');
  if(results == null || results.length == 0)
  {
	$("#casteAssignedVotersInnerDiv").html('No data found.');
	return;
  }
  $("#casteAssignedVotersHideAndShow").css("display","inline-block");
  var str = '';
  str +='<div id="totalVotersDiv">';
  str +='<span><b>Total Voters : </b> '+results[0].totalVotersCount+'</span>';
  str +='<span style="margin-left: 15px; margin-right: 36px;"><b>Caste Assigned Voters : </b> '+results[0].casteAssignedTotVoters+'</span>';
  str +='<span><b>Caste Not Assigned Voters : </b> '+results[0].casteNotAssignedTotVoters+'</span>';
  str +='</div>';
  str +='<table id="casteAssignedVoters" class="table table-bordered table-striped table-hover">';
  str +='<tr>';
  if(jsObj.type == "booth")
  {
   str +='<th>Panchayat</th>';
   str +='<th>Booth</th>';
  }
  else if(jsObj.type == "ward")
  {
   str +='<th>Muncipality</th>';
   str +='<th>Ward</th>';
  }
  else
  {
   str +='<th>Mandal</th>';
   str +='<th>Panchayat</th>';
  }
  str +='<th>Total Voters</th>';
  str +='<th>Caste Assigned Voters</th>';
  str +='<th>Caste Not Assigned Voters</th>';
  str +='</tr>';
  for(var i in results)
  {
	 str +='<tr>';
	 str +='<td>'+results[i].panchayatName+'</td>';
	 str +='<td>'+results[i].partNO+'</td>';
	 str +='<td>'+results[i].totalVoters+'</td>';
	 str +='<td>'+results[i].hamletAssignedVoters+'</td>';
	 str +='<td>'+results[i].hamletsNotAssignedVoters+'</td>';
	 str +='</tr>';
  }
  str +='</table>';
 $("#casteAssignedVotersInnerDiv").html(str);
}

function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
	
$(document).ready(function(){
	
$("#casteAssignedVotersHideMenu").live("click",function(){
	  $("#casteAssignedVotersInnerDiv").css("display","none");
	  
	  $("#casteAssignedVotersHideAndShow").html('<a id="casteAssignedVotersShowMenu" class="btn"  href="javascript:{}">show<i class="icon-chevron-down"></i></a>');
		
	});

	$("#casteAssignedVotersShowMenu").live("click",function(){
		$("#casteAssignedVotersInnerDiv").css("display","block");
		$("#casteAssignedVotersHideAndShow").html('<a id="casteAssignedVotersHideMenu" class="btn"  href="javascript:{}">Hide<i class="icon-chevron-up"></i></a>');
	});
	
});