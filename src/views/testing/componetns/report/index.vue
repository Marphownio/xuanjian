<template>
  <div class="report">
    <el-tabs v-model="activeName">
      <el-tab-pane label="信息总览" name="first">
        <div>
          <h4>应用信息</h4>
          <div class="appContent">
            <div class="icon">
              <img
                :src="appInfo.appIcon"
                width="100px"
                height="100px"
                class="icon-img"
              />
            </div>
            <div class="appInfo">
              <div class="flex row">
                <h4>应用名称：</h4>
                <div>{{ appInfo.apkName }}</div>
              </div>
              <div class="flex row">
                <h4>APK MD5：</h4>
                <div>{{ appInfo.apkMd5 }}</div>
              </div>
              <div class="flex row">
                <h4>上传时间：</h4>
                <div>{{ appInfo.updateTime }}</div>
              </div>
              <div class="flex row">
                <h4>分析时间：</h4>
                <div>{{ appInfo.testingTime }} min</div>
              </div>
              <div class="flex row">
                <h4>签名发布者：</h4>
                <div>{{ appInfo.author }}</div>
              </div>
              <div class="flex row">
                <h4>签名时间：</h4>
                <div>{{ appInfo.authorTime }}</div>
              </div>
              <div class="flex row">
                <h4>版本号：</h4>
                <div>{{ appInfo.version }}</div>
              </div>
            </div>
          </div>
        </div>
        <div>
          <h4>信息概览</h4>
          <div class="appContent">
            <div id="echarts1" ref="echarts1"></div>
            <div id="echarts2" ref="echarts2"></div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="黑产域名分类分析报告" name="second">
        <div>
          <el-tabs tab-position="right" style="height: 100%">
            <el-tab-pane label="分发域名">
              <div class="">
                <div class="flex row">
                  <h4>定义：</h4>
                  <div>提供APP运行需要的真实域名</div>
                </div>
                <div class="flex row">
                  <h4>被分发的域名数量：</h4>
                  <div>10</div>
                </div>
                <div class="flex row">
                  <h4>fqdn：</h4>
                  <div>hh4431.com</div>
                </div>
                <div class="flex row">
                  <h4>url：</h4>
                  <div>https://hh4431.com/json/h5app.json</div>
                </div>
                <div class="flex row">
                  <h4>fqdn对应IP：</h4>
                  <div>43.132.207.146</div>
                </div>
                <div class="flex row">
                  <h4>被分发域名：</h4>
                  <div>
                    <div>{"domain":"https://m.heapi0909.com/"},</div>
                    <div>{"domain":"https://m.heapi3381.com/"},</div>
                    <div>{"domain":"https://m.9276apphj.com/"},</div>
                    <div>{"domain":"https://m.ahpjp8878.com/"},</div>
                    <div>{"domain":"https://m.qdjk5689.com/"},</div>
                    <div>{"domain":"https://m.jkqdyy111.com/"},</div>
                    <div>{"domain":"https://m.ahjpp2152.com/"},</div>
                    <div>{"domain":"https://m.heapi7755.com/"},</div>
                    <div>{"domain":"https://m.jkqdppa999.com/"},</div>
                    <div>{"domain":"https://m.6721apphj.com/"}</div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="资源域名">
              <div class="">
                <div class="flex row">
                  <h4>定义：</h4>
                  <div>
                    应用中的图片等资源存放的域名，只请求图片、视频、音乐或js等资源，不涉及需要后台处理的请求
                  </div>
                </div>
                <div class="flex row">
                  <h4>详细的资源域名：</h4>
                  <div>
                    <div>
                      <h4>1</h4>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>https://cdnx-ali-5.yuge0427.com</div>
                      </div>
                      <div class="flex row">
                        <h4>ip地址：</h4>
                        <div>120.192.66.96</div>
                      </div>
                      <div class="flex row">
                        <h4>出现次数：</h4>
                        <div>684</div>
                      </div>
                      <div class="flex row">
                        <h4>图片示例：</h4>
                        <div>
                          <el-image
                            style="
                              width: 100px;
                              height: 100px;
                              margin-right: 10px;
                            "
                            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                          ></el-image>
                          <el-image
                            style="width: 100px; height: 100px"
                            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                          ></el-image>
                        </div>
                      </div>
                    </div>
                    <div>
                      <h4>2</h4>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>https://k8partner.com</div>
                      </div>
                      <div class="flex row">
                        <h4>ip地址：</h4>
                        <div>61.4.115.171</div>
                      </div>
                      <div class="flex row">
                        <h4>出现次数：</h4>
                        <div>326</div>
                      </div>
                      <div class="flex row">
                        <h4>图片示例：</h4>
                        <div>
                          <el-image
                            style="
                              width: 100px;
                              height: 100px;
                              margin-right: 10px;
                            "
                            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                          ></el-image>
                          <el-image
                            style="width: 100px; height: 100px;margin-right"
                            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                          ></el-image>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="直播域名">
              <div class="">
                <div class="flex row">
                  <h4>定义：</h4>
                  <div>
                    直播域名是应用中提供直播服务的域名。用户在直播服务中一般观看实时的视频，长度取决于视频资源提供者和观看者，因此预先不可知。与资源域名不同的是，直播域名建立的连接时间更长，可能会持续传输数据
                  </div>
                </div>
                <div class="flex row">
                  <h4>详细的直播域名：</h4>
                  <div>
                    <div>
                      <h4>1</h4>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>pull.263zb.com</div>
                      </div>
                      <div class="flex row">
                        <h4>ip地址：</h4>
                        <div>120.192.66.96</div>
                      </div>
                      <div class="flex row">
                        <h4>时长：</h4>
                        <div>>=5min</div>
                      </div>
                      <div class="flex row">
                        <h4>直播截图：</h4>
                        <div>
                          <el-image
                            style="
                              width: 100px;
                              height: 100px;
                              margin-right: 10px;
                            "
                            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                          ></el-image>
                          <el-image
                            style="width: 100px; height: 100px"
                            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                          ></el-image>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="图片分隔域名">
              <div class="">
                <div class="flex row">
                  <h4>定义：</h4>
                  <div>
                    黑产APP中页面的图片小组件很多，常会将多个组件组合成一张图片来传输，因此需要另外的数据来表示如何将这张组合图片重新分割成小组件
                  </div>
                </div>
                <div class="flex row">
                  <h4>详细的图片分隔域名 ：</h4>
                  <div>
                    <div>
                      <h4>1</h4>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>https://gci.wethirdsite.com</div>
                      </div>
                      <div class="flex row">
                        <h4>ip地址：</h4>
                        <div>120.192.66.96</div>
                      </div>
                      <div class="flex row">
                        <h4>json文件：</h4>
                        <div>https://gci.wethirdsite.com/sadada.json</div>
                      </div>
                      <div class="flex row">
                        <h4>图片：</h4>
                        <div>
                          <el-image
                            style="
                              width: 100px;
                              height: 100px;
                              margin-right: 10px;
                            "
                            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                          ></el-image>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="页面域名">
              <div class="">
                <div class="flex row">
                  <h4>定义：</h4>
                  <div></div>
                </div>
                <div class="flex row">
                  <h4>详细的页面域名 ：</h4>
                  <div>
                    <div>
                      <h4>1</h4>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>https://gci.wethirdsite.com</div>
                      </div>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>https://gci.wethirdsite.com</div>
                      </div>
                      <div class="flex row">
                        <h4>页面内容：</h4>
                        <div>
                          <el-image
                            style="
                              width: 100px;
                              height: 100px;
                              margin-right: 10px;
                            "
                            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                          ></el-image>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="更新域名">
              <div class="">
                <div class="flex row">
                  <h4>定义：</h4>
                  <div>
                    应用打开时，会向后台发送请求，检查版本号，决定是否更新。
                  </div>
                </div>
                <div class="flex row">
                  <h4>详细的更新域名 ：</h4>
                  <div>
                    <div>
                      <h4>1</h4>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>api.007zhibo.live</div>
                      </div>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>https://gci.wethirdsite.com</div>
                      </div>
                      <div class="flex row">
                        <h4>p地址：</h4>
                        <div>https://gci.wethirdsite.com</div>
                      </div>
                      <div class="flex row">
                        <h4>出现次数：</h4>
                        <div>1</div>
                      </div>
                      <div class="flex row">
                        <h4>更新前版本号：</h4>
                        <div>v0.1.1</div>
                      </div>
                      <div class="flex row">
                        <h4>待更新版本号：</h4>
                        <div>v0.1.2</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="用户域名">
              <div class="flex row">
                <h4>定义：</h4>
                <div></div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="业务域名">
              <div class="">
                <div class="flex row">
                  <h4>定义：</h4>
                  <div>具体负责的业务</div>
                </div>
                <div class="flex row">
                  <h4>详细的更新域名 ：</h4>
                  <div>
                    <div>
                      <h4>1</h4>
                      <div class="flex row">
                        <h4>url：</h4>
                        <div>mhnz.kwshth.com</div>
                      </div>
                      <div class="flex row">
                        <h4>用户信息：</h4>
                        <div>https://gci.wethirdsite.com</div>
                      </div>
                      <div class="flex row">
                        <h4>游戏列表：</h4>
                        <div>https://gci.wethirdsite.com</div>
                      </div>
                      <div class="flex row">
                        <h4>注册/登录：</h4>
                        <div>1</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  data() {
    return {
      appInfo: {},
      activeName: "first",
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      this.appInfo = {
        appIcon:
          "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        apkName: "凯发娱乐",
        apkMd5: "a60wad89wndwa0dawdwah0d8wahd8aw",
        updateTime: "2023-01-27 1:47",
        testingTime: "10",
        author: "5ad6d6d6d6d",
        authorTime: "1981-01-02 01:01:02",
        version: "v0.1.1",
      };
      this.innitEcharts1();
      this.innitEcharts2();
    },
    innitEcharts1() {
      const chartDom = this.$refs.echarts1;
      const myChart = echarts.init(chartDom);
      let option;

      option = {
        tooltip: {
          trigger: "item",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: [
              { value: 1048, name: "正常域名" },
              { value: 735, name: "黑产域名" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };

      option && myChart.setOption(option);
    },
    innitEcharts2() {
      const chartDom = this.$refs.echarts2;
      const myChart = echarts.init(chartDom);
      let option;

      option = {
        tooltip: {
          trigger: "item",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: [
              { value: 1048, name: "分发域名" },
              { value: 735, name: "资源域名" },
              { value: 580, name: "直播域名" },
              { value: 484, name: "备选域名" },
              { value: 300, name: "图片分隔域名" },
              { value: 300, name: "页面域名" },
              { value: 300, name: "更新域名" },
              { value: 300, name: "用户域名" },
              { value: 300, name: "业务域名" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };

      option && myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.flex {
  display: flex;
}
.report {
  padding: 10px;
}
.appContent {
  display: flex;
  align-items: center;
}
.icon {
  margin: 0 10px;
}
.icon-img {
  border-radius: 20px;
}
.appInfo {
  flex: 1;
  background-color: #88888850;
  padding: 10px;
  border-radius: 10px;
}
.row {
  align-items: center;
  width: 100%;
}
h4 {
  margin: 5px;
  min-width: 5rem;
  max-width: 12rem;
}
#echarts1,
#echarts2 {
  width: 400px;
  height: 400px;
}
</style>
