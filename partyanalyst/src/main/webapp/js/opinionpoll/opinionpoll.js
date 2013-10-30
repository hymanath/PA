<!--OPINION POLL SCRIPTS START-->

function buildPolls()
{	
	var jsObj=
	{
			task:"getAllPolls"					
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPolls.action?"+rparam;						
	homePageAjaxCall(rparam,jsObj,url);
}

function homePageAjaxCall(param,jsObj,url){
	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);								

								if(jsObj.task == "getRecentElectionsInState")
								{									
									showResults(myResults);
								} 
								else if(jsObj.task == "getAllPolls")
								{			
									
								    
									if(myResults.description==null){
										
										//showVotesObtainedForOptions(myResults.questionsOptionsVO);
										if(myResults.questionsOptionsVO != null)
										displayCurrentPollResult(myResults.questionsOptionsVO.questionId);

									}else{										
										//buildNewPoll(myResults);
										buildNewPoll1(myResults);
									}
								}
								else if(jsObj.task == "saveSelectedPoll")
								{	
									
									showVotesObtainedForOptions(myResults);
								} 
									
						}
						catch (e)
							{   
							  	//alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            	  			// alert( "Failed to load result" + o.status + " " + o.statusText);
			                         }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
	function displayCurrentPollResult(questionId){

	

	callAjaxToGetQuestionsDetails("",questionId);
}

function callAjaxToGetQuestionsDetails(voteStatus,questionId){

		

	var jsObj =
			{				
			   questionId:questionId,
				task:"getPollDetails"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getQuestionAndPercentageOfVotesForChoices.action?"+rparam;						
		callAjaxToGetPollDetails(voteStatus,jsObj,url); 

}

function callAjaxToGetPollDetails(voteStatus,jsObj,url){


	var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

			
			if(jsObj.task == "getPollDetails"){					
				
				buildResultForPoll(voteStatus,myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResultForPoll(voteStatus,result){

	var elmt = document.getElementById("pollsWidgetBody");

	var str='';
  
	str+='<div class="opinionpoll">';
	str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:1px 0px 2px #888;margin-bottom:5px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion polls</span></h3>';

	str+='<div class="breadcrumb"><p style="padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;"><b>'+result.question+'</b></p>';

	str+='<p class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	str+='<ul style="padding:0px ;margin-bottom:5px;">';
	for(var i=0;i<result.options.length;i++){ 
		
	 str+='<li>';
	   str+='<h5>'+result.options[i].option+'</h5>';

		str+='<div>';
		str+='<div class="span2 pull-left" style="margin-left:0px;">';

			str+='<div class="progress" style="margin:0px;">';
			  str+='<div id="option1" class="bar" style="width:'+result.options[i].percentage+'%"></div>';
			str+='</div>';							
			str+='</div>	';

			str+='<span class="label  label-info" style="margin-left:6px;">'+result.options[i].percentage+'% </span>';
	  str+= '</div>';
	   str+='</li>';
	}  
  str+='</ul>';	
	 if(voteStatus == "vote")

        str+='<a href="javaScript:{buildNewPoll1(result1)}");" class="btn btn-primary" title="Click Here To Vote" style="margin:10px 0px 0px 88px;">Vote Now</a>';
  	    str+='</div>';
		
	    str+='<div class="pager">';
		str+='<a class="btn" style="margin-left:-11px;" href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn" style="float:left;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		str+='<a class="btn" href="getAllPollsAction.action" class="btn" style="float:right;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		

		str+='</div>';

	  elmt.innerHTML=str;


}

function getCompletePollResult(questionId){  
	var browser1 = window.open("completeResultForAPollAction.action?questionId="+questionId,"completeResultForAPoll","scrollbars=yes,height=350,width=450,left=200,top=200");
	browser1.focus();
}

function getAllPollsResult(){ 
	var browser1 = window.open("getAllPollsAction.action?","allPollResults","scrollbars=yes,height=600,width=650,left=200,top=200");
	browser1.focus();
}

function buildNewPoll(result){
	
	if(result.quesitons!=null){
		var elmt = document.getElementById("pollsWidgetBody");
		if(!elmt)
			return;
		
		var questionId = '';
		var str = '';
		for(var i=0; i<1;i++)
		{
			questionId = result.quesitons[i].questionId;
			str += '<div id="pollQuestionDiv">';
			str += result.quesitons[i].question;
			str += '</div>';
			str += '<div id="pollOptionsDiv">';
			str += '<table>';
			for(var j=0 ; j<result.quesitons[i].options.length; j++){
				if(j==0){
					str += '<tr><td><input type="radio" name="pollradio" checked=checked value="'+result.quesitons[i].options[j].optionId+'">&nbsp;&nbsp;';
				}else{
					str += '<tr><td><input type="radio" name="pollradio" value="'+result.quesitons[i].options[j].optionId+'">&nbsp&nbsp;';
				}				
				str += result.quesitons[i].options[j].option;
				str += '</td></tr>';			
			}
			str += '</table>';
			str += '</div>';
		}
		
		
		str += '<div id="pollSubmitDiv">';
		str += '<div onclick="savePollResult(\''+questionId+'\')" class="viewReportButtonSpan" style="left:">';
		str += '	<span class="viewReportButtonLabel"  style="left:20px;top:5px;">Vote</span>';		
		str += '</div>';
		str += '</div>';
		
		
		str += '<table><tr><td>';
		str += '<div id="viewPollResDiv">';
		str += '<table><tr>';
		str += '<td><a href="completeResultForAPollAction.action?questionId='+questionId+'" style="text-decoration:underline;cursor:pointer;padding-right:43px;color:#3d3d3d;"> View Result</a>';
		str += '</td>';
		str += '<td><a href="getAllPollsAction.action?.action" style="text-align:right;text-decoration:underline;cursor:pointer;color:#3d3d3d;"> View Previous Polls</a>';
		str += '</td>';	
		str += '</tr></table>';
		str += '</div>';
		str += '</tr></table>';

		elmt.innerHTML = str;

	}
}

function savePollResult(questionId){

	var elmts = document.getElementsByName("pollradio");
	var checkedElmtId = '';
	
	for(var i=0; i<elmts.length;i++)
	{
		if(elmts[i].checked == true)
			checkedElmtId = elmts[i].value;
	}
	var jsObj=
	{
			questionId:questionId,
			selectedPollId:checkedElmtId,
			task:"saveSelectedPoll"					
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSelectedPoll.action?"+rparam;						
	homePageAjaxCall(rparam,jsObj,url);	
}

function buildNewPoll1(result){

	result1=result;  

	if(result.quesitons!=null){

		var elmt = document.getElementById("pollsWidgetBody");
		if(!elmt)
			return;

	}


    var str='';


     str+='<div class="opinionpoll">';

		<!--str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:3px 0px 2px #888;margin-bottom:5px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion Poll</span></h3>';-->

		for(var i=0; i<1;i++){

		questionId = result.quesitons[i].questionId;

		str+='<div class="breadcrumb"><p class="question"><b>'+result.quesitons[i].question+'</b></p>';	
	
		str+='<div id="qstnDiv1" style="margin:0px-20px;margin-top:3px">';	

		str+='<div class="control-group form-horizontal "><p class="answer">';
		for(var j=0 ; j<result.quesitons[i].options.length; j++){ 

			if(j==0){      

				str +='<label><input type="radio" class="radio" name="pollradio" value="'+result.quesitons[i].options[j].optionId+'" checked="true">';
				str +=result.quesitons[i].options[j].option+"</label>"; 

				
			}else{
				str +='<label><input type="radio" class="radio"  name="pollradio" value="'+result.quesitons[i].options[j].optionId+'">';
				str+=result.quesitons[i].options[j].option+"</label>";
			}
			
		}
		}
		str+='</p><a href="javaScript:saveCurrentPollResult(\''+questionId+'\');" style="margin:0px auto;display:block;width:75px;" class="btn btn-primary votebtn" title="Click Here To Vote">Vote</a>';
		
		str+='<p class="resultdisplay"><a  style="float:left;" class="previouslink" href="javaScript:{callAjaxToGetQuestionsDetails(\'vote\',\''+questionId+'\')}" title="Click Here To See This Poll Result">View Results</a>';

		    
		str+=' <a class="nextlink" style="float:right;" href="completeResultForAPollAction.action?questionId='+questionId+'&comments=getComments" title="Click Here To See Comments On This Poll" >View Comments</a></p>';
		str+=' </div></div>';

		
		str+='</div>';
		str+='<div class="widget-block" style="padding:2px;height:30px;border:0px;">';
   
        str+='<a href="completeResultForAPollAction.action?questionId='+questionId+'&comments=getComments"  class="btn btn-mini" title="Post Your Comment On This Poll"  style="float:left;margin-left:3px;">Post Your Comment</a>';
 
  
    str+='<a   style="float:right;margin-right:3px;"" class="btn btn-mini" href="getAllPollsAction.action"  title="Click Here To View Rececnt Poll Details" >View Recent Polls </a>';
   
    str+='</div>';
	elmt.innerHTML = str;	

	}
	
	function saveCurrentPollResult(questionId){

	var elmts = document.getElementsByName("pollradio");
	var checkedElmtId = '';
	
	for(var i=0; i<elmts.length;i++)
	{
		if(elmts[i].checked == true)
			checkedElmtId = elmts[i].value;
	}
	var jsObj=
	{
			questionId:questionId,
			selectedPollId:checkedElmtId,
			task:"saveSelectedPoll"					
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSelectedPoll.action?"+rparam;						
	callAjaxToSaveSelectedPollDetails(rparam,jsObj,url,questionId);	
}


function callAjaxToSaveSelectedPollDetails(param,jsObj,url,questionId){

	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								 
								 if(myResults.availability == true){

									 var cssObj = {    
										'font-weight' : 'bold',
										'color' : 'green'
								      }
									   $('#alreadyVotedDivId').text("You are already voted.").css(cssObj).show().delay(2000).fadeOut(400);
										
								 }
							     displayCurrentPollResult(questionId);						
													
								
						}
						catch (e)
							{   
							  	//alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function displayCurrentPollResult(questionId){

	

	callAjaxToGetQuestionsDetails("",questionId);
}

function callAjaxToGetQuestionsDetails(voteStatus,questionId){

		

	var jsObj =
			{				
			   questionId:questionId,
				task:"getPollDetails"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getQuestionAndPercentageOfVotesForChoices.action?"+rparam;						
		callAjaxToGetPollDetails(voteStatus,jsObj,url); 

}

function callAjaxToGetPollDetails(voteStatus,jsObj,url){


	var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

			
			if(jsObj.task == "getPollDetails"){					
				
				buildResultForPoll(voteStatus,myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResultForPoll(voteStatus,result){


	var elmt = document.getElementById("pollsWidgetBody");

	var str='';
  
	str+='<div class="opinionpoll">';
	<!--str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:1px 0px 2px #888;margin-bottom:5px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion polls</span></h3>';-->

	str+='<div class="breadcrumb"><p style="padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;"><b>'+result.question+'</b></p>';

	str+='<p class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	str+='<ul style="padding:0px ;margin-bottom:5px;">';
	for(var i=0;i<result.options.length;i++){ 
		
	 str+='<li style="width: 100%; clear: both;">';
	   str+='<h6>'+result.options[i].option+'</h6>';

		str+='<div>';
		str+='<div class="span8 pull-left" style="margin-left:0px;">';

			str+='<div class="progress" style="margin:0px;">';
			  str+='<div id="option1" class="bar" style="width:'+result.options[i].percentage+'%" ></div>';
			str+='</div>';							
			str+='</div>	';

			str+='<span class="label  label-info" style="margin-left:6px;">'+result.options[i].percentage+'% </span>';
	  str+= '</div>';
	   str+='</li>';
	}  
  str+='</ul>';	
	 if(voteStatus == "vote")

        str+='<a href="javaScript:{buildNewPoll1(result1)}");" class="btn btn-primary" title="Click Here To Vote" style="margin:10px 0px 0px 65px;">Vote Now</a>';
  	    str+='</div>';
		
	    str+='<div class="widget-block" style="padding:2px;height:30px;border:0px;">';
		str+='<a class="btn btn-mini" style="float:left;margin-left:3px;" href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn" style="float:left;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		str+='<a class="btn btn-mini" href="getAllPollsAction.action" class="btn" style="float:right;margin-right:3px;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		

		str+='</div>';

	  elmt.innerHTML=str;


}
function displayPollResult(questionId){
	callAjaxToGetQuestionsDetails("",questionId);
}

function afterRefreshOpinionPOll()
{
var elmt = document.getElementById("pollsWidgetBody");
	var str = '';
	str += '<table><tr><td>';
	str += '<div id="pollQuestionDiv" > Q) '  +'${opinionPollVO.questionsOptionsVO.question}';
	str += '</div>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	//str += '<img src="charts/'+myResults.imagePath+'"></img>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	str += '<div id="viewPollResDiv">';
	str += '<table style="margin-left: 26px;"><tr>';
	str += '<td><div style="width:157px;"><a href="completeResultForAPollAction.action?questionId=${opinionPollVO.questionsOptionsVO.questionId}" style="cursor: pointer; color:#0C67AC; font-weight: bold; text-decoration: none; background:#e3e3e3; padding: 4px; border-radius: 5px; margin: 2px;"> View Current Poll Result</a></div>';
	str += '</td>';
	str += '<td><div style="width:94px;"><a href="getAllPollsAction.action?.action" style="text-align: right; cursor: pointer; color:#0C67AC; font-weight: bold; text-decoration: none; background:#e3e3e3; padding: 4px; border-radius: 4px;"> View All Polls</a></div>';
	str += '</td>';	
	str += '</tr></table>';
	str += '</div>';
	str += '</tr></table>';
	

	str+='<div id="pollsChart" style=" height: auto;width: 324px; overflow: hidden;"></div>';
	elmt.innerHTML = str;


var arrData = pollStatus;

	var data = new google.visualization.DataTable();
	data.addColumn('string','option');
	data.addColumn('number','votesObtained');
		
	data.addRows(arrData.length);

		for(var j=0; j<arrData.length; j++)
		{
			
			data.setValue(j,0,arrData[j].option);
			data.setValue(j,1,arrData[j].votesObtained);
			
		}
			var chart = new google.visualization.LineChart(document.getElementById('pollsChart'));
	
	chart.draw(data,{width: 300, height: 280,legend:'right', 
	legendTextStyle:{fontSize:12},title:'${opinionPollVO.questionsOptionsVO.title}',titleTextStyle:{fontName:'verdana',fontSize:9}});
	
}

function callAjax(voteStatus,jsObj,url)
{
	
		var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

		    
			if(jsObj.task == "getPollDetails"){				
				buildResult(voteStatus,myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResult(voteStatus,result){

	var elmt = document.getElementById("pollsWidgetBody");

	var str='';
  
	str+='<div class="opinionpoll">';

	/*str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:3px 0px 2px #888;margin-bottom:5px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion Poll</span></h3>';*/

	str+='<div class="breadcrumb"><p class="question"><b>'+result.question+'</b></p>';	


	//str+='<p class="" style="margin-bottom:3px;"><b>'+result.question+'</b></p>'; 

	str+='<div id="qstnDiv1" style="margin-top:3px;">';	


	str+='<p style="margin-bottom: 0px; padding-bottom: 0px;" class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	
	str+='<ul style="margin:0px;width:100%;">';
	for(var i=0;i<result.options.length;i++){   
		
          str+='<li style="width:100%;clear:both;">';
		str+='<h6 style="margin:1px;">'+result.options[i].option+'</h6>';

		str+='<div>';
		str+='<div class="span8 pull-left">';

			str+='<div class="progress" style="margin:0px;">';
			  str+='<div id="option1" class="bar" style="width:'+result.options[i].percentage+'%"></div>';
			str+='</div>';							
			str+='</div>	';

			str+='<span class="label label-info m-left5">'+result.options[i].percentage+'% </span>';
	  str+= '</div>';
	   str+='</li>';
		
	}
	str+='</ul></div>';
	
	 if(voteStatus == "vote")

        str+='<a class="btn btn-primary" href="javaScript:{showVotesObtainedForOpinionPoll()}");" class="btn btn-primary" title="Click Here To Vote" style="margin:10px 0px 0px 104px;">Vote Now</a>';

		str+='</div>';
  
		//str+='</div>';

  
		
	    str+='<div class="widget-block" style="padding:2px;height:30px;border:0px;">';
		str+='<a href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn btn-mini" style="float:left;margin-left:3px;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		


		str+='<a href="getAllPollsAction.action" class="btn btn-mini" style="float:right;margin-right:3px;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		str+='</div>';
	
	

	elmt.innerHTML=str;

}
<!--OPINION POLL SCRIPTS END-->