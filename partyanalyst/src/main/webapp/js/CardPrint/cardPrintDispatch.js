
	function refreshDropKick(divId){
		var select1 = new Dropkick("#"+divId);
		select1.refresh();	
	}
	function districtSelectClear(){
		$("#districtListId").empty();
		$("#districtListId").append('<option value="0">Select District</option>');
		refreshDropKick("districtListId");
	}
	function constituencySelectClear(){
		$('#constencyListId').empty();
		$("#constencyListId").append('<option value="0">Select Constituency</option>');
		refreshDropKick("constencyListId");
	}
	function clearData(){
		$('#summaryDivId').html("");
		$("#dispatchingDiv").html("");
    }
	function getDistrictList(vendorId){
		
		clearData();
		districtSelectClear();
		constituencySelectClear();
		
		var vendorId = vendorId;
		if(vendorId == 0){
			return;
		}
		var jsObj = { 
			vendorId : vendorId
		}
		$('#distImgId').show();
		
		$.ajax({
			type : 'GET',
			url : 'getDistrictListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			$('#distImgId').hide();
			if(result != null && result.length > 0){
				for(var i in result){
					$("#districtListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}
			refreshDropKick("districtListId");
		});
	}

	function getConstituenciesForDistrict(){
		
		clearData();
		constituencySelectClear();
		
		var districtId = $("#districtListId").val();
		if(districtId == 0){
			return;
		}
		var vendorId = $("#vendorId").val();
		var jsObj = { 
			vendorId : vendorId ,
			districtId : districtId
		}
		$('#constImgId').show();
		
		$.ajax({
			type : 'GET',
			url : 'getConstencyListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			$('#constImgId').hide();
			if(result != null && result.length > 0){
				for(var i in result){
					$("#constencyListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}
			
			refreshDropKick("constencyListId");		
		});
	}

	function getDispatchDetails(){
		clearData();
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
			$("#dispatchDivIdImg").hide();
			
			if(result != null && result.totalCadre != null && result.totalCadre > 0){
				buildDispatchingDetails(result);
			}
			else{
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
				  str1+='<div class="col-md-3 col-xs-12 col-sm-4" style="font-size:16px;" ><b>Total Cadre :</b> '+result.totalCadre+'</div>';
				  str1+='<div class="col-md-3 col-xs-12 col-sm-4" style="font-size:16px;"><b> Printed Cadre :</b> '+result.printedCadre+'</div>';
				  str1+='<div class="col-md-3 col-xs-12 col-sm-4" style="font-size:16px;"><b>UnPrinted Cadre :</b> '+result.unPrintedCadre+'</div>';
			  str1+='</div>';
		str1+='</div>';
		$('#summaryDivId').html(str1);
		
		//table
		if(result.subList != null && result.subList.length > 0){
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
					
						for(var i in result.subList){
							str+='<tr>';
								str+='<td>';
									if(result.subList[i].isQAPassed == 'YES')
										str+='<input type="checkbox"/>';
									else
										str+='<input type="checkbox" disabled/>';
								str+='</td>';
								
								if(result.subList[i].boxNo != null && result.subList[i].boxNo > 0){
									str+='<td>'+result.subList[i].boxNo+'</td>';
								}else{
									str+='<td> - </td>';
								}
								str+='<td>'+result.subList[i].mandalName+'</td>';
								str+='<td>'+result.subList[i].panchayatName+'</td>';
								
								if(result.subList[i].noOfCards != null && result.subList[i].noOfCards > 0){
									str+='<td>'+result.subList[i].noOfCards+'</td>';
								}
								if(result.subList[i].validatedCardsCount != null && result.subList[i].validatedCardsCount > 0){
									str+='<td>'+result.subList[i].validatedCardsCount+'</td>';
									str+='<td>'+result.subList[i].isQAPassed+'</td>';
									str+='<td>'+result.subList[i].errorPerc+'%</td>';
									str+='<td>'+result.subList[i].status+'</td>';
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
				str+='<button class="btn btn-success btn-lg text-capital" id="updateBtnId">update & generate tracking agent</button>';
				$("#dispatchingDiv").html(str);
		}	
	
	}
	
