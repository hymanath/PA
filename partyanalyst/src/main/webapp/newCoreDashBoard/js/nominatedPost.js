var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
levelWiseNominatedArr=[{name:'state',id:'2'},{name:'district',id:'3'},{name:'constituency',id:'4'}];
function onloadNominatedCalls(){
	getLevelWisePostsOverView();
	if($(".moreAttNominatedBlocksIcon").hasClass("expandBlockNominated")){
		$("[role='tabCummulativeNomi1'] li").removeClass("active");
		$("[role='tabCummulativeNomi1'] li:nth-child(1)").addClass("active");
		 locationNominatedWiseDate('department');
		
	 }
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
		locationNominatedWiseDate('department');
		
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
		 "boardLevelId": 2
		 
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
								str+='<h5>Received <br>Applications</h5>';
								if(result[i].recivedCount !=null && result[i].recivedCount>0){
									str+='<h5 class="m_top10 font_weight">'+result[i].recivedCount+'</h5>';
								}else{
									str+='<h5 class="m_top10 font_weight"> - </h5>';
								}
								
							str+='</div>';
					str+='</td>';
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
							str+='<h5>Open&nbsp;Posts</h5>';
							if(result[i].openPostPer !=null && result[i].openPostPer>0){
								str+='<h5 class="m_top25 font_weight">'+result[i].openCount+' <small style="color:green">'+result[i].openPostPer.toFixed(1)+'%</small></h5>';
							}else{
								str+='<h5 class="m_top25 font_weight"> - </h5>';
							}
							
						str+='</div>';
					str+='</td>';
					str+='<td class="border-top0">';
						str+='<div class="pad_yash_8">';
							str+='<h5>Finalized <br>Posts</h5>';
							if(result[i].finalizedPer !=null && result[i].finalizedPer>0){
								str+='<h5 class="m_top10 font_weight">'+result[i].finalizedPost+' <small style="color:green">'+result[i].finalizedPer.toFixed(1)+'%</small></h5>';
							}else{
								str+='<h5 class="m_top10 font_weight"> - </h5>';
							}
							
						str+='</div>';
					str+='</td>';
					str+='<td class="border-top0">';
						str+='<div class="pad_yash_8">';
							str+='<h5>Completed/ <br>G.O Issued</h5>';
							if(result[i].goIssuedPer !=null && result[i].goIssuedPer>0){
								str+='<h5 class="m_top10 font_weight">'+result[i].goIsuuedCount+' <small style="color:green">'+result[i].goIssuedPer.toFixed(1)+'%</small></h5>';
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
		 "activityMemberId" :44,
		 "stateId" : 1,
		 "userTypeId" :2 
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
									name: 'Constotuency Level Posts',
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
									name: 'Constotuency Level Posts',
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
					collapse+='<h4 class="panel-title text-capital" style="color:#fff;">'+levelWiseNominatedArr[i].name+' Wise Overview</h4>';
						
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
			
		}
		
	}
}
 function getDepartmentWisePostAndApplicationDetails(locationTypeId,divId,locationTypeVal){   
		var jsObj ={
			 "locationValuesArr" : [],
			 "fromDateStr" : " ",
			 "toDateStr" : " ",
			 "locationTypeId" :locationTypeId,
			 "year" : " ",
			 "boardLevelId":2,
			 "deptId": 0
		  }
		$.ajax({
			type : 'POST',
			url : 'getDepartmentWisePostAndApplicationDetailsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildDepartmentWisePostAndApplicationDetails(result,divId,locationTypeVal);
			}
		});
	}
function buildDepartmentWisePostAndApplicationDetails(result,divId,locationTypeVal){
	var str='';
	str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table-condensed tableStyleLed table_alignment table-noborder dataTableNomi'+locationTypeVal+divId+'" style="width:100%">';
					str+='<thead style="background-color:#edeef0">';
						str+='<tr>';
							str+='<th class="font_weight">Department</th>';
							str+='<th class="font_weight">Total Posts</th>';
							str+='<th class="font_weight">G.O Issued</th>';
							str+='<th class="font_weight">Open Posts</th>';
							str+='<th class="font_weight">Expair in 1Month</th>';
							str+='<th class="font_weight">Expair in 2Month</th>';
							str+='<th class="font_weight">Expair in 3Month</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
								str+='<tr>';
									str+='<td>'+result[i].name+'</td>';
									str+='<td>'+result[i].totalCount+'</td>';
									str+='<td>'+result[i].goIsuuedCnt+'</td>';
									str+='<td>'+result[i].openCnt+'</td>';
									str+='<td>'+result[i].expireOneMnth	+'</td>';
									str+='<td>'+result[i].expireTwoMnth+'</td>';
									str+='<td>'+result[i].expireThreMnth+'</td>';
								str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			$("#nominated"+locationTypeVal+divId).html(str);
			$(".dataTableNomi"+locationTypeVal+divId).dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				
			});
			 
}	
$(document).on("click",".KaizalaRefresh",function(){
	onloadNominatedCalls();
});

//Calls
function getNominatedPostStateWiseCount()
{ 


	var jsObj ={
				 "fromDateStr" : " ",
				 "toDateStr" : " "
			  }
	$.ajax({
		type : 'POST',
		url : 'getNominatedPostStateWiseCountAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		//alert(78);
		
	});
}
function getNominatedPostLocationWiseBoardLevelCount()
{ 


	var jsObj ={
	             "locationValuesArr" : [],
				 "fromDateStr" : " ",
				 "toDateStr" : " ",
				 "locationTypeId" : locationTypeId,
				 "boardLevelId":boardLevelId
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getNominatedPostLocationWiseBoardLevelCountAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		
		
	});
}

/* function getDepartMentAndBoardWisePositinsStatusCount()
{ 

	var jsObj ={
	             "locationValuesArr" : [17],
				 "fromDateStr" : " ",
				 "toDateStr" : " ",
				 "locationTypeId" : 3,
				 "boardLevelId":3,
				 "statusId" :1
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getDepartMentAndBoardWisePositinsStatusCountAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		
	});
}	
getDepartMentWiseBoards();
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
*/	
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
}