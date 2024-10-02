<template>
    <div class="app-container">
      <el-form :label-position="labelPosition" :model="teacher" :rules="formRules" ref="teacher" label-width="120px">

        <!-- <el-form-item label="Avatar">
          <el-input v-model="teacher.name"></el-input>
        </el-form-item> -->


        <el-form-item label="Name" prop="name">
          <el-input v-model="teacher.name"></el-input>
        </el-form-item>

        <el-form-item label = "Description">
          <el-input type="textarea" v-model="teacher.intro" :rows="10"></el-input>
        </el-form-item>

        <el-form-item label = "Qualification" prop="career">
          <el-input v-model="teacher.career"></el-input>
        </el-form-item>

        <el-form-item label = "Level" prop="level">
          <el-select v-model = "teacher.level" placeholder="Teacher level">
            <el-option label="Associate lecturer" :value="1"></el-option>
            <el-option label="Senior lecturer" :value="2"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="saveOrUpdate('teacher')">Add</el-button>
          <el-button @click="resetForm('teacher')">Reset</el-button>
        </el-form-item>

      </el-form>
    </div>
</template>


<script>
import teachers from "@/api/edu/teacher.js"

export default{
  data(){
    return{
      labelPosition: "left",
      teacher:{},
      formRules:{
        name: [
          {required: true, message:"Please input the teacher's name", trigger:'blur'}
        ],
        career: [
          {required: true, message:"Please input the teacher's academic qualification", trigger:'blur'}
        ],
        level:[
          {required: true,trigger:'blur'}
        ]
      },
    }
  },
  created(){
    if(this.$route.params && this.$route.params.id){
      const id = this.$route.params.id
      this.getInfo(id)
    }
  },
  methods:{
    saveOrUpdate(teacher){
      this.$refs[teacher].validate((valid) =>{
        if (valid){
          if(!('avatar' in this.teacher))
            this.teacher.avatar = "https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg"

          if(this.$route.params && this.$route.params.id){
            this.updateTeacher()
          }
          else{
            this.saveTeacher()
          }
        } else{
          console.log('error submit!!')
          return false
        }
      })
    },
    saveTeacher(){
      teachers.addTeacher(this.teacher).then(response => {
              this.$message({type:"success",message:"Added a teacher"})
              this.$router.push({path:'/teacher/list'})
            }).catch(error => {
              console.log(error)
              this.$message({type:"error",message:"Failed to add a teacher"})
            })
    },
    updateTeacher(){
      teachers.updateTeacher(this.teacher).then(response => {
              this.$message({type:"success",message:"Updated a teacher"})
              this.$router.push({path:'/teacher/list'})
            }).catch(error=>{
              console.log(error)
              this.$message({type:"error",message:"Failed to update a teacher"})
            })
    },
    resetForm(formName) {
        this.$refs[formName].resetFields()
    },
    getInfo(id){
      teachers.searchTeacherById(id).then(response => {
        this.teacher = response.data.records
      }).catch(error => {
        this.$message({type:"error",message:"Failed to retrieve the teacher's information"})
        this.$router.push({path:'/teacher/list'})
      })
    }
  }
}


</script>