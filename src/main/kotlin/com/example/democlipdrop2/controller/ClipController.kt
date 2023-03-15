package com.example.democlipdrop2.controller

import com.example.democlipdrop2.common.R
import com.example.democlipdrop2.enums.APIType
import com.example.democlipdrop2.util.MultipartFileUtil.toFile
import com.example.democlipdrop2.util.OkHttpUtil
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException

@RequestMapping("/clip")
@RestController
class ClipController {

    companion object {
        val log: Logger = LogManager.getLogger(ClipController::class.java)
        val IMAGE_FILE = "image_file"
        val MASK_FILE = "mask_file"
    }

    @Autowired
    private lateinit var okHttpUtil: OkHttpUtil

    /**
     * mask遮罩
     */
    @PostMapping("/mask")
    fun inpainting(@RequestParam("imageFile") imageFile: MultipartFile,
                   @RequestParam("maskFile") maskFile: MultipartFile): R<Any> {
        log.info("inpainting")
        if (imageFile.isEmpty) {
             return R.fail("上传文件为空");
        }
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                IMAGE_FILE,
                imageFile.originalFilename,
                toFile(imageFile)
                    .asRequestBody(imageFile.contentType?.toMediaType())
            )
            .addFormDataPart(
                MASK_FILE,
                maskFile.originalFilename,
                toFile(maskFile)
                    .asRequestBody(imageFile.contentType?.toMediaType())
            )
            .build()
        val response = okHttpUtil.doPost(requestBody, APIType.INPAINTING)
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        log.info("inpainting response :{}",response)
        val file = File("docs/results/inpainting.jpg")
//        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return R.ok(response.body?.bytes());
    }

    /**
     * 移除背景
     */
    @PostMapping("/removeBg")
    fun removeBg(@RequestParam("imageFile") imageFile: MultipartFile): R<Any> {
        log.info("removeBg")
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    IMAGE_FILE,
                    imageFile.originalFilename,
                    toFile(imageFile).asRequestBody(imageFile.contentType?.toMediaType())
                )
                .build()
        val response = okHttpUtil.doPost(requestBody,APIType.REMOVE_BG)
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        log.info("removeBg response :{}",response)
        val file = File("docs/results/removeBg.png")
//        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return R.ok(response.body?.bytes())

    }

    /**
     * 锐化 清晰化
     */
    @PostMapping("/superResolution")
    fun superResolution(@RequestParam("imageFile") imageFile: MultipartFile): R<Any> {
        log.info("superResolution")
        log.info("imageFile:{}",imageFile.originalFilename)
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    IMAGE_FILE,
                    imageFile.originalFilename,
                    toFile(imageFile).asRequestBody(imageFile.contentType?.toMediaType())
                )
                .addFormDataPart("upscale",2.toString())
                .build()
        val response = okHttpUtil.doPost(requestBody,APIType.SUPER_RESOLUTION)
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        log.info("superResolution response :{}",response)
        val file = File("docs/results/superResolution.png")
//        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return R.ok(response.body?.bytes())
    }

    /**
     * 结构化 模块化 结构化编码
     */

    /**
     * 移除圖片中文本
     */
    @GetMapping("/removeText")
    fun removeText(@RequestParam("imageFile") imageFile: MultipartFile): R<Any> {
        log.info("removeText")
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    IMAGE_FILE,
                    imageFile.originalFilename,
                    toFile(imageFile).asRequestBody(imageFile.contentType?.toMediaType())
                )
                .build()
        val response = okHttpUtil.doPost(requestBody,APIType.REMOVE_TEXT)
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        log.info("removeText response :{}",response)
        val file = File("docs/results/removeText.png")
//        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return R.ok(response.body?.bytes())
    }

    @PostMapping("/textToImage")
    fun textToImage(@RequestParam("prompt") prompt: String): R<Any> {
        log.info("textToImage")
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("prompt", prompt)
                .build()
        val response = okHttpUtil.doPost(requestBody,APIType.TEXT_TO_IMAGE)
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        log.info("textToImage response :{}",response)
        val file = File("docs/results/textToImage.png")
//        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return R.ok(response.body?.bytes())
    }

    @GetMapping("/replaceBg")
    fun replaceBg(@RequestParam("imageFile") imageFile: MultipartFile,
                  @RequestParam("prompt") prompt:String): R<Any> {
        log.info("replaceBg")
        // this example uses the OkHttp library
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    IMAGE_FILE,
                    imageFile.originalFilename,
                    toFile(imageFile).asRequestBody(imageFile.contentType?.toMediaType())
                )
                .addFormDataPart("prompt", prompt)
                .build()
        val response = okHttpUtil.doPost(requestBody,APIType.REPLACE_BG)
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        log.info("replaceBg response :{}",response)
        val file = File("docs/results/replaceBg.png")
//        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return R.ok(response.body?.bytes())

    }

    @GetMapping("/pde")
    fun pde(@RequestParam("imageFile") imageFile: MultipartFile): R<Any> {
        // this example uses the OkHttp library
        log.info("pde")
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    IMAGE_FILE,
                    imageFile.originalFilename,
                    toFile(imageFile).asRequestBody(imageFile.contentType?.toMediaType())
                )
                .build()
        val response = okHttpUtil.doPost(requestBody,APIType.PDE)
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        log.info("pde response :{}",response)
        val file = File("docs/results/pde.png")
//        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return R.ok(response.body?.bytes())
    }

    @GetMapping("/psn")
    fun psn(@RequestParam("imageFile") imageFile: MultipartFile): R<Any> {
        // this example uses the OkHttp library
        log.info("psn")
        val requestBody =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    IMAGE_FILE,
                    imageFile.originalFilename,
                    toFile(imageFile).asRequestBody(imageFile.contentType?.toMediaType())
                )
                .build()
        val response = okHttpUtil.doPost(requestBody,APIType.PSN)
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        log.info("psn response :{}",response)
        val file = File("docs/results/psn.png")
//        FileUtils.writeByteArrayToFile(file, response.body?.bytes())
        return R.ok(response.body?.bytes())
    }
}

