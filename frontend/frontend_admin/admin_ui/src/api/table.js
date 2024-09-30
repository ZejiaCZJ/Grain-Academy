import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/administration/table/list',
    method: 'get',
    params
  })
}
