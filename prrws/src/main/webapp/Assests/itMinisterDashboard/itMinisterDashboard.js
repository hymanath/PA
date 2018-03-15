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
	
function noOfCompanyProperties(){
	$.ajax({
		//url: wurl+"/DTP/iTMinisterDashboardRestController/getDeveloperPropertyOverviewDetails/",
		url : "http://192.168.11.165:8080/DTP/iTMinisterDashboardRestController/getDeveloperPropertyOverviewDetails",
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
		url : "http://192.168.11.165:8080/DTP/iTMinisterDashboardRestController/getItCompanyPropertyOverviewDetails",
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
		url : "http://192.168.11.165:8080/DTP/iTMinisterDashboardRestController/getPendingLevelOverviewDetails",
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
		url : "http://192.168.11.165:8080/DTP/iTMinisterDashboardRestController/getPendingLevelPropertiesDetails",
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
		url : "http://192.168.11.165:8080/DTP/iTMinisterDashboardRestController/getBuildingStatusDetails",
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
	var devloperCount=0;
	var itCompanyCount=0;
	var str = '';
	for(var i in ajaxresp[0].subList){
		str+='<div class="col-sm-2">';
		str+='<div class="pad_5 m_top10" style="border-right:1px solid grey; height: 56px;">';
		str+='<h5 class="font_weight" style = "text-transform: uppercase;" ><b>'+ajaxresp[0].subList[i].name+'</b></h5>';
		str+='</div>';
		str+='</div>';
	}
	$("#pendingLevelId").html(str);
	str='';
	str+='<div class="col-sm-9">';
	str+='<div class="row">';
	for(var i in ajaxresp[0].subList){
		str+='<div class="col-sm-2" >';
		str+='<div class="pad_5 m_left-25" style="border-right:1px solid grey; height: 56px;padding-right:10px;">';
		str+='<div class="col-sm-10 m_top10">';
		str+='<h4 class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;">'+ajaxresp[0].subList[i].count+'</h4>';
		devloperCount+= ajaxresp[0].subList[i].count;
		str+='</div>';
		str+='</div>';
		str+='</div>';
	}
	str+='</div>';	
	str+='</div>';	
	$("#devloperPendingLevelDivId").html(str);
	str= '';
	str+='<div class="col-sm-9">';
	str+='<div class="row">';
	for(var i in ajaxresp[1].subList){
		str+='<div class="col-sm-2" >';
		str+='<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">';
		str+='<div class="col-sm-10 m_top10">';
		str+='<h4 class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;">'+ajaxresp[1].subList[i].count+'</h4>';
		itCompanyCount+= ajaxresp[1].subList[i].count;
		str+='</div>';
		str+='</div>';
		str+='</div>';
	}
	str+='</div>';	
	str+='</div>';
	$("#itCompanyPendingLevelDivId").html(str);
	$("#devloperId").text(devloperCount);
	$("#itCompanyId").text(itCompanyCount);
}
function initializeStatusProperties(ajaxresp){
	for(var i in ajaxresp){
		if(ajaxresp[i].name === "Approved Properties"){
			$(".approvedPropertiesCls").html('<h2>'+ajaxresp[i].count+'</h2><p style="font-size:14px;" >'+ajaxresp[i].sum+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>"+'</p>');
		}
		if(ajaxresp[i].name === "Available Properties"){
			$(".availablePropertiesCls").html('<h2>'+ajaxresp[i].count+'</h2><p style="font-size:14px;" >'+ajaxresp[i].sum+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>"+'</p>');
		}
		if(ajaxresp[i].name === "Occupied Properties"){
			$(".occupiedPropertiesCls").html('<b>'+ajaxresp[i].count+'</b><p align = "right"style="font-size:14px;" >'+ajaxresp[i].sum+"&nbsp;&nbsp;<span style='font-size:12px;'>"+"sft"+"</span>"+'</p>');
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
		if(ajaxresp[0].subList[i].name === "Applied IT Companies"){
			
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
		if(ajaxresp[1].subList[i].name === "Applied IT Companies"){
			$("#nonStatusAppliedItCompaniesId").html("Applied IT Companies"+'&nbsp;&nbsp;&nbsp;&nbsp'+"<span style='font-size:12px;'>"+ajaxresp[1].subList[i].count+"</span>");
		}
		if(ajaxresp[1].subList[i].name === "Occupied IT Companies"){
			$("#nonStatusOccupiedItCompaniesId").html("Occupied IT Companies"+'&nbsp;&nbsp;&nbsp;&nbsp'+"<span style='font-size:12px;'>"+ajaxresp[1].subList[i].count+"</span>");
		}
	}
}	 
		
	
	