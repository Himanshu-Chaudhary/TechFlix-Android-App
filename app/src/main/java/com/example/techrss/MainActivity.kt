package com.example.techrss

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.techrss.Adapter.AdapterManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewManager = LinearLayoutManager(this)
        var layout =  LinearLayout(this)
        var adapterManager = AdapterManager(resources)
        main_recyclerView.apply {
            layoutManager = viewManager
            adapter = adapterManager.adapter
        }
        main_recyclerView.setItemViewCacheSize(20)







    }
}
/**/