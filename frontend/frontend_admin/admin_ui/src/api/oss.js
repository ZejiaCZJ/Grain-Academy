import request from '@/utils/request'

var api = "/ossservice/file"

export default{
    deleteOssFile(url){
        return request({
            url: `${api}`,
            method: "delete",
            data: url
          })
    },
}