<template>
  <div >
    <div style="margin-bottom: 5px;margin-top: 5px;float: left;margin-left: 5px">
      <el-input v-model="name" placeholder="请输入物品名" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost"></el-input>
      <el-select v-model="storage" placeholder="请选择仓库">
        <el-option
            v-for="item in storageData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-select v-model="goodstype" placeholder="请选择分类">
        <el-option
            v-for="item in goodstypeData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
      <el-button type="primary" @click="add" v-if="user.roleId==0">新增</el-button>
      <el-button type="primary" @click="outGoods" v-if="user.roleId!=2">出库</el-button>
      <el-button type="primary" @click="inGoods" v-if="user.roleId!=2">入库</el-button>
      <el-button type="primary" @click="buy" v-if="user.roleId==2">购买</el-button>
    </div>
    <el-table :data="tableData" :header-cell-style="{background:'#f2f5fc',color:'#555555'}"  highlight-current-row
              @current-change="selectCurrentChange">
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="name" label="物品名" width="150">
      </el-table-column>
      <el-table-column prop="storage" label="仓库名" width="150" :formatter="formatStorage">
      </el-table-column>
      <el-table-column prop="goodstype" label="分类" width="150" :formatter="formatGoodstype">
      </el-table-column>
      <el-table-column prop="price" label="单价" width="150" >
      </el-table-column>
      <el-table-column prop="count" label="数量" width="150">
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
            <el-button slot="reference" type="danger" size="small" v-if="user.roleId!=2" >删除</el-button>
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
        <el-form-item label="物品名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="仓库名" prop="storage" >
          <el-input v-model="form.storage"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="goodstype">
          <el-input v-model="form.goodstype"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-input v-model="form.count"></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="form.price"></el-input>
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




    <el-dialog
        title="提示"
        :visible.sync="buyDialogVisible"
        width="30%"
        center>
      <el-form ref="form2" :model="form2" label-width="80px">

        <el-form-item label="数量" prop="count">
          <el-input v-model="form2.count"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input type="textarea" v-model="form2.remark"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="buyDialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="doSave1">确 定</el-button>
  </span>
    </el-dialog>














    <el-dialog
        title="出入库"
        :visible.sync="inDialogVisible"
        width="30%"
        center>

      <el-dialog
          width="70%"
          title="用户选择"
          :visible.sync="innerVisible"
          append-to-body>

        <SelectUser @doSelectUser="doSelectUser"></SelectUser>

        <span slot="footer" class="dialog-footer">
    <el-button @click="innerVisible = false">取 消</el-button>
    <el-button type="primary"  @click="confirmUser">确 定</el-button>
  </span>
      </el-dialog>

      <el-form ref="form1" :rules="rules1" :model="form1" label-width="80px">
        <el-form-item label="物品名" prop="name">
          <el-input v-model="form1.goodsname" readonly></el-input>
        </el-form-item>
        <el-form-item label="申请人" prop="username">
          <el-input v-model="form1.username" readonly @click.native="selectUser"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-input v-model="form1.count"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input type="textarea" v-model="form1.remark"></el-input>
          </el-col>

        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="inDialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="inGoodsSave">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>


import SelectUser from "@/components/user/SelectUser";
export default {

  name: "GoodsManage",
  components: {SelectUser},
  data() {
    let checkCount=(rule,value,callback)=>{
      if(value>9999){
        callback(new Error('数量过大'))
      }
      else{
        callback();
      }
    };
    return {
      buyDialogVisible:false,
      storageData:[],
      goodstypeData:[],
      tableData:[],
      pageSize:2,
      pageNum:1,
      total:0,
      name:"",
      goodstype: "",
      storage: "",
      centerDialogVisible:false,
      inDialogVisible:false,
      currentRow:"",
      innerVisible:false,
      user:JSON.parse(sessionStorage.getItem('CurUser')),
      tempUser:"",
      form:{
        id:"",
        name:"",
        remark:"",
        storage:"",
        goodstype:"",
        count:"",
        price:""
      },
      form1:{
        id:"",
        goods:"",
        goodsname:"",
        username:"",
        count:"",
        remark:"",
        userid:"",
        adminId:"",
        action:"1"

      },
      form2:{
        goods:"",
        count:"",
        remark:"",
        userid:""

      },
      rules1:{

      },
      rules:{
        name: [
          { required: true, message: '请输入物品名称', trigger: 'blur' },
        ],
        count:[{require:true,message:'请输入数量',trigger:'blur'},
          {pattern:/^([1-9][0-9]*){1,4}$/,message: '数量必须为正',trigger:'blur'},
          {validator:checkCount,trigger:'blur'}]
      }
    }
  },
  methods:{

    doSave1(){
      this.form2.goods=this.currentRow.id
      this.form2.userid=this.user.id
      this.$axios.post(this.$httpUrl+"/buy/save",this.form2
      ).then(res=>res.data).then(res=>{
        if(res.code==200){
          this.buyDialogVisible=false
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
    buy(){
      if(!this.currentRow.id){
        alert("请选择物品")
        return;
      }
      this.buyDialogVisible=true
      this.$nextTick(()=>{
        this.form2.count=""
        this.form2.remark=""
      })


    },
    doSelectUser(val){
      this.tempUser=val;
    },


    confirmUser(){
      this.form1.userid=this.tempUser.id;
      this.form1.username=this.tempUser.name;
      this.innerVisible = false;
    },

    selectUser(){
      this.innerVisible=true;
    },

    selectCurrentChange(val) {
      this.currentRow = val;
      console.log(val)
    },

    formatGoodstype(row){
      let temp = this.goodstypeData.find(item=>{


        if(item.id==row.goodsType){

          return item&&item.name

        }
      })
      return temp&&temp.name

    },
    formatStorage(row){
      let temp = this.storageData.find(item=>{
        if(item.id==row.storage){
          return item&&item.name
        }
      })
      return temp&&temp.name
    },
    resetForm(){
          this.form.name="",
          this.form.remark=""
      
    },
    resetForm1(){
          this.form1.name="",
          this.form1.remark=""
          this.form1.count="",
              this.form1.id=""
    },
    del(id){
      console.log(id)
      this.$axios.get(this.$httpUrl+"/goods/deletebyid?id="+id
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
        this.form.storage=row.storage;
        this.form.goodstype=row.goodstype;
        this.form.count=row.count;

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

    loadPost() {
      fetch("http://localhost:8090/goods/listp?pageNum="+this.pageNum+"&pageSize=" + this.pageSize + "&name=" + this.name+
          "&goodstype="+this.goodstype.toString()+"&storage1="+this.storage.toString())
          .then(res => res.json()).then(res => {
        this.tableData = res.data
        console.log(res.data)
        console.log(res.count)
        this.total = res.total
      })
    },


    loadStorage(){
      fetch("http://localhost:8090/storage/list").then(res => res.json())
          .then(res => {
        this.storageData = res.data

      })
    },
    loadGoodstype(){
      fetch("http://localhost:8090/goodstype/list").then(res => res.json())
          .then(res => {
            this.goodstypeData = res.data
          })
    },
    add(){
      this.centerDialogVisible=true;
      this.$nextTick(()=>{
        this.resetForm()
        this.form.id=this.form.id++
      })

    },
    inGoods(){
      if(!this.currentRow.id){
        alert("请选择物品")
        return;
      }
      this.inDialogVisible=true;
      this.$nextTick(()=>{
        this.resetForm1()

      })
      this.form1.goodsname=this.currentRow.name;
      this.form1.goods=this.currentRow.id
      this.form1.adminId=this.user.id
      this.form1.id=""
      this.form1.action=1
    },
    outGoods(){
      if(!this.currentRow.id){
        alert("请选择物品")
        return;
      }
      this.inDialogVisible=true;
      this.$nextTick(()=>{
        this.resetForm1()

      })
      this.form1.goodsname=this.currentRow.name;
      this.form1.goods=this.currentRow.id
      this.form1.adminId=this.user.id
      this.form1.id=""
      this.form1.action=2

    },

    doMod(){
      this.$axios.post(this.$httpUrl+"/goods/update",this.form
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
      console.log(this.form)
      this.$axios.post(this.$httpUrl+"/goods/save",this.form
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

    inGoodsSave(){
      console.log(this.form1)
      console.log(this.user)
      this.$axios.post(this.$httpUrl+"/record/save",this.form1
      ).then(res=>res.data).then(res=>{

        if(res.code==200){
          this.inDialogVisible=false
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.loadPost()
          this.resetForm1()
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
            console.log(123)
            this.doSave();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    resetParam(){
      this.name="",
          this.storage="",
          this.goodstype=""
    },

  },
  beforeMount() {
    // this.loadGet();
    this.loadStorage();
    this.loadGoodstype();
    this.loadPost();

  }
}
</script>
<style scoped>
</style>