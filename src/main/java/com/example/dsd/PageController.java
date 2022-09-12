package com.example.dsd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class PageController {


	@Value("${selenium.path}")
	private String path;


	@GetMapping("/healthCheck")
	public int status() {
		return 200;
	}

	@GetMapping("/page")
	public String crawler() {
		System.setProperty("webdriver.chrome.driver", this.path);
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		System.out.println("ptah >>> " + this.path);

		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.baidu.com");

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

		WebElement submitButton = driver.findElement(By.cssSelector(".title-content-title"));
		String value = submitButton.getText();

		return value;
	}
}
