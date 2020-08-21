var api = require('./api.js');

function formatTime(date) {
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()

    var hour = date.getHours()
    var minute = date.getMinutes()
    var second = date.getSeconds()


    return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}

/**
 * 封封微信的的request
 */
function request(url, data = {}, method = "POST", header = "application/x-www-form-urlencoded") {
    wx.showLoading({
        title: '加载中...',
    });
    return new Promise(function (resolve, reject) {
        wx.request({
            url: url,
            data: data,
            method: method,
            header: {
                'Content-Type': header,
                'X-Nideshop-Token': wx.getStorageSync('token')
            },
            success: function (res) {
                wx.hideLoading();
                if (res.statusCode == 200) {
                    if (res.data.errno == 401) {
                        wx.navigateTo({
                            url: '/pages/auth/btnAuth/btnAuth',
                        })
                    } else {
                        resolve(res.data);
                    }
                } else {
                    reject(res.errMsg);
                }

            },
            fail: function (err) {
                wx.hideLoading();
                reject(err)
            }
        })
    });
}

/**
 * 检查微信会话是否过期
 */
function checkSession() {
    return new Promise(function (resolve, reject) {
        wx.checkSession({
            success: function () {
                resolve(true);
            },
            fail: function () {
                reject(false);
            }
        })
    });
}

/**
 * 调用微信登录
 */
function login() {
    return new Promise(function (resolve, reject) {
        wx.login({
            success: function (res) {
                if (res.code) {
                    resolve(res);
                } else {
                    reject(res);
                }
            },
            fail: function (err) {
                reject(err);
            }
        });
    });
}

function redirect(url) {

    //判断页面是否需要登录
    if (false) {
        wx.redirectTo({
            url: '/pages/auth/login/login'
        });
        return false;
    } else {
        wx.redirectTo({
            url: url
        });
    }
}

function showErrorToast(msg) {
    wx.showToast({
        title: msg,
        image: '/static/images/icon_error.png'
    })
}

function showSuccessToast(msg) {
    wx.showToast({
        title: msg,
    })
}

module.exports = {
    formatTime,
    request,
    redirect,
    showErrorToast,
    showSuccessToast,
    checkSession,
    login,
    requestJson,
    fileUpload,
    downloadFile,
    base64_decode,
    utf8_decode,
    base64_encode,
}



/**
 * 封封微信的的request
 */
function requestJson(url, data = {}, method = "POST", header = "application/json;charset=UTF-8") {
    wx.showLoading({
        title: '加载中...',
    });
    return new Promise(function (resolve, reject) {
        wx.request({
            url: url,
            data: JSON.stringify(data),
            method: method,
            header: {
                'Content-Type': header,
                'X-Nideshop-Token': wx.getStorageSync('token')
            },
            success: function (res) {
                wx.hideLoading();
                if (res.statusCode == 200) {
                    if (res.data.errno == 401) {
                        wx.navigateTo({
                            url: '/pages/auth/btnAuth/btnAuth',
                        })
                    } else {
                        resolve(res.data);
                    }
                } else {
                    reject(res.errMsg);
                }

            },
            fail: function (err) {
                reject(err)
            }
        })
    });
}

/**
 * 封封微信的的request
 */
function fileUpload(url, data, userId,method = "",) {
    wx.showLoading({
        title: '上传中...',
    });
    return new Promise(function (resolve, reject) {
        wx.uploadFile({
            filePath: data.path,
            name: 'file',
            url: url,
            header: {
                userId:userId
            },
            success: function (res) {
                wx.hideLoading();
                if (res.statusCode == 200) {
                    resolve(JSON.parse(res.data));
                }else{
                    reject(res.errMsg);
                }
            },
            fail: function (err) {
                wx.hideLoading();
                reject(err)
            }
        });
    }).catch((e) => {});
}

/**
 * 封装微信下载功能
*/
function   downloadFile(url,filePath){
    wx.showLoading({
        title: '下载中...',
    });
    return new Promise(function (resolve, reject) {
        wx.downloadFile({
        url: url,
        filePath: filePath,
        // header: header,
        timeout: 0,
        success: function (res) {
            wx.hideLoading();
            if (res.statusCode == 200) {
                resolve(JSON.parse(res.data));
            }else{
                reject(res.errMsg);
            }
        },
        fail: function (err) {
            wx.hideLoading();
            reject(err)
        }
        });
    })
}

function base64_encode (str) { // 编码，配合encodeURIComponent使用
    var c1, c2, c3;
    var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var i = 0, len = str.length, strin = '';
    while (i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if (i == len) {
            strin += base64EncodeChars.charAt(c1 >> 2);
            strin += base64EncodeChars.charAt((c1 & 0x3) << 4);
            strin += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if (i == len) {
            strin += base64EncodeChars.charAt(c1 >> 2);
            strin += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
            strin += base64EncodeChars.charAt((c2 & 0xF) << 2);
            strin += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        strin += base64EncodeChars.charAt(c1 >> 2);
        strin += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
        strin += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
        strin += base64EncodeChars.charAt(c3 & 0x3F)
    }
    return strin
}

function base64_decode (input) { // 解码，配合decodeURIComponent使用
    var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var output = "";
    var chr1, chr2, chr3;
    var enc1, enc2, enc3, enc4;
    var i = 0;
    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
    while (i < input.length) {
        enc1 = base64EncodeChars.indexOf(input.charAt(i++));
        enc2 = base64EncodeChars.indexOf(input.charAt(i++));
        enc3 = base64EncodeChars.indexOf(input.charAt(i++));
        enc4 = base64EncodeChars.indexOf(input.charAt(i++));
        chr1 = (enc1 << 2) | (enc2 >> 4);
        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
        chr3 = ((enc3 & 3) << 6) | enc4;
        output = output + String.fromCharCode(chr1);
        if (enc3 != 64) {
            output = output + String.fromCharCode(chr2);
        }
        if (enc4 != 64) {
            output = output + String.fromCharCode(chr3);
        }
    }
    return utf8_decode(output);
}

function utf8_decode (utftext) { // utf-8解码
    var string = '';
    let i = 0;
    let c = 0;
    let c1 = 0;
    let c2 = 0;
    while (i < utftext.length) {
        c = utftext.charCodeAt(i);
        if (c < 128) {
            string += String.fromCharCode(c);
            i++;
        } else if ((c > 191) && (c < 224)) {
            c1 = utftext.charCodeAt(i + 1);
            string += String.fromCharCode(((c & 31) << 6) | (c1 & 63));
            i += 2;
        } else {
            c1 = utftext.charCodeAt(i + 1);
            c2 = utftext.charCodeAt(i + 2);
            string += String.fromCharCode(((c & 15) << 12) | ((c1 & 63) << 6) | (c2 & 63));
            i += 3;
        }
    }
    return string;
}