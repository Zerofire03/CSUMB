
var Sphere = function(origin, radius, color, reflectivity) {
	this.origin = origin;
	this.radius = radius;
	this.color = color || new Vector3(1, 1, 1);
	this.reflectivity = (reflectivity != undefined) ? reflectivity: 0;

	this.raycast = function(ray) {
		var sphereOriginToRayOrigin = ray.origin.clone().subtract(this.origin);

		var a = ray.direction.dot(ray.direction);
		var b = ray.direction.clone().multiplyScalar(2).dot(sphereOriginToRayOrigin);
		var c = sphereOriginToRayOrigin.dot(sphereOriginToRayOrigin) - this.radius * this.radius;

		var result = { hit: false, point: null };

		var discriminant = b * b - 4 * a * c;
		if (discriminant < 0) {
			return result;
		}

		var t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
		var t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

		if (t1 > 0 && t2 > 0) {
			var t = (t1 < t2) ? t1: t2;
			var originOffset = ray.clone().direction.multiplyScalar(t);

			result.hit = true;
			result.point = ray.origin.clone().add(originOffset);
			result.normal = (result.point.clone().subtract(this.origin)).normalized();
			result.distance = originOffset.length();
		}

		return result;
	}
};