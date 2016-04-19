package com.ccf.logic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

public class EaseCubicInInterpolator implements Interpolator {

    public EaseCubicInInterpolator() {}

    public EaseCubicInInterpolator(Context context, AttributeSet attrs) {}

    public float getInterpolation(float input) {
        return input * input * input;
    }
}
