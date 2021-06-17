package com.adrian.dekaid.ui.tvshow

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrian.dekaid.R
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.utils.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

class ShowAdapter : PagedListAdapter<ShowEntity, ShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((ShowEntity) -> Unit)? = null
    private var showList = ArrayList<ShowEntity>()

    fun setData(newListData: List<ShowEntity>?) {
        if (newListData == null) return
        showList.clear()
        showList.addAll(newListData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowAdapter.ViewHolder, position: Int) {
//        holder.bind(showList[position])
        val show = getItem(position)
        if (show != null) {
            holder.bind(show)
        } else {
            Log.e("ShowAdapter", show.toString())
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataShow: ShowEntity) {
            with(itemView) {
//                Log.e("show adapter", dataShow.showImage)
                movie_title.text = dataShow.showName
                movie_vote.text = dataShow.showId.toString()
                movie_year.text = Formatter.getYear(dataShow.showFirstAir)
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + dataShow.showImage)
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

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShowEntity>() {
            override fun areItemsTheSame(oldItem: ShowEntity, newItem: ShowEntity): Boolean =
                oldItem.showId == newItem.showId

            override fun areContentsTheSame(oldItem: ShowEntity, newItem: ShowEntity): Boolean =
                oldItem == newItem

        }
    }

}