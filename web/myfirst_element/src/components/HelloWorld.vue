<template>
    <div id="app">
       <div class="nav">
            <div class="line"></div>
            <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
                <el-menu-item index="1">处理中心</el-menu-item>
                <el-submenu index="2">
                    <template slot="title">我的工作台</template>
                    <el-menu-item index="2-1">选项1</el-menu-item>
                    <el-menu-item index="2-2">选项2</el-menu-item>
                    <el-menu-item index="2-3">选项3</el-menu-item>
                    <el-submenu index="2-4">
                        <template slot="title">二级导航</template>
                        <el-menu-item index="2-4-1">三级导航</el-menu-item>
                        <el-menu-item index="2-4-2">选项2</el-menu-item>
                        <el-menu-item index="2-4-3">选项3</el-menu-item>
                    </el-submenu>
                </el-submenu>
                <el-menu-item index="3" disabled>消息中心</el-menu-item>
                <el-menu-item index="4">订单管理</el-menu-item>
            </el-menu>
        </div> 
        <div class="banner" style="width: 80%;margin: 0 auto;">
                <template>
                    <el-carousel :interval="4000" type="card" height="200px">
                        <el-carousel-item v-for="item in bannerList" :key="item">
                            <h3 class="medium">{{ item }}</h3>
                        </el-carousel-item>
                    </el-carousel>
                </template>
        </div>
        <div class="block">
    
            <el-timeline>
                <el-timeline-item v-for="d in dataList" v-bind:todo="d" v-bind:key="d.id" :timestamp="d.time"
                    placement="top">
    
                    <el-card>
    
                        <h4>{{d.title}}:{{d.id}}</h4>
                        <p>{{d.msg}}</p>
                        <div>
                            <el-steps :active="d.steps.active" align-center :finish-status='d.steps.finishStatus'>
                                <el-step v-for="s in d.steps.list" v-bind:todo="s" title="步骤1"
                                    description="这是一段很长很长很长的描述性文字">
                                </el-step>
                            </el-steps>
                            <div style="margin-top: 10px;text-align: right;">
                                <!-- <el-popconfirm  title="确定删除这条记录吗？" >
                                                        <el-button  slot="reference" @Click="popList(d.id-1)">删除</el-button>
                                                        </el-popconfirm> -->
                                <el-button type="danger" icon="el-icon-delete" circle slot="reference"
                                    @Click="popList(d.id-1)"></el-button> 
                                <el-button type="primary" @click="next(d.id-1)" icon="el-icon-check" circle></el-button>
                            </div>
                        </div>
    
                    </el-card>
                </el-timeline-item>
            </el-timeline>
    
        </div>
    </div>
</template>

<script>
export default {
  name: 'HelloWorld',
 data: function () {
            return { visible: false, 
             dataList: [
                 { id:1,time: '2020-06-15', title: '用户上传信息', msg: '张嘉豪上传了文件 2020-06-15 20:22:01' ,steps:{active:2,finishStatus:'error',list:[{title:'First Step',description:'这是第一步xxxxx'},{title:'Second Step',description:'这是第二步xxxxx'},{title:'Third Step',description:'这是第三步xxxxx'},{title:'Fourth Step',description:'这是第四步xxxxx'}]}}, 
                 { id:2,time: '2020-06-14', title: '用户上传信息', msg: '张嘉豪上传了文件 2020-06-14 20:22:01' ,steps:{active:1,finishStatus:'process',list:[{title:'First Step',description:'这是第一步xxxxx'},{title:'Second Step',description:'这是第二步xxxxx'},{title:'Third Step',description:'这是第三步xxxxx'},{title:'Fourth Step',description:'这是第四步xxxxx'}]}}, 
                 { id:3,time: '2020-06-13', title: '用户上传信息', msg: '张嘉豪上传了文件 2020-06-13 20:22:01' ,steps:{active:3,finishStatus:'success',list:[{title:'First Step',description:'这是第一步xxxxx'},{title:'Second Step',description:'这是第二步xxxxx'},{title:'Third Step',description:'这是第三步xxxxx'},{title:'Fourth Step',description:'这是第四步xxxxx'}]}},
                 { id:4,time: '2020-06-12', title: '用户上传信息', msg: '张嘉豪上传了文件 2020-06-12 20:22:01' ,steps:{active:4,finishStatus:'finish',list:[{title:'First Step',description:'这是第一步xxxxx'},{title:'Second Step',description:'这是第二步xxxxx'},{title:'Third Step',description:'这是第三步xxxxx'},{title:'Fourth Step',description:'这是第四步xxxxx'}]}},
                 { id:5,time: '2020-06-11', title: '用户上传信息', msg: '张嘉豪上传了文件 2020-06-11 20:22:01' ,steps:{active:2,finishStatus:'wait',list:[{title:'First Step',description:'这是第一步xxxxx'},{title:'Second Step',description:'这是第二步xxxxx'},{title:'Third Step',description:'这是第三步xxxxx'},{title:'Fourth Step',description:'这是第四步xxxxx'}]}},
                 { id:6,time: '2020-06-10', title: '用户上传信息', msg: '张嘉豪上传了文件 2020-06-10 20:22:01' ,steps:{active:1,finishStatus:'error',list:[{title:'First Step',description:'这是第一步xxxxx'},{title:'Second Step',description:'这是第二步xxxxx'},{title:'Third Step',description:'这是第三步xxxxx'},{title:'Fourth Step',description:'这是第四步xxxxx'}]}}
                 ],
                 activeIndex: '1',
                activeIndex2: '1',
                bannerList:["永远相信美好的事情即将发生","Never Mind the Scandal and Liber","聘聘袅袅十三余","豆蔻梢头二月初"] 
                 }
        },
        methods:{
            popList(e) {
                this.$confirm('确认删除已保存信息？', '确认信息', {
                    distinguishCancelAndClose: true,
                    confirmButtonText: '删除',
                    cancelButtonText: '取消'
                })
                    .then(() => {
                        this.dataList.splice(e, 1);
                this.$message({
                    type: 'success',
                    message: '删除成功',
                    showClose: true
                })
                console.log('删除元素下标:', e, this.dataList)
                    })
                    .catch(action => {
                        this.$message({
                            type: 'info',
                            message: action === 'cancel'
                                ? '取消删除'
                                : '停留在当前页面'
                        })
                    });
            },
            cancel(){
                console.log("cancel")
            },
            next(id) {
                this.$notify({
                    title: '工作完成',
                    message: '切换到下一个工作状态',
                    // duration: 0     //是否自动关闭
                })
                if (this.dataList[id].steps.active++ > 3) this.dataList[id].steps.active = 0;
            },
            handleSelect(key, keyPath) {
            console.log(key, keyPath);
            }
        }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .el-carousel__item h3 {
            color: #475669;
            font-size: 14px;
            opacity: 0.75;
            line-height: 200px;
            margin: 0;
        }
        .el-carousel__item:nth-child(2n) {
            background-color: #99a9bf;
        } 
        .el-carousel__item:nth-child(2n+1) {
            background-color: #d3dce6;
        }
        .medium{
            text-align: center;
        }
</style>
