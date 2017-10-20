<template>
	<div>
		<section class="content-header">
			<h1>火车票信息
	        <small>车票</small>
			</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Forms</a></li>
        <li class="active">火车票信息</li>
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
									<input type="date" v-model="tick.train_date" class="form-control">
								</div>
								<div class="form-group">
									<label>起始站:</label>
									<input type="text" class="form-control" v-model="tick.from_station" placeholder="起始站">
								</div>
								<div class="form-group">
									<label>目的站:</label>
									<input type="text" class="form-control" v-model="tick.to_station" placeholder="目的站">
								</div>
								<div class="form-group">
									<label>类型:</label>
									<select name="" id="" v-model="tick.purpose_codes" class="form-control">
										<option value="ADULT">成人</option>
										<option value="STUDEND">学生</option>
									</select>
								</div>
								<button class="btn btn-success" @click="seachTick"><i class="fa fa-search"></i> 走起</button>
							</div>
            </div>
            <div class="box-body">
							<table class="table table-bordered table-hover table-striped" id="main-table">  
				        <thead>  
				          <tr>  
				            <th>车次</th>
				            <th>始发站</th>
				            <th>目的站</th>
				            <th>出发时间</th>
				            <th>到达时间</th>
				            <th>历时</th>
				            <th>商务特等</th>
				            <th>一等座</th>
				            <th>二等座</th>
				            <th>高级软卧</th>
				            <th>软卧</th>
				            <th>动卧</th>
				            <th>硬卧</th>
				            <th>软座</th>
				            <th>硬座</th>
				            <th>无座</th>
				            <th>其他</th>
				            <th>备注</th>
				          </tr>  
				        </thead>  
			          <tbody>
			          	<tr v-for="tick in ticks">
			          		<td>{{ tick.trainNumber }}</td>
			          		<td>{{ tick.departureStation }}</td>
			          		<td>{{ tick.destinationStation }}</td>
			          		<td>{{ tick.initialTime }}</td>
			          		<td>{{ tick.arriveTime }}</td>
			          		<td>{{ tick.last }}</td>
			          		<td>{{ tick.businessSeat }}</td>
			          		<td>{{ tick.firstSeat }}</td>
			          		<td>{{ tick.secondSeat }}</td>
			          		<td>{{ tick.highSleeper }}</td>
			          		<td>{{ tick.softSleeper }}</td>
			          		<td>{{ tick.activeSleeper }}</td>
			          		<td>{{ tick.hardSleeper }}</td>
			          		<td>{{ tick.softSeat }}</td>
			          		<td>{{ tick.hardSeat }}</td>
			          		<td>{{ tick.noSeat }}</td>
			          		<td>{{ tick.other }}</td>
			          		<td>{{ tick.remark }}</td>
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
</style>
<script>

export default{
	data() {
		return {
			ticks:[
			{
				"trainNumber":"Z307",
				"departureStation":"BXP",
				"destinationStation":"XMS",
				"initialTime":"16:02",
				"arriveTime":"10:49",
				"last":"18:47",
				"businessSeat":"",
				"firstSeat":"",
				"secondSeat":"",
				"highSleeper":null,
				"softSleeper":"无",
				"activeSleeper":null,
				"hardSleeper":"无",
				"softSeat":null,
				"hardSeat":"无",
				"noSeat":"无",
				"other":null,
				"remark":null
			},
			{
				"trainNumber":"K571",
				"departureStation":"BXP",
				"destinationStation":"XMS",
				"initialTime":"16:53",
				"arriveTime":"21:58",
				"last":"29:05",
				"businessSeat":"",
				"firstSeat":"",
				"secondSeat":"",
				"highSleeper":null,
				"softSleeper":"无",
				"activeSleeper":null,
				"hardSleeper":"无",
				"softSeat":null,
				"hardSeat":"无",
				"noSeat":"无",
				"other":null,
				"remark":null
			}
			],
			tick: {
				train_date:"2017-10-22",
				from_station:"BJP",
				to_station:"XMS",
				purpose_codes:"ADULT"
			}
		}
	},
	created (){
		this.seachTick();
	},
	methods: {
    seachTick: function () {
      console.info(this.tick);
      const url = "http://localhost:8999/ticketInfo/getTicket?train_date="+this.tick.train_date+"&from_station="+this.tick.from_station+"&to_station="+this.tick.to_station+"&purpose_codes="+this.tick.purpose_codes;
		this.$http.get(url,{credientials:false}).then(response => {
			    // get body data
			    console.info(response.body);
			    this.ticks = response.body;
			  }, response => {
			    // error callback
			  });
    }
  }
}
</script>
