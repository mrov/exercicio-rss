package br.ufpe.cin.if710.rss

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import kotlinx.android.synthetic.main.itemlista.view.*

class RSSadapter(private val RSSList: List<ItemRSS>, private val context: Context) : RecyclerView.Adapter<RSSadapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itemlista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return RSSList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rss = RSSList[position]
        holder?.let {
            it.item_titulo.text = rss.title
            it.item_data.text = rss.pubDate
        }
    }

    class ViewHolder(View: View) : RecyclerView.ViewHolder(View) {
        val item_titulo = itemView.item_titulo!!
        val item_data = itemView.item_data!!
    }


}