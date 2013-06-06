<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Telugudesham Party</title>
	<meta name="" content="">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
</head>
<body>
		
		<!----Container---->
		<div class="container">
			<div class="row m_top10">
				<div class="span6">
				<!----State waise news----->
					<div class="row-fluid widget">
						<div class="span8 boxHeading"><h4>state news</h4></div>
						
						<div class="span4">
							<ul class="nav nav-tabs pull-right" id="myTab">
								<li class="active"><a data-toggle="tab" href="#News">News</a></li>
								<li class=""><a data-toggle="tab" href="#Video">Video</a></li>
							</ul>
						</div>
						<div class="span12">
							<div class="tab-content" id="myTabContent" >
								<!-----------NEWS tab--------->
								<div id="News" class="tab-pane fade active in">
								<div class="carousel slide center" id="myCarousel">
										<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel" class=""></li>
										</ol>
										<div class="carousel-inner">
										  <div class="item active">
											<h4> TD to stall entry of tainted Ministers into Asse</h4>
												<div class="row-fluid">
														<a class="thumbnail span4" href="#" style="width: 146px;">
															<img src="http://placehold.it/200x150" style="width:100%">
														</a>
														<p class="span8">Cras sit amet nibh libero, in gravida nulla. 
															Nulla vel metus scelerisque ante 
															sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus
														</p>
												</div>
												<div class="row-fluid m_top10">
													<div class="span9">
														<p class="text-error">Source : Indian Express</p>
													</div>
													<div class="span2 ">
														<a href="#">
															<button class="btn btn-mini pull-right" type="button">More...</button>
														</a>
													</div>
												</div>
											</div>
											<div class="item">
											<h4> TD to stall entry of tainted Ministers into Asse</h4>
												<div class="row-fluid">
														<a class="thumbnail span4" href="#" style="width: 146px;">
															<img src="http://placehold.it/200x150" style="width:100%">
														</a>
														<p class="span8">Cras sit amet nibh libero, in gravida nulla. 
															Nulla vel metus scelerisque ante 
															sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus
														</p>
												</div>
												<div class="row-fluid m_top10">
													<div class="span9">
															<p class="text-error">Source : Indian Express</p>
													</div>
													<div class="span2 ">
														<a href="#">
															<button class="btn btn-mini pull-right" type="button">More...</button>
														</a>
													</div>
												</div>
											</div>
										</div>
										<!---<a data-slide="prev" href="#myCarousel" class="left ">‹</a>
										<a data-slide="next" href="#myCarousel" class="right">›</a>--->
									</div>
									
								</div>
							<!------ News tab end ---->
							<!------ Video tab --------->
								<div id="Video" class="tab-pane fade span11">
									<!----------------------->
									<div class="carousel slide center" id="myCarousel">
										<!----<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel" class=""></li>
										</ol>--->
										<div class="carousel-inner ">
											<!-------------------------->
										  <div class="item active">
											<div class="row-fluid">
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
											</div>
										  </div>
										   <div class="item">
											<div class="row-fluid">
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
											</div>
										  </div>
											<!-------------------------->
										  <a data-slide="prev" href="#myCarousel" class="left "><</a> | 
										  <a data-slide="next" href="#myCarousel" class="right">></a>
										  
										</div>
										<!---<a data-slide="prev" href="#myCarousel" class="left ">‹</a>
										<a data-slide="next" href="#myCarousel" class="right">›</a>---->
									</div>
					<!----------------------->
								</div>
							</div>
							<!------- Video tab end ------>
						</div>
					</div>
			<!----State waise news end----->
			<!----District waise news ----->
			<div class="row-fluid widget m_top10">
						<div class="span8 boxHeading"><h4>District level </h4></div>
						
						<div class="span4">
							<ul class="nav nav-tabs pull-right" id="myTab">
								<li class="active"><a data-toggle="tab" href="#News">News</a></li>
								<li class=""><a data-toggle="tab" href="#Video">Video</a></li>
							</ul>
						</div>
						<div class="span12">
							<div class="tab-content" id="myTabContent" >
								<!-----------NEWS tab--------->
								<div id="News" class="tab-pane fade active in">
								<div class="carousel slide center" id="myCarousel">
										<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel" class=""></li>
										</ol>
										<div class="carousel-inner">
										  <div class="item active">
											<h4> TD to stall entry of tainted Ministers into Asse</h4>
												<div class="row-fluid">
														<a class="thumbnail span4" href="#" style="width: 146px;">
															<img src="http://placehold.it/200x150" style="width:100%">
														</a>
														<p class="span8">Cras sit amet nibh libero, in gravida nulla. 
															Nulla vel metus scelerisque ante 
															sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus
														</p>
												</div>
												<div class="row-fluid m_top10">
													<div class="span9">
														<p class="text-error">Source : Indian Express</p>
													</div>
													<div class="span2 ">
														<a href="#">
															<button class="btn btn-mini pull-right" type="button">More...</button>
														</a>
													</div>
												</div>
											</div>
											<div class="item">
											<h4> TD to stall entry of tainted Ministers into Asse</h4>
												<div class="row-fluid">
														<a class="thumbnail span4" href="#" style="width: 146px;">
															<img src="http://placehold.it/200x150" style="width:100%">
														</a>
														<p class="span8">Cras sit amet nibh libero, in gravida nulla. 
															Nulla vel metus scelerisque ante 
															sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus
														</p>
												</div>
												<div class="row-fluid m_top10">
													<div class="span9">
															<p class="text-error">Source : Indian Express</p>
													</div>
													<div class="span2 ">
														<a href="#">
															<button class="btn btn-mini pull-right" type="button">More...</button>
														</a>
													</div>
												</div>
											</div>
										</div>
										<!---<a data-slide="prev" href="#myCarousel" class="left ">‹</a>
										<a data-slide="next" href="#myCarousel" class="right">›</a>--->
									</div>
									
								</div>
							<!------ News tab end ---->
							<!------ Video tab --------->
								<div id="Video" class="tab-pane fade span11">
									<!----------------------->
									<div class="carousel slide center" id="myCarousel">
										<!----<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel" class=""></li>
										</ol>--->
										<div class="carousel-inner ">
											<!-------------------------->
										  <div class="item active">
											<div class="row-fluid">
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
											</div>
										  </div>
										   <div class="item">
											<div class="row-fluid">
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
											</div>
										  </div>
											<!-------------------------->
										  <a data-slide="prev" href="#myCarousel" class="left "><</a> | 
										  <a data-slide="next" href="#myCarousel" class="right">></a>
										  
										</div>
										<!---<a data-slide="prev" href="#myCarousel" class="left ">‹</a>
										<a data-slide="next" href="#myCarousel" class="right">›</a>---->
									</div>
					<!----------------------->
								</div>
							</div>
							<!------- Video tab end ------>
						</div>
					</div>
			<!----District waise news end----->
			
				</div>
				<!---View your Constituency News Div--->
				<div class="span3" style="height:554px">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>View your Constituency News</h4></div>
						<div class="span12">ggdggfgggfggfgfdfgjkj kgjkgg kjgg kgjk gkjgk gjgkjgkjg gjjg</div>
					</div>
				</div>
				<!-----View your Constituency News End------>
				<!-----Top view News DIv------>
				<div class="span3" style="height:554px">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>Top Views</h4></div>
						<div class="span12">
							<ul class=" nav nav-list bs-docs-sidenav">
								<li>
									<h6>News Main Title</h6>
										<span ><i class="smal">04 June 2013</i></span>
								</li>
								
								
							</ul>
						</div>
					</div>
				</div>
			<!----- Top view News DIv End ------>
			</div>
		<!------------- Row-2 --------->
				<div class="row m_top10">
					<div class="span4">
						<div class="row-fluid widget">
							<div class="span12 boxHeading"><h4>Latest News</h4></div>
							<div class="span12">
								<ul class="unstyled pad10">
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-------->
					<div class="span4">
						<div class="row-fluid widget">
							<div class="span12 boxHeading"><h4>category wise news</h4></div>
							<div class="span12">
								<ul class="unstyled pad10">
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="row-fluid widget">
							<div class="span12 boxHeading"><h4>galleries</h4></div>
							<div class="span12">
								<ul class="unstyled pad10">
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			<!-------- Row-2 end -------------->
			<!------------- Row-3 --------->
				<div class="row m_top10">
					<div class="span12">
						<div class="row-fluid widget">
							<div class="span12 boxHeading"><h4>Latest Video</h4></div>
								<div class="row-fluid ">
									<div class="span12 pad10 ">
										<ul class="unstyled">
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
										</ul>
										<a href="#" class="pull-right btn btn-mini" style="margin-top:75px;">More</a>
									</div>
								</div>
						</div>
					</div>
				</div>
			<!-------- Row-3 end -------------->
				
	</div>
		<!------JS------>
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>-->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>	
</body>
</html>