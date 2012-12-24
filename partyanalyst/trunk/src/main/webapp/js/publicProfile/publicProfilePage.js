
var friendsInPP = [];

$("document").ready(function(){
	
	$('.friendsInPP').click(function(){
		$(".placeholderCenterDiv").children().remove();
		$(".placeholderCenterDiv").css('display','inline-block');
		$("#problemsDiv").children().remove();
		//$('.friendsDiv').css("display","none");
		for(var i in friendsInPP)
		{
		   var name = friendsInPP[i].firstName+" "+friendsInPP[i].lastName;
		   var imageStr = "pictures/profiles/"+friendsInPP[i].profilePic;
		   var template = $(".friendListTemplate");
		   var templateClone =  template.clone();
		   templateClone.removeClass("friendListTemplate");
		   templateClone.find('.frndName').html('<a href="userProfile.action?profileId='+friendsInPP[i].id+'">'+name+'</a>');
		   if(friendsInPP[i].profilePic == null)
			  templateClone.find('.frndImg').html('<a href="userProfile.action?profileId='+friendsInPP[i].id+'"><img height="50" width="55" src="pictures/profiles/member.jpg"/></a>');
		   else
			  templateClone.find('.frndImg').html('<a href="userProfile.action?profileId='+friendsInPP[i].id+'"><img height="50" width="55" src="'+imageStr+'"/></a>');
		 templateClone.appendTo(".placeholderCenterDiv");
	    }
	});


	$("#specialPageLink").click(function(){
		
		var jsObj ={
			task:"getSpecialPages"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSpecialPageForPublicProfileAction.action?"+rparam;	
		callAjax1(jsObj,url);
	});


});//End ready



function callAjax1(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
					
					results = YAHOO.lang.JSON.parse(o.responseText);
					
					 if(jsObj.task == "getSpecialPages")
					{
						showSpecialPages(results);
					}
					
					
					
					
			}catch (e) {   		
			   	//alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function showSpecialPages(results)
{
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.length == 0)
	{
		$(".templateDiv").html("Special Pages Are Not Available.");
		return;
	}
	for(var i in results)
	{
		var template = $(".templateDiv");
		var templateClone = template.clone();
		templateClone.removeClass('templateDiv');
		templateClone.find(".connectedPersonName").html(''+results[i].title+'');
		templateClone.find(".imgClass").html('<img src="'+results[i].eventImagePath+'"/>');
		templateClone.find(".constituencyName").html(''+results[i].title+'');
		templateClone.appendTo(".placeholderCenterDiv");
	}
}


