	
	//var globalApStateIdForInsuranceComparisonBlock=1;
	var cadreInsuranceFDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');
	var cadreInsuranceTDate = moment().format('DD-MM-YYYY');
	
	var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
	function getCadreStateVal(){
		 var globalStateId = ''; 
			$('.cadreInsuranceStateCls li').each(function(i, obj){
				if($(this).hasClass("active")){
					 globalStateId = $(this).attr("attr_id");
				}
			});
		return globalStateId;
	}
	function getCadreYearVal(){
		 var getCadreval = ''; 
		$('.yearWiseDtsCls').each(function(i, obj){
		 
			  getCadreval = $(this).val();
		 
		});
		return getCadreval;
	}	
	function getdateType(){
		 var getdate = ''; 
		$('.cadreInsuranceCDate li').each(function(i, obj){
		 
			  getdate = $(this).html();
		 
		});
		return getdate;
	}
	function globalInsuranceCalls()
	{
		if(globalselectedUserType != "STATE"){
			$(".cadreInsuranceStateCls li").removeClass("active");
			$(".cadreInsuranceStateCls li:nth-child(2)").addClass("active");
		}
		var getCadreval = getCadreYearVal();
		var getdate = getdateType();
		var getCadreval = getCadreYearVal();
		
		//first Block
		getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(getCadreval);
		//Expand Second Block
		getUserTypeWiseTotalCadreInsuranceComplainctCnt(getdate,getCadreval);
	}   
	function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip)
	{
		'use strict';
		
		$('#'+id).highcharts({
			colors:['#8D8057','#00B17D'],
			chart: type,
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			xAxis: xAxis,
			yAxis: yAxis,
			tooltip: tooltip,
			plotOptions: plotOptions,
			legend: legend,
			series: data
		});
	}
		
		
		
	
		$(document).on("click",".insuranceRefresh",function(){
			globalInsuranceCalls()
		});
		
		//More Icon Click View
		$(document).on("click",".moreCadreInsuranceIcon",function(){
			$(this).addClass("unExpandInsuranceCls")
			$(".cadreInsuranceCategoryStatus,.cadreInsuranceCategoryStatusTs").hide();
			$(".cadreInsuranceDetailedblock,.cadreInsuranceLi").show();
			$(".cadreInsuranceComparisonblock").hide();
			$(".showHideConstNoCls").hide(); 
			var getCadreval = getCadreYearVal();
			getInsuranceCompanyWiseOverviewAndStatusDetails(getCadreval);
			//Ap District & Constituency Category Wise Calls
			getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
			getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
			//Ts District & Constituency Category Wise Calls
			getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
			getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
		});
		$(document).on("click",".unExpandInsuranceCls",function(){
			$(".cadreInsuranceDetailedblock,.cadreInsuranceLi").hide();
			$(".cadreInsuranceComparisonblock").hide();
			$(this).removeClass("unExpandInsuranceCls");
		});	
		//Detailed & Comparison Block Click View
		$(document).on("click",".cadreInsuranceLi li",function(){
			var blockName = $(this).html();
			if(blockName == "Detailed")
			{
				var getCadreval = getCadreYearVal();
				getInsuranceCompanyWiseOverviewAndStatusDetails(getCadreval);
				getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
				getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
				$(".cadreInsuranceDetailedblock").show();
				$(".cadreInsuranceComparisonblock").hide();
			}else if(blockName == "Comparison")
			{
				getAllItsSubUserTypeIdsByParentUserTypeForInsurance();
				$(".cadreInsuranceComparisonblock").show();
				$(".cadreInsuranceDetailedblock").hide();
			}  
		});
		//Detailed Block category Check Box In AP
		$(document).on("click",".cadeInsuranceCatAndStsCls",function(){	
           if($(this).is(':checked')){
				var status = $(this).attr("attr_status");
				if(status == 'categoryStatus')
				{
					$(".cadreInsuranceCategory").hide();
					$(".cadreInsuranceCategoryStatus").show();
					$("#distGraphHeadingId").html("andhra pradesh district wise - status overview");
					$("#constGraphHeadingId").html("andhra pradesh constituency wise - status overview");
					getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter')
					getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				}else if(status == 'category')    
				{
					$(".cadreInsuranceCategory").show();
					$(".cadreInsuranceCategoryStatus").hide();
					$("#distGraphHeadingId").html("andhra pradesh district wise - category overview");
					$("#constGraphHeadingId").html("andhra pradesh constituency wise - category overview");
					getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
					getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				}
			}
		});
		//Detailed Block category Check Box In TS
		$(document).on("click",".cadeInsuranceCatTs",function(){         
			var status = $(this).attr("attr_status");
			if(status == 'category')
			{
				$(".cadreInsuranceCategoryTs").show();
				$(".cadreInsuranceCategoryStatusTs").hide();
				getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
				getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
			}else if(status == 'categoryStatus')
			{
				$(".cadreInsuranceCategoryTs").hide();
				$(".cadreInsuranceCategoryStatusTs").show();
				getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
				getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','asc',0,'filter');
			}
		});
		//Detailed Block category Submit Button click In AP
		function getInsuranceData(){
			var selectionType=$("input:radio[name=cadeInsuranceCat]:checked").val();
			if(selectionType == "category"){
				getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
			}else{
				getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
			}
		}
		//Detailed Block category Submit Button click In TS
		function getInsuranceDataForTs(){
			var selectionType=$("input:radio[name=cadeInsuranceCatTs]:checked").val();
			if(selectionType == "category"){
				getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
				getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
			}else{
				getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');     
				getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
			}
		}
		
		
		$(document).on("click",".cadreInsuranceStateCls li",function(){
			var globalStateId1 = $(this).attr('attr_id');
			
			var getCadreval = getCadreYearVal();
			var date='';
			$('.cadreInsuranceCDate li').each(function(i, obj){
				 if($(this).hasClass('active')){
						date = $(this).html();
				 }
			});
			getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(getCadreval);
			getUserTypeWiseTotalCadreInsuranceComplainctCnt(date,getCadreval);
			getInsuranceCompanyWiseOverviewAndStatusDetails(getCadreval);
			/* for details block */
			if(globalStateId1 ==1){
				$("#tsConstBlockId").hide();
				$("#tsDistrictBlockId").hide();
				$("#apDistrictBlockId").show();
				$("#apConstBlockId").show();
								
				var selectionType=$("input:radio[name=cadeInsuranceCat]:checked").val();
				if(selectionType == "category"){
					getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
					getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				}else{
					getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
					getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				}
				getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
				getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','asc',0,'filter');
			}else if(globalStateId1 == 2){
				$("#tsConstBlockId").show();
				$("#tsDistrictBlockId").show();
				$("#apDistrictBlockId").hide();
				$("#apConstBlockId").hide();
				
				var selectionType=$("input:radio[name=cadeInsuranceCatTs]:checked").val();
				if(selectionType == "category"){
					getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
					getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
				}else{
					getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');     
					getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
				}
				getAllItsSubUserTypeIdsByParentUserTypeForInsurance();
			}else{
				$("#tsConstBlockId").show();
				$("#tsDistrictBlockId").show();
				$("#apDistrictBlockId").show();
				$("#apConstBlockId").show();
				
				var selectionType=$("input:radio[name=cadeInsuranceCat]:checked").val();
				if(selectionType == "category"){
					getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
					getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				}else{
					getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
					getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				}
				var selectionType=$("input:radio[name=cadeInsuranceCatTs]:checked").val();
				if(selectionType == "category"){
					getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
					getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
				}else{
					getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');     
					getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
				}
				
				getAllItsSubUserTypeIdsByParentUserTypeForInsurance();
			}
			
			
		});
		
		
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
			}else if(date == '3 Months'){
				cadreInsuranceFDate = moment().subtract(3,'month').startOf("month").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == '6 Months'){
				cadreInsuranceFDate = moment().subtract(6,'month').startOf("month").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == '9 Months'){
				cadreInsuranceFDate = moment().subtract(9,'month').startOf("month").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}else if(date == 'All Time'){
				cadreInsuranceFDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');
				cadreInsuranceTDate = moment().format('DD-MM-YYYY');
			}
			$(".liclsChange").removeClass("active");
			$(".addActiveCls").addClass("active");
			var getCadreval = getCadreYearVal();
			var blockType='';
				$('.cadreInsuranceLi li').each(function(i, obj){
					 if($(this).hasClass('active')){
							blockType = $(this).attr("attr_type")
					 }
				});
			getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(getCadreval);
			if($(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				getUserTypeWiseTotalCadreInsuranceComplainctCnt(date,getCadreval);
				getInsuranceCompanyWiseOverviewAndStatusDetails(getCadreval);
				
				if($(".moreCadreInsuranceIcon").hasClass("unExpandInsuranceCls")){
					if(blockType == "Detailed"){
						
						/* for details block */
						var selectionType=$("input:radio[name=cadeInsuranceCat]:checked").val();
						if(selectionType == "category"){
							
							getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
							getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
						}else{
							getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
							getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
						}
						
						var selectionType=$("input:radio[name=cadeInsuranceCatTs]:checked").val();
						if(selectionType == "category"){
							getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
							getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
						}else{
							getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');     
							getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
						}
					}else{
						//For Comparison BLock
						getAllItsSubUserTypeIdsByParentUserTypeForInsurance();
					}
				}
			}
		});
		
		
		$(document).on("change",".yearWiseDtsCls",function(){
			var getdate = getdateType();
			var getCadreval = getCadreYearVal();
			
			getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(getCadreval);
			getUserTypeWiseTotalCadreInsuranceComplainctCnt(getdate,getCadreval);
			getInsuranceCompanyWiseOverviewAndStatusDetails(getCadreval);
			
			/* for details block */
			var selectionType=$("input:radio[name=cadeInsuranceCat]:checked").val();
			if(selectionType == "category"){
				getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
			}else{
				getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
				getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',0,'filter');
			}
			var selectionType=$("input:radio[name=cadeInsuranceCatTs]:checked").val();
			if(selectionType == "category"){
				getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');
				getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
			}else{
				getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',0,'filter');     
				getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',0,'filter');
			}
			
			getAllItsSubUserTypeIdsByParentUserTypeForInsurance();
		});
		
		$(document).on("click",".insuraceStatusWiseComplaints",function(){
			var $this = $(this);
			var status = $this.attr("attr_status");
			var issueType = $this.attr("attr_issueType");
			var companyid = $this.attr("attr_companyId");
			var statusName = $this.attr("attr_name");
			
			$("#insuranceHeadingId").html(status);
			$('#insuranceModal').modal({
				show: true,
				keyboard: false,
				backdrop: 'static'
			});
			
			getInsuraceStatusWiseComplaintsDetails(status,issueType,companyid);
			getLagDaysInsuranceComplaintsCounts(status,issueType,companyid);
			
			/* getStatusTrackingDetailsOfInsuranceByComplaint(41635);
			getRemarksByComplaint(41635);
			getComplaintScanCopyDetails(41635);
			getComplaintResponsesByComplaint(41635); */
		});
		
		
		
		
		 
		
		
		$(document).on("change","#locationListForCategoryId",function(){
			var locationId = $("#locationListForCategoryId").val();
			getDistrictWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',locationId,"");
		});

		$(document).on("change","#locationListForStatusId",function(){
			var locationId = $("#locationListForStatusId").val();
			getDistrictWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',locationId,'');    
		});

		$(document).on("change","#constListForCategoryId",function(){
			var locationId = $("#constListForCategoryId").val();
			getConstituencyWiseThenCategoryWiseInsuranceMemberCount('insuredMember','desc',locationId,'');
		});

		$(document).on("change","#constListForStatusId",function(){
			var locationId = $("#constListForStatusId").val();
			getConstituencyWiseThenStatusWiseInsuranceMemberCount('insuredMember','desc',locationId,'');
		});
		$(document).on("change","#locationIdForCategoryDist",function(){
			var locationId = $("#locationIdForCategoryDist").val();
			getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('district','insuredMember','desc',locationId,'')
		});
		$(document).on("change","#locationIdForCategoryCons",function(){
			var locationId = $("#locationIdForCategoryCons").val();
			getLocationWiseThenCategoryWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',locationId,'')
		});
		$(document).on("change","#locationIdForStatusDist",function(){
			var locationId = $("#locationIdForStatusDist").val();
			getLocationWiseThenStatusWiseInsuranceMemberCountForTS('district','insuredMember','desc',locationId,'')
		});
		$(document).on("change","#locationIdForStatusCons",function(){
			var locationId = $("#locationIdForStatusCons").val();
			getLocationWiseThenStatusWiseInsuranceMemberCountForTS('constituency','insuredMember','desc',locationId,'')
		});
		
	       
	function getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(getCadreval)
	{
		var globalStateId= getCadreStateVal();
	
		$("#insuraceCompanyAndTypeOfIssueWiseComplaintsDetails").html(spinner);
		var jsObj={ 
			activityMemberId: globalActivityMemberId, 
			cadreYearId		: getCadreval, 
			stateId 		: globalStateId, 
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
			str+='<table class="table table-bordered bg_ED m_top10 table_padding">';
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
								str+='<a role="button" class="collapseDebatesIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseInsurance'+i+'" aria-controls="collapseInsurance'+i+'">';
							}else{
								str+='<a role="button" class="collapsed collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseInsurance'+i+'" aria-controls="collapseInsurance'+i+'">';
							}
								str+='<h4 class="text-capital f_16 text_bold">'+result[i].name+' &nbsp; <span class="m_top5 f_14">[Total count :'+result[i].totalCount+' &nbsp; <span style="color:green">'+result[i].percentage+'%</span>]</span></h4>';
								//str+='<h5 class="m_top5 f_14">[Total count :'+result[i].totalCount+' &nbsp; <span style="color:green">'+result[i].percentage+'%</span>]</h5>';
							str+='</a>';
						str+='</div>';
						if(i == 0)
						{
							str+='<div id="collapseInsurance'+i+'" class="panel-collapse collapse" aria-labelledby="headingInsurance'+i+'">';
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
		var enrollmentId = $(".yearWiseDtsCls").val();
		var globalStateId= getCadreStateVal();
		$("#insuraceStatusWiseComplaintsDetails").html(spinner);
		var jsObj={ 
			activityMemberId: globalActivityMemberId, 
			cadreYearId		: enrollmentId, 
			stateId 		: globalStateId, 
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
		var str='';
		str+='<table class="table table-condensed table-bordered tableStyleInsurance" id="InsuraceStatusWiseComplaintsDetailsDataTab">';
			str+='<thead class="text-capital">';
				str+='<th>cid</th>';
				str+='<th>person details</th>';
				str+='<th>subject</th>';
				str+='<th >description</th>';
				str+='<th>type of issue</th>';
				str+='<th>status</th>';
				str+='<th>posted date</th>';
				str+='<th>last updated date</th>';
				str+='<th>last comment</th>';
			str+='</thead>';
			for(var i in result)
			{
				str+='<tr>';
					if(result[i].complaintId != null && result[i].complaintId >0)
					{
						str+='<td>'+result[i].complaintId+'</td>';
					}else{
						str+='<td>-</td>';
					}
					
					str+='<td>';
						if(result[i].name != null && result[i].name !="")
						{
							str+='<p>N:'+result[i].name+'</p>';
						}else{
							str+='<p>N: -</p>';
						}
						if(result[i].mobileNo != null && result[i].mobileNo>0)
						{
							str+='<p>M: '+result[i].mobileNo+'</p>';
						}else{
							str+='<p>M: -</p>';
						}
						if(result[i].districtName != null && result[i].districtName !="")
						{
							str+='<p>D: '+result[i].districtName+'</p>';
						}else{
							str+='<p>D: -</p>';
						}
						if(result[i].constituencyName != null && result[i].constituencyName !="")
						{
							str+='<p>C: '+result[i].constituencyName+'</p>';
						}else{
							str+='<p>C: -</p>';
						}
						if(result[i].mandalName != null && result[i].mandalName !="")
						{
							str+='<p>M: '+result[i].mandalName+'</p>';
						}else{
							str+='<p>M: -</p>';
						}
						if(result[i].villageName != null && result[i].villageName !="")
						{
							str+='<p>V: '+result[i].villageName+'</p>';
						}else{
							str+='<p>V: -</p>';
						}
						
						
					str+='</td>';
						if(result[i].subject!=null && result[i].subject !=""){
							str+='<td >'+result[i].subject+'</td>';
						}else{
							str+='<td>-</td>';
						}
						
						if(result[i].description != null && result[i].description !="")
						{
							if(result[i].description!=null && result[i].description.length>30)
							{
								str+='<td ><span class="tooltopDescCls" style="cursor:pointer;"  data-toggle="tooltip" data-placement="top" title="'+result[i].description+'" >'+result[i].description.substring(0,30)+'...</span></td>';
							}else{
								str+='<td >'+result[i].description+'</td>';
							}
							
						}else{
							str+='<td >-</td>';
						}
						
						if(result[i].typeOfIssue != null)
						{
							str+='<td>'+result[i].typeOfIssue+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].status != null)
						{
							str+='<td>'+result[i].status+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].postedDate != null)
						{
							str+='<td>'+result[i].postedDate+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].updatedDate != null)
						{
							str+='<td>'+result[i].updatedDate+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].comment != null)
						{
							str+='<td>'+result[i].comment+'</td>';
						}else{
							str+='<td>-</td>';
						}
				str+='</tr>';
			}
		str+='</table>';
		$("#insuraceStatusWiseComplaintsDetails").html(str);
		$(".tooltopDescCls").tooltip();
		$("#InsuraceStatusWiseComplaintsDetailsDataTab").dataTable();
		
	}
	function getLagDaysInsuranceComplaintsCounts(status,issueType,companyid)
	{
		var enrollmentId = $(".yearWiseDtsCls").val();
		$("#lagDaysInsuranceComplaintsCounts").html(spinner);
		var globalStateId= getCadreStateVal();
		var jsObj={ 
			activityMemberId: globalActivityMemberId, 
			cadreYearId		: enrollmentId, 
			stateId 		: globalStateId, 
			statusStr		: status, 
			companyId		: companyid, 
			issueType		: issueType
		};
		$.ajax({  
			type : 'GET',
			url : 'getLagDaysInsuranceComplaintsCountsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			console.log(result)
			if(result != null)
			{
				buildLagDaysInsuranceComplaintsCounts(result);
			}else{
				$("#lagDaysInsuranceComplaintsCounts").html('NO DATA');
			}
		});
	}
	function buildLagDaysInsuranceComplaintsCounts(result)
	{
		var str='';
		str+='<table class="table" style="background-color:#F2F2F2">';
			str+='<tr>';
				str+='<td>';
					str+='<h3>'+result.todayCount+'</h3>';
					str+='<p class="text-capitalize">today</p>';
				str+='</td>';
				str+='<td>';
					str+='<h3>'+result.weekCount+'</h3>';
					str+='<p class="text-capitalize">week</p>';
				str+='</td>';
				str+='<td>';
					str+='<h3>'+result.monthCount+'</h3>';
					str+='<p class="text-capitalize">month</p>';
				str+='</td>';
				str+='<td>';
					str+='<h3>'+result.threeMnthsCount+'</h3>';
					str+='<p class="text-capitalize">3 month</p>';
				str+='</td>';
				str+='<td>';
					str+='<h3>'+result.sixMnthsCount+'</h3>';
					str+='<p class="text-capitalize">6 month</p>';
				str+='</td>';
				str+='<td>';
					str+='<h3>'+result.nineMonthsCount+'</h3>';
					str+='<p class="text-capitalize">9 month</p>';
				str+='</td>';
				str+='<td>';
					str+='<h3>'+result.overAllCount+'</h3>';
					str+='<p class="text-capitalize">all time</p>';
				str+='</td>';
			str+='</tr>';
		str+='</table>';
		$("#lagDaysInsuranceComplaintsCounts").html(str);
	}
	
	
	  
	function getUserTypeWiseTotalCadreInsuranceComplainctCnt(date,getCadreval){
		$("#userTypeWiseTotalCadreInsuranceComplainctCnt").html(spinner);
		var globalStateId= getCadreStateVal();
		var jsObj ={ 
				activityMemberId : globalActivityMemberId,
				userTypeId : globalUserTypeId,
				stateId : globalStateId,
				fromDate : cadreInsuranceFDate,
				toDate : cadreInsuranceTDate,
				cadreEnrollmentYearId :getCadreval
			}
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseTotalCadreInsuranceComplainctCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0)
			{
				buildUserTypeWiseTotalCadreInsuranceComplainctCntTopFive(result,date);
				globalUserWiseMemberRslt = result;
			}else{
				$("#userTypeWiseTotalCadreInsuranceComplainctCnt").html("NO DATA AVAILABLE");
			}
		});
		
	} 
	function buildUserTypeWiseTotalCadreInsuranceComplainctCntTopFive(result,date)
	{
		$("#userTypeWiseTotalCadreInsuranceComplainctCnt").html('');
		var str='';
		str+='<div class="row headingColorApp">';
		str+='<span class="headingColor">'+date+'</span>';
		str+='<ul class="list-inline m_top10">';
			str+='<li><span class="Intimations"></span> Intimations</li>';
			str+='<li><span class="Forwarded"></span> Forwarded</li>';
			str+='<li><span class="Settled"></span> Settled</li>';
			str+='<li><span class="Rejcted"></span> Rejcted</li>';
		str+='</ul>';
		for(var i in result){
			var candidateList = result[i];
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
			str+='<h4 class="panel-title">'+candidateList[0].userType+'</h4>';
			str+='<ul class="list-inline m_top10">';
			for(var j in candidateList)
			{
				if(j != 4)
				{
					str+='<li style="width:20%;border-right:1px solid #ddd;">';
				}else{
					str+='<li style="width:20%;">';
				}
				
					str+='<p style="font-size:10px">'+candidateList[j].name+'</p>';
					str+='<div id="userTypeWiseTotalCadreInsuranceComplainctCntGraph'+i+''+j+'" style="height:200px;"></div>';
				str+='</li>';
				if(j == 4)
				{
					break;
				}
			}
			str+='</ul>';
			str+='</div>';
		}
		str+='</div>';
		$("#userTypeWiseTotalCadreInsuranceComplainctCnt").html(str);
		
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
		
		var plotOptions =  {
             series: {
				stacking: 'normal'
			}
        }
		
		var tooltip = {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
			}
		
		for(var i in result){
			var candidateList = result[i];
			for(var j in candidateList)
			{	
				var mainJosnObjArr = [];
				for(var k in candidateList[j].subList)
				{
					var intimationsArr = [];
					var forwardedArr = [];
					var settledArr = [];
					var rejectedArr = [];
					for(var l in candidateList[j].subList[k].subList)
					{
						
						if(candidateList[j].subList[k].subList[l].id == 1 )
						{
							intimationsArr.push({y:candidateList[j].subList[0].subList[l].totalCount});
							intimationsArr.push({y:candidateList[j].subList[1].subList[l].totalCount});
						}else if(candidateList[j].subList[k].subList[l].id == 2 )
						{
							forwardedArr.push({y:candidateList[j].subList[0].subList[l].totalCount});
							forwardedArr.push({y:candidateList[j].subList[1].subList[l].totalCount});
						}else if(candidateList[j].subList[k].subList[l].id == 3 )
						{
							settledArr.push({y:candidateList[j].subList[0].subList[l].totalCount});
							settledArr.push({y:candidateList[j].subList[1].subList[l].totalCount});
						}else if(candidateList[j].subList[k].subList[l].id == 4 )
						{
							rejectedArr.push({y:candidateList[j].subList[0].subList[l].totalCount});
							rejectedArr.push({y:candidateList[j].subList[1].subList[l].totalCount});
						}
					}
					
					 
				}
				mainJosnObjArr.push({name:'Intimation',data:intimationsArr,color:"#306F8F"});  
				mainJosnObjArr.push({name:'Forwarded',data:forwardedArr,color:"#F39C12"});  
				mainJosnObjArr.push({name:'Settled',data:settledArr,color:"#2DCC70"});  
				mainJosnObjArr.push({name:'Rejected',data:rejectedArr ,color:"#E74B3B"});  
				var id = 'userTypeWiseTotalCadreInsuranceComplainctCntGraph'+i+''+j+'';
				highcharts(id,type,xAxis,yAxis,legend,mainJosnObjArr,plotOptions,tooltip);
				if(j == 4)
				{
					break;
				}
			}
			
		}
	}
	function buildUserTypeWiseTotalCadreInsuranceComplainctCntTopPoor(result,date)
	{
		$("#userTypeWiseTotalCadreInsuranceComplainctCnt").html('');
		var str='';
		str+='<div class="row headingColorApp">';
		str+='<span class="headingColor">'+date+'</span>';
		str+='<ul class="list-inline m_top10">';
			str+='<li><span class="Intimations"></span> Intimations</li>';
			str+='<li><span class="Forwarded"></span> Forwarded</li>';
			str+='<li><span class="Settled"></span> Settled</li>';
			str+='<li><span class="Rejcted"></span> Rejcted</li>';
		str+='</ul>';
		for(var i in result){
			var candidateList = result[i];
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
			str+='<h4 class="panel-title">'+candidateList[0].userType+'</h4>';
			str+='<ul class="list-inline m_top10">';
			var countVar = 0;
			if(candidateList !=null && candidateList.length>0){
				for(var j = candidateList.length -1; j >= 0; j--)
				{
					
						str+='<li style="width:20%;">';
						str+='<p style="font-size:10px">'+candidateList[j].name+'</p>';
						str+='<div id="userTypeWiseTotalCadreInsuranceComplainctCntGraphPoor'+i+''+j+'" style="height:200px;"></div>';
					str+='</li>';
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				}
			}
			
			str+='</ul>';
			str+='</div>';
		}
		str+='</div>';
		$("#userTypeWiseTotalCadreInsuranceComplainctCnt").html(str);
		
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
		
		var plotOptions =  {
             series: {
				stacking: 'normal'
			}
        }
		
		var tooltip= {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
		}
			
		for(var i in result){
			var candidateList = result[i];
			for(var j = candidateList.length -1; j >= 0; j--)
			{	
				var mainJosnObjArr = [];
				for(var k in candidateList[j].subList)
				{
					var intimationsArr = [];
					var forwardedArr = [];
					var settledArr = [];
					var rejectedArr = [];
					for(var l in candidateList[j].subList[k].subList)
					{
						
						if(candidateList[j].subList[k].subList[l].id == 1 )
						{
							intimationsArr.push({y:candidateList[j].subList[0].subList[l].totalCount});
							intimationsArr.push({y:candidateList[j].subList[1].subList[l].totalCount});
						}else if(candidateList[j].subList[k].subList[l].id == 2 )
						{
							forwardedArr.push({y:candidateList[j].subList[0].subList[l].totalCount});
							forwardedArr.push({y:candidateList[j].subList[1].subList[l].totalCount});
						}else if(candidateList[j].subList[k].subList[l].id == 3 )
						{
							settledArr.push({y:candidateList[j].subList[0].subList[l].totalCount});
							settledArr.push({y:candidateList[j].subList[1].subList[l].totalCount});
						}else if(candidateList[j].subList[k].subList[l].id == 4 )
						{
							rejectedArr.push({y:candidateList[j].subList[0].subList[l].totalCount});
							rejectedArr.push({y:candidateList[j].subList[1].subList[l].totalCount});
						}
					}
					
					 
				}
				mainJosnObjArr.push({name:'Intimation',data:intimationsArr,color:"#306F8F"});  
				mainJosnObjArr.push({name:'Forwarded',data:forwardedArr,color:"#F39C12"});  
				mainJosnObjArr.push({name:'Settled',data:settledArr,color:"#2DCC70"});  
				mainJosnObjArr.push({name:'Rejected',data:rejectedArr ,color:"#E74B3B"});  
				var id = 'userTypeWiseTotalCadreInsuranceComplainctCntGraphPoor'+i+''+j+'';
				highcharts(id,type,xAxis,yAxis,legend,mainJosnObjArr,plotOptions,tooltip);
				countVar =countVar+1;
				if (countVar === 5) {
					break;
				}
			}
			
		}
	}
	
	$(document).on("click",".liclsChange",function(){
		var memberType=$(this).attr("attr_value");
		var getdate = getdateType();
		 if(memberType != null && memberType == "strong"){
			 buildUserTypeWiseTotalCadreInsuranceComplainctCntTopFive(globalUserWiseMemberRslt,getdate)
		 }else if(memberType == "poor"){
			buildUserTypeWiseTotalCadreInsuranceComplainctCntTopPoor(globalUserWiseMemberRslt,getdate)
		 }
	});
	
	 function getStatusTrackingDetailsOfInsuranceByComplaint(complaintId)
		{
			var jsObj={ 
				complaintId		: complaintId
			};
			$.ajax({  
				type : 'GET',
				url : 'getStatusTrackingDetailsOfInsuranceByComplaintAction.action',  
				dataType : 'json',
				data : {task :JSON.stringify(jsObj)} 
			}).done(function(result){
				console.log(result)
				
			});
		}
		
		function getComplaintScanCopyDetails(complaintId)
		{
			var jsObj={ 
				complaintId		: complaintId
			};
			$.ajax({  
				type : 'GET',
				url : 'getComplaintScanCopyDetailsAction.action',  
				dataType : 'json',
				data : {task :JSON.stringify(jsObj)} 
			}).done(function(result){
				console.log(result)
				
			});
		}
		
		function getRemarksByComplaint(complaintId)
		{
			var jsObj={ 
				complaintId		: complaintId
			};
			$.ajax({  
				type : 'GET',
				url : 'getRemarksByComplaintAction.action',  
				dataType : 'json',
				data : {task :JSON.stringify(jsObj)} 
			}).done(function(result){
				console.log(result)
				
			});
		}
		
		function getComplaintResponsesByComplaint(complaintId)
		{
			var jsObj={ 
				complaintId		: complaintId
			};
			$.ajax({  
				type : 'GET',
				url : 'getComplaintResponsesByComplaintAction.action',  
				dataType : 'json',
				data : {task :JSON.stringify(jsObj)} 
			}).done(function(result){
				console.log(result)
				
			});
		}
		
		function getInsuranceCompanyWiseOverviewAndStatusDetails(getCadreval)
		{
			$("#insuranceCompanyWiseOverviewAndStatusDetails").html(spinner);
			var jsObj={ 
				activityMemberId: globalActivityMemberId, 
				cadreYearId		: getCadreval, 
				fromDateStr 	: cadreInsuranceFDate, 
				toDateStr		: cadreInsuranceTDate
			};
			$.ajax({  
				type : 'GET',
				url : 'getInsuranceCompanyWiseOverviewAndStatusDetailsAction.action',  
				dataType : 'json',
				data : {task :JSON.stringify(jsObj)} 
			}).done(function(result){
				if(result != null && result.length > 0)
				{
					buildInsuranceCompanyWiseOverviewAndStatusDetails(result);
				}else{
					$("#insuranceCompanyWiseOverviewAndStatusDetails").html("NO DATA AVAILABLE");
				}
				
			});
		}
		function buildInsuranceCompanyWiseOverviewAndStatusDetails(result)
		{
			var globalStateId= getCadreStateVal();
			var str='';
			str+='<div class="row">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12 apBlockCls">';
					str+='<h4 class="panel-title headingColorApp"><span>ANDHRA PRADESH</span></h4>';
				str+='</div>';
				str+='<div class="col-md-4 col-xs-12 col-sm-12 m_top10 apBlockCls">';
					str+='<h4 class="panel-title text-capital">overview</h4>';
						str+='<ul class="list-inline m_top10">';
							str+='<li><span class="deathClrCls"></span> Death</li>';
							str+='<li><span class="hospitalClrCls"></span> Hospital</li>';
						str+='</ul>';
					str+='<div id="overviewInsurance" style="height:300px;"></div>';
				str+='</div>';
				str+='<div class="col-md-4 col-xs-12 col-sm-4 apBlockCls">';
					str+='<h4 class="panel-title text-capital">hospitalization</h4>';
					str+='<ul class="list-inline m_top10">';
						str+='<li><span class="Intimations"></span> Intimations</li>';
						str+='<li><span class="Forwarded"></span> Forwarded</li>';
						str+='<li><span class="Settled"></span> Settled</li>';
						str+='<li><span class="Rejcted"></span> Rejcted</li>';
					str+='</ul>';
					str+='<div id="hospitalizationInsurance" style="height:300px"></div>';
				str+='</div>';
				str+='<div class="col-md-4 col-xs-12 col-sm-4 apBlockCls">';
					str+='<h4 class="panel-title text-capital">death</h4>';
					str+='<ul class="list-inline m_top10">';
						str+='<li><span class="Intimations"></span> Intimations</li>';
						str+='<li><span class="Forwarded"></span> Forwarded</li>';
						str+='<li><span class="Settled"></span> Settled</li>';
						str+='<li><span class="Rejcted"></span> Rejcted</li>';
					str+='</ul>';
					str+='<div id="deathInsurance" style="height:300px"></div>';
				str+='</div>';
				str+='<div class="col-md-12 col-xs-12 col-sm-12 tsBlockCls">';  
					str+='<h4 class="panel-title headingColorApp"><span>TELANGANA</span></h4>';
				str+='</div>';
				str+='<div class="col-md-4 col-xs-12 col-sm-12 m_top10 tsBlockCls">';   
					str+='<h4 class="panel-title text-capital">overview</h4>';
					str+='<ul class="list-inline m_top10">';
							str+='<li><span class="deathClrCls"></span> Death</li>';
							str+='<li><span class="hospitalClrCls"></span> Hospital</li>';
						str+='</ul>';
					str+='<div id="overviewInsuranceTs" style="height:300px"></div>';
				str+='</div>';
				str+='<div class="col-md-4 col-xs-12 col-sm-4 tsBlockCls">';
					str+='<h4 class="panel-title text-capital">hospitalization</h4>';
					str+='<ul class="list-inline m_top10">';
						str+='<li><span class="Intimations"></span> Intimations</li>';
						str+='<li><span class="Forwarded"></span> Forwarded</li>';
						str+='<li><span class="Settled"></span> Settled</li>';
						str+='<li><span class="Rejcted"></span> Rejcted</li>';
					str+='</ul>';
					str+='<div id="hospitalizationInsuranceTs" style="height:300px"></div>';
				str+='</div>';
				str+='<div class="col-md-4 col-xs-12 col-sm-4 tsBlockCls">';  
					str+='<h4 class="panel-title text-capital">death</h4>';
					str+='<ul class="list-inline m_top10">';
						str+='<li><span class="Intimations"></span> Intimations</li>';
						str+='<li><span class="Forwarded"></span> Forwarded</li>';
						str+='<li><span class="Settled"></span> Settled</li>';
						str+='<li><span class="Rejcted"></span> Rejcted</li>';
					str+='</ul>';
					str+='<div id="deathInsuranceTs" style="height:300px"></div>';
				str+='</div>';
			str+='</div>';
			
			$("#insuranceCompanyWiseOverviewAndStatusDetails").html(str);
			
			if(globalStateId ==1){
				$(".apBlockCls").show();
				$(".tsBlockCls").hide();
			}else if(globalStateId ==2){
				$(".tsBlockCls").show();
				$(".apBlockCls").hide();
			}else{
				$(".apBlockCls").show();
				$(".tsBlockCls").show();
			}
			
			var overviewList = []
			var namesArr = [];
			var deathArray = [];
			var hospitalArray = [];
			var amountArry =[];
			if(result[0].overViewList != null && result[0].overViewList.length > 0)
			{
				for(var i in result[0].overViewList)
				{
					
					hospitalArray.push({"y":result[0].overViewList[i].hospitalizationCount})
					deathArray.push({"y":result[0].overViewList[i].deathCount})
					amountArry.push(result[0].overViewList[i].amount)
					var Obj = {
						name: 'Death',
						data: deathArray
					}
					var Obj1 = {
						name: 'Hospitalization',
						data: hospitalArray
					}
					namesArr.push(result[0].overViewList[i].name);
				}
				overviewList.push(Obj);
				overviewList.push(Obj1);
			}
			
			/* Overview Insurance*/
			var data = overviewList
			var id = 'overviewInsurance';
			var type = {
				type: 'bar',
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
					
					useHTML: true,
					qTotals: amountArry,
					enabled: true,
					align: 'left',
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					formatter: function() {
						
						return '<span style="top: 16px; position: absolute;"><br/>'+this.total+ '(<i class="fa fa-inr" aria-hidden="true"></i>'+this.options.qTotals[this.x]+')/-</span>';
					}
					
				}
				
			}
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: namesArr
			}
			var plotOptions =  {
	             series: {
					stacking: 'normal',
					pointWidth: 30,
					gridLineWidth: 15
				}
	        }
			var tooltip= {
				valueSuffix: ' ',
				shared:true
			}
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip); 
			/* Deaths Insurance*/
			var deathList = []
			var namesArr1 = [];
			var intimationsArr = []
			var forwardedArr = []
			var settledArr = []
			var rejectedArr = []
			var DamountArry = []
			if(result[0].deathList != null && result[0].deathList.length > 0)
			{
				for(var i in result[0].deathList)
				{
					
					intimationsArr.push({"y":result[0].deathList[i].subList[0].count,color:"#306F8F"})
					forwardedArr.push({"y":result[0].deathList[i].subList[1].count,color:"#F39C12"})
					settledArr.push({"y":result[0].deathList[i].subList[2].count,color:"#2DCC70"})
					rejectedArr.push({"y":result[0].deathList[i].subList[3].count,color:"#E74B3B"})
					DamountArry.push(result[0].deathList[i].amount)
					var Obj = {
						name: 'Intimation',
						data: intimationsArr
					}
					var Obj1 = {
						name: 'Forwarded',
						data: forwardedArr
					}
					var Obj2 = {
						name: 'Settled',
						data: settledArr
					}
					var Obj3 = {
						name: 'Rejected',
						data: rejectedArr
					}
					namesArr1.push(result[0].deathList[i].name);
				}
				deathList.push(Obj);
				deathList.push(Obj1);
				deathList.push(Obj2);
				deathList.push(Obj3);
			}
			
			var data1 = deathList;
			var id1 = 'deathInsurance';
			var tooltip= {
				valueSuffix: ' ',
				shared:true
			};
			var yAxisD = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				},
				stackLabels: {
					useHTML: true,
					align: 'left',
					qTotals: DamountArry,
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					formatter: function() {
						
						return '<span style="top: 16px; position: absolute;"><br/>'+this.total+ '(<i class="fa fa-inr" aria-hidden="true"></i>'+this.options.qTotals[this.x]+')/-</span>';
					}
				}
			}
			highcharts(id1,type,xAxis,yAxisD,legend,data1,plotOptions,tooltip); 
			
			/* Hospitalization Insurance*/
			var hospitalizationList = []
			var intimationsArr1 = []
			var forwardedArr1 = []
			var settledArr1 = []
			var rejectedArr1 = []
			var HamountArry = []
			if(result[0].hospitalizationList != null && result[0].hospitalizationList.length > 0)
			{
				for(var i in result[0].hospitalizationList)
				{
					intimationsArr1.push({"y":result[0].hospitalizationList[i].subList[0].count,color:"#306F8F"})
					forwardedArr1.push({"y":result[0].hospitalizationList[i].subList[1].count,color:"#F39C12"})
					settledArr1.push({"y":result[0].hospitalizationList[i].subList[2].count,color:"#2DCC70"})
					rejectedArr1.push({"y":result[0].hospitalizationList[i].subList[3].count,color:"#E74B3B"})
					HamountArry.push(result[0].hospitalizationList[i].amount)
					var Obj = {
						name: 'Intimation',
						data: intimationsArr1
					}
					var Obj1 = {
						name: 'Forwarded',
						data: forwardedArr1
					}
					var Obj2 = {
						name: 'Settled',
						data: settledArr1
					}
					var Obj3 = {
						name: 'Rejected',
						data: rejectedArr1
					}
					namesArr1.push(result[0].hospitalizationList[i].name);
				}
				hospitalizationList.push(Obj);
				hospitalizationList.push(Obj1);
				hospitalizationList.push(Obj2);
				hospitalizationList.push(Obj3);
			}
			
			var data2 = hospitalizationList;
			var id2 = 'hospitalizationInsurance';
			var tooltip= {
				valueSuffix: ' ',
				shared:true
			};
			var yAxisH = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				},
				stackLabels: {
					useHTML: true,
					align: 'left',
					qTotals: HamountArry,
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					formatter: function() {
						
						return '<span style="top: 16px; position: absolute;"><br/>'+this.total+ '(<i class="fa fa-inr" aria-hidden="true"></i>'+this.options.qTotals[this.x]+')/-</span>';
					}
				}
			}
			highcharts(id2,type,xAxis,yAxisH,legend,data2,plotOptions,tooltip); 
			
			/*Telangana*/
			var overviewListTs = []
			var namesArrTs = [];
			var deathArrayTs = []
			var hospitalArrayTs = []
			var amountArryT = []
			if(result[1].overViewList != null && result[1].overViewList.length > 0)
			{
				for(var i in result[1].overViewList)
				{
					
					hospitalArrayTs.push({"y":result[1].overViewList[i].hospitalizationCount})
					deathArrayTs.push({"y":result[1].overViewList[i].deathCount})
					amountArryT.push(result[1].overViewList[i].amount)
					var Obj = {
						name: 'Death',
						data: deathArrayTs
					}
					var Obj1 = {
						name: 'Hospitalization',
						data: hospitalArrayTs
					}
					namesArrTs.push(result[1].overViewList[i].name);
				}
				overviewListTs.push(Obj);
				overviewListTs.push(Obj1);
			}
			
			/* Overview Insurance*/
			var dataTs = overviewListTs
			var idTs = 'overviewInsuranceTs';
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: namesArr
			}
			var yAxisT = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				},
				stackLabels: {
					
					useHTML: true,
					qTotals: amountArryT,
					enabled: true,
					align: 'left',
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					formatter: function() {
						
						return '<span style="top: 16px; position: absolute;"><br/>'+this.total+ '(<i class="fa fa-inr" aria-hidden="true"></i>'+this.options.qTotals[this.x]+')/-</span>';
					}
					
				}
				
			}
			var tooltip= {
				valueSuffix: ' ',
				shared:true
			};
			highcharts(idTs,type,xAxis,yAxisT,legend,dataTs,plotOptions,tooltip); 
			/* Deaths Insurance*/
			var deathListTs = []
			var namesArrTs1 = [];
			var intimationsArrTs = []
			var forwardedArrTs = []
			var settledArrTs = []
			var rejectedArrTs = []
			var amountArryTD = []
			if(result[1].deathList != null && result[1].deathList.length > 0)
			{
				for(var i in result[1].deathList)
				{
					
					intimationsArrTs.push({"y":result[1].deathList[i].subList[0].count,color:"#306F8F"})
					forwardedArrTs.push({"y":result[1].deathList[i].subList[1].count,color:"#F39C12"})
					settledArrTs.push({"y":result[1].deathList[i].subList[2].count,color:"#2DCC70"})
					rejectedArrTs.push({"y":result[1].deathList[i].subList[3].count,color:"#E74B3B"})
					amountArryTD.push(result[1].deathList[i].amount)
					var Obj = {
						name: 'Intimation',
						data: intimationsArrTs
					}
					var Obj1 = {
						name: 'Forwarded',
						data: forwardedArrTs
					}
					var Obj2 = {
						name: 'Settled',
						data: settledArrTs
					}
					var Obj3 = {
						name: 'Rejected',
						data: rejectedArrTs
					}
					namesArrTs1.push(result[1].deathList[i].name);
				}
				deathListTs.push(Obj);
				deathListTs.push(Obj1);
				deathListTs.push(Obj2);
				deathListTs.push(Obj3);
			}
			
			var data1 = deathListTs;
			var id1 = 'deathInsuranceTs';
			var tooltip= {
				valueSuffix: ' ',
				shared:true
			};
			var yAxisTD = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				},
				stackLabels: {
					
					useHTML: true,
					align:"left",
					qTotals: amountArryTD,
					enabled: true,
					align: 'left',
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					formatter: function() {
						
						return '<span style="top: 16px; position: absolute;"><br/>'+this.total+ '(<i class="fa fa-inr" aria-hidden="true"></i>'+this.options.qTotals[this.x]+')/-</span>';
					}
					
				}
				
			}
			highcharts(id1,type,xAxis,yAxisTD,legend,data1,plotOptions,tooltip); 
			
			/* Hospitalization Insurance*/
			var hospitalizationListTs = []
			var intimationsArrTs1 = []
			var forwardedArrTs1 = []
			var settledArrTs1 = []
			var rejectedArrTs1 = []
			var amountArryTH = []
			if(result[1].hospitalizationList != null && result[1].hospitalizationList.length > 0)
			{
				for(var i in result[1].hospitalizationList)
				{
					intimationsArrTs1.push({"y":result[1].hospitalizationList[i].subList[0].count,color:"#306F8F"})
					forwardedArrTs1.push({"y":result[1].hospitalizationList[i].subList[1].count,color:"#F39C12"})
					settledArrTs1.push({"y":result[1].hospitalizationList[i].subList[2].count,color:"#2DCC70"})
					rejectedArrTs1.push({"y":result[1].hospitalizationList[i].subList[3].count,color:"#E74B3B"})
					amountArryTH.push(result[1].hospitalizationList[i].amount)
					var Obj = {
						name: 'Intimation',
						data: intimationsArrTs1
					}
					var Obj1 = {
						name: 'Forwarded',
						data: forwardedArrTs1
					}
					var Obj2 = {
						name: 'Settled',
						data: settledArrTs1
					}
					var Obj3 = {
						name: 'Rejected',
						data: rejectedArrTs1
					}
					namesArrTs1.push(result[1].hospitalizationList[i].name);
				}
				hospitalizationListTs.push(Obj);
				hospitalizationListTs.push(Obj1);
				hospitalizationListTs.push(Obj2);
				hospitalizationListTs.push(Obj3);
			}
			
			var dataTs2 = hospitalizationListTs;
			var idTs2 = 'hospitalizationInsuranceTs';
			var yAxisTH = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				},
				stackLabels: {
					
					useHTML: true,
					align:"left",
					qTotals: amountArryTH,
					enabled: true,
					align: 'left',
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					formatter: function() {
						
						return '<span style="top: 16px; position: absolute;"><br/>'+this.total+ '(<i class="fa fa-inr" aria-hidden="true"></i>'+this.options.qTotals[this.x]+')/-</span>';
					}
					
				}
				
			}
			var tooltip= {
				valueSuffix: ' ',
				shared:true
			};
		
			highcharts(idTs2,type,xAxis,yAxisTH,legend,dataTs2,plotOptions,tooltip); 
		}
		
		
	/* Comparison Block Start */
	
	//getAllItsSubUserTypeIdsByParentUserTypeForInsurance(); default call
	function getInsuranceChildUserType(childUserTypeId,childUserType){
	   getSelectedChildMemberCadreInsuranceComplainctCnt(childUserTypeId,childUserType);
   }
   function getDirectChildTypeMemberDtls(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,id){
	 $("#"+id).show(); 
	 if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
	  getCandiateWiseCadreInsurencaeDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  
	 }else{
	 getDirectChildMemberCadreInsuranceComplainctCnt(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	  getCandiateWiseCadreInsurencaeDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);	
	 }	
   }
   function getInsuranceChildUserTypeDls(selectedUserType,childActivityMemberId,selectedMemberName,activityMemberId,userTypeId){
		$(".insuranceSlickPanelSlider").find("li").find(".panelSlick").removeClass("panelActiveSlick");
		$("#panelColor"+activityMemberId).find(".panelSlick").addClass("panelActiveSlick");
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getCandiateWiseCadreInsurencaeDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  		
		 }else{
		  getDirectChildMemberCadreInsuranceComplainctCnt(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		  getCandiateWiseCadreInsurencaeDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  		
		 }
  }
	function remveInsuranceSlcUsrType(removeSelected){
		$("#"+removeSelected).html(' ');
		$("#"+removeSelected).closest('.showHideTr').hide();
	}
	function getAllItsSubUserTypeIdsByParentUserTypeForInsurance(){
	     $("#childUserTypeDetailsDivForCadreInsurance").html(spinner);
		 $("#cadreInsuranceChildActivityMemberDivId").html(spinner);
		 $("#candidateWiseCadreInsuranceDeathDtlsDivId").html(spinner);
		 $("#candidateWiseCadreInsuranceHospDtlsDivId").html(spinner);
		var jsObj = {parentUserTypeId : globalUserTypeId}
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#childUserTypeDetailsDivForCadreInsurance").html(" ");	
			if(result != null && result.length > 0){
			 buildUserTypesAndItsChildRlstForInsurance(result);	
			}else{
			$("#childUserTypeDetailsDivForCadreInsurance").html("NO DATA AVAILABLE");
			}
		});		 
	}
	function buildUserTypesAndItsChildRlstForInsurance(result){
		var str='';
		 str+='<ul class="comparisonSelect">';
		 var firstChildUserTypeIdString;
		 var userType;
		 if(result !=null && result.length >0){
			  firstChildUserTypeIdString = result[0].shortName;
			  userType=result[0].userType;
			 for(var i in result){
    		   str+='<li onclick="getInsuranceChildUserType(\''+result[i].shortName+'\',\''+result[i].userType+'\');">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDivForCadreInsurance").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		getSelectedChildMemberCadreInsuranceComplainctCnt(firstChildUserTypeIdString,userType);
	}
	function getSelectedChildMemberCadreInsuranceComplainctCnt(firstChildUserTypeIdString,childUserType){
	   $("#DeathHeadingId").hide();
	   $("#HospitalizationHeadingId").hide();
	   $("#cadreInsuranceChildActivityMemberDivId").html(spinner);
	   $("#userTypeWiseChildDtlsTabForCadreInsuranceDivId").html(spinner);
	   $("#candidateWiseCadreInsuranceDeathDtlsDivId").html(spinner);
	   $("#candidateWiseCadreInsuranceHospDtlsDivId").html(spinner);
	   var yearId = getCadreYearVal();
	  var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	  var parentActivityMemberId = globalActivityMemberId;
	  var globalStateId= getCadreStateVal();
        var jsObj = { 
					   parentActivityMemberId : parentActivityMemberId,
					   childUserTypeIdsArray : childUserTypeIdsArray,
					   reportType :"selectedUserType",
					   stateId : globalStateId,           
					   fromDate: cadreInsuranceFDate,        
					   toDate :	cadreInsuranceTDate,
					   cadreEnrollmentYearId : yearId
				  }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildMemberCadreInsuranceComplainctCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#cadreInsuranceChildActivityMemberDivId").html(' ');
			if(result != null && result.length > 0){
				buildChildTypeMembersForInsuranceReslt(result,childUserType);
			}else{
				$("#cadreInsuranceChildActivityMemberDivId").html(' ');
			}
		});
}
function buildChildTypeMembersForInsuranceReslt(result,childUserType){
	  var userTypeId = result[0].userTypeId;
	  var activityMemberId = result[0].activityMemberId;
	  var selectedMemberName = result[0].name;
	  var selectedUserType = result[0].userType;
	  var str = '';
	  str+='<ul class="list-inline insuranceSlickPanelSlider">';
	  var rank=1; 
	   for(var i in result){
	     //str+='<li style="cursor:pointer;" class="activityMemberClsForAlertBlock"  attr_selectedusertype="'+result[i].userType+'"  attr_id="userTypeWiseChildDtlsTabForAlertId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' style="width:380px !important;">';
		 
		  str+='<li style="cursor:pointer;" id="panelColor'+result[i].activityMemberId+'" onclick="getInsuranceChildUserTypeDls(\''+result[i].userType+'\',\'userTypeWiseChildDtlsTabForCadreInsuranceDivId\',\''+result[i].name+'\',\''+result[i].activityMemberId+'\',\''+result[i].userTypeId+'\');" class=""  style="width:380px !important;">';
	     if(i==0){
			str+='<div class="panel panel-default panelSlick panelActiveSlick">';
		  }else{
		   str+='<div class="panel panel-default panelSlick">';
		  }
		  str+='<div class="panel-heading">';
			 str+='<h4 class="panel-title">'+result[i].name+'</h4>';
			 str+='<span class="count">'+rank+'</span>';
		 str+='</div>';
		 str+='<div class="panel-body">';
	   if(result[i].userTypeId != null && result[i].userTypeId==7 || result[i].userTypeId==9 || result[i].userTypeId==5 || result[i].userTypeId==6){// MLA Constituency Incharge, MP and District President Incharge 
		   var lctnName = result[i].locationName;
           lctnName = lctnName.substring(0, lctnName.lastIndexOf(" "));
		   str+='<h4 class="text-capital">'+result[i].userType+' ('+lctnName+')</h4>';	 
		 }else{
		   str+='<h4 class="text-capital">'+result[i].userType+'</h4>';	 
		 }
			str+='<div class="table-responsive">';
			 str+='<table class="table table-condensed text_align_center">';
				 str+='<thead>';
					 str+='<th>Total</th>';
					 if(result[i].subList != null && result[i].subList.length > 0){
						 for(var k in result[i].subList){
							 str+='<th>'+result[i].subList[k].name+'</th>';	 
						 }
					 }
				 str+='</thead>';
				 str+='</tbody>';
				 str+='<tr style="font-size:11px">';
				 
				 // positiveCount : this key contain approved amount
					 str+='<td>'+result[i].totalCount+'(<i class="fa fa-inr" aria-hidden="true"></i>'+result[i].positiveCount+'/-)</td>';
					  if(result[i].subList != null && result[i].subList.length > 0){
						 for(var j in result[i].subList){
							 if(result[i].subList[j].totalCount > 0){
								str+='<td>'+result[i].subList[j].totalCount+'(<i class="fa fa-inr" aria-hidden="true"></i>'+result[i].subList[j].positiveCount+'/-)</td>';	 	
							 }
						 }
					 }
				 str+='</tr>';
				 str+='</tbody>';
			 str+='</table>';
			  str+='</div>';
		 str+='</div>';
	 str+='</div> ';
    str+=' </li> ';  
	rank=rank+1;
   }     
   $("#cadreInsuranceChildActivityMemberDivId").html(str);
   $(".insuranceSlickPanelSlider").slick({
			 slide: 'li',
			 slidesToShow: 3,
			 slidesToScroll: 3,
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
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
	     getDirectChildMemberCadreInsuranceComplainctCnt(activityMemberId,userTypeId,selectedMemberName,selectedUserType,"userTypeWiseChildDtlsTabForCadreInsuranceDivId");
		 getCandiateWiseCadreInsurencaeDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType);
 }
function getDirectChildMemberCadreInsuranceComplainctCnt(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberDivId){
      $("#"+childActivityMemberDivId).show();
      $("#"+childActivityMemberDivId).html(spinner);
	  var childUserTypeIdsArray = [];
	   childUserTypeIdsArray.push(userTypeId);
	   var yearId = getCadreYearVal();
	   var parentActivityMemberId = activityMemberId;
	   var globalStateId= getCadreStateVal();
        var jsObj = { 
					   parentActivityMemberId : parentActivityMemberId,
					   childUserTypeIdsArray : childUserTypeIdsArray,
					   reportType :"directChild",
					   stateId : globalStateId,           
					   fromDate: cadreInsuranceFDate,        
					   toDate :	cadreInsuranceTDate,
					   cadreEnrollmentYearId : yearId
				  }
	  $.ajax({
			type : 'POST',
			url : 'getDirectChildMemberCadreInsuranceComplainctCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#"+childActivityMemberDivId).html('');
		  if(result != null && result.length > 0){
			  buildDirectChildMemberCadreInsuranceDtlsRlst(result,selectedMemberName,selectedUserType,childActivityMemberDivId,userTypeId);
		  }else{
			  $("#"+childActivityMemberDivId).hide();
			  if(childActivityMemberDivId == "userTypeWiseChildDtlsTabForCadreInsuranceDivId"){
				//$("#"+childActivityMemberDivId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
			}
		  }
		});
}

function buildDirectChildMemberCadreInsuranceDtlsRlst(result,selectedMemberName,selectedUserType,childActivityMemberDivId,userTypeId){
		var str=''; 
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		 if(childActivityMemberDivId != "userTypeWiseChildDtlsTabForCadreInsuranceDivId"){
			str+='<span onclick="remveInsuranceSlcUsrType(\''+childActivityMemberDivId+'\');" class=" pull-right" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
			 str+='<div class="table-responsive">';
			 if(childActivityMemberDivId != "userTypeWiseChildDtlsTabForCadreInsuranceDivId"){
				 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
			 }else{
				str+='<table class="table table-condensed tableHoverLevels m_top20">';  
			 }
				str+='<thead   class="bg_D8 text-capital">';
					str+='<th>Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
					str+='<th style="text-align:center;">Total</th>';
					var issueTypeDtls = result[0].subList;
					var statusObj; 
					 if(issueTypeDtls != null && issueTypeDtls.length > 0){
						statusObj = issueTypeDtls[0].subList;
						 for(var i in issueTypeDtls){
							 str+='<th>'+issueTypeDtls[i].name+'</th>';	 
						 }
					 }
					 if(statusObj != null && statusObj.length > 0){
						 for(var i in statusObj){
							 str+='<th>'+statusObj[i].name+'</th>';	  
						 }
					 }
				str+'=</thead>';
		str+='<tbody style="font-size:11px">';
		var rank=1;
		 for(var i in result){
		 var yourValues = result[i].locationName;
		    str+='<tr  onclick="getDirectChildTypeMemberDtls(\''+result[i].activityMemberId+'\',\''+result[i].userTypeId+'\',\''+result[i].name+'\',\''+result[i].userType+'\',\'insuranceChildMemDtslId'+result[i].userTypeId+''+i+'\',\'insuranceHiddenTrId'+result[i].userTypeId+''+i+'\');" >';
			str+='<td>';
				str+='<span class="tableCount">'+rank+'</span>';
			str+='</td>';
		  if(yourValues.indexOf(',') == -1){
				//  var locationNameArr=result[i].locationName.split(" ");
			 	var locatinName = result[i].locationName;
                 locatinName = locatinName.substring(0, locatinName.lastIndexOf(" "));
				str+='<td>'+result[i].userType+' (<b>'+locatinName+'</b>)</td>';
			}else{
				str+='<td>'+result[i].userType+'</td>';
			}
		    str+='<td>'+result[i].name+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalCount+'</td>';
			 if(result[i].subList != null && result[i].subList.length > 0){
				 var intimations=0;
				 var forwarded = 0;
				 var settled = 0;
				 var rejected = 0;			
				 for(var j in result[i].subList){
					  if(result[i].subList[j].totalCount > 0){
						 str+='<td>'+result[i].subList[j].totalCount+'</td>';
					  }else{
						  str+='<td>-</td>';
					  }
					 if(result[i].subList[j].subList != null && result[i].subList[j].subList.length > 0){
						 for(var k in result[i].subList[j].subList){
							if(result[i].subList[j].subList[k].id == 1){
								intimations = intimations + result[i].subList[j].subList[k].totalCount;
							}else if(result[i].subList[j].subList[k].id == 2){
								forwarded = forwarded + result[i].subList[j].subList[k].totalCount;
							}else if(result[i].subList[j].subList[k].id == 3){
								settled = settled + result[i].subList[j].subList[k].totalCount;
							}else if(result[i].subList[j].subList[k].id == 4){
								rejected = rejected + result[i].subList[j].subList[k].totalCount;
							}	  
						 }
				 }
			   }
			  if(intimations > 0){
					str+='<td>'+intimations+'</td>';	  
			   }else{
				   str+='<td> - </td>';	  
			   }
			   if(forwarded > 0){
					str+='<td>'+forwarded+'</td>';	  
			   }else{
				   str+='<td> - </td>';	  
			   }
				if(settled > 0){
					str+='<td>'+settled+'</td>';	  
			   }else{
				   str+='<td> - </td>';	  
			   }
				if(rejected > 0){
					str+='<td>'+rejected+'</td>';	  
			   }else{
				   str+='<td> - </td>';	  
			   }
	    }
		 str+='</tr>';
		str+='<tr class="showHideTr" style="display:none" id="insuranceHiddenTrId'+result[i].userTypeId+''+i+'" attr_id="insuranceChildMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='<td colspan="10"  id="insuranceChildMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='</td>';
		 rank=rank+1;
		 }
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
	    $("#"+childActivityMemberDivId).html(str);
	}
	
function getCandiateWiseCadreInsurencaeDtls(userTypeId,activityMemberId,selectedMemberName,selectedUserType){
	        $("#DeathHeadingId").hide();
			$("#HospitalizationHeadingId").hide();
	        $("#candidateWiseCadreInsuranceDeathDtlsDivId").html(spinner);
			$("#candidateWiseCadreInsuranceHospDtlsDivId").html(spinner);
			var globalStateId= getCadreStateVal();
			var yearId = getCadreYearVal();
	        var jsObj = { 
					   activityMemberId : activityMemberId,
					   stateId : globalStateId,           
					   fromDate: cadreInsuranceFDate,        
					   toDate :	cadreInsuranceTDate,
					   cadreEnrollmentYearId : yearId
				  }
	  $.ajax({
			type : 'POST',
			url : 'getCandiateWiseCadreInsurencaeDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#candidateWiseCadreInsuranceDeathDtlsDivId").html(' ');
			$("#candidateWiseCadreInsuranceHospDtlsDivId").html(' ');
			 if(result != null && result.length > 0){
				 buildCandidateWiseCadreInsuranceDtls(result,"candidateWiseCadreInsuranceDeathDtlsDivId","Death","DeathHeadingId");
				 buildCandidateWiseCadreInsuranceDtls(result,"candidateWiseCadreInsuranceHospDtlsDivId","Hospitalization","HospitalizationHeadingId");
			 }
		});
}  
  function buildCandidateWiseCadreInsuranceDtls(result,divId,type,headingId){
	 		var str = '';
			var locationName = [];
			var totalComplaintCnt=0;
			var totalCompaintAmount=0;
			for(var i in result){
				 locationName.push(result[i].name);
			 }
			 var intimationsArr = [];
			 var forwardedArr = [];
			 var settledArr = [];
			 var rejectedArr = [];
			 var negitiveCntArr=[];
			 var TotalAmountArr=[];
			 for(var i in result){
				 if(result[i].subList != null){
					 negitiveCntArr.push(result[i].negativeCount)
					 TotalAmountArr.push(result[i].positiveCount)
					  totalCompaintAmount =totalCompaintAmount+result[i].positiveCount;
					 for(var j in result[i].subList){
						 if(result[i].subList[j].name==type){
							  totalComplaintCnt = totalComplaintCnt+result[i].subList[j].totalCount;
							 for(var k in result[i].subList[j].subList){
								if(result[i].subList[j].subList[k].id==1){
									  intimationsArr.push({y:result[i].subList[j].subList[k].totalCount,"extra":result[i].subList[j].subList[k].id+"-"+result[i].id}); 
								  }else if(result[i].subList[j].subList[k].id==2){
									  forwardedArr.push({y:result[i].subList[j].subList[k].totalCount,"extra":result[i].subList[j].subList[k].id+"-"+result[i].id});
								  }else if(result[i].subList[j].subList[k].id==3){
									  settledArr.push({y:result[i].subList[j].subList[k].totalCount,"extra":result[i].subList[j].subList[k].id+"-"+result[i].id}); 
								  }else if(result[i].subList[j].subList[k].id==4){
									  rejectedArr.push({y:result[i].subList[j].subList[k].totalCount,"extra":result[i].subList[j].subList[k].id+"-"+result[i].id}); 
								  }
							  } 
						 }
				   } 
				 }
			 } 
		       var mainJosnObjArr = [];
			   if(intimationsArr != null && intimationsArr.length > 0){
				mainJosnObjArr.push({name:'INTIMATIONS',data:intimationsArr,color:"#306F8F"});  
			  }
			   if(forwardedArr != null && forwardedArr.length > 0){
				mainJosnObjArr.push({name:'FORWARDED',data:forwardedArr,color:"#F39C12"});  
			  }
			  if(settledArr != null && settledArr.length > 0){
				mainJosnObjArr.push({name:'SETTLED',data:settledArr,color:"#2DCC70"});  
			  }
			  if(rejectedArr != null && rejectedArr.length > 0){
				mainJosnObjArr.push({name:'REJECTED',data:rejectedArr,color:"#E74B3B"});  
			  }
			 buildHighCandidateDtlsHighchart(mainJosnObjArr,locationName,divId,type,headingId,totalComplaintCnt,negitiveCntArr,TotalAmountArr,totalCompaintAmount);
   }
	function buildHighCandidateDtlsHighchart(mainJosnObjArr,locationName,divId,type,headingId,totalComplaintCnt,negitiveCntArr,TotalAmountArr,totalCompaintAmount){
	    if(mainJosnObjArr != null && mainJosnObjArr.length > 0){
			var str='';
			  if(type=="Death"){
				str+='<span style="margin-left:10px">'+type+'<span class="pull-right">Total:'+totalComplaintCnt+' (<i class="fa fa-inr" aria-hidden="true"></i>'+totalCompaintAmount+')<span></span>';  
			  }else if(type=="Hospitalization"){
				str+='<span style="margin-left:10px">'+type+'<span class="pull-right">Total:'+totalComplaintCnt+' (<i class="fa fa-inr" aria-hidden="true"></i>'+totalCompaintAmount+')<span></span>'; 
			  }
			  
			 $("#"+headingId).html(str);
			 $("#"+headingId).show();
			 
				$("#"+divId).highcharts({
					chart: {
						type: 'bar',
						backgroundColor:'transparent'
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
						categories: locationName,
						title: {
							text: null
						},
						
					},
					yAxis: {
						min: 0,
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						title: {
							text: '',
							align: 'high'
						},
						labels: {
							overflow: 'justify'
						},
						 stackLabels: {
							enabled: true,
							align: 'left',
							x:5,
							y:15,
							qTotals:negitiveCntArr,
							qTotalAmount:TotalAmountArr,
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							},
							formatter: function() {
							//return  (this.total);
							return '<span style="top: 25px; position: absolute;"><br/>'+this.total+'/'+this.options.qTotals[this.x]+'(<i class="fa fa-inr" aria-hidden="true"></i>'+this.options.qTotalAmount[this.x]+')/-</span>';
						},
						}
					},
					tooltip: {
						valueSuffix: ' ',
						shared:true
					},
					plotOptions: {
						bar: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15,
							dataLabels: {
								align:"center",
								x:5,
								y:-5,
								enabled: false,
								 formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y,0);      
									}
								}
							}
						},
					},
					legend: {
							reversed: false,
							verticalAlign:'top'
							},
					credits: {
						enabled: false
					},
					series: mainJosnObjArr
				}); 
			  }else{
			    $("#"+divId).html("NO DATA AVAILABLE.");
			  }
   }
   /* Comparison Block End */
   
	//1
   function getDistrictWiseThenCategoryWiseInsuranceMemberCount(sortingCondition,order,locationId,filter){
		$("#districtWiseThenCategoryWiseInsuranceMemberCount").html(spinner);
		var category = $("#apCategoryId").val();  
		var status = $("#apStatusId").val();
		var yearId = getCadreYearVal();
		var globalStateId= getCadreStateVal();
		var jsObj = {
			activityMemberId : globalActivityMemberId,                               
			userTypeId : globalUserTypeId,                                   
			stateId :globalStateId,
			cadreEnrollmentYearId : yearId,           
			locationId: locationId,        
			status :	status,
			category : category, 
			fromDateStr : cadreInsuranceFDate,
			toDateStr : cadreInsuranceTDate,
			sortingCondition : sortingCondition,  
			order : order                  
		};
		$.ajax({
   			type : 'POST',
   			url : 'getDistrictWiseThenCategoryWiseInsuranceMemberCountAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result != null && result.length > 0)
			{
				if(filter == 'filter'){
					$("#locationListForCategoryId").empty();           
					$("#locationListForCategoryId").append('<option value="-1">Select Location</option>'); 
					$("#locationListForCategoryId").append('<option value="0">All</option>');				
					$.each(result, function(index) {     
						$("#locationListForCategoryId").append('<option value="'+result[index].id+'">'+result[index].name+'</option>');  
					});
				}
				buildDistrictWiseThenCategoryWiseInsuranceMemberCount(result);
				   
			}else{
				$("#districtWiseThenCategoryWiseInsuranceMemberCount").html("NO DATA Available");
			}
		});
   }
   function buildDistrictWiseThenCategoryWiseInsuranceMemberCount(result)
   {
		var overviewList = []
		var namesArr = [];
		var deathArray = []
		var hospitalArray = []
		if(result != null && result.length > 0)
		{
			for(var i in result)
			{
				
				hospitalArray.push({"y":result[i].hospitalizationCount})
				deathArray.push({"y":result[i].deathCount})
				var Obj = {
					name: 'Death',
					data: deathArray
				}
				var Obj1 = {
					name: 'Hospitalization',
					data: hospitalArray
				}
				namesArr.push(result[i].name);
			}
			overviewList.push(Obj);
			overviewList.push(Obj1);
		}
		
		var heightOfDiv = namesArr.length ;
		if(heightOfDiv >10){
			heightOfDiv = heightOfDiv * 30;
			$("#districtWiseThenCategoryWiseInsuranceMemberCount").css("height",heightOfDiv);
			
		}else{
			$("#districtWiseThenCategoryWiseInsuranceMemberCount").css("height","auto");
			
		  }
		  
		var data = overviewList
		var id = 'districtWiseThenCategoryWiseInsuranceMemberCount';
		var type = {
			type: 'bar',
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
				},
				formatter: function() {
					return  (this.total);
				}
			}
		}
		var xAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: namesArr
		}
		var plotOptions =  {
			 series: {
				stacking: 'normal',
				pointWidth: 20,
				gridLineWidth: 15
			}
		}
		var tooltip= {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
		}
		highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
   }
   //2
   function getConstituencyWiseThenCategoryWiseInsuranceMemberCount(sortingCondition,order,locationId,filter)
   {
		$("#constituencyWiseThenCategoryWiseInsuranceMemberCount").html(spinner);
		var category = $("#apCategoryId").val();    
		var status = $("#apStatusId").val();
		var yearId = getCadreYearVal();
		var globalStateId= getCadreStateVal();
		var jsObj = { 
			activityMemberId : globalActivityMemberId,                                       
			userTypeId : globalUserTypeId,                
			stateId :globalStateId,
			cadreEnrollmentYearId : yearId,           
			locationId: locationId,                               
			status :	status,
			category : category, 
			fromDateStr : cadreInsuranceFDate,
			toDateStr : cadreInsuranceTDate,
			sortingCondition : sortingCondition,
			order : order
		};
		$.ajax({
   			type : 'POST',
   			url : 'getConstituencyWiseThenCategoryWiseInsuranceMemberCountAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0)
			{	
				if(filter == 'filter'){
					$("#constListForCategoryId").empty();           
					$("#constListForCategoryId").append('<option value="-1">Select Location</option>'); 
					$("#constListForCategoryId").append('<option value="0">All</option>');
					$.each(result, function(index) {     
						$("#constListForCategoryId").append('<option value="'+result[index].id+'">'+result[index].name+'</option>');  
					});
				}
				buildConstituencyWiseThenCategoryWiseInsuranceMemberCount(result)
			}else{
				$("#constituencyWiseThenCategoryWiseInsuranceMemberCount").html("NO DATA AVAILABLE");
			}
		});
   }
   function buildConstituencyWiseThenCategoryWiseInsuranceMemberCount(result)
   {
		var str='';
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="scroller">';
					str+='<div id="constituencyWiseThenCategoryWiseInsuranceMemberCountGraph">';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#constituencyWiseThenCategoryWiseInsuranceMemberCount").html(str);
		
		var overviewList = []
		var namesArr = [];
		var deathArray = []
		var hospitalArray = []
		if(result != null && result.length > 0)
		{
			for(var i in result)
			{
				
				hospitalArray.push({"y":result[i].hospitalizationCount})
				deathArray.push({"y":result[i].deathCount})
				var Obj = {
					name: 'Death',
					data: deathArray
				}
				var Obj1 = {
					name: 'Hospitalization',
					data: hospitalArray
				}
				namesArr.push(result[i].name);
			}
			overviewList.push(Obj);
			overviewList.push(Obj1);
		}
		
		var heightOfDiv = namesArr.length ;
		if(heightOfDiv >10){
			heightOfDiv = heightOfDiv * 30;
			$("#constituencyWiseThenCategoryWiseInsuranceMemberCountGraph").css("height",heightOfDiv);
			$(".scroller").mCustomScrollbar({setHeight:'600px'})
			
		}else{
			$("#constituencyWiseThenCategoryWiseInsuranceMemberCountGraph").css("height","auto");
			$(".scroller").removeAttr('style')
			$(".scroller").mCustomScrollbar('destroy');
			
		  }
		  
		var data = overviewList
		var id = 'constituencyWiseThenCategoryWiseInsuranceMemberCountGraph';
		var type = {
			type: 'bar',
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
				},
				formatter: function() {
					return  (this.total);
				}
			}
		}
		var xAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: namesArr
		}
		var plotOptions =  {
			 series: {
				stacking: 'normal',
				pointWidth: 20,
				gridLineWidth: 15
			}
		}
		var tooltip= {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
		}
		highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
   }
   //3
   function getDistrictWiseThenStatusWiseInsuranceMemberCount(sortingCondition,order,locationId,filter){
		$("#districtWiseThenStatusWiseInsuranceMemberCount").html(spinner);
		var category = $("#apCategoryId").val();
		var status = $("#apStatusId").val();
		var yearId = getCadreYearVal();
		var globalStateId= getCadreStateVal();
		var jsObj = { 
		   activityMemberId : globalActivityMemberId,                                        
		   userTypeId : globalUserTypeId,                         
		   stateId :globalStateId,
		   cadreEnrollmentYearId : yearId,           
		   locationId: locationId,                                       
		   status :	status,
		   category : category, 
		   fromDateStr : cadreInsuranceFDate,
		   toDateStr : cadreInsuranceTDate,
		   sortingCondition : sortingCondition,
		   order : order
	  };
		$.ajax({
   			type : 'POST',
   			url : 'getDistrictWiseThenStatusWiseInsuranceMemberCountAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0)
			{
				if(filter == 'filter'){
					$("#locationListForStatusId").empty();           
					$("#locationListForStatusId").append('<option value="-1">Select Location</option>');
					$("#locationListForStatusId").append('<option value="0">All</option>');
					$.each(result, function(index) {     
						$("#locationListForStatusId").append('<option value="'+result[index].id+'">'+result[index].name+'</option>');  
					});
				}
				buildDistrictWiseThenStatusWiseInsuranceMemberCount(result);
			}else{
				$("#districtWiseThenStatusWiseInsuranceMemberCount").html("NO DATA AVAILABLE");
			}
		});
   } 
   function buildDistrictWiseThenStatusWiseInsuranceMemberCount(result)
   {
		var intimationsArr = [];
		var forwardedArr = [];
		var settledArr = [];
		var rejectedArr = [];
		var namesArr = [];
		for(var i in result){
			intimationsArr.push({y:result[i].insuranceStatusVOs[0].insuredMemberCount}); 
			forwardedArr.push({y:result[i].insuranceStatusVOs[1].insuredMemberCount});
			settledArr.push({y:result[i].insuranceStatusVOs[2].insuredMemberCount}); 
			rejectedArr.push({y:result[i].insuranceStatusVOs[3].insuredMemberCount}); 
			namesArr.push(result[i].name);
		}
		var mainJosnObjArr = [];
		if(intimationsArr != null && intimationsArr.length > 0){
			mainJosnObjArr.push({name:'Intimation',data:intimationsArr,color:"#306F8F"});  
		}
		if(forwardedArr != null && forwardedArr.length > 0){
			mainJosnObjArr.push({name:'Forwarded',data:forwardedArr,color:"#F39C12"});  
		}
		if(settledArr != null && settledArr.length > 0){
			mainJosnObjArr.push({name:'Settled',data:settledArr,color:"#2DCC70"});  
		}
		if(rejectedArr != null && rejectedArr.length > 0){
			mainJosnObjArr.push({name:'Rejected',data:rejectedArr,color:"#E74B3B"});  
		}
		
		var heightOfDiv = namesArr.length ;
		if(heightOfDiv >10){
			heightOfDiv = heightOfDiv * 30;
			$("#districtWiseThenStatusWiseInsuranceMemberCount").css("height",heightOfDiv);
			
		}else{
			$("#districtWiseThenStatusWiseInsuranceMemberCount").css("height","auto");
			
		  }
		var id = 'districtWiseThenStatusWiseInsuranceMemberCount';
		var type = {
			type: 'bar',
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
				},
				formatter: function() {
					return  (this.total);
				}
			}
		}
		var xAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: namesArr
		}
		var plotOptions =  {
			 series: {
				stacking: 'normal',
				pointWidth: 20,
				gridLineWidth: 15
			}
		}
		var tooltip= {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
		}
		highcharts(id,type,xAxis,yAxis,legend,mainJosnObjArr,plotOptions,tooltip);
   }
   //4
   function getConstituencyWiseThenStatusWiseInsuranceMemberCount(sortingCondition,order,locationId,filter){
	   $("#constituencyWiseThenStatusWiseInsuranceMemberCount").html(spinner); 
		var category = $("#apCategoryId").val();
		var status = $("#apStatusId").val();
		var yearId = getCadreYearVal();
		var globalStateId= getCadreStateVal();
		var jsObj = { 
			activityMemberId : globalActivityMemberId,                                     
			userTypeId : globalUserTypeId,                         
			stateId :globalStateId,  
			cadreEnrollmentYearId : yearId,           
			locationId: locationId,                                       
			status :	status,
			category : category, 
			fromDateStr : cadreInsuranceFDate,
			toDateStr : cadreInsuranceTDate,
			sortingCondition : sortingCondition,
			order : order
		};
		$.ajax({
			type : 'POST',
			url : 'getConstituencyWiseThenStatusWiseInsuranceMemberCountAction.action',
			dataType : 'json',  
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){  
   			console.log(result);
			if(result != null && result.length > 0)
			{	
				if(filter == 'filter'){
					$("#constListForStatusId").empty();           
					$("#constListForStatusId").append('<option value="-1">Select Location</option>');  
					$("#constListForStatusId").append('<option value="0">All</option>');
					$.each(result, function(index) {     
						$("#constListForStatusId").append('<option value="'+result[index].id+'">'+result[index].name+'</option>');  
					});
				}
				buildConstituencyWiseThenStatusWiseInsuranceMemberCount(result)
			}else{
				$("#constituencyWiseThenStatusWiseInsuranceMemberCount").html("NO DATA AVAILABLE");
			}
			
		});
   }
   function buildConstituencyWiseThenStatusWiseInsuranceMemberCount(result)
   {
		var str='';
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="scrollerDiv">';
					str+='<div id="constituencyWiseThenStatusWiseInsuranceMemberCountGraph">';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#constituencyWiseThenStatusWiseInsuranceMemberCount").html(str);
		
		
		var intimationsArr = [];
		var forwardedArr = [];
		var settledArr = [];
		var rejectedArr = [];
		var namesArr = [];
		for(var i in result){
			intimationsArr.push({y:result[i].insuranceStatusVOs[0].insuredMemberCount}); 
			forwardedArr.push({y:result[i].insuranceStatusVOs[1].insuredMemberCount});
			settledArr.push({y:result[i].insuranceStatusVOs[2].insuredMemberCount}); 
			rejectedArr.push({y:result[i].insuranceStatusVOs[3].insuredMemberCount}); 
			namesArr.push(result[i].name);
		}
		var mainJosnObjArr = [];
		if(intimationsArr != null && intimationsArr.length > 0){
			mainJosnObjArr.push({name:'Intimation',data:intimationsArr,color:"#306F8F"});  
		}
		if(forwardedArr != null && forwardedArr.length > 0){
			mainJosnObjArr.push({name:'Forwarded',data:forwardedArr,color:"#F39C12"});  
		}
		if(settledArr != null && settledArr.length > 0){
			mainJosnObjArr.push({name:'Settled',data:settledArr,color:"#2DCC70"});  
		}
		if(rejectedArr != null && rejectedArr.length > 0){
			mainJosnObjArr.push({name:'Rejected',data:rejectedArr,color:"#E74B3B"});  
		}
		
		var heightOfDiv = namesArr.length ;
		if(heightOfDiv >10){
			heightOfDiv = heightOfDiv * 30;
			$("#constituencyWiseThenStatusWiseInsuranceMemberCountGraph").css("height",heightOfDiv);
			$(".scrollerDiv").mCustomScrollbar({setHeight:'600px'})
			
		}else{
			$("#constituencyWiseThenStatusWiseInsuranceMemberCountGraph").css("height","auto");
			$(".scrollerDiv").removeAttr('style')
			$(".scrollerDiv").mCustomScrollbar('destroy');
			
		  }
		var id = 'constituencyWiseThenStatusWiseInsuranceMemberCountGraph';
		var type = {
			type: 'bar',
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
				},
				formatter: function() {
					return  (this.total);
				}
			}
		}
		var xAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: namesArr
		}
		var plotOptions =  {
			 series: {
				stacking: 'normal',
				pointWidth: 20,
				gridLineWidth: 15
			}
		}
		var tooltip= {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
		}
		highcharts(id,type,xAxis,yAxis,legend,mainJosnObjArr,plotOptions,tooltip);
   }
   //5
   function getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(locationType,sortingCondition,order,locationId,filter){
	   
		if(locationType == 'district')
		{
			$("#locationWiseThenCategoryWiseInsuranceMemberCountForTS").html(spinner);
		}else{
			$("#locationWiseThenCategoryWiseInsuranceMemberCountForTSCons").html(spinner);
		}
		var category = $("#tsCategoryId").val();
		var status = $("#tsStatusId").val();
		var yearId = getCadreYearVal();
		var globalStateId= getCadreStateVal();
		var jsObj = { 
			stateId :globalStateId,
			cadreEnrollmentYearId : yearId,           
			locationId: locationId,                                       
			status :	status,  
			category : category,             
			fromDateStr : cadreInsuranceFDate,
			toDateStr : cadreInsuranceTDate,
			type : "category",
			locationType : locationType,
			sortingCondition : sortingCondition,
			order : order
		};
		$.ajax({
   			type : 'POST',
   			url : 'getLocationWiseThenCategoryWiseInsuranceMemberCountForTSAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			
			if(result != null && result.length > 0)
			{	
				if(locationType == 'district'){
					if(filter == 'filter'){
						$("#locationIdForCategoryDist").empty(); 
						$("#locationIdForCategoryDist").append('<option value="0">Select Location</option>');				
						$("#locationIdForCategoryDist").append('<option value="-1">All</option>');
						$.each(result, function(index) {     
							$("#locationIdForCategoryDist").append('<option value="'+result[index].id+'">'+result[index].name+'</option>');  
						});
					}	
				}else{
					if(filter == 'filter'){
						$("#locationIdForCategoryCons").empty();
						$("#locationIdForCategoryCons").append('<option value="0">Select Location</option>');				
						$("#locationIdForCategoryCons").append('<option value="-1">All</option>');
						$.each(result, function(index) {     
							$("#locationIdForCategoryCons").append('<option value="'+result[index].id+'">'+result[index].name+'</option>');  
						});
					}
				}
				buildLocationWiseThenCategoryWiseInsuranceMemberCountForTS(locationType,result);
			}else{
				$("#locationWiseThenCategoryWiseInsuranceMemberCountForTS").html("NO DATA AVAILABLE");
			}
			
		});
	}   
    function buildLocationWiseThenCategoryWiseInsuranceMemberCountForTS(locationType,result)
	{
		var type = {
			type: 'bar',
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
				},
				formatter: function() {
					return  (this.total);
				}
			}
		}
		var plotOptions =  {
			 series: {
				stacking: 'normal',
				pointWidth: 20,
				gridLineWidth: 15
			}
		}
		if(locationType == 'district')
		{
			var overviewList = []
			var namesArr = [];
			var deathArray = []
			var hospitalArray = []
			
			for(var i in result)
			{
				
				hospitalArray.push({"y":result[i].hospitalizationCount})
				deathArray.push({"y":result[i].deathCount})
				var Obj = {
					name: 'Death',
					data: deathArray
				}
				var Obj1 = {
					name: 'Hospitalization',
					data: hospitalArray
				}
				namesArr.push(result[i].name);
			}
			overviewList.push(Obj);
			overviewList.push(Obj1);
			
			var heightOfDiv = namesArr.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 30;
				$("#locationWiseThenCategoryWiseInsuranceMemberCountForTS").css("height",heightOfDiv);
			}else{
				$("#locationWiseThenCategoryWiseInsuranceMemberCountForTS").css("height","auto");
			  }
			  
			var data = overviewList
			var id = 'locationWiseThenCategoryWiseInsuranceMemberCountForTS';
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: namesArr
			}
			
			var tooltip= {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
			}
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
		}else if(locationType == 'constituency'){
			var str='';
			str+='<div class="row">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<div class="scroller4">';
						str+='<div id="locationWiseThenCategoryWiseInsuranceMemberCountForTSConsGraph">';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			$("#locationWiseThenCategoryWiseInsuranceMemberCountForTSCons").html(str);
			
			var overviewList = []
			var namesArr = [];
			var deathArray = []
			var hospitalArray = []
			for(var i in result)
			{
				
				hospitalArray.push({"y":result[i].hospitalizationCount})
				deathArray.push({"y":result[i].deathCount})
				var Obj = {
					name: 'Death',
					data: deathArray
				}
				var Obj1 = {
					name: 'Hospitalization',
					data: hospitalArray
				}
				namesArr.push(result[i].name);
			}
			overviewList.push(Obj);
			overviewList.push(Obj1);
			
			var heightOfDiv = namesArr.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 30;
				$("#locationWiseThenCategoryWiseInsuranceMemberCountForTSConsGraph").css("height",heightOfDiv);
				$(".scroller4").mCustomScrollbar({setHeight:'600px'})
				
			}else{
				$("#locationWiseThenCategoryWiseInsuranceMemberCountForTSConsGraph").css("height","auto");
				$(".scroller4").removeAttr('style')
				$(".scroller4").mCustomScrollbar('destroy');
				
			  }
			  
			var data = overviewList
			var id = 'locationWiseThenCategoryWiseInsuranceMemberCountForTSConsGraph';
			
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: namesArr
			}
			var tooltip= {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
			}
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
		}
	}
	//6
   function getLocationWiseThenStatusWiseInsuranceMemberCountForTS(locationType,sortingCondition,order,locationId,filter){
		if(locationType == 'district')
		{
			$("#locationWiseThenStatusWiseInsuranceMemberCountForTS").html(spinner);
		}else{
			$("#locationWiseThenStatusWiseInsuranceMemberCountForTSCons").html(spinner);
		}
		var category = $("#tsCategoryId").val();
		var status = $("#tsStatusId").val();
		var yearId = getCadreYearVal();
		var globalStateId= getCadreStateVal();
		var jsObj = { 
			stateId :globalStateId,
			cadreEnrollmentYearId : yearId,           
			locationId: locationId,                                               
			status :	status,  
			category : category,             
			fromDateStr : cadreInsuranceFDate,
			toDateStr : cadreInsuranceTDate,
			type : "status",
			locationType : locationType,
			sortingCondition : sortingCondition,
			order : order
   		};
		$.ajax({
   			type : 'POST',
   			url : 'getLocationWiseThenStatusWiseInsuranceMemberCountForTSAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			console.log(result);
			if(result != null && result.length > 0)
			{	
				if(locationType == 'district'){
					if(filter == 'filter'){
						$("#locationIdForStatusDist").empty(); 
						$("#locationIdForStatusDist").append('<option value="0">Select Location</option>');
						$("#locationIdForStatusDist").append('<option value="-1">All</option>');
						$.each(result, function(index) {     
							$("#locationIdForStatusDist").append('<option value="'+result[index].id+'">'+result[index].name+'</option>');  
						});
					}
				}else{
					if(filter == 'filter'){
						$("#locationIdForStatusCons").empty(); 
						$("#locationIdForStatusCons").append('<option value="0">Select Location</option>');				
						$("#locationIdForStatusCons").append('<option value="-1">All</option>');
						$.each(result, function(index) {     
							$("#locationIdForStatusCons").append('<option value="'+result[index].id+'">'+result[index].name+'</option>');  
						});
					}
				}
				buildLocationWiseThenStatusWiseInsuranceMemberCountForTS(locationType,result);
			}else{
				if(locationType == 'district'){
					$("#locationWiseThenStatusWiseInsuranceMemberCountForTS").html("NO DATA AVAILABLE");
				}else{
					$("#locationWiseThenStatusWiseInsuranceMemberCountForTSCons").html("NO DATA AVAILABLE");
				}
				
			}
		});
   }
   function buildLocationWiseThenStatusWiseInsuranceMemberCountForTS(locationType,result)
   {
		var type = {
			type: 'bar',
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
				},
				formatter: function() {
					return  (this.total);
				}
			}
		}
		var plotOptions =  {
			 series: {
				stacking: 'normal',
				pointWidth: 20,
				gridLineWidth: 15
			}
		}
		if(locationType == 'district')
		{
			var intimationsArr = [];
			var forwardedArr = [];
			var settledArr = [];
			var rejectedArr = [];
			var namesArr = [];
			for(var i in result){
				intimationsArr.push({y:result[i].insuranceStatusVOs[0].insuredMemberCount}); 
				forwardedArr.push({y:result[i].insuranceStatusVOs[1].insuredMemberCount});
				settledArr.push({y:result[i].insuranceStatusVOs[2].insuredMemberCount}); 
				rejectedArr.push({y:result[i].insuranceStatusVOs[3].insuredMemberCount}); 
				namesArr.push(result[i].name);
			}
			var mainJosnObjArr = [];
			if(intimationsArr != null && intimationsArr.length > 0){
				mainJosnObjArr.push({name:'Intimation',data:intimationsArr,color:"#306F8F"});  
			}
			if(forwardedArr != null && forwardedArr.length > 0){
				mainJosnObjArr.push({name:'Forwarded',data:forwardedArr,color:"#F39C12"});  
			}
			if(settledArr != null && settledArr.length > 0){
				mainJosnObjArr.push({name:'Settled',data:settledArr,color:"#2DCC70"});  
			}
			if(rejectedArr != null && rejectedArr.length > 0){
				mainJosnObjArr.push({name:'Rejected',data:rejectedArr,color:"#E74B3B"});  
			}
			
			var heightOfDiv = namesArr.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 30;
				$("#locationWiseThenStatusWiseInsuranceMemberCountForTS").css("height",heightOfDiv);
			}else{
				$("#locationWiseThenStatusWiseInsuranceMemberCountForTS").css("height","auto");
			  }
			var id = 'locationWiseThenStatusWiseInsuranceMemberCountForTS';
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: namesArr
			}
			var tooltip =  {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
			}
		
			highcharts(id,type,xAxis,yAxis,legend,mainJosnObjArr,plotOptions,tooltip);
		}else if(locationType == 'constituency'){
			var str='';
			str+='<div class="row">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<div class="scroller5">';
						str+='<div id="locationWiseThenStatusWiseInsuranceMemberCountForTSConsGraph">';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			$("#locationWiseThenStatusWiseInsuranceMemberCountForTSCons").html(str);
			
			var intimationsArr = [];
			var forwardedArr = [];
			var settledArr = [];
			var rejectedArr = [];
			var namesArr = [];
			for(var i in result){
				intimationsArr.push({y:result[i].insuranceStatusVOs[0].insuredMemberCount}); 
				forwardedArr.push({y:result[i].insuranceStatusVOs[1].insuredMemberCount});
				settledArr.push({y:result[i].insuranceStatusVOs[2].insuredMemberCount}); 
				rejectedArr.push({y:result[i].insuranceStatusVOs[3].insuredMemberCount}); 
				namesArr.push(result[i].name);
			}
			var mainJosnObjArr = [];
			if(intimationsArr != null && intimationsArr.length > 0){
				mainJosnObjArr.push({name:'Intimation',data:intimationsArr,color:"#306F8F"});  
			}
			if(forwardedArr != null && forwardedArr.length > 0){
				mainJosnObjArr.push({name:'Forwarded',data:forwardedArr,color:"#F39C12"});  
			}
			if(settledArr != null && settledArr.length > 0){
				mainJosnObjArr.push({name:'Settled',data:settledArr,color:"#2DCC70"});  
			}
			if(rejectedArr != null && rejectedArr.length > 0){
				mainJosnObjArr.push({name:'Rejected',data:rejectedArr,color:"#E74B3B"});  
			}
			
			var heightOfDiv = namesArr.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 30;
				$("#locationWiseThenStatusWiseInsuranceMemberCountForTSConsGraph").css("height",heightOfDiv);
				$(".scroller5").mCustomScrollbar({setHeight:'600px'})
				
			}else{
				$("#locationWiseThenStatusWiseInsuranceMemberCountForTSConsGraph").css("height","auto");
				$(".scroller5").removeAttr('style')
				$(".scroller5").mCustomScrollbar('destroy');
				
			  }
			var id = 'locationWiseThenStatusWiseInsuranceMemberCountForTSConsGraph';
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: namesArr
			}
			
			var tooltip = {
				enabled:true,
				pointFormat: '<span style="color:{series.color}">{series.name}</span><br/>(<b>{point.y}</b>)<br/>',
				shared:true
			}
			highcharts(id,type,xAxis,yAxis,legend,mainJosnObjArr,plotOptions,tooltip);
		}
   }
         