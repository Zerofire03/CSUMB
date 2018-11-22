precision mediump float;

uniform sampler2D uTexture;
uniform float uAlpha;

varying vec2 vTexCoord;

void main(void) {
    //gl_FragColor = vec4(vTexCoord, 0.0, uAlpha);
    gl_FragColor = texture2D(uTexture, vTexCoord);
    gl_FragColor.a = uAlpha;
}
