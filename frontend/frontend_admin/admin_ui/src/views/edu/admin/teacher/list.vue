<template>
  <div class="app-container">
    <template>
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
      <router-link :to="'/edu/teacher/edit/'+scope.row.id">
        <el-button type="primary" size="mini" icon="el-icon-edit" style="margin-right : 1px">Modify</el-button>
      </router-link>
      <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">Delete</el-button>
    </template>
  </el-table-column>

  </el-table>
</template>

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
            total:0 //Number of records
        }
    },
    created(){ 
        this.selectTeacherByPageAndCondition()
    },
    methods:{
        selectTeacherByPageAndCondition(){
            teacher.selectTeacherByPageAndCondition(this.page, this.limit,this.teachervo)
                .then(response =>{
                    this.list = response.data.records
                    this.total = response.data.total
                })
                .catch(error =>{
                    console.log(error)
                })
        }
    }
}
</script>
