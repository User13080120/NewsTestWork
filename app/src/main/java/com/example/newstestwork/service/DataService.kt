package com.example.newstestwork.service

data class Action<out T>(val status: Status, val data: T?, val error: Error?) {

    companion object {
        fun <T> loading(): Action<T> {
            return Action(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): Action<T> {
            return Action(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Error?): Action<T> {
            return Action(Status.ERROR, null, error)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}