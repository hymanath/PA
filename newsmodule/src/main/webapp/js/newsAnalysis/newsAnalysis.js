function callAjax(jsObj,url)
	{

		var callback =
		{			
			success : function( o )
			{
				try
				{ 
					myResults = YAHOO.lang.JSON.parse(o.responseText); 
					if (jsObj.task == "getPartyList")
					{
						buildSelectOptionVoforMuntiple(myResults,"whomPartysList","partyList","Party");	
					}
					else if (jsObj.task == "getCandidatesOfAParty")
					{
						fillSelectOptionsVO(myResults,jsObj.divId,"Party");	
					}
					else if (jsObj.task == "getBenefitList")
					{
						buildSelectOptionVoforMuntiple(myResults,"whomBenfitsList","benifitsList"," Benifits");	
					}
					else if (jsObj.task == "getKeywordsList")
					{
						buildKeywords(myResults);	
					}
					else if (jsObj.task == "partyGallariesForUplaod")
					{
						buildCategoriesList(myResults);	
					}
					
					else if (jsObj.task == "getSource")
					{
						fillSelectOptionsVO(myResults,jsObj.divId,"Source");	
					}
				
				}
				catch(e)
				{   
				 
				}  
			},
			scope : this,
			failure : function( o )
			{
				
			}
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
		
		
	function getPartiesList()
	{
		var jsObj=
			{
				partySelectBoxId:"partiesList",
				partiesListForWhome:"partiesListForWhome",
				task:'getPartyList'
			};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getPartiesListAction.action?"+rparam;
		callAjax(jsObj, url);
	}
	
	function buildSelectOptionVoforMuntiple(results,divId1,divId2,name)
	{
		$('#'+divId1+'').find('option').remove();
		$('#'+divId1+'').append('<option value="0">Select '+name+'</option>');
		$('#'+divId2+'').find('option').remove();
		$('#'+divId2+'').append('<option value="0">Select '+name+'</option>');
		if(results != null)
		{
			for(var i in results)
			{
				$('#'+divId1+'').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
				$('#'+divId2+'').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
			}
			
		}
	}
	
	function getCandidatesOfSelectedParty(partyId,divId)
	{
		$('#list1').find('option').remove();
		$('#candidateAjaxImg').show();
		
			var jsObj = {
				partyId :partyId,
				divId   : divId,
				task : "getCandidatesOfAParty"	
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCandidatesOfAParty.action?"+rparam;
		callAjax(jsObj,url);
	}
	
	function  getBenefitList()
	{
	  var jsObj = {
				 task : "getBenefitList"	
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getBenefitListAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function  getKeyWorsList()
	{
	  var jsObj = {
				 task : "getKeywordsList"	
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getKeywordsListAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function fillSelectOptionsVO(results,divId,type)
	{
		$('#'+divId+'').find('option').remove();
		if(!(type == "Source"))
		{
			$('#'+divId+'').append('<option value="0">Select '+type+'</option>');
		}			
		if(results != null)
		{
			for(var i in results)
			{
				$('#'+divId+'').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
			}
			
		}
		if(type == "Source")
		{
			$("#newsSourceId").multiselect({	
					multiple: true,
					selectedList: 10,
					hide: "explode"	
			}).multiselectfilter({
				//header:"Select Source"    
			});
		}
		
	}
	
	function unCheckCandidateCheckBox()
	{
		$('#candidateCheckId').attr('checked', false); 
		getKeyWorsList();
		$('#keywordsDiv').show();
	}
	
	function unCheckKeywordsCheckBox()
	{
		$('#keywordscheckId').attr('checked', false); 
		getPartyGallariesForUplaod();
		$('#keywordsDiv').show();
	}
	
	function getKeyWorsList()
	{
		$.ajax({
		type: "GET",
		url: "getKeywordsListAction.action",
		data: { task:"getKeywordsList" }
		})
		.done(function( result ) {
		$('#keywordsList').find('option').remove();
		//$('#keywordsList').append('<option value="0">Select Keyword</option>');
		$.each(result,function(index,value){
			$('#keywordsList').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		
			$("#keywordsList").multiselect({	
					multiple: true,
					selectedList: 10,
					hide: "explode"	
			}).multiselectfilter({
				header:"Select Keyword"    
			});
		});
	}
	function getPartyGallariesForUplaod()
	{
		var partyId=872;
		var jsObj =
			{ 
				partyId : partyId,
				contentType : "News Gallary",
				type:"whomegallaryId",
				task : "partyGallariesForUplaod"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartyGallariesForUplaodPhotoAction.action?"+rparam;
		callAjax(jsObj,url);
	}
	
	function buildCategoriesList(result)
	{
		$('#keywordsList').find('option').remove();
		//$('#keywordsList').append('<option value="0">Select Categoery</option>');
		$.each(result,function(index,value){
			$('#keywordsList').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		
			$("#keywordsList").multiselect({	
					multiple: true,
					selectedList: 10,
					hide: "explode"	
			}).multiselectfilter({
				header:"Select Keyword"    
			});
	}

function getSource()
{
	var timeST = new Date().getTime();	
	
		var jsObj = {
			time : timeST,
			selectOptionId :"filesourceId0",
			divId : "newsSourceId",
			task : "getSource"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventsGallariesForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
	
}

function getRespectedLocationValues(loc)
{
	 if(loc == 1){
		showHideLocations(true,false,false);
	 }else if(loc == 2){
		showHideLocations(false,true,false);
	 }else if(loc == 3){
		showHideLocations(false,false,true);
	 }else {
		$(".LocationLevelId").show();
		$(".districtSelReport").hide();
		$(".parliamSelReport").hide();
		$(".assembSelReport").hide();
	 }
}
function showHideLocations(dist,pc,ac)
{
	 if(dist){
	  $(".districtSelReport").show();
	  $(".LocationLevelId").hide();
	  //buildMultiSelectBox("districtSelReport");
	 }else{
	  $(".districtSelReport").hide();
	 }
	 if(pc){
	  $(".parliamSelReport").show();
	  $(".LocationLevelId").hide();
	  //buildMultiSelectBox("parliamSelReport");
	 }else{
	  $(".parliamSelReport").hide();
	 }
	 if(ac){
	  $(".assembSelReport").show();
	  $(".LocationLevelId").hide();
	  //buildMultiSelectBox("assembSelReport");
	 }else{
	  $(".assembSelReport").hide();
	 }
}

function buildMultiSelectBox(divId)
{	
	$('.'+divId+'').multiselect({	
					multiple: true,
					selectedList: 10,
					hide: "explode"	
			}).multiselectfilter({
				//header:"Select Keyword"    
			});
	
}

$(document).ready(function(){

	$('#todateId').datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date()
	});
	$('#fromDateId').datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date()
	});
		
		
	getPartiesList();
	getBenefitList();
	getSource();
		
	$('#districtSelReportId').multiselect({	
			multiple: true,
			selectedList: 10,
			hide: "explode"	
	}).multiselectfilter({
		//header:"Select District"    
	});
			
	$('#parliamSelReportId').multiselect({	
			multiple: true,
			selectedList: 10,
			hide: "explode"	
	}).multiselectfilter({
		//header:"Select Keyword"    
	});
	
	$('#assembSelReportId').multiselect({	
			multiple: true,
			selectedList: 10,
			hide: "explode"	
	}).multiselectfilter({
		//header:"Select Keyword"    
	});

});

function getAnalysisData()
{
	var fromdate = $("#fromDateId").val();
	var todate   = $("#todateId").val();
	var whoPartyId   = $("#partyList option:selected").val();
	var whomPartyId   = $("#whomPartysList option:selected").val();
	var whoCandidateId   = $("#candidateId option:selected").val();
	var whomCandidateId   = $("#whomCandidatesList option:selected").val();
	var whoBenfitId   = $("#benifitsList option:selected").val();
	var whomBenfitId   = $("#whomBenfitsList option:selected").val();
	var locationLevelId = $("#locationLevelId option:selected").val();
	var locationLevelValue = "";
	var selectedLocationvalues = "";
	if(locationLevelId == 0)
	{
		locationLevelValue = 0;
	}
	else if(locationLevelId == 1)
	{
		selectedLocationvalues = $("#districtSelReportId").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	}
	else if(locationLevelId == 2)
	{
		selectedLocationvalues = $("#parliamSelReportId").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	}
	else if(locationLevelId == 3)
	{
		selectedLocationvalues = $("#assembSelReportId").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	}
	for(var i in selectedLocationvalues)
	{
		locationLevelValue = locationLevelValue+""+selectedLocationvalues[i]+",";
	}
	
	var selected_values = $("#newsSourceId").multiselect("getChecked").map(function(){
	return this.value;    
	}).get();
	var newsSourceId = "";
	for(var i in selected_values){
	newsSourceId = newsSourceId+""+selected_values[i]+",";
	}
	
	var KeyWordsList = "";
	
	if($('#keywordscheckId').is(':checked') || $('#candidateCheckId').is(':checked'))
	{
		var selected_values = $("#keywordsList").multiselect("getChecked").map(function(){
		return this.value;    
		}).get();
		for(var i in selected_values){
		KeyWordsList = KeyWordsList+""+selected_values[i]+",";
		}
	}
	var timeST = new Date().getTime();	
	
		var jsObj = {
			time : timeST,
			fromdate :fromdate,
			todate : todate,
			whoPartyId : whoPartyId,
			whomPartyId : whomPartyId,
			whoCandidateId : whoCandidateId,
			whomCandidateId : whomCandidateId,
			whoBenfitId : whoBenfitId,
			whomBenfitId : whomBenfitId,
			locationLevelId : locationLevelId,
			locationLevelValue : locationLevelValue,
			newsSourceId : newsSourceId,
			KeyWordsList : KeyWordsList,
			task : "getAnalysedData"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAnalysedDataAction.action?"+rparam;						
	callAjax(jsObj,url);
}
