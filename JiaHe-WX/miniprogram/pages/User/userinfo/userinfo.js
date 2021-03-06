// miniprogram/pages/User/userinfo/userinfo.js

const api = require('../../../config/api.js');
const util = require('../../../config/util.js');
import Toast from '../../../miniprogram_npm/@vant/weapp/toast/toast';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    console.log(options);//     var userId=options.id; 可以打印一下option看查看参数
   


  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var _this=this;
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
      util.request(api.findUser+user.userId).then(function (res) {
        if (res.code === 0) {
          _this.data.userInfo = res.user
          _this.setData( _this.data);
        }
      });
  }
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

  }
})