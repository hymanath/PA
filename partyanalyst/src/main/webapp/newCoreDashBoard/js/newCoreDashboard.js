
	function getUserBasicDetails(){
		
		var jsObj ={userId:globalUserId}
		$.ajax({
			type : 'POST',
			url : 'getUserBasicDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				globalUserTypeId = result.userTypeId;
				globalUserAccessLevelId = result.userAccessLevelId;
				globalUserAccessLevelValues = result.userAccessLevelValuesList;
				
				onLoadCalls();
			}
		});
	}

function getDistrictWiseCommitteesCountReport(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={state:state,
					basicCommitteeIdsArray : basicCommitteeIdsArray,
					startDateString : startDateString,
 			        endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getDistrictWiseCommitteesCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	
	function getCommitteesBasicCountReport(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   basicCommitteeIdsArray.push(4);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={userAccessLevelId:globalUserAccessLevelId,
					userAccessLevelValuesArray:globalUserAccessLevelValues,
					state:state,
					basicCommitteeIdsArray : basicCommitteeIdsArray,
					startDateString : startDateString,
 			        endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getCommitteesBasicCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildgetCommitteesBasicCountReport(result);
			
		});
	}
	
	function buildgetCommitteesBasicCountReport(result){
		var str='';
		
		str+='<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 committeesBlock">';
				str+='<ul class="committesBlockUl">';
					str+='<li>';
						str+='<h4 class="text-capital bg_49 pad_custom">main committees</h4>';
						
						str+='<table class="table table-condensed">';
						   str+='<tr>';
								str+='<td>';
								if(result.mainVO.totalCount == null || result.mainVO.totalCount == 0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.mainVO.totalCount+'</h3>';
								}
									str+='<h5 class="text-muted text-capitalize">total</h5>';
								str+='</td>';
								str+='<td>';
								if(result.mainVO.startedCount == null || result.mainVO.startedCount == 0){
									str+='<h3> - </h3>';
									str+='<h5 class="text-muted text-capitalize">started</h5>';
								}else{
									str+='<h3>'+result.mainVO.startedCount+'</h3>';
									str+='<h5 class="text-muted text-capitalize">started</h5>';
									str+='<small class="text-success">'+result.mainVO.startedPerc+'%</small>';
								}
								str+='</td>';
								str+='<td>';
								if(result.mainVO.completedCount == null || result.mainVO.completedCount == 0){
									str+='<h3> - </h3>';
									str+='<h5 class="text-muted">Completed</h5>';
								}else{
									str+='<h3>'+result.mainVO.completedCount+'</h3>';
									str+='<h5 class="text-muted">Completed</h5>';
									str+='<small class="text-success">'+result.mainVO.completedPerc+'%</small>';
								}
								str+='</td>';
							str+='</tr>';
						str+='</table>';
					str+='</li>';
					str+='<li>';
						str+='<h4 class="text-capital bg_49 pad_custom">affliated committees</h4>';
						str+='<table class="table table-condensed">';
							str+='<tr>';
								str+='<td>';
								if(result.affliatedVO.startedCount == null || result.affliatedVO.startedCount == 0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.affliatedVO.startedCount+'</h3>';
								}
									str+='<h5 class="text-muted text-capitalize">Started</h5>';
								str+='</td>';
								str+='<td>';
								if(result.affliatedVO.completedCount == null || result.affliatedVO.completedCount ==0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.affliatedVO.completedCount+'</h3>';
								}
								str+='<h5 class="text-muted text-capitalize">Completed</h5>';
								str+='</td>';
						   str+='</tr>';
						str+='</table>';
					str+='</li>';
					str+='<hr style="margin:0px;">';
					if(result.subList != null && result.subList.length >0){
						var length = result.subList.length - 1;
							for(var i = length; i >= 0; i--){
								str+='<li>';
										str+='<h4 class="text-capitalize m_top10" style="color:#c9c0cc">'+result.subList[i].name+'/Ward</h4>';
										str+='<table class="table table-condensed bg_ED">';
											str+='<tr>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Total</h5>';
													if(result.subList[i].mainVO.totalCount == null || result.subList[i].mainVO.totalCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].mainVO.totalCount+'</p>';
													}
													
											   str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Started</h5>';
													if(result.subList[i].mainVO.startedCount == null || result.subList[i].mainVO.startedCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].mainVO.startedCount+' <small class="text-success"> '+result.subList[i].mainVO.startedPerc+'%</small></p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Completed</h5>';
													if(result.subList[i].mainVO.completedCount == null || result.subList[i].mainVO.completedCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].mainVO.completedCount+'<small class="text-success"> '+result.subList[i].mainVO.completedPerc+'%</small></p>';
													}
													
												str+='</td>';
											str+='</tr>';
										str+='</table>';
									str+='</li>';
									str+='<li>';
										str+='<table class="table table-condensed bg_ED" style="margin-top:30px !important;">';
											str+='<tr>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Started</h5>';
													if(result.subList[i].affliatedVO.startedCount == null || result.subList[i].affliatedVO.startedCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].affliatedVO.startedCount+'</p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Completed</h5>';
													if(result.subList[i].affliatedVO.completedCount == null || result.subList[i].affliatedVO.completedCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].affliatedVO.completedCount+'</p>';
													}
													
												str+='</td>';
											str+='</tr>';
										str+='</table>';
									str+='</li>';
							}
							
					}
					
				str+='</ul>';
        str+='</div>';
		
		$("#basicCommitteeCountsDiv").html(str)
	}
	
	function getLevelWiseBasicCommitteesCountReport(){
		
		$("#levelWiseBasicCommittees").html('<div ><center ><img  src="images/icons/loading.gif" id="commulativeEnlargeLoadingId"></center></div>');
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   basicCommitteeIdsArray.push(4);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={userAccessLevelId:globalUserAccessLevelId,
					userAccessLevelValuesArray:globalUserAccessLevelValues,
					state:state,
					basicCommitteeIdsArray : basicCommitteeIdsArray,
					startDateString : startDateString,
 			        endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLevelWiseBasicCommitteesCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#levelWiseBasicCommittees").html('');
			buildgetLevelWiseBasicCommitteesCountReport(result);
			
		});
	}
	
	function getProperLocationLevelName(levelName){
		var properName = "";
		if(levelName == "Village"){
			properName = "Village / Ward ";
		}else if(levelName == "Mandal"){
			properName = "Mandal / Town / Division ";
		}else{
			properName = levelName;
		}
		return properName;
	}
	
	function buildgetLevelWiseBasicCommitteesCountReport(result)
	{
		$("#levelWiseBasicCommittees").html('');
		
		var locationLevelNameArray =[];
		//var categories = [ "specialword", "word1", "word2" ],
        //found = $.inArray('specialword1', categories);
		
		if(result != null && result.length > 0){
			var str='';
			str+='<ul class="villageWardUl">';
			var length = result.length - 1;
			for(var i = length; i >= 0; i--){
				
				if(result[i].subList !=null && result[i].subList.length > 0){
					for(var j in result[i].subList){
						
						str+='<li>';
						var properName = getProperLocationLevelName(result[i].name);
						if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
							locationLevelNameArray.push(properName);
							str+='<h4>'+properName+' Level</h4>';
						}
						str+='<div id="mainCommittees'+i+''+j+'" class="chartLi" ></div>';
						str+='</li>';
					}
				}
										
            }
			str+='<ul>';
			
		}
	$("#levelWiseBasicCommittees").html(str);
		if(result != null && result.length > 0){
			var length = result.length - 1;
			for(var i = length; i >= 0; i--){
				
				if(result[i].subList !=null && result[i].subList.length > 0){
					for(var j in result[i].subList){
						var committeeName = result[i].subList[j].name;
						var completedPerc = [];
						var startedPerc = [];
						var notStartedPerc = [];
						if(result[i].subList[j].completedPerc == null || result[i].subList[j].completedPerc == 0){
							completedPerc.push(" - ")
						}else{
							completedPerc.push(result[i].subList[j].completedPerc)
						}
						if(result[i].subList[j].startedPerc == null || result[i].subList[j].startedPerc == 0){
							startedPerc.push(" - ")
						}else{
							startedPerc.push(result[i].subList[j].startedPerc)
						}
						if(result[i].subList[j].notStartedPerc == null || result[i].subList[j].notStartedPerc == 0){
							notStartedPerc.push(" - ")
						}else{
							notStartedPerc.push(result[i].subList[j].notStartedPerc) 
						}
						
						//if(committeeName == "Main")
						
						
						$(function () {
							$('#mainCommittees'+i+''+j+'').highcharts({
								colors: ['#F56800','#53BF8B','#A1B1BE'],
								chart: {
									type: 'column',
									
								},
								title: {
									text: committeeName,
									style: {
											fontSize: '16px',
											fontFamily: '"Helvetica Neue",Helvetica,Arial,sans-serif',
											textTransform: "uppercase"
											
									}
								},
								subtitle: {
									text: null
								},
								 xAxis: {
									 min: 0,
									gridLineWidth: 0,
									minorGridLineWidth: 0,
									labels: {
										enabled: false,
									}
								},
								yAxis: {
									min: 0,
									gridLineWidth: 0,
									minorGridLineWidth: 0,
									title: {
										text: ''
									},
									stackLabels: {
										enabled: true,
										style: {
											fontWeight: 'bold',
											color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
										}
									}
								},
								tooltip: {
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}%</b><br/>',
									shared: true
								},
								legend: {
									enabled: true,
									align: 'left'
							
								},
								plotOptions: {
									column: {
										  stacking: 'percent',
										dataLabels:{
											enabled: true,
											formatter: function () {
												if (this.y > 0) return this.y + '%';
												else return '';
											}
										},
										
									},
								},
								 series: [{
									name: 'Started',
									data: startedPerc 
								}, {
									name: 'Completed',
									data: completedPerc
								}, {
									name: 'Yet To Start',
									data: notStartedPerc
								}]
							});
						});	
						
					}
				}
				
			}
		}
		
		
		$(".villageWardUl").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 4,
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 4,
					slidesToScroll: 4,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		});
	}
	
	function getUserTypeWiseCommitteesCompletedCounts(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  state:state,
					  basicCommitteeIdsArray : basicCommitteeIdsArray,
					  startDateString : startDateString,
 			          endDateString :   endDateString,
					  userId :1,
					  activityMemberId : 0,
					  userTypeId : globalUserTypeId
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	
	function getUserTypeWiseCommitteesCompletedCounts1(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  state:state,
					  basicCommitteeIdsArray : basicCommitteeIdsArray,
					  startDateString : startDateString,
 			          endDateString :   endDateString,
					  userId :1,
					  activityMemberId : 0,
					  userTypeId : globalUserTypeId
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	
	function initialiseGraph()
	{
		var getWidth = $("#genSec").parent().width()+'px';
		$("#genSec").width(getWidth);
		$(function () {
			$('#genSec').highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
					title: {
						text: null
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					}
				},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				series: [{
					name: 'Year 1800',
					data: [107, 31, 635, 203, 2]
				}]
			});
		});
		$(function () {
			$('#Secretary').highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
					title: {
						text: null
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					}
				},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				series: [{
					name: 'Year 1800',
					data: [107, 31, 635, 203, 2]
				}]
			});
		});
		$(function () {
			$('#memOfParliament').highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
					title: {
						text: null
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					}
				},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				series: [{
					name: 'Year 1800',
					data: [107, 31, 635, 203, 2]
				}]
			});
		});
		$(function () {
			$('#disIncharges').highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					categories: ['Parthasarathi', 'Satyanarayana Murthy', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
					title: {
						text: null
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					}
				},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				series: [{
					name: 'Year 1800',
					data: [107, 31, 635, 203, 2]
				}]
			});
		});
	}
	
	
	
	$(document).on("click",".iconExpand",function(){
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".committeesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		setTimeout(function(){
			$(".committeesHiddenBlock,.moreBlocksIcon").toggle();
			initialiseGraph();
		},800);
	});
	/* Notes Functionality Complete*/
	$(document).on("click",".notesIcon",function(){
		$(this).closest(".panel-heading").find(".notesDropDown").toggle();
	});
	$(document).on("click",".btnCustomCreate",function(){
		var getNewNotes = $(".notesArea").val();
		var todayDate = moment().format("DD MMMM YYYY");
		var commentText = '<span class="notesText">'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>'; 
		$(".notesUl").append("<li>"+commentText+"</li>");
		$(".notesArea").val('');	
	});
	$(document).on("click",".editNotes",function(){
		var notesHtml = $(this).closest("li").find(".notesText").html();
		$(".notesArea").val(notesHtml);
	});
	$(document).on("click",".deleteNotes",function(){
		$(this).closest("li").remove();
	});
	$(document).on("click",".moreBlocksIcon",function(){
		$(".moreBlocks").toggle();
		getLevelWiseBasicCommitteesCountReport();
		customBuildGraph();
	});
	$(document).on("click",".activeUlCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
	});
	
	function customBuildGraph()
	{
		var j = 0;
		$(".graphStructure").each(function(){
			j = j + 1;
			$(this).attr("id","graphStructure_"+j+"");
			var getWidth = $("#graphStructure_"+j+"").width();
			
			var getNoOfLis = getWidth / $("#graphStructure_"+j+" li").length - 4;
			var kk = getNoOfLis.toFixed() + 'px';
			$("#graphStructure_"+j+" li").width(kk);
			
			var myColors = ['#66728C','#F56800','#31AA74'];
			var i = 0;
			$("#graphStructure_"+j+" li").each(function(){
				var l = $(this).find("span").length;
				var getNoOfSpans = $(this).find("span").length;
				$(this).find("span").each(function(){
					$(this).css('background-color', myColors[i]);
					i = (i + 1) % getNoOfSpans;
				});
				$(this).find("span").each(function(){
					var getPercentage = $(this).attr("attr_percent");
					if(l > 1)
					{
						$(this).css("height",getPercentage+'%');
					}else{
						$(this).css("height","100%");
					}
				});
			});
			
		});
	}
	
	$(document).on("click",".comparisonSelect li",function(){
		if($(this).hasClass("active") == true)
		{
			$(this).removeClass("active");
		}else{
			$(".comparisonSelect li").removeClass("active");
			$(this).addClass("active");
		}
	});
	
	
	