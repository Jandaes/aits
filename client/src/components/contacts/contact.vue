<template>
  <div>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1 style="text-align:left;">
        商户信息
        <small>Preview</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Forms</a></li>
        <li class="active">General Elements</li>
      </ol>
    </section>
    <!-- Main content -->
    <section class="content">



      <div class="row">
        <!-- left column -->
        <div class="col-md-8">
          <!-- general form elements -->
          <div class="box box-primary">
            <div class="box-header with-border" style="text-align:left">
              <div class="form-inline">
                <div class="form-group">
                  <label>商户名:</label>
                  <input type="text" class="form-control" v-model="contact.contactName" placeholder="姓名">
                </div>
                <div class="form-group">
                  <label>联系人:</label>
                  <input type="number" class="form-control" v-model="contact.linkman" placeholder="年龄">
                </div>
                <div class="form-group">
                  <label>联系电话:</label>
                  <input type="text" class="form-control" v-model="contact.phone" placeholder="地址">
                </div>

                <button class="btn btn-success" @click="seachContact"><i class="fa fa-search"></i> 走起</button>
              </div>
              <br>
              <button class="btn btn-success" @click="addContact"><i class="fa fa-search"></i> 新增</button>
              <button class="btn btn-success" @click="editContact"><i class="fa fa-search"></i> 修改</button>
              <button class="btn btn-success" @click="deleteContact"><i class="fa fa-search"></i> 删除</button>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form">
              <div class="box-body">
                <table class="table table-bordered table-hover table-striped">
                  <thead>
                  <tr>
                    <th>商户号</th>
                    <th>商户名</th>
                    <th>联系人</th>
                    <th>联系方式</th>
                    <th>商户地址</th>
                    <th>财务信息</th>
                    <th>商户级别</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="contact in contactList">
                    <td>{{ contact.contactNumber }}</td>
                    <td>{{ contact.contactName }}</td>
                    <td>{{ contact.linkman }}</td>
                    <td>{{ contact.phone }}</td>
                    <td>{{ contact.address }}</td>
                    <td>{{ contact.payAndGet }}</td>
                    <td>{{ contact.rank }}</td>
                    <td>
                      <button class="btn btn-success" @click="editContactFun(contact)">修改</button>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <!-- /.box-body -->
              <div class="box-footer" style="text-align:left">
                当前共	{{ contactList.length }} 	条数据
              </div>
            </form>
          </div>
          <!-- /.box -->
        </div>
        <!--/.col (left) -->
        <!-- right column -->
        <div class="col-md-4">
          <!-- Horizontal Form -->
          <div class="box box-info">
            <div class="box-header with-border" style="text-align:left;">
              <h3 class="box-title">添加/修改</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">商户名</label>
                  <div class="col-sm-10">
                    <input type="input" class="form-control" placeholder="商户名" readonly v-model="editContact.contactName">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">联系人</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="联系人" v-model="editContact.linkman">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">电话</label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" placeholder="电话" v-model="editContact.phone">
                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-2 control-label">商户地址</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="商户地址" v-model="editContact.address">
                  </div>
                </div>

              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button class="btn btn-success pull-right">提交</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>

          <!-- /.box -->
        </div>
        <!--/.col (right) -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>

</template>
<style></style>
<script>
  export default{
    data() {
      return {
        contactList:[],
        contact:{},
        editContact:{}
      }
    },
    created (){
      this.$http.post("http://localhost:8999/person/findAll").then(response => {
        // get body data
        this.contactList = response.body;
      }, response => {
        // error callback
        alert("数据加载失败了！");
      });
    },
    methods: {
      seachContact: function () {
        var formData = JSON.stringify(this.person);
        this.$http.post("http://localhost:8999/person/query",formData).then(response => {
          // get body data
          this.contactList = response.body;
        }, response => {
          // error callback
          alert("数据加载失败了！");
        });
      },
      addContact: function () {
      //  this.$layer.alert("找不到对象！");
        this.$layer.open({
          type: 1,
          title: "更新",
          area: ["300px", "230px"],
       //   content: $("#hello"),
          btn: ["保存"],
          yes: function (index) {

            alert("点击保存");

          },
          cancel: function () { //点击关闭按钮
          }
        });
      },
      editContactFun: function (obj) {
        // 选择修改的
        this.editContact = obj;
      }
    }
  }
</script>

