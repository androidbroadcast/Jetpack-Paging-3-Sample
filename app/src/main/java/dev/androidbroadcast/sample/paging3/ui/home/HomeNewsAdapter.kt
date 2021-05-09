package dev.androidbroadcast.sample.paging3.ui.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import dev.androidbroadcast.sample.paging3.data.model.Article
import dev.androidbroadcast.sample.paging3.R
import dev.androidbroadcast.sample.paging3.databinding.ItemNewsBinding

class HomeNewsAdapter(context: Context) : ListAdapter<Article, HomeNewsViewHolder>(
    ArticleDiffItemCallback
) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        return HomeNewsViewHolder(layoutInflater.inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(ItemNewsBinding::bind)

    fun bind(article: Article) {
        with(viewBinding) {
            image.load(article.urlToImage) {
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }
            title.text = article.title
        }
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title && oldItem.url == newItem.url
    }
}