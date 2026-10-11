#version 150

uniform sampler2D InSampler;
uniform vec2 InSize;
uniform float BlurRadius;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec4 color = vec4(0.0);
    float totalWeight = 0.0;
    int radius = int(ceil(BlurRadius));
    float sigma = BlurRadius * 0.5;
    float sigma2 = sigma * sigma;

    for (int x = -radius; x <= radius; x++) {
        for (int y = -radius; y <= radius; y++) {
            vec2 offset = vec2(float(x), float(y)) / InSize;
            float dist = float(x * x + y * y);
            float weight = exp(-dist / (2.0 * sigma2));
            color += texture(InSampler, texCoord + offset) * weight;
            totalWeight += weight;
        }
    }

    if (totalWeight > 0.0) {
        color /= totalWeight;
    }

    fragColor = color;
}
