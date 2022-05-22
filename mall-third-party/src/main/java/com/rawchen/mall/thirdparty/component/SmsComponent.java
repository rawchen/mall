package com.rawchen.mall.thirdparty.component;

import com.rawchen.common.utils.HttpUtils;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: SmsComponent</p>
 * Description：
 * date：2022/1/25 14:23
 */
@Data
@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")
@Component
public class SmsComponent {

	private String host;

	private String path;

	private String templateId;

	private String appCode;

	public String sendSmsCode(String phone, String code){
		String method = "POST";
		Map<String, String> headers = new HashMap<String, String>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + this.appCode);
		Map<String, String> bodys = new HashMap<String, String>();
		bodys.put("content", "code:" + code + ",expire_at:5");
		bodys.put("phone_number", phone);
		bodys.put("template_id", this.templateId);
		HttpResponse response = null;
		try {
			response = HttpUtils.doPost(this.host, this.path, method, headers, null, bodys);
			//获取response的body
			if(response.getStatusLine().getStatusCode() == 200){
				return EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail_" + response.getStatusLine().getStatusCode();
	}
}
