// miniprogram/pages/User/scanCode/scanCode.js
const util = require('../../../config/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    result:"",
    scanType:"",
    charSet:"",
    path:"",
    res:{},
    rawData:""

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
  getScancode:function(){
    var _this = this;
    // 允许从相机和相册扫码
    wx.scanCode({
      success: (res) => {
        console.log(res)
        var result = res.result;
        var scanType = res.scanType;
        var charSet = res.charSet;
        var path = res.path;
        var res = res;
        var rawData=res.rawData;
        _this.setData({
          result: result,
          scanType: scanType,
          charSet: charSet,
          path: path,
          res:res,
          rawData:util.base64_decode(rawData)
        })
      }
    })
  }
  
})