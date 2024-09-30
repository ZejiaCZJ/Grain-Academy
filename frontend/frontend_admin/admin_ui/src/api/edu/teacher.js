import request from '@/utils/request'

var api = "/eduservice/teacher"

export default {

    //GET: query a list of teachers using pagination and condition
    //PARAMS:
    //  current: the current page number
    //  limit: record numbers per page
    //  teachervo: query condition object
    selectTeacherByPageAndCondition(current, limit, teachervo) {
        return request({
          url: `${api}/${current}/${limit}`,
          method: 'post',
          data: teachervo //因为后端使用RequestBody,所以用data把对象转换为json然后传递到接口中
        })
    }
}