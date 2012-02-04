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
					<a href="specialPageAction.action?specialPageId=7" title="Goa 2012 Election"><img src="./images/new_homepage/goa.jpg" alt="Goa 2012 Election"/> <span>Goa</span> </a>
				</li>
				<li style="width:125px;">
					<a href="specialPageAction.action?specialPageId=4" title="Punjab 2012 Election"><img src="./images/new_homepage/punjab.png" alt="Punjab 2012 Election"/> <span>Punjab</span> </a>
				</li>
				<li style="width:125px;">
					<a href="specialPageAction.action?specialPageId=5" title="Uttarakhand 2012 Election"><img src="./images/new_homepage/uttaranchal.png" alt="Uttarakhand 2012 Election"/> <span>Uttarakhand</span> </a>
				</li>
				<li style="width:125px;">
					<a href="specialPageAction.action?specialPageId=6" title="Manipur 2012 Election"><img src="./images/new_homepage/manipur.png" alt="Manipur 2012 Election"/> <span>Manipur</span> </a>
				</li>
			</ul>
		</div>
<div class="next" style="right:-15px;top:70px;"></div></div>

<div id="wrap1">
	 <span style="background-color: #ED5B21; color: #FFFFFF; font-weight: bold;padding: 5px; clear: both; display: block;margin-top: 135px;margin-right: 112px;">View PoliticalProfile Photo's,News And Video's </span>
		<div id="list" style="margin-top:12px;width:400px;">
                <div class="prev1" style="top:76Px;left:-20px;"></div>
		<div class="slider1">
			<ul>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=36746" title="Mulayam Singh Yadav"><img src="./images/candidates/MULAYAM SINGH YADAV.jpeg" alt="Mulayam Singh Yadav"/> <span>Mulayam Singh Yadav</span> </a>
				 </li>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=43331" title="Uma Bharati"><img src="./images/candidates/UMA BHARTI.jpeg" alt="Uma Bharati"/> <span>Uma Bharati</span> </a>
				 </li>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=44476" title="Mayawati"><img src="./images/candidates/MAYAWATI.jpeg" alt="Mayawati"/> <span>Mayawati</span> </a>
				 </li>

				 <li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=36385" title="Akhilesh Yadav"><img src="./images/candidates/AKHILESH YADAV.jpeg" alt="Akhilesh Yadav"/> <span>Akhilesh Yadav</span> </a>
				 </li>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=36422" title="Rajnath Singh"><img src="./images/candidates/RAJNATH SINGH.jpeg" alt="Rajnath Singh"/> <span>Rajnath Singh</span> </a>
				 </li>
				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=36682" title="Rita Bahuguna Joshi"><img src="./images/candidates/RITA BAHUGUNA JOSHI.jpeg" alt="Rita Bahuguna Joshi"/> <span>Rita Bahuguna Joshi</span> </a>
				 </li>

				<li style="width:125px;">
				 <a href="candidateElectionResultsAction.action?
					candidateId=36340" title="Salman Khurshid"><img src="./images/candidates/SALMAN KHURSHEED.jpeg" alt="Salman Khurshid"/> <span>Salman Khurshid</span> </a>
				 </li>
				
			</ul>

	</div>
	<div class="next1" style="right:-15px;top:70px;"></div></div></div>


