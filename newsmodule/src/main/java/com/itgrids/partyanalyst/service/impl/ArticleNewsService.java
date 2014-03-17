package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IArticleDAO;
import com.itgrids.partyanalyst.dto.ArticleVO;
import com.itgrids.partyanalyst.model.Article;
import com.itgrids.partyanalyst.service.IArticleNewsService;



public class ArticleNewsService implements IArticleNewsService{
	private static final Logger log = Logger.getLogger(ArticleNewsService.class);
	private IArticleDAO articleDAO;
	
	
  

   public IArticleDAO getArticleDAO() {
		return articleDAO;
	}




	public void setArticleDAO(IArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}




public ArticleVO getArticleNews(){
	   
		log.debug("Enter into getArticleNews() Method of ArticleNewsService ");
		
		ArticleVO resultvo = new ArticleVO();
	     
		      List<ArticleVO> result = new ArrayList<ArticleVO>();
	      try{
	    	  List<Article> list = articleDAO.getTotalArticleNews();
	    	  if(list != null && list.size() > 0)
	    	  {
	    		  
	    		  for(Article article : list)
	    		  {
	    	    ArticleVO articleVO=new ArticleVO();
	    		 articleVO.setId(article.getId());
	    		 articleVO.setContent(article.getContent());
	    		 articleVO.setMediaSource(article.getMediaSource());
	    		 articleVO.setNewsTitle(article.getNewsTitle());
	    		 articleVO.setPlace(article.getPlace());
	    		 articleVO.setUrl(article.getUrl());
	    		
	    		 //result.add(articleVO);
	    		 if(article.getDate() != null)
	    		 {
	    		   String dateString =article.getDate().getDate()+"/"+(article.getDate().getMonth()+1)+"/"+(article.getDate().getYear()+1900);
	    		   articleVO.setFileDateAsString(dateString);
	    		 }
	    		  String Date = article.getDate().toString();
	    		  String dateObj = Date.substring(8,10)+'-'+Date.substring(5,7)+'-'+Date.substring(0,4);
	    		 articleVO.setDate(dateObj!=null?dateObj:"");
	    		 
	    		 result.add(articleVO);
	    		  }
	    		  resultvo.setArticleVOList(result);
	    		  resultvo.setTotalRecords(result.size());
	    	  }
	    	  }
	      catch(Exception e){
	    		log.error("Exception rised in  getArticleNews  Method of ArticleNewsService", e);
	    	}
	      
	      return resultvo;
	    	 
	      }
	      
}
