package com.zeroemotion.footballleague.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zeroemotion.footballleague.R
import java.text.SimpleDateFormat
import java.util.*

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_error)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(uri)
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, urlString: String?) {
    view.loadImage(urlString, getProgressDrawable(view.context))
}

object CustomUI {
    lateinit var showProgress: Dialog
    fun showProgressDialog(context: Context) {
        showProgress = Dialog(context, R.style.AppTheme_NoActionBar)
        showProgress.window?.setBackgroundDrawable(ColorDrawable(Color.argb(100, 0, 0, 0)))
        showProgress.setContentView(R.layout.custom_progress)
        showProgress.setCancelable(true)
        showProgress.setTitle(R.string.str_progress)
    }
}

object FormatDate {
    fun formatCurrentDate(date: String): String {
        val dateFormat = SimpleDateFormat("dd/MM/yy")
        val newDate = dateFormat.parse(date)
        val simpleFormat = SimpleDateFormat("dd MMMM yyyy")
        simpleFormat.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        return simpleFormat.format(newDate)
    }

    fun formatCurrentTime(time: String): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss")
        val newTime = dateFormat.parse(time)
        val simpleFormat = SimpleDateFormat("HH:mm:ss aa zz")
        simpleFormat.timeZone = TimeZone.getTimeZone("GMT+7")
        return simpleFormat.format(newTime)
    }
}