	onLoadInitialisations();
	var globalLevelObj =  {"distLevelDistrictNames":"DISTRICT","constLevelDistNames":"DISTRICT","mandalLevelDistNames":"DISTRICT","constLevelConstNames":"CONSTITUENCY","mandalLevelConstNames":"CONSTITUENCY","mandalLevelMandalNames":"MANDAL","villageLevelDistNames":"DISTRICT",'villageLevelConstNames':'CONSTITUENCY','villageLevelMandalNames':'MANDAL','villageLevelNames':'VILLAGE','constLevelParliaNames':'PARLIAMENT','parliamentLevelConstNames':'PARLIAMENT','villageLeveParliNames':'PARLIAMENT'};
	var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
	var glEndDate = moment().add(10, 'years').endOf('year').format("DD/MM/YYYY");
	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	var $windowWidth = $(window).width();
	///Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations
	
	function onLoadCalls()
	{
		getLocationWiseFundDetails(3,'highest','highFundDist',0,0);
		getLocationWiseFundDetails(4,'highest','highFundCons',0,0);
		getLocationWiseFundDetails(3,'lowest','lowFundDist',0,0);
		getLocationWiseFundDetails(4,'lowest','lowFundCons',0,0);
		getLocationWiseFundDetails(5,'highest','highFundMandal',0,0);
		getLocationWiseFundDetails(6,'highest','highFundVillage',0,0);
		getLocationWiseFundDetails(5,'lowest','lowFundMandal',0,0);
		getLocationWiseFundDetails(6,'lowest','lowFundVillage',0,0);
		getLocationWiseFundDetails(10,'lowest','lowFundParliament',0,0);
		getLocationWiseFundDetails(10,'highest','highFundParlmnt',0,0);
		getAverageFundForAnyLevel(3,'avgFundDist',0,0);
		getAverageFundForAnyLevel(4,'avgFundCons',0,0);
		getAverageFundForAnyLevel(5,'avgFundMandal',0,0);
		getAverageFundForAnyLevel(6,'avgFundVillage',0,0);
		getAverageFundForAnyLevel(10,'avgFundparlment',0,0);
		
		//onload overviewGraph building
		 getLocationWiseAmountDetails(2,'stateLevlOvervw','overview','','',0,'onLoad');
		getLocationWiseAmountDetails(3,'distLevlOvervw','overview','count','desc',0,'onLoad');
		getLocationWiseAmountDetails(4,'consLevlOvervw','overview','count','desc',0,'onLoad');
		getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview','count','desc',0,'onLoad');
		getLocationWiseAmountDetails(6,'villageLevlOvervw','overview','count','desc',0,'onLoad');
		
		//onload Parliament Names building
		getAllSubLocationsBySuperLocationId(30,'constLevelParliaNames',4);
		getAllSubLocationsBySuperLocationId(30,'parliamentLevelConstNames',5);
		getAllSubLocationsBySuperLocationId(30,'villageLeveParliNames',6);
		
		//onload district Names building
		getAllSubLocationsBySuperLocationId(21,'distLevelDistrictNames',3);
		getAllSubLocationsBySuperLocationId(21,'constLevelDistNames',4);
		getAllSubLocationsBySuperLocationId(21,'mandalLevelDistNames',5);
		getAllSubLocationsBySuperLocationId(21,'villageLevelDistNames',6);
		
		getSchemeWiseHighestAndLowestFund('highest','highFundScheme',0,0,3);
		getSchemeWiseHighestAndLowestFund('lowest','lowFundScheme',0,0,3);
		getTotalFunds('totFund');
		getTotalSchemes(4,'totFundScheme','scheme',0,'');
		getTotalSchemes(4,'totFundGrant','grant',0,'');
		// getSchemeWiseHighestAndLowestFund("highest");
		// getSchemeWiseHighestAndLowestFund("lowest");
		getTotalLocationsByScopeId(4,'totFundCons');
		getTotalLocationsByScopeId(5,'totFundMandal');
		getTotalLocationsByScopeId(6,'totFundVillage');
		getTotalLocationsByScopeId(10,'totFundParliament');
		
		getAverageFundForScheme('avgFundScheme','scheme',0,0);
		getAverageFundForScheme('avgFundGrant','grant',0,0);
		compareFundsBetweenFinancialYears(3,'comparionDistLevlOvervwTable');
		compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable');
		compareFundsBetweenFinancialYears(5,'comparionMandalLevlOvervwTable');
		getGrantTypeHighestAndLowestFund('highest','highFundSource');
		getGrantTypeHighestAndLowestFund('lowest','lowFundSource');
		//getAllSubLocations('distNames',3);
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
	function getblockTypeVillage(){
		 var blockTypeVillage = ''; 
		$('.villageLevelActive li').each(function(i, obj){
			 if($(this).hasClass('active')){
				blockTypeVillage = $(this).attr("attr_type");
			 }
		});
		return blockTypeVillage;
	}
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	$(document).on('cut copy paste', function (e) {
		e.preventDefault();
	});
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
			
			$("#constLevelParliaNames").html('');
			$("#constLevelParliaNames").append('<option value="0">SELECT PARLIAMENT</option>');
			$("#constLevelParliaNames").trigger('chosen:updated');
			
			if(blockType == 'overview')
			{
				$(".comaprisionViewShow").show();
				compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable')
				getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,0,'onLoad');
				$('#tabCons a[href="#consLevelTable"]').trigger('click');				
			}else if(blockType == 'scheme')
			{
			
				$(".comaprisionViewShow").hide();
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','scheme',sortingType,orderType,0,4);
				$('#tabCons a[href="#consLevelTable"]').trigger('click');
			}else if(blockType == 'deptscheme'){
				$(".comaprisionViewShow").hide();
				getFinancialYearWiseDeptsWiseSchemeAmountDetails(4,'consLevlOvervw','deptscheme',sortingType,orderType,0,4);
				$('#tabCons a[href="#consLevelTable"]').trigger('click');
			}
		}else if(blockName == 'distLevel')
		{
			$("#distLevelDistrictNames").val(0);
		    $("#distLevelDistrictNames").trigger('chosen:updated');
			if(blockType == 'overview')
			{
				$(".comaprisionViewShow").show();
				compareFundsBetweenFinancialYears(4,'comparionDistLevlOvervwTable')
				getLocationWiseAmountDetails(3,'distLevlOvervw','overview',sortingType,orderType,0,'onLoad');
				$('#tabDis a[href="#distLevelTable"]').trigger('click');
			}else if(blockType == 'scheme')
			{
				$(".comaprisionViewShow").hide();
				getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','scheme',sortingType,orderType,0,3);
				$('#tabDis a[href="#distLevelTable"]').trigger('click');
			}else if(blockType == 'deptscheme'){
				$(".comaprisionViewShow").hide();
				getFinancialYearWiseDeptsWiseSchemeAmountDetails(3,'distLevlOvervw','deptscheme',sortingType,orderType,0,3);
				$('#tabDis a[href="#distLevelTable"]').trigger('click');
			}
		}else if(blockName == 'stateLevel')
		{
			if(blockType == 'overview')
			{
				getLocationWiseAmountDetails(2,'stateLevlOvervw','overview','','',0,'onLoad');
				$('#tabSt a[href="#stateLevelTable"]').trigger('click');
			}else if(blockType == 'scheme')
			{
				getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','scheme','','',0,0);
				$('#tabSt a[href="#stateLevelTable"]').trigger('click');
			}else if(blockType == 'deptscheme'){
				getFinancialYearWiseDeptsWiseSchemeAmountDetails(2,'stateLevlOvervw','deptscheme','','',0,0)
				$('#tabSt a[href="#stateLevelTable"]').trigger('click');
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
			
			$("#parliamentLevelConstNames").html(''); 
			$("#parliamentLevelConstNames").append('<option value="0">SELECT PARLIAMENT</option>');
		    $("#parliamentLevelConstNames").trigger('chosen:updated');
			
			if(blockType == 'overview'){
				$(".comaprisionViewShow").show();
				compareFundsBetweenFinancialYears(5,'comparionMandalLevlOvervwTable')
				getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,0,'onLoad');
				$('#tabMan a[href="#mandalLevelTable"]').trigger('click');
			}else if(blockType == 'scheme'){
				$(".comaprisionViewShow").hide();
				getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','scheme',sortingType,orderType,0,5);
				$('#tabMan a[href="#mandalLevelTable"]').trigger('click');
			}else if(blockType == 'deptscheme'){
				$(".comaprisionViewShow").hide();
				getFinancialYearWiseDeptsWiseSchemeAmountDetails(5,'mandalLevlOvervw','deptscheme',sortingType,orderType,0,5);
				$('#tabMan a[href="#mandalLevelTable"]').trigger('click');
			}
		}else if(blockName == 'villageLevel'){
			$("#villageLevelDistNames").val(0);
		    $("#villageLevelDistNames").trigger('chosen:updated');
			
			$("#villageLevelConstNames").html('');
			$("#villageLevelConstNames").append('<option value="0">SELECT CONSTITUENCY</option>');
		    $("#villageLevelConstNames").trigger('chosen:updated');
			
			$("#villageLevelMandalNames").html('');
			$("#villageLevelMandalNames").append('<option value="0">SELECT MANDAL</option>');
		    $("#villageLevelMandalNames").trigger('chosen:updated');
			
			$("#villageLevelNames").html('');
			$("#villageLevelNames").append('<option value="0">SELECT VILLAGE</option>');
		    $("#villageLevelNames").trigger('chosen:updated');
			
			$("#villageLeveParliNames").html(''); 
			$("#villageLeveParliNames").append('<option value="0">SELECT PARLIAMENT</option>');
		    $("#villageLeveParliNames").trigger('chosen:updated');
			
			if(blockType == 'overview'){
				$(".comaprisionViewShow").show();
				compareFundsBetweenFinancialYears(6,'comparionVillageLevlOvervwTable')
				getLocationWiseAmountDetails(6,'villageLevlOvervw','overview',sortingType,orderType,0,'onLoad');
				$('#tabVill a[href="#villageLevelTable"]').trigger('click');
			}else if(blockType == 'scheme'){
				$(".comaprisionViewShow").hide();
				getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','scheme',sortingType,orderType,0,6);
				$('#tabVill a[href="#villageLevelTable"]').trigger('click');
			}else if(blockType == 'deptscheme'){
				$(".comaprisionViewShow").hide();
				getFinancialYearWiseDeptsWiseSchemeAmountDetails(6,'villageLevlOvervw','deptscheme',sortingType,orderType,0,6);
				$('#tabVill a[href="#villageLevelTable"]').trigger('click');
			}
		}
	});
	
	
	/*$(document).on('change','.levelmandalConstiCls',function(){
		var locationScopeId = $("#mandalLevelConstNames").val();
	    getAllSubLocationsBySuperLocationId(locationScopeId,'mandalLevelMandalNames',5);
	});*/
	
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
		var width = $(window).width()
		if(width > 767)
		{
			header = $('header section'),
			$(window).scroll(function(){
				var windowScrollTop = $(window).scrollTop();

				if (windowScrollTop>50) {
					header.addClass("header-fixed");
				} else {
					header.removeClass("header-fixed");
				}
			});
		}
		$(".chosenSelect").chosen();
		
		
		getAllDepartments();
		getAllFiniancialYears();		
	}
	
	var financialArrGlob=[];
	financialArrGlob.push("0");
	//console.log("glob -- "+financialArrGlob);
	$(document).on("change","#financialYearId",function(){//ara
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:first-child").addClass("active");
		$('[role="tablist"] li:first-child a').trigger('click');
		$('#tabVill a[href="#villageLevelTable"]').trigger('click');
		$('#tabMan a[href="#mandalLevelTable"]').trigger('click');
		$('#tabSt a[href="#stateLevelTable"]').trigger('click');
		$('#tabDis a[href="#distLevelTable"]').trigger('click');
		$('#tabCons a[href="#consLevelTable"]').trigger('click');	
		var values = $(this).val();//debugger;
		
		if(values != null && values.length > 0){
			for(var i=0; i<values.length; i++) {
			//console.log(values[i]+" -- "+financialArrGlob+" -- "+$.inArray(values[i], financialArrGlob));
				if($.inArray(values[i], financialArrGlob) == -1){
					if(values[i] == 0){values=[];values.push("0");
						$('#financialYearId').find($('option')).attr('selected',false)
						$("#financialYearId").val(0);
						$("#financialYearId").trigger('chosen:updated');
						financialArrGlob = [];
						financialArrGlob.push("0");
					}else{
						$('#financialYearId option:selected').each(function (index, option) { 
							if($(this).val()==0){
								$(option).attr('selected',false); 
								$("#financialYearId").trigger('chosen:updated');
							}
							financialArrGlob=[];
							financialArrGlob.push($(this).val());
						});
					}
				}
				
			 }
			 
		 onLoadCalls();
		}console.log(financialArrGlob +" ---- "+ values);
		//financialArrGlob = values;
		
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
		
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:first-child").addClass("active");
		$('[role="tablist"] li:first-child a').trigger('click');
		$('#tabCons a[href="#consLevelGraph"]').trigger('click');
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
	function getLocationWiseFundDetails(blockLevelId,dataType,divId,locationId,levelId)
	{
		
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdArr = $('#financialYearId').val();
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr = $('#DepartmentsId').val();  
		var searchLevelVals = [];
		if(locationId == null || locationId == 0){
			 searchLevelVals =[];
		}else{
			 var tempIdStr=""+locationId;
			 var finalIdStr= tempIdStr.substring(1);
			 searchLevelVals.push(finalIdStr);
		}
		
		//searchLevelVals.push(locationId);
		/* if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		}	 */
	
		  var json = {
			blockLevelId : blockLevelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,
			type:dataType,
			searchLevelId:levelId,
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
		attrDivId = $("#"+divId).attr("attr_id");
		if(ajaxresp.name != null)
		{
			str+='<h4 class="panel-title text-capital district-name"> '+ajaxresp.name+'</h4>';//avgFundMandal
		}
		if(attrDivId == 'avgFundDist' || attrDivId == 'avgFundCons' || attrDivId == 'avgFundScheme' || attrDivId == 'avgFundMandal' || attrDivId == 'avgFundVillage' || attrDivId == 'avgFundGrant' || attrDivId == 'avgFundparlment' ){
			if(ajaxresp.avrgeAmt != 0 && ajaxresp.avrgeAmt != undefined ){
			str+='<h3><i class="fa fa-inr"></i>: '+ajaxresp.avrgeAmt+'<small class="text-success">'+ajaxresp.perc+' %</small></h3>';
			}
		}
		else if(ajaxresp.totalAmt != null && ajaxresp.totalAmt != "0.0" && attrDivId != 'avgFundDist' && attrDivId != 'avgFundCons' &&  attrDivId != 'avgFundScheme' && attrDivId != 'avgFundMandal' && divId != 'avgFundVillage'  && attrDivId != 'avgFundGrant' &&  attrDivId != 'avgFundparlment')
		{
			if(ajaxresp.totalAmt != 0 && ajaxresp.totalAmt != undefined ){
			str+='<h3><i class="fa fa-inr"></i>: '+ajaxresp.totalAmt+'<small class="text-success">'+ajaxresp.perc+' %</small></h3>';
			}
		}
		
		/* if(ajaxresp.fundedLoc != null)
		{
			str+='<h3>'+ajaxresp.fundedLoc+'</h3>';
		} */
		if(ajaxresp.totSchemes != null && ajaxresp.totSchemes != 0)
		{
			str+='<h3>'+ajaxresp.totSchemes+'</h3>';
		}
		
		if(ajaxresp.subList !=null && ajaxresp.subList.length > 0)
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.subList)
				{
					 if(ajaxresp.subList[i].percentage != 0){
					str+='<li class="text-center">';
						str+='<p class="text-muted"><strong>'+ajaxresp.subList[i].name+'</strong></p>';
						str+='<p class="text-success"><small>'+ajaxresp.subList[i].percentage+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.subList[i].totl+'</p>';
					str+='</li>';
					 }
				}
			str+='</ul>';
		}else if(ajaxresp.detailsVOs !=null && ajaxresp.detailsVOs.length > 0  && attrDivId != 'avgFundDist' && attrDivId != 'avgFundCons' &&  attrDivId != 'avgFundScheme' && attrDivId != 'avgFundMandal' && attrDivId != 'avgFundVillage' &&attrDivId != 'avgFundparlment')
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.detailsVOs)
				{
					 if(ajaxresp.detailsVOs[i].perc != 0){
					str+='<li class="text-center">';
						str+='<p class="text-muted"><strong>'+ajaxresp.detailsVOs[i].name+'</strong></p>';
						str+='<p class="text-success"><small>'+ajaxresp.detailsVOs[i].perc+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.detailsVOs[i].avrgeAmt+'</p>';
					str+='</li>';
					 }
				}
			str+='</ul>';
		}else if(attrDivId == 'avgFundDist' || attrDivId == 'avgFundCons' || attrDivId != 'avgFundScheme' || attrDivId != 'avgFundMandal' || attrDivId != 'avgFundVillage'  || attrDivId != 'avgFundparlment')
		{
			str+='<ul class="list-inline">';
				for(var i in ajaxresp.detailsVOs)
				{
					if(ajaxresp.detailsVOs[i].perc != 0){
					str+='<li class="text-center">';
						str+='<p class="text-muted"><strong>'+ajaxresp.detailsVOs[i].name+'</strong></p>';
						str+='<p class="text-success"><small>'+ajaxresp.detailsVOs[i].perc+'%</small></p>';
						str+='<p class="panel-title" style="font-size:14px;"><i class="fa fa-inr"></i>: '+ajaxresp.detailsVOs[i].fundAvageAmt+'</p>';
					str+='</li>';
					}
				}
			str+='</ul>';
		}
		if(ajaxresp.notFundedLoc != null)
		{
			str+='<ul class="list-inline">';
			if(ajaxresp.fundedPerc != 0){
				str+='<li class="text-center">';
					str+='<p class="text-muted"><strong>FUNDED</strong></p>';
					str+='<p>'+ajaxresp.fundedLoc+'</p>';
					str+='<p class="text-success"><small>'+ajaxresp.fundedPerc+'%</small></p>';
				str+='</li>';
			 }
			 if(ajaxresp.nonFundedPerc != 0){
				str+='<li class="text-center">';
					str+='<p class="text-muted"><strong>NOT-FUNDED</strong></p>';
					str+='<p class="text-muted">'+ajaxresp.notFundedLoc+'</p>';
					str+='<p class="text-success"><small>'+ajaxresp.nonFundedPerc+'%</small></p>';
				str+='</li>';
			 }
			str+='</ul>';
		}
		
		
		
		$("#"+divId).html(str);
	}
	function getLocationWiseAmountDetails(levelId,divId,type,sortingType,orderType,locationId,displayType,locationName){
		$("#"+divId).html(spinner);
		$("#"+divId+"Table").html(spinner);
		var levelValues = [];
		var financialYrIdList = $('#financialYearId').val();
		var deptIdsArr = $('#DepartmentsId').val();
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
			deptIdsList:deptIdsArr
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
					buildLocationWiseAmountDetails(ajaxresp,divId,type,levelId,displayType,locationId,locationName);
				}else{
					$("#"+divId).html("NO DATA AVAILABLE");
					$("#"+divId+"Table").html("NO DATA AVAILABLE");
				}
			}
		});
	}
	function buildLocationWiseAmountDetails(result,divId,type,levelId,displayType,locationId,locationName)//ara
	{
		$("#"+divId).html("");
		var str='';
		var districtName =$("#distLevelDistrictNames").find('option:selected').text();
		
		var constLevelDistName =$("#constLevelDistNames").find('option:selected').text();
		var constLevelConstName = $("#constLevelConstNames").find('option:selected').text();
		var constLevelParliamentName = $("#constLevelParliaNames").find('option:selected').text();
		
		var mandalLevelDistName =$("#mandalLevelDistNames").find('option:selected').text();
		var mandalLevelConstName = $("#mandalLevelConstNames").find('option:selected').text();
		var mandalLevelParliamentName = $("#parliamentLevelConstNames").find('option:selected').text();
		var mandalLevelManName = $("#mandalLevelMandalNames").find('option:selected').text();
		
		
		var villageLevelDistName =$("#villageLevelDistNames").find('option:selected').text();
		var villageLevelConstName = $("#villageLevelConstNames").find('option:selected').text();
		var villageLevelManName = $("#villageLevelMandalNames").find('option:selected').text();
		var villageLevelVillName = $("#villageLevelNames").find('option:selected').text();
		var villageLevelparliName = $("#villageLeveParliNames").find('option:selected').text();
		
		if(locationId == null || locationId == 0){
			levelValues =[];
		}else{
			var tempIdStr = "" + locationId;
			var locationLevelId= tempIdStr.substring(0,1);
		}
		if(type == 'scheme' || type == 'deptscheme'){
					if(levelId ==3){
						if(districtName != "SELECT DISTRICT"){
							str+='<h4 class="m_top10">District - '+districtName+'</h4>';
						}
					}else if(levelId ==4){
					
						if(constLevelParliamentName == "SELECT PARLIAMENT" && constLevelConstName =="SELECT CONSTITUENCY"){
							if(constLevelDistName != "SELECT DISTRICT"){
							str+='<h4 class="m_top10">District - '+constLevelDistName+'</h4>';
							}
							
						}else if(constLevelConstName == "SELECT CONSTITUENCY"){
							str+='<h4 class="m_top10">District - <i>'+constLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+constLevelParliamentName+'</i></h4>';
						}else{
							str+='<h4 class="m_top10">District - <i>'+constLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+constLevelParliamentName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Constituency - <i>'+constLevelConstName+'</i></h4>';
						}
						
					}else if(levelId ==5){
						if(mandalLevelParliamentName == "SELECT PARLIAMENT" &&  mandalLevelConstName =="SELECT CONSTITUENCY" && mandalLevelManName =="SELECT MANDAL"){
							if(mandalLevelDistName != "SELECT DISTRICT"){
								str+='<h4 class="m_top10">District - '+mandalLevelDistName+'</h4>';
							}
						}else if(mandalLevelConstName =="SELECT CONSTITUENCY" && mandalLevelManName =="SELECT MANDAL"){
								str+='<h4 class="m_top10">District - <i>'+mandalLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+mandalLevelParliamentName+'</i></h4>';
						}else if(mandalLevelManName == "SELECT MANDAL"){
								str+='<h4 class="m_top10">District - <i>'+mandalLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+mandalLevelParliamentName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>Constituency - <i>'+mandalLevelConstName+'</i></h4>';
						}else{
								str+='<h4 class="m_top10">District - <i>'+mandalLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+mandalLevelParliamentName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>Constituency - <i>'+mandalLevelConstName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Mandal - <i>'+mandalLevelManName+'</i></h4>';
						}
					}else if(levelId ==6){
						if(villageLevelparliName == "SELECT PARLIAMENT" && villageLevelConstName =="SELECT CONSTITUENCY" && villageLevelManName =="SELECT MANDAL" && villageLevelVillName == "SELECT VILLAGE"){
							if(villageLevelDistName != "SELECT DISTRICT"){
							str+='<h4 class="m_top10">District - '+villageLevelDistName+'</h4>';
							}
							
						}else if(villageLevelConstName =="SELECT CONSTITUENCY" && villageLevelManName =="SELECT MANDAL" && villageLevelVillName == "SELECT VILLAGE"){
							str+='<h4 class="m_top10">District - <i>'+villageLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+villageLevelparliName+'</i></h4>';
						}else if(villageLevelVillName == "SELECT VILLAGE"){
							str+='<h4 class="m_top10">District - <i>'+villageLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+villageLevelparliName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;constituency - <i>'+villageLevelConstName+'</i>&nbsp;Mandal - <i>'+villageLevelManName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i></h4>';
						}
						else{
							str+='<h4 class="m_top10">District - <i>'+villageLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+villageLevelparliName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Constituency - <i>'+villageLevelConstName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Mandal - <i>'+villageLevelManName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Village - <i>'+villageLevelVillName+'</i></h4>';
						}
					}
		}
		
		if(displayType == 'selectBox')
		{
			if(type == 'overview'){
					if(levelId ==3){
						if(districtName != "SELECT DISTRICT"){
							str+='<h4 class="m_top10">District - '+districtName+'</h4>';
						}
					}else if(levelId ==4){
				
						if(constLevelParliamentName == "SELECT PARLIAMENT" && constLevelConstName =="SELECT CONSTITUENCY"){
							if(constLevelDistName != "SELECT DISTRICT"){
							str+='<h4 class="m_top10">District - '+constLevelDistName+'</h4>';
							}
							
						}else if(constLevelConstName == "SELECT CONSTITUENCY"){
							str+='<h4 class="m_top10">District - <i>'+constLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+constLevelParliamentName+'</i></h4>';
						}else{
							str+='<h4 class="m_top10">District - <i>'+constLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+constLevelParliamentName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Constituency - <i>'+constLevelConstName+'</i></h4>';
						}
					
					}else if(levelId ==5){
						if(mandalLevelParliamentName == "SELECT PARLIAMENT" &&  mandalLevelConstName =="SELECT CONSTITUENCY" && mandalLevelManName =="SELECT MANDAL"){
							if(mandalLevelDistName != "SELECT DISTRICT"){
								str+='<h4 class="m_top10">District - '+mandalLevelDistName+'</h4>';
							}
						}else if(mandalLevelConstName =="SELECT CONSTITUENCY" && mandalLevelManName =="SELECT MANDAL"){
								str+='<h4 class="m_top10">District - <i>'+mandalLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+mandalLevelParliamentName+'</i></h4>';
						}else if(mandalLevelManName == "SELECT MANDAL"){
								str+='<h4 class="m_top10">District - <i>'+mandalLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+mandalLevelParliamentName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>Constituency - <i>'+mandalLevelConstName+'</i></h4>';
						}else{
								str+='<h4 class="m_top10">District - <i>'+mandalLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+mandalLevelParliamentName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>Constituency - <i>'+mandalLevelConstName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Mandal - <i>'+mandalLevelManName+'</i></h4>';
						}
					}else if(levelId ==6){
							if(villageLevelparliName == "SELECT PARLIAMENT" && villageLevelConstName =="SELECT CONSTITUENCY" && villageLevelManName =="SELECT MANDAL" && villageLevelVillName == "SELECT VILLAGE"){
								if(villageLevelDistName != "SELECT DISTRICT"){
								str+='<h4 class="m_top10">District - '+villageLevelDistName+'</h4>';
								}
								
							}else if(villageLevelConstName =="SELECT CONSTITUENCY" && villageLevelManName =="SELECT MANDAL" && villageLevelVillName == "SELECT VILLAGE"){
								str+='<h4 class="m_top10">District - <i>'+villageLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+villageLevelparliName+'</i></h4>';
							}else if(villageLevelVillName == "SELECT VILLAGE"){
								str+='<h4 class="m_top10">District - <i>'+villageLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+villageLevelparliName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;constituency - <i>'+villageLevelConstName+'</i>&nbsp;Mandal - <i>'+villageLevelManName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i></h4>';
							}
						else{
							str+='<h4 class="m_top10">District - <i>'+villageLevelDistName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Parliament - <i>'+villageLevelparliName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Constituency - <i>'+villageLevelConstName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Mandal - <i>'+villageLevelManName+'</i><i class="fa fa-arrow-right" aria-hidden="true"></i>&nbsp;Village - <i>'+villageLevelVillName+'</i></h4>';
						}
					}
			}
			str+='<div id="accordion'+divId+'">';
			
				str+='<div class="col-sm-2 pull-right">';
						str+='<a class="panelCollapseIconChange" role="button" data-toggle="collapse" data-parent="#accordion'+divId+'" href="#collapse'+divId+'" aria-expanded="true" aria-controls="collapse'+divId+'">';
						str+='</a>';
					str+='</div>';
				str+='<div id="collapse'+divId+'" class="panel-collapse collapse in m_top20" role="tabpanel" aria-labelledby="heading'+divId+'">';
				str+='<div id="overviewTable'+divId+'">';	
				if($windowWidth < 768)
				{
					str+='<div class="table-responsive">';
				}
				str+='<table class="table table-bordered table-striped table-overview">';
					str+='<tr>';
						/*str+='<td>';
							str+='<h4 class="panel-title text-muted">High Funded District</h4>';
							str+='<div id="highFundDist'+divId+'" class="m_top5"></div>';
						str+='</td>';*/
						
						
						if(locationLevelId == 3 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">High Funded Constituency</h4>';
								str+='<div id="highFundCons'+divId+'" attr_id="highFundCons" class="m_top5"></div>';
							str+='</td>';
						}
						
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">High Funded Mandal</h4>';
								str+='<div id="highFundMandal'+divId+'" attr_id="highFundMandal" class="m_top5"></div>';
							str+='</td>';
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 5 || locationLevelId == 9)
						{
							/*str+='<td>';
								str+='<h4 class="panel-title text-muted">High Funded Village</h4>';
								str+='<div id="highFundVillage'+divId+'" attr_id="highFundVillage" class="m_top5"></div>';
							str+='</td>';*/
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 5 || locationLevelId == 6 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">High Funded Scheme</h4>';
								str+='<div id="highFundScheme'+divId+'" attr_id="highFundScheme" class="m_top5"></div>';
							str+='</td>';
						}
					str+='</tr>';
					str+='<tr>';
						/*str+='<td>';
							str+='<h4 class="panel-title text-muted">Low Funded District</h4>';
							str+='<div id="lowFundDist'+divId+'" class="m_top5"></div>';
						str+='</td>';*/
						if(locationLevelId == 3 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Low Funded Costituency</h4>';
								str+='<div id="lowFundCons'+divId+'" attr_id="lowFundCons" class="m_top5"></div>';
							str+='</td>';
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Low Funded Mandal</h4>';
								str+='<div id="lowFundMandal'+divId+'" attr_id="lowFundMandal" class="m_top5"></div>';
							str+='</td>';
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 5 || locationLevelId == 9)
						{
							/*str+='<td>';
								str+='<h4 class="panel-title text-muted">Low Funded Village</h4>';
								str+='<div id="lowFundVillage'+divId+'" attr_id="lowFundVillage" class="m_top5"></div>';
							str+='</td>';*/
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 5 || locationLevelId == 6 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Low Funded Scheme</h4>';
								str+='<div id="lowFundScheme'+divId+'" attr_id="lowFundScheme" class="m_top5"></div>';
							str+='</td>';
						}
					str+='</tr>';
					str+='<tr>';
						/*str+='<td>';
							str+='<h4 class="panel-title text-muted">Average Funded District</h4>';
							str+='<div id="avgFundDist'+divId+'" class="m_top5"></div>';
						str+='</td>';*/
						if(locationLevelId == 3 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Average Funded Costituency</h4>';
								str+='<div id="avgFundCons'+divId+'" attr_id="avgFundCons" class="m_top5"></div>';
							str+='</td>';
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Average Funded Mandal</h4>';
								str+='<div id="avgFundMandal'+divId+'" attr_id="avgFundMandal" class="m_top5"></div>';
							str+='</td>';
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 5  || locationLevelId == 9)
						{
							/*str+='<td>';
								str+='<h4 class="panel-title text-muted">Average Funded Village</h4>';
								str+='<div id="avgFundVillage'+divId+'" attr_id="avgFundVillage" class="m_top5"></div>';
							str+='</td>';*/
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 5 || locationLevelId == 6 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Average Funded Scheme</h4>';
								str+='<div id="avgFundScheme'+divId+'" attr_id="avgFundScheme" class="m_top5"></div>';
							str+='</td>';
						}
					str+='</tr>';
					str+='<tr>';
						/*str+='<td>';
							str+='<h4 class="panel-title text-muted">Total Funds</h4>';
							str+='<div id="totFund'+divId+'" class="m_top5"></div>';
						str+='</td>';*/
						
						if(locationLevelId == 3 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Total Funded Costituency</h4>';
								str+='<div id="totFundCons'+divId+'" attr_id="totFundCons" class="m_top5"></div>';
							str+='</td>';
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Total Funded Mandal</h4>';
								str+='<div id="totFundMandal'+divId+'" attr_id="totFundMandal" class="m_top5"></div>';
							str+='</td>';
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 5  || locationLevelId == 9)
						{
							/*str+='<td>';
								str+='<h4 class="panel-title text-muted">Total Funded Village</h4>';
								str+='<div id="totFundVillage'+divId+'" attr_id="totFundVillage" class="m_top5"></div>';
							str+='</td>';*/
						}
						if(locationLevelId == 3 || locationLevelId == 4 || locationLevelId == 5 || locationLevelId == 6 || locationLevelId == 9)
						{
							str+='<td>';
								str+='<h4 class="panel-title text-muted">Total Funded Scheme</h4>';
								str+='<div id="totFundScheme'+divId+'" attr_id="totFundScheme" class="m_top5"></div>';
							str+='</td>';
						}
					str+='</tr>';
				str+='</table>';
				if($windowWidth < 768)
				{
					str+='</div>';
				}
			str+='</div>';
			str+='</div>';
		}
		
		str+='<div class="scroller'+divId+'">';
			str+='<div class="chart'+divId+'"></div>';
		str+='</div>';
		str+='</div>';
		$("#"+divId).html(str);
		if(locationLevelId == 9){
			locationLevelId =10;
		}
		if(displayType == 'selectBox')
		{
			
			getLocationWiseFundDetails(3,'highest','highFundDist'+divId+'',locationId,locationLevelId);
			getLocationWiseFundDetails(4,'highest','highFundCons'+divId+'',locationId,locationLevelId);
			getLocationWiseFundDetails(3,'lowest','lowFundDist'+divId+'',locationId,locationLevelId);
			getLocationWiseFundDetails(4,'lowest','lowFundCons'+divId+'',locationId,locationLevelId);
			getLocationWiseFundDetails(5,'highest','highFundMandal'+divId+'',locationId,locationLevelId);
			getLocationWiseFundDetails(6,'highest','highFundVillage'+divId+'',locationId,locationLevelId);
			getLocationWiseFundDetails(5,'lowest','lowFundMandal'+divId+'',locationId,locationLevelId);
			getLocationWiseFundDetails(6,'lowest','lowFundVillage'+divId+'',locationId,locationLevelId);
			//getLocationWiseFundDetails(4,'lowest','lowFundParliament'+divId+'',locationId,locationLevelId);
			//getLocationWiseFundDetails(4,'highest','highFundParlmnt'+divId+'',locationId,locationLevelId);
			
			getSchemeWiseHighestAndLowestFund('highest','highFundScheme'+divId+'',locationLevelId,locationId,3);
			getSchemeWiseHighestAndLowestFund('lowest','lowFundScheme'+divId+'',locationLevelId,locationId,3);
			getAverageFundForAnyLevel(3,'avgFundDist'+divId+'',locationLevelId,locationId);
			getAverageFundForAnyLevel(4,'avgFundCons'+divId+'',locationLevelId,locationId);
			getAverageFundForAnyLevel(5,'avgFundMandal'+divId+'',locationLevelId,locationId);
			getAverageFundForAnyLevel(6,'avgFundVillage'+divId+'',locationLevelId,locationId);
			//getAverageFundForAnyLevel(10,'avgFundparlment'+divId+'',locationLevelId,locationId);
			getTotalFunds('totFund'+divId+'');
			getTotalLocationsByScopeId(4,'totFundCons'+divId+'',locationId,locationLevelId);
			getTotalLocationsByScopeId(5,'totFundMandal'+divId+'',locationId,locationLevelId);
			getTotalLocationsByScopeId(6,'totFundVillage'+divId+'',locationId,locationLevelId);
			//getTotalLocationsByScopeId(10,'totFundParliament'+divId+'',locationId,locationLevelId);
			getAverageFundForScheme('avgFundScheme'+divId+'','scheme',locationLevelId,locationId);
			getAverageFundForScheme('avgFundGrant'+divId+'','grant',locationLevelId,locationId);
			getTotalSchemes(4,'totFundScheme'+divId+'','scheme',locationLevelId,locationId);
			getTotalSchemes(4,'totFundGrant'+divId+'','grant',locationLevelId,locationId);
		}
		
		var length = result.length
		var newYearId;
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
			if($windowWidth < 768)
			{
				table+='<div class="table-responsive">';
			}	
			table+='<table class="table table-bordered table-striped table-condensed" id="dataTable'+divId+'" style="width:100%;">';
				table+='<thead class="text-center">';
					table+='<tr>';
						if(levelId == '2')
						{
							table+='<th rowspan="2" class="text-center">State</th>';
						}else if(levelId == '3'){
							table+='<th rowspan="2" class="text-center">District</th>';
						}else if(levelId == '4')
						{
							table+='<th rowspan="2" class="text-center">District</th>';
							table+='<th rowspan="2" class="text-center">Parliament</th>';
							table+='<th rowspan="2" class="text-center">Constituency</th>';
						}else if(levelId == '5')
						{
							table+='<th rowspan="2" class="text-center">District</th>';
							table+='<th rowspan="2" class="text-center">Parliament</th>';
							table+='<th rowspan="2" class="text-center">Constituency</th>';
							table+='<th rowspan="2" class="text-center">Mandal</th>';
						}else if(levelId == '6')
						{
							table+='<th rowspan="2" class="text-center">District</th>';
							table+='<th rowspan="2" class="text-center">Parliament</th>';
							table+='<th rowspan="2" class="text-center">Constituency</th>';
							table+='<th rowspan="2" class="text-center">Mandal</th>';
							table+='<th rowspan="2" class="text-center">Village</th>';
						}
						if($("#financialYearId").val().length > 1 || $("#financialYearId").val() == 0 ){
							for(var i in result[0].locationList1)
							{
								table+='<th colspan="2" class="text-center">'+result[0].locationList1[i].financialYear+'</th>';
							}
						}else{
							table+='<th colspan="2" class="text-center">'+result[0].locationList1[0].financialYear+'</th>';
						}
					table+='</tr>';
					table+='<tr>';
						//table+='<th>ID</th>';
						
						if($("#financialYearId").val().length > 1 || $("#financialYearId").val() == 0){
							for(var i in result[0].locationList1)
							{
								table+='<th class="text-center no-right-border">No</th>';
								table+='<th class="text-center">Amt.</th>';
							}
						}else{
								table+='<th class="text-center no-right-border">No</th>';
								table+='<th class="text-center">Amt.</th>';
						}
					table+='</tr>';
				table+='</thead>';
				table+='<tbody>';
					for(var i in result){
						table+='<tr>';
							//table+='<td>'+result[i].locationId+'</td>';
							if(levelId == '2')
							{
								
								//table+='<td>1</td>';
								table+='<td>Andhra Pradesh</td>';
							}else if(levelId == '3'){
								//table+='<td>'+result[i].addressVO.districtId+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
							}else if(levelId == '4')
							{
								//table+='<td>'+result[i].addressVO.assemblyId+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
								table+='<td>'+result[i].addressVO.parliamentName+'</td>';
								table+='<td>'+result[i].addressVO.assemblyName+'</td>';
							}else if(levelId == '5')
							{
								//table+='<td>'+result[i].addressVO.id+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
								table+='<td>'+result[i].addressVO.parliamentName+'</td>';
								table+='<td>'+result[i].addressVO.assemblyName+'</td>';
								table+='<td>'+result[i].addressVO.tehsilName+'</td>';
							}else if(levelId == '6')
							{
								//table+='<td>'+result[i].addressVO.id+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
								table+='<td>'+result[i].addressVO.parliamentName+'</td>';
								table+='<td>'+result[i].addressVO.assemblyName+'</td>';
								table+='<td>'+result[i].addressVO.tehsilName+'</td>';
								table+='<td>'+result[i].addressVO.panchayatName+'</td>';
							}
							
							if($("#financialYearId").val().length > 1 || $("#financialYearId").val() == 0){
								for(var j in result[i].locationList1){
									 if(result[i].locationList1[j].financialYearId != 0){
									      newYearId = result[i].locationList1[j].financialYearId;
								       }else{
										   newYearId = $("#financialYearId").val();
									   } 
										if(result[i].locationList1[j].count != null && result[i].locationList1[j].count > 0){
											table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+result[i].locationId+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="0" attr_dept_id="0" style="cursor:pointer;color:green;">'+result[i].locationList1[j].count+'</td>';
										}else{
											table+='<td class="text-center no-right-border">-</td>';
										}
									
									if(result[i].locationList1[j].amount != null && result[i].locationList1[j].amount > 0){
										table+='<td class="text-center">'+parseFloat(result[i].locationList1[j].amunt.replace(/,/g, ""))+'</td>';
									}else{
										table+='<td class="text-center">-</td>';
									}
								}
							}else{
								if(result[i].locationList1[0].count != null && result[i].locationList1[0].count > 0){
									        if(result[i].locationList1[0].financialYearId != 0){
									              newYearId = result[i].locationList1[0].financialYearId;
								              }else{
										           newYearId = $("#financialYearId").val();
									           }
											table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+result[i].locationId+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="0" attr_dept_id="0" style="cursor:pointer;color:green;">'+result[i].locationList1[0].count+'</td>';
										}else{
											table+='<td class="text-center no-right-border">-</td>';
										}
									
									if(result[i].locationList1[0].amount != null && result[i].locationList1[0].amount > 0){
										table+='<td class="text-center">'+parseFloat(result[i].locationList1[0].amunt.replace(/,/g, ""))+'</td>';
									}else{
										table+='<td class="text-center">-</td>';
									}
							}
						table+='</tr>';
					}
					
				table+='</tbody>';
			table+='</table>';
			if($windowWidth < 768)
			{
				table+='</div>';
			}	
		}else if(type == 'scheme')
		{
			if(result[0].subList.length >= 2 || $windowWidth < 768)
			{
				table+='<div class="table-responsive">';
			}	
			table+='<table class="table table-bordered table-striped table-condensed" id="dataTable'+divId+'" style="width:100%;">';
				table+='<thead class="text-center">';
					table+='<tr>';
					if(levelId == '2')
					{
						table+='<th rowspan="3" class="text-center">State</th>';
					}else if(levelId == '3'){
						table+='<th rowspan="3" class="text-center">District</th>';
					}else if(levelId == '4')
					{
						table+='<th rowspan="3" class="text-center">District</th>';
						table+='<th rowspan="3" class="text-center">Parliament</th>';
						table+='<th rowspan="3" class="text-center">Constituency</th>';
					}else if(levelId == '5')
					{
						table+='<th rowspan="3" class="text-center">District</th>';
						table+='<th rowspan="3" class="text-center">Parliament</th>';
						table+='<th rowspan="3" class="text-center">Constituency</th>';
						table+='<th rowspan="3" class="text-center">Mandal</th>';
					}else if(levelId == '6')
					{
						table+='<th rowspan="3" class="text-center">District</th>';
						table+='<th rowspan="3" class="text-center">Parliament</th>';
						table+='<th rowspan="3" class="text-center">Constituency</th>';
						table+='<th rowspan="3" class="text-center">Mandal</th>';
						table+='<th rowspan="3" class="text-center">Village</th>';
					}
						
						for(var j in result[0].subList)
						{
							table+='<th class="text-center" colspan="'+((result[0].subList[0].subList.length)*2)+'">'+result[0].subList[j].year+'</th>';
						}
					table+='</tr>';
					table+='<tr>';
						for(var i in result[0].subList)
						{
							for(var j in result[0].subList[0].subList){
								table+='<th colspan="2" class="text-center">'+result[0].subList[0].subList[j].name+'</th>';
							}
						}
					table+='</tr>';
					table+='<tr>';
						//table+='<th>ID</th>';
						
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
								//table+='<td>1</td>';
								table+='<td>Andhra Pradesh</td>';
							}else if(levelId == '3'){
								lvlVal =result[i].addressVO.districtId;
								//table+='<td>'+result[i].addressVO.districtId+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
							}else if(levelId == '4')
							{
								lvlVal =result[i].addressVO.assemblyId;
								//table+='<td>'+result[i].addressVO.assemblyId+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
								table+='<td>'+result[i].addressVO.parliamentName+'</td>';
								table+='<td>'+result[i].addressVO.assemblyName+'</td>';
							}else if(levelId == '5')
							{
								lvlVal =result[i].addressVO.id;
								//table+='<td>'+result[i].addressVO.id+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
								table+='<td>'+result[i].addressVO.parliamentName+'</td>';
								table+='<td>'+result[i].addressVO.assemblyName+'</td>';
								table+='<td>'+result[i].addressVO.tehsilName+'</td>';
							}else if(levelId == '6')
							{
								lvlVal =result[i].addressVO.id;
								//table+='<td>'+result[i].addressVO.id+'</td>';
								table+='<td>'+result[i].addressVO.districtName+'</td>';
								table+='<td>'+result[i].addressVO.parliamentName+'</td>';
								table+='<td>'+result[i].addressVO.assemblyName+'</td>';
								table+='<td>'+result[i].addressVO.tehsilName+'</td>';
								table+='<td>'+result[i].addressVO.panchayatName+'</td>';
							}
							for(var j in result[i].subList){
								for(var k in result[i].subList[j].subList)
								{
									if(result[i].subList[j].yearId != 0){
									        newYearId = result[i].subList[j].yearId;
								               }else{
										    newYearId = $("#financialYearId").val();
									       } 
									if(levelId != '2'){
									if(result[i].subList[j].subList[k].count != null && result[i].subList[j].subList[k].count > 0){
										table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+result[i].subList[j].subList[k].id+'" attr_dept_id="0" style="cursor:pointer;color:green;">'+result[i].subList[j].subList[k].count+'</td>';
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
										table+='<td class="text-center">'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'</td>';
									}else{
										table+='<td class="text-center">-</td>';
									}
								}
							}
						table+='</tr>';
					}
					
				table+='</tbody>';
			table+='</table>';
			if(result[0].subList.length >= 2 || $windowWidth < 768)
			{
				table+='</div>';
			}	
		}else if(type == 'deptscheme')
		{
			
				if($windowWidth < 768 || result[0].subList.length > 2)
				{
					table+='<div class="table-responsive">';
				}
				table+='<table class="table table-bordered table-striped table-condensed" id="dataTable'+divId+'" style="width:100%;">';
					table+='<thead class="text-center">';
						table+='<tr>';
							if(levelId == '2')
							{
								table+='<th rowspan="4" class="text-center">State</th>';
							}else if(levelId == '3'){
								table+='<th rowspan="4" class="text-center">District</th>';
							}else if(levelId == '4')
							{
								table+='<th rowspan="4" class="text-center">District</th>';
								table+='<th rowspan="4" class="text-center">Parliament</th>';
								table+='<th rowspan="4" class="text-center">Constituency</th>';
							}else if(levelId == '5')
							{
								table+='<th rowspan="4" class="text-center">District</th>';
								table+='<th rowspan="4" class="text-center">Parliament</th>';
								table+='<th rowspan="4" class="text-center">Constituency</th>';
								table+='<th rowspan="4" class="text-center">Mandal</th>';
							}else if(levelId == '6')
							{
								table+='<th rowspan="4" class="text-center">District</th>';
								table+='<th rowspan="4" class="text-center">Parliament</th>';
								table+='<th rowspan="4" class="text-center">Constituency</th>';
								table+='<th rowspan="4" class="text-center">Mandal</th>';
								table+='<th rowspan="4" class="text-center">Village</th>';
							}
						
							for(var j in result[0].subList)
							{
								table+='<th class="text-center" colspan="'+(((result[0].subList[0].subList[0].subList.length)*(result[0].subList[0].subList.length))*2)+'">'+result[0].subList[j].year+'</th>';
							}
						table+='</tr>';
						table+='<tr>';
						
							for(var i in result[0].subList){
								for(var j in result[0].subList[i].subList)
								{
									table+='<th class="text-center" colspan="'+((result[0].subList[0].subList[0].subList.length)*2)+'">'+result[0].subList[i].subList[j].name+'</th>';
								}
							}
							
						table+='</tr>';
						table+='<tr>';
						
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
							//table+='<th>ID</th>';
							
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
									//table+='<td>1</td>';
									table+='<td>Andhra Pradesh</td>';
								}else if(levelId == '3'){
									levlValId= result[i].addressVO.districtId;
									//table+='<td>'+result[i].addressVO.districtId+'</td>';
									table+='<td>'+result[i].addressVO.districtName+'</td>';
								}else if(levelId == '4')
								{
									levlValId= result[i].addressVO.assemblyId;
									//table+='<td>'+result[i].addressVO.assemblyId+'</td>';
									table+='<td>'+result[i].addressVO.districtName+'</td>';
									table+='<td>'+result[i].addressVO.parliamentName+'</td>';
									table+='<td>'+result[i].addressVO.assemblyName+'</td>';
								}else if(levelId == '5')
								{
									levlValId= result[i].addressVO.id;
									//table+='<td>'+result[i].addressVO.id+'</td>';
									table+='<td>'+result[i].addressVO.districtName+'</td>';
									table+='<td>'+result[i].addressVO.parliamentName+'</td>';
									table+='<td>'+result[i].addressVO.assemblyName+'</td>';
									table+='<td>'+result[i].addressVO.tehsilName+'</td>';
								}else if(levelId == '6')
								{
									levlValId= result[i].addressVO.id;
									//table+='<td>'+result[i].addressVO.id+'</td>';
									table+='<td>'+result[i].addressVO.districtName+'</td>';
									table+='<td>'+result[i].addressVO.parliamentName+'</td>';
									table+='<td>'+result[i].addressVO.assemblyName+'</td>';
									table+='<td>'+result[i].addressVO.tehsilName+'</td>';
									table+='<td>'+result[i].addressVO.panchayatName+'</td>';
								}
								
								for(var j in result[i].subList)
								{
									for(var k in result[i].subList[j].subList)
									{
										for(var l in result[i].subList[j].subList[k].subList)
										{
											if(result[i].subList[j].yearId != 0){
									        newYearId = result[i].subList[j].yearId;
								               }else{
										    newYearId = $("#financialYearId").val();
									       } 
											if(result[i].subList[j].subList[k].subList[l].count != null && result[i].subList[j].subList[k].subList[l].count > 0){
												table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+levlValId+'" attr_financial_yr_id="'+newYearId+'" attr_dept_id="'+result[i].subList[j].subList[k].id+'" attr_scheme_id="'+result[i].subList[j].subList[k].subList[l].id+'" style="cursor:pointer;color:green;">'+result[i].subList[j].subList[k].subList[l].count+'</td>';
											}else{
												table+='<td class="text-center no-right-border">-</td>';
											}
											
											if(result[i].subList[j].subList[k].subList[l].totalCount != null && result[i].subList[j].subList[k].subList[l].totalCount > 0){
												table+='<td class="text-center">'+parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, ""))+'</td>';
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
				if($windowWidth < 768 || result[0].subList.length > 2)
				{
					str+='</div>';
				}
			table+='</div>';
				
		}
		$("#"+divId+"Table").html("");
		$("#"+divId+"Table").html(table);
		if(levelId == 3 || levelId == 4 || levelId == 5 || levelId == 6)
		{
			if(levelId != 3){
				$("#dataTable"+divId).dataTable({
					"iDisplayLength": 15,
					"aaSorting": [],
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				});
			}else{
				$("#dataTable"+divId).dataTable({
					"paging":   false,
					"info":     false,
					"searching": false,
					"autoWidth": true,
					"sDom": '<"top"iflp>rt<"bottom"><"clear">',
					"order": [ 0, 'desc' ]
				});
			}
			
		}
		
		if(type == 'overview')
		{
			if(levelId == 2){
				if(result !=null && result.length>0){
					var locationNamesArr=[];
					var mainlocationArr =[];
					var totalCountArr = [];
					var totalCount2 = '';
					for(var i in result){
						if(result[i].locationList1 !=null &&  result[i].locationList1.length>0){
							for(var j in result[i].locationList1){
								totalCount2 =  result[i].locationList1[j].count;
								 if(result[i].locationList1[j].financialYearId != 0){
									//mainlocationArr.push({y:result[i].locationList1[j].amount})
									mainlocationArr.push({y:parseFloat(result[i].locationList1[j].amunt.replace(/,/g, "")),appData:result[i].locationLevelId+"-"+result[i].locationId+"-"+result[i].locationList1[j].financialYearId,"extra":result[i].locationList1[j].count})
								}
								
								//mainlocationArr.push({y:parseInt(result[i].locationList1[j].amunt.replace(/,/g, ""))})
								locationNamesArr.push("Year<br/>"+result[i].locationList1[j].financialYear);
								/* if(result[i].locationList1[j].financialYearId == 0){
									locationNamesArr.push("OverAll");
								}else{
									locationNamesArr.push("Year<br/>"+result[i].locationList1[j].financialYear);
								} */
								totalCountArr.push(totalCount2);
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
								qTotals: totalCountArr,
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
								
									//return '<span style="top:16px; position: absolute;"><br/><h3><i class="fa fa-inr"></i>: '+this.total+'</h3></span>';
									//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
									return '<span><i class="fa fa-inr"></i>: '+this.total+'-('+this.options.qTotals[this.x]+')</span>';
								} 
							
							}
						
						},
						tooltip: {
							useHTML:true,
							pointFormat: '<b><i class="fa fa-inr"></i>{point.y} - ({point.extra})</b>',
							 shared:true 
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
									click: function () {   //hyma
										var value = (this.appData).split("-");
										var blockLvlId = value[0];
										var levlValue = value[1];
										var financialYrId = value[2];
									
										getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,0,0);
									}
								}
							}
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
					var year1718Arr=[];
					var totalCountArr = [];
					var totalCount2 = '';
					for(var i in result){
						locationLevelNames.push(result[i].locationName);
						if(result[i].locationList1 !=null && result[i].locationList1.length>0){					
							for(var j in result[i].locationList1){
								totalCount2 = 0;
								totalCount2 =totalCount2+result[i].locationList1[j].count;
								if(result[i].locationList1[j].financialYearId==1){
									 //year1415Arr.push({"y":result[i].locationList1[j].amount}); 
									 year1415Arr.push({"y":parseFloat(result[i].locationList1[j].amunt.replace(/,/g, "")),appData:result[i].locationLevelId+"-"+result[i].locationId+"-"+result[i].locationList1[j].financialYearId,"extra":result[i].locationList1[j].count}); 
								}else if(result[i].locationList1[j].financialYearId==2){
									 //year1516Arr.push({"y":result[i].locationList1[j].amount}); 
									 year1516Arr.push({"y":parseFloat(result[i].locationList1[j].amunt.replace(/,/g, "")),appData:result[i].locationLevelId+"-"+result[i].locationId+"-"+result[i].locationList1[j].financialYearId,"extra":result[i].locationList1[j].count}); 
								}else if(result[i].locationList1[j].financialYearId==3){
									 //year1617Arr.push({"y":result[i].locationList1[j].amount}); 
									 year1617Arr.push({"y":parseFloat(result[i].locationList1[j].amunt.replace(/,/g, "")),appData:result[i].locationLevelId+"-"+result[i].locationId+"-"+result[i].locationList1[j].financialYearId,"extra":result[i].locationList1[j].count}); 
								}else if(result[i].locationList1[j].financialYearId==4){
									 //year1718Arr.push({"y":result[i].locationList1[j].amount}); 
									 year1718Arr.push({"y":parseFloat(result[i].locationList1[j].amunt.replace(/,/g, "")),appData:result[i].locationLevelId+"-"+result[i].locationId+"-"+result[i].locationList1[j].financialYearId,"extra":result[i].locationList1[j].count}); 
								}
							}
						}
						totalCountArr.push(totalCount2);
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
						   if(year1718Arr != null && year1718Arr.length > 0){
							mainJosnObjLocArr.push({name:'2017-2018',data:year1718Arr,color:"#6500FE"});  
						  }
					}
					var length = result.length
					var height = '';
					if(length == 0)
					{
						height = length * 100;
					}else if(length > 3){
						height = length * 90;
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
								align: 'left',
								enabled: true,
								qTotals: totalCountArr,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() { 
									return '<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total+'-('+this.options.qTotals[this.x]+')</span>';
									//return '<span style="top:16px; position: absolute;"><br/>'+this.options.qTotals[this.x]+'%'+' '+'('+this.total+')</span>';
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
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : <i class="fa fa-inr"></i>' +
									this.y+'-'+'('+(this.point.extra)+')'/* +' - ' +
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
											click: function () {   //hyma
												var value = (this.appData).split("-");
												var blockLvlId = value[0];
												var levlValue = value[1];
												var financialYrId = value[2];
											
												getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,0,0);
											}
										}
									}
							}
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
					var NRDWPArr=[]; 
					var SDPArr=[];
					var totalCountArr = [];
					var totalCount2 = '';
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
									var locationId= 1;
									if(countAvailable){
										totalCount2 = 0;
										for(var k in result[i].subList[j].subList){
                                            totalCount2 =totalCount2+result[i].subList[j].subList[k].count;
											if(result[i].subList[j].subList[k].id == 1){
												//NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
												NABARDArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 //NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NREGPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 //CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 //RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 RDFArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 //MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 6){
												 //FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
												 FC13Arr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 7){
												 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 APDRPGRANTArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 8){
												 //NRDWPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NRDWPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 9){
												 //NRDWPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 SDPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }
										}
										 totalCountArr.push(totalCount2);
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
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#46306A"});  
						}if(NRDWPArr != null && NRDWPArr.length > 0){
							mainJosnObjArr.push({name:'NRDWP',data:NRDWPArr,color:"#9C9C9C"});  
						}if(SDPArr != null && SDPArr.length > 0){
							mainJosnObjArr.push({name:'SDP',data:SDPArr,color:"#14BAAD"});  
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
								align: 'left',
								enabled: true,
								qTotals: totalCountArr,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
									return '<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total+'-('+this.options.qTotals[this.x]+')</span>';
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
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :<i class="fa fa-inr"></i> ' +
									this.y+'-'+'('+(this.point.extra)+')'/* +' - ' +
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
										var schemeId = value[3];
										getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,0);
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
					var NRDWPArr=[];
					var SDPArr=[];
					var totalCountArr = [];
                    var totalCount2 = '';
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
									var locationId;
									if(levelId == 3){
										locationId=result[i].subList[j].addressVO.id;
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.districtName+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 4){
										locationId=result[i].subList[j].addressVO.id;
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.assemblyName+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 5){
										locationId=result[i].subList[j].addressVO.id;
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.name+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 6){
										locationId=result[i].subList[j].addressVO.id;
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.panchayatName+"<br/>("+result[i].subList[j].year+")")
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
										 totalCount2 = 0;
										for(var k in result[i].subList[j].subList){
                                             totalCount2 =totalCount2+result[i].subList[j].subList[k].count;
											 if(result[i].subList[j].subList[k].id == 1){
												// NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NABARDArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 //NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NREGPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 //CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 //RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 RDFArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 //MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 6){
												//FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
												 FC13Arr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 7){
												 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 APDRPGRANTArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 8){
												 //NRDWPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NRDWPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 9){
												 //NRDWPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 SDPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }
											
										}
										totalCountArr.push(totalCount2);
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
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#46306A"});  
						}
						if(NRDWPArr != null && NRDWPArr.length > 0){
							mainJosnObjArr.push({name:'NRDWP',data:NRDWPArr,color:"#9C9C9C"});  
						}if(SDPArr != null && SDPArr.length > 0){
							mainJosnObjArr.push({name:'SDP',data:SDPArr,color:"#14BAAD"});  
						}
					}
					var length = result.length
					var height = '';
					if(length == 0)
					{
						height = length * 100;
					}else if(length > 3){
						height = length * 200;
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
								align: 'left',
								enabled: true,
								qTotals: totalCountArr,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
									return '<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total+'-('+this.options.qTotals[this.x]+')</span>';
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
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :<i class="fa fa-inr"></i> ' +
									this.y+'-'+'('+(this.point.extra)+')'/* +' - ' +
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
										var schemeId = value[3];
										getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,0);
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
		}else if(type == "deptscheme"){
			if(levelId == 2)
			{
			  if(result !=null && result.length>0){
	
				  var assemblyShemeNameArr=[];
				  var NABARDArr =[];
				  var NREGPArr =[];
				  var CRRArr=[];
				  var RDFArr=[];
				  var MRRArr=[];
				  var FC13Arr=[];
				  var APDRPGRANTArr=[];
				  var NRDWPArr=[];
				  var SDPArr=[];
				  var qTotalArr = [];
				  var qTotal = '';
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
							  if(levelId == 2){
								 locationId= result[i].subList[j].subList[k].id;
								assemblyShemeNameArr.push(result[i].subList[j].subList[k].name+"<br/>"+":"+result[i].subList[j].year);
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
								qTotal = 0;
								for(var l in result[i].subList[j].subList[k].subList){
								  qTotal =parseInt(qTotal+result[i].subList[j].subList[k].subList[l].count);
								  
								   if(result[i].subList[j].subList[k].subList[l].id == 1){
									NABARDArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
								   }else if(result[i].subList[j].subList[k].subList[l].id == 2){
									 NREGPArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
								   }else if(result[i].subList[j].subList[k].subList[l].id == 3){
									 CRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
								   }else if(result[i].subList[j].subList[k].subList[l].id == 4){
									 RDFArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
									 }else if(result[i].subList[j].subList[k].subList[l].id == 5){
									 MRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
								   }else if(result[i].subList[j].subList[k].subList[l].id == 6){
									 FC13Arr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
								   }else if(result[i].subList[j].subList[k].subList[l].id == 7){
									 APDRPGRANTArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
								   }else if(result[i].subList[j].subList[k].subList[l].id == 8){
									 NRDWPArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
								   }else if(result[i].subList[j].subList[k].subList[l].id == 9){
									 SDPArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
								   }
								}
								qTotalArr.push(qTotal);
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
					  mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#46306A"});  
					}
					if(NRDWPArr != null && NRDWPArr.length > 0){
					  mainJosnObjArr.push({name:'NRDWP',data:NRDWPArr,color:"#9C9C9C"});  
					}if(SDPArr != null && SDPArr.length > 0){
					  mainJosnObjArr.push({name:'SDP',data:SDPArr,color:"#14BAAD"});  
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
						  align: 'left',
						  qTotals: qTotalArr,
						  enabled: true,
						  style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						  },
						  formatter: function() {
							return '<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total+'-('+this.options.qTotals[this.x]+')</span>';
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
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : <i class="fa fa-inr"></i> ' +
							this.y+'-'+'('+(this.point.extra)+')'/* +' - ' +
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
		
			else if(levelId == 3 || levelId == 4 || levelId == 5){
				if(result !=null && result.length>0){
				
					var assemblyShemeNameArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var CRRArr=[];
					var RDFArr=[];
					var MRRArr=[];
					var FC13Arr=[];
					var APDRPGRANTArr=[];
					var NRDWPArr=[];
					var SDPArr=[];
					var qTotalArr = [];
					var qTotal = '';
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
											}else if(levelId == 6){
												locationId= result[i].subList[j].subList[k].addressVO.id;
												assemblyShemeNameArr.push(result[i].subList[j].subList[k].addressVO.panchayatName+"<br/>("+result[i].subList[j].year+":"+result[i].subList[j].subList[k].name+")")
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
												qTotal = 0;
												for(var l in result[i].subList[j].subList[k].subList){
													qTotal =parseInt(qTotal+result[i].subList[j].subList[k].subList[l].count);
													
													 if(result[i].subList[j].subList[k].subList[l].id == 1){
														//NABARDArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														NABARDArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 2){
														//NREGPArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 NREGPArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 3){
														 //CRRArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 CRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 4){
														 //RDFArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 RDFArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 5){
														//MRRArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 MRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 6){
														 //FC13Arr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 FC13Arr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 7){
														 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 APDRPGRANTArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 8){
														 //NRDWPArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 NRDWPArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }else if(result[i].subList[j].subList[k].subList[l].id == 9){
														 //SDPArr.push({"y":result[i].subList[j].subList[k].subList[l].totalCount})
														 SDPArr.push({"y":parseFloat(result[i].subList[j].subList[k].subList[l].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+result[i].subList[j].subList[k].subList[l].id,"extra":result[i].subList[j].subList[k].subList[l].count})
													 }
												}
												qTotalArr.push(qTotal);
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
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#46306A"});  
						}
						if(NRDWPArr != null && NRDWPArr.length > 0){
							mainJosnObjArr.push({name:'NRDWP',data:NRDWPArr,color:"#9C9C9C"});  
						}if(SDPArr != null && SDPArr.length > 0){
							mainJosnObjArr.push({name:'SDP',data:SDPArr,color:"#14BAAD"});  
						}
						var length = result.length
						var height = '';
						if(length == 0)
						{
							height = length * 100;
						}else if(length > 3){
							height = length * 400;
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
									align: 'left',
									qTotals: qTotalArr,
									enabled: true,
									style: {
										fontWeight: 'bold',
										color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
									},
									formatter: function() {
										return '<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total+'-('+this.options.qTotals[this.x]+')</span>';
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
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : <i class="fa fa-inr"></i> ' +
										this.y+'-'+'('+(this.point.extra)+')'/* +' - ' +
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
		$("#"+divId+"Table").html(spinner);
		var levelValues = [];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr =$('#DepartmentsId').val();
		
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
			order :orderType,
			govtSchmeIdsList :[],
			subProgramIdsList :[],
			glSearchLevelValue :[],
			glSearchLevelId :1
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
				if(ajaxresp != null && ajaxresp.length>0){
					buildLocationWiseAmountDetails(ajaxresp,divId,type,levelId);
				}
					
				else{
					$("#"+divId).html("NO DATA AVAILABLE");
					$("#"+divId+"Table").html("NO DATA AVAILABLE");
				}
					
			}
		});
	}
	
	function getFinancialYearWiseDeptsWiseSchemeAmountDetails(levelId,divId,type,sortingType,orderType,locationId,locationLevelType){
		$("#"+divId).html(spinner);
		$("#"+divId+"Table").html(spinner);
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
					$("#"+divId+"Table").html("NO DATA AVAILABLE");
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
	function getTotalSchemes(levelId,divId,type,searchLevelId,searchLevelVal)
	{
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdArr = $('#financialYearId').val();
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr =  $('#DepartmentsId').val();
		var searchLevelId = searchLevelId;
		var searchLevelVals = [];
		//searchLevelVals.push(searchLevelVal);
		if(searchLevelVal == null || searchLevelVal == 0){
			 searchLevelVals =[];
		}else{
			 var tempIdStr=""+searchLevelVal;
			 var finalIdStr= tempIdStr.substring(1);
			 searchLevelVals.push(finalIdStr);
		}
		if(searchLevelId == 9){
			searchLevelId =10;
		}
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
			searchLvlVals:searchLevelVals,
			type : type
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
	
	function getSchemeWiseHighestAndLowestFund(type,divId,searchLevelId,searchLevelVal,blockLevelId)
	{
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdArr = $('#financialYearId').val();
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr =  $('#DepartmentsId').val();
		var searchLevelId = searchLevelId;
		var searchLevelVals = [];
		
		if(searchLevelVal == null || searchLevelVal == 0){
			 searchLevelVals =[];
		}else{
			 var tempIdStr=""+searchLevelVal;
			 var finalIdStr= tempIdStr.substring(1);
			 searchLevelVals.push(finalIdStr);
		}
		
		/* if ($.inArray('0', financialYrIdArr) != -1)
		{
			var stringIds = "1,2,3";
			strx   = stringIds.split(',');
			financialYrIdArr = financialYrIdArr.concat(strx);
			financialYrIdArr.shift();
			
		} */
		var json = {
			blockLevelId : blockLevelId, 
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
	function getTotalLocationsByScopeId(locScopeId,divId,locationId,searchLevelId)
	{
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdArr = $('#financialYearId').val();
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr =  $('#DepartmentsId').val();
		var searchLevelVals = [];
		if(locationId == null || locationId == 0){
			 searchLevelVals =[];
		}else{
			 var tempIdStr=""+locationId;
			 var finalIdStr= tempIdStr.substring(1);
			 searchLevelVals.push(finalIdStr);
		}
		if(searchLevelId == 9){
			searchLevelId =10;
		}
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
	function getAverageFundForScheme(divId,type,searchLevelId,locationId){
		$("#"+divId).html(spinner);
		var financialYrIdList = $('#financialYearId').val(); 
		var deptIdsArr = $('#DepartmentsId').val();
		//var deptId = 0;
		var sourceId = 0;
		var schemeId = 0;
		var searchLevelVals = [];
		if(locationId == null || locationId == 0){
			 searchLevelVals =[];
		}else{
			 var tempIdStr=""+locationId;
			 var finalIdStr= tempIdStr.substring(1);
			 searchLevelVals.push(finalIdStr);
		}
		if(searchLevelId == 9){
			searchLevelId= 10;
		}
		var json = {
			deptIdsList : deptIdsArr,
			sourceId : sourceId,
			schemeId : schemeId,
			financialYrIdList : financialYrIdList,
			fromDateStr : glStartDate,//"01/06/2013",       
			toDateStr : glEndDate,//"10/06/2020",
			type : type,
			searchLevelId:searchLevelId,
			searchLvlVals:searchLevelVals
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
	function getAverageFundForAnyLevel(levelId,divId,searchLevelId,locationId){
		$("#"+divId).html(spinner);
		var levelValues = [];
		var financialYrIdList = $('#financialYearId').val(); 
		var deptId = 0;
		var sourceId = 0;
		
		var searchLevelVals = [];
		if(locationId == null || locationId == 0){
			 searchLevelVals =[];
		}else{
			 var tempIdStr=""+locationId;
			 var finalIdStr= tempIdStr.substring(1);
			 searchLevelVals.push(finalIdStr);
		}
		if(searchLevelId == 9){
			searchLevelId = 10;
		}
		var deptIdsArr = $('#DepartmentsId').val();
		var json = {
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			fromDateStr : glStartDate,//"01/06/2013",       
			toDateStr : glEndDate,//"10/06/2020", 
			searchLevelId:searchLevelId,
			searchLvlVals:searchLevelVals,
			deptIdsList : deptIdsArr
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
			//$("#DepartmentsId_chosen").find(".search-choice-close").attr("data-option-array-index","0").addClass("deptclicked");
			
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
				//$(".compMultiFinancialYear").val(['1','2']);
				$(".compMultiFinancialYear").val(1);
				$(".compSingleFinancialYear").val(3);
			}
			$("#financialYearId").chosen();
			$("#financialYearId").trigger('chosen:updated');
			$(".compMultiFinancialYear").trigger('chosen:updated');
			$(".compSingleFinancialYear").trigger('chosen:updated');
			$("#financialYearId_chosen").find(".search-choice-close").attr("data-option-array-index","0").addClass("clicked")
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
	var departmentArrGlob =[];
	departmentArrGlob.push("0");
	$(document).on("change","#DepartmentsId",function(){//ara1
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:first-child").addClass("active");
		$('[role="tablist"] li:first-child a').trigger('click');
		$('#tabVill a[href="#villageLevelTable"]').trigger('click');
		$('#tabMan a[href="#mandalLevelTable"]').trigger('click');
		$('#tabSt a[href="#stateLevelTable"]').trigger('click');
		$('#tabDis a[href="#distLevelTable"]').trigger('click');
		$('#tabCons a[href="#consLevelTable"]').trigger('click');
		
		var values = $(this).val();//debugger;
		
		if(values != null && values.length > 0){
			for(var i=0; i<values.length; i++) {
			//console.log(values[i]+" -- "+financialArrGlob+" -- "+$.inArray(values[i], financialArrGlob));
				if($.inArray(values[i], departmentArrGlob) == -1){
					if(values[i] == 0){values=[];values.push("0");
						$('#DepartmentsId').find($('option')).attr('selected',false)
						$("#DepartmentsId").val(0);
						$("#DepartmentsId").trigger('chosen:updated');
						departmentArrGlob = [];
						departmentArrGlob.push("0");
					}else{
						$('#DepartmentsId option:selected').each(function (index, option) { 
							if($(this).val()==0){
								$(option).attr('selected',false); 
								$("#DepartmentsId").trigger('chosen:updated');
							}
							departmentArrGlob=[];
							departmentArrGlob.push($(this).val());
						});
					}
				}
				
			 }
			 
		 onLoadCalls();
		}
	});
	
	//District
	$(document).on("click",".sortingDivDistCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var blockType = getblockType();
		var locationId = $("#distLevelDistrictNames").val();
		/* 
		$("#distLevelDistrictNames").val(0)
		$("#distLevelDistrictNames").trigger("chosen:updated"); */
		var locationLevelType = 3;
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(3,'distLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
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
			getLocationWiseAmountDetails(3,'distLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
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
		var districtId = $("#constLevelDistNames").val();
		var parliamentId = $("#constLevelParliaNames").val();
		var constituencyId = $("#constLevelConstNames").val();
		
		var blockType = getblockTypeCons();
		var locationId = 0;
		var locationLevelType = 0;
		if(districtId !=0 && parliamentId == 0 && constituencyId == 0){
			locationId = districtId;
			locationLevelType =3;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId == 0){
			locationId = parliamentId;
			locationLevelType =9;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId != 0){
			locationId = constituencyId;
			locationLevelType =4;
		}else if(districtId ==0 && parliamentId == 0 && constituencyId == 0){
			locationLevelType =4;
		}
		
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
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
		 getAllSubLocationsBySuperLocationId(locationId,'constLevelParliaNames',4);
		 var constituencyId =$("#constLevelConstNames").val();
		if(constituencyId !=0 || constituencyId == 0){
			$("#constLevelConstNames").empty();
			$("#constLevelConstNames").append('<option value="0">SELECT CONSTITUENCY</option>');	
			$("#constLevelConstNames").trigger('chosen:updated');
		}
		var districtId = $("#constLevelDistNames").val();
		var parliamentId = $("#constLevelParliaNames").val();
		var locationLevelType = 0;
		if(districtId !=0 && parliamentId == 0  || parliamentId == null && constituencyId == 0){
			locationLevelType = 3;
		}else{
			locationLevelType = 4;
		}
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(4,'consLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".parlaiLevelDistCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeCons();
		var locationId =$("#constLevelParliaNames").val();
		 getAllSubLocationsBySuperLocationId(locationId,'constLevelConstNames',4);
		 
		var districtId = $("#constLevelDistNames").val();
		var parliamentId = $("#constLevelParliaNames").val();
		var constituencyId = $("#constLevelConstNames").val();
		var locationLevelType = 0;
		
		if(districtId != 0 && parliamentId != 0 && constituencyId == 0 || constituencyId == null){
			locationLevelType = 9;
		}else{
			locationLevelType = 4;
		}
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
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
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(4,'consLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
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
		var districtId =  $("#mandalLevelDistNames").val();
		var parliamentId =  $("#parliamentLevelConstNames").val();
		var constituencyId = $("#mandalLevelConstNames").val();
		var mandalId = $("#mandalLevelMandalNames").val();
		
		var locationId = 0;
		var locationLevelType = 0;
		if(districtId !=0 && parliamentId == 0 && constituencyId == 0 && mandalId == 0){
			locationId = districtId;
			locationLevelType =3;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId == 0 && mandalId == 0){
			locationId = parliamentId;
			locationLevelType =9;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId != 0 && mandalId == 0){
			locationId = constituencyId;
			locationLevelType =4;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId != 0 && mandalId != 0){
			locationId = mandalId;
			locationLevelType =5;
		}else if(districtId == 0 && parliamentId == 0 && constituencyId == 0 && mandalId == 0){
			locationLevelType =5;
		}
		
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
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
		getAllSubLocationsBySuperLocationId(locationId,'parliamentLevelConstNames',5);
		
		var districtId =  $("#mandalLevelDistNames").val();
		var parliamentId =  $("#parliamentLevelConstNames").val();
		var constituencyId = $("#mandalLevelConstNames").val();
		var mandalId = $("#mandalLevelMandalNames").val();
		
		if(constituencyId !=0 || constituencyId == 0){
			$("#mandalLevelConstNames").empty();
			$("#mandalLevelConstNames").append('<option value="0">SELECT CONSTITUENCY</option>');	
			$("#mandalLevelConstNames").trigger('chosen:updated');
		}
		if(mandalId !=0 || mandalId == 0){
			$("#mandalLevelMandalNames").empty();
			$("#mandalLevelMandalNames").append('<option value="0">SELECT MANDAL</option>');	
			$("#mandalLevelMandalNames").trigger('chosen:updated');
		}
		
		var locationLevelType = 0;
		if(districtId !=0 && parliamentId == null || parliamentId == 0 && constituencyId == 0 && mandalId == 0){
			locationLevelType =3;
		}
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(5,'mandalLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".levelparliamentConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var locationId =$("#parliamentLevelConstNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'mandalLevelConstNames',5);
		
		var districtId =  $("#mandalLevelDistNames").val();
		var parliamentId =  $("#parliamentLevelConstNames").val();
		var constituencyId = $("#mandalLevelConstNames").val();
		var mandalId = $("#mandalLevelMandalNames").val();
		
		if(mandalId !=0 || mandalId == 0){
			$("#mandalLevelMandalNames").empty();
			$("#mandalLevelMandalNames").append('<option value="0">SELECT MANDAL</option>');	
			$("#mandalLevelMandalNames").trigger('chosen:updated');
		}
		
		var locationLevelType = 0;
		if(districtId !=0 && parliamentId != 0 && constituencyId == null || constituencyId == 0 && mandalId == 0){
			locationLevelType =9;
		}
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
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
		getAllSubLocationsBySuperLocationId(locationId,'mandalLevelMandalNames',5);
		
		var districtId =  $("#mandalLevelDistNames").val();
		var parliamentId =  $("#parliamentLevelConstNames").val();
		var constituencyId = $("#mandalLevelConstNames").val();
		var mandalId = $("#mandalLevelMandalNames").val();
		
		var locationLevelType = 0;
		if(districtId !=0 && parliamentId != 0 && constituencyId != 0 && mandalId == null || mandalId == 0){
			locationLevelType =4;
		}
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
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
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(5,'mandalLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(5,'mandalLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	
	
	//Village
	$(document).on("click",".sortingDivVillageCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var blockType = getblockTypeVillage();
		
		var districtId =  $("#villageLevelDistNames").val();
		var parliamentId =  $("#villageLeveParliNames").val();
		var constituencyId = $("#villageLevelConstNames").val();
		var mandalId = $("#villageLevelMandalNames").val();
		var villageId = $("#villageLevelNames").val();
		
		var locationId = 0;
		var locationLevelType = 0;
		if(districtId !=0 && parliamentId == 0 && constituencyId == 0 && mandalId == 0 && villageId == 0){
			locationId = districtId;
			locationLevelType=3;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId == 0 && mandalId == 0 && villageId == 0){
			locationId = parliamentId;
			locationLevelType=9;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId != 0 && mandalId == 0 && villageId == 0){
			locationId = constituencyId;
			locationLevelType=4;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId != 0 && mandalId != 0 && villageId == 0){
			locationId = mandalId;
			locationLevelType=5;
		}else if(districtId != 0 && parliamentId != 0 && constituencyId != 0 && mandalId != 0 && villageId != 0){
			locationId = villageId;
			locationLevelType=6;
		}else if(districtId == 0 && parliamentId == 0 && constituencyId == 0 && mandalId != 0 == villageId != 0){
			locationLevelType=6;
		}
		
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(6,'villageLevlOvervw','overview',sortingType,orderType,locationId,'onLoad');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(6,'villageLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	});	
	$(document).on("change",".villageLevelDistCls",function(){
		var locationId =$("#villageLevelDistNames").val();
	    getAllSubLocationsBySuperLocationId(locationId,'villageLeveParliNames',6);
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeVillage();
		
		var districtId =  $("#villageLevelDistNames").val();
		var parliamentId =  $("#villageLeveParliNames").val();
		var constituencyId = $("#villageLevelConstNames").val();
		var mandalId = $("#villageLevelMandalNames").val();
		var villageId = $("#villageLevelNames").val();
		
		if(constituencyId !=0 || constituencyId == 0){
			$("#villageLevelConstNames").empty();
			$("#villageLevelConstNames").append('<option value="0">SELECT CONSTITUENCY</option>');	
			$("#villageLevelConstNames").trigger('chosen:updated');
		}
		if(mandalId !=0 || mandalId == 0){
			$("#villageLevelMandalNames").empty();
			$("#villageLevelMandalNames").append('<option value="0">SELECT MANDAL</option>');	
			$("#villageLevelMandalNames").trigger('chosen:updated');
		}
		if(villageId !=0 || villageId == 0){
			$("#villageLevelNames").empty();
			$("#villageLevelNames").append('<option value="0">SELECT VILLAGE</option>');	
			$("#villageLevelNames").trigger('chosen:updated');
		}
		
		var locationLevelType =0;
		if(districtId !=0 &&  parliamentId == null || parliamentId == 0 && constituencyId == 0 && mandalId == 0 && villageId == 0){
			locationLevelType=3;
		}
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(6,'villageLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(6,'villageLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".villageLevelParliCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeVillage();
		var locationId =$("#villageLeveParliNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelConstNames',6);
		
		var districtId =  $("#villageLevelDistNames").val();
		var parliamentId =  $("#villageLeveParliNames").val();
		var constituencyId = $("#villageLevelConstNames").val();
		var mandalId = $("#villageLevelMandalNames").val();
		var villageId = $("#villageLevelNames").val();
		
		if(mandalId !=0 || mandalId == 0){
			$("#villageLevelMandalNames").empty();
			$("#villageLevelMandalNames").append('<option value="0">SELECT MANDAL</option>');	
			$("#villageLevelMandalNames").trigger('chosen:updated');
		}
		if(villageId !=0 || villageId == 0){
			$("#villageLevelNames").empty();
			$("#villageLevelNames").append('<option value="0">SELECT VILLAGE</option>');	
			$("#villageLevelNames").trigger('chosen:updated');
		}
		
		if(districtId !=0 && parliamentId != 0 && constituencyId == null || constituencyId == 0 && mandalId == 0 && villageId == 0){
			locationLevelType=9;
		}
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(6,'villageLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(6,'villageLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".villageLevelConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeVillage();
		var locationId =$("#villageLevelConstNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelMandalNames',6);
		
		var districtId =  $("#villageLevelDistNames").val();
		var parliamentId =  $("#villageLeveParliNames").val();
		var constituencyId = $("#villageLevelConstNames").val();
		var mandalId = $("#villageLevelMandalNames").val();
		var villageId = $("#villageLevelNames").val();
		
		if(villageId !=0 || villageId == 0){
			$("#villageLevelNames").empty();
			$("#villageLevelNames").append('<option value="0">SELECT VILLAGE</option>');	
			$("#villageLevelNames").trigger('chosen:updated');
		}
		if(districtId !=0 && parliamentId != 0 && constituencyId != 0 && mandalId == null || mandalId == 0 && villageId == 0){
			locationLevelType=4;
		}
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(6,'villageLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(6,'villageLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".villageLevelMandalCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeVillage();
		var locationId =$("#villageLevelMandalNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelNames',6);
		
		var districtId =  $("#villageLevelDistNames").val();
		var parliamentId =  $("#villageLeveParliNames").val();
		var constituencyId = $("#villageLevelConstNames").val();
		var mandalId = $("#villageLevelMandalNames").val();
		var villageId = $("#villageLevelNames").val();
		
		if(districtId !=0 && parliamentId != 0 && constituencyId != 0 && mandalId != 0 && villageId == null || villageId == 0){
			locationLevelType=5;
		}
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(6,'villageLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(6,'villageLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
	$(document).on("change",".villageLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeVillage();
		var locationId =$("#villageLevelNames").val();
		var locationLevelType = 6;
		
		if(blockType == 'overview'){
			getLocationWiseAmountDetails(6,'villageLevlOvervw','overview',sortingType,orderType,locationId,'selectBox');
		}else if(blockType == 'scheme'){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','scheme',sortingType,orderType,locationId,locationLevelType);
		}else if(blockType == 'deptscheme'){
			 getFinancialYearWiseDeptsWiseSchemeAmountDetails(6,'villageLevlOvervw','deptscheme',sortingType,orderType,locationId,locationLevelType);
		}
	})
		function getLocationWiseAmountAndCountDetails(levelId,divId,type,levelValueStr){
		$("#locDivModal").modal('show');
		$("#"+divId).html(spinner);   
		var levelValues = [];
		var strx   = levelValueStr.split(',');
			levelValues = levelValues.concat(strx);
			
		var financialYrIdList = $('#financialYearId').val();
		/*if(divId == 'locationsModal'){ 
		   financialYrIdList.length = 0;//remove all items 
		   financialYrIdList.push($(".multiDistYearCls").val());
		}*/
		
		var financialYrIdList=[];
		var firstFinancialYearArr=[];
		var secondFinancialYearArr=[];
	   var deptIds =$("#DepartmentsId").val();
		
		var temp=0;
		if(divId == 'locationsModal'){ 
		if(levelId == 3){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear = $(".multiDistYearCls").val();
			var secondFinancialYear = $(".distYearCls").val();
			
			if( firstFinancialYear > secondFinancialYear ){
				temp = firstFinancialYear;
				firstFinancialYear = secondFinancialYear;
				secondFinancialYear = temp;
			}
			financialYrIdList.push(firstFinancialYear);
			financialYrIdList.push(secondFinancialYear);
		}else if(levelId == 4){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear4 = $(".multiConYearCls").val();
			var secondFinancialYear4 = $(".conYearCls").val();
			if( firstFinancialYear4 > secondFinancialYear4 ){
				temp = firstFinancialYear4;
				firstFinancialYear4 = secondFinancialYear4;
				secondFinancialYear4 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear4);
			financialYrIdList.push(secondFinancialYear4);
		}else if(levelId == 5){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear5 = $(".multiMandalYearCls").val();
			var secondFinancialYear5 = $(".mandalYearCls").val();
			if( firstFinancialYear5 > secondFinancialYear5 ){
				temp = firstFinancialYear5;
				firstFinancialYear5 = secondFinancialYear5;
				secondFinancialYear5 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear5);
			financialYrIdList.push(secondFinancialYear5);
		}else if(levelId == 6){
			var firstFinancialYear6 = $(".multiVillageYearCls").val();
			var secondFinancialYear6 = $(".villageYearCls").val();
			if( firstFinancialYear6 > secondFinancialYear6 ){
				temp = firstFinancialYear6;
				firstFinancialYear6 = secondFinancialYear6;
				secondFinancialYear6 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear6);
			financialYrIdList.push(secondFinancialYear6);
		}
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
			url : "getLocationWiseAmountAndCountDetails",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				$("#"+divId).html("");
				if(ajaxresp != null && ajaxresp.length > 0)
				{
					
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
			if($windowWidth < 768)
			{
				table+='<div class="table-responsive">';
			}
			table+='<table class="table table-bordered table-striped table-condensed" id="dataTable'+divId+'" style="width:100%;">';
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
								if(result[i].locationList1[j].financialYearId != 0){
									newYearId = result[i].locationList1[j].financialYearId;
								}else{
                                    newYearId=result[i].locationList1[0].financialYearId;
									var len = result[i].locationList1.length;
									len = len - 1;
									for(var k=1 ; k < len ; k++){
										newYearId = newYearId+","+result[i].locationList1[k].financialYearId;
									}      
								} 
								if(result[i].locationList1[j].count != null && result[i].locationList1[j].count > 0){
									table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+result[i].locationId+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="0" attr_dept_id="0" style="cursor:pointer;color:green;">'+result[i].locationList1[j].count+'</td>';
								}else{
									table+='<td class="text-center no-right-border">-</td>';
								}
								table+='<td class="text-center">'+result[i].locationList1[j].amunt+'</td>';
							}
						table+='</tr>';
					}
				table+='</tbody>';
			table+='</table>';
			if($windowWidth < 768)
			{
				str+='</div>';
			}
		}
		
		$("#"+divId).html(table);
		
		$("#dataTable"+divId).dataTable({
			"iDisplayLength": 15,
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
		
	}

function getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,deptId){ 
	$("#fundModal").modal('show');
	$("#diptNameId").html('Location Wise Fund Overview');
	$("#fundSanctionModal").html(spinner);
    var searchLvlVals = [];
	searchLvlVals.push(levlValue);
    var financialYrIdList =[];
	var financialyr = financialYrId.split(',');
	for(var i = 0; i < financialyr.length; i++)
       {  
			 financialYrIdList.push(financialyr[i]);
       }

    var schemeIdsList = [];
	schemeIdsList.push(schemeId);
    var deptIdsList = [];
	deptIdsList=$('#DepartmentsId').val();
    
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
      buildLocationWiseFundSanctionDetails(result,blockLvlId);
    }
    });
  }
  function buildLocationWiseFundSanctionDetails(result,blockLvlId){
  
  var str = '';
	if($windowWidth < 768)
	{
		str+='<div class="table-responsive">';
	}
	str+='<table class="table table-condensed table-bordered table-striped" id="tableId">';
		str+='<thead>';
			str+='<th class="text-capital">District Name</th>';
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
		 if(result[i].locName != null){
			 str+='<td>'+result[i].locName+'</td>';
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
			if(result[i].filePath != null && result[i].filePath.length > 1){
				str+='<td style="cursor:pointer;" class="viewPdfCls" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get govt order document." attr_filePath="'+result[i].filePath+'"><a>'+result[i].goNoDate+'</a></td>';
			}else{
				str+='<td>'+result[i].goNoDate+'</td>';
			}
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
	str+='</table>';  
	if($windowWidth < 768)
	{
		str+='</div>';
	}
	$("#fundSanctionModal").html(str);   
	$("#tableId").dataTable({
		"iDisplayLength": 15,
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});  
}

$(document).on("click",".compClickCls",function(){
 var blockLvlId = $(this).attr("attr_scope_id");
 var levlValue = $(this).attr("attr_locationIds");
 getLocationWiseAmountAndCountDetails(blockLvlId,'locationsModal','overview',levlValue);
// getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,deptId);
});
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
	}else if(type == "villageComp"){
		compareFundsBetweenFinancialYears(6,'comparionVillageLevlOvervwTable');
	}
	
});

function compareFundsBetweenFinancialYears(levelId,divId){ 
	
	
		var financialYrIdList=[];
		var firstFinancialYearArr=[];
		var secondFinancialYearArr=[];
	   var deptIds =$("#DepartmentsId").val();
		
		var temp=0;
		if(levelId == 3){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear = $(".multiDistYearCls").val();
			var secondFinancialYear = $(".distYearCls").val();
			
			if( firstFinancialYear > secondFinancialYear ){
				temp = firstFinancialYear;
				firstFinancialYear = secondFinancialYear;
				secondFinancialYear = temp;
			}
			financialYrIdList.push(firstFinancialYear);
			financialYrIdList.push(secondFinancialYear);
			firstFinancialYearArr.push(firstFinancialYear);
			secondFinancialYearArr.push(secondFinancialYear);
			if ($.inArray(firstFinancialYear, secondFinancialYear) != -1){
				alert("Please Select Different Year")
				return;
			}
		}else if(levelId == 4){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear4 = $(".multiConYearCls").val();
			var secondFinancialYear4 = $(".conYearCls").val();
			if( firstFinancialYear4 > secondFinancialYear4 ){
				temp = firstFinancialYear4;
				firstFinancialYear4 = secondFinancialYear4;
				secondFinancialYear4 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear4);
			financialYrIdList.push(secondFinancialYear4);
			firstFinancialYearArr.push(firstFinancialYear4);
			secondFinancialYearArr.push(secondFinancialYear4);
			if ($.inArray(firstFinancialYear4, secondFinancialYear4) != -1){
				alert("Please Select Different Year")
				return;
			}
		}else if(levelId == 5){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear5 = $(".multiMandalYearCls").val();
			var secondFinancialYear5 = $(".mandalYearCls").val();
			if( firstFinancialYear5 > secondFinancialYear5 ){
				temp = firstFinancialYear5;
				firstFinancialYear5 = secondFinancialYear5;
				secondFinancialYear5 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear5);
			financialYrIdList.push(secondFinancialYear5);
			firstFinancialYearArr.push(firstFinancialYear5);
			secondFinancialYearArr.push(secondFinancialYear5);
			if ($.inArray(firstFinancialYear5, secondFinancialYear5) != -1){
				alert("Please Select Different Year")
				return;
			}
		}else if(levelId == 6){
			var firstFinancialYear6 = $(".multiVillageYearCls").val();
			var secondFinancialYear6 = $(".villageYearCls").val();
			if( firstFinancialYear6 > secondFinancialYear6 ){
				temp = firstFinancialYear6;
				firstFinancialYear6 = secondFinancialYear6;
				secondFinancialYear6 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear6);
			financialYrIdList.push(secondFinancialYear6);
			firstFinancialYearArr.push(firstFinancialYear6);
			secondFinancialYearArr.push(secondFinancialYear6);
			if ($.inArray(firstFinancialYear6, secondFinancialYear6) != -1){
				alert("Please Select Different Year")
				return;
			}
		}
	
		
			
	 
	 
	 
	 $("#"+divId).html(spinner);
    var json = {
      blockLevelId : levelId,
      financialYrIdList : financialYrIdList,
	  deptIdsList:deptIds
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
		if(ajaxresp !=null && ajaxresp.length>0){
			buildCompareFundsBetweenFinancialYears(ajaxresp,financialYrIdList,firstFinancialYearArr,secondFinancialYearArr,divId,levelId);
		}else{
			$("#"+divId).html("NO DATA AVAILABLE")
		}
		
      }
    
    });
   }
   
   function buildCompareFundsBetweenFinancialYears(result,financialYrIdList,firstFinancialYearArr,secondFinancialYearArr,divId,levelId){
	   
	   var yearLen =financialYrIdList.length;
	   
	    var globalYearObj =  {"1":"2014-2015","2":"2015-2016","3":"2016-2017","4":"2017-2018"};
		 $("#"+divId).removeClass('comparionStyle')
		 $("#"+divId).addClass('comparionStyle1')
	   var str='';
	   if(result !=null && result.length>0){
			if($windowWidth < 768)
			{
				str+='<div class="table-responsive">';
			}
		    str+='<table class="table table-bordered table-striped">';
			if(result[0].rangeList !=null && result[0].rangeList.length>0){
			    str+='<thead>';
				str+='<tr>';
				 str+='<th><span class="first"> '+globalYearObj[firstFinancialYearArr]+'</span><span class="second"> '+globalYearObj[secondFinancialYearArr]+'</span></th>';
			   for(var i in result[0].rangeList){
					   if(yearLen >=3){
							str+='<th colspan="2">'+result[0].rangeList[i].name+'</th>'
						}else{
							if(i == 0){
								str+='<th>Total</th>'
							}else if(i == 1){
								str+='<th>'+result[0].rangeList[i].name+'</th>'
							}else if(result[0].rangeList[i].name == ""){
								str+='<th>'+result[0].rangeList[i].name+'</th>'
							}
							else{
								str+='<th> > '+result[0].rangeList[i].name+'</th>'
							}
							
						}
				}
			   str+='</tr>';
			   str+='</thead>';
			   str+='<tbody>';
				for(var i in result){
				   var verticalRanges = result[i].range.split('/');
				   str+='<tr>';
						if(yearLen >=3){
							str+='<td style="background-color:#EAEBFF">'+verticalRanges[0]+'</td>';
							str+='<td style="background-color:#FFF3E9">'+verticalRanges[1]+'</td>';
						}else{
							if(i == 0){
								 str+='<td>'+result[i].range+'</td>';
							}else{
								 str+='<td> > '+result[i].range+'</td>';
							}
							 
						}
					  if(result[i].rangeList !=null && result[i].rangeList.length>0){
							for(var j in result[i].rangeList){
								var rangeValue='';
								var locationIds='';
								if(result[i].rangeList[j].value == null || result[i].rangeList[j].value == "0/0"){
									var emptyRangeVal = "-,-";
									var emptyLocIds="0,0";
									rangeValue = emptyRangeVal.split(',');
									locationIds = emptyLocIds.split(',');
									
								}else{
									rangeValue = result[i].rangeList[j].value.split('/');
									locationIds = result[i].rangeList[j].locationIds.split('-');
								}
								if(yearLen >=3){
									str+='<td class="compClickCls" style="background-color:#EAEBFF;cursor:pointer;color:green;" attr_locationIds="'+locationIds[0]+'" attr_scope_id="'+levelId+'">'+rangeValue[0]+'</td>';
									 str+='<td class="compClickCls" style="background-color:#FFF3E9;cursor:pointer;color:green;" attr_locationIds="'+locationIds[0]+'" attr_scope_id="'+levelId+'">'+rangeValue[1]+'</td>';
								}else{
									if(result[i].rangeList[j].value > 0){
										str+='<td class="compClickCls" attr_locationIds="'+result[i].rangeList[j].locationIds+'" style="cursor:pointer;color:green;"attr_scope_id="'+levelId+'">'+result[i].rangeList[j].value+'</td>';
									}else{
										str+='<td class="compClickCls" attr_locationIds="'+result[i].rangeList[j].locationIds+'" attr_scope_id="'+levelId+'">-</td>';
									}
									 
								}
							}
						}
					
					str+='</tr>';
				}
				
			   str+='</tbody>';
		   }
			str+='</table>';
			if($windowWidth < 768)
			{
				str+='</div>';
			}
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
		var searchLevelId = 0;
		var searchLevelVals = [];
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
	$(document).on('click','.closeShowPdfCls',function(){
		setTimeout(function(){
			$("body").addClass("modal-open");
		},400);
	});
	$(document).on('click','.viewPdfCls',function(){
		var dbFilePath = $(this).attr("attr_filePath");         
		var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if(extName.trim()=="pdf" || extName.trim()=="PDF"){
			if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i)) || navigator.userAgent.match(/Mobile|Windows Phone|Lumia|Android|webOS|iPhone|iPod|Blackberry|PlayBook|BB10|Opera Mini|\bCrMo\/|Opera Mobi/i)) {
				$("#govtOrderDocumentId").modal("hide");
				window.open('http://www.mydepartments.in/PRRWS/Govt_Orders/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			}else{
				
					$('#govtOrderDocumentId').modal({
						show: true,
						keyboard: false,
						backdrop: 'static'
					});
					str+='<object data="http://www.mydepartments.in/PRRWS/Govt_Orders/'+dbFilePath+'" type="application/pdf" width="100%" height="400px;" internalinstanceid="3" title=""></object>';
					//str+='<iframe src="http://www.mydepartments.in/PRRWS/Govt_Orders/'+dbFilePath+'" width="100%" height="800"></iframe>';
					$("#govtOrderDocumentId").find(".modal-title").html($(this).html());
				$("#govtOrderDocumentBodyId").html(str);       
			}   
		}		
	});
	$(document).on("click","[role='tablist'] a",function(){
		var value = $(this).html();
		if(value == 'Comparision')
		{
		  $(this).closest("[role='tabpanel']").find(".selectboxsShowHide").hide();
		}else{
			 $(this).closest("[role='tabpanel']").find(".selectboxsShowHide").show();
		}
	});
	
	getGovtSchemesDetails();
	function getGovtSchemesDetails(){
		var json = {
		}
		$.ajax({                
			  type:'POST',    
			  url: 'getGovtSchemesDetails',  
			  dataType: 'json',
		  data : JSON.stringify(json),
		  beforeSend :   function(xhr){
				  xhr.setRequestHeader("Accept", "application/json");
				  xhr.setRequestHeader("Content-Type", "application/json");
		}  
		}).done(function(result){
		  if(result != null && result.length > 0){
			//alert("success")
			//$("#GovtSchemesDetailsId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
		  }  
		});
	  }
	  
	  function getGovtSubProgramsDetails(){
		  //var GovtSchemesId = $('#GovtSchemesDetailsId').val();
		  
		var json = {
			govtSchemesId:GovtSchemesId,
		}
		$.ajax({                
			  type:'POST',    
			  url: 'getGovtSubProgramsDetails',  
			  dataType: 'json',
		  data : JSON.stringify(json),
		  beforeSend :   function(xhr){
				  xhr.setRequestHeader("Accept", "application/json");
				  xhr.setRequestHeader("Content-Type", "application/json");
		}  
		}).done(function(result){
		  if(result != null && result.length > 0){
			//alert("success")
			//$("#GovtSubProgramsDetailsId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
		  }  
		});
	  }
	 	  
	  //getSchemeWiseOverviewDetails(4);
	//getSchemeWiseOverviewDetails(3);
	function getSchemeWiseOverviewDetails(subProgId){
		var financialYearIdsList =[];
		financialYearIdsList.push(1);
		financialYearIdsList.push(3);
		financialYearIdsList.push(2);
		var deptIdsList=[];
		var sourceIdsList=[];
		var fromDateStr="01/01/1997";
		var toDateStr="01/01/2027";
		var glSearchLevelId=0;
		var glSearchLevelValue=[];
		var schemeIdsList = [];
		//schemeIdsList.push(1);
		//schemeIdsList.push(2);
		//schemeIdsList.push(3);
		//schemeIdsList.push(4);
		var subProgIds = [];
		subProgIds.push(subProgId);
		
		var json = {
		  financialYrIdList:financialYearIdsList,
		  deptIdsList : deptIdsList,
          sourceIdsList : sourceIdsList,
		  fromDateStr:fromDateStr,
		  toDateStr:toDateStr,
		  schemeIdsList:schemeIdsList,
		  subProgIds:subProgIds,
		  searchLevelId:glSearchLevelId,
		  searchLvlVals:glSearchLevelValue
		}
		$.ajax({
			url : "getSchemeWiseOverviewDetails",     
			data : JSON.stringify(json),
			type : "POST",  
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(result){   
				if(result !=null && result.length>0){
					 $("#distNames").append('<option value="0">SELECT district</option>');
					for(var i in result){
						$("#distNames").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
				$("#distNames").trigger('chosen:updated');
			}
		});
	}
	  
	 