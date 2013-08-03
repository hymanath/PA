
function showPartyPerformanceReportForBooth(result,jsObj)
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
}
