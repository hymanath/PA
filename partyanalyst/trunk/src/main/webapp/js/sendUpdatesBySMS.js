	
	var scope = $('#listValue').val();
	var errDivValue = '';
	var flag = false;
	
	function populateLocations(val)
	{				
	$('#tableRowC').remove();
	$('#tableRowM').remove();
	$('#tableRowP').remove();
	$('#tableRowB').remove();
	clearErrDiv();
	var scope = $('#listValue').val();
	$('#listValue').css("border","1px solid lightBlue");
	if(scope == '0'){
			$('#errorMsgDiv').html('<span style="margin-left: -165px;"> Please Select Scope Value </span>');
			$('#listValue').css("border","1px solid IndianRed");
			return false;
	}	
	var value = val;
		if(value=="Constituency" || value=="Mandal" || value=="Panchayat" || value=="Booth")
		{	
			var str =''; 
			str +='<table style="margin-top:5px">';
			str +='<tr id="tableRowC">';
			str +='<td class="tdWidth1">Assembly Constituency : <font id="requiredValue" class="requiredFont">*</font></td>';
			str+='<td><select id="userAccessConstituencyList" class="selectWidth" name="userAccessConstituencyList" onchange="getMandalList(this.options[this.selectedIndex].value);">';
			str+='<option value="0"> Select Constituency </option>';
			 for(var i in locationDetails.constituencyArr)
				{
			str+='<option value='+locationDetails.constituencyArr[i].id+'>'+locationDetails.constituencyArr[i].value+'</option>';
				} 
			str+='</select></td>';	 
			str +='</tr>';
			str +='</table>';
			document.getElementById("showScopeSubs").innerHTML = str;
		}
		if(value=="Mandal" || value=="Panchayat" || value=="Booth")
		{
			var str ='';
			str +='<table>';	  
			str +='<tr id="tableRowM">';
			str +='<td class="tdWidth1">Mandal : <font id="requiredValue" class="requiredFont">*</font></td>';
			str+='<td><select id="mandalList" class="selectWidth" name="mandalList" onchange="getPanchayatList(\'panchayat\',\'panchayatField\');">';
			str+='</select></td>';	 
			str +='</tr>';
			str +='</table>';
			document.getElementById("showScopeSubs1").innerHTML = str;
		}
		if(value=="Panchayat" || value=="Booth")
		{
			var str ='';
			str +='<table>';	  
			str +='<tr id="tableRowP">';
			str +='<td class="tdWidth1" >Panchayat/Ward : <font id="requiredValue" class="requiredFont">*</font></td>';
			str+='<td><select id="panchayatList" class="selectWidth" name="panchayatList" onchange="getBoothList(this.options[this.selectedIndex].value);">';
			str+='</select></td>';			 
			str +='</tr>';
			str +='</table>';
			document.getElementById("showScopeSubs2").innerHTML = str;
		}
		
	}
	function getMandalList(selectElmt)
	{ 
		$("#mandalInfoAjaxImg").show().css("display","inline-block");
		$('#tableRowB').remove();
		$('#errorMsgDiv').html('&nbsp;');	
		var scope = $('#listValue').val();	
		var constituencyID = document.getElementById("userAccessConstituencyList");
		var name=constituencyID.options[constituencyID.selectedIndex].name;
		var value=constituencyID.options[constituencyID.selectedIndex].value;
		var locationAlertEl =  document.getElementById("locationAlertMsg");
		if(scope=="Panchayat" || scope=="Booth"){
		var selectedElmt=document.getElementById("panchayatList");
		var selectedElmts=document.getElementById("mandalList");			
		removeSelectElements(selectedElmt);
		removeSelectElements(selectedElmts);
		}

		//For voterEditData
		$('#msgDiv').html('');
		$("#voterDetailsDiv").html("");
		$('#statusMsgDiv').html('');
		$("#savediv1").css("display","none");
		$("#savediv").css("display","none");
		$("#startIndex").val('');
		$("#endIndex").val('');
		$("#successDiv").css("display","none");
		var emList = document.getElementsByName("mandalList");		
		if(emList.length > '0' || value == '0'){
		var selectedElmt=document.getElementById("panchayatList");
		var selectedElmts=document.getElementById("mandalList");
		var selectedElmnts=document.getElementById("boothList");
		removeSelectElements(selectedElmt);
		removeSelectElements(selectedElmts);
		removeSelectElements(selectedElmnts);
		}
		//end
		addCssStyle();
		
		var choice=false;
		if(value == 0)
		{
			$('#mandalList').css("border","1px solid lightBlue");
			$('#panchayatList').css("border","1px solid lightBlue");
			$('#boothList').css("border","1px solid lightBlue");
			$('#errorMsgDiv').html('Please Select Constituency');
			//For voterEditData
			$('#msgDiv').html('Please Select Constituency');
			$("#mandalInfoAjaxImg").hide();
			//end
			return false;
		}	
		
		var jsObj=
			{
			selected:value,
			selectElmt:selectElmt,
			task:"getMandalList"
			};		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function getPanchayatList(checkedele,selectedEle)
	{
		$("#wardInfoAjaxImg").show().css("display","inline-block");
		$('#tableRowB').remove();
		$('#errorMsgDiv').html('&nbsp;');
		$('#msgDiv').html('&nbsp;');
		var scope = $('#listValue').val();	
		var mandalId=document.getElementById("mandalList");
		var name=mandalId.options[mandalId.selectedIndex].name;
		var value1=mandalId.options[mandalId.selectedIndex].value;	

		if(scope=="Panchayat" || scope=="Booth"){
		var selectedElmt=document.getElementById("panchayatList");
		removeSelectElements(selectedElmt);
		}	
		//For voterEditData
		var epList = document.getElementsByName("panchayatList");		
		if(epList.length > '0' || value1 == '0'){
		var selectedElmt=document.getElementById("panchayatList");
		var selectedElmts=document.getElementById("boothList");
		removeSelectElements(selectedElmt);
		removeSelectElements(selectedElmts);
		}
		//end
		addCssStyle();
		if(value1 == 0)
		{
			$('#panchayatList').css("border","1px solid lightBlue");
			$('#boothList').css("border","1px solid lightBlue");
			$('#errorMsgDiv').html('Please Select Mandal or Municipality or Corporation');
			//For voterEditData
			$('#msgDiv').html('Please Select Mandal or Municipal Corporation');
			$("#wardInfoAjaxImg").hide();
			//End
		return false;
		}
		if(value1.charAt(0) =="1")
		{	
			var jsObj ={ 	
			id:value1,
			task:"getWards"	
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByMandalAction.action?"+rparam;					
			callAjax(jsObj,url);
			return false;
		}		
		if(value1.charAt(0) =="2")
		{ 
			var value = value1.substring(1);
			var jsObj=
			{
			selected:value,
			checkedele:checkedele,
			task:"getPanchayat"
			}		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByMandalAction.action?"+rparam;	
			callAjax(jsObj,url);
			return false;	
		}		
	}
	
	function getBoothList(selectElmt)
	{	$('#tableRowB').remove();
		addCssStyle();	
		$('#errorMsgDiv').html('&nbsp;');
		var scopeValue = $('#listValue').val();
		if(scopeValue == 'Booth'){
		$('#successMsgDiv').html('Please Wait for Booth Details...');
		$('#sendButton').hide();
			var constituencyId=document.getElementById("userAccessConstituencyList").value;
			var mandalId=document.getElementById("mandalList").value;
				var jsObj =
				{ 
					mandalId:mandalId,
					constituencyId:constituencyId,
					id:selectElmt,
					task:"getBooths"	
				};
					 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
					 var url = "getPanchayatByMandalAction.action?"+rparam;						
					 callAjax(jsObj,url);
		}
		else
			return;
	}

	
	function buildMandalList(results,jsObj)
	{ 
		$('#tableRowP').show();
		var selectElmt =jsObj.selectElmt;
		var selectedElmt=document.getElementById("mandalList");
		removeSelectElements(selectedElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;
			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}	
		}
	}

	function buildPanchayatList(results,jsObj)
	{ 
		var selectElmt =jsObj.selectElmt;
		var selectedElmt=document.getElementById("panchayatList");
		removeSelectElements(selectedElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;
			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}	
		}
	}

	function buildWardsList(results,jsObj)
	{
	if(results.length == 1)
		$("#msgDiv").html('Data not available');
		var selectElmt =jsObj.selectElmt;
		var selectedElmt=document.getElementById("panchayatList");
		removeSelectElements(selectedElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;
			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}	
		}
	}
	
	function buildBoothsList(results,jsObj)
	{
	$('#successMsgDiv').html('&nbsp;');
	$('#sendButton').show();
	if(results.length == '0' || results.length == '1'){
	errDivValue = '<span style="margin-left: -170px;"> Booths are not Available </span>';
	$('#errorMsgDiv').html(''+errDivValue+'');
	return false;
	}
	else if (results.length > '1' && results != null)
	{
	errDivValue = '';
	var str ='';
	str +='<table>';
	str +='<tr id="tableRowB">';
	str +='<td class="tdWidth1">Booth : <font id="requiredValue" class="requiredFont">*</font></td>';
	str+='<td><select id="boothList" class="selectWidth" name="boothList" onchange="clearErrDiv(); addCssStyle();">';
	str+='</select></td>';
	str +='</tr>';
	str +='</table>';
	document.getElementById("showScopeSubs3").innerHTML = str;
	}
	var selectElmt =jsObj.selectElmt;
	var selectedElmt=document.getElementById("boothList");
	removeSelectElements(selectedElmt);
	for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
				if(val == '0')
					opElmt.text=results[val].name;
				else if(val >'0')
					opElmt.text='Booth No. '+results[val].name;
				try
				{
				selectedElmt.add(opElmt,null); // standards compliant
				}
				catch(ex)
				{
				selectedElmt.add(opElmt); // IE only
				}
		}
	}

	function removeSelectElements(selectedElmt)
	{
		var len = '';
		if(selectedElmt !=null){
			len = selectedElmt.length;
				if(len !=null)
				{
					for(var i=len-1;i>=0;i--)
					{
						selectedElmt.remove(i);
				}
			}
		}
	}
	
	function handleSubmit()
	{		
		var scope = $('#listValue').val();
		if(scope == '0'){
			$('#errorMsgDiv').html('<span style="margin-left: -165px;"> Please Select Scope Value </span>');
			$('#listValue').css("border","1px solid IndianRed");
			return false;
		}
		var scopeIdVal=document.getElementById("userAccessConstituencyList").value;	
		var mandalValues = $('#mandalList').val();
		var panchayatValues = $('#panchayatList').val();
		var boothValues = $('#boothList').val();

		if(scopeIdVal == '0' || mandalValues == '0' || panchayatValues == '0'  || boothValues == '0'){
			$('#errorMsgDiv').html('<span style="margin-left: -60px;"> Please Select All Scope Related Elements </span>');				
			addCssStyle();
			return false;
		}	
		
		var smsContent = trim($('#smstxt').val());			
		if( scope=="Booth" && errDivValue !='' ){
		$('#errorMsgDiv').html('<span style="font-size: 15px; margin-left: 200px;"> Booths are not Available, So You are not allow to send SMS, Based on Booth Wise</span>');
		return false;
		}
		if(smsContent.length == '0'){
		$('#smstxt').css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('<span style="font-size: 15px; margin-left: -140px;"> Message should not be Empty<span>');
		return false;
		}
		
		if(!flag){
		flag = true;			
			var value = $('input:radio[name=voterType]:checked').val();

			if(scope == "Constituency")
				var scopeIdVal = $("#userAccessConstituencyList").val();
			if(scope == "Mandal")
				var scopeIdVal = $("#mandalList").val();
			if(scope == "Panchayat")
				var scopeIdVal = $("#panchayatList").val();
			if(scope == "Booth")
				var scopeIdVal = $("#boothList").val();
				
			var contentText=smsContent;

				var jsObj=
					{	
							type:value,
							scope:scope,
							scopeId:scopeIdVal,
							content:contentText,
							task:"sendSMSForCadreIds"
					}					
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "sendSMSAjaxAction.action?"+rparam;						
				callAjax(jsObj,url);
				$('#imageForMail').show();
				}
	}

	function getCadreId(selectEle)
	{
		if(selectEle.checked == true)
		{
			deSelectCheckBox();
			selectEle.checked = true;
		}
		else
		  return;
	}
	
	function clearErrDiv()
		{		
		var panchayat = $('#panchayatList').val();
		$('#errorMsgDiv').html('&nbsp;');
		$('#successMsgDiv').html('&nbsp;');
		if(panchayat == '0'){
		$('#errorMsgDiv').html('Please Select Panchayat or Ward No.');
		return false;
		}
	}
	
	function callAjax(jsObj,url)
	{
			 var myResults;
			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "getMandalList")
								{     
										$("#mandalInfoAjaxImg").hide();
										buildMandalList(myResults,jsObj);							
								}
								if(jsObj.task == "getPanchayat")
								{  
										$("#wardInfoAjaxImg").hide();
										buildPanchayatList(myResults,jsObj);							
								}
								if(jsObj.task == "getWards")
								{     
										$("#wardInfoAjaxImg").hide();
										buildWardsList(myResults,jsObj);								
								}
								if(jsObj.task == "getWardsForVoter")
								{     
										$("#wardInfoAjaxImg").hide();
										buildWardsList(myResults,jsObj);								
								}
								if(jsObj.task == "getBooths")
								{  										
										buildBoothsList(myResults,jsObj);								
								}
								if(jsObj.task == "sendSMSForCadreIds")
								{ 
									if(myResults != null){	
									flag=false;										
									$('#imageForMail').hide();
									$('#successMsgDiv').html(myResults.resultState+'  Messages Sent Successfully');
									}
								}
								if(jsObj.task == "getPublicationDate")
								{
								$('#publicationAjaxImage').css('display','none');

									buildPublicationDateList(myResults);
								}
							}catch (e) { 
							     $("#votersEditSaveAjaxImg").hide();
							     $("#votersEditSaveButtnImg").removeAttr("disabled");
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };
 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}	
	
	function addCssStyle()
	{	
		// For voterEditData
			var pValue = $(".pListClass").val();
			var bValue = $(".boothClass").val();
			var mValue = $(".mandalClass").val();
			
			if(mValue != '0'){
			$(".mandalClass").css("border","1px solid lightBlue");
			$(".pListClass").css("border","1px solid lightBlue");
			$(".boothClass").css("border","1px solid IndianRed");
			}

			if(mValue == '0'){
			$(".mandalClass").css("border","1px solid IndianRed");
			}


			if(pValue != '0'){
			$(".pListClass").css("border","1px solid lightBlue");
			}

			if(pValue == '0'){
			$(".pListClass").css("border","1px solid IndianRed");
			}
		
			if(bValue != '0'){
			$(".boothClass").css("border","1px solid lightBlue");
			}

			if(bValue == '0'){
			$(".boothClass").css("border","1px solid IndianRed");
			}
			
			//End
		var scopeIdVal=document.getElementById("userAccessConstituencyList").value;	
		var mandalValues = $('#mandalList').val();
		var panchayatValues = $('#panchayatList').val();
		var boothValues = $('#boothList').val();
			$('#userAccessConstituencyList').css("border","1px solid lightBlue");
			$('#mandalList').css("border","1px solid lightBlue");
			$('#panchayatList').css("border","1px solid lightBlue");
			$('#boothList').css("border","1px solid lightBlue");
			$('#listValue').css("border","1px solid lightBlue");
		if(scopeIdVal == '0'){
				$('#userAccessConstituencyList').css("border","1px solid IndianRed");
				$('#mandalList').css("border","1px solid IndianRed");
				$('#panchayatList').css("border","1px solid IndianRed");
		}
		if(mandalValues == '0'){
				$('#mandalList').css("border","1px solid IndianRed");
		}
		if(panchayatValues == '0'){
				$('#panchayatList').css("border","1px solid IndianRed");
		}
		if(boothValues == '0'){
				$('#errorMsgDiv').html('<span style="margin-left: -190px;">Please Select Booth </span>');
				$('#boothList').css("border","1px solid IndianRed");
		}		
	}
	
	function limitText(limitField, limitCount, limitNum)
	{	
		clearErrDiv();
		var limitFieldElmt = document.getElementById(limitField);
		var limitCountElmt = document.getElementById(limitCount);

		if (limitFieldElmt.value.length > limitNum) 
				limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum)+"  ";
		else		
				limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"  ";		
	}
	
	//For Editing voter details for Admin
		function getPanchayatListForVoter(checkedele,selectedEle)
	{
		$("#wardInfoAjaxImg").show().css("display","inline-block");
		$('#tableRowB').remove();
		$('#errorMsgDiv').html('&nbsp;');
		$('#msgDiv').html('');
		var scope = $('#listValue').val();	
		var publicationDateId=$("#publicationDateList").val();
		var mandalId=document.getElementById("mandalList");
		var name=mandalId.options[mandalId.selectedIndex].name;
		var value1=mandalId.options[mandalId.selectedIndex].value;	

		if(scope=="Panchayat" || scope=="Booth"){
		var selectedElmt=document.getElementById("panchayatList");
		removeSelectElements(selectedElmt);
		}	
		//For voterEditData
		var epList = document.getElementsByName("panchayatList");		
		if(epList.length > '0' || value1 == '0'){
		var selectedElmt=document.getElementById("panchayatList");
		var selectedElmts=document.getElementById("boothList");
		removeSelectElements(selectedElmt);
		removeSelectElements(selectedElmts);
		}
		//end
		addCssStyle();
		if(value1 == 0)
		{
			$('#panchayatList').css("border","1px solid lightBlue");
			$('#boothList').css("border","1px solid lightBlue");
			$('#errorMsgDiv').html('Please Select Mandal or Municipality or Corporation');
			//For voterEditData
			$('#msgDiv').html('Please Select Mandal or Municipal Corporation');
			$("#wardInfoAjaxImg").hide();
			//End
		return false;
		}
		if(value1.charAt(0) =="1")
		{	
			var jsObj ={ 	
			id:value1,
			publicationDateId:publicationDateId,
			task:"getWardsForVoter"	
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByMandalForVoterAction.action?"+rparam;					
			callAjax(jsObj,url);
			return false;
		}		
		if(value1.charAt(0) =="2")
		{ 
			var value = value1.substring(1);
			var jsObj=
			{
			selected:value,
			checkedele:checkedele,
			task:"getPanchayat"
			}		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByMandalAction.action?"+rparam;	
			callAjax(jsObj,url);
			return false;	
		}		
	}
	//For Editing voter details for Admin
	function getBoothInfo(val){
		 $("#boothInfoAjaxImg").show().css("display","inline-block");
		boothValue="editdata";
		var constituencyId=document.getElementById("userAccessConstituencyList").value;
		var mandalId=document.getElementById("mandalList").value;
		var publicationDateId=$("#publicationDateList").val();
		if(publicationDateId == 0){
		$('#msgDiv').html('Please Select Publication Date');
		$('#boothInfoAjaxImg').hide();
		return;
		}
		$('#msgDiv').html('');
		var jsObj =
		{
		mandalId:mandalId,
		constituencyId:constituencyId,
		publicationDateId:publicationDateId,
		id:val,
		type:"booth",
		task:"getBoothsForVoter"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPanchayatByMandalForVoterAction.action?"+rparam;
		callAjaxForVoterEdit(jsObj,url);
		}

		function callAjaxForVoterEdit(jsObj,url)
		{
		var myResults;
		var callback = {
		success : function( o ) {
		try {
		myResults = YAHOO.lang.JSON.parse(o.responseText);
		if(jsObj.task == "getBooths")
		{
		 $("#boothInfoAjaxImg").hide();
		buildBoothsInfo(myResults,jsObj);
		}
		if(jsObj.task == "getBoothsForVoter")
		{
		 $("#boothInfoAjaxImg").hide();
		buildBoothsInfo(myResults,jsObj);
		}
		if(jsObj.task == "getVotersDetails")
		{
		buildVotersInfoByBoothIds(myResults,jsObj);
		}
		}catch (e) {
		}
		},
		scope : this,
		failure : function( o ) {
		}
		};
		YAHOO.util.Connect.asyncRequest('POST', url, callback);
		}

		function buildBoothsInfo(myResults,jsObj)
		{
		if(myResults == null || myResults == '' || myResults.length == 0)
			$("#msgDiv").html('Booths are not available');
		var selectedElmt=document.getElementById("boothList");
		removeSelectElements(selectedElmt);
		for(var val in myResults)
		{
		var opElmt = document.createElement('option');
		opElmt.value=myResults[val].id;
		if(val == '0')
		opElmt.text=myResults[val].name;
		else if(val >'0')
		opElmt.text='Booth No. '+myResults[val].name;
		try
		{
		selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
		selectedElmt.add(opElmt); // IE only
		}
	}
}

 //This Method is used to build voterDetails
function getVotersInfoByBoothId()
	{
		$("#successDiv").html('');
		var id = $("#boothList").val();
		var startvalue = $("#startIndex").val();
		var endvalue = $("#endIndex").val();
		
		$("#msgDiv").html('');
		$("#voterDetailsDiv").html("");
		$('#statusMsgDiv').html('');
		$("#savediv").css("display","none");
		$("#savediv1").css("display","none");

		if(id == '0' || id == null ){
		$("#msgDiv").html('Please select Booth No');
		return false;
		}
		if(startvalue == null || startvalue == '' ){
				 if(endvalue == null || endvalue == ''){
				$("#msgDiv").html('Please Enter Data in Starting and Ending Serial No');
				return false;
				}
			$("#msgDiv").html('Please Enter Data in Starting Serial No');
			return false;
		}
		if(endvalue == null || endvalue == ''){
			$("#msgDiv").html('Please Enter Data in Ending Serial No');
			return false;
		}
		var rangeDifference = endvalue - startvalue;
				
		if(startvalue != '' && endvalue != ''){
			if(isNaN(startvalue)){
			$("#msgDiv").html('Starting SerialNo must be number only');
			return false;
			}
			if(isNaN(endvalue)){
			$("#msgDiv").html('Ending SerialNo must be number only');
			return false;
			}

			 if(startvalue >= endvalue){
				$("#msgDiv").html('Starting SerialNo must be lessthan Ending serialNo');
				return false;
			 }
		}
	var jsObj=
			{
				startIndex:startvalue,
				endIndex:endvalue,
				id:id,
				task:"getVotersDetails"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersDetailsAction.action?"+rparam;						
		callAjaxForVoterEdit(jsObj,url);
	}

function buildVotersInfoByBoothIds(myResults,jsObj)
	{
		 $('#statusMsgDiv').html('');	
		 $("#voterDetailsDiv").html("");
		 var str='';
		 if(myResults != null && myResults.voterDetails != null){
		 var result = myResults.voterDetails;

	str +='<div>';
	str+='<div id="VoterDetailsTitle" style="text-align:center;width: 665px; margin-left: 137px;"><h3 id="subHeading" >Voter Details </h3></div>';
	str+=' <table id="voterDetailsJqTable" cellpadding="0" cellspacing="0" border="0"  width="100%">';
	str+='<thead>';
		str+='<tr>';
			  str+='     <th>SNo</th>';
  			  str+='     <th>Name</th>';
			  str+='     <th>VoterIdCardNo</th>';
			  str+='     <th>Gender</th>';
			  str+='     <th>Age</th>';
			  str+='     <th>House No</th>';
			  str+='     <th>Guardian Name</th>';
			  str+='     <th>Relationship</th>';
			  str+='     <th>Serial No</th>';
			  str+='     <th>Mobile No</th>';
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
for(var i in result){
	
		str+='<tr id=\"'+i+'\" class="textClass">';
		str +='<td>'+result[i].sNo+'</td>';
		str +='<td class="textClass"><input type="text" style="width: 140px;" name="firstName" class="textClass nameClass" value="'+result[i].firstName+'"/></td>';
		str +='<td class="textClass"><input type="text" name="voterIDCardNo" class="textClass VidCardClass" value="'+result[i].voterIDCardNo+'"/></td>';
		str +='<td class="textClass"><input type="text" style="width: 25px;" maxlength="1" name="gender" class="textClass1  genderClass" value="'+result[i].gender+'"/></td>';
		str +='<td class="textClass"><input type="text" style="width: 30px;" maxlength="3" name="age" class="textClass1 ageClass numericClass" value="'+result[i].age+'"/></td>';
		str +='<td class="textClass"><input type="text" style="width: 70px;" name="houseNo" class="textClass1 houseNoClass" value="'+result[i].houseNo+'"/></td>';
		str +='<td class="textClass"><input type="text" name="relativeFirstName" class="textClass rNameClass" value="'+result[i].relativeFirstName+'"/></td>';
		str +='<td class="textClass"><select name="relationshipType" class="textClass rTypeClass"  style="height:30px; margin-top: -10px;width: 86px;">';
		if(result[i].relationshipType == "Father"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father" selected="selected"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}else if(result[i].relationshipType == "Mother"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother" selected="selected"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}else if(result[i].relationshipType == "Husband"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband" selected="selected"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}else if(result[i].relationshipType == "Spouse"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse" selected="selected"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}
		else if(result[i].relationshipType == "Son"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son" selected="selected"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}
		else if(result[i].relationshipType == "Daughter"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter" selected="selected"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}
		else if(result[i].relationshipType == "Brother"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother" selected="selected"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}
		else if(result[i].relationshipType == "Sister"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister" selected="selected"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}
		else if(result[i].relationshipType == "Child"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child" selected="selected"> Child </option>';
		str+='<option value="Other"> Other </option>';
		str+='</select></td>';
		}else if(result[i].relationshipType == "Other"){
		str+='<option value="0"> Select Relationship</option>';
		str+='<option value="Father"> Father </option>';
		str+='<option value="Mother"> Mother </option>';
		str+='<option value="Husband"> Husband </option>';
		str+='<option value="Spouse"> Spouse </option>';
		str+='<option value="Son"> Son </option>';
		str+='<option value="Daughter"> Daughter </option>';
		str+='<option value="Brother"> Brother </option>';
		str+='<option value="Sister"> Sister </option>';
		str+='<option value="Child"> Child </option>';
		str+='<option value="Other" selected="selected"> Other </option>';
		str+='</select></td>';
		}
		str +='<td class="textClass"><input type="text" name="serialNo" id=serial'+i+' class="textClass1 serialNoClass numericClass" value="'+result[i].serialNo+'"/></td>';
		str +='<td class="textClass"><input type="text" name="mobileNo" class="textClass mobileNoClass numericClass" value="'+result[i].mobileNo+'"/></td>';
		str +='<td style="padding-left: 0px;"><input type="hidden" name="voterIds" class="voterIdsClass" value="'+result[i].voterIds+'"/></td>';
		str+='</tr>';
	}
	
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	$("#voterDetailsDiv").html(str);
	$("#savediv1").css("display","block");
	$("#savediv").css("display","block");
	}
	else{
	$("#voterDetailsDiv").html('<div style="font-weight:bold;">Data Not Available</div>');
	}
}

function updateVoterDetails(){
	$("#saveAjaxImg").show();
	$('#statusMsgDiv').html('');
	var votersEditInfo = new Array();
	var flag = true;
 $('#voterDetailsJqTable tr').each(function() {
		var firstName = $(this).closest("tr").find(".nameClass").val();
		var voterIDCardNo = $(this).closest("tr").find(".VidCardClass").val();
		var gender = $(this).closest("tr").find(".genderClass").val();
		var age = $(this).closest("tr").find(".ageClass").val();
		var houseNo = $(this).closest("tr").find(".houseNoClass").val();
		var relativeFirstName = $(this).closest("tr").find(".rNameClass").val();
		var relationshipType = $(this).closest("tr").find(".rTypeClass").val();
		var serialNo = $(this).closest("tr").find(".serialNoClass").val();
		var mobileNo = $(this).closest("tr").find(".mobileNoClass").val();
		var voterIds = $(this).closest("tr").find(".voterIdsClass").val();
		
  var obj={
					 firstName:firstName,
					 voterIDCardNo:voterIDCardNo,
					 gender:gender,
					 age:age,
					 houseNo:houseNo,
					 relativeFirstName:relativeFirstName,
					 relationshipType:relationshipType,
					 serialNo:serialNo,
					 mobileNo:mobileNo,
					 voterIds:voterIds,
			   }					  
						if(typeof(obj.serialNo) != 'undefined'){
							 if(obj.firstName!=null && obj.voterIDCardNo !=null && obj.gender !=null && obj.age !=null && obj.houseNo !=null && obj.relativeFirstName !=null && obj.relationshipType !=null && obj.serialNo !=null && obj.voterIds!=null && obj.firstName != "" && obj.voterIDCardNo != "" && obj.gender != "" && obj.age  != "" && obj.houseNo != ""  && obj.relativeFirstName != ""  && obj.relationshipType  != ""  && obj.serialNo  != ""  && obj.voterIds != "" )
							{
								 if(!($.trim(mobileNo).length == 0 || ($.trim(mobileNo).length >=10 && $.trim(mobileNo).length<=12)))
									{
									$('#statusMsgDiv').html("Enter valid MobileNo").css("color","red");
									flag =false;
									return;
									}
								votersEditInfo.push(obj);
							}
							 else{
									$('#statusMsgDiv').html("Fields data can't be empty").css("color","red");
									flag = false;
							}
						}
 })

	 if(flag == false)
		return;

	 var boothId = $("#boothList").val();
	 var $current =$(".serialNoClass");
     var flag = true;
    $('input[name^="serial"]').each(function() {
        if ($(this).val() == $current.val() && $(this).attr('id') != $current.attr('id'))
        {
          flag = false;
        }
    });

    if(flag == false)
	{
	$('#statusMsgDiv').html('Please enter unique serial No');
	  	return;
	}
	if(flag == true)
	{
	$('#statusMsgDiv').html('');
  var jsObj=
		  {
			boothId:boothId,
			selectedVoters:votersEditInfo,
			task:"allVotersEditInfo"
		  }
	$("#voterBasicDetailsFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
			var uploadHandler = {
				   success : function( o ) {
					     var myResults = YAHOO.lang.JSON.parse(o.responseText);
					      if(myResults.numbers == null){
							$("#statusMsgDiv").html('Updated Successfully');
							$("#saveAjaxImg").hide();
							$("#successDiv").html('Updated Successfully').css("display","block").css("color","blue");
							return false;
						}
						if(myResults.numbers != null){
						$("#statusMsgDiv").html('Please select Unique SerialNo Ranges').css("color","red");
						return false;
						}
					}									
			};

		YAHOO.util.Connect.setForm('voterBasicDetailsForm',false);
		YAHOO.util.Connect.asyncRequest('POST','getVoterDetailsForEditAction.action',uploadHandler);
	}
}

function getPublicationDate()
	{
	var constituencyID = document.getElementById("userAccessConstituencyList");
	var name=constituencyID.options[constituencyID.selectedIndex].name;
	var value=constituencyID.options[constituencyID.selectedIndex].value;
	var choice=false;
	var locationAlertEl =  document.getElementById("locationAlertMsg");
	
	var jsObj=
	{
		selected:value,
		task:"getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	

	$('#publicationAjaxImage').css('display','block');
	callAjax(jsObj,url);
}

function buildPublicationDateList(results)
	{
	publicationDatesList=results;
	var selectedElmt=document.getElementById("publicationDateList");
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}
}