	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" type="text/css" href="styles/socialnetwork/newdemo.css" />
<link rel="stylesheet" type="text/css" href="styles/socialnetwork/jScrollPane.css" />

	<!-- styles specific to demo site -->
		<!-- styles needed by jScrollPane - include in your own sites -->
<link type="text/css" href="styles/socialnetwork/scrollpane_style/jquery.jscrollpane.css" rel="stylesheet" media="all" />
<!-- For UserSocialNetworkSite.jsp-->
		
 <script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
 <script type="text/javascript" src="js/UserSocialNetworkSite.js"></script>
 <script type="text/javascript" src="js/socialNetwork/newTwitterAPI.js?1000"></script>
<!-- <script src="http://platform.twitter.com/widgets.js" type="text/javascript"></script>-->
 <!--End UserSocialNetworkSite.jsp-->
<style type="text/css" id="page-css">
			/* Styles specific to this particular page */
			.scroll-pane
			{
				width: 100%;
				height: 300px;
				overflow: auto;
			}
			.horizontal-only
			{
				height: auto;
				max-height: 200px;
			}

	 .error{
    
	color:red;
	font-weight: normal;

	}
	</style>
			
	

<script type="text/javascript">
 $(function(){

			$('#politician').parent().hide();
			//$('#party').parent().hide();
     $('input:radio').bind('change', function(){
     $('#party').attr('disabled', !$("#rparty").is(":checked"));
	 $('#politician').attr('disabled', !$("#rpolitician").is(":checked"));
if($("#rparty").is(":checked")){
	$('#party').parent().show();
	
}
else{
	$('#party').parent().hide();
}

if($("#rpolitician").is(":checked")){
	$('#politician').parent().show();
	
}
else{
	$('#politician').parent().hide();
}
  });
 
        });

function getPartyName()
{
	var str='';
	var partyRadio;
	var ele=document.getElementById('ele');
	var partyRadioele=document.getElementById('rparty');
	var politicianRadioele=document.getElementById('rpolitician');
	var allRadioele=document.getElementById('rall');
	var task = null;
	if(partyRadioele.checked)
	{
	partyRadio =partyRadioele.value;
	task="getPartyNames";
	}
	else if(politicianRadioele.checked)
	{
	partyRadio=politicianRadioele.value;
		task="getCandidateNames";
	}/*else if(allRadioele.checked){
		task="getNames";
	}*/
		var jsObj=
		{
				elmtId:partyRadio,
				//electionTypeId:id,
				task:task					
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getTwitterAction.action?"+rparam;						
		callNewHomePageAjax(jsObj,url);
	}

function callNewHomePageAjax(jsObj,url)
{	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == 'getPartyNames')
							{
								getPartyNames(myResults);				
							}
							else if(jsObj.task=="getCandidateNames")
							{
								getCandidateNames(myResults);
							}
							/*else if(jsObj.task=="getNames")
							{
							getAllTweets(myResults);
							}*/
						}catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function clearOptionsListForSelectElmtId(elmtId)

{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}
function getCandidateNames(result)
{
var elmt = document.getElementById('politician');
	var option = document.createElement('option');

	 clearOptionsListForSelectElmtId('politician');
	option.value="0";
	option.text="select";
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in result)
	{
		
		var option=document.createElement('option');
		//option.id=result[i];
			option.value=result[i].url;
		
		option.text=result[i].name;
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
	
}
var flag = false;
function getPartyNames(result)
{

	var elmt = document.getElementById('party');
	var option = document.createElement('option');

	 clearOptionsListForSelectElmtId('party');
	option.value="0";
	option.text="select";
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in result)
	{
		
		var option=document.createElement('option');
		//option.id=result[i].id;
		option.value=result[i].url;
		option.text=result[i].name;
		
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
	if(!flag){
	flag = true;
	$('#party').val('357110592005369856');
	}
}

setTimeout("getPartyName()",1000);

function setTweetUsers(ids){
if(ids > 0){
$('#TwitterDiv').html('');
//var id = '357110592005369856'; // tdp
//var id = '357143208817467392'; // partyanalyst
var id = ids;
var str='';
	str+='<a class="twitter-timeline" data-widget-id="'+id+'"> Please Wait ...</a>';
	//str+='<a class="twitter-timeline" data-widget-id="357123072467283970"> Tweets By User</a>';
	$('#TwitterDiv').html(str);
createDiv();
//setTimeout("createDiv()",1000);
}
}

function createDiv(){
!function(d,s,id){ 
var js;
var fjs=d.getElementsByTagName(s)[0];
var p=/^http:/.test(d.location)?'http':'https';
//if(!d.getElementById(id)){
js=d.createElement(s);
js.id=id;
js.src=p+"://platform.twitter.com/widgets.js";
fjs.parentNode.insertBefore(js,fjs);
//}
}(document,"script","twitter-wjs");
}

</script>


<div id="main">
 <div id="twitter-ticker">

<form name="search_cat_bar" method="get" action="" style="margin:0px;">
<div class="span12">
<img src="images/socialNetwork/twitter_64.png" width="48" height="48" alt="Twitter icon" class="thumbnail pull-left"/>
<div class="span10">
    <label class="radio inline">
    <input name="party" value="party" type="radio" id="rparty"  onclick="getPartyName()" checked="true"/> Party
    </label>
    <label class="radio inline">
    <input name="party" value="party" type="radio" id="rpolitician" onclick="getPartyName()"/>Leader
    </label>
  <!--  <label class="radio inline">
	<input name="party" value="party" type="radio" id="rall"  checked="true" onclick="getPartyName()">All 
    </label> -->
</div>	

<div class="row-fluid span10">
<div class="input-prepend span12 inline">
              <span class="add-on">Party Name:</span>
             <select name="party" onChange="setTweetUsers(this.options[this.selectedIndex].value)"
 id="party" class="span6"></select>
            </div>
			
			<div class="input-prepend span12 inline">
              <span class="add-on">Leader Name:</span>
             <select name="poliician" onChange="setTweetUsers(this.options[this.selectedIndex].value)" id="politician"  class="span8" >
			 </select>
            </div>
</div>			
</div>
	
</form>
</div>
 </div>
 
<div id="TwitterDiv" > 
<a class="twitter-timeline" data-widget-id="357110592005369856"></a>
</div>


	<!-- For UserSocialNetworkSite.jsp-->

	<div id="twitter_dialog"> 
	<div id="innerdiv" style="display:none;">
	<form  id="userSocialNetworkSiteForm" name="userSocialNetworkSiteForm"  method="POST" >
	<div class="placeholder">

	    <div style="height:45px;">
		<span style="font-size: 13px;"><font color="red">*</font>Your Name:</span>
		<span><input  id="username" type="text" class="yourNameRequired" style="height:18px;margin-bottom:-2px;margin-left: 116px;"/>
		<div id="usernameerrormssg"></div>
		</span></div>

		<div style="height:45px;">
		<span style="font-size: 13px;"><font color="red">*</font>Your EmailId:</span>
		<span><input id="emailId" type="text" class="emailRequired email" style="height:18px;margin-bottom:-2px;margin-left: 107px;"/>
		<div id="usermailerrmsg"></div>
		</span></div>
	
	<div style="height:45px;"> 
	<span style="font-size: 13px;"><font color="red">*</font>Select Category:</span>
	<span><select id="category"  class="selectoptionisRequired" style="width: 219px;height:28px;margin-bottom:-2px;margin-left: 80px;">
		<option value='0'>--Select Category--</option>
		<option value='1'>Party</option>
		<option value='2'>Leader</option>
		</select>
		<div id="selectoptionErrormsg"></div>
		</span></div>

		<div style="height:45px;">
		<span style="font-size: 13px;"><font color="red">*</font>Party/Leader Name:</span>
		<span><input  type="text" id="name" class="NameRequired" style="height: 18px; margin-bottom: -2px; margin-left: 60px;"/>
		<div id="accnameerrormsg"></div>
		</span></div>

		<div style="height:45px;"> 
		<span style="font-size: 13px;"><font color="red">*</font>TwitterId:</span>
		<span><input id="twitterAcc" type="text"  class="twitterIdRequired" style="height: 18px; margin-bottom: -2px; margin-left: 126px;"/>
		<div  id="twitteraccerrormsg"></div>
		</span></div>
		<div style="height:45px">
		<div id="LoadingImage"  style="display: none;height:30px">
		    Your Request has been Processing...
		<img src="images/icons/search.gif" style="margin-top: -118px; margin-left: 10px; border-top-width: 0px; padding-top: 127px; padding-bottom: 19px;"/>
		</div></div>
			
	</div>
 
</form>

		<div id="resultmessage"></div>
		 </div>
	     </div>
		 <hr>
		<div class="thumbnail" style="margin-top:25px;text-align:center;"><span style="font-weight:bold;font-size:14px;">
		Share Twitter ID's of your known Leaders & Parties  </span>
		<input type="button" id="click" class="btn btn-inverse btn-small" value="Click to Share Now!" onclick="openDialogForTwitterID()" style="margin-top:5px;margin-bottom:5px;"/></div>
		<!-- end of UserSocialNetworkSite.jsp-->
	

