// miniprogram/pages/User/userrelation/userrelation.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  username:"",
  emsg:"手机号格式错误",
  phone: "",
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
    this.data.username=inputname;
    this.setData(this.data);
  },
  phoneBlur: function(e){
    var inputphone=e.detail.value;
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (myreg.test(inputphone)) {
              this.data.errormsg="";
              this.data.phone=inputphone;
              this.setData(this.data)
            } else {
               this.data.errormsg=this.data.emsg
               this.setData(this.data)
            }
  }
})