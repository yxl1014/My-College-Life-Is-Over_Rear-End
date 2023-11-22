package org.example.common.verify;

import org.apache.logging.log4j.Logger;
import org.example.common.random.RandomComp;
import org.example.common.uuid.SystemUuidComp;
import org.example.domain.MathVerityBody;
import org.example.log.LogComp;
import org.example.log.LogEnum;
import org.example.log.LogType;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author yxl17
 * @Package : org.example.common.verty
 * @Create on : 2023/11/12 12:54
 **/

@Component
public class MathVerifyCodeComp {
    private final RandomComp randomComp;
    private final SystemUuidComp systemUuidComp;
    private static final Logger log = LogComp.getLogger(MathVerifyCodeComp.class);

    public MathVerifyCodeComp(RandomComp randomComp, SystemUuidComp systemUuidComp) {
        this.randomComp = randomComp;
        this.systemUuidComp = systemUuidComp;
    }

    public MathVerityBody buildMathVerityBody(String text){
        MathVerityBody body = new MathVerityBody();
        // 1. 生成uuid
        body.setUuid(systemUuidComp.genUuid());
        // 2. 生成base64图片
        buildPicture(body,text);
        return body;
    }


    /**
     * @param data 数字验证体
     * @param text 图片上的文字
     * @return
     */
    private MathVerityBody buildPicture(MathVerityBody data,String text) {
        // 0. 获得随机类
        Random random = randomComp.getRandom();
        // 1.确定颜色
        // 背景色：底色（0~255）
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Color bgColor = new Color(red, green, blue);
        int red2 = 255 - bgColor.getRed();
        int green2 = 255 - bgColor.getGreen();
        int blue2 = 255 - bgColor.getBlue();
        Color foreColor = new Color(red2, green2, blue2);

        // 2.开始画画（二维码）
        // 2.1准备画纸（大小）
        BufferedImage bufferedImage = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);

        // 2.2 准备画笔
        Graphics graphics = bufferedImage.getGraphics();

        // 2.3 画纸涂满底色
        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, 80, 30);

        // 2.4 画笔换成前景色
        graphics.setColor(foreColor);
        graphics.setFont(new Font("黑体", Font.BOLD, 26));
        graphics.drawString(text, 10, 28);

        // 画50个噪点 TODO YXL 像这里这个常量大多数情况下公司的的操作是放在常量池，但是这里只有这一个用的地方 所以我没加
        int pointCount = 50;
        for (int i = 0; i < pointCount; ++ i)
        {
            graphics.setColor(Color.white);
            int x = random.nextInt(80);
            int y = random.nextInt(30);
            graphics.fillRect(x, y, 1, 1);
        }

        // 3.5 展示观众
        ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
        // 变成文件
        try {
            ImageIO.write(bufferedImage, "jpeg", imageBytes);
        } catch (IOException e) {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.UTIL);
            logMessage.build(LogEnum.FILE_TO_BYTE_ERROR);
            log.error(logMessage.log());
            return data;
        }
        // 将字节数组转换为base64字符串
        String base64Image = Base64.getEncoder().encodeToString(imageBytes.toByteArray());
        data.setBase64Image(base64Image);
        return data;
    }
}