//SWADHIN
getAcceptanceStatus();    
getBloodBagType();
getBloodBagQuantity();
var acceptanceStr = '';
var bloodBagStr = '';
var bloodBagQuantityStr = '';
var acceptanceStatusJsonArr = [];
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
				acceptanceStatusJsonArr.push({"id":result[i].id,"name":result[i].name});
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
	var accptStatusValuesArr = [];
	var bloodBagTypeArr = [];
	var bloodBagQuantityArr = [];
	var quantityArr=[];
	str+='<thead style="background:#EBEBEB">';
		str+='<th>Membership No</th>';
		str+='<th>Name</th>';
		str+='<th>Mobile No</th>';
		str+='<th>Registration Status</th>';
		str+='<th>Segment No</th>';
		str+='<th>Registration No</th>';
		str+='<th>Bag Type	</th>';
		str+='<th>Bag Quantity	</th>';
		str+='<th>Quantity</th>';
		str+='<th></th>'; 
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){  
		for(var j in acceptanceStatusJsonArr){
			if(result[i].statusId==acceptanceStatusJsonArr[j].id){
				accptStatusValuesArr.push(acceptanceStatusJsonArr[j].name);
			}
		}
	}
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
			 	str+='<select id="registrationStatusId'+i+'" attr_position="'+i+'" class="form-control registrationStatusCls">';
				str+='<option>Approved</option>';
				str+='</select>';
			str+='</td>';
			str+='<td>';
			str+='<input id="bloodBagNoId'+i+'" placeholder="Segment No" type="text" value="'+result[i].bagNo+'" class="form-control" style="width:100px"/>';
			str+='</td>';
			str+='<td>';
			str+='<input id="regNoId'+i+'" value="'+result[i].registrationNo+'" placeholder="Reg No" type="text" class="form-control" style="width:100px"/>';
			str+='</td>';
			if(!(accptStatusValuesArr[i]=="Rejected")){
				str+='<td id="bloodBagTypeTdId'+i+'">';
				str+='<select id="bloodBagTypeId'+i+'" class="form-control bloodBagTypeCls">';
				//str+='<option>Single Bag</option>';
				str+='</select>';
				str+='</td>';
				str+='<td id="bloodBagQuantityTdId'+i+'">';
				str+='<select id="bloodBagQuantityId'+i+'" class="form-control bloodBagQuantityCls">';
			//	str+='<option>With Sagm 350ml</option>';
				str+='</select>';
				 str+='<input id="remarkId'+i+'" style="display:none" type="text" placeholder="Enter Remarks" class="form-control"/>';
				str+='</td>';
				str+='<td id="quantityTdId'+i+'">';
				str+='<select id="quantityId'+i+'" class="form-control">';
					str+='<option value="0">Select Quantity</option>';
					str+='<option value="350">350ml</option>';
					str+='<option value="450">450ml</option>';
				str+='</select>';
				str+='</td>';
			}else{
				str+='<td style="display:none" id="bloodBagTypeTdId'+i+'">';
				str+='<select id="bloodBagTypeId'+i+'" class="form-control bloodBagTypeCls">';
				//str+='<option>Single Bag</option>';
				str+='</select>';
				str+='</td>';
				str+='<td  colspan="3" id="bloodBagQuantityTdId'+i+'">';
				str+='<select style="display:none" id="bloodBagQuantityId'+i+'" class="form-control bloodBagQuantityCls">';
				//str+='<option>With Sagm 350ml</option>';
				str+='</select>';
                str+='<input id="remarkId'+i+'" value="'+result[i].remarks+'"  type="text" placeholder="remarks" class="form-control"/>';
                str+='</td>';
				str+='<td style="display:none" id="quantityTdId'+i+'">';
				str+='<select id="quantityId'+i+'" class="form-control">';
				str+='<option value="0">Select Quantity</option>';
				str+='<option value="350">350ml</option>';
				str+='<option value="450">450ml</option>';
				str+='</select>';
				str+='</td>';
			}
			
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
		if((accptStatusValuesArr[i]=="Rejected")){		
		}else{
			$("#bloodBagTypeId"+i).val(bloodBagTypeArr[i]);
		}
	}
	
	for(var i in result){ 
		if((accptStatusValuesArr[i]=="Rejected")){
		}else{
			$("#bloodBagQuantityId"+i).val(bloodBagQuantityArr[i]);
		}
	}
	for(var i in result){
		if((accptStatusValuesArr[i]=="Rejected")){
		}else{
			$("#quantityId"+i).val(quantityArr[i]);
		}
	}
}

$(document).on("change",".registrationStatusCls",function(){
	
	var position=$(this).attr("attr_position");
	var registrationStatus = $(this).find("option:selected").text();
	  
	 if(registrationStatus!=null && registrationStatus!=undefined && registrationStatus=="Rejected"){
		$("#bloodBagTypeTdId"+position).hide();
		$("#bloodBagQuantityId"+position).hide();
		$("#quantityTdId"+position).hide();
		$("#remarkId"+position).show();
		$("#bloodBagQuantityTdId"+position).show().attr("colspan","3");
	 }else{
		$("#bloodBagTypeTdId"+position).show();
		$("#bloodBagQuantityTdId"+position).show();
		$("#bloodBagQuantityId"+position).show();
		$("#quantityTdId"+position).show();
		$("#remarkId"+position).hide();
		$("#bloodBagQuantityTdId"+position).attr("colspan","1");
	 }
});
function validateFields(position){
	
	var registrationStatus = $("#registrationStatusId"+position+" option:selected").text();
	   $(".errorCls").html(' ');
	if(registrationStatus!=null && registrationStatus!=undefined && registrationStatus=="Rejected"){
		  var remarks=$("#remarkId"+position).val();
			 if(remarks==null || remarks==undefined || remarks.trim().length==0){
				$(".errorCls").html("Please Enter Remarks.");
				return;
			 }
	  }else{
		if(registrationStatus!=null && registrationStatus!=undefined && registrationStatus=="Pending"){
			  $(".errorCls").html("Please Change Status.");
			  return;
		   }
		   var bagTypeId=$("#bloodBagTypeId"+position).val();
		  if(bagTypeId==null || bagTypeId==undefined || bagTypeId==0){
			 $(".errorCls").html("Please Select Bag Type.");
			return;
		 }
		 var bagQuantity=$("#bloodBagQuantityId"+position).val();
		  if(bagQuantity==null || bagQuantity==undefined || bagQuantity==0){
			 $(".errorCls").html("Please Select Bag Quantity.");
			return;
		 }
		 var quantity=$("#quantityId"+position).val();
		  if(quantity==null || quantity==undefined || quantity==0){
			 $(".errorCls").html("Please Select Quantity.");
			  return;
		 }
	}
	   var  segmentNo=$("#bloodBagNoId"+position).val();
		if(segmentNo==null || segmentNo==undefined || segmentNo.trim().length==0){
				 $(".errorCls").html("Please Enter Segment No.");
				return;
		 }
		  var registrationNo=$("#regNoId"+position).val();
		  if(registrationNo==null || registrationNo==undefined || registrationNo.trim().length==0){
			 $(".errorCls").html("Please Enter Registration No.");
			 return;
		 }
		$(".errorCls").html(' ');
		 return true;
}

$(document).on('click','.submitCls',function(){
  
     var position = $(this).attr("attr_position");
     
	 var status=validateFields(position);
	
	if(status!=null && status!=undefined && status==true){
	
		$("#submitId"+position).html($("#submitId"+position).attr("attr_button_submitting"));
		//console.log(position);
		var membershipNo = $("#membershipNoId"+position).html();
		var status = $("#registrationStatusId"+position).val();
		var bloodBagNo = $("#bloodBagNoId"+position).val();
		var bloodBagTypeId = $("#bloodBagTypeId"+position).val();
		var bloodBagQuantityId = $("#bloodBagQuantityId"+position).val();
		var quantityId = $("#quantityId"+position).val();		
		if(quantityId!=null && quantityId!=undefined){
			quantityId = quantityId.substr(0,3);
		}
		var remarks=$("#remarkId"+position).val();
		var registrationNo=$("#regNoId"+position).val();
		
		if(status!=null && status==3){
		  bloodBagTypeId=0;
		  bloodBagQuantityId=0;
		  quantityId=0;
		}
		if(status!=null && status==1 || status==2){
			remarks=" ";
		}
	//console.log(status+":"+bloodBagNo+":"+bloodBagTypeId+":"+bloodBagQuantityId+":"+quantityId+":"+membershipNo);
		var jsObj = {
			status				: status,      
			bloodBagNo			: bloodBagNo,
			bloodBagTypeId		: bloodBagTypeId,
			bloodBagQuantityId	: bloodBagQuantityId,
			quantityId			: quantityId,
			membershipNo		: membershipNo,
			registrationNo      : registrationNo,
			remarks             : remarks
		};  
	
		$.ajax({
			type : 'POST',
			url : 'saveBleedingDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){ 
			if(result != null && result!=undefined && result.message=="success"){
				$("#submitId"+position).html($("#submitId"+position).attr("attr_button_submitted"));
			}
		});
	}
});
$(document).on("change","#totalStatusId",function(){
	var statusIdList = [];  
	var campId=1;
	var statusId=$("#totalStatusId").val();
	if(statusId !=null && statusId>0){
		statusIdList.push({"id":statusId});   
	}
	getBleedingCadreDetails(statusIdList,campId);
});
