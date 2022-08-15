package ru.kanogor.abra_kadabra.retrofit

sealed class State {
    object Loading : State()
    object Success : State()
    class Fail(val text: String) : State()
}
