package com.shaung.txt.part2videos

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var bounceAnim : Animation
    lateinit var scaleAnim : Animation
    lateinit var fadeInAnim : Animation
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        window.setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        bounceAnim = AnimationUtils.loadAnimation(this,R.anim.bounce2)
        scaleAnim = AnimationUtils.loadAnimation(this,R.anim.scale)
        fadeInAnim = AnimationUtils.loadAnimation(this,R.anim.fade_in)



        appLogo.animation = bounceAnim
        bounceAnim.setAnimationListener(this)
        scaleAnim.setAnimationListener(this)


        appMotto.visibility= View.INVISIBLE


        btnStart.setOnClickListener {
            val intent = Intent(this@MainActivity, AlbumList::class.java)
            startActivity(intent)
        }

        btnStart2.setOnClickListener {
            val intent = Intent(this@MainActivity, AlbumCard::class.java)
            startActivity(intent)
        }
    }



    override fun onAnimationRepeat(animation: Animation?) {
    }
    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {

        if (animation==bounceAnim){
            appMotto.visibility= View.VISIBLE
            appMotto.animation = scaleAnim

        }else if (animation==scaleAnim){
            if (checkConn()){
                btnStart.visibility=View.VISIBLE
                btnStart.animation = fadeInAnim

                btnStart2.visibility=View.VISIBLE
                btnStart2.animation = fadeInAnim

            }else{
                alert("Not connected to the Internet!","Connection Problem"){
                    yesButton { toast("Try switching on/off Airplane Mode.") }
                    noButton { toast("Sorry, you can't use this app without Internet Connection!") }
                }.show()

                widProgress.visibility=View.VISIBLE
            }
        }
    }

    fun checkConn () : Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork : NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

}