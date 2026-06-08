package com.sulinan.system.service;

import java.util.List;
import com.sulinan.system.domain.SysAuthCert;

/**
 * 系统授权认证Service接口
 * 
 * @author sulinan
 * @date 2026-06-08
 */
public interface ISysAuthCertService 
{
    /**
     * 查询系统授权认证
     * 
     * @param certId 系统授权认证主键
     * @return 系统授权认证
     */
    public SysAuthCert selectSysAuthCertByCertId(Long certId);

    /**
     * 查询系统授权认证列表
     * 
     * @param sysAuthCert 系统授权认证
     * @return 系统授权认证集合
     */
    public List<SysAuthCert> selectSysAuthCertList(SysAuthCert sysAuthCert);

    /**
     * 新增系统授权认证
     * 
     * @param sysAuthCert 系统授权认证
     * @return 结果
     */
    public int insertSysAuthCert(SysAuthCert sysAuthCert);

    /**
     * 修改系统授权认证
     * 
     * @param sysAuthCert 系统授权认证
     * @return 结果
     */
    public int updateSysAuthCert(SysAuthCert sysAuthCert);

    /**
     * 批量删除系统授权认证
     * 
     * @param certIds 需要删除的系统授权认证主键集合
     * @return 结果
     */
    public int deleteSysAuthCertByCertIds(Long[] certIds);

    /**
     * 删除系统授权认证信息
     * 
     * @param certId 系统授权认证主键
     * @return 结果
     */
    public int deleteSysAuthCertByCertId(Long certId);
}
