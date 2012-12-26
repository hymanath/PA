
var friendsInPP = [];
var startIndex;
$("document").ready(function(){
	
	$('.friendsInPP').click(function(){
		$(".placeholderCenterDiv").children().remove();
		$(".placeholderCenterDiv").css('display','inline-block');
		$("#problemsDiv").children().remove();

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

	 $(".problemLink").click(function(){
		var linkType = "problemLink";
		startIndex = 0;
	
		 var jsObj ={
				startIndex   : startIndex,
				maxIndex     : 10,
				linkType     : linkType,
				task:"getProblemDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getProblemDetailsForPublicProfileAction.action?"+rparam;	
		callAjaxForProfilePage(jsObj,url);
	});


});//End ready



function callAjaxForProfilePage(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
					
					results = YAHOO.lang.JSON.parse(o.responseText);
					
					if(jsObj.task == "getStreamingData")
						showStreamingData(results,jsObj);

					else if(jsObj.task == "getProblemDetails")
						showProblemDetails(results,jsObj);
					 


			}catch (e) {  
                  $("#subscriptionsStreamingAjaxImg").hide();			
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

function showProblemDetails(results,jsObj)
{
	$("#problemsDiv").children().remove();
	if(results == null || results.length == 0)
	{

	}
	for(var i in results)
	{
	  var problemList = results[i].problemBeanVOList;
	  var postedDate = results[i].postDate;
		
		for(var j in problemList)
		{
		  var template = $(".problemTemplate");
		  var templateClone = template.clone();
		  templateClone.removeClass("problemTemplate");
		  templateClone.find('.problemTitle').html(''+problemList[j].problem+'');
		  templateClone.find('.problemDescription').html(''+problemList[j].description+'');
		  templateClone.appendTo('.placeholderCenterDiv');
		}
	}
}

function executeOnload()
{
	startIndex = 0;
	
	var jsObj ={
		startIndex   : startIndex,
		maxIndex     : 10,
		profileId    :profileId,
		task:"getStreamingData"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStreamingDataForProfileUserAction.action?"+rparam;	
	callAjaxForProfilePage(jsObj,url);
}

function showStreamingData(results,jsObj)
{
	$("#problemsDiv").children().remove();
	var status;
	if(results != null)
	{
		for(var i in results)
		{
			var postedDate = $('<div></div>');
			postedDate.append('<span>'+results[i].postedDate+'</span>');
			postedDate.appendTo('.placeholderCenterDiv');
			var list = results[i].problemBeanVOList;
			for(var j in list)
			{
				
				 if(list[j].responseType == "Problems")
				{
					var template = $('.problemTemplate');
					var templateClone = template.clone();
					templateClone.removeClass('problemTemplate');
					templateClone.find('.problemImg').html('<img src="pictures/profiles/'+list[j].userImageURL+'" class="span1"/>');
					templateClone.find('.postedPersonName').html(''+list[j].postedPersonName+' Posted');
					templateClone.find('.problemTitle').html(''+list[j].problem+'');
					templateClone.find('.problemDescription').html(''+list[j].description+'');
					templateClone.find('.problemFromDate').html('Existing From: '+list[j].existingFrom+'');
					
					templateClone.appendTo('.placeholderCenterDiv');
				}

				
				if(list[j].responseType == "Comments")
				{
					var imageStr = "/PartyAnalyst/images/candidates/"+list[j].name;
					var template = $('.politicalReasonsTemplate');
					var templateClone = template.clone();
					templateClone.removeClass('politicalReasonsTemplate');
					templateClone.find('.postedBy').html('<img src="pictures/profiles/'+list[j].userImageURL+'" class="span1"/>');
					
					if(list[j].problemImpactLevelId == 1)
						status = "winning";
					else
						status = "losing";
					templateClone.find('.headingCls').html('<h5>'+list[j].postedPersonName+'</h5> Posted a Political Reason for '+list[j].name+' '+status+' '+list[j].constituency+' '+list[j].problemType+' constituency');
					templateClone.find('.candidateImg').html('<img src="'+imageStr+'.jpg" onerror="setDefaultImage(this)" style="width:100px;height:100px;vertical-align:middle;"></img>');
					templateClone.find('.politicalReaCls').html('<b>Political Reason:</b> '+list[j].problem+'');
					templateClone.find('.politicalDescCls').html('<b>Description:</b> '+list[j].description+'');
					templateClone.find('.polReaPostedDate').html('Posted On: '+list[j].postDate+'');
					templateClone.appendTo('.placeholderCenterDiv');
				}
			}
		}
	}

}

function setDefaultImage(img)
{
		img.src = "images/candidates/human.jpg";
}