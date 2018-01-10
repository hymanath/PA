<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NEWS ARTICLES</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<link rel="stylesheet" type="text/css" href="Assests/Plugins/Scroller/simplePagination.css"/> 
<style>
</style>
</head>
<body>
<main>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div id="articlesDivId"></div>
			</div>
		</div>
		<div class="row ">
			<div class="col-sm-12 m_top20">
				<div class="paginationId"></div>
			</div>
		</div>
	</div>
</main>
<div class="modal fade" id="myModalShowNew"></div>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" ></script>
<script type="text/javascript" src="Assests/Plugins/Scroller/simplePagination3.js" ></script>
<script type="text/javascript">
var globalEdTypeIdStr = "${param.edTypeIdStr}";
var globalBfIdStr = "${param.bfIdStr}";
var globalOrgType = "${param.orgType}";
var globalOrgIdStr = "${param.orgIdStr}";
var globalCallFrom = "${param.callFrom}";
var globalStIndex = "${param.stIdx}";
var globalEndIndex = "${param.edIdx}";
var globalUserAccessLevelId = "${param.levelId}";
var globalTemp = "${param.temp}";
var globalState = "${param.state}";
var globalStartDate = "${param.sdat}";
var globalEndDate = "${param.edat}";
var globalImpactScopeIdsStr = "${param.scops}";
var globalNewsPaperIdsStr = "${param.npsStr}";
var globalEdiDistIdsStr = "${param.ediDistIdsStr}";
var globalPropIdsStr = "${param.propIdsStr}";

var globalEditionId = "${param.editionId}";
var globalorganizationId = "${param.organizationId}";
var globalbenefitId = "${param.benefitId}";
var globaltype = "${param.type}";
	$(document).ready(function(){
		//Main header remove
		//$(".eventsheader").hide();
		$('[data-toggle="tooltip"]').tooltip();
		if(globalCallFrom == "fblk"){
			getArticlesOfNewsBasicCounts(0);
		}else if(globalCallFrom == "newsArtclesInPrrws"){
			getPrintMediaCountsDetailsInfoForArtcles(0);
		}else if(globalCallFrom == "propForPrrws"){
			if(globaltype == "actionData"){
				getDepartmentWiseAnalysisOfActionImmediatelyDetailsInfoForArticles(0);
			}else if(globaltype == "districtdata"){
				getDepartmentWiseDistrictViewForArticles(0);
			}
		}	
	});
	
	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

	function getArticlesOfNewsBasicCounts(globalStIndex){
	
		$("#articlesDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getArticlesOfNewsBasicCounts/"+globalUserAccessLevelId+"/"+globalTemp+"/"+globalState+"/"+globalStartDate+"/"+globalEndDate+"/"+globalBfIdStr+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalEdTypeIdStr+"/"+globalNewsPaperIdsStr+"/"+globalStIndex+"/"+globalEndIndex+""
			
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesOfNewsBasicCounts/"+globalUserAccessLevelId+"/"+globalTemp+"/"+globalState+"/"+globalStartDate+"/"+globalEndDate+"/"+globalBfIdStr+"/"+globalImpactScopeIdsStr+"/"+globalOrgIdStr+"/"+globalOrgType+"/"+globalEdTypeIdStr+"/"+globalNewsPaperIdsStr+"/"+globalStIndex+"/"+globalEndIndex+""
			
		}).then(function(results){
			$("#articlesDivId").html('');
			var countByDate = 0;
			buildArticlesByDateRangeWise(results,globalStIndex,countByDate)
		});
	}
	
	function buildArticlesByDateRangeWise(results,globalStIndex,countByDate){
		var str ='';
		
		if(results !=null && results.length>0){
			
			if(globalStIndex == 0){
				countByDate=results[0].totalArticlesCount;
			}
				//str+='<div class="row">';
				for(var i in results){
					var artclNmbr  = parseInt(globalStIndex) + parseInt(i);
					
						if(i == 0 || i%3 == 0){
							str+='<div class="row">';
						}
					
						str+='<div class="col-md-4 col-sm-3 widgets widget-hide autoWidthBlock" id="articlediv'+results[i].articleId+'">';
							str+='<div class="thumbnail thumbnail-widget" style="height:auto;">';
							if(results[i].imageURL !='images/NoImageUpd.png'){
								str+='<a class="viewArticleDetailsByAllArticlesPage" attr_articleNo="'+artclNmbr+'" attr_articleId='+results[i].articleId+' attr_paginCnt="'+countByDate+'" style="cursor:pointer;"><img src="http://mytdp.com/NewsReaderImages/'+results[i].imageURL+'" class="groupingimage" alt="image Not Available." style="width:311px;height:140px;"/></a>';
							}else{
								str+='<a class="viewArticleDetailsByAllArticlesPage" attr_articleNo="'+artclNmbr+'" attr_articleId='+results[i].articleId+' attr_paginCnt="'+countByDate+'" style="cursor:pointer;"><img src="http://mytdp.com/NewsReaderImages/'+results[i].imageURL+'" class="groupingimage" alt="image Not Available." style="width:311px;height:140px;"/></a>';
							}
							str+='<div class="caption">';
							 if(results[i].articleTitle == null || results[i].articleTitle.length>50){
								 str+='<p style="font-size:14px;">'+results[i].articleTitle.substring(0,50)+'...</p>';
							 }else{
								 str+='<p style="font-size:14px;">'+results[i].articleTitle+'</p>';
							 }
								
							
								 if(results[i].description == null || results[i].description == 0){
									str+='<p> - </p>';
								}else{
									if(results[i].description !=null && results[i].description.length>100){
									   str+='<p  style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+results[i].description+'" >'+results[i].description.substring(0,100)+'...</p>';
									}else{
										str+='<p>'+results[i].description+'</p>';
									}
								} 
								
							str+='</div>';
							
						str+='<div class="row" >';
						var scrollstr = results[i].articleId;
							
						if(results[i].subList !=null && results[i].subList.length>0){
							
							str+='<div class="scrolllengthDiv">';
							for(var j in results[i].subList){
								
								str+='<div class="row"  style="margin: auto;" >';
							
								if(results[i].subList[j].fromList !=null && results[i].subList[j].fromList.length>0){
									str+='<div class="col-xs-6" style="padding-right: 5px;">';
								   str+='<div class="col-xs-12 subBlock m_top10">';
										str+='<span><i>From</i></span>';
									  for(var l in results[i].subList[j].fromList){
										 
										   str+='<table class="table table-bordered tableGr">';
											str+='<tbody>';
											str+='<tr>';
											  str+='<td style="width:50%;">';
												  str+='<ul class="list-inline m_0">';
													
													 str+='<li><img class="img-circle" style="display: inline-block; width: 20px; height: 20px; " src="Assests/newsImages/'+results[i].subList[j].fromList[l].organizationName+'.png" onerror="setDefaultImage(this);"></li>';
													 
													 if(results[i].subList[j].fromList[l].organizationName == null || results[i].subList[j].fromList[l].organizationName == 0){
														  str+='<li class="text-muted" style="font-size: 12px;text-align:center;"> - </li>';
													 }else{
														 if(results[i].subList[j].fromList[l].organizationName != null && results[i].subList[j].fromList[l].organizationName.length>7){
															  str+='<li   data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].organizationName+'" class="text-muted " style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].fromList[l].organizationName.substring(0,7)+'...</li>';
														 }else{
															 str+='<li class="text-muted" style="font-size: 12px;">'+results[i].subList[j].fromList[l].organizationName+'</li>';
														 }
														 
													 }
													 
												  str+='</ul>';
											  str+='</td>';
											  str+='<td style="width:50%;">';
												   str+='<ul class="list-inline m_0">';
													   str+='<li><img  src="Assests/newsImages/'+results[i].subList[j].fromList[l].benefit+'.png" style="display: inline-block; font-size: 20px;width: 20px; height: 20px;"></i></li>';
													  if(results[i].subList[j].fromList[l].benefit == null || results[i].subList[j].fromList[l].benefit == 0){
														   str+='<li class="text-muted" style="font-size: 12px;text-align:center;"> - </li>';
													   }else{
														   str+='<li class="text-muted" style="font-size: 12px">'+results[i].subList[j].fromList[l].benefit+'</li>';
													   }
													   
												   str+='</ul>';
											   str+='</td>';
											str+='</tr>';
											str+='</tbody>';
										str+=' </table>';
										
										var FromcandidateName = results[i].subList[j].fromList[l].candidateName == null || results[i].subList[j].fromList[l].	candidateName == 0;
										   var Fromdesignation = results[i].subList[j].fromList[l].designation == null || results[i].subList[j].fromList[l].designation == 0
											if( FromcandidateName &&  Fromdesignation ){
												str+=' <div style="text-align:center;"><span > - </span></div> ';
											}else{
												if(FromcandidateName){
												 str+='<span > - </span>';
												}else{
													if(results[i].subList[j].fromList[l].candidateName != null && results[i].subList[j].fromList[l].candidateName.length>17){
														str+='<span  data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].candidateName+'"  style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].fromList[l].candidateName.substring(0,17)+'...</span>';
													}else{
														str+='<span style="font-size:12px;">'+results[i].subList[j].fromList[l].candidateName+'</span>';
													}
													
												}
												
												str+=' <span > - </span> ';
												if(Fromdesignation){
													str+='<span >( - )</span>';
												}else{
													if(results[i].subList[j].fromList[l].designation != null && results[i].subList[j].fromList[l].designation.length>6){
														str+='<span data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].designation+'"  style="font-size: 12px;cursor:pointer;">('+results[i].subList[j].fromList[l].designation.substring(0,6)+'...)</span>';
													}else{
														str+='<span style="font-size:12px;">('+results[i].subList[j].fromList[l].designation+')</span>';
													}
													
												}
											}
											
										
										
										str+='<table class="table table-bordered tableGr">';
									  str+='<tbody>';
									   str+='<tr>';
										  str+='<td>';
										  if(results[i].subList[j].fromList[l].categories == null || results[i].subList[j].fromList[l].categories == 0){
											  str+='<p><span>Category : <span class="text-muted" style="text-align:center;"> - </span></p>';
										  }else{
											if(results[i].subList[j].fromList[l].categories !=null && results[i].subList[j].fromList[l].categories.length>21){
												  str+='<p><span>Category: &nbsp;&nbsp;<span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].categories+'"  style="cursor:pointer;">'+results[i].subList[j].fromList[l].categories.substring(0,21)+'...</span></p>';
											  }else{
												  str+='<p><span>Category :&nbsp; <span class="text-muted">'+results[i].subList[j].fromList[l].categories+'</span></p>';
											  }
											  
										  }
										  if(results[i].subList[j].fromList[l].newsType == null || results[i].subList[j].fromList[l].newsType == 0){
											  str+='<p><span>News Type: <span class="text-muted" style="text-align:center;"> - </span></p>';
										  }else{
											   if(results[i].subList[j].fromList[l].newsType !=null && results[i].subList[j].fromList[l].newsType.length>25){
												   str+='<p><span>News Type:&nbsp;&nbsp; <span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].newsType+'" style="cursor:pointer;">'+results[i].subList[j].fromList[l].newsType.substring(0,25)+'...</span></p>';
											   }else{
												   str+='<p><span>News Type: &nbsp;&nbsp;<span class="text-muted">'+results[i].subList[j].fromList[l].newsType+'</span></p>';
											   }
											  
										  }
										  
										   if( results[i].subList[j].fromList[l].newsType != null && results[i].subList[j].fromList[l].newsType == "Problems"){
											if(results[i].subList[j].fromList[l].newsRelated != null && $.trim(results[i].subList[j].fromList[l].newsRelated).length > 0){
											  str+='<p class="m_0">News Related : <span class="text-muted">'+results[i].subList[j].fromList[l].newsRelated+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">News Related : - </p>';	
											}
											if(results[i].subList[j].fromList[l].priority != null && $.trim(results[i].subList[j].fromList[l].priority).length > 0){
											  str+='<p class="m_0">Priority : <span class="text-muted">'+results[i].subList[j].fromList[l].priority+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">Priority : - </p>';	
											}
											if(results[i].subList[j].fromList[l].solution != null && $.trim(results[i].subList[j].fromList[l].solution).length > 0){
											  str+='<p class="m_0">Solution : <span class="text-muted">'+results[i].subList[j].fromList[l].solution+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">Solution : - </p>';	
											}
										}
										
										  if(results[i].subList[j].fromList[l].newsActivity == null || results[i].subList[j].fromList[l].newsActivity == 0){
											  str+='<p><span>News Activity: </span><span class="text-muted" style="text-align:center;"> - </span></p>';
										  }else{
											  if(results[i].subList[j].fromList[l].newsActivity !=null && results[i].subList[j].fromList[l].newsActivity.length>25){
												   str+='<p><span>News Activity: &nbsp;&nbsp;</span><span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].newsActivity+'" style="cursor:pointer;">'+results[i].subList[j].fromList[l].newsActivity.substring(0,25)+'...</span></p>';
											  }else{
												   str+='<p><span>News Activity: &nbsp;&nbsp;</span><span class="text-muted">'+results[i].subList[j].fromList[l].newsActivity+'</span></p>';
											  }
											 
										  }
										   str+='</td>';
										  str+='</tr>';
									  str+='</tbody>';
									  str+='</table>';
									 
										
									}
									str+='</div>';  
									str+='</div>';
								}
							if(results[i].subList[j].toList !=null && results[i].subList[j].toList.length>0){	
									str+='<div class="col-xs-6" style="padding-left: 5px;">';
										  str+='<div class="col-xs-12 subBlock m_top10" >';
											str+='<span><i>To</i></span>';
										
									 for(var k in results[i].subList[j].toList){
										 
										 str+='<table class="table table-bordered tableGr">';
												str+='<tbody>';
												str+='<tr>';
												  str+='<td style="width:50%;">';
													  str+='<ul class="list-inline m_0">';
														   str+='<li><img class="img-circle" style="display: inline-block; width: 20px; height: 20px;" src="Assests/newsImages/'+results[i].subList[j].toList[k].organizationName+'.png" onerror="setDefaultImage(this);"></li>';
														  if(results[i].subList[j].toList[k].organizationName != null && results[i].subList[j].toList[k].organizationName.length>7){
															  str+='<li   data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].organizationName+'" class="text-muted " style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].toList[k].organizationName.substring(0,7)+'...</li>';
														 }else{
															 str+='<li class="text-muted" style="font-size: 12px;">'+results[i].subList[j].toList[k].organizationName+'</li>';
														 }
														  
													  str+='</ul>';
												  str+='</td>';
												  str+='<td style="width:50%;">';
													   str+='<ul class="list-inline m_0">';
														  str+='<li><img  src="Assests/newsImages/'+ results[i].subList[j].toList[k].benefit+'.png" style="display: inline-block; font-size: 20px;width: 20px; height: 20px;"></i></li>';
														  if(results[i].subList[j].toList[k].benefit == null || results[i].subList[j].toList[k].benefit == 0){
															   str+='<li class="text-muted" style="font-size: 12px"> - </li>';
														  }else{
															   str+='<li class="text-muted" style="font-size: 12px">'+results[i].subList[j].toList[k].benefit+'</li>';
														  }
														  
													   str+='</ul>';
												   str+='</td>';
												str+='</tr>';
												str+='</tbody>';
											str+=' </table>';
						                   var tocandidateName = results[i].subList[j].toList[k].candidateName == null || results[i].subList[j].toList[k].	candidateName == 0;
										   var todesignation = results[i].subList[j].toList[k].designation == null || results[i].subList[j].toList[k].designation == 0
											if( tocandidateName &&  todesignation ){
												str+=' <div style = "text-align:center;"><span> - </span></div> ';
											}else{
												if(tocandidateName){
												 str+='<span> - </span>';
												}else{
													if(results[i].subList[j].toList[k].candidateName != null && results[i].subList[j].toList[k].candidateName.length>17){
														str+='<span  data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].candidateName+'"  style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].toList[k].candidateName.substring(0,17)+'...</span>';
													}else{
														str+='<span style="font-size:12px;">'+results[i].subList[j].toList[k].candidateName+'</span>';
													}
												}
												
												str+=' <span> - </span> ';
												if(todesignation){
													str+='<span>( - )</span>';
												}else{
													if(results[i].subList[j].toList[k].designation != null && results[i].subList[j].toList[k].designation.length>6){
														str+='<span data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].designation+'"  style="font-size: 12px;cursor:pointer;">('+results[i].subList[j].toList[k].designation.substring(0,6)+'...)</span>';
													}else{
														str+='<span style="font-size:12px;">('+results[i].subList[j].toList[k].designation+')</span>';
													}
													
													
												}
											}
											
											
											str+='<table class="table table-bordered tableGr">';
										  str+='<tbody>';
										  str+='<tr>';
										  str+='<td>';
										  
										  if(results[i].subList[j].toList[k].categories == null || results[i].subList[j].toList[k].categories == 0){
											  str+='<p>Category:&nbsp;&nbsp;<span class="text-muted" style = "text-align:center;"> - </span></p>';
										  }else{
											  if(results[i].subList[j].toList[k].categories !=null && results[i].subList[j].toList[k].categories.length>21){
												  str+='<p>Category:&nbsp;&nbsp;<span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].categories+'"  style="cursor:pointer;">'+results[i].subList[j].toList[k].categories.substring(0,21)+'...</span></p>';
											  }else{
												  str+='<p>Category:&nbsp;&nbsp;<span class="text-muted">'+results[i].subList[j].toList[k].categories+'</span></p>';
											  }
											  
											 
										  }
										  
										 
										  
										 
										  
										  if(results[i].subList[j].toList[k].newsType == null || results[i].subList[j].toList[k].newsType == 0){
											  str+='<p>News Type:&nbsp;&nbsp;<span class="text-muted" style = "text-align:center;"> - </span></p>';
										  }else{
											  if(results[i].subList[j].toList[k].newsType !=null && results[i].subList[j].toList[k].newsType.length>25){
												   str+='<p>News Type :&nbsp;&nbsp;<span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].newsType+'" style="cursor:pointer;">'+results[i].subList[j].toList[k].newsType.substring(0,25)+'...</span></p>';
											   }else{
												    str+='<p>News Type:&nbsp;&nbsp;<span class="text-muted">'+results[i].subList[j].toList[k].newsType+'</span></p>';
											   }
											   
											 
										  }
										  if( results[i].subList[j].toList[k].newsType != null && results[i].subList[j].toList[k].newsType == "Problems"){
											if(results[i].subList[j].toList[k].newsRelated != null && $.trim(results[i].subList[j].toList[k].newsRelated).length > 0){
											  str+='<p class="m_0">News Related : <span class="text-muted">'+results[i].subList[j].toList[k].newsRelated+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">News Related : - </p>';	
											}
											if(results[i].subList[j].toList[k].priority != null && $.trim(results[i].subList[j].toList[k].priority).length > 0){
											  str+='<p class="m_0">Priority : <span class="text-muted">'+results[i].subList[j].toList[k].priority+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">Priority : - </p>';	
											}
											if(results[i].subList[j].toList[k].solution != null && $.trim(results[i].subList[j].toList[k].solution).length > 0){
											  str+='<p class="m_0">Solution : <span class="text-muted">'+results[i].subList[j].toList[k].solution+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">Solution : - </p>';	
											}
										}
										
										  
										  if(results[i].subList[j].toList[k].newsActivity == null || results[i].subList[j].toList[k].newsActivity == 0){
											  str+='<p>News Activity :&nbsp;&nbsp;<span class="text-muted">- </span></p>';
										  }else{
											if(results[i].subList[j].toList[k].newsActivity !=null && results[i].subList[j].toList[k].newsActivity.length>25){
												   str+='<p>News Activity:&nbsp;&nbsp;<span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].newsActivity+'" style="cursor:pointer;">'+results[i].subList[j].toList[k].newsActivity.substring(0,25)+'...</span></p>';
											  }else{
												   str+='<p>News Activity:&nbsp;&nbsp;<span class="text-muted">'+results[i].subList[j].toList[k].newsActivity+'</span></p>';
											  }
											  
											  
										  }
										  
										  str+='</td>';
										 
											
										  str+='</tr>';
										  str+='</tbody>';
										  str+='</table>';
										 
										
									}
									str+='</div>';  
									str+='</div>';
								}
								  str+='</div>';
							}
							 str+='</div>';
						}
						 str+='<div class="col-xs-12">';
							str+='<table class="table table-bordered m_top10 table-condensed">';
								str+='<tbody>';
									if(results[i].trackLocationScope == null || results[i].trackLocationScope == 0){
										str+='<p class="text-muted m_top10"><b>Article Tracking</b>  &nbsp;: &nbsp; <i class="glyphicon glyphicon-remove text-danger"></i></p>';
									}else{
										str+='<tr>';
										str+='<td colspan="2">';
										str+='<p class="text-muted m_top10" style="text-align: center;"><b>Article Tracking</b>  &nbsp;: &nbsp; <i class="glyphicon glyphicon-ok text-success"></i></p>';
										str+='</td>';
										str+='</tr>';
										str+='<tr>';
									str+='<td >';
											str+='<p class="text-muted"><b>Tracking Location Scope</b>  &nbsp;: &nbsp;'+results[i].trackLocationScope+'</p>';
									str+='</td>';
									str+='<td>';
											str+='<p class="text-muted"><b>Tracking Location</b> :&nbsp;'+results[i].trackLocationValue+'</p>';
									str+='</td>';
									str+='</tr>';
									str+='<tr>';
									str+='<td colspan="2">';
											str+='<p class="text-muted"><b>Label Name</b>  &nbsp;&nbsp;&nbsp;:&nbsp;'+results[i].trackLabelName+'</p>';
									str+='</td>';
									str+='</tr>';
										
									}
										
									
									
								str+='</tbody>';
						  str+='</table>';
						str+='</div>';
						 str+='<div class="col-xs-12">';
							str+='<table class="table table-condensed m_top10" style="border:1px solid #ddd;">';
								str+='<tbody>';
									str+='<tr>';
										 if(results[i].important == 'Y'){
											 str+='<td>Is Important : &nbsp;&nbsp;Yes</label></td>';
										 }else{
											 str+='<td>Is Important  : &nbsp;&nbsp;No</label></td>';
										 }
										
										 if(results[i].actionable == 'Y'){
											  str+='<td>Actionable : &nbsp;&nbsp;Yes</label></td>';
										 }else{
											  str+='<td>Actionable : &nbsp;&nbsp;No</label></td>';
										 }
										 
										 if(results[i].newsBulliten == 'Y'){
											 str+='<td>News Bulletin : &nbsp;&nbsp;Yes</label></td>';
										 }else{
											 str+='<td>News Bulletin : &nbsp;&nbsp;No</label></td>';
										 }
									str+='</tr>';
						  
								str+='</tbody>';
						  str+='</table>';
						str+='</div>';
						
						  str+='<div class="col-xs-12">';
							str+='<table class="table table-bordered m_top10">';
								str+='<tbody>';
									str+='<tr>';
										str+='<td colspan="3"><p class="text-muted">'+results[i].editionName+'&nbsp;&nbsp; (Page No:'+results[i].pageNo+');&nbsp; '+results[i].articleInsertedTime+'</p></td>';
									str+='</tr>';
						  
								str+='</tbody>';
						  str+='</table>';
						str+='</div>';
						 str+='<div class="col-xs-12 m_top10">';
						
					 	 if(results[i].groupedArtcls=="N"){
							
							str+='<i class="icon-article-group pull-right" style="cursor:default;padding-left:5px;color:#ccc;" data-toggle="group" data-placement="top" title="Grouped Article"></i>';
						}else{
							str+='<i class="icon-article-group pull-right" style="cursor:default;padding-left:5px;color:#FC5AB8;font-weight: bold;" data-toggle="group" data-placement="top" title="Grouped Article"></i>';
						}
						if(results[i].lnkdArtcls=='N'){
										
							str+='<i class="icon-linked-articles pull-right" style="cursor:default;padding-left:5px;color:#ccc;" data-toggle="group" data-placement="top" title="Linked Article"></i>';
						}else{
							str+='<i class="icon-linked-articles pull-right" style="cursor:default;padding-left:5px;color:red;" data-toggle="group" data-placement="top" title="Linked Article"></i>';
						}
						if(results[i].trackLabelName == null || results[i].trackLabelName == 0){
										
							str+='<i class="icon-track-articles pull-right" style="cursor:default;padding-left:5px;color:#ccc;" data-toggle="group" data-placement="top" title="Tracked Article"></i>';
						}else{
							str+='<i class="icon-track-articles pull-right" style="cursor:default;padding-left:5px;color:green;" data-toggle="group" data-placement="top" title="Tracked Article"></i>';
						}
						
						 /* var artcTtl = results[i].articleTitle;
						  var artcDesc = results[i].description;
						  var ttlTxt = "";
						  var descTxt = "";
						  
						  if(artcTtl!=""){
							ttlTxt = artcTtl.replace(/(\r\n|\n|\r)/gm,"");
						  }
						  
						  if(artcDesc!=""){
							descTxt = artcDesc.replace(/(\r\n|\n|\r)/gm,"");
						  }
						  
						  
						  str+='<div class="" onclick="gettingImages(\'../NewsReaderImages/'+results[i].imageURL+'\',\''+results[i].articleId+'\',\''+ttlTxt+'\',\''+results[i].categoryId+'\',\''+results[i].feedback+'\',\''+descTxt+'\',\''+results[i].imageWidth+'\',\''+results[i].editionId+'\',\''+results[i].editionDownloadedId+'\');"><i title="" data-placement="top" data-toggle="group" style="padding-left:5px;font-weight: bold;cursor:pointer;" class="icon-edit pull-right" data-original-title="Edit Article"></i></div>'; */
						 
					  str+='</div>';
					  
					 
								  
						 str+='</div>';
					str+='</div>';
				str+='</div>';
			//str+='</div>';
			 
				if(i == 2 || i%3 == 2){
						str+='</div>';
					}
	
			
			}
			if(globalStIndex == 0 && countByDate > 6){
				//setTimeout(5000);
				$(".paginationId").pagination({
					items: countByDate,
					itemsOnPage: 6,
					cssStyle: 'light-theme',
					hrefTextPrefix: '#pages-',
					onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*6;
						
						if(globalCallFrom == "fblk"){
							getArticlesOfNewsBasicCounts(num);
						}else if(globalCallFrom == "newsArtclesInPrrws"){
							getPrintMediaCountsDetailsInfoForArtcles(num);
						}else if(globalCallFrom == "propForPrrws"){
							if(globaltype == "actionData"){
								getDepartmentWiseAnalysisOfActionImmediatelyDetailsInfoForArticles(num);
							}else if(globaltype == "districtdata"){
								getDepartmentWiseDistrictViewForArticles(num);
							}
						}	
					}
					
				});
			}
				str+='</div>';
			
		}else{
			$(".paginationId").html("");
						
						str+='<div class="col-md-5 widgets widget-hide" style="margin-left:200px;color:red;"> <h4> NO ARTICLES AVAILABLE WITH SEARCHED FILTERS </h4> </div>';
		}
		
		$("#articlesDivId").append(str);
		setTimeout(function () {
		$(".scrolllengthDiv").each(function(){
			$('.scrolllengthDiv').wrapInner('<div class="scrollable" />');
			var scrolllengthDiv = $(this).find("tr").length;
			
			if(scrolllengthDiv >= 8){
				$(this).find('.scrollable').slimscroll({
					height: '250px',
					alwaysVisible: true
				});
			}else{
				$('.scrolllengthDiv').css("height","auto");
			}
			
		});
		}, 1000);
		$('[data-toggle="tooltip"]').tooltip();
		
		
	}

		function setDefaultImage(img)
		{
			
				img.onerror = "";
				img.src = "Assests/newsImages/NoImageUpd.png";
				return true;
		}
			
	$(document).on("click",".viewArticleDetailsByAllArticlesPage",function(){
		getOverAllDetailsOfAnArticle($(this).attr("attr_articleid"));
	});

	function getOverAllDetailsOfAnArticle(articleId){
		$("#myModalShowNew").modal('show');
			$("#myModalShowNew").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
			$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
			
		}).then(function(results){
			var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","CORP-GMC","Ward","NATIONAL","INTERNATIONAL","MUNICIPALITY"];
				var result = results[0];
				var str = '';
					str+='<div class="modal-dialog modal-lg" role="document">';
					str+='<div class="modal-content">';
					str+='<div class="modal-header">';
					str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
					str+='<h4 class="modal-title" id="myModalLabel">';
					str+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
					str+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
					str+='</h4>';
					str+='</div>';
					str+='<div class="modal-body">';
					str+='<div class="row">';
					str+='<div class="col-md-12">';
					str+='<img class="mainImage"  src="http://mytdp.com/NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;width:100%;" alt="Img Title"/>';
					str+='</div>';
					str+='<div class="col-md-12 m_top10">';
					str+='<h4 class="panel-title text-success">Description</h4>';
					str+='<p class="m_0 f_14">'+result.description+'</p>';
					str+='</div>';
					str+='<div class="col-md-12">';
					if( result.subList != null && result.subList.length > 0){
						for(var i in result.subList){
							/* Candidate*/
							str+='<div class="row ">';
							str+='<div class="col-md-6">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">FROM WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* From Table*/
								if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
									for( var j in result.subList[i].fromList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="Assests/newsImages/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
										}
										str+='<td><img class="img-circle" src="Assests/newsImages/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].fromList[j].candidateName;
										}
										if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
											candidataExist = true; 
											str+=' ('+result.subList[i].fromList[j].designation + ")";
										}
										if(!candidataExist){
											str+=' - ';
										}
										str+='</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
											str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
										}else{ 
											str+='<p class="m_0">Impact Level : - </p>';	
										}
										if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
											str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
										}else{ 
											str+='<p class="m_0">Category : - </p>';	
										}
										if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
											str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
										}else{ 
											str+='<p class="m_0">News Activity : - </p>';	
										}
										if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
											str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
										}else{ 
											str+='<p class="m_0">News type : - </p>';	
										}
										if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
											if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
												str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
											}else{ 
												str+='<p class="m_0">News Related : - </p>';	
											}
											if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
												str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
											}else{ 
												str+='<p class="m_0">Priority : - </p>';	
											}
											if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
												str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
											}else{ 
												str+='<p class="m_0">Solution : - </p>';	
											}
										}
										str+='</td>';
										str+='</tr>';
										str+='</table>';
									}
								}
							str+='</div>';//panel-body
							str+='</div>';//panel
							str+='</div>';//colmd6
							str+='<div class="col-md-6">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">TO WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* TO Table*/
								if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
									for( var j in result.subList[i].toList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="Assests/newsImages/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
										}else{
											str+='<td> - </td>';
										}
										str+='<td><img class="img-circle" src="Assests/newsImages/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].toList[j].candidateName;
																			}
																			if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
																				candidataExist = true; 
																				str+=' ('+result.subList[i].toList[j].designation + ")";
																			}
																			if(!candidataExist){
																				str+=' - ';
																			}
																		   str+='</td>';
																	str+='</tr>';
																	str+='<tr>';
																		str+='<td colspan="2">';
																		    
																			if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
																			  str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Impact Level : - </p>';	
																			}
																		
																		    if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
																			  str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Category : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
																			  str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News Activity : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
																			  str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News type : - </p>';	
																			}
																			if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){
																				
																				if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
																				  str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
																				}else{ 
																				  str+='<p class="m_0">News Related : - </p>';	
																				}
																				if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
																				  str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Priority : - </p>';	
																				}
																				if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
																				  str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Solution : - </p>';	
																				}
																			}
																		str+='</td>';
																	str+='</tr>';
																str+='</table>';
															}
														}
														
													str+='</div>';//panelbody
												str+='</div>';//panel
											str+='</div>';//colmd6
											
										str+='</div>';//row
								  }
								}
								
								str+='</div>';//colmd12
							str+='</div>';//row
							
							/* Tracking*/
							str+='<div class="row">';
								str+='<div class="col-md-6 m_top10">';
									str+='<div class="panel panel-default panelArticleGroup">';
									if(result.trackLocationScope!=null && $.trim(result.trackLocationScope.length > 0)){
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">Article Tracking &nbsp;&nbsp;&nbsp<i class="glyphicon glyphicon-ok text-success"></i></h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-bordered">';
												str+='<tr>';
												   if(result.trackLocationScope!=null && $.trim(result.trackLocationScope.length > 0)){
													 str+='<td>Tracking Location Scope : '+result.trackLocationScope+'</td>';  
												   }else{
													  str+='<td>Tracking Location Scope : - </td>';   
												   }
												str+='</tr>';
												
												str+='<tr>';
												 if(result.trackLocationValue!=null && $.trim(result.trackLocationValue.length > 0)){
													 str+='<td>Tracking Location  : '+result.trackLocationValue+'</td>';  
												 }else{
													  str+='<td>Tracking Location : - </td>';   
												 }
												str+='</tr>';
												
												str+='<tr>';
												if(result.trackLabelName!=null && $.trim(result.trackLabelName.length > 0)){
													 str+='<td>Label Name  : '+result.trackLabelName+'</td>';  
												 }else{
													  str+='<td>Label Name : - </td>';   
												 }
												str+='</tr>';
											str+='</table>';
										str+='</div>';
									}else{
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">Article Tracking </h4>';
										str+='</div>';
										str+='<div class="panel-body">';
										str+='<h4 class="panel-title" style="text-align:center;">Tracking &nbsp;&nbsp;&nbsp<i class="glyphicon glyphicon-remove text-danger"></i></h4>';
										str+='</div>';
									}
										
									str+='</div>';
								str+='</div>';
						
							/* Characteristics */
						
								str+='<div class="col-md-6 m_top10">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">Article Characteristics</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed" style="border:1px solid #ddd;">';
												str+='<tr>';
												   if(result.important == 'Y'){
													str+='<td>Is Important : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
												   }else{
													   str+='<td>Is Important :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
												   }
												str+='</tr>';
												str+='<tr>';
												   if(result.actionable == 'Y'){
													   str+='<td>Actionable : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
												    }else{
													   str+='<td>Actionable :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
												    }
												str+='</tr>';
												str+='<tr>';
													if(result.newsBulliten == 'Y'){
													   str+='<td>News Bulletin : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
												    }else{
													   str+='<td>News Bulletin :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
												    }
												str+='</tr>';
											str+='</table>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
							
							str+='<div class="row">';
							    /* NewsType */
								str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">NEWS TYPE</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed">';
												str+='<tr>';
													str+='<td>Published Article</td>';
													if(result.publishedArticle!=null){
													   str+='<td>'+result.publishedArticle+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
												str+='<tr>';
													str+='<td>Article Nature</td>';
													if(result.articleNature != null){
													  str+='<td>'+result.articleNature+'</td>';	
													}else{
														str+='<td> - </td>';	
													}
												str+='</tr>';
											str+='</table>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								
								/* Article Scope Location */
								str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed">';
												str+='<tr>';
													str+='<td>Impact Scope</td>';
													if(result.impactScopeId!=null){
														str+='<td>'+obj[result.impactScopeId]+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
												str+='<tr>';
													str+='<td>Location</td>';
													if(result.scopeLocation!=null){
														str+='<td>'+result.scopeLocation+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
											str+='</table>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
							str+='<div class="row">';
							     /*Lnking*/
								str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
										     if( result.linkedList != null && result.linkedList.length > 1){
											str+='<div class="row">';
												for( var i in result.linkedList){
													if(result.linkedList[i].articleId !=articleId ){
														str+='<div class="col-md-4" style="margin-top:5px;">';
															str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="http://mytdp.com/NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;"/>';
														str+='</div>';
													}
												}
											str+='</div>';
											}else{
												str+="<h5> No Linked Articles Available </h5>";
											}
											
										str+='</div>';
									str+='</div>';
								str+='</div>';
								/*Grouping*/
								str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">GROUPED ARTICLES</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
										    if( result.groupedArticlesList != null && result.groupedArticlesList.length > 1){
												if( result.groupedArticlesList.length > 6){
													str+='<div class="row" style="height:200px;overflow-y:scroll">';
												}else{
													str+='<div class="row">';
												}
												for( var i in result.groupedArticlesList){
													if(result.groupedArticlesList[i].articleId !=articleId ){
														str+='<div class="col-md-4 m_top10">';
															str+='<img class="img-responsive thumbnail groupedArticleId" attr_articleId='+result.groupedArticlesList[i].articleId+' src="http://mytdp.com/NewsReaderImages/'+result.groupedArticlesList[i].imageURL+'" style="display:block;margin:auto;height:90px;"/>';
														str+='</div>';
													}
												}
											str+='</div>';
											}else{
												str+="<h5> No Grouped Articles Available </h5>";
											}
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
						  str+='</div>';
						str+='</div>';
					 str+='</div>';
					 
					$("#myModalShowNew").html(str);
					
		        
		});
	}	
	
	$(document).on("click",".linkedArticlesClickId",function(){	 
		var temp=$(this).attr('src');
		$(this).attr('src',$(".mainImage").attr('src'));
		$(".mainImage").attr('src',temp);
	});
	$(document).on("click",".groupedArticleId",function(){
		var articleId = $(this).attr('attr_articleId');
		getOverAllDetailsOfAnArticle(articleId);
	});
	function getPrintMediaCountsDetailsInfoForArtcles(globalStIndex){
		$("#articlesDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfoForArtcles/"+globalStartDate+"/"+globalEndDate+"/"+globalorganizationId+"/"+globalEditionId+"/"+globalbenefitId+"/"+globalStIndex+"/"+globalEndIndex+""
			
			url: "http://localhost:8085/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfoForArtcles/"+globalStartDate+"/"+globalEndDate+"/"+globalorganizationId+"/"+globalEditionId+"/"+globalbenefitId+"/"+globalStIndex+"/"+globalEndIndex+""
			
		}).then(function(results){
			$("#articlesDivId").html('');
			var countByDate = 0;
			buildArticlesByDateRangeWise(results,globalStIndex,countByDate)
		});
	}
	function getDepartmentWiseAnalysisOfActionImmediatelyDetailsInfoForArticles(globalStIndex){
		$("#articlesDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getDepartmentWiseAnalysisOfActionImmediatelyDetailsInfoForArticles/"+globalStartDate+"/"+globalEndDate+"/"+globalorganizationId+"/"+globalEditionId+"/"+globalStIndex+"/"+globalEndIndex+""
			
			url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartmentWiseAnalysisOfActionImmediatelyDetailsInfoForArticles/"+globalStartDate+"/"+globalEndDate+"/"+globalorganizationId+"/"+globalEditionId+"/"+globalStIndex+"/"+globalEndIndex+""
			
		}).then(function(results){
			$("#articlesDivId").html('');
			var countByDate = 0;
			buildArticlesByDateRangeWise(results,globalStIndex,countByDate)
		});
	}
	function getDepartmentWiseDistrictViewForArticles(globalStIndex){
		$("#articlesDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getDepartmentWiseDistrictViewForArticles/"+globalStartDate+"/"+globalEndDate+"/"+globalorganizationId+"/"+globalEditionId+"/"+globalStIndex+"/"+globalEndIndex+""
			
			url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartmentWiseDistrictViewForArticles/"+globalStartDate+"/"+globalEndDate+"/"+globalorganizationId+"/"+globalEditionId+"/"+globalStIndex+"/"+globalEndIndex+""
			
		}).then(function(results){
			$("#articlesDivId").html('');
			var countByDate = 0;
			buildArticlesByDateRangeWise(results,globalStIndex,countByDate)
		});
	}
</script> 	
</body>
</html>