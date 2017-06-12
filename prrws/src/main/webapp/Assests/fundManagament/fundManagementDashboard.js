	//onLoadCalls();
	//onLoadClicks();
	onLoadInitialisations();
	var glStartDate = moment().subtract(5,'years').format("DD/MM/YYYY");
	var glEndDate = moment().add(5,'years').format("DD/MM/YYYY");
	//var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	var spinner = '<h1 class="loader">  <span>L</span>  <span>O</span>  <span>A</span>  <span>D</span>  <span>I</span>  <span>N</span>  <span>G</span></h1>';
	///Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations
	function onLoadCalls()
	{
		getLocationWiseFundDetails(3,'highest','highFundDist');
		getLocationWiseFundDetails(4,'highest','highFundCons');
		getLocationWiseFundDetails(3,'lowest','lowFundDist');
		getLocationWiseFundDetails(4,'lowest','lowFundCons');
		getLocationWiseFundDetails(5,'highest','highFundMandal');
		getLocationWiseFundDetails(6,'highest','highFundVillage');
		getLocationWiseFundDetails(5,'lowest','lowFundMandal');
		getLocationWiseFundDetails(6,'lowest','lowFundVillage');
		getAverageFundForAnyLevel(3,'avgFundDist');
		getAverageFundForAnyLevel(4,'avgFundCons');
		getLocationWiseAmountDetails(2,'stateLevlOvervw','overview');
		getLocationWiseAmountDetails(3,'distLevlOvervw','overview');
		getLocationWiseAmountDetails(4,'consLevlOvervw','overview');
		getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview');
		getAllSubLocationsBySuperLocationId(21);
		getSchemeWiseHighestAndLowestFund('highest','highFundScheme');
		getSchemeWiseHighestAndLowestFund('lowest','lowFundScheme');
		getTotalFunds('totFund');
		getTotalSchemes(4,'totFundScheme');
		// getSchemeWiseHighestAndLowestFund("highest");
		// getSchemeWiseHighestAndLowestFund("lowest");
		getTotalLocationsByScopeId(4,'totFundCons');
		// getTotalLocationsByScopeId(5);
		// getTotalLocationsByScopeId(6);
		
		getAverageFundForScheme('avgFundScheme');
		
	}
	
	$(document).on("click","[tab-switch] li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var blockName = $(this).closest("ul").attr("tab-switch");
		var blockType = $(this).attr("attr_type");
		if(blockName == 'consLevel')
		{
			if(blockType == 'overview')
			{
				getLocationWiseAmountDetails(4,'consLevlOvervw','overview');
			}else if(blockType == 'scheme')
			{
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','scheme');
			}else if(blockType == 'deptscheme'){
				 getFinancialYearWiseDeptsWiseSchemeAmountDetails(4,'consLevlOvervw','deptscheme');
			}
		}else if(blockName == 'distLevel')
		{
			if(blockType == 'overview')
			{
				getLocationWiseAmountDetails(3,'distLevlOvervw','overview');
			}else if(blockType == 'scheme')
			{
				getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','scheme');
			}else if(blockType == 'deptscheme'){
				 getFinancialYearWiseDeptsWiseSchemeAmountDetails(3,'distLevlOvervw','deptscheme');
			}
		}else if(blockName == 'stateLevel')
		{
			if(blockType == 'overview')
			{
				getLocationWiseAmountDetails(2,'stateLevlOvervw','overview');
			}else if(blockType == 'scheme')
			{
				getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','scheme');
			}else if(blockType == 'deptscheme'){
				//getFinancialYearWiseDeptsWiseSchemeAmountDetails(2,'stateLevlOvervw','deptscheme')
			}
		}
	});
	$(document).on('change','.constLevelDistNames',function(){
		var locationScopeId = $("#constLevelDistNames").val();
	    getAllSubLocationsBySuperLocationId(locationScopeId);
	});
	function onLoadInitialisations()
	{
		$(document).keydown(function(event){
			if(event.keyCode==123){
				alert("Hoo no! don't try to expose me");
				return false;
			}
			else if(event.ctrlKey && event.shiftKey && event.keyCode==73){        
				alert("Hoo no! don't try to expose me");
				return false;  //Prevent from ctrl+shift+i
			}
		});
		header = $('header section'),
		$(window).scroll(function(){
			var windowScrollTop = $(window).scrollTop();

			if (windowScrollTop>50) {
				header.addClass("header-fixed");
			} else {
				header.removeClass("header-fixed");
			}
		});
		$(".chosenSelect").chosen();
		
		/*$("#mainDate").daterangepicker({
			startDate: moment().subtract(5,'years'),
			endDate:moment().add(5,'years'),
			format:"DD/MM/YYYY",
			opens:'left'
		});
		$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
			glStartDate = picker.startDate.format('DD/MM/YYYY')
			glEndDate = picker.endDate.format('DD/MM/YYYY')
			onLoadCalls();
		});*/
		
		//srujna
		$("#dateRangePicker").daterangepicker({
		opens: 'left',
		startDate: glStartDate,
		endDate: glEndDate,
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
			'Today' : [moment(), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	});
    var dates= $("#dateRangePicker").val();
	var date=dates.split('-');
	var pickerDates = date[0]+'-'+date[1]
	//var pickerDates = glStartDate+' - '+glEndDate
	if(dates == pickerDates)
	{
		$("#dateRangePicker").val('All');
	}
	$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
		    glStartDate = picker.startDate.format('DD/MM/YYYY')
			glEndDate = picker.endDate.format('DD/MM/YYYY')
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePicker").val('All');
		}
		
		onLoadCalls();
	});  //srujna
		
		$(document).on("change","#financialYearId",function(){
			$(".switch-btn li").removeClass("active");
			$(".switch-btn li:first-child").addClass("active");
			onLoadCalls();
		});
		getAllDepartments();
		getAllFiniancialYears();		
	}
	
	function highChart(divId,namesArr,DataArr)
	{
		console.log(namesArr)
		$(".chart"+divId).highcharts({
			chart: {
				type: 'bar',
				backgroundColor:'transparent'
			
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
				categories: namesArr
			},
			yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
				title: {
					text: null
				},
				labels: {
					enabled:false
				},
				stackLabels: {
					useHTML: true,	
					//align: 'left',
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					formatter: function() {
						return '<span><i class="fa fa-inr"></i>: '+this.total+'</span>';
						//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
						//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
						//return (this.total);
					} 
				
				}
			
			},
			tooltip: {
				formatter: function () {
				var s = '<b>' + this.x + '</b>';

					$.each(this.points, function () {
						if(this.series.name != "Series 1")  
						s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
						this.y/* +' - ' +
						(Highcharts.numberFormat(this.percentage,1)+'%'); */
					});

					return s;
				},
				shared: true
			},
			plotOptions: {
				bar: {
					stacking: 'normal',
					pointWidth: 30,
					gridLineWidth: 15
				}
			},
			legend: {
				verticalAlign:'top',
				enabled: true
			},
			series: DataArr
		});
	}
	function getLocationWiseFundDetails(locationId,dataType,divId)
	{
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdArr = $('#financialYearId').val();
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr = $('#DepartmentsId').val();  
		var searchLevelId = 0;
		var searchLevelVals = [];
		//searchLevelVals.push(13);
		if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		}	
		  var json = {
			blockLevelId : locationId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,
			type:dataType,
			searchLevelId:searchLevelId,
			searchLvlVals:searchLevelVals
		  }
		$.ajax({
			url: 'getLocationWiseFundDetails',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp) {
				buildLocationWiseFundDetails(ajaxresp,divId)
			}
		});
	}
	function buildLocationWiseFundDetails(ajaxresp,divId)
	{
		var str='';
		if(ajaxresp.name != null)
		{
			str+='<h4 class="panel-title text-capital district-name"> '+ajaxresp.name+'</h4>';
		}
		if(ajaxresp.totalAmt != null && ajaxresp.totalAmt != "0.0" && divId != 'avgFundDist' && divId != 'avgFundCons')
		{
			str+='<h3><i class="fa fa-inr"></i>: '+ajaxresp.totalAmt+'<small class="text-success">'+ajaxresp.perc+' %</small></h3>';
		}else if(divId == 'avgFundDist' || divId == 'avgFundCons'){
			str+='<h3><i class="fa fa-inr"></i>: '+ajaxresp.averageAmt+'<small class="text-success">'+ajaxresp.perc+' %</small></h3>';
		}
		
		/* if(ajaxresp.fundedLoc != null)
		{
			str+='<h3>'+ajaxresp.fundedLoc+'</h3>';
		} */
		if(ajaxresp.totSchemes != null)
		{
			str+='<h3>'+ajaxresp.totSchemes+'</h3>';
		}
		
		if(ajaxresp.subList.length > 0)
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.subList)
				{
					str+='<li class="text-center">';
						str+='<p class="text-muted">'+ajaxresp.subList[i].name+'</p>';
						str+='<p class="text-success"><small>'+ajaxresp.subList[i].percentage+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.subList[i].total+'</p>';
					str+='</li>';
				}
			str+='</ul>';
		}else if(ajaxresp.detailsVOs.length > 0  && divId != 'avgFundDist' && divId != 'avgFundCons')
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.detailsVOs)
				{
					str+='<li class="text-center">';
						str+='<p class="text-muted">'+ajaxresp.detailsVOs[i].name+'</p>';
						str+='<p class="text-success"><small>'+ajaxresp.detailsVOs[i].perc+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.detailsVOs[i].totalAmt+'</p>';
					str+='</li>';
				}
			str+='</ul>';
		}else if(divId == 'avgFundDist' || divId == 'avgFundCons')
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.detailsVOs)
				{
					str+='<li class="text-center">';
						str+='<p class="text-muted">'+ajaxresp.detailsVOs[i].name+'</p>';
						str+='<p class="text-success"><small>'+ajaxresp.detailsVOs[i].perc+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.detailsVOs[i].averageAmt+'</p>';
					str+='</li>';
				}
			str+='</ul>';
		}
		if(ajaxresp.notFundedLoc != null)
		{
			str+='<ul class="list-inline">';
				str+='<li class="text-center">';
					str+='<p class="text-muted">FUNDED</p>';
					str+='<p>'+ajaxresp.fundedLoc+'</p>';
					str+='<p class="text-success"><small>'+ajaxresp.fundedPerc+'%</small></p>';
				str+='</li>';
				str+='<li class="text-center">';
					str+='<p class="text-muted">NOT-FUNDED</p>';
					str+='<p class="text-muted">'+ajaxresp.notFundedLoc+'</p>';
					str+='<p class="text-success"><small>'+ajaxresp.nonFundedPerc+'%</small></p>';
				str+='</li>';
			str+='</ul>';
		}
		
		
		
		$("#"+divId).html(str);
	}
	function getLocationWiseAmountDetails(levelId,divId,type){
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdList = $('#financialYearId').val();
		if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		}
		var json = {
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			sortingType : "name",      //name,count    
			order : "desc",   //asc,desc
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
		}
		$.ajax({
			url : "getLocationWiseAmountDetails",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				if(ajaxresp != null && ajaxresp.length > 0)
				{
					buildLocationWiseAmountDetails(ajaxresp,divId,type,levelId);
				}else{
					$("#"+divId).html("NO DATA AVAILABLE");
				}
			}
		});
	}
	function buildLocationWiseAmountDetails(result,divId,type,levelId)
	{
		var str='';
		str+='<div class="scroller'+divId+'">';
			str+='<div class="chart'+divId+'"></div>';
		str+='</div>';
		$("#"+divId).html(str);
		var length = result.length
		var height = '';
		if(length == 0)
		{
			height = length * 100;
		}else if(length > 3){
			height = length * 60;
		}else if(length > 20){
			height = length * 160;
		}
		if(length > 8)
		{
			$(".scroller"+divId).mCustomScrollbar({setHeight:'400px'})
		}
		$(".chart"+divId).height(height);
		var table='';
		if(type == 'overview')
		{
			table+='<table class="table table-bordered table-condensed" id="dataTable'+divId+'" style="width:100%;">';
				table+='<thead class="text-center">';
					table+='<tr>';
						table+='<th></th>';
						table+='<th></th>';
						for(var i in result[0].locationList1)
						{
							table+='<th colspan="2" class="text-center">'+result[0].locationList1[i].financialYear+'</th>';
						}
					table+='</tr>';
					table+='<tr>';
						table+='<th>ID</th>';
						if(levelId == '2')
						{
							table+='<th>State</th>';
						}else if(levelId == '3'){
							table+='<th>District</th>';
						}else if(levelId == '4')
						{
							table+='<th>Constituency</th>';
						}else if(levelId == '5')
						{
							table+='<th>Mandal</th>';
						}
						
						for(var i in result[0].locationList1)
						{
							table+='<th class="text-center no-right-border">No</th>';
							table+='<th class="text-center">Amt.</th>';
						}
					table+='</tr>';
				table+='</thead>';
				table+='<tbody>';
					for(var i in result){
						table+='<tr>';
							table+='<td>'+result[i].locationId+'</td>';
							table+='<td>'+result[i].locationName+'</td>';
							for(var j in result[i].locationList1){
								table+='<td class="text-center no-right-border">'+result[i].locationList1[j].count+'</td>';
								table+='<td class="text-center">'+result[i].locationList1[j].amount+'</td>';
							}
						table+='</tr>';
					}
					
				table+='</tbody>';
			table+='</table>';
		}else if(type == 'scheme')
		{
			if(result[0].subList.length >= 2)
			{
				table+='<div class="table-responsive">';
			}	
			table+='<table class="table table-bordered table-condensed" id="dataTable'+divId+'" style="width:100%;">';
				table+='<thead class="text-center">';
					table+='<tr>';
						table+='<th></th>';
						table+='<th></th>';
						for(var j in result[0].subList)
						{
							table+='<th class="text-center" colspan="'+((result[0].subList[0].subList.length)*2)+'">'+result[0].subList[j].year+'</th>';
						}
					table+='</tr>';
					table+='<tr>';
						table+='<th></th>';
						table+='<th></th>';
						for(var i in result[0].subList)
						{
							for(var j in result[0].subList[0].subList){
								table+='<th colspan="2" class="text-center">'+result[0].subList[0].subList[j].name+'</th>';
							}
						}
					table+='</tr>';
					table+='<tr>';
						table+='<th>ID</th>';
						if(levelId == '2')
						{
							table+='<th>State</th>';
						}else if(levelId == '3'){
							table+='<th>District</th>';
						}else if(levelId == '4')
						{
							table+='<th>Constituency</th>';
						}else if(levelId == '5')
						{
							table+='<th>Mandal</th>';
						}
						for(var i in result[0].subList)
						{
							for(var j in result[0].subList[0].subList){
								table+='<th class="text-center no-right-border">No</th>';
								table+='<th class="text-center">Amt.</th>';
							}
						}
					table+='</tr>';
				table+='</thead>';
				table+='<tbody>';
					for(var i in result){
						table+='<tr>';
							if(levelId == '2')
							{
								table+='<td>1</td>';
								table+='<td>Andhra Pradesh</td>';
							}else if(levelId == '3'){
								table+='<td>'+result[i].addressVO.districtId+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
							}else if(levelId == '4')
							{
								table+='<td>'+result[i].addressVO.assemblyId+'</td>';
								table+='<td>'+result[i].addressVO.assemblyName+'</td>';
							}else if(levelId == '5')
							{
								table+='<td>'+result[i].addressVO.mandalId+'</td>';
								table+='<td>'+result[i].addressVO.mandalName+'</td>';
							}
							for(var j in result[i].subList){
								for(var k in result[i].subList[j].subList)
								{
									table+='<td class="text-center no-right-border">'+result[i].subList[j].subList[k].count+'</td>';
									table+='<td class="text-center">'+result[i].subList[j].subList[k].totalCount+'</td>';
								}
							}
						table+='</tr>';
					}
					
				table+='</tbody>';
			table+='</table>';
			if(result[0].subList.length >= 2)
			{
				table+='</div>';
			}	
		}else if(type == 'deptscheme')
		{
			table+='<div class="table-responsive">';
				table+='<table class="table table-bordered table-condensed" id="dataTable'+divId+'" style="width:100%;">';
					table+='<thead class="text-center">';
						table+='<tr>';
							table+='<th></th>';
							table+='<th></th>';
							for(var j in result[0].subList)
							{
								table+='<th class="text-center" colspan="'+(((result[0].subList[0].subList[0].subList.length)*(result[0].subList[0].subList.length))*2)+'">'+result[0].subList[j].year+'</th>';
							}
						table+='</tr>';
						table+='<tr>';
							table+='<th></th>';
							table+='<th></th>';
							for(var i in result[0].subList){
								for(var j in result[0].subList[i].subList)
								{
									table+='<th class="text-center" colspan="'+((result[0].subList[0].subList[0].subList.length)*2)+'">'+result[0].subList[i].subList[j].name+'</th>';
								}
							}
							
						table+='</tr>';
						table+='<tr>';
							table+='<th></th>';
							table+='<th></th>';
							for(var i in result[0].subList){
								for(var j in result[0].subList[i].subList){
									for(var k in result[0].subList[i].subList[j].subList)
									{
										table+='<th colspan="2" class="text-center">'+result[0].subList[i].subList[j].subList[k].name+'</th>';
									}
								}
							}	
						table+='</tr>';
						table+='<tr>';
							table+='<th>ID</th>';
							if(levelId == '2')
							{
								table+='<th>State</th>';
							}else if(levelId == '3'){
								table+='<th>District</th>';
							}else if(levelId == '4')
							{
								table+='<th>Constituency</th>';
							}else if(levelId == '5')
							{
								table+='<th>Mandal</th>';
							}
							for(var i in result[0].subList){
								for(var j in result[0].subList[i].subList){
									for(var k in result[0].subList[i].subList[j].subList)
									{
										table+='<th class="text-center no-right-border">No</th>';
										table+='<th class="text-center">Amt.</th>';
									}
								}
							}
						table+='</tr>';
					table+='</thead>';
					table+='<tbody>';
						for(var i in result){
							table+='<tr>';
								if(levelId == '2')
								{
									table+='<td>1</td>';
									table+='<td>Andhra Pradesh</td>';
								}else if(levelId == '3'){
									table+='<td>'+result[i].addressVO.districtId+'</td>';
									table+='<td>'+result[i].addressVO.districtName+'</td>';
								}else if(levelId == '4')
								{
									table+='<td>'+result[i].addressVO.assemblyId+'</td>';
									table+='<td>'+result[i].addressVO.assemblyName+'</td>';
								}else if(levelId == '5')
								{
									table+='<td>'+result[i].addressVO.mandalId+'</td>';
									table+='<td>'+result[i].addressVO.mandalName+'</td>';
								}
								
								for(var j in result[i].subList)
								{
									for(var k in result[i].subList[j].subList)
									{
										for(var l in result[i].subList[j].subList[k].subList)
										{
											table+='<td class="text-center no-right-border">'+result[i].subList[j].subList[k].subList[l].count+'</td>';
											table+='<td class="text-center">'+result[i].subList[j].subList[k].subList[l].totalCount+'</td>';
										}
									}
								}
								
							table+='</tr>';
						}
						
					table+='</tbody>';
				table+='</table>';
			table+='</div>';
				
		}
		
		$("#"+divId+"Table").html(table);
		$("#dataTable"+divId).dataTable();
		
		if(type == 'overview')
		{
			if(levelId == 2){
				if(result !=null && result.length>0){
					var locationNamesArr=[];
					var mainlocationArr =[];
					for(var i in result){
						if(result[i].locationList1 !=null &&  result[i].locationList1.length>0){
							for(var j in result[i].locationList1){
								if(result[i].locationList1[j].financialYearId != 0){
									mainlocationArr.push({y:result[i].locationList1[j].amount})
								}
								locationNamesArr.push("Year<br/>"+result[i].locationList1[j].financialYear);
							}
						}
					}
					var length = result.length
					var height = '';
					if(length == 0)
					{
						height = length * 100;
					}else if(length > 3){
						height = length * 120;
					}
					if(length > 8)
					{
						$(".scroller"+divId).mCustomScrollbar({setHeight:'600px'})
					}
					$(".chart"+divId).height(height);
					$(".chart"+divId).highcharts({
						chart: {
							type: 'bar',
							backgroundColor:'transparent'
						
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
							categories: locationNamesArr,
							labels: {
							   enabled: true,
								
							}
						},
						yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
							title: {
								text: null
							},
							labels: {
								enabled:false
							},
							stackLabels: {
								useHTML: true,
								align: 'left',
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
								
									return '<span style="top:16px; position: absolute;"><br/><h3><i class="fa fa-inr"></i>: '+this.total+'</h3></span>';
									//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
									//return (this.total);
								} 
							
							}
						
						},
						tooltip: {
							 pointFormat: '<b>{point.y}</b>',
							 shared:true
						},
						plotOptions: {
							bar: {
								stacking: 'normal',
								pointWidth: 30,
								gridLineWidth: 15
							}
						},
						legend: {
							verticalAlign:'top',
							enabled: false
						},
						series: [{
							 name: '',
							 data: mainlocationArr,
							 color: '#FF872C'
							 
						}]
					});
				}else{
					$(".chart"+divId).html("No Data AVailable")
				}
				
			}else if(levelId == 3 || levelId == 4){
				
				if(result !=null && result.length>0){
					var locationLevelNames=[];
					var year1415Arr=[];
					var year1516Arr=[];
					var year1617Arr=[];
					
					for(var i in result){
						locationLevelNames.push(result[i].locationName);
						if(result[i].locationList1 !=null && result[i].locationList1.length>0){
							for(var j in result[i].locationList1){
								if(result[i].locationList1[j].financialYearId==1){
									 year1415Arr.push({"y":result[i].locationList1[j].amount}); 
								}else if(result[i].locationList1[j].financialYearId==2){
									 year1516Arr.push({"y":result[i].locationList1[j].amount}); 
								}else if(result[i].locationList1[j].financialYearId==3){
									 year1617Arr.push({"y":result[i].locationList1[j].amount}); 
								}
							}
						}
						var mainJosnObjLocArr = [];
						   if(year1415Arr != null && year1415Arr.length > 0){
							mainJosnObjLocArr.push({name:'2014-2015',data:year1415Arr,color:"#FE6603"});  
						  }
						   if(year1516Arr != null && year1516Arr.length > 0){
							mainJosnObjLocArr.push({name:'2015-2016',data:year1516Arr,color:"#4546B6"});  
						  }
						  if(year1617Arr != null && year1617Arr.length > 0){
							mainJosnObjLocArr.push({name:'2016-2017',data:year1617Arr,color:"#0065FE"});  
						  }
					}
					var length = result.length
					var height = '';
					if(length == 0)
					{
						height = length * 100;
					}else if(length > 3){
						height = length * 60;
					}
					if(length > 8)
					{
						$(".scroller"+divId).mCustomScrollbar({setHeight:'400px'})
					}
					$(".chart"+divId).height(height);
					$(".chart"+divId).highcharts({
						chart: {
							type: 'bar',
							backgroundColor:'transparent'
						
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
							categories: locationLevelNames
						},
						yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
							title: {
								text: null
							},
							labels: {
								enabled:false
							},
							stackLabels: {
								useHTML: true,
								//align: 'left',
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
									return '<span><i class="fa fa-inr"></i>: '+this.total+'</span>';
									//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
									//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
									//return (this.total);
								} 
							
							}
						
						},
						tooltip: {
							useHTML: true,
							formatter: function () {
							var s = '<b>' + this.x + '</b>';

								$.each(this.points, function () {
									if(this.series.name != "Series 1")  
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									this.y/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
								});

								return s;
							},
							shared: true
						},
						plotOptions: {
							bar: {
								stacking: 'normal',
								pointWidth: 30,
								gridLineWidth: 15
							},
						},
						legend: {
							verticalAlign:'top',
							enabled: true
						},
						series: mainJosnObjLocArr
					});
				}
			}
			
			
		}else if(type == 'scheme')
		{
			
			if(levelId == 2){
				
				if(result !=null && result.length>0){
					
					var yearsArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var CRRArr=[];
					var RDFArr=[];
					var MRRArr=[];
					var FC13Arr=[]; 
					for(var i in result){
						
						
						if(result[i].subList !=null && result[i].subList.length>0){
							for(var j in result[i].subList){
								var countAvailable = false;
								if(result[i].subList[j].subList != null && result[i].subList[j].subList.length > 0){
									for(var s in result[i].subList[j].subList){
										if(result[i].subList[j].subList[s].totalCount != null && result[i].subList[j].subList[s].totalCount > 0){
											countAvailable = true;
										}
									}
								}
								if(countAvailable){
									yearsArr.push(result[i].subList[j].year);
								}
								
								
								if(result[i].subList[j].subList !=null && result[i].subList[j].subList.length>0){
									var countAvailable = false;
									for(var s in result[i].subList[j].subList){
										if(result[i].subList[j].subList[s].totalCount != null && result[i].subList[j].subList[s].totalCount > 0){
											countAvailable = true;
										}
									}
									if(countAvailable){
										for(var k in result[i].subList[j].subList){
											if(result[i].subList[j].subList[k].id == 1){
												NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 6){
												 FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }
										}
									}
									
								}	
							}
						}
						var mainJosnObjArr=[];
						if(NABARDArr != null && NABARDArr.length > 0){
							mainJosnObjArr.push({name:'NABARD',data:NABARDArr,color:"#309AFF"});  
						}
						if(MRRArr != null && MRRArr.length > 0){
							mainJosnObjArr.push({name:'MRR',data:MRRArr,color:"#01A64E"});  
						}
						if(NREGPArr != null && NREGPArr.length > 0){
							mainJosnObjArr.push({name:'NREGP',data:NREGPArr,color:"#FF0DAD"});  
						}
						if(CRRArr != null && CRRArr.length > 0){
							mainJosnObjArr.push({name:'CRR',data:CRRArr,color:"#FF872C"});  
						}
						if(RDFArr != null && RDFArr.length > 0){
							mainJosnObjArr.push({name:'RDF',data:RDFArr,color:"#3C46FF"});  
						}
						if(FC13Arr != null && FC13Arr.length > 0){
							mainJosnObjArr.push({name:'13th FC',data:FC13Arr,color:"#5B5B5B"});  
						}
					}
					$(".chart"+divId).highcharts({
						chart: {
							type: 'bar',
							backgroundColor:'transparent'
						
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
							categories: yearsArr
						},
						yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
							title: {
								text: null
							},
							labels: {
								enabled:false
							},
							stackLabels: {
								useHTML: true,
								//align: 'left',
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
									return '<span><i class="fa fa-inr"></i>: '+this.total+'</span>';
									//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
									//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
									//return (this.total);
								} 
							
							}
						
						},
						tooltip: {
							useHTML: true,
							formatter: function () {
							var s = '<b>' + this.x + '</b>';

								$.each(this.points, function () {
									if(this.series.name != "Series 1")  
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									this.y/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
								});
								
								return s;
							},
							shared: true
						},
						plotOptions: {
							bar: {
								stacking: 'normal',
								pointWidth: 30,
								gridLineWidth: 15
							}
						},
						legend: {
							verticalAlign:'top',
							enabled: true
						},
						series: mainJosnObjArr
					});
					
				}
			}else if(levelId == 3 || levelId == 4){
				
				if(result !=null && result.length>0){
				
					var assemblyShemeNameArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var CRRArr=[];
					var RDFArr=[];
					var MRRArr=[];
					var FC13Arr=[];
					for(var i in result){
						if(result[i].subList !=null && result[i].subList.length>0){
							for(var j in result[i].subList){
								var countAvailable = false;
								if(result[i].subList[j].subList != null && result[i].subList[j].subList.length > 0){
									for(var s in result[i].subList[j].subList){
										if(result[i].subList[j].subList[s].totalCount != null && result[i].subList[j].subList[s].totalCount > 0){
											countAvailable = true;
										}
									}
								}
								if(countAvailable){
									if(levelId == 3){
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.districtName+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 4){
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.assemblyName+"<br/>("+result[i].subList[j].year+")")
									}
								}
								
								
								if(result[i].subList[j].subList !=null && result[i].subList[j].subList.length>0){
									var countAvailable = false;
									for(var s in result[i].subList[j].subList){
										if(result[i].subList[j].subList[s].totalCount != null && result[i].subList[j].subList[s].totalCount > 0){
											countAvailable = true;
										}
									}
									if(countAvailable){
										for(var k in result[i].subList[j].subList){
											 if(result[i].subList[j].subList[k].id == 1){
												 NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }else if(result[i].subList[j].subList[k].id == 6){
												 FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
											 }
											
										}
									}
									
								}
							}
						}
						var mainJosnObjArr= [];
						if(NABARDArr != null && NABARDArr.length > 0){
							mainJosnObjArr.push({name:'NABARD',data:NABARDArr,color:"#309AFF"});  
						}
						if(MRRArr != null && MRRArr.length > 0){
							mainJosnObjArr.push({name:'MRR',data:MRRArr,color:"#01A64E"});  
						}
						if(NREGPArr != null && NREGPArr.length > 0){
							mainJosnObjArr.push({name:'NREGP',data:NREGPArr,color:"#FF0DAD"});  
						}
						if(CRRArr != null && CRRArr.length > 0){
							mainJosnObjArr.push({name:'CRR',data:CRRArr,color:"#FF872C"});  
						}
						if(RDFArr != null && RDFArr.length > 0){
							mainJosnObjArr.push({name:'RDF',data:RDFArr,color:"#3C46FF"});  
						}
						if(FC13Arr != null && FC13Arr.length > 0){
							mainJosnObjArr.push({name:'13th FC',data:FC13Arr,color:"#5B5B5B"});  
						}
					}
					var length = result.length
					var height = '';
					if(length == 0)
					{
						height = length * 100;
					}else if(length > 3){
						height = length * 120;
					}
					if(length > 8)
					{
						$(".scroller"+divId).mCustomScrollbar({setHeight:'600px'})
					}
					$(".chart"+divId).height(height);
					$(".chart"+divId).highcharts({
						chart: {
							type: 'bar',
							backgroundColor:'transparent'
						
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
							categories: assemblyShemeNameArr
						},
						yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
							title: {
								text: null
							},
							labels: {
								enabled:false
							},
							stackLabels: {
								useHTML: true,
								//align: 'left',
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
									return '<span><i class="fa fa-inr"></i>: '+this.total+'</span>';
									//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
									//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
									//return (this.total);
								} 
							
							}
						
						},
						tooltip: {
							useHTML: true,
							formatter: function () {
							var s = '<b>' + this.x + '</b>';

								$.each(this.points, function () {
									if(this.series.name != "Series 1")  
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									this.y/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
								});
								
								return s;
							},
							shared: true
						},
						plotOptions: {
							bar: {
								stacking: 'normal',
								pointWidth: 30,
								gridLineWidth: 15
							}
						},
						legend: {
							verticalAlign:'top',
							enabled: true
						},
						series: mainJosnObjArr
					});
					
				}
			}
		}else if(type == "deptscheme"){
			if(levelId == 3 || levelId == 4){
				if(result !=null && result.length>0){
				
					var assemblyShemeNameArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var CRRArr=[];
					var RDFArr=[];
					var MRRArr=[];
					var FC13Arr=[];
					for(var i in result){
						
						
						if(result[i].subList !=null && result[i].subList.length>0){
							for(var j in result[i].subList){
								
								
								if(result[i].subList[j].subList !=null && result[i].subList[j].subList.length>0){
									for(var k in result[i].subList[j].subList){
										var countAvailable = false;
										if(result[i].subList[j].subList[k].subList != null && result[i].subList[j].subList[k].subList.length > 0){
											for(var s in result[i].subList[j].subList[k].subList){
												if(result[i].subList[j].subList[k].subList[s].totalCount != null && result[i].subList[j].subList[k].subList[s].totalCount > 0){
													countAvailable = true;
												}
											}
										}
										if(countAvailable){
											if(levelId == 3){
												assemblyShemeNameArr.push(result[i].subList[j].subList[k].addressVO.districtName+"<br/>("+result[i].subList[j].year+":"+result[i].subList[j].subList[k].name+")")
											}else if(levelId == 4){
												assemblyShemeNameArr.push(result[i].subList[j].subList[k].addressVO.assemblyName+"<br/>("+result[i].subList[j].year+":"+result[i].subList[j].subList[k].name+")")
											}
										}
											
										if(result[i].subList[j].subList[k].subList !=null && result[i].subList[j].subList[k].subList.length>0){
											var countAvailable = false;
											for(var s in result[i].subList[j].subList[k].subList){
												if(result[i].subList[j].subList[k].subList[s].totalCount > 0){
													countAvailable = true;
												}
											}
											if(countAvailable){
												for(var l in result[i].subList[j].subList[k].subList){
													 if(result[i].subList[j].subList[k].subList[l].id == 1){
														NABARDArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 2){
														 NREGPArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 3){
														 CRRArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 4){
														 RDFArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 5){
														 MRRArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 6){
														 FC13Arr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
													 }
												}
											}
											
										}
										
									}
								}
							}
						}
					}
						var mainJosnObjArr=[];
						if(NABARDArr != null && NABARDArr.length > 0){
							mainJosnObjArr.push({name:'NABARD',data:NABARDArr,color:"#309AFF"});  
						}
						if(MRRArr != null && MRRArr.length > 0){
							mainJosnObjArr.push({name:'MRR',data:MRRArr,color:"#01A64E"});  
						}
						if(NREGPArr != null && NREGPArr.length > 0){
							mainJosnObjArr.push({name:'NREGP',data:NREGPArr,color:"#FF0DAD"});  
						}
						if(CRRArr != null && CRRArr.length > 0){
							mainJosnObjArr.push({name:'CRR',data:CRRArr,color:"#FF872C"});  
						}
						if(RDFArr != null && RDFArr.length > 0){
							mainJosnObjArr.push({name:'RDF',data:RDFArr,color:"#3C46FF"});  
						}
						if(FC13Arr != null && FC13Arr.length > 0){
							mainJosnObjArr.push({name:'13th FC',data:FC13Arr,color:"#5B5B5B"});  
						}
					
						var length = result.length
						var height = '';
						if(length == 0)
						{
							height = length * 100;
						}else if(length > 3){
							height = length * 300;
						}
						if(length > 8)
						{
							$(".scroller"+divId).mCustomScrollbar({setHeight:'600px'})
						}
						//$(".chart"+divId).css("height","4000px")
						$(".chart"+divId).height(height);
						$(".chart"+divId).highcharts({
							chart: {
								type: 'bar',
								backgroundColor:'transparent'
							
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
								categories: assemblyShemeNameArr
							},
							yAxis: {
									min: 0,
									gridLineWidth: 0,
									minorGridLineWidth: 0,
								title: {
									text: null
								},
								labels: {
									enabled:false
								},
								stackLabels: {
									useHTML: true,
									//align: 'left',
									enabled: true,
									style: {
										fontWeight: 'bold',
										color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
									},
									formatter: function() {
										return '<span><i class="fa fa-inr"></i>: '+this.total+'</span>';
										//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
										//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
										//return (this.total);
									} 
								
								}
							
							},
							tooltip: {
								useHTML: true,
								formatter: function () {
								var s = '<b>' + this.x + '</b>';

									$.each(this.points, function () {
										if(this.series.name != "Series 1")  
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
										this.y/* +' - ' +
										(Highcharts.numberFormat(this.percentage,1)+'%'); */
									});
									
									return s;
								},
								shared: true
							},
							plotOptions: {
								bar: {
									stacking: 'normal',
									pointWidth: 30,
									gridLineWidth: 15
								}
							},
							legend: {
								verticalAlign:'top',
								enabled: true
							},
							series: mainJosnObjArr
						});
					
				}
			}
		}
			
		
	}
	
	function getTotalFunds(divId)
	{
		  var levelValues = [];
		  var financialYrIdArr =$('#financialYearId').val();
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr = $('#DepartmentsId').val();
		  var searchLevelId = 0;
		  var searchLevelVals = [];
		 // searchLevelVals.push(1);
		   if ($.inArray('0', financialYrIdArr) != -1)
			{
				var stringIds = "1,2,3";
				strx   = stringIds.split(',');
				financialYrIdArr = financialYrIdArr.concat(strx);
				financialYrIdArr.shift();
				
			}
		  
		  var json = {
			blockLevelId : 4, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,
			searchLevelId:searchLevelId,
			searchLvlVals:searchLevelVals
		  }
		$.ajax({
			url: 'getTotalFunds',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp) {
			buildLocationWiseFundDetails(ajaxresp,divId);
			}
		});
   }
	function getSchemeWiseLocationWiseAmountDetails(levelId,divId,type){
		$("#"+divId).html(spinner);
		var levelValues = [];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr = [];
		
		var financialYrIdArr = $('#financialYearId').val();
		if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		}
		
		var json = {
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr
		}
		$.ajax({
			url : "getFinancialYearWiseScheameDetails",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				if(ajaxresp != null && ajaxresp.length>0)
					buildLocationWiseAmountDetails(ajaxresp,divId,type,levelId);
				else
					$("#"+divId).html("NO DATA AVAILABLE");
			}
		});
	}
	
	function getFinancialYearWiseDeptsWiseSchemeAmountDetails(levelId,divId,type){
		$("#"+divId).html(spinner);
		var levelValues = [];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr = $("#DepartmentsId").val();
		
		var financialYrIdArr = $('#financialYearId').val();
		if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		}
		var json = {
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr
		}
		$.ajax({
			url : "getFinancialYearWiseDeptsWiseSchemeDetails",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				if(ajaxresp != null && ajaxresp.length>0){
					buildLocationWiseAmountDetails(ajaxresp,divId,type,levelId);
				}else
				{
					$("#"+divId).html("NO DATA AVAILABLE");
				}
			}
		});
	}
	
	function buildSchemewiseReport(result,divId){
		var str='';
		str+='<div class="scroller'+divId+'">';
			str+='<div class="chart'+divId+'"></div>';
		str+='</div>';
		$("#"+divId).html(str);
		if(result !=null && result.length>0)
		{
			var locationNamesArr=[];
			var amountArr =[];
			for(var i in result){
				locationNamesArr.push(result[i].addressVO.districtName);
				
				if(result[i].locationList1 !=null &&  result[i].locationList1.length>0){
					for(var j in result[i].subList){
						for(var k in result[i].subList[j].subList)
						{
							amountArr.push(result[i].subList[j].subList[k].totalCount)
						}
					}
				}
				var mainJosnObjArr=[];
				if(amountArr != null && amountArr.length > 0){
					mainJosnObjArr.push({name:'2014-2015',data:amountArr,color:"#FF872C"});  
				}
				
			}
			var length = result.length
			var height = '';
			if(length == 0)
			{
				height = length * 100;
			}else if(length > 3){
				height = length * 40;
			}
			if(length > 8)
			{
				$(".scroller"+divId).mCustomScrollbar({setHeight:'400px'})
			}
			$(".chart"+divId).height(height);
			highChart(divId,locationNamesArr,mainJosnObjArr);
		}
	}
	function getTotalSchemes(levelId,divId)
	{
		  var levelValues = [];
		  var financialYrIdArr = $('#financialYearId').val();
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr =  $('#DepartmentsId').val();
		  var searchLevelId = 0;
		  var searchLevelVals = [];
		  //searchLevelVals.push(1);
		  	if ($.inArray('0', financialYrIdArr) != -1)
			{
				var stringIds = "1,2,3";
				strx   = stringIds.split(',');
				financialYrIdArr = financialYrIdArr.concat(strx);
				financialYrIdArr.shift();
				
			}
		  var json = {
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,
			searchLevelId:searchLevelId,
			searchLvlVals:searchLevelVals
		  }
		$.ajax({
			url: 'getTotalSchemes',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp) {
				buildLocationWiseFundDetails(ajaxresp,divId)
			}
		});
	}
	function buildTotalSchemes()
	{
		
	}
	function getSchemeWiseHighestAndLowestFund(type,divId)
	{
		  var levelValues = [];
		  var financialYrIdArr = $('#financialYearId').val();
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr =  $('#DepartmentsId').val();
		  var searchLevelId = 3;
		  var searchLevelVals = [];
		  searchLevelVals.push(13);
		  if ($.inArray('0', financialYrIdArr) != -1)
			{
				var stringIds = "1,2,3";
				strx   = stringIds.split(',');
				financialYrIdArr = financialYrIdArr.concat(strx);
				financialYrIdArr.shift();
				
			}
		  var json = {
			blockLevelId : 4, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,
			type:type,
			searchLevelId:searchLevelId,
			searchLvlVals:searchLevelVals
		  }
		$.ajax({
			url: 'getSchemeWiseHighestAndLowestFund',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp) {
				buildLocationWiseFundDetails(ajaxresp,divId);
			}
		});
	}
	function getTotalLocationsByScopeId(locScopeId,divId)
	{
		  var levelValues = [];
		  var financialYrIdArr = $('#financialYearId').val();
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr =  $('#DepartmentsId').val();
		var searchLevelId = 0;
		var searchLevelVals = [];
		//searchLevelVals.push(13);
		if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		}
		  var json = {
			blockLevelId : 4, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,
			searchLevelId:searchLevelId,
			searchLvlVals:searchLevelVals
		  }
		$.ajax({
			url: 'getTotalLocationsByScopeId',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp) {
				buildLocationWiseFundDetails(ajaxresp,divId);
			}
        });
	}	
	function getAverageFundForScheme(divId){
		var financialYrIdList = $('#financialYearId').val(); 
		var deptIdsArr = $('#DepartmentsId').val();
		//var deptId = 0;
		var sourceId = 0;
		var schemeId = 0;
		if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		}
		
		var json = {
			deptIdsList : deptIdsArr,
			sourceId : sourceId,
			schemeId : schemeId,
			financialYrIdList : financialYrIdList,
			fromDateStr : glStartDate,//"01/06/2013",       
			toDateStr : glEndDate,//"10/06/2020",
		}
		$.ajax({
			url : "getAverageFundForScheme",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				buildLocationWiseFundDetails(ajaxresp,divId);
			}
		});
	}
	function getAverageFundForAnyLevel(levelId,divId){
		var levelValues = [];
		var financialYrIdList = $('#financialYearId').val(); 
		var deptId = 0;
		var sourceId = 0;
		if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		}
		var json = {
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			fromDateStr : glStartDate,//"01/06/2013",       
			toDateStr : glEndDate,//"10/06/2020",   
		}
		$.ajax({
			url : "getAverageFundForAnyLevel",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				buildLocationWiseFundDetails(ajaxresp,divId);
			}
		});
	}


	
	function getAllDepartments(){
		$("#DepartmentsId").html('');
		var json = {

		}
		$.ajax({                
			type:'POST',    
			url: 'getAllDepartments',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			//$("#DepartmentsId").find('option').remove();
			$("#DepartmentsId").append("<option value='0'>All</option>");
			if(result != null && result.length >0){
				for(var i in result){
					$("#DepartmentsId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
				}
			}
			$("#DepartmentsId").val(0)
			$("#DepartmentsId").trigger("chosen:updated");
		});
	}
	function getAllFiniancialYears()
	{
		$("#financialYearId").html('');
		var json = {
		}
		$.ajax({                
			type:'POST',    
			url: 'getAllFiniancialYears',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			//$("#financialYearId").find('option').remove();
			$("#financialYearId").append("<option value='0'>All</option>");
			if(result != null && result.length >0){
				for(var i in result){
					$("#financialYearId").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
				}
				$("#financialYearId").val(["1", "2"]);
			}
			$("#financialYearId").chosen();
			$("#financialYearId").trigger('chosen:updated');
			onLoadCalls();	
		});
   }
   
    
	function getAllSubLocationsBySuperLocationId(locationScopeId){
		var financialYrIdList = $('#financialYearId').val();
		var deptIdsArr = $('#DepartmentsId').val();
		//var deptId = 0;
		var sourceId = 0;
		if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		}
		var json = {
		  superLocationId : locationScopeId, 
		  deptIdsList : deptIdsArr,
		  sourceId : sourceId,
		  financialYrIdList : financialYrIdList,  
		  fromDateStr : glStartDate,       
		  toDateStr : glEndDate  
		}
		$.ajax({
			url : "getAllSubLocationsBySuperLocationId",     
			data : JSON.stringify(json),
			type : "POST",  
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(result){   
				if(result !=null && result.length>0){
					$("#distLevelDistrictNames").append('<option value="0">SELECT DISTRICT</option>');
					$("#constLevelDistNames").append('<option value="0">SELECT DISTRICT</option>');
					for(var i in result){
						$("#distLevelDistrictNames").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
						$("#constLevelDistNames").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
			}
		});
	}
	$(document).on("change","#DepartmentsId,#dateRangePicker",function(){
			$(".switch-btn li").removeClass("active");
			$(".switch-btn li:first-child").addClass("active");
			onLoadCalls();
		});