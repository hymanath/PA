<style>
	.whitegloss{background:linear-gradient(to bottom, #EEEEEE 0%, #FFFFFF 49%, #FFFFFF 100%) repeat scroll 0 0 transparent;}
</style>
<div class="whitegloss">
<div>	
								
								  <div class="modal-header ">
									<h3 style="font-size:18px;color:#5B5B5B;"> Lot of New Functionalities are available as <span style="color:green;">Online</span> & <span style="color:red;">Offline</span> Application in PartyAnalyst to Earn 3%-5% more Votes</h3>
									</div>
								  
								  <div class="modal-body "><div class="row-fluid">
									    

<div class="span8">
<img src="images/new_homepage/Popup.png" height='350' width='400' style="margin-left:55px;"/></div>
<form class=" span4">
										  <h2 style="font-size: 24px;line-height: 36px;"><small>For our Product</small> DEMO</h2><span id="errorMsg" style="display:none;color:red;font-family:verdana;"></span>
										  <fieldset style="border: 1.7px solid #D1D1D1;margin-bottom: 10px;padding:8px 10px 4px;">
											<div class="control-group">
												<label class="control-label" style="font-size:13px;line-height:18px;color:#5b5b5b">MLA/Aspirant Name</label>
												<div class="controls" style="height:28px;">
												 <input type="text" placeholder="Enter MLA/Aspirant Name..." id="aspirantName">
												</div>
											</div>
											
											<div class="control-group">
												<label class="control-label" style="font-size:13px;line-height:18px;color:#5b5b5b">Mobile Number</label>
												<div class="controls" style="height:28px;">
												 <input type="text" placeholder="Enter Mobile Number..." id="mobileId">
												</div>
											</div>
											
											<div class="control-group">
												<label class="control-label" style="font-size:13px;line-height:18px;color:#5b5b5b">Constituency</label>
												<div class="controls" style="height:28px;">
												 <input type="text" placeholder="Enter Constituency..."  id="constituencyName">
												</div>
											</div>
<div class="control-group">
												<label class="control-label" style="font-size:13px;line-height:18px;color:#5b5b5b">Email Id</label>
												<div class="controls" style="height:28px;">
												 <input type="text" placeholder="Enter Email Id..." id="aspirantEmailId">
												</div>
											</div>
											
											
											
											
											
											
											
											
											
											
											
										  </fieldset>	
										</form>
								  </div></div>
								  
								  <div class="modal-footer">
								  <div id="textMsg" style="text-align:center;display:none;font-family:verdana;"></div>
								  <img id='AjaxImg' style='width: 20px; padding-left: 30px; display: none;' src='images/icons/loading.gif'>
									<a href="#" class="btn btn-primary" id="sendMailToAdminGroup" onclick="validate()">Submit</a>
									<a href="#" class="btn " id="skipId" onclick="closeDialogue();">Skip</a>
								  </div>
								</div>
</div>
<script>
$(document).ready(function(){
$(".ElectionInfo a").parent().click(function(){
$(location).attr('href',$(this).find("a").attr("href"));

 });
setTimeout(function(){
$("#fancybox-wrap").css({"width":"880px","margin-left":"auto","margin-right":"auto"});
$("#fancybox-wrap").addClass("centerdiv");
$("#fancybox-content").css({"width":"860px"});
},0);

});

function validate()
{
var mobileNO = $("#mobileId").val();
if(mobileNO.length == 0)
	{
	$("#errorMsg").css("display","block");
	$("#errorMsg").html('Mobile No is Mandatory').css("display","red");;
    return;
	}
$("#textMsg").html('Your request is submitted successfully and we will get back to you soon..........').css({ 'display': 'block', 'color': 'green' });
$("#AjaxImg").show();
$("#errorMsg").html("");
	var constituencyName = $("#constituencyName").val();
	var name = $("#aspirantName").val();
	var email =$("#aspirantEmailId").val();

	
	 $("#sendMailToAdminGroup").attr('disabled','disabled');


	var jsObj=
				{ 
					constituencyName:constituencyName,
					mobileNO:mobileNO,
					name:name,
					email:email,
					task:"sendEmail"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendMailtoAdminGroupAction.action?"+rparam;
	ajaxResultInPopUpPage(jsObj,url);
	setTimeout("$.fancybox.close();",3000);
}
function closeDialogue()
{
  $.fancybox.close();
}

function ajaxResultInPopUpPage(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);	
									
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