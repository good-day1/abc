<template>
<div>

  <el-descriptions class="margin-top" title="个人中心" :column="2" :size="size" border>

    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        账号
      </template>
      {{user.no}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        手机号
      </template>
      {{user.phone}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        性别
      </template>
      <el-tag
      :type="user.sex===1?'primary':'danger'"
      disabled-transitons><i :class="user.sex=='1'?'el-icon-male':'el-icon-female'"></i>
        {{user.sex=='1'?'男':'女'}}
      </el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        角色
      </template>
      <el-tag
          size="small"
          type="success"
          disabled-transitions>{{user.roleId=='0'?"超级管理员":(user.roleId=='1'?"管理员":"用户")}}
      </el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        余额
      </template>
      {{user.money}}
    </el-descriptions-item>
  </el-descriptions>
</div>
</template>
<script>
export default {
  name: "myHome",
  data () {
    return {
      size: '',
      user:{}
    };
  },
  methods:{
    init(){
      this.user=JSON.parse(sessionStorage.getItem("CurUser"))
    },

  },
  created() {
    this.init()
    console.log(this.user)
  }
}
</script>
<style scoped>

</style>