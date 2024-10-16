<template>
    <div class="app-container">
      <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />
  
      <el-tree
        ref="tree2"
        :data="data2"
        :props="defaultProps"
        :filter-node-method="filterNode"
        class="filter-tree"
        default-expand-all
      />
  
    </div>
  </template>
  
  <script>

    import subjects from '@/api/edu/subjects.js'

  export default {
    data() {
      return {
        filterText: '',
        data2: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    watch: {
      filterText(val) {
        this.$refs.tree2.filter(val)
      }
    },
    created(){
        this.getSubjects()
    },
    methods: {
      filterNode(value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
      },
      getSubjects(){
        subjects.getSubjects().then(response => {
            this.data2 = response.data.records
        })
      }
    }
  }
  </script>