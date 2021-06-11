package com.carolmusyoka.movieapp.util.extension

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.openUrl(uri: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    requireContext().startActivity(intent)
}
