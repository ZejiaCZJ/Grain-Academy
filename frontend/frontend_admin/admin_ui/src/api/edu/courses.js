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
}