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
		getReasons();
	});
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
				buildTotalRegCdrVendorAndTabUserWise(result,modifyDate(startDate),modifyDate(endDate));
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
	function buildTotalRegCdrVendorAndTabUserWise(result,startDate,endDate){
		var str = '';
		str+='<h4 class="panel-title text-muted">Logged In FieldUsers</h4>';
			str+='<div class="table-responsive">';
				str+='<table class="table b_1 m_top10" id="tabWiseDtlsId">';
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
							str+='<td><button class="btn btn-success issuesBtn" attr_user_mobile="'+result[i].mobileNo+'" attr_user_name="'+result[i].tabUserName+'" attr_survey_user_id="'+result[i].id+'" attr_tab_user_id="'+result[i].tabUserId+'" attr_web_user_id="'+0+'" attr_start_date="'+startDate+'" attr_end_date="'+endDate+'">Verify Pending Records</button></td>';
						str+='</tr>';  
					}
				str+='</table>';
			str+='</div>';
			$("#loggedInFieldUsersId").html(str);
			$("#tabWiseDtlsId").dataTable();    
	}
	$(document).on('click','.issuesBtn',function(){
		$("#cadreValidateId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');  
		$("#issuesModal").modal('show');
		var surveyUserId = $(this).attr("attr_survey_user_id");
		var tabUserId = $(this).attr("attr_tab_user_id");
		var webUserId = $(this).attr("attr_web_user_id");
		var startDate = $(this).attr("attr_start_date");
		var endDate = $(this).attr("attr_end_date");
		var userName = $(this).attr("attr_user_name");
		var userMobile = $(this).attr("attr_user_mobile");
		var jsObj=
		{				
			surveyUserId : surveyUserId,
			tabUserId : tabUserId,
			webUserId : webUserId,
			startDate : startDate,
			endDate : endDate          
		}
		$.ajax({
			type:'GET',
			url: 'getVerifiedDtlsAction.action',           
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#cadreValidateId").html('');
			if(result != null){  
				buildVerifiedDtlsCount(result,tabUserId,userName,userMobile);  
			}else{
				$("#cadreValidateId").html('No Data Available...');
			}
		});
		
	});
	function buildVerifiedDtlsCount(result,tabUserId,userName,userMobile){
		var str = '';
		var str2 = '';
		str2+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
        str2+='<p>User ID - '+tabUserId+'</p>';
        str2+='<p><i>'+userName+' - '+userMobile+'</i></p>';
		$("#tabUserId").html(str2);
		str+='<!-- Nav tabs -->';
		str+='<ul class="nav nav-tabs" role="tablist">';
			str+='<li role="presentation" class="active text-capital"><a href="#self" aria-controls="self" role="tab" data-toggle="tab">voter with self photo</a></li>';
            str+='<li role="presentation" class="text-capital"><a href="#relative" aria-controls="relative" role="tab" data-toggle="tab">registered with relative voter id</a></li>';
         str+='</ul>';
		 
		str+='<!-- Tab panes -->';
		str+='<div class="tab-content">';
		str+='<div role="tabpanel" class="tab-pane active" id="self">';  
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
					for(var i in result[0]){
						if(result[0][i].status == "Approved"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
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
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
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
									str+='<input type="text" value="'+result[0][i].wish+'" class="form-control"></input>';    
										//str+='<option></option>';  
									//str+='</select>';
								str+='</td>';
							str+='</tr>';
						}
						if(result[0][i].status == "noStatus"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[0][i].name+'</td>';
								str+='<td>'+result[0][i].mobileNo+'</td>';
								str+='<td>'+result[0][i].gender+'</td>';
								str+='<td><input attr_cadre_id="'+result[0][i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'" class="localSelectOwnCls" type="checkbox"/></td>';  
							str+='</tr>'; 
					
					
							str+='<tr>';
								str+='<td>';  
									str+='<button class="btn btn-success singleApproveCls"  attr_cadre_id="'+result[0][i].cadreId+'">Approve</button>';
									str+='<button class="btn btn-danger singleRejectCls"  attr_cadre_id="'+result[0][i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'">Reject</button>';
								str+='</td>';
								str+='<td colspan="3">';
									str+='<select class="select" id="ownHideSelectBoxId'+i+'" style="display:none;">';
										str+='<option value="0">Andhra Pradesh</option>';                
									str+='</select>';  
								str+='</td>';    
							str+='</tr>';  
							
						}
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
			str+='<div role="tabpanel" class="tab-pane" id="relative">';
				str+='<table class="table">';
					str+='<thead class="text-capital">';
						str+='<th>captured photo</th>';
						str+='<th>Voter photo</th>';
						str+='<th>name</th>';
						str+='<th>mobile number</th>';
						str+='<th>gender</th>';
						str+='<th><input id="globalSelectfamilyId" type="checkbox"/></th>';  
					str+='</thead>';
					str+='<tbody class="b_1">';
						for(var i in result[1]){
						if(result[1][i].status == "Approved"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[1][i].name+'</td>';
								str+='<td>'+result[1][i].mobileNo+'</td>';
								str+='<td>'+result[1][i].gender+'</td>';
								str+='<td>';
									//str+='<input type="checkbox"/>';
								str+='</td>';
							str+='</tr>';  

					
							str+='<tr>';
								str+='<td><img src="Assests/img/verified.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str+='<td colspan="3">';
									//str+='<select>';
										//str+='<option></option>';
									//str+='</select>';
								str+='</td>';
							str+='</tr>';
						}
						if(result[1][i].status == "Rejected"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[1][i].name+'</td>';
								str+='<td>'+result[1][i].mobileNo+'</td>';
								str+='<td>'+result[1][i].gender+'</td>';
								str+='<td>';
									//str+='<input type="checkbox"/>';
								str+='</td>';
							str+='</tr>';  

					
							str+='<tr>';
								str+='<td><img src="Assests/img/verified.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str+='<td colspan="3">';
									str+='<input type="text" value="'+result[1][i].wish+'"></input>';
										//str+='<option></option>';
									//str+='</select>';
								str+='</td>';
							str+='</tr>';
						}
						if(result[1][i].status == "noStatus"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';  
								str+='<td>'+result[1][i].name+'</td>';
								str+='<td>'+result[1][i].mobileNo+'</td>';
								str+='<td>'+result[1][i].gender+'</td>';
								str+='<td><input class="localSelectFamilyCls" type="checkbox"/></td>';
							str+='</tr>'; 
							str+='<tr>';
								str+='<td>';
									str+='<button class="btn btn-success">Approve</button>';
									str+='<button class="btn btn-danger">Reject</button>';
								str+='</td>';
							str+='</tr>';
						}
					}  
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
		$("#cadreValidateId").html(str);
		
		//$("#ownHideSelectBoxId8").trigger("chosen:updated");  
		for(var i in result[0]){
			if(result[0][i].status == "noStatus"){
				 $("#ownHideSelectBoxId"+i).html(globalSrt);  
			}
		}
		for(var i in result[0]){
			if(result[0][i].status == "noStatus"){
				refreshSelectBox("ownHideSelectBoxId"+i);  
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
			}else{
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).hide(); 
			}  
		});
	}); 
	//single approve
	$(document).on('click','.singleApproveCls',function(){
		var cadreId = $(this).attr("attr_cadre_id");
		console.log(cadreId);
	});
	//single rejectedCount
	$(document).on('click','.singleRejectCls',function(){
		var cadreId = $(this).attr("attr_cadre_id");
		var selectReasonId = $(this).attr("attr_reason_id");
		var reasonId = $("#"+selectReasonId).val();
		console.log(cadreId);
		console.log(reasonId);      
	});
	//by selecting single check box show and hide the chexk box. for own tab
	$(document).on('click','.localSelectOwnCls',function(){
		if($(this).is(':checked')){
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).show();
		}else{
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).hide(); 
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
	
	//bulk reject
	$(document).on('click','#bulkRejectId',function(){  
		var cadreId = '';
		var selectReasonId = '';
		var reasonId = '';
		
		var rejectList = [];
		$('.localSelectOwnCls').each(function(){
			if($(this).is(':checked')){
				cadreId = $(this).attr("attr_cadre_id");
				selectReasonId = $(this).attr("attr_reason_id");
				reasonId = $("#"+selectReasonId).val();
				rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId, "userId" : 3256 });     
			}
		});
		var singleReject = {"data" : rejectList};        
		$.ajax({
			type:'GET',      
			url: 'updateRejectListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			$("#totalCountId").html('');
			if(result != null){  
				console.log(true);
			}else{
			}
		});
	});
	//bulk approve
	$(document).on('click','#bulkApproveId',function(){  
		var cadreId = '';
		var rejectList = [];
		$('.localSelectOwnCls').each(function(){
			if($(this).is(':checked')){
				cadreId = $(this).attr("attr_cadre_id");
				
				rejectList.push({"cadreId" : cadreId, "userId" : 3256 });     
			}
		});
		var singleReject = {"data" : rejectList};        
		$.ajax({
			type:'GET',      
			url: 'updateApproveListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			$("#totalCountId").html('');
			if(result != null){  
				console.log(true);         
			}else{
			}
		});
	});
	