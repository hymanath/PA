	//onLoadCalls();
	//onLoadClicks();
	onLoadInitialisations();
	 var globalLevelObj =  {"distLevelDistrictNames":"DISTRICT","constLevelDistNames":"DISTRICT","mandalLevelDistNames":"DISTRICT","constLevelConstNames":"CONSTITUENCY","mandalLevelConstNames":"CONSTITUENCY","mandalLevelMandalNames":"MANDAL"};
	var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
	var glEndDate = moment().add(10, 'years').endOf('year').format("DD/MM/YYYY");
	//var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	var spinner = '<h1 class="loader">  <span>L</span>  <span>O</span>  <span>A</span>  <span>D</span>  <span>I</span>  <span>N</span>  <span>G</span></h1>';
	///Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations
	function onLoadCalls()
	{
		//getLocationWiseAmountAndCountDetails(3,'locationsModal','overview');
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
		getAverageFundForAnyLevel(5,'avgFundMandal');
		getAverageFundForAnyLevel(6,'avgFundVillage');
		
		//onload overviewGraph building
		getLocationWiseAmountDetails(2,'stateLevlOvervw','overview','','',0);
		getLocationWiseAmountDetails(3,'distLevlOvervw','overview','name','desc',0);
		getLocationWiseAmountDetails(4,'consLevlOvervw','overview','name','desc',0);
		getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview','name','desc',0);
		
		//onload district Names building
		getAllSubLocationsBySuperLocationId(21,'distLevelDistrictNames',3);
		getAllSubLocationsBySuperLocationId(21,'constLevelDistNames',4);
		getAllSubLocationsBySuperLocationId(21,'mandalLevelDistNames',5);
		
		getSchemeWiseHighestAndLowestFund('highest','highFundScheme');
		getSchemeWiseHighestAndLowestFund('lowest','lowFundScheme');
		getTotalFunds('totFund');
		getTotalSchemes(4,'totFundScheme');
		// getSchemeWiseHighestAndLowestFund("highest");
		// getSchemeWiseHighestAndLowestFund("lowest");
		getTotalLocationsByScopeId(4,'totFundCons');
		 getTotalLocationsByScopeId(5,'totFundMandal');
		 getTotalLocationsByScopeId(6,'totFundVillage');
		
		getAverageFundForScheme('avgFundScheme');		
		compareFundsBetweenFinancialYears(3,'comparionDistLevlOvervwTable');
		compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable');
		compareFundsBetweenFinancialYears(5,'comparionMandalLevlOvervwTable');
		getGrantTypeHighestAndLowestFund('highest','highFundSource');
		getGrantTypeHighestAndLowestFund('lowest','lowFundSource');
	}
	function getSelectedType(){
		 var sortingType = ''; 
		 var orderType='';
		$('.sortingDivDistCls li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			  orderType=$(this).attr("attr_order_type");
			 }
		});
		return {
				sortingType:sortingType,
				orderType:orderType
		}
	}
	function getblockType(){
		 var blockType = ''; 
		$('.distLevelActive li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  blockType = $(this).attr("attr_type");
			 }
		});
		return blockType;
	}
	function getblockTypeCons(){
		 var blockTypeCons = ''; 
		$('.consLevelActive li').each(function(i, obj){
			 if($(this).hasClass('active')){
				blockTypeCons = $(this).attr("attr_type");
			 }
		});
		return blockTypeCons;
	}
	function getblockTypeMandal(){
		 var blockTypeMadal = ''; 
		$('.mandalLevelActive li').each(function(i, obj){
			 if($(this).hasClass('active')){
				blockTypeMadal = $(this).attr("attr_type");
			 }
		});
		return blockTypeMadal;
	}
	$(document).on("click","[tab-switch] li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var blockName = $(this).closest("ul").attr("tab-switch");
		var blockType = $(this).attr("attr_type");
		
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		
		if(blockName == 'consLevel')
		{
			$("#constLevelDistNames").val(0);
			$("#constLevelDistNames").trigger('chosen:updated');
			
			$("#constLevelConstNames").html('');
			$("#constLevelConstNames").append('<option value="0">SELECT CONSTITUENCY</option>');
			$("#constLevelConstNames").trigger('chosen:updated');
			
			if(blockType == 'overview')
			{
				$(".comaprisionViewShow").show();
				compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable')
				getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,0);
				$('#tabCons a[href="#consLevelGraph"]').trigger('click');
			}else if(blockType == 'scheme')
			{
				
				$(".comaprisionViewShow").hide();
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','scheme',sortingType,orderType,0,4);
				$('#tabCons a[href="#consLevelGraph"]').trigger('click');
			}else if(blockType == 'deptscheme'){
				$(".comaprisionViewShow").hide();
				getFinancialYearWiseDeptsWiseSchemeAmountDetails(4,'consLevlOvervw','deptscheme',sortingType,orderType,0,4);
				$('#tabCons a[href="#consLevelGraph"]').trigger('click');
			}
		}else if(blockName == 'distLevel')
		{
			$("#distLevelDistrictNames").val(0);
		    $("#distLevelDistrictNames").trigger('chosen:updated');
			if(blockType == 'overview')
			{
				$(".comaprisionViewShow").show();
				compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable')
				getLocationWiseAmountDetails(3,'distLevlOvervw','overview',sortingType,orderType,0);
				$('#tabDis a[href="#distLevelGraph"]').trigger('click');
			}else if(blockType == 'scheme')
			{
				$(".comaprisionViewShow").hide();
				getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','scheme',sortingType,orderType,0,3);
				$('#tabDis a[href="#distLevelGraph"]').trigger('click');
			}else if(blockType == 'deptscheme'){
				$(".comaprisionViewShow").hide();
				getFinancialYearWiseDeptsWiseSchemeAmountDetails(3,'distLevlOvervw','deptscheme',sortingType,orderType,0,3);
				$('#tabDis a[href="#distLevelGraph"]').trigger('click');
			}
		}else if(blockName == 'stateLevel')
		{
			if(blockType == 'overview')
			{
				getLocationWiseAmountDetails(2,'stateLevlOvervw','overview','','',0);
			}else if(blockType == 'scheme')
			{
				getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','scheme','','',0);
			}else if(blockType == 'deptscheme'){
				//getFinancialYearWiseDeptsWiseSchemeAmountDetails(2,'stateLevlOvervw','deptscheme')
			}
		}else if(blockName == 'mandalLevel'){
			$("#mandalLevelDistNames").val(0);
		    $("#mandalLevelDistNames").trigger('chosen:updated');
			
			$("#mandalLevelConstNames").html('');
			$("#mandalLevelConstNames").append('<option value="0">SELECT CONSTITUENCY</option>');
		    $("#mandalLevelConstNames").trigger('chosen:updated');
			
			$("#mandalLevelMandalNames").html('');
			$("#mandalLevelMandalNames").append('<option value="0">SELECT MANDAL</option>');
		    $("#mandalLevelMandalNames").trigger('chosen:updated');
			
			if(blockType == 'overview'){
				$(".comaprisionViewShow").show();
				compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable')
				getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,0);
				$('#tabMan a[href="#mandalLevelGraph"]').trigger('click');
			}else if(blockType == 'scheme'){
				$(".comaprisionViewShow").hide();
				getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','scheme',sortingType,orderType,0,5);
				$('#tabMan a[href="#mandalLevelGraph"]').trigger('click');
			}else if(blockType == 'deptscheme'){
				$(".comaprisionViewShow").hide();
				getFinancialYearWiseDeptsWiseSchemeAmountDetails(5,'mandalLevlOvervw','deptscheme',sortingType,orderType,0,5);
				$('#tabMan a[href="#mandalLevelGraph"]').trigger('click');
			}
		}
	});
	$(document).on('change','.constiLevelDistCls',function(){
		var locationScopeId = $("#constLevelDistNames").val();
	    getAllSubLocationsBySuperLocationId(locationScopeId,'constLevelConstNames',4);
	});
	$(document).on('change','.mandalLevelDistCls',function(){
		var locationScopeId = $("#mandalLevelDistNames").val();
	    getAllSubLocationsBySuperLocationId(locationScopeId,'mandalLevelConstNames',5);
	});
	$(document).on('change','.levelmandalConstiCls',function(){
		var locationScopeId = $("#mandalLevelConstNames").val();
	    getAllSubLocationsBySuperLocationId(locationScopeId,'mandalLevelMandalNames',5);
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
		
		
		getAllDepartments();
		getAllFiniancialYears();		
	}
	$(document).on("change","#financialYearId",function(){
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:first-child").addClass("active");
		onLoadCalls();
	});
		
	$("#dateRangePickerAUM").daterangepicker({
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
		   'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
		   'Last 3 Year': [moment().subtract(3, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	});
    var dates= $("#dateRangePickerAUM").val();
	var pickerDates = glStartDate+' - '+glEndDate
	if(dates == pickerDates)
	{
		$("#dateRangePickerAUM").val('All');
	}
	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		glStartDate = picker.startDate.format('DD/MM/YYYY')
		glEndDate = picker.endDate.format('DD/MM/YYYY')
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePickerAUM").val('All');
		}
		
		onLoadCalls();
	});
	
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
		/* if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		}	 */
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
			str+='<h4 class="panel-title text-capital district-name"> '+ajaxresp.name+'</h4>';//avgFundMandal
		}
		if(divId == 'avgFundDist' || divId == 'avgFundCons' || divId == 'avgFundScheme' || divId == 'avgFundMandal' || divId == 'avgFundVillage' ){
			str+='<h3><i class="fa fa-inr"></i>: '+ajaxresp.avrgeAmt+'<small class="text-success">'+ajaxresp.perc+' %</small></h3>';
		}
		else if(ajaxresp.totalAmt != null && ajaxresp.totalAmt != "0.0" && divId != 'avgFundDist' && divId != 'avgFundCons' &&  divId != 'avgFundScheme' && divId != 'avgFundMandal' && divId != 'avgFundVillage' )
		{
			str+='<h3><i class="fa fa-inr"></i>: '+ajaxresp.totalAmt+'<small class="text-success">'+ajaxresp.perc+' %</small></h3>';
		}
		
		/* if(ajaxresp.fundedLoc != null)
		{
			str+='<h3>'+ajaxresp.fundedLoc+'</h3>';
		} */
		if(ajaxresp.totSchemes != null)
		{
			str+='<h3>'+ajaxresp.totSchemes+'</h3>';
		}
		
		if(ajaxresp.subList !=null && ajaxresp.subList.length > 0)
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.subList)
				{
					str+='<li class="text-center">';
						str+='<p class="text-muted">'+ajaxresp.subList[i].name+'</p>';
						str+='<p class="text-success"><small>'+ajaxresp.subList[i].percentage+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.subList[i].totl+'</p>';
					str+='</li>';
				}
			str+='</ul>';
		}else if(ajaxresp.detailsVOs !=null && ajaxresp.detailsVOs.length > 0  && divId != 'avgFundDist' && divId != 'avgFundCons' &&  divId != 'avgFundScheme' && divId != 'avgFundMandal' && divId != 'avgFundVillage')
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.detailsVOs)
				{
					str+='<li class="text-center">';
						str+='<p class="text-muted">'+ajaxresp.detailsVOs[i].name+'</p>';
						str+='<p class="text-success"><small>'+ajaxresp.detailsVOs[i].perc+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.detailsVOs[i].avrgeAmt+'</p>';
					str+='</li>';
				}
			str+='</ul>';
		}else if(divId == 'avgFundDist' || divId == 'avgFundCons' || divId != 'avgFundScheme' || divId != 'avgFundMandal' || divId != 'avgFundVillage' )
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.detailsVOs)
				{
					str+='<li class="text-center">';
						str+='<p class="text-muted">'+ajaxresp.detailsVOs[i].name+'</p>';
						str+='<p class="text-success"><small>'+ajaxresp.detailsVOs[i].perc+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.detailsVOs[i].fundAvageAmt+'</p>';
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
	function getLocationWiseAmountDetails(levelId,divId,type,sortingType,orderType,locationId){
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdList = $('#financialYearId').val();
		/* if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		} */
		var json = {
			locationId :locationId,
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			sortingType : sortingType,      //name,count    
			order : orderType,   //asc,desc
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
									if(result[i].locationList1[j].count != null && result[i].locationList1[j].count > 0){
										table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+result[i].locationId+'" attr_financial_yr_id="'+result[i].locationList1[j].financialYearId+'" attr_scheme_id="0" attr_dept_id="0" style="cursor:pointer;color:green;">'+result[i].locationList1[j].count+'</td>';
									}else{
										table+='<td class="text-center no-right-border">-</td>';
									}
								
								if(result[i].locationList1[j].amount != null && result[i].locationList1[j].amount > 0){
									table+='<td class="text-center">'+parseInt(result[i].locationList1[j].amunt.replace(/,/g, ""))+'</td>';
								}else{
									table+='<td class="text-center">-</td>';
								}
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
						var lvlVal = 0;
							if(levelId == '2')
							{
								lvlVal =1;
								table+='<td>1</td>';
								table+='<td>Andhra Pradesh</td>';
							}else if(levelId == '3'){
								lvlVal =result[i].addressVO.districtId;
								table+='<td>'+result[i].addressVO.districtId+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
							}else if(levelId == '4')
							{
								lvlVal =result[i].addressVO.assemblyId;
								table+='<td>'+result[i].addressVO.assemblyId+'</td>';
								table+='<td>'+result[i].addressVO.assemblyName+'</td>';
							}else if(levelId == '5')
							{
								lvlVal =result[i].addressVO.id;
								table+='<td>'+result[i].addressVO.id+'</td>';
								table+='<td>'+result[i].addressVO.name+'</td>';
							}
							for(var j in result[i].subList){
								for(var k in result[i].subList[j].subList)
								{
									if(levelId != '2'){
									if(result[i].subList[j].subList[k].count != null && result[i].subList[j].subList[k].count > 0){
										table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+result[i].subList[j].yearId+'" attr_scheme_id="'+result[i].subList[j].subList[k].id+'" attr_dept_id="0" style="cursor:pointer;color:green;">'+result[i].subList[j].subList[k].count+'</td>';
									}else{
										table+='<td class="text-center no-right-border">-</td>';
									}
								}else{
									if(result[i].subList[j].subList[k].count != null && result[i].subList[j].subList[k].count > 0){
										table+='<td class="text-center no-right-border">'+result[i].subList[j].subList[k].count+'</td>';
									}else{
										table+='<td class="text-center no-right-border">-</td>';
									}
								}
									if(result[i].subList[j].subList[k].totalCount != null && result[i].subList[j].subList[k].totalCount > 0){
										table+='<td class="text-center">'+parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'</td>';
									}else{
										table+='<td class="text-center">-</td>';
									}
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
							var levlValId = 0;
								if(levelId == '2')
								{
									levlValId= 1;
									table+='<td>1</td>';
									table+='<td>Andhra Pradesh</td>';
								}else if(levelId == '3'){
									levlValId= result[i].addressVO.districtId;
									table+='<td>'+result[i].addressVO.districtId+'</td>';
									table+='<td>'+result[i].addressVO.districtName+'</td>';
								}else if(levelId == '4')
								{
									levlValId= result[i].addressVO.assemblyId;
									table+='<td>'+result[i].addressVO.assemblyId+'</td>';
									table+='<td>'+result[i].addressVO.assemblyName+'</td>';
								}else if(levelId == '5')
								{
									levlValId= result[i].addressVO.id;
									table+='<td>'+result[i].addressVO.id+'</td>';
									table+='<td>'+result[i].addressVO.name+'</td>';
								}
								
								for(var j in result[i].subList)
								{
									for(var k in result[i].subList[j].subList)
									{
										for(var l in result[i].subList[j].subList[k].subList)
										{
											if(result[i].subList[j].subList[k].subList[l].count != null && result[i].subList[j].subList[k].subList[l].count > 0){
												table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+levlValId+'" attr_financial_yr_id="'+result[i].subList[j].yearId+'" attr_dept_id="'+result[i].subList[j].subList[k].id+'" attr_scheme_id="'+result[i].subList[j].subList[k].subList[l].id+'" style="cursor:pointer;color:green;">'+result[i].subList[j].subList[k].subList[l].count+'</td>';
											}else{
												table+='<td class="text-center no-right-border">-</td>';
											}
											
											if(result[i].subList[j].subList[k].subList[l].totalCount != null && result[i].subList[j].subList[k].subList[l].totalCount > 0){
												table+='<td class="text-center">'+parseInt(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, ""))+'</td>';
											}else{
												table+='<td class="text-center">-</td>';
											}
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
									//mainlocationArr.push({y:result[i].locationList1[j].amount})
									mainlocationArr.push({y:parseInt(result[i].locationList1[j].amunt.replace(/,/g, ""))})
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
								
									//return '<span style="top:16px; position: absolute;"><br/><h3><i class="fa fa-inr"></i>: '+this.total+'</h3></span>';
									//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
									return '<span><i class="fa fa-inr"></i>: '+this.total+'</span>';
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
				
			}else if(levelId == 3 || levelId == 4 || levelId == 5){
				
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
									 //year1415Arr.push({"y":result[i].locationList1[j].amount}); 
									 year1415Arr.push({"y":parseInt(result[i].locationList1[j].amunt.replace(/,/g, ""))}); 
								}else if(result[i].locationList1[j].financialYearId==2){
									 //year1516Arr.push({"y":result[i].locationList1[j].amount}); 
									 year1516Arr.push({"y":parseInt(result[i].locationList1[j].amunt.replace(/,/g, ""))}); 
								}else if(result[i].locationList1[j].financialYearId==3){
									 //year1617Arr.push({"y":result[i].locationList1[j].amount}); 
									 year1617Arr.push({"y":parseInt(result[i].locationList1[j].amunt.replace(/,/g, ""))}); 
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
					var APDRPGRANTArr=[]; 
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
												//NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
												NABARDArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 //NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NREGPArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 //CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CRRArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 //RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 RDFArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 //MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MRRArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 6){
												 //FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
												 FC13Arr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 7){
												 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 APDRPGRANTArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
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
						if(APDRPGRANTArr != null && APDRPGRANTArr.length > 0){
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#6B6B6B"});  
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
			}else if(levelId == 3 || levelId == 4 || levelId == 5){
				
				if(result !=null && result.length>0){
				
					var assemblyShemeNameArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var CRRArr=[];
					var RDFArr=[];
					var MRRArr=[];
					var FC13Arr=[];
					var APDRPGRANTArr=[];
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
									}else if(levelId == 5){
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.name+"<br/>("+result[i].subList[j].year+")")
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
												// NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NABARDArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 //NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NREGPArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 //CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CRRArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 //RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 RDFArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 //MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MRRArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 6){
												//FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
												 FC13Arr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
											 }else if(result[i].subList[j].subList[k].id == 7){
												 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 APDRPGRANTArr.push({"y":parseInt(result[i].subList[j].subList[k].amount.replace(/,/g, ""))})
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
						if(APDRPGRANTArr != null && APDRPGRANTArr.length > 0){
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#6B6B6B"});  
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
			if(levelId == 3 || levelId == 4 || levelId == 5){
				if(result !=null && result.length>0){
				
					var assemblyShemeNameArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var CRRArr=[];
					var RDFArr=[];
					var MRRArr=[];
					var FC13Arr=[];
					var APDRPGRANTArr=[];
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
											var locationId;
											if(levelId == 3){
												 locationId= result[i].subList[j].subList[k].addressVO.id;
												assemblyShemeNameArr.push(result[i].subList[j].subList[k].addressVO.districtName+"<br/>("+result[i].subList[j].year+":"+result[i].subList[j].subList[k].name+")")
											}else if(levelId == 4){
												locationId= result[i].subList[j].subList[k].addressVO.id;
												assemblyShemeNameArr.push(result[i].subList[j].subList[k].addressVO.assemblyName+"<br/>("+result[i].subList[j].year+":"+result[i].subList[j].subList[k].name+")")
											}else if(levelId == 5){
												locationId= result[i].subList[j].subList[k].addressVO.id;
												assemblyShemeNameArr.push(result[i].subList[j].subList[k].addressVO.name+"<br/>("+result[i].subList[j].year+":"+result[i].subList[j].subList[k].name+")")
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
														//NABARDArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														NABARDArr.push({"y":parseInt(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 2){
														//NREGPArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 NREGPArr.push({"y":parseInt(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 3){
														 //CRRArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 CRRArr.push({"y":parseInt(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 4){
														 //RDFArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 RDFArr.push({"y":parseInt(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 5){
														//MRRArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 MRRArr.push({"y":parseInt(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 6){
														 //FC13Arr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 FC13Arr.push({"y":parseInt(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 7){
														 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 APDRPGRANTArr.push({"y":parseInt(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id})
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
						if(APDRPGRANTArr != null && APDRPGRANTArr.length > 0){
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#6B6B6B"});  
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
								},series: {
                                       cursor: 'pointer',
										point: {
							events: {
						click: function () {   
							var value = (this.appData).split("-");
							var blockLvlId = value[0];
							var levlValue = value[1];
							var financialYrId = value[2];
							var departmentId = value[3];
							var schmeId = value[4];
                    getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schmeId,departmentId);
                  }
                }
              }
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
		$("#"+divId).html(spinner);
		  var levelValues = [];
		  var financialYrIdArr =$('#financialYearId').val();
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr = $('#DepartmentsId').val();
		  var searchLevelId = 0;
		  var searchLevelVals = [];
		 // searchLevelVals.push(1);
		   /* if ($.inArray('0', financialYrIdArr) != -1)
			{
				var stringIds = "1,2,3";
				strx   = stringIds.split(',');
				financialYrIdArr = financialYrIdArr.concat(strx);
				financialYrIdArr.shift();
				
			} */
		  
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
	function getSchemeWiseLocationWiseAmountDetails(levelId,divId,type,sortingType,orderType,locationId,locationLevelType){
		$("#"+divId).html(spinner);
		var levelValues = [];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr = [];
		
		var financialYrIdArr = $('#financialYearId').val();
		/* if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		} */
		
		if(locationId == null || locationId == 0){
			 levelValues =[];
		 }else{
			 var tempIdStr=""+locationId;
			 var finalIdStr= tempIdStr.substring(1);
			 levelValues.push(finalIdStr);
		 }
		 
		
		var json = {
			searchLevelId:locationLevelType,
			blockLevelId : levelId, 
			levelValues : levelValues,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,			
			sortingType :sortingType,
			order :orderType		
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
	
	function getFinancialYearWiseDeptsWiseSchemeAmountDetails(levelId,divId,type,sortingType,orderType,locationId,locationLevelType){
		$("#"+divId).html(spinner);
		var levelValues = [];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		
		
		var deptIdsArr = $("#DepartmentsId").val();
		
		var financialYrIdArr = $('#financialYearId').val();
		/* if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		} */
		if(locationId == null || locationId == 0){
			 levelValues =[];
		 }else{
			 var tempIdStr=""+locationId;
			 var finalIdStr= tempIdStr.substring(1);
			 levelValues.push(finalIdStr);
		 }
		var json = {
			searchLevelId:locationLevelType,
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,			
			sortingType :sortingType,
			order :orderType		
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
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdArr = $('#financialYearId').val();
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr =  $('#DepartmentsId').val();
		var searchLevelId = 0;
		var searchLevelVals = [];
		//searchLevelVals.push(1);
		/* if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		} */
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
	
	function getSchemeWiseHighestAndLowestFund(type,divId)
	{
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdArr = $('#financialYearId').val();
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr =  $('#DepartmentsId').val();
		var searchLevelId = 3;
		var searchLevelVals = [];
		searchLevelVals.push(13);
		/* if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		} */
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
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdArr = $('#financialYearId').val();
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr =  $('#DepartmentsId').val();
		var searchLevelId = 0;
		var searchLevelVals = [];
		//searchLevelVals.push(13);
		/* if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		} */
		  var json = {
			blockLevelId : locScopeId, 
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
		$("#"+divId).html(spinner);
		var financialYrIdList = $('#financialYearId').val(); 
		var deptIdsArr = $('#DepartmentsId').val();
		//var deptId = 0;
		var sourceId = 0;
		var schemeId = 0;
		/* if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		} */
		
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
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdList = $('#financialYearId').val(); 
		var deptId = 0;
		var sourceId = 0;
		/* if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		} */
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
		$(".compMultiFinancialYear").html('');
		$(".compSingleFinancialYear").html('');
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
					$(".compMultiFinancialYear").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
					$(".compSingleFinancialYear").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
				}
				$("#financialYearId").val(0);
				$(".compMultiFinancialYear").val(['1','2']);
				$(".compSingleFinancialYear").val(3);
			}
			$("#financialYearId").chosen();
			$("#financialYearId").trigger('chosen:updated');
			$(".compMultiFinancialYear").trigger('chosen:updated');
			$(".compSingleFinancialYear").trigger('chosen:updated');
			onLoadCalls();	
		});
   }
   
    
	function getAllSubLocationsBySuperLocationId(locationScopeId,divId,levelId){
		$("#"+divId).html('');
		var financialYrIdList = $('#financialYearId').val();
		var deptIdsArr = $('#DepartmentsId').val();
		//var deptId = 0;
		var sourceId = 0;
		/* if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		} */
		var json = {
		   blockLevelId:levelId,
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
					 $("#"+divId).append('<option value="0">SELECT '+globalLevelObj[divId]+'</option>');
					for(var i in result){
						$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
				
				$("#"+divId).trigger('chosen:updated');
			}
		});
	}
	$(document).on("change","#DepartmentsId",function(){
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:first-child").addClass("active");
		onLoadCalls();
	});
	//District
	$(document).on("click",".sortingDivDistCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var blockType = getblockType();
		var locationId = 0;
		var locationLevelType = 3;
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(3,'distLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(3,'distLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
		
	});
	$(document).on("change",".distLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockType();
		var locationId =$("#distLevelDistrictNames").val();
		var locationLevelType = 3;
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(3,'distLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(3,'distLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
			
	});	
	//Constituency
	$(document).on("click",".sortingDivConstCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var blockType = getblockTypeCons();
		var locationId = 0;
		var locationLevelType = 4;
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(4,'consLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	});	
	
	$(document).on("change",".constiLevelDistCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeCons();
		var locationId =$("#constLevelDistNames").val();
		var locationLevelType = 3;
		if(locationId == 0){
			var locationLevelType = 4;
		}
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(4,'consLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".constiLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeCons();
		var locationId =$("#constLevelConstNames").val();
		var locationLevelType = 4;
		if(locationId == 0){
			var locationId =$("#constLevelDistNames").val();
			var locationLevelType = 3;
		}
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(4,'consLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	});
	
	//Mandal
	$(document).on("click",".sortingDivMandalCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var blockType = getblockTypeMandal();
		var locationId = 0;
		var locationLevelType = 5;
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(5,'mandalLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	});	
	$(document).on("change",".mandalLevelDistCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var locationId =$("#mandalLevelDistNames").val();
		var locationLevelType = 3;
		if(locationId == 0){
			var locationLevelType = 5;
		}
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(5,'mandalLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".levelmandalConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var locationId =$("#mandalLevelConstNames").val();
		var locationLevelType = 4;
		if(locationId == 0){
			var locationId =$("#mandalLevelDistNames").val();
			var locationLevelType = 3;
		}
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(5,'mandalLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".mandalLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var locationId =$("#mandalLevelMandalNames").val();
		var locationLevelType = 5;
		if(locationId == 0){
			var locationId =$("#mandalLevelConstNames").val();
			var locationLevelType = 4;
		}
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId);
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(5,'mandalLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
		function getLocationWiseAmountAndCountDetails(levelId,divId,type){
			$("#"+divId).html("");
		//$("#"+divId).html(spinner);
		var levelValues = [];
		levelValues.push(11);
		levelValues.push(12);
		levelValues.push(13);
		levelValues.push(14);
		var financialYrIdList = $('#financialYearId').val();
		/* if ($.inArray('0', financialYrIdList) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdList = financialYrIdList.concat(strx);
			financialYrIdList.shift();
			
		} */
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
			url : "getLocationWiseAmountAndCountDetails",  
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
					$("#locDivModal").modal('show');
					buildLocationsAmountDetailsOverview(ajaxresp,divId,type,levelId);
				}else{
					$("#"+divId).html("NO DATA AVAILABLE");
				}
			}
		});
	}
	
	function buildLocationsAmountDetailsOverview(result,divId,type,levelId){
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
							table+='<th colspan="2" class="text-center text-capital">'+result[0].locationList1[i].financialYear+'</th>';
						}
					table+='</tr>';
					table+='<tr>';
						table+='<th class="text-capital">ID</th>';
						if(levelId == '2')
						{
							table+='<th class="text-capital">State</th>';
						}else if(levelId == '3'){
							table+='<th class="text-capital">District</th>';
						}else if(levelId == '4')
						{
							table+='<th class="text-capital">Constituency</th>';
						}else if(levelId == '5')
						{
							table+='<th class="text-capital">Mandal</th>';
						}
						
						for(var i in result[0].locationList1)
						{
							table+='<th class="text-center no-right-border text-capital">No</th>';
							table+='<th class="text-center text-capital">Amt.</th>';
						}
					table+='</tr>';
				table+='</thead>';
				table+='<tbody>';
					for(var i in result){
						table+='<tr>';
							table+='<td>'+result[i].locationId+'</td>';
							table+='<td>'+result[i].locationName+'</td>';
							for(var j in result[i].locationList1){
								if(result[i].locationList1[j].count != null && result[i].locationList1[j].count > 0){
									table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+result[i].locationId+'" attr_financial_yr_id="'+result[i].locationList1[j].financialYearId+'" attr_scheme_id="0" attr_dept_id="0" style="cursor:pointer;color:green;">'+result[i].locationList1[j].count+'</td>';
								}else{
									table+='<td class="text-center no-right-border">-</td>';
								}
								table+='<td class="text-center">'+result[i].locationList1[j].amount+'</td>';
							}
						table+='</tr>';
					}
					
				table+='</tbody>';
			table+='</table>';
		}
		
		$("#"+divId).html(table);
		$("#dataTable"+divId).dataTable();
	}

function getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,deptId){ 
     
	$("#fundSanctionModal").html("");
    var searchLvlVals = [];
	searchLvlVals.push(levlValue);
    var financialYrIdList =[];
	financialYrIdList.push(financialYrId);
    var schemeIdsList = [];
	schemeIdsList.push(schemeId);
    var deptIdsList = [];
	deptIdsList.push(deptId);
    
    var json = {
          blockLevelId : blockLvlId, 
          searchLvlVals : searchLvlVals,
          financialYrIdList : financialYrIdList,
          schemeIdsList : schemeIdsList,  
          deptIdsList : deptIdsList,       
          fromDateStr : glStartDate,
          toDateStr : glEndDate
        }
    $.ajax({ 
      url: 'getLocationWiseFundSanctionDetails', 
      type:'POST',  
      data : JSON.stringify(json),
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },
            
    }).done(function(result){
      if(result != null && result.length>0){
		  $("#fundModal").modal('show');
      buildLocationWiseFundSanctionDetails(result);
    }
    });
  }
  function buildLocationWiseFundSanctionDetails(result){
  
  var str = '';
  str+='<table class="table table-condensed" id="tableId">';
  str+='<thead>';
    str+='<th class="text-capital">Location Name</th>';
    str+='<th class="text-capital">Work Name</th>';
    str+='<th class="text-capital">Department</th>'; 
    str+='<th class="text-capital" > Scheme Name</th>';
    str+='<th class="text-capital"> G.o</th>';
    str+='<th class="text-capital"> Sanction Amount</th>';
  str+='</thead>';
  str+='<tbody>';
  for(var i in result){
    str+='<tr>'; 
    if(result[i].locationName != null){
         str+='<td>'+result[i].locationName+'</td>';
    }else{
         str+='<td>-</td>';
    }
    if(result[i].workName != null){
         str+='<td>'+result[i].workName+'</td>';
    }else{
         str+='<td>-</td>';
    }
       if(result[i].departmentName != null){
         str+='<td>'+result[i].departmentName+'</td>';
    }else{
         str+='<td>-</td>';
    }    
    if(result[i].schemeName != null){
         str+='<td>'+result[i].schemeName+'</td>';
    }else{
         str+='<td>-</td>';
    }  
    if(result[i].goNoDate != null){
         str+='<td>'+result[i].goNoDate+'</td>';
    }else{
         str+='<td>-</td>';
    }
    if(result[i].sactionAmount != null){
         str+='<td>'+result[i].sactionAmount+'</td>';
    }else{
         str+='<td>-</td>';
    }
    str+='</tr>';     
  }
  str+='</tbody>';  
  
  $("#fundSanctionModal").html(str);   
  $("#tableId").dataTable();    
}

$(document).on("click",".fundSanctionCls",function(){
 var blockLvlId = $(this).attr("attr_scope_id");
  var levlValue = $(this).attr("attr_level_value");
  var financialYrId =$(this).attr("attr_financial_yr_id");
  var schemeId = $(this).attr("attr_scheme_id");
  var deptId = $(this).attr("attr_dept_id");
  getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,deptId);
});
$(document).on("click",".comapreFinancialYearCls",function(){
	var type = $(this).attr("attr_type");
	if(type == "distComp"){
		compareFundsBetweenFinancialYears(3,'comparionDistLevlOvervwTable');
	}else if(type == "constComp"){
		compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable');
	}else if(type == "mandalComp"){
		compareFundsBetweenFinancialYears(5,'comparionMandalLevlOvervwTable');
	}
	
});

function compareFundsBetweenFinancialYears(levelId,divId){ 
	
	
	
	var multipleFinYear='';
	var singleFinYear='';
	var singleFinYearArr=[];
	var financialYrIdList='';
	
	if(levelId == 3){
		var disValue = $(".compMultiFinancialYear").val();
		multipleFinYear = $(".compMultiFinancialYear").val();
		singleFinYear = $(".compSingleFinancialYear").val();
		singleFinYearArr.push(singleFinYear);
		financialYrIdList = multipleFinYear;
		financialYrIdList.push(singleFinYear)
		
		if(disValue.length == 3){
		 alert("Please Select Two Financial Years");
		 return;
	 }
	 
	}else if(levelId == 4){
		var consValue = $(".compMultiFinancialYear").val();
		multipleFinYear = $(".compMultiFinancialYear").val();
		singleFinYear = $(".compSingleFinancialYear").val();
		singleFinYearArr.push(singleFinYear);
		financialYrIdList = multipleFinYear;
		financialYrIdList.push(singleFinYear)
		if(consValue.length == 3){
		 alert("Please Select Two Financial Years");
		 return;
	 }
	
	 
	}else if(levelId == 5){
		var mandValue = $(".compMultiFinancialYear").val();
		multipleFinYear = $(".compMultiFinancialYear").val();
		singleFinYear = $(".compSingleFinancialYear").val();
		singleFinYearArr.push(singleFinYear);
		financialYrIdList = multipleFinYear;
		financialYrIdList.push(singleFinYear)
		if(mandValue.length == 3){
		 alert("Please Select Two Financial Years");
		 return;
	 }
	 
	}
	 
	 
	 
	 $("#"+divId).html(spinner);
    var json = {
      blockLevelId : levelId,
      financialYrIdList : financialYrIdList
    }
    $.ajax({
      url : "compareFundsBetweenFinancialYears",     
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		
		buildCompareFundsBetweenFinancialYears(ajaxresp,financialYrIdList,multipleFinYear,singleFinYearArr,divId);
      },
      error : function(request,error){
      alert(error);
      }
    });
   }
   
   function buildCompareFundsBetweenFinancialYears(result,financialYrIdList,multipleFinYear,singleFinYearArr,divId){
	   
	   var yearLen =financialYrIdList.length;
	    var globalYearObj =  {"1":"2014-2015","2":"2015-2016","3":"2016-2017"};
	   if(yearLen >=3){
			$("#"+divId).removeClass('comparionStyle1')
			$("#"+divId).addClass('comparionStyle')
		 }else{
			 $("#"+divId).removeClass('comparionStyle')
			 $("#"+divId).addClass('comparionStyle1')
		 }
	   var str='';
	   if(result !=null && result.length>0){
		    str+='<table class="table table-bordered">';
		   if(result[0].rangeList !=null && result[0].rangeList.length>0){
			    str+='<thead>';
				str+='<tr>';
				 if(yearLen >=3){
					 str+='<th style="background-color:#EAEBFF">'+globalYearObj[multipleFinYear[1]]+'</th>';
					str+='<th><span class="first" style="background-color:#FFF3E9">'+globalYearObj[multipleFinYear[0]]+'</span><span class="second"> '+globalYearObj[singleFinYearArr]+'</span></th>';
				 }else{
					 str+='<th><span class="first"> '+globalYearObj[multipleFinYear[0]]+'</span><span class="second"> '+globalYearObj[singleFinYearArr]+'</span></th>';
				 }
			   for(var i in result[0].rangeList){
					   if(yearLen >=3){
							str+='<th colspan="2">'+result[0].rangeList[i].name+'</th>'
						}else{
							str+='<th>'+result[0].rangeList[i].name+'</th>'
						}
				}
			   str+='</tr>';
			   str+='</thead>';
			   str+='<tbody>';
				for(var i in result){
				   var verticalRanges = result[i].range.split(',');
				   str+='<tr>';
						if(yearLen >=3){
							str+='<td style="background-color:#EAEBFF">'+verticalRanges[0]+'</td>';
							str+='<td style="background-color:#FFF3E9">'+verticalRanges[1]+'</td>';
						}else{
							  str+='<td>'+result[i].range+'</td>';
						}
					  if(result[i].rangeList !=null && result[i].rangeList.length>0){
							for(var j in result[i].rangeList){
								var rangeValue='';
								if(result[i].rangeList[j].value == null || result[i].rangeList[j].value == "0,0"){
									var emptyRangeVal = "-,-";
									rangeValue = emptyRangeVal.split(',');
								}else{
									rangeValue = result[i].rangeList[j].value.split(',');
								}
								if(yearLen >=3){
									str+='<td style="background-color:#EAEBFF">'+rangeValue[0]+'</td>';
									 str+='<td style="background-color:#FFF3E9">'+rangeValue[1]+'</td>';
								}else{
									 str+='<td>'+result[i].rangeList[j].value+'</td>';
								}
							}
							 
							
						}
					
					str+='</tr>';
				}
				
			   str+='</tbody>';
		   }
			str+='</table>';
	   }
	   $("#"+divId).html(str)
	   
   }
   
   
   //getMinMaxDates();
	function getMinMaxDates()
	{ 
		$.ajax({                
			type:'POST',    
			url: 'getMinMaxDates',
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			glStartDate = result.minDate;
			glEndDate = result.maxDate;
			onLoadCalls();
		});
   }
   
   function getGrantTypeHighestAndLowestFund(type,divId)
	{
		$("#"+divId).html(spinner);
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
			url: 'getGrantTypeHighestAndLowestFund',
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