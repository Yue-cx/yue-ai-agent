package com.yue.yueaiagent.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * 网页抓取工具（优化版：仅提取纯文本）
 */
public class WebScrapingTool {

    @Tool(description = "Scrape content from a web page URL to get its readable text content")
    public String scrapeWebPage(@ToolParam(description = "The URL of the web page to scrape") String url) {
        try {
            // 1. 增加 User-Agent 模拟浏览器，并设置超时，防止被某些网站拦截
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10000)
                    .get();

            // 2. 移除干扰元素：脚本、样式表、头部、页脚、导航栏
            doc.select("script, style, header, footer, nav, noscript, iframe").remove();

            // 3. 获取网页标题和纯文本
            String title = doc.title();
            String text = doc.body().text(); // 获取 Body 下的所有可视文本

            // 4. 进行长度截断，防止 Token 溢出（建议保留前 4000-5000 字符）
            String result = "Title: " + title + "\n\nContent:\n" + text;
            if (result.length() > 5000) {
                result = result.substring(0, 5000) + "... [Content Truncated]";
            }

            return result;
        } catch (Exception e) {
            return "Error scraping web page: " + e.getMessage();
        }
    }
}