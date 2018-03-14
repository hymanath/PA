	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));
	
	noOfCompanyProperties();
	var devloperIds = [];
		$(".devloperCls").find("h2").each(function(){ 
		var removeChar = this.id.replace('devloper','');
		devloperIds.push(removeChar);
		});
		
	var companyIds = [];
		$(".companyCls").find("h2").each(function(){
		var removeChar = this.id.replace('company','');	
		companyIds.push(removeChar);
		});
	var approvalDevloperIds = [];
		$(".approvalDevloperCls").find("h4").each(function(){
		var removeChar = this.id.replace('devloperId','');	
		approvalDevloperIds.push(removeChar);
		});
	var approvalCompanyIds = [];
		$(".approvalItCompanyCls").find("h4").each(function(){
		var removeChar = this.id.replace('itCompanyId','');	
		approvalCompanyIds.push(removeChar);
		});
		
function noOfCompanyProperties(){
	$.ajax({
		//url: wurl+"/DTP/iTMinisterDashboardRestController/getDeveloperPropertyOverviewDetails/",
		url : "http://localhost:8080/DTP/iTMinisterDashboardRestController/getDeveloperPropertyOverviewDetails",
		type : "GET",
		processData : false,
		contentType : false,
			success : function(ajaxresp) {
				if(ajaxresp!=null){
					initializeDevloperProperties(ajaxresp);
				}		
			},
			error:function(error,request){
				console.log("UNABLE TO DISPLAY");
			}
		});
	$.ajax({
		//url: wurl+"/DTP/iTMinisterDashboardRestController/getItCompanyPropertyOverviewDetails/",
		url : "http://localhost:8080/DTP/iTMinisterDashboardRestController/getItCompanyPropertyOverviewDetails",
		type : "GET",
		processData : false,
		contentType : false,
			success : function(ajaxresp) {
				if(ajaxresp!=null){
					initializeCompanyProperties(ajaxresp);
				}		
			},
			error:function(error,request){
				console.log("UNABLE TO DISPLAY");
			}
		});
	$.ajax({
		//url: wurl+"/DTP/iTMinisterDashboardRestController/getPendingLevelOverviewDetails/",
		url : "http://localhost:8080/DTP/iTMinisterDashboardRestController/getPendingLevelOverviewDetails",
		type : "GET",
		processData : false,
		contentType : false,
			success : function(ajaxresp) {
				if(ajaxresp!=null){
					initializeApprovalProcessFlow(ajaxresp);
				}		
			},
			error:function(error,request){
				console.log("UNABLE TO DISPLAY");
			}
		});
		
	$.ajax({
		//url: wurl+"/DTP/iTMinisterDashboardRestController/getPendingLevelPropertiesDetails/",
		url : "http://localhost:8080/DTP/iTMinisterDashboardRestController/getPendingLevelPropertiesDetails",
		type : "GET",
		processData : false,
		contentType : false,
			success : function(ajaxresp) {
				if(ajaxresp!=null){
					initializeStatusProperties(ajaxresp);
				}		
			},
			error:function(error,request){
				console.log("UNABLE TO DISPLAY");
			}
		});	
	$.ajax({
		//url: wurl+"/DTP/iTMinisterDashboardRestController/getBuildingStatusDetails/",
		url : "http://localhost:8080/DTP/iTMinisterDashboardRestController/getBuildingStatusDetails",
		type : "GET",
		processData : false,
		contentType : false,
			success : function(ajaxresp) {
				if(ajaxresp!=null){
					initializeBuildingStatus(ajaxresp);
				}		
			},
			error:function(error,request){
				console.log("UNABLE TO DISPLAY");
			}
		});	
}
function initializeDevloperProperties(ajaxresp){
	for(var i in ajaxresp){
		for(var j in devloperIds){
			if(ajaxresp[i].id == devloperIds[j]){
			$("#"+"devloper"+devloperIds[j]).text(ajaxresp[i].count);
			}	
		}
		if(ajaxresp[i].name === "registeredProperty"){
			$("#devloperRegPropertiesId").text(ajaxresp[i].count);
		}
		if(ajaxresp[i].name === "registeredCompany"){
			$("#devloperRegCompanyId").text(ajaxresp[i].count);
		}
	}
}
function initializeCompanyProperties(ajaxresp){
	for(var i in ajaxresp){
		for(var j in companyIds){
			if(ajaxresp[i].id == companyIds[j]){
			$("#"+"company"+companyIds[j]).text(ajaxresp[i].count);
			}	
		}
		if(ajaxresp[i].name === "registeredProperty"){
			$("#regCompanyPropertiesId").text(ajaxresp[i].count);
		}
		if(ajaxresp[i].name === "registeredCompany"){
			$("#regItCompanyId").text(ajaxresp[i].count);
		}
	}
}
function initializeApprovalProcessFlow(ajaxresp){
	for(var i in ajaxresp[0].subList){
		for(var j in approvalDevloperIds){
			if(ajaxresp[0].name === "devloper"){
				if(approvalDevloperIds[j] == ajaxresp[0].subList[i].id){
					$("#"+"devloperId"+approvalDevloperIds[j]).text(ajaxresp[0].subList[i].count);
				}
			}
		}
	}
	for(var i in ajaxresp[1].subList){
		for(var j in approvalCompanyIds){
			if(ajaxresp[1].name === "itCompany"){
				if(approvalCompanyIds[j] == ajaxresp[1].subList[i].id){
					
					$("#"+"itCompanyId"+approvalCompanyIds[j]).text(ajaxresp[1].subList[i].count);
				}
			}
		}
	}
}
function initializeStatusProperties(ajaxresp){
	for(var i in ajaxresp){
		if(ajaxresp[i].name === "Approved Properties"){
			$(".approvedPropertiesCls").text(ajaxresp[i].count);
			$(".approvedPropertiesSumCls").html(ajaxresp[i].sum+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
		if(ajaxresp[i].name === "Available Properties"){
			$(".availablePropertiesCls").text(ajaxresp[i].count);
			$(".availablePropertiesSumCls").html(ajaxresp[i].sum+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
		if(ajaxresp[i].name === "Occupied Properties"){
			$(".occupiedPropertiesCls").html('<b>'+ajaxresp[i].count+'</b>');
			$(".occupiedPropertiesSumCls").html(ajaxresp[i].sum+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
		if(ajaxresp[i].name === "Occupied IT Companies"){
			$(".occupiedItCompanies").html('<b>'+ajaxresp[i].count+'</b>');
		}
		
	}
}
 function initializeBuildingStatus(ajaxresp){
	for(var i in ajaxresp){
		
		if(ajaxresp[i].name === "DTP Status Buildings"){
			$(".statusBuildingCls").html('<b>'+ajaxresp[i].count+'</b>'+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-size:12px;'>"+ajaxresp[i].sum+"</span>"+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
		if(ajaxresp[i].name === "Non DTP Status Buildings"){
			$(".nonStatusBuildingCls").html('<b>'+ajaxresp[i].count+'</b>'+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-size:12px;'>"+ajaxresp[i].sum+"</span>"+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
	}
	for(var i in ajaxresp[0].subList){
		if(ajaxresp[0].subList[i].name === "Available"){
			$(".statusBuildingAvailableCls").html('<b>Available</b>'+'&nbsp;&nbsp'+'<b>'+ajaxresp[0].subList[i].count+'</b>'+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span align='right'; style='font-size:12px;'>"+ajaxresp[0].subList[i].sum+"</span>"+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
		if(ajaxresp[0].subList[i].name === "Occupied"){
			$(".statusBuildingOccupiedCls").html('<b>Occupied</b>'+'&nbsp;&nbsp'+'<b>'+ajaxresp[0].subList[i].count+'</b>'+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-size:12px;'>"+ajaxresp[0].subList[i].sum+"</span>"+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
		if(ajaxresp[0].subList[i].name === "Available IT Companies"){
			$("#statusAppliedItCompaniesId").html("Applied IT Companies"+'&nbsp;&nbsp;&nbsp;&nbsp'+"<span style='font-size:12px;'>"+ajaxresp[0].subList[i].count+"</span>");
		}
		if(ajaxresp[0].subList[i].name === "Occupied IT Companies"){
			$("#statusOccupiedItCompaniesId").html("Occupied IT Companies"+'&nbsp;&nbsp;&nbsp;&nbsp'+"<span style='font-size:12px;'>"+ajaxresp[0].subList[i].count+"</span>");
		}
		
	}
	for(var i in ajaxresp[1].subList){
		if(ajaxresp[1].subList[i].name === "Available"){
			$(".nonStatusBuildingAvailableCls").html('<b>Available</b>'+'&nbsp;&nbsp'+'<b>'+ajaxresp[1].subList[i].count+'</b>'+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span align='right'; style='font-size:12px;'>"+ajaxresp[1].subList[i].sum+"</span>"+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
		if(ajaxresp[1].subList[i].name === "Occupied"){
			$(".nonStatusBuildingOccupiedCls").html('<b>Occupied</b>'+'&nbsp;&nbsp'+'<b>'+ajaxresp[1].subList[i].count+'</b>'+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-size:12px;'>"+ajaxresp[1].subList[i].sum +"</span>"+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>");
		}
		if(ajaxresp[1].subList[i].name === "Available IT Companies"){
			$("#nonStatusAppliedItCompaniesId").html("Applied IT Companies"+'&nbsp;&nbsp;&nbsp;&nbsp'+"<span style='font-size:12px;'>"+ajaxresp[1].subList[i].count+"</span>");
		}
		if(ajaxresp[1].subList[i].name === "Occupied IT Companies"){
			$("#nonStatusOccupiedItCompaniesId").html("Occupied IT Companies"+'&nbsp;&nbsp;&nbsp;&nbsp'+"<span style='font-size:12px;'>"+ajaxresp[1].subList[i].count+"</span>");
		}
		
	}
	
}	 
		
	