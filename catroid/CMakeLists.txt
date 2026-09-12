cmake_minimum_required(VERSION 3.18.1)
project("catroid_local_ai")

# Подключение llama.cpp как подмодуля или библиотеки
add_subdirectory(llama.cpp)

add_library(
    catroid_ai_native
    SHARED
    native-lib.cpp
)

find_library(log-lib log)

target_link_libraries(
    catroid_ai_native
    llama
    ${log-lib}
)
