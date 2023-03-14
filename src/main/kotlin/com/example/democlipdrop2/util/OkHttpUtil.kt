package com.example.democlipdrop2.util

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class OkHttpUtil {
    companion object {
        val log: Logger = LogManager.getLogger(OkHttpUtil::class.java)
        val API_KEY = "95358a121e9458ae796e169fe5d3de0590cf7bb06f34112700815f4467318f1a8345d4589e0be0023e8192136926747e"
        val CLIP_DROP_API_V1 = "https://clipdrop-api.co/cleanup/v1"
    }

    @Autowired
    private lateinit var okHttpClient: OkHttpClient

    fun doPost(requestBody: RequestBody): Response {
        val request =
            Request.Builder()
                .header("x-api-key", API_KEY)
                .url(CLIP_DROP_API_V1)
                .post(requestBody)
                .build()
        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            log.info("success")
            return response
        }
    }
}