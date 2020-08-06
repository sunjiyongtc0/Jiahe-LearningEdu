const API_BASE_URL = 'http://192.168.100.145/jiahe-web/api/';
const IMG_BASE_URL='http://192.168.100.145/jiahe-web/statics/img/'

module.exports = {
  img: IMG_BASE_URL,
  userList: API_BASE_URL + 'user/list', //用户列表
  findUser: API_BASE_URL + 'user/finduser/', //获取用户
  IndexUrlBanner: API_BASE_URL + 't2', //首页banner
  WxSync: API_BASE_URL+'vx/loginUser/',
  WxRelation: API_BASE_URL+'vx/relationUser',
  getImgList:API_BASE_URL+'utils/getImgs/',
  fileUpload:API_BASE_URL+'utils/fileUpload',
  fileDel:API_BASE_URL+'utils/fileDel/'
}