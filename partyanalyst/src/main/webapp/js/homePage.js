
var statesInCountryObj = new Array();
var assemblyResultsArray = new Array();
var parliamentResultsArray = new Array();
var MPTCResultsArray = new Array();
var ZPTCResultsArray = new Array();
var emptyArray = new Array();

var selectedState = '';
var selectedStateId = '';
var localBodyString = '';

function initializeHomePage()
{
	//buildLogoImage();	
	$( "#accordion" ).accordion();
	
	$('#floatingDiv_absolute_main').addFloating(  
	 {  
		 targetRight: 10,  
		 targetTop: 10,  
		 snap: true  
	 });  

	

	//buildElectionTrendzTabView();
	buildHOmePageImageSlider();
	buildHOmePageChartsSlider();
	//var stateEl = document.getElementById("stateList_res");
	//var stateSelectElVal = stateEl.options[stateEl.selectedIndex].value;

	var statelocalEl = document.getElementById("stateList_l");
	var stateSelectlocalElVal = statelocalEl.options[statelocalEl.selectedIndex].value;

	getDistrictsComboBoxForAState(1, 'districtList_d');
	//getRecentElectionsInState(stateSelectElVal);
	//getProblemsInState(stateSelectElVal);
	//buildPolls();
	hideUnhideSelectBox('a_radio', 'constituency');
	getLocalBodiesForState(stateSelectlocalElVal);
	//buildleadersNews();
	//buildTopStoriesNews();
	//buildPartiesNews();
}

function showFeedBackFormPanel()
{		
	if(loginStatus == "false")
	{
		$("#feedback_window").dialog({
			resizable:false,
			width: 400,
			minHeight:110,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("feedback_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Feed Back</div>';
		str += '<div id="feedback_window_body">';
		str += '<table width="100%">';
		str += '<tr>';
		str += '<td width="2%"><img width="7" height="5" src="images/icons/districtPage/listIcon.png"></td>';
		str += '<th width="98%" align="left"><font color="red">You must be logged in to post feedback.</font></th>';
		str += '</tr>';		
		str += '<tr>';
		str += '<td width="2%"><img width="7" height="5" src="images/icons/districtPage/listIcon.png"></td>';
		str += '<th width="98%" align="left"><font color="red">Please login to continue..</font></th>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="40%" align="left"><div id="feedback_window_errorMsg"></div></td>';
		str += '	<td width="60%" align="right">';
		str += '		<input id="loginButton" type="button" value="Login"></input>';
		str += '		<input id="cancelButton" type="button" value="Cancel"></input>';
		str += '	</td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';

		elmt.innerHTML = str;

		var oPushButton1 = new YAHOO.widget.Button("loginButton");  
		var oPushButton2 = new YAHOO.widget.Button("cancelButton");

		oPushButton1.on("click",function(){
			$("#feedback_window").dialog("destroy");
			window.location = "loginInputAction.action";
		});

		oPushButton2.on("click",function(){
			$("#feedback_window").dialog("destroy");
		});
	}
	else
	{
		$("#feedback_window").dialog({
			resizable:false,
			width: 600,
			minHeight:350,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("feedback_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Feed Back</div>';
		str += '<div id="feedback_window_body">';
		str += '	<div id="feedBackNote_div">';
		str += '		<table>';
		str += '		<tr>';
		str += '		<td><img src="images/icons/infoicon.png"></td>';
		str += '		<td>Fields marked with (<font color="red">*</font>) are mandatory</td>';
		str += '		</tr>';
		str += '		</table>';
		str += '	</div>';
		str += '	<div id="feedBackForm_div">';
		str += '		<table id="feedbackTable" width="100%">';
		str += '		<tr>';
		str += '		<th><font color="red">*</font> Select feedback type </th>';
		str += '		<td>';
		str += '			<input type="radio" checked="checked" class="selectWidth" value="1" name="commentType"> Complaint';
		str += '			<input type="radio" class="selectWidth" value="2" name="commentType"> Problem ';
		str += '			<input type="radio" class="selectWidth" value="3" name="commentType"> Praise ';
		str += '			<input type="radio" class="selectWidth" value="4" name="commentType"> Suggestion ';
		str += '		</td>';
		str += '		</tr>';

		str += '		<tr>';
		str += '		<th><font color="red">*</font>Feedback about</th>';
		str += '		<td>';
		str += '			<select id="taskId">';
		str += '			<option value="1">Web Site</option>';
		str += '			<option value="2">Party Analysis </option>';
		str += '			<option value="3">Constituency page</option>';
		str += '			<option value="4">Politician Analysis</option>';
		str += '			<option value="5">Search Analysis</option>';
		str += '			<option value="6">Other</option>';
		str += '			</select>';
		str += '		</td>';
		str += '		</tr>';

		str += '		<tr>';
		str += '		<th><font color="red">*</font>Feedback </th>';
		str += '		<td>';
		str += '			<textarea align="right" id="commentId" rows="5" cols="39" name="comment"></textarea>';
		str += '		</td>';
		str += '		</tr>';

		str += '		<tr>';
		str += '		<th><font color="red">*</font> Select Response type </th>';
		str += '		<td>';
		str += '		      <input type="radio" checked="checked" value="Early" name="responseCategory">Early ';
		str += '		      <input type="radio" value="Late" name="responseCategory">Late';
		str += '		</td>';
		str += '		</tr>';

		str += '		</table>';
		str += '	</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '		<input id="postButton" type="button" value="Post"></input>';
		str += '		<input id="cancelButton" type="button" value="Cancel"></input>';
		str += '	</td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton1 = new YAHOO.widget.Button("postButton");  
		var oPushButton2 = new YAHOO.widget.Button("cancelButton");

		oPushButton1.on("click",function(){
			postFeedbackAjaxCall();
		});

		oPushButton2.on("click",function(){
			$("#feedback_window").dialog("destroy");
		});
	}	
	

}

function postFeedbackAjaxCall()
{
	var errorElmt = document.getElementById("feedback_window_errorMsg");
	
	var feedBackElmt = document.getElementById("commentId");
	feedBackElmtValue = feedBackElmt.value;
   
	if(feedBackElmtValue == "")
	{	
		errorElmt.innerHTML = "Feedback box cannot be empty";
	    return; 
	}
	
	else
		errorElmt.innerHTML = "";
	
   var feedbackTypeElmt = document.getElementsByName("commentType");
   var feedbackAboutElmt = document.getElementById("taskId");
   var responseTypeElmt = document.getElementsByName("responseCategory");
	
	
   var feedbackType = '';
   var feedbackAbout = '';
   var responseType = '';

	feedbackAbout = feedbackAboutElmt.value;
   for(var i=0; i<feedbackTypeElmt.length; i++)
		 if(feedbackTypeElmt[i].checked==true)
             feedbackType = feedbackTypeElmt[i].value;
	
	for(var j=0;j<responseTypeElmt.length;j++)
		if(responseTypeElmt[j].checked == true)
				responseType = responseTypeElmt[j].value;

  var jObj=
         {
			feedback:feedBackElmtValue,
			commentTypeId :feedbackType,
			commentTaskId :feedbackAbout,
			responseType :responseType,
			task : "getComments"
		 };
		 
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
     var url = "userFeedbackSubmitAjaxAction.action?"+rparam;
	 callHomePageAjax(jObj,url);
}

function callHomePageAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == 'getComments')
							{
								showFeedBackStatus(myResults);
							}		
						}
						catch(e)
						{   
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
function closewindow()
{
	$("#feedback_window").dialog("destroy");
}
function showFeedBackStatus(result)
{
	if(result.exceptionEncountered == null)
	{
		var errorElmt = document.getElementById("feedback_window_errorMsg");
			errorElmt.innerHTML = "<font color='green'>Your FeedBack Submitted Successfully.</font>";
		
		setTimeout("closewindow()",2000);
	}
	else
	{
		var errorElmt = document.getElementById("feedback_window_errorMsg");
			errorElmt.innerHTML = "<font color='red'>Sorry,Your FeedBack not Submitted.Please Try again.</font>";
	}
}

function buildHOmePageImageSlider()
{
	var elmt = document.getElementById("homePage_Image_Header");

	var str = '';
	str += '<ul>';
	str += '<li><img width="660" height="390" src="images/icons/homePage_new/slideImg1.png"></li>';
	str += '<li><img width="660" height="390" src="images/icons/homePage_new/slideImg2.png"></li>';
	str += '<li><img width="660" height="390" src="images/icons/homePage_new/slideImg3.png"></li>';
	str += '<li><img width="660" height="390" src="images/icons/homePage_new/slideImg4.png"></li>';
	str += '<li><img width="660" height="390" src="images/icons/homePage_new/slideImg5.png"></li>';
	str += '</ul>';

	elmt.innerHTML = str;
	
	var navArray = ['','','','',''];
	$("#homePage_Image_Header").sudoSlider({ 
			numeric: true,
			fade: true,
			speed:'5000',
			auto:true,
			crossFade: false,
			updateBefore:true,
			prevNext: false,
			startSlide: 1,
			updateBefore: true,			
			numericText:navArray
	   });
}

function buildHOmePageChartsSlider()
{
	var elmt = document.getElementById("homePage_Chart_Header");

	var str = '';
	str += '<ul>';
	str += '<li><img width="700" height="280" src="images/icons/homePage_new/chart_view_1.PNG"></li>';
	str += '<li><img width="700" height="280" src="images/icons/homePage_new/chart_view_4.png"></li>';
	str += '<li><img width="700" height="280" src="images/icons/homePage_new/chart_view_3.png"></li>';	
	str += '<li><img width="700" height="280" src="images/icons/homePage_new/chart_view_5.png"></li>';
	str += '<li><img width="700" height="280" src="images/icons/homePage_new/chart_view_6.png"></li>';
	str += '<li><img width="700" height="280" src="images/icons/homePage_new/chart_view_2.png"></li>';
	str += '</ul>';

	elmt.innerHTML = str;
	
	var navArray = ['','','','','',''];
	$("#homePage_Chart_Header").sudoSlider({ 
			numeric: true,
			fade: true,
			speed:'5000',
			auto:true,
			crossFade: false,
			updateBefore:true,
			prevNext: false,
			startSlide: 1,
			updateBefore: true,			
			numericText:navArray
	   });
}

function openKnowMoreWindow()
{	
	$( "#knowMore_window" ).dialog({
			title:false,
			autoOpen: true,
			show: "blind",
			width: 700,
			minHeight:550,
			modal: true,
			hide: "explode",
			draggable:false,
			buttons: {
						"Close": function() {
								$(this).dialog("destroy"); }
					 }
		});
	$(".ui-dialog-titlebar").hide();


	var elmt = document.getElementById("knowMore_window_inner");

	var str = '';
	str += '<div class="know_more_data_main">';
	str += '<table>';
	str += '<tr>';
	str += '<td valign="top">';
	str += '<img width="115" height="100" style="padding:2px;border:1px solid #767676;-moz-border-radius:8px;" src="images/usergroups/group-of-people.jpg"></td>';
	str += '<td valign="top">';
	str += '<div class="know_more_data_head">Know Your Constituency, Party and Leader</div>';
	str += '<div class="know_more_data_body">Your Constituency, Your Party and Your Leader...None can understand them better than you. No matter in which part of the globe you are, your heart longs remains rooted in your village, your people and your constituency. </div>';
	str += '</td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';

	str += '<div class="know_more_data_main">';
	str += '<table>';
	str += '<tr>';
	str += '<td valign="top"><img width="120" height="100" src="images/icons/homePage_new/connect_people.jpg"></td>';
	str += '<td valign="top">';
	str += '<div class="know_more_data_head">Connect To Your Locality People</div>';
	str += '<div class="know_more_data_body">PartyAnalyst.com offers you a platform for you to connect with people having similar concerns. You can log on to connect with the people from your constituency, to discuss with them the problems plaguing the place, to share with them your views about the political scenario of the constituency.</div>';
	str += '</td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';

	str += '<div class="know_more_data_main">';
	str += '<table>';
	str += '<tr>';
	str += '<td valign="top"><img width="120" height="100" src="images/icons/homePage_new/connect_share.jpg"></td>';
	str += '<td valign="top">';
	str += '<div class="know_more_data_head">Discuss, Interact and Convey</div>';
	str += '<div class="know_more_data_body">Apart from helping you in connecting with people from your constituency, this platform also helps you take part in a wider discussion about the prospects of individual candidates and political parties. Your postings can have a definite impact on the candidates and the political parties.</div>';
	str += '</td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';

	str += '<div style="margin-bottom:5px;">';
	str += '<table>';
	str += '<tr>';
	str += '<td valign="top"><img width="120" height="100" src="images/icons/homePage_new/const_problems.jpg"></td>';
	str += '<td valign="top">';
	str += '<div class="know_more_data_head">Post Your Constituency Problems</div>';
	str += '<div class="know_more_data_body">You can also make use of this platform to post the problems faced by the people of your village, your mandal and your constituency. You can also affirm or condemn the views posted by others. </div>';
	str += '</td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';

	if(elmt)
		elmt.innerHTML = str;

}

function buildPartiesNews()
{
	var options = {
    "format" : "300x250",
	"queryList" : [
          {
            "title" : "",
            "q" : "INC, TDP, TRS, PRP"
          }	
     ],
	"linkTarget" : "_blank"
  }


  var content = document.getElementById('partiesNews');
  var newsShow = new google.elements.NewsShow(content, options);
}

function buildleadersNews()
{	
	var options = {
    "format" : "300x250",
	"queryList" : [
          {
            "title" : "",
            "q" : "Chandra Babu Naidu, Y S Jagan"
          }	
     ],
	"linkTarget" : "_blank"
  }


  var content = document.getElementById('leadersNews');
  var newsShow = new google.elements.NewsShow(content, options);
}

function buildTopStoriesNews()
{
	var options = {
    "format" : "300x250",
	"queryList" : [
          {
            "title" : "",            
			"topic" :  "n",
			"ned"   : "in"
          }	
     ],
	"linkTarget" : "_blank"
  }


  var content = document.getElementById('topStories');
  var newsShow = new google.elements.NewsShow(content, options);
}

function getLocalBodiesForState(stateId)
{
	var jsObj=
	{
			stateId:stateId,
			task:"getLocalBodiesForState"					
	}; 

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocalBodiesTypesInAState.action?"+rparam;						
	homePageAjaxCall(rparam,jsObj,url);
}

function buildLocalBodiesForAState(jsObj,results)
{
	var elmtLabel = document.getElementById("localBodiesRadioDiv_label");
	var elmtData = document.getElementById("localBodiesRadioDiv_data");

	var selectElmtLabel = document.getElementById("localBodiesSelectDiv_label");
	var selectElmtdata = document.getElementById("localBodiesSelectDiv_data");


	if(!elmtLabel || !elmtData || !selectElmtLabel || !selectElmtdata)
		return;
	

	selectElmtLabel.innerHTML = '';
	selectElmtdata.innerHTML = '';

	if(results.length == 0)
		elmtLabel.innerHTML = '';
	else
		elmtLabel.innerHTML = localBodyString;

	var str = '';
	for(var i=0; i<results.length; i++)
	{
		str += '<input type="radio" name="localBodyRadio" onclick="getSelectElmtForLocalBody(this.value)" value="'+results[i].id+'"> '+results[i].name+' </input>';
		if(i == 1)
			str += '<br/>';
	}

	elmtData.innerHTML = str;
}

function navigateToLocalBodyPage()
{
	var errorElmt = document.getElementById("localBodies_errorDiv");
	var stateElmt = document.getElementById("stateList_l");		
	var localBodySelectElmt = document.getElementById("localBodySelectElmt");
	var radioElmts = document.getElementsByName("localBodyRadio");
	var localBodyElectionId;

	if(!stateElmt || !localBodySelectElmt || !radioElmts || radioElmts.length == 0)
		return;
	
	var stateId = stateElmt.options[stateElmt.selectedIndex].value;
	var localBodySelectElmtValue = localBodySelectElmt.options[localBodySelectElmt.selectedIndex].value;
	for(var i=0; i<radioElmts.length; i++)
	{
		if(radioElmts[i].checked == true)
			localBodyElectionId = radioElmts[i].value;
	}
	
	if(localBodySelectElmtValue == 0 && errorElmt)
	{
		errorElmt.innerHTML = '<font color="red" style="font-size:10px;"> Please Select Location.. </font>';
		return;
	}
	else
		errorElmt.innerHTML = '';

	window.location="localBodyElectionAction.action?stateId="+stateId+"&localBodyElectionTypeId="+localBodyElectionId+"&localBodyId="+localBodySelectElmtValue;
}

function getSelectElmtForLocalBody(localBodyId)
{
	var statelocalEl = document.getElementById("stateList_l");
	var stateSelectlocalElVal = statelocalEl.options[statelocalEl.selectedIndex].value;
	
	var jsObj =
		{
			stateId: stateSelectlocalElVal,
			electionTypeId:localBodyId,
			task: "getLocalBodiesSelectElmtForState"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getLocalBodiesInAState.action?"+rparam; 
	homePageAjaxCall(rparam,jsObj,url);	
}

function buildLocalBodiesSelectElmt(jsObj,results)
{
	var labelElmt = document.getElementById("localBodiesSelectDiv_label");
	var dataElmt = document.getElementById("localBodiesSelectDiv_data");

	if(!labelElmt || !dataElmt)
		return;

	var labelStr = '';
	labelStr += 'Select Location';
	labelElmt.innerHTML = labelStr;
	
	var dataStr = '';
	dataStr += '<select id="localBodySelectElmt"></select>';

	dataElmt.innerHTML = dataStr;

	clearOptionsListForSelectElmtId("localBodySelectElmt");
	createOptionsForSelectElmtIdWithSelectOption("localBodySelectElmt",results);
}


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
					str += '<tr><td><input type="radio" name="pollradio" checked=checked value="'+result.quesitons[i].options[j].optionId+'">';
				}else{
					str += '<tr><td><input type="radio" name="pollradio" value="'+result.quesitons[i].options[j].optionId+'">';
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
		str += '<table><tr>';
		str += '<td onclick="getCompletePollResult(\''+questionId+'\')" style="text-decoration:underline;cursor:pointer;padding-right:43px;"> View Result';
		str += '</td>';
		str += '<td onclick="getAllPollsResult()" style="text-align:right;text-decoration:underline;cursor:pointer;"> View Previous Polls';
		str += '</td>';	
		str += '</tr></table>';
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

function showVotesObtainedForOptions(myResults){

	var elmt = document.getElementById("pollsWidgetBody");
	var str = '';
	str += '<table><tr><td>';
	str += '<div id="pollQuestionDiv">Q)'+myResults.question;
	str += '</div>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	str += '<img src="charts/'+myResults.imagePath+'"></img>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	str += '<table><tr>';
	str += '<td onclick="getCompletePollResult(\''+myResults.questionId+'\')" style="text-decoration:underline;cursor:pointer;padding-right:43px;"> view current poll result';
	str += '</td>';
	str += '<td onclick="getAllPollsResult()" style="text-align:right;text-decoration:underline;cursor:pointer;"> view all polls';
	str += '</td>';	
	str += '</tr></table>';
	str += '</tr></table>';
	
	elmt.innerHTML = str;
}

function buildLogoImage()
{
	var elmt = document.getElementById("pa_Logo");

	if(!elmt)
		return;

	var str = '';	
	
	
	if(navigator.appName=="Microsoft Internet Explorer")
		str += '<img width="171" height="84" src="images/icons/homePage_new/logo.gif">';
	else
		str += '<img width="171" height="84" src="images/icons/homePage_new/logo.png">';

	elmt.innerHTML = str;
}


function navigateToStatePage()
{
	var stateSelectEl = document.getElementById("stateList_s");
	var stateSelectElVal = stateSelectEl.options[stateSelectEl.selectedIndex].value
	window.location="statePageAction.action?stateId="+stateSelectElVal; 
}
function navigateToDistrictPage()
{
	var distSelectEl = document.getElementById("districtList_d");
	var alertEl = document.getElementById("alertMessage_district");
	var distSelectElVal = distSelectEl.options[distSelectEl.selectedIndex].value;
	var distSelectElText =  distSelectEl.options[distSelectEl.selectedIndex].text;
	if(distSelectElVal == 0)
	{
		alertEl.innerHTML = 'Please Select District';
		return;
	}
	else
		alertEl.innerHTML = '';
	window.location="districtPageAction.action?districtId="+distSelectElVal+"&districtName="+distSelectElText;
	
}
function navigateToConstituencyPage()
{
	var constSelectEl = document.getElementById("constituency");
	var alertEl = document.getElementById("alertMessage");
	var constSelectElVal = constSelectEl.options[constSelectEl.selectedIndex].value
	alertEl.innerHTML = '';
	if(constSelectElVal == 0)
	{
		alertEl.innerHTML = errotMsg;
		return;
	}
	window.location = "constituencyPageAction.action?constituencyId="+constSelectElVal;
	
}
function hideUnhideSelectBox(radioElement, selectElement)
{
	var tableEl = document.getElementById("constTable");
	var stateTableEl = document.getElementById("stateTable");
	var stateSelectEl = document.getElementById("stateList_c");
	var stateId = stateSelectEl.options[stateSelectEl.selectedIndex].value;
	var alertEl = document.getElementById("alertMessage");
	alertEl.innerHTML = '';
	if(radioElement == 'a_radio')
	{
		
		if(stateTableEl.style.display == 'none')
		{
			stateTableEl.style.display = 'block';
		}
		if(tableEl.style.display == 'none')
		{
			tableEl.style.display = 'block';
		}
		/*election type 2 for mla const*/
		getAllConstituenciesInStateByType(2,stateId,selectElement);
	} else if(radioElement == 'p_radio')
	{
		 /*election type 1 for mla const*/
		
		if(stateTableEl.style.display == 'block')
		{
			stateTableEl.style.display = 'none';
		}
		if(tableEl.style.display == 'none')
		{
			tableEl.style.display = 'block';
		}
		getAllParliamentConstInCountry(selectElement);
	}
	 
}
function getProblemsInState(stateId)
{
	var jsObj = {
			stateId: stateId,
			task: "getProblemsInState"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
		var url = "getProblemsInState.action?"+rparam; 
		homePageAjaxCall(rparam,jsObj,url);
}
function getRecentElectionsInState(stateId)
{
			var jsObj = {
					stateId: stateId,
					task: "getRecentElectionsInState"
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
				var url = "getRecentElectionsInState.action?"+rparam; 
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
										showVotesObtainedForOptions(myResults.questionsOptionsVO);
									}else{
										buildNewPoll(myResults);
									}
								}
								else if(jsObj.task == "saveSelectedPoll")
								{									
									showVotesObtainedForOptions(myResults);
								} 
								else if(jsObj.task == "getLocalBodiesForState")
								{
									buildLocalBodiesForAState(jsObj,myResults);
								}
								else if(jsObj.task == "getLocalBodiesSelectElmtForState")
								{
									buildLocalBodiesSelectElmt(jsObj,myResults)
								}
								
						}
						catch (e)
							{   
							  // 	alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            	  			// alert( "Failed to load result" + o.status + " " + o.statusText);
			                         }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}



function buildElectionTrendzTabView()
{
	var tabView = new YAHOO.widget.TabView(); 
	
	var content = '';
	content += '<div id="stateSelectionDiv">';
	content += '<table>';
	content += '	<tr>';
	content += '		<th style="color:#213540"> Recent Election Results In :</th>';
	content += '		<td>';
	content += '		<select id="stateList_res" onchange="getRecentElectionsInState(this.options[this.selectedIndex].value)">';
	for(var i in statesInCountryObj)
		content += '		<option value='+statesInCountryObj[i].id+'>'+statesInCountryObj[i].name+'</option>';
	content += '		</select>';
	content += '</td>';
	content += '	</tr>';
	content += '</table>';
	content += '</div>';
	
	var content1 = '';
	content1 += content;
	content1 += '<div id="assemblyElectionTrendzDiv_main">';
	content1 += '<div style="padding:10px;"><center><img width="16" height="11" src="images/icons/partypositions.gif"></center></div>';
	content1 += '</div>';

	var content2 = '';
	//content2 += content;
	content2 += '<div id="parliamentElectionTrendzDiv_main">';
	content2 += '<div style="padding:10px;"><center><img width="16" height="11" src="images/icons/partypositions.gif"></center></div>';
	content2 += '</div>';


	tabView.addTab( new YAHOO.widget.Tab({
        label: 'Assembly',
        content: content1,
        active: true
    }));

    tabView.addTab( new YAHOO.widget.Tab({
        label: 'Parliament',
        content:content2 

    }));
	
	tabView.appendTo('electionTrendzDiv_main'); 

	var tab0 = tabView.getTab(0);
	var tab1 = tabView.getTab(1);

	tab0.addListener('click', handleClick);
	tab1.addListener('click', handleClick);

	function handleClick(e) {  
		if(e.originalTarget.innerHTML == "Assembly")
		{			
			buildResultsHTMLTable(assemblyResultsArray,"assemblyElectionTrendzDiv_main","Assembly");
		}
		else if(e.originalTarget.innerHTML == "Parliament")
		{			
			buildResultsHTMLTable(parliamentResultsArray,"parliamentElectionTrendzDiv_main","Parliament");  
		}
	}
}
function showResults(results)
{
	assemblyResultsArray = new Array();
	parliamentResultsArray = new Array();
	if(results.length == 0 || results == null)
		elmt.innerHTML = 'No Results To Display';

	var stateSelectEl = document.getElementById("stateList_res");
	var stateSelectElVal = stateSelectEl.options[stateSelectEl.selectedIndex].text;
	var stateSelectElId = stateSelectEl.options[stateSelectEl.selectedIndex].value;

	selectedState = stateSelectElVal;
	selectedStateId = stateSelectElId;

	for(var i=0;i<results.length;i++)
	{
		if(results[i].electionType == "Assembly")
			assemblyResultsArray.push(results[i]);
		else if(results[i].electionType == "Parliament")
			parliamentResultsArray.push(results[i]);
		else if(results[i].electionType == "MPTC")
			MPTCResultsArray.push(results[i]);
		else if(results[i].electionType == "ZPTC")
			ZPTCResultsArray.push(results[i]);
	}

	buildResultsHTMLTable(assemblyResultsArray,"assemblyElectionTrendzDiv_main","Assembly");
		
	
}

function buildResultsTableForElectionType(elecType)
{
	if(elecType == 'assembly')
		buildResultsHTMLTable(assemblyResultsArray);
	else if(elecType == 'parliament')
		buildResultsHTMLTable(parliamentResultsArray);

}

function buildResultsHTMLTable(arr,divId,elecType)
{
	var elmt = document.getElementById(divId);
	
	if(!elmt)
		return;
	var navigationArr = new Array();

	var str = '';	
	str += '<div id="jQuerySliderDiv_'+elecType+'" class="slider yui-skin-sam">';
	str += '<ul>';
	for(var i=0;i<arr.length;i++)
	{
		navigationArr.push(''+(i+1));
		str += ' <li>';
		str += '<div class="resultsHeading">';
		str += '<table>';
		str += '<tr>';
		str += '<td><img width="8" height="9" src="images/icons/indexPage/listIcon.png"/></td>';
		if(elecType == "Assembly")
		{
			if(arr[i].electionSubtype == "BYE")
				str += '<td>'+selectedState+' '+arr[i].electionType+' ['+arr[i].electionSubtype+'] Election Results In Year '+arr[i].year+'</td>';
			else
				str += '<td>'+selectedState+' '+arr[i].electionType+' Election Results In Year '+arr[i].year+'</td>';
		}
		else if(elecType == "Parliament")		
		{
			if(arr[i].electionSubtype == "BYE")
				str += '<td>'+arr[i].electionType+' ['+arr[i].electionSubtype+'] Election Results In Year '+arr[i].year+'</td>';
			else
				str += '<td>'+arr[i].electionType+' Election Results In Year '+arr[i].year+'</td>';
		}
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '<div id="electionTrendzBodyDiv_'+elecType+'_'+i+'" >';
		str += '<table id="electionTrendzBodyTable_'+elecType+'_'+i+'">';
		for(var j=0;j<arr[i].partyResultsVO.length;j++)
		{
			if(j == 6)
				break;
			str += '<tr>';
			str += '<td>'+arr[i].partyResultsVO[j].partyName+'</td>';
			str += '<td>'+arr[i].partyResultsVO[j].totalSeatsWon+'</td>';
			str += '<td> <img height="30" width="40" src="images/party_flags/'+arr[i].partyResultsVO[j].partyFlag+'"></td>';
			str += '</tr>';			
		}
		str += '</table>';
		str += '</div>';
		str += '<div id="electionTrendzBodyDiv_'+elecType+'_'+i+'_footer" style="padding:5px;">';
		str += '	<a href="javascript:{}" onclick="showElectionResults('+i+',\''+elecType+'\')" class="viewAncs">View All Party Results</a>';
		str += '		|';
		str += '	<a class="viewAncs" href="electionDetailsReportAction.action?electionId='+arr[i].electionId+'&stateID='+selectedStateId+'&stateName='+selectedState+'&electionType='+arr[i].electionType+'&electionTypeId='+arr[i].electionTypeId+'&year='+arr[i].year+'">Analyze</a>';	
		str += '</div>';
		str += '</li>';
	}
	str += '</ul>';
	str += '</div>';

	elmt.innerHTML = str;
		
	for(var i=0;i<arr.length;i++)
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("electionTrendzBodyTable_"+elecType+"_"+i));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "totalSeatsWon",parser:"number"
			}, {
				key : "partyFlag"
			}]
		};

		var resultsColumnDefs = [ {
			key : "partyName",
			parser:"number",
			label : "Party",
			sortable : true
		}, {
			key : "totalSeatsWon",
			label : "Seats Won",
			sortable : true
		}, {
			key : "partyFlag",
			label : "Flag",
			sortable : false
		}];	

		/*var myConfigs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 5
			})
		};*/
		var myDataTable = new YAHOO.widget.DataTable("electionTrendzBodyDiv_"+elecType+"_"+i,resultsColumnDefs, resultsDataSource);  
	}
	
	buildSlider(navigationArr,elecType);
}

function buildSlider(navArray,elecType)
{
	
		$("#jQuerySliderDiv_"+elecType).sudoSlider({ 
			numeric: true,
			fade: true,
			speed:'6000',
			auto:true,
			crossFade: false,
			updateBefore:true,
			prevNext: false,
			startSlide: 1,
			updateBefore: true,			
			numericText:navArray
	   });
	

}

function showElectionResults(index,elecType)
{	
	if(elecType == "Assembly")
		results = assemblyResultsArray[index].partyResultsVO;
	else if(elecType == "Parliament")
		results = parliamentResultsArray[index].partyResultsVO;


	var str='';
	str+='<div id="elecResultsDiv" class="yui-skin-sam">';
	str+='<table id="elecResultsTab">';
	for(var item in results)
	{			
		str+='<tr>';
		str+='<td>'+results[item].partyName+'</td>';
		if(results[item].partyFlag)
			str+='<td><img src="images/party_flags/'+results[item].partyFlag+'" height="30" width="40"/></td>';
		else	
			str+='<td><img src="images/party_flags/no_Image.png" height="30" width="40"/></td>';
		str+='<td align="center">'+results[item].totalSeatsWon+'</td>';
		str+='</tr>';
	}
	str+='</table>';
	str+='</div>';
	
	var myPanel = new YAHOO.widget.Panel("electionResultsPopupDiv_inner", {			
			 width:"500px",
			 fixedcenter: true, 
			 constraintoviewport: true, 
			 underlay: "none", 
			 close: true, 
			 visible: true, 
			 draggable: true
   });
   myPanel.setHeader("Election Results ..");
   myPanel.setBody(str);
   myPanel.render();
	   

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("elecResultsTab"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "partyName"
		},{
			key : "partyFlag"
		}, {
			key : "totalSeatsWon",parser:"number"
		}]
	};

	var resultsColumnDefs = [ {
		key : "partyName",
		label : "PARTY NAME",
		sortable : true
	},{
		key : "partyFlag",
		label : "PARTY Flag"
	}, {
		key : "totalSeatsWon",
		label : "SEATS WON",
		sortable : true
	}];

	
	var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    }) 
				};	

   	var myDataTable = new YAHOO.widget.DataTable("elecResultsDiv",resultsColumnDefs, resultsDataSource,myConfigs);  
}

//to validate QuickRequest fields
function validateQuickRequest(){
		var errorMsg='';
		var name=document.getElementsByName("name")[0].value;
		var email=document.getElementsByName("email")[0].value;
		var mobile=document.getElementsByName("mobileNO")[0].value;
		var requirement=document.getElementsByName("requirement")[0].value;
		var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
        var alphaExp = /^[a-zA-Z\s]+$/;

	if(name==""){
		errorMsg += "Name is Required<br>";
	}
	else if(!name.match(alphaExp) || name.charAt(0)==" "){
	
		errorMsg += "Name may consist of a-z, A-Z, begin with a letter.<br>";
	}
	if(email==""){
		errorMsg += "Email is Required<br>";
	}
	else if(!email.match(emailExp)){
		errorMsg += "Invalid Email<br>";
	}
	if(mobile==""){
		errorMsg += "MobileNo is Required<br>";
	}
	else if(isNaN(mobile)||mobile.indexOf(" ")!=-1||mobile.length>10 || mobile.length<10||(!(mobile.charAt(0)=="9" || mobile.charAt(0)=="8" || mobile.charAt(0)=="7")))
	{
		errorMsg+= "Invalid MobileNo<br>";
	}
	if(requirement==""){
		errorMsg += "Requirement is Required<br>";
	}
    else if(!requirement.match(alphaExp)){
		errorMsg += "Requirement should not contain Special Characters and Numbers";
	}
	return errorMsg;
	}

	//to create jquery dialog box for quick request
	function submitDialogBox(){
		
	var errorMsg=validateQuickRequest();
	$("#quickRequest_window").dialog({
			resizable:false,
			width: 400,
			minHeight:110,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("quickRequest_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Quick Request</div>';
		str += '<div id="feedback_window_body">';
		str += '<table width="100%">';
		str += '<tr>';
		
		if(errorMsg==''){
		str += '<th width="98%" align="left">Your request sent successfully.</th>';
		}
		else{
		str += '<th width="98%" align="left">';
		str +='<div id="quickRequestErrDiv">';
		str += errorMsg;
		str +='</div>';
		str+='</th>';
		}
		str += '</tr>';		
		str += '</table>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '<table width="100%">';
		str += '<tr>';
		str += '<input id="okButton" type="button" value="Ok"></input>';
		str += '</td>';
		str += '</tr>';
		str += '</table>';	
		str += '</div>';

		elmt.innerHTML = str;

		var oPushButton2 = new YAHOO.widget.Button("okButton");
			oPushButton2.on("click",function(){
			$("#quickRequest_window").dialog("destroy");
		});

	}
	