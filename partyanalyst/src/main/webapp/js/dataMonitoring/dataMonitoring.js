	$(document).on("change", "#stateId", function(e){
		getVendors();  
	});  
	$(document).on("change", "#vendorId", function(e){
		getVendorDistricts();
	});
	$(document).on("change", "#districtId", function(e){
		getVendorConstituencies();
	});	
		
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
	$(document).on("click","#getRegStatusId",function(){
		$("#totalCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#loggedInFieldUsersId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		getCadreRegStatusType();  
	});
	function getCadreRegStatusType(){
		var stateId = $("#stateId").val();
		var vendorId = $("#vendorId").val();
		var districtId = $("#districtId").val();
		var constituencyId = $("#constituencyId").val();
		var dateStr = $(".datePicker").val();
		var dateArr = dateStr.split("-");
		var startDate = dateArr[0].trim();
		var endDate = dateArr[1].trim();
		var jsObj=
		{				
			stateId : stateId,
			vendorId : vendorId,
			districtId : districtId,
			constituencyId : constituencyId,
			startDate : modifyDate(startDate),
			endDate : modifyDate(endDate)          
		}
		$.ajax({
			type:'GET',
			url: 'getCadreRegStatusTypeAction.action',    
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
			url: 'getTotalRegCdrVendorAndTabUserWiseAction.action',    
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#loggedInFieldUsersId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			if(result != null && result.length > 0){  
				buildTotalRegCdrVendorAndTabUserWise(result);
			}else{
				$("#loggedInFieldUsersId").html('No Data Available...');
			}
		});  
	}  
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
	function buildTotalRegCdrVendorAndTabUserWise(result){
		var str = '';
		str+='<h4 class="panel-title text-muted">Logged In FieldUsers</h4>';
			str+='<div class="table-responsive">';
				str+='<table class="table b_1 m_top10">';
					str+='<thead>';
						str+='<th>SURVEY USER ID</th>';
						str+='<th>FIELD STAFF NAME</th>';
						str+='<th>CONTACT NO</th>';
						str+='<th>COMPLETED REGISTRATIONS</th>';
						str+='<th>VERIFIED - PASSED</th>';
						str+='<th>VERIFIED - JUNK/REJECTED</th>';
						str+='<th>PENDING</th>';
						str+='<th></th>';
					str+='</thead>';
					for(var i in result){
						str+='<tr>';
						if(result[i].status == "active"){ 
							str+='<td class="issueCmpltd">'+result[i].name+'</td>';
						}else{
							str+='<td class="issuePending">'+result[i].name+'</td>';
						}
							str+='<td>'+result[i].tabUserName+'</td>';
							str+='<td>'+result[i].mobileNo+'</td>';
							str+='<td>'+result[i].totalCount+'</td>';
							str+='<td>'+result[i].approvedCount+'</td>';
							str+='<td>'+result[i].rejectedCount+'</td>';
							str+='<td>'+result[i].pendingCount+'</td>';  
							str+='<td><button class="btn btn-success issuesBtn">Verify Pending Records</button></td>';
						str+='</tr>';
					}
				str+='</table>';
			str+='</div>';
			$("#loggedInFieldUsersId").html(str);
	}
	