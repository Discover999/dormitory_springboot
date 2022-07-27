<template>
  <!--  修改楼栋-->
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0 solid red;">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="ID">
        <el-input v-model="ruleForm.id" readonly></el-input>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="介绍" prop="introduction">
        <el-input v-model="ruleForm.introduction"></el-input>
      </el-form-item>
      <el-form-item label="管理员" prop="adminId">
        <el-select v-model="ruleForm.adminId" placeholder="请选择管理员">
          <el-option v-for="item in dormitoryAdminList" :label="item.name" :value="item.id" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
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
        name: '',
        introduction: '',
        adminId: ''
      },
      dormitoryAdminList: '',
      rules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'}
        ],
        introduction: [
          {required: true, message: '请输入介绍', trigger: 'blur'}
        ],
        adminId: [
          {required: true, message: '请选择管理员', trigger: 'change'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:9090/building/update', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.name + '修改成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/buildingManager')
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
    axios.get('http://localhost:9090/dormitoryAdmin/list').then(function (resp) {
      _this.dormitoryAdminList = resp.data.data
    })
    axios.get('http://localhost:9090/building/findById/' + _this.$route.query.id).then(function (resp) {
      _this.ruleForm = resp.data.data
    })
  }
}
</script>
