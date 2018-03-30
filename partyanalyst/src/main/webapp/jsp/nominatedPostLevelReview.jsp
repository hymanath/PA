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
<title>Nominated post profile overview </title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<style>
.font_15{font-size:15px;}
.font_24{font-size: 20px;}
.bar_view{padding: 6px; float: left; height: 32px; width: 12px;  margin-left: -9px;}
.filterIcon{background-color:#4A5863;color:#fff;padding:5px;border-radius:50%;cursor:pointer;}
.list-inline li
{
	position:relative;
}
.list-inline li:hover .filterIconDiv
{
	display:block;
	transition:1s ease-in linear;
}
.filterIconDiv
{
	display:none;
	transition:1s ease-in linear;
}
.filterIconDiv
{
	position:absolute;
	right:0px;
	background-color:#fff;
	z-index:99;
	padding:5px;
}
.updateDropDownArrow::after, .updateDropDownArrow::before
{
	right:10px;
}
.filterIconDiv li
{
	list-style:none;
	padding:2px;
	margin-top:2px;
}
</style>
<body>
<div class="container">
    <div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
		 <h3 class="text-capital"  style="color:#5C2D25;">Nominated Posts Overview Details</h3>
        </div>
		<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >
			<div class="applicationsOverView">
			<div class="m_top10">
				<span class="bar_view" style="background-color: rgb(245, 129, 129);"></span>
				<span class="headingColor" style="padding:7px;"><span class="text-capital font_24">CENTRAL LEVEL</span> - <span class="font_15">Board/Corporation Overview</span></span>
				<div id="centralWiseOverviewId"  style="margin-top: 15px"></div>
			</div>
			</div>
        </div>
		<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
			<ul class="list-inline pull-right">
				<li>
					<i class="glyphicon glyphicon-filter filterIcon"></i>
					<ul style="margin-right:60px" class="pull-right filterIconDiv updateDropDownArrow">
					  <li> 
						<div  style="border-radius:15px;" class="btn btn-xs btn-success">
							<input type="radio" value="1" name="stateName" style="cursor:pointer;" class="stateCls" checked="true"/> <b>AP</b>
						</div>
					  </li> 
					  <li>
						<div style="border-radius:15px;" class="btn btn-xs btn-success">
							<input type="radio" value="2" name="stateName" style="cursor:pointer;" class="stateCls"/> <b>TS</b>
						</div>
					  </li>
					</ul>
				</li>
			</ul>
			<span style="margin-right:60px" class="pull-right">
				
				
			</span>
			
		</div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top10" >
			<div class="applicationsOverView">
			<span class="bar_view" style="background-color: #62B9A9;"></span>
				<span class="headingColor" style="padding:7px;"><span class="text-capital font_24">State LEVEL</span> - <span class="font_15">Board/Corporation Overview</span></span>
					
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
		<c:if test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_OVERVIEW_AC_MANDAL_VILLAGE_ACCESS_ENTITLEMENT')}">
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
		  <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div class="applicationsOverView">
				<span class="bar_view" style="background-color: #330099;"></span>
					<span class="headingColor" style="padding:7px;"><span class="text-capital font_24">Panchayat/Ward/Division Level </span> - <span class="font_15">Board/Corporation Overview</span></span>
					<div id="villageORWardWiseOverviewId"  style="margin-top: 15px"></div>
				</div>
			</div>
		</c:if>
         <!-- 
		 <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor"></h4>
				<div id="villageORWardWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
		-->
    </div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>

<script>
	
	function getNominatdPostsOverview(divId,levelId,levelTxt){
		
		//$('#'+divId+'').html('<img id="dataLoadingsImgForImagePath" src="images/Loading-data.gif" style="width:60px;height:50px;margin:auto;display:block"/>');
		$('#'+divId+'').html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var stateId = $('input[name=stateName]:checked').val();
		if(stateId >1)
			stateId = 36;
		if(levelId == 1){
			stateId =0;
		}
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
				var applicationsCount =0;
				if(result[i].totalApplicationReceivedCnt != null && result[i].totalApplicationReceivedCnt >0)
					applicationsCount = result[i].totalApplicationReceivedCnt;
				
				if(result[i].name != "TOTAL AVAILABLE"){
					if(result[i].name == "READY TO SHORT LIST" || result[i].name == "RUNNING")
						str+='<li style="font-size:12px;cursor:pointer;" class="newWindowCls" attr_level_id="'+levelId+'" attr_level_txt="'+levelTxt+'" attr_status="'+result[i].name+'" >';
					else if(result[i].totalPositions >0)
						str+='<li style="font-size:12px;cursor:pointer;" class="newWindowCls" attr_level_id="'+levelId+'" attr_level_txt="'+levelTxt+'" attr_status="'+result[i].name+'" >';
					else 
						str+='<li style="font-size:12px;cursor:pointer;">';
					str+='<p class="headingPanel"> '+result[i].name+'</p>';
					str+='<div class="panel panel-default panelCustom ">';
					
					str+='<div class="panel-heading">';
					if(i>0){
					
						if(result[i].name == "TOTAL AVAILABLE"){
							
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_RUNNING_ENTITLEMENT' )}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_level_txt="'+levelTxt+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:when> 
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:otherwise>
							</c:choose>
						}						
						else if(result[i].name == "READY TO SHORT LIST" || result[i].name == "RUNNING"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_RUNNING_ENTITLEMENT' )}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_level_txt="'+levelTxt+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:when> 
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:otherwise>
							</c:choose>
						}
							
						else if(result[i].name == "READY FOR FINAL REVIEW"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="finalReviewCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:otherwise>
							</c:choose>
						}
						else if(result[i].name == "FINALIZED"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="finalReviewCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:otherwise>
							</c:choose>
						}
						else if(result[i].name == "GO ISSUED / COMPLETED"){
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="finalReviewCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:otherwise>
							</c:choose>
						}
						else{
							
							<!--str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'">'+result[i].totalPositions+'</span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:15px;">Applications : <span id="">700</span></p>';-->
							
							<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_READY_TO_FINALYZE_ENTITLEMENT')}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-muted">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;"></p>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:when>
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">'+result[i].perc+'%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:otherwise>
							</c:choose>
							
						}
					}
					else{
						//str+='<h3>'+result[i].totalPositions+' </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+result[i].totalApplicationReceivedCnt+'</span></p>';
						
						<c:choose>
								<c:when test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_MOVE_TO_RUNNING_ENTITLEMENT' )}">
								if(result[i].totalPositions > 0)
									str+='<h3><span class="yetToStartCls" attr_level_id="'+levelId+'" attr_level_txt="'+levelTxt+'" attr_status="'+result[i].name+'" style="cursor:pointer;color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">100.0%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								else
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">100.0%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:when> 
								<c:otherwise>
									str+='<h3><span attr_level_id="'+levelId+'" attr_status="'+result[i].name+'" style="color:#70A4BE">'+result[i].totalPositions+'<span style="font-size:13px;"> Posts</span></span><span class="pull-right text-danger">100.0%</span> </h3><p style="color:#DDC4E2;text-align:center;font-size:14px;">Applications : <span id="">'+applicationsCount+'</span></p>';
								</c:otherwise>
							</c:choose>							
					}
					
					//str+='<p> '+result[i].name +' '+result[i].name+'</p>';
					
					
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

$(document).on("click",".newWindowCls",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	var levelTxt = $(this).attr("attr_level_txt");
	if(status == "TOTAL")//totalCorpCls
		window.location.replace('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=Total','_self');
	else if(status == "TOTAL AVAILABLE")//totalCorpCls
		window.location.replace('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=Open&levelTxt='+levelTxt+'','_self');
	else if(status == "READY TO SHORT LIST")
		window.location.replace('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=notYet&levelTxt='+levelTxt+'','_self');
	else if(status == "RUNNING")
		window.location.replace('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=running&levelTxt='+levelTxt+'','_self');	
	else if(status == "APPLICATIONS NOT RECIEVED")
		window.location.replace('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=notRecieved&levelTxt='+levelTxt+'','_self');	
	else if(status == "READY FOR FINAL REVIEW")
		var redirectWindow=window.open('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=finalReview','_self');
	else if(status == "FINALIZED")
		var redirectWindow=window.open('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=finaliZed','_self');
	else if(status == "GO ISSUED / COMPLETED")  
		var redirectWindow=window.open('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=goPassed','_self');
	
});
$(document).on("click",".yetToStartCls11",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	var levelTxt = $(this).attr("attr_level_txt");
	
	if(status == "TOTAL")//totalCorpCls
		window.location.replace('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=Total','_self');
	else if(status == "TOTAL AVAILABLE")//totalCorpCls
		window.location.replace('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=Open&levelTxt='+levelTxt+'','_self');
	else if(status == "READY TO SHORT LIST")
		window.location.replace('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=notYet&levelTxt='+levelTxt+'','_self');
	else if(status == "RUNNING")
		window.location.replace('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=running&levelTxt='+levelTxt+'','_self');	
	else if(status == "APPLICATIONS NOT RECIEVED")
		window.location.replace('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=notRecieved&levelTxt='+levelTxt+'','_self');	

});

$(document).on("click",".finalReviewCls11",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	
	if(status == "READY FOR FINAL REVIEW")
		window.location.replace('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=finalReview','_self');
	else if(status == "FINALIZED")
		window.location.replace('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=finaliZed','_self');
	else if(status == "GO ISSUED / COMPLETED")
		window.location.replace('nominatedReadyToFinalReviewAction.action?lId='+levelId+'&stId='+stateId+'&sts=goPassed','_self');
	
});
/*
$(document).on("click",".finalyzedCls",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	
	var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=finalyzed','_self');
});
$(document).on("click",".goPassedCls",function(){
	var levelId = $(this).attr("attr_level_id");
	var status = $(this).attr("attr_status");
	var stateId = $('input[name=stateName]:checked').val();
	
	var redirectWindow=window.open('nominatedPostManagementAction.action?lId='+levelId+'&stId='+stateId+'&sts=g_o_passed','_self');
});
*/

$(document).on("click",".stateCls",function(){
	
		getNominatdPostsOverview("stateWiseOverviewId",2,"state");
		getNominatdPostsOverview("districtWiseOverviewId",3,"district");
		getNominatdPostsOverview("assemblyWiseOverviewId",4,"constituency");
		getNominatdPostsOverview("mandalORMunciWiseOverviewId",5,"mandal");
		//getNominatdPostsOverview("villageORWardWiseOverviewId",7);
		getNominatdPostsOverview("villageORWardWiseOverviewId",7,"Panchayat");
});
$('document').ready(function(){
	 getNominatdPostsOverview("centralWiseOverviewId",1,"central");
	 getNominatdPostsOverview("stateWiseOverviewId",2,"state");
	 getNominatdPostsOverview("districtWiseOverviewId",3,"district");
	 getNominatdPostsOverview("assemblyWiseOverviewId",4,"constituency");
	 getNominatdPostsOverview("mandalORMunciWiseOverviewId",5,"mandal");
	 //getNominatdPostsOverview("villageORWardWiseOverviewId",7);
	getNominatdPostsOverview("villageORWardWiseOverviewId",7,"Panchayat");
		
	});
</script>
</body>
</html>
