/*
Copyright (c) 2009, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 3.0.0
build: 1549
*/
YUI.add("plugin",function(B){function A(C){A.superclass.constructor.apply(this,arguments);}A.ATTRS={host:{writeOnce:true}};A.NAME="plugin";A.NS="plugin";B.extend(A,B.Base,{_handles:null,initializer:function(C){this._handles=[];},destructor:function(){if(this._handles){for(var D=0,C=this._handles.length;D<C;D++){this._handles[D].detach();}}},doBefore:function(G,D,C){var E=this.get("host"),F;C=C||this;if(G in E){F=B.Do.before(D,E,G,C);}else{if(E.on){F=E.on(G,D,C);}}this._handles.push(F);return F;},doAfter:function(G,D,C){var E=this.get("host"),F;C=C||this;if(G in E){F=B.Do.after(D,E,G,C);}else{if(E.after){F=E.after(G,D,C);}}this._handles.push(F);return F;},toString:function(){return this.constructor.NAME+"["+this.constructor.NS+"]";}});B.namespace("Plugin").Base=A;},"3.0.0",{requires:["base-base"]});