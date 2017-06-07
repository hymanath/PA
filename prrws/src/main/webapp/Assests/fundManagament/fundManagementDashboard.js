+function ($) {
	onLoadCalls();
	onLoadClicks();
	onLoadInitialisations();
	
	///Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations
	function onLoadCalls()
	{
		getLocationWiseFundDetails(3,'highest','highFundDist');
		getLocationWiseFundDetails(4,'highest','highFundCons');
		getLocationWiseFundDetails(3,'lowest','lowFundDist');
		getLocationWiseFundDetails(4,'lowest','lowFundCons');
		getLocationWiseAmountDetails(2,'stateLevlOvervw');
		getLocationWiseAmountDetails(3,'distLevlOvervw');
		getLocationWiseAmountDetails(4,'consLevlOvervw');
		getLocationWiseAmountDetails(5,'mandalLevlOvervw');
		getSchemeWiseLocationWiseAmountDetails(4,'SchemeWiseTotal');
		getSchemeWiseHighestAndLowestFund('highest','highFundScheme');
		getSchemeWiseHighestAndLowestFund('lowest','lowFundScheme');
		getTotalFunds('totFund');
		//getTotalSchemes(4,'mandalLevlOvervw');
		// getSchemeWiseHighestAndLowestFund("highest");
		// getSchemeWiseHighestAndLowestFund("lowest");
		// getTotalLocationsByScopeId(4);
		// getTotalLocationsByScopeId(5);
		// getTotalLocationsByScopeId(6);
		//getAverageFundForAnyLevel();
		//getAverageFundForScheme();
	}
	function onLoadClicks()
	{
		
	}
	function onLoadInitialisations()
	{
		
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
					//useHTML: true,
					//align: 'left',
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					formatter: function() {
					
						//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
						//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
						return (this.total);
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
		var levelValues = [];
		var financialYrIdArr = [1,2];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr = [];
  
		  var json = {
			levelId : 4, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : "01-06-2013",       
			toDateStr : "10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,
			type:dataType
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
			},
			error: function(request,error) { 
				//alert(request.responseText);
				//alert(error);
			}
		});
	}
	function buildLocationWiseFundDetails(ajaxresp,divId)
	{
		var str='';
		str+='<div class="col-sm-12">';
			//str+='<h4 class="panel-title">High Funded District</h4>';
			if(ajaxresp.name != null)
			{
				str+='<h4 class="panel-title text-capital district-name"> '+ajaxresp.name+'</h4>';
			}
			str+='<h3><i class="fa fa-inr"></i>: '+ajaxresp.totalAmt+'<small class="text-success">'+ajaxresp.perc+' %</small></h3>';
			if(ajaxresp.subList.length > 0)
			{
				str+='<ul class="list-inline">';
					for(var i in ajaxresp.subList)
					{
						str+='<li class="text-center">';
							str+='<p class="text-muted">'+ajaxresp.subList[i].name+'</p>';
							str+='<p class="text-success"><small>'+ajaxresp.subList[i].percentage+'%</small></p>';
							str+='<p class="panel-title"><i class="fa fa-inr"></i>: '+ajaxresp.subList[i].total+'</p>';
						str+='</li>';
					}
				str+='</ul>';
			}
		str+='</div>';
		$("#"+divId).html(str);
	}
	function getLocationWiseAmountDetails(levelId,divId){
		var levelValues = [];
		var financialYrIdList = [1];
		var json = {
			levelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			sortingType : "name",      //name,count    
			order : "desc",   //asc,desc
			fromDateStr : "01/06/2017",       
			toDateStr : "10/06/2017"
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
				buildLocationWiseAmountDetails(ajaxresp,divId);
			},
			error : function(request,error){
				alert(error);
			}
		});
	}
	function buildLocationWiseAmountDetails(result,divId)
	{
		var str='';
		str+='<div class="scroller'+divId+'">';
			str+='<div class="chart'+divId+'"></div>';
		str+='</div>';
		$("#"+divId).html(str);
		if(result !=null && result.length>0)
		{
			var locationNamesArr=[];
			
			var financialAmount =[];
			var mainJosnObjArr=[];
			
			var amountArr =[];
			for(var i in result){
				var obj={}
				locationNamesArr.push(result[i].locationName);
				
				if(result[i].locationList1 !=null &&  result[i].locationList1.length>0){
					for(var j in result[i].locationList1){
						
						amountArr.push(result[i].locationList1[j].amount)
						financialAmount.push(result[i].locationList1[j].amount)
						obj={
							name:"A::",data:amountArr,color:"#FF872C"
						}
						
						
					}
				}
				
				
				
				/* if(financialAmount != null && financialAmount.length > 0){
					mainJosnObjArr.push({name:'All Financial Year',data:financialAmount,color:"#FF872C"});  
				}
				if(amountArr != null && amountArr.length > 0){
					mainJosnObjArr.push({name:'2014-2015',data:amountArr,color:"#FF872C"});  
					
				} */
				
			}
			mainJosnObjArr.push(obj);
			console.log(mainJosnObjArr)
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
	
	function getTotalFunds(divId)
	{
		  var levelValues = [];
		  var financialYrIdArr = [1,2];
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr = [];
		  
		  var json = {
			levelId : 4, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : "01-06-2013",       
			toDateStr : "10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr
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
			},
			error: function(request,error) { 
		
		
			}
		});
   }
	function getSchemeWiseLocationWiseAmountDetails(levelId,divId){
		var levelValues = [];
		var financialYrIdArr = [1,2];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var deptIdsArr = [];

		var json = {
			levelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : "01-06-2013",       
			toDateStr : "10-06-2020",
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
					buildSchemewiseReport(ajaxresp,divId);
				else
					alert('No data available..');
				},
			error : function(request,error){
				alert(error);
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
	function getTotalSchemes(levelId)
	{
		  var levelValues = [];
		  var financialYrIdArr = [1,2];
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr = [];
		  
		  var json = {
			levelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : "01-06-2013",       
			toDateStr : "10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr
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
				
			},
			error: function(request,error) { 
				//alert(request.responseText);
				//alert(error);
			}
		});
	}
	function buildTotalSchemes()
	{
		
	}
	function getSchemeWiseHighestAndLowestFund(type,divId)
	{
		  var levelValues = [];
		  var financialYrIdArr = [1,2];
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr = [];
		  
		  var json = {
			levelId : 4, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : "01-06-2013",       
			toDateStr : "10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,
			type:type
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
			},
			error: function(request,error) { 
				//alert(request.responseText);
				//alert(error);
			}
		});
	}
	function getTotalLocationsByScopeId(locScopeId)
	{
		  var levelValues = [];
		  var financialYrIdArr = [1,2];
		  var sourceIdsArr = [];
		  var schemeIdsArr = [];
		  var deptIdsArr = [];
  
		  var json = {
			levelId : 4, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdArr,
			fromDateStr : "01-06-2013",       
			toDateStr : "10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr
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
			},
			error: function(request,error) { 
			//alert(request.responseText);
			//alert(error);
			}
        });
	}	
	function getAverageFundForScheme(){
		var financialYrIdList = [1]; 
		var deptId = 0;
		var sourceId = 0;
		var schemeId = 0;
		var json = {
			deptId : deptId,
			sourceId : sourceId,
			schemeId : schemeId,
			financialYrIdList : financialYrIdList,
			fromDateStr : "01/06/2017",       
			toDateStr : "10/06/2017"
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
				alert(ajaxresp);
			},
			error : function(request,error){
				alert(error);
			}
		});
	}
	function getAverageFundForAnyLevel(){
		var levelValues = [];
		var financialYrIdList = [1]; 
		var deptId = 0;
		var sourceId = 0;
		var json = {
			levelId : 3, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			fromDateStr : "01/06/2017",       
			toDateStr : "10/06/2017"   
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
				alert(ajaxresp);
			},
			error : function(request,error){
				alert(error);
			}
		});
	}
}(jQuery);