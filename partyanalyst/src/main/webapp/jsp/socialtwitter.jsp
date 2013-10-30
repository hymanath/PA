<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="refresh" content="300" />

<title>A jQuery Twitter Ticker | Tutorialzine demo</title>

<link rel="stylesheet" type="text/css" href="styles/socialnetwork/demo.css" />
<link rel="stylesheet" type="text/css" href="styles/socialnetwork/jScrollPane.css" />
<link rel="stylesheet" type="text/css" href="jquerySlider.css" />

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<style>
.jScrollPaneContainer{
	height:446px !important;
}
</style>
<script src="jquery.js"></script>
<script>



 $(function(){

     $('input:radio').bind('change', function(){
     $('#party').attr('disabled', !$("#rparty").is(":checked"));
	 $('#politician').attr('disabled', !$("#rpolitician").is(":checked"));
	
  });
 
        });


       
    </script>


</head>

<body>


<div id="main">

 
  
 <div id="twitter-ticker">
    <div id="top-bar">
	     
        
      <div id="twitIcon"><img src="images/socialNetwork/twitter_64.png" width="64" height="64" alt="Twitter icon" /></div>
    
        <h2 class="tut"> Tweets From Parties and Leaders </h2>
	     
        </div>
		
		<!--Search : <input type="text" class="searchbox" name="s" value=" search for..." />-->
		<form name="search_cat_bar" method="get" action="">
 <table><tr> <td> <div class="twits1">
<pre><input name="party" value="party" type="radio" id="rparty" checked="true" onclick="getPartyName()" style="cursor:pointer;">&nbsp;Party     <input name="party" value="party" type="radio" id="rpolitician" onclick="getPartyName()" style="cursor:pointer;">&nbsp;&nbsp;Leader      <input name="party" value="party" type="radio" id="rall" onclick="getPartyName()" style="cursor:pointer;">&nbsp;All </pre>
</div>
</td> </tr></table>
<table>
<tr><td>
 Party Name:&nbsp; <select name="party" onChange="setTweetUser(this.options[this.selectedIndex].value)"
 id="party" style="width:100px;" >

</select></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Leader Name: &nbsp;<select name="politician" onChange="setTweetUser(this.options[this.selectedIndex].value)" id="politician" style="width:150px;">

</select> </td></tr>
</table></center>
</form>

       
        <div id="tweet-container"><img id="loading" src="img/loading.gif" width="16" height="11"/></div>
        
        <div id="scroll"></div>
    </div>

  
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
		callHomePageAjax(jsObj,url);
	}

function callHomePageAjax(jsObj,url)
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

</body>
</html>

