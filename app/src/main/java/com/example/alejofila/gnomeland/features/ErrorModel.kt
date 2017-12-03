package com.example.alejofila.gnomeland.features

import android.support.annotation.StringRes
import com.example.alejofila.gnomeland.R

/**
 *
 */

/**
 * This class is used to  pass errors from the presenter to the view
 */
enum class ErrorModelImpl : ErrorModel {
    NO_INTERNET_CONNECTION {
        @StringRes
        override fun message(): Int {
            return R.string.no_internet_connection
        }
    }

}


interface ErrorModel {
    fun message(): Int
}