


var Sphere = function(origin, radius) {
	this.origin = origin || new Vector3(0,0,0);
	this.radius = radius || 1;

	// todo - make sure origin and radius are replaced with default values if and only
	//        if they are invalid(i.e. origin & radius should be Vector3) or undefined
	//        default origin should be zero vector
	//        default radius should be 1

	this.raycast = function(ray) {
		// todo determine whether the ray intersects this sphere and where
		
		
		var L = (ray.origin).subtract(origin);
		var tc = (L).dot(ray.direction)
		
		//Check to see if ray intersects sphere
		if(tc < 0)
		{
			var result = { hit: false, point: null };
			return result;
		}
		//console.log(L.lengthSqr());
		var d = Math.sqrt(Math.pow(tc,2)-L.lengthSqr());
		if(d > radius)
		{
			var result = { hit: false, point: null };
			return result;
		}
		
		var t1c = Math.sqrt(Math.pow(radius,2)-Math.pow(d,2));
		var t1 = tc - t1c;
		var t2 = tc + t1c;
		//console.log((ray.origin).add((ray.direction).multiplyScalar(t1)));
		//console.log((ray.origin).add((ray.direction).multiplyScalar(t2)));
		var p1 = (ray.origin).add((ray.direction).multiplyScalar(t1));
		var p2 = (ray.origin).add((ray.direction).multiplyScalar(t2));
		console.log(p1);
		console.log(p2);
		
		
		var result = {
			hit: true,
			point: p1,
			normal: p1.normalized(),
			distance: p1.length()
		};
		
		// Recommended steps
		// 1. review slides/book math
		// 2. create the vector(s) needed to solve for the coefficients in the
		//    quadratic equation
		// 3. calculate the discriminant
		// 4. use the result of the discriminant to determine if further computation
		//    is necessary
		// 5. return the following javascript object literal with the following properties
		//		case 1: no VALID intersections
		//	      var result = { hit: false, point: null }
		//		case 2: 1 or more intersections
		//			var result = { 
		//        		hit: true,
		// 				point: 'a Vector3 containing the closest VALID intersection',
		//              normal: 'a vector3 containing a unit length normal at the intersection',
		//              distance: 'a scalar containing the intersection distance from the ray origin'
		//          }

		//var result = {
		//	hit: ?,
		//	point: ?,
		//	normal: ?,
		//	distance: ?
		//};

		return result;
	}
};