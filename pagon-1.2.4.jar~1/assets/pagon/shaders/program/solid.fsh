#version 150

uniform sampler2D InSampler;
uniform vec2 resolution;
uniform vec2 texelSize;
uniform int glowThickness;
uniform float glowIntensityScale;
uniform int glowSampleStep;
uniform vec4 fillColor;
uniform int OutlineMode;

uniform float saturation;
uniform float lightness;
uniform float factor;
uniform float time;

uniform sampler2D imageTexture;

in vec2 texCoord;

out vec4 fragColor;

vec3 rgb2hsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));
    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

vec3 hsv2rgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

void main() {
    vec4 original = texture(InSampler, texCoord);

    if (original.a > 0.0) {
        vec4 finalColor = original;

        if (fillColor.a > 0.0) {
            finalColor = vec4(mix(finalColor.rgb, fillColor.rgb, fillColor.a), original.a);
        }

        float s = saturation;
        float l = lightness;
        if (s > 0.0 && factor > 0.0) {
            vec3 hsv = rgb2hsv(finalColor.rgb);
            hsv.x = mod(time * factor + hsv.x, 1.0);
            hsv.y = mix(hsv.y, s, 0.3);
            hsv.z = mix(hsv.z, l, 0.3);
            finalColor.rgb = hsv2rgb(hsv);
        }

        fragColor = finalColor;
    } else {
        if (OutlineMode == 0) {
            fragColor = original;
            return;
        }

        int range = max(glowThickness, 1);
        vec4 edgeColor = vec4(0.0);
        float edgeWeight = 0.0;

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                if (x == 0 && y == 0) continue;
                vec2 offset = vec2(float(x), float(y)) * texelSize;
                vec4 sample = texture(InSampler, texCoord + offset);
                if (sample.a > 0.0) {
                    float dist = length(vec2(x, y));
                    float w = 1.0 / (1.0 + dist * 0.5);
                    edgeColor += vec4(sample.rgb, 1.0) * w;
                    edgeWeight += w;
                }
            }
        }

        if (edgeWeight > 0.0) {
            edgeColor /= edgeWeight;

            float s = saturation;
            float l = lightness;
            if (s > 0.0 && factor > 0.0) {
                vec3 hsv = rgb2hsv(edgeColor.rgb);
                hsv.x = mod(time * factor + hsv.x, 1.0);
                hsv.y = mix(hsv.y, s, 0.3);
                hsv.z = mix(hsv.z, l, 0.3);
                edgeColor.rgb = hsv2rgb(hsv);
            }

            float alpha = clamp(edgeWeight / float((range * 2 + 1) * (range * 2 + 1) - 1), 0.0, 1.0);
            fragColor = vec4(edgeColor.rgb, alpha);
        } else {
            fragColor = original;
        }
    }
}
