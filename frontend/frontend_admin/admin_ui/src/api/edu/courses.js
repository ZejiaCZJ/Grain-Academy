import request from '@/utils/request'

var api = "/coursesservice/course"

export default{
    addCourseInfo(courseVo){
        return request({
            url: `${api}/add`,
            method: "post",
            data: courseVo
          })
    },
    getCourseInfo(id){
        return request({
            url: `${api}/${id}`,
            method: "get",
          })
    },
    updateCourseInfo(courseVo){
        return request({
            url: `${api}`,
            method: "put",
            data: courseVo
          })
    }
}