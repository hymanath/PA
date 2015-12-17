
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Training Center</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<style>
header.eventsheader { 
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px; 
}
footer{background-color:#5c2d25;color:#ccc;padding:30px}
</style>
<body>
<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:27px;"></p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
            </div>			
        </div>       
    </div>	
</header>
<main>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default panel-custom">
					<div class="panel-heading">
						<h4 class="panel-title"><b>FEEDBACK</b></h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4">
								<label>Select Training Program</label>
								<select class="form-control" id="selectProgramId" onchange="feedbackCategoryCountsCenterWise();">
									<option value="0">Select Program</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default m_top20" id="centerWiseCategoryMainDivId" style="">
									<div class="panel-heading">
										<h4 class="panel-title" id="programNameTitleId"></h4>
									</div>
									<div class="panel-body pad_0">
										<div class="table-responsive bg_ff" id="centerWiseCategoryDivId">
										</div>
									</div>
								</div>
								<center><img id="centerWiseCategoryAjaxImage" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:45px;display:none;margin-top:20px"/></center>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label>Select District</label>
								<select class="form-control" id="selectDistrictId">
									<option value="0">All</option>
								</select>
							</div>
							<div class="col-md-3">
								<label>Select Constituency</label>
								<select class="form-control" id="selectConstituencyId">
									<option value="0">All</option>
								</select>
							</div>
							<div class="col-md-3">
								<label>Select Category</label>
								<select class="form-control" id="selectCategoryId">
									<option value="0">All</option>
								</select>
							</div>
							<div class="col-md-3">
								<button class="m_top20 btn btn-success btn-sm" onclick="getFeedbackDetailsOfEachDistrictAndConstituencyWise();">SUBMIT</button>
							</div> 
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table-responsive m_top20" id="distConstCatWiseCounts">
								</div>
								<center><img id="distConstCatWiseCountsAjaxImage" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:45px;display:none;margin-top:20px"/></center>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" id="feedBackDetailsDivId" style="display:none;">
								<div class="panel panel-default m_top20">
									<div class="panel-heading">
										<h4 class="panel-title">FEEDBACK FULL DETAILS<button class="pull-right btn-xs btn btn-success">EXPORT TO PDF</button></h4>
									</div>
									<div class="panel-body pad_0">
										<div class="table-responsive" id="cadreDetailsDivId">
										</div>
										<center><img id="cadreDetailsAjaxImage" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:45px;display:none;margin-top:20px"/></center>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<footer>
		<p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

<script>
$(function () {
	$("#mainheading").html("TRAINING CAMP CADRE FEEDBACK REPORT");
	getAllPrograms();
	getAllDistrictsByState(1);
	getAllCategories();
});


function getAllPrograms(){
	
	$("#selectProgramId option:selected").remove();	
	$("#selectProgramId").append('<option value="0">Select Program</option>'); 
	$.ajax({
		  type:'POST',
          url: 'getProgramsForFeedBackAction.action',
          dataType: 'json',
		  data: {}
	}).done(function(result){
		if(result !=null && result.length>0){
			for(var i in result){
				if(result[i].id==1){
					$("#selectProgramId").append('<option value="'+result[i].id+'" selected>'+result[i].name+'</option>');
				}else{
					$("#selectProgramId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
				}
			}	
			feedbackCategoryCountsCenterWise();	
			getFeedbackDetailsOfEachDistrictAndConstituencyWise();
		}
	});
}

//$("#selectProgramId").change(function(){
	function feedbackCategoryCountsCenterWise(){
		$("#programNameTitleId").html($("#selectProgramId :selected").text());
		$("#centerWiseCategoryDivId").html("");
		$("#centerWiseCategoryAjaxImage").show();
		//$("#centerWiseCategoryMainDivId").hide();
		
		var programId = $("#selectProgramId").val();
		if(programId ==null || programId ==0){
			return;
		}
		var jsObj={
			programId:programId
		}
		$.ajax({
		  type:'POST',
          url: 'getFeedbackCategoryCountsCenterWiseAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<table class="table table-bordered m_0">';
				str+='<thead>';
						str+='<th>TRAINING CENTER</th>';
						str+='<th>CATEGORY</th>';
						str+='<th>COUNT</th>';
						str+='<th>TOTAL CATEGORY COUNT</th>';
				str+='</thead>';
				
				str+='<tbody>';
					for(var i in result){
						str+='<tr>';
						var categoryLength=0;
						if(result[i].trainingCampVOList !=null){
							categoryLength=parseInt(categoryLength)+parseInt(result[i].trainingCampVOList.length);
						
						 for(var j in result[i].trainingCampVOList){
							if(j==0){
								str+='<td id="'+result[i].trainingCampVOList[j].programId+'" rowspan='+categoryLength+'>'+result[i].trainingCampVOList[j].programName+'</td>';
							}
							
							str+='<td id="'+result[i].trainingCampVOList[j].campId+'">'+result[i].trainingCampVOList[j].campName+'</td>';							
							str+='<td>'+result[i].trainingCampVOList[j].assignedCount+'</td>';
							if(j==0){
								str+='<td rowspan='+categoryLength+'>'+result[i].totalProgrammesCount+'</td>';
							}
							str+='</tr>';
						 }
						 
						}
					}
					str+='<tr>';
						 str+='<td style="border-right: 1px solid #EFF3F4;">TOTAL</td>';
						 str+='<td style="border: 1px solid #EFF3F4;"></td>';
						 str+='<td ></td>';
						 str+='<td>'+result[0].acceptedCount+'</td>';
					str+='</tr>';
				
				str+='</tbody>';
			str+='</table>';
			
			$("#centerWiseCategoryAjaxImage").hide();
			$("#centerWiseCategoryDivId").html(str);
			//$("#centerWiseCategoryMainDivId").show();
		}else{
			$("#centerWiseCategoryDivId").html("<h4>No Data Available</h4>");
		}
	});
	}
	function getAllDistrictsByState(stateId){
		$("#selectDistrictId option:selected").remove();
		$("#selectDistrictId").append('<option value="0">All</option>');
		
		var jsObj={
			stateId:stateId
		}
		$.ajax({
		  type:'POST',
          url: 'getAllDistrictsByStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}			
		}).done(function(result){
			if(result !=null && result.length>0){
				for(var i in result){
					$("#selectDistrictId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
			}
		});
		
	}
	
	$("#selectDistrictId").change(function(){
		getAllConstituencyByDistrict();
	});

	function getAllConstituencyByDistrict(){
		
		//$("#selectConstituencyId").val(0);
		$('#selectConstituencyId').children().remove();
		$("#selectConstituencyId").append('<option value="0">All</option>');
		
		var districtId = $("#selectDistrictId").val();
		
		var jsObj={
			districtId:districtId
		}
		$.ajax({
		  type:'POST',
          url: 'getAllConstituencysByDistrictAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}			
		}).done(function(result){
			if(result !=null && result.length>0){
				for(var i in result){
					$("#selectConstituencyId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
			}
		});
	}
	//getFeedbackDetailsOfCadre();
//function getFeedbackDetailsOfCadre(){
$(document).on('click','.feedBackDetailsCls',function(){
		
		$("#feedBackDetailsDivId").show();
		$("#cadreDetailsAjaxImage").show();
		$("#cadreDetailsDivId").html("");
		
		var programId = $("#selectProgramId").val();
		var locationId = $(this).attr("attr_constituencyId");
		
		//programId=1;
		//locationId =22;
		type ="constituecy";
		
		var jsObj={
			programId:programId,
			locationId:locationId,
			type:type
		}
		$.ajax({
		  type:'POST',
          url: 'getFeedbackDetailsOfCadreAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}			
		}).done(function(result){
			if(result != null && result.length > 0){
				buildFeedbackDetailsOfCadre(result);
			}
			else{
				$("#cadreDetailsAjaxImage").hide();
				$("#cadreDetailsDivId").html("NO DATA AVAILABLE...");
			}
		});
	});
	
function buildFeedbackDetailsOfCadre(result){
	
	var str = '';
	
	str+='<table class="table table-bordered dataTableDiv">';
		str+='<thead class="bg_d">';
			str+='<th>IMAGE</th>';
			str+='<th>CADRE NAME</th>';
			str+='<th>DESIGNATION</th>';
			str+='<th>MOBILE NO</th>';
			str+='<th>CATEGORY</th>';
			str+='<th>ANSWER</th>';
		str+='</thead>';
		for(var i in result){
			if(result[i].simpleVOList1 != null && result[i].simpleVOList1.length > 0){
				for(var j in result[i].simpleVOList1){
					str+='<tr>';
						if(result[i].simpleVOList1[j].imageStr != null){
							str+='<td><img src="images/cadre_images/'+result[i].simpleVOList1[j].imageStr+'" style="height:40px" class="img-reponsive"></td>';
						}
						else{
							str+='<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive" ></td>';
						}
						str+='<td>';
						str+='<p>'+result[i].simpleVOList1[j].name+'</p>';
						str+='<p>'+result[i].simpleVOList1[j].constituencyName+'</p>';
						str+='<p>'+result[i].simpleVOList1[j].id+'</p>';
						str+='</td>';
						str+='<td>';
						str+='<p>'+result[i].simpleVOList1[j].designation+'</p>';
						str+='<p>'+result[i].simpleVOList1[j].designationLevel+'</p>';
						str+='</td>';
						str+='<td>'+result[i].simpleVOList1[j].mobileNo+'</td>';
						str+='<td>'+result[i].simpleVOList1[j].category+'</td>';
						str+='<td>'+result[i].simpleVOList1[j].remarks+'</td>';
					str+='</tr>';
				}
			}
		}
	str+='</table>';
	
	$("#cadreDetailsAjaxImage").hide();
	$("#cadreDetailsDivId").html(str);
	$(".dataTableDiv").dataTable();
	$(".dataTableDiv").removeClass("dataTable");
}
	
		
	
	function getFeedbackDetailsOfEachDistrictAndConstituencyWise(){
		$("#distConstCatWiseCounts").html("");
		$("#distConstCatWiseCountsAjaxImage").show();
		var jsObj={
			districtId : $("#selectDistrictId").val(),
			constituencyId : $("#selectConstituencyId").val(),
			categoryId : $("#selectCategoryId").val(),
			programId : $("#selectProgramId").val(),
			type : "constituecys"
		}
		$.ajax({
			type:'POST',
			url: 'getFeedbackDetailsOfEachDistrictAndConstituencyWiseAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}			
		}).done(function(result){
			if(result!=null && result.length>0){
				var str='';
				str+='<table class="table table-bordered m_0" id="resultTableDivId">';
				str+='<thead>';
				str+='<thaed>';
				str+='<th>District</th>';
				str+='<th>Constituency</th>';
				str+='<th>Category</th>';
				str+='<th>Count</th>';
				str+='<th>Total Category Count</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					if(result[i].trainingCampVOList != null && result[i].trainingCampVOList.length > 0){
						for(var j in result[i].trainingCampVOList){
							if(result[i].trainingCampVOList[j].trainingCampVOList != null && result[i].trainingCampVOList[j].trainingCampVOList.length > 0){
								for(var k in result[i].trainingCampVOList[j].trainingCampVOList){
									str+='<tr>';
									str+='<td attr_district_id='+result[i].trainingCampVOList[j].trainingCampVOList[k].programId+'>'+result[i].trainingCampVOList[j].trainingCampVOList[k].programName+'</td>';
									str+='<td attr_district_id='+result[i].trainingCampVOList[j].trainingCampVOList[k].campId+'>'+result[i].trainingCampVOList[j].trainingCampVOList[k].campName+'</td>';
									str+='<td attr_district_id='+result[i].trainingCampVOList[j].trainingCampVOList[k].thirdId+'>'+result[i].trainingCampVOList[j].trainingCampVOList[k].thirdName+'</td>';
									str+='<td>'+result[i].trainingCampVOList[j].trainingCampVOList[k].assignedCount+'</td>';
									str+='<td attr_constituencyId="'+result[i].trainingCampVOList[j].trainingCampVOList[k].campId+'" class="feedBackDetailsCls" style="cursor:pointer">'+result[i].trainingCampVOList[j].trainingCampVOList[0].totalProgrammesCount+'</td>';
									str+='</tr>';
								}
							}
						}
					}
				}
				str+='</tbody>';
				str+='</table>';
				$("#distConstCatWiseCountsAjaxImage").hide();
				$("#distConstCatWiseCounts").html(str);
				 $('#resultTableDivId').dataTable();
				 $('#resultTableDivId').removeClass("dataTable");
			}else{
				$("#distConstCatWiseCountsAjaxImage").hide();
				$("#distConstCatWiseCounts").html("<h4>No Data Available.</h4>");
			}
		});
	}	

	function getAllCategories(){
		
		$.ajax({
			  type:'POST',
			  url: 'getAllCategoriesAction.action',
			  dataType: 'json',
			  data: {}
		}).done(function(result){
			if(result != null && result.length > 0){
				var str='';
				for(var i in result){
					$("#selectCategoryId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
				}
			}
			
		});
	}
	
</script>
</body>
</html>