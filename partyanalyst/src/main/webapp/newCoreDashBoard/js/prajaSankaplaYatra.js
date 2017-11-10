var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));

var currentFromDate = moment().format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");
$("#dateRangePrajaSankalpaId").daterangepicker({
	opens: 'left',
	startDate:currentFromDate,
	endDate: currentToDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
});
$('#dateRangePrajaSankalpaId').on('apply.daterangepicker', function(ev, picker) {
  currentFromDate = picker.startDate.format('DD/MM/YYYY');
  currentToDate = picker.endDate.format('DD/MM/YYYY');
  onloadPrajaSankaplaYatraCalls();
});
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
		url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaOverAllPSYCounts/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaOverAllPSYCounts/"
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
						if(prajaSankTotalCount>0){
							str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="0" attr_benefitId = "0" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+prajaSankTotalCount+'</a></p>';
						}else{
							str+='<p>'+prajaSankTotalCount+'</p>';
						}
						
					str+='</td>';
					str+='<td>';
						str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
						if(result.psyPosCnt !=null && result.psyPosCnt>0){
							str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="0" attr_benefitId = "1" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result.psyPosCnt+'</a></span>';
						}else{
							str+='<span>'+result.psyPosCnt+'</span>';
						}
						
						if(prajaSankTotalCount > 0){
							str+='<small id="" class="text-success"> '+((result.psyPosCnt*100)/(prajaSankTotalCount)).toFixed(2)+' %</small>';
						}else{
							str+='<small id="" class="text-success"> 0.0 %</small>';
						}
					str+='</td>';
					str+='<td>';
						str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
						if(result.psyNegCnt !=null && result.psyNegCnt>0){
							str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="0" attr_benefitId = "2" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result.psyNegCnt+'</a></span>';
						}else{
							str+='<span>'+result.psyNegCnt+'</span>';
						}
						
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
						if(prajaSAMASYALATotalCount>0){
							str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="0" attr_benefitId = "0" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+prajaSAMASYALATotalCount+'</a></p>';
						}else{
							str+='<p>'+prajaSAMASYALATotalCount+'</p>';
						}
						
					str+='</td>';
					str+='<td>';
						str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
						if(result.cpsyPosCnt !=null && result.cpsyPosCnt>0){
							str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="0" attr_benefitId = "1" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result.cpsyPosCnt+'</a></span>';
						}else{
							str+='<span>'+result.cpsyPosCnt+'</span>';
						}
						
						if(prajaSAMASYALATotalCount > 0){
							str+='<small id="" class="text-success"> '+((result.cpsyPosCnt*100)/(prajaSAMASYALATotalCount)).toFixed(2)+' %</small>';
						}else{
							str+='<small id="" class="text-success"> 0.0 %</small>';
						}
					str+='</td>';
					str+='<td>';
						str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
						if(result.cpsyNegCnt !=null && result.cpsyNegCnt>0){
							str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="0" attr_benefitId = "2" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result.cpsyNegCnt+'</a></span>';
						}else{
							str+='<span>'+result.cpsyNegCnt+'</span>';
						}
						
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
		url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaOrganizationWisePSYCounts/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaOrganizationWisePSYCounts/"
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
								if(toatlCountPrajaSank>0){
									str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId = "0" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+toatlCountPrajaSank+'</a></p>';
								}else{
									str+='<p>'+toatlCountPrajaSank+'</p>';
								}
								
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
								if(result[i].psyPosCnt !=null && result[i].psyPosCnt>0){
									str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="1" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result[i].psyPosCnt+'</a></span>';
								}else{
									str+='<span>'+result[i].psyPosCnt+'</span>';
								}
								
								if(toatlCountPrajaSank > 0){
									str+='<small id="" class="text-success"> '+((result[i].psyPosCnt*100)/((result[i].psyPosCnt)+(result[i].psyNegCnt))).toFixed(2)+' %</small>';
								}else{
									str+='<small id="" class="text-success"> 0.0 %</small>';
								}
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
								if(result[i].psyNegCnt !=null && result[i].psyNegCnt>0){
									str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="2" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result[i].psyNegCnt+'</a></span>';
								}else{
									str+='<span>'+result[i].psyNegCnt+'</span>';
								}
								
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
								if(toatlCountPrajaSam>0){
									str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="0" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+toatlCountPrajaSam+'</a></p>';
								}else{
									str+='<p>'+toatlCountPrajaSam+'</p>';
								}
								
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
								if(result[i].cpsyPosCnt !=null && result[i].cpsyPosCnt>0){
									str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="1" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result[i].cpsyPosCnt+'</a></span>';
								}else{
									str+='<span>'+result[i].cpsyPosCnt+'</span>';
								}
								
								if(toatlCountPrajaSam > 0){
									str+='<small id="" class="text-success"> '+((result[i].cpsyPosCnt*100)/(toatlCountPrajaSam)).toFixed(2)+' %</small>';
								}else{
									str+='<small id="" class="text-success"> 0.0 %</small>';
								}
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
								if(result[i].cpsyNegCnt !=null && result[i].cpsyNegCnt>0){
									str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="2" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result[i].cpsyNegCnt+'</a></span>';
								}else{
									str+='<span>'+result[i].cpsyNegCnt+'</span>';
								}
								
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
		url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaPublicationWisePSYCounts/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaPublicationWisePSYCounts/"
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
							
							if(toatlCountPrajaSank>0){
								str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="0" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+toatlCountPrajaSank+'</a></p>';
							}else{
								str+='<p>'+toatlCountPrajaSank+'</p>';
							}
							
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
							if(result[i].psyPosCnt !=null && result[i].psyPosCnt>0){
								str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="1" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result[i].psyPosCnt+'</a></span>';
							}else{
								str+='<span>'+result[i].psyPosCnt+'</span>';
							}
							
							if(toatlCountPrajaSank > 0){
								str+='<small id="" class="text-success"> '+((result[i].psyPosCnt*100)/((result[i].psyPosCnt)+(result[i].psyNegCnt))).toFixed(2)+' %</small>';
							}else{
								str+='<small id="" class="text-success"> 0.0 %</small>';
							}
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
							if(result[i].psyNegCnt !=null && result[i].psyNegCnt>0){
								str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1051" attr_organizationId ="'+result[i].id+'" attr_benefitId ="2" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result[i].psyNegCnt+'</a></span>';
							}else{
								str+='<span>'+result[i].psyNegCnt+'</span>';
							}
							
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
							if(toatlCountPrajaSam>0){
								str+='<p><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="0" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+toatlCountPrajaSam+'</a></p>';
							}else{
								str+='<p>'+toatlCountPrajaSam+'</p>';
							}
							
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
							if(result[i].cpsyPosCnt !=null && result[i].cpsyPosCnt>0){
								str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="1" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result[i].cpsyPosCnt+'</a></span>';
							}else{
								str+='<span>'+result[i].cpsyPosCnt+'</span>';
							}
							
							if(toatlCountPrajaSam > 0){
								str+='<small id="" class="text-success"> '+((result[i].cpsyPosCnt*100)/(toatlCountPrajaSam)).toFixed(2)+' %</small>';
							}else{
								str+='<small id="" class="text-success"> 0.0 %</small>';
							}
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
							if(result[i].cpsyNegCnt !=null && result[i].cpsyNegCnt>0){
								str+='<span><a class="newsArticlesDetailsCls" attr_categoryId="1050" attr_organizationId ="'+result[i].id+'" attr_benefitId ="2" attr_type="'+divId+''+type+'" attr_newsType="'+type+'">'+result[i].cpsyNegCnt+'</a></span>';
							}else{
								str+='<span>'+result[i].cpsyNegCnt+'</span>';
							}
							
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
		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePSYCounts/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePSYCounts/"
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
		url: wurl+"/CommunityNewsPortal/webservice/getBulletinOrganizationWisePSYCounts/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getBulletinOrganizationWisePSYCounts/"
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
		url: wurl+"/CommunityNewsPortal/webservice/getBulletinPointOverAllPSYCounts/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getBulletinPointOverAllPSYCounts/"
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
	var newsType = $(this).attr("attr_newsType");
	if(newsType == "printMedia"){
		window.open('showArticlesAction.action?categoryId='+categoryId+'&organizationId='+organizationId+'&benefitId='+benefitId+'&type='+type+'&callFrom=prajaSankalpa&startIndex=0&endIndex=6','_blank');
	}else{
		window.open('showElectronicBulletinsAction.action?categoryId='+categoryId+'&organizationId='+organizationId+'&benefitId='+benefitId+'&type='+type+'&callFrom=prajaSankalpa&startIndex=0&endIndex=6','_blank');
	}
	
		
	});
