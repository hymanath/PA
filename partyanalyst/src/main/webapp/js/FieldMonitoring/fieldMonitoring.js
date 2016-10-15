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
		