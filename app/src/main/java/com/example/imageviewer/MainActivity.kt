package com.example.imageviewer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter
    private val imageUrls = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView 2열 그리드로 초기화
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = ImageAdapter(imageUrls)
        recyclerView.adapter = adapter

        // 데이터 가져오기 시작
        fetchImageDataFromApi()
    }

    private fun fetchImageDataFromApi() {
        // 개발자 도구에서 확인한 API URL
        val url = "https://sch.sooplive.co.kr/api.php?m=categoryList&szKeyword=&szOrder=view_cnt&nPageNo=1&nListCnt=120&nOffset=0&szPlatform=pc"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("FetchData", "API 호출 실패", e)
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonString = response.body?.string() ?: ""
                Log.d("FetchData", "Received JSON: $jsonString")

                try {
                    val jsonObj = JSONObject(jsonString)
                    val result = jsonObj.getInt("result")
                    if (result == 1) {
                        val data = jsonObj.getJSONObject("data")
                        val list = data.getJSONArray("list")
                        imageUrls.clear()
                        for (i in 0 until list.length()) {
                            val item = list.getJSONObject(i)
                            // "cate_img" 필드에 이미지 URL이 있음
                            val imageUrl = item.getString("cate_img")
                            if (imageUrl.isNotBlank()) {
                                imageUrls.add(imageUrl)
                            }
                        }
                        Log.d("FetchData", "Extracted URLs: $imageUrls")
                        // 메인 스레드에서 RecyclerView 업데이트
                        runOnUiThread {
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.e("FetchData", "API 결과가 실패함 (result != 1)")
                    }
                } catch (e: Exception) {
                    Log.e("FetchData", "JSON 파싱 실패", e)
                }
            }
        })
    }
}
