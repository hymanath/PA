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
<title> VOTER SLIP DASHBOARD </title>
<link href="dist/mobileApp/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/mobileApp/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/mobileApp/Daterange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/> 


<link href="dist/activityDashboard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">


</head>
<body>
<style type="text/css">
	.table th{text-align:center !important;cursor:pointer;}
	.font-12{font-size:12px;}
	#dk0-combobox{border-bottom:1px solid rgba(0,0,0,0.5);background-color:transparent}
	.dk-select-open-up .dk-selected::before, .dk-select-open-down .dk-selected::before,.dk-selected:hover::before, .dk-selected:focus::before,.dk-selected::before{border-color:#333 transparent transparent}
	.dk-select-open-up .dk-selected::before, .dk-select-open-down .dk-selected::before{border-bottom-color:#333 !important}
	
</style>
<script>
var locationArr = [];
var locatinId = '${divisionId}';

    <c:forEach items="${idNamevoList1}" var="current">
		var obj ={
			id:'${current.locationId}',
			name: '${current.locationName}'
		}
		locationArr.push(obj);
    </c:forEach>
	
</script>
<div class="container">
	<div class="row">
		<div class="text-danger text-center" id="errDivId"></div>
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading bg_cc" style="padding:5px 15px;">
					<div class="panel-title">
						<div class="row">
							<div class="col-md-4" style="margin-left:57px;">
								<!--<s:select theme="simple" cssClass="" id="divisionList" list="idNamevoList1" listKey="locationId" listValue="locationName" headerKey="0" headerValue=" Select Division "/>-->
								<select name='role' id="divisionList" onchange="getDetails();">									
								</select>
							</div>
							<div class="col-md-5" style="margin-top:5px;display:none;">
								<label class="font-12"><input type="checkbox" id="allUsersId" checked="true" class="usersClsAll" value="All"> All &nbsp;&nbsp;</label>
								<label class="font-12"><input type="checkbox" id="bbbUserId" checked="true" class="usersCls" value="BBB User"> BBB User &nbsp;&nbsp;</label>
								<label class="font-12"><input type="checkbox" id="fieldUserId" checked="true" class="usersCls" value="Field User"> Field User &nbsp;&nbsp;</label>
								<label class="font-12"><input type="checkbox" id="geoUserId" checked="true" class="usersCls" value="Geo User"> Geo User &nbsp;&nbsp;</label>
								<button class="btn btn-success btn-xs" id="getDetailsId">Get Details</button>
							</div>
							<div class="col-md-3" style="margin-left:410px;">
								<div class="input-group inputGroupCustom">
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i>
										<span class="caret"></span>
									</span>
									<input class="form-control" id="Date" type="text" placeholder="Select Date range">
								</div>
							</div>
						</div>
					</div>
					<!--<h4 class="panel-title" style="text-transform: uppercase;" >${param.division} - (${param.divisonId}) DIVISION REPORT
						<span class="pull-right col-md-3" style="margin-top:-8px">
							
						</span>
						<span class="pull-right font-12">
							
						    <input type="checkbox" id="allUsersId"  class="usersClsAll" value="All"> ALL  &nbsp;&nbsp;</label>
							<input type="checkbox" id="bbbUserId" class="usersCls" value="BBB User"> BBB USER &nbsp;&nbsp;</label>
							<input type="checkbox" id="fieldUserId" class="usersCls" value="Field User"> FIELD USER &nbsp;&nbsp;</label>
							<input type="checkbox" id="geoUserId" class="usersCls" value="Geo User"> GEO USER &nbsp;&nbsp;</label>
							
							<button class="btn btn-success btn-xs" id="getDetailsId">Get Details</button>
						</span>
						<!--<label class="pull-right font-12"><input type="radio" name="userTypeRadio" value="BBB User" onclick="getUsersSummary(2)" checked>BBB User</input></label>
						<label class="pull-right font-12"><input type="radio" name="userTypeRadio" value="Field User" onclick="getUsersSummary(2)" >Field User</input></label>
						<label class="pull-right font-12"><input type="radio" name="userTypeRadio" value="Geo User" onclick="getUsersSummary(2)" >Geo User</input></label>
					</h4>-->
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
								<th rowspan=2>USER ID</th>
								<th rowspan=2>USERNAME</th>
								<th rowspan=2>MOBILE NO</th>
								<th rowspan=2>START TIME</th>
								<th rowspan=2>END TIME</th>
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
var accessValue = '${sessionScope.USER.accessValue}';
$("#Date").daterangepicker({opens:"left"});
jQuery( document ).ready(function( $ ) {
	$("#Date").daterangepicker({opens:"left"});
	$("#Date").val('');
	buildAccessValues();
	//$("#divisionList").val(${param.divisionId});
});

$(document).on('click','.applyBtn',function(){
	getUsersSummary(["All"]);
});
getUsersSummary(["All"]);
//getUsersSummary('${param.divisionId}','${param.fromDate}','${param.toDate}');

function buildAccessValues(){
	if(locationArr != null && locationArr.length>0)
	{
		$('#divisionList').append('<option value="0">ALL</option>');
		for(var i in locationArr)
		{
			if(locationArr[i].id == locatinId)
				$('#divisionList').append('<option value="'+locationArr[i].id+'" selected="selected">'+locationArr[i].name+'</option>');
			else
				$('#divisionList').append('<option value="'+locationArr[i].id+'">'+locationArr[i].name+'</option>');
		}
	}
	$("#divisionList").dropkick();
}

/* function getUsersSummary(searchTypeId,userArr){
	
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
	
	alert(searchTypeId)
	alert(${param.divisionId})
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
		//locationId = '${param.divisionId}';
		locationId = $("#divisionList").val();
		locationType = "ward";
		
		var dates=$('#Date').val();
		if(dates != null && dates.length >0){
			var dateArray=dates.split("-");
			startDate=dateArray[0].trim();
			endDate=dateArray[1].trim();	
		}
		else{
			startDate = '${param.fromDate}';
			endDate = '${param.toDate}';
		}
	}
	//var userType = $("input[name='userTypeRadio']:checked").val();
	var jsObj = {
		locationId : locationId,
		locationType:locationType,
		startDate:startDate,
		endDate:endDate,
		usersArr : userArr
	};
	$.ajax({
		type : "GET",
		url : "getUserWiseDivisionSummaryAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildSummary(result);
	});
} */
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
			str1+="<td class='openTab' attr_divisonId="+locId+" attr_surveydate="+result.userRslt[i].date+" attr_userId="+result.userRslt[i].mobileAppUserId+" attr_levelId="+lvlId+" style='cursor:pointer;'><a href='javascript:{}' style='color:green;font-weight:bold;'>"+result.userRslt[i].name+"</a></td>";
			if(result.userRslt[i].userName.trim().length > 0){
				str1+="<td>"+result.userRslt[i].userName+"</td>";
			}
			else{
				str1+="<td class='text-center'> - </td>";
			}
			str1+="<td>"+result.userRslt[i].mobileNo+"</td>";
			str1+="<td>"+result.userRslt[i].startTime+"</td>";
			str1+="<td>"+result.userRslt[i].endtime+"</td>";
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
		window.open("showGoogleMapDetails.action?userId="+$(this).attr("attr_userId")+"&divisonId="+$(this).attr("attr_divisonId")+"&surveyDate="+$(this).attr("attr_surveydate")+"&levelIdId="+$(this).attr("attr_levelId"), "new window", "scrollbars=1,height=900,width=1300");
	}); 
	
	var userArr = [];
$(document).on('click','#getDetailsId',function(){
	
	$("#errDivId").html("");
	userArr = [];
	var index = 0;
	$(".usersCls").each(function(){
		var id = $(this).attr("id");
		var checked = document.getElementById(id).checked;
		if(checked){
			index = 1;
			var value = $(this).val();
			userArr.push(value);
		}
	});
	
	if(index == 0){
		$("#errDivId").html("Please Select Atleast One User");
		return;
	}
	getUsersSummary(userArr);
});

function getDetails(){
	$("#errDivId").html("");
	userArr = [];
	var index = 0;
	$(".usersCls").each(function(){
		var id = $(this).attr("id");
		var checked = document.getElementById(id).checked;
		if(checked){
			index = 1;
			var value = $(this).val();
			userArr.push(value);
		}
	});
	
	if(index == 0){ 
		$("#errDivId").html("Please Select Atleast One User");
		return;
	}
	getUsersSummary(userArr);
}


$("#allUsersId").change(function () {
    $("input:checkbox").prop('checked', $(this).prop("checked"));
	//alert($(this).prop("checked"));
});

$(".usersCls").change(function () {   
	var isAllChecked=true;
	   $(".usersCls").each(function(){
		   //console.log($(this).is(":checked"));	 
		   var isChecked = $(this).is(":checked");
			if(!isChecked){
				isAllChecked=false;
				$('#allUsersId').prop('checked',false);
			}
		
	   });
	if(isAllChecked)
		 $("input:checkbox").prop('checked', $(this).prop("checked"));
});
var locId = accessValue;
var lvlId = 4;
function getUsersSummary(userArr){
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
	
	var startDate;
	var endDate;
	var locationId = $("#divisionList").val();
	
	if(locationId == 0 || locationId == null){
		locId= accessValue;
		lvlId = 4;
	}else{
		locationId = $("#divisionList").val();
		var subStrValue = locationId.substr(0,1);
		if(subStrValue != null){
			if(subStrValue == 1){
				locId = locationId.substr(1);
				lvlId = 7;
			}else if(subStrValue == 2){
				locId = locationId.substr(1);
				lvlId = 5;
			}
		}
		
	}
	var dates=$('#Date').val();
	if(dates != null && dates.length >0){
		var dateArray=dates.split("-");
		startDate=dateArray[0].trim();
		endDate=dateArray[1].trim();	
	}
	var jsObj = {
		locationId : locId,
		levelId:lvlId,
		startDate:startDate,//"01/01/2017",//startDate,
		endDate:endDate,//"08/01/2017",//endDate,//"08/17/2020",
		publicationDateId : 25,
		electionYearId : 3,
		usersArr : userArr
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
</script>
</body>
</html>