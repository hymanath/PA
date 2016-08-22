<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<title>Nominated Post - Shortlisting</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
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
<div class="container" id="buildBodyId">	
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
var LocationLevelId = ${param.LocationLevelId};
var locationLevelValueArr = [${param.locationLevelValueArr}];      
var deptId = ${param.deptId};
var boardId = ${param.boardId};
var positionId = ${param.positionId};  
var status = "${param.status}"; 
$(document).on("click",".selectBox",function(){
	$(this).toggleClass("active")
});

buildPage();
function buildPage(){  
	var jsObj = {
	LocationLevelId : LocationLevelId, 
	locationLevelValueArr : locationLevelValueArr,              
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
	if(result != null){
	   console.log(result);   
	   buildModel(result);  
	}
	});
}
 function buildModel(result){      
	var str = '';
	
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="panel panel-default">';
				str+='<div class="panel-heading" style="background-color:#CCC">';
					str+='<h3 class="text-capital">G.O.issued / completed</h3>';
					str+='<p>State Level - Labout Department</p>';
				str+='</div>';
				str+='<div class="panel-body">';
					str+='<div class="row">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12">';
							str+='<div class="pad_15" style="background-color:#F5F5F5">';
								str+='<div class="row">';
									str+='<div class="col-md-3 col-sm-6 col-xs-12">';
										str+='<div class="selectBox active">labour Department</div>';
									str+='</div>';
									str+='<div class="col-md-3 col-sm-6 col-xs-12">';
										str+='<div class="selectBox">All Boards/Corporations</div>';
									str+='</div>';
									str+='<div class="col-md-3 col-sm-6 col-xs-12">';
										str+='<div class="selectBox">All Positions</div>';
									str+='</div>';
									str+='<div class="col-md-3 col-sm-6 col-xs-12">';
										str+='<div class="selectBox">Expire in 3 months</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row m_top10">';
					for(var i in result){
						str+='<div class="col-md-4 col-sm-6 col-xs-12">';
							str+='<div class="panel panel-default panelGO">';
								str+='<div class="panel-heading">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<img src="dist/img/profile.png" class="media-object"/>';
										str+='</div>';
										str+='<div class="media-body">';
											str+='<p>'+result[i].name+'</p>';
											str+='<p>Ph: '+result[i].cadreMobile+'</p>';
											str+='<p>M.ID: '+result[i].membershipNO+'</p>';
										str+='</div>';
									str+='</div>';
									str+='<p>';
										str+='<span>Male '+result[i].age+' years Old</span>';
										str+='<span class="pull-right">'+result[i].castCategoryName+' - '+result[i].casteName+'</span>';
									str+='</p>';
								str+='</div>';
								str+='<div class="panel-body text-capitalize">';
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
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#buildBodyId").html(str);   
	}
</script>
</body>
</html>
