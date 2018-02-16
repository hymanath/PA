var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
levelWiseNominatedArr=[{name:'state',id:'2'},{name:'district',id:'3'},{name:'constituency',id:'4'}];
function onloadNominatedCalls(){
	getLevelWisePostsOverView();
	//if($(".moreAttNominatedBlocksIcon").hasClass("expandBlockNominated")){
		$("[role='tabCummulativeNomi1'] li").removeClass("active");
		$("[role='tabCummulativeNomi1'] li:nth-child(1)").addClass("active");
		 locationNominatedWiseDate('location');
		
	 //}
}
$(document).on("click",".nominatedIconExpand",function(){
	if($(this).find("i").hasClass("glyphicon glyphicon-resize-small" )){
		$("[role='tabCummulativeNomi'] li").removeClass("active");
		$("[role='tabCummulativeNomi'] li:nth-child(1)").addClass("active");
		getUserTypeWiseNominatedPostDetailsCnt();
	}
		
});
$(document).on("click",".moreAttNominatedBlocksIcon",function(){
		$(this).addClass("expandBlockNominated");
		$(this).removeClass("moreAttNominatedBlocksIcon");
		$(".moreAttNominatedBlocks").show(); 
		$(".deptLocNominatedCls").show(); 
		$("[role='tabCummulativeNomi1'] li").removeClass("active");
		$("[role='tabCummulativeNomi1'] li:nth-child(1)").addClass("active");
		locationNominatedWiseDate('location');
		
});
$(document).on("click",".expandBlockNominated",function(){
		$(this).removeClass("expandBlockNominated");
		$(this).addClass("moreAttNominatedBlocksIcon");
		$(".moreAttNominatedBlocks").hide();
		$(".deptLocNominatedCls").hide();
});
$(document).on("click",".deptLocChnageCls li",function(){
	$(this).closest("ul").removeClass("active");
	$(this).addClass("active");
	var type = $(this).attr("attr_location_type");
	if(type == "department"){
		locationNominatedWiseDate('department');
	}else{
		locationNominatedWiseDate('location');
	}
});
function getLevelWisePostsOverView(){ 
	$("#levelWiseNominatedPostDivId").html(spinner);
	var jsObj =
	{
		 "locationValuesArr" :[],
		 "fromDateStr" : " ",
		 "toDateStr" : " ",
		 "locationTypeId" : 2,
		 "boardLevelId": 2,
		 "activityMemberId" :globalActivityMemberId
		 
	  }
	$.ajax({
		type : 'POST',
		url : 'getLevelWisePostsOverViewAction1.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildLevelWisePostsOverView(result);
		}else{
			$("#levelWiseNominatedPostDivId").html("No Data Available")
		}
	});
}
function buildLevelWisePostsOverView(result){
	var str='';
	for(var i in result){
		str+='<h4 class="bg_49 m_top10" style="padding: 7px;color:#fff;">'+result[i].board+' Level</h4>';
		str+='<div class="table-responsive">';
			str+='<table class="table table-responsive borderless">';
				str+='<tr>';
					str+='<td class="border-top0">';
						str+='<div class="pad_yash_8">';
							str+='<h5>Total&nbsp;Posts</h5>';
							if(result[i].totalPosts !=null && result[i].totalPosts>0){
								str+='<h5 class="m_top25 font_weight">'+result[i].totalPosts+'</h5>';
							}else{
								str+='<h5 class="m_top10 font_weight"> - </h5>';
							}
							
						str+='</div>';
					str+='</td>';
					str+='<td class="border-top0">';
							str+='<div class="pad_yash_8">';
								str+='<h5> Received Applications</h5>';
								if(result[i].recivedCount !=null && result[i].recivedCount>0){
									str+='<h5 class="m_top10 font_weight " >'+result[i].recivedCount+'</h5>';
								}else{
									str+='<h5 class="m_top10 font_weight"> - </h5>';
								}
								
							str+='</div>';
					str+='</td>';
					str+='<td class="border-top0">';
						str+='<div class="pad_yash_8">';
							str+='<h5>Open&nbsp;Posts</h5>';
							if(result[i].openPostPer !=null && result[i].openPostPer>0){
								str+='<h5 class="m_top25 font_weight statusClickCls"  attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="open" attr_department_name="'+result[i].board+' Level" attr_department_id="0">'+result[i].openCount+' <small style="color:green">'+result[i].openPostPer.toFixed(1)+'%</small></h5>';
							}else{
								str+='<h5 class="m_top25 font_weight"> - </h5>';
							}
							
						str+='</div>';
					str+='</td>';
					str+='<td class="border-top0">';
						str+='<div class="pad_yash_8">';
							str+='<h5>Finalized <br>Posts</h5>';
							if(result[i].finalizedPer !=null && result[i].finalizedPer>0){
								str+='<h5 class="m_top10 font_weight statusClickCls"   attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="finalIssed" attr_department_name="'+result[i].board+' Level" attr_department_id="0" attr_board_statusIds="2">'+result[i].finalizedPost+' <small style="color:green">'+result[i].finalizedPer.toFixed(1)+'%</small></h5>';
							}else{
								str+='<h5 class="m_top10 font_weight"> - </h5>';
							}
							
						str+='</div>';
					str+='</td>';
					str+='<td class="border-top0">';
						str+='<div class="pad_yash_8">';
							str+='<h5>Completed/ <br>G.O Issued</h5>';
							if(result[i].goIssuedPer !=null && result[i].goIssuedPer>0){
								str+='<h5 class="m_top10 font_weight statusClickCls"  attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="goIssued" attr_department_name="'+result[i].board+' Level" attr_department_id="0" attr_board_statusIds="0">'+result[i].goIsuuedCount+' <small style="color:green">'+result[i].goIssuedPer.toFixed(1)+'%</small></h5>';
							}else{
								str+='<h5 class="m_top10 font_weight"> - </h5>';
							}
							
						str+='</div>';
					str+='</td>';
				str+='</tr>';
			str+='</table>';
		str+='</div>';
	}		
	$("#levelWiseNominatedPostDivId").html(str);
}
var globalNominatedUserWiseMemberRslt;
function getUserTypeWiseNominatedPostDetailsCnt(){
	$("#userTypeWiseNominatedDiv").html(spinner);
	var jsObj ={
		 "fromDateStr" : " ",
		 "toDateStr" : " ",
		 "activityMemberId" :globalActivityMemberId,
		 "stateId" : globalStateId,
		 "userTypeId" :globalUserTypeId
	  }
	$.ajax({
		type : 'POST',
		url : 'getUserTypeWiseNominatedPostDetailsCntAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$("#userTypeWiseNominatedDiv").html('');
		buildNominatedgetUserTypeWiseTopFiveStrong(result);
		
		//buildgetUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(result);
		 globalNominatedUserWiseMemberRslt = result;
	});
}

$(document).on("click",".nominatedLicls li",function(){
	var memberType=$(this).attr("attr_value");
		buildNominatedgetUserTypeWiseTopFiveStrong(globalNominatedUserWiseMemberRslt); 
	 if(memberType != null && memberType == "strong"){
	 }else if(memberType == "poor"){
		buildNominatedgetUserTypeWiseTopFivePoor(globalNominatedUserWiseMemberRslt)
	 }
});

function buildNominatedgetUserTypeWiseTopFiveStrong(result){
		$("#userTypeWiseNominatedDiv").html('');
		
		if(result != null && result.length > 0){
			for(var i in result){
				var centralLevelCountArr=[];
				var stateLevelCountArr=[];
				var districtLevelCountArr=[];
				var constituencyLevelNameArray=[]; 
				var mandalLevelNameArray=[]; 
				var villageLevelNameArray=[]; 
				var countVar =0;
				var candidateNameArray=[];
				if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						candidateNameArray.push(result[i][j].name.toUpperCase());
						
						centralLevelCountArr.push({"y":parseFloat(result[i][j].centralPostsPerc),"extra":result[i][j].centralPosts})
						stateLevelCountArr.push({"y":parseFloat(result[i][j].statePostsPerc),"extra":result[i][j].statePosts})
						districtLevelCountArr.push({"y":parseFloat(result[i][j].districtPostsPerc),"extra":result[i][j].districtPosts})
						constituencyLevelNameArray.push({"y":parseFloat(result[i][j].constncyPostsPerc),"extra":result[i][j].constncyPosts})
						mandalLevelNameArray.push({"y":parseFloat(result[i][j].mandalPostsPerc),"extra":result[i][j].mandalPosts})
						villageLevelNameArray.push({"y":parseFloat(result[i][j].villagePostsPerc),"extra":result[i][j].villagePosts})
						
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
				
					
				if( result[i][j].centralPostsPerc !=0 || result[i][j].statePostsPerc !=0 || result[i][j].constncyPostsPerc !=0 || result[i][j].mandalPostsPerc !=0 || result[i][j].villagePostsPerc !=0){
					
					var str='';
					//str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';	
					str+='<div id="genSecNominated'+i+'" style="height:170px;" class="scrollLenDiv"></div>';
					//str+='</div>'
					$("#userTypeWiseNominatedDiv").append(str);
					
					$(function () {
						 $("#genSecNominated"+i).highcharts({
							 colors: ['#119215','#8D4653','#E7D768','#8085E9','#FD9700','#80E9D3'],
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
									name: 'Central Level Posts',
									data: centralLevelCountArr
								},{
									name: 'State Level Posts',
									data: stateLevelCountArr
								},{
									name: 'District Level Posts',
									data: districtLevelCountArr
								},{
									name: 'Constituency Level Posts',
									data: constituencyLevelNameArray
								},{
									name: 'Mandal Level Posts',
									data: mandalLevelNameArray
								},{
									name: 'Village Level Posts',
									data: villageLevelNameArray
								}],
							});
					});
				} 
				else{
					$("#genSecNominated"+i).html("No Data Available");
					$("#genSecNominated"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			$("#userTypeWiseNominatedDiv").html("");
		}
		
		$("#userTypeWiseNominatedDiv").each(function(){
			var scrollengthDiv = $(this).find(".scrollLenDiv").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBarNominated").mCustomScrollbar({setHeight:'560px'})
				
			}else{
				$(".verticalScrollBarNominated").css("height","auto");
			
			}
		});
		
}
	
function buildNominatedgetUserTypeWiseTopFivePoor(result){
	$("#userTypeWiseNominatedDiv").html('');
	if(result != null && result.length > 0){
			for(var i in result){
				
				var centralLevelCountArr=[];
				var stateLevelCountArr=[];
				var districtLevelCountArr=[];
				var constituencyLevelNameArray=[]; 
				var mandalLevelNameArray=[]; 
				var villageLevelNameArray=[]; 
				
				var candidateNameArray=[]; 
				
				var centralLevelVar =0;
				var stateLevel = 0;
				var districtLevel = 0;
				var constituencyLevel = 0;
				var mandalLevel = 0;
				var villageLevel = 0;
				var countVar=0;
				
				if(result[i] !=null && result[i].length  >0){
					for(var j = result[i].length -1; j >= 0; j--){
						
						candidateNameArray.push(result[i][j].name.toUpperCase());
						
						centralLevelVar = result[i][j].centralPosts;
						stateLevel = result[i][j].statePosts;
						districtLevel = result[i][j].districtPosts;
						constituencyLevel = result[i][j].constncyPosts;
						mandalLevel = result[i][j].mandalPosts;
						villageLevel = result[i][j].villagePosts;
						
						centralLevelCountArr.push({"y":parseFloat(result[i][j].centralPostsPerc),"extra":result[i][j].centralPosts})
						stateLevelCountArr.push({"y":parseFloat(result[i][j].statePostsPerc),"extra":result[i][j].statePosts})
						districtLevelCountArr.push({"y":parseFloat(result[i][j].districtPostsPerc),"extra":result[i][j].districtPosts})
						constituencyLevelNameArray.push({"y":parseFloat(result[i][j].constncyPostsPerc),"extra":result[i][j].constncyPosts})
						mandalLevelNameArray.push({"y":parseFloat(result[i][j].mandalPostsPerc),"extra":result[i][j].mandalPosts})
						villageLevelNameArray.push({"y":parseFloat(result[i][j].villagePostsPerc),"extra":result[i][j].villagePosts})
						
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
			  		
				if( centralLevelVar !=0 || stateLevel !=0 || districtLevel !=0 || constituencyLevel !=0 || mandalLevel !=0 || villageLevel !=0){
					var str='';
					
					//str+='<div class="col-sm-12">';
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';	
					str+='<div id="genSecNominated1'+i+'" class="m_top20 scrollLenPoorDiv" style="height:170px;" ></div>';
					//str+='</div>';
				
					$("#userTypeWiseNominatedDiv").append(str);
					
					var getWidth = $("#genSecNominated1"+i).parent().width()+'px';
					$("#genSecNominated1"+i).width(getWidth);
					$(function () {
						 $("#genSecNominated1"+i).highcharts({
							 colors: ['#119215','#8D4653','#E7D768','#8085E9','#FD9700','#80E9D3'],
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
									name: 'Central Level Posts',
									data: centralLevelCountArr
								},{
									name: 'State Level Posts',
									data: stateLevelCountArr
								},{
									name: 'District Level Posts',
									data: districtLevelCountArr
								},{
									name: 'Constituency Level Posts',
									data: constituencyLevelNameArray
								},{
									name: 'Mandal Level Posts',
									data: mandalLevelNameArray
								},{
									name: 'Village Level Posts',
									data: villageLevelNameArray
								}],
						 
						});
					});
				}else{
					$("#genSecNominated1"+i).html("No Data Available");
					$("#genSecNominated1"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			$("#userTypeWiseNominatedDiv").html("No Data Available");
		}
		
		$("#userTypeWiseNominatedDiv").each(function(){
			var scrollengthDiv = $(this).find(".scrollLenPoorDiv").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBarNominated").mCustomScrollbar({setHeight:'560px'})
				
			}else{
				$(".verticalScrollBarNominated").css("height","auto");
			
			}
		});
	
}
function locationNominatedWiseDate(locationTypeVal)
{
	var collapse='';
	for(var i in levelWiseNominatedArr)
	{
		collapse+='<div class="panel-group" id="accordion'+locationTypeVal+levelWiseNominatedArr[i].id+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default m_top20">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+locationTypeVal+levelWiseNominatedArr[i].id+'" style="background-color:#5F6A73;">';
					if(i == 0)
					{
						collapse+='<a role="button" class="collapseNominatedIcon '+locationTypeVal+levelWiseNominatedArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+locationTypeVal+levelWiseNominatedArr[i].id+'" href="#collapse'+locationTypeVal+levelWiseNominatedArr[i].id+'" aria-expanded="true" level_name="'+locationTypeVal+levelWiseNominatedArr[i].name+'" aria-controls="collapse'+locationTypeVal+levelWiseNominatedArr[i].id+'">';
					}else{
						collapse+='<a role="button" class="collapseNominatedIcon collapsed '+locationTypeVal+levelWiseNominatedArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+locationTypeVal+levelWiseNominatedArr[i].id+'" href="#collapse'+locationTypeVal+levelWiseNominatedArr[i].id+'" level_name="'+locationTypeVal+levelWiseNominatedArr[i].name+'" aria-expanded="true" aria-controls="collapse'+locationTypeVal+levelWiseNominatedArr[i].id+'">';
					}
					collapse+='<h4 class="panel-title text-capital" style="color:#fff;">'+levelWiseNominatedArr[i].name+' Level Overview</h4>';
						
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+locationTypeVal+levelWiseNominatedArr[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+locationTypeVal+levelWiseNominatedArr[i].id+'">';
				}else{
					collapse+='<div id="collapse'+locationTypeVal+levelWiseNominatedArr[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+locationTypeVal+levelWiseNominatedArr[i].id+'">';
				}
				
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
						collapse+='<div class="col-sm-12">';
						collapse+='<div id="nominated'+locationTypeVal+levelWiseNominatedArr[i].name+'"></div>';
					collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	}
	$("#levelWiseNominatedDetailsDivId").html(collapse);
	for(var i in levelWiseNominatedArr){
		if(locationTypeVal == "department"){
			getDepartmentWisePostAndApplicationDetails(levelWiseNominatedArr[i].id,levelWiseNominatedArr[i].name,locationTypeVal);
		}else{
			if(levelWiseNominatedArr[i].name == "state"){
				getNominatedPostStateWiseCount(levelWiseNominatedArr[i].id,levelWiseNominatedArr[i].name,locationTypeVal)
			}else{
				getNominatedPostLocationWiseBoardLevelCount(levelWiseNominatedArr[i].id,levelWiseNominatedArr[i].name,locationTypeVal)
			}
			
			
		}
		
	}
}
function getNominatedPostStateWiseCount(locationTypeId,divId,locationTypeVal){ 
	$("#nominated"+locationTypeVal+divId).html(spinner);
	var jsObj ={
				 "fromDateStr" : " ",
				 "toDateStr" : " ",
				 "activityMemberId" :globalActivityMemberId
			  }
	$.ajax({
		type : 'POST',
		url : 'getNominatedPostStateWiseCountAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentWisePostAndApplicationDetails(result,divId,locationTypeVal);
		}else{
			$("#nominated"+locationTypeVal+divId).html("No Data Available");
		}
		
	});
}

function getDepartmentWisePostAndApplicationDetails(locationTypeId,divId,locationTypeVal){
		$("#nominated"+locationTypeVal+divId).html(spinner);
		var jsObj ={
			 "locationValuesArr" : [],
			 "fromDateStr" : " ",
			 "toDateStr" : " ",
			 "locationTypeId" :locationTypeId,
			 "year" : " ",
			 "boardLevelId":locationTypeId,
			 "deptId": 0,
			 "activityMemberId" :globalActivityMemberId
		  }
		$.ajax({
			type : 'POST',
			url : 'getDepartmentWisePostAndApplicationDetailsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildDepartmentWisePostAndApplicationDetails(result,divId,locationTypeVal);
			}else{
				$("#nominated"+locationTypeVal+divId).html("No Data Available");
			}
		});
}
function getNominatedPostLocationWiseBoardLevelCount(locationTypeId,divId,locationTypeVal){ 
	$("#nominated"+locationTypeVal+divId).html(spinner);
	var jsObj ={
			 "locationValuesArr" : [],
			 "fromDateStr" : " ",
			 "toDateStr" : " ",
			 "locationTypeId" : locationTypeId,
			 "boardLevelId":locationTypeId,
			 "activityMemberId" :globalActivityMemberId
			 
		  }
	$.ajax({
		type : 'POST',
		url : 'getNominatedPostLocationWiseBoardLevelCountAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildNominatedPostLocationWiseBoardLevelCount(result,divId,locationTypeVal,locationTypeId);
		}else{
			$("#nominated"+locationTypeVal+divId).html("No Data Available");
		}
	});
}
 
function buildDepartmentWisePostAndApplicationDetails(result,divId,locationTypeVal){
	var str='';
	str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table-condensed tableStyleLed table_alignment table-noborder dataTableNomi'+locationTypeVal+divId+'" style="width:100%">';
					str+='<thead style="background-color:#edeef0">';
						str+='<tr>';
						if(locationTypeVal == "location"){
							str+='<th class="font_weight">State</th>';
							str+='<th class="font_weight">Total Posts</th>';
							str+='<th class="font_weight">G.O Issued</th>';
							str+='<th class="font_weight">Finalized Posts</th>';
							str+='<th class="font_weight">Open Posts</th>';
							str+='<th class="font_weight">Expire in 1Month</th>';
							str+='<th class="font_weight">Expire in 2Month</th>';
							str+='<th class="font_weight">Expire in 3Month</th>'; 
						}else{
							str+='<th class="font_weight">Department</th>';
							str+='<th class="font_weight">Total Posts</th>';
							str+='<th class="font_weight">G.O Issued</th>';
							str+='<th class="font_weight">Finalized Posts</th>';
							str+='<th class="font_weight">Open Posts</th>';
							str+='<th class="font_weight">Expire in 1Month</th>';
							str+='<th class="font_weight">Expire in 2Month</th>';
							str+='<th class="font_weight">Expire in 3Month</th>';
						}
							
							
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
								str+='<tr>';
									if(locationTypeVal == "location"){ 
										str+='<td>'+result[i].locationName+'</td>';
										str+='<td>'+result[i].totalPosts+'</td>';
										str+='<td class="statusClickCls"  attr_type="goIssued" attr_department_name="'+result[i].locationName+'" attr_department_id="0" attr_boardLevelId="2" attr_board_statusIds="4">'+result[i].goIsuuedCount+'</td>';
										str+='<td class="statusClickCls"  attr_type="finalIssued" attr_department_name="'+result[i].locationName+'" attr_department_id="0" attr_boardLevelId="2" attr_board_statusIds="3">'+result[i].finalizedPost+'</td>';
										str+='<td class="statusClickCls"  attr_type="open" attr_department_name="'+result[i].locationName+'" attr_department_id="0" attr_boardLevelId="2" attr_board_statusIds="1">'+result[i].openCount+'</td>';
										str+='<td>'+result[i].expireOneMonth+'</td>';
										str+='<td>'+result[i].exprireTwoMnth+'</td>';
										str+='<td>'+result[i].expireThreeMnth+'</td>';
									}else{
										str+='<td>'+result[i].name+'</td>';
										str+='<td>'+result[i].totalCount+'</td>';
										str+='<td>'+result[i].goIsuuedCnt+'</td>';
										str+='<td>'+result[i].finalizedCnt+'</td>';
										str+='<td>'+result[i].openCnt+'</td>';
										str+='<td>'+result[i].expireOneMnth	+'</td>';
										str+='<td>'+result[i].expireTwoMnth+'</td>';
										str+='<td>'+result[i].expireThreMnth+'</td>';
									}
									
								str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			$("#nominated"+locationTypeVal+divId).html(str);
			$(".dataTableNomi"+locationTypeVal+divId).dataTable({
				"iDisplayLength": 10,
				"aaSorting": [[4,'desc']],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				
			});
			 
}
function buildNominatedPostLocationWiseBoardLevelCount(result,divId,locationTypeVal,locationTypeId){
	var str='';
	str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table-condensed tableStyleLed table_alignment table-noborder dataTableNomiL'+locationTypeVal+divId+'" style="width:100%">';
					str+='<thead style="background-color:#edeef0">';
						str+='<tr>';
							str+='<th class="font_weight" rowspan="2" style="background-color:#EFF0F1 !important;">District</th>';
							for(var i in result[0].levelList){
								if(result[0].levelList[i].locationName =="District"){
									str+='<th class="font_weight" colspan="4" style="background-color:#EDECE0 !important;">'+result[0].levelList[i].locationName+' Level Posts</th>';
								}else if(result[0].levelList[i].locationName =="Assembly"){
									str+='<th class="font_weight" colspan="4" style="background-color:#E3E4F0 !important;">Constituency Level Posts</th>';
								}else if(result[0].levelList[i].locationName =="Mandal/Muncipality/Corporation"){
									str+='<th class="font_weight" colspan="4" style="background-color:#F0E6D7 !important;">'+result[0].levelList[i].locationName+' Level Posts</th>';
								}else if(result[0].levelList[i].locationName =="Village"){
									str+='<th class="font_weight" colspan="4" style="background-color:#ECF7F4 !important;">'+result[0].levelList[i].locationName+' Level Posts</th>';
								}
								
							}
						str+='</tr>';
						str+='<tr>';
						for(var i in result[0].levelList){
								if(result[0].levelList[i].locationName =="District"){
									str+='<th class="font_weight" style="background-color:#EDECE0 !important;">Total Posts</th>';
									str+='<th class="font_weight" style="background-color:#EDECE0 !important;">Open Posts</th>';
									str+='<th class="font_weight" style="background-color:#EDECE0 !important;">Final Review</th>';
									str+='<th class="font_weight" style="background-color:#EDECE0 !important;">Completed/ G.O Issued</th>';
								}else if(result[0].levelList[i].locationName =="Assembly"){
									str+='<th class="font_weight" style="background-color:#E3E4F0 !important;">Total Posts</th>';
									str+='<th class="font_weight" style="background-color:#E3E4F0 !important;">Open Posts</th>';
									str+='<th class="font_weight" style="background-color:#E3E4F0 !important;">Final Review</th>';
									str+='<th class="font_weight" style="background-color:#E3E4F0 !important;">Completed/ G.O Issued</th>';
								}else if(result[0].levelList[i].locationName =="Mandal/Muncipality/Corporation"){
									str+='<th class="font_weight" style="background-color:#F0E6D7 !important;">Total Posts</th>';
									str+='<th class="font_weight" style="background-color:#F0E6D7 !important;">Open Posts</th>';
									str+='<th class="font_weight" style="background-color:#F0E6D7 !important;">Final Review</th>';
									str+='<th class="font_weight" style="background-color:#F0E6D7 !important;">Completed/ G.O Issued</th>';
								}else if(result[0].levelList[i].locationName =="Village"){
									str+='<th class="font_weight" style="background-color:#ECF7F4 !important;">Total Posts</th>';
									str+='<th class="font_weight" style="background-color:#ECF7F4 !important;">Open Posts</th>';
									str+='<th class="font_weight" style="background-color:#ECF7F4 !important;">Final Review</th>';
									str+='<th class="font_weight" style="background-color:#ECF7F4 !important;">Completed/ G.O Issued</th>';
								}
								
								
							}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							var colorObj={"District":"#EDECE0","Assembly":"#E3E4F0","Mandal/Muncipality/Corporation":"#F0E6D7","Village":"#ECF7F4"};
								str+='<tr>';
									str+='<td>'+result[i].board+'</td>';
									for(var j in result[i].levelList){
										if(result[i].levelList[j].totalPosts !=null && result[i].levelList[j].totalPosts>0){
											str+='<td style="background-color:'+colorObj[result[i].levelList[j].locationName]+' !important;" class="postWiseDetailsCls" attr_name="'+result[i].board+'" attr_status_id="0" attr_locationTypeid="'+locationTypeId+'" attr_boardLevelId='+result[i].levelList[j].levelValue+' attr_level_name="total Posts" attr_levelId ="'+result[i].boardLevelId+'">'+result[i].levelList[j].totalPosts+'</td>';
										}else{
											str+='<td style="background-color:'+colorObj[result[i].levelList[j].locationName]+' !important;"> - </td>';
										}
										
										if(result[i].levelList[j].openCount !=null && result[i].levelList[j].openCount>0){
											str+='<td style="background-color:'+colorObj[result[i].levelList[j].locationName]+' !important;" class="postWiseDetailsCls" attr_name="'+result[i].board+'" attr_status_id="1" attr_locationTypeid="'+locationTypeId+'" attr_boardLevelId='+result[i].levelList[j].levelValue+' attr_level_name="open Posts" attr_levelId ="'+result[i].boardLevelId+'">'+result[i].levelList[j].openCount+'</td>';
										}else{
											str+='<td style="background-color:'+colorObj[result[i].levelList[j].locationName]+' !important;"> - </td>';
										}
										if(result[i].levelList[j].finalizedPost !=null && result[i].levelList[j].finalizedPost>0){
											str+='<td style="background-color:'+colorObj[result[i].levelList[j].locationName]+' !important;" class="postWiseDetailsCls" attr_name="'+result[i].board+'" attr_status_id="2" attr_locationTypeid="'+locationTypeId+'" attr_boardLevelId='+result[i].levelList[j].levelValue+' attr_level_name="open Posts" attr_levelId ="'+result[i].boardLevelId+'">'+result[i].levelList[j].finalizedPost+'</td>';
										}else{
											str+='<td style="background-color:'+colorObj[result[i].levelList[j].locationName]+' !important;"> - </td>';
										}
										if(result[i].levelList[j].goIsuuedCount !=null && result[i].levelList[j].goIsuuedCount>0){
											str+='<td style="background-color:'+colorObj[result[i].levelList[j].locationName]+' !important;" class="postWiseDetailsCls" attr_name="'+result[i].board+'" attr_status_id="4" attr_locationTypeid="'+locationTypeId+'" attr_boardLevelId='+result[i].levelList[j].levelValue+' attr_level_name="Completed/G.O Issued Posts" attr_levelId ="'+result[i].boardLevelId+'">'+result[i].levelList[j].goIsuuedCount+'</td>';
										}else{
											str+='<td style="background-color:'+colorObj[result[i].levelList[j].locationName]+' !important;"> - </td>';
										}
									}
								str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			//oTable.fnSort( [[2,"asc"], [3,"asc"]] );
			$("#nominated"+locationTypeVal+divId).html(str);
			$(".dataTableNomiL"+locationTypeVal+divId).dataTable({
				"iDisplayLength": 10,
				"aaSorting": [[6,'desc']],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				
			});
	
}
$(document).on("click",".postWiseDetailsCls",function(){
	$("#nominatedPostDetailsModalId").modal("show");
	
	var statusId=$(this).attr("attr_status_id");
	var locationTypeId=$(this).attr("attr_locationTypeid");
	var boardLevelId=$(this).attr("attr_boardLevelId");
	var name=$(this).attr("attr_name");
	var levelName=$(this).attr("attr_level_name");
	var levelId =$(this).attr("attr_levelId");
	$("#nominatedPostHeadingId").html(name+levelName);
	getDepartMentAndBoardWisePositinsStatusCount(statusId,locationTypeId,boardLevelId,levelId);
});
 function getDepartMentAndBoardWisePositinsStatusCount(statusId,locationTypeId,boardLevelId,levelId){
	 var locationValuesArr =[];
	 if(levelId != null && levelId>0){
	   locationValuesArr.push(levelId);
	 }
	$("#nominatedPostDetailsDivId").html(spinner);
	var jsObj ={
	             "locationValuesArr" : locationValuesArr,
				 "fromDateStr" : " ",
				 "toDateStr" : " ",
				 "locationTypeId" :locationTypeId,
				 "boardLevelId":boardLevelId,
				 "statusId" :statusId,
				 "activityMemberId" :globalActivityMemberId
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getDepartMentAndBoardWisePositinsStatusCountAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildDepartMentAndBoardWisePositinsStatusCount(result);
		}
	});
}
function buildDepartMentAndBoardWisePositinsStatusCount(result){
	var str='';
	str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table-condensed tableStyleLed table_alignment table-noborder dataTableNomiPopup" style="width:100%">';
					str+='<thead style="background-color:#edeef0">';
						str+='<tr>';
							str+='<th class="font_weight">Department</th>';
							str+='<th class="font_weight" >Total Open Posts</th>';
							str+='<th class="font_weight">Board / Corporation</th>';
							str+='<th class="font_weight">Open Posts</th>';
							str+='<th class="font_weight">Positions/ Posts</th>';
						str+='</tr>';
						
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							
								for(var j in result[i].list){
									str+='<tr>';
									str+='<td>'+result[i].name+'</td>';
									str+='<td>'+result[i].count+'</td>';
									str+='<td>'+result[i].list[j].board+'</td>';
									str+='<td>'+result[i].list[j].boardCunt+'</td>';
									str+='<td>';
									str+='<ul class="list-inline">';
									for(var k in result[i].list[j].subList){
										str+='<li style="margin-left:10px;"><h5 style="padding:10px;background-color:#f0f0f0;border:1px solid #ddd;border-radius:5px;">'+result[i].list[j].subList[k].position+' - '+result[i].list[j].subList[k].positionCount+'</h5></li>';
									}
									str+='</ul>';
									str+='</td>';
									str+='</tr>';
								}
								
								
							
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			
			
	$("#nominatedPostDetailsDivId").html(str);
	$(".dataTableNomiPopup").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		
	});
}
$(document).on("click",".nominatedRefresh",function(){
	onloadNominatedCalls();
	getUserTypeWiseNominatedPostDetailsCnt();
});
/*getDepartMentWiseBoards();
function getDepartMentWiseBoards()
{ 

	var jsObj ={
	             "locationValuesArr" : [17],
				 "fromDateStr" : " ",
				 "toDateStr" : " ",
				 "locationTypeId" : 3,
				 "boardLevelId": 3,
				 "statusId" : 1,
				 "departmentId" : 6
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getDepartMentWiseBoardsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		
	});
}
	
//getBoardWisePositions();
function getBoardWisePositions()
{ 

	var jsObj ={
	             "locationValuesArr" : [17],
				 "fromDateStr" : " ",
				 "toDateStr" : " ",
				 "locationTypeId" : 3,
				 "boardLevelId": 3,
				 "statusId" : 1,
				 "boardId" : 1753
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getBoardWisePositionsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		
	});
}*/
 $(document).on("click",".statusClickCls",function(){
	
	var deptId =  $(this).attr("attr_department_id");
		var boardLevelId =  $(this).attr("attr_boardLevelId");
		var type =  $(this).attr("attr_type");
		var departmentName =  $(this).attr("attr_department_name");
		var statusIds =  $(this).attr("attr_board_statusIds");
		if(type == "open"){
			$("#departmentPostModal").modal("show");
			$("#departmentPostModal .modal-dialog").css("width","95%");
			$("#TitleId").html(departmentName+  "  Open Posts Details");
			$("#openPostDetailsModalDivId").html(spinner)
			$(".paginationId").html("");
			getDepartmentWiseOpenPostAndApplicationDetails(deptId,boardLevelId,type);
		}else if(type == "goIssued"){
			$("#departmentPostModal").modal("show");
			$("#departmentPostModal .modal-dialog").css("width","95%");
			$("#TitleId").html(departmentName + "  G.O Issued Positions");
			$(".paginationId").html("");
			getLevelWiseGoIssuedPostions(boardLevelId,statusIds,0,10);
		}else if(type == "finalIssued"){
			$("#departmentPostModal").modal("show");
			$("#departmentPostModal .modal-dialog").css("width","95%");
			$("#TitleId").html(departmentName + "  Final Review Positions");
			$(".paginationId").html("");
			getLevelWiseGoIssuedPostions(boardLevelId,statusIds,0,10);
		}else if(type == "department"){
			$("#boardWiseModal").modal("show");
			$("#boardWiseModal .modal-dialog").css("width","95%");
			$("#boardTitleId").html(departmentName+" Details");
			$(".paginationCls").html("");
			$("#departmentDetailsModalDivId").html(spinner)
			$(".paginationId").html("");
			getDepartmentWiseOpenPostAndApplicationDetails(deptId,0,type);
		}
	
}); 
  function getDepartmentWiseOpenPostAndApplicationDetails(deptId,boardLevelId,type){
	var jsObj={
	  "fromDateStr" 		:"",
      "toDateStr"			:"",
      "locationValuesArr"	:[1],
      "locationTypeId"		:2,
      "year"				:"",
      "boardLevelId"		:boardLevelId,
	  "deptId"				:deptId,
	  "activityMemberId" :globalActivityMemberId
	  }
    $.ajax({   
      type:'GET',
      url:'getDepartmentWisePostAndApplicationDetailsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			 buildDepartmentWiseOpenPostAndApplicationDetails(result,type);
		}else{
			if(type == "open"){
			$("#openPostDetailsModalDivId").html("No Data Available");
			}else{
				$("#departmentDetailsModalDivId").html("No Data Available");
				
			}
		}
    }); 
  }
	 function buildDepartmentWiseOpenPostAndApplicationDetails(result,type){
		var str='';
		
		str+='<div class="table-responsive">';
			if(type == "open"){
				str+='<table class="table table-condensed tableOpenPostCss" id="dataTableOpenPostId">';
			}else{
				str+='<table class="table table-condensed tableOpenPostCss" id="dataTabledeptPostId">';
			}
			
				str+='<thead>';
					str+='<tr>';
						if(type == "open"){
							str+='<th rowspan="2" class="openPostDeptColor" style="vertical-align:middle;">Department</th>';
						}else{
							str+='<th rowspan="2" class="openPostDeptColor" style="vertical-align:middle;">Board/ Corporation</th>';
						}
						
						str+='<th colspan="3" class="openPostColor text-center">Posts</th>';
						str+='<th colspan="3" class="openPostAppColor text-center">Applications</th>';
					str+='</tr>';
					str+='<tr>';
						str+='<th class="openPostColor text-center">Total</th>';
						str+='<th class="openPostColor text-center">Finalized/ G.O Issueed</th>';
						str+='<th class="openPostColor text-center">Open</th>';
						
						str+='<th class="openPostAppColor text-center">Recieved</th>';
						str+='<th class="openPostAppColor text-center">Shortlisted</th>';
						str+='<th class="openPostAppColor text-center">Ready for Final Review</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							if(type =="open"){
								str+='<td attr_department_name="'+result[i].name+'" attr_department_id="'+result[i].id+'"  class="statusClickCls"  attr_type="department" style="color: #337ab7;font-weight:normaltext-decoration:none;cursor:pointer;">'+result[i].name+'</td>';
							}else{
								str+='<td>'+result[i].name+'</td>';
							}
							
							
							if(result[i].totalCount !=null && result[i].totalCount>0){
								str+='<td class="openPostColor text-center">'+result[i].totalCount+'</td>';
							}else{
								str+='<td class="openPostColor text-center"> - </td>';
							}
							if(result[i].finalOrGOCnt !=null && result[i].finalOrGOCnt>0){
								str+='<td class="openPostColor text-center">'+result[i].finalOrGOCnt+'</td>';
							}else{
								str+='<td class="openPostColor text-center"> - </td>';
							}
							
							if(result[i].openCnt !=null && result[i].openCnt>0){
								str+='<td class="openPostColor text-center">'+result[i].openCnt+'</td>';
							}else{
								str+='<td class="openPostColor text-center"> - </td>';
							}
							
							if(result[i].receivedCnt !=null && result[i].receivedCnt>0){
								str+='<td class="openPostAppColor text-center">'+result[i].receivedCnt+'</td>';
							}else{
								str+='<td class="openPostAppColor text-center"> - </td>';
							}
							if(result[i].shorlistedCnt !=null && result[i].shorlistedCnt>0){
								str+='<td class="openPostAppColor text-center">'+result[i].shorlistedCnt+'</td>';
							}else{
								str+='<td class="openPostAppColor text-center"> - </td>';
							}
							
							if(result[i].readyToFinalCnt !=null && result[i].readyToFinalCnt>0){
								str+='<td class="openPostAppColor text-center">'+result[i].readyToFinalCnt+'</td>';
							}else{
								str+='<td class="openPostAppColor text-center"> - </td>';
							}
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		if(type == "open"){
			$("#openPostDetailsModalDivId").html(str);
				$("#dataTableOpenPostId").dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}else{
			$("#departmentDetailsModalDivId").html(str);
			$("#dataTabledeptPostId").dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}
		
	} 
  
function getLevelWiseGoIssuedPostions(boardLevelId,statusId,startIndex,endIndex){
	  $("#openPostDetailsModalDivId").html(spinner)
	  var statusIds=[];
	  if(statusId == 0 || statusId == null){
		  statusIds.push(3,4)
	  }else{
		 statusIds.push(statusId); 
	  }
	 var totalPosCount = 0; 
	var jsObj={
      fromDateStr 		:"",
      toDateStr			:"",
      locationValuesArr	:[1],
      locationTypeId	:2,
      year				:"",
      boardLevelId		:boardLevelId, 
	  statusIds			:statusIds, // 3-complered 4 goIsuued
	  startIndex:startIndex,
	  endIndex:endIndex
     
    }
    $.ajax({   
      type:'GET',
      url:'getLevelWiseGoIssuedPostionsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return LevelWiseGoIssuedPostions(result,totalPosCount,startIndex,boardLevelId,statusId);
		}else{
			$(".paginationId").html("");
			$("#openPostDetailsModalDivId").html("No Data Available");
		}
		
    });
}
function LevelWiseGoIssuedPostions(result,totalPosCount,startIndex,boardLevelId,statusId){
		if(startIndex == 0){
			totalPosCount=result[0].postCount;
		}
		var str='';
		str+='<div class="table-responsive">';
			str+='<table class="table table-condensed tableStyledGoIssued" id="dataTablegoIssuedPostId">';
				str+='<thead class="bg-E9">';
					str+='<tr>';
						str+='<th class="text-center">Department</th>';
						str+='<th class="text-center">Board/ Corporation</th>';
						str+='<th class="text-center">Position Level</th>';
						str+='<th class="text-center">Image</th>';
						str+='<th class="text-center">Name</th>';
						str+='<th class="text-center">Gender</th>';
						str+='<th class="text-center">Caste Category</th>';
						str+='<th class="text-center">G.O Validity</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							str+='<td>'+result[i].department+'</td>';
							str+='<td>'+result[i].board+'</td>';
							str+='<td>'+result[i].position+'</td>';
							str+='<td><img src="https://mytdp.com/images/cadre_images/'+result[i].image+'" class="img-border" alt="profile" onerror="setDefaultImage(this);" style="width:50px;height:50px;"/></td>';
							str+='<td>'+result[i].candidateName+'</td>';
							str+='<td>'+result[i].gender+'</td>';
							str+='<td>'+result[i].casteCategory+'</td>';
							if(result[i].date !=null && result[i].date.length>0){
								str+='<td>'+result[i].date+'</td>';
							}else{
								str+='<td> - </td>';
							}
							
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		if(startIndex == 0 && totalPosCount > 10){
			$(".paginationId").pagination({
				items: totalPosCount,
				itemsOnPage: 10,
				cssStyle: 'light-theme',
				hrefTextPrefix: '#pages-',
				onPageClick: function(pageNumber) { 
					var num=(pageNumber-1)*10;
					getLevelWiseGoIssuedPostions(boardLevelId,statusId,num,10)
				}
				
			});
		}
		$("#openPostDetailsModalDivId").html(str);		
	}
