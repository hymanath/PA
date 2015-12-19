$(document).ready(function(){
	// HTML markup implementation, overlap mode
	$( '#menu' ).multilevelpushmenu({
		//containersToPush: [$( '#pushobj' )],
		direction: 'rtl',
		fullCollapse: true,
		 collapsed: true,
		mode: 'overlap',
		menuWidth: '24%',
	    menuHeight: '100%',
		// Just for fun also changing the look of the menu
		wrapperClass: 'mlpm_w',
		menuInactiveClass: 'mlpm_inactive',
		onItemClick: function() {
			// First argument is original event object
			var event = arguments[0],
				// Second argument is menu level object containing clicked item (<div> element)
				$menuLevelHolder = arguments[1],
				// Third argument is clicked item (<li> element)
				$item = arguments[2],
				// Fourth argument is instance settings/options object
				options = arguments[3];

			// You can do some cool stuff here before
			// redirecting to href location
			// like logging the event or even
			// adding some parameters to href, etc...

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
	
	
	
	$(document).on('click', '.levelHolderClass', function(){  

    if($(this).find("i").hasClass("floatLeft"))
    {
      $(this).find("i").trigger('click');
    }
    
  });

	

