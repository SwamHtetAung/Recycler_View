package com.shaung.txt.part2videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.shaung.txt.part2videos.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_detail.*
import kotlinx.android.synthetic.main.image_card.view.*

class AlbumCardImageDetail : AppCompatActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        supportActionBar?.setTitle("Image Detail")

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val album = intent.getParcelableExtra<Album>("album_detail")

        Picasso.get().load(album.thumbnailUrl).into(detailImg)
        detailTitle.text =album.title
        detailAlbumID.text = "Album ID = " + album.albumId.toString()
        detailID.text = "Image ID = " + album.id.toString()
        detailURL.text = "Image URL = " + album.url
        detailThumbnailURL.text = "Thumbnail URL = " + album.thumbnailUrl.toString()

    }
}