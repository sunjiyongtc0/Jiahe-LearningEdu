package sjy.jiahe.utils;

public class SmsUtil {

//    /**
//     * 指定模板ID群发
//     * 签名参数未提供或者为空时，会使用默认签名发送短信
//     *
//     * @param appid
//     * @param appkey
//     * @param phoneNumbers
//     * @param templateId
//     * @param params
//     * @param smsSign
//     * @return
//     */
//    public static SmsMultiSenderResult crSendSms(int appid, String appkey, String[] phoneNumbers, int templateId, String[] params, String smsSign) {
//        SmsMultiSenderResult result = new SmsMultiSenderResult();
//        try {
//            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
//            result = msender.sendWithParam("86", phoneNumbers,
//                    templateId, params, smsSign, "", "");
//        } catch (Exception e) {
//            // 网络IO错误
//            e.printStackTrace();
//        }
//        return result;
//    }


    /**
     * 指定模板ID单发短信
     * 签名参数未提供或者为空时，会使用默认签名发送短信
     *
     * @param appid
     * @param appkey
     * @param phoneNumber
     * @param templateId
     * @param params
     * @param smsSign
     * @return
     */
//    public static SmsSingleSenderResult crSendSms(int appid, String appkey, String phoneNumber, int templateId, String[] params, String smsSign) {
//        SmsSingleSenderResult result = new SmsSingleSenderResult();
//        try {
//            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
//            result = ssender.sendWithParam("86", phoneNumber,
//                    templateId, params, smsSign, "", "");
//        } catch (Exception e) {
//            // 网络IO错误
//            e.printStackTrace();
//        }
//        return result;
//    }
}
