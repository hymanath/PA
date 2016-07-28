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
<title>NOMINATED POST PROFILE CREATION</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<ol class="breadcrumb">
            	<li><i class="glyphicon glyphicon-home"></i></li>
                <li>Nominated Post Shortlisting</li>
            </ol>
        </div>
		<div class="col-md-12 col-xs-12 col-sm-12" >
        	<h4 class="headingColor">Central Level - Board/Corporation Overview</h4>
				<div id="centralWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12" >
        	<h4 class="headingColor">State Level - Board/Corporation Overview
				<span style="margin-right:60px" class="pull-right">
				  <span style="border-radius:15px;" class="btn btn-xs btn-success"> 
					<input type="radio" value="1" name="stateName" style="cursor:pointer;" class="stateCls" checked="true"> <b>AP</b>
				  </span> 
				  <span style="border-radius:15px;" class="btn btn-xs btn-success">
					<input type="radio" value="2" name="stateName" style="cursor:pointer;" class="stateCls"> <b>TS</b>
				  </span>
				</span>
			</h4>
				<div id="stateWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor">District Level - Board/Corporation Overview</h4>
			<div id="districtWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor">Constituency Level - Board/Corporation Overview</h4>
			<div id="assemblyWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor">Mandal/Muncipality/Corporation Level - Board/Corporation Overview</h4>
			<div id="mandalORMunciWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
      <!--  <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor">Panchayat/Ward/Division Level - Board/Corporation Overview</h4>
				<div id="villageORWardWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
		-->
    </div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>

<script>
	$('document').ready(function(){
	getNominatdPostsOverview("centralWiseOverviewId",1);
	getNominatdPostsOverview("stateWiseOverviewId",2);
	getNominatdPostsOverview("districtWiseOverviewId",3);
	getNominatdPostsOverview("assemblyWiseOverviewId",4);
	getNominatdPostsOverview("mandalORMunciWiseOverviewId",5);
	//getNominatdPostsOverview("villageORWardWiseOverviewId",7);
		
	});
	function getNominatdPostsOverview(divId,levelId){
		
		$('#'+divId+'').html('<img id="dataLoadingsImgForImagePath" src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		var stateId = $('input[name=stateName]:checked').val();
		var jsObj =
		{
			levelId:levelId,
			startDateStr:"",
			endDateStr:"",
			stateId : stateId
		};
		
		$.ajax({
				type : "POST",
				url : "getNominatdPostsOverviewAction.action",
				data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
				buildStatusDetails(result,levelId,divId);
		});  
	}
	
	function buildStatusDetails(result,levelId,divId){
		var str="";
		if(result != null && result.length>0){
			str+='<ul class="panelBlockCustom">';
			for(var i in result){
				str+='<li style="font-size:12px">';
					str+='<div class="panel panel-default panelCustom">';
					str+='<div class="panel-heading">';
					if(i>0){
						if(result[i].name == "YET TO START" || result[i].name == "RUNNING"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_RUNNING_ENTITLEMENT' )}">
								if(result[i].totalCorp > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalCorp+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:when> 
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:otherwise>
							</c:choose>
						}
							
						else if(result[i].name == "READY FOR FINAL REVIEW"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalCorp > 0)
									str+='<h3><span class="finalReviewCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalCorp+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:otherwise>
							</c:choose>
						}
						else if(result[i].name == "FINALYZED"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_FINALYZE_ENTITLEMENT' )}">
									<!--str+='<h3><span class="finalyzedCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalCorp+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';-->
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>'
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>'
								</c:otherwise>
							</c:choose>
						}
						else if(result[i].name == "GO PASSED / COMPLETED"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_G_O_PASSED_ENTITLEMENT' )}">
									<!--str+='<h3><span class="goPassedCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalCorp+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';-->
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>'
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>'
								</c:otherwise>
							</c:choose>
						}
						else{
							str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalCorp+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
						}
					}
					else{
						str+='<h3>'+result[i].totalCorp+' </h3>';
					}
					str+='<p>'+result[i].name+'</p>';
					str+='</div>';
					str+='<div class="panel-body">';
					if(i>0)
						str+='<p>'+result[i].totalPositions+' Positions <span class="pull-right text-danger">'+result[i].perc1+'%</span></p>';
					else 
						str+='<p>'+result[i].totalPositions+' Positions</p>';
					
					str+='</div>';
					str+='<div class="panel-footer">';
					str+='<p>'+result[i].totalDept+' Departments</p>';
					str+='</div>';
					str+='</div>';
				str+='</li>';
				
			}
				
			str+='</ul>';
		}
		else{
			str="No data available.";
		}
		$('#'+divId+'').html(str);
	}
</script>
<script>

$(document).on("click",".yetToStartCls",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	if(status == "YET TO START")
		var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=notYet','_blank');
	else if(status == "RUNNING")
		var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=running','_blank');
});
$(document).on("click",".finalReviewCls",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	
	var redirectWindow=window.open('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=finalReview','_blank');
	
});
/*
$(document).on("click",".finalyzedCls",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	
	var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=finalyzed','_blank');
});
$(document).on("click",".goPassedCls",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	
	var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=g_o_passed','_blank');
});
*/

$(document).on("click",".stateCls",function(){
	
		getNominatdPostsOverview("stateWiseOverviewId",2);
		getNominatdPostsOverview("districtWiseOverviewId",3);
		getNominatdPostsOverview("assemblyWiseOverviewId",4);
		getNominatdPostsOverview("mandalORMunciWiseOverviewId",5);
		//getNominatdPostsOverview("villageORWardWiseOverviewId",7);
});

</script>
</body>
</html>
