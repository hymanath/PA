<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<script type="text/javascript">
		$(function() {
    		$(".slider1").jCarouselLite({
        		btnNext: ".next1",
        		btnPrev: ".prev1",
        		visible: 3
    		});
		});

		$(document).ready(function(){
			$('img.captify').captify({

				speedOver: 'fast',

				speedOut: 'normal',

				hideDelay: 500,	

				animation: 'slide',		

				prefix: '',		

				opacity: '0.7',					

				className: 'caption-bottom',	

				position: 'bottom',

				spanWidth: '100%'
			});
		});
		</script>

	<div id="wrap">
 <span style="background-color: #ED5B21; color: #FFFFFF; font-weight: bold;padding: 5px;">View Other States Election Results</span>
		<div id="list" style="margin-top:12px">
                <div class="prev" style="top:68Px;left:-20px;"></div>
	<div class="slider">
			<ul>
			 <li style="width:125px;">
				 <a href="specialPageAction.action?specialPageId=6" title="Manipur 2012 Election"><img src="./images/new_homepage/manipur.png" alt="Manipur 2012 Election"/> <span>Manipur</span> </a>
				 </li>
				
				<li style="width:125px;">
				 <a href="specialPageAction.action?specialPageId=5" title="Uttarakhand 2012 Election"><img src="./images/new_homepage/uttaranchal.png" alt="Uttarakhand 2012 Election"/> <span>Uttarakhand</span> </a>
				 </li>

				 <li style="width:125px;"> 
					<a href="specialPageAction.action?specialPageId=7" title="Goa 2012 Election"><img src="./images/new_homepage/goa.jpg" alt="Goa 2012 Election"/> <span>Goa</span> </a>
				</li>
				<li style="width:125px;">
				 <a href="specialPageAction.action?specialPageId=3" title="UttarPradesh 2012 Election"><img src="./images/new_homepage/uttarpradesh.png" alt="UttarPradesh 2012 Election"/> <span>UttarPradesh</span> </a>
				 </li>
			</ul>

		</div>
	<div class="next" style="right:-15px;top:70px;"></div></div>


 <div id="wrap1">
	 <span style="background-color: #ED5B21; color: #FFFFFF; font-weight: bold;padding: 5px; clear: both; display: block;margin-top: 135px;margin-right: 93px;">View PoliticalProfile Photo's,News And Video's </span>
		<div id="list" style="margin-top:12px;width:400px;">
                <div class="prev1" style="top:76Px;left:-20px;"></div>
		<div class="slider1">
			<ul>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=225499" title="Parkash Singh Badal"><img src="./images/candidates/PARKASH SINGH BADAL.jpeg" alt="Parkash Singh Badal"/> <span>Parkash Singh Badal</span> </a>
				 </li>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=39840" title=" Sukhbir Singh Badal"><img src="./images/candidates/SUKHBIR SINGH BADAL.jpeg" alt=" Sukhbir Singh Badal"/> <span> Sukhbir Singh Badal</span> </a>
				 </li>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=225360" title="Amarinder Singh"><img src="./images/candidates/AMARINDER SINGH.jpeg" alt="Amarinder Singh"/> <span>Amarinder Singh</span> </a>
				 </li>

				 <li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=118354" title="Rajinder Kaur Bhattal"><img src="./images/candidates/RAJINDER KAUR.jpeg " alt="Rajinder Kaur Bhattal"/> <span>Rajinder Kaur Bhattal</span> </a>
				 </li>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=164336" title="Ambika Soni"><img src="./images/candidates/AMBIKA SONI.jpeg" alt="Ambika Soni"/> <span>Ambika Soni</span> </a>
				 </li>
				
			</ul>

	</div>
	<div class="next1" style="right:-15px;top:70px;"></div></div></div>



    
   
