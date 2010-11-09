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
  	 
	 function showAllGroups(results){	
	  	var allGroups = document.getElementById("allGroupsDiv");
	  	var str='';	
	  
	  	if(results.resultStatus==null){
	  		str+='<table>';	  		
		  		for(var i in results.setOfGroups)
				{
		  			var groupName = results.setOfGroups[i].name;
		  			str+='	<tr>';
		  			str+='		<td><input name="entitlementsCheckBox" type="checkbox" onclick="getAllCheckedButtons('+results.setOfGroups[i].id+',\''+groupName+'\')" id=" '+results.setOfGroups[i].id+' ">'+ results.setOfGroups[i].name +'</input></td>';
		  			str+='		<td>';
		  			str+='			<a onclick="showCompleteDetailsOfAUserGroup('+results.setOfGroups[i].id+',\''+groupName+'\')" >view all entitlements </a>	';
		  			str+='		</td>';
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
	   
	  function getAllCheckedButtons(id,name)
	  {	
		selectedGroup+=id;
		selectedGroup+=",";
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
			alert("please enter group name");
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
			alert("please enter entitlement name");
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
			alert("please enter entitlement");
		}		
	}
	
	function viewAllEntitlements()
	{
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
		var groupsDiv = document.getElementById("groupsInfoDiv");

		if(groupsDiv){
			if(groupsDiv.style.display == 'block'){
				groupsDiv.style.display = 'none';
			}else{
				var jsObj=
				{					
						type:"getAllUserGroups",			
						task:"getAllUserGroups"						
				};				
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "entitlementUserAction.action?"+rparam;	
				callAjax(jsObj,url);
				groupsDiv.style.display = 'block';
			}
		}
	}
	
	function saveUserGroupRelation()
	{
		var usersId = document.getElementById("usersId");
		var value = usersId.options[usersId.selectedIndex].value;
		
		if(selectedGroup != ""){
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
		}else{
			var details = document.getElementById("completeDetailsOfAGroup");
			var str='';
			str+='<b style="color:green;">Please select a group</b>';
			details.innerHTML = str;
		}
		
	}
	
	
	function showSaveUserGroupsRelation(results)
	{
		
		
	}