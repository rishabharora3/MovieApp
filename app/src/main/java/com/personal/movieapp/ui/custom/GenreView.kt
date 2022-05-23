package com.personal.movieapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.personal.movieapp.R

class GenreView @JvmOverloads constructor(
    mContext: Context,
    attrs: AttributeSet? = null
) : LinearLayout(
    mContext, attrs
) {
    private val view: View
    private var tvGenre: TextView? = null

    init {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.genre_item, this, true)
        initViews()
    }

    private fun initViews() {
        tvGenre = view.findViewById(R.id.tvGenre)
    }

    fun setText(genre: String?) {
        tvGenre?.text = genre
    }

}