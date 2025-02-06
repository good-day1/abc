<template>
  <el-container style="height:100vh; border: 1px solid #eee">
    <el-aside :width="aside_width" style="background-color: rgb(238, 241, 246);margin-left: -1px;margin-top: -1px">
          <myAside :isCollapse="isCollapse"></myAside>
      </el-aside>

    <el-container style="height: 100%">
      <el-header style="text-align: right; font-size: 12px;border-bottom: #6defef 1px solid">
        <myHeader @doCollapse="doCollapse" :icon="icon"></myHeader>
      </el-header>

      <el-main style="padding: 0">
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

import myHeader from "./myHeader";

import myAside from "./myAside";

export default {
  name: "myTmp",
  components: {myAside,myHeader},
  data(){
    return{
        isCollapse:false,
      aside_width:"200px",
      icon:"el-icon-s-fold"
    }
  },
  methods:{
    doCollapse(){
      this.isCollapse = !this.isCollapse;
      if(!this.isCollapse){
        this.aside_width="200px"
        this.icon="el-icon-s-fold"
      }
      else{
        this.aside_width="64px"
        this.icon="el-icon-s-unfold"
      }
    }
  }

}



</script>

<style scoped>
.el-header {
  /*background-color: #B3C0D1;*/
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
#app{
  height: 100%;
}

</style>