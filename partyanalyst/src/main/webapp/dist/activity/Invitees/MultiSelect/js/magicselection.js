/**
 * magicselection.js v1.0.0
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2013, Codrops
 * http://www.codrops.com
 */
;( function( window ) {
	
	'use strict';

	function extend( a, b ) {
		for( var key in b ) { 
			if( b.hasOwnProperty( key ) ) {
				a[key] = b[key];
			}
		}
		return a;
	}

	// http://snipplr.com/view.php?codeview&id=5259
	function isMouseLeaveOrEnter(e, handler) { 
		if (e.type != 'mouseout' && e.type != 'mouseover') return false; 
		var reltg = e.relatedTarget ? e.relatedTarget : 
		e.type == 'mouseout' ? e.toElement : e.fromElement; 
		while (reltg && reltg != handler) reltg = reltg.parentNode; 
		return (reltg != handler); 
	}

	// http://stackoverflow.com/a/11381730/989439
	function mobilecheck() {
		var check = false;
		(function(a){if(/(android|ipad|playbook|silk|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4)))check = true})(navigator.userAgent||navigator.vendor||window.opera);
		return check;
	}

	function magicSelection( elems, options ) {
		if( mobilecheck() ) { return false; }
		this.items = [].slice.call( elems );
		this.options = extend( this.defaults, options );
		this._init();
	}

	magicSelection.prototype.defaults = {	
		onSelectionTime : 300,
		// callbacks
		onSelection : function( el ) { return false; },
		onSelectionStart : function( ev ) { return false; },
		onSelectionEnd : function( ev ) { return false; },
		onClick : function( el ) { return false; }
	};

	magicSelection.prototype._init = function() {
		this._initEvents();
	};

	magicSelection.prototype._initEvents = function() {
		var self = this;
		// event binding
		this.items.forEach( function( el ) {
			el.addEventListener( 'mousedown', function( ev ) { self._selectHandler( this, ev ); } );
			el.addEventListener( 'mouseup', function() { self.abortSelection(); } );
			el.addEventListener( 'mouseover', function(ev) { if( isMouseLeaveOrEnter( ev, this ) && self.isOnSelection ) self._selectHandler( this, ev, true ); } );
			el.addEventListener( 'mouseout', function(ev) { if( isMouseLeaveOrEnter( ev, this ) ) self.abortSelection(); } );
			el.addEventListener( 'click', function( ev ) { self._clickHandler( this, ev ); } );
			document.addEventListener( 'mouseup', function( ev ) { if( self.isOnSelection ) self._unselectAllHandler( ev ); } );
		} );
		// Disable text selection / dragging
		document.body.addEventListener( 'selectstart', function() { return false; } );
		document.body.addEventListener( 'dragstart', function() { return false; } );
	};

	magicSelection.prototype._selectHandler = function( el, ev, nodelay ) {
		var self = this;
		this.onSelectionTimeout = setTimeout( function() {
			if(!self.isOnSelection) {
				self.options.onSelectionStart( ev );
			}
			self.isOnSelection = true;
			if( !el.getAttribute( 'data-holdstate' ) || el.getAttribute( 'data-holdstate' ) === 'false' ) {
				self.options.onSelection( el );
			}
			// the element will be selected/unselected just once, while performing the mouse move
			el.setAttribute( 'data-holdstate', true );
			// prevent click action
			self.preventClick = true;
		}, nodelay ? 0 : this.options.onSelectionTime );
	};
	
	magicSelection.prototype._clickHandler = function( el, ev ) {
		if( this.preventClick ) {
			ev.preventDefault();
			this.preventClick = false;
			return false;
		}
		this.options.onClick( el );
	};
	
	magicSelection.prototype._unselectAllHandler = function( ev ) {
		this.abortSelection();
		this.isOnSelection = false;
		this.options.onSelectionEnd( ev );
		var self = this;
		setTimeout( function() { self.preventClick = false; }, 0 );
		this.items.forEach( function( el ) { el.setAttribute( 'data-holdstate', false ); } );
	};
	
	magicSelection.prototype.abortSelection = function() {
		clearTimeout( this.onSelectionTimeout );
	};

	// add to global namespace
	window.magicSelection = magicSelection;

} )( window );