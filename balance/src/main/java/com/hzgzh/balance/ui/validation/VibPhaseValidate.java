package com.hzgzh.balance.ui.validation;

import android.support.annotation.NonNull;
import android.util.Log;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by Administrator on 2015/2/9.
 */
public class VibPhaseValidate extends METValidator {
    public VibPhaseValidate(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean isValid(@NonNull CharSequence charSequence, boolean b) {
        String s = charSequence.toString();
        int index = s.indexOf("/");

        Log.d("validate", Integer.toString(index));
        if (0 < index) {
            String front = s.substring(0, index).trim();
            Log.d("validate", "front=" + front);
            String back = s.substring(index + 1, s.length()).trim();
            Log.d("validate", "back=" + back);
            try {
                double d1 = Double.parseDouble(front);
                double d2 = Double.parseDouble(back);
                if (d1 < 0) return false;
                if (d2 < 0 || d2 > 360) return false;
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }
}
