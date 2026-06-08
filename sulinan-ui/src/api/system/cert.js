import request from '@/utils/request'

// 查询系统授权认证列表
export function listCert(query) {
  return request({
    url: '/system/cert/list',
    method: 'get',
    params: query
  })
}

// 查询系统授权认证详细
export function getCert(certId) {
  return request({
    url: '/system/cert/' + certId,
    method: 'get'
  })
}

// 新增系统授权认证
export function addCert(data) {
  return request({
    url: '/system/cert',
    method: 'post',
    data: data
  })
}

// 修改系统授权认证
export function updateCert(data) {
  return request({
    url: '/system/cert',
    method: 'put',
    data: data
  })
}

// 删除系统授权认证
export function delCert(certId) {
  return request({
    url: '/system/cert/' + certId,
    method: 'delete'
  })
}
