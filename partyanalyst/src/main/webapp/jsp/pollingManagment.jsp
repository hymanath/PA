<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> GHMC ELECTIONS - 2016  </title>
<!--<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Daterange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">-->

<link href="dist/activityDashboard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/> 

</head>

<body>
<div class="container">
	<div class="row">
		<div class="text-danger text-center" id="errDivId"></div>
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading bg_cc">
					<h4 class="panel-title"> GHMC ELECTIONS - 2016 
						<span class="pull-right col-md-3" style="margin-top:-8px">
							<div class="input-group inputGroupCustom">
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i>
									<span class="caret"></span>
								</span>
								<input class="form-control" id="Date" type="text" placeholder="Select Date range">
							</div>
						</span>
						<span class="pull-right">
						    <input type="checkbox" name="checkBoxName" id="allUsersId" checked="true" class="usersClsAll" value="All"> ALL &nbsp;&nbsp;</label>
							<input type="checkbox" name="checkBoxName" id="bbbUserId" checked="true" class="usersCls" value="BBB User"> BBB USER &nbsp;&nbsp;</label>
							<input type="checkbox" name="checkBoxName" id="fieldUserId" checked="true" class="usersCls" value="Field User"> FIELD USER &nbsp;&nbsp;</label>
							<input type="checkbox" name="checkBoxName" id="geoUserId" checked="true" class="usersCls" value="Geo User"> GEO USER &nbsp;&nbsp;</label>
							<button class="btn btn-success btn-xs" id="getDetailsId">Get Details</button>
						</span>
					</h4>
				</div>
				<div class="panel-body bg_EF">
					<div id="overAllDetailsDivId"></div>
					<center><img id="dataLoadingsImgForOverAllDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
					<div class="panel panel-default m_top10">
						<div class="panel-heading bg_cc">
							<h4 class="panel-title">
								DIVISION WISE REPORT
							</h4>
						</div>
						<div class="panel-body pad_0">
							<div id="divisionWiseReportDivId"></div>
							<center><img id="dataLoadingsImgForDivisionWiseReport" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</div>
	
</div>

<!--<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Daterange/moment.js" type="text/javascript"></script>
<script src="dist/Daterange/daterangepicker.js" type="text/javascript"></script>-->

<script src="dist/activityDashboard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/activityDashboard/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/js/custom.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/moment.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<script type="text/javascript">
jQuery( document ).ready(function( $ ) {
	$("#Date").daterangepicker({opens:"left"});
	getAccessValues();
	//getTotalDetails(["All"]);
	$("#Date").val('');
});

$(document).on('click','.applyBtn',function(){
	//getAccessValues();
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
	getTotalDetails(userArr);
	getLocationWiseDetails(userArr);
});

var locationIds = [];

function getAccessValues(){
var jsObj={
		type:"ward"
	}
	$.ajax({
	  type:'GET',
	  url: 'getAccessValuesOfUserIdAction.action',
	  data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){
			for(var i in result){
				var locationVal = result[i].districtid;
				locationIds.push(locationVal);
			}
		}
		getTotalDetails(["All"]);
		getLocationWiseDetails(["All"]);
	});
}

function getLocationWiseDetails(usersArr){
	
$("#divisionWiseReportDivId").html("");
$("#dataLoadingsImgForDivisionWiseReport").show();
	
var dates=$('#Date').val();

var fromDateStr="";
var toDateStr="";

if(dates != null && dates.length >0){
	var dateArray=dates.split("-");
	 fromDateStr=dateArray[0].trim();
	 toDateStr=dateArray[1].trim();	
}

var jsObj={
		fromDate:fromDateStr,
		toDate:toDateStr,
		locationIds:locationIds, //[31917,31926]
		locationType:"ward",
		usersArr:usersArr
	}
	$.ajax({
	  type:'GET',
	  url: 'getLocationWiseDetailsAction.action',
	  data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			var str='';
		
			str+='<table class="table table-bordered" id="locationTblId">';
				str+='<thead class="bg_F0 font-12">';
					str+='<tr>';
						str+='<th rowspan="2">DIVISION<br/> NO</th>';
						str+='<th rowspan="2">DIVISION<br/> NAME</th>';
						if(dates.trim().length>0){
							if(fromDateStr.trim() != toDateStr.trim()){
							  str+='<th rowspan="2">DATE</th>';
						   }
						}else{
							str+='<th rowspan="2">DATE</th>';
						}
							
						str+='<th rowspan="2">NO OF USERS <br/>LOGGED IN</th>';
						str+='<th colspan="3">NO OF VOTER ID CAPTURED</th>';
						str+='<th rowspan="2">NO OF MOBILES <br/>COLLECTED</th>';
						str+='<th colspan="5">';
						str+='<p class="text-center">';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='RATINGS';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='<i class="glyphicon glyphicon-star"></i>';
						str+='</p>';
					str+='</th>';
					str+='</tr>';
					str+='<tr>';
						str+='<th>TOTAL</th>';
						str+='<th>PUBLIC</th>';
						str+='<th>CADRE</th>';
						str+='<th>None</th>';
						str+='<th>01</th>';
						str+='<th>02</th>';
						str+='<th>03</th>';
						str+='<th>04</th>';
						str+='<th>05</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
					   
						
						if(result[i].dateList != null){
							for(var j in result[i].dateList){
							  
							  str+='<td> <a href="mobileAppDivisionWiseUsersAction.action?divisionId='+result[i].wardId+'&division='+result[i].divisionName+'&fromDate='+result[i].dateList[j].formatDate+'&toDate='+result[i].dateList[j].formatDate+'&divisonId='+result[i].divisionNo+'" target="_blank"> '+result[i].divisionNo+'</a></td>';
						
						      str+='<td>'+result[i].divisionName+'</td>';
						      if(dates.trim().length>0){
								 if(fromDateStr.trim() != toDateStr.trim()){
									str+='<td>'+result[i].dateList[j].dateString+'</td>';
								 }
							  }else{
								 str+='<td>'+result[i].dateList[j].dateString+'</td>'; 
							  }
									str+='<td>'+result[i].dateList[j].usersCount+'</td>';
									str+='<td>'+result[i].dateList[j].voterscount+'</td>';
									str+='<td>'+result[i].dateList[j].publicCount+'</td>';
									str+='<td>'+result[i].dateList[j].tdpCadreCount+'</td>';
									str+='<td>'+result[i].dateList[j].mobilescount+'</td>';
									for(var k in result[i].dateList[j].ratingList){
										str+='<td>'+result[i].dateList[j].ratingList[k].ratingCount+'</td>';
									}
								str+='</tr>';
							}
						}
				}
				str+='</tbody>';
			str+='</table>';
			
			$("#dataLoadingsImgForDivisionWiseReport").hide();
			$("#divisionWiseReportDivId").html(str);	
			$("#locationTblId").dataTable({	 "aaSorting": [] });
			$("#locationTblId").removeClass("dataTable");
		}
		else{
			$("#dataLoadingsImgForDivisionWiseReport").hide();
			$("#divisionWiseReportDivId").html("NO DATA AVAILABLE...");
		}
	});
}

function getTotalDetails(usersArr){

	$("#overAllDetailsDivId").html("");
	$("#dataLoadingsImgForOverAllDetails").show();
	
	var fromDateStr="";
	var toDateStr="";
	
	var dates=$('#Date').val();
	if(dates != null && dates.length >0){
		var dateArray=dates.split("-");
		fromDateStr=dateArray[0].trim();
		toDateStr=dateArray[1].trim();
	}
	
	var jsObj={
		fromDate:fromDateStr,
		toDate:toDateStr,
		locationIds:locationIds, 
		usersArr:usersArr
	}
	$.ajax({
	  type:'GET',
	  url: 'getTotalDetailsAction.action',
	  data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){
			var str='';
			
			str+='<table class="table table-bordered tableVM bg_ff">';
				str+='<tr>';
					str+='<td rowspan="3">';
						str+='<h3 class="text-center">'+result.wardsCount+'</h3>';
						str+='<h5 class="text-center">TOTAL DIVISIONS</h5>';
					str+='</td>';
					str+='<td rowspan="3">';
						str+='<h3 class="text-center">'+result.usersCount+'</h3>';
						str+='<h5 class="text-center">TOTAL LOGGED IN USERS</h5>';
					str+='</td>';
					str+='<td rowspan="3">';
						str+='<h3 class="text-center">'+result.voterscount+'</h3>';
						str+='<h5 class="text-center">TOTAL VOTER ID CAPTURED</h5>';
					str+='</td>';
					str+='<td rowspan="3">';
						str+='<h3 class="text-center">'+result.mobilescount+'</h3>';
						str+='<h5 class="text-center">TOTAL MOBILE NUMBERS COLLECTED</h5>';
					str+='</td>';
					str+='<td colspan="10">';
						str+='<p class="text-center">';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='RATINGS';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='<i class="glyphicon glyphicon-star"></i>';
							str+='<i class="glyphicon glyphicon-star"></i>';
						str+='</p>';
					str+='</td>';
				str+='</tr>';
				str+='<tr>';
				    str+='<th>None</th>';
					str+='<td>01</td>';
					str+='<td>02</td>';
					str+='<td>03</td>';
					str+='<td>04</td>';
					str+='<td>05</td>';
				str+='</tr>';
				str+='<tr>';
				    for(var x in result.ratingList){
						str+='<td>'+result.ratingList[x].ratingCount+'</td>';
					}
				str+='</tr>';
			str+='</table>';
			
			$("#dataLoadingsImgForOverAllDetails").hide();
			$("#overAllDetailsDivId").html(str);
		}
		else{
			$("#dataLoadingsImgForOverAllDetails").hide();
			$("#overAllDetailsDivId").html("NO DATA AVAILABLE...");
		}
	});
}
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
	getTotalDetails(userArr);
	getLocationWiseDetails(userArr);
});


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


</script>
</body>
</html>