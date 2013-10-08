  
  $(document).ready(function(){
	
	$("#userSocialNetworkSiteForm").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.next() );
	}
	});

	$.validator.addMethod("emailRequired", function(value, element) {
  if(value == '')
	  return false;
  return true;
}, "Email is required");

$.validator.addMethod("yourNameRequired", function(value, element) {
if(value == '')
return false;
return true;
}, " YourName is required");


$.validator.addMethod("twitterIdRequired", function(value, element) {
if(value == '')
return false;
return true;
}, " TwitterId is required");

$.validator.addMethod("NameRequired", function(value, element) {
if(value == '')
return false;
return true;
}, "Party or Leader Name is required");

$.validator.addMethod("selectoptionisRequired", function(value, element) {
if(value == '' || value =='0')
return false;
return true;
}, " select option is required");


$('#emailId').live('blur',function(){
$(this).valid();		
});

$('#username').live('blur',function(){
$(this).valid();	
});

$('#twitterAcc').live('blur',function(){
$(this).valid();		
});

$('#name').live('blur',function(){
$(this).valid();	
});


$('#category').live('blur',function(){
$(this).valid();	
});


 });


 //To open a popup box

   function openDialogForTwitterID(){
			
				var originalContent=$('#twitter_dialog').html();
				$('#twitter_dialog').dialog("open");
				 $('#twitter_dialog').dialog({
				title:' Enter Twitter IDs of Your known Leaders & Parties',
				height: 'auto',
				width: 560,
				modal: true,
				resizable:false,
				autoopen:false,
				open:function(){
					$("#innerdiv").css("display","block");	
					
					$(this).parent(".ui-dialog").find(".ui-dialog-titlebar-close").click(function(){
						$(this).dialog('close');
						
					});
				},
	
				buttons:   [
				{
					text: "Send",
					"class":'btn btn-inverse btn-small',
					"click":function(){
						getInsertData();
					}
				},
					 {
					text: "Cancel",
					"class":'btn btn-inverse btn-small',
					"click":function(){
						
						$(this).dialog('close');
					}	
					
				}
				],
				close: function(){
					
					$('#twitter_dialog').html(originalContent);					
				}
				

		 });
} 


//for ajax call


var error = false;
var partyName="";
var candidateName="";
function getInsertData()
{		
	
	if($("#emailId").valid() == 0){
		error=true;
	}

	if($("#username").valid() == 0){
		error=true;
	}

	if($("#twitterAcc").valid() == 0){
		error=true;
	}
	if($("#name").valid() == 0){
		error=true;
	}
	if($("#category").valid() == 0){
		error=true;
	}

	if(error==true){
		error=false;
		return false;

	}
	
	var category = document.getElementById("category").value ;
	
	if(category =="1")
	partyName= document.getElementById("name").value ;

	else if(category =="2"){
	candidateName= document.getElementById("name").value ;
	}
		
	var twitterId = document.getElementById("twitterAcc").value;
	var yourName = document.getElementById('username').value; 
	var emailId = document.getElementById('emailId').value; 
	
		var jsObj=
		{		
				partyName:partyName,
				candidateName:candidateName,
				twitterId:twitterId,
				yourName:yourName,
				emailId:emailId,
				task:"insertUserSocialNetworkSiteInfo"					
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "userSocialNetworkSiteAction.action?"+rparam;						
		callUserSocialNetworkSiteAjax(jsObj,url);
		$("#LoadingImage").show();
	}


function callUserSocialNetworkSiteAjax(jsObj,url){

	
var str='';
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "insertUserSocialNetworkSiteInfo")
							{		 $("#LoadingImage").hide();
									 $("#resultmessage").html('');
									if(myResults=="success"){
										
									 str+='Thanks We will verify And Process Your Request';
									}
									 $("#resultmessage").html(str);
									setTimeout( "$('#twitter_dialog').dialog('close')",3500);
							}	
							
						}catch(e)
						{   

							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								 $("#LoadingImage").hide();//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);



}