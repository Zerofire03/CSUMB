/*
 * An object representing a 3x3 matrix
 */

var Matrix3 = function() {

	// Stores a matrix in a flat array, see the "set" function for an example of the layout
	// This format will be similar to what we'll eventually need when feeding this to WebGL
	this.elements = new Float32Array(9);
	for (var i = 0; i < 9; ++i) {
		this.elements[i] = (i % 3 == Math.floor(i / 3)) ? 1 : 0;
	}
	// todo
	// this.elements should be initialized with values equal to the identity matrix

	// -------------------------------------------------------------------------
	this.clone = function() {
		// todo
		// create a new Matrix3 instance that is an exact copy of this one and return it
		var theClone = new Matrix3();
		theClone.set(this.elements[0],this.elements[1],this.elements[2],this.elements[3],this.elements[4],this.elements[5],this.elements[6],this.elements[7],this.elements[8])

		return theClone /* should be a new Matrix instance*/;
	};

	// -------------------------------------------------------------------------
	this.copy = function(other) {
		// todo
		// copy all of the elements of other into the elements of 'this' matrix
		for (var i = 0; i < 9; i++){
			this.elements[i] = other.elements[i];
		}

		return this;
	};

	// -------------------------------------------------------------------------
	this.set = function (e11, e12, e13, e21, e22, e23, e31, e32, e33) {
		// todo
		// given the 9 elements passed in as argument e-row#col#, use
		// them as the values to set on 'this' matrix

		this.elements[0] = e11;
		this.elements[1] = e12;
		this.elements[2] = e13;
		this.elements[3] = e21;
		this.elements[4] = e22;
		this.elements[5] = e23;
		this.elements[6] = e31;
		this.elements[7] = e32;
		this.elements[8] = e33;
		
		return this;
	};

	// -------------------------------------------------------------------------
	this.getElement = function(row, col) {
		// todo
		var returnElement = this.elements[(3 * row) + col];
		//console.log(row + " row " + " col " + col);
		// use the row and col to get the proper index into the 1d element array and return it
		// return this.elements[/*index computed from row and col*/];
		
		return returnElement; // <== delete this line and use the one above
	};

	// -------------------------------------------------------------------------
	this.identity = function() {
		// todo
		// reset every element in 'this' matrix to make it the identity matrix
		for (var i = 0; i < 9; ++i) {
			this.elements[i] = (i % 3 == Math.floor(i / 3)) ? 1 : 0;
		}
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
		for (var i = 0; i < 9; ++i) {
			this.elements[i] = this.elements[i] * s;
		}

		return this;
	};

	// -------------------------------------------------------------------------
	this.multiplyRightSide = function(otherMatrixOnRight) {
		// todo
		// multiply 'this' matrix (on the left) by otherMatrixOnRight (on the right)
		// the results should be applied to the elements on 'this' matrix
		var cloned = this.clone();

		for (var i = 0; i < 9; ++i) {
			//console.log("i = " + i + "whole equation = " + cloned.elements[Math.floor(i/3)*3] +" * "+ otherMatrixOnRight.elements[i%3] +" + "+ cloned.elements[Math.floor(i/3)*3+1] +" * "+ otherMatrixOnRight.elements[(i%3)+3] +" + "+ cloned.elements[Math.floor(i/3)*3+2] +" * "+ otherMatrixOnRight.elements[(i%3)+6])
			this.elements[i] = cloned.elements[Math.floor(i/3)*3] * otherMatrixOnRight.elements[i%3] + cloned.elements[Math.floor(i/3)*3+1] * otherMatrixOnRight.elements[(i%3)+3] + cloned.elements[Math.floor(i/3)*3+2] * otherMatrixOnRight.elements[(i%3)+6];
		}

		return this;
	};

	// -------------------------------------------------------------------------
	this.determinant = function() {
		// todo
		// compute and return the determinant for 'this' matrix
		var determinant = 0;
		console.log("")



		var firstMinor = this.elements[0]*((this.elements[4] * this.elements[8]) - (this.elements[5] * this.elements[7]));
		var secondMinor = this.elements[1]*((this.elements[5] * this.elements[6]) - (this.elements[3] * this.elements[8]));
		var thirdMinor = this.elements[2]*((this.elements[3] * this.elements[7]) - (this.elements[4] * this.elements[6]));
		determinant = firstMinor + secondMinor + thirdMinor;

		return determinant; // should be the determinant
	};

	// -------------------------------------------------------------------------
	this.transpose = function() {
		// todo
		// modify 'this' matrix so that it becomes its transpose
		var elementHolder = 0;
		//console.log("Before transpose")
		//this.log();
		elementHolder = this.elements[1];
	//	console.log(elementHolder);
	//	console.log(this.elements[3] +" Element 3 " + this.elements[1] + " Element 1");
		this.elements[1] = this.elements[3]
		
		this.elements[3] = elementHolder;
	//	console.log(this.elements[3] +" Element 3 " + this.elements[1] + " Element 1");

		elementHolder = this.elements[2];
		this.elements[2] = this.elements[6];
		this.elements[6] = elementHolder;
		
		elementHolder = this.elements[5];
		this.elements[5] = this.elements[7];
		this.elements[7] = elementHolder;
		
		//console.log("After Transpose");
		//this.log();
		
		return this;
	};

	// -------------------------------------------------------------------------
	this.inverse = function() {
		// todo
		var determinant = this.determinant();
		var inbetweenMatrix = this.inverseStep();
		console.log("After inverseStep");
		inbetweenMatrix.log();
		console.log(1/determinant + " 1/determinant");
		
		inbetweenMatrix.multiplyScalar(1/determinant);
		inbetweenMatrix.log();
		this.copy(inbetweenMatrix);
		
	


		// modify 'this' matrix so that it becomes its inverse
		return this;
	};

	this.inverseStep = function(){
		var returnMatrix = new Matrix3();
		var indexOne = this.elements[4] * this.elements[0] - this.elements[5] * this.elements[7];
		var indexTwo = this.elements[3] * this.elements[8] - this.elements[5] * this.elements[6];
		var indexThree = this.elements[3] * this.elements[7] - this.elements[4] * this.elements[6];
		var indexFour = this.elements[1] * this.elements[8] - this.elements[2] * this.elements[7];
		var indexFive = this.elements[0] * this.elements[8] - this.elements[2] * this.elements[6];
		var indexSix = this.elements[0] * this.elements[7] - this.elements[1] * this.elements[6];
		var indexSeven = this.elements[1] * this.elements[5] - this.elements[2] * this.elements[4];
		var indexEight = this.elements[0] * this.elements[5] - this.elements[2] * this.elements[3];
		var indexNine = this.elements[0] * this.elements[4] - this.elements[1] * this.elements[3];
		indexTwo = -1 * indexTwo;
		indexFour = -1 * indexFour;
		indexSix = -1 * indexSix;
		indexEight = -1 * indexEight;
		
		returnMatrix.set(indexOne,indexTwo,indexThree,indexFour,indexFive,indexSix,indexSeven,indexEight,indexNine);
		returnMatrix.log();
		returnMatrix.transpose();
		returnMatrix.log();
		return returnMatrix;


	};

	// -------------------------------------------------------------------------
	this.log = function() {
		var e = this.elements;
		console.log('[ '+
					'\n ' + e[0]  + ', ' + e[1]  + ', ' + e[2]  +
			        '\n ' + e[3]  + ', ' + e[4]  + ', ' + e[5]  +
			        '\n ' + e[6]  + ', ' + e[7]  + ', ' + e[8] +
			        '\n] '
		);

		return this;
	};
};
