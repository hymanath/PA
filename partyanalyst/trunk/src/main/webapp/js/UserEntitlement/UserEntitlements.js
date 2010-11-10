  function showCompleteResultsOfAGroup(results)
	  {
		  var completeDetails = document.getElementById("completeDetailsOfAGroup");
		  var str='';	
		  
		  if(results.listOfEntitlements!=null){
			  str+='<table>';	
			  str+='	<tr>';
			  str+='		<td style="font-weight:bold;"> Details Of '+results.name+' Group</td>';
			  str+='	</tr>';
			  for(var i in results.listOfEntitlements)
			 {
					  str+='	<tr>';
					  str+='		<td>'+results.listOfEntitlements[i].name+'</td>';		  
					  str+='	</tr>';
			 }
			  str+='</table>';
			  completeDetails.innerHTML = str;
		  }else{
			  str+='<b style="color:green;"> No entitlements were alloted for the group.</b>';
			  completeDetails.innerHTML = str;
		  }		  
	  }
	  
  	 function showCompleteDetailsOfAUserGroup(id,name){
  	  	
  		selectedGroup = id;
  		var jsObj=
		{	
  				userGroupId:id,	
  				name:name,
				type:"getAllEntitlementsForAUserGroup",			
				task:"getAllEntitlementsForAUserGroup"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementUserAction.action?"+rparam;	
		callAjax(jsObj,url);
  	 } 
  	function showAllCommonGroups(results)
  	{  		
  		var allGroups = document.getElementById("allGroupsDiv");
	  	var str='';	
	  
	  	if(results.resultStatus.resultCode==0){
	  		str+='<table>';	  		
		  		for(var i in results.entitlementVO)
				{		  			
		  			str+='	<tr>';
		  			if(results.entitlementVO[i].message=="AVAILABLE"){
		  				str+='		<td><input name="entitlementsCheckBox" value="'+results.entitlementVO[i].userId+'" type="checkbox" checked="checked"   id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';
		  			}else{
		  				str+='		<td><input name="entitlementsCheckBox" type="checkbox"  value="'+results.entitlementVO[i].userId+'"  id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';	
		  			}
		  			str+='	</tr>';
				}	
		  	str+='	<tr><td>';
		  	str+='		<div id="completeDetailsOfAGroup"></div>';
			str+='	</td></tr>';
			str+='	<tr><td>';
	  		str+='		<input type="button" class="button" value="Assign Group" onclick="saveUserGroupRelation()"></input>';
	  		str+='	</td></tr>';
	  		str+='</table>';
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}		
	  	allGroups.innerHTML = str;
	  }
	   
	 
	  function showAvailabilityOfEntitlement(results){
	
	  	var entitlementAvailability = document.getElementById("entitlementAvailabilityID");
	  	var str='';	
	  	if(results.resultStatus==null){
			    	if(results.message=="NOT_AVAILABLE"){
			    		str+='<b style="color:red;">'+results.name+' Entitlement is Not Available</b>';
			        }else{
			        	str+='<b style="color:green;"> '+results.name+' Entitlement is available. </b>';
			        }
			}else{
				str+='<b style="color:red;">There was an error in processing the request.</b>';
			}		
	  	entitlementAvailability.innerHTML = str;
	  }
	  
	  function showAvailabilityOfUserGroup(results){
			
		  	var entitlementAvailability = document.getElementById("userGroupAvailabilityID");
		  	
		  	var str='';	
		  	if(results.resultStatus==null){
				    	if(results.message=="NOT_AVAILABLE"){
				    		str+='<b style="color:red;">'+results.name+' Group is Not Available</b>';
				        }else{
				        	str+='<b style="color:green;"> '+results.name+' Group is available. </b>';
				        }
				}else{
					str+='<b style="color:red;">There was an error in processing the request.</b>';
				}		
		  	entitlementAvailability.innerHTML = str;
		  }
    
    function showAvailabilityOfGroups(results){

    	var groupAvailability = document.getElementById("groupAvailabilityID");
    	var str='';
    	if(results.resultStatus==null){
		    	if(results.message=="NOT_AVAILABLE"){
		    		str+='<b style="color:red;">'+results.name+' Group is Not Available</b>';
		        }else{
		        	str+='<b style="color:green;"> '+results.name+' Group is available. </b>';
		        }
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}    	
    	groupAvailability.innerHTML = str;
    }
    
    function showResult(results){

    	var groupAvailability = document.getElementById("groupAvailabilityID");
    	var str='';
    	if(results.resultStatus==null){
		    	if(results.message=="NOT_AVAILABLE"){
		    		str+='<b style="color:red;">'+results.name+' Group is Not Available</b>';
		        }else{
		        	str+='<b style="color:green;">Successfuly created '+results.name+' group </b>';
		        }
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}
    	str+='';
    	groupAvailability.innerHTML = str;
    }  

    function showEntitlementResult(results){

    	var groupAvailability = document.getElementById("entitlementAvailabilityID");
    	var str='';
    	if(results.resultStatus==null){
		    	if(results.message=="NOT_AVAILABLE"){
		    		str+='<b style="color:red;">'+results.name+' Entitlement is Not Available</b>';
		        }else{
		        	str+='<b style="color:green;">Successfuly created '+results.name+' Entitlement </b>';
		        }
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}
    	str+='';
    	groupAvailability.innerHTML = str;
    }
    	
    function showUserGroupResult(results){

    	var groupAvailability = document.getElementById("userGroupAvailabilityID");
    	var str='';
    	
    	if(results.resultStatus==null){
		    	if(results.message=="NOT_AVAILABLE"){
		    		str+='<b style="color:red;">'+results.name+' Entitlement is Not Available</b>';
		        }else{
		        	str+='<b style="color:green;">Successfuly created '+results.name+' Entitlement </b>';
		        }
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}
    	str+='';
    	groupAvailability.innerHTML = str;
    }
    
	function checkForAvailabilityOfGroupName()
	{
		var enteredText = document.getElementById("groupNameId").value;
		if(enteredText!=""){					
			var jsObj=
			{	
					name:enteredText,	
					type:"checkForgroupNameAvailability",			
					task:"checkForAvailabilityOfGroupName"						
			};				
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "entitlementUserAction.action?"+rparam;	
			callAjax(jsObj,url);
		}else{
			var text = document.getElementById("groupAvailabilityID");
			var str='';
			text.innerHTML = str;
			
			var str2='';
			str2+='<b style="color:red">please enter group name';
			text.innerHTML = str2;
		}		
	}		

	
	function createAEntitlement()
	{
		var enteredText = document.getElementById("entitlementId").value;		
		if(enteredText!=""){					
			var jsObj=
			{	
					name:enteredText,	
					type:"createAnEntitlement",			
					task:"createAnEntitlement"						
			};				
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "entitlementUserAction.action?"+rparam;	
			callAjax(jsObj,url);
		}else{
			var text = document.getElementById("entitlementAvailabilityID");
			var str='';
			text.innerHTML = str;
			
			var str2='';
			str2+='<b style="color:red">please enter group name';
			text.innerHTML = str2;
		}		
	}
	
	function checkForAvailabilityOfEntitlement()
	{
		var enteredText = document.getElementById("entitlementId").value;
		if(enteredText!=""){	
			var selectedCheckBoxes = '';						
			var jsObj=
			{		
					name:enteredText,	
					type:"checkForAvailabilityOfAnEntitlement",			
					task:"checkForAvailabilityOfAnEntitlement"						
			};				
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "entitlementUserAction.action?"+rparam;	
			callAjax(jsObj,url);
		}else{
			var text = document.getElementById("entitlementAvailabilityID");
			var str='';
			text.innerHTML = str;
			
			var str2='';
			str2+='<b style="color:red">please enter group name';
			text.innerHTML = str2;
		}		
	}
	
	function checkForAvailabilityOfUserGroup()
	{
		var enteredText = document.getElementById("userGroupID").value;
		if(enteredText!=""){	
			var selectedCheckBoxes = '';						
			var jsObj=
			{		
					name:enteredText,	
					type:"checkForUserGroupNameAvailability",			
					task:"checkForUserGroupNameAvailability"						
			};				
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "entitlementUserAction.action?"+rparam;	
			callAjax(jsObj,url);
		}else{
			var text = document.getElementById("userGroupAvailabilityID");
			var str='';
			text.innerHTML = str;
			
			var str2='';
			str2+='<b style="color:red">please enter group name';
			text.innerHTML = str2
		}		
	}
	
		
	function viewAllEntitlements()
	{
		var text = document.getElementById("groupAvailabilityID");
		var str='';
		text.innerHTML = str;
		
		var allEntiDiv = document.getElementById("allEntitlementsDiv");

		if(allEntiDiv){
			if(allEntiDiv.style.display == 'block'){
				allEntiDiv.style.display = 'none';
			}else{
				allEntiDiv.style.display = 'block';
			}
		}
	}
	function getAllGroups()
	{		
		var usersId = document.getElementById("usersId");
		var userId = usersId.options[usersId.selectedIndex].value;
		if(userId==0){
			var elemt = document.getElementById("assignEntitlementsId");
			var str='';
			str+='<b style="color:red;"> Please select a user.</b>';
			elemt.innerHTML = str;
			return ;
		}
		var groupsDiv = document.getElementById("groupsInfoDiv");

		
		var jsObj=
		{		
				selectedUserId:userId,
				type:"getAllGroups",			
				task:"getAllGroups"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementAction.action?"+rparam;	
		callAjax(jsObj,url);		
	}	
	
	function getAllEntitlementGroups()
	{		
		var usersId = document.getElementById("userGroupsId");
		var userId = usersId.options[usersId.selectedIndex].value;
		if(userId==0){
			var elemt = document.getElementById("userGroupEntitlemntGroupId");
			var str='';
			str+='<b style="color:red;"> Please select a user group.</b>';
			elemt.innerHTML = str;
			return ;
		}
		var groupsDiv = document.getElementById("userGroupsInfoDiv");

		
		var jsObj=
		{		
				selectedUserGroupId:userId,
				type:"getAllEntitlementGroupsBasedOnUserGroup",			
				task:"getAllEntitlementGroups"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementAction.action?"+rparam;	
		callAjax(jsObj,url);
		
	}
	
	function saveUserGroupRelation()
	{
		var usersId = document.getElementById("usersId");
		var value = usersId.options[usersId.selectedIndex].value;
		var selectedGroup = "";
		var elements = document.getElementsByName("entitlementsCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				selectedGroup+=elements[i].value;
				selectedGroup+=",";
			}
		}
		var jsObj=
		{			
				userId:value,
				groupIds:selectedGroup,
				type:"saveUserGroupsRelation",			
				task:"saveUserGroupsRelation"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementUserAction.action?"+rparam;			
		callAjax(jsObj,url);	
		
	}
	
	function saveUserGroupAndEntitlementGroupRelation()
	{
		var usersId = document.getElementById("userGroupsId");
		var value = usersId.options[usersId.selectedIndex].value;
		var selectedGroup = "";
		var elements = document.getElementsByName("groupCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				selectedGroup+=elements[i].value;
				selectedGroup+=",";
			}
		}
		var jsObj=
		{			
				userGroupId:value,
				entitlementGroupIds:selectedGroup,
				type:"saveRelationBetweenEntitlementsGroupsAndUserGroupId",			
				task:"saveRelationBetweenEntitlementsGroupsAndUserGroupId"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementUserAction.action?"+rparam;			
		callAjax(jsObj,url);	
		
	}
	
	
	function saveGroupRelation()
	{
		var usersId = document.getElementById("userGroupsId");
		var value = usersId.options[usersId.selectedIndex].value;
		var selectedGroup = "";
		var elements = document.getElementsByName("entitlementsCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				selectedGroup+=elements[i].value;
				selectedGroup+=",";
			}
		}
		var jsObj=
		{			
				userId:value,
				groupIds:selectedGroup,
				type:"saveUserGroupsRelation",			
				task:"saveUserGroupsRelation"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementUserAction.action?"+rparam;			
		callAjax(jsObj,url);	
		
	}
	
	
	
	function showSaveUserGroupsRelation(results)
	{
	}
	
	function showUserGroupsForAUser()
	{
		var usersId = document.getElementById("userGroupsId");
		var userId = usersId.options[usersId.selectedIndex].value;
		if(userId==0){
			var elemt = document.getElementById("userGroupEntitlemntGroupId");
			var str='';
			str+='<b style="color:red;"> Please select a user group.</b>';
			elemt.innerHTML = str;
			return ;
		}
		var groupsDiv = document.getElementById("userGroupsInfoDiv");
		
		var jsObj=
		{		
				selectedUserGroupId:userId,
				type:"getAllEntitlementGroupsBasedOnUserGroup",			
				task:"getAllEntitlementGroupsBasedOnUserGroup"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementAction.action?"+rparam;	
		callAjax(jsObj,url);
	}
	
	function showGroupsForAUser()
	{
		var usersId = document.getElementById("usersId");
		var userId = usersId.options[usersId.selectedIndex].value;
		if(userId==0){
			var elemt = document.getElementById("assignEntitlementsId");
			var str='';
			str+='<b style="color:red;"> Please select a user.</b>';
			elemt.innerHTML = str;
			return ;
		}
		var groupsDiv = document.getElementById("groupsInfoDiv");

		
		var jsObj=
		{		
				selectedUserId:userId,
				type:"getAllGroups",			
				task:"getAllUserGroups"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementAction.action?"+rparam;	
		callAjax(jsObj,url);
	}
	
	function getAllEntitlementGroupsBasedOnUserGroup(results)
	{
		var allGroups = document.getElementById("allUserGroupsDiv");
	  	var str='';	
	  	var j=0;
	  	if(results.resultStatus.resultCode==0){
	  		str+='<table>';	  		
		  		for(var i in results.entitlementVO)
				{		  			
		  			str+='	<tr>';
		  			if(results.entitlementVO[i].message=="AVAILABLE"){
		  				j++;
		  				str+='		<td><b name="entitlementsCheckBox" value="'+results.entitlementVO[i].userId+'" type="checkbox"  id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';
		  			}
		  			str+='	</tr>';
				}	
		  		if(j==0){
		  			str+='	<tr>';
		  			str+='		<td style="color:green;"> UserGroup does not have any EntitlementGroup. </td>';
		  			str+='	</tr>';
		  		}
	  		str+='</table>';
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}		
	  	allGroups.innerHTML = str;
	}
	
	function showAllUserGroups(results)
	{
		var allGroups = document.getElementById("allGroupsDiv");
	  	var str='';	
	  	var j=0;
	  	if(results.resultStatus.resultCode==0){
	  		str+='<table>';	  		
		  		for(var i in results.entitlementVO)
				{		  			
		  			str+='	<tr>';
		  			if(results.entitlementVO[i].message=="AVAILABLE"){
		  				j++;
		  				str+='		<td><b name="entitlementsCheckBox" value="'+results.entitlementVO[i].userId+'" type="checkbox"  id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';
		  			}
		  			str+='	</tr>';
				}	
		  		if(j==0){
		  			str+='	<tr>';
		  			str+='		<td style="color:green;"> User is not present in any of the groups </td>';
		  			str+='	</tr>';
		  		}
	  		str+='</table>';
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}		
	  	allGroups.innerHTML = str;
	}
	
	function showAllEntitlementGroups(results)
	{
		var allGroups = document.getElementById("allUserGroupsDiv");
		var str='';	
		  
	  	if(results.resultStatus.resultCode==0){
	  		str+='<table>';	  		
		  		for(var i in results.entitlementVO)
				{		  			
		  			str+='	<tr>';
		  			if(results.entitlementVO[i].message=="AVAILABLE"){
		  				str+='		<td><input name="groupCheckBox" value="'+results.entitlementVO[i].userId+'" type="checkbox" checked="checked"   id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';
		  			}else{
		  				str+='		<td><input name="groupCheckBox" type="checkbox"  value="'+results.entitlementVO[i].userId+'"  id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';	
		  			}
		  			str+='	</tr>';
				}	
		  	str+='	<tr><td>';
		  	str+='		<div id="completeDetailsOfAGroup"></div>';
			str+='	</td></tr>';
			str+='	<tr><td>';
	  		str+='		<input type="button" class="button" value="Assign Group" onclick="saveUserGroupAndEntitlementGroupRelation()"></input>';
	  		str+='	</td></tr>';
	  		str+='</table>';
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}		
	  	allGroups.innerHTML = str;
	}
	
	function showEntitlementsForAEntitlementGroup()
	{
		var usersId = document.getElementById("entitlementGroupId");
		var userId = usersId.options[usersId.selectedIndex].value;
		if(userId==0){
			var elemt = document.getElementById("entitlemntGroupId");
			var str='';
			str+='<b style="color:red;"> Please select a entitlement.</b>';
			elemt.innerHTML = str;
			return ;
		}
		var groupsDiv = document.getElementById("entitlementsInfoDIV");
		
		var jsObj=
		{		
				selectedEntitlementGroupId:userId,
				type:"getAllEntitlementsBasedOnEntitlementGroup",			
				task:"getAllEntitlementsBasedOnEntitlementGroup"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementAction.action?"+rparam;	
		callAjax(jsObj,url);
	}
	
	function getAllEntitlementsBasedOnEntitlementGroup(results)
	{
		var allGroups = document.getElementById("allEntitlementsGroupsDIV");
	  	var str='';	
	  	var j=0;
	  	if(results.resultStatus.resultCode==0){
	  		str+='<table>';	  		
		  		for(var i in results.entitlementVO)
				{		  			
		  			str+='	<tr>';
		  			if(results.entitlementVO[i].message=="AVAILABLE"){
		  				j++;
		  				str+='		<td><b name="entitlementsCheckBox" value="'+results.entitlementVO[i].userId+'" type="checkbox"  id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';
		  			}
		  			str+='	</tr>';
				}	
		  		if(j==0){
		  			str+='	<tr>';
		  			str+='		<td style="color:green;"> EntitlementGroup does not have any Entitlements. </td>';
		  			str+='	</tr>';
		  		}
	  		str+='</table>';
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}		
	  	allGroups.innerHTML = str;
	}
	
	
	function showEntitlements(results)
	{
		var allGroups = document.getElementById("allEntitlementsGroupsDIV");
		var str='';	
		  
	  	if(results.resultStatus.resultCode==0){
	  		str+='<table>';	  		
		  		for(var i in results.entitlementVO)
				{		  			
		  			str+='	<tr>';
		  			if(results.entitlementVO[i].message=="AVAILABLE"){
		  				str+='		<td><input name="entitlementCheckBox" value="'+results.entitlementVO[i].userId+'" type="checkbox" checked="checked"   id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';
		  			}else{
		  				str+='		<td><input name="entitlementCheckBox" type="checkbox"  value="'+results.entitlementVO[i].userId+'"  id=" '+results.entitlementVO[i].userId+' ">'+ results.entitlementVO[i].name +'</input></td>';	
		  			}
		  			str+='	</tr>';
				}	
		  	str+='	<tr><td>';
		  	str+='		<div id="completeDetailsOfAGroup"></div>';
			str+='	</td></tr>';
			str+='	<tr><td>';
	  		str+='		<input type="button" class="button" value="Assign Group" onclick="saveEntitlementAndEntitlementGroupRelation()"></input>';  
	  		str+='	</td></tr>';
	  		str+='</table>';
		}else{
			str+='<b style="color:red;">There was an error in processing the request.</b>';
		}		
	  	allGroups.innerHTML = str;
	}
	
	function saveEntitlementAndEntitlementGroupRelation()
	{
		var usersId = document.getElementById("entitlementGroupId");
		var value = usersId.options[usersId.selectedIndex].value;
		var selectedGroup = "";
		var elements = document.getElementsByName("entitlementsCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				selectedGroup+=elements[i].value;
				selectedGroup+=",";
			}
		}
		var jsObj=
		{			
				groupId:value,
				entitlementIds:selectedGroup,
				type:"saveRelationBetweenEntitlementGroupAndEntitlement",			
				task:"saveRelationBetweenEntitlementGroupAndEntitlement"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementUserAction.action?"+rparam;			
		callAjax(jsObj,url);	
		
	}
	
	function getAllEntitlements()
	{		
		var usersId = document.getElementById("entitlementGroupId");
		var userId = usersId.options[usersId.selectedIndex].value;
		if(userId==0){
			var elemt = document.getElementById("entitlemntGroupId");
			var str='';
			str+='<b style="color:red;"> Please select a Entitlement group.</b>';
			elemt.innerHTML = str;
			return ;
		}
		var groupsDiv = document.getElementById("entitlementsInfoDIV");

		
		var jsObj=
		{		
				selectedEntitlementGroupId:userId,
				type:"getAllEntitlementsBasedOnEntitlementGroup",			
				task:"getEntitlements"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "entitlementAction.action?"+rparam;	
		callAjax(jsObj,url);
		
	}
	
	function createAnUserGroup()
	{
		var enteredText = document.getElementById("userGroupID").value;		
		if(enteredText!=""){					
			var jsObj=
			{	
					name:enteredText,	
					type:"createAnUserGroup",			
					task:"createAnUserGroup"						
			};				
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "entitlementUserAction.action?"+rparam;	
			callAjax(jsObj,url);
		}else{
			var text = document.getElementById("entitlementAvailabilityID");
			var str='';
			text.innerHTML = str;
			
			var str2='';
			str2+='<b style="color:red">please enter group name';
			text.innerHTML = str2;
		}		
	}