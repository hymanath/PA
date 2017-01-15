onloads();
function onloads(){
	getDistrictList(0);
}
function getDistrictList(vendorId){
	//getConstituencyList();
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
		$("#districtListId").append('<option value="0">Select District</option>');
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
		$("#constencyListId").append('<option value="0">Select Constituency</option>');
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
		$("#constencyListId").append('<option value="0">Select Constituency</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#constencyListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		var select1 = new Dropkick("#constencyListId");
		select1.refresh();			
	});
}

function getDispatchDetails(){
	$("#dispatchingDiv").html("");
	$("#errorDivId").html("");
	var vendorId = $("#vendorId").val();
	var districtId = $("#districtListId").val();
	var constituencyId = $("#constencyListId").val();
	
	if(vendorId == 0){
		$("#errorDivId").html("select vendor");
		return;
	}
	if(districtId == 0){
		$("#errorDivId").html("select district");
		return;
	}
	if(constituencyId == 0){
		$("#errorDivId").html("select constituency");
		return;
	}
	$("#boxWiseDivId").show();
	$("#dispatchDivIdImg").show();
	
	var jsObj = { 
		vendorId : vendorId,
		districtId : districtId,
		constituencyId : constituencyId
	}
	$.ajax({
		type : 'GET',
		url : 'getPrintingDispatchDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildDispatchingDetails(result);
		}
		else{
			$("#dispatchDivIdImg").hide();
			$("#updateBtnId").hide();
			$("#dispatchingDiv").html('<h4 style="color:red">NO DATA AVAILABLE...</h4>');
		}
	});
}

	function buildDispatchingDetails(result){
		//summary
		var str1 = '';
		str1+='<div class="panel panel-default panelWhite">';
				str1+='<div class="panel-heading">';
					str1+='<h3 class="panel-title text-capital"> PRINTED CARDS SUMMARY FOR THE CONSTITUENCY</h3>';
				str1+='</div>';
				str1+='<div class="panel-body">';
				  str1+='<div class="col-md-3 col-xs-12 col-sm-4" style="font-size:16px;" ><b>Total Cadre :</b> '+result[0].totalCadre+'</div>';
				  str1+='<div class="col-md-3 col-xs-12 col-sm-4" style="font-size:16px;"><b> Printed Cadre :</b> '+result[0].printedCadre+'</div>';
				  str1+='<div class="col-md-3 col-xs-12 col-sm-4" style="font-size:16px;"><b>UnPrinted Cadre :</b> '+result[0].unPrintedCadre+'</div>';
			  str1+='</div>';
		str1+='</div>';
		$('#summaryDivId').html(str1);
		
		//table
		var str=''
		str+='<table class="table tablePrinting text-capital">';
			str+='<thead>';
				str+='<th>Select</th>';
				str+='<th>BOX ID</th>';
				str+='<th>mandal/town/division</th>';
				str+='<th>village/ward</th>';
				str+='<th>no of cards</th>';
				str+='<th>validated cards</th>';
				str+='<th>qa passed</th>';
				str+='<th>error %</th>';
				str+='<th>status</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>';
						if(result[i].isQAPassed == 'YES')
							str+='<input type="checkbox"/>';
						else
							str+='<input type="checkbox" disabled/>';
					str+='</td>';
					
					if(result[i].boxNo != null && result[i].boxNo > 0){
						str+='<td>'+result[i].boxNo+'</td>';
					}else{
						str+='<td> - </td>';
					}
					str+='<td>'+result[i].mandalName+'</td>';
					str+='<td>'+result[i].panchayatName+'</td>';
					
					if(result[i].noOfCards != null && result[i].noOfCards > 0){
						str+='<td>'+result[i].noOfCards+'</td>';
					}
					if(result[i].validatedCardsCount != null && result[i].validatedCardsCount > 0){
						str+='<td>'+result[i].validatedCardsCount+'</td>';
						str+='<td>'+result[i].isQAPassed+'</td>';
						str+='<td>'+result[i].errorPerc+'%</td>';
						str+='<td>'+result[i].status+'</td>';
					}else{
						str+='<td> - </td>';
						str+='<td> - </td>';
						str+='<td> - </td>';
						str+='<td> - </td>';
					}
					
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		
		$("#updateBtnId").show();
		$("#dispatchDivIdImg").hide();
		$("#dispatchingDiv").html(str);
	}