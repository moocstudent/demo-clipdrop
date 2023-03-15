<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style lang="less">
*{
  margin: 0;
  padding: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100%;
  min-height: 100vh;
  min-width: 1200px;
}
.fl{
  float: left;
}
.fr{
  float: right;
}
.clearfix:after{
  display: block;
  clear: both;
  content: "";
  height: 0;
  overflow: hidden;
}
.clearfix{
  *zoom: 1;
}
</style>
