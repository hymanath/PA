	var selectedState;
	var selectedDistrict;
	var selectedConstituency;
	var locationScope;
	var selectedUsersArray;
	function selectMe(){
		var elmts = document.getElementById('selectall');
			if(elmts.checked == true)
					selectAllPeople();
			else if(elmts.checked == false)
				DeSelectAllPeople();
	}
	function selectAllPeople()
	{
			var elmts = document.getElementsByTagName('input');			
			if(elmts.length == 0)
				return;
			for(var i=0; i<elmts.length; i++)
			{
				if(elmts[i].type == "checkbox" && !elmts[i].checked)
					elmts[i].checked = true;
			}
	}
	function DeSelectAllPeople()
	{
			var elmts = document.getElementsByTagName('input');
			if(elmts.length == 0)
				return;
			for(var i=0; i<elmts.length; i++)
			{
				if(elmts[i].type == "checkbox" && elmts[i].checked)
					elmts[i].checked = false;
			}
	}
	function sendEmailsForSelectedUser()
	{	
	var selectedElmts = [];
		var elmts = document.getElementsByName("userCheckBox1");	
			for(var i=0; i<elmts.length; i++)
			{
				if(elmts[i].checked == true)
					selectedElmts.push(elmts[i].value);
			}
			var errorElmt = document.getElementById("errorDiv1");
			if(selectedElmts.length == '0')
				return errorElmt.innerHTML = 'Atleast one checkbox has to be selected to send email';
				sendEmail(selectedElmts);
	}
	function sendEmail(selectedElmts)
	{	
		var textAreaElmt;
		var str ='';
		for(var i in selectedElmts){
            str += selectedElmts[i]+",";
		}
		var txtElmtValue;
		var subject=$('#subject').val();
		textAreaElmt=$('#descriptionForEmail').val();
		if(textAreaElmt){
			// txtElmtValue =  textAreaElmt;
			 txtElmtValue = removeAllUnwantedCharacters(textAreaElmt);
		}
		var errorElmt = document.getElementById("errorDiv1");
		if(subject.length != '0')
			subject.innerHTML = '';
		if(subject.length == '0')
			return errorElmt.innerHTML = 'Subject should not be empty';
		if(textAreaElmt.length != '0')
			errorElmt.innerHTML = '';
		if(textAreaElmt.length == '0')
				return errorElmt.innerHTML = 'Message should not be empty';
         
		 $("#descId").val(txtElmtValue);
		 $("#userIds").val(str);
		 

		var sendEmailHandler = {
				success: function(o) {
					var myResults = YAHOO.lang.JSON.parse(o.responseText);
					if(myResults == "success"){
					$("#errorDiv1").html('');
					$('#imageForMail').hide();
					$('#errorDiv1').html('<font style="color:blue;">Mail Sent Successfully</font>');
					$('#subject').val('');
					$('#descriptionForEmail').val("");
					var editor = $("#descriptionForEmail").cleditor()[0];
					editor.updateFrame();
				}
			}
      };
	YAHOO.util.Connect.setForm('sendUpdateEmailform',false);
	YAHOO.util.Connect.asyncRequest('POST','sendEmailForSelectedUsersAction.action',sendEmailHandler);
	$('#imageForMail').show();
	}
	/*function sendEmail(selectedElmts)
	{	
		var textAreaElmt;
		var str ='';
		var txtElmtValue;
		var subject=$('#subject').val();
		 textAreaElmt=$('#descriptionForEmail').val();
		if(textAreaElmt){
			// txtElmtValue =  textAreaElmt;
			 txtElmtValue = removeAllUnwantedCharacters(textAreaElmt);
		}
		var errorElmt = document.getElementById("errorDiv1");
		if(subject.length != '0')
			subject.innerHTML = '';
		if(subject.length == '0')
			return errorElmt.innerHTML = 'Subject should not be empty';
		if(textAreaElmt.length != '0')
			errorElmt.innerHTML = '';
		if(textAreaElmt.length == '0')
				return errorElmt.innerHTML = 'Message should not be empty';
		
		var jsObj=
			{						
					txtAreaValue:txtElmtValue,
					selectedElmts:selectedElmts,
					subject:subject,
					task:"sendEmailsForUserIds"
			}	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "sendEmailForSelectedUsersAction.action?"+rparam;						
		callAjaxForEmail(jsObj,url);
		$('#imageForMail').show();
	}*/
function showPreview()
	{
	$('#previewDiv').html($('#descriptionForEmail').val());
	}
function sendEmailsToUsersTable()
	{
    var resultsColumnDefs = [
		{
		key : "checkBox",
		label : "<input type='checkbox'  id='selectall' onclick='selectMe()'> Select All"
	}, 
	{
		key : "userName",
		label : "Username",
		sortable : true
	},
	{
		key : "email",
		label : "E-mail",
		sortable : true
	},
	{
		key : "state",
		label : "State",
		sortable : true
	},
	{
		key : "district",
		label : "District",
		sortable : true
	},
	{
		key : "constituency",
		label : "Constituency",
		sortable : true
	}
	];
	 var myConfigs = {  
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [10,20,30,50], 
						pageLinks: 10
						})						
					};	
					var myDataSource = new YAHOO.util.DataSource(dataArr);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "checkBox","userName" , "email" ,"state","district",  "constituency"]
					};				
	var myDataTable = new YAHOO.widget.DataTable("table",resultsColumnDefs, myDataSource,myConfigs);
	//to update selectall checkbox for each page.
	setTimeout(function(){
		$(".yui-pg-pages").each(function(){
		    $(this).find(".yui-pg-page").live("click",function(){
			  if(!$(this).hasClass("yui-pg-current-page"))
			  $(".yui-dt-label").find("#selectall").attr("checked",false);
			}); 
		});
	},1000);
}
function callAjaxForEmail(jsObj,url)
{
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);
							if(jsObj.task == 'getStatesAjaxAction')
							{
								iterateStateNames(myResults);
							}	
							else if(jsObj.task == 'getDistrictNames')
							{
								iterateDistrictNames(myResults);
							}									
							else if(jsObj.task == 'getConstituencyNames')
							{
								iterateConstituencyNames(myResults);
							}
							else if(jsObj.task == 'getUsersForSendingEmails')
							{			
								$("#commentsData_outer").show();
								printUserDetatils(myResults);							
							}	
							/*else if(jsObj.task == 'sendEmailsForUserIds'){
								if(myResults=="success"){
								$("#errorDiv1").html('');
								$('#imageForMail').hide();
								$('#errorDiv1').html('<font style="color:blue;">Mail Sent Successfully</font>');
								$('#subject').val('');
								$('#descriptionForEmail').val("");
								var editor = $("#descriptionForEmail").cleditor()[0];
								editor.updateFrame();

								}
							}*/		
						}
						catch(e)
						{   
							//alert("Invalid JSON result" + e);  
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };
	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
function  getDetailsForStates(){
	/* var jsObj=
		{		
				task:"getStateNames"
		}	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "sendUpdatesByemailsAction.action?"+rparam;	 */	
var jsObj=
		{		
				electionType :'Assembly',		
				task:"getStatesAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getStatesAjaxAction.action?"+rparam;		
		callAjaxForEmail(jsObj,url);			
}
function  getDetailsForallDistricts(stateId){
var jsObj=
		{						
				stateId:stateId,
				task:"getDistrictNames"
		}	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendUpdatesByemailsAction.action?"+rparam;						
	callAjaxForEmail(jsObj,url);
	clearSelectBoxes('constituency');
}
function  getDetailsForallConstituencies(districtId){
var jsObj=
		{						
				districtId:districtId,
				task:"getConstituencyNames"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendUpdatesByemailsAction.action?"+rparam;						
	callAjaxForEmail(jsObj,url);
}
function iterateConstituencyNames(result)
{
	var elmt = document.getElementById('constituency');
	var option = document.createElement('option');
	clearOptionsListForSelectElmtId('constituency');
	option.value="0";
	option.text="Select Constituency";
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in result)
	{		
		var option=document.createElement('option');		
		option.value=result[i].id;
		option.text=result[i].name;		
		try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
}
function iterateDistrictNames(result)
{
	var elmt = document.getElementById('district');
	var option = document.createElement('option');
	clearOptionsListForSelectElmtId('district');
	option.value="0";
	option.text="Select District";
	try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in result)
	{		
		var option=document.createElement('option');		
		option.value=result[i].id;
		option.text=result[i].name;
	try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
}
function iterateStateNames(result)
{
	var elmt = document.getElementById('state');
	var option = document.createElement('option');
	clearOptionsListForSelectElmtId('state');
	option.value="0";
	option.text="Select State";
		try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in result)
	{		
		var option=document.createElement('option');		
		option.value=result[i].id;
		option.text=result[i].name;		
		try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
}
 function clearOptionsListForSelectElmtId(elmtId)
{
	var elmt = document.getElementById(elmtId);
	if(!elmt)
		return;	
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}
//For getting Users based on radiobutton select		
		function getUsers(){
			var userType;
			var allUsers = document.getElementById('All');
			var customerRadio =document.getElementById('Customers');
			var freeUsersRadio =document.getElementById('FreeUsers');
			var locationScope1=document.getElementById('locationScope');
			var selectedState1 =document.getElementById('state');
			var selectedDistrict1 = document.getElementById('district');
			var selectedConstituency1 = document.getElementById('constituency');
			if(allUsers.checked){
				userType='0';
				locationScope=locationScope1.value;
				if(locationScope=='1'){
					selectedState='0';
					selectedDistrict='0';
					selectedConstituency='0';
				}
				if(locationScope=='2'){	
					selectedState=selectedState1.value;
					$('#errorDiv').html('');
					if(selectedState=='0'){
					$('#errorDiv').html('Please Select  State');
					return false;
					}
					selectedDistrict='0';
					selectedConstituency='0';
				}
				if(locationScope=='3'){
					selectedState=selectedState1.value;
					selectedDistrict=selectedDistrict1.value;
					$('#errorDiv').html('');
					if(selectedState=='0' || selectedDistrict=='0'){
						$('#errorDiv').html('Please Select  State and District');	
						return false;
						}
						selectedConstituency='0';
				}
				if(locationScope=='4'){
					$('#errorDiv').html('');							
					selectedState=selectedState1.value;					
					selectedDistrict=selectedDistrict1.value;					
					selectedConstituency=selectedConstituency1.value;					
					if(selectedState =='0' || selectedDistrict == '0' || selectedConstituency == '0'){
						$('#errorDiv').html('Please Select  State , District and Constituency');
					return false;
						}
				}
			}
			else if(customerRadio.checked){
				userType='1';
				locationScope=locationScope1.value;
						$('#errorDiv').html();
					if(locationScope=='1'){
					selectedState='0';
					selectedDistrict='0';
					selectedConstituency='0';
					}
				if(locationScope=='2'){
					$('#errorDiv').html('');
							
					selectedState=selectedState1.value;
					selectedDistrict='0';
					selectedConstituency='0';
					if(selectedState=='0'){
					$('#errorDiv').html('Please Select  State');
					return false;
					}
				}
				if(locationScope=='3'){
					selectedState=selectedState1.value;
					selectedDistrict=selectedDistrict1.value;
					selectedConstituency='0';
					if(selectedState=='0' || selectedDistrict=='0'){
						$('#errorDiv').html('Please Select  State and District');							
					return false;
						}
						$('#errorDiv').html('');
				}
				if(locationScope=='4'){							
					selectedState=selectedState1.value;
					selectedDistrict=selectedDistrict1.value;
					selectedConstituency=selectedConstituency1.value;
					if(selectedState=='0' || selectedDistrict=='0' || selectedConstituency=='0'){
					$('#errorDiv').html('Please Select  State , District and Constituency');								
					return false;
						}
						$('#errorDiv').html('');
				}
			}
			else if(freeUsersRadio.checked){
				userType='2';
				locationScope=locationScope1.value;				
				if(locationScope=='1'){							
					selectedState='0';
					selectedDistrict='0';
					selectedConstituency='0';
				}
				if(locationScope=='2'){						
					selectedState=selectedState1.value;
					if(selectedState=='0'){
						$('#errorDiv').html('Please Select  State ');								
					return false;
						}
						$('#errorDiv').html('');
					selectedDistrict='0';
					selectedConstituency='0';
				}
				if(locationScope=='3'){							
					selectedState=selectedState1.value;
					selectedDistrict=selectedDistrict1.value;
					if(selectedState=='0' || selectedDistrict=='0'){
						$('#errorDiv').html('Please Select  State and District');								
					return false;
						}
						$('#errorDiv').html('');
					selectedConstituency='0';
				}
				if(locationScope=='4'){
							$('#errorDiv').html('');
					selectedState=selectedState1.value;
					selectedDistrict=selectedDistrict1.value;
					selectedConstituency=selectedConstituency1.value;
					if(selectedState=='0' || selectedDistrict=='0' || selectedConstituency=='0'){
						$('#errorDiv').html('Please Select  State , District and Constituency');								
					return false;
						}
					}
			}			
			var jsObj=
			{		
				selectedState:selectedState,
				selectedDistrict:selectedDistrict,
				selectedConstituency:selectedConstituency,
				userType:userType,
				locationScope:locationScope,
				task:"getUsersForSendingEmails"
			}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "sendUpdatesByemailsForgettingUsers.action?"+rparam;		
		callAjaxForEmail(jsObj,url);
		}
function selectLocation(){
			  $("#state").val(0);
			  $("#district").val(0);
			  $("#constituency").val(0);
				var value=document.getElementById('locationScope').value;
				if(value==1 || value==0){
					$("#Statediv").css("display","none");
					$("#Districtdiv").css("display","none");
					$("#Constituencydiv").css("display","none");				
				}
				 if(value==2){
					$("#Statediv").css("display","block");
					$("#Districtdiv").css("display","none");
					$("#Constituencydiv").css("display","none");
						clearSelectBoxes('district');
						clearSelectBoxes('constituency');						
				}
				 if(value==3){
					$("#Statediv").css("display","block");
					$("#Districtdiv").css("display","block");
					$("#Constituencydiv").css("display","none");
					clearSelectBoxes('district');
					clearSelectBoxes('constituency');						
				}
				 if(value==4){
					$("#Statediv").css("display","block");
					$("#Districtdiv").css("display","block");
					$("#Constituencydiv").css("display","block");
					clearSelectBoxes('district');
					clearSelectBoxes('constituency');						
				}				
		}
	function defaultForRadio(){
			 $("#locationScope").val(1);
			 selectLocation();	
		}
function clearSelectBoxes(id){	
		var elmt = document.getElementById(id);
		var option = document.createElement('option');
		clearOptionsListForSelectElmtId(id);
		option.value="0";
			if(id=='district'){	
			option.text="Select District";
			}
			else if(id=='constituency'){	
			option.text="Select Constituency";
			}	
				try
				{
				elmt.add(option,null);	
				}
				catch (ex)
				{
					elmt.add(option);
				}
	}
	function hideTableDiv(){
		$("#commentsData_outer").hide();
	}