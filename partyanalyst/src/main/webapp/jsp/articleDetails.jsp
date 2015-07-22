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
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/daterange/daterangepicker-bs3.css" type="text/css" rel="stylesheet">
</head>

<body>
<style>
body
{
	background:#f5f5f5
}

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
 
</style>
<div class="container">
	
	<div id="dummyDiv"></div>
</div>
    	
<!-- Button trigger modal -->
<!--<button type="button" class="btn btn-primary btn-lg artclMdl" >
  Launch demo modal
</button>-->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  
</div>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script>


dummyfun(0);
var linkResult = [];
var groupResult = [];
var artclesRsltPar = [];
var obj = ["0","State","District","Constituency","Parliament","Mandal","Panchayat","muncipal Corporation"];



function dummyfun(num){
 var stNO=num;
 var paginationCount='';
 var jsObj={stNO:stNO,endNo : 10};
 $.ajax({
	url: "http://localhost:8080/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/0/0/0/0/14-07-2015/15-07-2015/0/10/district/13/0/department"
 }).then(function(data) {
	 
	 var str='';
	 var result = data.articlesList;
	 artclesRslt = result;
	 for(var i in result){
		var linkArticleId=result[i].articleDetails[0].articleId;
		paginationCount=result[0].paginationCount;
		str+='<div style="border:3px solid #ccc; margin:5px;width:300px;float:right;">';
		str+='<h4 class="text-left" style="line-height:30px;color:#2E5682; font-size: 13pt"><i>Title : </i>'+result[i].articleDetails[0].articleTitle+'<br><small style="font-size:13px;"><i>Edition Source :'+result[i].articleDetails[0].editionSource+' [ Date : </span>'+result[i].articleDetails[0].articleInsertedTime+' ]</i></small></h4>';
		str+='<img src="../NewsReaderImages/'+result[i].articleDetails[0].imageURL+'" style="width:100px;height:100px;border:2px solid #adadad;" class="artclMdl" attr_artclId='+result[i].articleDetails[0].articleId+'/>';
		 
	 if(result[i].articleDetails[0].description==""){
		str+='<div class="pull-left" style="margin-top:3px;color:#2E5682; font-size: 12pt"><b><span>Description : </span>- - - -</b></div>';
	 }
	 else{
		str+='<div class="pull-left" style="margin-top:3px;color:#2E5682; font-size: 12pt"><b><span>Description : </span>'+result[i].articleDetails[0].description+'</b></div>';
	 }
	 
	 str+='<p>**fromList**</p>';
	 str+='<ul>';
	 
	 if(result[i].articleDetails[0].fromList!=null && result[i].articleDetails[0].fromList.candidates!=null && result[i].articleDetails[0].fromList.candidates.length>0){
		 str+='<li>';
		 for(var j in result[i].articleDetails[0].fromList.candidates){
			 str+='<img class="img-circle" style="width: 32px; height: 32px;" src="images/'+result[i].articleDetails[0].fromList.candidates[j].fromParty+'.jpg">';
			 str+='<img class="img-circle" style="width: 16px; height: 16px; margin-left: -7px; margin-top: -24px;" src="images/'+result[i].articleDetails[0].fromList.candidates[j].fromBenefit+'.png">';
			 str+=''+result[i].articleDetails[0].fromList.candidates[j].fromCandiName+'</h4>';
			 for(var k in result[i].articleDetails[0].fromList.candidates[j].fromCategories){
				 if(result[i].articleDetails[0].fromList.candidates[j].fromCategories[k].name==""){
					flag=0;
					str+='<p class="list-group-item-text">'+result[i].articleDetails[0].fromList.candidates[j].fromCategories[k].name+'</p>';
				 }else{
					flag=0;
					str+='<p class="list-group-item-text" style="margin-top: 10px; margin-left: 2px; border-radius: 5px; padding: 3px; text-align: center; background-color: rgb(119, 119, 142); color: rgb(255, 255, 255); font-weight: bold;">'+result[i].articleDetails[0].fromList.candidates[j].fromCategories[k].name+'</p>';
				 }
			 }
			 str+='<hr>';
		 }
		 str+='</li>';
		 if(flag==1){
			str='<li class="list-group-item border-radius-0" style="margin-top: 35px;border: 5px solid rgb(106, 176, 207); margin-left: -5px;"><span >No candidates assigned</span></li>';
		 }
	 }else{
		str='<li class="list-group-item border-radius-0" style="margin-top: 35px;border: 5px solid rgb(106, 176, 207); margin-left: -5px;"><span >No candidates assigned</span></li>';
	 }
	 str+='</ul>';
	 
	 str+='<p>**toList**</p>';
	 str+='<ul>';
	 
	 if(result[i].articleDetails[0].toList!=null && result[i].articleDetails[0].toList.candidates!=null && result[i].articleDetails[0].toList.candidates.length>0){
		 str+='<li>';
		 for(var j in result[i].articleDetails[0].toList.candidates){
			 str+='<img class="img-circle" style="width: 32px; height: 32px;" src="images/'+result[i].articleDetails[0].toList.candidates[j].toParty+'.jpg">';
			 str+='<img class="img-circle" style="width: 16px; height: 16px; margin-left: -7px; margin-top: -24px;" src="images/'+result[i].articleDetails[0].toList.candidates[j].toBenefit+'.png">';
			 str+=''+result[i].articleDetails[0].toList.candidates[j].toCandiName+'</h4>';
			 for(var k in result[i].articleDetails[0].toList.candidates[j].toCategories){
				 if(result[i].articleDetails[0].toList.candidates[j].toCategories[k].name==""){
					 flag=0;
					 str+='<p class="list-group-item-text">'+result[i].articleDetails[0].toList.candidates[j].toCategories[k].name+'</p>';
				 }else{
					 flag=0;
					 str+='<p class="list-group-item-text" style="margin-top: 10px; margin-left: 2px; border-radius: 5px; padding: 3px; text-align: center; background-color: rgb(119, 119, 142); color: rgb(255, 255, 255); font-weight: bold;">'+result[i].articleDetails[0].toList.candidates[j].toCategories[k].name+'</p>';
				 }
			 }
			 str+='<hr>';
		 }
		 str+='</li>';
		 if(flag==1){
			str='<li class="list-group-item border-radius-0" style="margin-top: 35px;border: 5px solid rgb(106, 176, 207); margin-left: -5px;"><span >No candidates assigned</span></li>';
		 }
	 }else{
		str+='<li class="list-group-item border-radius-0" style="margin-top: 35px;border: 5px solid rgb(106, 176, 207); margin-left: -5px;"><span >No candidates assigned</span></li>';
	 }
	 str+='</ul>';
	 
	 if(result[i].articleDetails[0].selectedArea!=null){
		str+='selected area=>'+result[i].articleDetails[0].selectedArea;
	 }else{
		str+='selected area=>--';
	 }
	 
	 if(result[i].articleDetails[0].impactScope!=null)
	 {
		str+='selected impactScope=>'+obj[result[i].articleDetails[0].impactScope];
	 }else{
		str+='selected impactScope=>--';
	 }
	 
	 if(result[i].articleDetails[0].newsType1!=null){
		str+='selected newsType=>'+result[i].articleDetails[0].newsType1;
	 }else{
		str+='selected newsType=>--';
	 }
	 
	 if(result[i].articleDetails[0].newsActivity1!=null){
		str+='selected newsActivity=>'+result[i].articleDetails[0].newsActivity1;
	 }else{
		str+='selected newsActivity=>--';
	 }
	 
	 if(result[i].articleDetails[0].newsRelated1!=null){
		str+='selected newsRelated=>'+result[i].articleDetails[0].newsRelated1;
	 }else{
		str+='selected newsRelated=>--';
	 }
	 
	 if(result[i].articleDetails[0].priority1!=null){
		str+='selected priority=>'+result[i].articleDetails[0].priority1;
	 }else{
		str+='selected priority=>--';
	 }
	 
	 if(result[i].articleDetails[0].solution1!=null){
		str+='selected solution=>'+result[i].articleDetails[0].solution1;
	 }else{
		str+='selected solution=>--';
	 }
	 
	 if(result[i].articleDetails[0].selectedKeyWords!=null){
		str+='selected selectedKeyWords=>'+result[i].articleDetails[0].selectedKeyWords;
	 }else{
		str+='selected selectedKeyWords=>--';
	 }
	 
	 /* //linked articles
	 if(result[i].linkedArticles.imageList2.length>1){
	 for(var j in result[i].linkedArticles.imageList2){
	 linkResult = result[i].linkedArticles.imageList2;
	 if(result[i].linkedArticles.imageList2[j].articleId==linkArticleId){
	 continue;
	 }else{
	 str+='<br/><hr>linked Articles';
	 str+='<div class="col-sm-4 " style="width:70px;height:70px; margin-top:10px;">'; 
	 str+='<img style="border:2px solid #ababab;height:100px;width:149px;cursor:pointer;" class="img-responsive linkedArticlesClickId" attr_linkedArticleId='+result[i].linkedArticles.imageList2[j].articleId+' src="../NewsReaderImages/'+result[i].linkedArticles.imageList2[j].imageURL+'"/>';
	 str+='</div>'; 
	 }
	 }
	 }

	 //grouped Articles
	 if(result[i].groupedArticles.imageList2.length>1){
	 for(var j in result[i].groupedArticles.imageList2){
	 groupResult = result[i].groupedArticles.imageList2;
	 if(result[i].groupedArticles.imageList2[j].articleId==linkArticleId){
	 continue;
	 }else{
	 str+='<br/><hr>linked Articles';
	 str+='<div class="col-sm-4 " style="width:70px; margin-top:70px;">'; 
	 str+='<img style="border:2px solid #ababab;height:100px;width:149px;cursor:pointer;" class="img-responsive groupedArticlesClickId" attr_groupedArticleId='+result[i].groupedArticles.imageList2[j].articleId+' src="../NewsReaderImages/'+result[i].groupedArticles.imageList2[j].imageURL+'"/>';
	 str+='</div>';
	 }
	 }
	 } */
	 str+='</div>';
	 }
	 
	 $("#dummyDiv").html(str);
	  $("#dummyDiv").html("");
	var str = '';
	var result = data.articlesList;
	for(var i in result){
		str+='<div class="col-md-3 col-sm-3 widgets widget-hide"><div class="thumbnail thumbnail-widget"><a style="cursor:pointer;" attr_articleid="16840" class="viewArticleDetailsByAllArticlesPage"><img src="../NewsReaderImages/'+result[i].articleDetails[0].imageURL+'" style="width:100px;height:100px;border:2px solid #adadad;" class="artclMdl" attr_artclId='+result[i].articleDetails[0].articleId+'/></a><div class="caption"><p style="font-size:14px;">'+result[i].articleDetails[0].articleTitle+'</p><p><small><i>Publication: '+result[i].articleDetails[0].editionSource+'<br>Date: '+result[i].articleDetails[0].articleInsertedTime+'<br><br>Location: '+result[i].articleDetails[0].selectedArea+'<br><br>Keywords: '+result[i].articleDetails[0].selectedKeyWords+'<br></i></small><br>';
		if(result[i].linkedArticles.imageList2.length>1){
			str+='<i title="Link Articles" data-placement="top" data-toggle="group" style="cursor:default;padding-left:5px;color:#000;" class="icon-linked-articles pull-right"></i>';
		}
		if(result[i].groupedArticles.imageList2.length>1){
			str+='<i title="Grouped Articles" data-placement="top" data-toggle="group" style="cursor:default;padding-left:5px;color:#000;" class="icon-article-group pull-right"></i>';
		}
		
		str+='</p></div></div></div>';
			
		 $("#dummyDiv").html(str);
	 }
	 
	 /* if(stNO == 0){
		 setTimeout(5000);
		 $(".paginationId").pagination({
			 items: paginationCount,
			 itemsOnPage: 10,
			 cssStyle: 'light-theme',
			 hrefTextPrefix: '#pages-',
			 onPageClick: function(pageNumber) { 
				 var num=(pageNumber-1)*10;
				 dummyfun(num);
			 }
		});
	 }  */
 });
 
 
 
}
$(document).on("click",".artclMdl",function(){
	var articleId = $(this).attr("attr_artclId");
	getClickedArticle(articleId);
	
 });
 
 function getClickedArticle(artclId){
	 alert(artclId);
	 
	var result = artclesRslt;
	console.log(result);
	
	var str = '';
	for(var i in result){
		 if(result[i].articleDetails[0].articleId == artclId){
			 str+='<div class="modal-dialog modal-lg" role="document">';
			str+='<div class="modal-content">';
			  str+='<div class="modal-header">';
				str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
				str+='<h4 class="modal-title" id="myModalLabel"></h4>';
			  str+='</div>';
			  str+='<div class="modal-body">';
				str+='<div class="row">';
					str+='<div class="col-md-12">';
						str+='<img src="../NewsReaderImages/'+result[i].articleDetails[0].imageURL+'" style="width:100px;height:100px;border:2px solid #adadad;"/>';
						str+='<p class="m_bottom0">Title : '+result[i].articleDetails[0].articleTitle+'</p>';
						str+='<p class="m_bottom0 text-italic font-10">Edition Source :'+result[i].articleDetails[0].editionSource+' [ Date : '+result[i].articleDetails[0].articleInsertedTime+' ]</p>';
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
											str+='<div class="panel-body">';
												str+='<p>'+result[i].articleDetails[0].fromList.candidates[j].editionName+'';
												if(result[i].articleDetails[0].fromList.candidates[j].fromCandiName!=null){
													str+='<p>'+result[i].articleDetails[0].fromList.candidates[j].fromCandiName+'';
												}
													str+='<span class="pull-right">';
														str+='<img class="img-circle" style="width: 16px; height: 16px; margin-left: -7px; margin-top: -24px;" src="images/'+result[i].articleDetails[0].fromList.candidates[j].fromBenefit+'.png"></span>';
												str+='</p>';
											str+='</div>';
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
											str+='<div class="panel-body">';
												str+='<p>'+result[i].articleDetails[0].toList.candidates[j].editionName+'';
												if(result[i].articleDetails[0].toList.candidates[j].toCandiName!=null){
													str+='<p>'+result[i].articleDetails[0].toList.candidates[j].toCandiName+'';
												}
													str+='<span class="pull-right">';
														str+='<img class="img-circle" style="width: 16px; height: 16px; margin-left: -7px; margin-top: -24px;" src="images/'+result[i].articleDetails[0].toList.candidates[j].toBenefit+'.png"></span>';
												str+='</p>';
											str+='</div>';
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
										str+='<table width="100%">';
											str+='<tr>';
												str+='<td width="60%">News type</td>';
												str+='<td width="40%">'+result[i].articleDetails[0].newsType1+'</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td>News Activity</td>';
												str+='<td>'+result[i].articleDetails[0].newsActivity1+'</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td>News related</td>';
												str+='<td>'+result[i].articleDetails[0].newsRelated1+'</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td>Priority</td>';
												str+='<td>'+result[i].articleDetails[0].priority1+'</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td>Solution</td>';
												str+='<td>'+result[i].articleDetails[0].solution1+'</td>';
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
										str+='<table width="100%">';
											str+='<tr>';
												str+='<td>IMPACT SCOPE</td>';
												str+='<td>'+obj[result[i].articleDetails[0].impactScope]+'</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td>LOCATION</td>';
												str+='<td>'+result[i].articleDetails[0].selectedArea+'</td>';
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
										str+='<p>'+result[i].articleDetails[0].selectedKeyWords+'</p>';
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
												if(result[i].linkedArticles.imageList2[j].articleId!=linkArticleId){
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
													 if(result[i].groupedArticles.imageList2[j].articleId!=linkArticleId){
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
				str+='</div>';
			  str+='</div>';
			str+='</div>';
		  str+='</div>';
		 }
	}
	$("#myModal").html(str);
	$('#myModal').modal('show'); 
 }
 
 
</script>
</body>
</html>
