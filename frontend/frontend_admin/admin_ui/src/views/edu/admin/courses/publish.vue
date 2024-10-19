<template>
    <div class="app-container">
        <h2 style="text-align: center;">Publish a New Course</h2>


        <el-steps :active="active" process-status="wait" align-center>
            <el-step title="Fill in Course Information"></el-step>
            <el-step title="Fill in Course Chapters"></el-step>
            <el-step title="Publish and Review"></el-step>
        </el-steps>

        <el-button :disabled="saveBtnDisabled" style="margin-top: 12px;" @click="previous">Previous</el-button>
        <el-button :disabled="saveBtnDisabled" style="margin-top: 12px;" @click="next">Publish</el-button>

    </div>
</template>


<script>
// import { defineComponent } from '@vue/composition-api'

export default{
    data(){
        return{
            active: 3,
            saveBtnDisabled:false,
            id: -1
        }
    },
    created(){
        if(this.$route.params && this.$route.params.id){
            this.id = this.$route.params.id
        }
    },
    methods:{
        previous(){
            this.$router.push({path:`/courses/chapter/${this.id}`})
        },
        next(){
            this.$router.push({path:`/courses/list`})
        }
    }
}
</script>
