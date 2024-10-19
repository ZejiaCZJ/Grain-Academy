import request from '@/utils/request'

var api = "/eduservice/teacher"

export default {

    //Get: get all teachers
    getAllTeachers(){
      return request({
        url: `${api}`,
        method: 'get',
      })
    },

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
    },

    //Delete: delete a teacher by id
    //PARAMS:
    //  id: the id that need to be deleted
    removeTeacherById(id){
      return request({
        url: `${api}/${id}`,
        method: 'delete',
      })
    },

    //POST:add a given teacher
    //PARAMS:
    //  teacher: the teacher's information
    addTeacher(teacher){
      return request({
        url: `${api}`,
        method: "post",
        data: teacher
      })
    },

    //GET: get a teacher by id
    //PARAMS:
    //  id: the teacher's id
    searchTeacherById(id){
      return request({
        url: `${api}/${id}`,
        method: 'get',
      })
    },

    //PUT: update the information of the given teacher
    updateTeacher(teacher){
      return request({
        url: `${api}`,
        method: "put",
        data: teacher
      })
    }
}