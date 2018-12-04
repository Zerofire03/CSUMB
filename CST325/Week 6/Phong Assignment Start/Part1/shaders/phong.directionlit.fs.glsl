precision mediump float;

uniform vec3 uLightDirection;
uniform vec3 uCameraPosition;
uniform sampler2D uTexture;

varying vec2 vTexcoords;
varying vec3 vWorldNormal;
varying vec3 vWorldPosition;

void main(void) {
    // todo - diffuse contribution
    // 1. normalize the light direction and store in a separate variable
    vec3 uLightDirectionNormal = normalize(uLightDirection);
    // 2. normalize the world normal and store in a separate variable
    vec3 vWorldNormalized = normalize(vWorldNormal);
    // 3. calculate the lambert term
    float lambert = dot(uLightDirectionNormal, vWorldNormalized);

    // todo - specular contribution
    // 1. in world space, calculate the direction from the surface point to the eye (normalized)
    vec3 directionNormal = normalize(uCameraPosition - vWorldPosition);
    // 2. in world space, calculate the reflection vector (normalized)
    //2(N to the surface of the mirror dot incident light ray L)N to the surface of the mirror - incident light ray L
    vec3 reflectionVector = 2.0 * dot(vWorldNormalized, uLightDirectionNormal) * vWorldNormalized - uLightDirectionNormal;
    // 3. calculate the phong term
    float phong = pow(dot(reflectionVector, directionNormal), 64.0);

    vec3 albedo = texture2D(uTexture, vTexcoords).rgb;

    // todo - combine
    // 1. apply light and material interaction for diffuse value by using the texture color as the material
    vec3 lightColor = vec3(1.0, 1.0, 1.0);
    
    // 2. apply light and material interaction for phong, assume phong material color is (0.3, 0.3, 0.3)

    vec3 ambient = albedo * 0.1;
    vec3 diffuseColor = albedo * lightColor * lambert;
    vec3 specularColor = lightColor * vec3(.3, .3, .3) * phong;
    vec3 finalColor = ambient + diffuseColor + specularColor;

    gl_FragColor = vec4(finalColor, 1.0);
    //gl_FragColor = texture2D(uTexture, vTexcoords);
    //gl_FragColor.a = lambert;
}
