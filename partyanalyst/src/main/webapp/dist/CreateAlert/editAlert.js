	function buildAlertPage(result){
		if(result != null){  
			//alert Type 
			$("#alertTypeId").val(result.alertTypeId);
			var select = new Dropkick("#alertTypeId");
			select.refresh();
			
			//alert alertImpactId 
			$("#alertImpactId").val(result.alertImpactId);
			var select = new Dropkick("#alertImpactId");
			select.refresh();
			
			//information source 
			$("#alertSourceId").val(result.alertSourceId);
			var select = new Dropkick("#alertSourceId");
			select.refresh();
			
			//severity
			$("#alertSeverityId").val(result.severity);
			var select = new Dropkick("#alertSeverityId");
			select.refresh();
			
			//locationLevelId
			var level = result.locationLevelId;
			if(level == 5 || level == 7){
				$("#alertlevelId1").val(5);
				var select = new Dropkick("#alertlevelId1");       
				select.refresh();
			}else if(level == 6 || level == 8){       
				$("#alertlevelId1").val(6);
				var select = new Dropkick("#alertlevelId1");       
				select.refresh();
			}else{
				$("#alertlevelId1").val(result.locationLevelId);
				var select = new Dropkick("#alertlevelId1");
				select.refresh();
			}
			
			
			//stateId
			$("#stateId1").val(result.stateId);
			var select = new Dropkick("#stateId1");
			select.refresh(); 
			
			disableByLevel(1);    
			initializeLocation(result);
			
			//alertTitleId
			$("#alertTitleId").val(result.title);  
			
			//alertdescriptionId
			$("#alertdescriptionId").text(result.desc); 

			//build invloved candidate
			buildInvolvedCandidate(result.idNamesList);
			
			//build assigned candidate
			buildAssignedCandidate(result.assignList);
			
			//build document
			buildDocument(result.docList);
			setTimeout(function(){
				if(result.districtId > 0){
					$("#referdistrictId1").val(result.districtId);
					var select = new Dropkick("#referdistrictId1");
					select.refresh();
				}
			}, 2000);                  
			
			
		}
	}
	function initializeLocation(result){
		if(result.locationLevelId == 2){//state
			
		}else if(result.locationLevelId == 3){//district
			initDist(1,result.districtId);
		}else if(result.locationLevelId == 4){//constituency
			initConst(1,result.districtId,result.constituencyId);                
		}else if(result.locationLevelId == 5 || result.locationLevelId == 7){//mandal/muncipality 
			if(result.localBodyId > 0){
				initMandal(1,result.districtId,result.constituencyId,result.localBodyId);
			}else{
				initMandal(1,result.districtId,result.constituencyId,result.tehsilId);
			} 
		}else if(result.locationLevelId == 6 || result.locationLevelId == 8){  //village/ward
			if(result.localBodyId > 0){
				initPanchayat(1,result.districtId,result.constituencyId,result.localBodyId,result.wardId);
			}else{
				initPanchayat(1,result.districtId,result.constituencyId,result.tehsilId,result.panchayatId);
			}  
		}
	}
	function initDist(index,distId){
		var stateId = $("#stateId"+index).val();
		var jobj = {
			stateId : stateId  
		}
		$.ajax({
			type : 'GET',
			url : 'getDistrictsForStateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			var str='';
			if(result != null && result.length > 0){    
				for(var i in result){
					if(result[i].id > 0)
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#referdistrictId"+index).html(str);
				$("#referdistrictId"+index).dropkick();
				var select1 = new Dropkick("#referdistrictId"+index);
				select1.refresh();
				$("#referdistrictId1").val(distId);
				var select = new Dropkick("#referdistrictId1");
				select.refresh();
			}  
		});
	}
	
	function initConst(index,districtId,constituencyId){
		var stateId = $("#stateId"+index).val();
		var jobj = {
			stateId : stateId  
		}
		$.ajax({
			type : 'GET',
			url : 'getDistrictsForStateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			var str='';
			if(result != null && result.length > 0){    
				for(var i in result){
					if(result[i].id > 0)
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#referdistrictId"+index).html(str);
				$("#referdistrictId"+index).dropkick();
				var select1 = new Dropkick("#referdistrictId"+index);
				select1.refresh();
				$("#referdistrictId1").val(districtId);
				var select = new Dropkick("#referdistrictId1");
				select.refresh();  
				 //get all the constituency and select one here...
				var distId = $("#referdistrictId"+index).val();
				var jobj = {
					districtId : distId  
				}
				$.ajax({
					type : "POST",
					url  : "getConstituenciesByDistrictAction.action",
					data : {task:JSON.stringify(jobj)}
				}).done(function(result){
					var constiStr='';
					if(result != null && result.length > 0){
						for(var i in result){
							if(result[i].id > 0)
								constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
						}
						$("#referconstituencyId"+index).html(constiStr);
						$("#referconstituencyId"+index).dropkick();
						var select3 = new Dropkick("#referconstituencyId"+index);
						select3.refresh();
						$("#referconstituencyId1").val(constituencyId);
						var select4 = new Dropkick("#referconstituencyId1");
						select4.refresh();
						//temp
						$("#referdistrictId1").val(districtId);
						var select = new Dropkick("#referdistrictId1");
						select.refresh();   
					}
				}); 
			}  
		});
	}
	function initMandal(index,districtId,constituencyId,localBodyId){
		var stateId = $("#stateId"+index).val();
		var jobj = {
			stateId : stateId  
		}
		$.ajax({
			type : 'GET',
			url : 'getDistrictsForStateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			var str='';
			if(result != null && result.length > 0){    
				for(var i in result){
					if(result[i].id > 0)
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#referdistrictId"+index).html(str);
				$("#referdistrictId"+index).dropkick();
				var select1 = new Dropkick("#referdistrictId"+index);
				select1.refresh();
				$("#referdistrictId1").val(districtId);
				var select = new Dropkick("#referdistrictId1");
				select.refresh();  
				 //get all the constituency and select one here...
				var distId = $("#referdistrictId"+index).val();
				var jobj = {
					districtId : distId  
				}
				$.ajax({
					type : "POST",
					url  : "getConstituenciesByDistrictAction.action",
					data : {task:JSON.stringify(jobj)}
				}).done(function(result){
					var constiStr='';
					if(result != null && result.length > 0){
						for(var i in result){
							if(result[i].id > 0)
								constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
						}
						$("#referconstituencyId"+index).html(constiStr);
						$("#referconstituencyId"+index).dropkick();
						var select3 = new Dropkick("#referconstituencyId"+index);
						select3.refresh();
						$("#referconstituencyId1").val(constituencyId);
						var select4 = new Dropkick("#referconstituencyId1");
						select4.refresh();
						//get all the mandal/muncipality and select one here...
						var constId = $('#referconstituencyId'+index).val();
						var jobj = {
							constituencyId : constId
						}
						$.ajax({
							type : "POST",
							url  : "getMandalsByConstituencyAction.action",
							data : {task:JSON.stringify(jobj)}
						}).done(function(result){
							var mandalStr='';
							if(result != null && result.length > 0){
								for(var i in result){
									if(result[i].id > 0)
									mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
								}
								$("#refermandalNameId"+index).html(mandalStr);
								$("#refermandalNameId"+index).dropkick();
								var select5 = new Dropkick("#refermandalNameId"+index);
								select5.refresh(); 
								
								$("#refermandalNameId1").val(localBodyId);
								var select6 = new Dropkick("#refermandalNameId1");
								select6.refresh(); 
							}	
						});
					}
				}); 
			}  
		});
	}
	function initPanchayat(index,districtId,constituencyId,localBodyId,wardId){
		var stateId = $("#stateId"+index).val();
		var jobj = {
			stateId : stateId  
		}
		$.ajax({
			type : 'GET',
			url : 'getDistrictsForStateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			var str='';
			if(result != null && result.length > 0){    
				for(var i in result){
					if(result[i].id > 0)
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#referdistrictId"+index).html(str);
				$("#referdistrictId"+index).dropkick();
				var select1 = new Dropkick("#referdistrictId"+index);
				select1.refresh();
				$("#referdistrictId1").val(districtId);
				var select = new Dropkick("#referdistrictId1");
				select.refresh();  
				 //get all the constituency and select one here...
				var distId = $("#referdistrictId"+index).val();
				var jobj = {
					districtId : distId  
				}
				$.ajax({
					type : "POST",
					url  : "getConstituenciesByDistrictAction.action",
					data : {task:JSON.stringify(jobj)}
				}).done(function(result){
					var constiStr='';
					if(result != null && result.length > 0){
						for(var i in result){
							if(result[i].id > 0)
								constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
						}
						$("#referconstituencyId"+index).html(constiStr);
						$("#referconstituencyId"+index).dropkick();
						var select3 = new Dropkick("#referconstituencyId"+index);
						select3.refresh();
						$("#referconstituencyId1").val(constituencyId);
						var select4 = new Dropkick("#referconstituencyId1");
						select4.refresh();
						//get all the mandal/muncipality and select one here...
						var constId = $('#referconstituencyId'+index).val();
						var jobj = {
							constituencyId : constId
						}
						$.ajax({
							type : "POST",
							url  : "getMandalsByConstituencyAction.action",
							data : {task:JSON.stringify(jobj)}
						}).done(function(result){
							var mandalStr='';
							if(result != null && result.length > 0){
								for(var i in result){
									if(result[i].id > 0)
									mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
								}
								$("#refermandalNameId"+index).html(mandalStr);
								$("#refermandalNameId"+index).dropkick();
								var select5 = new Dropkick("#refermandalNameId"+index);
								select5.refresh();
								$("#refermandalNameId1").val(localBodyId);
								var select6 = new Dropkick("#refermandalNameId1");
								select6.refresh();    
								//get all the panchayat/ward and select one here...
								$("#referpanchayatId"+index).find('option').not(':first').remove();
								var mandalId = $('#refermandalNameId'+index).val();
								var  type = $("#refermandalNameId"+index+" option:selected").text();  
								if(type.indexOf("Mandal") == -1) 
									type = "muncipality" ;
								else
									type = "mandal" ; 
								var jsObj={
									mandalId :mandalId,
									type:type,
									task:""
								}
								$.ajax({
									type:"POST",
									url :"getPanchayatDetailsAction.action",
									dataType: 'json',
									data: {task:JSON.stringify(jsObj)}
								}).done(function(result){
									var panchyatStr='';
									if(result!=null && result.length>0){
										for(var i in result){
											if(result[i].id > 0)
												panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
										}
										$("#referpanchayatId"+index).html(panchyatStr);
										$("#referpanchayatId"+index).dropkick();
										var select7 = new Dropkick("#referpanchayatId"+index);
										select7.refresh();
										
										$("#referpanchayatId1").val(wardId);
										var select8 = new Dropkick("#referpanchayatId1");
										select8.refresh();
									}  
								});
							}	
						});
					}
				}); 
			}  
		});
	}
	var control;
	var lang;
	
	function buildInvolvedCandidate(candidateList){  
		if(candidateList != null && candidateList.length > 0){
			for(var i in candidateList){
				$("#involvedCandidatesDiv").show();
				$(".membersBlock").show();
				var name  = candidateList[i].name;
				var image = $(this).attr("attr_img_url");
				var attrId = parseInt(candidateList[i].cadreId);       
				var attrConsti =  candidateList[i].constituencyName;
				var mobile = candidateList[i].mobileNo;
				var statusId = candidateList[i].statusId;
				var uniqueId = "unique"+i;    
				var str ='';
				str+='<div id="'+uniqueId+'" class="col-md-3 col-xs-12 col-sm-6">';
				str+='<div class="involveBlock">';
				str+='<div class="media"><div class="media-left">';
				str+='<img src="'+image+'" onerror="setDefaultImage(this);" alt="image" style="height:30px;width:30px;" class="img-circle">';
				str+='</div>';
				str+='<div class="media-body">';
				str+='<input type="hidden" class="form-control memberDatacls" name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'"/>';
				str+='<div class="col-md-12"><b>'+name+'</b></div>';
				str+='<div class="col-md-12"><b>'+mobile+'</b></div>';
				str+='<div class="col-md-12"><label>'+attrConsti+'</label></div>';
				str+='<div class="col-md-12">';
				str+='<div class="form-inline">';
				str+='<div class="onoffswitch" style="display:inline-block">';
				str+='<input type="checkbox"  name="alertVO.idNamesList['+cloneCount+'].name" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch'+cloneCount+'" checked>';
				str+='<label class="onoffswitch-label" for="myonoffswitch'+cloneCount+'">';
				str+='<span class="onoffswitch-inner"></span>';
				str+='<span class="onoffswitch-switch"></span>';
				str+='</label>';
				str+='</div>';
				str+='</div>';
				str+='</div></div></div>';
				str+='<span class="closeIcon" btn-type="involve" attr_category="involve" attr_unique_id="'+uniqueId+'" id="'+attrId+'"  clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove removeIconNew"       style="display:block;"></i></span></div></div>';
				$("#duplicateCandidateBlock").html('');
				
				involvedCadreIds.push(attrId);  	
				$(".membersBlock").append(str);
				$("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
				var addStr ='';
				addStr+='<p class="text-capital" >'+name+'</p>';
				addStr+='<p>'+mobile+'</p>';
				addStr+='<p class="text-capitalize">'+attrConsti+'</p>';
				$("#duplicateCandidateBlock").html(''+addStr+'');
				cloneCount = cloneCount+1;
				$('html, body').animate({
						scrollTop: $('.membersBlock').offset().bottom
				}, 2000);
			}
		}
	}
	function buildAssignedCandidate(candidateList){
		if(candidateList != null && candidateList.length > 0){
			for(var i in candidateList){
				$("#involvedCandidatesDiv").show();
				$(".membersBlock").show();
				var name  = candidateList[i].name;
				var image = $(this).attr("attr_img_url");
				var attrId = parseInt(candidateList[i].cadreId);       
				var attrConsti =  candidateList[i].constituencyName;
				var mobile = candidateList[i].mobileNo;
				var uniqueId = "unique"+i;    
				var str ='';
				str+='<div id="'+uniqueId+'" class="col-md-3 col-xs-12 col-sm-6">';
				str+='<div class="involveBlock">';
				str+='<div class="media"><div class="media-left">';
				str+='<img src="'+image+'" onerror="setDefaultImage(this);" alt="image" style="height:30px;width:30px;" class="img-circle">';
				str+='</div>';
				str+='<div class="media-body">';
				str+='<input type="hidden" class="form-control assignmemberDatacls" name="alertVO.assignList['+cloneCount+'].id" value="'+attrId+'"/>';
				str+='<div class="col-md-12"><b>'+name+'</b></div>';
				str+='<div class="col-md-12"><b>'+mobile+'</b></div>';
				str+='<div class="col-md-12"><label>'+attrConsti+'</label></div>';
				str+='<div class="col-md-12">';
				str+='</div></div></div>';
				str+='<span class="closeIcon" btn-type="assign" attr_category="assign" attr_unique_id="'+uniqueId+'" id="'+attrId+'" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove removeIconNew" style="display:block;"></i></span></div></div>';
				$("#duplicateCandidateBlock").html('');
				
				assignCadreIds.push(attrId);	
				$(".assignedMembersBlock").append(str);
				$("#assignedMembers").html('('+assignCadreIds.length+' - Members added)');
				var addStr ='';
				addStr+='<p class="text-capital" >'+name+'</p>';
				addStr+='<p>'+mobile+'</p>';
				addStr+='<p class="text-capitalize">'+attrConsti+'</p>';
				$("#duplicateCandidateBlock").html(''+addStr+'');
				
				cloneCount = cloneCount+1;
				$('html, body').animate({
						scrollTop: $('.membersBlock').offset().bottom
				}, 2000);
			}
		}
	}
	function buildDocument(docList){ 
		 
		if(docList != null && docList.length > 0){
			var str = '';
			str+='<ul class="list-group" id="totalFileListId">';    
			for(var i in docList){
				var fileName = docList[i].name;
				var filePath = docList[i].positionName;
				var extName = filePath.split(".")[1];
				if(fileName.search('#') != -1 || fileName.search('u0') != -1){
					var name = filePath.split("/")[1];
				}else{
					var name = fileName;
					name = name+"."+extName;    
				}
				str+='<li id="existFileId'+i+'" class="list-group-item">'+name+'<span attr_file_id="'+docList[i].id+'" attr_id="existFileId'+i+'" class="closeIcon clearExistFileCls">x</span></li>';
			}
			str+='</ul>'; 
			$("#existDocId").html(str);
		}
		
	}
	
	$(document).on("click",".clearExistFileCls",function(){  
		var fileCloseId = $(this).attr("attr_id");
		var fileId = $(this).attr("attr_file_id");
		$("#"+fileCloseId).remove(); 
		var idList = $("#fileIdList").val();
		idList = idList+","+fileId;
		$("#fileIdList").val(idList);
	});
	