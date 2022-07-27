<template>
  <!--  修改宿舍-->
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0 solid red;">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="ID">
        <el-input v-model="ruleForm.id"></el-input>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="telephone">
        <el-input v-model="ruleForm.telephone"></el-input>
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
      category: null,
      ruleForm: {
        id: '',
        buildingId: '',
        name: '',
        type: '',
        telephone: ''
      },
      rules: {
        buildingId: [
          {required: true, message: '请选择楼栋', trigger: 'change'}
        ],
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'}
        ],
        telephone: [
          {required: true, message: '请输入联系电话', trigger: 'blur'}
        ],
        type: [
          {required: true, message: '请选择房间类型', trigger: 'change'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:9090/dormitory/update', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.name + '修改成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/dormitoryManager')
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
    axios.get('http://localhost:9090/dormitory/findById/' + _this.$route.query.id).then(function (resp) {
      _this.ruleForm = resp.data.data
    })
  }
}
</script>
