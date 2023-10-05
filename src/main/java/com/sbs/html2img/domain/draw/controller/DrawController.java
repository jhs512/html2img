package com.sbs.html2img.domain.draw.controller;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;

@Controller
@RequiredArgsConstructor
@RequestMapping("/draw")
public class DrawController {
    @GetMapping("")
    public String draw() {
        // 헤드리스 모드로 브라우저 실행
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--allow-file-access-from-files");

        WebDriver driver = new ChromeDriver(options);

        try {
            // 새 탭 생성
            String url = "data:text/html;charset=utf-8," + """
                    <style>
                    @font-face {
                      font-family: 'Pretendard-Regular';
                      src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
                      font-weight: 400;
                      font-style: normal;
                    }
                                        
                    .font-pretendard {
                      font-family: 'Pretendard-Regular';
                    }
                    </style>
                                       
                    <script> 
                    document.fonts.ready.then(function() {
                      // 웹폰트가 로딩되었음을 알리는 코드를 추가
                      document.body.dataset.fontsLoaded = 'true';
                    });
                    </script>
                                        
                    <h1 class="font-pretendard">안녕하세요.</h1>
                                        
                    <h1>안녕하세요.</h1>

                    """.stripIndent();

            // 생성된 페이지로 이동
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));

            wait.until(ExpectedConditions.attributeToBe(By.ByTagName.tagName("body"), "data-fonts-loaded", "true"));

            // 페이지 스크린샷 캡쳐
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 지정된 영역을 스크린샷으로 캡쳐
            BufferedImage fullImg = ImageIO.read(screenshot);
            // 해당 좌표, 범위를 지정하여 원하는 부분을 캡쳐
            BufferedImage eleScreenshot = fullImg.getSubimage(0, 0, 500, 200);
            ImageIO.write(eleScreenshot, "png", screenshot);

            screenshot.renameTo(new File("C:/Users/SBS/Desktop/aa/test/screenshot.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }

        return "draw/main";
    }
}
