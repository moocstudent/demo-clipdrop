<template>
  <div>
    <!-- 上传文件的表单，但是需要隐藏起来，利用其它按钮触发他 -->
    <input
      type="file"
      v-show="false"
      id="fileBox"
      :accept="fileType == 'file' ? 'image/*' : ''"
      @change="getFile"
      :multiple="isMultiple"
    />
    <!-- 用来触发上传文件框 -->
    <button class="btn" @click="openFile">选择文件</button>
    <span class="number">
      当前选中{{fileData.length}}个文件
    </span>
    <!-- 循环遍历出所有文件 -->
    <div class="showFile">
      <div
        class="item"
        v-for="(item, index) in fileData"
        :key="index"
        :title="item.name"
      >
        <!-- 放图片的盒子 -->
        <div class="imageBox">
          <img
            :src="require('../../assets/fjIcons/' + item.type + '.png')"
            alt=""
            v-if="
              item.type == 'xls' ||
              item.type == 'xlsx' ||
              item.type == 'doc' ||
              item.type == 'docx' ||
              item.type == 'ppt' ||
              item.type == 'pdf' ||
              item.type == 'txt' ||
              item.type == 'zip' ||
              item.type == '7z' ||
              item.type == 'rar' ||
              item.type == 'jpg' ||
              item.type == 'png' ||
              item.type == 'psd' ||
              item.type == 'ai' ||
              item.type == 'gif' ||
              item.type == 'bmp' ||
              item.type == 'svg' ||
              item.type == 'flv' ||
              item.type == 'mp4' ||
              item.type == 'fla' ||
              item.type == 'mov' ||
              item.type == 'mkv' ||
              item.type == 'wmv' ||
              item.type == 'wav'
            "
          />
          <img src="../../assets/fjIcons/tongyong.png" alt="" v-else />
        </div>
        <!-- 文字 -->
        <div class="text">
          {{ item.name }}
        </div>
        <!-- 删除按钮 -->
        <div class="icon" @click="deletData(index)">
          <el-button icon="el-icon-delete" circle></el-button>
        </div>
      </div>

      <!-- 下面四个item用于解决flex布局的位置错乱的问题 -->
      <div class="item clear"></div>
      <div class="item clear"></div>
      <div class="item clear"></div>
      <div class="item clear"></div>
      <div class="item clear"></div>
      <div class="item clear"></div>
      <div class="item clear"></div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    fileType: {
      type: String,
      default: "image",
    },
    isMultiple: {
      type: Boolean,
      default: true,
    },
    isClear: {
      type: Boolean,
      default: false,
    },
  },
  watch:{
    //所选中的文件发生变化时传递到父组件
    fileData:function(val){
      // console.log(val);
      this.$emit("getFileData",val)
    }
  },
  data() {
    return {
      fileData: [], //每次选择文件后会更新该数据
    };
  },
  methods: {
    //触发选择文件筐
    openFile() {
      //获取文件框file
      var fileBox = document.querySelector("#fileBox");
      //触发该事件
      fileBox.click();
    },
    //获取选中的内容
    getFile() {
      console.log('getFile this.fileType', this.fileType)
      //获取文件对象
      var fileObj = document.querySelector("#fileBox").files;
      if (this.isClear) {
        //清空用于接收文件的数组
        this.fileData.length = 0;
      }
      //如果设置选项为image，判断一下多选的文件中是否包含非图片的文件
      //下面这段代码是对上传的文件进行过滤判断，如文件大小等其他过滤内容请自行设置，文件名称为item.name,文件大小为item.size,文件类型为item.type
      fileObj.forEach((item) => {
        console.log(item);
        console.log('this.fileType', this.fileType)
        if (this.fileType == "image" && item.type.indexOf("image/") == -1) {
          this.$message({
            type: "warning",
            message: item.name + "为非图片文件，请重新上传",
          });
        } else {
          //将循环出来的文件用数组接收，利于之后的数据处理
          this.fileData.push({
            name: item.name,
            type: item.type,
            size: item.size,
          });
          console.log(this.fileData);
        }
      });
      //循环fileData，获取文件的后缀
      this.fileData.forEach((item) => {
        console.log(item.name.slice(item.name.lastIndexOf(".") + 1));
        item.type = item.name.slice(item.name.lastIndexOf(".") + 1);
        console.log(item);
        // console.log(item.type)
      });
    },
    //删除该文件
    deletData(index) {
      this.$confirm("此操作将删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.fileData.splice(index, 1);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>
<style lang="less" scoped>
//按钮样式
.btn {
  background: none;
  border: none;
  outline: none;
  padding: 10px 30px;
  border-top: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
  border-image: linear-gradient(rgb(77, 03, 216), rgb(165, 117, 237)) 30 30;
  cursor: pointer;
  // transition: all 1s linear;
  background: none;
  color: rgb(77, 03, 216);
  font-weight: bold;
  background: linear-gradient(rgba(77, 03, 216, 0), rgba(165, 117, 237, 0));
  box-sizing: border-box;
  &:hover {
    background: linear-gradient(rgba(77, 03, 216, 1), rgba(165, 117, 237, 1));
    transition: all 0.5s linear;
    color: #fff;
    border: 1px solid #fff;
    box-sizing: border-box;
  }
}
.number{
  font-size: 14px;
  margin-left: 14px;
}
.showFile {
  width: 100%;
  padding-right: 20px;
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .item {
    width: 12%;
    height: 150px;
    margin-top: 20px;
    transition: all 0.2s linear;
    cursor: pointer;
    position: relative;
    .imageBox {
      width: 100%;
      text-align: center;
      height: 100px;
      margin-top: 10px;
      img {
        height: 100px;
      }
    }
    .text {
      text-align: center;
      margin-top: 5px;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    .icon {
      width: 40px;
      height: 40px;
      position: absolute;
      right: 0;
      bottom: 0;
      left: 0;
      top: 0;
      margin: auto;
      display: none;
    }
    &:hover {
      background: rgba(0, 0, 0, 0.1);
      transition: all 0.2s linear;
    }
    &:hover .icon {
      display: block;
    }
  }

  .item.clear {
    border: none;
    height: 0;
    overflow: hidden;
    visibility: hidden;
  }
}
</style>