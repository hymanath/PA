$(document).ready(function(){
	// HTML markup implementation, overlap mode
	$( '#menu' ).multilevelpushmenu({
		//containersToPush: [$( '#pushobj' )],
		direction: 'rtl',
		fullCollapse: true,
		 collapsed: true,
		mode: 'overlap',
		menuWidth: '30%',
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
	
	
	
	$(document).on('click', '.levelHolderClass', function(){  

		if($(this).find("i").hasClass("floatLeft"))
		{
		  $(this).find("i").trigger('click');
		}
		
	  });

	

