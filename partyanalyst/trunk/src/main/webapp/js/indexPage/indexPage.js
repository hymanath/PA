

function buildIndexPageLayout()
{ 	 
	var candidatePageLayout = new YAHOO.widget.Layout('dashboard_layout_main', { 
	height:400,
	units: [			
			{ 
				position: 'left', 
				width: 250,
				header:false,
				body: 'dashBoardLeftlayoutDiv',
				resize: false,
				gutter: '2px',
				collapse: false,
				scroll: true,						
				animate: true 
			}, 					 
			{ 
				position: 'center',						
				body: 'dashBoardCenterlayoutDiv',
				resize: false,
				gutter: '2px',
				collapse: true,
				scroll: true,						
				animate: true
			} 
		 ] 
		
	});
	candidatePageLayout.render(); 
}


function buildReportsCarousel()
{
	var carousel = new YAHOO.widget.Carousel("pa_reports_carousel",
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 3,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});

		carousel.render(); 
		carousel.show();
}

function showReportsInCarousel()
{
	
}

function initializeIndexPage()
{
	buildIndexPageLayout();
	buildReportsCarousel();

}