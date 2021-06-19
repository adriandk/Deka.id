package com.adrian.dekaid.ui.favorite.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrian.dekaid.R
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.utils.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

class FavoriteMovieAdapter: PagedListAdapter<MovieEntity, FavoriteMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((MovieEntity) -> Unit)? = null
    private var movieList = ArrayList<MovieEntity>()

    fun setData(newListData: List<MovieEntity>?) {
        if (newListData == null) return
        movieList.clear()
        movieList.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteMovieAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        } else {
            Log.e("MovieAdapter", movie.toString())
        }
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(dataFavorite: MovieEntity) {
            with(itemView) {
                movie_title.text = dataFavorite.movieTitle
                movie_vote.text = dataFavorite.movieVote.toString()
                movie_year.text = Formatter.getYear(dataFavorite.movieReleaseYear)
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + dataFavorite.movieImage)
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

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem

        }
    }

}