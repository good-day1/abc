<template>
  <div style="display: flex;line-height: 60px" >
    <div style="cursor: pointer">
      <i :class="icon" style="font-size: 30px;line-height: 60px" @click="collapse"></i>
    </div>

    <div style="flex: 1;text-align: center;font-size: 30px">
      <span>网上购物系统</span>
    </div>
    <el-dropdown>
      <span style="font-size: 20px">{{user.name}}</span>
      <i class="el-icon-user-solid" style="margin-left: 5px;font-size: 20px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="touser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登陆</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<script>
export default {
  name: "myHeader",
  data(){
    return {
      user:JSON.parse(sessionStorage.getItem("CurUser"))
    }
  },
  methods:{
    touser(){
      console.log("123")
      this.$router.push("/myHome")
    },
    logout(){
      this.$confirm('你确定退出吗','提示',{
        confirmButtonText:'确定',
        type:'warning',
        center:true

      }).then(()=>{
        this.$message({
          type:"success",
          message:"退出登陆成功"
        })
        this.$router.push("/")
        sessionStorage.clear()

      }).catch(()=>{
        this.$message({
          type:"info",
          message:"已取消退出登陆"
        })
      })
    },
    collapse(){
      this.$emit("doCollapse")
    }
  },
  created() {
    this.$router.push("/myHome")
  },
  props:{
    icon:String
  }
}
</script>

<style scoped>

</style>