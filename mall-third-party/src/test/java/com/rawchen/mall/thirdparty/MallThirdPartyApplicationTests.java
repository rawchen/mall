package com.rawchen.mall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.rawchen.mall.thirdparty.component.SmsComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 第三方服务上传文件
 */
@SpringBootTest
class MallThirdPartyApplicationTests {

	@Resource
	private OSSClient ossClient;

	@Resource
	private SmsComponent smsComponent;

	@Value("${spring.cloud.alicloud.oss.bucket}")
	private String bucketName;

	@Test
	public void SendCodeTest(){
		String s = smsComponent.sendSmsCode("18259234xx", "6666");
		System.out.println(s);
	}

	@Test
	public void testFindPath() throws IOException {
		// 上传网络流。
		InputStream inputStream = new FileInputStream("C:\\Users\\root\\Desktop\\1.jpg");

		ossClient.putObject(bucketName, "1.jpg", inputStream);

		// 关闭OSSClient。
		ossClient.shutdown();
		System.out.println("上传成功");
	}
}
