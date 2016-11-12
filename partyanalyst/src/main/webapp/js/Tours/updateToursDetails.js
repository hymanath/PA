$("#toursDateRangePicker").daterangepicker({
		opens: 'left',
	     startDate: moment().startOf('month'),
         endDate: moment().endOf('month'),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	})
	 $('#toursDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	  getToursDetailsOverview();
	});
	getToursDetailsOverview(); //default call 
	function getToursDetailsOverview(){ 
	$("#overAllLeaderDivId").html(' ');
	$("#overAllLeaderDivProcessImgId").show();
	  var dates=$("#toursDateRangePicker").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
	var jsObj = { 
			 fromDate : fromDateStr,
			 toDate : toDateStr,
			}
		$.ajax({
			type : 'POST',
			url : 'getToursDetailsOverviewAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#overAllLeaderDivProcessImgId").hide();
		 if(result != null && result.length > 0){
			 buildLeadersOverviewRslt(result);
		 }else{
			$("#overAllLeaderDivId").html('NO DATA AVAILABLE.'); 
		 }
		});
	}
	function buildLeadersOverviewRslt(result){
	 var str='';
	 str+='<table class="table table-condensed tableOverview">';
		str+='<thead>';
			str+='<th></th>';
			str+='<th>Total Leaders</th>';
			str+='<th>Submited Leaders</th>';
			str+='<th>Not Submited Leaders</th>';
			str+='<th>Submited Tours</th>';
			str+='<th>Average Tours</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
			str+='<td>'+result[i].designation+'</td>';
			if(result[i].noOfLeaderCnt != null && result[i].noOfLeaderCnt > 0){
				str+='<td>'+result[i].noOfLeaderCnt+'</td>';
			}else{
			str+='<td> - </td>';
			}
			if(result[i].submitedLeaderCnt != null && result[i].submitedLeaderCnt > 0){
				str+='<td><a style="cursor:pointer;" class="getSubMitedLeadersDtlsCls" attr_desig_name="'+result[i].designation+'" attr_designation_id='+result[i].id+'>'+result[i].submitedLeaderCnt+'</a></td> ';
			}else{
			str+='<td> - </td>';
			}
			if(result[i].notSubmitedLeaserCnt != null && result[i].notSubmitedLeaserCnt > 0){
				str+='<td>'+result[i].notSubmitedLeaserCnt+'</td>';
			}else{
			str+='<td> - </td>';
			}
			if(result[i].totalSubmittedToursCnt != null && result[i].totalSubmittedToursCnt > 0){
				str+='<td>'+result[i].totalSubmittedToursCnt+'</td>';
			}else{
			str+='<td> - </td>';
			}
			if(result[i].averageTours != null && result[i].averageTours > 0){
				str+='<td>'+result[i].averageTours.toFixed(2)+'</td>';
			}else{
			str+='<td> - </td>';
			}
			str+='</tr>';
		}
		str+='</tbody>';
	  str+='</table>';
	  $("#overAllLeaderDivId").html(str);
	}




	function savingApplication(){
		var flag = true;
		var uploadHandler = { 
			upload: function(o) {
				$("#savingAjaxImg").css("display","none");
				uploadResult = o.responseText;
				showSbmitStatus(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('submitApplication',true);  
		YAHOO.util.Connect.asyncRequest('POST','savingTourDtlsApplicationAction.action',uploadHandler);
	}  
	function showSbmitStatus(uploadResult){
			console.log(uploadResult);
			 $(".clearFieldCls").val(' ');
			 $("#monthSelectBoxId").val(0);
			 $(".selectChosen").trigger("chosen:updated");
        /*  if(uploadResult != null && uploadResult.resultCode==1){
			$(".updateTourStatusCls").html("<p style='color:green'>"+uploadResult.message+"</p>");
			 $(".clearFieldCls").val(' ');
			 $("#monthSelectBoxId").val(0);
			 $(".selectChosen").trigger("chosen:updated");
		 }else{
		   $(".updateTourStatusCls").html("<p style='color:red'>"+uploadResult.message+"</p>"); 
		 }
		 setTimeout(function(){ $(".updateTourStatusCls").modal("hide"); }, 3000); */
	}
	
	getDesigationList(); //default call 
	function getDesigationList(){      
		var jsObj = { }
		$.ajax({
			type : 'POST',
			url : 'getDesigationListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$("#designationSlctBxId").empty();
				$("#designationSlctBxId").append("<option value='0'>Select Designation Level</option>");
				for(var i in result){
				 $("#designationSlctBxId").append("<option value="+result[i].id+">"+result[i].name+"</option>");	
				}
			}
			$(".selectChosen").trigger("chosen:updated");
		});
	}
	$(document).on("change","#designationSlctBxId",function(){
		var designationId = $(this).val();
		$("#selectedMemberDtslDivId").html(' ');
		$(".showDivCls").hide();
		 if(designationId != null && designationId > 0){
			 getCandidateList(designationId);
		 }
	});
	function getCandidateList(designationId){ 
		var jsObj = {     
			designationId : designationId
		}
		$.ajax({
			type : 'POST',
			url : 'getCandidateListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#memberSlctBxId").empty();
			$("#memberSlctBxId").append("  <option value='0'>Select Name</option>");
			if(result != null && result.length > 0){
				for(var i in result){
					$("#memberSlctBxId").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
				// $("#memberSlctBxId").append("<option value='-1'>Other Name</option>");  
			}
			$(".selectChosen").trigger("chosen:updated");
		});
	}  
	$(document).on("change","#memberSlctBxId",function(){
		var candidateId = $(this).val();
		 if(candidateId != null && candidateId > 0){
			//$(".otherMemberBlockCls").hide();
			//$(".showDivCls").show();
			//$(".hideProfileDivCls").show();
			$(".ownDivCls").hide();
			$(".inchageDivCls").hide();
			 getCandiateSearchDetails(candidateId);
		 }else if(candidateId == -1){
			//$(".otherMemberBlockCls").show();
			//$(".showDivCls").hide();
			//$(".hideProfileDivCls").hide();
			 //$("#searchValueInputBoxId").val(' ');
			 //$("#searchValueInputBoxId").attr("placeholder","Enter MemberShip Number");	
			getConstituenciesList();
		 }
	});
	function getCandiateSearchDetails(candidateId){ 
	$("#selectedMemberDtslDivId").html(' ');
	$(".showDivCls").hide();
	$("#profileProcessingImgId").show();
	var jsObj = { 
			 candidateId : candidateId
			}
		$.ajax({
			type : 'POST',
			url : 'getCandiateDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#profileProcessingImgId").hide();
			if(result != null){
				buildSelectedProfileRslt(result);
			}
		});
	}
	function buildSelectedProfileRslt(result){
		var str='';
			str+='<ul class="list-inline">';
			str+='<li>';
				str+='<div class="panel panel-default panelProfile">';
					str+='<div class="panel-body">';
						str+='<img src="http://mytdp.com/images/cadre_images/'+result.image+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img>';
						str+='<p>'+result.name+'</p>';
						str+='<p>'+result.memberShipNo+'</p>';
						str+='<p>'+result.mobileNo+'</p>';
					str+='</div>';
					str+='<div class="panel-footer">';
						str+='<label class="checkbox-inline">';
							str+='<input id="profileCheckboxId" type="checkbox"/>Select Profile';
						str+='</label>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
            str+='</ul>';
		$("#selectedMemberDtslDivId").html(str);
		
		var locationList = result.subList;
	     if(locationList != null && locationList.length > 0){
			 for(var i in locationList){
				 if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==1){
					 if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own DIstrict");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge DIstrict");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
					    $(".inchageDivCls").show(); 
					 }
				 }else if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==2){
					 if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own Parliament");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 $(".ownDivCls").show();    
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge Parliament");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
					   $(".inchageDivCls").show(); 
					 }
				 }else if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==3){
					  if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own Assembly");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge Assembly");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
					   $(".inchageDivCls").show(); 
					 }
				 }
			 }
		 }
	}
	function setDefaultImage(img){
		img.onerror = "";
		img.src = "images/cadre_images/human.jpg";
		return true;
	}
	$(document).on("click","#profileCheckboxId",function(){
		if($(this).is(':checked')){
			$(".showDivCls").show();
		}else{
			$(".showDivCls").hide();
		}
	});
	function getConstituenciesList(){ 
	var jsObj = { 
			 stateId : 1
			}
		$.ajax({
			type : 'POST',
			url : 'getConstituenciesListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$("#constituencySelectBoxId").empty();
				$("#constituencySelectBoxId").append("	<option value='0'>Select Constituency</option>");
				for(var i in result){
				 $("#constituencySelectBoxId").append("<option value="+result[i].id+">"+result[i].name+"</option>");	
				}
				 $("#constituencySelectBoxId").append("<option value='-1'>Other Name</option>");	
				$(".selectChosen").trigger("chosen:updated");
			}
		});
	}
	 $(document).on("change","#constituencySelectBoxId",function(){
		$("#constituencyErrorId").html(' '); 
	 });
	 $(document).on("click",".searchRadioBtnCls",function(){
		  var type = $(this).attr("attr_type");
		  $("#searchValueInputBoxId").attr("placeholder","Enter "+type);	 
	 });
	 $(document).on("click","#searchMemberBtnId",function(){
		 var locationId = $("#constituencySelectBoxId").val();
		 var searchValue = $("#searchValueInputBoxId").val();
		 var designationId = $("#designationSlctBxId").val();
		 var searchType;
		 if(locationId == 0){
		  $("#constituencyErrorId").html("Please Select Constituency.");
          return;		  
		 }
		 if(searchValue == null || searchValue.trim().length==0){
		 $("#constituencyErrorId").html("Please Enter Search Value.");
         return;		 
		 }
		 $("#constituencyErrorId").html(' ');
		  $(".searchRadioBtnCls").each(function() {
		  if($(this).is(":checked")){
			 var type = $(this).attr("attr_type");
			 if(type=="MemberShip Number"){
				searchType="MemberShipNo" ;
			 }else if(type=="Mobile Number"){
				searchType="MobileNo" ; 
			 }else {
				searchType=type ;  
			 }
		  }
	   });
	   //getSearchMembersDetailsAction(locationId,searchType,searchValue,designationId);
	 });
	function getSearchMembersDetailsAction(locationId,searchType,searchValue,designationId){ 
	var jsObj = { 
			 locationId : locationId,
			 searchType : searchType,
			 searchValue : searchValue,
			 designationId :designationId
			}
		$.ajax({
			type : 'POST',
			url : 'getSearchMembersDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
			 buildSearchProfileDetails(result);	
			}
		});
	}
	function buildSearchProfileDetails(result){
		$(".showDivCls").show();
		$(".hideProfileDivCls").show();
		var str='';
			str+='<ul class="list-inline">';
			for(var i in result){
				str+='<li>';
				str+='<div class="panel panel-default panelProfile">';
					str+='<div class="panel-body">';
						str+='<img src="http://mytdp.com/images/cadre_images/'+result[i].image+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img>';
						str+='<p>'+result[i].name+'</p>';
						str+='<p>'+result[i].memberShipNo+'</p>';
						str+='<p>'+result[i].mobileNo+'</p>';
					str+='</div>';
					str+='<div class="panel-footer">';
						str+='<label class="checkbox-inline">';
							str+='<input attr_location_scope_str="'+result[i].subList+'" id="profileCheckboxId" type="checkbox"/>Select Profile';
						str+='</label>';
					str+='</div>';
				str+='</div>';
			str+='</li>';	
			}
	        str+='</ul>';
		$("#selectedMemberDtslDivId").html(str);
	}
	$(document).on("click",".getSubMitedLeadersDtlsCls",function(){
		var desigName = $(this).attr("attr_desig_name");
		$("#myModalLabel").html(desigName+" OVERVIEW");
		$("#desigDtlsId").html("");
		$("#memDtlsId").html(""); 
		$("#desigDtlsProcessImgId").show();  
		$("#memDtlsProcessImgId").show();   		
		$("#myModal").modal("show");  
		
		var desigId = $(this).attr("attr_designation_id");
		var dates=$("#toursDateRangePicker").val();        
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		getDesignationDtls(fromDateStr,toDateStr,desigId);
		getMemDtls(fromDateStr,toDateStr,desigId);
		
	});
	//getDesignationDtls();
	function getDesignationDtls(fromDateStr,toDateStr,desigId){ 
		var jsObj = { 
			 desigId : desigId,
			 startDateStr : fromDateStr,
			 endDateStr : toDateStr  
			}
		$.ajax({
			type : 'POST',
			url : 'getDesignationDtlsAction.action',  
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#desigDtlsProcessImgId").hide();
			if(result != null){
				buildDesignationDtls(result);               
			}
		});
	}
	function buildDesignationDtls(result){
		var str = '';
		str+='<table class="table">';
			str+='<tr>';
				str+='<td>';
					str+='<h4 class="text-capitalize text-muted">Total Leaders</h4>';
					str+='<h3>'+result.candidateCount+'</h3>';
				str+='</td>';
				str+='<td>';
					str+='<h4 class="text-capitalize text-muted">Submited</h4>';
					var percent = (result.selectedCandCount * (100/result.candidateCount)).toFixed(2);
					str+='<h3>'+result.selectedCandCount+'<small class="text-success">'+percent+'%</small></h3>';
				str+='</td>';
				str+='<td>';
					str+='<h4 class="text-capitalize text-muted">Not Submited</h4>';
					var notSubmit = result.candidateCount-result.selectedCandCount;
					var percent = (notSubmit * (100/result.candidateCount)).toFixed(2);
					str+='<h3>'+(result.candidateCount-result.selectedCandCount)+'<small class="text-success">'+percent+'%</small></h3>';
				str+='</td>';
				str+='<td>';
					str+='<h4 class="text-capitalize text-muted">Submited Tours</h4>';
					str+='<h3>'+result.totalTour+'</h3>';
				str+='</td>';
				str+='<td>';
					str+='<h4 class="text-capitalize text-muted">Average Tours</h4>';
					str+='<h3>'+(result.totalTour/result.selectedCandCount).toFixed(2)+'</h3>';    
				str+='</td>';
			str+='</tr>';
		str+='</table>';
		$("#desigDtlsId").html(str);
	}
	//getMemDtls();
	function getMemDtls(fromDateStr,toDateStr,desigId){   
		var jsObj = { 
			 desigId : desigId,
			 startDateStr : fromDateStr,
			 endDateStr : toDateStr  
			}
		$.ajax({
			type : 'POST',
			url : 'getMemDtlsAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#memDtlsProcessImgId").hide();  
			if(result != null){
				buildMemDtls(result);               
			}
		});
	}
	function buildMemDtls(result){
		var str = '';
		str+='<table class="table table-condensed tableModal" id="memTableId">';
			str+='<thead class="text-capital">';
				str+='<th>general secretary name</th>';
				str+='<th>Year</th>';
				str+='<th>Month</th>';
				str+='<th>total tours submited</th>';
				str+='<th>comments</th>';
				str+='<th>attachments</th>';
			str+='</thead>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td>'+result[i].year+'</td>';
					str+='<td>'+result[i].month+'</td>';
					str+='<td>'+result[i].totalTour+'</td>'; 
					if(result[i].comment.trim().length > 0){
						str+='<td>'+result[i].comment+'</td>';  
					}else{
						str+='<td>-</td>';  
					}
					
					str+='<td>';
						str+='<div class="row">';
						str+='<div class="col-md-3">';
						if(result[i].type=="pdf"){
							str+='<img src="images/pdf.jpg" class="img-responsive" alt="" style="width:80px;"/>';
						}else if(result[i].type=="xls"){  
							str+='<img src="images/excel.jpg" class="img-responsive" alt="" style="width:80px;"/>';       
						}else if(result[i].type=="doc"){
							str+='<img src="images/word.jpg" class="img-responsive" alt="" style="width:80px;"/>';         
						}else{  
							str+='<img src="" class="img-responsive" alt=""/>';           
						}
						str+='</div>';  
						str+='<div class="col-md-3">';
						str+='<button class="btn btn-success editBtn updateMemCls" attr_name="'+result[i].name+'" attr_candidate_dtls_id="'+result[i].id+'">EDIT</button>';    
						str+='</div>';   
						str+='</div>';   
					str+='</td>';  
				str+='</tr>';
			}
		str+='</table>';
		$("#memDtlsId").html(str);    
		$("#memTableId").dataTable();        
	}
	$(document).on("click",".updateMemCls",function(){
		var name = $(this).attr("attr_name");
		
		$("#myModalLabelUpdateId").html(name+" - TOURS");       
		$("#memDtlsUpdateId").html("");   
		$("#myModalUpdateId").modal("show"); 
		$("#memDtlsUpdateProcessImgId").show();  
		var candidateDtlsId = $(this).attr("attr_candidate_dtls_id");
		var jsObj = { 
			 candidateDtlsId : candidateDtlsId
			}
		$.ajax({
			type : 'POST',
			url : 'getUniqueMemDtlsAction.action',    
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#memDtlsUpdateProcessImgId").hide();  
			if(result != null){
				buildUniqueMemDtls(result);                 
			}
		});
	});
	function buildUniqueMemDtls(result){
		var monthArr = ["January","February","March","May","June","July","August","September","October","November","December"]
		var str='';
		str+='<form name="updateApplication" method="post">';  
		str+='<div class="row m_top20 showDivCls">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				//str+='<h4 class="panel-title text-capital">update your details</h4>';
				str+='<div class="block">';
					str+='<div class="row">';  
						str+='<div class="col-md-3 col-xs-12 col-sm-6">';
							str+='<label>Select Month</label>';
							str+='<select id="updateMonthId" class="selectChosen" name="toursInputVO.month">';
								str+='<option value="0">Select Month</option>';
								str+='<option value="January">January</option>';
								str+='<option value="February">February</option>';
								str+='<option value="March">March</option>';
								str+='<option value="April">April</option>';
								str+='<option value="May">May</option>';
								str+='<option value="June">June</option>';
								str+='<option value="July">July</option>';
								str+='<option value="August">August</option>';
								str+='<option value="September">September</option>';
								str+='<option value="October">October</option>';
								str+='<option value="November">November</option>';
								str+='<option value="December">December</option>';
							str+='</select>';
						str+='</div>';
						str+='<div class="col-md-3 col-xs-12 col-sm-6">';
							str+='<label>Insert Year</label>';
							str+='<input type="text" value="'+result.year+'" length="4"  placeholder="Type Year Here" id="updateYearId" class="form-control clearFieldCls" name="toursInputVO.year"></input>';
						str+='</div>';
						str+='<div class="col-md-3 col-xs-12 col-sm-6 ownDivCls">';
							str+='<label Id="ownLabelId">Tours</label>';
							var total = result.ownToursCnt + result.inchargerToursCnt;
							str+='<input type="text" value="'+total+'" length="2" placeholder="Type No of Tour Here" id="updateOwnLocationId" class="form-control clearFieldCls" name="toursInputVO.ownTours"></input>';
							str+='<input type="hidden" id="" name="toursInputVO.ownLocationScopeId">';
							str+='<input type="hidden" id="" name="toursInputVO.ownLocationId">';
						str+='</div>'; 
					str+='</div>';
					str+='<div class="row m_top10">';  
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
							str+='<label>Add Comment</label>';
							str+='<textarea class="form-control clearFieldCls" name="toursInputVO.remarks">'+result.comment+'</textarea>';    
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top20">';    
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<label>UPLOAD ATTACHMENTS:</label>';    
			str+='</div>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div style="padding:10px;border:1px solid #ddd">';  
					str+='<div class="row">';
						str+='<div class="col-md-6 col-xs-12 col-sm-6">';
							str+='<div class="media">';
								str+='<div class="media-left">';
									if(result.type=="pdf"){
										str+='<span><img src="images/pdf.jpg" class="media-object" alt="" style="width:30px;"/>';
									}else if(result.type=="xls"){  
										str+='<span><img src="images/excel.jpg" class="media-object" alt="" style="width:30px;"/>';       
									}else if(result.type=="doc"){
										str+='<span><img src="images/word.jpg" class="media-object" alt="" style="width:30px;"/>';         
									}else{  
										str+='<span><img src="" class="media-object" alt=""/>';           
									}  
									str+='</span>';
								str+='</div>';
								str+='<div class="media-body">';      
									str+=''+result.filePath+'';
								str+='</div>';
							str+='</div>';
							
						str+='</div>';
						str+='<div class="col-md-6 col-xs-12 col-sm-6">';
							str+='<button type="button" style="margin-left:5px;" class="btn btn-default btn-sm pull-right btn-success">VIEW</button>';
							str+='<button type="button" class="btn btn-default btn-sm pull-right btn-danger">DELETE</button>';  
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';      
		str+='<div class="row showDivCls" id="">'; 
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<p class="m_0 text-success font_16 font_weight">UPLOAD SCAN COPY</p>';
				str+='<input type="file" id="" multiple="multiple"  name="fileImage" class="m_top20"/>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row showDivCls">';
			str+='<div class="col-md-4 col-md-offset-4">';
			   str+='<span class="updateTourStatusCls"></span>';  
				str+='<button class="btn btn-success btn-block" onclick="updatingApplication();" type="button">UPDATE APPLICATION</button>';
			str+='</div>';
			  str+='<div class="col-md-12 col-sm-12 col-xs-12" id=""></div>';  
		str+='</div>';
		str+='</form>';
		$("#memDtlsUpdateId").html(str);      
		$("#updateMonthId").val(result.month);
		$('#updateMonthId').chosen({width:'100%'});  
		$("#updateMonthId").trigger("chosen:updated");    	
	}
	function updatingApplication(){    
		var flag = true;
		var uploadHandler = { 
			upload: function(o) {
				$("#savingAjaxImg").css("display","none");
				uploadResult = o.responseText;
				showSbmitStatus(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('updateApplication',true);      
		YAHOO.util.Connect.asyncRequest('POST','updatingTourDtlsApplicationAction.action',uploadHandler);
	} 
	$(document).on('click','.closeButtonCls',function(){  
		setTimeout(function(){
			$('body').addClass("modal-open");  
		}, 500);    
	});
	