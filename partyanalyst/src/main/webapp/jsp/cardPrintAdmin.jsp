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
					<h4 class="panel-title">VALIDATE CONSTITUENCY CADRE DATA</h4>
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
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6 m_top20" id="overAllSummaryDivId"></div>
						<div class="col-md-6 col-xs-12 col-sm-6 m_top20" id="statusWiseSummaryDivId"></div>	
					</div>
					<div class="m_top20" style="padding: 5px;" id="teluguNamesDivId"></div>
					<div class="m_top20" style="padding: 5px;" id="specialCharsDivId"></div>
					<div class="m_top20" style="padding: 5px;" id="imageMissedDivId"></div>
					
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
	$("#detailsImgId").show();
	$("#errorDivId").html("");
	$("#overAllSummaryDivId").html('');
	$("#teluguNamesDivId").html('');
	$("#imageMissedDivId").html('');
	$("#specialCharsDivId").html('');
	$("#statusWiseSummaryDivId").html('');
	
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
		if(result != null){
			if(result.resultStatus != null && result.resultStatus.resultCode == 1){
				buildConstituencyVerifiedSummary(result);
			}
			else if(result.resultStatus != null && result.resultStatus.resultCode == 0){
				$("#detailsImgId").hide();
				$("#overAllSummaryDivId").html('<h4 style="color:red">Exception Occured While Verifying,Please Try Again</h4>');
			}
		}
		else{
			$("#detailsImgId").hide();
			$("#overAllSummaryDivId").html('<h4 style="color:red">Exception Occured While Verifying,Please Try Again</h4>');
		}
	}).fail(function(xhr, err){
           $("#detailsImgId").hide(); 
           $("#overAllSummaryDivId").html('<h4 style="color:red">Exception Occured While Verifying,Please Try Again</h4>');
      })
});

function buildConstituencyVerifiedSummary(result){
	$("#detailsImgId").hide();
	
	var str = '';
	str+='<h4 class="m_top10"><b>CONSTITUENCY VERIFICATION SUMMARY</b></h4>';
	str+='<div class="m_top10">';
		str+='<table class="table table-bordered">';
			str+='<thead>';
				str+='<tr>';
					str+='<th rowspan="2">Total Cadres</th>';
					str+='<th rowspan="2">Previous Verified</th>';
					str+='<th colspan="2">Present Verified ('+result.nowVerifiedCount+')</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th>Approved</th>';
					str+='<th>Rejected</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				str+='<tr>';
					str+='<td>'+result.totalCadreCount+'</td>';
					str+='<td>'+result.beforeVerifiedCount+'</td>';
					str+='<td>'+result.approvedCount+'</td>';
					str+='<td>'+result.rejectedCount+'</td>';
				str+='</tr>';
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#overAllSummaryDivId").html(str);
	
	var str4 = '';
	str4+='<h4 class="m_top10"><b>CARD PRINT STATUS WISE SUMMARY</b></h4>';
	str4+='<div class="m_top10">';
		str4+='<table class="table table-bordered">';
			str4+='<thead>';
				str4+='<th>Total Cadres</th>';
				if(result.statusCountsList != null && result.statusCountsList.length > 0){
					for(var i in result.statusCountsList){
						if(result.statusCountsList[i].firstName == 'Verified')
							str4+='<th> Verification Approved </th>';
						else
							str4+='<th>'+result.statusCountsList[i].firstName+'</th>';
					}
				}
			str4+='</thead>';
			str4+='<tbody>';
				str4+='<tr>';
					str4+='<td>'+result.totalCadreCount+'</td>';
					if(result.statusCountsList != null && result.statusCountsList.length > 0){
						for(var i in result.statusCountsList){
							str4+='<td>'+result.statusCountsList[i].totalCadreCount+'</td>';
						}
					}
				str4+='</tr>';
			str4+='</tbody>';
		str4+='</table>';
	str4+='</div>';
	$("#statusWiseSummaryDivId").html(str4);
	
	var str1 = '';
	str1+='<h4 class="m_top10"><b>TELUGU NAMES MISSED CADRES</b></h4>';
	if(result.teluguNamesMissedList != null && result.teluguNamesMissedList.length > 0){
		str1+='<div class="m_top10">';
			str1+='<table class="table table-bordered" id="teluguNamesTableId">';
				str1+='<thead>';
					str1+='<th>Image</th>';
					str1+='<th>Name</th>';
					str1+='<th>Membership No</th>';
					str1+='<th>Mobile No</th>';
					str1+='<th>Gender</th>';
					str1+='<th>Age</th>';
				str1+='</thead>';
				str1+='<tbody>';
				if(result.teluguNamesMissedList != null && result.teluguNamesMissedList.length > 0){
					for(var i in result.teluguNamesMissedList){
						str1+='<tr>';
							str1+='<td><img src="mytdp.com/images/cadre_images/'+result.teluguNamesMissedList[i].image+'"/></td>';
							str1+='<td>'+result.teluguNamesMissedList[i].firstName+'</td>';
							str1+='<td>'+result.teluguNamesMissedList[i].memberShipId+'</td>';
							str1+='<td>'+result.teluguNamesMissedList[i].mobileNo+'</td>';
							if(result.teluguNamesMissedList[i].gender != null && result.teluguNamesMissedList[i].gender == 'M')
								str1+='<td> Male </td>';
							else if(result.teluguNamesMissedList[i].gender != null && result.teluguNamesMissedList[i].gender == 'F')
								str1+='<td> FeMale </td>';
							else
								str1+='<td>'+result.teluguNamesMissedList[i].gender+'</td>';
							str1+='<td>'+result.teluguNamesMissedList[i].age+'</td>';
						str1+='</tr>';
					}
				}
				str1+='</tbody>';
			str1+='</table>';
		str1+='</div>';
		$("#teluguNamesDivId").html(str1);
		$("#teluguNamesTableId").dataTable();
	}
	else{
		str1+='<h4 class="m_top10" style="color:red"> NO DATA AVAILABLE...</h4>';
		$("#teluguNamesDivId").html(str1);
	}
	
	var str2 = '';
	str2+='<h4 class="m_top10"><b>IMAGE MISSED CADRES</b></h4>';
	if(result.imagesMissedList != null && result.imagesMissedList.length > 0){
		str2+='<div class="m_top10">';
			str2+='<table class="table table-bordered" id="imageTableId">';
				str2+='<thead>';
					str2+='<th>Image</th>';
					str2+='<th>Name</th>';
					str2+='<th>Membership No</th>';
					str2+='<th>Mobile No</th>';
					str2+='<th>Gender</th>';
					str2+='<th>Age</th>';
				str2+='</thead>';
				str2+='<tbody>';
				if(result.imagesMissedList != null && result.imagesMissedList.length > 0){
					for(var i in result.imagesMissedList){
						str2+='<tr>';
							str2+='<td><img src="mytdp.com/images/cadre_images/'+result.imagesMissedList[i].image+'"/></td>';
							str2+='<td>'+result.imagesMissedList[i].firstName+'</td>';
							str2+='<td>'+result.imagesMissedList[i].memberShipId+'</td>';
							str2+='<td>'+result.imagesMissedList[i].mobileNo+'</td>';
							if(result.imagesMissedList[i].gender != null && result.imagesMissedList[i].gender == 'M')
								str2+='<td> Male </td>';
							else if(result.imagesMissedList[i].gender != null && result.imagesMissedList[i].gender == 'F')
								str2+='<td> FeMale </td>';
							else
								str2+='<td>'+result.imagesMissedList[i].gender+'</td>';
							str2+='<td>'+result.imagesMissedList[i].age+'</td>';
						str2+='</tr>';
					}
				}
				str2+='</tbody>';
			str2+='</table>';
		str2+='</div>';
		$("#imageMissedDivId").html(str2);
		$("#imageTableId").dataTable();
	}
	else{
		str2+='<h4 class="m_top10" style="color:red"> NO DATA AVAILABLE...</h4>';
		$("#imageMissedDivId").html(str2);
	}
	
	var str3 = '';
	str3+='<h4 class="m_top10"><b>SPECIAL CHARACTERS IN NAMES CADRES</b></h4>';
	if(result.specialCharactersList != null && result.specialCharactersList.length > 0){
		str3+='<div class="m_top10">';
			str3+='<table class="table table-bordered" id="specialCharTableId">';
				str3+='<thead>';
					str3+='<th>Image</th>';
					str3+='<th>Name</th>';
					str3+='<th>Membership No</th>';
					str3+='<th>Mobile No</th>';
					str3+='<th>Gender</th>';
					str3+='<th>Age</th>';
				str3+='</thead>';
				str3+='<tbody>';
				if(result.specialCharactersList != null && result.specialCharactersList.length > 0){
					for(var i in result.specialCharactersList){
						str3+='<tr>';
							str3+='<td><img src="mytdp.com/images/cadre_images/'+result.specialCharactersList[i].image+'"/></td>';
							str3+='<td>'+result.specialCharactersList[i].firstName+'</td>';
							str3+='<td>'+result.specialCharactersList[i].memberShipId+'</td>';
							str3+='<td>'+result.specialCharactersList[i].mobileNo+'</td>';
							if(result.specialCharactersList[i].gender != null && result.specialCharactersList[i].gender == 'M')
								str3+='<td> Male </td>';
							else if(result.specialCharactersList[i].gender != null && result.specialCharactersList[i].gender == 'F')
								str3+='<td> FeMale </td>';
							else
								str3+='<td>'+result.specialCharactersList[i].gender+'</td>';
							str3+='<td>'+result.specialCharactersList[i].age+'</td>';
						str3+='</tr>';
					}
				}
				str3+='</tbody>';
			str3+='</table>';
		str3+='</div>';
		$("#specialCharsDivId").html(str3);
		$("#specialCharTableId").dataTable();
	}
	else{
		str3+='<h4 class="m_top10" style="color:red"> NO DATA AVAILABLE...</h4>';
		$("#specialCharsDivId").html(str3);
	}
}

</script>
</body>
</html>