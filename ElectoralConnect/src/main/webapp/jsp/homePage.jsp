<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Registration</title>

</head>  
<body>  
<style>
	#bodyId{width:995px;margin-left:auto;margin-right:auto;}
	
</style>
<div>
<div id="bodyId">Hello Welcome to Electoral Connect </div>
<span style="float:right;display:none;" id="passwordWindow"><a class="btn btn-primary" id="changePassword" >Change Password</a></span>

<div id="commentDis">
<span>Comment : </span><textarea rows="4" cols="50" style="width: 252px; height: 56px; margin-left: 10px;" id="commentText">
</textarea>
<input type="button" class="btn btn-primary" value="Comment" onClick="saveComment();"></input>
<div id="errorMsg"></div>
</div>

<div>
<div id="topFiveComments"></div>
<div><a onClick="getAllComments(0,5);" class="btn btn-primary">View More</a></div>
</div>


<div id="totalComments"></div>


</div>
<script>
var startIndex = 0;
var maxIndex = 5;
var totalCount = "";
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
		
		//getTop5Comments(startIndex,maxIndex,"getCommentsList");
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
		callAjaxForComments(jsObj, url);
}

function getTop5Comments(startIndex,maxIndex,task)
{
	var jsObj =
		{  	
			id          : 1,
			startIndex  : startIndex,
			maxIndex    : maxIndex,
			task        : task
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getCommentsAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}

function buildCommentsList(myResults)
{
	if(myResults != null)
	{
		var str = "";
		for(var i in myResults)
		{
			str += '<div class="span10 widget"  style="border-radius: 4px 4px 4px 4px; border: 1px solid green; margin-bottom: 10px;">';
			str +='<span><b>Comment : </b>'+myResults[i].comment+'</span></br>';
			str +='<span style="float:left; margin-top: 13px;"><b>Commented By : <b>'+myResults[i].name+'</span>';
			
			str +='<span style="float:right; margin-top: 13px;"><b>Date : </b>'+myResults[i].date+'</span></br>';
			str += '</div>';
		}
		$('#topFiveComments').html(str);
	}
}


function getAllComments(startIndex,maxIndex)
{
	
	getTop5Comments(startIndex,maxIndex,"getTotalComments");
	
}
function buildTotalCommentsList(myResults)
{
	totalCount = myResults[0].total;
	
	var str = "";
		for(var i in myResults)
		{
			str += '<div class="span10 widget"  style="border-radius: 4px 4px 4px 4px; border: 1px solid blue; margin-bottom: 10px;">';
			str +='<span><b>Comment : </b></span><span>'+myResults[i].comment+'</span></br>';
			str +='<span style="float:left;"><b>Commented By : <b>'+myResults[i].name+'</span>';
			
			str +='<span style="float:right"><b>Date : </b>'+myResults[i].date+'</span></br>';
			str += '</div>';
			str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList();">More</a>'
		}
		$('#totalComments').append(str);
	if(maxIndex < totalCount)
	{
		startIndex = maxIndex;
		maxIndex   = maxIndex + 5;
		$('#moreButton').show();
	}
	else
	{
		$('#moreButton').hide();
	}
	
}

function getRemaingCommentsList()
{
	getTop5Comments(startIndex,maxIndex,"getTotalComments");
}

/* function buildRemainingCommentsList(myResults)
{
	var str = "";
		for(var i in myResults)
		{
			str += '<div class="span10 widget">';
			str +='<span>Name : </span><span>'+myResults[i].name+'</span></br>';
			str +='<span>Comment : </span><span>'+myResults[i].comment+'</span></br>';
			str +='<span>Date : </span><span>'+myResults[i].date+'</span></br>';
			str += '</div>';
			str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList();">More</a>'
		}
		$('#totalComments').append(str);
} */
function callAjaxForComments(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="commentSave")
								{
									if(myResults.resultCode == 0)
									{
										$('#errorMsg').html('<b style="color:green">Comment Saved Successfully..</b>');
										getTop5Comments(startIndex,maxIndex,"getCommentsList");
									}
									else if(myResults == 'notLogged')
									{
										$('#errorMsg').html('<b style="color:red">Please Login To Comment..</b>');
									}
									else 
									{
										$('#errorMsg').html('<b style="color:red">Comment Not Saved Successfully?</b>');
									}
								}
								else if(jsObj.task =="getCommentsList")
								{
									buildCommentsList(myResults);
								}
								else if(jsObj.task =="getTotalComments")
								{
									buildTotalCommentsList(myResults);
								}
								/* else if(jsObj.task =="getRemaingComments")
								{
									buildRemainingCommentsList(myResults);
								} */
								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
</script>

</body>  
</html>