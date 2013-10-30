/*
Copyright (c) 2009, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 3.0.0
build: 1549
*/
YUI.add("pluginhost",function(C){var A=C.Lang;function B(){this._plugins={};}B.prototype={plug:function(G,D){if(G){if(A.isFunction(G)){this._plug(G,D);}else{if(A.isArray(G)){for(var E=0,F=G.length;E<F;E++){this.plug(G[E]);}}else{this._plug(G.fn,G.cfg);}}}return this;},unplug:function(E){if(E){this._unplug(E);}else{var D;for(D in this._plugins){if(this._plugins.hasOwnProperty(D)){this._unplug(D);}}}return this;},hasPlugin:function(D){return(this._plugins[D]&&this[D]);},_initPlugins:function(E){this._plugins=this._plugins||{};var G=(this._getClasses)?this._getClasses():[this.constructor],D=[],H={},F,I,K,L,J;for(I=G.length-1;I>=0;I--){F=G[I];L=F._UNPLUG;if(L){C.mix(H,L,true);}K=F._PLUG;if(K){C.mix(D,K,true);}}for(J in D){if(D.hasOwnProperty(J)){if(!H[J]){this.plug(D[J]);}}}if(E&&E.plugins){this.plug(E.plugins);}},_destroyPlugins:function(){this._unplug();},_plug:function(F,D){if(F&&F.NS){var E=F.NS;D=D||{};D.host=this;if(this.hasPlugin(E)){this[E].setAttrs(D);}else{this[E]=new F(D);this._plugins[E]=F;}}},_unplug:function(F){var E=F,D=this._plugins;if(A.isFunction(F)){E=F.NS;if(E&&(!D[E]||D[E]!==F)){E=null;}}if(E){if(this[E]){this[E].destroy();delete this[E];}if(D[E]){delete D[E];}}}};B.plug=function(E,I,G){var J,H,D,F;if(E!==C.Base){E._PLUG=E._PLUG||{};if(!A.isArray(I)){if(G){I={fn:I,cfg:G};}I=[I];}for(H=0,D=I.length;H<D;H++){J=I[H];F=J.NAME||J.fn.NAME;E._PLUG[F]=J;}}};B.unplug=function(E,H){var I,G,D,F;if(E!==C.Base){E._UNPLUG=E._UNPLUG||{};if(!A.isArray(H)){H=[H];}for(G=0,D=H.length;G<D;G++){I=H[G];F=I.NAME;if(!E._PLUG[F]){E._UNPLUG[F]=I;}else{delete E._PLUG[F];}}}};C.namespace("Plugin").Host=B;},"3.0.0",{requires:["yui-base"]});