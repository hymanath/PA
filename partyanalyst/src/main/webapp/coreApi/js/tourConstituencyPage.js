function getTourLeaderDtlsBasedOnSelectionType(designationId,filterType){    
		  $("#tourLeadrDtlsHeadingId").html('Leaders Detailed Report');
		  $("#tourDetailsDivId").html(spinner);
		   
		var designationIds=[];
		designationIds.push(designationId);
		
	 	var jsObj ={ 
					 activityMemberId 	: 0,
					 stateId 			: 1,
					 fromDate 			:customStartATMDate,
					 toDate 			: customEndATMDate,
					 userTypeId			:0,
					 designationIds 	:designationIds,
					 filterType 		:filterType,
					 locationTypeId		:locationLevelId,
		             locationValuesArr	:userAccessLevelValuesArray,
					 type				:"constituencyPage"
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getTourLeaderDtlsBasedOnSelectionTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildTourMemberDetails(result);
			}else{
			 $("#tourDetailsDivId").html("NO DATA AVAILABLE.");	
			}
		});
	}
	
	function buildTourMemberDetails(result){
    	if(result != null && result.length > 0){
		  var str1='';
			  
		  for(var i in result){
		  str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str1+='<div class="panel panel-default panelNew">';
			  str1+='<div class="panel-heading">';
				str1+='<div class="row">';
				  str1+='<div class="col-md-8 col-xs-12 col-sm-6">';
					str1+='<h4 class="panel-title"><span class="headingColor text-capital">'+result[i].name+'&nbsp&nbspTours Submitted Report</span></h4>';
				  str1+='</div>';
				 
			  str1+='</div>';
			  str1+='<div class="panel-body">';
				str1+='<div class="row">';
				  str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
				  str1+='<div id="tourMemberDtls'+i+'"></div>';
				   str1+='</div>';
				str1+='</div>';
			  str1+='</div>';
			str1+='</div>';
		  str1+='</div>';
		  str1+='</div>';
		  }
		  $("#tourDetailsDivId").html(str1);
		}
		if(result != null && result.length > 0){
		  for(var i in result){
		  if(result[i].subList3 == null || result[i].subList3.length == 0){
			$("#tourDetailsDivId").html('NO DATA AVAILABLE');
			 return;			
		  }
			var str='';
			var length = result
			if($(window).width() < 800)
			{
				str+='<div class="table-responsive">';
			}
			
			  str+='<table class="table table-bordered borderedWeight" id="tourDetailsDataTabelId'+i+'">';
			  str+='<thead class="bg_D8">';
				str+='<tr>';
					var maxLengthForSpan = 0;
					if(result[i].subList3 != null && result[i].subList3.length > 0){
					  for(var j in result[i].subList3){
						if(result[i].subList3[j].subList3 != null && result[i].subList3[j].subList3.length > 0)
						  maxLengthForSpan = result[i].subList3[j].subList3.length;
					  }
					}
					str+='<th rowspan="2" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
					/* if(maxLengthForSpan >=3){
					  str+='<th rowspan="'+(maxLengthForSpan)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
					}else{
					  str+='<th rowspan="'+(maxLengthForSpan+1)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
					} */
					
					str+='<th colspan="'+(maxLengthForSpan+1)+'" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">Complaince RATIO</th>';
					
					for(var k in result[i].subList3[0].subList3){
					  str+='<th colspan="2" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
					}
				  
				  str+='</tr>';
				  str+='<tr>';
					
					str+='<th class="text-capital text-center" style="vertical-align: middle;">over all</th>';
						for(var k in result[i].subList3[0].subList3){
						  str+='<th class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
						  
						}
					for(var k in result[i].subList3[0].subList3){
					str+='<th class="text-capital text-center" style="vertical-align: middle;">Target</th>';
					str+='<th class="text-capital text-center" style="vertical-align: middle;">Toured</th>';
					}
				  str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					//str+='<td></td>';
						for(var j in result[i].subList3){
						  str+='<tr>';
							str+='<td style="cursor:pointer;color:rgb(51, 122, 183)" attr_type="subLevel" class="candiateCls text-capital" attr_candiate_id="'+result[i].subList3[j].id+'" attr_candiate_name="'+result[i].subList3[j].name+'" attr_designation_name="'+result[i].subList3[j].designation+'"><a>'+result[i].subList3[j].name+'</a></td>';//santosh
							str+='<td class="text-center">'+result[i].subList3[j].complaincePer+' %</td>';

								for(var k in result[i].subList3[j].subList3){
								 
								str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complaincePer+'%</td>';
							  }                        
						  for(var k in result[i].subList3[j].subList3){
							var monthList = result[i].subList3[j].subList3[k].monthList;
							str+='<td class="text-center">'+result[i].subList3[j].subList3[k].targetDays+'</td>';
							str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complainceDays+'';
									str+='<div class="dropup">';
									str+='<span class="pull-right dropdown-toggle" style="font-weight: 600; cursor: pointer;font-size: 16px; margin-top: -21px;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
									str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar2" aria-labelledby="dropdownMenu2" style="padding:10px;">';
										str+='<table class="table table-bordered scrollApplyDiv2">';
										str+='<thead>';
										str+='<th>Month</th>';
										str+='<th>Target</th>';
										str+='<th>Tour</th>';
										str+='<th>Complaince</th>';
										str+='</thead>';
										str+='<tbody>';
												 if(monthList != null && monthList.length > 0){
													 for(var l in monthList){
														str+='<tr>';
														if(monthList[l].name != null && monthList[l].name.length > 0){
														str+='<td style="border-top:none !important">'+monthList[l].name+'</td>';	
														}else{
														str+='<td style="border-top:none !important"> - </td>';	
														}
														str+='<td class="text-center;">'+monthList[l].targetDays+'</td>';
														if(monthList[l].complainceDays > 0){
														str+='<td class="text-center;">'+monthList[l].complainceDays+'</td>';	
														}else{
														str+='<td> - </td>';	
														}
														if(monthList[l].complainceDays == 0){
														str+='<td>-</td>';	
														}else if(monthList[l].complainceDays>=monthList[l].targetDays){
														str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#3DBC93;color:#fff;margin-left:7px;" class="glyphicon glyphicon-ok"></i></small>&nbsp&nbspYES<small></td>';		
														}else if(monthList[l].complainceDays<monthList[l].targetDays){
														 str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#E35B69;color:#fff;margin-left:7px;" class="glyphicon glyphicon-remove"></i></small>&nbsp&nbspNO<small></td>';			
														}
													   str+='</tr>'; 
													 }
												 }
										str+='</tbody>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							
							str+='</td>';
						  }
						  str+='</tr>';
						}
					  
					str+='</tbody>';
					
			  str+='</table>';
				  if($(window).width() < 800)
				{
					str+='</div>';
				 }
			$("#tourMemberDtls"+i).html(str);
			$("#tourDetailsDataTabelId"+i+"").dataTable({
				"aaSorting": [],
				"iDisplayLength" : 10	
			 });
			$("#tourDetailsDataTabelId"+i+"").removeClass("dataTable");
			
			
			if($(window).width() < 800)
				{
					$(".scrollApplyDiv2").each(function(){
						var scrollengthDiv = $(this).find("tr").length;
						if(scrollengthDiv >= 3){
							$(".verticalScrollBar2").mCustomScrollbar({setHeight:'150px'})
							
						}else{
							$(".verticalScrollBar2").css("height","auto");
						
						}
					}); 
				}else{
					$(".scrollApplyDiv2").each(function(){
						var scrollengthDiv = $(this).find("tr").length;
						if(scrollengthDiv >= 5){
							$(".verticalScrollBar2").mCustomScrollbar({setHeight:'240px'})
							
						}else{
							$(".verticalScrollBar2").css("height","auto");
						
						}
					}); 
				}
			  
			}
		}
	}
   
	
	function getCandiateWiseTourDetails(candiateId,designationName,candiateName){ 
		$("#tourIndividualPerformanceDivId").modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
		var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		var fromDate = customStartATMDate.split("/")
		var toDate = customEndATMDate.split("/")
				
		$("#tourSlider").dateRangeSlider({
				bounds: {min: new Date(fromDate[2], 0, 1), max: new Date(toDate[2], 11, 31)},
				//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
				defaultValues: {min: new Date(fromDate[2], fromDate[1]-1,fromDate[0]), max: new Date(toDate[2],toDate[1]-1,toDate[0])},
				scales: [{
				  first: function(value){ return value; },
				  end: function(value) {return value; },
				  next: function(value){
					var next = new Date(value);
					return new Date(next.setMonth(value.getMonth() + 1));
				  },
				  label: function(value){
					return months[value.getMonth()];
				  },
				  format: function(tickContainer, tickStart, tickEnd){
					tickContainer.addClass("myCustomClass");
				  }
				}]
			
		});
		//var selectedDate = $("#toursNewHeadingId").html();
		//&nbsp&nbsp<small style='color:green;'>"+selectedDate+"</small>&nbsp<small>DETAILS</small>
		$("#nameOfMemberHeadingId").html('');
		$("#nameOfMemberHeadingId").html("<h4 class='modal-title text-capital'>"+candiateName+" - <small style='color:#4A5863'>"+designationName+"</small></h4>");
		$("#tourIndividualDetailsBlock").html(spinner);
		$("#tourIndividualDetailsTableBlock").html(spinner);
		$("#monthWiseComplainceDivId").html(spinner);
		var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :customStartATMDate ,
					 toDate : customEndATMDate
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
	}
	function buildIndividualPersonTourDetails(result){
	   $("#tourIndividualDetailsBlock").html('');
	 	if(result !=null && result.subList != null && result.subList.length > 0){
			 var str='';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<div class="row">';
					
						str+='<div class="col-md-4 col-xs-12 col-md-12">';
							str+='<h4 class="text-capital">TOTAL COMPLAINCE OVERVIEW</h4>';
							str+='<div id="overAllComplainsGraph" class="" style="height:150px" ></div>';
						str+='</div>';
						str+='<div class="col-md-8 col-xs-12 col-md-12">';
							str+='<div class="row">';
						for(var i in result.subList){
							str+='<div class="col-md-4 col-xs-12 col-md-12">';
							str+='<h4 class="text-capital">'+result.subList[i].name+'</h4>';
							str+='<div id="individualComplainsGraph'+i+'" class="" style="height:150px" ></div>';
							str+='</div>';
						
						}
					str+='</div>';
					str+='</div>';
					str+='</div>';
					
			str+='</div>';
			$("#tourIndividualDetailsBlock").html(str);
		}else{
			$("#tourIndividualDetailsBlock").html("NO DATA AVAILABLE.");
		}
		
		if(result !=null && result.subList != null && result.subList.length > 0){
			
			var mainArrNma=[];
			var individualPerfArr=[];
			var nameArr;
			var jsonObj=[];	
			mainArrNma.push("All")
			jsonObj.push(result.complaincePer);
			for(var i in result.subList){
				jsonObj.push(result.subList[i].complaincePer);
				mainArrNma.push(result.subList[i].name);
				nameArr = result.subList[i].name;
			}
		
		 $(function () {
			  $('#overAllComplainsGraph').highcharts({
				colors: ['#80F6F8'],
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
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: mainArrNma,
					title: {
						text: null
					},
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				 tooltip: {formatter: function(){
					return '<b>'+this.x+' : '+ Highcharts.numberFormat(this.y, 2) +'%</b>';
				}      
				},
				plotOptions: {
					column: {  
						//stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,2) +"%"; 
								}
							}
						  
						},
					},
					
				},
				legend: {
					enabled: false,
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
				
				series:  [{
					name: nameArr,
					data: jsonObj
				}]
			});
		});
	}
	
	if(result !=null && result.subList != null && result.subList.length > 0){
			for(var i in result.subList){
				var performanceArr =[];
				performanceArr.push(result.subList[i].targetDays);
				performanceArr.push(result.subList[i].complainceDays);
				//performanceArr.push(result.subList[i].yetToTourCnt);
		if(performanceArr != 0 && performanceArr.length > 0){		
		 $(function () {
			  $('#individualComplainsGraph'+i+'').highcharts({
				colors: ['#7F7037','#80F6F8'],
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
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ["Target","Toured"],
					title: {
						text: null
					},
					labels: {
							formatter: function() {
								return this.value.toString().substring(0, 5)+'...';
							},
							
						}
					
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				 tooltip: {formatter: function(){
					return '<b>'+this.x+' : '+ (this.y) +'</b>';
					
				}      
				},
				plotOptions: {
					column: {  
						//stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						  
						},
					},
					
				},
				legend: {
					enabled: false,
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
				
				series:  [{
					name: '',
					data: performanceArr,
					colorByPoint:true
				}]
			});
		});
		}else{
			 $('#individualComplainsGraph'+i+'').html("No Data Availble");
		}
	}	
	}
	
	if(result !=null && result.monthList != null && result.monthList.length > 0){
		  var str2='';
		  
		  	 str2+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				str2+='<h4 class="text-capital">MONTH WISE COMPLIANCE OVERVIEW</h4>';
					 str2+='<div class="slickApplyTourCls" >';	
				for(var i in result.monthList){
					
						//str2+='<li>';
						if(i == 0 || i%3 == 0){
							str2+='<div class="row">';
						}
					   if($(window).width() < 500)
						{
							str2+='<div class="table-responsive">';
						}
						str2+='<div class="col-md-4 col-xs-12 col-sm-12 m_top10">';
							str2+='<table class="table table-bordered">';
							 var categoryList = result.monthList[i].subList;
							 
							 if(categoryList != null && categoryList.length > 0){
									var moxCategoryLength = categoryList.length;
									var categoryVO = result.monthList[i].subList[0];
								str2+='<tr>';
									str2+='<td rowspan='+(moxCategoryLength+1)+' style="font-size:22px;background-color:#EDECE7">'+result.monthList[i].name+'<br>'+result.monthList[i].year+'</td>';
									str2+='<td style="background-color:#EDECE7"><p>'+categoryVO.name+'('+categoryVO.complainceDays+')';
									   if(categoryVO.complainceDays >= categoryVO.targetDays){
											str2+='<small style="text-align: center;"><i  style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-success "></i></small>';
										   }else{
											str2+='<small style="text-align: center;"><i  style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
										   } 
									  str2+='<div class="dropup">';
									str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
										str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;z-index:999;width:220px">';
											str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per Month</i></p>';
											str2+='<table class="table">';
												str2+='<tr><td style="background-color:#EDECE7">'+categoryVO.name+' - '+categoryVO.targetDays+'</td></tr>';
											str2+='</table>';
										str2+='</div>';
									str2+='</div></p>';  
										  str2+='</td>';  
								        if(result.monthList[i].isComplaince != null && result.monthList[i].isComplaince.trim()=="True"){
										   str2+='<td style="background-color:#3DBC93;text-align:center;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-ok" style="font-size:55px;display:block;color:#068057;"></i><small style="color:#fff;">Compliance</small></td>';
										}else{
										   str2+='<td style="background-color:#E35B69;text-align:center;width:50px;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-remove" style="font-size:55px;display:block;color:#bf3646;"></i><small style="color:#fff;">Non Compliance</small></td>';
										} 	 
								  str2+='</tr>';
								
									for(var k in categoryList){
										if(k==0)
										continue;
										 str2+='<tr>';
										if(categoryList[k].name == null || categoryList[k].name == ""){
											str2+='<td style="background-color:#EDECE7;"> - </td>';
										}else{
										   str2+='<td style="background-color:#EDECE7"><p>'+categoryList[k].name+'('+categoryList[k].complainceDays+')';
											   if(categoryList[k].complainceDays >= categoryList[k].targetDays){
												str2+='<small style="text-align: center;"><i style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-danger"></i></small>';
											   }else{
													str2+='<small style="text-align: center;"><i style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
											   } 
											 str2+='<div class="dropup">';
											str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
												str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu3" style="padding:10px;z-index:999;width:220px !important;">';
													str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per Month</i></p>';
													str2+='<table class="table">';
														str2+='<tr style="background-color:#EDECE7"><td>'+categoryList[k].name+' - '+categoryList[k].targetDays+'</td></tr>';
													str2+='</table>';
												str2+='</div>';
											str2+='</div></p>';  
										   str2+='</td>';
										}
									str2+='</tr>';
								  }
							 }
							str2+='</table>';
							str2+='</div>';
							//str2+='</li>';
						   if(i == 2 || i%3 == 2){
								str2+='</div>';
							}
						 if($(window).width() < 500)
						{
							str2+='</div>';
						}
						
				}
				str2+='</div>';
				str2+='</div>';
			
			
		$("#monthWiseComplainceDivId").html(str2);
		/* $('.slickApplyTourCls').slick({
			//slide: 'li',
		 	 slidesToShow: 3,
			 slidesToScroll: 3,
			  infinite: false,
			 variableWidth: false
			 
		}); */
	}
	 if(result !=null && result.subList3 != null && result.subList3.length > 0){
		var str1='';
		str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
		str1='<h4 class="text-capital">MONTH WISE COMPLIANCE COMMENTS & ATTACHMENTS</h4>';
				str1+='<div class="m_top20">';
					 if($(window).width() < 500)
					{
						str1+='<div class="table-responsive">';
					}
					str1+='<table class="table table-bordered borderedWeight">';
						str1+='<thead class="bg_D8">';
							str1+='<tr>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Month & Date</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Category</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Type</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">NO OF Days</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">ATTACHMENT</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Comment</th>';
							str1+='</tr>';
							str1+='<tbody>';
							  
								for(var i in result.subList3){
									str1+='<tr>';
									var moxCategoryLength = 0;
									if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
									   moxCategoryLength = result.subList3[i].subList.length;
									}
										str1+='<td rowspan='+(moxCategoryLength)+'>'+result.subList3[i].tourDate+'</td>';
										if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
											 var monthVO = result.subList3[i].subList[0];
											    if(monthVO != null){
													 if(monthVO.tourCategory == null || monthVO.tourCategory == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+monthVO.tourCategory+'</td>';
														}
														if(monthVO.tourType == null || monthVO.tourType == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+monthVO.tourType+'</td>';
														}
														if(monthVO.count == null || monthVO.count==0){
															str1+='<td> - </td>';
														}else{
															str1+='<td class="text-center">'+monthVO.count+'</td>';
														}	
												}
										} 
											if(result.subList3[i].filePath != null && result.subList3[i].filePath.length > 0){
													var filePathArr = result.subList3[i].filePath.split(",");
													if(filePathArr != null && filePathArr.length > 0){
														str1+='<td rowspan='+(moxCategoryLength)+'>';
													for (var m = 0; m < filePathArr.length; m++) { 
															var fullName = filePathArr[m];
															var nameArr = fullName.split(".");
															var type = nameArr[1];
															if(type=="pdf" || type=="PDF"){
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/pdf.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type=="xls" ||type=="xlsx"){  
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/excel.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type=="doc" || type=="docx"){
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/word.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type != null){  
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/fileImage.png" class="media-object" alt="" style="width:30px;"/></span>';
															}           
														}
														str1+='</td>';	
													}
												}else{    
													str1+='<td rowspan='+(moxCategoryLength)+'> - </td>';  
												} 	
												
												
												if(result.subList3[i].comment != null && result.subList3[i].comment.length > 0){
														if(result.subList3[i].comment.length > 15){
														 str1+='<td rowspan='+(moxCategoryLength)+' style="cursor:pointer;" title="'+result.subList3[i].comment+'">'+result.subList3[i].comment.substring(0,30)+'...</td>';	
														}else{
														 str1+='<td rowspan='+(moxCategoryLength)+'>'+result.subList3[i].comment+'</td>';	
														}
													}else{
													  str1+='<td rowspan='+(moxCategoryLength)+'> - </td>';	
													}
									str1+='</tr>';
										if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
											var categoryList = result.subList3[i].subList;
												for(var k in categoryList){
													if(k == 0)
													continue;
													str1+='<tr>';
													if(categoryList[k].tourCategory == null || categoryList[k].tourCategory == ""){
														str1+='<td> - </td>';
													}else{
														str1+='<td>'+categoryList[k].tourCategory+'</td>';
													}
													if(categoryList[k].tourType == null || categoryList[k].tourType == ""){
														str1+='<td> - </td>';
													}else{
														str1+='<td>'+categoryList[k].tourType+'</td>';
													}
													if(categoryList[k].count == null || categoryList[k].count==0){
														str1+='<td> - </td>';
													}else{
														str1+='<td class="text-center">'+categoryList[k].count+'</td>';
													}
											 str1+='</tr>';
											}
										}
							
							  }
			
							str1+='</tbody>';
						str1+='</thead>';
					str1+='</table>';	
					if($(window).width() < 500)
					{
						str1+='</div>';
					}
				str1+='</div>';
			str1+='</div>';
		$("#tourIndividualDetailsTableBlock").html(str1); 
	}
}
	

function getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat){
	
	$("#tourIndividualDetailsBlock").html(spinner);
	$("#tourIndividualDetailsTableBlock").html(spinner);
	$("#monthWiseComplainceDivId").html(spinner);
	var jsObj ={ 
			 candiateId : candiateId,
			 fromDate 	:frmDateInRequiredFormat ,
			 toDate 	: toDateInRequiredFormat
		  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
}