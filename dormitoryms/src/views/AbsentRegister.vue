<template>
  <!--  缺寝登记-->
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0 solid red;">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="楼栋" prop="buildingId">
        <el-select v-model="ruleForm.buildingId" placeholder="请选择楼栋" @change="buildingChange">
          <el-option v-for="item in buildingList" :label="item.name" :value="item.id" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="宿舍" prop="dormitoryId">
        <el-select v-model="ruleForm.dormitoryId" placeholder="请选择宿舍" @change="dormitoryChange">
          <el-option v-for="item in dormitoryList" :label="item.name" :value="item.id" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学生" prop="studentId">
        <el-select v-model="ruleForm.studentId" placeholder="请选择学生">
          <el-option v-for="item in studentList" :label="item.name" :value="item.id" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="原因" prop="reason">
        <el-input type="textarea" v-model="ruleForm.reason"></el-input>
      </el-form-item>
      <el-form-item label="日期" prop="createDate">
        <el-date-picker
            style="width: 200px"
            v-model="ruleForm.createDate"
            type="date"
            placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登记</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
export default {
  data() {
    return {
      i:  0,
      category: null,
      ruleForm: {
        buildingId: '',
        dormitoryId: '',
        studentId: '',
        reason: '',
        createDate: '',
        dormitoryAdminId: ''
      },
      buildingList: '',
      dormitoryList: '',
      studentList: '',
      rules: {
        buildingId: [
          {required: true, message: '请选择楼栋', trigger: 'change'}
        ],
        dormitoryId: [
          {required: true, message: '请选择宿舍', trigger: 'change'}
        ],
        studentId: [
          {required: true, message: '请选择学生', trigger: 'change'}
        ],
        reason: [
          {required: true, message: '请输入原因', trigger: 'blur'}
        ],
        createDate: [
          {required: true, message: '请输入日期', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    buildingChange() {
      const _this = this
      axios.get('http://localhost:9090/absent/findDormitoryByBuildingId/' + _this.ruleForm.buildingId).then(function (resp) {
        _this.dormitoryList = resp.data.data
        _this.ruleForm.dormitoryId = ''
        _this.ruleForm.studentId = ''
      })
    },
    dormitoryChange() {
      const _this = this
      axios.get('http://localhost:9090/absent/findStudentByDormitoryId/' + _this.ruleForm.dormitoryId).then(function (resp) {
        _this.studentList = resp.data.data
        _this.ruleForm.studentId = ''
      })
    },
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let admin = _this.$store.state.admin
          _this.ruleForm.dormitoryAdminId = admin.id
          console.log(_this.ruleForm)
          axios.post('http://localhost:9090/absent/save', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert('缺寝登记成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/absentRecord2')
                }
              });
            }
          })
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:9090/absent/buildingList').then(function (resp) {
      _this.buildingList = resp.data.data
    })
  }
}
</script>
