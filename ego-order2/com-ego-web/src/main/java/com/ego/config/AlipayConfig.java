package com.ego.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092700606787";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCcGcXWJ6svwIn662NTq7PbQ6BOH8RIqmek5cPegQ7VV3ElRrXKSh4h+g0Zp954JOV628OeMVHxxi9CdgfExZ7f/ws3XcILtDDzDoS4qK6u3Uj99+VvDe7khciYHRbpkdPbqcwJHoY1wJgScosFhFalI/c1aEQX3AzfXugVJSSCcM8QbKkYlweWm9/nmFeo1wcjOppRRfsvcVWATfabcj+fZgRmpXRATyMYcAijFa9jAhea2y9PLpI4BTRoAQGV2zyo+a8GP1JrJbtwuOzq5KQMbLusxdiWdBvqkpcxlTgO3StMyhTOjepqZY+wqE3z8C/kx9CDJwnNlb85QOSWHVIFAgMBAAECggEAe06ciWtyfVkRZaU1I+35OBX2dl//lAsPriFVlNjLl9jn5rMxfOcJYHt+h6qkQLGcmG0q3DZHbb3IrS5BOr2En5p/CLBGTSp8lDL+wkN1IWppczyz/wK5l8jaSNaJt91axiUdqIJ1L9bACapziNsd+nsrlwOS7moDvRriJ3ncmjA4XNyDT1fIbncLpKxuYVVOM2tnTmk9CcDf0IzsG5NHN6B4IODWYie5fWIWR5yI/Pu3Adn8GFAflaYp7bvhXZFzvyMCUgkMx8kyav9tYhoxNu7U6nZKlgfPFyuAIlTSJCieF5hceURdDh5/i8tDcv9pfwhv6A8EFABPOQMHRp5NoQKBgQDNv+nNPrXrGdcMeTPKxQsxpeHCqrood/FZMXbfUlvsn9BZvXosNp68lRB6ZI4yDi6xsY173KeCynR9FkC9gZDn42DmAXOBUCrGjOSIgFWb0axvyvH+DgEs0bqKaoONTt1lJrIbYO0hJq5Wly0Nb7G8f57kDgVYWvNI1ve7uH1reQKBgQDCOafhqYf7oFdVCs8hcKwViMWlBOjmxiC4r/UMuvWz6W1s0kHyvGH1fmA8GoZ6Unu49edN3+u3lx1jzZDlmNaV+/Q2PqGuVmy++mZMgRw3byxgROP+eNSfaus68MBCY89SPGTAMPF7lx78j5R3MVD1JqKScgWFQ4QtiXHAiC+r7QKBgFOBi7wAsscLwstOOeYXWTa6U8m1jz6zh6IiwFNdUu/T4TU7AOYG+SAjBAAY5dmNBgiavojCAr7Dusm2CWqgtpZXI9HWTvHx/pp9PpfkNlq6m9j3roXlWza8nJBnMKNemd7e+LTB/xxQXbvJOcDVT3bo+S0oP6UfrNQoY32uIEFJAoGBAJ0F7+xJG18ban+elYb7iKSARwn6xjCMzbeCyiLYo/woJAdulVKharEG0tbEuyP5u3z1kWMsPW3YBjLB5vs4bYQkhnO0fxJrXM+psTr4xs8qWj4adBkvpvabgkHMkiMafmqF2Q5og1LgAnU9v1H91O3dpmGvPTdYNmx6SjTV5XShAoGAaTsDc7rlstgEeWRPktrXTZGxCJDvJsUG52Whx99v1ZqsWU6gsNZ2Cn9An/rAQvpCUKPB4Cpaa5Q9Uhl0vOJCv5YcSwoiAjS9qiNlVFtT8B1irN8L2R9iU7acpzE/yH1n5wxiPGksnsbtEdGHmpBo9o4UGhg9OtdbLQqz3iLqIDk=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnBnF1ierL8CJ+utjU6uz20OgTh/ESKpnpOXD3oEO1VdxJUa1ykoeIfoNGafeeCTletvDnjFR8cYvQnYHxMWe3/8LN13CC7Qw8w6EuKiurt1I/fflbw3u5IXImB0W6ZHT26nMCR6GNcCYEnKLBYRWpSP3NWhEF9wM317oFSUkgnDPEGypGJcHlpvf55hXqNcHIzqaUUX7L3FVgE32m3I/n2YEZqV0QE8jGHAIoxWvYwIXmtsvTy6SOAU0aAEBlds8qPmvBj9SayW7cLjs6uSkDGy7rMXYlnQb6pKXMZU4Dt0rTMoUzo3qamWPsKhN8/Av5MfQgycJzZW/OUDklh1SBQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:9091/ego-portal-web/index";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

