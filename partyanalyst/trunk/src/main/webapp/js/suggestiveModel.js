
/* function showPartyPerformanceReportForBooth(result,jsObj)
{

	 $("#partyPerformanceBoothDiv").css("display","block");
	$("#partyPerformanceBoothDiv").html('');
  if(result == null || result.length == 0)
	{
     $("#partyPerformanceBoothDiv").html('No Data Found');
	 return;
	}
	var partyName = $('#partySelectEl option:selected').text();
	var str = '';
	 
	str+='<div class="widget green">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	for(var i in result[0].boothwisePartyPositionVOList)
	{
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+ result[0].boothwisePartyPositionVOList[i].name+'  Muncipality BOOTH WISE '+partyName+' PARTY PERFORMANCE REPORT</h4>';
	}
	str +='<table  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;text-align:center;">';
    str +='<tr>';
	str +='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">Type</th>';
	for(var i in result)
	
	if(result[i].boothwisePartyPositionVOList.length > 0)
	{
	str +='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">'+result[i].name+'</th>';
	}
	str +='</tr>';
	var flag = false;
	for(var i in result)
	if(result[i].boothwisePartyPositionVOList.length > 0)
	{
	flag = true;
	var boothList =result[i].boothwisePartyPositionVOList;
	if(flag)
	break;
	}
	if(flag == true)
	for(var h in boothList)
	{
	    var length =boothList[h].boothwisePartyPositionVOList.length;
		for(var k=0;k<length;k++)
		{
			str+='<tr>';
			str +='<td>'+boothList[h].boothwisePartyPositionVOList[k].name+'</td>';
			for(var i in result)
			{
			
			var boothList1 =result[i].boothwisePartyPositionVOList;
			for(var h in boothList1)
			{
			var partyList = boothList1[h].boothwisePartyPositionVOList[k].partyPositionVOList;
				str+='<td>';
				for(var p in partyList)
					{
					
					var tempVar = partyList.length-1;
					str+=''+partyList[p].name+'';
					
					if(p != tempVar)
						str +=', ';
					}
					str+='</td>';
			}
		}
		
	}
	str+='</tr>';
}


	str+='</table>';
	str += '</div>';
	str += '</div>';
	$("#partyPerformanceBoothDiv").html(str);
}*/


function showPartyPerformanceReportForBooth(result,jsObj,constituencyType)
{

	$("#partyPerformanceBoothDiv").css("display","block");
	$("#partyPerformanceBoothDiv").html('');
  if(result == null || result.length == 0)
	{
     $("#partyPerformanceBoothDiv").html('No Data Found');
	 return;
	}
	var partyName = $('#partySelectEl option:selected').text();
	var str = '';
	 
	str+='<div class="widget green">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';

	var tempVar1 = "";
	if(constituencyType == "urban")
     tempVar1 = "";
	else
	 tempVar1 = "Muncipality ";

	for(var i in result[0].boothwisePartyPositionVOList)
	{
	  str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+ result[0].boothwisePartyPositionVOList[i].name+' '+tempVar1+'BOOTH WISE '+partyName+' PARTY PERFORMANCE REPORT</h4>';
	}
	str +='<div style="margin-top:12px;">';
	str +='<span class="yearSpan" style="margin-right: 530px;">'+result[1].name+'</span>';
	str +='<span class="yearSpan">'+result[0].name+'</span>';
	str +='</div>';

    str +='<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
    str +='<tr>';
	str +='<th>Type</th>';
	str +='<th>Booth</th>';
	str +='<th>Total Votes</th>';
	str +='<th>Votes Polled</th>';
	str +='<th>Polling %</th>';
	str +='<th>Margin</th>';
	str +='<th>Votes Gained('+partyName+')</th>';

	str +='<th>Booth</th>';
	str +='<th>Total Votes</th>';
	str +='<th>Votes Polled</th>';
	str +='<th>Polling %</th>';
	str +='<th>Margin</th>';
	str +='<th>Votes Gained('+partyName+')</th>';
	
	str +='</tr>';


   var length = result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList.length;
   for(var j=0;j<length;j++)
   {
      var listSize1 = result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList.length;
	  var trFlag = false;
	
	  var strengthType = result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].name;
	  
	    if(listSize1 == 0)
	    {
		 str +='<tr>';
	     str +='<td style="background: none repeat scroll 0% 0% '+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].tempVar+';font-weight:bold;">'+strengthType+'</td>';
		 str +='<td></td><td></td><td></td><td></td><td></td><td></td>';
		 str +='<td></td><td></td><td></td><td></td><td></td><td></td>';
		 str +='</tr>';
	    }
	    else if(listSize1 > 0)
	    {
         str +='<tr>';
	     str +='<td rowspan="'+listSize1+'" style="background: none repeat scroll 0% 0% '+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].tempVar+';font-weight:bold;">'+strengthType+'</td>';
         for(var r=0;r<listSize1;r++)
	     {
           var partNo = result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].name;
		   var inFlag = true;
          
		   if(trFlag)
		    str +='<tr>';
			
		  /*  str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].name+'</td>';
			str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].totalVoters+'</td>';
			str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].totalValidVotes+'</td>';
			str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].percentage+'</td>';
			str +='<td><span style="background: none repeat scroll 0% 0% '+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].tempVar+'" class="spanCls">'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].margin+'</span></td>';
			str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].selectedPartyTotalVoters+'</td>';*/
            
		 if(result[0].boothwisePartyPositionVOList.length > 0)
		 {
			 for(var k=0;k<result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList.length;k++)
			 {
					for(var m=0;m<result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].partyPositionVOList.length;m++)
					{
					 if(result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].partyPositionVOList[m].name==partNo)
					 {
					    inFlag = false;
					    str +='<td>'+result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].partyPositionVOList[m].name+'</td>';
					    str +='<td>'+result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].partyPositionVOList[m].totalVoters+'</td>';
						str +='<td>'+result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].partyPositionVOList[m].totalValidVotes+'</td>';
						str +='<td>'+result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].partyPositionVOList[m].percentage+'</td>';
						str +='<td><span style="background: none repeat scroll 0% 0% '+result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].tempVar+'" class="spanCls">'+result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].partyPositionVOList[m].margin+'</span></td>';
						str +='<td>'+result[1].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[k].partyPositionVOList[m].selectedPartyTotalVoters+'</td>';
					 }
					}
			 }
		 }
				if(inFlag)
						str +='<td></td><td></td><td></td><td></td><td></td><td></td>';
						str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].name+'</td>';
					    str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].totalVoters+'</td>';
						str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].totalValidVotes+'</td>';
						str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].percentage+'</td>';
						str +='<td><span style="background: none repeat scroll 0% 0% '+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].tempVar+'" class="spanCls">'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].margin+'</span></td>';
						str +='<td>'+result[0].boothwisePartyPositionVOList[0].boothwisePartyPositionVOList[j].partyPositionVOList[r].selectedPartyTotalVoters+'</td>';
				str +='</tr>';
		  	     trFlag = true;
					
		  
		 }//inner for close
		 str +='</tr>';

	    }//else if

   }//main for close

   str += ' </table>';
	str += '</div>';
	str += '</div>';
	str += '</div>';

	
	$("#partyPerformanceBoothDiv").html(str);
}

function buildPollingHighPercentageForBooths(result)
{

var divEle = document.getElementById("strongPollingPerDiv");
var strongList = result[0].strongPollingPercentVOList;
if(strongList != null)
$("#strongPollingPerDiv").css("display","block");
var str = '';
str+='<div class="widget green">';
str+='<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;"><span>OverAll Avg Polling Percentage : '+result[0].pollingPercentage+'</span>&nbsp&nbsp;';
str+='<span>'+strongList[0].weakPollingPercentVOList[0].partyName+'  Avg Polling Percentage : '+result[0].partyPercentage+'</span></div>';
str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;">Polling High,' +strongList[0].weakPollingPercentVOList[0].partyName+' Party Weak</h4>';
str+='<div style="overflow-x:scroll;"><table id="pollingPerHigh"  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;text-align:center;">';
str+='<th>Booth</th>';
str+='<th>Panchayat</th>';
str+='<th>Location</th>';
str+='<th>Villages Covered</th>';
str+='<th>'+strongList[0].weakPollingPercentVOList[0].partyName+' </th>';
str+='<th>Total Votes</th>';
str+='<th>Polled Votes</th>';
str+='<th>Polling %</th>';
str+='<th>'+strongList[0].weakPollingPercentVOList[0].partyName+' %</th>';
str+='<th>To Decrease</th>';
str+='<th>Polling @'+result[0].pollingPercentage.toFixed(2)+'</th>';
str+='<th>Lost Votes</th>';
for(var i in strongList)
	{
str+='<tr>';
str+='<td>'+strongList[i].name+'</td>';
if(strongList[i].localbodyName !="")
str+='<td>'+strongList[i].localbodyName+'</td>';
else
str+='<td>-</td>';
if(strongList[i].location !="")
str+='<td>'+strongList[i].location+'</td>';
else
str+='<td>-</td>';
if(strongList[i].villagesCovered !="")
str+='<td>'+strongList[i].villagesCovered+'</td>';
else
str+='<td>-</td>';
str+='<td>'+strongList[i].weakPollingPercentVOList[0].partyTotalvotes+'</td>';
str+='<td>'+strongList[i].totalVoters+'</td>';
str+='<td>'+strongList[i].totalValidVotes+'</td>';
str+='<td>'+strongList[i].pollingPercentage.toFixed(2)+'</td>';
str+='<td>'+strongList[i].weakPollingPercentVOList[0].partyPercentage.toFixed(2)+'</td>';
str+='<td>'+strongList[i].minValue.toFixed(2)+'</td>';
str+='<td>'+strongList[i].rangePercentage+'</td>';
str+='<td>'+strongList[i].lostSeats+'</td>';
str+='</tr>';
	}
str+='</table></div>';
str+='</div>';
divEle.innerHTML = str;
buildAddedVotersCountForBooth(result);
}

function buildPollingLowPercentageForBooths(result)
{

var divEle = document.getElementById("weakPollingPerDiv");
var weakList = result[0].weakPollingPercentVOList;
if(weakList != null)
$("#weakPollingPerDiv").css("display","block");
var str = '';
str+='<div class="widget green">';
str+='<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;"><span>OverAll Avg Polling Percentage : '+result[0].pollingPercentage+'</span>&nbsp&nbsp;';
str+='<span>'+weakList[0].strongPollingPercentVOList[0].partyName+'  Avg Polling Percentage : '+result[0].partyPercentage+'</span></div>';
str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;">Polling Low,' +weakList[0].strongPollingPercentVOList[0].partyName+' Party Strong</h4>';
str+='<div style="overflow-x:scroll;"><table id="pollingPerLow"  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;text-align:center;">';
str+='<th>Booth</th>';
str+='<th>Panchayat</th>';
str+='<th>Location</th>';
str+='<th>Villages Covered</th>';
str+='<th>'+weakList[0].strongPollingPercentVOList[0].partyName+'</th>';
str+='<th>Total Votes</th>';
str+='<th>Polled Votes</th>';
str+='<th>Polling %</th>';
str+='<th>'+weakList[0].strongPollingPercentVOList[0].partyName+' %</th>';
str+='<th>Scope To Improve(Avg Poll%('+result[0].pollingPercentage+'))</th>';
str+='<th>Polling @'+result[0].pollingPercentage.toFixed(2)+'</th>';
str+='<th>To Target</th>';
str+='<th>'+weakList[0].strongPollingPercentVOList[0].partyName+'</th>';
for(var i in weakList)
	{
str+='<tr>';
str+='<td>'+weakList[i].name+'</td>';
if(weakList[i].localbodyName !="")
str+='<td>'+weakList[i].localbodyName+'</td>';
else
	str+='<td>-</td>';
if(weakList[i].location !="")
str+='<td>'+weakList[i].location+'</td>';
else
str+='<td>-</td>';
if(weakList[i].villagesCovered !="")
str+='<td>'+weakList[i].villagesCovered+'</td>';
else
str+='<td>-</td>';
str+='<td>'+weakList[0].strongPollingPercentVOList[0].partyTotalvotes+'</td>';
str+='<td>'+weakList[i].totalVoters+'</td>';
str+='<td>'+weakList[i].totalValidVotes+'</td>';
str+='<td>'+weakList[i].pollingPercentage.toFixed(2)+'</td>';
str+='<td>'+weakList[i].strongPollingPercentVOList[0].partyPercentage.toFixed(2)+'</td>';
str+='<td>'+weakList[i].maxValue.toFixed(2)+'</td>';
str+='<td>'+weakList[i].rangePercentage+'</td>';
str+='<td>'+weakList[i].toTarget+'</td>';
str+='<td>'+weakList[i].toImprove+'</td>';
str+='</tr>';
	}
str+='</table></div>';
str+='</div>';
divEle.innerHTML = str;

}

function buildAddedVotersCountForBooth(result)
{

var strongList = result[0].strongPollingPercentVOList;
var str='';
var divEle = document.getElementById("addedVoterDetailsDiv1");
var constituencyId = $("#listConstituencyNames").val();
var flag = false;
for(var i in strongList)
	{
	if(strongList[i].addedVotersCount > 0)
		flag = true;
		if(flag)
		break;
	}
	if(flag)
	{
$("#addedVoterDetailsDiv1").css("display","block");
 str+='<div class="widget green">';
 str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;">Poor AND ADDED VOTERS ARE MORE</h4>';
str+='<table id="addedvoters"  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;text-align:center;">';
str+='<th>Booth</th>';
str+='<th>Panchayat</th>';
str+='<th>ADDED VOTERS</th>';

for(var i in strongList)
	{
	if(strongList[i].addedVotersCount > 0)
		{
	str+='<tr>';
	str+='<td>'+strongList[i].name+'</td>';
if(strongList[i].localbodyName !="")
str+='<td>'+strongList[i].localbodyName+'</td>';
else
str+='<td>-</td>';
str+='<td><a onclick="getVoterDetailsByPartNo('+strongList[i].name+','+constituencyId+',0);">'+strongList[i].addedVotersCount+'</td>';
	
	str+='</tr>';
		}
	}

str+='</table>';
str+='</div>';
	}
divEle.innerHTML = str;

}

function buildAddedVoterDetails(results,jsObj)
{
$("#voterDetailsDiv").css("display","block");
	 $('#voterDetailsInnerDiv').html('');
  var str = '';
 
  str +='<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;text-align:center;">';
  str +='<tr>';
  str +='<th>Name</th>';
  str +='<th>Age</th>';
  str +='<th>Gender</th>';
  str +='<th>HouseNo</th>';
  str +='</tr>';
  for(var i in results)
  {
    str +='<tr>';
    
    str +='<td>'+results[i].name+'</td>';
    str +='<td>'+results[i].age+'</td>';
    str +='<td>'+results[i].gender+'</td>';
    str +='<td>'+results[i].houseNo+'</td>';
    str +='</tr>'; 
  }
  str+='</table>';

  $('#voterDetailsInnerDiv').html(str);
$('#voterDetailsDiv').dialog({                   
		    modal: true,
            title: "<b>Voter Details</b>",
			width: 970,
            height: 600
     });
	
  var itemsCount=results[0].totalVoters;
	    var maxResults=jsObj.results;
	   
	     if(jsObj.startIndex==0){
		   $("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getVoterDetailsByPartNo(jsObj.partno,jsObj.constituencyId,num);
				
			}
		});
	}
}