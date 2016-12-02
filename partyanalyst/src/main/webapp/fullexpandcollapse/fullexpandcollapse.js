$(document).ready(function(){
	var windowWidth = $(window).width();
	//alert(windowWidth)
	console.log("menu width from menuinitialisation file and the font size of the page are coming from the css/menuresponsive.css")
	var res = windowWidth * 30 / 100;
	//alert(res)
	if(windowWidth < 767 &&  windowWidth > 500)
	{
		var res = windowWidth * 30 / 100;
		windowWidth = res+'px';	
	}
	else if(windowWidth < 400 )
	{
		var res = windowWidth * 30 / 100;
		windowWidth = res+'px';	
		//alert(windowWidth)
	}
	else if(windowWidth > 767 )
	{
		var res = windowWidth * 20 / 100;
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

	
	function loadScript( url, callback ) {
	  var script = document.createElement( "script" )
	  script.type = "text/javascript";
	  if(script.readyState) {  //IE
		script.onreadystatechange = function() {
		  if ( script.readyState === "loaded" || script.readyState === "complete" ) {
			script.onreadystatechange = null;
			callback();
		  }
		};
	  } else {  //Others
		script.onload = function() {
		  callback();
		};
	  }

	  script.src = url;
	  document.getElementsByTagName( "head" )[0].appendChild( script );
	}


	// call the function...
	loadScript('../jquery.multilevelpushmenu.min.js', function() {
	  $("#menu").css("display","none");
	  $("#menu").find("li").css("text-align","left");
	});

