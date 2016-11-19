	/*$(document).on("change", "#stateId", function(e){
		getVendors();  
	});  
	$(document).on("change", "#vendorId", function(e){
		getVendorDistricts();
	});
	$(document).on("change", "#districtId", function(e){
		getVendorConstituencies();
	});	*/
	
function getDistricts(){
	$('#districtId').find('option').remove();
	var jsObj = { 
	}
	$.ajax({
		type : 'GET',
		url : 'getDataCadreRegUserAssignedDistrictsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#districtId").append('<option value="0">Select District</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#districtId").trigger("chosen:updated");		
	});
}
	
function getConstituencies(districtId){
	$('#constituencyId').find('option').remove();
	var jsObj = { 
		districtId :districtId
	}
	$.ajax({
		type : 'GET',
		url : 'getDataMntrgUsersConstituenciesAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#constituencyId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#constituencyId").trigger("chosen:updated");		
	});
}

function getUsers(constituencyId){
	$('#cadreSurveyUserId').find('option').remove();
	$("#cadreSurveyUserId").trigger("chosen:updated");		
	/*var constituencyId = [];
	if(type == "onchange")
		constituencyId = $("#constituencyId").val();*/
	
	var jsObj = { 
	constituencyId : constituencyId
	}
	$.ajax({
		type : 'GET',
		url : 'getDataMntrgUserAssignedUsersAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#cadreSurveyUserId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#cadreSurveyUserId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#cadreSurveyUserId").trigger("chosen:updated");		
	});
}
		
	function getVendors(){
		clearVendors();
		clearDistricts();
		clearConstituencies();
			
		var stateId = $("#stateId option:selected").val();
		var jsObj = { stateId : stateId };
			
		if(stateId == 0){
			return;
		}
		$.ajax({
			type : 'GET',
			url : 'getVendorsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result!=null && result.length>0){
				for(var i in result){
					$('#vendorId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
			}
			refreshSelectBox('vendorId');
		});     
	}
	function getVendorDistricts(){
		clearDistricts();
		clearConstituencies();
			
		var stateId  = $("#stateId option:selected").val();
		var vendorId = $("#vendorId option:selected").val();
		var jsObj = { 
			stateId : stateId,
			vendorId : vendorId 
		};
			
		$.ajax({
			type : 'GET',
			url : 'getVendorDistrictsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result!=null && result.length>0){
				for(var i in result){
					$('#districtId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
		}
		refreshSelectBox('districtId');
		});
	}
	function getVendorConstituencies(){
		clearConstituencies();
			
		var vendorId = $("#vendorId option:selected").val();
		var districtId  = $("#districtId option:selected").val();
			
		var jsObj = { 
			vendorId : vendorId ,
			districtId : districtId
		};
			
		$.ajax({
			type : 'GET',
			url : 'getVendorConstituenciesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result!=null && result.length>0){
				for(var i in result){
					$('#constituencyId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
			}
			refreshSelectBox('constituencyId');
		});
	}
	function clearVendors(){
		$('#vendorId').find('option').remove();
		$('#vendorId').append('<option value="0">Select Vendor</option>');
		refreshSelectBox('vendorId');  
	}
	function clearDistricts(){
		$('#districtId').find('option').remove();
		$('#districtId').append('<option value="0">Select District</option>');
		refreshSelectBox('districtId');
	}
	function clearConstituencies(){
		$('#constituencyId').find('option').remove();
		$('#constituencyId').append('<option value="0">Select Constituency</option>');
		refreshSelectBox('constituencyId');
	}
	function refreshSelectBox(selectBoxId){
		$("#"+selectBoxId).trigger("chosen:updated");
	}
	/*$(document).on("click","#getRegStatusId",function(){
		$("#detailsDivId").show();
		$("#totalCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#loggedInFieldUsersId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		getCadreRegStatusType();
		getReasons();
	});*/
	var globalSrt = '';
	function getReasons(){
		$.ajax({
			type:'GET',
			url: 'getReasonAction.action',           
			dataType: 'json',
			data: {}
		}).done(function(result){     
			
			if(result != null && result.length > 0){
				globalSrt = '';  
				globalSrt+='<option value="'+0+'">Select Reason</option>';
				for(var i in result){
					globalSrt+='<option value="'+result[i].id+'">'+result[i].name+'</option>';  
				}
			}else{
				
			}     
		});
	}
	/*function getCadreRegStatusType(){
		
		var userId = $("#cadreSurveyUserId").val();
		var constituencyId = $("#constituencyId").val();
		var dateStr = $(".datePicker").val();
		var dateArr = dateStr.split("-");
		var startDate = dateArr[0].trim();
		var endDate = dateArr[1].trim();
		var jsObj=
		{				
			userId : userId,
			constituencyId : constituencyId,
			startDate : modifyDate(startDate),
			endDate : modifyDate(endDate)          
		}
		$.ajax({
			type:'GET',
			url: 'getTotalRegCdrVendorWiseNewAction.action',    
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#totalCountId").html('');
			if(result != null){  
				buildTotalCount(result);  
			}else{
				$("#totalCountId").html('No Data Available...');
			}
		});
		$.ajax({
			type:'GET',
			url: 'getTotalRegCdrVendorAndTabUserWiseNewAction.action',    
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#loggedInFieldUsersId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			if(result != null && result.length > 0){  
				buildTotalRegCdrVendorAndTabUserWise(result,modifyDate(startDate),modifyDate(endDate));
			}else{
				$("#loggedInFieldUsersId").html('No Data Available...');  
			}
		});  
	}  */
	function modifyDate(date){  
		var dateArr = date.split("/");     
		return dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
	}
	function buildTotalCount(result){
		var str = '';
		str+='<div class="col-md-3 col-xs-12 col-sm-6">';
			str+='<h4 class="text-capitalize">total registered</h4>';  
			str+='<h2>'+result.count+'</h2>';
		str+='</div>';
		str+='<div class="col-md-3 col-xs-12 col-sm-6">';
			str+='<h4 class="text-capitalize">Verification Pending</h4>';
			str+='<h2>'+result.availableCount+'</h2>';   
		str+='</div>';
		str+='<div class="col-md-3 col-xs-12 col-sm-6">';
			str+='<h4 class="text-capitalize">Verified passed</h4>';
			str+='<h2>'+result.actualCount+'</h2>';
		str+='</div>';
		str+='<div class="col-md-3 col-xs-12 col-sm-6">';
			str+='<h4 class="text-capitalize">Verified - Junk/Rejected</h4>';
			str+='<h2>'+result.orderId+'</h2>';
		str+='</div>';
		$("#totalCountId").html(str);
	}
	function buildTotalRegCdrVendorAndTabUserWise(result,startDate,endDate){
		
		var str = '';
		str+='<h4 class="panel-title text-muted">User Wise Data Verification Details</h4>';
			str+='<div class="table-responsive">';
				str+='<table class="table b_1 m_top10" id="tabWiseDtlsId">';
					str+='<thead>';
						str+='<th>USER ID</th>';
						str+='<th>TAB USER NAME</th>';
						str+='<th>MOBILE NO</th>';
						str+='<th>COMPLETED REGISTRATIONS</th>';
						str+='<th>VERIFIED - PASSED</th>';
						str+='<th>VERIFIED - JUNK/REJECTED</th>';
						str+='<th>PENDING</th>';
						str+='<th></th>';
					str+='</thead>';
					for(var i in result){   
						str+='<tr>';
							str+='<td>'+result[i].userName+'</td>';
							str+='<td>'+result[i].tabUserName+'</td>';
							str+='<td>'+result[i].mobileNo+'</td>';
							str+='<td>'+result[i].totalCount+'</td>';
							str+='<td>'+result[i].passedcount+'</td>';
							str+='<td>'+result[i].rejectedCount+'</td>';
							str+='<td>'+result[i].pendingCount+'</td>';  
							str+='<td><button class="btn btn-success issuesBtn" attr_user_mobile="'+result[i].mobileNo+'" attr_user_name="'+result[i].tabUserName+'" attr_survey_user_id="'+result[i].cadreSurveyUserId+'" attr_tab_user_id="'+result[i].tabUserId+'" attr_web_user_id="'+0+'" attr_start_date="'+startDate+'" attr_end_date="'+endDate+'">Verify Records</button></td>';
						str+='</tr>';  
					}
				str+='</table>';
			str+='</div>';
			$("#loggedInFieldUsersId").html(str);
			$("#tabWiseDtlsId").dataTable();    
	}
	$(document).on('click','.issuesBtn',function(){
		var surveyUserId = $(this).attr("attr_survey_user_id");
		var tabUserId = $(this).attr("attr_tab_user_id");
		var webUserId = $(this).attr("attr_web_user_id");
		var startDate = $(this).attr("attr_start_date");
		var endDate = $(this).attr("attr_end_date");
		var userName = $(this).attr("attr_user_name");
		var userMobile = $(this).attr("attr_user_mobile");
		var resultType="All"
		var minValue = 0; 
		if(webUserId==0){
			$("#userId").html("Tab UserID - "+tabUserId+"");	
			$("#userDescriptionId").html("<i>"+userName+" - "+userMobile+"</i>");	
		}else{
			$("#userId").html("User ID - "+webUserId+"");	
			$("#userDescriptionId").html("<i>"+userName+" - "+userMobile+"</i>");		
		}
	   
	   	$("#relativePaginationId").html(' ');
		$("#selfPaginationId").html(' ');
	    $("#issuesDataMonitroingDashboardModal").modal('show');
		$("#issuesDataMonitroing li").removeClass("active");
		$("#issuesDataMonitroing li:first-child").addClass("active");  
		$(".activeCls").addClass("active");
		$(".relativeCls").removeClass("active");
		getMembersDetails(surveyUserId,tabUserId,webUserId,userName,userMobile,minValue,resultType);
	});
	function getMembersDetails(surveyUserId,tabUserId,webUserId,userName,userMobile,minValue,resultType){
		var dateStr = $(".datePicker").val();
		var dateArr = dateStr.split("-");
		var startDate = dateArr[0].trim();
		var endDate = dateArr[1].trim();
		if(resultType=="All"){
			$("#selfTblDivId").html(' ');
			$("#relativeDivId").html(' ');
			$("#selfTblDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			$("#relativeDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   }else if(resultType=="Self"){
			$("#selfTblDivId").html(' ');  
			$("#selfTblDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   }else if(resultType=="Relative"){
			$("#relativeDivId").html(' ');   
			$("#relativeDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   } 
	   $("#issuesDataMonitroingDashboardModal").modal('show');
		
		var jsObj=
		{				
			surveyUserId : surveyUserId,
			tabUserId : tabUserId,
			webUserId : webUserId,
			startDate : modifyDate(startDate),
			endDate : modifyDate(endDate) ,  
			minValue :minValue,
            maxValue :10,
			resultType:resultType,
			verificationStatus:"Total",
            dataSourceType:"",
			stateId : 0
		}    
		$.ajax({
			type:'GET',
			url: 'getVerifiedDtlsAction.action',           
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#cadreValidateId").html('');
			if(result != null && result.length > 0){  
				buildVerifiedDtlsCount(result,surveyUserId,tabUserId,webUserId,userName,userMobile,minValue,resultType);  
			}else{
				if(resultType=="All"){    
					$("#selfTblDivId").html('No Data Available...');
					$("#relativeDivId").html('NO DATA Available.');
				}else if(resultType=="Self"){
					$("#selfTblDivId").html('No Data Available...');
				}else if(resultType=="Relative"){
					$("#relativeDivId").html('No Data Available...');
				}
			}
		});
	}
		
	function buildVerifiedDtlsCount(result,surveyUserId,tabUserId,webUserId,userName,userMobile,minValue,resultType){
		$("#globalErrId").html('');  
		$("#globalSuccId").html('');
		if(resultType=="All" || resultType=="Self"){  
		var str = '';
		var selfTotalCount=0;
			str+='<table class="table">';
				str+='<thead class="text-capital">';
					str+='<th>captured photo</th>';
					str+='<th>Voter photo</th>';
					str+='<th>name</th>';
					str+='<th>mobile number</th>';
					str+='<th>gender</th>';
					str+='<th><input id="globalSelectOwnId" type="checkbox"/></th>';
				str+='</thead>';
				str+='<tbody class="b_1">';
				selfTotalCount=result[0][0].totalCount;
					for(var i in result[0]){
						if(result[0][i].status == "Approved"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="https://www.mytdp.com/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="https://www.mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[0][i].name+'</td>';
								str+='<td>'+result[0][i].mobileNo+'</td>';  
								str+='<td>'+result[0][i].gender+'</td>';
								str+='<td>';
									//str+='<input type="checkbox"/>';
								str+='</td>';
							str+='</tr>';  

					
							str+='<tr>';
								str+='<td><img src="images/right.jpg" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str+='<td colspan="3">';
									//str+='<select>';
										//str+='<option></option>';
									//str+='</select>';
								str+='</td>';
							str+='</tr>';
						}
						if(result[0][i].status == "Rejected"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="https://www.mytdp.com/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="https://www.mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[0][i].name+'</td>';
								str+='<td>'+result[0][i].mobileNo+'</td>';
								str+='<td>'+result[0][i].gender+'</td>';
								str+='<td>';
									//str+='<input type="checkbox"/>';
								str+='</td>';
							str+='</tr>';  

					     
							str+='<tr>';
								str+='<td><img src="images/close.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str+='<td colspan="3">';
									str+='<input type="text" value="'+result[0][i].wish+'" class="form-control" disabled></input>';    
										//str+='<option></option>';  
									//str+='</select>';
								str+='</td>';
							str+='</tr>';
						}
						if(result[0][i].status == "noStatus"){
							str+='<tr class="ownDeleteRow'+i+'">';
								str+='<td rowspan="2">';
									str+='<img src="https://www.mytdp.com/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="https://www.mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[0][i].name+'</td>';
								str+='<td>'+result[0][i].mobileNo+'</td>';
								str+='<td>'+result[0][i].gender+'</td>';
								str+='<td><input attr_position_id="'+i+'" attr_cadre_id="'+result[0][i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'" class="localSelectOwnCls" type="checkbox"/></td>';  
							str+='</tr>'; 
					
					
							str+='<tr class="ownDeleteRow'+i+'">';    
								str+='<td>';  
									str+='<button class="btn btn-success singleApproveCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result[0][i].cadreId+'">Approve</button>';
									str+='<button class="btn btn-danger singleRejectCls btn-sm"  attr_position_id="'+i+'" attr_cadre_id="'+result[0][i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'" style="margin-left: 5px;">Reject</button>';
								str+='</td>';    
								str+='<td colspan="3">';
									str+='<select class="select" id="ownHideSelectBoxId'+i+'" style="display:none;">';
										str+='<option value="0">Andhra Pradesh</option>';                
									str+='</select>'; 
									str+='<span id="ownErrorId'+i+'" style="color:red;"></span>';
								str+='</td>';    
							str+='</tr>'; 
						}
					}
				str+='</tbody>';
			str+='</table>';
			$("#selfTblDivId").html(str);
			for(var i in result[0]){
				if(result[0][i].status == "noStatus"){
					$("#ownHideSelectBoxId"+i).html(globalSrt);  
				}
			}
			if(minValue == 0 && selfTotalCount > 10){
				$("#selfPaginationId").pagination({
					items: selfTotalCount,
					itemsOnPage: 10,
					cssStyle: 'light-theme',
					onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*10;
						getMembersDetails(surveyUserId,tabUserId,webUserId,userName,userMobile,num,"Self");
					}
				});
			}	
		}
		if(resultType=="All" || resultType=="Relative"){
			var relativeTotalCount=0;
			var str2 = '';
			str2+='<table class="table">';
				str2+='<thead class="text-capital">';
					str2+='<th>captured photo</th>';
					str2+='<th>Voter photo</th>';
					str2+='<th>name</th>';
					str2+='<th>mobile number</th>';
					str2+='<th>gender</th>';
					str2+='<th><input id="globalSelectFamilyId" type="checkbox"/></th>';  
				str2+='</thead>';
				str2+='<tbody class="b_1">';
					for(var i in result[1]){ 
						relativeTotalCount=result[1][0].totalCount;
						if(result[1][i].status == "Approved"){
							str2+='<tr>';
								str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td>'+result[1][i].name+'</td>';
								str2+='<td>'+result[1][i].mobileNo+'</td>';
								str2+='<td>'+result[1][i].gender+'</td>';
								str2+='<td>';
									//str+='<input type="checkbox"/>';
								str2+='</td>';
							str2+='</tr>';  

					
							str2+='<tr>';
								str2+='<td><img src="images/right.jpg" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str2+='<td colspan="3">';
									//str+='<select>';
										//str+='<option></option>';
									//str+='</select>';
								str2+='</td>';
							str2+='</tr>';
						}
						if(result[1][i].status == "Rejected"){
							str2+='<tr>';
								str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td>'+result[1][i].name+'</td>';
								str2+='<td>'+result[1][i].mobileNo+'</td>';
								str2+='<td>'+result[1][i].gender+'</td>';
								str2+='<td>';
									//str+='<input type="checkbox"/>';
								str2+='</td>';
							str2+='</tr>';  

					
							str2+='<tr>';
								str2+='<td><img src="images/close.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str2+='<td colspan="3">';
									str2+='<input type="text" value="'+result[1][i].wish+'" disabled></input>';
										//str+='<option></option>';
									//str+='</select>';
								str2+='</td>';
							str2+='</tr>';
						}
						if(result[1][i].status == "noStatus"){
							str2+='<tr class="familyDeleteRow'+i+'">';
								str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';  
								str2+='<td>'+result[1][i].name+'</td>';
								str2+='<td>'+result[1][i].mobileNo+'</td>';
								str2+='<td>'+result[1][i].gender+'</td>';
								str2+='<td><input attr_position_id="'+i+'"  attr_cadre_id="'+result[1][i].cadreId+'" attr_reason_id="familyHideSelectBoxId'+i+'" class="localSelectFamilyCls" type="checkbox"/></td>';
							str2+='</tr>'; 
							str2+='<tr class="familyDeleteRow'+i+'">';
								str2+='<td>';
									str2+='<button class="btn btn-success singleApproveCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result[1][i].cadreId+'">Approve</button>';//familyHideSelectBoxId0
									str2+='<button class="btn btn-danger singleRejectCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result[1][i].cadreId+'" attr_reason_id="familyHideSelectBoxId'+i+'" style="margin-left: 5px;">Reject</button>';
								str2+='</td>';
								str2+='<td colspan="3">';
									str2+='<select class="select" id="familyHideSelectBoxId'+i+'" style="display:none;">';      
										str2+='<option value="0">Andhra Pradesh</option>';                  
									str2+='</select>';
									str2+='<span id="familyErrorId'+i+'" style="color:red;"></span>';
								str2+='</td>';    
							str2+='</tr>';    
						}
					}
					
					str2+='</tbody>';
					str2+='</table>';
					$("#relativeDivId").html(str2);
					for(var i in result[1]){
						if(result[1][i].status == "noStatus"){    
							$("#familyHideSelectBoxId"+i).html(globalSrt);        
						}
					}
					//$(".select").chosen({width:'100%'});        
					if(minValue == 0 && relativeTotalCount > 10){
						$("#relativePaginationId").pagination({
						items: relativeTotalCount,
						itemsOnPage: 10,
						cssStyle: 'light-theme',
						onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*10;
						getMembersDetails(surveyUserId,tabUserId,webUserId,userName,userMobile,num,"Relative");
					}
				});
			}	
		}
	}      		
	
	$(document).on('click','#globalSelectOwnId',function(){
		if($(this).is(':checked')){
			$(".localSelectOwnCls").prop( "checked", true);
			$('.singleApproveCls').prop('disabled', true);
			$('.singleRejectCls').prop('disabled', true);
		}else{
			$(".localSelectOwnCls").prop( "checked", false);
			$('.singleApproveCls').prop('disabled', false);
			$('.singleRejectCls').prop('disabled', false);    
		}
		$('.localSelectOwnCls').each(function(){
			if($(this).is(':checked')){
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).show();
				//to clear error message  
				var position = $(this).attr("attr_position_id");
				$("#ownErrorId"+position).html("");
				$("#globalErrId").html("");				
			}else{
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).hide();
				//to clear error message
				var position = $(this).attr("attr_position_id");
				$("#ownErrorId"+position).html("");
				$("#globalErrId").html("");				
			}  
		});
	}); 
	$(document).on('click','#globalSelectFamilyId',function(){
		if($(this).is(':checked')){
			$(".localSelectFamilyCls").prop( "checked", true);  
			$('.singleApproveCls').prop('disabled', true);
			$('.singleRejectCls').prop('disabled', true);
		}else{
			$(".localSelectFamilyCls").prop( "checked", false);
			$('.singleApproveCls').prop('disabled', false);
			$('.singleRejectCls').prop('disabled', false);    
		}
		$('.localSelectFamilyCls').each(function(){
			if($(this).is(':checked')){
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).show();
				//to clear error message
				var position = $(this).attr("attr_position_id");
				$("#familyErrorId"+position).html(""); 
				$("#globalErrId").html("");
			}else{    
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).hide(); 
				//to clear error message
				var position = $(this).attr("attr_position_id");
				$("#familyErrorId"+position).html("");  
				$("#globalErrId").html("");
			}  
		});
	});
	  
	//by selecting single check box show and hide the chexk box. for own tab
	$(document).on('click','.localSelectOwnCls',function(){
		if($(this).is(':checked')){
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).show();
			$("#globalErrId").html("");
		}else{
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).hide(); 
			//to clear error message.
			var position = $(this).attr("attr_position_id");
			$("#ownErrorId"+position).html("");	
			$("#globalErrId").html("");
		}
		var count = $("input.localSelectOwnCls:checked").length;
		if(count >= 1){
			$('.singleApproveCls').prop('disabled', true);
			$('.singleRejectCls').prop('disabled', true);
		}else if( count == 0){
			$('.singleApproveCls').prop('disabled', false);
			$('.singleRejectCls').prop('disabled', false);   
		}
	});
	//by selecting single check box show and hide the chexk box. for family tab
	$(document).on('click','.localSelectFamilyCls',function(){
		
		if($(this).is(':checked')){   
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).show();
		}else{
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).hide(); 
			//to clear error message
			var position = $(this).attr("attr_position_id");  
			$("#familyErrorId"+position).html(""); 
		}
		var count = $("input.localSelectFamilyCls:checked").length;
		if(count >= 1){
			$('.singleApproveCls').prop('disabled', true);
			$('.singleRejectCls').prop('disabled', true);
		}else if( count == 0){
			$('.singleApproveCls').prop('disabled', false);
			$('.singleRejectCls').prop('disabled', false);   
		}
	});
	//single rejected 
	$(document).on("click",".singleRejectCls",function(){
		var cadreId = $(this).attr("attr_cadre_id");
		$("#submitBtnReasonId").attr("attr_cadre_id",cadreId);
		var position = $(this).attr("attr_position_id");
		$("#submitBtnReasonId").attr("attr_position_id",position);
		$(".reasonErrorCls").html('');
		$(".reasonSuccessCls").html('');
		$("#rsnSlctBxId").html(globalSrt);  
		$("#rejectedModalId").modal("show");  
	});	
	$(document).on('click','#submitBtnReasonId',function(){ 
		$(".reasonErrorCls").html('');  
		var cadreId = $(this).attr("attr_cadre_id");
		var reasonId = $("#rsnSlctBxId").val();
		var position = $(this).attr("attr_position_id");   
		if(reasonId == 0){  
			$(".reasonErrorCls").html('Please Select Reason...');  
			return;  
		} 
		var rejectList = [];
		rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId });
		var singleReject = {"data" : rejectList};
		$.ajax({
			type:'GET',      
			url: 'updateRejectListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){  
			if(result != null){ 
				if(result.resultCode == 1){      
					$(".reasonSuccessCls").html('Updated Successfully...'); 
				}else{
					$(".reasonSuccessCls").html('Updation Failed...'); 
				}
				setTimeout(function(){ $("#rejectedModalId").modal("hide"); }, 3000);
				$(".closeButtonCls").trigger("click");
				//for hide deleted row(s)
				if($("#self").hasClass('active')){
					$(".ownDeleteRow"+position).remove();   
					$(".ownDeleteRow"+position).remove();
				}else{
					$(".familyDeleteRow"+position).remove();   
					$(".familyDeleteRow"+position).remove();          
				}
				         
			}
		});
	});
	
	//bulk reject
	$(document).on('click','#bulkRejectId',function(){
		$("#globalSuccId").html(''); 
		$("#globalErrId").html('');     
		var count = 0;
		var checkCount = 0;
		if($("#self").hasClass('active')){
			$('.localSelectOwnCls').each(function(){  
				if($(this).is(':checked')){
					checkCount = checkCount + 1;
					var selectReasonId = $(this).attr("attr_reason_id");
					reasonId = $("#"+selectReasonId).val();
					if(reasonId == 0){
						var position = $(this).attr("attr_position_id");
						$("#ownErrorId"+position).html("please Select Reason");
						count = count + 1;
						return false;         
					}else{
						var position = $(this).attr("attr_position_id");
						$("#ownErrorId"+position).html("");  
					}  
				}
			});
		}else{
			$('.localSelectFamilyCls').each(function(){  
				if($(this).is(':checked')){
					checkCount = checkCount + 1;
					var selectReasonId = $(this).attr("attr_reason_id");
					reasonId = $("#"+selectReasonId).val();
					if(reasonId == 0){
						var position = $(this).attr("attr_position_id");
						$("#familyErrorId"+position).html("please Select Reason");
						count = count + 1;
						return false;         
					}else{
						var position = $(this).attr("attr_position_id");  
						$("#familyErrorId"+position).html("");  
					}  
				}
			});
		}
		if(count > 0){  
			return;    
		}   
		if(checkCount == 0){
			$("#globalErrId").html("Please Select Atleast One member");  
			return;    
		}  		
		var cadreId = '';
		var selectReasonId = '';
		var reasonId = '';
		var positionIdArr = [];
		var position = 0;
		var rejectList = [];
		if($("#self").hasClass('active')){
			$('.localSelectOwnCls').each(function(){
				if($(this).is(':checked')){
					cadreId = $(this).attr("attr_cadre_id");
					selectReasonId = $(this).attr("attr_reason_id");
					reasonId = $("#"+selectReasonId).val();
					rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId}); 
					//collect row position for delete row.
					position = $(this).attr("attr_position_id");
					positionIdArr.push(position);
				}
			});
		}else{
			$('.localSelectFamilyCls').each(function(){
				if($(this).is(':checked')){
					cadreId = $(this).attr("attr_cadre_id");
					selectReasonId = $(this).attr("attr_reason_id");
					reasonId = $("#"+selectReasonId).val();
					rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId});
					//collect row position for delete row.
					position = $(this).attr("attr_position_id");
					positionIdArr.push(position);      
				}
			});
		}
		
		var singleReject = {"data" : rejectList};        
		$.ajax({
			type:'GET',      
			url: 'updateRejectListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			if(result != null){  
				if(result.resultCode == 1){ 
					$("#globalSuccId").html('Updated Successfully...'); 
					//for hide deleted row(s)
					if($("#self").hasClass('active')){
						$(".localSelectOwnCls").prop( "checked",false);
						for(var i in positionIdArr){
							$(".ownDeleteRow"+positionIdArr[i]).remove();   
							$(".ownDeleteRow"+positionIdArr[i]).remove();
						}
					}else{
						$(".localSelectFamilyCls").prop( "checked",false);    
						for(var i in positionIdArr){
							$(".familyDeleteRow"+positionIdArr[i]).remove();   
							$(".familyDeleteRow"+positionIdArr[i]).remove();       
						}     
					}
					$('.singleApproveCls').prop('disabled', false);
					$('.singleRejectCls').prop('disabled', false);      
				}else{
					$("#globalErrId").html('Updation Failed...');     
				}
			}else{    
			}
		});
	});
	//single approve
	$(document).on("click",".singleApproveCls",function(){  
		var cadreId = $(this).attr("attr_cadre_id");
		var position = $(this).attr("attr_position_id");
		$("#groupingApprovedYes").attr("attr_position_id",position);
		$("#groupingApprovedYes").attr("attr_cadre_id",cadreId);
		$("#confirmModalId").modal("show");      
	});
	$(document).on("click","#groupingApprovedYes",function(){
		var cadreId = $(this).attr("attr_cadre_id");
		var position = $(this).attr("attr_position_id");   
		var rejectList = [];
		rejectList.push({"cadreId" : cadreId}); 
		var singleReject = {"data" : rejectList};
		$.ajax({
			type:'GET',      
			url: 'updateApproveListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			if(result != null){  
				if(result.resultCode == 1){ 
					$("#successApprovedId").html('Updated Successfully...'); 
				}else{
					$("#errorApprovedId").html('Updation Failed...');       
				} 
				setTimeout(function(){ $("#confirmModalId").modal("hide"); }, 2000);
				$(".closeButtonCls").trigger("click");      
				//for hide deleted row(s)
				if($("#self").hasClass('active')){
					$(".ownDeleteRow"+position).remove();   
					$(".ownDeleteRow"+position).remove();
				}else{
					$(".familyDeleteRow"+position).remove();   
					$(".familyDeleteRow"+position).remove();              
				}
			}else{    
			}
		});  
	});
	
	//bulk approve      
	$(document).on('click','#bulkApproveId',function(){  
		var isMemberSelected="NO";
		if($("#self").hasClass('active')){
			$('.localSelectOwnCls').each(function(){  
				if($(this).is(':checked')){
					isMemberSelected="Yes";
				}
			});
		}
		$('.localSelectFamilyCls').each(function(){  
			if($(this).is(':checked')){
				isMemberSelected="Yes";
			}
		});
		if(isMemberSelected == "NO"){
			$("#globalErrId").html("Please Select Atleast One Member.");
			return;       
		}    
		$("#globalErrId").html(' ');  
		var cadreId = ''; 
		var rejectList = []; 
		var positionIdArr = [];
		var position = 0;
		if($("#self").hasClass('active')){
			$('.localSelectOwnCls').each(function(){  
				if($(this).is(':checked')){
					cadreId = $(this).attr("attr_cadre_id");
					rejectList.push({"cadreId" : cadreId});
					//collect row position for delete row.
					position = $(this).attr("attr_position_id");
					positionIdArr.push(position);
				}
			});
		}else{
			$('.localSelectFamilyCls').each(function(){    
				if($(this).is(':checked')){
					cadreId = $(this).attr("attr_cadre_id");
					rejectList.push({"cadreId" : cadreId});
					//collect row position for delete row.
					position = $(this).attr("attr_position_id");
					positionIdArr.push(position);
				}  
			});
		}
		var singleReject = {"data" : rejectList};        
		$.ajax({
			type:'GET',      
			url: 'updateApproveListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			if(result != null){  
				if(result.resultCode == 1){ 
					$("#globalSuccId").html('Updated Successfully...');
					//for hide deleted row(s)
					if($("#self").hasClass('active')){
						 
						for(var i in positionIdArr){
							$(".ownDeleteRow"+positionIdArr[i]).remove();   
							$(".ownDeleteRow"+positionIdArr[i]).remove();
						}  
					}else{
						
						for(var i in positionIdArr){
							$(".familyDeleteRow"+positionIdArr[i]).remove();     
							$(".familyDeleteRow"+positionIdArr[i]).remove();      
						}	
					}
					$('.singleApproveCls').prop('disabled', false);    
					$('.singleRejectCls').prop('disabled', false);
				}else{
					$("#globalErrId").html('Updation Failed...');   
				}
			}else{        
			}
		});
	});
	$(document).on("click","#submitBtnReasonId",function(){
		var cadreId = $(this).attr("attr_cadre_id");
		var reasonId = $("#rsnSlctBxId").val();
		if(reasonId == 0){  
			$(".reasonErrorCls").html("Please Select Reason.");
			return;
		}
		$(".reasonErrorCls").html(' ');
	});
	$(document).on("click","#groupingApprovedNo",function(){
		var cadreId = $(this).attr("attr_cadre_id");
	    $("#confirmModalId").modal("hide");  
	});   
	$(document).on('click','.closeButtonCls',function(){  
		setTimeout(function(){
			$('body').addClass("modal-open");
		}, 500);    
	});
	$(document).on('click','#bulkApproveId',function(){
		//$("#getRegStatusId").trigger("click");
	}); 
	$(document).on('click','#bulkRejectId',function(){
		//$("#getRegStatusId").trigger("click");    
	});

	
function getOverAllVerificationCount(){
	if(!validations())
		{
			return;
		}	
	
	  $("#verifiactionDivId").show();
	  
		var dateStr = $(".datePicker").val();
		var dateArr = dateStr.split("-");
		var fromDate = dateArr[0].trim();
		var toDate = dateArr[1].trim();
		var state=0;
		var userId = $("#cadreSurveyUserId").val();
		var constituencyId = $("#constituencyId").val();
		var districtId = $("#districtId").val();
		
		var jsObj = { 
		  stateId : state,
		  districtId : districtId,
		  constituencyId : constituencyId,
		  userId : userId,
		  fromDate : fromDate,		
		  toDate : toDate		 	
		}
		$.ajax({
			type : 'GET',
			url : 'getVerificationCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			$("#verifiactionDivId").show();
			if(result != null){
				$("#totalRegisteredId").html(result.todayRegCount);
				$("#verfPendingId").html(result.pendingCount);
				$("#verPassedId").html(result.passedcount);
				$("#verRejectedId").html(result.rejectedCount);
			}	
		});
	}	
$(document).on("click","#getRegStatusId",function(){
	getOverAllVerificationCount();
	getCadreVerificationDetails();
});
	
function getCadreVerificationDetails(){
	if(!validations())
		{
			return;
		}
	
	var dateStr = $(".datePicker").val();
		var dateArr = dateStr.split("-");
		var fromDate = dateArr[0].trim();
		var toDate = dateArr[1].trim();
		var state=0;
		var userId = $("#cadreSurveyUserId").val();
		var constituencyId = $("#constituencyId").val();
		var districtId = $("#districtId").val();
		
		var jsObj = { 
		  stateId : state,
		  districtId : districtId,
		  constituencyId : constituencyId,
		  userId : userId,
		  fromDate : fromDate,		
		  toDate : toDate		 	
		}
		$.ajax({
			type : 'GET',
			url : 'getCadreVerificationDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
				buildTotalRegCdrVendorAndTabUserWise(result,fromDate,toDate);
		});
}	

function validations(){
	var districtId = $("#districtId").val();
	if(districtId == 0){
		$("#errMsgDivId").html("<span style='color:red;'>Please Select District</span>");
		return false;
	}else{
		$("#errMsgDivId").html("");
	}
	return true;
}

	