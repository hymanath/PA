	
	var scope = $('#listValue').val();
	var errDivValue = '';
	
	function populateLocations(val)
	{
	$('#tableRowC').remove();
	$('#tableRowD').remove();
	$('#tableRowM').remove();
	$('#tableRowP').remove();
	$('#tableRowB').remove();
	$('#headingDiiv').html('');
	clearErrDiv();
	var scope = $('#listValue').val();
	$('#listValue').css("border","1px solid #F3E81E");
	if(scope == '0'){
		$('#headingDiiv').html('View Location Wise News');
			$('#showScopeSubsD').css("display","none");
			$('#showScopeSubsC').css("display","none");
			$('#listValue').css("border","1px solid IndianRed");
			return false;
	}	
	var value = val;
	if(value == 'District')
		{	
			$('#headingDiiv').html('View District Wise News');
			$('#showScopeSubsC').css("display","none");
			var str =''; 
			str +='<table style="margin-top:5px">';
			str +='<tr id="tableRowD">';
			str +='<td class="tdWidth1">District:<font id="requiredValue" class="requiredFont">*</font></td>';
			//str+='<td><select id="userAccessDistrictList" class="selectWidth" name="userAccessDistrictList" onchange="getAllConstituenciesInStateByType(2,1,this.options[this.selectedIndex].value);">';
			str+='<td><select id="userAccessDistrictList" class="selectWidth" name="userAccessDistrictList" onchange="addCssStyle();">';
			str+='</select></td>';	 
			str +='</tr>';
			str +='</table>';
			document.getElementById("showScopeSubsD").innerHTML = str;
			$('#showScopeSubsD').css("display","block");
			getAllConstituenciesInDistrictByType(1);
		}
		if(value=="Constituency" || value=="Mandal" || value=="Panchayat" || value=="Booth")
		{	
			$('#headingDiiv').html('View Constituency Wise News');
			$('#showScopeSubsD').css("display","none");
			var str =''; 
			str +='<table style="margin-top:5px">';
			str +='<tr id="tableRowC">';
			str +='<td class="tdWidth1" style="padding-bottom: 15px;">Constituency:<font id="requiredValue" class="requiredFont">*</font></td>';
			str+='<td><select id="userAccessConstituencyList" class="selectWidth" name="userAccessConstituencyList" onchange="addCssStyle();" >';
			//onchange="getMandalList(this.options[this.selectedIndex].value);">';
			str+='</select></td>';	 
			str +='</tr>';
			str +='</table>';
			document.getElementById("showScopeSubsC").innerHTML = str;
			$('#showScopeSubsC').css("display","block");
			getAllConstituenciesInStateByType(2, 1, 'constituency');
		}
		if(value=="Mandal" || value=="Panchayat" || value=="Booth")
		{
			var str ='';
			str +='<table>';	  
			str +='<tr id="tableRowM">';
			str +='<td class="tdWidth1">Mandal:<font id="requiredValue" class="requiredFont">*</font></td>';
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
			str +='<td class="tdWidth1" >Panchayat:<font id="requiredValue" class="requiredFont">*</font></td>';
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
		addCssStyle();
		
		var choice=false;
		if(value == 0)
		{
			$('#mandalList').css("border","1px solid #F3E81E");
			$('#panchayatList').css("border","1px solid #F3E81E");
			$('#boothList').css("border","1px solid #F3E81E");
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
		callsAjax(jsObj,url);
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
		
		addCssStyle();
		if(value1 == 0)
		{
			$('#panchayatList').css("border","1px solid #F3E81E");
			$('#boothList').css("border","1px solid #F3E81E");
			$('#errorMsgDiv').html('Please Select Mandal or Municipality or Corporation');
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
			callsAjax(jsObj,url);
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
			callsAjax(jsObj,url);
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
					 callsAjax(jsObj,url);
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
	str +='<td class="tdWidth1">Booth:<font id="requiredValue" class="requiredFont">*</font></td>';
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
	
	function callsAjax(jsObj,url)
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
								else if(jsObj.task == "getPanchayat")
								{  
										$("#wardInfoAjaxImg").hide();
										buildPanchayatList(myResults,jsObj);							
								}
								else if(jsObj.task == "getWards")
								{     
										$("#wardInfoAjaxImg").hide();
										buildWardsList(myResults,jsObj);								
								}								
								else if(jsObj.task == "getBooths")
								{  										
										buildBoothsList(myResults,jsObj);								
								}
								else if(jsObj.task == "getPartyWiseNewsToDisplay")
								{	
									$('#imageForMail').css("display","none");
									showTotalNews(myResults,jsObj);
								}
								else if(jsObj.task == "getConstituencies")
								{
								createOptionsForSelectElmtId('userAccessConstituencyList',myResults);
								}
								else if(jsObj.task == "getDistrictsForState")
								{
								createOptionsForSelectElmtId('userAccessDistrictList',myResults);
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
			$('#listValue').css("border","1px solid #F3E81E");
			$('#userAccessConstituencyList').css("border","1px solid #F3E81E");
			$('#userAccessDistrictList').css("border","1px solid #F3E81E");
			
			var cValue = $("#userAccessConstituencyList").val();
			var dValue = $("#userAccessDistrictList").val();
			
			if(cValue == '0'){			
			$('#userAccessConstituencyList').css("border","1px solid IndianRed");
			}						
			if(dValue == '0'){	
			$('#userAccessDistrictList').css("border","1px solid IndianRed");
			}			
	}
	
	var locationName = '';
	var scopeIdVal;
	var PartyName = 'TDP';
	var partyId = 872;
	function handleSubmit()
	{		
		var scope = document.getElementById('listValue').value;
	
		if(scope == '0'){
			$('#errorMsgDiv').html('<span style="margin-left: -165px;"> Please Select Scope Value </span>');
			$('#listValue').css("border","1px solid IndianRed");
			return false;
		}
		/*var mandalValues = document.getElementById("mandalList").value;
		var panchayatValues = document.getElementById("panchayatList").value;
		var boothValues = document.getElementById("boothList").value;
		
		if(scopeIdVal == '0' || mandalValues == '0' || panchayatValues == '0'  || boothValues == '0'){
			$('#errorMsgDiv').html('<span style="margin-left: -60px;"> Please Select All Scope Related Elements </span>');				
			addCssStyle();
			return false;
		}	
				
		if( scope=="Booth" && errDivValue !='' ){
		$('#errorMsgDiv').html('<span style="font-size: 15px; margin-left: 200px;"> Booths are not Available</span>');
		return false;
		}
					
			var value = $('input:radio[name=voterType]:checked').val();			
			if(scope == "Constituency"){
				scopeIdVal = document.getElementById('listValue').value;
				locationName = $('#userAccessConstituencyList option:selected').text()+' Constituency';
			}
			if(scope == "Mandal"){
				scopeIdVal = $("#mandalList").val();
				locationName = $('#mandalList option:selected').text();
			}
			if(scope == "Panchayat"){
				scopeIdVal = $("#panchayatList").val();
				locationName = $('#panchayatList option:selected').text()+' Panchayat';
			}
			if(scope == "Booth"){
				scopeIdVal = $("#boothList").val();
				locationName = $('#boothList option:selected').text();
			}*/
			if(scope == "District"){
				scopeIdVal = document.getElementById("userAccessDistrictList").value;	
				locationName = $('#userAccessDistrictList option:selected').text();
			}
			if(scope == "Constituency"){
				scopeIdVal = document.getElementById("userAccessConstituencyList").value;	
				locationName = $('#userAccessConstituencyList option:selected').text();
			}
			if(scopeIdVal != '0'){			
			var urlStr="partyWiseNewsPopupAction.action?scope="+scope+"&locationName="+locationName+"&locationValue="+scopeIdVal+"&partyName="+PartyName+"&partyId="+partyId;			
			var updatedBrowser = window.open(urlStr,'_blank');	
			updatedBrowser.focus();
			}
	}

function getNewsForPagination(num)
 {
	$('#imageForMail').css("display","block");
	var queryType='Public';
        var jsObj =
		    {   
				locationType:scope,
				locationId:locationValue,
				partyId:partyId,
				startRecord:num,
			    maxRecord:10,
				queryType:queryType,
				task:"getPartyWiseNewsToDisplay"
		    };
	 
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyWiseNewsDetailsForALocation.action?"+rparam;						
	callsAjax(jsObj,url);  
 }
 function getAllConstituenciesInStateByType(electionType, stateId, element)
	{
		var jsObj=
		{				
				electionTypeId: electionType,
				stateId: stateId,
				task: "getConstituencies",
				elmtId: element 	
		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllConstituenciesInState.action?"+rparam;						
	callsAjax(jsObj,url);
	}
	
	var flag =false;
	function createOptionsForSelectElmtId(divId,myResults)
{	
	var elmt = document.getElementById(divId);
	if( !elmt || myResults == null)
		return;
	
	for(var i in myResults)
	{
		var option = document.createElement('option');
		option.value=myResults[i].id;
		option.text=myResults[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
		if(myResults[i].name == locationName ){
			flag=true;
			$('#userAccessConstituencyList').val(option.value);
			$('#userAccessDistrictList').val(option.value);
		}
		else if(myResults[i].name == 'Kuppam' && !flag){
			flag=true;
			$('#userAccessConstituencyList').val(option.value);
		}
	}
}
var maxResults;
function showTotalNews(myResult,jsObj){	
	var str='';
	str+='<ul class="unstyled">';
	for(var i in myResult){	
	maxResults = myResult[i].totalResultsCount;
	if(myResult[i].fileType == 'Party'){
		str+='<li >';
		str+='<div class="">';
		var source = myResult[i].source.trim();
		if(source == "Eenadu Telugu")
		{
			str+='<h4  style="text-transform: capitalize;" class="enadu"> '+myResult[i].fileTitle1+'</h4>';
		}
		else
		{
			str+='<h4  style="text-transform: capitalize;"> '+myResult[i].fileTitle1+'</h4>';
		}
		
		str+='<div class="row-fluid">';
	if(myResult[i].displayImagePath != null){
		str+='<a class="thumbnail span4" onclick="getNewsDetailsByContentId('+myResult[i].contentId+')" style="width: 146px;height:120px; cursor: pointer;">';
		str+='<img src="'+myResult[i].displayImagePath+'" style="width:100%" alt="'+myResult[i].fileTitle1+'" onerror="imgError(this)"/>';
		str+='</a>';
	}
	if(myResult[i].displayImagePath == null){
		str+='<a class="thumbnail span4" onclick="getNewsDetailsByContentId('+myResult[i].contentId+')" style="width: 146px;height:120px;  cursor: pointer;">';
		str+='<img src="images/'+myResult[i].imagePathInUpperCase+'" style="width:100%" alt="'+myResult[i].fileTitle1+'" onerror="imgError(this);"/>';
		str+='</a>';
	}
		if(source == "Eenadu Telugu")
		{
			str+='<p class="span8 enadu"  style="text-align: left;">'+myResult[i].description+'</p>';
		}
		else
		{
			str+='<p class="span8"  style="text-align: left;">'+myResult[i].description+'</p>';
		}
		
		str+='</div>';
		str+='<div class="row-fluid m_top10">';
		str+='<div class="span9">';
		str+='<table><tr><td style="width:250px;font-weight:bold;"><p class="text-error" style="float: left; position: absolute;">Source : '+myResult[i].source+'</p></td><td style="width:250px;font-weight:bold;"><p class="text-error" style="float: right; position: absolute;">Date : '+myResult[i].fileDate+'</p></td></tr></table>';
		str+='</div>';
		str+='<div class="span2 ">';
		str+='<a onclick="getNewsDetailsByContentId('+myResult[i].contentId+')">';
		str+='<button class="btn btn-mini pull-right btn-info" type="button">Read Now...</button>';
		str+='</a>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</li>';
	}
	}
	str+='</ul>';
	
	document.getElementById('newsDispalyId').innerHTML=str;
	
	//var maxResults=jsObj.maxRecord;
	//var itemsCount=results[0].count;
		if(jsObj.startRecord==0){
			$("#paginationId").pagination({
				items: maxResults,
				itemsOnPage: 10,
				cssStyle: 'light-theme'
			});
		}
}

function getAllConstituenciesInDistrictByType(stateId){

	var jsObj=
		{				
			stateId: 1,
			task: "getDistrictsForState",	
		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDistrictsForAStateAjaxAction.action?"+rparam;						
	callsAjax(jsObj,url);
}

function imgError(image) {
    image.onerror = "";
    image.src = "images/TDP.PNG";
    return true;
}