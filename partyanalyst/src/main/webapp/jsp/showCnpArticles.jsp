<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Core DashBoard</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Rating/bootstrap-rating.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/iconmoon/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="newCoreDashBoard/css/simplePagination.css"/> 
</head>
<body>

<div class="container m_top20">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div id="articlesDivId"></div>
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
<script type="text/javascript" src="newCoreDashBoard/slimscroller/jquery.slimscroll.js"></script>
<script type="text/javascript" src="newCoreDashBoard/js/simplePagination3.js" ></script>

<script type="text/javascript">
var edTypeIdStr = "${param.edTypeIdStr}";
var bfIdStr = "${param.bfIdStr}";
var orgType = "${param.orgType}";
var orgIdStr = "${param.orgIdStr}";
var callFrom = "${param.callFrom}";
var stIndex = "${param.stIdx}";
var endIndex = "${param.edIdx}";
var globalUserAccessLevelId = "${param.levelId}";
var temp = "${param.temp}";
var state = "${param.state}";
var startDate = "${param.sdat}";
var endDate = "${param.edat}";
var impactScopeIdsStr = "${param.scops}";
var newsPaperIdsStr = "${param.npsStr}";


	$(document).ready(function(){
		//Main header remove
		$(".eventsheader").hide();
		$('[data-toggle="tooltip"]').tooltip();
		getArticlesOfNewsBasicCounts(0);
	});
	
	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));
	
	function getArticlesOfNewsBasicCounts(stIndex){
	
		$("#articlesDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getArticlesOfNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+bfIdStr+"/"+impactScopeIdsStr+"/"+orgIdStr+"/"+orgType+"/"+edTypeIdStr+"/"+newsPaperIdsStr+"/"+stIndex+"/"+endIndex+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesOfNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+bfIdStr+"/"+impactScopeIdsStr+"/"+orgIdStr+"/"+orgType+"/"+edTypeIdStr+"/"+newsPaperIdsStr+"/"+stIndex+"/"+endIndex+""
			
		}).then(function(results){
			$("#articlesDivId").html('');
			var countByDate = 0;
			buildArticlesByDateRangeWise(results,stIndex,countByDate)
		});
	}
	
	function buildArticlesByDateRangeWise(results,stIndex,countByDate){
		var str ='';
		
		if(results !=null && results.length>0){
			
			if(stIndex == 0){
				countByDate=results[0].totalArticlesCount;
			}
				//str+='<div class="row">';
				for(var i in results){
					var artclNmbr  = parseInt(stIndex) + parseInt(i);
					
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
													
													 str+='<li><img class="img-circle" style="display: inline-block; width: 20px; height: 20px; " src="newCoreDashBoard/img/'+results[i].subList[j].fromList[l].organizationName+'.png" onerror="setDefaultImage(this);"></li>';
													 
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
													   str+='<li><img  src="images/'+results[i].subList[j].fromList[l].benefit+'.png" style="display: inline-block; font-size: 20px;width: 20px; height: 20px;"></i></li>';
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
														   str+='<li><img class="img-circle" style="display: inline-block; width: 20px; height: 20px;" src="newCoreDashBoard/img/'+results[i].subList[j].toList[k].organizationName+'.png" onerror="setDefaultImage(this);"></li>';
														  if(results[i].subList[j].toList[k].organizationName != null && results[i].subList[j].toList[k].organizationName.length>7){
															  str+='<li   data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].organizationName+'" class="text-muted " style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].toList[k].organizationName.substring(0,7)+'...</li>';
														 }else{
															 str+='<li class="text-muted" style="font-size: 12px;">'+results[i].subList[j].toList[k].organizationName+'</li>';
														 }
														  
													  str+='</ul>';
												  str+='</td>';
												  str+='<td style="width:50%;">';
													   str+='<ul class="list-inline m_0">';
														  str+='<li><img  src="images/'+ results[i].subList[j].toList[k].benefit+'.png" style="display: inline-block; font-size: 20px;width: 20px; height: 20px;"></i></li>';
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
							str+='<i class="icon-article-group pull-right" style="cursor:default;padding-left:5px;color:#FC5AB8;font-weight: bold;" data-toggle="group" data-placement="top" title="Grouped Articled"></i>';
						}
						if(results[i].lnkdArtcls=='N'){
										
							str+='<i class="icon-linked-articles pull-right" style="cursor:default;padding-left:5px;color:#ccc;" data-toggle="group" data-placement="top" title="Link Article"></i>';
						}else{
							str+='<i class="icon-linked-articles pull-right" style="cursor:default;padding-left:5px;color:red;" data-toggle="group" data-placement="top" title="Linked Articled"></i>';
						}
						if(results[i].trackLabelName == null || results[i].trackLabelName == 0){
										
							str+='<i class="icon-track-articles pull-right" style="cursor:default;padding-left:5px;color:#ccc;" data-toggle="group" data-placement="top" title="Track Article"></i>';
						}else{
							str+='<i class="icon-track-articles pull-right" style="cursor:default;padding-left:5px;color:green;" data-toggle="group" data-placement="top" title="Track Articled"></i>';
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
			if(stIndex == 0 && countByDate > 0){
				//setTimeout(5000);
				$(".paginationId").pagination({
					items: countByDate,
					itemsOnPage: 6,
					cssStyle: 'light-theme',
					hrefTextPrefix: '#pages-',
					onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*6;
							getArticlesOfNewsBasicCounts(num);							
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
				img.src = "images/NoImageUpd.png";
				return true;
		}
		
</script> 
</body>
</html>
