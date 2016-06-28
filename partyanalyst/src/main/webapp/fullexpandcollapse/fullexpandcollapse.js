$(document).ready(function(){
	var windowWidth = $(window).width();
	console.log("menu width from menuinitialisation file and the font size of the page are coming from the css/menuresponsive.css")
	//alert(windowWidth)
	var res = windowWidth * 40 / 100;
	//alert(res)
	if(windowWidth < 767 &&  windowWidth > 500)
	{
		var res = windowWidth * 40 / 100;
		windowWidth = res+'px';	
	}
	else if(windowWidth < 400 )
	{
		var res = windowWidth * 60 / 100;
		windowWidth = res+'px';	
		//alert(windowWidth)
	}
	else if(windowWidth > 767 )
	{
		var res = windowWidth * 35 / 100;
		windowWidth = res+'px';	
		//alert(windowWidth)
	}
	
	// HTML markup implementation, overlap mode
	$( '#menu' ).multilevelpushmenu({
		//containersToPush: [$( '#pushobj' )],
		direction: 'rtl',
		fullCollapse: true,
		 collapsed: true,
		mode: 'overlap',
		menuWidth: windowWidth,
	    menuHeight: '100%',
		// Just for fun also changing the look of the menu
		wrapperClass: 'mlpm_w',
		menuInactiveClass: 'mlpm_inactive',
		onItemClick: function() {
			// Anchor href
			var itemHref = $item.find( 'a:first' ).attr( 'href' );
			// Redirecting the page
			location.href = itemHref;
		}
	});
	
});
	// Full collapse
	$( '.fullcollapse' ).click(function(){
	
		$( '#menu' ).multilevelpushmenu( 'collapse' );
		
	});

	// Base expand
	$( '.fullcollapse' ).click(function(){
		
		$( '#menu' ).multilevelpushmenu( 'expand' );
		
	});
	$( document ).click(function(){
	
		$( '#menu' ).multilevelpushmenu( 'collapse' );
		
	});
	
	
	$("#menu").on('click', '.levelHolderClass', function(){  

		if($(this).find("i").hasClass("floatLeft"))
		{
		  $(this).find("i").trigger('click');
		}
		
	  });

	

