<template>
    <div class="app-container">
        <h2 style="text-align: center;">Publish a New Course</h2>

        <!--Steps-->
        <el-steps :active="active" process-status="wait" align-center>
            <el-step title="Fill in Course Information"></el-step>
            <el-step title="Fill in Course Chapters"></el-step>
            <el-step title="Publish and Review"></el-step>
        </el-steps>

        <!--New Chapter Button-->
        <el-button type="primary" style="margin-top: 12px; margin-bottom: 12px;" @click="newChapterDialogVisible = true"
            icon="el-icon-plus">New Chapter</el-button>

        <!--New Chapter Form-->
        <el-dialog title="New Chapter" :visible.sync="newChapterDialogVisible" :show-close="false">

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

        <!--Modify Chapter Form-->
        <el-dialog title="Modify Chapter" :visible.sync="modifyChapterDialogVisible" :show-close="false">

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
                <el-button type="primary" @click="modifyChapter()">Modify</el-button>
            </div>
        </el-dialog>

        <!--Add New Video Form-->
        <el-dialog title="New Video" :visible.sync="newVideoDialogVisible" :show-close="false">

            <el-form :model="newVideo" label-position="right" label-width="120px">
                <el-form-item label="Title" :label-width="formLabelWidth">
                    <el-input v-model="newVideo.title" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="Position" :label-width="formLabelWidth">
                    <el-input-number :min="0" v-model="newVideo.sort" controls-position="right"
                        placeholder="The index of the chapter" />
                    <el-tooltip class="item" effect="dark"
                        content="Enter the index of the chapter. Lower numbers appear first." placement="top">
                        <el-button icon="el-icon-question" size="mini"
                            style="margin-left: 5px; border: none; color: grey" disabled></el-button>
                    </el-tooltip>
                </el-form-item>

                <el-form-item label="Pricing Option">
                    <el-radio-group v-model="newVideo.isFree">
                        <el-radio :label="1">Free</el-radio>
                        <el-radio :label="0">Priced</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="Upload Video">

                </el-form-item>

            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelAddVideo()">Cancel</el-button>
                <el-button type="primary" @click="addVideo()">Add</el-button>
            </div>

        </el-dialog>


        <!--Add New Video Form-->
        <el-dialog title="Modify Video" :visible.sync="modifyVideoDialogVisible" :show-close="false">

            <el-form :model="currentVideo" label-position="right" label-width="120px">
                <el-form-item label="Title" :label-width="formLabelWidth">
                    <el-input v-model="currentVideo.title" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="Position" :label-width="formLabelWidth">
                    <el-input-number :min="0" v-model="currentVideo.sort" controls-position="right"
                        placeholder="The index of the chapter" />
                    <el-tooltip class="item" effect="dark"
                        content="Enter the index of the chapter. Lower numbers appear first." placement="top">
                        <el-button icon="el-icon-question" size="mini"
                            style="margin-left: 5px; border: none; color: grey" disabled></el-button>
                    </el-tooltip>
                </el-form-item>

                <el-form-item label="Pricing Option">
                    <el-radio-group v-model="currentVideo.isFree">
                        <el-radio :label="1">Free</el-radio>
                        <el-radio :label="0">Priced</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="Upload Video">

                </el-form-item>

            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelModifyVideo()">Cancel</el-button>
                <el-button type="primary" @click="modifyVideo()">Modify</el-button>
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
                                <el-button type="primary" icon="el-icon-plus"
                                    @click="addNewVideoDialog(chapter.id)"></el-button>
                                <el-button type="primary" icon="el-icon-edit"
                                    @click="modifyChapterDialog(chapter.id)"></el-button>
                                <el-button type="danger" icon="el-icon-delete"
                                    @click="removeChapter(chapter.id)"></el-button>
                            </el-button-group>
                        </span>

                    </p>

                    <ul class="chapterList videoList">
                        <li v-for="video in chapter.children" :key="video.id">
                            <p>
                                {{ video.title }}

                                <span class="acts">
                                    <el-button type="text" @click="modifyVideoDialog(video.id)">Edit</el-button>
                                    <el-button type="text" @click="removeVideo(video.id)">Delete</el-button>
                                </span>
                            </p>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>






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
import videos from '@/api/edu/videos.js'

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
            newVideo:{
                courseId:this.courseId,
                chapterId:"",
                title:"",
                sort:0,
                isFree:1,
            },
            currentVideo:{

            },
            newChapterDialogVisible:false,
            modifyChapterDialogVisible:false,
            newVideoDialogVisible:false,
            modifyVideoDialogVisible:false,
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
        cancelNewChapter(){
            this.newChapter.title = ""
            this.newChapter.sort = 0
            this.newChapterDialogVisible = false
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
                    message: "Deleted a chapter of the course",
                    showClose: false
                })
                this.getChapters()
            }).catch(error => {
                this.$notify.error({
                    title: 'Error',
                    message: "Failed to delete a chapter of the course",
                    showClose: false
                })
            })
        },
        modifyChapter(){
            chapters.modifyChapter(this.currentChapter).then(response => {
                this.$notify.success({
                    title: 'Success',
                    message: "Modified a chapter of the course",
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
                    message: "Failed to modify a chapter of the course",
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
        addNewVideoDialog(chapterId){
            this.newVideoDialogVisible = true
            this.newVideo.chapterId = chapterId
            this.newVideo.courseId = this.courseId
        },
        addVideo() {
            videos.addVideo(this.newVideo).then(response => {
                this.$notify.success({
                    title: 'Success',
                    message: "Add a new video to a chapter",
                    showClose: false
                })
                this.getChapters()
                this.newVideoDialogVisible = false
                this.newVideo = {
                    courseId: this.courseId,
                    chapterId: "",
                    title: "",
                    sort: 0,
                    isFree: 1,
                }
            }).catch(error => {
                this.$notify.error({
                    title: 'Error',
                    message: "Failed to add a video to a chapter",
                    showClose: false
                })
            })
        },
        cancelAddVideo(){
            this.newVideo={
                courseId:this.courseId,
                chapterId:"",
                title:"",
                sort:0,
                isFree:1,
            }
            this.newVideoDialogVisible = false
        },
        removeVideo(id) {
            videos.deleteVideoById(id).then(response => {
                this.$notify.success({
                    title: 'Success',
                    message: "Deleted a video of a chapter",
                    showClose: false
                })
                this.getChapters()
            }).catch(error => {
                this.$notify.error({
                    title: 'Error',
                    message: "Failed to delete a video of a chapter",
                    showClose: false
                })
            })
        },
        getCurrentVideo(id){
            videos.getVideoById(id).then(response =>{
                this.currentVideo = response.data.records
            })
        },
        modifyVideoDialog(id){
            this.modifyVideoDialogVisible = true
            this.currentVideo.videoId = id
            this.getCurrentVideo(this.currentVideo.videoId)
        },
        cancelVideoDialog(){
            this.getChapters()
            this.modifyVideoDialogVisible = false
            this.currentVideo = {}
        },
        modifyVideo(){
            videos.updateVideo(this.currentVideo).then(response => {
                this.$notify.success({
                    title: 'Success',
                    message: "Modified a video of the chapter",
                    showClose: false
                })
                this.getChapters()
                this.modifyVideoDialogVisible = false
                this.currentVideo = {}
            }).catch(error => {
                this.$notify.error({
                    title: 'Error',
                    message: "Failed to modify a video of the chapter",
                    showClose: false
                })
            })
        },
        cancelModifyVideo(){
            this.getChapters()
            this.modifyVideoDialogVisible = false
            this.currentVideo = {}
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

