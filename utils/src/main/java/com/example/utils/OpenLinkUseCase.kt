package com.example.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

class OpenLinkUseCase {

    operator fun Context.invoke(url: String) {
        val parsedUri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, parsedUri)
        startActivity(intent)
    }
}