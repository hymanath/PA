var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));

function onloadPrajaSankaplaYatraCalls(){
	
	getPrintMediaOverAllPSYCounts("OverAll","printMedia")
	getPrintMediaOrganizationWisePSYCounts("Party","printMedia")
	getPrintMediaPublicationWisePSYCounts("Publication","printMedia")
	
	getBulletinPointOverAllPSYCounts("OverAll","electronicMedia")
	getBulletinOrganizationWisePSYCounts("Party","electronicMedia")
	getChannelWisePSYCounts("Publication","electronicMedia")
	
	
}
function getPrintMediaOverAllPSYCounts(divId,type){
		$("#"+type+divId+"BlockId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>')
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaOverAllPSYCounts/"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaOverAllPSYCounts/"
	}).then(function(result){
		
		if(result !=null){
			buildOverAllCountDetails(result,divId,type);
		}else{
			$("#"+type+divId+"BlockId").html("No Data Available")
		}
	});
}
function buildOverAllCountDetails(result,divId,type){
	var categoryId = $(this).attr("attr_categoryId"); 	
	var organizationId = $(this).attr("attr_organizationId"); 	
	var benefitId = $(this).attr("attr_benefitId"); 	
	var str='';
	var prajaSankTotalCount=0;
	var prajaSAMASYALATotalCount=0;
	prajaSankTotalCount=prajaSankTotalCount+result.psyPosCnt+result.psyNegCnt;
	prajaSAMASYALATotalCount=prajaSAMASYALATotalCount+result.cpsyPosCnt+result.cpsyNegCnt;
		str+='<div class="row">';
		str+='<div class="col-sm-6 m_top10">';
			str+='<table class="table table-condensed tableNews bg_ED">';
				str+='<tr>';
					str+='<td>';
						str+='<p class="text-capital responsiveFont">OverAll</p>';
						str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="0" attr_benefitId = "0" attr_type="'+divId+''+type+'">'+prajaSankTotalCount+'</a></p>';
					str+='</td>';
					str+='<td>';
						str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
						str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="0" attr_benefitId = "1" attr_type="'+divId+''+type+'">'+result.psyPosCnt+'</a></span>';
						if(prajaSankTotalCount > 0){
							str+='<small id="" class="text-success"> '+((result.psyPosCnt*100)/(prajaSankTotalCount)).toFixed(2)+' %</small>';
						}else{
							str+='<small id="" class="text-success"> 0.0 %</small>';
						}
					str+='</td>';
					str+='<td>';
						str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
						str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="0" attr_benefitId = "2" attr_type="'+divId+''+type+'">'+result.psyNegCnt+'</a></span>';
						if(prajaSankTotalCount > 0){
							str+='<small id="" class="text-danger"> '+((result.psyNegCnt*100)/(prajaSankTotalCount)).toFixed(2)+' %</small>';
						}else{
							str+='<small id="" class="text-danger"> 0.0 %</small>';
						}
							
					str+='</td>';
				str+='</tr>';
			str+='</table>';
		str+='</div>';
		
		str+='<div class="col-sm-6 m_top10">';
			str+='<table class="table table-condensed tableNews bg_ED">';
				str+='<tr>';
					str+='<td>';
						str+='<p class="text-capital responsiveFont">OverAll</p>';
						str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="0" attr_benefitId = "0" attr_type="'+divId+''+type+'">'+prajaSAMASYALATotalCount+'</a></p>';
					str+='</td>';
					str+='<td>';
						str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
						str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="0" attr_benefitId = "1" attr_type="'+divId+''+type+'">'+result.cpsyPosCnt+'</a></span>';
						if(prajaSAMASYALATotalCount > 0){
							str+='<small id="" class="text-success"> '+((result.cpsyPosCnt*100)/(prajaSAMASYALATotalCount)).toFixed(2)+' %</small>';
						}else{
							str+='<small id="" class="text-success"> 0.0 %</small>';
						}
					str+='</td>';
					str+='<td>';
						str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
						str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="0" attr_benefitId = "2" attr_type="'+divId+''+type+'">'+result.cpsyNegCnt+'</a></span>';
						if(prajaSAMASYALATotalCount > 0){
							str+='<small id="" class="text-danger"> '+((result.cpsyNegCnt*100)/(prajaSAMASYALATotalCount)).toFixed(2)+' %</small>';
						}else{
							str+='<small id="" class="text-danger"> 0.0 %</small>';
						}
							
					str+='</td>';
				str+='</tr>';
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#"+type+divId+"BlockId").html(str)
}
function getPrintMediaOrganizationWisePSYCounts(divId,type){
		$("#"+type+divId+"BlockId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>')
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaOrganizationWisePSYCounts/"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaOrganizationWisePSYCounts/"
	}).then(function(result){
		if(result !=null && result.length>0){
			buildPartyWiseDetails(result,divId,type);
		}else{
			$("#"+type+divId+"BlockId").html("No Data Available")	
		}
	});
}
function buildPartyWiseDetails(result,divId,type){
	var str='';
		str+='<div class="panel panel-default panelNew">';
			str+='<div class="panel-heading">';
				str+='<a class="collapseDebatesIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+divId+''+type+'" aria-expanded="true" aria-controls="collapse'+divId+''+type+'">';
					str+='<h4 class="panel-title text-capital">Party Wise</h4>';
				str+='</a>';
			str+='</div>';
		
		str+='<div id="collapse'+divId+''+type+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId+''+type+'">';
			str+='<div class="panel-body" style="background-color: rgb(246, 246, 246);">';
			for(var i in result){
				str+='<div class="row">';
				var toatlCountPrajaSank=0;
				var toatlCountPrajaSam=0;
				toatlCountPrajaSank = toatlCountPrajaSank+result[i].psyPosCnt+result[i].psyNegCnt;
				toatlCountPrajaSam = toatlCountPrajaSam+result[i].cpsyPosCnt+result[i].cpsyNegCnt;
				str+='<div class="col-sm-6">';
					str+='<table class="table table-condensed tableNews bg_ED">';
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-capital responsiveFont">'+result[i].name+'</p>';
								str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="0" attr_benefitId = "0" attr_type="'+divId+''+type+'">'+toatlCountPrajaSank+'</a></p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
								str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="1" attr_type="'+divId+''+type+'">'+result[i].psyPosCnt+'</a></span>';
								if(toatlCountPrajaSank > 0){
									str+='<small id="" class="text-success"> '+((result[i].psyPosCnt*100)/((result[i].psyPosCnt)+(result[i].psyNegCnt))).toFixed(2)+' %</small>';
								}else{
									str+='<small id="" class="text-success"> 0.0 %</small>';
								}
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
								str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="2" attr_type="'+divId+''+type+'">'+result[i].psyNegCnt+'</a></span>';
								if(toatlCountPrajaSank > 0){
									str+='<small id="" class="text-danger"> '+((result[i].psyNegCnt*100)/((result[i].psyPosCnt)+(result[i].psyNegCnt))).toFixed(2)+' %</small>';
								}else{
									str+='<small id="" class="text-danger"> 0.0 %</small>';
								}
									
							str+='</td>';
						str+='</tr>';
					str+='</table>';
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<table class="table table-condensed tableNews bg_ED">';
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-capital responsiveFont">'+result[i].name+'</p>';
								str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="0" attr_benefitId ="0" attr_type="'+divId+''+type+'">'+toatlCountPrajaSam+'</a></p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
								str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="1" attr_type="'+divId+''+type+'">'+result[i].cpsyPosCnt+'</a></span>';
								if(toatlCountPrajaSam > 0){
									str+='<small id="" class="text-success"> '+((result[i].cpsyPosCnt*100)/(toatlCountPrajaSam)).toFixed(2)+' %</small>';
								}else{
									str+='<small id="" class="text-success"> 0.0 %</small>';
								}
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
								str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="2" attr_type="'+divId+''+type+'">'+result[i].cpsyNegCnt+'</a></span>';
								if(toatlCountPrajaSam > 0){
									str+='<small id="" class="text-danger"> '+((result[i].cpsyNegCnt*100)/(toatlCountPrajaSam)).toFixed(2)+' %</small>';
								}else{
									str+='<small id="" class="text-danger"> 0.0 %</small>';
								}
									
							str+='</td>';
						str+='</tr>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	$("#"+type+divId+"BlockId").html(str)	
}
function getPrintMediaPublicationWisePSYCounts(divId,type){
		$("#"+type+divId+"BlockId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>')
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaPublicationWisePSYCounts/"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaPublicationWisePSYCounts/"
	}).then(function(result){
		if(result !=null && result.length>0){
			buildpublicationAndChannelWiseDetails(result,divId,type);
		}else{
			$("#"+type+divId+"BlockId").html("No Data Available");	
		}
	});
}
function buildpublicationAndChannelWiseDetails(result,divId,type){
	var str='';
		str+='<div class="panel panel-default panelNew">';
			str+='<div class="panel-heading">';
				str+='<a class="collapseDebatesIcon collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+type+''+divId+'" aria-expanded="true" aria-controls="collapse'+type+''+divId+'">';
					if(type == "printMedia"){
						str+='<h4 class="panel-title text-capital">Publication Wise</h4>';
					}else{
						str+='<h4 class="panel-title text-capital">Channel Wise</h4>';
					}
					
				str+='</a>';
			str+='</div>';
		str+='<div id="collapse'+type+''+divId+'" class="panel-collapse collapse " role="tabpanel" aria-labelledby="heading'+type+''+divId+'">';
		str+='<div class="panel-body" style="background-color: rgb(246, 246, 246);">';
		for(var i in result){
			str+='<div class="row">';
			var toatlCountPrajaSank=0;
			var toatlCountPrajaSam=0;
			toatlCountPrajaSank = toatlCountPrajaSank+result[i].psyPosCnt+result[i].psyNegCnt;
			toatlCountPrajaSam = toatlCountPrajaSam+result[i].cpsyPosCnt+result[i].cpsyNegCnt;
			str+='<div class="col-sm-6">';
				str+='<table class="table table-condensed tableNews bg_ED">';
					str+='<tr>';
						str+='<td>';
						if(type == "printMedia"){
							if(result[i].name !=null && result[i].name.length>6){
								str+='<p class="text-capital responsiveFont"><span class="tooltipCls" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,6)+'..</p>';
							}else{
								str+='<p class="text-capital responsiveFont">'+result[i].name+'</p>';
							}
						}else{
							if(result[i].name !=null && result[i].name.length>4){
								str+='<p class="text-capital responsiveFont"><span class="tooltipCls" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,4)+'..</p>';
							}else{
								str+='<p class="text-capital responsiveFont">'+result[i].name+'</p>';
							}
						}
							
							
							str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="0" attr_benefitId ="0" attr_type="'+divId+''+type+'">'+toatlCountPrajaSank+'</a></p>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
							str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="1" attr_type="'+divId+''+type+'">'+result[i].psyPosCnt+'</a></span>';
							if(toatlCountPrajaSank > 0){
								str+='<small id="" class="text-success"> '+((result[i].psyPosCnt*100)/((result[i].psyPosCnt)+(result[i].psyNegCnt))).toFixed(2)+' %</small>';
							}else{
								str+='<small id="" class="text-success"> 0.0 %</small>';
							}
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
							str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="2" attr_type="'+divId+''+type+'">'+result[i].psyNegCnt+'</a></span>';
							if(toatlCountPrajaSank > 0){
								str+='<small id="" class="text-danger"> '+((result[i].psyNegCnt*100)/((result[i].psyPosCnt)+(result[i].psyNegCnt))).toFixed(2)+' %</small>';
							}else{
								str+='<small id="" class="text-danger"> 0.0 %</small>';
							}
								
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
			str+='<div class="col-sm-6">';
				str+='<table class="table table-condensed tableNews bg_ED">';
					str+='<tr>';
						str+='<td>';
							if(type == "printMedia"){
								if(result[i].name !=null && result[i].name.length>6){
									str+='<p class="text-capital responsiveFont"><span class="tooltipCls" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,6)+'..</p>';
								}else{
									str+='<p class="text-capital responsiveFont">'+result[i].name+'</p>';
								}
							}else{
								if(result[i].name !=null && result[i].name.length>4){
									str+='<p class="text-capital responsiveFont"><span class="tooltipCls" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,4)+'..</p>';
								}else{
									str+='<p class="text-capital responsiveFont">'+result[i].name+'</p>';
								}
							}
							str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="0" attr_benefitId ="0" attr_type="'+divId+''+type+'">'+toatlCountPrajaSam+'</a></p>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
							str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="1" attr_type="'+divId+''+type+'">'+result[i].cpsyPosCnt+'</a></span>';
							if(toatlCountPrajaSam > 0){
								str+='<small id="" class="text-success"> '+((result[i].cpsyPosCnt*100)/(toatlCountPrajaSam)).toFixed(2)+' %</small>';
							}else{
								str+='<small id="" class="text-success"> 0.0 %</small>';
							}
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
							str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="2" attr_type="'+divId+''+type+'">'+result[i].cpsyNegCnt+'</a></span>';
							if(toatlCountPrajaSam > 0){
								str+='<small id="" class="text-danger"> '+((result[i].cpsyNegCnt*100)/(toatlCountPrajaSam)).toFixed(2)+' %</small>';
							}else{
								str+='<small id="" class="text-danger"> 0.0 %</small>';
							}
								
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
		}
	str+='</div>';
	str+='</div>';
	$("#"+type+divId+"BlockId").html(str);
	$(".tooltipCls").tooltip();	
	
}
function getChannelWisePSYCounts(divId,type){
		$("#"+type+divId+"BlockId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>')
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePSYCounts/"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePSYCounts/"
	}).then(function(result){
		if(result !=null && result.length>0){
			buildpublicationAndChannelWiseDetails(result,divId,type);
		}else{
			$("#"+type+divId+"BlockId").html("No Data Available");
		}
	});
}
function getBulletinOrganizationWisePSYCounts(divId,type){
		$("#"+type+divId+"BlockId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>')
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getBulletinOrganizationWisePSYCounts/"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getBulletinOrganizationWisePSYCounts/"
	}).then(function(result){
		if(result !=null && result.length>0){
			buildPartyWiseDetails(result,divId,type);
		}else{
			$("#"+type+divId+"BlockId").html("No Data Available");
		}
	});
}
function getBulletinPointOverAllPSYCounts(divId,type){
		$("#"+type+divId+"BlockId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>')
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getBulletinPointOverAllPSYCounts/"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getBulletinPointOverAllPSYCounts/"
	}).then(function(result){
		if(result !=null){
			buildOverAllCountDetails(result,divId,type);
		}else{
			$("#"+type+divId+"BlockId").html("No Data Available");
		}
	});
}
$(document).on("click",".newsArticlesDetailsCls",function(){
		
	var categoryId = $(this).attr("attr_categoryId"); 	
	var organizationId = $(this).attr("attr_organizationId"); 	
	var benefitId = $(this).attr("attr_benefitId"); 	
	var type = $(this).attr("attr_type"); 	
	
	window.open('showArticlesAction.action?categoryId='+categoryId+'&organizationId='+organizationId+'&benefitId='+benefitId+'&type='+type+'&callFrom=prajaSankalpa&startIndex=0&endIndex=6','_blank');
		
	});
