package com.sulinan.system.service.impl;

import java.util.List;

import com.sulinan.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sulinan.system.mapper.SysAuthCertMapper;
import com.sulinan.system.domain.SysAuthCert;
import com.sulinan.system.service.ISysAuthCertService;

/**
 * 系统授权认证Service业务层处理
 * 
 * @author sulinan
 * @date 2026-06-08
 */
@Service
public class SysAuthCertServiceImpl implements ISysAuthCertService 
{
    @Autowired
    private SysAuthCertMapper sysAuthCertMapper;

    /**
     * 查询系统授权认证
     * 
     * @param certId 系统授权认证主键
     * @return 系统授权认证
     */
    @Override
    public SysAuthCert selectSysAuthCertByCertId(Long certId)
    {
        return sysAuthCertMapper.selectSysAuthCertByCertId(certId);
    }

    /**
     * 查询系统授权认证列表
     * 
     * @param sysAuthCert 系统授权认证
     * @return 系统授权认证
     */
    @Override
    public List<SysAuthCert> selectSysAuthCertList(SysAuthCert sysAuthCert)
    {
        return sysAuthCertMapper.selectSysAuthCertList(sysAuthCert);
    }

    /**
     * 新增系统授权认证
     * 
     * @param sysAuthCert 系统授权认证
     * @return 结果
     */
    @Override
    public int insertSysAuthCert(SysAuthCert sysAuthCert)
    {
        sysAuthCert.setCreateTime(DateUtils.getNowDate());
        return sysAuthCertMapper.insertSysAuthCert(sysAuthCert);
    }

    /**
     * 修改系统授权认证
     * 
     * @param sysAuthCert 系统授权认证
     * @return 结果
     */
    @Override
    public int updateSysAuthCert(SysAuthCert sysAuthCert)
    {
        sysAuthCert.setUpdateTime(DateUtils.getNowDate());
        return sysAuthCertMapper.updateSysAuthCert(sysAuthCert);
    }

    /**
     * 批量删除系统授权认证
     * 
     * @param certIds 需要删除的系统授权认证主键
     * @return 结果
     */
    @Override
    public int deleteSysAuthCertByCertIds(Long[] certIds)
    {
        return sysAuthCertMapper.deleteSysAuthCertByCertIds(certIds);
    }

    /**
     * 删除系统授权认证信息
     * 
     * @param certId 系统授权认证主键
     * @return 结果
     */
    @Override
    public int deleteSysAuthCertByCertId(Long certId)
    {
        return sysAuthCertMapper.deleteSysAuthCertByCertId(certId);
    }
}
