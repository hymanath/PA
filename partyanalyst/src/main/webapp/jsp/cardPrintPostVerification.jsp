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
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

<style>
.m_top20
{
	margin-top: 20px;
}
.spinner {
  margin: 30px auto;
  width: 40px;
  height: 40px;
  position: relative;
  text-align: center;
  
  -webkit-animation: sk-rotate 2.0s infinite linear;
  animation: sk-rotate 2.0s infinite linear;
}

.dot1, .dot2 {
  width: 60%;
  height: 60%;
  display: inline-block;
  position: absolute;
  top: 0;
  background-color: #1ABC9C;
  border-radius: 100%;
  
  -webkit-animation: sk-bounce 2.0s infinite ease-in-out;
  animation: sk-bounce 2.0s infinite ease-in-out;
}

.dot2 {
  top: auto;
  bottom: 0;
  -webkit-animation-delay: -1.0s;
  animation-delay: -1.0s;
}

@-webkit-keyframes sk-rotate { 100% { -webkit-transform: rotate(360deg) }}
@keyframes sk-rotate { 100% { transform: rotate(360deg); -webkit-transform: rotate(360deg) }}

@-webkit-keyframes sk-bounce {
  0%, 100% { -webkit-transform: scale(0.0) }
  50% { -webkit-transform: scale(1.0) }
}

@keyframes sk-bounce {
  0%, 100% { 
    transform: scale(0.0);
    -webkit-transform: scale(0.0);
  } 50% { 
    transform: scale(1.0);
    -webkit-transform: scale(1.0);
  }
}

</style>

</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default m_top10">
				<div class="panel-heading">
					<h4 class="panel-title"><b>VALIDATE CONSTITUENCY CADRE DATA</b></h4>
				</div>
				<div class="panel-body">
					<div id="errorDivId" style="color:red"></div>
					<div class="row">
						<div class="col-md-5 col-xs-12 col-sm-5" style="margin-left: 10px;">
							<label> SELECT CONSTITUENCY: </label>
							<select id="constituencyId" class="chosenSelect">
								<option value="0">Select Constituency</option>
							</select>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
							<button class="btn btn-info" id="validateBtnId">VALIDATE</button>
						</div>
					</div>
					
					<div id="detailsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
					<div id="postValidationFailCadreDetails" class="m_top20"></div>
					<div id="postValidationFailCadreExcelReport" style="display:none;"></div>
					
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
	$(".chosenSelect").chosen({width:'200px'});
	getPrintPushedConstituencies();
});
function getPrintPushedConstituencies(){
	
	var jsObj ={}
	$.ajax({
		type:"post",
		url:"getPrintPushedConstituenciesAction.action",
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
	$("#postValidationFailCadreDetails").html('');
	var constId = $("#constituencyId").val();
	if(constId == 0){
		$("#errorDivId").html("Select Constituency");
		return;
	}
	$("#detailsImgId").show();
	var jsObj ={
		constituencyId : constId
	}
	$.ajax({
		type:"post",
		url:"cadrePrintDataPostVerificationAction.action",
		dataType:"json",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		$("#detailsImgId").hide();
		buildcadrePrintDataPostVerification(result);
	}).fail(function(xhr, err){
          alert("fail");
    })
});

	function buildcadrePrintDataPostVerification(result){
		$("#postValidationFailCadreDetails").html('');
		if(result != null && result.length >0){
			var str='';
			var str1='';
			str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading">';
				  str+='<div class="row">';
					  str+='<div class="col-md-6 col-xs-12 col-sm-2">';
						 str+=' <h3 class="panel-title"><b>POST VALIDATION FAIL CADRE</b></h3>';
					   str+='</div>';
					    str+='<div class="col-md-2 col-xs-12 col-sm-2 pull-right">';
							str+='<button class="btn btn-success btn-xs" id="PostValidationExcelReport" >Export To Excel</button></h4>';
						str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
						  str+='<div class="table-responsive">';
							  str+='<table class="table table-striped postValidationDataTable" >';
								str+='<thead>';
									str+='<tr>';
										str+='<th></th>';
									str+='</tr>';
								str+='</thead>';
								str+='<tbody>';
								for(var i in result){
									 str+='<tr>';
										str+='<td><h4  style="font-family: Roboto; line-height: 25px;">'+result[i]+'</h4></td>';
										str+='</tr>';
								}
								str+='</tbody>';
							  str+='</table>';
							  
							  //For Fectching all Records in export excel
							   str1+='<table class="table table-striped" id="PostValidationExcelDivId">';
								str1+='<thead>';
									str1+='<tr>';
										str1+='<th></th>';
									str1+='</tr>';
								str1+='</thead>';
								str1+='<tbody>';
								for(var i in result){
									 str1+='<tr>';
										str1+='<td><h4  style="font-family: Roboto; line-height: 25px;">'+result[i]+'</h4></td>';
										str1+='</tr>';
								}
								str1+='</tbody>';
							  str1+='</table>';
							  
						  str+='</div>';
				  str+='</div>';
				str+='</div>';
			$("#postValidationFailCadreDetails").html(str);
			$("#postValidationFailCadreExcelReport").html(str1);
			//$(".postValidationDataTable").dataTable();
			$(".postValidationDataTable").dataTable({
				//"aaSorting": [[ 1, "desc" ]], 
				"iDisplayLength" : 20,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
			});
			$(".postValidationDataTable").removeClass("dataTable");
		}
	}

	$(document).on("click","#PostValidationExcelReport",function(){
		tableToExcel(PostValidationExcelDivId, "POST VALIDATION FAIL CADRE");   
	});
	
	
</script>
<script>
var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()	
</script>
</body>
</html>