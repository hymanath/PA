<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Registration</title>

</head>  
<body>  
<style>
	#bodyId{width:995px;margin-left:auto;margin-right:auto;}
	
</style>

<div id="bodyId">Hello Welcome to Electoral Connect </div>
<span style="float:right;display:none;" id="passwordWindow"><a class="btn btn-primary" id="changePassword" >Change Password</a></span>

<div id="commentDis">
<span>Comment : </span><textarea rows="4" cols="50" style="width: 252px; height: 56px; margin-left: 10px;" id="commentText">
</textarea>
<input type="button" class="btn btn-primary" value="COmment" onClick="saveComment();"></input>
</div>
<script>

$(document).ready(function () {
	var passwordChanged = '${passwordChanged}';
	//alert(passwordChanged);
	if(passwordChanged == "NO")
		{
			$('#passwordWindow').show();
		}
		
		else if(passwordChanged == "YES")
		{
			$('#passwordWindow').hide();
		}
});

$('#changePassword').click(function(){
$('#passwordModal').modal('show');
});

function saveComment()
{
	var comment = $('#commentText').val();
	var jsObj =
		{  	
			id       : 1,
			comment  : comment,
			task     : 'commentSave'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "commentSaveAction.action?"+rparam;
		callAjax(jsObj, url);
}

function callAjax(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="commentSaveAction")
								{
									alert(12345);
								}
								
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
</script>

</body>  
</html>