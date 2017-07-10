package com.trangiabao.sixjars.base.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NumericEditText extends AppCompatEditText {

    private static final char GROUPING_SEPARATOR = DecimalFormatSymbols.getInstance().getGroupingSeparator();
    private static final char DECIMAL_SEPARATOR = DecimalFormatSymbols.getInstance().getDecimalSeparator();
    private static final String LEADING_ZERO_FILTER_REGEX = "^0+(?!$)";

    private String mDefaultText = null;
    private String mPreviousText = "";
    private String mNumberFilterRegex = "[^\\d\\" + DECIMAL_SEPARATOR + "]";
    private List<NumericValueWatcher> mNumericListeners = new ArrayList<>();

    private final TextWatcher mTextWatcher = new TextWatcher() {
        private boolean validateLock = false;

        @Override
        public void afterTextChanged(Editable s) {
            if (validateLock)
                return;
            if (countMatches(s.toString(), String.valueOf(DECIMAL_SEPARATOR)) > 1) {
                validateLock = true;
                setText(mPreviousText);
                setSelection(mPreviousText.length());
                validateLock = false;
                return;
            }
            if (s.length() == 0) {
                handleNumericValueCleared();
                return;
            }
            setTextInternal(format(s.toString()));
            setSelection(getText().length());
            handleNumericValueChanged();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    };

    public NumericEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(mTextWatcher);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelection(getText().length());
            }
        });
    }

    private void handleNumericValueCleared() {
        mPreviousText = "";
        for (NumericValueWatcher listener : mNumericListeners) {
            listener.onCleared();
        }
    }

    private void handleNumericValueChanged() {
        mPreviousText = getText().toString();
        for (NumericValueWatcher listener : mNumericListeners) {
            listener.onChanged(getNumericValue());
        }
    }

    public void addNumericValueChangedListener(NumericValueWatcher watcher) {
        mNumericListeners.add(watcher);
    }

    public void removeAllNumericValueChangedListeners() {
        while (!mNumericListeners.isEmpty()) {
            mNumericListeners.remove(0);
        }
    }

    public void setDefaultNumericValue(double defaultNumericValue, final String defaultNumericFormat) {
        mDefaultText = String.format(defaultNumericFormat, defaultNumericValue);
        setTextInternal(mDefaultText);
    }

    public void clear() {
        setTextInternal(mDefaultText != null ? mDefaultText : "");
        if (mDefaultText != null) {
            handleNumericValueChanged();
        }
    }

    public double getNumericValue() {
        String original = getText().toString().replaceAll(mNumberFilterRegex, "");
        try {
            return NumberFormat.getInstance().parse(original).doubleValue();
        } catch (ParseException e) {
            return Double.NaN;
        }
    }

    private String format(final String original) {
        final String[] parts = original.split("\\" + DECIMAL_SEPARATOR, -1);
        String number = parts[0].replaceAll(mNumberFilterRegex, "")
                .replaceFirst(LEADING_ZERO_FILTER_REGEX, "");
        number = reverse(reverse(number).replaceAll("(.{3})", "$1" + GROUPING_SEPARATOR));
        number = removeStart(number, String.valueOf(GROUPING_SEPARATOR));
        if (parts.length > 1) {
            number += DECIMAL_SEPARATOR + parts[1];
        }
        return number;
    }

    private void setTextInternal(String text) {
        removeTextChangedListener(mTextWatcher);
        setText(text);
        addTextChangedListener(mTextWatcher);
    }

    private String reverse(String original) {
        if (original == null || original.length() <= 1) {
            return original;
        }
        return TextUtils.getReverse(original, 0, original.length()).toString();
    }

    private String removeStart(String str, String remove) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    private int countMatches(String str, String sub) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int lastIndex = str.lastIndexOf(sub);
        if (lastIndex < 0) {
            return 0;
        } else {
            return 1 + countMatches(str.substring(0, lastIndex), sub);
        }
    }

    public interface NumericValueWatcher {
        void onChanged(double newValue);

        void onCleared();
    }
}
