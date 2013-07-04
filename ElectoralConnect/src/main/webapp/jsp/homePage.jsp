<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Registration</title>

</head>  
<body>  
<script src="js/jquery.transit.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

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


<div id="totalComments"></div>-->

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
							</ol>
							<div class="carousel-inner">
							  
							  <div class="item active">
								<div class="hero-unit small ">
									<h1>Are you eligible for voting? But, still don't have vote?</h1>
									<h2>- See how to register your vote.</h2>
									<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>
								</div>
							  </div>
							  <div class="item">
								<div class="hero-unit small ">
									<h1>Are you eligible for voting? But, still don't have vote?</h1>
									<h2>- See how to register your vote.</h2>
									<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>
								</div>
							  </div>
							  <div class="item">
								<div class="hero-unit small ">
									<h1>Are you eligible for voting? But, still don't have vote?</h1>
									<h2>- See how to register your vote.</h2>
									<ul class="pager">
										<li class="next"><a href="#">Know More &rarr;</a></li>
									</ul>
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
						<div class="row-fluid">	
							<div class="span2 widget">
							  <img src="image/register_vote.png" />
							  <h3>Register VOTE</h3>
							  <a href="#" class="morebtn"></a>
							</div>
							<div class="span2 widget">
							  <img src="image/dublicate_vote.png" />
							  <h3>Duplication VOTE</h3>
							  <a href="#" class="morebtn"></a>
							</div>
							<div class="span2 widget">
								<img src="image/Transceive_vote.png" />
								<h3>Transfer VOTE</h3>
								<a href="#" class="morebtn"></a>
							</div>
							<div class="span2 widget">
								<img src="image/modify_vote.png" />
								<h3>Modify VOTE</h3>
								<a href="#" class="morebtn"></a>
							</div>
							<div class="span2 widget">
								<img src="image/cancel_vote.png" />
								<h3>Cancel VOTE</h3>
								<a href="#" class="morebtn"></a>
							</div>
							<div class="span2 widget">
								<img src="image/NRI_vote.png" />
								<h3>Army & NRI VOTE</h3>
								<a href="#" class="morebtn"></a>
							</div>
							
							
							</div>
						</div>
					</div>
				</div>
			</section><!----S_Blocks END---->
			
			<!----Content---->
			<section class="row ">
				<div class="container m_top10">
					<div class="row-fluid">
						<div class="span5 well well-small">
							<h4>Notifications</h4>
							<article class="row-fluid notifications">
								<a href="" class="date span2">
									<h2>July 03 2013</h2>
									<span class="text-center label"><i class="icon-comment"></i><br/><small>2500</small></span>
									
								</a>
								<a href="" class=" title span10">
									<h1>Affidavits of Contesting Candidates - New Affidav ...</h1>
									<p>Affidavits of Contesting Candidates New Affidav Affidavits of Contesting Candidates New Affidav Affidavits of Contesting Candidates New Affidav Affidavits of Contesting Candidates New Affidav</p>
								</a>	
							</article>
							<article class="row-fluid notifications">
								<a href="" class="date span2">
									<h2>July 03
									2013</h2>
									<span class="text-center label"><i class="icon-comment"></i><br/><small>2500</small></span>
									
								</a>
								<a href="" class=" title span10">
									<h1>Affidavits of Contesting Candidates - New Affidav ...</h1>
									<p>Affidavits of Contesting Candidates New Affidav Affidavits of Contesting Candidates</p>
								</a>	
							</article>
							
							<article class="row-fluid notifications">
								<a href="" class="date span2">
									<h2>July 03
									2013</h2>
									<span class="text-center label"><i class="icon-comment"></i><br/><small>2500</small></span>
									
								</a>
								<a href="" class=" title span10">
									<h1>Affidavits of Contesting Candidates - New Affidav ...</h1>
									<p></p>
								</a>	
							</article>
							
							<article class="row-fluid notifications">
								<a href="" class="date span2">
									<h2>July 03
									2013</h2>
									<span class="text-center label"><i class="icon-comment"></i><br/><small>2500</small></span>
									
								</a>
								<a href="" class=" title span10">
									<h1>Affidavits of Contesting Candidates - New Affidav ...</h1>
									<p>Affidavits of Contesting Candidates New Affidav...</p>
								</a>	
							</article>
							
							<article class="row-fluid notifications">
								<a href="" class="date span2">
									<h2>July 03
									2013</h2>
									<span class="text-center label"><i class="icon-comment"></i><br/><small>2500</small></span>
									
								</a>
								<a href="" class=" title span10">
									<h1>Affidavits of Contesting Candidates - New Affidav ...</h1>
									<p>Affidavits of Contesting Candidates New Affidav...</p>
								</a>	
							</article>
							
						</div>
						
						<div class="span7">
							<article>
								<h4>Press Releases</h4>
								<table class="table table-bordered table-hover table-condensed">
									
									<tbody>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
										<tr><td><i class="downarrow-icon"></i>Affidavits of Contesting Candidates - New Affidav ...<span class="pull-right label"><i class="icon-comment"></i><small>|2500</small></span></td></tr>
									</tbody>
								</table>
								
							</article>
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

function saveComment()
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
}

function getTop5Comments(startIndex,maxIndex,task)
{
	var jsObj =
		{  	
			id          : 1,
			startIndex  : startIndex,
			maxIndex    : maxIndex,
			task        : task
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


function getAllComments(startIndex,maxIndex)
{
	
	getTop5Comments(startIndex,maxIndex,"getTotalComments");
	
}
function buildTotalCommentsList(myResults)
{
	if(myResults != null)
	{
		totalCount = myResults[0].total;
		var str = "";
			for(var i in myResults)
			{
				str += '<div class="span10 widget"  style="border-radius: 4px 4px 4px 4px; border: 1px solid blue; margin-bottom: 10px;">';
				str +='<span><b>Comment : </b></span><span>'+myResults[i].comment+'</span></br>';
				str +='<span style="float:left;"><b>Commented By : <b>'+myResults[i].name+'</span>';
				
				str +='<span style="float:right"><b>Date : </b>'+myResults[i].date+'</span></br>';
				str += '</div>';
				str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList();">More</a>'
			}
			$('#totalComments').append(str);
		if(startIndex < totalCount)
		{
			startIndex = startIndex + maxIndex;
			maxIndex   =  maxIndex;
			$('#moreButton').show();
		}
		else
		{
			$('#moreButton').hide();
		}
	}
	else
	{
		$('#moreButton').hide();
	}
	
}

function getRemaingCommentsList()
{
	getTop5Comments(startIndex,maxIndex,"getTotalComments");
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
										getTop5Comments(startIndex,maxIndex,"getCommentsList");
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
									buildTotalCommentsList(myResults);
								}
								/* else if(jsObj.task =="getRemaingComments")
								{
									buildRemainingCommentsList(myResults);
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
		interval: 10000 // in milliseconds  
	})
</script>

</body>  
</html>