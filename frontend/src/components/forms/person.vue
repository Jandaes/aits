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
	        <div class="col-md-6">
	          <!-- general form elements -->
	          <div class="box box-primary">
	            <div class="box-header with-border" style="text-align:left">
	              <h3 class="box-title">列表显示</h3>
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
	              			<tr>
	              				<td>1</td>
	              				<td>73</td>
	              				<td>大佬</td>
	              				<td>上海</td>
	              				<td>
	              					<button class="btn btn-success">修改</button>
	              				</td>
	              			</tr>
	              			<tr>
	              				<td>1</td>
	              				<td>73</td>
	              				<td>大佬</td>
	              				<td>上海</td>
	              				<td>
	              					<button class="btn btn-success">修改</button>
	              				</td>
	              			</tr>
	              			<tr>
	              				<td>1</td>
	              				<td>73</td>
	              				<td>大佬</td>
	              				<td>上海</td>
	              				<td>
	              					<button class="btn btn-success">修改</button>
	              				</td>
	              			</tr>
	              		</tbody>
	              	</table>
	              </div>
	              <!-- /.box-body -->
	              <div class="box-footer" style="text-align:left">
	                页脚显示
	              </div>
	            </form>
	          </div>
	          <!-- /.box -->
	        </div>
	        <!--/.col (left) -->
	        <!-- right column -->
	        <div class="col-md-6">
	          <!-- Horizontal Form -->
	          <div class="box box-info">
	            <div class="box-header with-border">
	              <h3 class="box-title">添加/修改</h3>
	            </div>
	            <!-- /.box-header -->
	            <!-- form start -->
	            <form class="form-horizontal">
	              <div class="box-body">
	                <div class="form-group">
	                  <label for="inputEmail3" class="col-sm-2 control-label">Email</label>

	                  <div class="col-sm-10">
	                    <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <label for="inputPassword3" class="col-sm-2 control-label">Password</label>

	                  <div class="col-sm-10">
	                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <div class="col-sm-offset-2 col-sm-10">
	                    <div class="checkbox">
	                      <label>
	                        <input type="checkbox"> Remember me
	                      </label>
	                    </div>
	                  </div>
	                </div>
	              </div>
	              <!-- /.box-body -->
	              <div class="box-footer">
	                <button type="submit" class="btn btn-default">Cancel</button>
	                <button type="submit" class="btn btn-info pull-right">Sign in</button>
	              </div>
	              <!-- /.box-footer -->
	            </form>
	          </div>
	          <!-- /.box -->
	          
	          <!-- /.box -->
	        </div>
	        <!--/.col (right) -->
	      </div>
	      <!-- /.row -->
	    </section>
	    <!-- /.content -->
    </div>
</template>
