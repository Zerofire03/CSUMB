/*
 * An object representing a 3x3 matrix
 */

var Matrix3 = function() {

	// Stores a matrix in a flat array, see the "set" function for an example of the layout
	// This format will be similar to what we'll eventually need when feeding this to WebGL
	this.elements = new Float32Array(9);

	// todo
	// this.elements should be initialized with values equal to the identity matrix

	// -------------------------------------------------------------------------
	this.clone = function() {
		// todo
		// create a new Matrix3 instance that is an exact copy of this one and return it
		return this /* should be a new Matrix instance*/;
	};

	// -------------------------------------------------------------------------
	this.copy = function(other) {
		// todo
		// copy all of the elements of other into the elements of 'this' matrix
		return this;
	};

	// -------------------------------------------------------------------------
	this.set = function (e11, e12, e13, e21, e22, e23, e31, e32, e33) {
		// todo
		// given the 9 elements passed in as argument e-row#col#, use
		// them as the values to set on 'this' matrix
		return this;
	};

	// -------------------------------------------------------------------------
	this.getElement = function(row, col) {
		// todo
		// use the row and col to get the proper index into the 1d element array and return it
		// return this.elements[/*index computed from row and col*/];
		return this; // <== delete this line and use the one above
	};

	// -------------------------------------------------------------------------
	this.identity = function() {
		// todo
		// reset every element in 'this' matrix to make it the identity matrix
		return this;
	};

	// -------------------------------------------------------------------------
	this.setRotationX = function(angle) {
		// not required yet, attempt to implement if finished early
		// create a rotation matrix that rotates around the X axis
		return this;
	};

	// -------------------------------------------------------------------------
	this.setRotationY = function(angle) {
		// not required yet, attempt to implement if finished early
		// create a rotation matrix that rotates around the Y axis
		return this;
	};


	// -------------------------------------------------------------------------
	this.setRotationZ = function(angle) {
		// not required yet, attempt to implement if finished early
		// create a rotation matrix that rotates around the Z axis
		return this;
	};

	// -------------------------------------------------------------------------
	this.multiplyScalar = function(s) {
		// todo
		// multiply every element in 'this' matrix by the scalar argument s
		return this;
	};

	// -------------------------------------------------------------------------
	this.multiplyRightSide = function(otherMatrixOnRight) {
		// todo
		// multiply 'this' matrix (on the left) by otherMatrixOnRight (on the right)
		// the results should be applied to the elements on 'this' matrix
		return this;
	};

	// -------------------------------------------------------------------------
	this.determinant = function() {
		// todo
		// compute and return the determinant for 'this' matrix
		return Math.Infinity; // should be the determinant
	};

	// -------------------------------------------------------------------------
	this.transpose = function() {
		// todo
		// modify 'this' matrix so that it becomes its transpose
		return this;
	};

	// -------------------------------------------------------------------------
	this.inverse = function() {
		// todo
		// modify 'this' matrix so that it becomes its inverse
		return this;
	};

	// -------------------------------------------------------------------------
	this.log = function() {
		var e = this.elements;
		console.log('[ '+
					'\n ' + e[0]  + ', ' + e[1]  + ', ' + e[2]  +
			        '\n ' + e[4]  + ', ' + e[5]  + ', ' + e[6]  +
			        '\n ' + e[8]  + ', ' + e[9]  + ', ' + e[10] +
			        '\n ' + e[12] + ', ' + e[13] + ', ' + e[14] +
			        '\n]'
		);

		return this;
	};
};
