<template>
	<div>
		<section class="content-header">
			<h1>权限管理
	        <small>角色列表</small>
			</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 权限管理</a></li>
        <li class="active">角色列表</li>
      </ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-6">
					<div class="box">
						<div class="box-header with-border" style="text-align:left">
							<div class="form-inline">
								<div class="form-group">
									<label>名称:</label>
									<input type="text" value="还没实现这功能" class="form-control">
								</div>
								<button class="btn btn-success"><i class="fa fa-search"></i> 走起</button>
							</div>
            </div>
            <div class="box-body">
							<table class="table table-bordered table-hover table-striped" id="main-table">  
				        <thead class="table-header">  
				          <tr>  
				            <th>角色名称</th>
				            <th>角色类型</th>
				            <th>创建时间</th>
				            <th>状态</th>
				            <th>备注</th>
				            <th>操作</th>
				          </tr>  
				        </thead>  
			          <tbody>
			          	<tr v-for="role in roles">
			          		<td>{{ role.name }}</td>
			          		<td>{{ role.type }}</td>
			          		<td>{{ role.createTime }}</td>
			          		<td>{{ role.status==0?"锁定":"正常" }}</td>
			          		<td>{{ role.remarks }}</td>
			          		<td>
			          			<button class="btn btn-default">删除</button>
			          		</td>
			          	</tr>
			          </tbody>  
			        </table>
		      	</div>
	      	</div>
				</div>
			</div>
		</section>
	</div>
</template>
<style>
	.content-header>h1{
		text-align: left;
	}
	.table-header>tr>th{
		text-align: center;
	}
</style>
<script>

export default{
	data() {
		return {
			roles:[],
		}
	},
	created (){
		this.list();
	},
	methods: {
	    list: function () {
	      	const url = "http://localhost:8999/role/list";
			this.$http.get(url).then(response => {
				var obj = response.body;
			    if(obj.code == 200){
			    	this.roles = obj.data;
			    }else if(obj.code == 403){
			    	/*这里是无权限操作*/
			    	this.$router.push('/');
            		this.$router.push('/forbidden');
			    }else if(obj.code == 10007){
			    	/*这里是未登录*/
			    	if(confirm("老铁、去登录吧")){
			    		this.$router.push('/');
            			this.$router.push('/');	
			    	}else{
			    		alert("你不登录咋看a");	
			    	}
			    }
			    console.info(this.roles);
		  	});
   		}
  }
}
</script>
