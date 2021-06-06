package com.adrian.dekaid.ui.tvshow

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.dekaid.R
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.utils.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

@Suppress("DEPRECATION")
class ShowAdapter : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {

    var onItemClick: ((MovieData) -> Unit)? = null
    private var showList = ArrayList<MovieData>()

    fun setShow(show: List<MovieData>?) {
        if (show == null) return
        this.showList.clear()
        this.showList.addAll(show)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowAdapter.ViewHolder, position: Int) {
        holder.bind(showList[position])
    }

    override fun getItemCount(): Int = showList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataShow: MovieData) {
            with(itemView) {
                Log.e("show adapter", "data in adapter going to recycler view")
                movie_title.text = dataShow.movieName
                movie_vote.text = dataShow.movieVote.toString()
                movie_year.text = Formatter.getYear(dataShow.movieFirstAir)
                Glide.with(itemView.context)
                    .load(dataShow.posterLink)
                    .apply(RequestOptions.placeholderOf(R.drawable.image_icon))
                    .error(R.drawable.broken_image)
                    .into(movie_image)
                button_detail.setOnClickListener {
                    onItemClick?.invoke(showList[adapterPosition])
                }
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(showList[adapterPosition])
            }
        }
    }

}