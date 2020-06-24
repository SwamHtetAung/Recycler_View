package com.shaung.txt.part2videos

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaung.txt.part2videos.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_card.view.*

class SecondAdapter(val context : Context, val album : List<Album>) : RecyclerView.Adapter<ViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val layoutInflater = LayoutInflater.from(context)
        val cellInRow = layoutInflater.inflate(R.layout.image_card,parent, false)
        return ViewHolder2(cellInRow)
    }

    override fun getItemCount(): Int {
        return album.size
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {

        var album = album[position]
        Picasso.get().load(album.thumbnailUrl).into(holder.view.cardImage)
        holder.view.cardTitle.text = album.title
        holder.view.cardID.text ="Image ID = " + album.id.toString()
        holder.view.cardButton.setOnClickListener {
            val intent = Intent(context, AlbumCardImageDetail::class.java)
            intent.putExtra("album_detail", album)
            context.startActivity(intent)

        }

    }
}

class ViewHolder2(var view: View) : RecyclerView.ViewHolder(view){

}