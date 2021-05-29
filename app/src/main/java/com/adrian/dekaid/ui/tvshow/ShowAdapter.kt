package com.adrian.dekaid.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.dekaid.R
import com.adrian.dekaid.data.source.model.MovieData
import com.bumptech.glide.Glide
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
                movie_title.text = dataShow.movieTitle
                movie_genre.text = dataShow.movieGenre
                movie_year.text = dataShow.movieReleaseYear
                Glide.with(itemView.context)
                    .load(dataShow.movieImage)
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