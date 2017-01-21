
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
		$('#boxesSummaryId').html("");
		$('#qaStatusUpdationDivId').hide();
    }
	
	function getDistrictList(vendorId){
		
		clearData();
		districtSelectClear();
		constituencySelectClear();
		
		/* $.ajax({
			type:'GET',
			url: "http://localhost:8080/PartyAnalyst/WebService/cadre/getDistrictList/"+vendorId+"",
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(result){
			$("#districtListId").append('<option value="0">Select District</option>');
			if(result != null && result.length > 0){
				for(var i in result){
					$("#districtListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}
			var select1 = new Dropkick("#districtListId");
			select1.refresh();	
		});	 */
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
		
		
		/* $.ajax({
			type : 'GET',
			url: "http://localhost:8080/PartyAnalyst/WebService/cadre/getConstencyList/"+vendorId+"/"+districtId+"",
			contentType: "application/json; charset=utf-8",
			dataType : 'json'
		}).done(function(result){
			$("#constencyListId").append('<option value="0">Select Constituency</option>');
			if(result != null && result.length > 0){
				for(var i in result){
					$("#constencyListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}
			var select1 = new Dropkick("#constencyListId");
			select1.refresh();			
		}); */
		$('#constImgId').show();
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
		
		
		/* $.ajax({
			type : 'GET',
			url: "http://localhost:8080/PartyAnalyst/WebService/cadre/getPrintingDispatchDetails/"+vendorId+"/"+districtId+"/"+constituencyId+"",
			contentType: "application/json; charset=utf-8",
			dataType : 'json'
		}).done(function(result){
			if(result != null && result.length > 0){
				buildDispatchingDetails(result);
			}
			else{
				$("#dispatchDivIdImg").hide();
				$("#updateBtnId").hide();
				$("#dispatchingDiv").html('<h4 style="color:red">NO DATA AVAILABLE...</h4>');
			}
		}); */
		
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
		var constituencyName = $("#constencyListId option:selected").text();
		var vendorName = $("#vendorId option:selected").text();
		//summary
		var str1 = '';
		str1+='<div class="panel panel-default panelWhite">';
		
				str1+='<div class="panel-heading">';
					str1+='<div class="row">';
						   str1+='<div class="col-md-8 col-xs-12 col-sm-6">';
							str1+='<p  class="panel-title text-capital border-line"> PRINTED CARDS SUMMARY FOR THE CONSTITUENCY <b>'+constituencyName+'</b> for <b>'+vendorName+'</b></p>';
						  str1+='</div>';
						  str1+='<div class="col-md-4 col-xs-12 col-sm-6">';
							str1+='<p class="panel-title text-capital"> PRESENT STATUS : <b id="presentStatusId"> '+result.constPrintStatus+' </b></p>';
						  str1+='</div>';
					str1+='</div>';
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
			var str='';
			str+='<div class="panel panel-default">';
			
				  str+='<div class="panel-heading">';
					str+='<h3 class="panel-title text-capital">Box Wise Details for the constituency <b>'+constituencyName+'</b> for <b>'+vendorName+'</b> Vendor</h3>';
				  str+='</div>';
				  
			  str+='<div class="panel-body">';
					 str+='<table class="table tablePrinting text-capital" id="boxTableId" >';
						str+='<thead>';
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
			  str+='</div>';
			  
			str+='</div>';
		   $("#dispatchingDiv").html(str);
		   //applying datatable
		    $('#boxTableId').DataTable({
				 "lengthMenu": [[25, 50, 75, -1], [25, 50, 75, "All"]]
			});
			$('#boxTableId').removeClass('dataTable');
			
			//boxesSummaryBuilding.
			
			var boxesSummary='';
			boxesSummary+='<div class="panel panel-default">';
			
			  boxesSummary+='<div class="panel-heading">';
				boxesSummary+='<h3 class="panel-title text-capital"><b>Boxes Summary</b></h3>';
			  boxesSummary+='</div>';
			  
			  boxesSummary+='<div class="panel-body">';
				 boxesSummary+='<table class="table tablePrinting text-capital table-bordered" id="boxesSummaryTableId" >';
						boxesSummary+='<thead>';
						  boxesSummary+='<tr>';
							boxesSummary+='<th rowspan="2">Total Boxes</th>';
							if(result.totalBoxesCount != null & result.totalBoxesCount > 0){
								boxesSummary+='<th colspan="2" >Verified Boxes <span> ('+result.verifiedBoxesCount+')</span></th>';
							}else{
								boxesSummary+='<th colspan="2" >Verified Boxes <span> </span></th>';
							}
							boxesSummary+='<th rowspan="2">Not Verified Boxes</th>';
						    boxesSummary+='</tr>';
						  
						   boxesSummary+='<tr>';
							boxesSummary+='<th>Accepted Boxes</th>';
							boxesSummary+='<th>Rejected Boxes</th>';
						  boxesSummary+='</tr>';
						  
						boxesSummary+='</thead>';
						
						boxesSummary+='<tbody>';
						if(result.totalBoxesCount != null & result.totalBoxesCount > 0){
								boxesSummary+='<tr>';
									boxesSummary+='<td> '+result.totalBoxesCount+' </td>';
									boxesSummary+='<td id="acceptedBoxesId" attr_accepted_boxes_count ='+result.acceptedBoxesCount+' > '+result.acceptedBoxesCount+' </td>';
									boxesSummary+='<td id="rejectedBoxesId" attr_rejected_boxes_count ='+result.rejectedBoxesCount+'> '+result.rejectedBoxesCount+' </td>';
									boxesSummary+='<td> '+result.notVerifiedBoxesCount+' </td>';
								boxesSummary+='</tr>';
						}else{
							boxesSummary+='<tr>';
							  boxesSummary+='<td style="color:red;font-size:15px"> NO DATA AVAIALBLE.. </td>';
							boxesSummary+='</tr>';
						}
						boxesSummary+='</tbody>'; 
						
					boxesSummary+='</table>';
			  boxesSummary+='</div>';
			  
			boxesSummary+='</div>';
			$('#boxesSummaryId').html(boxesSummary);
			
			if(result.verifiedBoxesCount != null && result.verifiedBoxesCount > 0){
				$('#qaStatusUpdationDivId').show();
			}
		}
	}
	
	function qaStatusUpdation(){
		$("#statusErrorId").html("");
		$("#successMsgId").html("");
		
		var statusId = $("#statusId").val();
		var remarks = $('#remarksId').val().trim();
		var vendorId = $("#vendorId").val();
		var constituencyId = $("#constencyListId").val();
		
		if(statusId == 0){
			$("#statusErrorId").html(" Please Select Status");
			return;
		}
		 
		 if(remarks.length == 0){
		  $("#statusErrorId").html('Please Enter Remarks');
		  return ;
	    }
		
		if(statusId == 6){//QA Passed
			var rejectedBoxesCount = $('#rejectedBoxesId').attr("attr_rejected_boxes_count");
			if(rejectedBoxesCount != 0){
				$("#statusErrorId").html(rejectedBoxesCount +" Rejected Boxes Exist..So Updation To Passed Is Not Possible.");
			    return;
			}
		}
		if(statusId == 7){//QA failed
			var rejectedBoxesCount = $('#rejectedBoxesId').attr("attr_rejected_boxes_count");
			if(rejectedBoxesCount == 0){
				$("#statusErrorId").html("No Rejected Boxes .So Updation Failed Is Not Possible.");
			    return;
			}
		}
		var jsObj = {
			printVendorId : vendorId ,
			constituencyId:constituencyId,
			printStatusId : statusId,
			remarks : remarks
		}
		$("#successMsgId").html('<span><img class="img" src="images/search.gif"/></span>');
		$.ajax({
	     type:'POST',
         url:'saveConstituencyPrintStatusAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
		 }).done(function(result){
			  if(result != null){
				  if(result.resultCode == 0){//local project
					  $("#successMsgId").html("<span style='color:red'>"+result.exceptionMsg+"</span>");
					  return;
				  }
				  if(result.id == 0){//live project
					  $("#successMsgId").html("<span style='color:red'> Exception Occurred While Updating To Live DataBase.. Try Later</span>");
					  return;
				  }
				  if(result.resultCode == 1 && result.id == 1){
					  $("#successMsgId").html("<span style='color:green'>Constituency QA Status  Updated Successfully.</span>").fadeOut(6000);
					  $('#presentStatusId').html($("#statusId option:selected").text());
					  $('#statusId').val(0);
					  $('#remarksId').val(' ');
					  return;
				  }
			  }else{
				  $("#successMsgId").html("<span style='color:red'> Exception Occurred .. Try Later. </span>");
			  }
		  })
	}
	