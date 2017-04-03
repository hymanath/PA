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
	
      
	  //getUserTypeWiseTotalCadreInsuranceComplainctCnt();
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
	     $("#childUserTypeDetailsDivForCadreInsurance").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 $("#cadreInsuranceChildActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 $("#candidateWiseCadreInsuranceDeathDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 $("#candidateWiseCadreInsuranceHospDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
	   $("#cadreInsuranceChildActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   $("#userTypeWiseChildDtlsTabForCadreInsuranceDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   $("#candidateWiseCadreInsuranceDeathDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   $("#candidateWiseCadreInsuranceHospDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	  var parentActivityMemberId = globalActivityMemberId;
        var jsObj = { 
					   parentActivityMemberId : parentActivityMemberId,
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
				 str+='<tr>';
				 // positiveCount : this key contain approved amount
					 str+='<td>'+result[i].totalCount+'('+result[i].positiveCount+')</td>';
					  if(result[i].subList != null && result[i].subList.length > 0){
						 for(var j in result[i].subList){
							 if(result[i].subList[j].totalCount > 0){
								str+='<th>'+result[i].subList[j].totalCount+'('+result[i].subList[j].positiveCount+')</th>';	 	
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
      $("#"+childActivityMemberDivId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var childUserTypeIdsArray = [];
	   childUserTypeIdsArray.push(userTypeId);
	   var parentActivityMemberId = activityMemberId;
        var jsObj = { 
					   parentActivityMemberId : parentActivityMemberId,
					   childUserTypeIdsArray : childUserTypeIdsArray,
					   reportType :"directChild",
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
		str+='<tbody>';
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
	        $("#candidateWiseCadreInsuranceDeathDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			$("#candidateWiseCadreInsuranceHospDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	        var jsObj = { 
					   activityMemberId : activityMemberId,
					   stateId : globalStateIdForCadreInsurance,           
					   fromDate: "",        
					   toDate :	"",
					   cadreEnrollmentYearId : 0
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
			for(var i in result){
				 locationName.push(result[i].name);
			 }
			 var intimationsArr = [];
			 var forwardedArr = [];
			 var settledArr = [];
			 var rejectedArr = [];
			 for(var i in result){
				 if(result[i].subList != null){
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
				mainJosnObjArr.push({name:'SETTLED',data:settledArr,color:"#5CD48F"});  
			  }
			  if(rejectedArr != null && rejectedArr.length > 0){
				mainJosnObjArr.push({name:'REJECTED',data:rejectedArr,color:"#E74B3B"});  
			  }
			 buildHighCandidateDtlsHighchart(mainJosnObjArr,locationName,divId,type,headingId,totalComplaintCnt);
   }
   function buildHighCandidateDtlsHighchart(mainJosnObjArr,locationName,divId,type,headingId,totalComplaintCnt){
	    if(mainJosnObjArr != null && mainJosnObjArr.length > 0){
			var str='';
			  if(type="Death"){
				str+='<span style="margin-left:10px">'+type+'<span style="margin-left:860px;">Total:'+totalComplaintCnt+'<span></span>';  
			  }else{
				str+='<span style="margin-left:10px">'+type+'<span style="margin-left:790px;">Total:'+totalComplaintCnt+'<span></span>'; 
			  }
			  
			 $("#"+headingId).html(str);
			 $("#"+headingId).show();
					 $("#"+divId).highcharts({
					chart: {
						type: 'bar'
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
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							},
							formatter: function() {
							return  (this.total);
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
   
 //getDistrictWiseThenCategoryWiseInsuranceMemberCount();      
   function getDistrictWiseThenCategoryWiseInsuranceMemberCount(){
   	var jsObj = { 
   					   activityMemberId : 59,                               
   					   userTypeId : 6,                                   
   					   stateId :globalStateIdForCadreInsurance,
   					   cadreEnrollmentYearId : 0,           
   					   locationId: 0,        
   					   status :	"",
   					   category : "", 
   					   fromDateStr : "",
   					   toDateStr : "",
   					   sortingCondition : "name",  
   					   order : "asc"                  
   				  };
   	console.log(jsObj);    
   	$.ajax({
   			type : 'POST',
   			url : 'getDistrictWiseThenCategoryWiseInsuranceMemberCountAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
   	}).done(function(result){
   			console.log(result);
   	});
   }
   //getConstituencyWiseThenCategoryWiseInsuranceMemberCount();     
   function getConstituencyWiseThenCategoryWiseInsuranceMemberCount(){
   	var jsObj = { 
   					   activityMemberId : 44,                                       
   					   userTypeId : 2,                
   					   stateId :globalStateIdForCadreInsurance,
   					   cadreEnrollmentYearId : 0,           
   					   locationId: 0,                               
   					   status :	"",
   					   category : "", 
   					   fromDateStr : "",
   					   toDateStr : "",
   					   sortingCondition : "insuredMember",
   					   order : "desc"
   				  };
   	console.log(jsObj);                                    
   	$.ajax({
   			type : 'POST',
   			url : 'getConstituencyWiseThenCategoryWiseInsuranceMemberCountAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
   	}).done(function(result){
   			console.log(result);
   	});
   } 
   //getDistrictWiseThenStatusWiseInsuranceMemberCount();      
   function getDistrictWiseThenStatusWiseInsuranceMemberCount(){
   	var jsObj = { 
   					   activityMemberId : 44,                                     
   					   userTypeId : 2,                         
   					   stateId :globalStateIdForCadreInsurance,
   					   cadreEnrollmentYearId : 0,           
   					   locationId: 0,                                       
   					   status :	"",  
   					   category : "",             
   					   fromDateStr : "",
   					   toDateStr : "",
   					   sortingCondition : "insuredMember",
   					   order : "desc"
   				  };
   	console.log(jsObj);                                
   	$.ajax({
   			type : 'POST',
   			url : 'getDistrictWiseThenStatusWiseInsuranceMemberCountAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
   	}).done(function(result){
   			console.log(result);
   	});
   } 
   //getConstituencyWiseThenStatusWiseInsuranceMemberCount();           
   function getConstituencyWiseThenStatusWiseInsuranceMemberCount(){
   	var jsObj = { 
   					   activityMemberId : 44,                                     
   					   userTypeId : 2,                         
   					   stateId :globalStateIdForCadreInsurance,
   					   cadreEnrollmentYearId : 0,           
   					   locationId: 0,                                       
   					   status :	"",  
   					   category : "",             
   					   fromDateStr : "",
   					   toDateStr : "",
   					   sortingCondition : "insuredMember",
   					   order : "desc"
   				  };
   	console.log(jsObj);                                
   	$.ajax({
   			type : 'POST',
   			url : 'getConstituencyWiseThenStatusWiseInsuranceMemberCountAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
   	}).done(function(result){  
   			console.log(result);
   	});
   }
   //getLocationWiseThenCategoryWiseInsuranceMemberCountForTS();                        
   function getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(){
   	var jsObj = { 
   					   stateId :globalStateIdForCadreInsurance,
   					   cadreEnrollmentYearId : 0,           
   					   locationId: 0,                                       
   					   status :	"",  
   					   category : "",             
   					   fromDateStr : "",
   					   toDateStr : "",
   					   type : "category",
   					   locationType : "constituency",
   					   sortingCondition : "insuredMember",
   					   order : "desc"
   				  };
   	console.log(jsObj);                                
   	$.ajax({
   			type : 'POST',
   			url : 'getLocationWiseThenCategoryWiseInsuranceMemberCountForTSAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
   	}).done(function(result){  
   			console.log(result);
   	});
   }   
   //getLocationWiseThenStatusWiseInsuranceMemberCountForTS();        
   function getLocationWiseThenStatusWiseInsuranceMemberCountForTS(){
   	var jsObj = { 
   					   stateId :globalStateIdForCadreInsurance,
   					   cadreEnrollmentYearId : 0,           
   					   locationId: 0,                                               
   					   status :	"",  
   					   category : "",             
   					   fromDateStr : "",    
   					   toDateStr : "",
   					   type : "status",
   					   locationType : "district",
   					   sortingCondition : "insuredMember",
   					   order : "desc"
   				  };
   	console.log(jsObj);                                
   	$.ajax({
   			type : 'POST',
   			url : 'getLocationWiseThenStatusWiseInsuranceMemberCountForTSAction.action',
   			dataType : 'json',  
   			data : {task:JSON.stringify(jsObj)}
   	}).done(function(result){  
   			console.log(result);
   	});
   }            