

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


function callAjaxToGetQuestionsDetails(voteStatus,questionId){
	


	var jsObj =
			{				
			   questionId:questionId,
				task:"getPollDetails"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getQuestionAndPercentageOfVotesForChoices.action?"+rparam;						
		callAjax(voteStatus,jsObj,url); 

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

	str+='<div class=""><p class="question"><b>'+result.question+'</b></p>';	


	//str+='<p class="" style="margin-bottom:3px;"><b>'+result.question+'</b></p>'; 

	str+='<div id="qstnDiv1" style="margin-top:3px;">';	


	str+='<p style="margin-bottom: 0px; padding-bottom: 0px;" class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	
	str+='<ul class="span12" >';
	for(var i=0;i<result.options.length;i++){   
		
          str+='<li class="widget-block">';
		str+='<h6 style="margin:1px;">'+result.options[i].option+'</h6>';

		str+='<div>';
		str+='<div class="span9 pull-left">';

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

  
		
	    str+='<div class="pager" style="margin-top:25px;">';
		str+='<a href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn btn-mini" style="float:left;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		


		str+='<a href="getAllPollsAction.action" class="btn btn-mini" style="float:right;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		str+='</div>';
	
	

	elmt.innerHTML=str;

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
									   $('#alreadyVotedMsg').text("You are already voted.").css(cssObj).show().delay(2000).fadeOut(400);

										

									}							
															
								 
							displayPollResult(questionId);
													
								
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


function homePageProblemAjaxCall(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == 'getProblemDetails')
							{
								showFeedBackStatus(myResults);
							}		
						}
						catch(e)
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


function showFeedBackStatus(result)
{
	var str = '';
	var probEle = document.getElementById("problemsShowDIV");
	for(var i in result)
	{
		str += '<div class ="widget-block">';
				
		str+='<a title="Click Here To View Problem Complete details" class ="problemTitleClass" href="completeProblemDetailsAction.action?problemId='+result[i].problemId+'" ><h6>'+(result[i].problem)+'</h6></a>';
		
		str+='<div><div class="star"></div><input type="hidden" style="display:none;" value='+result[i].averageRating.avgRating +'" >';
		
		str+='</div>';
		
		if(result[i].url != null){
	     str += '<span >Posted by: '+initialCap(result[i].name)+' '+initialCap(result[i].lastName)+'</span>,  <a title="Click Here To View  '+initialCap(result[i].problemLocation)+' '+initialCap(result[i].impactLevel)+' Details, Election Results and Different Parties Performances" href="'+result[i].url+'" class="label"> '+initialCap(result[i].problemLocation)+'</a>';
	   }else{
	     str += '<p>'+initialCap(result[i].problemLocation)+ initialCap(result[i].impactLevel)+'</p>'+initialCap(result[i].name)+' '+initialCap(result[i].lastName);
	   }
		
		str += '</div>';
	}
	probEle.innerHTML = str;

}

function initialCap(data) {
   data = data.substr(0, 1).toUpperCase() + data.substr(1).toLowerCase();
   return data;
}

function showNotLogIn()
{
   document.getElementById("logInDiv").style.display='block';
			var str='';
		$("#logInDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="container"><h4><div style="margin: 10px;color:ActiveCaption;">Only Registered Users Can Post Problems.</div></h4><h5 style="color:#000;display:inline;position:relative;top:0px;"><div style="margin: 10px;"> Already a member ,   Click here to <a style="color:red;" href="loginInputAction.action">Login</a></div><div style="margin-left:160px;">(OR)</div><div style="margin: 10px;margin-top:-20px;">Not a member, Click here for <a style="color: Red; width: 114px; height: 8px;" href="freeUserRegistration.action"> FREE REGISTRATION <span style="margin-bottom: 20px;"><img src="images/specialPage/freeUser.png"></span></a></div></h5></div>';
		document.getElementById("logInDiv").innerHTML = str;
   

}
var uname = '${sessionScope.USER.userName}';
var emailId='${sessionScope.USER.email}';
var changedUserName='${sessionScope.changedUserName}';
var loadingFirstTime = '${sessionScope.loadingFirstTime}';


function viewParty(value){
	
}



$(document).ready(function(){

 $('#my-jqCarousel-3 ul li').width($('#my-jqCarousel-3').width()/3.2);

 $('#my-jqCarousel-3').jqCarousel({
				itemsPerPage: 3,
				itemsPerTransition: 3,
				easing: 'linear',
				noOfRows: 1
			});
		 $('#my-jqCarousel-3 .mask').height($('#my-jqCarousel-3 ul li').height()+10);	
			
$('#my-jqCarousel-news').jqCarousel();

$(".news-paginate .prev-sl").click(function(){
$("#my-jqCarousel-news .prev").trigger("click");
});


$(".news-paginate .next-sl").click(function(){
$("#my-jqCarousel-news .next").trigger("click");
});
$("#my-jqCarousel-news ul li").width($("#my-jqCarousel-news").width());

/* Photo Carausel */

$("#my-jqCarousel-1 ul li").width($("#my-jqCarousel-1").width());
$('#my-jqCarousel-1').jqCarousel();

$(".photogallerynav .next-nav").click(function(){
$("#my-jqCarousel-1 .next").trigger("click");
});

$(".photogallerynav .prev-nav").click(function(){
$("#my-jqCarousel-1 .prev").trigger("click");
});

// Automate
setInterval(function(){
if($('.photogallerynav').is(':hidden')) {
var gallerylength=$("#my-jqCarousel-1 .pagination-links").find("li").length-1;
var ind=$("#my-jqCarousel-1 .pagination-links").find(".current").index();
if(gallerylength==ind)
{
$("#my-jqCarousel-1").find("ol.pagination-links").remove();
$("#my-jqCarousel-1").find(".next").remove();
$("#my-jqCarousel-1").find(".prev").remove();
$('#my-jqCarousel-1').jqCarousel();
}
else {$("#my-jqCarousel-1 .next").trigger("click");} 
}
},8000);


/**/


			
	setTimeout("applyRaty()",1000);

});


function applyRaty(){
$('.star').each(function(){
var rating=0;
if($(this).next().val()!="null"){rating= $(this).next().val();}
	$(this).raty({
				half       : true,
	     		precision  : true,
				readOnly	: true,
				score		:rating
				});
});
}

 /* Stop page jumping when links are pressed  */
    $('a[href="#"]').live("click", function(e) {
         return false; // prevent default click action from happening!
         e.preventDefault(); // same thing as above
    });
	
	
function getProblemDetails()
{
	
	var jsObj=
		{		
			task:"getProblemDetails"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getProblemDetailsForHomePageAction.action?"+rparam+"&resultsCount=6&startIndex=0";		
		homePageProblemAjaxCall(jsObj,url);
}