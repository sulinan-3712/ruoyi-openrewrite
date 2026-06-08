<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证书类型" prop="certType">
        <el-select v-model="queryParams.certType" placeholder="请选择证书类型" clearable>
          <el-option label="身份证" value="identity_card" />
          <el-option label="护照" value="passport" />
          <el-option label="驾驶证" value="driver_license" />
          <el-option label="营业执照" value="business_license" />
        </el-select>
      </el-form-item>
      <el-form-item label="证书编号" prop="certNo">
        <el-input
          v-model="queryParams.certNo"
          placeholder="请输入证书编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证书姓名" prop="certName">
        <el-input
          v-model="queryParams.certName"
          placeholder="请输入证书姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签发机关" prop="issueAuthority">
        <el-input
          v-model="queryParams.issueAuthority"
          placeholder="请输入签发机关"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签发日期" prop="issueDate">
        <el-date-picker clearable
          v-model="queryParams.issueDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择签发日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="到期日期" prop="expiryDate">
        <el-date-picker clearable
          v-model="queryParams.expiryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择到期日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="认证状态" prop="certStatus">
        <el-select v-model="queryParams.certStatus" placeholder="请选择认证状态" clearable>
          <el-option label="待审核" value="0" />
          <el-option label="已认证" value="1" />
          <el-option label="已拒绝" value="2" />
          <el-option label="已过期" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人ID" prop="auditUserId">
        <el-input
          v-model="queryParams.auditUserId"
          placeholder="请输入审核人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核时间" prop="auditTime">
        <el-date-picker clearable
          v-model="queryParams.auditTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择审核时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:cert:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:cert:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:cert:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:cert:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="certList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="证书ID" align="center" prop="certId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="证书类型" align="center" prop="certType">
        <template slot-scope="scope">
          <span v-if="scope.row.certType === 'identity_card'">身份证</span>
          <span v-else-if="scope.row.certType === 'passport'">护照</span>
          <span v-else-if="scope.row.certType === 'driver_license'">驾驶证</span>
          <span v-else-if="scope.row.certType === 'business_license'">营业执照</span>
          <span v-else>{{ scope.row.certType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="证书编号" align="center" prop="certNo" />
      <el-table-column label="证书姓名" align="center" prop="certName" />
      <el-table-column label="签发机关" align="center" prop="issueAuthority" />
      <el-table-column label="签发日期" align="center" prop="issueDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.issueDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到期日期" align="center" prop="expiryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expiryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="认证状态" align="center" prop="certStatus">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.certStatus === '0'" type="warning">待审核</el-tag>
          <el-tag v-else-if="scope.row.certStatus === '1'" type="success">已认证</el-tag>
          <el-tag v-else-if="scope.row.certStatus === '2'" type="danger">已拒绝</el-tag>
          <el-tag v-else-if="scope.row.certStatus === '3'" type="info">已过期</el-tag>
          <el-tag v-else type="info">{{ scope.row.certStatus }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核人ID" align="center" prop="auditUserId" />
      <el-table-column label="审核时间" align="center" prop="auditTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.auditTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核备注" align="center" prop="auditRemark" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:cert:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:cert:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改系统授权认证对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="用户ID" prop="userId">
              <el-input v-model="form.userId" placeholder="请输入用户ID" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="证书类型" prop="certType">
              <el-select v-model="form.certType" placeholder="请选择证书类型" style="width: 100%">
                <el-option label="身份证" value="identity_card" />
                <el-option label="护照" value="passport" />
                <el-option label="驾驶证" value="driver_license" />
                <el-option label="营业执照" value="business_license" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="证书编号" prop="certNo">
              <el-input v-model="form.certNo" placeholder="请输入证书编号" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="证书姓名" prop="certName">
              <el-input v-model="form.certName" placeholder="请输入证书姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="签发机关" prop="issueAuthority">
              <el-input v-model="form.issueAuthority" placeholder="请输入签发机关" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="签发日期" prop="issueDate">
              <el-date-picker clearable
                v-model="form.issueDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择签发日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="到期日期" prop="expiryDate">
              <el-date-picker clearable
                v-model="form.expiryDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择到期日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="认证状态" prop="certStatus">
              <el-radio-group v-model="form.certStatus">
                <el-radio label="0">待审核</el-radio>
                <el-radio label="1">已认证</el-radio>
                <el-radio label="2">已拒绝</el-radio>
                <el-radio label="3">已过期</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="审核人ID" prop="auditUserId">
              <el-input v-model="form.auditUserId" placeholder="请输入审核人ID" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="审核时间" prop="auditTime">
              <el-date-picker clearable
                v-model="form.auditTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择审核时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="审核备注" prop="auditRemark">
              <el-input v-model="form.auditRemark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCert, getCert, delCert, addCert, updateCert } from "@/api/system/cert"

export default {
  name: "Cert",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 系统授权认证表格数据
      certList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        certType: null,
        certNo: null,
        certName: null,
        issueAuthority: null,
        issueDate: null,
        expiryDate: null,
        certStatus: null,
        auditUserId: null,
        auditTime: null,
        auditRemark: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        certType: [
          { required: true, message: "证书类型不能为空", trigger: "change" }
        ],
        certNo: [
          { required: true, message: "证书编号不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询系统授权认证列表 */
    getList() {
      this.loading = true
      listCert(this.queryParams).then(response => {
        this.certList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        certId: null,
        userId: null,
        certType: null,
        certNo: null,
        certName: null,
        issueAuthority: null,
        issueDate: null,
        expiryDate: null,
        certStatus: null,
        auditUserId: null,
        auditTime: null,
        auditRemark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.certId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加系统授权认证"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const certId = row.certId || this.ids
      getCert(certId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改系统授权认证"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.certId != null) {
            updateCert(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCert(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const certIds = row.certId || this.ids
      this.$modal.confirm('是否确认删除系统授权认证编号为"' + certIds + '"的数据项？').then(function() {
        return delCert(certIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/cert/export', {
        ...this.queryParams
      }, `cert_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
