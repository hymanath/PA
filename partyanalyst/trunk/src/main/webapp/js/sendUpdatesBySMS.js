	
	var CLICKTYPE="Sms";
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
			$('#errorMsgDiv').html('Please Select Scope Value');
			$('#listValue').css("border","1px solid IndianRed");
			return false;
	}	
	var value = val;
		if(value=="Constituency" || value=="Mandal" || value=="Panchayat" || value=="Booth")
		{	
			var str ='';
			str +='<table>';
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
		$('#tableRowB').remove();
		addCssStyle();
		$('#errorMsgDiv').html('&nbsp;');		
		var selectedElmt=document.getElementById("panchayatList");
		removeSelectElements(selectedElmt);
		var constituencyID = document.getElementById("userAccessConstituencyList");
		var name=constituencyID.options[constituencyID.selectedIndex].name;
		var value=constituencyID.options[constituencyID.selectedIndex].value;
		var choice=false;
		var locationAlertEl =  document.getElementById("locationAlertMsg");
		if(value == 0)
		{
		$('#errorMsgDiv').html('Please Select Constituency');
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
		addCssStyle();	
		$('#errorMsgDiv').html('&nbsp;');		
		var mandalId=document.getElementById("mandalList");
		var name=mandalId.options[mandalId.selectedIndex].name;
		var value1=mandalId.options[mandalId.selectedIndex].value;	
		if(value1 == 0)
		{
		$('#errorMsgDiv').html('Please Select Mandal or Municipal Corporation');
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
	var errDivValue = '';
	function buildBoothsList(results,jsObj)
	{
	$('#successMsgDiv').html('&nbsp;');
	$('#sendButton').show();
	if(results.length == '0' || results.length == '1'){
	errDivValue = 'Booths are not Available';
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
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
	var flag = false;
	function handleSubmit()
	{		
		var scope = $('#listValue').val();
		if(scope == '0'){
			$('#errorMsgDiv').html('Please Select Scope Value');
			$('#listValue').css("border","1px solid IndianRed");
			return false;
		}
		var scopeIdVal=document.getElementById("userAccessConstituencyList").value;	
		var mandalValues = $('#mandalList').val();
		var panchayatValues = $('#panchayatList').val();
		var boothValues = $('#boothList').val();

		if(scopeIdVal == '0' || mandalValues == '0' || panchayatValues == '0'  || boothValues == '0'){
			$('#errorMsgDiv').html('Please Select All Scope Related Elements');				
			addCssStyle();
			return false;
		}	
		
		var smsContent = trim($('#smstxt').val());
		if(errDivValue !='' ){
		$('#errorMsgDiv').html('Booths are not Available, So You are not allow to send SMS, Based on Booth Wise');
		return false;
		}
		if(smsContent.length == '0'){
		$('#smstxt').css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Message should not be Empty');
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
										buildMandalList(myResults,jsObj);							
								}
								if(jsObj.task == "getPanchayat")
								{     
										buildPanchayatList(myResults,jsObj);							
								}
								if(jsObj.task == "getWards")
								{     
										buildWardsList(myResults,jsObj);								
								}
								if(jsObj.task == "getBooths")
								{  										
										buildBoothsList(myResults,jsObj);								
								}
								if(jsObj.task == "sendSMSForCadreIds")
								{ 
									flag = false;
									$('#imageForMail').hide();
									if(!($("#cadreIds").is(":checked")))
										$('input:radio[name=voterType]')[0].checked = true;
									$('#successMsgDiv').html('Messages Sent Successfully');	
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
				$('#panchayatList').css("border","1px solid IndianRed");
		}
		if(panchayatValues == '0'){
				$('#panchayatList').css("border","1px solid IndianRed");
		}
		if(boothValues == '0'){
				$('#errorMsgDiv').html('Please Select Booth');
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