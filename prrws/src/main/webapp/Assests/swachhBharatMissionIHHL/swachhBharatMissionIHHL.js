
	getSwachhBharatMissionOverviewDtls();
	getSwachhBharatMissionStatusOverviewDtls();
	getIHHLCategoryWiseAnalysis();
	getIHHLAchivementProgressDtls();
	getSwachhBharatMissionLocationWiseDetails();
	
	function getSwachhBharatMissionOverviewDtls(){
		var json = {
			fromDate:"",
			toDate:"",
			year:""
		}
		$.ajax({                
			type:'POST',    
			url: 'getSwachhBharatMissionOverviewDtls',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			console.log(result);
		});	
	}
		function getSwachhBharatMissionStatusOverviewDtls(){
			var json = {
				fromDate:"",
				toDate:"",
				year:""
			}
			$.ajax({                
				type:'POST',    
				url: 'getSwachhBharatMissionStatusOverviewDtls',
				dataType: 'json',
				data : JSON.stringify(json),
				beforeSend :   function(xhr){
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				}
			}).done(function(result){
				console.log(result);
			});	
		}
		function getIHHLCategoryWiseAnalysis(){
				var json = {
					fromDate:"",
					toDate:"",
					year:""
				}
				$.ajax({                
					type:'POST',    
					url: 'getIHHLCategoryWiseAnalysis',
					dataType: 'json',
					data : JSON.stringify(json),
					beforeSend :   function(xhr){
						xhr.setRequestHeader("Accept", "application/json");
						xhr.setRequestHeader("Content-Type", "application/json");
					}
				}).done(function(result){
					console.log(result);
				});	
			}
			function getIHHLAchivementProgressDtls(){
					var json = {
						fromDate:"",
						toDate:"",
						year:""
					}
					$.ajax({                
						type:'POST',    
						url: 'getIHHLAchivementProgressDtls',
						dataType: 'json',
						data : JSON.stringify(json),
						beforeSend :   function(xhr){
							xhr.setRequestHeader("Accept", "application/json");
							xhr.setRequestHeader("Content-Type", "application/json");
						}
					}).done(function(result){
						console.log(result);
					});	
				}
				function getSwachhBharatMissionLocationWiseDetails(){
						var json = {
							fromDate:"",
							toDate:"",
							year:""
						}
						$.ajax({                
							type:'POST',    
							url: 'getSwachhBharatMissionLocationWiseDetails',
							dataType: 'json',
							data : JSON.stringify(json),
							beforeSend :   function(xhr){
								xhr.setRequestHeader("Accept", "application/json");
								xhr.setRequestHeader("Content-Type", "application/json");
							}
						}).done(function(result){
							console.log(result);
						});	
					}