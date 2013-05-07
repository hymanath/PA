<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Sub User</title>


<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

<style type="text/css">

#subUserMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    margin-top: 35px;
    width: 980px;}
</style>
<script type="text/javascript">

</script>
</head>
<body>

<div id="subUserMainDiv">

 <div id="subUserInnerDiv">
 <div id="errorMsgDiv"></div>
	<p><span>Select User : </span><select id="PAUsersList"></select></p>
	<p><span>Parent User : </span><select id="parentUsersList"></select></p>
	<p><span>Main Account : </span><select id="mainAccountUsersList"></select></p>

	<input type="button" value="update" id="assignParentUser"/>

 </div>


</div>

<script type="text/javascript">

function getAllPAUsers()
{
	var jsObj = {
		task : "getAllPAUsers"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPAUsersAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "getAllPAUsers")
									  showAllPAUsers(myResults,"PAUsersList",null);
									
									else if(jsObj.task == "getAllUsers")
									{
										showAllPAUsers(myResults.selectOptionsList,"parentUsersList",myResults.parentUserId);
										showAllPAUsers(myResults.selectOptionsList,"mainAccountUsersList",myResults.mainAccountId);
									}
									else if(jsObj.task == "assignParentUser")
									 showSuccessMsg(myResults);
								
								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}

	function showAllPAUsers(result,elmtId,selectedId)
	{
		var elmt = document.getElementById(elmtId);
		if(elmt == null || result == null)
			return;
		removeSelectElements(elmt);

		for(var i in result)
		{
			var option = document.createElement('option');
			option.value = result[i].id;
			option.text = result[i].name;
			try{
				elmt.add(option,null);
			}
			catch(ex){
				elmt.add(option);
			}
			
			if(selectedId != null && selectedId != "0" && result[i].id ==selectedId)
			 $(option).attr("selected","selected") ;	
		}
	
	}

	function showAllParentUsers(result,parentUserId)
	{
		var elmt = document.getElementById("parentUsersList");
		removeSelectElements(elmt);

		if(elmt == null || result == null)
			return;
		for(var i in result)
		{
			var option = document.createElement('option');
			option.value = result[i].id;
			option.text = result[i].name;
			try{
				elmt.add(option,null);
			}
			catch(ex){
				elmt.add(option);
			}
			if(parentUserId != null && parentUserId != "0" && result[i].id ==parentUserId)
			 $(option).attr("selected","selected") ;	

		}

	}
	function showAllMainAccountUsers(result,mainAccountId)
	{
		
		
		var elmt = document.getElementById("mainAccountUsersList");
		removeSelectElements(elmt);

		if(elmt == null || result == null)
			return;
		for(var i in result)
		{
			var option = document.createElement('option');
			option.value = result[i].id;
			option.text = result[i].name;
			try{
				elmt.add(option,null);
			}
			catch(ex){
				elmt.add(option);
			}
			if(mainAccountId != null && mainAccountId != "0" && result[i].id == mainAccountId)
			{
				
			 $(option).attr("selected","selected") ;	
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
$(document).ready(function(){
	
	$("#PAUsersList").change(function(){
		
		var userId = $("#PAUsersList").val();
		var jsObj = {
		id : userId,
		task : "getAllUsers"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllParentUsersAction.action?"+rparam;						
	callAjax(jsObj,url);

	});


	$("#assignParentUser").click(function(){
		
		var userId = $("#PAUsersList").val();
		var parentUserId = $("#parentUsersList").val();
		var mainAccountId = $("#mainAccountUsersList").val();

		var jsObj = {
		id : userId,
		parentUserId:parentUserId,
		mainAccountId:mainAccountId,
		task : "assignParentUser"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "assignParentUserAction.action?"+rparam;						
	callAjax(jsObj,url);

	});

});

function showSuccessMsg(result)
{
	if(result.resultCode == 0)
	{
		$("#errorMsgDiv").html("Updated Successfully").css("color","green");
		return;
	}
	else
	{
		$("#errorMsgDiv").html("Error Occured try Again.").css("color","red");
		return;
	}

}

getAllPAUsers();

</script>
</body>
</html>