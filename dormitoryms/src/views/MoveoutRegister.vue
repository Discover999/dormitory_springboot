<template>
  <!--  学生迁出登记-->
  <div style="margin-top: 60px;margin-left:80px;border: 0 solid red;">
    <el-form style="margin-left: -40px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
             class="demo-ruleForm">
      <el-form-item label="搜索范围:" prop="key">
        <el-select v-model="ruleForm.key" style="width: 160px;float: left" placeholder="请选择字段">
          <el-option label="学号" value="number"></el-option>
          <el-option label="姓名" value="name"></el-option>
        </el-select>
      </el-form-item>
      <div style="border: 0 solid red;width: 400px;height: 60px;position: relative;top: -64px;left: 270px">
        <el-form-item label="搜索内容:">
          <el-input v-model="ruleForm.value" placeholder="请输入关键字" style="width: 160px;"></el-input>
          <el-button type="primary" icon="el-icon-search" style="position: relative;left: 10px;"
                     @click="submitForm('ruleForm')">搜索
          </el-button>
        </el-form-item>
      </div>
    </el-form>

    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%;position: relative;top:-30px">
      <el-table-column
          fixed
          sortable
          align="center"
          prop="id"
          label="ID"
          width="120">
      </el-table-column>
      <el-table-column
          align="center"
          prop="dormitoryName"
          label="宿舍">
      </el-table-column>
      <el-table-column
          align="center"
          prop="number"
          label="学号">
      </el-table-column>
      <el-table-column
          align="center"
          prop="name"
          label="姓名">
      </el-table-column>
      <el-table-column
          align="center"
          prop="gender"
          label="性别">
      </el-table-column>
      <el-table-column
          align="center"
          prop="state"
          label="状态">
      </el-table-column>
      <el-table-column
          align="center"
          prop="createDate"
          label="入住时间">
      </el-table-column>
      <el-table-column label="操作" width="120" align="center">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="danger"
              @click="moveout(scope.row)">迁出
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination style="margin-top: 20px;float: right"
                   background
                   layout="prev, pager, next"
                   :page-size="pageSize"
                   :total="total"
                   :current-page.sync="currentPage"
                   @current-change="page">
    </el-pagination>
  </div>

</template>

<script>
export default {
  data() {
    return {
      tableData: null,
      currentPage: 1,
      pageSize: 5,
      total: null,
      key: '',
      value: '',
      ruleForm: {
        key: '',
        value: '',
        page: '',
        size: 5
      },
      rules: {
        key: [
          {required: true, message: '请选择字段', trigger: 'change'}
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      const _this = this
      //让翻页复原
      _this.currentPage = 1
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          _this.ruleForm.page = _this.currentPage
          axios.get('http://localhost:9090/moveout/search', {params: _this.ruleForm}).then(function (resp) {
            _this.tableData = resp.data.data.data
            _this.total = resp.data.data.total
          })
        }
      });

    },
    page(currentPage) {
      const _this = this
      if (_this.ruleForm.value === '') {
        axios.get('http://localhost:9090/moveout/list/' + currentPage + '/' + _this.pageSize).then(function (resp) {
          _this.tableData = resp.data.data.data
          _this.total = resp.data.data.total
        })
      } else {
        _this.ruleForm.page = _this.currentPage
        axios.get('http://localhost:9090/moveout/search', {params: _this.ruleForm}).then(function (resp) {
          _this.tableData = resp.data.data.data
          _this.total = resp.data.data.total
        })
      }

    },
    moveout(row) {
      const _this = this
      this.$prompt('请输入迁出原因', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[\s\S]*.*[^\s][\s\S]*$/,
        inputErrorMessage: '迁出原因不能为空'
      }).then(({value}) => {
        this.$confirm('确认迁出【' + row.name + '】吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.put('http://localhost:9090/moveout/moveout/' + row.id + "/" + value).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert('【' + row.name + '】已迁出', '', {
                confirmButtonText: '确定',
                callback: action => {
                  location.reload()
                }
              });
            }
          });
        });
      });
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:9090/moveout/list/1/' + _this.pageSize).then(function (resp) {
      _this.tableData = resp.data.data.data
      _this.total = resp.data.data.total
    })
  }
}
</script>