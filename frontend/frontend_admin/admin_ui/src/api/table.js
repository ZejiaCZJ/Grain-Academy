import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/eduservice/administration/table/list',
    method: 'get',
    params
  })
}
