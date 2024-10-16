import request from '@/utils/request'

var api = "/eduservice/subject"

export default{
    addSubject(file){
        return request({
            url: `${api}`,
            method: "post",
            data: file
          })
    },

    getSubjects(){
        return request({
            url: `${api}`,
            method: "get",
          })
    }


}