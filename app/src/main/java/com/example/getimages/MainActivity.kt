package com.example.getimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.RenderScript
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var searchBtn: Button
    private val dataList: MutableList<ImagesApi> = mutableListOf()
    private lateinit var adapter: Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = Adapter(dataList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_view.adapter = adapter

        searchBtn = findViewById(R.id.searchBtn)

        searchBtn.setOnClickListener {
            AndroidNetworking.initialize(this)

            AndroidNetworking.get("http://api-edu.gtl.ai/api/v1/imagesearch/bing")
                .addQueryParameter(
                    "url",
                    "https://e7.pngegg.com/pngimages/796/636/png-clipart-banana-banana.png"
                )
                .build()
                .getAsObject(Liste::class.java, object : ParsedRequestListener<Liste> {
                    override fun onResponse(response: Liste) {
                        dataList.addAll(response)
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }
                })
        }
    }
}