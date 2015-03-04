package com.hzgzh.balance.ui.validation;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by Administrator on 2015/2/8.
 */
public class VibValidate extends METValidator {
    public VibValidate(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean isValid(@NonNull CharSequence charSequence, boolean b) {
        try {
            double d = Double.parseDouble(charSequence.toString());
            if (d < 0)
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
