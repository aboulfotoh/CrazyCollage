package com.objects.crazeycollage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.objects.crazeycollage.views.CollageView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val collage = findViewById<CollageView>(R.id.collage)

        val listRes = ArrayList<Int>()
        listRes.add(R.drawable.img1)
        listRes.add(R.drawable.img2)
        listRes.add(R.drawable.img3)
        listRes.add(R.drawable.img4)

        val listString = ArrayList<String>()

        listString.add("http://www.bandwidthblog.com/wp-content/uploads/2011/11/twitter-logo.png")
        listString.add("http://simpozia.com/pages/images/stories/windows-icon.png")
        listString.add("http://radiotray.sourceforge.net/radio.png")
        collage.setFixedCollage(false)
        collage.createCollageUrl(listString)

    }
}
