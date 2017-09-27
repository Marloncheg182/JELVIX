package com.jelvixtest.jelvixtest.common;

import android.support.annotation.NonNull;

public class CheckNull {

    @FunctionalInterface
    public interface OnAction<T> {
        void onAction(T argument);
    }

    @FunctionalInterface
    public interface OnNull {
        void onNull();
    }

    public static <T> void check(T argument, @NonNull OnAction<T> onAction) {
        if (argument != null) {
            onAction.onAction(argument);
        }
    }

    public static <T> void check(T argument, @NonNull OnAction<T> onAction, @NonNull OnNull onNull) {
        if (argument != null) {
            onAction.onAction(argument);
        } else {
            onNull.onNull();
        }
    }
}
