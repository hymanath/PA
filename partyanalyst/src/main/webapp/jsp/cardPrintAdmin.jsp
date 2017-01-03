<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CARDS PRINTING ADMIN DASHBOARD</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

<style>
.m_top20
{
	margin-top: 20px;
}
</style>

</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default m_top10">
				<div class="panel-heading">
					<h4 class="panel-title">CARDS PRINTING ADMIN DASHBOARD</h4>
				</div>
				<div class="panel-body">
					<div id="errorDivId" style="color:red"></div>
					<div class="row">
						<div class="col-md-4 col-xs-12 col-sm-4" style="margin-left: 10px;">
							<label>CONSTITUENCY:</label>
							<select id="constituencyId" class="chosenSelect">
								<option value="0">Select Constituency</option>
							</select>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
							<button class="btn btn-info" id="validateBtnId">VALIDATE</button>
						</div>
					</div>
					
					<div class="m_top20">
						<h4>OVERALL CONSTITUENCY VERIFIED SUMMARY</h4>
						<div class="m_top10">
							<table class="table table-bordered">
								<thead>
									<th>Total Records</th>
									<th>Previous Verified</th>
									<th>Present Verified</th>
									<th>Approved</th>
									<th>Rejected</th>
								</thead>
								<tbody>
									<tr>
										<td>10000</td>
										<td>5000</td>
										<td>4000</td>
										<td>3000</td>
										<td>1000</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="m_top20" style="padding: 5px;">
						<h4>TELUGU NAMES MISSED MEMBERS DETAILS</h4>
						<div class="m_top10">
							<table class="table table-bordered">
								<thead>
									<th>Image</th>
									<th>Name</th>
									<th>Membership No</th>
									<th>Mobile No</th>
									<th>Gender</th>
									<th>Age</th>
								</thead>
								<tbody>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="m_top20" style="padding: 5px;">
						<h4>SPECIAL CHARACTERS MEMBERS DETAILS</h4>
						<div class="m_top10">
							<table class="table table-bordered">
								<thead>
									<th>Image</th>
									<th>Name</th>
									<th>Membership No</th>
									<th>Mobile No</th>
									<th>Gender</th>
									<th>Age</th>
								</thead>
								<tbody>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="m_top20" style="padding: 5px;">
						<h4>IMAGES MISSED MEMBERS DETAILS</h4>
						<div class="m_top10">
							<table class="table table-bordered">
								<thead>
									<th>Image</th>
									<th>Name</th>
									<th>Membership No</th>
									<th>Mobile No</th>
									<th>Gender</th>
									<th>Age</th>
								</thead>
								<tbody>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
									<tr>
										<td></td>
										<td>Sravanth</td>
										<td>99999999</td>
										<td>9000200014</td>
										<td>Male</td>
										<td>24</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>

<script type ="text/javascript">

$(document).ready(function(){
	$(".chosenSelect").chosen({width:'200px'});
	getConstituencyDetailsList();
});
function getConstituencyDetailsList(){
	
	var jsObj ={}
	$.ajax({
		type:"post",
		url:"getConstituencyDetailsListAction.action",
		dataType:"json",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length>0){
			for(var i in result){
				$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#constituencyId").trigger('chosen:updated');
	});
}

$(document).on("click","#validateBtnId",function(){
	$("#errorDivId").html("");
	
	var constId = $("#constituencyId").val();
	if(constId == 0){
		$("#errorDivId").html("Select Constituency");
		return;
	}
	
	var jsObj ={
		constituencyId : constId
	}
	$.ajax({
		type:"post",
		url:"getConstNotVerfiedCardPrintStatusCadreAndValidateAction.action",
		dataType:"json",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		
	});
});

</script>
</body>
</html>