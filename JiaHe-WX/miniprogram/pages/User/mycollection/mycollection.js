// miniprogram/pages/User/mycollection.js

const api = require('../../../config/api.js');
const util = require('../../../config/util.js');
import Toast from '../../../miniprogram_npm/@vant/weapp/toast/toast';
import { WxRelation } from '../../../config/api.js';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fileList: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var user=wx.getStorageSync('user');
    if(user==null||user==""){
      Toast({
        type: 'fail',
        message: '用户请先登录',
        onClose: () => {
          wx.navigateBack();//返回上一页面
        },
      });
    }else{
      var _this=this;
      util.request(api.getImgList+user.userId).then(function (res) {
        if (res.code === 0) {
          var fileList=res.fileList;
          var newList=[];
          for(var i=0;i<fileList.length;i++){
            var file=fileList[i];
            file.url=api.img+file.name;
            newList.push(file)
          }
        _this.setData({
          fileList: newList
        })
      }
    });
    }
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {


  },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //加载图片后执行
  readIMG:function(event){
    var _this=this;
    var user=wx.getStorageSync('user');
    var file=event.detail.file;
    if(file!=null&&file!=""){
      util.fileUpload(api.fileUpload,file,user.userId).then((res)=>{
        if (res.code == 0) {
          var fileList=_this.data.fileList;
          fileList.push({isImage: true, name: res.fileName, deletable: true, url: api.img+res.fileName});
          _this.setData({
            fileList: fileList
          })
        }
      });
    }else{
      console.log("未添加图片")
    }
  },
  // 删除图片
  delIMG: function(event){
    var i=event.detail.index;
    var _this=this;
    var fileList=_this.data.fileList;
    var file=fileList[i];
    util.request(api.fileDel+file.name).then(function (res) {
      if(res.code==0){
        fileList.splice(i,1);
        _this.setData({
          fileList: fileList
        });
      }
    });
  }
})