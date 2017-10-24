<template>
	<div>
	  	<!-- Content Header (Page header) -->
	    <section class="content-header">
	      <h1 style="text-align:left;">
	        列表操作
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
										<label>编号:</label>
										<input type="text" v-model="person.id" class="form-control">
									</div>
									<div class="form-group">
										<label>姓名:</label>
										<input type="text" class="form-control" v-model="person.name" placeholder="姓名">
									</div>
									<div class="form-group">
										<label>年龄:</label>
										<input type="number" class="form-control" v-model="person.age" placeholder="年龄">
									</div>
									<div class="form-group">
										<label>地址:</label>
										<input type="text" class="form-control" v-model="person.address" placeholder="地址">
									</div>
									<button class="btn btn-success" @click="seachPerson"><i class="fa fa-search"></i> 走起</button>
								</div>
	            </div>
	            <!-- /.box-header -->
	            <!-- form start -->
	            <form role="form">
	              <div class="box-body">
	              	<table class="table table-bordered table-hover table-striped">
	              		<thead>
	              			<tr>
	              				<th>编号</th>
	              				<th>姓名</th>
	              				<th>年龄</th>
	              				<th>地址</th>
	              				<th>操作</th>
	              			</tr>
	              		</thead>
	              		<tbody>
	              			<tr v-for="person in personList">
	              				<td>{{ person.id }}</td>
	              				<td>{{ person.name }}</td>
	              				<td>{{ person.age }}</td>
	              				<td>{{ person.address }}</td>
	              				<td>
	              					<button class="btn btn-success" @click="editPersonFun(person)">修改</button>
	              				</td>
	              			</tr>
	              		</tbody>
	              	</table>
	              </div>
	              <!-- /.box-body -->
	              <div class="box-footer" style="text-align:left">
	                当前共	{{ personList.length }} 	条数据
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
	                  <label class="col-sm-2 control-label">编号</label>
	                  <div class="col-sm-10">
	                    <input type="input" class="form-control" placeholder="编号" readonly v-model="editPerson.id">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
	                  <div class="col-sm-10">
	                    <input type="text" class="form-control" placeholder="姓名" v-model="editPerson.name">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <label for="inputPassword3" class="col-sm-2 control-label">年龄</label>
	                  <div class="col-sm-10">
	                    <input type="number" class="form-control" placeholder="年龄" v-model="editPerson.age">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <label for="inputPassword3" class="col-sm-2 control-label">地址</label>
	                  <div class="col-sm-10">
	                    <input type="text" class="form-control" placeholder="地址" v-model="editPerson.address">
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
				personList:[],
				person:{},
				editPerson:{}
			}
		},
		created (){
			this.seachPerson();		  
		},
		methods: {
			seachPerson: function () {
				this.$http.get("http://localhost:8999/person/findAll",{"id":"1"}).then(response => {
			    // get body data
			    this.personList = response.body;
			  }, response => {
			    // error callback
			    alert("数据加载失败了！");
			  });
			},
			editPersonFun: function (obj) {
				// 选择修改的
				this.editPerson = obj;
			}
		}
	}
</script>

