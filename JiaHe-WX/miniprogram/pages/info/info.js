// miniprogram/pages/info/info.js
const util = require('../../config/util.js');
const api = require('../../config/api.js');


Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: '../index/user-unlogin.png',
    userInfo: {
      nickName:'游客'
    },
    userMsg:"未登录",
    gradientColor: {
      '0%': '#ffd01e',
      '100%': '#ee0a24',
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getIndexData();
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
    console.log(user)
    if(user!=null&&user!=""){
      this.setData({
        userMsg: user.username,
      })
    }else{
      this.setData({
        userMsg: "未登录",
      })
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

  },
/***********************************************************************/
  getIndexData: function(){
     // 获取用户信息
     wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              console.log(res)
              this.setData({
                avatarUrl: res.userInfo.avatarUrl,
                userInfo: res.userInfo
              })
            }
          })
        }
      }
    })

  },
  userInfo: function(event){
    // console.log(event.detail);
    // wx.navigateTo({
    //   url:'../User/userinfo/userinfo?id=1',//跳转页面的路径，可带参数？隔开，不同参数用 & 分隔；相对路径，不需要.wxml后缀
    //   success:function(){
    //     //成功后的回调；  
    //   },        
    //   fail:function(){
    //     //失败后的回调；
    //   },          
    //   complete:function(){
    //     //结束后的回调(成功，失败都会执行)
    //   } 
    // })     
  },
  userlogin: function(){
    wx.navigateTo({
      url:'../User/loginauth/loginauth',//跳转页面的路径，可带参数？隔开，不同参数用 & 分隔；相对路径，不需要.wxml后缀
      success:function(){
        //成功后的回调；  
      },        
      fail:function(){
        //失败后的回调；
      },          
      complete:function(){
        //结束后的回调(成功，失败都会执行)
      }      
    })
  },
  btnAuth: function(){
   if(this.userMsg=="欢迎访问"){
     console.log("欢迎访问")
   }else{
    this.userlogin();
   }
  }
})
