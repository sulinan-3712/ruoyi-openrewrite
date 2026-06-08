package com.sulinan.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sulinan.common.annotation.Excel;
import com.sulinan.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统授权认证对象 sys_auth_cert
 * 
 * @author sulinan
 * @date 2026-06-08
 */
public class SysAuthCert extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 证书ID */
    private Long certId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 证书类型 */
    @Excel(name = "证书类型")
    private String certType;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String certNo;

    /** 证书姓名 */
    @Excel(name = "证书姓名")
    private String certName;

    /** 签发机关 */
    @Excel(name = "签发机关")
    private String issueAuthority;

    /** 签发日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签发日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueDate;

    /** 到期日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiryDate;

    /** 认证状态（0待审核 1已认证 2已拒绝 3已过期） */
    @Excel(name = "认证状态", readConverterExp = "0=待审核,1=已认证,2=已拒绝,3=已过期")
    private String certStatus;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private Long auditUserId;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String auditRemark;

    public void setCertId(Long certId) 
    {
        this.certId = certId;
    }

    public Long getCertId() 
    {
        return certId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setCertType(String certType) 
    {
        this.certType = certType;
    }

    public String getCertType() 
    {
        return certType;
    }

    public void setCertNo(String certNo) 
    {
        this.certNo = certNo;
    }

    public String getCertNo() 
    {
        return certNo;
    }

    public void setCertName(String certName) 
    {
        this.certName = certName;
    }

    public String getCertName() 
    {
        return certName;
    }

    public void setIssueAuthority(String issueAuthority) 
    {
        this.issueAuthority = issueAuthority;
    }

    public String getIssueAuthority() 
    {
        return issueAuthority;
    }

    public void setIssueDate(Date issueDate) 
    {
        this.issueDate = issueDate;
    }

    public Date getIssueDate() 
    {
        return issueDate;
    }

    public void setExpiryDate(Date expiryDate) 
    {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() 
    {
        return expiryDate;
    }

    public void setCertStatus(String certStatus) 
    {
        this.certStatus = certStatus;
    }

    public String getCertStatus() 
    {
        return certStatus;
    }

    public void setAuditUserId(Long auditUserId) 
    {
        this.auditUserId = auditUserId;
    }

    public Long getAuditUserId() 
    {
        return auditUserId;
    }

    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }

    public void setAuditRemark(String auditRemark) 
    {
        this.auditRemark = auditRemark;
    }

    public String getAuditRemark() 
    {
        return auditRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("certId", getCertId())
            .append("userId", getUserId())
            .append("certType", getCertType())
            .append("certNo", getCertNo())
            .append("certName", getCertName())
            .append("issueAuthority", getIssueAuthority())
            .append("issueDate", getIssueDate())
            .append("expiryDate", getExpiryDate())
            .append("certStatus", getCertStatus())
            .append("auditUserId", getAuditUserId())
            .append("auditTime", getAuditTime())
            .append("auditRemark", getAuditRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
