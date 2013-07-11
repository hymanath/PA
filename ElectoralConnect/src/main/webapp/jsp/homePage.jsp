<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Registration</title>

</head>  
<body>  
<script src="js/jquery.transit.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

<script src="js/bootstrap.min.js"></script>
	
<style>
	#bodyId{width:995px;margin-left:auto;margin-right:auto;}
	
	.sblockRowBg{background:#F0EEEF; padding-bottom:10px;}
			.widget{background:#fcfcfc; width:100%; height:191px;border:1px solid #2DCFFE;}
			.widget:hover{box-shadow:0px 0px 5px #c0c0c0;}
			.widget h3{font-size:17px;line-height:20px;padding:12px  15px 20px;text-align: center;}
			
			.morebtn{bottom:0px; right:0px;background-image:url('image/more_btn.png');width:30px; height:27px;margin-top:-30px;float:right;}
			
			.hero-unit{height:60px;}
			
			.notifications a{color:#1a1a1a; text-decoration:none;}
			.notifications:hover{background:#F0EEEF;border-radius:5px;padding:0px;}
			.title h1{font-size:12px; line-height:18px;color:1a1a1a; padding:5px;background:#fff; border-bottom:1px solid #c0c0c0;}
			.title p{font-size:11px; line-height:15px;padding:5px;background:#fff;min-height:40px;}
			
			.title{margin-left:-4px !important;}
			.title p{margin-top:-9px;}
			.date h2{font-size:9px; line-height:15px;padding:2px 15px 2px 2px;color:#2DCFFE; float:left;}
			#totalCommentsModal{width:600px;}
			
			/*----------5-july---------*/
			.bigsblock{position:relative; display: table;width:100%;background:#ffffff;border:5px solid #2DCFFE; }
			/*.bigsblock:after{ content: "";display: table;position: absolute;bottom: 0px;right: 0px;background-image:url('image/close_btn.png');width:31px; height:28px;cursor: pointer; }*/
			 .borderTop{border-top:1px solid #cccccc;padding-bottom:20px;cursor:default;}
			 #registerVoter,#armiNriVote,#cancelVote,#modifyVote,#modifyVote,#transferVote,#duplicateVoter{display:none;}
			.closeBtn{margin-left: 54px;bottom: 0px; position: absolute;}
			.closeBtn:hover{box-shadow:0px 0px 10px #c0c0c0;}
			.newsReleaseId{width:500px;background:#FFFFFF;margin:2px;padding:8px;display:inline-block;}
			.indiComment{margin:4px;padding:10px;}
			#indiCommentName{margin:2px;}
			#indiCommentDate{margin:2px;color:#28B0D6;}
</style>
<div>
<!--<div id="bodyId">Hello Welcome to Electoral Connect </div>
<span style="float:right;display:none;" id="passwordWindow"><a class="btn btn-primary" id="changePassword" >Change Password</a></span>

<div id="commentDis">
<span>Comment : </span><textarea rows="4" cols="50" style="width: 252px; height: 56px; margin-left: 10px;" id="commentText">
</textarea>
<input type="button" class="btn btn-primary" value="Comment" onClick="saveComment();"></input>
<div id="errorMsg"></div>
</div>

<div>
<div id="topFiveComments"></div>
<div><a onClick="getAllComments(0,5);" class="btn btn-primary">View More</a></div>
</div>

-->
<div id="totalCommentsModal" class="modal hide fade">
		<div class="modal-body">
			<a class="close" data-dismiss="modal">X</a>
			<legend>Comments On this Announcement</legend>
			<div id="totalComments"></div>
			<a id="moreId"></a>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
		</form>
</div>


<section role="main">
			<!----Slider---->
			<section class="row slidesow">
				<div class="container">
					<div class="row-fluid">
					<!-----Slide Show----->
						<div class="carousel span12 slide" id="myCarousel">
							<ol class="carousel-indicators">
							  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
							  <li data-slide-to="1" data-target="#myCarousel" ></li>
							  <li data-slide-to="2" data-target="#myCarousel"></li>
							  <li data-slide-to="3" data-target="#myCarousel" ></li>
							  <li data-slide-to="4" data-target="#myCarousel"></li>
							  <li data-slide-to="5" data-target="#myCarousel" ></li>
							  <li data-slide-to="6" data-target="#myCarousel"></li>
							  <li data-slide-to="7" data-target="#myCarousel"></li>
							  <li data-slide-to="8" data-target="#myCarousel"></li>
							</ol>
							<div class="carousel-inner">
							  <div class="item active">
								<div class="hero-unit small ">
									<h1>Know Latest Updates and Press Releases from Election Commission of AndhraPradesh</h1>
									<h2>- Register Now with Us.</h2>
									<!--<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							   <div class="item">
								<div class="hero-unit small ">
									<h1>Share Your Views on the Latest Updates</h1>
									<h2>- Comment Now on the Updates.</h2>
									<!--<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							  <div class="item ">
								<div class="hero-unit small ">
									<h1>Know your Particulars ?</h1>
									<h2>- Search now with your Name/House No/Voter Id.</h2>
								<!--	<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							  <div class="item ">
								<div class="hero-unit small ">
									<h1>Are you eligible for voting? But, still don't have vote?</h1>
									<h2>- See how to register your vote.</h2>
								<!--	<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							  <div class="item">
								<div class="hero-unit small ">
									<h1>Lost your Voter Id ?</h1>
									<h2>- See how to get Duplicate your vote.</h2>
									<!--<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							  <div class="item">
								<div class="hero-unit small ">
									<h1>Unable to Vote where you had Registered?</h1>
									<h2>- See how to Transfer your vote.</h2>
									<!--<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							   <div class="item">
								<div class="hero-unit small ">
									<h1>Any wrong particulars in your Voter Id ?</h1>
									<h2>- See how to Modify your details.</h2>
									<!--<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							   <div class="item">
								<div class="hero-unit small ">
									<h1>Are you Army/NRI Personnel? Do you know how to Vote?</h1>
									<h2>- See Army/NRI Personnel Voting.</h2>
									<!--<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							  <div class="item">
								<div class="hero-unit small ">
									<h1>Do you/Some other having More than one Vote?</h1>
									<h2>- See how to Cancel Voter Id.</h2>
									<!--<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>-->
								</div>
							  </div>
							   
							</div>
							<!---<a data-slide="prev" href="#myCarousel" class="left carousel-control">&lsaquo;</a>
							<a data-slide="next" href="#myCarousel" class="right carousel-control">&rsaquo;</a>---->
						</div><!-----Slide Show END----->
						
					</div>
				</div>
			</section><!----Slider END---->
			<!----S_Blocks---->
			
			<section>
				<div class="row sblockRowBg">
					<div class="container m_top10">
						<div id="specialBlockDIV" class="row-fluid">	
							<div class="span2 widget" onclick="hideMainMenu('registerVoter')">
							  <img src="image/register_vote.png" />
							  <h3>Register VOTE</h3>
							  <a href="javascript:{}" class="morebtn"></a>
							</div>
							<div class="span2 widget" onclick="hideMainMenu('duplicateVoter')">
							  <img src="image/dublicate_vote.png" />
							  <h3>Duplication VOTE</h3>
							  <a href="javascript:{}" class="morebtn"></a>
							</div>
							<div class="span2 widget" onclick="hideMainMenu('transferVote')">
								<img src="image/Transceive_vote.png" />
								<h3>Transfer VOTE</h3>
								<a href="javascript:{}"  class="morebtn"></a>
							</div>
							<div class="span2 widget" onclick="hideMainMenu('modifyVote')">
								<img src="image/modify_vote.png" />
								<h3>Modify <br>VOTE</h3>
								<a href="javascript:{}"  class="morebtn"></a>
							</div>
							<div class="span2 widget" onclick="hideMainMenu('cancelVote')">
								<img src="image/cancel_vote.png" />
								<h3>Cancel<br> VOTE</h3>
								<a href="javascript:{}"  class="morebtn"></a>
							</div>
							<div class="span2 widget" onclick="hideMainMenu('armiNriVote')">
								<img src="image/NRI_vote.png" />
								<h3>Army & NRI VOTE</h3>
								<a href="javascript:{}" class="morebtn"></a>
							</div>
						</div>
						
						<!---Big Speshal Box 1---Register VOTE--->
						<div id="registerVoter" class="row-fluid bigsblock registerBlock" onclick="showMainMenu('registerVoter')">
							<section class="span10 offset1 text-center">
								<img src="image/register_vote.png" />
								 <h4>Register VOTE</h4>
								 <div class="borderTop">
									<p>House-to-house enumeration is done and electors residing in each house are registered by official enumerators who go physically from door-to-door to collect the information about electors.</p>
									<p>Any eligible person can file claim in <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="image/votersInfo/FORM6.pdf" title="Click here to View Form-6 ( For Registering in the Electoral roll ) Application">Form No. 6 </a>for inclusion of his name in the roll.</p>
							        <a class="inline link btn-inverse btn " style="position:relative;bottom:0px;right:0px;margin-top:10px;margin-bottom:10px;" target="_blank" href="image/votersInfo/Form 6 - Application for inclusion of name in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-6 ( For Registering in the Electoral roll )">How to File Form-6 </a>
						</div>
							</section>	
								<a href="javascript:{}" class="closeBtn float-right"><img src="image/close_btn.png"/></a>
						</div><!---Big Speshal Box 1 ---Register VOTE---END--->
						<!---Big Speshal Box 2---Duplicate Vote--->
						<div id="duplicateVoter" class="row-fluid bigsblock" onclick="showMainMenu('duplicateVoter')">
							<section class="span10 offset1 text-center">
								<img src="image/dublicate_vote.png" />
								 <h4>Duplicate Voter Id</h4>
								 <div class="borderTop">
									<p>By filling in Form 002, the individual applies for a duplicate Voters ID Cards in case he has lost it. But before filling in the form, the individual must first file an FIR in the local police station. After filing an FIR the individual needs to take a copy of the FIR. Along with the form 002 the copy of the FIR must be attached.</p>
									<p>One needs to submit Rs. 25 to the local Electoral Registration Office as penalty. </p>
									<a class="inline link btn-inverse btn" style="position:relative;bottom:0px;right:0px;" target="_blank" href="image/votersInfo/Form002.pdf"  title="Click here to View Form 002 ( For getting Duplicate Voter Id ) Application">form 002</a>
									
								</div>	
							</section>	
							<a href="javascript:{}" class="closeBtn float-right"><img src="image/close_btn.png"/></a>
						</div><!---Big Speshal Box 2---Duplicate Vote--- END--->
						<!---Big Speshal Box 3---Transfer Vote--->
						<div id="transferVote" class="row-fluid bigsblock" onclick="showMainMenu('transferVote')">
							<section class="span10 offset1 text-center">
								<img src="image/Transceive_vote.png" />
								 <h4>Transfer VOTE</h4>
								 <div class="borderTop">
									<p>If any particulars in the electoral roll are to be modified such as name, house number, middle name, last name, age, sex, epic number etc. a claim in <a target="_blank" href="image/votersInfo/Form 8 - Application for correction to particulars entered in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-8( For modifiying the particulars in the registered roll )">Form No. 8</a> can be filed.</p>
									<a  class="inline link btn-inverse btn" target="_blank" href="image/votersInfo/FORM8.pdf" title="Click here to View Form-8 ( For modifiying the particulars in the registered roll )Application">How to File Form No.8</a>
								</div>
							</section>	
							<a href="javascript:{}" class="closeBtn float-right"><img src="image/close_btn.png"/></a>
						</div><!---Big Speshal Box 3---Transfer Vote--- END--->
						<!---Big Speshal Box 4---Modify  Vote--->
						<div id="modifyVote" class="row-fluid bigsblock" onclick="showMainMenu('modifyVote')">
							<section class="span10 offset1 text-center">
								<img src="image/modify_vote.png" />
								 <h4>Modify VOTE </h4>
								 <div class="borderTop">
									<p>In case any elector has changed his house from the polling area of one booth to other booth in the same Assembly Constituency he can file application in <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="image/votersInfo/FORM8A.pdf" title="Click here to View Form-8A ( For Transporting the roll ) Application">Form No. 8A</a> for change/transposition from one electoral part to other part.</p>
									<a target="_blank" href="image/votersInfo/Form 8A - Application for transposition of entry in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-8A ( For Transporting the roll )" class="inline link btn-inverse btn ">How to File Form No.8A</a>
								</div>
							</section>
							<a href="javascript:{}" class="closeBtn float-right"><img src="image/close_btn.png"/></a>
						</div><!---Big Speshal Box 4---Modify  Vote--- END--->
						<!---Big Speshal Box 5---Cancel   Vote--->
						<div id="cancelVote" class="row-fluid bigsblock" onclick="showMainMenu('cancelVote')">
							<section class="span10 offset1 text-center">
								<img src="image/cancel_vote.png" />
								 <h4>Cancel VOTE </h4>
								 <div class="borderTop">
									<p>Any eligible person can raise an objection to somebody's name or for deletion of his or any other person's name using <a target="_blank" href="image/votersInfo/FORM7.pdf" title="Click here to View Form-7( For Deletion roll in Electoral ) Application">Form No. 7.</a></p>
									<a target="_blank" href="image/votersInfo/Form 7 - Application for objecting inclusion or seeking deletion of name in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-7( For Deletion roll in Electoral )" class="inline link btn-inverse btn ">How to File Form No.7</a>
									<h5>Reasons why we should cancel a Duplicate Vote</h5>
									<p><i class="icon-hand-right"></i>  Chances of Misuse of Vote.</p>
									<p><i class="icon-hand-right"></i>  Polling % Decreases.</p>
									
								</div>
							</section>		
							<a href="javascript:{}" class="closeBtn float-right"><img src="image/close_btn.png"/></a>
						</div><!---Big Speshal Box 5---Cancel   Vote--- END--->
						<!---Big Speshal Box 6---Army & NRI Vote--->
						<div id="armiNriVote" class="row-fluid bigsblock" onclick="showMainMenu('armiNriVote')">
							<section class="span10 offset1 text-center">
								<img src="image/NRI_vote.png" />
								 <h4>Army & NRI VOTE</h4>
								 <div class="borderTop">
									<h5>Different Voting Procedures for Army Personnel </h5>
									<b>Proxy</b>
									<p>Any person appointed by a Classified Service Voters(CSV) as his proxy under Rule 27N of Conduct of Election Rules 1961 to give vote on his behalf and in his name. Any person who is eligible to vote and is not disqualified for registration as an elector in an electoral roll under Section 16 of the Representation of the People Act, 1951 (43 of 1950) can be nominated as Proxy by filling up <a target="_blank" href="image/votersInfo/FORM13F&G.pdf" title="Click here to View Form-13F ( For Proxy registration ) Application">Form 13F.</a> (Rule 27 N ) lays down the qualification and procedure for appointment/revocation of the proxy by the (CSV).</p>
									<b>Substitute Proxy</b>
									<p>When a CSV changes the proxy the changed proxy is known as Substitute Proxy. Substitute proxy will be effective from the date of receipt of <a target="_blank" href="image/votersInfo/FORM13F&G.pdf" title="Click here to View Form-13G ( For Substitue-Proxy registration ) Application">Form 13G</a> (as given at appex 'C' attached) by the Returning Officer and will continue to act as the Proxy of the CVS until his/her nomination is revoked by the CSV.</p>
									<h5>NRI Voter Registration Procedure</h5>
									<p>Every Indian citizen staying in a foreign country, who has not acquired citizenship of a Foreign country, and has completed 18 years as on 1st January of the year, can make an Application in <a style="position:relative;bottom:0px;right:0px;" target="_blank" href="image/votersInfo/FORM6A.pdf" title="Click here to View Form-6A ( For NRI'S to register in the Electoral roll ) Application">Form 6A</a> for being registered in the roll for the constituency pertaining to the Locality in which his place of residence in India as mentioned in the passport is located. The Application in Form 6A can be presented to the registration officer concerned.</p>
									<p>After Filling the Details the application should be submitted directly to the Electoral Registration Officer (ERO) of the constituency within which the place of ordinary residence of the applicant as given in the valid passport falls. The Application in <a target="_blank" href="image/votersInfo/FORM6A.pdf" title="Click here to View Form-6A ( For NRI'S to register in the Electoral roll ) Application">Form 6A</a> can be presented in person to the ERO or sent by post addressed to the ERO concerned.</p>
									<a target="_blank" href="image/votersInfo/Form 6A - Application for inclusion of name in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-6A ( For NRI's to Register in the Electoral roll )" class="inline link btn-inverse btn ">How to File Form No.6A</a>
									
								</div>
							</section>	
							<a href="javascript:{}" class="closeBtn float-right"><img src="image/close_btn.png"/></a>
						</div><!---Big Speshal Box 6---Army & NRI Vote--- END--->
						
					</div>
					</div>
				</div>
			</section><!----S_Blocks END---->
			
			<!----Content---->
			<section class="row ">
				<div class="container m_top10">
					<div class="row-fluid">
						<div class="span5 well well-small" id="topFiveNotifications">
						</div>
						
						<div class="span7 well well-small" id="pressReleaseDiv">
						</div>
					</div>
					
				</div>
			</section><!----Content END---->
		</section>


</div>
<script>
var startIndex = 0;
var maxIndex = 5;
var totalCount = "";
var id = "";
$(document).ready(function () {
	var passwordChanged = '${passwordChanged}';
	//alert(passwordChanged);
	if(passwordChanged == "NO")
		{
			$('#passwordWindow').show();
		}
		
		else if(passwordChanged == "YES")
		{
			$('#passwordWindow').hide();
		}
		
		
		$(".carousel-indicators li:eq(0)").trigger("click");
		//getTop5Comments(startIndex,maxIndex,"getCommentsList");
});

$('#changePassword').click(function(){
$('#passwordModal').modal('show');
});

/* function saveComment()
{
	var comment = $('#commentText').val();
	var jsObj =
		{  	
			id       : 1,
			comment  : comment,
			task     : 'commentSave'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "commentSaveAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
} */

function getTop5Comments(id,startIndex,maxIndex,task,showMore)
{
	/**
		The showMore is used in callback function to append the results to existing div..else we clear the div
	**/
	var jsObj =
		{  	
			id          : id,
			startIndex  : startIndex,
			maxIndex    : maxIndex,
			task        : task,
			showMore    : showMore
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getCommentsAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}

function buildCommentsList(myResults)
{
	if(myResults != null)
	{
		var str = "";
		for(var i in myResults)
		{
			str += '<div class="span10 widget"  style="border-radius: 4px 4px 4px 4px; border: 1px solid green; margin-bottom: 10px;">';
			str +='<span><b>Comment : </b>'+myResults[i].comment+'</span></br>';
			str +='<span style="float:left; margin-top: 13px;"><b>Commented By : <b>'+myResults[i].name+'</span>';
			
			str +='<span style="float:right; margin-top: 13px;"><b>Date : </b>'+myResults[i].date+'</span></br>';
			str += '</div>';
		}
		$('#topFiveComments').html(str);
	}
}


function getAllComments(id,startIndex,maxIndex)
{
	
	$('#totalCommentsModal').modal('show');
	getTop5Comments(id,startIndex,maxIndex,"getTotalComments",false);
	
}
function buildTotalCommentsList(myResults,jsObj)
{
	var announcementId=jsObj.id;
	
	if(!jsObj.showMore)
	$('#totalComments').html("");
	
	if(myResults != null)
	{
		totalCount = myResults[0].total;
		var recordsPerPage=5;
		var str = "";
		var date="";
			for(var i in myResults)
			{
				str += '<div class="thumbnail indiComment">';
				str +='<span id="indiCommentId">'+myResults[i].comment+'</span></br>';
				str +='<span class="pull-left label label-info" id="indiCommentName">'+myResults[i].name+'</span>';
				str +='<span class="pull-right" id="indiCommentDate">'+myResults[i].commentedTime+'</span></br>';
				str += '</div>';
			}
			<!--str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList('+announcementId+');">More</a>';-->
			
			$('#totalComments').append(str);
		if(recordsPerPage+startIndex < totalCount)
		{
			$('#moreId').html('<a class="btn btn-primary" onClick="getRemaingCommentsList('+announcementId+');">More</a>');
			startIndex = startIndex + maxIndex;
			maxIndex   =  maxIndex;
			$('#moreId').css('display','block');
		}
		else
		{
			$('#moreId').css('display','none');
			startIndex=0;
		}
	}
	else
	{
		$('#moreId').css('display','none');
		startIndex=0;
	}
	
}

function getRemaingCommentsList(announcementId)
{
	getTop5Comments(announcementId,startIndex,maxIndex,"getTotalComments",true);
}

/* function buildRemainingCommentsList(myResults)
{
	var str = "";
		for(var i in myResults)
		{
			str += '<div class="span10 widget">';
			str +='<span>Name : </span><span>'+myResults[i].name+'</span></br>';
			str +='<span>Comment : </span><span>'+myResults[i].comment+'</span></br>';
			str +='<span>Date : </span><span>'+myResults[i].date+'</span></br>';
			str += '</div>';
			str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList();">More</a>'
		}
		$('#totalComments').append(str);
} */

function getTopAnnouncements()
{
	var jsObj =
		{  	
			task  : "topAnnouncements"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getTopAnnouncementsAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}

function getSelectedAnnouncement(id,name)
{
	var jsObj =
		{  	
			announcementId : id,
			name           : name,
			task           : "getAnnouncementForSelected"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getTopAnnouncementsAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}

/* function getAllAnnoncements()
{
	var jsObj =
		{  	
			task           : "getAllAnnouncements"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getTopAnnouncementsAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}
 */
function buildTopAnnouncements(myResults)
{
	if(myResults[0].notificationsList != null)
	{
		
		var nstr = "";
		nstr += '<h4>Electoral Updates</h4>';
		for(var i in myResults[0].notificationsList)
		{
			id =  myResults[0].notificationsList[i].id;
			nstr += '<article class="row-fluid notifications">';
			
			if(myResults[0].notificationsList[i].count>0){
			nstr += '<a style="cursor: pointer;" class="date span2" onClick="getAllComments('+myResults[0].notificationsList[i].id+',0,5)"><h2>'+myResults[0].notificationsList[i].dateString+'</h2>';
			}
			else{
			nstr += '<a class="date span2"><h2>'+myResults[0].notificationsList[i].dateString+'</h2>';
			}
			
			nstr += '<span class="text-center label"><i class="icon-comment"></i><br/><small>'+myResults[0].notificationsList[i].count+'</small></span></a>';
			nstr +='<a style="cursor: pointer;" class=" title span10" onClick="getSelectedAnnouncement('+myResults[0].notificationsList[i].id+',\'Electoral Updates\');"><h1>'+myResults[0].notificationsList[i].title+'</h1><p>'+myResults[0].notificationsList[i].description+'</p></a>';
			nstr +='	</article>';
		}
		$('#topFiveNotifications').html(nstr);
	}
	if(myResults[0].pressReleasesList != null)
	{
		var pstr = "";
			pstr +=  '<article>';
			pstr +=	 '<h4>Press Releases</h4>';
			pstr += '<div>';
			//pstr +=	 '<table class="table table-bordered table-hover table-condensed"><tbody>';
		for(var i in myResults[0].pressReleasesList)
		{
			id =  myResults[0].pressReleasesList[i].id;
			pstr +=	 '<span class="thumbnail newsReleaseId"><a style="cursor: pointer;text-decoration:none;" class=" title" onClick="getSelectedAnnouncement('+myResults[0].pressReleasesList[i].id+',\'Press Releases\');"><i class="downarrow-icon"></i>'+myResults[0].pressReleasesList[i].title+'</a><span class="pull-right label">';
			
			if(myResults[0].pressReleasesList[i].count>0){
			pstr +=	'<a style="cursor: pointer;" onClick="getAllComments('+myResults[0].pressReleasesList[i].id+',0,5)"><i class="icon-comment"></i></a><small>'+myResults[0].pressReleasesList[i].count+'</small></span></span>';
			}
			else{
			pstr +='<a><i class="icon-comment"></i></a><small>'+myResults[0].pressReleasesList[i].count+'</small></span></span>';
			}
		}
		//pstr +=	'</table>';
		pstr +='</div>';
			pstr += '</article>';
		$('#pressReleaseDiv').html(pstr);
		
	}
}
var result = "";
function buildAnnouncementForSelected(myResults,name)
{
	if(myResults != null)
	{
		result = myResults;
		window.open('selectedAnnouncementsAction.action?name='+name+'');
	}
}

function callAjaxForComments(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="commentSave")
								{
									if(myResults.resultCode == 0)
									{
										$('#errorMsg').html('<b style="color:green">Comment Saved Successfully..</b>');
										getTop5Comments(id,startIndex,maxIndex,"getCommentsList",false);
									}
									else if(myResults == 'notLogged')
									{
										$('#errorMsg').html('<b style="color:red">Please Login To Comment..</b>');
									}
									else 
									{
										$('#errorMsg').html('<b style="color:red">Comment Not Saved Successfully?</b>');
									}
								}
								else if(jsObj.task =="getCommentsList")
								{
									buildCommentsList(myResults);
								}
								else if(jsObj.task =="getTotalComments")
								{
									buildTotalCommentsList(myResults,jsObj);
								}
								else if(jsObj.task =="topAnnouncements")
								{
									buildTopAnnouncements(myResults);
								} 
								else if(jsObj.task =="getAnnouncementForSelected")
								{
									buildAnnouncementForSelected(myResults,jsObj.name);
								}
								/* else if(jsObj.task =="getAllAnnouncements")
								{
									buildAllAnnouncements(myResults);
								} */
								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
	
	$('.carousel').carousel({  
		interval: 5000 // in milliseconds  
	})
	getTopAnnouncements();
	//getSelectedAnnouncement(1);
	//getAllAnnoncements();
	
	$(document).ready(function(){
	
	$(".carousel-indicators li:eq(0)").trigger("click");
	});
	/*------
	$('.carousel').carousel({  
		interval: 100 // in milliseconds  
	})  */
	function hideMainMenu(subBoxId){
	  $('#specialBlockDIV').toggle('slow');
	  showSubBoxes(subBoxId);
	}
	function showSubBoxes(subBoxId){
	  if($('#'+subBoxId).is(":visible")){
	      $('#'+subBoxId).toggle('slow');  
	  }else{
		 $('#'+subBoxId).toggle('slow');
		 $('#'+subBoxId).css("display","table");
	  }
	}
	function showMainMenu(subBoxId){
	  $('#specialBlockDIV').toggle('slow');
	  hideSubBoxes(subBoxId);
	}
	function hideSubBoxes(subBoxId){
	      $('#'+subBoxId).toggle('slow');  
	}
</script>

</body>  
</html>