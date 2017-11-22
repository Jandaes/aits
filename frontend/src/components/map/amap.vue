<template>
	<div>
		<section class="content-header">
			<h1>地图显示
	        <small>地图</small>
			</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">地图显示</a></li>
        <li class="active">地图</li>
      </ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header with-border" style="text-align:left">
							<div class="form-inline">
								<div class="form-group">
									<label>出发日期:</label>
									<input type="date" class="form-control">
								</div>
								<div class="form-group">
									<label>起始站:</label>
									<input type="text" class="form-control" placeholder="起始站">
								</div>
								<div class="form-group">
									<label>目的站:</label>
									<input type="text" class="form-control" placeholder="目的站">
								</div>
								<div class="form-group">
									<label>类型:</label>
									<input type="text" class="form-control" placeholder="目的站">
								</div>
								<button class="btn btn-success"><i class="fa fa-search"></i> 走起</button>
							</div>
            </div>
            <div class="box-body">
							<el-amap ref="map" vid="amapDemo" :amap-manager="amapManager" :center="center" :zoom="zoom" :plugin="plugin" :events="events" class="amap-demo">
      				</el-amap>
		      	</div>
		      	<div class="box-footer">
		      		<button @click="getMap()" class="btn btn-success pull-left">get map</button>
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
 	.amap-demo {
    height: 300px;
  }
</style>
<script>

let amapManager = new VueAMap.AMapManager();
export default{
	data() {
        return {
          amapManager,
          zoom: 12,
          center: [121.59996, 31.197646],
          events: {
            init: (o) => {
              console.log(o.getCenter())
              console.log(this.$refs.map.$$getInstance())
              o.getCity(result => {
                console.log(result)
              })
            },
            'moveend': () => {
            },
            'zoomchange': () => {
            },
            'click': (e) => {
              alert('map clicked');
            }
          },
          plugin: ['ToolBar', {
            pName: 'MapType',
            defaultType: 0,
            events: {
              init(o) {
                console.log(o);
              }
            }
          }]
        };
	},
	created (){
		console.log("********************");
		
		console.log("********************");
	},
	methods: {
		getMap() {
			alert("admin");
      // amap vue component
      console.log(amapManager._componentMap);
      // gaode map instance
      console.log(amapManager._map);
    }
  }
}
</script>
