package com.shaung.txt.part2videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.shaung.txt.part2videos.models.Album
import kotlinx.android.synthetic.main.activity_album_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class AlbumList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)

        supportActionBar?.title = "Json to Gson Album List"

        doAsync {
            val jsons = URL("http://jsonplaceholder.typicode.com/photos").readText()

            uiThread {
                val albums = Gson().fromJson(jsons, Array<Album>::class.java).toList()

                albumRecyclerView.layoutManager=LinearLayoutManager(this@AlbumList)
                albumRecyclerView.adapter = MainAdapter(this@AlbumList, albums)
            }
        }
    }
}