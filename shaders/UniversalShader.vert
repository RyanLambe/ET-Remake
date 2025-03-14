#version 450 core

in vec3 position;

uniform mat4 transform;
uniform mat4 camTransform;
uniform mat4 projection;

out vec2 TexCoord;

void main(void){

    gl_Position = projection * camTransform * transform * vec4(position, 1.0);
    TexCoord = (position.xy + vec2(0.5, 0.5)) * vec2(1, -1);
}