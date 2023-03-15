import request from '@/utils/request'

export function inpainting(data){
    return request({
        url:'/clip/mask',
        method: 'post',
        data: data
    })
}

export function removeBg(data){
    return request({
        url:'/clip/removeBg',
        method: 'post',
        data: data
    })
}

export function superResolution(data){
    return request({
        url:'/clip/superResolution',
        method: 'post',
        data: data
    })
}

export function removeText(data){
    return request({
        url:'/clip/removeText',
        method: 'post',
        data: data
    })
}

export function textToImage(data){
    return request({
        url:'/clip/textToImage',
        method: 'post',
        data: data
    })
}

export function replaceBg(data){
    return request({
        url:'/clip/replaceBg',
        method: 'post',
        data: data
    })
}

export function pde(data){
    return request({
        url:'/clip/pde',
        method: 'post',
        data: data
    })
}

export function psn(data){
    return request({
        url:'/clip/psn',
        method: 'post',
        data: data
    })
}