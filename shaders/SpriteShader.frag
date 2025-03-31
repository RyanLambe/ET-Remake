#version 450 core

out vec4 out_Color;

in vec2 TexCoord;

uniform vec4 color;

uniform bool useTexture;
uniform sampler2D ourTexture;

void main(){

    if(useTexture)
        out_Color = texture(ourTexture, TexCoord) * color;
    else
        out_Color = color;


    // discard is not efficient, but it is not a demanding game so its not worth improving
    if (out_Color.a < 1)
        discard;
}