package com.yue.yueaiagent.tools;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.yue.yueaiagent.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.File;
import java.io.IOException;

/**
 * PDF 生成工具（方案一：系统字体兼容版）
 */
public class PDFGenerationTool {

    @Tool(description = "Generate a PDF file with given content", returnDirect = false)
    public String generatePDF(
            @ToolParam(description = "Name of the file to save the generated PDF") String fileName,
            @ToolParam(description = "Content to be included in the PDF") String content) {

        String fileDir = FileConstant.FILE_SAVE_DIR + "/pdf";
        String filePath = fileDir + "/" + fileName;

        try {
            // 1. 确保目录存在
            FileUtil.mkdir(fileDir);

            // 2. 初始化 PDF 写入器
            try (PdfWriter writer = new PdfWriter(filePath);
                 PdfDocument pdf = new PdfDocument(writer);
                 Document document = new Document(pdf)) {

                // 3. 寻找系统中文字体（按优先级尝试：微软雅黑 -> 宋体 -> 苹方）
                String[] fontPaths = {
                        "C:/Windows/Fonts/msyh.ttc,0",   // Windows 微软雅黑
                        "C:/Windows/Fonts/simsun.ttc,0",  // Windows 宋体
                };

                PdfFont font = null;
                for (String path : fontPaths) {
                    File fontFile = new File(path.split(",")[0]); // 处理 .ttc 索引
                    if (fontFile.exists()) {
                        try {
                            // 使用 IDENTITY_H 编码以支持中文显示
                            font = PdfFontFactory.createFont(path, PdfEncodings.IDENTITY_H,
                                    PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
                            break;
                        } catch (Exception ignored) {
                            // 当前字体文件不可读取则尝试下一个
                        }
                    }
                }

                // 4. 应用字体（如果都没找到，则使用 iText 默认字体，但中文会乱码）
                if (font != null) {
                    document.setFont(font);
                }

                // 5. 写入内容并保存
                document.add(new Paragraph(content));
            }

            return "PDF generated successfully to: " + filePath;

        } catch (IOException e) {
            return "Error generating PDF: " + e.getMessage();
        }
    }
}