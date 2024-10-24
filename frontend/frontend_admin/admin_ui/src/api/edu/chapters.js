import request from '@/utils/request'

var api = "/coursesservice/chapter"

export default {
    getChaptersById(id) {
        return request({
            url: `${api}/all/${id}`,
            method: 'get',
        })
    },
    addChapter(chapter){
        return request({
            url: `${api}`,
            method: 'post',
            data: chapter
        })
    },
    modifyChapter(chapter){
        return request({
            url: `${api}`,
            method: 'put',
            data: chapter
        })
    },
    getChapterById(id){
        return request({
            url: `${api}/${id}`,
            method: 'get',
        })
    },
    deleteChapterById(id){
        return request({
            url: `${api}/${id}`,
            method: 'delete',
        })
    },
    addVideo(video){
        return request({
            url: `${api}`,
            method: 'post',
            data: video
        })
    },

}