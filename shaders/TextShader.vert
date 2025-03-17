#version 450 core

in vec3 position;

uniform mat4 transform;
uniform mat4 camTransform;
uniform mat4 projection;

void main(void){

    gl_Position = projection * camTransform * transform * vec4(position.x, -position.y, position.z, 1.0);
    //gl_Position = transform * vec4(position.x, -position.y, position.z, 1.0);
}