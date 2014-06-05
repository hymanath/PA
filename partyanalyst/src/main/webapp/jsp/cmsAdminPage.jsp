<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CMS Admin Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>

<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />


<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">


<style>
.dataTables_filter, .dataTables_length {
    display: none !important;
}

table.dataTable tr.odd td.sorting_1  {
background-color: #F9F9F9 !important;
}
table.dataTable tr.even td.sorting_1
{
background-color:  #FFFFFF !important;
}

.widget {border-top:5px solid #FFDC2D;}
.dataTables_filter,.dataTables_length{display:none !important;}

#JqTable{font-size:12px}
#errorMsgDiv{font-weight:bold;}

.paginate_disabled_next, .paginate_enabled_next {
    margin-left: 10px !important;
    padding-right: 31px !important;
}
.paginate_disabled_previous, .paginate_enabled_previous, .paginate_disabled_next, .paginate_enabled_next {
   
    margin-left: 40px !important;
  
}
</style>

</head>
<body>
<div class="container" style="margin-top:10px;">


<div id="reportDiv" style="margin-left:auto;margin-right:auto;"></div>
<div id="ajaxImg" style="display:none;margin-left: auto; margin-right: auto;text-align:center;"><img src="./images/Loading-data.gif" alt="Processing Image" /></div>
<div id="errorDiv"></div>
<div id="reportDetailsDiv" style="margin-left:auto;margin-right:auto;"></div>


<div style="margin-top:30px;clear:both;display:none;" id="submitDiv"></div>
</div>

<script>

function getReport()
{
	$("#reportDiv").html('');
	var jsObj = {
		task : "getData"
	}
$.ajax({
          type:'GET',
          url: 'getCmsAdminReport.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			 
			buildReport(result);
	   });
}

function buildReport(result)
{
	var str = '';
	str+='<h4 style="color:#59A2BE;font-family:verdana;font-weight:bold;text-align:center;"> CMS Admin Report</h4>';
	str+='<table class="table table-bordered" style="margin-left:auto;margin-right:auto;width:43%;">';
	str+='<tr>';
	str+='<th></th>';
	str+='<th>Total</th>';
	str+='<th>Inserted</th>';
	str+='<th>NotInserted</th>';
	str+='</tr>';
	
	str+='<tr>';
	str+='<td>Cadre</td>';
	str+='<td><a onclick="getDetails(\'cadre\',\'total\')">'+result.totalCadre+'</a></td>';
	str+='<td><a onclick="getDetails(\'cadre\',\'inserted\')">'+result.cadreInserted+'</a></td>';
	str+='<td><a onclick="getDetails(\'cadre\',\'notInserted\')">'+result.cadreNotInserted+'</a></td>';
	str+='</tr>';

	str+='<tr>';
	str+='<td>Influencing People</td>';
	str+='<td><a onclick="getDetails(\'influencePeople\',\'total\')">'+result.totalInfluencePeople+'</a></td>';
	str+='<td><a onclick="getDetails(\'influencePeople\',\'inserted\')">'+result.influencePeopleInserted+'</a></td>';
	str+='<td><a onclick="getDetails(\'influencePeople\',\'notInserted\')">'+result.influencePeopleNotInserted+'</a></td>';
	str+='</tr>';


	str+='<tr>';
	str+='<td>Tagged Voters</td>';
	str+='<td><a onclick="getDetails(\'tagged\',\'total\')">'+result.totalTagged+'</a></td>';
	str+='<td><a onclick="getDetails(\'tagged\',\'inserted\')">'+result.totalInserted+'</a></td>';
	str+='<td><a onclick="getDetails(\'tagged\',\'notInserted\')">'+result.totalNotInserted+'</a></td>';
	str+='</tr>';
	



	str+='</table>';
	$("#reportDiv").html(str);

}

function getDetails(isType,typeOfData)
{
$("#ajaxImg").show();
$("#reportDetailsDiv").html('');
$("#submitDiv").hide();
var jsObj = {

	isType : isType,
	typeOfData : typeOfData
}
$.ajax({
          type:'GET',
          url: 'getCmsAdminReportDetailsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			 
			buildDetails(result,jsObj);
	   });
}

function buildDetails(result,jsObj)
{
	var title ='';
	var str = '';
	$("#ajaxImg").hide();
	if(jsObj.isType == 'tagged')
	title = 'Tagged';
	else if(jsObj.isType == 'influencePeople')
			title = 'Influencing People';
	else if(jsObj.isType == 'cadre')
			title = 'Cadre';
	str+='<h4 style="color:#2B7295;font-weight:bold;font-family:verdana;font-size:12px;">';
	str+=''+title+' Voter Details</h4>';
	str+='<table id="JqTable" class="table table-striped table-bordered m-top15 clearfix" style="border:2px solid black">';
	str+='<thead>';
	str+='<tr>';
	if(jsObj.typeOfData == 'notInserted')
	str+='<th>Select</th>';
	str+='<th>SNO</th>';
	str+='<th>Constituency</th>';
	str+='<th>VoterId</th>';
	str+='<th>Name</th>';
	str+='<th>Gender</th>';
	str+='<th>Age</th>';
	str+='<th>Mobile</th>';

	str+='</tr>';
	str+='</thead>';
 str+='  <tbody>';
	var j = 0;
	for(var i in result)
	{
		j++;
	str+='<tr class="rowClass">';
	if(jsObj.typeOfData == 'notInserted')
		{
	str+='<td><input class="checkboxClass" type="checkbox" value='+result[i].voterIdCardNo+'/></td>';
		}
	str+='<td>'+j+'</td>';
	str+='<td><input type="hidden" class="constituency" value='+result[i].constituencyId+'></input>'+result[i].constituency+'</td>';
	str+='<td><input type="hidden" class="voterId" value='+result[i].voterIdCardNo+'></input>'+result[i].voterIdCardNo+'</td>';
	str+='<td class="name">'+result[i].name+'</td>';
	str+='<td><input type="hidden" class="gender" value='+result[i].gender+'></input>'+result[i].gender+'</td>';
	str+='<td><input type="hidden" class="age" value='+result[i].age+'></input>'+result[i].age+'</td>';
	str+='<td><input type="hidden" class="mobileNo" value='+result[i].mobileNo+'></input>'+result[i].mobileNo+'</td>';
	str+='</tr>';
	}
	 str+='</tbody>';
	str+='</table>';
	$("#submitDiv").html('<input type="button" class="btn btn-info" value="Submit" onclick="saveDetails(\''+jsObj.isType+'\');"/>');
	$("#reportDetailsDiv").html(str);

	
	if(jsObj.typeOfData == 'notInserted')
	{
			$('#JqTable').dataTable({
			//"aaSorting": [[ 1, "asc" ]],
			"iDisplayLength": 15,
			"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
			
			  "aoColumns": [null,null,null,null,null,null,null,null
		 
		  
		] 
			});
		$("#submitDiv").show();
	}
	else
	{
$('#JqTable').dataTable({
		//"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		
		  "aoColumns": [null,null,null,null,null,null,null
     
	  
    ] 
		});
		
	}

}

function saveDetails(type)
{
var dataArr = [];
	$(".checkboxClass").each(function() {
if($(this).is(':checked')) 
		{
var constituencyId = $(this).closest("tr").find(".constituency").val();
var name = $(this).closest("tr").find(".name").html();

var voterId = $(this).closest("tr").find(".voterId").val();
var gender = $(this).closest("tr").find(".gender").val();
var age = $(this).closest("tr").find(".age").val();
var mobileNo = $(this).closest("tr").find(".mobileNo").val();
			var obj = {
			constituencyId : constituencyId	,
				name : name,
				voterId:voterId,
				gender:gender,
				age:age,
				mobileNo : mobileNo
			}
			dataArr.push(obj);
		}
		});
		if(dataArr.length == 0)
	{
			$("#errorDiv").html("Select atleast one voter").css("color","red");
			$("html, body").animate({scrollTop:200}, 1000);;
		return;
	}
$("#errorDiv").html('');
var jsObj ={
	type:type,
	dataArr : dataArr
}

$.ajax({
          type:'GET',
          url: 'saveVoterTaggedDetailsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			 
			buildStatus(result,jsObj);
			getReport();
	   });
		
}
function buildStatus(result)
{

	if(result.resultCode == 0)
	{
		
		$("#errorDiv").html('<font color="green"><b>Saved Successfully.</b>');
	}
	else
	{
		$("#errorDiv").html('<font color="green"><b>Error Ocuured, Try Again.</b>');
	}
	$("html, body").animate({scrollTop:200}, 1000);;
}
</script>
<script>
getReport();
</script>
</body>
</html>
