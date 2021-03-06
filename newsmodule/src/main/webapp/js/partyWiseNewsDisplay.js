	
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
			//str +='<td class="tdWidth1">District:<font id="requiredValue" class="requiredFont">*</font></td>';
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
			//str +='<td class="tdWidth1" style="padding-bottom: 15px;">Constituency:<font id="requiredValue" class="requiredFont">*</font></td>';
			str+='<td><select id="userAccessConstituencyList" class="selectWidth" name="userAccessConstituencyList" onchange="addCssStyle();" style="margin-left:20px;">';
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

								else if(jsObj.task == "getLocationsList")
								{
								  clearOptionsListForSelectElmtId(''+jsObj.divEle+'');
								  createOptionsForSelectElmtId(''+jsObj.divEle+'',myResults);	
								 
								}
								else if(jsObj.task == "getLocationNewsDetails")
								{    
									if(jsObj.scope == "District")
										divId = "userAccessDistrictList";
									 if(jsObj.scope == "Constituency")
										 divId = "userAccessConstituencyList";
									 clearOptionsListForSelectElmtId(divId);
									 createOptionsForSelectElmtId(divId,myResults);
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
		$('#errorMsgDiv').html('');
		var scope = document.getElementById('listValue').value;
	  
		if(scope == '0'){
			$('#errorMsgDiv').html('<span style="margin-left: -120px;"> Please Select Scope Value </span>');
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
			if(scopeIdVal == '0' || scopeIdVal == 0)
		    {
			 if(scope == "District")
			  $('#errorMsgDiv').html('<span style="margin-left: -100px;"> Please Select District. </span>');
			 else
			  $('#errorMsgDiv').html('<span style="margin-left: -100px;"> Please Select Constituency. </span>');

			 return;

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
				fromDate:"",
				toDate:"",
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
    
	$("#newsDispalyId").html('');
	if(myResult == null || myResult.length == 0)
	{
	 $("#newsDispalyId").html('No Data Found.');
     $("#paginationId").html('');
	 return;
	}

	
	var str='';
	str+='<ul class="unstyled">';
	for(var i in myResult){	
	maxResults = myResult[i].totalResultsCount;
	if(myResult[i].fileType == 'Party'){
		str+='<li >';
		str+='<div class="">';
		var font = myResult[i].font;
		var descFont = myResult[i].descEenadu;
		var source = null;
		if(myResult[i].source != null && !myResult[i].source == "")
		source = myResult[i].source.trim();
		if(font != null)
		{
			str+='<h4  style="" class="enadu"> <a style="cursor:pointer;color: #005580;" onclick="getNewsDetailsByContentId('+myResult[i].contentId+')">'+myResult[i].fileTitle1+'</a></h4>';
		}
		else
		{
			str+='<h4  style="text-transform: capitalize;"><a style="cursor:pointer;color: #005580;" onclick="getNewsDetailsByContentId('+myResult[i].contentId+')"> '+myResult[i].fileTitle1+'</a></h4>';
		}
		
		str+='<div class="row-fluid">';
	if(myResult[i].displayImagePath != null){
		str+='<a class="thumbnail span4" onclick="getNewsDetailsByContentId('+myResult[i].contentId+')" style="width: 146px;height:120px; cursor: pointer;">';
		str+='<img src="'+myResult[i].displayImagePath+'" style="width: 146px;height:113px;"  onerror="imgError(this)"/>';
		str+='</a>';
	}
	if(myResult[i].displayImagePath == null){
		str+='<a class="thumbnail span4" onclick="getNewsDetailsByContentId('+myResult[i].contentId+')" style="width: 146px;height:120px;  cursor: pointer;">';
		str+='<img src="images/'+myResult[i].imagePathInUpperCase+'" style="width: 146px;height:113px;"  onerror="imgError(this);"/>';
		str+='</a>';
	}
		if(descFont)
		{
			str+='<p class="span8 enadu"  style="text-align: left;">'+myResult[i].description+'</p>';
		}
		else
		{
			str+='<p class="span8"  style="text-align: left;">'+myResult[i].description+'</p>';
		}
		
		str+='</div>';
		str+='<div class="row-fluid m_top10">';
		str+='<div class="span9" style="width: 550px;">';
		str+='<table><tr>';
		if(myResult[i].source != null)
			str+='<td style="width:730px;font-weight:bold;"><p class="text-error" >Source : <span style="font-weight:normal;color:black;">'+myResult[i].source+'</span></p></td><td style="font-weight:bold;width:200px;"><p class="text-error" >Date : <span style="font-weight:normal;color:black;">'+myResult[i].fileDate+'</span></p></td>';
		else
			str+='<td style="width:260px;font-weight:bold;"></td><td style="font-weight:bold;"><p class="text-error" >Date : <span style="font-weight:normal;color:black;">'+myResult[i].fileDate+'</span></p></td>';

		if(myResult[i].responseCount > 0)
			str+='<td style="font-weight:bold;padding-left: 20px;width:200px;"><p class="text-error" ><a href="javascript:{populateNewsResponseWindow('+myResult[i].contentId+')}; "><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /><span style="font-weight:normal;color:black;">'+myResult[i].responseCount+'</span></a></p></td>';
		else
			str+='<td style="font-weight:bold;padding-left: 20px;width:200px;"></td>';
		str+='</tr>';	
		if(myResult[i].candidateName != null && myResult[i].candidateName != "")
			str+='<tr><td colspan="2" style="font-weight:bold;"><p class="text-error" >Candidate(s) involved : <span style="font-weight:normal;color:black;">'+myResult[i].candidateName+'</span></p></td></tr>';
	
	if(myResult[i].keywords != null && myResult[i].keywords != "")
		str+='<tr><td colspan="3" style="font-weight:bold;"><p class="text-error" >Keyword(s) : <span style="font-weight:normal;color:black;">'+myResult[i].keywords+'</span></p></td></tr>';
	if(myResult[i].categoryName != null && myResult[i].categoryName != "")
		str+='<tr><td colspan="3" style="font-weight:bold;"><p class="text-error" >Category(s) : <span style="font-weight:normal;color:black;">'+ myResult[i].categoryName+'</span></p></td></tr>';
	

		str+='</table></div> <br>';
		str+='<div class="span2 " style="width: 500px;">';
		str+='<a onclick="getNewsDetailsByContentId('+myResult[i].contentId+')">';
		str+='<button class="btn btn-mini pull-right btn-info" type="button">Details...</button>';
		str+='</a>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</li>';
	}
	}
	str+='</ul>';
	
	document.getElementById('newsDispalyId').innerHTML=str;
    $('html,body').animate({
        scrollTop: $("#mainDiv").offset().top},
        'slow');
	
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
    image.src = "images/no_image.gif";
    return true;
}

function getLocationWiseNewsDetails(scope,divEle)
{
	var jsObj={
		scope:scope,
		divEle:divEle,
		task:'getLocationsList'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getLocationsListAction.action?"+rparam;
	callsAjax(jsObj, url);
}

var flag1= false;
function createOptions(jsObj,myResults)
{
  var elmt = jsObj.divEle;
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
			flag1=true;
			$('#userAccessConstituencyList').val(option.value);
			$('#userAccessDistrictList').val(option.value);
		}
		else if((myResults[i].name == 'Kuppam' || myResults[i].name == 'KUPPAM') && !flag1){
			flag1=true;
			$('#userAccessConstituencyList').val(option.value);
		}
	}

}
function populateNewsResponseWindow(contentId){

var urlstr = 'showNewsResponseAction.action?responseContentId ='+contentId;
var browser1 = window.open(urlstr,"newsDetails","scrollbars=yes,height=600,width=1050,left=200,top=200");	
browser1.focus();

}

function buildNewsDisplay(resultMap){
	$("#stateAjaxCallImg").hide();
	$("#districtAjaxCallImg").hide();
var str = "";
if(resultMap != null)
	for(var i in resultMap.NewsGallary){
	if(i == 0)
	 str += "<div class='item active' style='top: 10px;'>";
	else
	 str += "<div class='item' style='top: 10px;'>";
	if(resultMap.NewsGallary[i].source == 'Eenadu Telugu' || resultMap.NewsGallary[i].eenadu)
		str += "<span class='enadu'><a style='color: #005580;font-weight: bolder;' href='javascript:{}' onclick='getNewsDetailsByContentId("+resultMap.NewsGallary[i].contentId+")'> "+resultMap.NewsGallary[i].fileTitle1+"</a></span>";
	else
		str += "<h4 style='text-transform: capitalize;'> <a style='color: #005580;font-weight: bolder;' href='javascript:{}' onclick='getNewsDetailsByContentId("+resultMap.NewsGallary[i].contentId+")'>"+resultMap.NewsGallary[i].fileTitle1+" </a></h4>";
	
	str += "<div class='row-fluid'>";
	if(resultMap.NewsGallary[i].fileType == 'Party'){
		if(resultMap.NewsGallary[i].displayImagePath != null)
			str += "<a class='thumbnail span4'  href='javascript:{}'  onclick='getNewsDetailsByContentId("+resultMap.NewsGallary[i].contentId+")'><img style='float:left;width:150px;height:110px;' src='"+resultMap.NewsGallary[i].displayImagePath+"' onerror='imgError(this);' /></a>";
		if(resultMap.NewsGallary[i].displayImagePath == null)
			str += "<a class='thumbnail span4'  href='javascript:{}' onclick='getNewsDetailsByContentId("+resultMap.NewsGallary[i].contentId+")'> <img style='float:left;width:150px;height:110px;' src='./images/party_flags/"+resultMap.NewsGallary[i].imagePathInUpperCase+"' onerror='imgError(this);'/></a>";
		
		if(resultMap.NewsGallary[i].descEenadu)
			str += "<p class='span8 enadu'>"+resultMap.NewsGallary[i].description+"</p>";
		else
			str += "<p class='span8'>"+resultMap.NewsGallary[i].description+"</p>";
	str += "</div>";
	
	str += "<div class='row-fluid m_top10'>";
	str += "<div class='span9' style='width:460px;'>";
	str += "<table><tr><td style='width:200px;font-weight:bold;'><p class='text-error' >Source : <span style='font-weight:normal;color:black;'> "+resultMap.NewsGallary[i].source+"</span></p></td><td style='font-weight:bold;'><p class='text-error' >Date : <span style='font-weight:normal;color:black;'>"+resultMap.NewsGallary[i].fileDate+"</span></p></td>";
	if(resultMap.NewsGallary[i].responseCount >0)
		str += "<td style='font-weight:bold;padding-left: 20px;'><p class='text-error' >Response Count : <span style='font-weight:normal;color:black;'>"+resultMap.NewsGallary[i].responseCount +"</span></p></td>";									
	str += "</tr>";
	if(resultMap.NewsGallary[i].candidateName != null && resultMap.NewsGallary[i].candidateName !='')
		str += "<tr><td colspan='2'  style='font-weight:bold;'><p class='text-error' >Candidate(s) Involved : <span style='font-weight:normal;color:black;'>"+resultMap.NewsGallary[i].candidateName +"</span></p></td></tr>";
	str += "</table>";
	str += "</div></br>";
	str += "<div class='span2 ' style='float:right'><br>";
	str += "<a href='newsPaginationAction.action?level=state'>";
	str += "<button class='btn btn-mini pull-right' type='button'  style='margin-top: -20px; margin-bottom: 15px;margin-right: 25px;'>More...</button>";
	str += "</a>";
	str += "</div>";
	
	str += "</div>";
	}
	str += "</div>";
	}
	
$("#newsDiv").html(str);
$('#myCarousel').carousel( {interval: 5000});
//For district level
var str = "";
if(resultMap != null)
	for(var i in resultMap.NewsGallaryForDist){
	if(i == 0)
	str += "<div class='item active' style='top: 10px;'>";
	else
	str += "<div class='item' style='top: 10px;'>";
	if(resultMap.NewsGallaryForDist[i].source == 'Eenadu Telugu' || resultMap.NewsGallaryForDist[i].eenadu)
		str += "<span class='enadu'><a style='color: #005580;font-weight: bolder;' href='javascript:{}' onclick='getNewsDetailsByContentId("+resultMap.NewsGallaryForDist[i].contentId+")'> "+resultMap.NewsGallaryForDist[i].fileTitle1+"</a></span>";
	else
		str += "<h4 style='text-transform: capitalize;'> <a style='color: #005580;font-weight: bolder;' href='javascript:{}' onclick='getNewsDetailsByContentId("+resultMap.NewsGallaryForDist[i].contentId+")'>"+resultMap.NewsGallaryForDist[i].fileTitle1+" </a></h4>";
	
	str += "<div class='row-fluid'>";
	if(resultMap.NewsGallaryForDist[i].fileType == 'Party'){
		if(resultMap.NewsGallaryForDist[i].displayImagePath != null)
			str += "<a class='thumbnail span4'  href='javascript:{}'  onclick='getNewsDetailsByContentId("+resultMap.NewsGallaryForDist[i].contentId+")'><img style='float:left;width:150px;height:110px;' src='"+resultMap.NewsGallaryForDist[i].displayImagePath+"' onerror='imgError(this);' /></a>";
		if(resultMap.NewsGallaryForDist[i].displayImagePath == null)
			str += "<a class='thumbnail span4'  href='javascript:{}' onclick='getNewsDetailsByContentId("+resultMap.NewsGallaryForDist[i].contentId+")'> <img style='float:left;width:150px;height:110px;' src='./images/party_flags/"+resultMap.NewsGallaryForDist[i].imagePathInUpperCase+"' onerror='imgError(this);'/></a>";
		
		if(resultMap.NewsGallaryForDist[i].descEenadu)
			str += "<p class='span8 enadu'>"+resultMap.NewsGallaryForDist[i].description+"</p>";
		else
			str += "<p class='span8'>"+resultMap.NewsGallaryForDist[i].description+"</p>";
	str += "</div>";
	
	str += "<div class='row-fluid m_top10'>";
	str += "<div class='span9' style='width:460px;'>";
	str += "<table><tr><td style='width:200px;font-weight:bold;'><p class='text-error' >Source : <span style='font-weight:normal;color:black;'> "+resultMap.NewsGallaryForDist[i].source+"</span></p></td><td style='font-weight:bold;'><p class='text-error' >Date : <span style='font-weight:normal;color:black;'>"+resultMap.NewsGallaryForDist[i].fileDate+"</span></p></td>";
	if(resultMap.NewsGallaryForDist[i].responseCount >0)
		str += "<td style='font-weight:bold;padding-left: 20px;'><p class='text-error' >Response Count : <span style='font-weight:normal;color:black;'>"+resultMap.NewsGallaryForDist[i].responseCount +"</span></p></td>";									
	str += "</tr>";
	if(resultMap.NewsGallaryForDist[i].candidateName != null && resultMap.NewsGallaryForDist[i].candidateName !='')
		str += "<tr><td colspan='2'  style='font-weight:bold;'><p class='text-error' >Candidate(s) Involved : <span style='font-weight:normal;color:black;'>"+resultMap.NewsGallaryForDist[i].candidateName +"</span></p></td></tr>";
	str += "</table>";
	str += "</div></br>";
	str += "<div class='span2 ' style='float:right'><br>";
	str += "<a href='newsPaginationAction.action?level=district'>";
	str += "<button class='btn btn-mini pull-right' type='button'  style='margin-top: -20px; margin-bottom: 15px;margin-right: 25px;'>More...</button>";
	str += "</a>";
	str += "</div>";
	
	str += "</div>";
	}
	str += "</div>";
	}

$("#newsDivForDistrict").html(str);
$('#myCarousel2').carousel( {interval: 5000});

}

function buildCategoryNews(results){
var	str = "";
if(results != null)
	for(var i in results){
		str += "<li><a href='javascript:{showTopFiveGallaries("+results[i].id+")}' class='muted'><i class='icon-share-alt'></i>"+results[i].name+"</a></li>  ";
	}

$(".categoryNews").html(str);
}

function buildLatestNewsDiv(results){
var str = "";
if(results != null)
	for(var i in results){
	str += "<li>";
	if(results[i].source == 'Eenadu Telugu')
		str += "<a href='javascript:{}' onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='enadu muted' style='font-weight:bold;'><i class='icon-share-alt '></i>"+results[i].fileTitle1+"</a>";
	else
		str += "<a href='javascript:{}' onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='muted' style='text-transform: capitalize;'><i class='icon-share-alt'></i>"+results[i].fileTitle1+"</a>";
	str += "</li>";
	}
$(".latestNews").html(str);
}

function buildMainCategories(results){
var str = "";
if(results != null)
	for(var i in results){
		str += "<li><a class='muted' href='javascript:{showFilesInGallary("+results[i].id+")}'><i class='icon-share-alt'></i>"+results[i].name+"</a></li> ";
	}
$(".mainCategoriesList").html(str);
}

function buildResponseFileNews(results){
var str = "";
if(results != null)
	for(var i in results){
	
		str += "<li class='thumbnail' style='margin: 3px 5px 5px;padding:5px 5px 5px 15px;'>";
		if(results[i].fontId == 1)
			str += "<span class='text-info enadu' onclick='getNewsDetailsByContentId("+results[i].fileId+")' style='cursor:pointer;font-weight:bold;'> "+results[i].title+"</span>";
		else
			str += "<h6 class='text-info' onclick='getNewsDetailsByContentId("+results[i].fileId+")' style='cursor:pointer; text-transform: capitalize;'> "+results[i].title+"</h6>";

		str += "<span style='color:#ccc;'>";
		if(results[i].fileDate != ''){
			str += "<div><i class='smal' style='color:#3A87AD;'>Date: "+results[i].fileDate+"</i></div>";
		}
		str += "</span>";
		str += "</li>";
		str += "<a href='javascript:{getResponseDetailsByContentId("+results[i].fileId+");}'  class='btn btn-mini btn-primary' style='margin-left:154px;background: none repeat scroll 0% 0% #FAA938;font-weight:bold;margin-top: -2px;'>Track</a>";
		
	}
$(".responseFileNews").html(str);
}