<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Electronic Media news bulletin Points</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<link rel="stylesheet" type="text/css" href="Assests/Plugins/Scroller/simplePagination.css"/> 
<style>
.prev{
	 height: auto !important;
	 width:auto !important;
}
.next{
	 height: auto !important;
	 width:auto !important;
}
</style>
</head>
<body>
<main>
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
</main>
<div class="modal fade" id="myModalShowNew"></div>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/Plugins/Scroller/jquery.slimscroll.js" ></script>
<script type="text/javascript" src="Assests/Plugins/Scroller/simplePagination3.js" ></script>
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
var globaltype = "${param.type}";
var globalPartyId = "${param.partyId}";

var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	 if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

$(document).ready(function(){
	setTimeout(function(){
		if(callFrom == "bad"){
			newsBulletinPointBasicDetailsBulletinsOfOrganization(0); 
		}else if(callFrom == "electrincMediaFOrPrrws"){
			getDepartMentWiseAllNewsBulletinsAndProgramsClicks(0);
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
															str+='&nbsp;<img  src="Assests/newsImages/'+result[i].candidates[j].fromList[k].benefit+'.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
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
																str+='&nbsp;<img  src="Assests/newsImages/'+result[i].candidates[j].fromList[k].benefit+'.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
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
															str+='&nbsp;<img  src="Assests/newsImages/'+result[i].candidates[j].toList[k].benefit+'.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
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
															str+='<img   src="Assests/newsImages/'+result[i].candidates[j].toList[k].benefit+'.png" class="img-responsive" alt="" style="height:17px;width:17px;display:inline-block"/>';
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
							}else if(callFrom == "electrincMediaFOrPrrws"){
								getDepartMentWiseAllNewsBulletinsAndProgramsClicks(num);
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
function getDepartMentWiseAllNewsBulletinsAndProgramsClicks(globalStIndex){
		$("#newsBulletinPointBasicDetailsBulletinsOfOrganization").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');

		$.ajax({
			type : 'GET',			
			url: wurl+"/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndProgramsClicks/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globalbenefitId+"/"+globalorganizationId+"/"+globalStIndex+"/"+globalEndIndex
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndProgramsClicks/"+globalStartDate+"/"+globalEndDate+"/"+globalcategoryIdP+"/"+globalbenefitId+"/"+globalorganizationId+"/"+globalStIndex+"/"+globalEndIndex
		}).then(function(result){
			var countByDate=0;
			buildnewsBulletinPointBasicDetailsBulletinsOfOrganization(result,globalStIndex,countByDate);
		});		
	}
</script> 	
</body>
</html>