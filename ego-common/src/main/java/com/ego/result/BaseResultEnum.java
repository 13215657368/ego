package com.ego.result;

public enum BaseResultEnum {
//  自定义枚举类
SUCESS(200, " 成功"),
ERROR(400, " 失败"),
PASS_ERROR_01(501, " 两次输入的密码不一致"),
PASS_ERROR_02(502, " 输入的原始密码不正确"),
PASS_ERROR_03(503, " 输入的验证码不正确"),
PASS_ERROR_04(504, " 用户名或者密码错误");
private Integer code;
private String message;
private BaseResultEnum(Integer code, String message) {
this.code = code;
this.message = message;
}
public Integer getCode() {
return code;
}
public void setCode(Integer code) {
this.code = code;
}
public String getMessage() {
return message;
}
public void setMessage(String message) {
this.message = message;
}
}