

$("document").ready(function(){
	
	 $("#friendsLink").click(function(){
		var jsObj ={	
			task:"getLatestFriendsList"
	};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getLatestFriendsList.action?"+rparam;						
		callAjax(jsObj,url);
		
	});

	$("#requestLink").click(function(){
		var jsObj ={
			task:"getAllRequestMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getRequestMessagesForUserAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	});

	 
	$("#messagesLink").click(function(){
		
		var jsObj ={
			task:"getRequestMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllRequestMessagesForAUserAction.action?"+rparam;	
		
		callAjax(jsObj,url);
	});

});


function callAjax(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getAllRequestMessagesForUser")
					{													
					
						showAllRequestMessagesForUser(results,jsObj);
						
					}
					else if(jsObj.task == "getLatestFriendsList")
					{
						getFriendsListForUser(results);				
					}
					else if(jsObj.task == "getRequestMessagesForUser")
					{
						showRequestedMessagesForAUser(results);
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
	
	if(results.resultStatusForConnectedPeople.resultCode != "0")
	{
		$(".templateDiv").html('<div>Data could not be retrived due to some technical difficulties</div>');
			return;
	}
	else if(results.connectedPeople == "")
	{
		$(".templateDiv").html('<div>There are no connections established till now.</div>');
			return;
	}
	$(".placeholderCenterDiv").children().remove();
	$("#headerDiv").html('You have total '+results.connectedPeople.length+' connections.');
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


function showAllRequestMessagesForUser(results,jsObj){

	if(results.friendRequest ==null)
	{
		$("#headerDiv").html('You have 0 Requests');
		return;
	}

	$(".placeholderCenterDiv").children().remove();
	$("#headerDiv").html('You have '+results.friendRequest.length+' Requests');
	for(var i in results.friendRequest){
		var template = $(".templateDiv");
		var templateClone =  template.clone();

		templateClone.removeClass("templateDiv");

		templateClone.find('.connectedPersonName').html(''+results.friendRequest[i].candidateName+'');
		templateClone.find('.imgClass').html('<img height="50" width="55" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/>');
		templateClone.find('.constituencyName').html(''+results.friendRequest[i].message+'');
		templateClone.find('.districtName').html('<input type="button" onclick="acceptRequest('+results.friendRequest[i].id+')" value="Accept" class="acceptButton"></input>');
		templateClone.find('.stateName').html('<input type="button" onclick="rejectRequest('+results.friendRequest[i].id+')" value="Decline" class="rejectButton"></input>');
		templateClone.find('.sendMsg').html('<input type="button" onclick="blockRequest('+results.friendRequest[i].id+')" value="Block this person" class="rejectButton"></input>');
		templateClone.appendTo(".placeholderCenterDiv");
	}
}


function showRequestedMessagesForAUser(results)
{
	if(results.resultStatus.resultCode !="0")
	{
		$(".templateDiv").html("Data could not be retrived due to some technical difficulties.").appendTo(".placeholderCenterDiv");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$(".templateDiv").html("No messages has been sent to you.").appendTo(".placeholderCenterDiv");
		return;
	}
		$("#headerDiv").html('Unread: '+results.unreadMsgCount+'  Total Messages:'+results.totalMsgCount+'');
		for(var i in results.candidateVO)
		{
		var template = $(".templateDiv");
		var templateClone = template.clone();
		templateClone.removeClass("templateDiv");
		templateClone.find(".imgClass").html(''+results.candidateVO[i].candidateName+'');
		templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].data+'');
		templateClone.find(".constituencyName").html(''+results.candidateVO[i].postedDate+'');

		templateClone.appendTo(".placeholderCenterDiv");
		}
}


function showMailPopup(id,name,type)
{
	$( "#connectPeoplePopup" ).dialog({
			title:"Send Message to "+name,
			autoOpen: true,
			show: "blind",
			width: 400,
			minHeight:300,
			modal: true,
			hide: "explode"
		});
	
}

