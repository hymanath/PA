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
		</div>
	</div>
</div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">

var globalBfIdStr =" ";
var globalCategoryIds ="1,2";

var localBfIdStr = "${param.bfIds}";//benefitIds
var localCategoryIds = "${param.catIds}";//categoryIds

if(localBfIdStr != null && localBfIdStr.length>0){
	globalBfIdStr = localBfIdStr;
}

if(localCategoryIds != null && localCategoryIds.length>0){
	globalCategoryIds = localCategoryIds;
}

/* var globalOrgType = "${param.dept}";//isDepartment
var globalOrgIdStr = "${param.ogIds}";//OrganizationIds
var globalUserAccessLevelId = "${param.lid}";//LevelId
var globalLevelValue = "${param.lvl}";//levelValue
var globalStartDate = "${param.sdat}";//startDate
var globalEndDate = "${param.edat}";//endDate
var globalImpactScopeIdsStr = "${param.scops}";//impactScopeIds
var globalNewsChannelIds = "${param.chnIds}";//News ChannelIds
var globalStIndex = "${param.stIdx}";//startIndex
var globalEndIndex = "${param.edIdx}";//endIndex
var globalSearchType = "${param.schType}"; */


 var globalOrgType = "N";//isDepartment
var globalOrgIdStr = "872,1117,163,362";//OrganizationIds
var globalUserAccessLevelId = "2";//LevelId
var globalLevelValue = "1";//levelValue
var globalStartDate = "01-01-2016";//startDate
var globalEndDate = "16-11-2016";//endDate
var globalImpactScopeIdsStr = "1,2,3,4,5,6,7,8,9";//impactScopeIds
var globalNewsChannelIds = "1,2,3,4,5,6,7";//News ChannelIds
var globalStIndex = "0";//startIndex
var globalEndIndex = "2";//endIndex
var globalSearchType = "category";


	newsBulletinPointBasicDetailsBulletinsOfOrganization()
	function newsBulletinPointBasicDetailsBulletinsOfOrganization(){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		//var jsObj = {parentUserTypeId : globalUserTypeId}
		//{userAccessLevelId}/{userAccessLevelValuesArray}/{startDate}/{endDate}/{channelIdsStr}/{impactScopeIdsStr}" +
			//"/{orgIdStr}/{isDept}/{searchType}/{bfIdsStr}/{catIdsStr}/{startIndex}/{endIndex}
			
			var searchType="category";

		$.ajax({
			type : 'GET',
			//url: wurl+"/CommunityNewsPortal/webservice/getNewsBulletinPointBasicDetails/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N"
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBulletinPointBasicDetailsBulletinsOfOrganization/"+globalUserAccessLevelId+"/"+globalLevelValue+"/"+globalStartDate+"/"+globalEndDate+"/"+globalNewsChannelIds+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalSearchType+"/"+globalBfIdStr+"/"+globalCategoryIds+"/"+globalStIndex+"/"+globalEndIndex+"/"
		}).then(function(result){
			buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result);
		});		
	}
	function buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result)
	{
		var str='';
		for(var i in result)
		{
			if(i == 0 || i%3 == 0){
				str+='<div class="row deleteRow">';
			}
			str+='<div class="col-md-4 col-xs-12 col-sm-6">';
				str+='<span class="panelHeading">'+result[i].channelName+'</span>';
				str+='<div class="panel panel-default panelEMNPopup">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">'+result[i].title+'</h4>';
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								//str+='<span attr_point_id="'+result[0].tvNewsBulletinPointId+'" attr_type="tvnews" class="pull-right deleteTvNewsPoint" style="cursor:pointer;"><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/removeIcon.png" style="width: 30px; height: 30px;"></span>';
									str+='<p><b>News Description:</b></p>';
									if(result[i].description == null || result[i].description == 0){
										str+='<p> - </p>';
									}else{
										if(result.description !=null && result[0].description.length>450){
											str+='<h4 class="modal-title" style="cursor:pointer;" data-toggle="tooltip" data-placement="bottom" title="'+result[i].description+'">'+result[i].description.substring(0,450)+'...</h4>';
										}else{
											str+='<h4 class="modal-title">'+result[i].description+'</h4>';
										}
									}
									
							str+='</div>';
						str+='</div>';
						str+='<div class="m_top10">';
							str+='<div>';
								if(result[i].candidates != null && result[i].candidates.length > 0){
									str+='<label class="font_fff m_top10" >From Whom</label>';
									for(var j in result[i].candidates){
										if(result[i].candidates[j].fromList != null && result[i].candidates[j].fromList.length > 0){
											for(var k in result[i].candidates[j].fromList){
												str+='<div class="pad_5 bg_ff color333" >';
												if(result[i].candidates[j].fromList[k].organizationTypeId == 2){
													if(result[i].candidates[j].fromList[k].organizationTypeId == null || result[i].candidates[j].fromList[k].organizationTypeId ==0){
														str+='<span ><img   src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														str+='&nbsp;&nbsp; - ';
													}else{
														str+='<span ><img   src="https://www.mytdp.com/CommunityNewsPortal/Party_logos_small/'+result[i].candidates[j].fromList[k].organizationTypeId+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														if(result[i].candidates[j].fromList[k].organizationName == null || result[i].candidates[j].fromList[k].organizationName == 0){
															str+='&nbsp;&nbsp; - ';
														}else{
															str+='&nbsp;&nbsp;'+result[i].candidates[j].fromList[k].organizationName+'';
														}
														
													}
													
												}else{
													if(result[i].candidates[j].fromList[k].organizationName == null || result[i].candidates[j].fromList[k].organizationName == 0){
														str+='<span ><img   src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														str+='&nbsp;&nbsp; - ';
													}else{
														str+='<span ><img   src="Party_logos_small/'+result[i].candidates[j].fromList[k].organizationName+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														str+='&nbsp;&nbsp;'+result[i].candidates[j].fromList[k].organizationName+'';
													}
													
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
												str+='<div class="pad_5 bg_ff color333" >';
												if(result[i].candidates[j].toList[k].organizationTypeId == 2){
													if(result[i].candidates[j].toList[k].organizationTypeId == null || result[i].candidates[j].toList[k].organizationTypeId == 0){
														str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														str+='&nbsp;&nbsp; - </span>';
													}else{
														str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/Party_logos_small/'+result[i].candidates[j].toList[k].organizationTypeId+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														if(result[i].candidates[j].toList[k].organizationName == null || result[i].candidates[j].toList[k].organizationName ==0){
															str+='&nbsp;&nbsp; - </span>';
														}else{
															str+='&nbsp;&nbsp;'+result[i].candidates[j].toList[k].organizationName+'</span>';
														}
														
													}
													
													
												}else{
													if(result[i].candidates[j].toList[k].organizationName == null || result[i].candidates[j].toList[k].organizationName == 0){
														str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/ElectronicMedia/img/empty.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														str+='&nbsp;&nbsp; - </span>';
													}else{
														str+='<span ><img src="https://www.mytdp.com/CommunityNewsPortal/Party_logos_small/'+result[i].candidates[j].toList[k].organizationName+'.png" class="img-responsive" alt="" style="height:25px;width:25px;display:inline-block"/>';
														str+='&nbsp;&nbsp;'+result[i].candidates[j].toList[k].organizationName+'</span>';
													}
													
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
									str+='<label class="font_fff m_top10" >From Whom</label>';
									str+='No Data Availabel';
									str+='<label class="font_fff m_top10" >To Whom</label>';
									str+='No Data Availabel';
									
								}
								
								str+='<label class="font_fff m_top10">News Details-time & Program Wise</label>';
								
								if(result[i].timings != null && result[i].timings.length > 0){
									for(var l in result[i].timings){
										str+='<p style="text-align: center; color: rgb(255, 255, 255); font-weight: bold;">----'+result[0].timings[l].bulletinDate+'----</p>';
										str+='<table class="table table-bordered  bg_ff color333">';
										str+='<thead>';
											str+='<th>Program Time</th>';
											str+='<th>Program Name</th>';
											str+='<th>In TIme</th>';
											str+='<th>Out Time</th>';
											str+='<th>Total Time</th>';
										str+='</thead>';
										str+='<tbody>';
										
										if(result[i].timings[l].timeList != null && result[i].timings[l].timeList.length > 0){
											for(var m in result[i].timings[l].timeList){
												str+='<tr>';
												if(result[i].timings[l].timeList[m].bulletinStartTime == null || result[i].timings[l].timeList[m].bulletinStartTime == 0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].timings[l].timeList[m].bulletinStartTime+'</td>';
												}
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
								
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			if(i == 2 || i%3 == 2){
				str+='</div>';
			}
		}
		
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html(str);
	}
				
				
</script>
</body>
</html>