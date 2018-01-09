<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Electronic Meida News Bulletin</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="newCoreDashBoard/css/simplePagination.css"/>
<style type="text/css">
.panelEMNPopup
{
	border: 2px solid #FFC21F;
	border-radius: 0px;
	margin-bottom: 0px;
}
.panelEMNPopup .panel-heading
{
	background-color: #EEEEEE;
	border-radius: 0px;
	border-bottom: 2px solid #ffc21f;
}
.panelEMNPopup .panel-body
{
	background-color: #666666;
	color: #fff !important;
}
.pad_5
{
	padding: 5px;
}
.panelHeading
{
	color:#333;
	background-color:#FFC21F;
}
.bg_ff
{
	background-color: #fff;
}
.color333
{
	color:#000000
}
.popupLabel {
	font-size: 12px;
}

.fromWhomUl li img{
	display: inline-block;
	height: 15px;
	width: 15px;
	margin-right: 5px;
}
.fromWhomUl li:last-child
{
	border-right: 0px;
}
.fromWhomUl li
{
	border-right: 1px solid #333;
	color: #333
}

.m_top20 {
	margin-top: 20px;
}

.m_top10 {
	margin-top: 10px;
}

.text-capital {
	text-transform: uppercase;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div id="newsBulletinPointBasicDetailsBulletinsOfOrganization"></div>
			<div class="row ">
				<div class="col-md-10 col-md-offset-5 m_top20">
					<div class="paginationId"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="newCoreDashBoard/js/simplePagination3.js" ></script>
<script type="text/javascript">

var globalBfIdStr =" ";
var globalCategoryIds =" ";

var localBfIdStr = "${param.bfIds}";//benefitIds
var localCategoryIds = "${param.ediDistIdsStr}";//categoryIds

if(localBfIdStr != null && localBfIdStr.length>0){
	globalBfIdStr = localBfIdStr;
}

if(localCategoryIds != null && localCategoryIds.length>0){
	globalCategoryIds = localCategoryIds;
}

var globalUserAccessLevelId = "${param.levelId}";//LevelId
var globalLevelValue = "${param.temp}";//levelValue
var state="${param.state}";
var globalStartDate = "${param.sdat}";//startDate
var globalEndDate = "${param.edat}";//endDate
var globalOrgIdStr = "${param.orgIdStr}";//OrganizationIds
var globalOrgType = "${param.orgType}";//isDepartment
var globalImpactScopeIdsStr = "${param.scops}";//impactScopeIds
var globalSearchType = "${param.status}"; 

var globalNewsChannelIds = "${param.npsStr}";//News ChannelIds
var globalStIndex = "${param.stIdx}";//startIndex
var globalEndIndex = "${param.edIdx}";//endIndex
var callFrom = "${param.callFrom}";

var globalcategoryIdP = "${param.categoryId}";
var globalorganizationId = "${param.organizationId}";
var globalbenefitId = "${param.benefitId}";
var globalstartIndexP = "${param.startIndex}";
var globalendIndexP = "${param.endIndex}";
var globaltype = "${param.type}";
var globalPartyId = "${param.partyId}";

/*  var globalOrgType = "N";//isDepartment
var globalOrgIdStr = "872,1117,163,362";//OrganizationIds
var globalUserAccessLevelId = "2";//LevelId
var globalLevelValue = "1";//levelValue
var globalStartDate = "01-01-2016";//startDate
var globalEndDate = "16-11-2016";//endDate
var globalImpactScopeIdsStr = "1,2,3,4,5,6,7,8,9";//impactScopeIds
var globalNewsChannelIds = "1,2,3,4,5,6,7";//News ChannelIds
var globalStIndex = "0";//startIndex
var globalEndIndex = "2";//endIndex
var globalSearchType = "category"; */

var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	 if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

$(document).ready(function(){
	setTimeout(function(){
	if(callFrom == "bad"){
		newsBulletinPointBasicDetailsBulletinsOfOrganization(0); 
	}else if(callFrom == "pds"){
		getEMMMediaProgramsOnPartyProgramsWiseBulletins(0);
	}else if(callFrom == "dpdpd"){
		getEMMDetailedPartyDistrictWiseProgramsBulletinsOverview(0);
	}else if(callFrom == "dpdwd"){
		getEMMDetailedPartiesVsChannelsPartiesDistrictWiseBulletins(0);
	}else if(callFrom == "dpcwd"){
		getEMMDetailedPartiesVsChannelsTvChannelWiseBulletins(0);
	}else if(callFrom == "dgpdsb"){
		getEMMDetailedGovtProblemsDetailedOverviewOfStateBulletins(0);
	}else if(callFrom == "dgpdcb"){
		getEMMDetailedGovtProblemsDetailedOverviewChannelWiseBulletins(0);
	}else if(callFrom == "dgpdwp"){
		getEMMDetailedGovtProblemsDetailedOverviewDepartmentWiseBulletins(0);
	}else if(callFrom == "dgpdiswd"){
		getEMMDetailedGovtProblemsDetailedOverviewBulletins(0);
	}else if(callFrom == "prajaSankalpa"){
		if(globaltype == "mainBlock"){
			getChannelWisePartiesAnalysisInfoForClick(0);
		}else if(globaltype == "partyWiseCnt"){
			getChannelWisePartiesAnalysisForClick(0);
		}else if(globaltype == "distWiseCnt"){
			getDistrictWisePartyViewForElectrronicMediaInfoForClik(0);
		}
	}else if(callFrom == "EMCT"){
		getCoverageTimeOfTvNewsBulletinPointClick(0);
	}
		
	}, 1000);
	
});
	
	
	function newsBulletinPointBasicDetailsBulletinsOfOrganization(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
			type : 'GET',			
			url: wurl+"/CommunityNewsPortal/webservice/getNewsBulletinPointBasicDetailsBulletinsOfOrganization/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalSearchType+"/"+globalBfIdStr+"/"+globalCategoryIds+"/"+globalStIndex+"/"+globalEndIndex+"/"
			
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBulletinPointBasicDetailsBulletinsOfOrganization/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalSearchType+"/"+globalBfIdStr+"/"+globalCategoryIds+"/"+globalStIndex+"/"+globalEndIndex+"/"
		}).then(function(result){
			var countByDate=0;
			buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});		
	}
	function buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate)
	{
		var str='';		
		if(result !=null && result.length>0){
			if(globalStIndex == 0){
					countByDate=result[0].totalBulletins;
			}
		for(var i in result)
		{
			if(i == 0 || i%3 == 0){
				str+='<div class="row deleteRow">';
			}
			str+='<div class="col-md-4 col-xs-12 col-sm-6 m_top10">';
				str+='<span class="panelHeading pad_5 "><img   src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/'+result[i].channelName+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>&nbsp; '+result[i].channelName+'</span>';
				str+='<div class="panel panel-default panelEMNPopup" >';
					str+='<div class="panel-heading">';
					if(result[i].title !=null && result[i].title.length >30){
						str+='<h4 class="panel-title" style="cursor:pointer;font-size: 16px;" data-toggle="tooltip" data-placement="bottom" title="'+result[i].title+'">'+result[i].title.substring(0,30)+'...</h4>';
					}else{
						str+='<h4 class="panel-title">'+result[i].title+'</h4>';
					}
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								//str+='<span attr_point_id="'+result[0].tvNewsBulletinPointId+'" attr_type="tvnews" class="pull-right deleteTvNewsPoint" style="cursor:pointer;"><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/removeIcon.png" style="width: 30px; height: 30px;"></span>';
									str+='<p><b>News Description:</b></p>';
									if(result[i].description == null || result[i].description == 0){
										str+='<p> - </p>';
									}else{
										if(result[i].description !=null && result[i].description.length >120){
											str+='<span class="modal-title" style="cursor:pointer;font-size: 16px;" data-toggle="tooltip" data-placement="bottom" title="'+result[i].description+'">'+result[i].description.substring(0,120)+'...</span>';
										}else{
											str+='<span class="modal-title" style="font-size: 16px;">'+result[i].description+'</span>';
										}
									}
									
							str+='</div>';
							str+='<span class="arrowChange pull-right" style="border:none;color:#fff;top:0px;"><i class="glyphicon glyphicon-plus bulletinDetailsCls "  attr_id="showFromToBlockDiv'+i+'" style="margin-right: 13px; border: 1px solid black; padding: 5px; margin-top: -5px;cursor:pointer;"/>';
						str+='</div>';
						str+='<div class="m_top10 bulletinShowDivs" style="display:none;" id="showFromToBlockDiv'+i+'" >';
							str+='<div>';
								if(result[i].candidates != null && result[i].candidates.length > 0){
									str+='<label class="font_fff m_top10" >From Whom</label>';
									for(var j in result[i].candidates){
										if(result[i].candidates[j].fromList != null && result[i].candidates[j].fromList.length > 0){
											for(var k in result[i].candidates[j].fromList){
												str+='<div class="pad_5 bg_ff color333 m_top10" >';
												if(result[i].candidates[j].fromList[k].organizationTypeId == 2){
													str+='<p class="bg_ff color333">';
														if(result[i].candidates[j].fromList[k].organizationTypeId == null || result[i].candidates[j].fromList[k].organizationTypeId == ""){
															str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														}else{
															str+='<span ><img   src="https://www.mytdp.com/CommunityNewsPortal/Party_logos_small/'+result[i].candidates[j].fromList[k].organizationTypeId+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														}
														if(result[i].candidates[j].fromList[k].organizationName == null || result[i].candidates[j].fromList[k].organizationName == ""){
															str+='&nbsp;&nbsp; - |';
														}else{
															str+='&nbsp;'+result[i].candidates[j].fromList[k].organizationName+' |';
														}
														if(result[i].candidates[j].fromList[k].candidateName == null || result[i].candidates[j].fromList[k].candidateName == ""){
															str+='&nbsp;&nbsp; - ';
														}else{
															str+='&nbsp;'+result[i].candidates[j].fromList[k].candidateName+' | ';
														}
														if(result[i].candidates[j].fromList[k].benefit == null || result[i].candidates[j].fromList[k].benefit ==""){
															str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
														}else{
															str+='&nbsp;<img  src="images/'+result[i].candidates[j].fromList[k].benefit+'.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
														}
														
													str+='</p>';
													
													
													}else{
														str+='<p class="bg_ff color333 pad_5">';
														if(result[i].candidates[j].fromList[k].organizationName == null || result[i].candidates[j].fromList[k].organizationName == ""){
															str+='<span ><img   src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
															str+='&nbsp;&nbsp; - ';
														}else{
															str+='<span ><img   src="https://www.mytdp.com/CommunityNewsPortal/Party_logos_small/'+result[i].candidates[j].fromList[k].organizationName+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														}
															if(result[i].candidates[j].fromList[k].organizationName == null || result[i].candidates[j].fromList[k].organizationName == ""){
																str+='&nbsp;&nbsp; - |';
															}else{
																str+='&nbsp;'+result[i].candidates[j].fromList[k].organizationName+' |';
															}
															if(result[i].candidates[j].fromList[k].candidateName == null || result[i].candidates[j].fromList[k].candidateName == ""){
																str+='&nbsp;&nbsp; - ';
															}else{
																str+='&nbsp;'+result[i].candidates[j].fromList[k].candidateName+' | ';
															}
															if(result[i].candidates[j].fromList[k].benefit == null || result[i].candidates[j].fromList[k].benefit ==""){
																str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
															}else{
																str+='&nbsp;<img  src="images/'+result[i].candidates[j].fromList[k].benefit+'.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
															}
															
															str+='</p>';
															
														
														
													}
												
												 
														
												str+='</div>';
												
												//str+='<p>';
												//str+=' | '+result[0].candidates[j].fromList[k].designation+'';
												//str+=' | '+result[0].candidates[j].fromList[k].candidateName+'';
												//str+=' | '+result[0].candidates[j].fromList[k].benefit+'';
												//str+=' | '+result[0].candidates[j].fromList[k].impactLevel+'';
												//str+='</p>';
											}
										}
									}
									str+='<label class="font_fff m_top10" >To Whom</label>';
									for(var j in result[i].candidates){
										if(result[i].candidates[j].toList != null && result[i].candidates[j].toList.length > 0){
											for(var k in result[i].candidates[j].toList){
												str+='<div class="pad_5 bg_ff color333 m_top10" >';
												if(result[i].candidates[j].toList[k].organizationTypeId == 2){
														str+='<p class="bg_ff color333 pad_5">';
														if(result[i].candidates[j].toList[k].organizationTypeId == null || result[i].candidates[j].toList[k].organizationTypeId == ""){
															str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														}else{
															str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/Party_logos_small/'+result[i].candidates[j].toList[k].organizationTypeId+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														}
														
														if(result[i].candidates[j].toList[k].organizationName == null || result[i].candidates[j].toList[k].organizationName == ""){
															str+='&nbsp;&nbsp; - |';
														}else{
															str+='&nbsp;'+result[i].candidates[j].toList[k].organizationName+' |';
														}
														if(result[i].candidates[j].toList[k].candidateName == null || result[i].candidates[j].toList[k].candidateName == ""){
															str+='&nbsp;&nbsp; - ';
														}else{
															str+='&nbsp;'+result[i].candidates[j].toList[k].candidateName+' | ';
														}
														if(result[i].candidates[j].toList[k].benefit == null || result[i].candidates[j].toList[k].benefit ==""){
															str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
														}else{
															str+='&nbsp;<img  src="images/'+result[i].candidates[j].toList[k].benefit+'.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
														}
														
														str+='</p>';
														
														
													
													
													
												}else{
													str+='<p class="bg_ff color333 pad_5">';
													if(result[i].candidates[j].toList[k].organizationName == null || result[i].candidates[j].toList[k].organizationName == ""){
														str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														
													}else{
														str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/Party_logos_small/'+result[i].candidates[j].toList[k].organizationName+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
													}
														if(result[i].candidates[j].toList[k].organizationName == null || result[i].candidates[j].toList[k].organizationName == ""){
															str+='&nbsp;&nbsp; - |';
														}else{
															str+='&nbsp;'+result[i].candidates[j].toList[k].organizationName+' |';
														}
														if(result[i].candidates[j].toList[k].candidateName == null || result[i].candidates[j].toList[k].candidateName == ""){
															str+='&nbsp;&nbsp; - ';
														}else{
															str+=''+result[i].candidates[j].toList[k].candidateName+' | ';
														}
														if(result[i].candidates[j].toList[k].benefit == null || result[i].candidates[j].toList[k].benefit ==""){
															str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
														}else{
															str+='<img   src="images/'+result[i].candidates[j].toList[k].benefit+'.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
														}
														
													str+='</p>';
												}
													
													
												str+='</div>';
												
												/* str+='<p>';
												str+='<img src="ElectronicMedia/img/'+result[0].candidates[j].toList[k].organizationName+'.png">';
												str+=''+result[0].candidates[j].toList[k].organizationName+'';
												str+=' | '+result[0].candidates[j].toList[k].designation+'';
												str+=' | '+result[0].candidates[j].toList[k].candidateName+'';
												str+=' | '+result[0].candidates[j].toList[k].benefit+'';
												str+=' | '+result[0].candidates[j].toList[k].impactLevel+'';
												str+='</p>'; */
											}
										}
									}
								}else{
									str+='<label class="font_fff m_top10" >From Whom</label><br/>';
									str+='No Data Availabel<br/>';
									str+='<label class="font_fff m_top10" >To Whom</label><br/>';
									str+='No Data Availabel<br/>';
									
								}
								
								str+='<label class="font_fff m_top10">News Details-time & Program Wise</label>';
								
								if(result[i].timings != null && result[i].timings.length > 0){
									for(var l in result[i].timings){
										str+='<p style="text-align: center; color: rgb(255, 255, 255); font-weight: bold;">----'+result[0].timings[l].bulletinDate+'----</p>';
										str+='<table class="table table-bordered  bg_ff color333">';
										str+='<thead>';
											//str+='<th>Program Time</th>';
											str+='<th>Program Name</th>';
											str+='<th>In TIme</th>';
											str+='<th>Out Time</th>';
											str+='<th>Total Time</th>';
										str+='</thead>';
										str+='<tbody>';
										
										if(result[i].timings[l].timeList != null && result[i].timings[l].timeList.length > 0){
											for(var m in result[i].timings[l].timeList){
												str+='<tr>';
												/* if(result[i].timings[l].timeList[m].bulletinStartTime == null || result[i].timings[l].timeList[m].bulletinStartTime == 0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].timings[l].timeList[m].bulletinStartTime+'</td>';
												} */
												if(result[i].timings[l].timeList[m].newsBulletinName == null || result[i].timings[l].timeList[m].newsBulletinName == 0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].timings[l].timeList[m].newsBulletinName+'</td>';
												}
												if(result[i].timings[l].timeList[m].bulletinPointStartTime == null || result[i].timings[l].timeList[m].bulletinPointStartTime == 0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].timings[l].timeList[m].bulletinPointStartTime+'</td>';
												}
												if(result[i].timings[l].timeList[m].bulletinPointEndTime == null || result[i].timings[l].timeList[m].bulletinPointEndTime == 0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].timings[l].timeList[m].bulletinPointEndTime+'</td>';
												}
												if(result[i].timings[l].timeList[m].drivenBy == null || result[i].timings[l].timeList[m].drivenBy ==0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].timings[l].timeList[m].drivenBy+'</td>';
												}
												
												str+='</tr>';
											}
										}
										str+='</tbody>';
										str+='</table>';
										
									}
									
								}else{
									str+='<h4>No Data Availabel.</h4>';
								}
								
								str+='<label class="font_fff m_top10" >Article Tracking</label>';
								str+='<div class="pad_5 bg_ff" style="color:#000;">';
								if(result[i].trackingLocationScopeId == null || result[i].trackingLocationScopeId == 0){
									str+='<span>Tracking Location Scope : - </span>';
								}else{
									if(result[i].trackingLocationScopeId == 1){
										str+='<span>Tracking Location Scope : State</span>';
									}
									if(result[i].trackingLocationScopeId == 2){
										str+='<span>Tracking Location Scope : District</span>';
									}
								}
								str+='</div>';
								
								str+='<div class="pad_5 bg_ff" style="color:#000;margin-top:4px;">';
								
								if(result[i].trackingLocationLevelId == null || result[i].trackingLocationLevelId ==0){
										str+='<span>Tracking Location       :  - </span>';
								}else{
									if(result[i].trackingLocationLevelList !=null && result[i].trackingLocationLevelList.length>0){
										for(var j in result[i].trackingLocationLevelList){
											if(result[i].trackingLocationLevelId == result[i].trackingLocationLevelList[j].categoryId){
												str+='<span>Tracking Location       : '+result[i].trackingLocationLevelList[j].category+'</span>';
											}
										}
									}
									
								}
									
											
								str+='</div>';
								
								str+='<div class="pad_5 bg_ff" style="color:#000;margin-top:4px;">';
								
								if(result[i].trackingLabelId == null || result[i].trackingLabelId ==0){
										str+='<span>Label Name       :  - </span>';
								}else{
									if(result[i].trackingLablesList !=null && result[i].trackingLablesList.length>0){
										for(var j in result[i].trackingLablesList){
											if(result[i].trackingLabelId == result[i].trackingLablesList[j].categoryId){
												str+='<span>Label Name       : '+result[i].trackingLablesList[j].category+'</span>';
											}
										}
									}
									
								}
								
								str+='</div>';
								str+='<label class="font_fff m_top10" >Article Characteristics</label>';
								str+='<div class="pad_5 bg_ff" style="color:#000;">';
								if(result[i].isImportant == 'Y'){
									str+='<span>Is Important       : YES  </span>';
								}else{
									str+='<span>Is Important       : -  </span>';
								}
								
								str+='</div>';
								str+='<div class="pad_5 bg_ff" style="color:#000;margin-top:4px;">';
								if(result[i].isActionable == 'Y'){
									str+='<span>Actionable       :  YES </span>';
								}else{
									str+='<span>Actionable       :  -  </span>';
								}
									
								str+='</div>';
								
								str+='<label class="font_fff m_top10" >Location Details</label>';
								str+='<div class="pad_5 bg_ff" style="color:#000;margin-top:4px;">';
								if(result[i].idNameVO.emailString == null || result[i].idNameVO.emailString == 0){
									str+='<span>Impact Scope       :  -  </span>';
								}else{
									str+='<span>Impact Scope       : '+result[i].idNameVO.emailString+' </span>';
								}
									
								str+='</div>';
								str+='<div class="pad_5 bg_ff" style="color:#000;margin-top:4px;">';
								if(result[i].idNameVO.name == null || result[i].idNameVO.name == 0){
									str+='<span>Location       :  -  </span>';
								}else{
									str+='<span>Location       : '+result[i].idNameVO.name+' </span>';
								}
									
								str+='</div>';
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			if(i == 2 || i%3 == 2){
				str+='</div>';
			}
		}
		if(globalStIndex == 0 && countByDate > 6){
				$(".paginationId").pagination({
					items: countByDate,
					itemsOnPage: 6,
					cssStyle: 'light-theme',
					hrefTextPrefix: '#pages-',
					onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*6;
							if(callFrom == "bad"){
								newsBulletinPointBasicDetailsBulletinsOfOrganization(num);	
							}else if(callFrom == "pds"){
								getEMMMediaProgramsOnPartyProgramsWiseBulletins(num);
							}else if(callFrom == "dpdpd"){
								getEMMDetailedPartyDistrictWiseProgramsBulletinsOverview(num);
							}else if(callFrom == "dpdwd"){
								getEMMDetailedPartiesVsChannelsPartiesDistrictWiseBulletins(num);
							}else if(callFrom == "dpcwd"){
								getEMMDetailedPartiesVsChannelsTvChannelWiseBulletins(num);
							}else if(callFrom == "dgpdsb"){
								getEMMDetailedGovtProblemsDetailedOverviewOfStateBulletins(num);
							}else if(callFrom == "dgpdcb"){
								getEMMDetailedGovtProblemsDetailedOverviewChannelWiseBulletins(num);
							}else if(callFrom == "dgpdwp"){
								getEMMDetailedGovtProblemsDetailedOverviewDepartmentWiseBulletins(num);
							}else if(callFrom == "dgpdiswd"){
								getEMMDetailedGovtProblemsDetailedOverviewBulletins(num);
							}else if(callFrom == "prajaSankalpa"){
								if(globaltype == "mainBlock"){
									getChannelWisePartiesAnalysisInfoForClick(num);
								}else if(globaltype == "partyWiseCnt"){
									getChannelWisePartiesAnalysisForClick(num);
								}else if(globaltype == "distWiseCnt"){
									getDistrictWisePartyViewForElectrronicMediaInfoForClik(num);
								}
							}else if(callFrom == "EMCT"){
								getCoverageTimeOfTvNewsBulletinPointClick(num);
							}
					}					
				});
			}
		
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html(str);
		$('[data-toggle="tooltip"]').tooltip();
		
	 }
	}
		
	$(document).on("click",".bulletinDetailsCls",function(){
		
		if($(this).hasClass("glyphicon-plus")){
			$(".bulletinDetailsCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$(this).removeClass("glyphicon-plus").addClass("glyphicon-minus");
		}else{
			$(this).removeClass("glyphicon-minus").addClass("glyphicon-plus");
		}
		
		
		var id= $(this).attr("attr_id");
		$('.bulletinShowDivs').each(function(index) {
			
			if($(this).attr("id") == id){
			   $(this).toggle();
			}
		  else{
			   $(this).hide();
			}

		});
	 
	
	});
			
	function getEMMMediaProgramsOnPartyProgramsWiseBulletins(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getEMMMediaProgramsOnPartyProgramsWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMMediaProgramsOnPartyProgramsWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalStIndex+"/"+globalEndIndex
		
		}).then(function(result){
		  var countByDate=0;
		  buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});    
  }		
  
  function getEMMDetailedPartyDistrictWiseProgramsBulletinsOverview(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsBulletinsOverview/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalBfIdStr+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsBulletinsOverview/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalBfIdStr+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		}).then(function(result){
		  var countByDate=0;
		  buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});    
  }		
  
  function getEMMDetailedPartiesVsChannelsPartiesDistrictWiseBulletins(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
		  type : 'GET',      
		 url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsPartiesDistrictWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalBfIdStr+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		 //url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsPartiesDistrictWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalBfIdStr+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		}).then(function(result){
		  var countByDate=0;
		  buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});    
  }	
  function getEMMDetailedPartiesVsChannelsTvChannelWiseBulletins(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsTvChannelWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalBfIdStr+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsTvChannelWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalBfIdStr+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		}).then(function(result){
		  var countByDate=0;
		  buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});    
  }	
  function getEMMDetailedGovtProblemsDetailedOverviewOfStateBulletins(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverviewOfStateBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalBfIdStr+"/"+globalCategoryIds+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverviewOfStateBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalBfIdStr+"/"+globalCategoryIds+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		}).then(function(result){
		  var countByDate=0;
		  buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});    
  }	
  function getEMMDetailedGovtProblemsDetailedOverviewChannelWiseBulletins(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverviewChannelWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgType+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverviewChannelWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgType+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		}).then(function(result){
		  var countByDate=0;
		  buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});    
  }	
   function getEMMDetailedGovtProblemsDetailedOverviewDepartmentWiseBulletins(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverviewDepartmentWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverviewDepartmentWiseBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		}).then(function(result){
		  var countByDate=0;
		  buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});    
  }	
  function getEMMDetailedGovtProblemsDetailedOverviewBulletins(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverviewBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverviewBulletins/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalStIndex+"/"+globalEndIndex+"/"
		
		}).then(function(result){
		  var countByDate=0;
		  buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});    
  }
//Clicks Electronic Media
  function getElecMediaPSYOverAllBulletins(globalstartIndexP){
  	$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
  	$.ajax({
  		url: wurl+"/CommunityNewsPortal/webservice/getElecMediaPSYOverAllBulletins/"+globalcategoryIdP+"/"+globalbenefitId+"/"+globalstartIndexP+"/"+globalendIndexP+"/"+globalStartDate+"/"+globalEndDate
  		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getElecMediaPSYOverAllBulletins/"+globalcategoryIdP+"/"+globalbenefitId+"/"+globalstartIndexP+"/"+globalendIndexP+"/"+globalStartDate+"/"+globalEndDate
  	}).then(function(result){
  		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('');
  		var countByDate = 0;
  		buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalstartIndexP,countByDate)
  	});
  }
  function getEleMediaOrganizationWisePSYBulletins(globalstartIndexP){
  	$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
  	$.ajax({
  		url: wurl+"/CommunityNewsPortal/webservice/getEleMediaOrganizationWisePSYBulletins/"+globalcategoryIdP+"/"+globalorganizationId+"/"+globalbenefitId+"/"+globalstartIndexP+"/"+globalendIndexP+"/"+globalStartDate+"/"+globalEndDate
  		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEleMediaOrganizationWisePSYBulletins/"+globalcategoryIdP+"/"+globalorganizationId+"/"+globalbenefitId+"/"+globalstartIndexP+"/"+globalendIndexP+"/"+globalStartDate+"/"+globalEndDate
  	}).then(function(result){
  		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('');
  		var countByDate = 0;
  		buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalstartIndexP,countByDate)
  	});
  }
  function getEleMediaChannelWisePSYBulletins(globalstartIndexP){
  	$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
  	var globalpublicationId = globalorganizationId;
  	$.ajax({
  		url: wurl+"/CommunityNewsPortal/webservice/getEleMediaChannelWisePSYBulletins/"+globalcategoryIdP+"/"+globalpublicationId+"/"+globalbenefitId+"/"+globalstartIndexP+"/"+globalendIndexP+"/"+globalStartDate+"/"+globalEndDate
  		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEleMediaChannelWisePSYBulletins/"+globalcategoryIdP+"/"+globalpublicationId+"/"+globalbenefitId+"/"+globalstartIndexP+"/"+globalendIndexP+"/"+globalStartDate+"/"+globalEndDate
  	}).then(function(result){
  		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('');
  		var countByDate = 0;
  		buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalstartIndexP,countByDate)
  	});
  }
   function getChannelWisePartiesAnalysisInfoForClick(globalstartIndexP){
  	$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
  	$.ajax({
  		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfoForClick/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globalstartIndexP+"/"+globalEndIndex+"/"+globalorganizationId+"/"+globalbenefitId+"/"+globalNewsChannelIds+"/"+globalUserAccessLevelId+"/"+globalLevelValue
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfoForClick/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globalstartIndexP+"/"+globalEndIndex+"/"+globalorganizationId+"/"+globalbenefitId+"/"+globalNewsChannelIds+"/"+globalUserAccessLevelId+"/"+globalLevelValue
  	}).then(function(result){
  		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('');
  		var countByDate = 0;
  		buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalstartIndexP,countByDate)
  	}); 
  }
  function getDistrictWisePartyViewForElectrronicMediaInfoForClik(globalstartIndexP){
  	$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
  	$.ajax({
  		url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfoForClik/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globalstartIndexP+"/"+globalEndIndex+"/"+globalorganizationId+"/"+globalbenefitId+"/"+globalNewsChannelIds+"/"+globalUserAccessLevelId+"/"+globalLevelValue
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfoForClik/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globalstartIndexP+"/"+globalEndIndex+"/"+globalorganizationId+"/"+globalbenefitId+"/"+globalNewsChannelIds+"/"+globalUserAccessLevelId+"/"+globalLevelValue
  	}).then(function(result){
  		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('');
  		var countByDate = 0;
  		buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalstartIndexP,countByDate)
  	}); 
  }
  function getChannelWisePartiesAnalysisForClick(globalstartIndexP){
  	$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
  	$.ajax({
  		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisForClick/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globalstartIndexP+"/"+globalEndIndex+"/"+globalorganizationId+"/"+globalbenefitId+"/"+globalNewsChannelIds+"/"+globalUserAccessLevelId+"/"+globalLevelValue
		
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisForClick/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globalstartIndexP+"/"+globalEndIndex+"/"+globalorganizationId+"/"+globalbenefitId+"/"+globalNewsChannelIds+"/"+globalUserAccessLevelId+"/"+globalLevelValue
  	}).then(function(result){
  		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('');
  		var countByDate = 0;
  		buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalstartIndexP,countByDate)
  	}); 
  }
  function getCoverageTimeOfTvNewsBulletinPointClick(globalstartIndexP){
  	$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	if(globaltype == "party"){
		globalorganizationId =0;
	}else if(globaltype == "candidate"){
		globalPartyId =0;
	}
  	$.ajax({
  		url: wurl+"/CommunityNewsPortal/webservice/getCoverageTimeOfTvNewsBulletinPointClick/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globaltype+"/"+globalorganizationId+"/"+globalNewsChannelIds+"/"+globalbenefitId+"/"+globalOrgType+"/"+globalstartIndexP+"/"+globalEndIndex+"/"+globalPartyId
		
		//url: "http://localhost:8086/CommunityNewsPortal/webservice/getCoverageTimeOfTvNewsBulletinPointClick/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globaltype+"/"+globalorganizationId+"/"+globalNewsChannelIds+"/"+globalbenefitId+"/"+globalOrgType+"/"+globalstartIndexP+"/"+globalEndIndex+"/"+globalPartyId
  	}).then(function(result){
  		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('');
  		var countByDate = 0;
  		buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalstartIndexP,countByDate)
  	}); 
  }
</script>
</body>
</html>