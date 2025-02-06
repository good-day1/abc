<template>
  <div class="div1">
    <div class="loginForm1">
      <h2>登录页面</h2>
      <el-form
      :model="loginForm"
      :rules="rules"
      ref="loginForm"
      >
        <el-form-item
            label="账号"
            prop="no"
        >
          <el-input type="text" v-model="loginForm.no" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item
            label="密码"
            prop="password"
        >
          <el-input  type="password" v-model="loginForm.password" autocomplete="off" @keyup.enter.native="confirm"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="confirm" :disabled="confirm_disabled">提交</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>

</template>

<script>
export default {
  name: "aLogin",
  data(){
    return{
      confirm_disabled:false,
      loginForm:{

        no:"",
        password:"",

      },
      rules:{
        no:[{required:true,message:"请输入账号",trigger:'blur'}],
        password: [{required:true,message:"请输入密码",trigger:'blur'}],
      }
    }
  },
  methods:{
    confirm(){
      this.confirm_disabled=true;
      this.$refs.loginForm.validate((valid) =>{
        if(valid){
          this.$axios.post(this.$httpUrl+"/user/login?no="+this.loginForm.no+"&password="+this.loginForm.password,{},{ withCredentials: true })
              .then(res => res.data).then(res => {
              console.log(res)
            if(res.code==200){

                  console.log(this.loginForm.no)
                  sessionStorage.setItem("CurUser",JSON.stringify(res.data.user))
                  console.log(res.data.user)
                  console.log(res.data.menu)
                  this.$store.commit("setMenu",res.data.menu)
                  this.$router.replace('/myTmp')
                }
                else{
                  this.confirm_disabled=false;
                  alert("账号或密码错误")
                  return false
                }
          })
        }else{
          this.confirm_disabled=false;
          console.log("shibai")
          return false
        }
      })
    }
  }
}

</script>

<style scoped>

.div1{
  width: 100%;
  height: 100%;
  position: absolute;
}
.loginForm1 {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 250px;
  padding: 20px;
  height: 300px;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 5px;
  border: 2px solid black;
}
.loginForm1 input {
  display: block;
  margin-bottom: 10px;
}
.loginForm1 input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.loginForm1 button {
  width: 100%;
  padding: 8px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>