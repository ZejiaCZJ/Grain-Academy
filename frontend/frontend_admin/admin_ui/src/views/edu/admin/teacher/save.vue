<template>
    <div class="app-container">
      <el-form :label-position="labelPosition" :model="teacher" :rules="formRules" ref="teacher" label-width="120px">

        <el-form-item label="Avatar">
          <!--Image thumbnail-->
          <pan-thumb :image="teacher.avatar"/>

          <!--Image Upload button-->
          <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">Upload</el-button>

          <image-cropper
                v-show="imagecropperShow"
                :width="300"
                :height="300"
                :key="imagecropperKey"
                :url="'/ossservice/file'"
                field="file"
                @close="close"
                @crop-upload-success="cropSuccess"/>

        </el-form-item>



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
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default{
  components:{ImageCropper, PanThumb},
  data(){
    return{
      labelPosition: "left",
      teacher:{
        avatar: "https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg"
      },
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
      imagecropperShow:false,
      imagecropperKey:0,
      BASE_API:process.env.BASE_API,

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
    saveTeacher(){ //Save a new teacher
      teachers.addTeacher(this.teacher).then(response => {
              this.$message({type:"success",message:"Added a teacher"})
              this.$router.push({path:'/teacher/list'})
            }).catch(error => {
              console.log(error)
              this.$message({type:"error",message:"Failed to add a teacher"})
            })
    },
    updateTeacher(){ //Update a current teacher
      teachers.updateTeacher(this.teacher).then(response => {
              this.$message({type:"success",message:"Updated a teacher"})
              this.$router.push({path:'/teacher/list'})
            }).catch(error=>{
              console.log(error)
              this.$message({type:"error",message:"Failed to update a teacher"})
            })
    },
    resetForm(formName) { //Reset the form 
        this.$refs[formName].resetFields()
    },
    getInfo(id){ //Get the information of a teacher by id
      teachers.searchTeacherById(id).then(response => {
        this.teacher = response.data.records
      }).catch(error => {
        this.$message({type:"error",message:"Failed to retrieve the teacher's information"})
        this.$router.push({path:'/teacher/list'})
      })
    },
    close(){ //Close the pop-up window
      this.imagecropperShow = false
      this.imagecropperKey = this.imagecropperKey + 1
    },
    cropSuccess(data){
      this.imagecropperShow = false
      this.teacher.avatar = data.records
      this.imagecropperKey = this.imagecropperKey + 1
    }
  }
}


</script>