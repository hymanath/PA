
	function showAndHideTabs(searchType)
	{
		$('#basicCommitteeTab, #publicrepresantativeTab, #mandalaffiliatedTab').removeClass('arrow_selected');
		if(searchType == 'basicCommitteeDiv')
		{
			$('#'+searchType+'').show();
			$('#publicrepresantative').hide();
			$('#mandalaffiliated').hide();			
			$('#basicCommitteeTab').addClass('arrow_selected');
		}
		
		else if(searchType == 'publicrepresantative')
		{				
			$('#basicCommitteeDiv').hide();
			$('#'+searchType+'').show();
			$('#mandalaffiliated').hide();
			$('#publicrepresantativeTab').addClass('arrow_selected');
		}
		else if(searchType == 'mandalaffiliated')
		{				
			$('#publicrepresantative').hide();
			$('#basicCommitteeDiv').hide();
			$('#'+searchType+'').show();
			$('#mandalaffiliatedTab').addClass('arrow_selected');
		}
		
	}
	
	function validateSearchType(searchTypeId)
	{
		if(searchTypeId == 1)
		{
			$('#searchTypeId').val(searchTypeId);
		}
		
		else if(searchTypeId == 2)
		{				
			$('#searchTypeId').val(searchTypeId);
		}
	}
	
	function getCommiteeDetailsForCommittee()
	{
		var committeeLevelId = 	$('#searchTypeId').val();
		var committeeTypeId = 	$('#committeeTypeId').val();
		var committeeId = 	$('#committeeId').val();
		
		var jsObj = {
			committeeLevelId : committeeLevelId,
			committeeTypeId : committeeTypeId,
			committeeId : committeeId
		}
		
		 $.ajax({
				type : "POST",
				url : "getStatewiseDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			});
			
	}