<template>
    <div class="app-container">

        <h2 style="text-align: center;">Publish A New Course</h2>


        <el-steps :active="active" process-status="wait" align-center style="margin-bottom: 40px;">
            <el-step title="Fill in Course Information"></el-step>
            <el-step title="Fill in Course Chapters"></el-step>
            <el-step title="Publish and Review"></el-step>
        </el-steps>

        <el-form label-width="200px" :model="courseInfo" :rules="rules" ref="courseInfo">
            <el-form-item label="Title" prop="title">
                <el-input v-model="courseInfo.title" placeholder=" Example: CMPT 410 - Machine Learning" />
            </el-form-item>

            <!-- Subject TODO -->
            <el-form-item label="Subject" prop="subjectId">
                <el-cascader v-model="subjectIds" placeholder="Select or Search" :options="subjects"
                    @change="handleSubjectClick" :props="subjectProps" filterable>
                </el-cascader>
            </el-form-item>

            <!-- Teacher -->
            <el-form-item label="Teacher" prop="teacherId">
                <el-select v-model="courseInfo.teacherId" filterable placeholder="Select a Teacher">
                    <el-option v-for="t in teachers" :key="t.id" :label="t.name" :value="t.id">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="Estimated Study Hours" prop="lessonNum">
                <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right"
                    placeholder="Please fill in the study hours of the course" />
            </el-form-item>

            <el-form-item label="Description" prop="description">
                <el-input v-model="courseInfo.description" placeholder="" />
            </el-form-item>

            <!-- Cover -->
            <el-form-item label="Cover" required>
                <el-upload drag :limit="1" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload"
                    :on-remove="handleAvatarRemove" list-type="picture" :action="BASE_API + '/ossservice/file/courses'" :file-list="cover_url"
                    class="avatar-uploader">
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">Drop file here, or <em>click to upload</em></div>
                    <div class="el-upload__tip" slot="tip">one jpg only with a size less than 2MB</div>
                </el-upload>
            </el-form-item>

            <el-form-item label="Price" required>
                <el-input-number :min="0" v-model="courseInfo.price" controls-position="right"
                    placeholder="0 is for free course" /> Dollars
            </el-form-item>

            <el-form-item>
                <el-button :disabled="saveBtnDisabled" type="primary" @click="next('courseInfo')">Save and
                    Next</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>


<script>
import courses from '@/api/edu/courses.js'
import teacher from '@/api/edu/teacher.js'
import subject from '@/api/edu/subjects.js'
import oss from '@/api/oss.js'

export default{
    data(){
        return{
            active: 1,
            saveBtnDisabled:false,
            courseInfo:{
                title: '',
                subjectId: '',
                subjectParentId: '',
                teacherId: '',
                price: 0,
                lessonNum: 0,
                cover: '',
                description: ''
            },
            rules:{
                title: [{required: true, message: "Please fill in the title of the course", trigger: 'blur' }],
                lessonNum: [{required: true, message: "Please fill in the estimated study hours of the course", trigger: 'blur' }],
                description: [{required: true, message: "Please fill in the description of the course", trigger: 'blur' }],
                teacherId: [{required: true, message: "Please select a teacher for the course", trigger: 'blur' }],
                subjectId: [{required: true, message: "Please select a subject for the course", trigger: 'blur' }]
            },
            teachers: [],
            subjects: [],
            subjectIds: [],
            subjectProps:{
                value: 'id',
                label: 'label'
            },
            BASE_API: process.env.VUE_APP_BASE_API,
            cover_url: [],
            current_id: ""
        }
    },
    created(){
        this.getAllTeachers()
        this.getAllSubjects()
        if (this.$route.params && this.$route.params.id) {
            this.current_id = this.$route.params.id
            this.getCourseInfo(this.current_id)
        }
    },
    methods:{
        getAllTeachers(){
            teacher.getAllTeachers().then(response =>{
                this.teachers = response.data.records
            })
        },
        getAllSubjects(){
            subject.getSubjects().then(response => {
                this.subjects = response.data.records
            }).catch()
        },
        handleSubjectClick(){
            this.courseInfo.subjectParentId = this.subjectIds[0]
            this.courseInfo.subjectId = this.subjectIds[1]
        },
        next(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    if (this.current_id !== "") {
                        this.updateCourseInfo()
                    } else {
                        this.addCourseInfo()
                    }
                }
                else{
                    this.$notify.error({
                            title: 'Failed',
                            message: "Some course's basic information are missing",
                            showClose: false
                        })
                    return false
                }
            })
        },
        addCourseInfo(){
            courses.addCourseInfo(this.courseInfo).then(response => {
                this.$notify.success({
                    title: 'Success',
                    message: "Added a course's basic information",
                    showClose: false
                })
                var id = response.data.records
                this.$router.push({ path: `/courses/chapter/${id}` })
            })
        },
        updateCourseInfo() {
            courses.updateCourseInfo(this.courseInfo).then(response => {
                this.$notify.success({
                    title: 'Success',
                    message: "Updated the course's basic information",
                    showClose: false
                })
                var id = response.data.records
                this.$router.push({ path: `/courses/chapter/${id}` })
            })
        },
        handleAvatarSuccess(response) {
            this.courseInfo.cover = response.data.records
        },
        handleAvatarRemove(){
            oss.deleteOssFile(this.courseInfo.cover).then(response => {
                this.courseInfo.cover = ""
                this.$notify.success({
                    title: 'Success',
                    message: "Deleted the course's cover",
                    showClose: false
                })
            })
        },
        //上传封面之前调用的方法
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg'
            const isLt2M = file.size / 1024 / 1024 < 2
            if (!isJPG) {
                this.$message.error('Picture must be in JPG format')
            }
            if (!isLt2M) {
                this.$message.error('Picture cannot be exceeded to 2MB!')
            }
            return isJPG && isLt2M
        },
        //获取课程基础信息
        getCourseInfo(id){
            courses.getCourseInfo(id).then(response => {
                this.courseInfo = response.data.records
                this.subjectIds = [this.courseInfo.subjectParentId, this.courseInfo.subjectId]
                if (this.courseInfo.cover !== "") {
                    // Extract the file name from the URL
                    let fileNameWithUUID = this.courseInfo.cover.split('/').pop();
                    let filename = fileNameWithUUID.slice(32);
                    this.cover_url = [{ name: filename, url: this.courseInfo.cover }]
                }
            })
        }
    }
}
</script>
