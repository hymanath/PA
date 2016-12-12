onloads();
function onloads(){
	getDistrictList(0);
}
function getDistrictList(vendorId){
	getConstituencyList();
	$("#districtListId").empty();
	var vendorId = vendorId;
	var jsObj = { 
	vendorId : vendorId
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictListAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#districtListId").append('<option value="0">ALL</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#districtListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		var select1 = new Dropkick("#districtListId");
		select1.refresh();	
	});
}
function getConstituencyList(){
	$('#constencyListId').empty();
	var vendorId = $("#vendorId").val();
	var districtId = 0;
	var jsObj = { 
	vendorId : vendorId ,
	districtId : districtId
	}
	$.ajax({
		type : 'GET',
		url : 'getConstencyListAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#constencyListId").append('<option value="0">ALL</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#constencyListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		var select1 = new Dropkick("#constencyListId");
		select1.refresh();			
	});
}

function getConstituenciesForDistrict(){
	$('#constencyListId').empty();
	var vendorId = $("#vendorId").val();
	var districtId = $("#districtListId").val();
	var jsObj = { 
	vendorId : vendorId ,
	districtId : districtId
	}
	$.ajax({
		type : 'GET',
		url : 'getConstencyListAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#constencyListId").append('<option value="0">ALL</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#constencyListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		var select1 = new Dropkick("#constencyListId");
		select1.refresh();			
	});
}