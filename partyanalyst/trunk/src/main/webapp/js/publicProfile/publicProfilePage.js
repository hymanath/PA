

$("document").ready(function(){
	
	 $("#friendsLink").click(function(){
		var jsObj ={
			profileId:profileId,
			task:"getLatestFriendsList"
	};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getFriendsListForPublicProfileAction.action?"+rparam;						
		callAjax1(jsObj,url);
		
	});
	
	$('.friendsInPP').click(function(){
		//getFriendsForUser();
		
	});


	$("#specialPageLink").click(function(){
		
		var jsObj ={
			profileId:profileId,
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
					
					 if(jsObj.task == "getLatestFriendsList")
						getFriendsListForUser(results);				
										
					else if(jsObj.task == "getSpecialPages")
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



function getFriendsListForUser(results)
{
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.resultStatusForConnectedPeople.resultCode != "0")
	{
		$(".templateDiv").html('<div>Data could not be retrived due to some technical difficulties</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	else if(results.connectedPeople == "")
	{
		$(".templateDiv").html('<div>There are no connections established till now.</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	
	
	
	$("#headerDiv").html('You have total <span style="color:blue;">'+results.connectedPeople.length+'</span>  connections.');
	
	for(var i in results.connectedPeople)
	{
		
		var template = $(".templateDiv");
		var templateClone =  template.clone();
		templateClone.removeClass("templateDiv");
		templateClone.find('.connectedPersonName').html(''+results.connectedPeople[i].candidateName+'');
		templateClone.find('.imgClass').html('<img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/>');
		templateClone.find('.constituencyName').html(''+results.connectedPeople[i].constituencyName+'');
		templateClone.find('.districtName').html(''+results.connectedPeople[i].district+'');
		templateClone.find('.stateName').html(''+results.connectedPeople[i].state+'');
		templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a>');
		templateClone.appendTo(".placeholderCenterDiv");
	}

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


var friendsInPP = {
					id:'',
					profilePic:'',
					firstName:'',
					lastName:''
					};
					
function getFriendsForUser()
{
//console.log(friendsInPP);

	/*$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.resultStatusForConnectedPeople.resultCode != "0")
	{
		$(".templateDiv").html('<div>Data could not be retrived due to some technical difficulties</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	else if(results.connectedPeople == "")
	{
		$(".templateDiv").html('<div>There are no connections established till now.</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	
	
	
	$("#headerDiv").html('You have total <span style="color:blue;">'+results.connectedPeople.length+'</span>  connections.');
	
	for(var i in results.connectedPeople)
	{
		
		var template = $(".templateDiv");
		var templateClone =  template.clone();
		templateClone.removeClass("templateDiv");
		templateClone.find('.connectedPersonName').html(''+results.connectedPeople[i].candidateName+'');
		templateClone.find('.imgClass').html('<img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/>');
		templateClone.find('.constituencyName').html(''+results.connectedPeople[i].constituencyName+'');
		templateClone.find('.districtName').html(''+results.connectedPeople[i].district+'');
		templateClone.find('.stateName').html(''+results.connectedPeople[i].state+'');
		templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a>');
		templateClone.appendTo(".placeholderCenterDiv");
	}*/
}

