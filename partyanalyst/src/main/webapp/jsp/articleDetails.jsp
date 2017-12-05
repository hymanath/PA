<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ALL ARTICLES</title>
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/daterange/daterangepicker-bs3.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/> 

</head>

<body>
<style>
.modal-backdrop
{
	z-index:104 !important
}
body
{
	background:#f5f5f5
}
#previous
{
	position:absolute;
	left:-30px;
	top:50%;
	font-size:22px;
	color:#46b8da;
	cursor:pointer;
}
#next
{position:absolute;right:-30px;top:50%;font-size:22px;color:#46b8da;cursor:pointer;}
.img-border
{
	border:1px solid #ccc;
}

.m_bottom0
{
	margin-bottom:0px;
}

.m_top10
{
	margin-top:10px;
}

.border_b1
{
	border-bottom:1px solid #666
}

.text-italic
{
	font-style:italic
}
.border-box-shadow
{
 	box-shadow: 0px 0px 8px 0px rgba(0, 0, 0, 0.3);
	padding:10px;
	background:#fff;
}
 
 .pad_10
 {
	 padding:10px;
 }
 
 .font-10
 {
	 font-size:10px
 }
 
 .col-md-4-artcl{
	 height: 520px;
    margin: 2px;
    width: 32.333%;
 }
 
 .light-theme a, .light-theme span{float:none;}
 #prevNxtId li:first-child{float:left;}
 #prevNxtId li:last-child{float:right;}
 .light-theme a, .light-theme span{font-weight:bold !important;padding:2px 18px !important;}
 .thumbnail-widget {
    border: 2px solid #ccc !important;
    border-radius: 0;
    height: 390px;
}
#dummyDiv {
    display: table-cell !important;
    float: left;
    width: 100%;
}
.m_top40{
	margin-top: 80px;
}

</style>
<div class="container-fluid" style="padding-left: 35px;">
	
	<div id="dummyDiv" class="row" style="background: rgb(236, 236, 236) none repeat scroll 0% 0%; padding: 10px 10px 10px 5px;"><center><img id="dataLoadingsImgForImagePath" src="images/icons/loading.gif" style="width:75px;height:75px;margin-left:auto;margin-right:auto;"/></center></div>
	
		<div class="row">
		
		<div class="span6 offset7 m_top40">
			<div class="paginationId"></div>
		</div>
	</div>
</div>
    	
<!-- Button trigger modal -->
<!--<button type="button" class="btn btn-primary btn-lg artclMdl" >
  Launch demo modal
</button>-->

<!-- Modal -->
<div class="modal fade" id="myModal" style="width:900px;left:35%;top:43%;bottom:5%;">
 </div>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
<link href="NewBootstrap/bootstrap.css" rel="stylesheet" type="text/css"/>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
 <script type="text/javascript" src="js/simplePagination/simplePagination3.js" ></script>
 <script type="text/javascript" src="NewBootstrap/bootstrap.js"></script>
<script>



var linkResult = [];
var groupResult = [];
var artclesRsltPar = [];
var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","CORP-GMC","Ward","NATIONAL","INTERNATIONAL","MUNICIPALITY"];



var fromDate = "${param.fdt}";
var toDate = "${param.tdt}";
var type = "${param.typ}";
var locationType = "${param.ltp}";
var locationId = "${param.lid}";
var deptId = "${param.did}";
var paginCount = "${param.atCnt}";

var partyId = "${param.ptid}";
var propertyId = "${param.prid}";

var benefitId = "${param.bfid}";
var categoryId = "${param.caid}";
var candidateId = "${param.cid}";

var secondaryPartyId = "${param.scpid}";


if(deptId==""){deptId =0;}
if(propertyId==""){propertyId =0;}
if(partyId==""){partyId =0;}
if(benefitId==""){benefitId =0;}
if(candidateId==""){candidateId =0;}
if(categoryId==""){categoryId =0;}
if(secondaryPartyId==""){secondaryPartyId =0;}
if(locationId==""){locationId = 0;}
if(locationType == ""){locationType = "district"}

setTimeout(function(){
 dummyfun(0);
}, 2000);

var totalArticleIds=[];
function dummyfun(num){
	
 $("#dummyDiv").html('<center><img id="dataLoadingsImgForImagePath" src="images/icons/loading.gif" style="width:75px;height:75px;margin-left:auto;margin-right:auto;"/></center>');
	
 var stNO=num;
 var endNO = 12;
 var paginationCount=paginCount;
 var jsObj={stNO:stNO,endNo : endNO};
 //{candidateId}/{categoryId}/{benefitId}/{departmentId}/{fromDate}/{toDate}/{fromNo}/{toNo}/{locationType}/{locationId}/{secondaryPartyId}/{type}/{partyId}/{propertyI	d}
	var url = window.location.href;
		var wurl = url.substr(0,(url.indexOf(".com")+4));
 $.ajax({
	 
	url:wurl+'/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/'+candidateId+'/'+categoryId+'/'+benefitId+'/'+deptId+'/'+fromDate+'/'+toDate+'/'+stNO+'/'+endNO+'/'+locationType+'/'+locationId+'/'+secondaryPartyId+'/'+type+'/'+partyId+'/'+propertyId+''
	//url:'http://localhost:8080/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/'+candidateId+'/'+categoryId+'/'+benefitId+'/'+deptId+'/'+fromDate+'/'+toDate+'/'+stNO+'/'+endNO+'/'+locationType+'/'+locationId+'/'+secondaryPartyId+'/'+type+'/'+partyId+'/'+propertyId+''
 }).then(function(data) {
	var result = data.articlesList;
	artclesRslt = result;
	
	$("#dummyDiv").html("");
	var str = '';
	var result = data.articlesList;
	for(var i in result){
		//totalArticleIds.push(result[i].articleDetails[0].articleId);
		var artclNmbr  = parseInt(stNO) + parseInt(i);
		str+='<div class="span4 widgets widget-hide"><div class="thumbnail thumbnail-widget" style="background: #fff none repeat scroll 0 0; !important"><a style="cursor:pointer;" attr_articleid="'+result[i].articleDetails[0].articleId+'" class="viewArticleDetailsByAllArticlesPage"><img src="../NewsReaderImages/'+result[i].articleDetails[0].imageURL+'" style="width:285px;height:180px;border:1px solid #adadad;" class="artclMdl" attr_artclNo="'+artclNmbr+'" attr_artclId='+result[i].articleDetails[0].articleId+'></a><div class="caption" ><p style="font-size:14px;">'+result[i].articleDetails[0].articleTitle+'</p><p><small><i>'+result[i].articleDetails[0].editionSource+'<br>Date: '+result[i].articleDetails[0].articleInsertedTime+'<br>';
		if(result[i].articleDetails[0].selectedArea!=null){
			str+='<br>Location: '+result[i].articleDetails[0].selectedArea+'<br>';
		}else{
			str+='<br>Location:  <br>';
		}
		
		
		if(result[i].articleDetails[0].selectedKeyWords!=null){
			str+='Keywords: '+result[i].articleDetails[0].selectedKeyWords+'<br></i></small><br>';
		}else{
			str+='<br>Keywords: <br></i></small><br>';
		}
		if(result[i].linkedArticles.imageList2.length>1){
			str+='<i title="Link Articles" data-placement="top" data-toggle="group" style="cursor:default;padding-left:5px;color:#000;" class="icon-linked-articles pull-right"></i>';
		}
		if(result[i].groupedArticles.imageList2.length>1){
			str+='<i title="Grouped Articles" data-placement="top" data-toggle="group" style="cursor:default;padding-left:5px;color:#000;" class="icon-article-group pull-right"></i>';
		}
		
		str+='</p></div></div></div>';
			
		 $("#dummyDiv").html(str);
	 }
	 if(stNO == 0){
		 setTimeout(5000);
		 $(".paginationId").pagination({
			 items: paginationCount,
			 itemsOnPage: 12,
			 cssStyle: 'light-theme',
			 hrefTextPrefix: '#pages-',
			 onPageClick: function(pageNumber) { 
				 var num=(pageNumber-1)*12;
				 dummyfun(num);
			 }
		});
	 }
 });
}

$(document).on("click",".artclMdl",function(){
	var articleId = $(this).attr("attr_artclId");
	var articleNo = $(this).attr("attr_artclNo");
	getClickedArticle(articleNo);
	
 });
 
 function getClickedChildArticle(artclId){
 	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	$.ajax({
		
	url: wurl+'/CommunityNewsPortal/webservice/getArticleDetailsForAArticle/'+artclId+''
	//url: 'http://localhost:8080/CommunityNewsPortal/webservice/getArticleDetailsForAArticle/'+artclId+''
	 }).then(function(data) {
		var result = data.articlesList;
		 
		 var str = '';
			for(var i in result){
				$("#mdlArtclTtl").html('<p class="m_bottom0" >'+result[i].articleDetails[0].articleTitle+'</p>');
				$("#mdlArtclDesc").html('<p class="m_bottom0 text-italic font-10">Edition Source :'+result[i].articleDetails[0].editionSource+' [ Date : '+result[i].articleDetails[0].articleInsertedTime+' ]</p>');
				
					  
							str+='<div class="col-md-12">';
								str+='<div class="row">';
								str+='<div class="col-md-10 col-md-offset-1">';
								str+='<img attr_id="'+result[i].articleDetails[0].articleId+'" src="../NewsReaderImages/'+result[i].articleDetails[0].imageURL+'" class="img-responsive thumbnail"/></div>';
								str+='</div>';
								
								str+='<p class="m_top10 m_bottom0">Description</p>';
								str+='<p>'+result[i].articleDetails[0].description+'</p>';
								str+='<div class="row">';
									str+='<div class="col-md-6">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading">';
												str+='<h4 class="panel-title">FROM WHOM</h4>';
											str+='</div>';
											if(result[i].articleDetails[0].fromList!=null && result[i].articleDetails[0].fromList.candidates!=null && result[i].articleDetails[0].fromList.candidates.length>0){
												for(var j in result[i].articleDetails[0].fromList.candidates){
													if(result[i].articleDetails[0].fromList.candidates[j].editionName!=null || result[i].articleDetails[0].fromList.candidates[j].fromCandiName!=null || result[i].articleDetails[0].fromList.candidates[j].fromBenefit!=null){
														str+='<div class="panel-body">';
															if(result[i].articleDetails[0].fromList.candidates[j].editionName!=null){
																str+='<p>'+result[i].articleDetails[0].fromList.candidates[j].editionName+'</p>';	
															}
															if(result[i].articleDetails[0].fromList.candidates[j].fromCandiName!=null){
																str+='<p>'+result[i].articleDetails[0].fromList.candidates[j].fromCandiName+'</p>';
															}
															if(result[i].articleDetails[0].fromList.candidates[j].fromBenefit!=null){
																str+='<span class="pull-right">';
																str+='<img class="img-circle" style="width: 16px; height: 16px; margin-left: -7px; margin-top: -24px;" src="images/'+result[i].articleDetails[0].fromList.candidates[j].fromBenefit+'.png"></span>';
															}
																
														str+='</div>';
													}
												}
											}else{
												str+='<li class="list-group-item border-radius-0"><span >No candidates assigned</span></li>';
											 }
										str+='</div>';
									str+='</div>';
									str+='<div class="col-md-6">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading">';
												str+='<h4 class="panel-title">TO WHOM</h4>';
											str+='</div>';
											if(result[i].articleDetails[0].toList!=null && result[i].articleDetails[0].toList.candidates!=null && result[i].articleDetails[0].toList.candidates.length>0){
												for(var j in result[i].articleDetails[0].toList.candidates){
													if(result[i].articleDetails[0].toList.candidates[j].editionName!=null || result[i].articleDetails[0].toList.candidates[j].toCandiName!=null || result[i].articleDetails[0].toList.candidates[j].toBenefit!=null){
														str+='<div class="panel-body">';
														if(result[i].articleDetails[0].toList.candidates[j].editionName!=null){
															str+='<p>'+result[i].articleDetails[0].toList.candidates[j].editionName+'</p>';
														}
														if(result[i].articleDetails[0].toList.candidates[j].toCandiName!=null){
															str+='<p>'+result[i].articleDetails[0].toList.candidates[j].toCandiName+'';
														}
														if(result[i].articleDetails[0].toList.candidates[j].toBenefit!=null){
															str+='<span class="pull-right">';
															str+='<img class="img-circle" style="width: 16px; height: 16px; margin-left: -7px; margin-top: -24px;" src="images/'+result[i].articleDetails[0].toList.candidates[j].toBenefit+'.png"></span>';
														}
														str+='</div>';
													}
												}
											}else{
												str+='<li class="list-group-item border-radius-0"><span >No candidates assigned</span></li>';
											 }
										str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="row">';
									str+='<div class="col-md-4">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading">';
												str+='<h4 class="panel-title">NEWS TYPE</h4>';
											str+='</div>';
											str+='<div class="panel-body">';
												str+='<table width="100%" class="table">';
													str+='<tr>';
														str+='<td width="60%">News type</td>';
														if(result[i].articleDetails[0].newsType1!=null){
															str+='<td width="40%">'+result[i].articleDetails[0].newsType1+'</td>';
														}else{
															str+='<td width="40%"> </td>';
														}
														
													str+='</tr>';
													str+='<tr>';
														str+='<td>News Activity</td>';
														if(result[i].articleDetails[0].newsActivity1!=null){
															str+='<td width="40%">'+result[i].articleDetails[0].newsActivity1+'</td>';
														}else{
															str+='<td width="40%"> </td>';
														}
													str+='</tr>';
													str+='<tr>';
														str+='<td>News related</td>';
														if(result[i].articleDetails[0].newsRelated1!=null){
															str+='<td width="40%">'+result[i].articleDetails[0].newsRelated1+'</td>';
														}else{
															str+='<td width="40%"> </td>';
														}
													str+='</tr>';
													str+='<tr>';
														str+='<td>Priority</td>';
														if(result[i].articleDetails[0].priority1!=null){
															str+='<td width="40%">'+result[i].articleDetails[0].priority1+'</td>';
														}else{
															str+='<td width="40%"> </td>';
														}
													str+='</tr>';
													str+='<tr>';
														str+='<td>Solution</td>';
														if(result[i].articleDetails[0].solution1!=null){
															str+='<td width="40%">'+result[i].articleDetails[0].solution1+'</td>';
														}else{
															str+='<td width="40%"> </td>';
														}
													str+='</tr>';
												str+='</table>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-md-4">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading">';
												str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
											str+='</div>';
											str+='<div class="panel-body">';
												str+='<table width="100%" class="table">';
													str+='<tr>';
														str+='<td>IMPACT SCOPE</td>';
														if(result[i].articleDetails[0].impactScope!=null){
															str+='<td>'+obj[result[i].articleDetails[0].impactScope]+'</td>';
														}else{
															str+='<td> - </td>';
														}
													str+='</tr>';
													str+='<tr>';
														str+='<td>LOCATION</td>';
														if(result[i].articleDetails[0].selectedArea!=null){
															str+='<td>'+result[i].articleDetails[0].selectedArea+'</td>';
														}else{
															str+='<td> - </td>';
														}
													str+='</tr>';
												str+='</table>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-md-4">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading">';
												str+='<h4 class="panel-title">KEYWORDS</h4>';
											str+='</div>';
											str+='<div class="panel-body">';
												if(result[i].articleDetails[0].selectedKeyWords!=null){
													str+='<p>'+result[i].articleDetails[0].selectedKeyWords+'</p>';
												}else{
													str+='<p> - </p>';
												}
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="row">';
									str+='<div class="col-md-6">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading">';
												str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
											str+='</div>';
											str+='<div class="panel-body pad_10">';
											   str+='<div class="row">';
													if(result[i].linkedArticles.imageList2.length>1){
													 for(var j in result[i].linkedArticles.imageList2){
														linkResult = result[i].linkedArticles.imageList2;
														if(result[i].linkedArticles.imageList2[j].articleId!=artclId){
																 str+='<div class="col-md-4">';
																 str+='<img width="100%" class="img-responsive linkedArticlesClickId" attr_linkedArticleId='+result[i].linkedArticles.imageList2[j].articleId+' src="../NewsReaderImages/'+result[i].linkedArticles.imageList2[j].imageURL+'"/>';
																 str+='</div>'; 
															}
														}
													}else{
														str+="<h6 style='margin-left:20px;'> No Linked Articles Available</h6>";
													}
												 str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-md-6">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading">';
												str+='<h4 class="panel-title">GROUPED ARTICLES</h4>';
											str+='</div>';
											str+='<div class="panel-body pad_10">';
												str+='<div class="row">';
													if(result[i].groupedArticles.imageList2.length>1){
														 for(var j in result[i].groupedArticles.imageList2){
															 groupResult = result[i].groupedArticles.imageList2;
															 if(result[i].groupedArticles.imageList2[j].articleId!=artclId){
																 str+='<div class="col-md-4">';
																 str+='<img class="img-responsive groupedArticlesClickId" attr_groupedArticleId='+result[i].groupedArticles.imageList2[j].articleId+' src="../NewsReaderImages/'+result[i].groupedArticles.imageList2[j].imageURL+'"/>';
																 str+='</div>';
															 }
														 }
													 }else{
														 str+="<h6 style='margin-left:20px;'> No Grouped Articles Available</h6>";
													 }
											   str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
				// }
			}
			
			$("#mdlInBodyId").html(str);
	 });
	
 }
 
	$(document).on("click",".linkedArticlesClickId",function(){	 
		var temp=$(this).attr('src');
		$(this).attr('src',$(".mainImage").attr('src'));
		$(".mainImage").attr('src',temp);
		
	});
	
	$(document).on("click",".groupedArticlesClickId",function(){
		getClickedChildArticle($(this).attr('attr_groupedArticleId'));
	});
	
	$(document).on("click",".changeArticle",function(){
		var position;
		for(var i=0;i<totalArticleIds.length;i++){
			if(totalArticleIds[i]==$(".mainImage").attr('attr_id')){
				console.log(totalArticleIds+"__"+i);
				position=i;
			}
		}
		
		if($(this).attr('id')=="previous" && position==0){
			alert("No Previous Article");
		}else if($(this).attr('id')=="previous" && position!=0){
			getClickedArticle(totalArticleIds[position-1]);
		}

		var check=(parseInt(totalArticleIds.length)-1);
		console.log($(this).attr('id')+"--"+(position+1)+"--"+parseInt(totalArticleIds.length));
		if($(this).attr('id')=="next" && position==check){
			alert("No Next Article");
		}else if($(this).attr('id')=="next" && position<check){
			getClickedArticle(totalArticleIds[position+1]);
		}
	
	});
	

function getClickedArticle(artclePosNo){
	endNO = 1;
	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	$.ajax({
	url:wurl+'/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/'+candidateId+'/'+categoryId+'/'+benefitId+'/'+deptId+'/'+fromDate+'/'+toDate+'/'+artclePosNo+'/'+endNO+'/'+locationType+'/'+locationId+'/'+secondaryPartyId+'/'+type+'/'+partyId+'/'+propertyId+''
	//url:'http://localhost:8080/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/'+candidateId+'/'+categoryId+'/'+benefitId+'/'+deptId+'/'+fromDate+'/'+toDate+'/'+artclePosNo+'/'+endNO+'/'+locationType+'/'+locationId+'/'+secondaryPartyId+'/'+type+'/'+partyId+'/'+propertyId+''
 }).then(function(data) {
	var result = data.articlesList;
	var str = '';
	for(var i in result){
		var artclId = result[i].articleDetails[0].articleId;
		 str+='<div class="modal-dialog modal-lg" role="document">';
			str+='<div class="modal-content">';
			  str+='<div class="modal-header">';
				str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
				str+='<h4 class="modal-title" id="myModalLabel">';
					str+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result[i].articleDetails[0].articleTitle+'</p>';
					str+='<p class="m_bottom0 text-italic font-10" id="mdlArtclDesc">Edition Source :'+result[i].articleDetails[0].editionSource+' [ Date : '+result[i].articleDetails[0].articleInsertedTime+' ]</p>';
					str+='<div id="prevNxtId" style="margin-top: 12px;"></div>';
				str+='</h4>';
			  str+='</div>';
			  str+='<div class="modal-body">';
			
				str+='<div class="row-fluid" id="mdlInBodyId">';
					str+='<div class="span12">';
						str+='<img src="../NewsReaderImages/'+result[i].articleDetails[0].imageURL+'" class="img-responsive mainImage"/>';
						
						
						str+='<p class="m_top10 m_bottom0">Description</p>';
						str+='<p>'+result[i].articleDetails[0].description+'</p>';
						str+='<div class="row-fluid">';
							str+='<div class="span6">';
								str+='<div class="panel panel-default">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">FROM WHOM</h4>';
									str+='</div>';
									if(result[i].articleDetails[0].fromList!=null && result[i].articleDetails[0].fromList.candidates!=null && result[i].articleDetails[0].fromList.candidates.length>0){
										for(var j in result[i].articleDetails[0].fromList.candidates){
											if(result[i].articleDetails[0].fromList.candidates[j].editionName!=null || result[i].articleDetails[0].fromList.candidates[j].fromCandiName!=null || result[i].articleDetails[0].fromList.candidates[j].fromBenefit!=null){
												str+='<div class="panel-body">';
													if(result[i].articleDetails[0].fromList.candidates[j].editionName!=null){
														str+='<p>'+result[i].articleDetails[0].fromList.candidates[j].editionName+'</p>';	
													}
													if(result[i].articleDetails[0].fromList.candidates[j].fromCandiName!=null){
														str+='<p>'+result[i].articleDetails[0].fromList.candidates[j].fromCandiName+'</p>';
													}
													if(result[i].articleDetails[0].fromList.candidates[j].fromBenefit!=null){
														str+='<span class="pull-right">';
														str+='<img class="img-circle" style="width: 16px; height: 16px; margin-left: -7px; margin-top: -24px;" src="images/'+result[i].articleDetails[0].fromList.candidates[j].fromBenefit+'.png"></span>';
													}
												str+='</div>';
											}
										}
									}else{
										str+='<li class="list-group-item border-radius-0"><span >No candidates assigned</span></li>';
									 }
								str+='</div>';
							str+='</div>';
							str+='<div class="span6">';
								str+='<div class="panel panel-default">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">TO WHOM</h4>';
									str+='</div>';
									if(result[i].articleDetails[0].toList!=null && result[i].articleDetails[0].toList.candidates!=null && result[i].articleDetails[0].toList.candidates.length>0){
										for(var j in result[i].articleDetails[0].toList.candidates){
											if(result[i].articleDetails[0].toList.candidates[j].editionName!=null || result[i].articleDetails[0].toList.candidates[j].toCandiName!=null || result[i].articleDetails[0].toList.candidates[j].toBenefit!=null){
												str+='<div class="panel-body">';
													if(result[i].articleDetails[0].toList.candidates[j].editionName!=null){
														str+='<p>'+result[i].articleDetails[0].toList.candidates[j].editionName+'</p>';	
													}
													if(result[i].articleDetails[0].toList.candidates[j].toCandiName!=null){
														str+='<p>'+result[i].articleDetails[0].toList.candidates[j].toCandiName+'';
													}
													if(result[i].articleDetails[0].toList.candidates[j].toBenefit!=null){
														str+='<span class="pull-right">';
														str+='<img class="img-circle" style="width: 16px; height: 16px; margin-left: -7px; margin-top: -24px;" src="images/'+result[i].articleDetails[0].toList.candidates[j].toBenefit+'.png"></span>';
													}
												str+='</div>';
											}
										}
									}else{
										str+='<li class="list-group-item border-radius-0"><span >No candidates assigned</span></li>';
									 }
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='<div class="row-fluid">';
							str+='<div class="span4">';
								str+='<div class="panel panel-default">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">NEWS TYPE</h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										str+='<table width="100%" class="table">';
											str+='<tr>';
												str+='<td width="60%">News type</td>';
												if(result[i].articleDetails[0].newsType1!=null){
													str+='<td width="40%">'+result[i].articleDetails[0].newsType1+'</td>';
												}else{
													str+='<td width="40%"> </td>';
												}
												
											str+='</tr>';
											str+='<tr>';
												str+='<td>News Activity</td>';
												if(result[i].articleDetails[0].newsActivity1!=null){
													str+='<td width="40%">'+result[i].articleDetails[0].newsActivity1+'</td>';
												}else{
													str+='<td width="40%"> </td>';
												}
											str+='</tr>';
											str+='<tr>';
												str+='<td>News related</td>';
												if(result[i].articleDetails[0].newsRelated1!=null){
													str+='<td width="40%">'+result[i].articleDetails[0].newsRelated1+'</td>';
												}else{
													str+='<td width="40%"> </td>';
												}
											str+='</tr>';
											str+='<tr>';
												str+='<td>Priority</td>';
												if(result[i].articleDetails[0].priority1!=null){
													str+='<td width="40%">'+result[i].articleDetails[0].priority1+'</td>';
												}else{
													str+='<td width="40%"> </td>';
												}
											str+='</tr>';
											str+='<tr>';
												str+='<td>Solution</td>';
												if(result[i].articleDetails[0].solution1!=null){
													str+='<td width="40%">'+result[i].articleDetails[0].solution1+'</td>';
												}else{
													str+='<td width="40%"> </td>';
												}
											str+='</tr>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="span4">';
								str+='<div class="panel panel-default">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										str+='<table width="100%" class="table">';
											str+='<tr>';
												str+='<td>IMPACT SCOPE</td>';
												if(result[i].articleDetails[0].impactScope!=null){
													str+='<td>'+obj[result[i].articleDetails[0].impactScope]+'</td>';
												}else{
													str+='<td> - </td>';
												}
											str+='</tr>';
											str+='<tr>';
												str+='<td>LOCATION</td>';
												if(result[i].articleDetails[0].selectedArea!=null){
													str+='<td>'+result[i].articleDetails[0].selectedArea+'</td>';
												}else{
													str+='<td> - </td>';
												}
											str+='</tr>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="span4">';
								str+='<div class="panel panel-default">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">KEYWORDS</h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										if(result[i].articleDetails[0].selectedKeyWords!=null){
											str+='<p>'+result[i].articleDetails[0].selectedKeyWords+'</p>';
										}else{
											str+='<p> - </p>';
										}
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='<div class="row-fluid">';
							str+='<div class="span6">';
								str+='<div class="panel panel-default">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
									str+='</div>';
									str+='<div class="panel-body pad_10">';
									   str+='<div class="row-fluid">';
											if(result[i].linkedArticles.imageList2.length>1){
											 for(var j in result[i].linkedArticles.imageList2){
												linkResult = result[i].linkedArticles.imageList2;
												if(result[i].linkedArticles.imageList2[j].articleId!=artclId){
														 str+='<div class="col-md-4">';
														 str+='<img width="100%" class="img-responsive linkedArticlesClickId" attr_linkedArticleId='+result[i].linkedArticles.imageList2[j].articleId+' src="../NewsReaderImages/'+result[i].linkedArticles.imageList2[j].imageURL+'"/>';
														 str+='</div>'; 
													}
												}
											}else{
												str+="<h6 style='margin-left:20px;'> No Linked Articles Available</h6>";
											}
										 str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="span6">';
								str+='<div class="panel panel-default">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">GROUPED ARTICLES</h4>';
									str+='</div>';
									str+='<div class="panel-body pad_10">';
										str+='<div class="row-fluid">';
											if(result[i].groupedArticles.imageList2.length>1){
												 for(var j in result[i].groupedArticles.imageList2){
													 groupResult = result[i].groupedArticles.imageList2;
													 if(result[i].groupedArticles.imageList2[j].articleId!=artclId){
														 str+='<div class="span4">';
														 str+='<img class="img-responsive groupedArticlesClickId" attr_groupedArticleId='+result[i].groupedArticles.imageList2[j].articleId+' src="../NewsReaderImages/'+result[i].groupedArticles.imageList2[j].imageURL+'"/>';
														 str+='</div>';
													 }
												 }
											 }else{
												 str+="<h6 style='margin-left:20px;'> No Grouped Articles Available</h6>";
											 }
									   str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			  str+='</div>';
			str+='</div>';
		  str+='</div>';
		
	}
	$("#myModal").html(str);
	
	var crrnt = parseInt(artclePosNo) + 1;
	$("#prevNxtId").pagination({
		 items: paginCount,
		 itemsOnPage: 1,
		 edges : 0,
		 currentPage :crrnt,
		 cssStyle: 'light-theme',
		 hrefTextPrefix: '#pages-',
		 displayedPages:0,
		 onPageClick: function(pageNumber) { 
		 var num=(pageNumber-1)*1;
			 getClickedArticle(num);
		 }
	});
	$('#myModal').modal('show'); 
	
	});
 }
	
</script>
</body>
</html>
