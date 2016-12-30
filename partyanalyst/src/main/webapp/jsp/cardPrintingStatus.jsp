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
<title>CARD PRINTING STATUS</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

<style>
.chosen-container{width:100% !important}
.spinner {
  margin:auto;
  width: 40px;
  height: 40px;
  position: relative;
  text-align: center;
  -webkit-animation: sk-chasingDotsRotate 2s infinite linear;
    animation: sk-chasingDotsRotate 2s infinite linear; 
}
.spinner .dot1 , .spinner .dot2 {
    width: 60%;
    height: 60%;
    display: inline-block;
    position: absolute;
    top: 0;
    background-color: #1ABC9C;
    border-radius: 100%;
    -webkit-animation: sk-chasingDotsBounce 2s infinite ease-in-out;
    animation: sk-chasingDotsBounce 2s infinite ease-in-out; 
}
.spinner .dot2 {
    top: auto;
    bottom: 0;
    -webkit-animation-delay: -1s;
    animation-delay: -1s; 
}

@-webkit-keyframes sk-chasingDotsRotate {
  100% {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg); } }

@keyframes sk-chasingDotsRotate {
  100% {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg); } }

@-webkit-keyframes sk-chasingDotsBounce {
  0%, 100% {
    -webkit-transform: scale(0);
            transform: scale(0); }
  50% {
    -webkit-transform: scale(1);
            transform: scale(1); } }

@keyframes sk-chasingDotsBounce {
  0%, 100% {
    -webkit-transform: scale(0);
            transform: scale(0); }
  50% {
    -webkit-transform: scale(1);
            transform: scale(1); } }
</style>

</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default m_top10">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-10 col-xs-12 col-sm-9">
							<h3 class="panel-title m_top10 text-capital">distrcit wise printing status counts</h3>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3">
							<select class="chosen" id="districtStateId">
								<option value="0">All</option>
								<option value="1">AP</option>
								<option value="36">TS</option>
							</select>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div id="districtImgId" style="display:none"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
					<div id="districtBodyDivId"></div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-10 col-xs-12 col-sm-9">
							<h3 class="panel-title m_top10 text-capital">constituency wise printing status counts</h3>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3">
							<select class="chosen" id="constStateId">
								<option value="0">All</option>
								<option value="1">AP</option>
								<option value="36">TS</option>
							</select>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div id="constituencyImgId" style="display:none"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
					<div id="constiBodyDivId"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>

<script type ="text/javascript">
$(document).ready(function(){
	$(".chosen").chosen();
	getDistrictWisePrintingStatusDetails();
	getConstituencyWisePrintingStatusDetails();
});

$(document).on("change","#districtStateId",function(){
	getDistrictWisePrintingStatusDetails();
});

$(document).on("change","#constStateId",function(){
	getConstituencyWisePrintingStatusDetails();
});

function getDistrictWisePrintingStatusDetails(type){
	$("#districtImgId").show();
	$("#districtBodyDivId").html("");
	
	var stateId = $("#districtStateId").val();
	
	var jsObj={
		stateId : stateId,
		type : "district"
	}
	$.ajax({
		url:"cardPrinStatusByLocationAction.action",
		data:"Post",
		dataType:"JSON",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildDistrictWiseDetails(result);
		}
	})
}

function buildDistrictWiseDetails(result){
	var str='';
	
	str+='<table class="table table-bordered" id="districtTableId">';
		str+='<thead>';
			str+='<th>District</th>';
			str+='<th>State</th>';
			str+='<th>Total Records</th>';
			str+='<th>Not Verified</th>';
			str+='<th>Verified</th>';
			str+='<th>Verification Failed</th>';
			str+='<th>Print Given</th>';
			str+='<th>Printed</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].state+'</td>';
				str+='<td>'+result[i].count+'</td>';
				for(var j in result[i].subList){
					str+='<td>'+result[i].subList[j].count+'</td>';
				}
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#districtImgId").hide();
	$("#districtBodyDivId").html(str);
	$("#districtTableId").dataTable();
}

function getConstituencyWisePrintingStatusDetails(type){
	$("#constituencyImgId").show();
	$("#constiBodyDivId").html("");
	
	var stateId = $("#districtStateId").val();
	
	var jsObj={
		stateId : stateId,
		type : "constituency"
	}
	$.ajax({
		url:"cardPrinStatusByLocationAction.action",
		data:"Post",
		dataType:"JSON",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildConstituencyWiseDetails(result);
		}
	})
}

function buildConstituencyWiseDetails(result){
	var str='';
	
	str+='<table class="table table-bordered" id="constTableId">';
		str+='<thead>';
			str+='<th>Constituency</th>';
			str+='<th>District</th>';
			str+='<th>State</th>';
			str+='<th>Total Records</th>';
			str+='<th>Not Verified</th>';
			str+='<th>Verified</th>';
			str+='<th>Verification Failed</th>';
			str+='<th>Print Given</th>';
			str+='<th>Printed</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].superName+'</td>';
				str+='<td>'+result[i].state+'</td>';
				str+='<td>'+result[i].count+'</td>';
				for(var j in result[i].subList){
					str+='<td>'+result[i].subList[j].count+'</td>';
				}
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#constituencyImgId").hide();
	$("#constiBodyDivId").html(str);
	$("#constTableId").dataTable();
}
</script>
</body>
</html>