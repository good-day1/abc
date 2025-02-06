<template>
  <div >
    <div style="margin-bottom: 5px;margin-top: 5px;float: left;margin-left: 5px">
      <el-input v-model="name" placeholder="请输入分类名" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost"></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
      <el-button type="primary" @click="add" v-if="user.roleId!=2">新增</el-button>
    </div>
    <el-table :data="tableData" :header-cell-style="{background:'#f2f5fc',color:'#555555'}">
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>

      <el-table-column prop="name" label="分类名" width="150">
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="90">
      </el-table-column>

      <el-table-column prop="operate" label="操作" >
        <template slot-scope="scope">
          <el-button type="success" size="small" @click="mod(scope.row)" v-if="user.roleId!=2">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.id)"
              style="margin-left: 5px"
          >
            <el-button slot="reference" type="danger" size="small" v-if="user.roleId!=2">删除</el-button>
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
        <el-form-item label="分类名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-col>

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
  name: "GoodstypeManage",
  data() {

    return {
      user:JSON.parse(sessionStorage.getItem('CurUser')),
      tableData:[],
      pageSize:2,
      pageNum:1,
      total:0,
      name:"",
      centerDialogVisible:false,
      form:{
        id:"",
        name:"",
        remark:""
      },
      rules:{
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' },
        ],
      }
    }
  },
  methods:{
    resetForm(){
      this.form.name="",
          this.form.remark=""
    },
    del(id){
      console.log(id)
      this.$axios.get(this.$httpUrl+"/goodstype/deletebyid?id="+id
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
        this.form.name=row.name;
        this.form.remark=row.remark;

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
      this.$axios.post(this.$httpUrl+"/goodstype/listp1",{
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
      fetch("http://localhost:8090/goodstype/listp?pageNum="+this.pageNum+"&pageSize=" + this.pageSize + "&name=" + this.name)
          .then(res => res.json()).then(res => {

        this.tableData = res.data
        this.total = res.total
      })
    },
    add(){

      this.centerDialogVisible=true;
      this.$nextTick(()=>{
        this.resetForm()
        this.form.id=this.form.id++
      })

    },
    doMod(){
      this.$axios.post(this.$httpUrl+"/goodstype/update",this.form
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
      this.$axios.post(this.$httpUrl+"/goodstype/save",this.form
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
      this.name=""
    },

  },
  beforeMount() {
    // this.loadGet();
    this.loadPost();
  }
}
</script>
<style scoped>
</style>