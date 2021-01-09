package com.ashish.pixel.data.models

import java.io.IOException

sealed class Result<out T> {

    data class Success<T>(val data: T): Result<T>()
    object Empty : Result<Nothing>()
    object Loading : Result<Nothing>()
    data class Error(val exception: Throwable) : Result<Nothing>() {

        val isNetworkError: Boolean get() = exception is IOException
    }

    companion object {

        /**
         * Return [Success] with [data] to emit.
         */
        fun <T> success(data: T) = Success(data)

        /**
         * Return [Error] with [exception] to emit.
         */
        fun error(exception: Throwable) = Error(exception)

        /**
         * Return [Empty].
         */
        fun empty() = Empty

        /**
         * Return [Loading].
         */
        fun loading() = Loading

        /**
         * Return [Empty] if [list] is empty, otherwise return [Success].
         */
        fun <T> successOrEmpty(list: List<T>): Result<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}