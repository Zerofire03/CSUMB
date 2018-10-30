var Sphere = function(origin, radius) {
	this.origin = origin || new Vector3(0,0,0);
	this.radius = radius || 1;

	// todo - make sure origin and radius are replaced with default values if and only
	//        if they are invalid(i.e. origin & radius should be Vector3) or undefined
	//        default origin should be zero vector
	//        default radius should be 1

	this.raycast = function(ray) {
		// todo determine whether the ray intersects this sphere and where
		
		//Distance from ray origin to sphere center
		//var L = (origin).subtract(ray.origin);
		var L = this.origin.clone().subtract(ray.origin);
		//console.log("L:");
		//console.log(L);
		
		//Distance from potential point on sphere to d
		var tc = (L).dot(ray.direction)
		//console.log("tc:", tc);
		
		//Check to see if ray intersects sphere. If it doesn't return false
		if(tc <= 0)
		{
			var result = { hit: false, point: null };
			return result;
		}
		
		//Solve for distance between ray path and center of sphere
		var d = Math.sqrt(L.dot(L)-Math.pow(tc,2));
		//console.log("d:", d);
		
		
		//If distance is greater than radius, ray does not intersect sphere
		if(d > radius || d < 0)
		{
			//console.log("Radius: ", radius);
			var result = { hit: false, point: null };
			return result;
		}
		
		//Distance between point of intersection on sphere and d
		var t1c = Math.sqrt(Math.pow(radius,2)-Math.pow(d,2));
		//console.log("t1C:", t1c);
		
		//Distance between ray origin and first potential intersection
		var t1 = tc - t1c;
		//console.log("t1:", t1);
		
		//Distance between ray origin and second potential intersection
		var t2 = tc + t1c;
		//console.log("t2:", t2);
		
		//Vector of first potential intersection
		var scalar1 = (ray.direction).clone();
		var origin1 = (ray.origin).clone()
		var p1 = (origin1).add((scalar1).multiplyScalar(t1));
		//console.log("p1:");
		//console.log(p1);
		
		//Vector of second potential intersection
		var scalar2 = (ray.direction).clone();
		var origin2 = (ray.origin).clone();
		var p2 = (origin2).add((scalar2).multiplyScalar(t2));
		//console.log("p2:");
		//console.log(p2);
		
		//console.log("Normalized");
		//console.log(p1.normalized());
		
		if(t1 < t2)
		{
			var result = {
				hit: true,
				point: p1,
				normal: p1.normalized(),
				distance: t1
			};
			return result;
		}
		else
		{
			var result = {
				hit: true,
				point: p2,
				normal: p2.normalized(),
				distance: t2
			};
			return result;
		}
		
		
		
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

		//return result;
	}
};