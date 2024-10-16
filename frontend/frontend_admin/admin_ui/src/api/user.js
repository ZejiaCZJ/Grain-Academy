import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/eduservice/administration/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/eduservice/administration/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/eduservice/administration/user/logout',
    method: 'post'
  })
}
