<template>
    <div class="app-container">
        <h2 style="text-align: center;">Publish a New Course</h2>

        <el-steps :active="active" process-status="wait" align-center>
            <el-step title="Fill in Course Information"></el-step>
            <el-step title="Fill in Course Chapters"></el-step>
            <el-step title="Publish and Review"></el-step>
        </el-steps>

        <el-button type="primary" style="margin-top: 12px; margin-bottom: 12px;" @click="newChapterDialogVisible = true"
            icon="el-icon-plus">New Chapter</el-button>

        <!--New Chapter Form-->
        <el-dialog title="New Chapter" :visible.sync="newChapterDialogVisible">

            <el-form :model="newChapter">
                <el-form-item label="Title" :label-width="formLabelWidth">
                    <el-input v-model="newChapter.title" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="Position" :label-width="formLabelWidth">
                    <el-input-number :min="0" v-model="newChapter.sort" controls-position="right"
                        placeholder="The index of the chapter" />
                    <el-tooltip class="item" effect="dark"
                        content="Enter the index of the chapter. Lower numbers appear first." placement="top">
                        <el-button icon="el-icon-question" size="mini"
                            style="margin-left: 5px; border: none; color: grey" disabled></el-button>
                    </el-tooltip>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelNewChapter()">Cancel</el-button>
                <el-button type="primary" @click="addChapter()">Add</el-button>
            </div>
        </el-dialog>



        <!--Display all Chapters-->
        <div class="chapterViewPort">
            <ul class="chapterList">
                <li v-for="chapter in chapterList" :key="chapter.id">
                    <p>
                        {{ chapter.title }}

                        <span class="acts">
                            <el-button-group>
                                <el-button type="primary" icon="el-icon-edit"
                                    @click="modifyChapterDialog(chapter.id)"></el-button>
                                <el-button type="primary" icon="el-icon-delete"
                                    @click="removeChapter(chapter.id)"></el-button>
                            </el-button-group>
                        </span>

                    </p>

                    <ul class="chapterList videoList">
                        <li v-for="video in chapter.children" :key="video.id">
                            <p>
                                {{ video.title }}
                            </p>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

        <!--New Chapter Form-->
        <el-dialog title="Modify Chapter" :visible.sync="modifyChapterDialogVisible">

            <el-form :model="currentChapter">
                <el-form-item label="Title" :label-width="formLabelWidth">
                    <el-input v-model="currentChapter.title" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="Position" :label-width="formLabelWidth">
                    <el-input-number :min="0" v-model="currentChapter.sort" controls-position="right"
                        placeholder="The index of the chapter" />
                    <el-tooltip class="item" effect="dark"
                        content="Enter the index of the chapter. Lower numbers appear first." placement="top">
                        <el-button icon="el-icon-question" size="mini"
                            style="margin-left: 5px; border: none; color: grey" disabled></el-button>
                    </el-tooltip>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelModifyChapter()">Cancel</el-button>
                <el-button type="primary" @click="modifyChapter()">Add</el-button>
            </div>
        </el-dialog>




        <el-button :disabled="saveBtnDisabled" style="margin-top: 12px;" @click="previous">Previous</el-button>
        <el-button :disabled="saveBtnDisabled" style="margin-top: 12px;" @click="next">Next</el-button>

    </div>
</template>


<script>
import courses from '@/api/edu/courses.js'
import teacher from '@/api/edu/teacher.js'
import subject from '@/api/edu/subjects.js'
import chapters from '@/api/edu/chapters.js'
import oss from '@/api/oss.js'

export default{
    data(){
        return{
            active: 2,
            saveBtnDisabled:false,
            courseId: -1,
            chapterList:[],
            formLabelWidth: '120px',
            newChapter: {
                courseId:this.courseId,
                title:"",
                sort:0
            },
            currentChapter:{
                courseId:this.courseId,
                chapterId:"",
                title:"",
                sort:0,
            },
            newChapterDialogVisible:false,
            modifyChapterDialogVisible:false,
        }
    },
    created(){
        if(this.$route.params && this.$route.params.id){
            this.courseId = this.$route.params.id
            this.newChapter.courseId = this.courseId
            this.currentChapter.courseId = this.courseId
        }
        this.getChapters()
    },
    methods: {
        addChapter(){
            chapters.addChapter(this.newChapter).then(response=>{
                this.$notify.success({
                    title: 'Success',
                    message: "Added a new chapter to the course",
                    showClose: false
                })
                this.newChapter.title = ""
                this.newChapter.sort = 0
                this.getChapters()
                this.newChapterDialogVisible = false
            }).catch(error =>{
                this.$notify.error({
                    title: 'Error',
                    message: "Failed to add a chapter to the course",
                    showClose: false
                })
            })         
        },
        getChapters(){
            chapters.getChaptersById(this.courseId).then(response =>{
                this.chapterList = response.data.records
            })
        },
        getCurrentChapter(chapterId){
            chapters.getChapterById(chapterId).then(response => {
                this.currentChapter = response.data.records
            })
        },
        modifyChapterDialog(chapterId){
            this.modifyChapterDialogVisible = true
            this.currentChapter.chapterId = chapterId
            this.getCurrentChapter(this.currentChapter.chapterId)
        },
        removeChapter(chapterId){
            chapters.deleteChapterById(chapterId).then(response => {
                this.$notify.success({
                    title: 'Success',
                    message: "Deleted a chapter to the course",
                    showClose: false
                })
                this.getChapters()
            }).catch(error => {
                this.$notify.error({
                    title: 'Error',
                    message: "Failed to delete a chapter to the course",
                    showClose: false
                })
            })
        },
        modifyChapter(){
            chapters.modifyChapter(this.currentChapter).then(response => {
                this.$notify.success({
                    title: 'Success',
                    message: "Modified a chapter to the course",
                    showClose: false
                })
                this.getChapters()
                this.modifyChapterDialogVisible = false
                this.currentChapter = {
                    courseId: this.courseId,
                    chapterId: "",
                    title: "",
                    sort: 0,
                }
            }).catch(error => {
                this.$notify.error({
                    title: 'Error',
                    message: "Failed to modify a chapter to the course",
                    showClose: false
                })
            })
        },
        cancelModifyChapter() {
            this.getChapters()
            this.modifyChapterDialogVisible = false
            this.currentChapter = {
                courseId: this.courseId,
                chapterId: "",
                title: "",
                sort: 0,
            }
        },
        cancelNewChapter(){
            this.newChapter.title = ""
            this.newChapter.sort = 0
            this.newChapterDialogVisible = false
        },
        previous(){
            this.$router.push({path:`/courses/info/${this.courseId}`})
        },
        next(){
            this.$router.push({path:`/courses/publish/${this.courseId}`})
        }
    }
}
</script>

<style scoped>
.chapterViewPort{
    overflow-y: auto; /* Optional: allows scrolling if needed */
    height: calc(75vh); /* Adjust this value as necessary */
    border: 1px solid #DDD;
}
.chapterList{
    position: relative;
    list-style: none;
    margin: 5px 0;
    padding: 5px;
}
.chapterList li{
  position: relative;
}
.chapterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chapterList .acts {
    float: right;
    font-size: 14px;
}
.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}
.button-container {
    position: fixed;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    gap: 12px; /* Space between buttons */
}
</style>

