package com.hzgzh.balance.ui.validation;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by Administrator on 2015/2/8.
 */
public class PhaseValidate extends METValidator {
    public PhaseValidate(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @NonNull
    @Override
    public String getErrorMessage() {
        return super.getErrorMessage();
    }

    @Override
    public void setErrorMessage(@NonNull String errorMessage) {
        super.setErrorMessage(errorMessage);
    }

    @Override
    public boolean isValid(@NonNull CharSequence charSequence, boolean b) {
        try {
            double d = Double.parseDouble(charSequence.toString());
            if (d < 0.0 || d > 360.0)
                return false;

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
