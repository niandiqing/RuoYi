package com.ruoyi.common.utils;// 导入所需依赖
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    public static void main(String[] args) {
        String url = "https://your-long-url.com"; // 你的网址
        try {
            // 生成短链接（此处假设你已经有了一个获取短链接的接口或服务）
            String shortUrl = getShortUrlForWechat(url);

            // 设置二维码配置
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 指定字符集
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 错误纠正级别（L/M/Q/H）

            // 创建二维码写入器
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // 生成二维码比特矩阵
            BitMatrix bitMatrix = qrCodeWriter.encode(shortUrl, BarcodeFormat.QR_CODE, 300, 300, hints);

            // 将比特矩阵输出为图片文件
            Path path = FileSystems.getDefault().getPath("qrcode.png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            System.out.println("二维码已生成至: " + path.toAbsolutePath());

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    // 假设这是一个用于获取微信可用短链接的方法
    private static String getShortUrlForWechat(String longUrl) {
        // 实现这部分逻辑，可能需要调用微信的短链接API或者使用第三方服务
        // 这部分代码未给出，具体实现请参考相应服务文档
        return "www.baidu.com";
    }
}