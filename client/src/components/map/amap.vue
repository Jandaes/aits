<template>
  <div>
    <div class="amap-page-container">
      <el-amap vid="amapDemo" :zoom="zoom" :center="center" class="amap-demo" :plugin="plugin">
        <el-amap-info-window  :position="mywindow.position" :content="mywindow.content" :visible="mywindow.visible" :events="mywindow.events"></el-amap-info-window>
        <el-amap-marker :position="marker.position" :events="marker.events" :visible="marker.visible" :draggable="marker.draggable"></el-amap-marker>
        <el-amap-circle :center="circle.center" :radius="circle.radius" :fillOpacity="circle.fillOpacity" :events="circle.events" :strokeColor="circle.strokeColor" :strokeStyle="circle.strokeStyle" :fillColor="circle.fillColor"></el-amap-circle>

      </el-amap>
    </div>
  </div>
</template>

<style>
  .amap-page-container {
    height: 500px;
  }
</style>


<script>
  export default {
    data () {
      return {
        zoom: 15,
        center: [121.5273285, 31.21515044],
        circle: {
          clickable: true,
          center: [121.5273285, 31.21515044],
          radius: 200,
          fillOpacity: 0.3,
          strokeStyle: 'dashed',
          fillColor: '#FFFF00',
          strokeColor: '#00BFFF'
        },
        marker: {
          position: [121.5273285, 31.21515044],
          events: {
            click: () => {
              if (this.mywindow.visible === true) {
                this.mywindow.visible = false
              } else {
                this.mywindow.visible = true
              }
            },
            dragend: (e) => {
              this.markers[0].position = [e.lnglat.lng, e.lnglat.lat]
            }
          },
          visible: true,
          draggable: false
        },
        mywindow: {
          position: [121.5273285, 31.21515044],
          content: '<h4>该点数据信息</h4><div class="text item">Information A: ...</div><div class="text item">Information B: ...</div>',
          visible: true,
          events: {
            close () {
              this.mywindow.visible = false
            }
          }
        },
        plugin: {
          pName: 'Scale',
          events: {
            init (instance) {
              console.log(instance)
            }
          }
        }
      }
    }
  }
</script>
