//SWADHIN
getAcceptanceStatus();    
getBloodBagType();
getBloodBagQuantity();
var acceptanceStr = '';
var bloodBagStr = '';
var bloodBagQuantityStr = '';
function getAcceptanceStatus(){
	var jsObj = {};
	$.ajax({
		type : 'GET',
		url : 'getAcceptanceStatusAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
		if(result != null){
			for(var i in result){
				acceptanceStr+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			buildStatusList(result);
		}
	});
}
function getBloodBagType(){
	var jsObj = {};
	$.ajax({
		type : 'GET',
		url : 'getBloodBagTypeAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
		if(result != null){
			bloodBagStr+='<option value="0">Select BloodBagType</option>';
			for(var i in result){
				bloodBagStr+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
	});
}
function getBloodBagQuantity(){   
	var jsObj = {};
	$.ajax({
		type : 'GET',
		url : 'getBloodBagQuantityAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
		if(result != null){
			bloodBagQuantityStr+='<option value="0">Select BloodBagQuantity</option>';
			for(var i in result){
				bloodBagQuantityStr+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
	});
}

function buildStatusList(result){
	var str = '';
	$(".totalStatusCls").find("option").remove();
	str+='<option value="0">All Applications</option>';
	for(var i in result){
		str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	$(".totalStatusCls").html(str);
	var statusIdList = [];
	var campId = 1;
	var statusId = $(".totalStatusCls").val();     
	if(statusId !=null && statusId>0){
		statusIdList.push({"id":statusId});   
	}
	getBleedingCadreDetails(statusIdList,campId);
}
function getBleedingCadreDetails(statusIdList,campId){
/* 	console.log(statusIdList);
	console.log(campId);
	 */
	var jsObj = {
		statusIdList	: statusIdList,      
		campId			: campId     
	};
	$.ajax({
		type : 'GET',
		url : 'getBleedingCadreDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
		if(result != null && result.length>0){
			buildBleedingCadreDetails(result);
		}
	});
}
function buildBleedingCadreDetails(result){
	$("#BleedingCadreDetailsId").html('');
	var str = '';
	var accptStatusArr = [];
	var bloodBagTypeArr = [];
	var bloodBagQuantityArr = [];
	var quantityArr=[];
	str+='<thead style="background:#EBEBEB">';
		str+='<th>Membership No</th>';
		str+='<th>Name</th>';
		str+='<th>Mobile No</th>';
		str+='<th>Registration Status</th>';
		str+='<th>Blood Bag No</th>';
		str+='<th>Blood Bag Type	</th>';
		str+='<th>Blood Bag Quantity	</th>';
		str+='<th>Quantity</th>';
		str+='<th></th>'; 
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){   
		accptStatusArr.push(result[i].statusId);
		bloodBagTypeArr.push(result[i].bagTypeId);
		bloodBagQuantityArr.push(result[i].bloodBankQuantityId);
		quantityArr.push(result[i].quantity);
		
		str+='<tr>';
			str+='<td id="membershipNoId'+i+'">'+result[i].membershipNo+'</td>';
			str+='<td>'+result[i].name+'</td>';
			str+='<td>'+result[i].mobile+'</td>';
			str+='<td>';
			 	str+='<select id="registrationStatusId'+i+'" class="form-control registrationStatusCls">';
				str+='<option>Approved</option>';
				str+='</select>';
			str+='</td>';
			str+='<td>';
			str+='<input id="bloodBagNoId'+i+'" type="text" class="form-control" style="width:100px"/>';
			str+='</td>';
			str+='<td>';
			str+='<select id="bloodBagTypeId'+i+'" class="form-control bloodBagTypeCls">';
			str+='<option>Single Bag</option>';
			str+='</select>';
			str+='</td>';
			str+='<td>';
			str+='<select id="bloodBagQuantityId'+i+'" class="form-control bloodBagQuantityCls">';
			str+='<option>With Sagm 350ml</option>';
			str+='</select>';
			str+='</td>';
			str+='<td>';
			str+='<select id="quantityId'+i+'" class="form-control">';
				str+='<option value="0">Select Quantity</option>';
				str+='<option value="350">350ml</option>';
				str+='<option value="450">450ml</option>';
			str+='</select>';
			str+='</td>';
			str+='<td>';
			str+='<button id="submitId'+i+'" class="btn btn-success btn-sm submitCls" attr_button_submitting="SUBMITTING..." attr_button_submitted="SUBMITTED" attr_position="'+i+'">SUBMIT</button>';
			str+='</td>';
		str+='</tr>';
	}
	str+='</tbody>';
	$("#BleedingCadreDetailsId").html(str);
	
	$(".registrationStatusCls").find("option").remove();
	$(".registrationStatusCls").html(acceptanceStr);
	
	$(".bloodBagTypeCls").find("option").remove();
	$(".bloodBagTypeCls").html(bloodBagStr);
	
	$(".bloodBagQuantityCls").find("option").remove();
	$(".bloodBagQuantityCls").html(bloodBagQuantityStr);
	
	for(var i in result){ 
	$("#registrationStatusId"+i).val(accptStatusArr[i]);
	}
	
	for(var i in result){ 
	$("#bloodBagTypeId"+i).val(bloodBagTypeArr[i]);
	}
	
	for(var i in result){ 
	$("#bloodBagQuantityId"+i).val(bloodBagQuantityArr[i]);
	}
	for(var i in result){
	 $("#quantityId"+i).val(quantityArr[i]);	
	}
}

$(document).on('click','.submitCls',function(){

	var position = $(this).attr("attr_position");
	$("#submitId"+position).html($("#submitId"+position).attr("attr_button_submitting"));
	//console.log(position);
	var membershipNo = $("#membershipNoId"+position).html();
	var status = $("#registrationStatusId"+position).val();
	var bloodBagNo = $("#bloodBagNoId"+position).val();
	var bloodBagTypeId = $("#bloodBagTypeId"+position).val();
	var bloodBagQuantityId = $("#bloodBagQuantityId"+position).val();
	var quantityId = $("#quantityId"+position).val();  
	quantityId = quantityId.substr(0,3);
	//console.log(status+":"+bloodBagNo+":"+bloodBagTypeId+":"+bloodBagQuantityId+":"+quantityId+":"+membershipNo);
	var jsObj = {
		status				: status,      
		bloodBagNo			: bloodBagNo,
		bloodBagTypeId		: bloodBagTypeId,
		bloodBagQuantityId	: bloodBagQuantityId,
		quantityId			: quantityId,
		membershipNo		: membershipNo
	};  
	$.ajax({
		type : 'POST',
		url : 'saveBleedingDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
		if(result != null){
			$("#submitId"+position).html($("#submitId"+position).attr("attr_button_submitted"));
		}
	});
	
});
$(document).on("change","#totalStatusId",function(){
	var statusIdList = [];
	var campId=1;
	 var statusId=$("#totalStatusId").val();
	  statusIdList.push({"id":statusId});   
	  getBleedingCadreDetails(statusIdList,campId);
});
