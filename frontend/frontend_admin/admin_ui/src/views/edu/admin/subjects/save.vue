<template>
    <div class="app-container">
        <el-form label-width="200px">
            <el-form-item label="Excel Format Sample">
                <el-tag type="info">excel sample</el-tag>
                <el-tag>
                    <i class="el-icon-download"/>
                    <a :href="'/static/01.xlsx'" download>Download Sample</a>
                </el-tag>
            </el-form-item>

            <el-form-item label="Choose File and Upload">
                <el-upload
                    ref="upload"
                    :auto-upload="false"
                    :on-success="fileUploadSuccess"
                    :on-error="fileUploadError"
                    :disabled="importBtnDisabled"
                    :limit="1"
                    :action="BASE_API + '/eduservice/subject'"
                    name="file"
                    accept=".xls, .xlsx">
                    <el-button slot="trigger" size="small" type="primary">Choose</el-button>
                    <el-button
                        :loading="loading"
                        style="margin-left: 10px;"
                        size="small"
                        type="success"
                        @click="submitUpload">Upload
                    </el-button>
                </el-upload>
            </el-form-item>
        </el-form>




    </div>
</template>


<script>
export default {
    data(){
        return{
            BASE_API: process.env.VUE_APP_BASE_API,
            importBtnDisabled: false,
            loading: false
        }
    },

    created(){

    },

    methods:{
        submitUpload(){
            this.importBtnDisabled = true
            this.loading = true
            this.$refs.upload.submit()
        },
        
        fileUploadSuccess() {
            this.loading = false
            this.$message({
                type: 'success',
                message: 'Uploaded new subjects'
            })
            
        },

        
        fileUploadError() {
            this.importBtnDisabled = false
            this.loading = false
            this.$message({
                type: 'error',
                message: 'Failed to upload new subjects'
            })
        }
    }
}
</script>