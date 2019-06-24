package cn.mirrorming.core.social.qq.api;

/**
 * @author: mirrorming
 * @create: 2019-06-23 12:30
 **/

public class ReadMe_QQAPI {

    /**
     * 使用Authorization_Code获取Access_Token
     *              本步骤的作用：
     *              通过用户验证登录和授权，获取Access Token，为下一步获取用户的OpenID做准备；
     *              同时，Access Token是应用在调用OpenAPI访问和修改用户数据时必须传入的参数。
     *              移动端应用可以直接获得AccessToken，请参考使用Implicit_Grant方式获取Access_Token
     *
     *
     *
     * 1. 简介
     *              即server-side模式，是OAuth2.0认证的一种模式，又称Web Server Flow；
     *              适用于需要从web server访问的应用，例如Web网站。
     *              其授权验证流程示意图如下（图片来源：OAuth2.0协议草案V21的4.1节 ）
     *              OAuth_guide_V2_1.png
     *              对于应用而言，需要进行两步：
     *                  1. 获取Authorization Code；
     *                  2. 通过Authorization Code获取Access Token
     *
     * 2. 过程详解
     *              Step1：获取Authorization Code
     *              请求地址：
     *                  PC网站：https://graph.qq.com/oauth2.0/authorize
     *              请求方法：
     *                  GET
     *              请求参数：
     *
     *                  参数	        是否必须	含义
     *                  response_type	必须	授权类型，此值固定为“code”。
     *                  client_id	    必须	申请QQ登录成功后，分配给应用的appid。
     *                  redirect_uri	必须	成功授权后的回调地址，必须是注册appid时填写的主域名下的地址，建议设置为网站首页或网站的用户中心。注意需要将url进行URLEncode。
     *                  state	        必须	client端的状态值。用于第三方应用防止CSRF攻击，成功授权后回调时会原样带回。请务必严格按照流程检查用户与state参数状态的绑定。
     *                  scope	        可选	请求用户授权时向用户显示的可进行授权的列表。
     *
     *              可填写的值是API文档中列出的接口，以及一些动作型的授权（目前仅有：do_like），如果要填写多个接口名称，请用逗号隔开。
     *              例如：scope=get_user_info,list_album,upload_pic,do_like
     *              不传则默认请求对接口get_user_info进行授权。
     *              建议控制授权项的数量，只传入必要的接口名称，因为授权项越多，用户越可能拒绝进行任何授权。
     *              display	可选	仅PC网站接入时使用。
     *              用于展示的样式。不传则默认展示为PC下的样式。
     *              如果传入“mobile”，则展示为mobile端下的样式。
     *
     * 返回说明：
     *              1. 如果用户成功登录并授权，则会跳转到指定的回调地址，并在redirect_uri地址后带上Authorization Code和原始的state值。如：
     *              PC网站：http://graph.qq.com/demo/index.jsp?code=9A5F************************06AF&state=test
     *              注意：此code会在10分钟内过期。
     *              2. 如果用户在登录授权过程中取消登录流程，对于PC网站，登录页面直接关闭；对于WAP网站，同样跳转回指定的回调地址，并在redirect_uri地址后带上usercancel参数和原始的state值，其中usercancel值为非零，如：
     *              http://open.z.qq.com/demo/index.jsp?usercancel=1&state=test
     *              错误码说明：
     *              接口调用有错误时，会返回code和msg字段，以url参数对的形式返回，value部分会进行url编码（UTF-8）。
     *              PC网站接入时，错误码详细信息请参见：100000-100031：PC网站接入时的公共返回码。
     *
     * Step2：通过Authorization Code获取Access Token
     *              请求地址：
     *                  PC网站：https://graph.qq.com/oauth2.0/token
     *              请求方法：
     *                  GET
     *              请求参数：
     *
     *                  参数	        是否必须	    含义
     *
     *                  grant_type	    必须	    授权类型，在本步骤中，此值为“authorization_code”。
     *
     *                  client_id	    必须	    申请QQ登录成功后，分配给网站的appid。
     *
     *                  client_secret	必须	    申请QQ登录成功后，分配给网站的appkey。
     *
     *                  code	        必须	    上一步返回的authorization code。
     *                                              如果用户成功登录并授权，则会跳转到指定的回调地址，并在URL中带上Authorization Code。
     *                                              例如，回调地址为www.qq.com/my.php，则跳转到：
     *                                              http://www.qq.com/my.php?code=520DD95263C1CFEA087******
     *                                              注意此code会在10分钟内过期。
     *
     *                  redirect_uri	必须	    与上面一步中传入的redirect_uri保持一致。
     *
     * 返回说明：
     *
     *              如果成功返回，即可在返回包中获取到Access Token。 如：
     *
     *                  access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
     *
     * 参数说明	描述
     *              access_token	授权令牌，Access_Token。
     *              expires_in	该access token的有效期，单位为秒。
     *              refresh_token	在授权自动续期步骤中，获取新的Access_Token时需要提供的参数。
     *
     *              错误码说明：
     *              接口调用有错误时，会返回code和msg字段，以url参数对的形式返回，value部分会进行url编码（UTF-8）。
     *              PC网站接入时，错误码详细信息请参见：100000-100031：PC网站接入时的公共返回码。
     *
     *
     * Step3：（可选）权限自动续期，获取Access Token
     *              Access_Token的有效期默认是3个月，过期后需要用户重新授权才能获得新的Access_Token。本步骤可以实现授权自动续期，避免要求用户再次授权的操作，提升用户体验。
     *              请求地址：
     *                  PC网站：https://graph.qq.com/oauth2.0/token
     *              请求方法：
     *                  GET
     *              请求参数：
     *
     *                  参数	        是否必须	    含义
     *                  grant_type	    必须	    授权类型，在本步骤中，此值为“refresh_token”。
     *                  client_id	    必须	    申请QQ登录成功后，分配给网站的appid。
     *                  client_secret	必须	    申请QQ登录成功后，分配给网站的appkey。
     *                  refresh_token	必须	    在Step2中，返回的refres_token。
     *
     *              返回说明：
     *                  如果成功返回，即可在返回包中获取到Access Token。 如：
     *                      access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14。
     *
     *
     *              参数说明	        描述
     *              access_token	授权令牌，Access_Token。
     *              expires_in	    该access token的有效期，单位为秒。
     *              refresh_token	在授权自动续期步骤中，获取新的Access_Token时需要提供的参数。
     *
     *              错误码说明：
     *                  接口调用有错误时，会返回code和msg字段，以url参数对的形式返回，value部分会进行url编码（UTF-8）。
     *                  PC网站接入时，错误码详细信息请参见：100000-100031：PC网站接入时的公共返回码。
     *
     * 3. 快速上手
     *              详见：开发攻略_Server-side。
     *
     * 4. 其他资源
     *              移动端应用可以直接获得AccessToken，请参考使用Implicit_Grant方式获取Access_Token 。
     */


    /**
     * 获取用户OpenID_OAuth2.0
     *
     *
     * 本步骤的作用：
     *              通过输入在上一步获取的Access Token，得到对应用户身份的OpenID。
     *              OpenID是此网站上或应用中唯一对应用户身份的标识，网站或应用可将此ID进行存储，便于用户下次登录时辨识其身份，或将其与用户在网站上或应用中的原有账号进行绑定。
     *
     * 1 请求地址
     *              PC网站：https://graph.qq.com/oauth2.0/me
     *
     * 2 请求方法
     *              GET
     *
     * 3 请求参数
     *              参数	                是否必须	        含义
     *              access_token	        必须	        在Step1中获取到的access token。
     *
     * 4 返回说明
     *              PC网站接入时，获取到用户OpenID，返回包如下：
     *
     *              callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
     *
     *              openid是此网站上唯一对应用户身份的标识，网站可将此ID进行存储便于用户下次登录时辨识其身份，或将其与用户在网站上的原有账号进行绑定。
     *
     * 5 错误码说明
     *              接口调用有错误时，会返回code和msg字段，以url参数对的形式返回，value部分会进行url编码（UTF-8）。
     */
}