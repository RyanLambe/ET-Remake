#version 450 core

in vec3 position;

uniform mat4 transform;
uniform mat4 camTransform;
uniform mat4 projection;

void main(void){
    vec2 fontSize = vec2(0.05, 0.05);
    gl_Position = projection * camTransform * transform * vec4(position.x * fontSize.x, -position.y * fontSize.y, position.z, 1.0);
}