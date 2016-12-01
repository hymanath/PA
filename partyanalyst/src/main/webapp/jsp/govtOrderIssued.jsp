<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<title>Nominated Post - Shortlisting</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Datatables/datatables.css" rel="stylesheet" type="text/css"/>
<link href="dist/NominatedPost/Slick/slick.css" rel="stylesheet" type="text/css"/>
<link href="dist/NominatedPost/Slick/slick-theme.css" rel="stylesheet" type="text/css"/>
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<style type="text/css">
.panelGO
{
	border:2px solid #CCC;
}
.panelGO .panel-heading , .panelGO .panel-body , .panelGO .panel-heading , .panelGO .panel-footer
{
	background-color:#EFF3F4
}
.selectBox
{
	padding:8px 12px;
	border:2px solid #DDD;
	background-color:#fff;
	position:relative;
	text-transform:capitalize;
	cursor:pointer;
}
.selectBox.active:after
{
	font-family: 'Glyphicons Halflings';
	content:'\e014';
	display:block;
	position:absolute;
	right:10px;
	font-size:10px;
	background-color:#535353;
	color:#fff;
	padding:2px 4px;
	border-radius:50%;
	top:10px;
}
</style>
</head>
<body>
<div class="container">
<div class="row">';
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="background-color:#CCC">
						<h3 class="text-capital">G.O.issued / completed</h3>
						<p><span id="globalLocationId" style="font-weight:bold"></span> / <span style="font-style:italic" id="globalHeadId"></span></p>    
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="pad_15" style="background-color:#F5F5F5">
									<div class="row">
										<div class="col-md-3 col-sm-6 col-xs-12">
									
											<label>Department</label>
											<select class="chosenSelect" id="departmentId" multiple>   
												<option value="0">ALL</option>
												<option value="1">aaa</option>
												<option value="2">bbb</option>
											</select>
											<div id="deptErrVid" style="color:red;"></div>
										</div>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label>Board</label>
											<select class="chosenSelect" id="corporationId" multiple> 
												<option value="0">ALL</option>
												<option value="1">aaa</option>
												<option value="2">bbb</option>
											</select>
											<div id="boardErrVid" style="color:red;"></div>
										</div>
										<div class="col-md-3 col-sm-6 col-xs-12">  
											<label>Position</label>
											<select class="chosenSelect" id="positionId" multiple>   
												<option value="0">ALL</option>
												<!--<option value="1">aaa</option>
												<option value="2">bbb</option>	-->									
											</select>
											<div id="positionErrVid" style="color:red;"></div> 
										</div>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label>Expire Date</label>  
											<div class="input-group"><input type="text" id="DateRanges" class="form-control"/><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span></div> 
										</div>
								</div>
									<div class="row">
										<div class="col-md-3 col-sm-2 col-xs-12 m_top20">      
											<button type="button" value="getDetails" class="btn btn-success m_top10"  id="statusDetailsId">SUBMIT</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row m_top10" id="bodyId">
						</div>
					</div>
				</div>
			</div>
		</div>

</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/NominatedPost/Slick/slick.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script> 
<script src="dist/activityDashboard/Date/moment.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/daterangepicker.js" type="text/javascript"></script>

<script type="text/javascript">
$('.chosenSelect').chosen({width: "100%"});  
$(document).ready(function(){
	getDepartmentList(globalLocationLevelId);
	getBoardList([deptId]);
	//getPositionList(); 
	var depts = [];
	var brds = [];
	depts.push(deptId);
	brds.push(boardId);
	getDepartmentBoardPositions(depts,brds);
	//$("#globalHeadId").html(deptName+" - "+boardName+" - "+positionName);
});

setTimeout(function(){ $("#globalHeadId").html(" "+deptName+" - "+boardName+" - "+positionName); }, 3000);
var globalLocationLevelId = ${param.LocationLevelId};
var globalLocationLevelValueArr = [${param.locationLevelValueArr}];      
var deptId = ${param.deptId};
var boardId = ${param.boardId};
var positionId = ${param.positionId};  
var status = "${param.status}"; 
var deptName = '';
var boardName = '';
var positionName = '';
$(document).on("click",".selectBox",function(){
	$(this).toggleClass("active")
});

var globalLocation = '';
if(globalLocationLevelId == 2){
	globalLocation = "State";
}else if(globalLocationLevelId == 3){
	globalLocation = "District";
}else if(globalLocationLevelId == 4){
	globalLocation = "Constituency";
}
$("#globalLocationId").html(globalLocation+"  Level")
buildPage();
function buildPage(){       
	var jsObj = {
	LocationLevelId : globalLocationLevelId, 
	locationLevelValueArr : globalLocationLevelValueArr,              
	departmentId : deptId,
	boardId : boardId,
	positionId : positionId,  
	status : status
	}
	$.ajax({
	  type:'GET',
	  url: 'getFinalReviewCandidateCountForLocationAction.action',    
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}      
	}).done(function(result){
	if(result != null && result.length > 0){
	   //console.log(result);   
	   buildModel(result);  
	}else{
		$("#bodyId").html("No Data Is Available...")
	}
	});
}
	function buildModel(result){      
		var str = '';
		for(var i in result){
			str+='<div class="col-md-4 col-sm-6 col-xs-12">';
				str+='<div class="panel panel-default panelGO">';
					str+='<div class="panel-heading" style="min-height:130px;">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							if(result[i].tdpCadreId != null && result[i].tdpCadreId > 0){
								
								if(result[i].imageURL != null && result[i].imageURL.length>0){
								str+='<a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].tdpCadreId+'" >';
								str+='<img src="https://mytdp.com/images/cadre_images/'+result[i].imageURL+'"  class=" img-circle" alt="Profile" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"/></a>';
								}
							}else{
								str +='<img style="width: 50px;height:50px;border:1px solid #ddd;" src="https://mytdp.com/images/'+ result[i].imageURL+'" class="img-circle" onerror="setDefaultImage(this);" alt="Profile" style="width: 50px; height: 50px;"/>';
								}
							str+='</div>';
							str+='<div class="media-body">';
							if(result[i].tdpCadreId != null && result[i].tdpCadreId > 0){
								str+='<a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].tdpCadreId+'" >';
								str+='<p>'+result[i].name+'</p></a>';
							}else{
								str+='<p>'+result[i].name+'</p></a>';
							}
								str+='<p>Ph: '+result[i].cadreMobile+'</p>';
								str+='<p>M.ID: '+result[i].membershipNO+'</p>';
							str+='</div>';
						str+='</div>';
						str+='<p>';
							str+='<span>Male '+result[i].age+' years Old</span>';
							str+='<span class="pull-right">'+result[i].castCategoryName+' - '+result[i].casteName+'</span>';
						str+='</p>';
					str+='</div>';
					str+='<div class="panel-body text-capitalize"  style="min-height:100px;">';
						str+='<b>';
							str+='<p>'+result[i].boardName+'</p>';
							str+='<p> - '+result[i].positionName+'</p>';                    
						str+='</b>';   
					str+='</div>';
					str+='<div class="panel-footer text-capitalize">';
						str+='<p><b>'+result[i].govtOrderName+'</b></p>';
						str+='<p class="text-muted">Dated : '+result[i].fromDate.substring(0,10)+' to '+result[i].toDate.substring(0,10)+'</p>';
						str+='<p class="text-danger"><i>Going to expire : '+result[i].expireDate+'</i></p>';    
					str+='</div>';
				str+='</div>';
			str+='</div>';
	}
						
		$("#bodyId").html(str);  
	} 
	$("#DateRanges").daterangepicker({
			opens:'left',
			 ranges: {
				   'Next One Month': [moment(), moment().add(1, 'month')],
				   'Next two Month': [moment(), moment().add(2, 'month')],
				   'Next Three Month': [moment(), moment().add(3, 'month')],   
				   'Next Six Month': [moment(), moment().add(6, 'month')],
				   'Next One Year': [moment(), moment().add(1, 'year')],  
				}  
			
		});
	function getDepartmentList(boardLevelId){
		var jsObj={
			boardLevelId : boardLevelId  
		}  
		$.ajax({
			type:'GET',
			url:'getDepartmentListAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$('#departmentId').html('<option value="0">ALL</option>');
			if(result != null && result.length > 0){
				for(var i in result){
					if(result[i].id == deptId){
						$('#departmentId').append('<option value="'+result[i].id+'" selected="selected">'+result[i].name+'</option>');  
						deptName = result[i].name;
					}else{
						$('#departmentId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
					}
					
				}
				$("#departmentId").trigger("chosen:updated");
			}
		});
	}
	function getBoardList(deptsId){  
	$("#corporationId").empty();
	$("#corporationId").trigger("chosen:updated");
		var jsObj={
			deptId : deptsId
		}
		$.ajax({
			type:'GET',
			url:'getBoardListAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#corporationId").empty();
			if(boardId == 0){
				$('#corporationId').html('<option value="0" selected="selected">ALL</option>');
			}else{
				$('#corporationId').html('<option value="0" >ALL</option>');
			}
			if(result != null && result.length > 0){
				for(var i in result){
					if(result[i].id == boardId){ 
						$('#corporationId').append('<option value="'+result[i].id+'" selected="selected">'+result[i].name+'</option>');  
						boardName = result[i].name;
						//getPositionList();
						var depts = [];
						var brds = [];
						depts.push(deptId);
						brds.push(boardId);
						getDepartmentBoardPositions(depts,brds)
					}else{
						$('#corporationId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');  
					}
					
				}
				$("#corporationId").trigger("chosen:updated");	  			
			}
		});
	}
	function getPositionList(){
		var jsObj={}
		$.ajax({
			type:'GET',
			url:'getPositionListAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$('#positionId').html('<option value="0">ALL</option>');
			if(result != null && result.length > 0){
				for(var i in result){
					if(result[i].id == positionId){  
						$('#positionId').append('<option value="'+result[i].id+'" selected="selected">'+result[i].name+'</option>');
						positionName = result[i].name;
					}else{
						$('#positionId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
					}
					
				}
				$("#positionId").trigger("chosen:updated");  
			}
		});
	}
	
	$(document).on('change','#departmentId',function(){
		var departmentId = $("#departmentId").val();  
		$("#corporationId").empty();
		$("#corporationId").trigger("chosen:updated");
		$("#positionId").empty();
		$("#positionId").trigger("chosen:updated");
		$("#bodyId").html("");
		if(departmentId != null)
		getBoardList(departmentId);
	});
	
	$(document).on('change','#corporationId',function(){
		var boardId = $("#corporationId").val();
		var departmentId = $("#departmentId").val(); 
		$("#positionId").empty();
		$("#positionId").trigger("chosen:updated");
		$("#bodyId").html("");
		if(boardId != null)
		getDepartmentBoardPositions(departmentId,boardId);
	});
	 
	$(document).on('click','#statusDetailsId',function(){
		$("#deptErrVid").html("");
		$("#boardErrVid").html("");
		$("#positionErrVid").html("");
		var deptIds = [];
		deptIds = $("#departmentId").val();
		
		if(deptIds == null){
			$("#deptErrVid").html("Please  select atleast one department");
			return;
		}
		if(deptIds == null){
			deptIds=[0];
		}
		var boardIds = [];
		boardIds = $("#corporationId").val();
		if(boardIds == null){
			$("#boardErrVid").html("Please  select atleast one board");
			return;
		}
		if(boardIds == null){
			boardIds=[0];
		}
		var positionIs = [];
		positionIs = $("#positionId").val(); 
		if(positionIs == null){
			$("#positionErrVid").html("Please  select atleast one position");
			return;
		}
		if(positionIs == null){
			positionIs=[0];
		}  
		var strDate = $("#DateRanges").val();
		var dateArray = strDate.split("-");
		var today = dateArray[0].trim();
		var expireDate = dateArray[1].trim();  
		//console.log(deptIds+":"+boardIds+":"+positionIs+":"+today+":"+expireDate);
		var jsObj = {
		LocationLevelId : globalLocationLevelId, 
		locationLevelValueArr : globalLocationLevelValueArr,              
		departmentIds : deptIds,
		boardIds : boardIds,
		positionIds : positionIs,  
		today : today,
		expireDate : expireDate,
		status : status
		}
		$.ajax({
		  type:'GET',
		  url: 'getFinalReviewCandidateCountForLocationFilterAction.action',    
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}        
		}).done(function(result){
		if(result != null && result.length > 0){
		   //console.log(result);   
		   buildModel(result);  
		}else{
		$("#bodyId").html("No Data Is Available...")
		}
		});      
	});
	function setDefaultImage(img){
    img.src = "images/User.png";
}
function getDepartmentBoardPositions(deptId,boardId){	
//alert(globalLocationLevelValueArr)
//var levelValue = globalLocationLevelValueArr[0];
	var jsObj = {
		
		deptId : deptId,
		boardId :boardId,
		boardLevelId : globalLocationLevelId,
		searchLevelValue:globalLocationLevelValueArr,
		searchLevelId:0,
		nominatedPostCandId:0
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardPositionsAction1.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){ 
   if(positionId == 0){
		$('#positionId').html('<option value="0" selected="selected">ALL</option>');
   }else{
	   $('#positionId').html('<option value="0">ALL</option>');
   }
			if(result != null && result.length > 0){
				for(var i in result){
					if(result[i].id == positionId){  
						$('#positionId').append('<option value="'+result[i].id+'" selected="selected">'+result[i].name+'</option>');
						positionName = result[i].name;
					}else{
						$('#positionId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
					}
					
				}
				$("#positionId").trigger("chosen:updated");  
			}
   
   });
  }
	
</script>
</body>
</html>
