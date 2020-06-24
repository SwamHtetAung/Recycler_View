package com.shaung.txt.part2videos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaung.txt.part2videos.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_row.view.*

class MainAdapter(val context : Context, val album : List<Album>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val cellForRow = layoutInflater.inflate(R.layout.image_row,parent,false)
        return ViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return album.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var album = album[position]

        Picasso.get().load(album.thumbnailUrl).into(holder.view.imgImg)
        holder.view.imgTitle.text =album.title
        holder.view.imgAlbumID.text ="Album ID = " + album.albumId.toString()
        holder.view.imgID.text ="Image ID = " + album.id.toString()
        holder.view.imgURL.text ="Image URL = " + album.url
        holder.view.imgThumbnailURL.text ="Thumbnail URL = " + album.thumbnailUrl

    }
}

class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){

}