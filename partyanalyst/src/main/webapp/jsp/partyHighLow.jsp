<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Polling High,Party Weak and Polling Low,Party Strong</title>
 <style>
  #mainDiv{
   min-height:400px;
  }
  #selectDiv{
    margin-top: 75px;
  }
  #ajaxImage{
    display:none; 
  }
  
  #partyHighTable table,#partyLowTable table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#partyHighTable table tr:nth-child(even),#partyLowTable table tr:nth-child(even){background:#EdF5FF;border:1px solid #d3d3d3;}
#partyHighTable table td,#partyLowTable table td{border:1px solid #d3d3d3;padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#partyHighTable table th,#partyLowTable table th{
background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 4px;
    padding-left: 4px;
    padding-right: 4px;
    padding-top: 4px;
    text-align: left;
	color:#333333;
	border:1px solid #d3d3d3;
	}
#partyHighTable,#partyLowTable{
	font-family : arial;
	font-size: 13px;
    margin-top:0px;
	padding: 10px 10px 10px 0px;
	 width: 950px;
}
#partyHighTable table th a,#partyLowTable table th a{
color:#333333;
}

</style>
</head>
<body>
<center> 
 <div id="mainDiv"> 
  <s:if test="{constituenciesList != null && constituenciesList.size >0}">
    <div id="selectDiv"><span style="font-weight:bold;font-size:16px;">Select Constituency : </span> <s:select theme="simple"    name="constituencyId" id="constituencyId" list="constituenciesList" onChange="getAllCriticalPanchayats();" listKey="id" listValue="name" /></div> 
  </s:if>
  <div id="ajaxImage" style="margin-top:35px;"><img src="images/icons/goldAjaxLoad.gif"></div>
  <div id="partyHighTable"></div>
  <div id="partyLowTable"></div>
  </div>
</center>  
  
  <script type="text/javascript">
   var constituencyId = '${constituencyId}';
   var type = '${type}';
   function getAllCriticalPanchayats(){
      var constiId = $("#constituencyId").val();
	  if(constiId > 0){
	    getCriticalPanchayats(constiId);
	  }
	  
   }
   function getCriticalPanchayats(constituencyId){
   $("#partyHighTable").html("");
   $("#partyLowTable").html("");
   if(constituencyId == 0){
     return;
   }
     $("#ajaxImage").show();
	   var jsObj= 
		{	
			constituencyId:constituencyId,
			partyId:872,
			eleId:38,
			eleId1:3,
			task:"getPollingPercentages"		
		};
	$.ajax({
	      type : "POST",
	      url : "getPollingPercentagesByPartyNYearAction.action",
	      data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
	  try{
		  buildCriticalPanchayats(result);
		  }catch(e){
		   
		  }
		   $("#ajaxImage").hide();
   });
   }
   function buildCriticalPanchayats(result){
	   if(result != null){
	   alert(type);
	       if(type == "reducePolling")
		     buildPollingLowPercentageForBooths(result); 
		   else
		     buildPollingHighPercentageForBooths(result);
	   }else{
	       $("#ajaxImage").hide();
	   }
   }
   if(constituencyId !=''){
	   getCriticalPanchayats(constituencyId);
   }else{
    $("#constituencyId").prepend("<option value='0'>Select Constituency</option>");
	$("#constituencyId").val(0);
   }
function buildPollingHighPercentageForBooths(result)
{

		var divEle = document.getElementById("partyHighTable");
		var strongList = result[0].strongPollingPercentVOList;
		if(strongList == null || strongList.length ==0){
		  $("#partyHighTable").html("");
          $("#partyLowTable").html("");
		  $("#partyHighTable").html("<span style='color:red;font-weight:bold;font-size:12px;'>No Data Available</span>");
		 }
		var str = '';
		str+='<div class="widget green">';
		str+='<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;"><span>OverAll Avg Polling Percentage : '+result[0].pollingPercentage+'</span>&nbsp&nbsp;';
		str+='<span>'+strongList[0].weakPollingPercentVOList[0].partyName+'  Avg Polling Percentage : '+result[0].partyPercentage+'</span></div>';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;">Polling High,' +strongList[0].weakPollingPercentVOList[0].partyName+' Party Weak</h4>';
		str+='<div style="overflow-x:scroll;"><table id="pollingPerHigh"  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;text-align:center;">';
		str+='<th>Booth</th>';
		if(result[0].constituencyType != "URBAN"){
		  str+='<th>Panchayat</th>';
		}
		str+='<th>Location</th>';
		str+='<th>Villages Covered</th>';
		
		str+='<th>Total Votes 2009</th>';
		str+='<th>Polled Votes 2009</th>';
		str+='<th>Polling %</th>';
		str+='<th>'+strongList[0].weakPollingPercentVOList[0].partyName+' Votes 2009 </th>';
		str+='<th>'+strongList[0].weakPollingPercentVOList[0].partyName+' %</th>';
		str+='<th>To Decrease</th>';
		str+='<th>Polling @'+result[0].pollingPercentage.toFixed(2)+'</th>';
		str+='<th>Lost Votes</th>';
		for(var i in strongList)
			{
		str+='<tr>';
		str+='<td>'+strongList[i].name+'</td>';
		if(result[0].constituencyType != "URBAN"){
			if(strongList[i].localbodyName !="")
			str+='<td>'+strongList[i].localbodyName+'</td>';
			else
			str+='<td>-</td>';
		}
		if(strongList[i].location !="")
		str+='<td>'+strongList[i].location+'</td>';
		else
		str+='<td>-</td>';
		if(strongList[i].villagesCovered !="")
		str+='<td>'+strongList[i].villagesCovered+'</td>';
		else
		str+='<td>-</td>';		
		str+='<td>'+strongList[i].totalVoters+'</td>';
		str+='<td>'+strongList[i].totalValidVotes+'</td>';
		str+='<td>'+strongList[i].pollingPercentage.toFixed(2)+'</td>';
		str+='<td>'+strongList[i].weakPollingPercentVOList[0].partyTotalvotes+'</td>';
		str+='<td>'+strongList[i].weakPollingPercentVOList[0].partyPercentage.toFixed(2)+'</td>';
		str+='<td>'+strongList[i].minValue.toFixed(2)+'</td>';
		str+='<td>'+strongList[i].rangePercentage+'</td>';
		str+='<td>'+strongList[i].lostSeats+'</td>';
		str+='</tr>';
			}
		str+='</table></div>';
		str+='</div>';
		divEle.innerHTML = str;
}

function buildPollingLowPercentageForBooths(result)
{

		var divEle = document.getElementById("partyLowTable");
		var weakList = result[0].weakPollingPercentVOList;
		if(weakList == null || weakList.length ==0){
		  $("#partyHighTable").html("");
          $("#partyLowTable").html("");
		  $("#partyHighTable").html("<span style='color:red;font-weight:bold;font-size:12px;'>No Data Available</span>");
		 }
		var str = '';
		str+='<div class="widget green">';
		str+='<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;"><span>OverAll Avg Polling Percentage : '+result[0].pollingPercentage+'</span>&nbsp&nbsp;';
		str+='<span>'+strongList[0].weakPollingPercentVOList[0].partyName+'  Avg Polling Percentage : '+result[0].partyPercentage+'</span></div>';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;">Polling Low,' +weakList[0].strongPollingPercentVOList[0].partyName+' Party Strong</h4>';
		str+='<div style="overflow-x:scroll;"><table id="pollingPerLow"  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;text-align:center;">';
		str+='<th>Booth</th>';
		if(result[0].constituencyType != "URBAN")
		str+='<th>Panchayat</th>';
		str+='<th>Location</th>';
		str+='<th>Villages Covered</th>';
		
		str+='<th>Total Votes 2009</th>';
		str+='<th>Polled Votes 2009</th>';
		str+='<th>Polling %</th>';
		str+='<th>'+weakList[0].strongPollingPercentVOList[0].partyName+' Votes 2009</th>';
		str+='<th>'+weakList[0].strongPollingPercentVOList[0].partyName+' %</th>';
		str+='<th>Scope To Improve(Avg Poll%('+result[0].pollingPercentage+'))</th>';
		str+='<th>Polling @'+result[0].pollingPercentage.toFixed(2)+'</th>';
		str+='<th>To Target</th>';
		str+='<th>'+weakList[0].strongPollingPercentVOList[0].partyName+' Lost Votes</th>';
		for(var i in weakList)
			{
		str+='<tr>';
		str+='<td>'+weakList[i].name+'</td>';
		if(result[0].constituencyType != "URBAN"){
			if(weakList[i].localbodyName !="")
			  str+='<td>'+weakList[i].localbodyName+'</td>';
			else
			  str+='<td>-</td>';
		}
		if(weakList[i].location !="")
		str+='<td>'+weakList[i].location+'</td>';
		else
		str+='<td>-</td>';
		if(weakList[i].villagesCovered !="")
		str+='<td>'+weakList[i].villagesCovered+'</td>';
		else
		str+='<td>-</td>';
		
		str+='<td>'+weakList[i].totalVoters+'</td>';
		str+='<td>'+weakList[i].totalValidVotes+'</td>';
		str+='<td>'+weakList[i].pollingPercentage.toFixed(2)+'</td>';
		str+='<td>'+weakList[i].strongPollingPercentVOList[0].partyTotalvotes+'</td>';
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
</script>
</body>
</html>