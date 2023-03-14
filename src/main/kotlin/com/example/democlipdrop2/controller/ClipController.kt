package com.example.democlipdrop2.controller

import com.example.democlipdrop2.util.OkHttpUtil
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import org.apache.commons.io.FileUtils
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RequestMapping("/clip")
@RestController
class ClipController {

    companion object {
        val log: Logger = LogManager.getLogger(ClipController::class.java)
    }

    @Autowired
    private lateinit var okHttpUtil: OkHttpUtil

    /**
     * mask遮罩
     */
    @GetMapping("/mask")
    fun inpainting(): Response {
        print("inpainting")
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "image_file",
                "ori.jpg",
                File("docs/images/ori.jpg")
                    .asRequestBody("image/jpeg".toMediaType())
            )
            .addFormDataPart(
                "mask_file",
                "mask.png",
                File("docs/images/mask.png")
                    .asRequestBody("image/png".toMediaType())
            )
            .build()

        val response = okHttpUtil.doPost(requestBody)
        log.info("inpainting response :{}",response)
        val file = File("docs/results/inpainting.jpg")
        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return response // here is a byte array of the returned image
    }

    /**
     * 移除背景
     */
    @GetMapping("/removeBg")
    fun removeBg(): Response {
        print("removeBg")
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "image_file",
                    "street.jpg",
                    File("docs/images/street.jpg").asRequestBody("image/jpeg".toMediaType())
                )
                .build()

        val response = okHttpUtil.doPost(requestBody)
        log.info("removeBg response :{}",response)
        val file = File("docs/results/removeBg.png")
        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return response

    }

    /**
     * 锐化 清晰化
     */
    @GetMapping("/superResolution")
    fun superResolution(): Response {
        print("superResolution")
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "image_file",
                    "car.png",
                    File("docs/images/car.png").asRequestBody("image/jpeg".toMediaType())
                )
                .addFormDataPart("upscale", 2.toString())
                .build()

        val response = okHttpUtil.doPost(requestBody)
        log.info("superResolution response :{}",response)
        val file = File("docs/results/superResolution.png")
        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return response
    }

    /**
     * 结构化 模块化 结构化编码
     */

    /**
     * 移除圖片中文本
     */
    @GetMapping("/removeText")
    fun removeText(): Response {
        print("removeText")
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "image_file",
                    "image.jpg",
                    File("docs/images/image.jpg").asRequestBody("image/jpeg".toMediaType())
                )
                .build()
        val response = okHttpUtil.doPost(requestBody)
        log.info("removeText response :{}",response)
        val file = File("docs/results/removeText.png")
        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return response
    }

    @GetMapping("/textToImage")
    fun textToImage(): Response {
        print("textToImage")
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("prompt", "photograph of a cat surfing")
                .build()
        val response = okHttpUtil.doPost(requestBody)
        log.info("textToImage response :{}",response)
        val file = File("docs/results/textToImage.png")
        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return response
    }

    @GetMapping("/replaceBg")
    fun replaceBg(): Response {
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "image_file",
                    "car.jpg",
                    File("docs/images/car.jpg").asRequestBody("image/jpeg".toMediaType())
                )
                .addFormDataPart("prompt", "a cozy marble kitchen with wine glasses")
                .build()
        val response = okHttpUtil.doPost(requestBody)
        log.info("replaceBg response :{}",response)
        val file = File("docs/results/replaceBg.png")
        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return response

    }

    @GetMapping("/pde")
    fun pde(): Response {
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "image_file",
                    "portrait.jpg",
                    File("docs/images/portrait.jpg").asRequestBody("image/jpeg".toMediaType())
                )
                .build()
        val response = okHttpUtil.doPost(requestBody)
        log.info("pde response :{}",response)
        val file = File("docs/results/pde.png")
        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return response
    }

    @GetMapping("/psn")
    fun psn(): Response {
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "image_file",
                    "portrait.jpg",
                    File("docs/images/portrait.jpg").asRequestBody("image/jpeg".toMediaType())
                )
                .build()
        val response = okHttpUtil.doPost(requestBody)
        log.info("psn response :{}",response)
        val file = File("docs/results/psn.png")
        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return response
    }
}

