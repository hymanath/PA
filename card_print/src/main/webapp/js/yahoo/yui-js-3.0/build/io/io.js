/*
Copyright (c) 2009, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 3.0.0
build: 1549
*/
YUI.add('io-base', function(Y) {

   /**
   	* Base IO functionality. Provides basic XHR transport support.
   	* @module io
   	* @submodule io-base
   	*/

   /**
	* The io class is a utility that brokers HTTP requests through a simplified
	* interface.  Specifically, it allows JavaScript to make HTTP requests to
	* a resource without a page reload.  The underlying transport for making
	* same-domain requests is the XMLHttpRequest object.  YUI.io can also use
	* Flash, if specified as a transport, for cross-domain requests.
	*
   	* @class io
   	*/

   /**
   	* @event io:start
   	* @description This event is fired by YUI.io when a transaction is initiated.
   	* @type Event Custom
   	*/
   	var E_START = 'io:start',

   /**
   	* @event io:complete
   	* @description This event is fired by YUI.io when a transaction is complete.
   	* Response status and data are accessible, if available.
   	* @type Event Custom
   	*/
   	E_COMPLETE = 'io:complete',

   /**
   	* @event io:success
   	* @description This event is fired by YUI.io when a transaction is complete, and
   	* the HTTP status resolves to HTTP2xx.
   	* @type Event Custom
   	*/
   	E_SUCCESS = 'io:success',

   /**
   	* @event io:failure
   	* @description This event is fired by YUI.io when a transaction is complete, and
   	* the HTTP status resolves to HTTP4xx, 5xx and above.
   	* @type Event Custom
   	*/
   	E_FAILURE = 'io:failure',

   /**
   	* @event io:end
   	* @description This event signifies the end of the transaction lifecycle.  The
   	* transaction transport is destroyed.
   	* @type Event Custom
   	*/
   	E_END = 'io:end',

   	//--------------------------------------
   	//  Properties
   	//--------------------------------------
   /**
   	* @description A transaction counter that increments for each transaction.
   	*
   	* @property transactionId
   	* @private
   	* @static
   	* @type int
   	*/
   	transactionId = 0,

   /**
   	* @description Object of default HTTP headers to be initialized and sent
   	* for all transactions.
   	*
   	* @property _headers
   	* @private
   	* @static
   	* @type object
   	*/
   	_headers = {
   		'X-Requested-With' : 'XMLHttpRequest'
   	},

   /**
   	* @description Object that stores timeout values for any transaction with
   	* a defined "timeout" configuration property.
   	*
   	* @property _timeout
   	* @private
   	* @static
   	* @type object
   	*/
   	_timeout = {},

   	// Window reference
   	w = Y.config.win;

   	//--------------------------------------
   	//  Methods
   	//--------------------------------------
   /**
   	* @description Method for requesting a transaction. _io() is implemented as
   	* yui.io().  Each transaction may include a configuration object.  Its
   	* properties are:
   	*
   	* method: HTTP method verb (e.g., GET or POST). If this property is not
   	*         not defined, the default value will be GET.
   	*
   	* data: This is the name-value string that will be sent as the transaction
    *		data.  If the request is HTTP GET, the data become part of
    *		querystring. If HTTP POST, the data are sent in the message body.
   	*
   	* xdr: Defines the transport to be used for cross-domain requests.  By
   	*      setting this property, the transaction will use the specified
   	*      transport instead of XMLHttpRequest.  Currently, the only alternate
   	*      transport supported is Flash (e.g., { xdr: 'flash' }).
   	*
   	* form: This is a defined object used to process HTML form as data.  The
   	*       properties are:
   	*       {
   	*	      id: object, //HTML form object or id of HTML form
   	*         useDisabled: boolean, //Allow disabled HTML form field values
   	*                      to be sent as part of the data.
	*       }
	*
	* on: This is a defined object used to create and handle specific
	*     events during a transaction lifecycle.  These events will fire in
	*     addition to the global io events. The events are:
	*	  start - This event is fired when a request is sent to a resource.
	*     complete - This event fires when the transaction is complete.
	*     success - This event fires when the response status resolves to
	*               HTTP 2xx.
	*     failure - This event fires when the response status resolves to
	*               HTTP 4xx, 5xx; and, for all transaction exceptions,
	*               including aborted transactions and transaction timeouts.
	*	  end -  This even is fired at the conclusion of the transaction
   	*			 lifecycle, after a success or failure resolution.
	*
	*     The properties are:
	*     {
   	*       start: function(id, args){},
   	*       complete: function(id, responseobject, args){},
   	*       success: function(id, responseobject, args){},
   	*       failure: function(id, responseobject, args){},
   	*       end: function(id, args){}
   	*     }
   	*	  Each property can reference a function or be written as an
   	*     inline function.
   	*
   	*     context: Object reference for an event handler when it is implemented
   	*              as a method of a base object. Defining "context" will preserve
   	*              the proper reference of "this" used in the event handler.
   	*     headers: This is a defined object of client headers, as many as.
   	*              desired for the transaction.  These headers are sentThe object
   	*              pattern is:
   	*              {
   	*		         header: value
   	*              }
   	*
   	* timeout: This value, defined as milliseconds, is a time threshold for the
   	*          transaction. When this threshold is reached, and the transaction's
   	*          Complete event has not yet fired, the transaction will be aborted.
   	* arguments: Object, array, string, or number passed to all registered
   	*            event handlers.  This value is available as the second
   	*            argument in the "start" and "abort" event handlers; and, it is
   	*            the third argument in the "complete", "success", and "failure"
   	*            event handlers.
   	*
   	* @method _io
   	* @private
   	* @static
	* @param {string} uri - qualified path to transaction resource.
	* @param {object} c - configuration object for the transaction.
	* @param {number} i - transaction id, if already set by queue.
	* @return object
   	*/
   	function _io(uri, c, i) {
   		var f, o, m;
   			c = c || {};
   			o = _create(c.xdr || c.form, i);
   			m = c.method ? c.method.toUpperCase() : 'GET';

   		if (c.form) {
   			if (c.form.upload) {
   				return Y.io._upload(o, uri, c);
   			}
   			else {
				f = Y.io._serialize(c.form, c.data);
				if (m === 'POST') {
					c.data = f;
					_setHeader('Content-Type', 'application/x-www-form-urlencoded');
				}
				else if (m === 'GET') {
					uri = _concat(uri, f);
				}
			}
		}
		else if (c.data && m === 'GET') {
			uri = _concat(uri, c.data);
		}

   		if (c.xdr) {
			if (c.xdr.use === 'native' && window.XDomainRequest || c.xdr.use === 'flash') {
   				return Y.io.xdr(uri, o, c);
			}
			if (c.xdr.credentials) {
				o.c.withCredentials = true;
			}
   		}

   		o.c.onreadystatechange = function() { _readyState(o, c); };
   		try {
			o.c.open(m, uri, true);
   		}
   		catch(e0){
			if (c.xdr) {
				// This exception is usually thrown by browsers
				// that do not support native XDR transactions.
				return _resend(o, uri, c);
			}
		}

   		if (c.data && m === 'POST') {
   			_setHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
   		}

   		_setHeaders(o.c, c.headers || {});
		try {
			// Using "null" will result in a POST request with
			// no Content-Length defined.
	   		o.c.send(c.data || '');
		}
   		catch(e1) {
			if (c.xdr) {
				// This exception is usually thrown by browsers
				// that do not support native XDR transactions.
				return _resend(o, uri, c);
			}
		}

   		_ioStart(o.id, c);
   		// If config.timeout is defined, and the request is standard XHR,
   		// initialize timeout polling.
   		if (c.timeout) {
   			_startTimeout(o, c.timeout);
   		}

		return {
			id: o.id,
			abort: function() {
				return o.c ? _ioCancel(o, 'abort') : false;
			},
			isInProgress: function() {
				return o.c ? o.c.readyState !== 4 && o.c.readyState !== 0 : false;
	   		}
		}
   	}

   /**
   	* @description Method for creating and subscribing transaction events.
   	*
   	* @method _subscribe
   	* @private
   	* @static
   	* @param {string} e - event to be published
   	* @param {object} c - configuration data subset for event subscription.
   	*
	* @return void
   	*/
   	function _subscribe(e, c){
   		var evt = new Y.EventTarget().publish('transaction:' + e);
		evt.subscribe(c.on[e], (c.context || Y), c.arguments);

   		return evt;
   	}

   /**
   	* @description Fires event "io:start" and creates, fires a
   	* transaction-specific start event, if config.on.start is
   	* defined.
   	*
   	* @method _ioStart
   	* @private
   	* @static
   	* @param {number} id - transaction id
   	* @param {object} c - configuration object for the transaction.
   	*
    * @return void
   	*/
   	function _ioStart(id, c) {
   		var evt;
   			// Set default value of argument c, property "on" to Object if
   			// the property is null or undefined.
   			c.on = c.on || {};

   		Y.fire(E_START, id);
   		if (c.on.start) {
   			evt = _subscribe('start', c);
   			evt.fire(id);
   		}
   	}


   /**
   	* @description Fires event "io:complete" and creates, fires a
   	* transaction-specific "complete" event, if config.on.complete is
   	* defined.
   	*
   	* @method _ioComplete
   	* @private
   	* @static
   	* @param {object} o - transaction object.
   	* @param {object} c - configuration object for the transaction.
   	*
    * @return void
   	*/
   	function _ioComplete(o, c) {
   		var evt,
			r = o.status ? { status: 0, statusText: o.status } : o.c;
   			// Set default value of argument c, property "on" to Object if
   			// the property is null or undefined.
   			c.on = c.on || {};

   		Y.fire(E_COMPLETE, o.id, r);
   		if (c.on.complete) {
   			evt = _subscribe('complete', c);
   			evt.fire(o.id, r);
   		}
   	}

   /**
   	* @description Fires event "io:success" and creates, fires a
   	* transaction-specific "success" event, if config.on.success is
   	* defined.
   	*
   	* @method _ioSuccess
   	* @private
   	* @static
   	* @param {object} o - transaction object.
   	* @param {object} c - configuration object for the transaction.
   	*
    * @return void
   	*/
   	function _ioSuccess(o, c) {
   		var evt;
   			// Set default value of argument c, property "on" to Object if
   			// the property is null or undefined.
   			c.on = c.on || {};

   		Y.fire(E_SUCCESS, o.id, o.c);
   		if (c.on.success) {
   			evt = _subscribe('success', c);
   			evt.fire(o.id, o.c);
   		}

   		_ioEnd(o, c);
   	}

   /**
   	* @description Fires event "io:failure" and creates, fires a
   	* transaction-specific "failure" event, if config.on.failure is
   	* defined.
   	*
   	* @method _ioFailure
   	* @private
   	* @static
   	* @param {object} o - transaction object.
   	* @param {object} c - configuration object for the transaction.
   	*
    * @return void
   	*/
   	function _ioFailure(o, c) {
   		var evt,
 			r = o.status ? { status: 0, statusText: o.status } : o.c;
   			// Set default value of argument c, property "on" to Object if
   			// the property is null or undefined.
   			c.on = c.on || {};

   		Y.fire(E_FAILURE, o.id, r);
   		if (c.on.failure) {
   			evt = _subscribe('failure', c);
   			evt.fire(o.id, r);
   		}

   		_ioEnd(o, c);
   	}

   /**
   	* @description Fires event "io:end" and creates, fires a
   	* transaction-specific "end" event, if config.on.end is
   	* defined.
   	*
   	* @method _ioEnd
   	* @private
   	* @static
   	* @param {object} o - transaction object.
   	* @param {object} c - configuration object for the transaction.
   	*
    * @return void
   	*/
   	function _ioEnd(o, c) {
   		var evt;
   			// Set default value of argument c, property "on" to Object if
   			// the property is null or undefined.
   			c.on = c.on || {};

   		Y.fire(E_END, o.id);
   		if (c.on.end) {
   			evt = _subscribe('end', c);
   			evt.fire(o.id);
   		}

   		_destroy(o, c.xdr ? true : false );
   	}

   /**
   	* @description Terminates a transaction due to an explicit abort or
   	* timeout.
   	*
   	* @method _ioCancel
   	* @private
   	* @static
	* @param {object} o - Transaction object generated by _create().
	* @param {string} s - Identifies timed out or aborted transaction.
   	*
    * @return void
   	*/
   	function _ioCancel(o, s) {
   		if (o && o.c) {
   			o.status = s;
   			o.c.abort();
   		}
   	}

   /**
   	* @description Resends an XDR transaction, using the Flash tranport,
   	* if the native transport fails.
   	*
   	* @method _resend
   	* @private
   	* @static

	* @param {object} o - Transaction object generated by _create().
	* @param {string} uri - qualified path to transaction resource.
   	* @param {object} c - configuration object for the transaction.
   	*
    * @return void
   	*/
	function _resend(o, uri, c) {
		var id = parseInt(o.id);

		_destroy(o);
		c.xdr.use = 'flash';

		return Y.io(uri, c, id);
	}

   /**
   	* @description Method that increments _transactionId for each transaction.
   	*
   	* @method _id
   	* @private
   	* @static
    * @return int
   	*/
   	function _id() {
   		var id = transactionId;

   		transactionId++;

   		return id;
   	}

   /**
   	* @description Method that creates a unique transaction object for each
   	* request.
   	*
   	* @method _create
   	* @private
   	* @static
	* @param {number} c - configuration object subset to determine if
	*                     the transaction is an XDR or file upload,
	*                     requiring an alternate transport.
	* @param {number} i - transaction id
	* @return object
   	*/
   	function _create(c, i) {
   		var o = {};
	   		o.id = Y.Lang.isNumber(i) ? i : _id();
	   		c = c || {};

		if (!c.use && !c.upload) {
   			o.c = _xhr();
		}
   		else if (c.use) {
			if (c.use === 'flash') {
   				o.c = Y.io._transport[c.use];
			}
			else if (c.use === 'native' && window.XDomainRequest) {
				o.c = new XDomainRequest();
			}
			else {
				o.c = _xhr();
			}
   		}
   		else {
   			o.c = {};
   		}

   		return o;
   	};

   /**
   	* @description Method that creates the XMLHttpRequest transport
   	*
   	* @method _xhr
   	* @private
   	* @static
	* @return object
   	*/
   	function _xhr() {
   		return w.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
   	}

   /**
   	* @description Method that concatenates string data for HTTP GET transactions.
   	*
   	* @method _concat
   	* @private
   	* @static
	* @param {string} s - URI or root data.
	* @param {string} d - data to be concatenated onto URI.
	* @return int
   	*/
   	function _concat(s, d) {
   		s += ((s.indexOf('?') == -1) ? '?' : '&') + d;
   		return s;
   	}

   /**
   	* @description Method that stores default client headers for all transactions.
   	* If a label is passed with no value argument, the header will be deleted.
   	*
   	* @method _setHeader
   	* @private
   	* @static
	* @param {string} l - HTTP header
	* @param {string} v - HTTP header value
	* @return int
   	*/
   	function _setHeader(l, v) {
   		if (v) {
   			_headers[l] = v;
   		}
   		else {
   			delete _headers[l];
   		}
   	}

   /**
   	* @description Method that sets all HTTP headers to be sent in a transaction.
   	*
   	* @method _setHeaders
   	* @private
   	* @static
	* @param {object} o - XHR instance for the specific transaction.
	* @param {object} h - HTTP headers for the specific transaction, as defined
	*                     in the configuration object passed to YUI.io().
	* @return void
   	*/
   	function _setHeaders(o, h) {
   		var p;

   		for (p in _headers) {
   			if (_headers.hasOwnProperty(p)) {
   				if (h[p]) {
   					// Configuration headers will supersede IO preset headers,
   					// if headers match.
   					break;
   				}
   				else {
   					h[p] = _headers[p];
   				}
   			}
   		}

   		for (p in h) {
   			if (h.hasOwnProperty(p)) {
   				o.setRequestHeader(p, h[p]);
   			}
   		}
   	}

   /**
   	* @description Starts timeout count if the configuration object
   	* has a defined timeout property.
   	*
   	* @method _startTimeout
   	* @private
   	* @static
	* @param {object} o - Transaction object generated by _create().
	* @param {object} c - Configuration object passed to YUI.io().
	* @return void
   	*/
   	function _startTimeout(o, timeout) {
   		_timeout[o.id] = w.setTimeout(function() { _ioCancel(o, 'timeout'); }, timeout);
   	}

   /**
   	* @description Clears the timeout interval started by _startTimeout().
   	*
   	* @method _clearTimeout
   	* @private
   	* @static
	* @param {number} id - Transaction id.
	* @return void
   	*/
   	function _clearTimeout(id) {
   		w.clearTimeout(_timeout[id]);
   		delete _timeout[id];
   	}

   /**
   	* @description Event handler bound to onreadystatechange.
   	*
   	* @method _readyState
   	* @private
   	* @static
	* @param {object} o - Transaction object generated by _create().
	* @param {object} c - Configuration object passed to YUI.io().
	* @return void
   	*/
   	function _readyState(o, c) {
   		if (o.c.readyState === 4) {
   			if (c.timeout) {
   				_clearTimeout(o.id);
   			}

   			w.setTimeout(
   				function() {
   					_ioComplete(o, c);
   					_handleResponse(o, c);
   				}, 0);
   		}
   	}

   /**
   	* @description Method that determines if a transaction response qualifies
   	* as success or failure, based on the response HTTP status code, and
   	* fires the appropriate success or failure events.
   	*
   	* @method _handleResponse
   	* @private
   	* @static
	* @param {object} o - Transaction object generated by _create().
	* @param {object} c - Configuration object passed to io().
	* @return void
   	*/
   	function _handleResponse(o, c) {
   		var status;
   		try{
   			if (o.c.status && o.c.status !== 0) {
   				status = o.c.status;
   			}
   			else {
   				status = 0;
   			}
   		}
   		catch(e) {
   			status = 0;
   		}

   		// IE reports HTTP 204 as HTTP 1223.
   		if (status >= 200 && status < 300 || status === 1223) {
   			_ioSuccess(o, c);
   		}
   		else {
   			_ioFailure(o, c);
   		}
   	}

   	function _destroy(o, transport) {
   		// IE, when using XMLHttpRequest as an ActiveX Object, will throw
   		// a "Type Mismatch" error if the event handler is set to "null".
   		if(w.XMLHttpRequest && !transport) {
   			if (o.c) {
   				o.c.onreadystatechange = null;
   			}
   		}

   		o.c = null;
   		o = null;
   	}

   	_io.start = _ioStart;
	_io.complete = _ioComplete;
   	_io.success = _ioSuccess;
   	_io.failure = _ioFailure;
   	_io.end = _ioEnd;
   	_io._id = _id;
   	_io._timeout = _timeout;

	//--------------------------------------
	//  Begin public interface definition
	//--------------------------------------
   /**
   	* @description Method that stores default client headers for all transactions.
   	* If a label is passed with no value argument, the header will be deleted.
   	* This is the interface for _setHeader().
   	*
   	* @method header
   	* @public
   	* @static
	* @param {string} l - HTTP header
	* @param {string} v - HTTP header value
	* @return int
   	*/
   	_io.header = _setHeader;

   /**
   	* @description Method for requesting a transaction. This
   	* is the interface for _io().
   	*
   	* @method io
   	* @public
   	* @static
    * @param {string} uri - qualified path to transaction resource.
    * @param {object} c - configuration object for the transaction.
    * @return object
    */
   	Y.io = _io;
	Y.io.http = _io;



}, '3.0.0' ,{requires:['event-custom-base']});

YUI.add('io-form', function(Y) {

   /**
    * Extends the IO base class to enable HTML form data serialization, when specified
    * in the transaction's configuration object.
    * @module io
    * @submodule io-form
    */

    Y.mix(Y.io, {
       /**
        * @description Method to enumerate through an HTML form's elements collection
        * and return a string comprised of key-value pairs.
        *
        * @method _serialize
        * @private
        * @static
        * @param {object} c - YUI form node or HTML form id.
        * @param {string} s - Transaction data defined in the configuration.
        * @return string
        */
        _serialize: function(c, s) {
			var eUC = encodeURIComponent,
            	data = [],
            	useDf = c.useDisabled || false,
            	item = 0,
            	id = (typeof c.id === 'string') ? c.id : c.id.getAttribute('id'),
            	e, f, n, v, d, i, il, j, jl, o;

            	if (!id) {
					id = Y.guid('io:');
					c.id.setAttribute('id', id);
				}

            	f = Y.config.doc.getElementById(id);

            // Iterate over the form elements collection to construct the
            // label-value pairs.
            for (i = 0, il = f.elements.length; i < il; ++i) {
                e = f.elements[i];
                d = e.disabled;
                n = e.name;

                if ((useDf) ? n : (n && !d)) {
                    n = encodeURIComponent(n) + '=';
                    v = encodeURIComponent(e.value);

                    switch (e.type) {
                        // Safari, Opera, FF all default options.value from .text if
                        // value attribute not specified in markup
                        case 'select-one':
                            if (e.selectedIndex > -1) {
                                o = e.options[e.selectedIndex];
                                data[item++] = n + eUC((o.attributes.value && o.attributes.value.specified) ? o.value : o.text);
                            }
                            break;
                        case 'select-multiple':
                            if (e.selectedIndex > -1) {
                                for (j = e.selectedIndex, jl = e.options.length; j < jl; ++j) {
                                    o = e.options[j];
                                    if (o.selected) {
                                      data[item++] = n + eUC((o.attributes.value && o.attributes.value.specified) ? o.value : o.text);
                                    }
                                }
                            }
                            break;
                        case 'radio':
                        case 'checkbox':
                            if(e.checked){
                                data[item++] = n + v;
                            }
                            break;
                        case 'file':
                            // stub case as XMLHttpRequest will only send the file path as a string.
                        case undefined:
                            // stub case for fieldset element which returns undefined.
                        case 'reset':
                            // stub case for input type reset button.
                        case 'button':
                            // stub case for input type button elements.
                            break;
                        case 'submit':
                        default:
                            data[item++] = n + v;
                    }
                }
            }
            return s ? data.join('&') + "&" + s : data.join('&');
        }
    }, true);



}, '3.0.0' ,{requires:['io-base','node-base','node-style']});

YUI.add('io-xdr', function(Y) {

   /**
    * Extends the IO base class to provide an alternate, Flash transport, for making
    * cross-domain requests.
	* @module io
	* @submodule io-xdr
	*/

   /**
	* @event io:xdrReady
	* @description This event is fired by YUI.io when the specified transport is
	* ready for use.
	* @type Event Custom
	*/
	var E_XDR_READY = 'io:xdrReady',


   /**
	* @description Object that stores callback handlers for cross-domain requests
	* when using Flash as the transport.
	*
	* @property _fn
	* @private
	* @static
	* @type object
	*/
	_fn = {},

   /**
	* @description Map of transaction state used when XDomainRequest is the
	* XDR transport.
	*
	* @property _rS
	* @private
	* @static
	* @type object
	*/
	_rS = {};

   /**
	* @description Method that creates the Flash transport swf.
	*
	* @method _swf
	* @private
	* @static
	* @param {string} uri - location of io.swf.
	* @param {string} yid - YUI instance id.
	* @return void
	*/
	function _swf(uri, yid) {
		var o = '<object id="yuiIoSwf" type="application/x-shockwave-flash" data="' +
		        uri + '" width="0" height="0">' +
		     	'<param name="movie" value="' + uri + '">' +
		     	'<param name="FlashVars" value="yid=' + yid + '">' +
                '<param name="allowScriptAccess" value="always">' +
		    	'</object>',
		    c = document.createElement('div');

		document.body.appendChild(c);
		c.innerHTML = o;
	}

   /**
	* @description Sets event handlers for XDomainRequest transactions.
	*
	* @method _xdr
	* @private
	* @static
    * @param {object} o - Transaction object generated by _create() in io-base.
	* @param {object} c - configuration object for the transaction.
	* @return void
	*/
	function _xdr(o, c) {
		o.c.onprogress = function() { _rS[o.id] = 3; }
		o.c.onload = function() {
			_rS[o.id] = 4;
			Y.io.xdrResponse(o, c, 'success');
		};
		o.c.onerror = function() {
			_rS[o.id] = 4;
			Y.io.xdrResponse(o, c, 'failure');
		};
		if (c.timeout) {
			o.c.ontimeout = function() {
				_rS[o.id] = 4;
				Y.io.xdrResponse(o, c, 'timeout');
			};
			o.c.timeout = c.timeout;
		}
	}

   /**
	* @description Creates a response object for XDR transactions, for success
	* and failure cases.
	*
	* @method _data
	* @private
	* @static
    * @param {object} o - Transaction object generated by _create() in io-base.
	* @param {boolean} isFlash - True if Flash was used as the transport.
	* @param {boolean} isXML - True if the response data are XML.
	*
	* @return object
	*/
	function _data(o, isFlash, isXML) {
		var text, xml;

		if (!o.status) {
			text = isFlash ? decodeURI(o.c.responseText) : o.c.responseText;
			xml = isXML ? Y.DataType.XML.parse(text) : null;

			return { id: o.id, c: { responseText: text, responseXML: xml } };
		}
		else {
			return { id: o.id, status: o.status };
		}

	}

   /**
	* @description Method for intiating an XDR transaction abort.
	*
	* @method _abort
	* @private
	* @static
	* @param {object} o - Transaction object generated by _create() in io-base.
	* @param {object} c - configuration object for the transaction.
	*/
	function _abort(o, c) {
		return c.xdr.use === 'flash' ? o.c.abort(o.id, c) : o.c.abort();
	}

   /**
	* @description Method for determining if an XDR transaction has completed
	* and all data are received.
	*
	* @method _isInProgress.
	* @private
	* @static
	* @param {object} o - Transaction object generated by _create() in io-base.
	* @param {object} c - configuration object for the transaction.
	*/
	function _isInProgress(o, t) {
		return (t === 'flash' && o.c) ? o.c.isInProgress(o.id) : _rS[o.id] !== 4;
	}

    Y.mix(Y.io, {

	   /**
		* @description Map of io transports.
		*
		* @property _transport
		* @private
		* @static
		* @type object
		*/
		_transport: {},

	   /**
	   	* @description Method for accessing the transport's interface for making a
	   	* cross-domain transaction.
	   	*
		* @method _xdr
		* @private
		* @static
		* @param {string} uri - qualified path to transaction resource.
    	* @param {object} o - Transaction object generated by _create() in io-base.
		* @param {object} c - configuration object for the transaction.
		*/
		xdr: function(uri, o, c) {
			if (c.on && c.xdr.use === 'flash') {
				_fn[o.id] = {
					on: c.on,
					context: c.context,
					arguments: c.arguments
				};
				// These nodes do not need to be serialized across Flash's
				// ExternalInterface.  Doing so will result in exceptions.
				c.context = null;
				c.form = null;

				o.c.send(uri, c, o.id);
			}
			else if (window.XDomainRequest) {
				_xdr(o, c);
				o.c.open(c.method || 'GET', uri);
				o.c.send(c.data);
			}

			return {
				id: o.id,
				abort: function() {
					return o.c ? _abort(o, c) : false;
				},
				isInProgress: function() {
					return o.c ? _isInProgress(o, c.xdr.use) : false;
				}
			}
		},

	   /**
	   	* @description Response controller for cross-domain requests when using the
	   	* Flash transport or IE8's XDomainRequest object.
	   	*
		* @method xdrResponse
		* @private
		* @static
    	* @param {object} o - Transaction object generated by _create() in io-base.
		* @param {object} c - configuration object for the transaction.
		* @param {string} e - Event name
		* @return object
		*/
		xdrResponse: function(o, c, e) {
   			var m, fn,
   				isFlash = c.xdr.use === 'flash' ? true : false,
   				isXML = c.xdr.dataType === 'xml' ? true : false;
   				c.on = c.on || {};

   			if (isFlash) {
   				m = _fn || {};
   				fn = m[o.id] ? m[o.id] : null;
   				if (fn) {
	   				c.on = fn.on;
	   				c.context = fn.context;
	   				c.arguments = fn.arguments;
				}
			}
			if (e === ('abort' || 'timeout')) {
				o.status = e;
			}

			switch (e) {
				case 'start':
					Y.io.start(o.id, c);
					break;
				case 'success':
					Y.io.success(_data(o, isFlash, isXML), c);
					isFlash ? delete m[o.id] : delete _rS[o.id];
					break;
				case 'timeout':
				case 'abort':
				case 'failure':
					Y.io.failure(_data(o, isFlash, isXML), c);
					isFlash ? delete m[o.id] : delete _rS[o.id];
					break;
			}
		},

	   /**
		* @description Fires event "io:xdrReady"
		*
		* @method xdrReady
		* @private
		* @static
		* @param {number} id - transaction id
		* @param {object} c - configuration object for the transaction.
		*
		* @return void
		*/
		xdrReady: function(id) {
			Y.fire(E_XDR_READY, id);
		},

	   /**
		* @description Method to initialize the desired transport.
		*
		* @method transport
		* @public
		* @static
		* @param {object} o - object of transport configurations.
		* @return void
		*/
		transport: function(o) {
			var id = o.yid ? o.yid : Y.id;

			_swf(o.src, id);
			this._transport.flash = Y.config.doc.getElementById('yuiIoSwf');
		}
	});



}, '3.0.0' ,{requires:['io-base','datatype-xml']});

YUI.add('io-upload-iframe', function(Y) {

   /**
   	* Extends the IO base class to enable file uploads, with HTML forms,
   	* using an iframe as the transport medium.
	* @module io
	* @submodule io-upload-iframe
	*/

	var w = Y.config.win;
   /**
	* @description Parses the POST data object and creates hidden form elements
	* for each key-value, and appends them to the HTML form object.
	* @method appendData
	* @private
	* @static
	* @param {object} f HTML form object.
	* @param {string} s The key-value POST data.
	* @return {array} o Array of created fields.
	*/
	function _addData(f, s) {
		var o = [],
			m = s.split('='),
			i, l;

		for (i = 0, l = m.length - 1; i < l; i++) {
			o[i] = document.createElement('input');
			o[i].type = 'hidden';
			o[i].name = m[i].substring(m[i].lastIndexOf('&') + 1);
			o[i].value = (i + 1 === l) ? m[i + 1] : m[i + 1].substring(0, (m[i + 1].lastIndexOf('&')));
			f.appendChild(o[i]);
		}

		return o;
	}

   /**
	* @description Removes the custom fields created to pass additional POST
	* data, along with the HTML form fields.
	* @method f
	* @private
	* @static
	* @param {object} f HTML form object.
	* @param {object} o HTML form fields created from configuration.data.
	* @return {void}
	*/
	function _removeData(f, o) {
		var i, l;

		for(i = 0, l = o.length; i < l; i++){
			f.removeChild(o[i]);
		}
	}

   /**
	* @description Sets the appropriate attributes and values to the HTML
	* form, in preparation of a file upload transaction.
	* @method _setAttrs
	* @private
	* @static
	* @param {object} f HTML form object.
	* @param {object} id The Transaction ID.
	* @param {object} uri Qualified path to transaction resource.
	* @return {void}
	*/
	function _setAttrs(f, id, uri) {
		var ie8 = (document.documentMode && document.documentMode === 8) ? true : false;

		f.setAttribute('action', uri);
		f.setAttribute('method', 'POST');
		f.setAttribute('target', 'ioupload' + id );
		f.setAttribute(Y.UA.ie && !ie8 ? 'encoding' : 'enctype', 'multipart/form-data');
	}

   /**
	* @description Sets the appropriate attributes and values to the HTML
	* form, in preparation of a file upload transaction.
	* @method _resetAttrs
	* @private
	* @static
	* @param {object} f HTML form object.
	* @param {object} a Object of original attributes.
	* @return {void}
	*/
	function _resetAttrs(f, a){
		var p;

		for (p in a) {
			if (a.hasOwnProperty(a, p)) {
				if (a[p]) {
					f.setAttribute(p, f[p]);
				}
				else {
					f.removeAttribute(p);
				}
			}
		}
	}

   /**
	* @description Creates the iframe transported used in file upload
	* transactions, and binds the response event handler.
	*
	* @method _create
	* @private
	* @static
    * @param {object} o Transaction object generated by _create().
    * @param {object} c Configuration object passed to YUI.io().
    * @return {void}
	*/
	function _create(o, c) {
		var i = Y.Node.create('<iframe id="ioupload' + o.id + '" name="ioupload' + o.id + '" />');
			i._node.style.position = 'absolute';
			i._node.style.top = '-1000px';
			i._node.style.left = '-1000px';

		Y.one('body').appendChild(i);
		// Bind the onload handler to the iframe to detect the file upload response.
		Y.on("load", function() { _handle(o, c) }, '#ioupload' + o.id);
	}

   /**
	* @description Bound to the iframe's Load event and processes
	* the response data.
	* @method _handle
	* @private
	* @static
	* @param {o} o The transaction object
	* @param {object} c Configuration object for the transaction.
	* @return {void}
	*/
	function _handle(o, c) {
		var d = Y.one('#ioupload' + o.id).get('contentWindow.document'),
			b = d.one('body'),
			xml = (d._node.nodeType === 9),
			p;

		if (c.timeout) {
			_clearTimeout(o.id);
		}

		if (b) {
			// When a response Content-Type of "text/plain" is used, Firefox and Safari
			// will wrap the response string with <pre></pre>.
			p = b.query('pre:first-child');
			o.c.responseText = p ? p.get('innerHTML') : b.get('innerHTML');
		}
		else if (xml) {
			o.c.responseXML =  d._node;
		}

		Y.io.complete(o, c);
		Y.io.end(o, c);
		// The transaction is complete, so call _destroy to remove
		// the event listener bound to the iframe transport, and then
		// destroy the iframe.
		w.setTimeout( function() { _destroy(o.id); }, 0);
	}

   /**
	* @description Starts timeout count if the configuration object
	* has a defined timeout property.
	*
	* @method _startTimeout
	* @private
	* @static
    * @param {object} o Transaction object generated by _create().
    * @param {object} c Configuration object passed to YUI.io().
    * @return {void}
	*/
	function _startTimeout(o, c) {
		Y.io._timeout[o.id] = w.setTimeout(
			function() {
				var r = { id: o.id, status: 'timeout' };

				Y.io.complete(r, c);
				Y.io.end(r, c);
			}, c.timeout);
	}

   /**
	* @description Clears the timeout interval started by _startTimeout().
	* @method _clearTimeout
	* @private
	* @static
    * @param {number} id - Transaction ID.
    * @return {void}
	*/
	function _clearTimeout(id) {
		w.clearTimeout(Y.io._timeout[id]);
		delete Y.io._timeout[id];
	}

   /**
	* @description
	* @method _destroy
	* @private
	* @static
	* @param {o} o The transaction object
	* @param {object} uri Qualified path to transaction resource.
	* @param {object} c Configuration object for the transaction.
	* @return {void}
	*/
	function _destroy(id) {
		Y.Event.purgeElement('#ioupload' + id, false);
		Y.one('body').removeChild(Y.one('#ioupload' + id));
	}

	Y.mix(Y.io, {
	   /**
		* @description Uploads HTML form data, inclusive of files/attachments,
		* using the iframe created in _create to facilitate the transaction.
		* @method _upload
		* @private
		* @static
		* @param {o} o The transaction object
		* @param {object} uri Qualified path to transaction resource.
		* @param {object} c Configuration object for the transaction.
		* @return {void}
		*/
		_upload: function(o, uri, c) {
			var f = (typeof c.form.id === 'string') ? Y.config.doc.getElementById(c.form.id) : c.form.id,
				fields,
				// Track original HTML form attribute values.
				attr = {
					action: f.getAttribute('action'),
					target: f.getAttribute('target')
				};

			_create(o, c);
			// Initialize the HTML form properties in case they are
			// not defined in the HTML form.
			_setAttrs(f, o.id, uri);
			if (c.data) {
				fields = _addData(f, c.data);
			}

			// Start polling if a callback is present and the timeout
			// property has been defined.
			if (c.timeout) {
				_startTimeout(o, c);
			}

			// Start file upload.
			f.submit();
			Y.io.start(o.id, c);
			if (c.data) {
				_removeData(f, fields);
			}
			// Restore HTML form attributes to their original values.
			_resetAttrs(f, attr);

			return {
				id: o.id,
				abort: function() {
					var r = { id: o.id, status: 'abort' };

					if (Y.one('#ioupload' + o.id)) {
						_destroy(o.id);
						Y.io.complete(r, c);
						Y.io.end(r, c);
					}
					else {
						return false;
					}
				},
				isInProgress: function() {
					return Y.one('#ioupload' + o.id) ? true : false;
				}
			}
		}
	});



}, '3.0.0' ,{requires:['io-base','node-base','event-base']});

YUI.add('io-queue', function(Y) {

   /**
    * Extends the IO base class to implement Queue for synchronous
    * transaction processing.
	* @module io
	* @submodule io-queue
	*/

   /**
	* @description Array of transactions queued for processing
	*
	* @property _yQ
	* @private
	* @static
	* @type Object
	*/
	var _q = new Y.Queue(),

   /**
	* @description Reference to "io:complete" event handler.
	*
	* @property _e
	* @private
	* @static
	* @type Object
	*/
	_e,

	_activeId,
   /**
	* @description Property to determine whether the queue is set to
	* 1 (active) or 0 (inactive).  When inactive, transactions
	* will be stored in the queue until the queue is set to active.
	*
	* @property _qState
	* @private
	* @static
	* @type int
	*/
	_qState = 1;

   /**
	* @description Method for requesting a transaction, and queueing the
	* request before it is sent to the resource.
	*
	* @method _queue
	* @private
	* @static
	* @return Object
	*/
	function _queue(uri, c) {
		var o = { uri: uri, id: Y.io._id(), cfg:c };

		_q.add(o);
		if (_qState === 1) {
			_shift();
		}

		return o;
	}

   /**
	* @description Method Process the first transaction from the
	* queue in FIFO order.
	*
	* @method _shift
	* @private
	* @static
	* @return void
	*/
	function _shift() {
		var o = _q.next();

		_activeId = o.id;
		_qState = 0;
		Y.io(o.uri, o.cfg, o.id);
	}

   /**
	* @description Method for promoting a transaction to the top of the queue.
	*
	* @method _unshift
	* @private
	* @static
	* @return void
	*/
	function _unshift(o) {
		_q.promote(o);
	}

	function _next(id) {
		_qState = 1;
		if (_activeId === id && _q.size() > 0) {
			_shift();
		}
	}

   /**
	* @description Method for removing a specific, pending transaction from
	* the queue.
	*
	* @method _remove
	* @private
	* @static
	* @return void
	*/
	function _remove(o) {
		_q.remove(o);
	}

	function _start() {
		_qState = 1;

		if (_q.size() > 0) {
			_shift();
		}
	}

   /**
	* @description Method for setting queue processing to inactive.
	* Transaction requests to YUI.io.queue() will be stored in the queue, but
	* not processed until the queue is reset to "active".
	*
	* @method _stop
	* @private
	* @static
	* @return void
	*/
	function _stop() {
		_qState = 0;
	};

   /**
	* @description Method to query the current size of the queue.
	*
	* @method _size
	* @private
	* @static
	* @return int
	*/
	function _size() {
		return _q.size();
	};

	_e = Y.on('io:complete', function(id) { _next(id); }, Y.io);

   /**
	* @description Method to query the current size of the queue, or to
	* set a maximum queue size.  This is the interface for _size().
	*
	* @method size
	* @public
	* @static
	* @param {number} i - Specified maximum size of queue.
    * @return number
	*/
	_queue.size = _size;

   /**
	* @description Method for setting the queue to active. If there are
	* transactions pending in the queue, they will be processed from the
	* queue in FIFO order. This is the interface for _start().
	*
	* @method start
	* @public
	* @static
    * @return void
	*/
	_queue.start = _start;

   /**
	* @description Method for setting queue processing to inactive.
	* Transaction requests to YUI.io.queue() will be stored in the queue, but
	* not processed until the queue is restarted. This is the
	* interface for _stop().
	*
	* @method stop
	* @public
	* @static
    * @return void
	*/
	_queue.stop = _stop;

   /**
	* @description Method for promoting a transaction to the top of the queue.
	* This is the interface for _unshift().
	*
	* @method promote
	* @public
	* @static
	* @param {Object} o - Reference to queued transaction.
    * @return void
	*/
	_queue.promote = _unshift;

   /**
	* @description Method for removing a specific, pending transaction from
	* the queue. This is the interface for _remove().
	*
	* @method remove
	* @public
	* @static
	* @param {Object} o - Reference to queued transaction.
    * @return void
	*/
	_queue.remove = _remove;

    Y.mix(Y.io, {
		queue: _queue
    }, true);



}, '3.0.0' ,{requires:['io-base','queue-promote']});



YUI.add('io', function(Y){}, '3.0.0' ,{use:['io-base', 'io-form', 'io-xdr', 'io-upload-iframe', 'io-queue']});

