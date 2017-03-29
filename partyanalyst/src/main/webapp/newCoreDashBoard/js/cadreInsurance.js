    var globalStateIdForCadreInsurance=1;
	var cadreInsuranceFDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');
	var cadreInsuranceTDate = moment().format('DD-MM-YYYY');
	var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
	function globalInsuranceCalls()
	{
		getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails();
		cadreInsuranceClickActions();
		if($("[expand-icon='cadreInsurance']").hasClass("glyphicon-resize-small"))
		{
			alert(1)
		}
	}
	function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions)
	{
		'use strict';
		
		$('#'+id).highcharts({
			chart: type,
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			xAxis: xAxis,
			yAxis: yAxis,
			tooltip: {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.2f}%</b><br/>'
			},
			plotOptions: plotOptions,
			legend: legend,
			series: data
		});
	}
	function cadreInsuranceClickActions()
	{
		$(document).on("click",".cadreInsuranceCDate li",function(){
			var date = $(this).html();
			var momentDate = '';
			if(date == 'Today')
			{
				cadreInsuranceFDate = moment().format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == 'Week'){
				cadreInsuranceFDate = moment().subtract(1,'week').format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == 'Month'){
				cadreInsuranceFDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == '3 Month'){
				cadreInsuranceFDate = moment().subtract(3,'month').startOf("month").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == '6 Month'){
				cadreInsuranceFDate = moment().subtract(6,'month').startOf("month").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == '9 Month'){
				cadreInsuranceFDate = moment().subtract(9,'month').startOf("month").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == 'All Time'){
				cadreInsuranceFDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}
			getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails()
		});
		$(document).on("click",".insuranceRefresh",function(){
			globalInsuranceCalls()
		});
		$(document).on("click",".insuraceStatusWiseComplaints",function(){
			var $this = $(this);
			var status = $this.attr("attr_status");
			var issueType = $this.attr("attr_issueType");
			var companyid = $this.attr("attr_companyId");
			getInsuraceStatusWiseComplaintsDetails(status,issueType,companyid);
		});
	}
	function getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails()
	{
		$("#insuraceCompanyAndTypeOfIssueWiseComplaintsDetails").html(spinner);
		var jsObj={ 
			activityMemberId: 44, 
			cadreYearId		: 4, 
			stateId 		: 1, 
			fromDateStr 	: cadreInsuranceFDate, 
			toDateStr		: cadreInsuranceTDate
		};
		
		$.ajax({  
			type : 'GET',
			url : 'getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			if(result != null && result.length > 0)
			{
				buildInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(result);
			}else{
				$("#insuraceCompanyAndTypeOfIssueWiseComplaintsDetails").html('NO DATA');
			}
			
		});
	}
	function buildInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(result)
	{
		var str='';
		str+='<div class="panel panel-default panelNew bg_ED">';
			var totalHospital = 0;
			var totalDeath = 0;
			var totalCount = 0;
			for(var i in result)
			{
				totalDeath = result[i].deathCount + totalDeath;
				totalHospital = result[i].hospitalizationCount + totalHospital;
			}
			totalCount = totalDeath + totalHospital
			str+='<table class="table table-bordered bg_ED m_top10">';
				str+='<tr>';
					str+='<td rowspan="2" style="vertical-align:middle;width:50%;"><h3>'+totalCount+'</h3><p class="text-capital text-muted">total claims submitted</p></td>';
					str+='<td><h3><span class="insuraceStatusWiseComplaints" attr_status="" attr_issueType="Hospitalization" attr_companyId="0">'+totalHospital+'</span></h3><p class="text-capital text-muted">total hospital</p></td>';
				str+='</tr>';
				str+='<tr>';
					str+='<td><h3><span class="insuraceStatusWiseComplaints" attr_status="" attr_issueType="Death" attr_companyId="0">'+totalDeath+'</span></h3><p class="text-capital text-muted">total death</p></td>';
				str+='</tr>';
			str+='</table>';
		str+='</div>';
		
		str+='<div class="panel-group cadreInsurancePanel" id="accordion" role="tablist" aria-multiselectable="true">';
			for(var i in result)
			{
				if(result[i].totalCount != null && result[i].totalCount > 0)
				{
					str+='<div class="panel panel-default panelNew bg_ED">';
						str+='<div class="panel-heading bg_ED" role="tab" id="headingInsurance'+i+'">';
							if(i == 0)
							{
								str+='<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseInsurance'+i+'" aria-controls="collapseInsurance'+i+'">';
							}else{
								str+='<a role="button" class="collapsed collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseInsurance'+i+'" aria-controls="collapseInsurance'+i+'">';
							}
								str+='<h5>'+result[i].totalCount+' <small class="text-success">20%</small></h5>';
								str+='<small class="text-capital">'+result[i].name+'</small>';
							str+='</a>';
						str+='</div>';
						if(i == 0)
						{
							str+='<div id="collapseInsurance'+i+'" class="panel-collapse collapse in" aria-labelledby="headingInsurance'+i+'">';
						}else{
							str+='<div id="collapseInsurance'+i+'" class="panel-collapse collapse" aria-labelledby="headingInsurance'+i+'">';
						}
						
							str+='<div class="panel-body bg_ED notesArrow">';
								str+='<table class="table table-condensed">';
									
									str+='<thead>';
										str+='<th></th>';
										str+='<th><span class="label label-success label-white">Total</label></th>';
										str+='<th><span class="label label-success label-white">Hospital</label></th>';
										str+='<th><span class="label label-success label-white">Death</label></th>';
									str+='</thead>';
									for(var j in result[i].subList)
									{
										str+='<tr>';
											str+='<td class="text-capital">'+result[i].subList[j].name+'</td>';
											str+='<td><span class="insuraceStatusWiseComplaints" attr_status="'+result[i].name+'" attr_issueType="" attr_companyId="'+result[i].subList[j].id+'">'+result[i].subList[j].totalCount+'</span></td>';
											str+='<td><span class="insuraceStatusWiseComplaints" attr_status="'+result[i].name+'" attr_issueType="Hospitalization" attr_companyId="'+result[i].subList[j].id+'">'+result[i].subList[j].hospitalizationCount+'</span></td>';
											str+='<td><span class="insuraceStatusWiseComplaints" attr_status="'+result[i].name+'" attr_issueType="Death" attr_companyId="'+result[i].subList[j].id+'">'+result[i].subList[j].deathCount+'</span></td>';
										str+='</tr>';
									}
								str+='</table>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				}
			}
			
		str+='</div>';
	$("#insuraceCompanyAndTypeOfIssueWiseComplaintsDetails").html(str);
	}
	
	function getInsuraceStatusWiseComplaintsDetails(status,issueType,companyid)
	{
		$("#insuraceStatusWiseComplaintsDetails").html(spinner);
		var jsObj={ 
			activityMemberId: 44, 
			cadreYearId		: 4, 
			stateId 		: 1, 
			fromDateStr 	: cadreInsuranceFDate, 
			toDateStr		: cadreInsuranceTDate,
			statusStr		: status, 
			companyId		: companyid, 
			issueType		: issueType
		};
		$.ajax({  
			type : 'GET',
			url : 'getInsuraceStatusWiseComplaintsDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			console.log(result)
			if(result != null && result.length > 0)
			{
				buildInsuraceStatusWiseComplaintsDetails(result);
			}else{
				$("#insuraceStatusWiseComplaintsDetails").html('NO DATA');
			}
		});
	}
	function buildInsuraceStatusWiseComplaintsDetails(result)
	{
		
		$("#insuraceStatusWiseComplaintsDetails").html(str);
		
	}
	
	/* 2nd block graph code 
	/* var data = 
			series: [{
				name: 'Intimations',
				data: [5, 3]
			}, {
				name: 'Forwarded',
				data: [2, 2]
			}, {
				name: 'settled',
				data: [3, 4]
			}, {
				name: 'rejected',
				data: [3, 4]
			}] 
			
			
		var id = '';
		var type = {
			type: 'column',
			backgroundColor:'transparent'
		}
		var legend = {
			enabled: false,
		}
		var yAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: null
			},
			stackLabels: {
					enabled: true,
				style: {
					fontWeight: 'bold',
					color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
				}
			}
		}
		var xAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: ['Death', 'Hospital']
		}
		
		highcharts(id,type,xAxis,yAxis,legend,data); */
	
    /* Cadre Insurance Call */
	// getUserTypeWiseTotalCadreInsuranceComplainctCnt();
	// getSelectedChildMemberCadreInsuranceComplainctCnt("","");
	 function getUserTypeWiseTotalCadreInsuranceComplainctCnt(){
		  var jsObj ={ 
		             activityMemberId : 44,//globalActivityMemberId
					 userTypeId : 2,//globalUserTypeId
					 stateId : globalStateIdForCadreInsurance,
					 fromDate : "",
					 toDate : "",
					 cadreEnrollmentYearId :0
				  }
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseTotalCadreInsuranceComplainctCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   console.log(result);
		});
		
	} 
	function getSelectedChildMemberCadreInsuranceComplainctCnt(firstChildUserTypeIdString,childUserType){
	 // $("#alertChildActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	//  $("#userTypeWiseChildDtlsTabForAlertId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 // $("#candidateLocationAlertDtlsStatusWiseDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 // var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	 var childUserTypeIdsArray = [3];
	 //  var parentActivityMemberId = globalActivityMemberId;
        var jsObj = { 
					   parentActivityMemberId : 44,
					   childUserTypeIdsArray : childUserTypeIdsArray,
					   reportType :"selectedUserType",
					   stateId : globalStateIdForCadreInsurance,           
					   fromDate: "",        
					   toDate :	"",
					   cadreEnrollmentYearId : 0
				  }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildMemberCadreInsuranceComplainctCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			console.log(result);
		});
}
function getDirectChildMemberCadreInsuranceComplainctCnt(firstChildUserTypeIdString,childUserType){
	 var childUserTypeIdsArray = [3];
	 //  var parentActivityMemberId = globalActivityMemberId;
        var jsObj = { 
					   parentActivityMemberId : 44,
					   childUserTypeIdsArray : childUserTypeIdsArray,
					   reportType :"selectedUserType",
					   stateId : globalStateIdForCadreInsurance,           
					   fromDate: "",        
					   toDate :	"",
					   cadreEnrollmentYearId : 0
				  }
	  $.ajax({
			type : 'POST',
			url : 'getDirectChildMemberCadreInsuranceComplainctCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			console.log(result);
		});
}