/*
Copyright (c) 2009, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 3.0.0
build: 1549
*/
YUI.add('datatype-number-format', function(Y) {

/**
 * Number submodule.
 *
 * @module datatype
 * @submodule datatype-number
 */

/**
 * Format number submodule.
 *
 * @module datatype
 * @submodule datatype-number-format
 */
 
/**
 * DataType.Number provides a set of utility functions to operate against Number objects.
 *
 * @class DataType.Number
 * @static
 */
var LANG = Y.Lang;

Y.mix(Y.namespace("DataType.Number"), {
     /**
     * Takes a Number and formats to string for display to user.
     *
     * @method format
     * @param data {Number} Number.
     * @param config {Object} (Optional) Optional configuration values:
     *  <dl>
     *   <dt>prefix {String}</dd>
     *   <dd>String prepended before each number, like a currency designator "$"</dd>
     *   <dt>decimalPlaces {Number}</dd>
     *   <dd>Number of decimal places to round. Must be a number 0 to 20.</dd>
     *   <dt>decimalSeparator {String}</dd>
     *   <dd>Decimal separator</dd>
     *   <dt>thousandsSeparator {String}</dd>
     *   <dd>Thousands separator</dd>
     *   <dt>suffix {String}</dd>
     *   <dd>String appended after each number, like " items" (note the space)</dd>
     *  </dl>
     * @return {String} Formatted number for display. Note, the following values
     * return as "": null, undefined, NaN, "".
     */
    format: function(data, config) {
        if(LANG.isNumber(data)) {
            config = config || {};

            var isNeg = (data < 0),
                output = data + "",
                decPlaces = config.decimalPlaces,
                decSep = config.decimalSeparator || ".",
                thouSep = config.thousandsSeparator,
                decIndex,
                newOutput, count, i;

            // Decimal precision
            if(LANG.isNumber(decPlaces) && (decPlaces >= 0) && (decPlaces <= 20)) {
                // Round to the correct decimal place
                output = data.toFixed(decPlaces);
            }

            // Decimal separator
            if(decSep !== "."){
                output = output.replace(".", decSep);
            }

            // Add the thousands separator
            if(thouSep) {
                // Find the dot or where it would be
                decIndex = output.lastIndexOf(decSep);
                decIndex = (decIndex > -1) ? decIndex : output.length;
                // Start with the dot and everything to the right
                newOutput = output.substring(decIndex);
                // Working left, every third time add a separator, every time add a digit
                for (count = 0, i=decIndex; i>0; i--) {
                    if ((count%3 === 0) && (i !== decIndex) && (!isNeg || (i > 1))) {
                        newOutput = thouSep + newOutput;
                    }
                    newOutput = output.charAt(i-1) + newOutput;
                    count++;
                }
                output = newOutput;
            }

            // Prepend prefix
            output = (config.prefix) ? config.prefix + output : output;

            // Append suffix
            output = (config.suffix) ? output + config.suffix : output;

            return output;
        }
        // Not a Number, just return as string
        else {
            return (LANG.isValue(data) && data.toString) ? data.toString() : "";
        }
    }
});



}, '3.0.0' );
