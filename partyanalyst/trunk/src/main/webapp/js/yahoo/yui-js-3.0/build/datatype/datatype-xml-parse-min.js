/*
Copyright (c) 2009, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 3.0.0
build: 1549
*/
YUI.add("datatype-xml-parse",function(B){var A=B.Lang;B.mix(B.namespace("DataType.XML"),{parse:function(E){var D=null;if(A.isString(E)){try{if(!A.isUndefined(DOMParser)){D=new DOMParser().parseFromString(E,"text/xml");}}catch(F){try{if(!A.isUndefined(ActiveXObject)){D=new ActiveXObject("Microsoft.XMLDOM");D.async=false;D.loadXML(E);}}catch(C){}}}if((A.isNull(D))||(A.isNull(D.documentElement))||(D.documentElement.nodeName==="parsererror")){}return D;}});B.namespace("Parsers").xml=B.DataType.XML.parse;},"3.0.0");