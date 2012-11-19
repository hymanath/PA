<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>A jQuery Twitter Ticker | Tutorialzine demo</title>

<link rel="stylesheet" type="text/css" href="styles/socialnetwork/newdemo.css" />
<link rel="stylesheet" type="text/css" href="styles/socialnetwork/jScrollPane.css" />
<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>-->
<script type="text/javascript" src="js/socialNetwork/scrollpane_js/jquery.mousewheel.js"></script>

<script type="text/javascript" src="js/socialNetwork/script.js"></script>
<script type="text/javascript" src="js/socialNetwork/scrollpane_js/jquery.jscrollpane.js"></script>
	<!-- styles specific to demo site -->
		<!-- styles needed by jScrollPane - include in your own sites -->
		<link type="text/css" href="styles/socialnetwork/scrollpane_style/jquery.jscrollpane.css" rel="stylesheet" media="all" />

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
		</style>
			
		
<script>
			$(function()
			{
			$(".scroll-pane").jScrollPane();
			});
		</script>

<script>
function scrollpanenew()
{

var api = $(".scroll-pane").data('jsp');
	var throttleTimeout;
	if ($.browser.msie) {
						// IE fires multiple resize events while you are dragging the browser window which
						// causes it to crash if you try to update the scrollpane on every one. So we need
						// to throttle it to fire a maximum of once every 50 milliseconds...
						if (!throttleTimeout) {
							throttleTimeout = setTimeout(
								function()
								{
									api.reinitialise();
									throttleTimeout = null;
								},
								50
							);
						}
					} else {
						api.reinitialise();
					}
}


 $(function(){

			$('#politician').parent().hide();
			$('#party').parent().hide();
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


       
    </script>


</head>




<div id="main">

 
  
 <div id="twitter-ticker">

    
<form name="search_cat_bar" method="get" action="" style="margin:0px;">
<div class="span12">
<img src="images/socialNetwork/twitter_64.png" width="48" height="48" alt="Twitter icon" class="thumbnail pull-left"/>
<div class="span10">
    <label class="radio inline">
    <input name="party" value="party" type="radio" id="rparty"  onclick="getPartyName()" > Party
    </label>
    <label class="radio inline">
    <input name="party" value="party" type="radio" id="rpolitician" onclick="getPartyName()">Leader
    </label>
    <label class="radio inline">
	<input name="party" value="party" type="radio" id="rall"  checked="true" onclick="getPartyName()">All 
    </label>
</div>	

<div class="row-fluid span10">
<div class="input-prepend span12 inline">
              <span class="add-on">Party Name:</span>
             <select name="party" onChange="setTweetUser(this.options[this.selectedIndex].value)"
 id="party" class="span6"></select>
            </div>
			
			<div class="input-prepend span12 inline">
              <span class="add-on">Leader Name:</span>
             <select name="poliician" onChange="setTweetUser(this.options[this.selectedIndex].value)" id="politician"  class="span8" >
			 </select>
            </div>
</div>			
</div>
		<!-- Search : <input type="text" class="searchbox" name="s" value=" search for..." />
		

		<div>
		<label><input name="party" value="party" type="radio" id="rparty" checked="true" onclick="getPartyName()" style="cursor:pointer;">Party</label>    
		
		<label><input name="party" value="party" type="radio" id="rpolitician" onclick="getPartyName()">Leader</label>   

		<label>		
		<input name="party" value="party" type="radio" id="rall" onclick="getPartyName()" style="cursor:pointer;">All 
		</label> 
		</div> -->


</form>
</div>
    </div>

<div class="scroll-pane">
        <div id="tweet-container"><img id="loading" src="img/loading.gif" width="16" height="11"/></div>
</div>
  

  



<script type="text/javascript">



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
	}else if(allRadioele.checked){
		task="getNames";
	}
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
							else if(jsObj.task=="getNames")
							{
							getAllTweets(myResults);
							}
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
}

 getPartyName();
</script>


