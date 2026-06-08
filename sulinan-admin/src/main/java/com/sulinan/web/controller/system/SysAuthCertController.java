package com.sulinan.web.controller.system;

import java.util.List;

import com.sulinan.common.annotation.Log;
import com.sulinan.common.core.controller.BaseController;
import com.sulinan.common.core.domain.AjaxResult;
import com.sulinan.common.core.page.TableDataInfo;
import com.sulinan.common.enums.BusinessType;
import com.sulinan.common.utils.poi.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sulinan.system.domain.SysAuthCert;
import com.sulinan.system.service.ISysAuthCertService;

/**
 * 系统授权认证Controller
 * 
 * @author sulinan
 * @date 2026-06-08
 */
@RestController
@RequestMapping("/system/cert")
public class SysAuthCertController extends BaseController
{
    @Autowired
    private ISysAuthCertService sysAuthCertService;

    /**
     * 查询系统授权认证列表
     */
    @PreAuthorize("@ss.hasPermi('system:cert:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAuthCert sysAuthCert)
    {
        startPage();
        List<SysAuthCert> list = sysAuthCertService.selectSysAuthCertList(sysAuthCert);
        return getDataTable(list);
    }

    /**
     * 导出系统授权认证列表
     */
    @PreAuthorize("@ss.hasPermi('system:cert:export')")
    @Log(title = "系统授权认证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysAuthCert sysAuthCert)
    {
        List<SysAuthCert> list = sysAuthCertService.selectSysAuthCertList(sysAuthCert);
        ExcelUtil<SysAuthCert> util = new ExcelUtil<SysAuthCert>(SysAuthCert.class);
        util.exportExcel(response, list, "系统授权认证数据");
    }

    /**
     * 获取系统授权认证详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cert:query')")
    @GetMapping(value = "/{certId}")
    public AjaxResult getInfo(@PathVariable("certId") Long certId)
    {
        return success(sysAuthCertService.selectSysAuthCertByCertId(certId));
    }

    /**
     * 新增系统授权认证
     */
    @PreAuthorize("@ss.hasPermi('system:cert:add')")
    @Log(title = "系统授权认证", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAuthCert sysAuthCert)
    {
        return toAjax(sysAuthCertService.insertSysAuthCert(sysAuthCert));
    }

    /**
     * 修改系统授权认证
     */
    @PreAuthorize("@ss.hasPermi('system:cert:edit')")
    @Log(title = "系统授权认证", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAuthCert sysAuthCert)
    {
        return toAjax(sysAuthCertService.updateSysAuthCert(sysAuthCert));
    }

    /**
     * 删除系统授权认证
     */
    @PreAuthorize("@ss.hasPermi('system:cert:remove')")
    @Log(title = "系统授权认证", businessType = BusinessType.DELETE)
	@DeleteMapping("/{certIds}")
    public AjaxResult remove(@PathVariable Long[] certIds)
    {
        return toAjax(sysAuthCertService.deleteSysAuthCertByCertIds(certIds));
    }
}
