// miniprogram/pages/User/userrelation/userrelation.js
const api = require('../../../config/api.js');
const util = require('../../../config/util.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    user:{
      username:"",
      mobile: ""
    },
  emsg:"手机号格式错误",
  
  errormsg: ""
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
  usernameBlur: function(e){
    var inputname=e.detail.value;
    this.data.user.username=inputname;
    this.setData(this.data);
  },
  phoneBlur: function(e){
    var inputphone=e.detail;
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    this.data.user.mobile=inputphone;
    if (myreg.test(inputphone)) {
      this.data.errormsg=""; 
    } else {
      this.data.errormsg=this.data.emsg
    }
    this.setData(this.data)
  },
  takeCode:function(){

  },
  syncInfo:function(){
   if(this.data.errormsg=="" ){
    var user=this.data.user;
    wx.login({
      success: res => {
        if(res.errMsg=="login:ok"){
          var code=res.code;
          var param={};
          param["code"]=code;
          param["user"]=user;
          console.log(JSON.stringify(param))
          util.requestJson(api.WxRelation,param).then(function (res) {
            if (res.code === 0) {
                 //存储用户信息
            wx.setStorageSync('user',res.user);
            wx.navigateBack();//返回上一页面
          }
        });
      }
    }
  });
    
   }
  }

})