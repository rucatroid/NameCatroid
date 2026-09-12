package org.catroid.catroid.ai;

public class LocalLlamaBridge {

    static {
        // Загрузка скомпилированной нативной библиотеки
        System.loadLibrary("catroid_ai_native");
    }

    // Нативный метод, запускающий вычисления на устройстве
    public native String generateTokensNative(String modelPath, String prompt);

    public void runInferenceAsync(String modelPath, String prompt, LocalAiCallback callback) {
        new Thread(() -> {
            try {
                String result = generateTokensNative(modelPath, prompt);
                callback.onSuccess(result);
            } catch (Exception e) {
                callback.onError(e);
            }
        }).start();
    }

    public interface LocalAiCallback {
        void onSuccess(String jsonResult);
        void onError(Exception e);
    }
}
