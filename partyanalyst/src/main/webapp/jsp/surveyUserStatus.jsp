<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
 <html>
  <head>	

    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
		<style>
			body{background:#f0f0f0;}
.m_top20{margin-top:20px;}
.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
.username thead tr:nth-child(2){ background:#eee;}
.username td:first-child{ min-width: 200px; }
.username th small{ font-size:11px; }
.username th{ text-align:center; }




		</style>	
	
  </head>
  
  <body>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="js/maps/leaflet.js"></script>
<link rel="stylesheet" href="css/leaflet.css"></link>
<script src="js/maps/google.js"></script>
<script src="js/maps/Permalink.js"></script>

<script src="js/maps/googleMap.js"></script>
<script>
function getconstituencies()
{


	var jsObj =
	{
	
	task : "getConstituencies"
	}
	$.ajax({
	type:'GET',
	url: 'getsurveyuserConstituenciesAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){

	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
		
	$("#constituencyId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}
	
	});
}


function getSurveyUserLoctionCount()
{
var constituencyID = $("#constituencyId").val();
var userTypeId = $("#userTypeId").val();

$("#basicCountDiv").html('');
$("#errorMsgDiv").html('');
var str ='';
if(constituencyID == 0)
	{
str +='<font color="red">Select Constituency</font>';
	}
else if(userTypeId == 0)
	{
str +='<font color="red">Select User Type</font>';
	}
	if(str.length > 0)
	{
$("#errorMsgDiv").html(str);
return;
	}
	$("#processingImg").show();
	var jsObj =
	{
	constituencyId : constituencyID,
		userTypeId:1,
	task : "getLocationCount"
	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUserLoctionCount.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		$("#processingImg").hide();
     buildSurveyUserStatusCount(result);
	
	});
}

 function buildSurveyUserStatusCount(result)
{
	var str ='';
	if(result.length == 0)
	{
str+='<font color=red>No Data avilable</font>';
	$("#basicCountDiv").html(str);
return;
	}
	
	str+='<table class=" table table-bordered m_top20 table-hover table-striped">';
	str+='<thead >';
	str+='<tr class="alert alert-success">'
	str+='<th rowspan="5">DCName</th>';
	str+='<th rowspan="5">Booth</th>';
	str+='<th rowspan="5"> Total Voters</th>';
	str+='<th colspan="3" style="text-align : center;">Data Collector</th>';
	str+='<th colspan="5" style="text-align : center;">Web monitoring</th>';
	
	str+='</tr>';

	str+='<tr class="alert alert-success">';
    str+='<th >Caste Mapped</th>';
	str+='<th >Hamlet Mapped</th>';
	str+='<th >Mobile Collected</th>';

	str+='<th>TOTAL </th>';
	str+='<th>Mobile MATCHED</th>';
	str+='<th>Mobile UN MATCHED</th>';
	str+='<th>CASTE MATCHED</th>';
	str+='<th>CASTE UN MATCHED</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result)
	{

		for(var j=0;j<result[i].subList.length;j++)
		{
			str+='<tr>';
			str+='<td>'+result[i].userName+'</td>';
			str+='<td> '+result[i].subList[j].partNo+'</td>';
			str+='<td>'+result[i].subList[j].totalVoters+'</td>';
			str+='<td>'+result[i].subList[j].casteCount+'</td>';
			str+='<td>'+result[i].subList[j].hamletCount+'</td>';
			str+='<td>'+result[i].subList[j].mobileNoCount+'</td>';
		    str+='<td>'+result[i].subList[j].count+'</td>';
			str+='<td>'+result[i].subList[j].mobileMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].mobileNotMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].casteMatchedCount+'</td>';
			str+='<td>'+result[i].subList[j].casteNotMatchedCount+'</td>';

			str+='</tr>';
		}
	}
	str+='</tbody>';
	str+='</table>';
	$("#basicCountDiv").html(str);
	

}


</script>
	<div class="container">
	
			<div class="row">
			<div class="span12">
			<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<!--<h4>User Tracking</h4>-->
							<div class="row">
								<div class="span8 offset3">
								<div id="errorMsgDiv" class="offset1"></div>
									<div class="row-fluid">
									
										<div class="span3 offset1">
											<label>Select Constituency</label>
										<select name="constituency" id="constituencyId" list="constituenciesList" style="width:130px;"></select>
								
										</div>
										<div class="span4">
											<label>Select User Type</label>
											<select name="constituency" id="userTypeId"  style="width:130px;">
											<option value="0">Select user type</option>
											<option value="1">Data Collectors</option>
											
											</select>
										</div>	
									</div>	
									
								</div>
							</div>
							<div class="row text-center m_top20" style="margin-right:51px;"><button type="button" class="btn btn-success" onClick="getSurveyUserLoctionCount();">SUBMIT</button>
							<img id="processingImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
							</div>
					</div>
					</div>
					</div>
					<div id="basicCountDiv" class="span12 m_top20">

				</div>

				
				</div>

	</div>




	<script>
	 getconstituencies();
	</script>
  </body>
  

 </html>
