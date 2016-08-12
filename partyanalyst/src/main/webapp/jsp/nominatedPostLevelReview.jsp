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
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<style>
.font_15{font-size:18px;}
.font_24{font-size: 20px;}
.bar_view{padding: 6px; float: left; height: 32px; width: 12px;  margin-left: -9px;}
</style>
<body>
<div class="container">
    <div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<ol class="breadcrumb">
            	<li><i class="glyphicon glyphicon-home"></i></li>
                <li>Nominated Posts Overview Details  </li>
            </ol>
        </div>
		<div class="col-md-12 col-xs-12 col-sm-12" >
			<div class="applicationsOverView">
			<div class="m_top10">
				<span class="bar_view" style="background-color: rgb(245, 129, 129);"></span>
				<span class="headingColor" style="padding:7px;"><span class="text-capital font_24">CENTRAL LEVEL</span> - <span class="font_15">Board/Corporation Overview</span></span>
				<div id="centralWiseOverviewId"  style="margin-top: 15px"></div>
			</div>
			</div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top20" >
			<div class="applicationsOverView">
			<span class="bar_view" style="background-color: #62B9A9;"></span>
				<span class="headingColor" style="padding:7px;"><span class="text-capital font_24">State LEVEL</span> - <span class="font_15">Board/Corporation Overview</span></span>
					<span style="margin-right:60px" class="pull-right">
					  <span style="border-radius:15px;" class="btn btn-xs btn-success"> 
						<input type="radio" value="1" name="stateName" style="cursor:pointer;" class="stateCls" checked="true"> <b>AP</b>
					  </span> 
					  <span style="border-radius:15px;" class="btn btn-xs btn-success">
						<input type="radio" value="2" name="stateName" style="cursor:pointer;" class="stateCls"> <b>TS</b>
					  </span>
					</span>
				</span>
				<div id="stateWiseOverviewId"  style="margin-top: 15px"></div>
			</div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
			<div class="applicationsOverView">
			<span class="bar_view" style="background-color: #C391CE;"></span>
				<span class="headingColor" style="padding:7px;"><span class="text-capital font_24">District LEVEL</span> - <span class="font_15">Board/Corporation Overview</span></span>
				<div id="districtWiseOverviewId"  style="margin-top: 15px"></div>
			</div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
			<div class="applicationsOverView">
			<span class="bar_view" style="background-color: #3788B3;"></span>
				<span class="headingColor" style="padding:7px;"><span class="text-capital font_24">Constituency LEVEL</span> - <span class="font_15">Board/Corporation Overview</span></span>
				<div id="assemblyWiseOverviewId"  style="margin-top: 15px"></div>
			</div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
			<div class="applicationsOverView">
			<span class="bar_view" style="background-color: #330099;"></span>
				<span class="headingColor" style="padding:7px;"><span class="text-capital font_24">Mandal/Muncipality/Corporation Level LEVEL</span> - <span class="font_15">Board/Corporation Overview</span></span>
				<div id="mandalORMunciWiseOverviewId"  style="margin-top: 15px"></div>
			</div>
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
	
	function getNominatdPostsOverview(divId,levelId,levelTxt){
		
		$('#'+divId+'').html('<img id="dataLoadingsImgForImagePath" src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		var stateId = $('input[name=stateName]:checked').val();
		if(stateId >1)
			stateId = 36;
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
				buildStatusDetails(result,levelId,divId,levelTxt);
		});  
	}
	
	function buildStatusDetails(result,levelId,divId,levelTxt){
		var str="";
		if(result != null && result.length>0){
			str+='<ul class="panelBlockCustom">';
			for(var i in result){
				if(result[i].name != "TOTAL AVAILABLE"){
				str+='<li style="font-size:12px">';
					str+='<div class="panel panel-default panelCustom ">';
					str+='<div class="panel-heading">';
					if(i>0){
					
						if(result[i].name == "TOTAL AVAILABLE"){
							
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_RUNNING_ENTITLEMENT' )}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_level_txt="'+levelTxt+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalPositions+'</u></span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:when> 
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:otherwise>
							</c:choose>
						}						
						else if(result[i].name == "YET TO START" || result[i].name == "RUNNING"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_RUNNING_ENTITLEMENT' )}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_level_txt="'+levelTxt+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalPositions+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:when> 
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:otherwise>
							</c:choose>
						}
							
						else if(result[i].name == "READY FOR FINAL REVIEW"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="finalReviewCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalPositions+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:otherwise>
							</c:choose>
						}
						else if(result[i].name == "FINALYZED"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="finalReviewCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalPositions+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:otherwise>
							</c:choose>
						}
						else if(result[i].name == "GO PASSED / COMPLETED"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="finalReviewCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalPositions+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:otherwise>
							</c:choose>
						}
						else{
							
							<!--str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';-->
							
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalPositions+'</u></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
								</c:otherwise>
							</c:choose>
							
						}
					}
					else{
						//str+='<h3>'+result[i].totalPositions+' </h3>';
						
						<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_RUNNING_ENTITLEMENT' )}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_level_txt="'+levelTxt+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].totalPositions+'</u></span><span class="pull-right text-muted">100.0%</span> </h3>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">100.0%</span> </h3>';
								</c:when> 
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">100.0%</span> </h3>';
								</c:otherwise>
							</c:choose>							
					}
					
					//str+='<p> '+result[i].name +' '+result[i].name+'</p>';
					str+='<p> '+result[i].name +'  POSITIONS  </p>';
					
					str+='</div>';
					str+='<div class="panel-body">';
					str+='<p>'+result[i].totalCorp+' Corporations </p>';					
					str+='</div>';
					str+='<div class="panel-footer">';
					str+='<p>'+result[i].totalDept+' Departments</p>';
					str+='</div>';
					str+='</div>';
				str+='</li>';
				}
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
	var levelTxt = $(this).attr("attr_level_txt");
	
	if(status == "TOTAL")//totalCorpCls
		var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=Total&levelTxt='+levelTxt+'','_blank');
	else if(status == "TOTAL AVAILABLE")//totalCorpCls
		var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=Open&levelTxt='+levelTxt+'','_blank');
	else if(status == "YET TO START")
		var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=notYet&levelTxt='+levelTxt+'','_blank');
	else if(status == "RUNNING")
		var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=running&levelTxt='+levelTxt+'','_blank');	
	else if(status == "APPLICATIONS NOT RECIEVED")
		var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=notRecieved&levelTxt='+levelTxt+'','_blank');	
});

$(document).on("click",".finalReviewCls",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	
	if(status == "READY FOR FINAL REVIEW")
		var redirectWindow=window.open('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=finalReview','_blank');
	else if(status == "FINALYZED")
		var redirectWindow=window.open('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=finaliZed','_blank');
	else if(status == "GO PASSED / COMPLETED")
		var redirectWindow=window.open('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=goPassed','_blank');
	
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
	
		getNominatdPostsOverview("stateWiseOverviewId",2,"state");
		getNominatdPostsOverview("districtWiseOverviewId",3,"district");
		getNominatdPostsOverview("assemblyWiseOverviewId",4,"constituency");
		getNominatdPostsOverview("mandalORMunciWiseOverviewId",5,"mandal");
		//getNominatdPostsOverview("villageORWardWiseOverviewId",7);
});
$('document').ready(function(){
	getNominatdPostsOverview("centralWiseOverviewId",1,"central");
	getNominatdPostsOverview("stateWiseOverviewId",2,"state");
	getNominatdPostsOverview("districtWiseOverviewId",3,"district");
	getNominatdPostsOverview("assemblyWiseOverviewId",4,"constituency");
	getNominatdPostsOverview("mandalORMunciWiseOverviewId",5,"mandal");
	//getNominatdPostsOverview("villageORWardWiseOverviewId",7);
		
	});
</script>
</body>
</html>
