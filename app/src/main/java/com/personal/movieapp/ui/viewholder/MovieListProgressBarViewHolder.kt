package com.personal.movieapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.personal.movieapp.data.ErrorModel
import kotlinx.android.synthetic.main.movie_list_progress_bar.view.*

/**
 * Created by Rishabh
 */
class MovieListProgressBarViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(errorModel: ErrorModel) {
        view.apply {
            if (errorModel.showLoader)
                pbLoading.visibility = View.VISIBLE
            else
                pbLoading.visibility = View.GONE

            if (errorModel.errorMessage.isNotEmpty()) {
                tvResponse.text = errorModel.errorMessage
                tvResponse.visibility = View.VISIBLE
            } else
                tvResponse.visibility = View.GONE
        }
    }
}
