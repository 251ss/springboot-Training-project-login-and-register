<template>

  <div id="poster">

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-position="left" label-width="0px" class="register-container">
      <h3 class="register_title">
        系统注册
        <el-button @click="toLogin()">去登陆 </el-button>
      </h3>
      <el-form-item label="" prop="loginName">
        <el-input type="text" v-model="ruleForm.loginName" placeholder="请输入用户名" autocomplete="off" prefix-icon="el-icon-user-solid"></el-input>
      </el-form-item>
      <el-form-item label="" prop="name">
        <el-input type="text" v-model="ruleForm.name" prefix-icon="el-icon-user-solid" autocomplete="off" placeholder="请输入网名"></el-input>
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input type="password" v-model="ruleForm.password" prefix-icon="el-icon-lock" autocomplete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item label="" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" prefix-icon="el-icon-lock" placeholder="请确认密码" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="background:#505458;border:none" @click="submitForm('ruleForm')">注册</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>
 
<script>
export default {
  name: "Register",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        loginName: "",
        name: "",
        password: "",
        checkPass: "",
      },
      rules: {
        loginName: [
          { required: true, massage: " 请输入你的名称", trigger: "blur" },
          { min: 2, max: 9, massage: " 长度2~9个字符", trigger: "blur" },
        ],
        password: [{ validator: validatePass, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm() {
      this.axios
        .post("http://localhost:3231/user/register", this.ruleForm)
        .then((resp) => {
          let data = resp.data;
          if (data.success) {
            this.ruleForm = {};
            this.$message({
              message: "注册成功！！",
              type: "success",
            });
          }
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    toLogin() {
      this.$router.push({ path: "/" });
    },
  },
};
</script>

<style>
#poster {
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
  margin: 0px;
  padding: 0px;
}

.register-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.register-title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>


  
  
  
