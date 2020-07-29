const API_BASE_URL = 'http://192.168.100.145/jiahe-web/api/';

module.exports = {
  userList: API_BASE_URL + 'user/list', //用户列表
  findUser: API_BASE_URL + 'user/finduser/', //获取用户
  IndexUrlBanner: API_BASE_URL + 't2', //首页banner
}