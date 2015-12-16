
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
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
</head>
<body>
<header>
	<img src="dist/img/header.jpg" width="100%">
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
								<select class="form-control" id="selectProgramId" onchange="FeedbackCategoryCountsCenterWise();">
									<option value="0">Select Program</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default m_top20" id="centerWiseCategoryMainDivId" style="display:none;">
									<div class="panel-heading">
										<h4 class="panel-title">LEADERSHIP SKILLS</h4>
									</div>
									<div class="panel-body pad_0">
										<div class="table-responsive bg_ff" id="centerWiseCategoryDivId">
											<!--<table class="table table-bordered m_0">
												<thead class="bg_d">
													<th>Center</th>
													<th>Category</th>
													<th>Count</th>
													<th>Total Category Count</th>
												</thead>
												<tr>
													<td rowspan="3">GPN Center</td>
													<td>మీ నియోజకవర్గం పార్టీ, పార్టీ నాయకులు</td>
													<td>100</td>
													<td rowspan="3" style="vertical-align:middle" class="text-center">100</td>
												</tr>
												<tr>
													<td>మీ గ్రామమం లో సమస్యల వివరాలు</td>
													<td>100</td>
												</tr>
												<tr>
													<td>మీ గ్రామమము లో పథకాల అమలు ఫై మీ అభిప్రాయం</td>
													<td>100</td>
												</tr>
												<tr>
													<td rowspan="3">EWK Center</td>
													<td>మీ నియోజకవర్గం పార్టీ, పార్టీ నాయకులు</td>
													<td>100</td>
													<td rowspan="3" style="vertical-align:middle" class="text-center">100</td>
												</tr>
												<tr>
													<td>మీ గ్రామమం లో సమస్యల వివరాలు</td>
													<td>100</td>
												</tr>
												<tr>
													<td>మీ గ్రామమము లో పథకాల అమలు ఫై మీ అభిప్రాయం</td>
													<td>100</td>
												</tr>
												<tr>
													<td rowspan="3">AKKC Center</td>
													<td>మీ నియోజకవర్గం పార్టీ, పార్టీ నాయకులు</td>
													<td>100</td>
													<td rowspan="3" style="vertical-align:middle" class="text-center">100</td>
												</tr>
												<tr>
													<td>మీ గ్రామమం లో సమస్యల వివరాలు</td>
													<td>100</td>
												</tr>
												<tr>
													<td>మీ గ్రామమము లో పథకాల అమలు ఫై మీ అభిప్రాయం</td>
													<td>100</td>
												</tr>
												<tr>
													<td colspan="1" class="text-center"><b>TOTAL</b></td>
													<td>1000</td>
													<td>500</td>
												</tr>
											</table> -->
										</div>
									</div>
								</div>
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
									<option>Select Constituency</option>
								</select>
							</div>
							<!--<div class="col-md-3">
								<label>Select Training Program</label>
								<select class="form-control">
									<option>Training Program Name</option>
								</select>
							</div>
							<div class="col-md-3">
								<button class="m_top20 btn btn-success btn-sm">SUBMIT</button>
							</div> -->
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table-responsive m_top20">
									<table class="table table-bordered m_0">
										<thead class="bg_d">
											<th>District</th>
											<th>Category</th>
											<th>Count</th>
										</thead>
										<tr>
											<td rowspan="3">Adilabad</td>
											<td>Category 1</td>
											<td>100</td>
										</tr>
										<tr>
											<td>Category 2</td>
											<td>100</td>
										</tr>
										<tr>
											<td>Category 3</td>
											<td>100</td>
										</tr>
										<tr>
											<td colspan="2" class="text-center"><b>TOTAL</b></td>
											<td><b>2000</b></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default m_top20">
									<div class="panel-heading">
										<h4 class="panel-title">FEEDBACK FULL DETAILS<button class="pull-right btn-xs btn btn-success">EXPORT TO PDF</button></h4>
									</div>
									<div class="panel-body pad_0">
										<div class="table-responsive" id="cadreDetailsDivId">
											<!--<table class="table table-bordered">
												<thead class="bg_d">
													<th></th>
													<th>CADRE NAME</th>
													<th>MEMBERSHIP NO</th>
													<th>MOBILE NO</th>
													<th>CATEGORY</th>
												
													<th>Feedback Answer</th>
												</thead>
												<tr>
													<td><img src="dist/img/profile-img.png" style="height:40px;width:40px;display:block;margin:auto"></td>
													<td>B AYYAPPA</td>
													<td>12345678</td>
													<td>9848012345</td>
													<td>Category 1</td>
													
													<td>Problems...</td>
												</tr>
												<tr>
													<td><img src="dist/img/profile-img.png" style="height:40px;width:40px;display:block;margin:auto"></td>
													<td>B AYYAPPA</td>
													<td>12345678</td>
													<td>9848012345</td>
													<td>Category 1</td>
													
													<td>Problems...</td>
												</tr>
												<tr>
													<td><img src="dist/img/profile-img.png" style="height:40px;width:40px;display:block;margin:auto"></td>
													<td>B AYYAPPA</td>
													<td>12345678</td>
													<td>9848012345</td>
													<td>Category 1</td>
												
													<td>Problems...</td>
												</tr>
											</table> -->
										</div>
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
		<img src="dist/img/footer.jpg" width="100%">
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<script>
$(function () {
	getAllPrograms();
	getAllDistrictsByState(1);
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
			FeedbackCategoryCountsCenterWise();	
		}
	});
}

//$("#selectProgramId").change(function(){
	function FeedbackCategoryCountsCenterWise(){
		$("#centerWiseCategoryDivId").html("");
		$("#centerWiseCategoryMainDivId").hide();
		
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
				
				str+='</tbody>';
			str+='</table>';
			
			$("#centerWiseCategoryDivId").html(str);
			$("#centerWiseCategoryMainDivId").show();
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
		$("#selectConstituencyId").append('<option value="0">Select Constituency</option>');
		
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
	getFeedbackDetailsOfCadre();
	function getFeedbackDetailsOfCadre(){
		
		$("#cadreDetailsDivId").html("");
		programId=1;
		locationId =22;
		type ="district";
		
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
				$("#cadreDetailsDivId").html("NO DATA AVAILABLE...");
			}
		});
	}
		
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
	
	$("#cadreDetailsDivId").html(str);
	 $(".dataTableDiv").dataTable();
}

</script>
</body>
</html>