<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> DIVISION WISE USERS </title>
<link href="dist/mobileApp/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/mobileApp/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/mobileApp/Daterange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/> 

<link href="dist/activityDashboard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">


</head>
<body>
<style>
	.table th{text-align:center !important;cursor:pointer;}
	.font-12{font-size:12px;}
</style>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading bg_cc">
					<h4 class="panel-title" style="text-transform: uppercase;" >${param.division} - (${param.divisonId}) DIVISION REPORT
						<span class="pull-right col-md-3" style="margin-top:-8px">
							<div class="input-group inputGroupCustom">
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i>
									<span class="caret"></span>
								</span>
								<input class="form-control" id="Date" type="text" placeholder="Select Date range">
							</div>
						</span>
						<label class="pull-right font-12"><input type="radio" name="userTypeRadio" value="BBB User" onclick="getUsersSummary(2)" checked>BBB User</input></label>
						<label class="pull-right font-12"><input type="radio" name="userTypeRadio" value="Field User" onclick="getUsersSummary(2)" >Field User</input></label>
						<label class="pull-right font-12"><input type="radio" name="userTypeRadio" value="Geo User" onclick="getUsersSummary(2)" >Geo User</input></label>
					</h4>
				</div>
				<div class="panel-body bg_EF">
					<table class="table table-bordered tableVM bg_ff">
						<tr>
							<td rowspan="3">
								<h3 id="ttlUsrs">-</h3>
								<h5>TOTAL USERS</h5>
							</td>
							<td rowspan="3">
								<h3 id="ttlVtrs">-</h3>
								<h5>TOTAL VOTERS</h5>
							</td>
							<td rowspan="3">
								<h3 id="ttlVtrsCaptrd">-</h3>
								<h5>TOTAL VOTER ID CAPTURED</h5>
							</td>
							<td rowspan="3">
								<h3 id="ttlMblCaptrd">-</h3>
								<h5>TOTAL MOBILE NUMBERS COLLECTED</h5>
							</td>
							<td colspan="10">
								<p class="text-center">
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									RATING
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
								</p>
							</td>
						</tr>
						<tr>
							<td>None</td>
							<td>01</td>
							<td>02</td>
							<td>03</td>
							<td>04</td>
							<td>05</td>
						</tr>
						<tr id="mainRtng"></tr>
					</table>
				</div>
				<div class="panel-body bg_EF">
					<table class="table table-bordered tableVM bg_ff" id="usrSmmryTbl">
						<thead>
							<tr>
								<th rowspan=2>NAME</th>
								<th rowspan=2>MOBILE NO</th>
								<th rowspan=2>DATE</th>
								<th rowspan=2>VOTER ID'S CAPTURED</th>
								<th rowspan=2>MOBILE NO'S CAPTURED</th>
								<th colspan=6>RATINGS</th>
							</tr>
							<tr>
								<th>NONE</th>
								<th>1</th>
								<th>2</th>
								<th>3</th>
								<th>4</th>
								<th>5</th>
							</tr>
						</thead>
						<tbody id="usrRtng"></tbody>
					</table>
				</div>
			</div>	
		</div>
	</div>
	
</div>
<script src="dist/mobileApp/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/mobileApp/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/mobileApp/Daterange/moment.js" type="text/javascript"></script>
<script src="dist/mobileApp/Daterange/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<script type="text/javascript">
$("#Date").daterangepicker({opens:"left"});
jQuery( document ).ready(function( $ ) {
	$("#Date").daterangepicker({opens:"left"});
	$("#Date").val('');
});

$(document).on('click','.applyBtn',function(){
	getUsersSummary(2);
});
getUsersSummary(1);
//getUsersSummary('${param.divisionId}','${param.fromDate}','${param.toDate}');
function getUsersSummary(searchTypeId){
	
	var locationId = '';
	var locationType = "ward";
	var startDate = '';
	var endDate = '';
	$("#ttlUsrs").html("");		
	$("#ttlVtrs").html("");
	$("#ttlVtrsCaptrd").html("");
	$("#ttlMblCaptrd").html("");
	
	$('#ttlVtrs').html('<center><img id="dataLoadingsImgForDivisionWiseReport" src="images/icons/loading.gif" style="width:50px;height:50px;"/></center>');
	$('#usrRtng').html('<center><img id="dataLoadingsImgForDivisionWiseReport" src="images/icons/loading.gif" style="width:50px;height:50px;"/></center>');
	$('#ttlVtrsCaptrd').html('<center><img id="dataLoadingsImgForDivisionWiseReport" src="images/icons/loading.gif" style="width:50px;height:50px;"/></center>');
	$('#ttlMblCaptrd').html('<center><img id="dataLoadingsImgForDivisionWiseReport" src="images/icons/loading.gif" style="width:50px;height:50px;"/></center>');
	$('#ttlUsrs').html('<center><img id="dataLoadingsImgForDivisionWiseReport" src="images/icons/loading.gif" style="width:50px;height:50px;"/></center>');
	$('#mainRtng').html('<center><img id="dataLoadingsImgForDivisionWiseReport" src="images/icons/loading.gif" style="width:50px;height:50px;"/></center>');
	
	if(searchTypeId == 1){
		locationId = '${param.divisionId}';
		locationType = "ward";
		startDate = '${param.fromDate}';
		endDate = '${param.toDate}';
		//$("#Date").attr("value",startDate+'-'+endDate);
		//var date1 = $("#Date").val();
		//console.log(date1);
	}
	else if(searchTypeId == 2){
		locationId = '${param.divisionId}';
		locationType = "ward";
		
		var dates=$('#Date').val();
		if(dates != null && dates.length >0){
			var dateArray=dates.split("-");
			startDate=dateArray[0].trim();
			endDate=dateArray[1].trim();	
		}
	}
	var userType = $("input[name='userTypeRadio']:checked").val();
	var jsObj = {
		locationId : locationId,
		locationType:locationType,
		startDate:startDate,
		endDate:endDate,
		userType : userType
	};
	$.ajax({
		type : "GET",
		url : "getUserWiseDivisionSummaryAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildSummary(result);
	});
}
	function buildSummary(result){
		$("#ttlUsrs").html(result.usersCount);		
		$("#ttlVtrs").html(result.totalVoters);
		$("#ttlVtrsCaptrd").html(result.voterIdsCollected);
		$("#ttlMblCaptrd").html(result.noOfMobiles);
		
		var str = "";
		for(var i in result.ratings){
			str+="<td>"+result.ratings[i].ratingCount+"</td>";
		}
		$("#mainRtng").html(str);
		
		
		var str1 = "";
		for(var i in result.userRslt){
			str1+="<tr class='bg_FFF'>";
			str1+="<td class='openTab' attr_divisonId="+'${param.divisionId}'+" attr_surveydate="+result.userRslt[i].date+" attr_userId="+result.userRslt[i].mobileAppUserId+" style='cursor:pointer;'><a href='javascript:{}' style='color:green;font-weight:bold;'>"+result.userRslt[i].name+"</a></td>";
			str1+="<td>"+result.userRslt[i].mobileNo+"</td>";
			str1+="<td>"+result.userRslt[i].date+"</td>";
			str1+="<td>"+result.userRslt[i].voterIdsCollected+"</td>";
			str1+="<td>"+result.userRslt[i].noOfMobiles+"</td>";
			for(var j in result.userRslt[i].ratings){
				str1+="<td>"+result.userRslt[i].ratings[j].ratingCount+"</td>";
			}
			str1+="</tr>";
		}
		
		$("#usrRtng").html(str1);
		$("#usrSmmryTbl").dataTable();
		$("#usrSmmryTbl").removeClass("dataTable");
	}


	$(document).on("click",".openTab",function(){
		window.open("showGoogleMapDetails.action?userId="+$(this).attr("attr_userId")+"&divisonId="+$(this).attr("attr_divisonId")+"&surveyDate="+$(this).attr("attr_surveydate"), "new window", "scrollbars=1,height=900,width=1300");
	});
</script>
</body>
</html>