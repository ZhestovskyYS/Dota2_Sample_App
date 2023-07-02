package com.example.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import coil.ImageLoader
import coil.request.ImageRequest

class LoadImageUseCase {
    suspend operator fun invoke(context: Context, url: String): Bitmap? {
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()


        return ImageLoader(context)
            .execute(request)
            .drawable?.toBitmapOrNull()
    }
}