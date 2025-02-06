<template>
  <div >
    <div style="margin-bottom: 5px;margin-top: 5px;float: left;margin-left: 5px">
      <el-input v-model="name" placeholder="请输入名字" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost"></el-input>
      <el-select v-model="sex" placeholder="请选择性别" style="margin-left: 5px;width: 150px">
        <el-option
            v-for="item in sexs"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
      <el-button type="primary" @click="add">新增</el-button>

    </div>

    <el-table :data="tableData" :header-cell-style="{background:'#f2f5fc',color:'#555555'}">
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="no" label="账号" width="170">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="150">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="110">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="110">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.sex === 0 ? 'primary' : 'success'"
              disable-transitions>{{scope.row.sex === 0 ? '男' : '女'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="roleId" label="角色" width="150">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
              disable-transitions>{{scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' : '其他')}}</el-tag>
        </template>

      </el-table-column>
      <el-table-column prop="phone" label="电话" width="180">
      </el-table-column>
      <el-table-column prop="operate" label="操作" >
        <template slot-scope="scope">
          <el-button type="success" size="small" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.id)"
              style="margin-left: 5px"
          >
            <el-button slot="reference" type="danger" size="small" >删除</el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="no">
          <el-input v-model="form.no"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio label=0>男</el-radio>
            <el-radio label=1>女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="save">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>


export default {
  name: "UserManage",
  data() {
    let checkDuplicate=(rules,value,callback)=>{
      if(this.form.id){

        return callback();
      }
      fetch("http://localhost:8090/user/findByNo?no="+this.form.no)
          .then(res => res.json()).then(res => {
        if(res.code!=200){
          callback();
        }else{
          callback("该用户已经存在")
        }


      })

    }
    return {
      tableData:[],
      pageSize:2,
      pageNum:1,
      total:0,
      name:"",
      sex:"",
      sexs:[
        {
          value: 0,
          label: '男'
        }, {
          value: 1,
          label: '女'
        }
      ],
      centerDialogVisible:false,
      form:{
        id:"",
        name:"",
        no:"",
        password:"",
        age:"",
        phone:"",
        sex:0,
        roleId:2,
        isvalid:1
      },
      rules:{
        no: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' },
          {validator:checkDuplicate,trigger: "blur"}
        ],
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { min: 7, max: 11, message: '长度在 7 到 11 个字符', trigger: 'blur' }
        ],
        age:[
          { required: true, message: '年龄不能为空'},

        ],

      }


    }
  },
  methods:{
    del(id){
      console.log(id)
      this.$axios.get(this.$httpUrl+"/user/deletebyid?id="+id
      ).then(res=>res.data).then(res=>{
        if(res.code==200){
          this.centerDialogVisible=false
          this.$message({
            message: '操作成功',
            type: 'success'
          })
        }
        else{
          this.$message.error('错了哦，baobao');
        }
      })


    },
    mod(row){
      this.centerDialogVisible=true;
      this.$nextTick(()=>{
        this.form.id=row.id;
        this.form.no=row.no;
        this.form.age=row.age;
        this.form.name=row.name;
        this.form.sex=row.sex;
        this.form.password="";
        this.form.phone=row.phone;
        this.form.roleId=row.roleId+'';
        this.centerDialogVisible=true
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum=1;
      this.pageSize=val;
      this.loadPost();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum=val;
      this.loadPost();
    },

    loadPost1(){
      this.$axios.post(this.$httpUrl+"/user/listp1",{
        PageSize:this.pageSize,
        PageNum:this.pageNum,
        param:{
          name:this.name,
          sex:this.sex,
          roleId:'1'
        }
      },).then(res=>res.data).then(res=>{
        console.log(res)
        console.log(this.pageSize)
        console.log(this.name)
        if(res.code==200){
          this.tableData=res.data
          this.total=res.total
        }
        else{
          alert("获取数据失败")
        }
      })
    },
    loadPost() {
      fetch("http://localhost:8090/user/listp?pageNum="+this.pageNum+"&pageSize=" + this.pageSize + "&name=" + this.name +
          "&roleId=2")
          .then(res => res.json()).then(res => {
        console.log(res)
        this.tableData = res.data
        this.total = res.total
      })
    },
    add(){

      this.centerDialogVisible=true;
      this.$nextTick(()=>{
        this.resetForm()
      })
    },
    doMod(){
      this.$axios.post(this.$httpUrl+"/user/update",this.form
      ).then(res=>res.data).then(res=>{
        if(res.code==200){
          this.centerDialogVisible=false
          this.$message({
            message: '操作成功',
            type: 'success'
          })
        }
        else{
          this.$message.error('错了哦，baobao');
        }
      })

    },
    doSave(){
      this.$axios.post(this.$httpUrl+"/user/save",this.form
      ).then(res=>res.data).then(res=>{
        if(res.code==200){
          this.centerDialogVisible=false
          this.$message({
            message: '操作成功',
            type: 'success'
          })
        }
        else{
          this.$message.error('错了哦，baobao');
        }
      })

    },
    save(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          if(this.form.id){
            this.doMod();
          }
          else{
            this.doSave();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    resetParam(){
      this.name="", this.sex="",
          this.no="",
          this.password="",
          this.age="",
          this.phone=""
    },
    resetForm(){
      this.form.name="", this.form.sex="",
          this.form.no="",
          this.form.password="",
          this.form.age="",
          this.form.phone=""
    }

  },
  beforeMount() {
    // this.loadGet();
    this.loadPost();
  }
}
</script>

<style scoped>

</style>