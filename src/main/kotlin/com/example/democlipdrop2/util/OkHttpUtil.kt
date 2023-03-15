package com.example.democlipdrop2.util

import com.example.democlipdrop2.DemoClipdrop2Application
import com.example.democlipdrop2.controller.ClipController
import com.example.democlipdrop2.enums.APIType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.apache.commons.io.FileUtils
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException

@Component
class OkHttpUtil @Autowired constructor(
   private val okHttpClient: OkHttpClient
) {
    companion object {
        val log: Logger = LogManager.getLogger(OkHttpUtil::class.java)
        val API_KEY = "95358a121e9458ae796e169fe5d3de0590cf7bb06f34112700815f4467318f1a8345d4589e0be0023e8192136926747e"
        val CLIP_DROP_API_V1 = "https://clipdrop-api.co/%s/v1"

    }


    fun doPost(requestBody: RequestBody,apiType: APIType): Response {
        log.info("requestBody:{}", requestBody)
        val request =
            Request.Builder()
                .header("x-api-key", API_KEY)
                .url(String.format(CLIP_DROP_API_V1,apiType.code))
                .post(requestBody)
                .build()
        return okHttpClient.newCall(request).execute()
    }


}