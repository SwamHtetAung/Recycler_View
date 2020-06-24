package com.shaung.txt.part2videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.shaung.txt.part2videos.models.Album
import kotlinx.android.synthetic.main.activity_album_card.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class AlbumCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_card)

        supportActionBar?.setTitle("Json to Gson Album Card")

        doAsync {
            val jsons = URL("http://jsonplaceholder.typicode.com/photos").readText()

            uiThread {
                val albums = Gson().fromJson(jsons, Array<Album>::class.java).toList()

                albumRecyclerView_Card.layoutManager = GridLayoutManager(this@AlbumCard,2)
                albumRecyclerView_Card.adapter = SecondAdapter(this@AlbumCard, albums)

            }
        }
    }
}