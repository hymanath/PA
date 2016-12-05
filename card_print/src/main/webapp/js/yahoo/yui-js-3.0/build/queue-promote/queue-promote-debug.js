/*
Copyright (c) 2009, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 3.0.0
build: 1549
*/
YUI.add('queue-promote', function(Y) {

/**
 * Adds methods promote, remove, and indexOf to Queue instances.
 *
 * @module queue-promote
 * @for Queue
 */

Y.mix(Y.Queue.prototype, {
    /**
     * Returns the current index in the queue of the specified item
     * 
     * @method indexOf
     * @param needle {MIXED} the item to search for
     * @return {Number} the index of the item or -1 if not found
     */
    indexOf : function (callback) {
        return Y.Array.indexOf(this._q, callback);
    },

    /**
     * Moves the referenced item to the head of the queue
     *
     * @method promote
     * @param item {MIXED} an item in the queue
     */
    promote : function (callback) {
        var index = this.indexOf(callback);

        if (index > -1) {
            this._q.unshift(this._q.splice(index,1));
        }
    },

    /**
     * Removes the referenced item from the queue
     *
     * @method remove
     * @param item {MIXED} an item in the queue
     */
    remove : function (callback) {
        var index = this.indexOf(callback);

        if (index > -1) {
            this._q.splice(index,1);
        }
    }

});


}, '3.0.0' ,{requires:['yui-base']});
