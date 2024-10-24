import request from '@/utils/request'

var api = "/coursesservice/video"

export default {
    addVideo(video){
        return request({
            url: `${api}`,
            method: 'post',
            data: video
        })
    },
    deleteVideoById(id){
        return request({
            url: `${api}/${id}`,
            method: 'delete',
        })
    },
    updateVideo(video){
        return request({
            url: `${api}`,
            method: 'put',
            data: video
        })
    },
    getVideoById(id){
        return request({
            url: `${api}/${id}`,
            method: 'get',
        })
    }
}