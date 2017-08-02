
var globalFromDate = moment().startOf("month").format('DD-MM-YYYY');
var globalToDate = moment().format('DD-MM-YYYY');

$("#reportrange").daterangepicker({
		opens: 'left',
		startDate: globalFromDate,
		endDate: globalToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		}
	});
$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
	
	globalFromDate = picker.startDate.format('DD-MM-YYYY')
	globalToDate = picker.endDate.format('DD-MM-YYYY')
	//
	
});
$(document).on('click','.calendar_active_cls li', function(){
	var date = $(this).attr("attr_val");
	
	if(date == 'Day')
	{
		globalFromDate = moment().format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}else if(date == 'Week'){
		globalFromDate = moment().subtract(1,'week').format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}else if(date == 'Month'){
		globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
	}
	
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	//
	
});
getCaseCountDiseasesWise();
function getCaseCountDiseasesWise(){
	var diseasesIdArr=[];
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var json = {
		fromDate : '01/07/2017',
		toDate : '31/07/2017', 
		diseasesIdList : diseasesIdArr
    }
    $.ajax({
      url : "getCaseCountDiseasesWise",       
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		  if(ajaxresp != null && ajaxresp.length > 0){
			  buildOverviewBlock(ajaxresp);
		  }else{
			  
		  }
      }
    
    });
}
function buildOverviewBlock(ajaxresp){
	var totalCases = 0;
	var dengueTotalCases = 0;
	var dengueTotalPercent = 0;
	var malariaTotalCases = 0;
	var malariaTotalPercent = 0;
	for(var i in ajaxresp){
		totalCases = totalCases + parseInt(ajaxresp[i].count);
		if(ajaxresp[i].name == "dengue"){
			dengueTotalCases = ajaxresp[i].count;
			dengueTotalPercent = ajaxresp[i].percent;
		}
		if(ajaxresp[i].name == "malaria"){
			malariaTotalCases = ajaxresp[i].count;
			malariaTotalPercent = ajaxresp[i].percent;
		}
	}
	$("#totalCases").html(totalCases);
	$("#dengueTotalCases").html(dengueTotalCases);
	$("#dengueTotalPercent").html(dengueTotalPercent);
	$("#malariaTotalCases").html(malariaTotalCases);
	$("#malariaTotalPercent").html(malariaTotalPercent);
	if(ajaxresp[0].distCountList != null && ajaxresp[0].distCountList.length > 0){
		var distLen = ajaxresp[0].distCountList.length;
		if(distLen > 5){
			distLen = 4;
		}
		buildTopFiveLocation(distLen,ajaxresp[0].distCountList,"allDiseasesTotDist","distId");
	}
	if(ajaxresp[0].mandalCountList != null && ajaxresp[0].mandalCountList.length > 0){
		var distLen = ajaxresp[0].mandalCountList.length;
		if(distLen > 5){
			distLen = 4;
		}
		buildTopFiveLocation(distLen,ajaxresp[0].mandalCountList,"allDiseasesTotMandal","mandalId");
	}
	if(ajaxresp[0].distCountForDengueList != null && ajaxresp[0].distCountForDengueList.length > 0){
		var distLen = ajaxresp[0].distCountForDengueList.length;
		if(distLen > 5){
			distLen = 4;
		}
		buildTopFiveLocation(distLen,ajaxresp[0].distCountForDengueList,"topDistForDengue","distDengueId");
	}
	if(ajaxresp[0].mandalCountForDengueList != null && ajaxresp[0].mandalCountForDengueList.length > 0){
		var distLen = ajaxresp[0].mandalCountForDengueList.length;
		if(distLen > 5){
			distLen = 4;
		}
		buildTopFiveLocation(distLen,ajaxresp[0].mandalCountForDengueList,"topMandalForDengue","mandalDengueId");
	}
	if(ajaxresp[0].distCountForMalariaList != null && ajaxresp[0].distCountForMalariaList.length > 0){
		var distLen = ajaxresp[0].distCountForMalariaList.length;
		if(distLen > 5){
			distLen = 4;
		}
		buildTopFiveLocation(distLen,ajaxresp[0].distCountForMalariaList,"topDistForMalaria","distMalariaId");
	}
	if(ajaxresp[0].mandalCountForMalariaList != null && ajaxresp[0].mandalCountForMalariaList.length > 0){
		var distLen = ajaxresp[0].mandalCountForMalariaList.length;
		if(distLen > 5){
			distLen = 4;
		}
		buildTopFiveLocation(distLen,ajaxresp[0].mandalCountForMalariaList,"topMandalForMalaria","mandalMalariaId");
	}
}
function buildTopFiveLocation(distLen,locArray,tableDivId,tableId){
	var str = '';
		str+='<table class="table table-bordered table-hover" id="'+tableId+'">';
		str+='<thead>';
		  str+='<tr>';
			str+='<th>District</th>';
			str+='<th>Cases</th>'; 
			str+='<th>%</th>';
		  str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(i = 0; i <= distLen; i++){
			str+='<tr>';
				str+='<td>'+locArray[i].locationName+'</td>';
				str+='<td>'+locArray[i].count+'</td>';
				str+='<td>'+locArray[i].percent+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#"+tableDivId).html(str);
		$("#"+tableId).dataTable({
			"aaSorting": [1,"desc"],
			"searching": false,
			"paging": false
		});   
}
getCaseCountLocationWise();
function getCaseCountLocationWise(){
	var diseasesIdArr=[];
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var json = {
		fromDate : '01/07/2017',
		toDate : '31/07/2017', 
		diseasesIdList : diseasesIdArr,
		scopeId : 5,
		superLocationId : 11     
    }
    $.ajax({
      url : "getCaseCountLocationWise",       
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		  if(ajaxresp != null && ajaxresp.length > 0){
			  buildLocationBlock(ajaxresp);
		  }else{
			  
		  }
      }
    
    });
}
function buildLocationBlock(ajaxresp){
	var str = '';
	str+='<table class="table table-bordered table-hover" id="locationTableId">';
	str+='<thead>';
	  str+='<tr>';
		str+='<th>District</th>';
		str+='<th>Cases</th>';  
		str+='<th>%</th>';
	  str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in ajaxresp){
		str+='<tr>';
			str+='<td>'+ajaxresp[i].name+'</td>';
			str+='<td>'+ajaxresp[i].count+'</td>';
			str+='<td>'+ajaxresp[i].percent+'</td>';
		str+='</tr>';
	}    
	str+='</tbody>';
	str+='</table>';
	$("#locationWiseCaseCount").html(str);
	$("#locationTableId").dataTable({
		"aaSorting": [1,"desc"]
	});   
}
getCaseCountDateWise();
function getCaseCountDateWise(){
	var diseasesIdArr=[];
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var json = {
		fromDate : '01/07/2017',  
		toDate : '31/07/2017',   
		diseasesIdList : diseasesIdArr,
		rangeType : 'day'          
    }
    $.ajax({
      url : "getCaseCountDateWise",           
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		  if(ajaxresp != null && ajaxresp.length > 0){
			  buildGraph(ajaxresp,diseasesIdArr);
		  }else{
			  
		  }
      }
    });
}
function buildGraph(ajaxresp,diseasesIdArr){
	var chronologicalList = [];
	var caseCountList = [];
	for(var i in ajaxresp){
		chronologicalList.push(ajaxresp[i].name);
		caseCountList.push(ajaxresp[i].count);
	}  
	var name = '';
	if(diseasesIdArr.length == 2){
		name = "Dengue & Malaria";
	}else if(diseasesIdArr[0] == 1){
		name = "Malaria";
	}else if(diseasesIdArr[0] == 2){
		name = "Dengue";
	}
	Highcharts.chart('chronologicalId', {
    chart: {
        type: 'line'
    },
    title: {
        text: 'Number Of Cases Registered'
    },
    subtitle: {
        text: 'Source: Govt Of Andhrapradesh'
    },
    xAxis: {
        categories: chronologicalList
    },
    yAxis: {
        title: {
            text: 'Number Of Cases'  
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: true
            },
            enableMouseTracking: false
        }
    },
    series: [{
        name: name,
        data: caseCountList
    }]
});
}