package com.example.democlipdrop2.enums

import lombok.AllArgsConstructor
import lombok.Getter

@Getter
@AllArgsConstructor
enum class APIType(val code:String,val desc:String) {
    INPAINTING("cleanup","https://clipdrop.co/apis/docs/inpainting"),
    REMOVE_BG("remove-background","https://clipdrop.co/apis/docs/remove-background"),
    SUPER_RESOLUTION("super-resolution","https://clipdrop.co/apis/docs/super-resolution"),
    REMOVE_TEXT("remove-text","https://clipdrop.co/apis/docs/remove-text"),
    TEXT_TO_IMAGE("text-to-image","https://clipdrop.co/apis/docs/text-to-image"),
    REPLACE_BG("replace-background","https://clipdrop.co/apis/docs/replace-background"),
    PDE("portrait-depth-estimation","https://clipdrop.co/apis/docs/portrait-depth-estimation"),
    PSN("portrait-surface-normals","https://clipdrop.co/apis/docs/portrait-surface-normals"),
}