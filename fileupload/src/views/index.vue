<template>
  <div class="box clearfix">
    <!-- 组件标签 -->
    <div class="fileBox fl">
      <upload-files
        class="compoentPosition"
        :fileType="fileType"
        :isMultiple="isMultiple"
        :isClear="isClear"
        @getFileData="getFileData"
      ></upload-files>
    </div>
    <!-- 设置内容 -->
<!--    <div class="setBox fr">-->
<!--      <el-card class="box-card">-->
<!--        <div slot="header">-->
<!--          <span>设置</span>-->
<!--        </div>-->
<!--        <el-divider content-position="left">选择类型（如果是图片则展示图片预览，是文件则展示文件图标）</el-divider>-->
<!--        <el-radio v-model="fileType" label="file" border size="mini"-->
<!--          >所有文件</el-radio-->
<!--        >-->
<!--        <el-radio v-model="fileType" label="image" border size="mini"-->
<!--          >图片</el-radio-->
<!--        >-->
<!--        <el-divider content-position="left">是否可以多选</el-divider>-->
<!--        <el-radio v-model="isMultiple" :label="true" border size="mini"-->
<!--          >可多选</el-radio-->
<!--        >-->
<!--        <el-radio v-model="isMultiple" :label="false" border size="mini"-->
<!--          >不可多选</el-radio-->
<!--        >-->
<!--        <el-divider content-position="left">每次上传是否需要清空已选择的文件</el-divider>-->
<!--        <el-radio v-model="isClear" :label="false" border size="mini"-->
<!--          >否</el-radio-->
<!--        >-->
<!--        <el-radio v-model="isClear" :label="true" border size="mini"-->
<!--          >是</el-radio-->
<!--        >-->
<!--      </el-card>-->
<!--    </div>-->
  </div>
</template>
<script>
//引入组件
import uploadFiles from "@/components/uploadFiles/index";
export default {
  //注册组件
  components: {
    uploadFiles,
  },
  data() {
    return {
      fileType: "file", //image为图片，file为所有文件
      isMultiple:true,//是否可以多选
      isClear:false,//每次上传是否需要清空已选择的文件
      fileData:[]
    };
  },
  methods:{
    //获取子组件传来的文件
    getFileData(val){
      this.fileData=val
    }
  }
};
</script>
<style scoped lang="less">
.box {
  padding: 30px;
  .fileBox{
      width: 70%;
  }
  .setBox{
      width: 30%;
  }
}
</style>