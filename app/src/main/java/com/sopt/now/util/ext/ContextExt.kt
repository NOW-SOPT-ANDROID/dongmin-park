package com.sopt.now.util.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.snackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : Activity> Activity.navigateToScreen(
    flags: List<Int>? = null,
    isFinish: Boolean = true,
) {
    Intent(this, T::class.java).apply {
        flags?.map {
            addFlags(it)
        }
        startActivity(this)
    }
    if (isFinish) {
        finish()
    }
}