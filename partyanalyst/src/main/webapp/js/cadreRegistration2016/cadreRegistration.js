
$(document).ready(function() {
    initialiseDatePickerForCadreRegistration();
});

function initialiseDatePickerForCadreRegistration(){
		$("#dateRangeIdForCadre").daterangepicker({
			opens: 'right',
			startDate: moment(),
			endDate: moment(),
			locale: {
			  format: 'DD-MM-YYYY'   
			},
		})
}  
function convertRequiredDate(date){
	   var dateArr=date.split("-");
	   return dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
}  
$(document).on('click','#cadreModalTabDivid',function(){
	$("#tabUserWiseReportDiv").html(' ');        
	$("#cadreModal").modal('show');
	$(".tabModal").show();
	$(".webModal").hide();
	$("#myModalLabel1").html("KUPPAM CONSTITUENCY TAB USER DETAILED REPORT");
	 var constituencyId = $("#constituencySeletBoxId").val();
		var dates = $("#dateRangeIdForCadre").val();
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		var curr_year = d.getFullYear();  
		var fromDates= new Date(convertRequiredDate(fromDate.trim()));
		var toDates= new Date(convertRequiredDate(toDate.trim()));
		var currentDate =curr_date+ "-"+curr_month+"-"+curr_year;
		var currentDates = currentDate;
		var currentDates = new Date(convertRequiredDate(currentDates.trim()));
	   if(currentDates.getTime() == fromDates.getTime() && currentDates.getTime() == toDates.getTime()){
		   $("#notReceiveRegistrationFieldStaffDivId").show();
		  getNotReceiveRegistrationPerson(constituencyId,currentDate);
	   }else{
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');   
			$("#notReceiveRegistrationFieldStaffDivId").hide();   
	   }
	   
		$(".showTabUserWiseDetails").show();
		getCadreRegistrationCountByConstituency(constituencyId,fromDate.trim(),toDate.trim());
});  
function getNotReceiveRegistrationPerson(constituencyId,currentDate){
	$("#notReceiveRegistrationFieldStaffDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,      
			date : currentDate
		};
		$.ajax({          
			type : 'GET',    
			url : 'getNotReceiveRegistrationPersonAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');
		  if(result != null && result.length > 0){
			  buildNotReceivedFieldStaffResult(result);
		  }else{
		  $("#notReceiveRegistrationFieldStaffDivId").html("No Data Available.");  
		  }
		});
	}
	function buildNotReceivedFieldStaffResult(result){
	var str='';
			str+='<h4>IDLE TAB USER DETAILS</h4>';
			str+='<table class="table table-bordered table-condensed m_top10">';
				str+='<tbody>';
				str+='<tr> ';
				for(var i in result){
					if(result[i].count != null && result[i].count>0){
				     str+='<td><h2><a style="cursor:pointer;" class="tabUserCountcls" attr_Ids='+result[i].idsList+'>'+result[i].count+'</a></h2>';
				     str+='<p style="color:'+htmlColorCodeArr[i]+'">'+result[i].name+'</p></td> ';
					}else{
					  str+='<td><h2>'+result[i].count+'</h2>';
			          str+='<p style="color:'+htmlColorCodeArr[i]+'">'+result[i].name+'</p></td> ';
					}
				}
			 str+='</tr>';
			str+='</tbody>'; 
		str+='</table>';
		$("#notReceiveRegistrationFieldStaffDivId").html(str);
	}
	function getCadreRegistrationCountByConstituency(constituencyId,fromDate,toDate){
		 $("#tabUserWiseReportDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,      
			fromDate : fromDate,
			toDate : toDate
		};  
		$.ajax({          
			type : 'GET',    
			url : 'getCadreRegistrationCountByConstituencyAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#tabUserWiseReportDiv").html('');
		  if(result != null && result.length > 0){
			  buildCadreRegistrationOverViewResult(result);
		  }else{
			  $("#tabUserWiseReportDiv").html("NO DATA AVAILABLE.");
		  }
		});
	}
	function buildCadreRegistrationOverViewResult(tabUserInfoList){
		
			var str='';
			str+='<h4>FIELD USER REGISTRATION DETAILS</h4>';
			str+='<table class="table table-bordered table-condensed m_top10" id="tabUserWiseReportDataTableId"> ';
				str+='<thead> ';
					str+='<tr>';
					    str+='<th>Survey UserId</th>';
						str+='<th>Field Staff Name </th>';
						str+='<th>Image</th>';
						str+='<th>MobileNo</th>';
						str+='<th>No.Of Samples</th>';
						str+='<th>First Record Time</th>';
						str+='<th>Last Record Time</th>';
					str+='</tr>'; 
				str+='</thead>'; 
				str+='<tbody>';
				for(var j in tabUserInfoList){
					if(tabUserInfoList[j].name == "prasad" || tabUserInfoList[j].name == "abhi" || tabUserInfoList[j].name == "lakshman" || tabUserInfoList[j].name == "nagaraju" || tabUserInfoList[j].name == "mahi" || tabUserInfoList[j].name == "dhj" || tabUserInfoList[j].name == "sri" || tabUserInfoList[j].name == "Sudhakar" || tabUserInfoList[j].name == "H.Jyothi kumar"){
						continue;   
					}
						str+='<tr> ';
						   if(tabUserInfoList[j].userName != null && tabUserInfoList[j].userName.length > 0){
							str+='<td>'+tabUserInfoList[j].userName+'</td>';   
						   }else{
							 str+='<td> - </td>';  
						   }
						   if(tabUserInfoList[j].sampleCount > 0){
							 	if(tabUserInfoList[j].name != null){
							str+='<td>'+tabUserInfoList[j].name+'</td>';
							}else{
								str+='<td> - </td>';	
							}  
						   }else{
							if(tabUserInfoList[j].name != null){
							str+='<td style="color:red;">'+tabUserInfoList[j].name+'</td>';
							}else{
								str+='<td> - </td>';	
							}  
						   }
						 
							str+='<td><img src="http://mytdp.com/CR/tab_user_images/'+tabUserInfoList[j].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
							 if(tabUserInfoList[j].mobileNo != null && tabUserInfoList[j].mobileNo.length > 0){
							 str+='<td>'+tabUserInfoList[j].mobileNo+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							 if(tabUserInfoList[j].sampleCount != null && tabUserInfoList[j].sampleCount> 0){
							 str+='<td><a style="cursor:pointer;" class="noOfSamplesDetailsPopUpView" attr_tab_user_info_id='+tabUserInfoList[j].id+'>'+tabUserInfoList[j].sampleCount+'</a></td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							 if(tabUserInfoList[j].firstRecordInsertedTime != null && tabUserInfoList[j].firstRecordInsertedTime.length> 0){
							 str+='<td>'+tabUserInfoList[j].firstRecordInsertedTime.substring(0,19)+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							  if(tabUserInfoList[j].lastRecordInsertedTime != null && tabUserInfoList[j].lastRecordInsertedTime.length> 0){
							 str+='<td>'+tabUserInfoList[j].lastRecordInsertedTime.substring(0,19)+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
						str+='</tr>';
						  }
			str+='</tbody>'; 
		str+='</table>';
		
		$("#tabUserWiseReportDiv").html(str);
		$("#tabUserWiseReportDataTableId").dataTable();  
	}
	
	function setDefaultImage(img){
		img.onerror = "";
		img.src = "images/cadre_images/human.jpg";
		return true;
	}
	function setDefaultImage(img){
		img.onerror = "";
		img.src = "images/cadre_images/human.jpg";
		return true;
	}
	$(document).on("click",".tabUserCountcls",function(){
	var tabUserIdsString = $(this).attr("attr_Ids");
	var idsArr = tabUserIdsString.split(",");
	var tabUserIdStr;
	for(var i=0;i<idsArr.length;i++){
		if(i==0){
		tabUserIdStr = 	idsArr[i];
		}else{
		tabUserIdStr = tabUserIdStr+","+idsArr[i];	
		}
	}
	$("#noOfSamplesDetailsDiv").html(' ');
	$("#tabUserInfoDetailsHeadingId").html("IDLE TAB USER DETAILS.");
	$("#noOfSamplesModal").modal("show");
    getTabUserInfoDetails(tabUserIdStr);
});		
function getTabUserInfoDetails(tabUserIdStr){
	$("#tabUserInfoDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div   class="dot2"></div></div></div>');
		var jsObj={  
			tabUserInfoStrIds :tabUserIdStr      
		};
		$.ajax({          
			type : 'GET',    
			url : 'getTabUserInfoDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
		  if(result != null && result.length > 0){
			buildNotReceivedTabUserDetails(result);  
		  }else{
			$("#tabUserInfoDivId").html("NO DATA AVAILABLE.");  
		  }
		});
	}	
	function buildNotReceivedTabUserDetails(result){
		var str=''
		str+='<table class="table table-bordered table-condensed" id="fieldStaffDetailsDataTableId"> ';
		        str+='<thead>';
				 str+='<th>Name</th>'
				 str+='<th>MobileNo</th>'
				 str+='<th>Image</th>'
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr> ';
					 if(result[i].name != null && result[i].name.length > 0){
							str+='<td>'+result[i].name+'</td> '; 
					 }else{
							str+='<td> - </td> ';
					 }
					 if(result[i].mobileNo != null && result[i].mobileNo.length > 0){
					 str+='<td>'+result[i].mobileNo+'</td> ';	 	 
					 }else{
					 str+='<td> - </td> ';	 
					  }
					 str+='<td><img src="http://mytdp.com/CR/tab_user_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
				   str+='</tr>';
				}
			str+='</tbody>'; 
		str+='</table>';
		$("#tabUserInfoDivId").html(str);
		$("#fieldStaffDetailsDataTableId").dataTable();
	}
	$(document).on('click','.locationRadioCls',function(){
	var selectionType=$("input:radio[name=selectionType]:checked").val();
	var scopeType=$("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(selectionType,scopeType);  
});
$(document).on('click','.scopeRadioCls',function(){
	var selectionType=$("input:radio[name=selectionType]:checked").val();
	var scopeType=$("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(selectionType,scopeType);    
});
$(document).on('click','#cadreModalDivid',function(){ 
	$("#cadreModal").modal('show');
	$(".tabModal").hide();
	$(".webModal").show();
	$("#myModalLabel1").html("KUPPAM CONSTITUENCY DETAILED REPORT");
	var location = $("input:radio[name=selectionType]:checked").val();
	var scope = $("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(location,scope);  
});  
function getRegistrationCountDtls(location,scope){
		$("#kupamRegDtlsId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		//var location = "booth";
		var jsObj={  
			location : location,       
			constId : globalConstId,    
			scope : scope    
		};  
		$.ajax({          
			type : 'GET',      
			url : 'getRegistrationCountDtlsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#kupamRegDtlsId").html('');
			//console.log(result);
			buildRegistrationCountDtls(result,location,scope);       
		});
	}
	function buildRegistrationCountDtls(result,location,scope){
		var str = '';
		str+='<div class="table-responsive m_top20">';
          str+='<table class="table table-bordered" id="regCadreCountTableId">';
            str+='<thead class="text-capital text-center">';
              str+='<tr>';
                str+='<th rowspan="2">mandal</th>';
				if(location == "panchayat"){
					str+='<th rowspan="2">panchayat</th>';
				}
                if(location == "booth"){
					str+='<th rowspan="2">panchayat</th>';
					str+='<th rowspan="2">Booth No</th>'; 
				}
                str+='<th rowspan="2">total voters</th>';
                str+='<th rowspan="2">2014 Total Cadre</th>';
				if(scope == "today"){
					str+='<th colspan="6" class="text-capital text-center">2016 Cadre</th>';
				}else{
					str+='<th colspan="5" class="text-capital text-center">2016 Cadre</th>';
				}
                    
              str+='</tr>';
              str+='<tr>';
				str+='<th>renewal Cadre 2016</th>';
                str+='<th>renewal cadre percent(%)</th>';
                str+='<th>total Cadre 2016</th>';
				if(scope == "today"){
					str+='<th>total Cadre On Today</th>'; 
				}
                
                str+='<th>new cadre</th>';
                str+='<th>new cadre percent(%)</th>';
              str+='</tr>';
            str+='</thead>';
			for(var i in result.responseData){  
				str+='<tr>';
				str+='<td>'+result.responseData[i].mandalName+'</td> ';
				if(location == "panchayat"){
					str+='<td>'+result.responseData[i].panchayatName+'</td>';
				}
				if(location == "booth"){
					str+='<td>'+result.responseData[i].panchayatName+'</td>';
					str+='<td>'+result.responseData[i].boothName+'</td>'; 
				}
			   str+='<td>'+result.responseData[i].totalVoter+'</td>';  
			   str+='<td>'+result.responseData[i].cadreCount2014+'</td>';
			   str+='<td>'+result.responseData[i].renewalCount+'</td>';
			   if(result.responseData[i].cadreCount2014 > 0){
				   var precent = (result.responseData[i].renewalCount*(100/result.responseData[i].cadreCount2014)).toFixed(0);
				   str+='<td>'+precent+'</td>';
			   }else{
				   str+='<td>0</td>';
			   }
			   str+='<td>'+result.responseData[i].cadreCount2016OverAll+'</td>';
			   if(scope == "today"){
					str+='<td>'+result.responseData[i].cadreCount2016Today+'</td>';  
			   }			  
            
              
              str+='<td>'+result.responseData[i].newCount+'</td>'; 
			  if(result.responseData[i].cadreCount2016OverAll > 0){    
				  var precent = (result.responseData[i].newCount*(100/result.responseData[i].cadreCount2016OverAll)).toFixed(0);   
				  str+='<td>'+precent+'</td>';     
			  }else{
				  str+='<td>0</td>';  
			  }
              
			  str+='</tr>';
			}
          str+='</table>';
        str+='</div>';
		$("#kupamRegDtlsId").html(str);  
		$("#regCadreCountTableId").dataTable();   
	}
	