/*
 * An object representing a 3d vector to make operations simple and concise.
 */

var Vector3 = function(x, y, z) {
	this.x = x || 0; this.y = y || 0; this.z = z || 0;

	// todo - make sure to set a default value in case x, y, or z is not passed in

	this.set = function(x, y, z) {
		//todo set this' values to those from x, y, and z
		this.x = x; this.y = y; this.z = z;
		return this;
	}

	this.clone = function() {
		return new Vector3(this.x, this.y, this.z);
	};

	this.copy = function(other) {
		// copy the values from other into this
		this.x = other.x; this.y = other.y; this.z = other.z;
		return this;
	}

	this.add = function(v) {
		// todo - add v to this vector (1pt)
		// This SHOULD change the values of this.x, this.y, and this.z
		this.x += v.x; this.y += v.y; this.z += v.z;
		return this;
	};

	this.subtract = function(v) {
		// todo - subtract v from this vector (1pt)
		// This SHOULD change the values of this.x, this.y, and this.z
		this.x -= v.x; this.y -= v.y; this.z -= v.z;
		return this;
	};

	this.negate = function() {
		// multiply this vector by -1 (1pt)
		// This SHOULD change the values of this.x, this.y, and this.z
		this.x = -this.x; this.y = -this.y; this.z = -this.z;
		return this;
	};

	this.multiplyScalar = function(scalar) {
		// multiply this vector by "scalar" (1pt)
		// This SHOULD change the values of this.x, this.y, and this.z
		this.x *= scalar; this.y *= scalar; this.z *= scalar;
		return this;
	};

	this.length = function() {
		// todo - return the magnitude (a.k.a length) of this vector (1pt)
		// This should NOT change the values of this.x, this.y, and this.z
		return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
		//return 0;
	};

	this.lengthSqr = function() {
		// todo - return the squared magnitude of this vector ||v||^2 (1pt)
		// This should NOT change the values of this.x, this.y, and this.z
		return (Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
		return 0;
	};

	this.normalized = function() {
		// todo - return a new vector that is a normalized version of this vector (1pt)
		// This should NOT change the values of this.x, this.y, and this.z
		//return null; // Should return a new vector that is not this
		var length = Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
		var newx = this.x/length
		var newy = this.y/length
		var newz = this.z/length
		return new Vector3(newx, newy, newz);
	};

	this.normalize = function() {
		// todo - Change the components of this vector so that its magnitude will equal 1. (1pt)
		// This SHOULD change the values of this.x, this.y, and this.z
		var length =  Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
		this.x/=length 
		this.y/=length
		this.z/=length
		return this;
	};

	this.dot = function(other) {
		// todo - return the dot product betweent this vector and "other" (5pt)
		// This should NOT change the values of this.x, this.y, and this.z
		return (this.x*other.x) + (this.y*other.y) + (this.z*other.z);
	};

	this.cross = function(other) {
		// todo - return the cross product (as a new vector) betweent this vector and "other" (3pt)
		// This should NOT change the values of this.x, this.y, and this.z
		var cx = (this.y)*(other.z) - (this.z)*(other.y);
		var cy = (this.z)*(other.x) - (this.x)*(other.z);
		var cz = (this.x)*(other.y) - (this.y)*(other.x);
		return new Vector3(cx, cy, cz);
	}
};
