package com.yue.yueaiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import java.util.HashMap;
import java.util.Map;

public class HttpAiInvoke {
    // 替换成你的阿里云API KEY
    private static final String DASHSCOPE_API_KEY = TestApiKey.API_KEY;
    private static final String REQUEST_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

    public static void main(String[] args) {
        // 1. 构建请求体
        Map<String, Object> bodyMap = buildRequestBody();
        String jsonBody = JSONUtil.toJsonStr(bodyMap);

        String responseBody = "";
        try (HttpResponse response = HttpRequest.post(REQUEST_URL)
                // 设置请求头
                .header("Authorization", "Bearer " + DASHSCOPE_API_KEY)
                .header("Content-Type", "application/json")
                // 设置请求体
                .body(jsonBody)
                // 设置超时时间：连接超时30秒，读取超时60秒
                .timeout(60 * 1000)
                // 发送请求
                .execute()) {

            // 判断请求是否成功
            if (response.isOk()) {
                responseBody = response.body();
                System.out.println("请求成功，返回结果：\n" + responseBody);
            } else {
                System.err.println("请求失败，状态码：" + response.getStatus() + "，错误信息：" + response.body());
            }
        } catch (Exception e) {
            System.err.println("调用通义千问接口异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 抽离请求体构建逻辑，便于复用
     */
    private static Map<String, Object> buildRequestBody() {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("model", "qwen-plus");

        Map<String, Object> input = new HashMap<>();
        Map<String, String> systemMsg = new HashMap<>(2);
        systemMsg.put("role", "system");
        systemMsg.put("content", "You are a helpful assistant.");

        Map<String, String> userMsg = new HashMap<>(2);
        userMsg.put("role", "user");
        userMsg.put("content", "你是谁？");

        input.put("messages", new Object[]{systemMsg, userMsg});
        bodyMap.put("input", input);

        Map<String, String> parameters = new HashMap<>(1);
        parameters.put("result_format", "message");
        bodyMap.put("parameters", parameters);

        return bodyMap;
    }
}