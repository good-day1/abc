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

    </div>
    <el-table :data="tableData" :header-cell-style="{background:'#f2f5fc',color:'#555555'}">
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="goodsname" label="物品名" width="150">
      </el-table-column>
      <el-table-column prop="storagename" label="仓库名" width="150" >
      </el-table-column>
      <el-table-column prop="goodstypename" label="分类" width="150" >
      </el-table-column>
      <el-table-column prop="count" label="数量" width="150">
      </el-table-column>
      <el-table-column prop="username" label="用户" width="150">
      </el-table-column>
      <el-table-column prop="createtime" label="操作时间" width="150" >
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="90">
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




  </div>
</template>
<script>


export default {
  name: "BuyManage",
  data() {

    return {
      user:JSON.parse(sessionStorage.getItem('CurUser')),
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
      form:{
        id:"",
        name:"",
        remark:"",
        storage:"",
        goodstype:"",
        count:""
      },

    }
  },
  methods:{

    resetForm(){
      this.form.name="",
          this.form.remark=""
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
      fetch("http://localhost:8090/buy/list?pageNum="+this.pageNum+"&pageSize=" + this.pageSize + "&name=" + this.name+
          "&goodstype="+this.goodstype.toString()+"&storage1="+this.storage.toString()+"&userId1="+this.user.id.toString()+
          "&roleId="+this.user.roleId.toString()
         )
          .then(res => res.json()).then(res => {
        this.tableData = res.data
        console.log(123)
        console.log(this.tableData)
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