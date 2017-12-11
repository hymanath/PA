var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var globalUserWiseMemberKaizalaRslt;
levelWiseArr=[{name:'district',id:'3'},{name:'parliament',id:'10'},{name:'constituency',id:'4'}];
function onloadKaizalaCalls1(){
	getOverAllCommitteeWiseMembersCounts();
	if($(".kaizalaIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		 getUserTypeWiseKaizalaCommitteeMemberDetailsCnt();
	}
	 if($(".morekaizalaBlocksIcon").hasClass("expandBlockKaizala")){
		 locationWiseDate()
		
	 }
}
$(document).on("click",".kaizalaIconExpand",function(){
	if($(this).find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getUserTypeWiseKaizalaCommitteeMemberDetailsCnt();
	}
		
});
$(document).on("click",".morekaizalaBlocksIcon",function(){
		$(this).addClass("expandBlockKaizala");
		$(".morekaizalaBlocksDetailed").show(); 
		locationWiseDate()
		
});
$(document).on("click",".expandBlockKaizala",function(){
		$(this).removeClass("expandBlockKaizala");
		$(".morekaizalaBlocksDetailed").hide();
});

function getOverAllCommitteeWiseMembersCounts(){  
  $("#overAllKaizalaBlockId").html(spinner);
	var jObj={}	
	$.ajax({
	 type: "POST",
	 url: "getOverAllCommitteeWiseMembersCountsAction.action",
	 data: {task :JSON.stringify(jObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildOverAllCommitteeWiseMembersCounts(result);
		}else{
			$("#overAllKaizalaBlockId").html("No Data Available");
		}		
	});
}
function buildOverAllCommitteeWiseMembersCounts(result){
	var str='';
	
	str+'<div class="row">';
	for(var i in result){
		str+='<h4 class="text-capitalize m_top10"><b>'+result[i].name+'</b></h4>';
		str+='<div class="m_top5">';
		  str+='<div class="table-responsive">';
				str+='<table class="table tableTraining bg_ED">';
					str+='<tbody>';
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-muted text-capitalize">total</p>';
								str+='<h4>'+result[i].totalCount+'</h4>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-muted text-capitalize">Installed</p>';
								str+='<h4>'+result[i].installed+' <span class="f_13 text-success"> '+result[i].installedPerc+'%</span></h4>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-muted text-capitalize">Pending</p>';
								str+='<h4>'+result[i].pending+' <span class="f_13 text-success"> '+result[i].pendingPerc+'%</span></h4>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-muted text-capitalize">Not Having Smart Phone</p>';
								str+='<h4>'+result[i].notHavingSmartPhone+' <span class="f_13 text-success"> '+result[i].notSmartPhonePerc+'%</span></h4>';
							str+='</td>';
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			str+='</div>';  
		str+='</div>';
	}
	str+='</div>'; 
	$("#overAllKaizalaBlockId").html(str);
}

function getUserTypeWiseKaizalaCommitteeMemberDetailsCnt(){  
  $("#userTypeWiseKaizalaDiv").html(spinner);
	var jObj={
		activityMemberId	:globalActivityMemberId,
		stateId				:globalStateId,
		userTypeId			:globalUserTypeId
			
	}	
	$.ajax({
	 type: "POST",
	 url: "getUserTypeWiseKaizalaCommitteeMemberDetailsCntAction.action",
	 data: {task :JSON.stringify(jObj)}
	}).done(function(result){
		buildgetUserTypeWiseKaizalaTopFiveStrong(result);
		globalUserWiseMemberKaizalaRslt = result;		
	});
}
$(document).on("click",".kaizalaLicls li",function(){
	var memberType=$(this).attr("attr_value");
		buildgetUserTypeWiseKaizalaTopFiveStrong(globalUserWiseMemberKaizalaRslt); 
	 if(memberType != null && memberType == "strong"){
	 }else if(memberType == "poor"){
		buildgetUserTypeWiseKaizalaTopFivePoor(globalUserWiseMemberKaizalaRslt)
	 }
});
function buildgetUserTypeWiseKaizalaTopFiveStrong(result){
		$("#userTypeWiseKaizalaDiv").html('');
		
		if(result != null && result.length > 0){
			for(var i in result){
				var installedCountArr=[];
				var pendingCountArr=[];
				var notHavingCountArr=[];
				var candidateNameArray=[]; 
				var countVar =0;
				
				if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						candidateNameArray.push(result[i][j].name);
						installedCountArr.push({"y":parseFloat(result[i][j].installedPerc),"extra":result[i][j].installed})
						pendingCountArr.push({"y":parseFloat(result[i][j].pendingPerc),"extra":result[i][j].pending})
						notHavingCountArr.push({"y":parseFloat(result[i][j].notSmartPhonePerc),"extra":result[i][j].notSmartPhone})
						
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
				
					
				if( result[i][j].installedPerc !=0 || result[i][j].pendingPerc !=0 || result[i][j].notSmartPhonePerc !=0){
					
					var str='';
					//str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';	
					str+='<div id="genSecKaizala'+i+'" style="height:170px;" class="scrollLenDiv"></div>';
					//str+='</div>'
					$("#userTypeWiseKaizalaDiv").append(str);
					
					$(function () {
						 $("#genSecKaizala"+i).highcharts({
							 colors: ['#7DDF7D','#a94442','#0061D0'],
							chart: {
								type: 'column'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								categories: candidateNameArray,
								type: 'category',
								labels: {
											formatter: function() {
												return this.value.toString().substring(0, 10)+'...';
											},
											
										}
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								},
								labels: {
									enabled:false
								}
							},
							legend: {
								enabled: false
							},
							
									
							plotOptions: {
								column: {
									stacking: 'percent',
									dataLabels: {
										enabled: true,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return (this.y) +'%';
											}
										}
									  
									}
								}
							},
							tooltip: {
								formatter: function () {
									var s = '<b>' + this.x + '</b>';
									$.each(this.points, function () {
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :'+this.point.extra+' -  ' +
											(this.y)+'%';
									});

									return s;
								},
								shared: true
							},
								
								series: [{
									name: 'Installed',
									data: installedCountArr
								},{
									name: 'Pending',
									data: pendingCountArr
								},{
									name: 'Not Having Smart Phone',
									data: notHavingCountArr
								}],
						 
						});
					});
				} 
				else{
					$("#genSecKaizala"+i).html("No Data Available");
					$("#genSecKaizala"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			$("#userTypeWiseKaizalaDiv").html("");
		}
		
		$("#userTypeWiseKaizalaDiv").each(function(){
			var scrollengthDiv = $(this).find(".scrollLenDiv").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBarKaizala").mCustomScrollbar({setHeight:'560px'})
				
			}else{
				$(".verticalScrollBarKaizala").css("height","auto");
			
			}
		});
		
	}
	
	function buildgetUserTypeWiseKaizalaTopFivePoor(result){
		$("#userTypeWiseKaizalaDiv").html('');
		if(result != null && result.length > 0){
			for(var i in result){
				
				var installedCountArr=[];
				var pendingCountArr=[];
				var notHavingCountArr=[];
				var candidateNameArray=[]; 
				var countVar =0;
				var installed = 0;
				var pending = 0;
				var notSmartPhone = 0;
				
				if(result[i] !=null && result[i].length  >0){
					for(var j = result[i].length -1; j >= 0; j--){
						
						installed = result[i][j].installed;
						pending = result[i][j].pending;
						notSmartPhone = result[i][j].notSmartPhone;
						
						candidateNameArray.push(result[i][j].name);
						
						installedCountArr.push({"y":parseFloat(result[i][j].installedPerc),"extra":result[i][j].installed})
						pendingCountArr.push({"y":parseFloat(result[i][j].pendingPerc),"extra":result[i][j].pending})
						notHavingCountArr.push({"y":parseFloat(result[i][j].notSmartPhonePerc),"extra":result[i][j].notSmartPhone})
						
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
			  		
				if( installedPerc !=0 || pendingPerc !=0 || notSmartPhonePerc !=0){
					var str='';
					
					//str+='<div class="col-sm-12">';
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';	
					str+='<div id="genSecKaizala1'+i+'" class="m_top20 scrollLenPoorDiv" style="height:170px;" ></div>';
					//str+='</div>';
				
					$("#userTypeWiseKaizalaDiv").append(str);
					
					var getWidth = $("#genSecKaizala1"+i).parent().width()+'px';
					$("#genSecKaizala1"+i).width(getWidth);
					$(function () {
						 $("#genSecKaizala1"+i).highcharts({
							colors: ['#7DDF7D','#a94442','#0061D0'],
							chart: {
								type: 'column'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								categories: candidateNameArray,
								type: 'category',
								labels: {
											formatter: function() {
												return this.value.toString().substring(0, 10)+'...';
											},
											
										}
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								}

							},
							legend: {
								enabled: false
							},
							
									
							plotOptions: {
								column: {
									stacking: 'percent',
									dataLabels: {
										enabled: true,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return (this.y) + '%';
											}
										}
									  
									}
								}
							},

							/* tooltip: {
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
							}, */
							tooltip: {
								formatter: function () {
									var s = '<b>' + this.x + '</b>';
									$.each(this.points, function () {
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :'+this.point.extra+' -  ' +
											(this.y)+'%';
									});

									return s;
								},
								shared: true
							},
								series: [{
									name: 'Installed',
									data: installedCountArr
								},{
									name: 'Pending',
									data: pendingCountArr
								},{
									name: 'Not Having Smart Phone',
									data: notHavingCountArr
								}],
						 
						});
					});
				}else{
					$("#genSecKaizala1"+i).html("No Data Available");
					$("#genSecKaizala1"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			$("#userTypeWiseKaizalaDiv").html("No Data Available");
		}
		
		$("#userTypeWiseKaizalaDiv").each(function(){
			var scrollengthDiv = $(this).find(".scrollLenPoorDiv").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBarKaizala").mCustomScrollbar({setHeight:'560px'})
				
			}else{
				$(".verticalScrollBarKaizala").css("height","auto");
			
			}
		});
		
	}

function locationWiseDate()
{
	var collapse='';
	for(var i in levelWiseArr)
	{
		collapse+='<div class="panel-group" id="accordion'+levelWiseArr[i].id+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default m_top20">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+levelWiseArr[i].id+'" style="background-color:#f1f1f1;">';
					if(i == 0)
					{
						collapse+='<a role="button" class="collapseDebatesIcon '+levelWiseArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+levelWiseArr[i].id+'" href="#collapse'+levelWiseArr[i].id+'" aria-expanded="true" level_name="'+levelWiseArr[i].name+'" aria-controls="collapse'+levelWiseArr[i].id+'">';
					}else{
						collapse+='<a role="button" class="collapseDebatesIcon collapsed '+levelWiseArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+levelWiseArr[i].id+'" href="#collapse'+levelWiseArr[i].id+'" level_name="'+levelWiseArr[i].name+'" aria-expanded="true" aria-controls="collapse'+levelWiseArr[i].id+'">';
					}
					collapse+='<h4 class="panel-title text-capital">'+levelWiseArr[i].name+' Wise Overview</h4>';
						
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+levelWiseArr[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+levelWiseArr[i].id+'">';
				}else{
					collapse+='<div id="collapse'+levelWiseArr[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+levelWiseArr[i].id+'">';
				}
				
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
						collapse+='<div class="col-sm-12">';
						collapse+='<div id="kaizala'+levelWiseArr[i].name+'"></div>';
					collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	}
	$("#levelWiseDetailsDivId").html(collapse);
	for(var i in levelWiseArr)
	{
		getLocationWiseCommitteeMemberDetailsAction(levelWiseArr[i].name);
	}	
}
function getLocationWiseCommitteeMemberDetailsAction(locationType){  
	$("#kaizala"+locationType).html(spinner);
	var jObj={
		name:locationType
	}	
	$.ajax({
	 type: "POST",
	 url: "getLocationWiseCommitteeMemberDetailsAction.action",
	 data: {task :JSON.stringify(jObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			 buildLocationWiseCommitteeMemberDetails(result,locationType);
		}else{
			 $("#kaizala"+locationType).html("NO DATA AVAILABLE");
		 }		
	});
}
function  buildLocationWiseCommitteeMemberDetails(result,locationType){
	var str = '';
			str+='<div class="row">';
			  str+='<div class="col-sm-12">';
			str+='<h5 class="pull-left">NSP - No Smart Phone</h5>';
			str+='</div>';
			str+='</div>';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table-condensed tableStyleLed table_alignment table-noborder dataTable'+locationType+'" style="width:100%">';
					str+='<thead style="background-color:#f0f0f0">';
							str+='<tr>';
								if(locationType == "district"){
									str+='<th class="text-capital" rowspan="3">District</th>';
									str+='<th class="text-capital" rowspan="3">Total</th>';
									str+='<th class="text-capital" rowspan="3">Installed</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									str+='<th class="text-capital" rowspan="3">Pending</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									str+='<th class="text-capital" rowspan="3">NSP</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									
								}else if(locationType == "parliament"){
									str+='<th class="text-capital" rowspan="3">parliament</th>';
									str+='<th class="text-capital" rowspan="3">Total</th>';
									str+='<th class="text-capital" rowspan="3">Installed</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									str+='<th class="text-capital" rowspan="3">Pending</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									str+='<th class="text-capital" rowspan="3">NSP</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									
								}
								else if(locationType == "constituency"){
									str+='<th class="text-capital" rowspan="3">Parliament</th>';
									str+='<th class="text-capital" rowspan="3">Constituency</th>';
									str+='<th class="text-capital" rowspan="3">Total</th>';
									str+='<th class="text-capital" rowspan="3">Installed</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									str+='<th class="text-capital" rowspan="3">Pending</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									str+='<th class="text-capital" rowspan="3">NSP</th>';
									str+='<th class="text-capital" rowspan="3">%</th>';
									
								}
							str+='</tr>';
							str+='<tr>';
								for(var i in result[0].subList){
									if(locationType != "district"){
										if(result[0].subList[i].name !="DISTRICT"){
											str+='<th colspan="7">'+result[0].subList[i].name+'</th>';
										}
									}else{
										str+='<th colspan="7">'+result[0].subList[i].name+'</th>';
									}
									
								}
							str+='</tr>';
							str+='<tr>';
								for(var i in result[0].subList){
									if(locationType != "district"){
										if(result[0].subList[i].name !="DISTRICT"){
											str+='<th style="border-left:1px solid #ccc;">Total</th>';
											str+='<th>Installed</th>';
											str+='<th> % </th>';
											str+='<th>Pending</th>';
											str+='<th> % </th>';
											str+='<th>No Smart Phone</th>';
											str+='<th> % </th>';
										}
									}else{
										str+='<th style="border-left:1px solid #ccc;">Total</th>';
										str+='<th>Installed</th>';
										str+='<th> % </th>';
										str+='<th>Pending</th>';
										str+='<th> % </th>';
										str+='<th>No Smart Phone</th>';
										str+='<th> % </th>';
									}
								}
							str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								if(locationType == "constituency")
									str+='<td>'+result[i].parlName+'</td>';
								str+='<td>'+result[i].name+'</td>';
								
								str+='<td>'+result[i].totalCount+'</td>';
								str+='<td>'+result[i].installed+'</td>';
								str+='<td class="f_13 text-success">'+result[i].installedPerc+'</td>';
								str+='<td>'+result[i].pending+'</td>';
								str+='<td class="f_13 text-success">'+result[i].pendingPerc+'</td>';
								str+='<td>'+result[i].notHavingSmartPhone+'</td>';
								str+='<td class="f_13 text-success">'+result[i].notSmartPhonePerc+'</td>';
								
								if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										if(locationType != "district"){
											if(result[i].subList[j].name !="DISTRICT"){
												if(result[i].subList[j].totalCount == null || result[i].subList[j].totalCount == 0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].subList[j].totalCount+' </td>';
												}
												if(result[i].subList[j].installedPerc == null || result[i].subList[j].installedPerc == 0){
													str+='<td> - </td>';
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].subList[j].installed+'</td>';
													str+='<td class="f_13 text-success">'+result[i].subList[j].installedPerc+'</td>';
												}
												
												if(result[i].subList[j].pendingPerc == null || result[i].subList[j].pendingPerc == 0){
													str+='<td> - </td>';
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].subList[j].pending+'</td>';
													str+='<td class="f_13 text-success">'+result[i].subList[j].pendingPerc+'</span></td>';
												}
												if(result[i].subList[j].notHavingSmartPhone == null || result[i].subList[j].notHavingSmartPhone == 0){
													str+='<td> - </td>';
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].subList[j].notHavingSmartPhone+'</td>';
													str+='<td class="f_13 text-success">'+result[i].subList[j].notSmartPhonePerc+'</td>';
												}
											}
										}else{
											if(result[i].subList[j].totalCount == null || result[i].subList[j].totalCount == 0){
												str+='<td> - </td>';
											}else{
												str+='<td>'+result[i].subList[j].totalCount+' </td>';
											}
											if(result[i].subList[j].installedPerc == null || result[i].subList[j].installedPerc == 0){
												str+='<td> - </td>';
												str+='<td> - </td>';
											}else{
												str+='<td>'+result[i].subList[j].installed+'</td>';
												str+='<td class="f_13 text-success">'+result[i].subList[j].installedPerc+'</td>';
											}
											
											if(result[i].subList[j].pendingPerc == null || result[i].subList[j].pendingPerc == 0){
												str+='<td> - </td>';
												str+='<td> - </td>';
											}else{
												str+='<td>'+result[i].subList[j].pending+'</td>';
												str+='<td class="f_13 text-success">'+result[i].subList[j].pendingPerc+'</span></td>';
											}
											if(result[i].subList[j].notHavingSmartPhone == null || result[i].subList[j].notHavingSmartPhone == 0){
												str+='<td> - </td>';
												str+='<td> - </td>';
											}else{
												str+='<td>'+result[i].subList[j].notHavingSmartPhone+'</td>';
												str+='<td class="f_13 text-success">'+result[i].subList[j].notSmartPhonePerc+'</td>';
											}
										}
											
										
									}
									
								}
								
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			$("#kaizala"+locationType).html(str);
			
			if(locationType !="district"){
				$(".dataTable"+locationType).dataTable({
					"iDisplayLength": 10,
					"aaSorting": [],
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
					
				});
			}else{
				$(".dataTable"+locationType).dataTable({
					"paging":   false,
					"info":     false,
					"searching": true,
					"autoWidth": true
				});
			} 
}