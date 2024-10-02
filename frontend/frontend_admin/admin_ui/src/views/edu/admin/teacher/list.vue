<template>
  <div class="app-container">

  <el-form :inline="true" class="demo-form-inline">

    <el-form-item label="Name">
      <el-input v-model="teachervo.name" placeholder="Name of the Teacher"></el-input>
    </el-form-item>

    <el-form-item label="Title">
      <el-select v-model="teachervo.level" clearable placeholder="Title of the Teacher">
        <el-option label="Associate Lecturer" value="1"></el-option>
        <el-option label="Senior Lecturer" value="2"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="Creation Time">
    <el-col :span="11">
      <el-date-picker 
        type="datetime" 
        placeholder="Begin Date" 
        v-model="teachervo.begin" 
        value-format="yyyy-MM-dd HH:mm:ss" 
        default-time="00:00:00"
        style="width: 100%;">
      </el-date-picker>
    </el-col>
    <!-- <el-col class="line" :span="2">-</el-col> -->
    <el-col :span="11">
      <el-date-picker 
        type="datetime" 
        placeholder="End Date" 
        v-model="teachervo.end" 
        value-format="yyyy-MM-dd HH:mm:ss" 
        default-time="00:00:00"
        style="width: 100%;">
      </el-date-picker>
    </el-col>
  </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="selectTeacherByPageAndCondition()">Search</el-button>
      <el-button @click="resetCondition()">Reset</el-button>
    </el-form-item>

  </el-form>

  <el-table
    v-loading="loading"
    element-loading-text="Loading data..."
    :data="list"
    border
    fit>

    <el-table-column
      fixed
      label="Index"
      width="70"
      align="center">
      <template slot-scope="scope">
        {{ (page - 1) * limit + scope.$index + 1 }}
      </template>
    </el-table-column>

    <el-table-column
      fixed
      prop="id"
      label="Id"
      width="180">
    </el-table-column>

    <el-table-column
      fixed
      prop="name"
      label="Name"
      width="180">
    </el-table-column>

    <el-table-column label="Title" width="150">
        <template slot-scope="scope">
            {{ scope.row.level===1?"Associate Lecturer":"Senior Lecturer" }}
        </template>
    </el-table-column>

    <el-table-column
      prop="intro"
      label="Introduction">
      
    </el-table-column>

    <el-table-column prop="gmtCreate" label="Creation Time" width="160"/>

    <el-table-column label="Avatar" width="120">
        <template slot-scope="scope">
            <el-avatar shape="square" :size="100" :fit="fit" :src="scope.row.avatar"></el-avatar>
        </template>
    </el-table-column>

    <el-table-column label="Modification" width="200" align="center">
    <template slot-scope="scope">
      <router-link :to="'/teacher/edit/'+scope.row.id">
        <el-button type="primary" size="mini" icon="el-icon-edit" style="margin-right : 2px">Modify</el-button>
      </router-link>
      <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeTeacherById(scope.row.id)">Delete</el-button>
    </template>
  </el-table-column>

  </el-table>

  <el-pagination
    :current-page="page"
    :page-size="limit"
    :total="total"
    style="padding: 30px 0; text-align: center;"
    layout="total, prev, pager, next, jumper"
    @current-change="selectTeacherByPageAndCondition"
  />

  </div>
</template>
<script>

import teacher from '@/api/edu/teacher.js'

export default {
    data(){
        return {
            page:1,
            limit:10,
            teachervo:{},
            list:null, //The return data from backend service
            total:0, //Number of records
            loading: true,
            fit:'cover'
        }
    },
    created(){ 
        this.selectTeacherByPageAndCondition()
    },
    methods:{
        selectTeacherByPageAndCondition(page = 1){
            this.page = page
            this.loading = true
            teacher.selectTeacherByPageAndCondition(this.page, this.limit,this.teachervo)
                .then(response =>{
                    this.list = response.data.records
                    this.total = response.data.total
                    this.loading = false
                })
                .catch(error =>{
                    console.log(error)
                    this.loading = false
                })
        },
        resetCondition(){
          this.teachervo = {}
          this.selectTeacherByPageAndCondition()
        },
        removeTeacherById(id){
          this.$confirm("This will PERMANENTLY DELETE the selected teacher. Action cannot be recovered. Continue?", "Warning!",{
            confirmButtonText: "DELETE",
            cancelButtonText: "CANCEL",
            type: "error"
          }).then(()=>{
            teacher.removeTeacherById(id)
              .then(response=>{
                this.selectTeacherByPageAndCondition()
                this.$message({
                  type:"success",
                  message:"Delete completed"
                })
              })
              .catch(error=>{
                console.log(error)
                this.$message({
                  type:"error",
                  message:"Delete failed"
                })
              })
            
          }).catch(()=> {
            this.$message({
              type:"info",
              message:"Delete canceled"
            })
          })
          
        }
    }
}
</script>
