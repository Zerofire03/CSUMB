/*
 * A function object representing a 4x4 matrix
 */

var Matrix4 = function() {

    this.elements = new Float32Array(16);

    // -------------------------------------------------------------------------
    this.clone = function () {
        var newMatrix = new Matrix4();
        for (var i = 0; i < 16; ++i) {
            newMatrix.elements[i] = this.elements[i];
        }
        return newMatrix;
    };

    // -------------------------------------------------------------------------
    this.copy = function(otherMatrix4) {
        for (var i = 0; i < 16; ++i) {
            this.elements[i] = otherMatrix4.elements[i];
        }
        return this;
    };

    // -------------------------------------------------------------------------
    this.set = function (n11, n12, n13, n14, n21, n22, n23, n24, n31, n32, n33, n34, n41, n42, n43, n44) {
        var e = this.elements;

        e[0] = n11;   e[1] = n12;   e[2] = n13;   e[3] = n14;
        e[4] = n21;   e[5] = n22;   e[6] = n23;   e[7] = n24;
        e[8] = n31;   e[9] = n32;   e[10] = n33;  e[11] = n34;
        e[12] = n41;  e[13] = n42;  e[14] = n43;  e[15] = n44;

        return this;
    };

	// -------------------------------------------------------------------------
	this.getElement = function(row, col) {
		return this.elements[row * 4 + col];
	};

    // -------------------------------------------------------------------------
    this.setRotationX = function(degrees) {
        // todo
        // first convert to radians
        // set "this" matrix to be a rotation around the x-axis by angle degrees
        var radians = degrees * Math.PI / 180;

		var e = this.elements;
		var c = Math.cos(radians);
		var s = Math.sin(radians);

		e[0] = 1;   e[1] = 0;   e[2] = 0;   e[3] = 0;
		e[4] = 0;   e[5] = c;   e[6] = -s;  e[7] = 0;
		e[8] = 0;   e[9] = s;   e[10] = c;  e[11] = 0;
		e[12] = 0;  e[13] = 0;  e[14] = 0;  e[15] = 1;

        return this;
    };

    // -------------------------------------------------------------------------
    this.setRotationY = function(degrees) {
        // todo
        // first convert to radians
        // set "this" matrix to be a rotation around the y-axis by angle degrees
        var radians = degrees * Math.PI / 180;

		var e = this.elements;
		var c = Math.cos(radians);
		var s = Math.sin(radians);

		e[0] = c;   e[1] = 0;   e[2] = s;   e[3] = 0;
		e[4] = 0;   e[5] = 1;   e[6] = 0;   e[7] = 0;
		e[8] = -s;  e[9] = 0;   e[10] = c;  e[11] = 0;
		e[12] = 0;  e[13] = 0;  e[14] = 0;  e[15] = 1;
        return this;
    };

    // -------------------------------------------------------------------------
    this.setRotationZ = function(degrees) {
        // todo
        // first convert to radians
        // set "this" matrix to be a rotation around the z-axis by angle degrees
        var radians = degrees * Math.PI / 180;

		var e = this.elements;
		var c = Math.cos(radians);
		var s = Math.sin(radians);

		e[0] = c;   e[1] = -s;  e[2] = 0;   e[3] = 0;
		e[4] = s;   e[5] = c;   e[6] = 0;   e[7] = 0;
		e[8] = 0;   e[9] = 0;   e[10] = 1;  e[11] = 0;
		e[12] = 0;  e[13] = 0;  e[14] = 0;  e[15] = 1;

        return this;
    };

    // -------------------------------------------------------------------------
    this.translate = function(arg1, arg2, arg3) {
        // todo
        // keep the current matrix intact and add the translation values

        // this function needs to work for the following 2 cases:
        // - arg1 is a Vector3 (arg2 and arg3 will be undefined)
        // - arg1 is x, arg2 is y, and arg3 is zd
        if (arg1 instanceof Vector3) {
			this.elements[3] += arg1.x;
			this.elements[7] += arg1.y;
			this.elements[11] += arg1.z;
		} else {
			this.elements[3] += arg1;
			this.elements[7] += arg2;
			this.elements[11] += arg3;
		}

        return this;
    }

    // -------------------------------------------------------------------------
    this.setPerspective = function(fovy, aspect, near, far) {
        var fovyRads = (Math.PI / 180) * fovy;
		var t = near * Math.tan(fovyRads / 2);
		var r = t * aspect;

		// lazy
		this.identity();

		var e = this.elements;
		e[0] = near / r;
		e[5] = near / t;
		e[10] = -(far + near) / (far - near);
		e[11] = (-2 * near * far) / (far - near);
		e[14] = -1;
		e[15] = 0; // easy to forget this one (that lazy identity call...)

		return this;
    };

    // -------------------------------------------------------------------------
    this.identity = function() {
        this.set(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        );
        return this;
    };

    // -------------------------------------------------------------------------
    this.multiplyScalar = function(s) {
        for (var i = 0; i < 16; ++i) {
            this.elements[i] = this.elements[i] * s;
        }
    };

    // -------------------------------------------------------------------------
    this.multiplyRightSide = function(otherMatrix4) {
        // ignore
        return this;
    };

    // -------------------------------------------------------------------------
    this.determinant = function() {
        // ignore
        return this;
    };

    // -------------------------------------------------------------------------
    this.transpose = function() {
        // ignore
        return this;
    };

    // -------------------------------------------------------------------------
    this.inverse = function() {
        // ignore
        return this;
    };

    // -------------------------------------------------------------------------
    this.log = function() {
        var te = this.elements;
        console.log('[ '+
                    '\n ' +te[0] + ', ' + te[1] + ', ' + te[2] + ', ' + te[3] +
                    '\n ' + te[4] + ', ' + te[5] + ', ' + te[6] + ', ' + te[7] +
                    '\n ' + te[8] + ', ' + te[9] + ', ' + te[10] + ', ' + te[11] +
                    '\n ' + te[12] + ', ' + te[13] + ', ' + te[14] + ', ' + te[15] +
                    '\n]'
        );

        return this;
    };

    return this.identity();
};
