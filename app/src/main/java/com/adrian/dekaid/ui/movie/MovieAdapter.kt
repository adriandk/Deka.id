package com.adrian.dekaid.ui.movie

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
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onItemClick: ((MovieData) -> Unit)? = null
    private var movieList = ArrayList<MovieData>()

    fun setMovie(movie: List<MovieData>?) {
        if (movie == null) return
        this.movieList.clear()
        this.movieList.addAll(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataMovie: MovieData) {
            with(itemView) {
                Log.e("movie adapter", "data in adapter going to recycler view")
                movie_title.text = dataMovie.movieTitle
                movie_vote.text = dataMovie.movieVote.toString()
                movie_year.text = Formatter.getYear(dataMovie.movieReleaseYear)
                Glide.with(itemView.context)
                    .load(dataMovie.posterLink)
                    .apply(RequestOptions.placeholderOf(R.drawable.image_icon))
                    .error(R.drawable.broken_image)
                    .into(movie_image)
                button_detail.setOnClickListener {
                    onItemClick?.invoke(movieList[adapterPosition])
                }
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(movieList[adapterPosition])
            }
        }
    }

}