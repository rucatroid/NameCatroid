#include <jni.h>
#include <string>
#include "llama.h"

extern "C" JNIEXPORT jstring JNICALL
Java_org_catroid_catroid_ai_LocalLlamaBridge_generateTokensNative(
        JNIEnv* env,
        jobject /* this */,
        jstring model_path,
        jstring prompt) {
    
    const char* path = env->GetStringUTFChars(model_path, nullptr);
    const char* user_prompt = env->GetStringUTFChars(prompt, nullptr);

    // Логика инициализации llama.cpp и выполнения инференса на CPU/GPU устройства
    // (Llama context init -> eval prompt -> decode -> get output)
    std::string response_json = "{\"actions\": [{\"type\": \"move\", \"steps\": 20}]}";

    env->ReleaseStringUTFChars(model_path, path);
    env->ReleaseStringUTFChars(prompt, user_prompt);

    return env->NewStringUTF(response_json.c_str());
}
