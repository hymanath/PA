<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Call Center Training Agent </title>
<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="css/Training/css/custom.css" rel="stylesheet" type="text/css"/>
	<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
	<img src="css/Training/img/header.jpg" width="100%">
</header>
<section>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default panel-custom">
					<div class="panel-heading">
						<h4 class="panel-title">CALL CENTER AGENT</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3">
								<table class="table table-bordered">
									<tr>
										<td colspan="2"><div id="donutchart" style="width:100%;height:250px"></div></td>
									</tr>
									<tr>
										<td colspan="2"><h4>ALLOCATED CALLS - 1000</h4></td>
									</tr>
									<tr class="text-warning">
										<td colspan="2">
											<span class="font-30">800</span>
											<span>Dialed Calls</span>
										</td>
									</tr>
									<tr>
										<td class="text-warning">700 Call <br/> Answered</td>
										<td class="text-success">700 Call <br/> Switched Off / Userbusy</td>
									</tr>
									<tr>
										<td colspan="2" class="text-success">600 - Members Interested</td>
									</tr>
									<tr>
										<td colspan="2" class="text-custom">80 - Currently Not Interested</td>
									</tr>
									<tr>
										<td colspan="2" class="text-danger">20 - Totally Not Interested</td>
									</tr>
								</table>
							</div>
							<div class="col-md-9" >
								<div class="table-scroll" id="memberInfoDiv">
									
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
		<div class="modal-content ">
		  <div class="modal-header  bg_d">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">Joseph</h4>
		  </div>
		  <div class="modal-body">
			<div class="row">
				<div class="col-md-12">
					<input type="checkbox" class="checkbox-inline m_0">
					Call Answered
					<input type="checkbox" class="checkbox-inline m_0">
					Switched Off / User busy
				</div>
				<div class="col-md-12 m_top10">
					<label>Member Interested In Training</label>
					<select class="form-control">
						<option>Member Interested</option>
					</select>
				</div>
				<div class="col-md-12 m_top10">
					<label>Select Program Name</label>
					<select class="form-control">
						<option>Leadership Skills</option>
					</select>
				</div>
				<div class="col-md-12 m_top10">
					<label>Select Calendar Batch</label>
					<select class="form-control">
						<option>Sep_2015_1 to 15</option>
					</select>
				</div>
				<div class="col-md-12 m_top10">
					<label>Preferabble Dates</label>
					<select class="form-control">
						<option>Sep_2015_1 to 3</option>
					</select>
				</div>
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		</div>
	  </div>
	</div>

</section>
<footer>
		<img src="css/Training/img/footer.jpg" width="100%">
</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
	<!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
	<script src="css/Training/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/highcharts/js/highcharts_cadre.js"></script>

<script type="text/javascript">
var purposeId = '${purposeId}';
var programId = '${programId}';
var campId = '${campId}';
var scheduleId = '${scheduleId}';
var status = '${status}';
var batchId = '${batchId}';
var statusType = '${statusType}';
$(".table-scroll").mCustomScrollbar({
	setHeight:850,
	theme:"minimal-dark"
});
</script>
<script type="text/javascript">
$(function () {
	Highcharts.setOptions({
        colors: ['#40b5bf', '#999967', '#089bf8', '#ac69ae' , '#cccccc']
    });
    $('#donutchart').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 50
            }
        },
		legend: {
                enabled: true,
                align: 'right',
                verticalAlign: 'right',
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 120,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
            data: [
                ['Interested', 157],
                ['Not Interested', 100],
                ['Not Eligible', 100],
                ['Not Possible', 100],
				['Not Completed', 100],
            ]
        }]
    });
});
function getMemberDetails()
{
alert(batchId)
if(batchId == "")
batchId = 0;
var jObj={
		purposeId : purposeId,
		programId:programId,
		campId:campId,
		scheduleId:scheduleId,
		status:status,
		batchId :batchId,
		statusType:statusType,
		task:"scheduleWiseCount"
		};
		$.ajax({
			  type:'POST',
			  url: 'getScheduleCallMemberDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
			buildScheduleCallMemberDetailsCount(result,jObj);
		   });	
}

function buildScheduleCallMemberDetailsCount(result,jObj)
{
var purpose ;
var callFor ;
if(jObj.purposeId == 1)
purpose = "Invitation";
else if(jObj.purposeId == 2)
purpose = "Confirmation";
else if(jObj.purposeId == 3)
purpose = "Batch Change";
if(jObj.batchId == 0)
callFor = "Schedule";
else
callFor = "Batch";
var str='';
str+='<table class="table table-bordered">';
str+='<thead class="bg_d">';
str+='<th>Image</th>';
str+='<th>Name</th>';
str+='<th>Age</th>';
str+='<th>Committee Roll Position</th>';
str+='<th>Contact Number</th>';
str+='<th>Calling For</th>';
str+='<th>District</th>';
str+='<th>Status</th>';
str+='</thead>';
if(result.subList != null && result.subList .length > 0)
{
for(var i in result.subList)
{
str+='<tr>';
str+='<td><img style="height:50px;width:50px;" class="media-object img-border profile-image img-circle" src="http://mytdp.com/images/cadre_images/'+result.subList[i].image+'" onerror="setDefaultImage(this);" alt="Profile Image"></td>';
str+='<td>'+result.subList[i].name+'</td>';
if(result.subList[i].age == null)
result.subList[i].age ='';
str+='<th>'+result.subList[i].age+'</th>';
if(result.subList[i].role == null)
result.subList[i].role = '';
str+='<td>'+result.subList[i].role+'</td>';
str+='<td>'+result.subList[i].mobileNumber+'</td>';
str+='<td>'+callFor+' '+purpose+'</td>';
str+='<td>'+result.subList[i].location+'</td>';
str+='<td>'+result.subList[i].status+'</td>';
str+='</tr>';
}
}
/*
<tr>
<td>
<input class="checkbox-inline m_0" type="checkbox">
Rajamahendar
</td>
<td>
9630258741
</td>
<td>
<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal">Update Status</button>
</td>
</tr>*/
str+='</table>';
$("#memberInfoDiv").html(str);

}
function setDefaultImage(img){
	  img.src = "dist/img/profileimage.png";
   }
</script>
<script>
getMemberDetails();
</script>
</body>
</html>