package com.seed.springboot;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.seed.springboot.common.utils.mapper.JsonMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringbootSeedApplication.class)
@Slf4j
public class MySpringbootSeedApplicationTests {

	// 端口
	final static long PORT = 9999;
	// clientId
	final static String CLIENT_ID = "vuejs";
	// clientSecret
	final static String CLIENT_SECRET = "vuejs";
	// 用户名
	final static String USERNAME = "admin";
	// 密码
	final static String PASSWORD = "123456";
	// 获取accessToken得URI
//	final static String TOKEN_REQUEST_URI = "http://localhost:" + PORT + "/oauth/token?grant_type=client_credentials&scope=all";
	final static String TOKEN_REQUEST_URI = "http://localhost:" + PORT + "/oauth/token?grant_type=password&username="
			+ USERNAME + "&password=" + PASSWORD + "&scope=all";
	// 获取用户信息得URL
	final static String USER_INFO_URI = "http://localhost:" + PORT + "/current";

	@Test
	public void getUserInfo() throws Exception {
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("authorization", "Bearer " + getAccessToken());
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		// pay attention, if using get with headers, should use exchange instead
		// of getForEntity / getForObject
		ResponseEntity<String> result = rest.exchange(USER_INFO_URI, HttpMethod.GET, entity, String.class,
				new Object[] { null });
		log.info("用户信息返回的结果={}", JsonMapper.toJson(result));
	}

	/**
	 * 获取accessToken
	 * 
	 * @return
	 */
	private String getAccessToken() {
		log.info(TOKEN_REQUEST_URI);
		log.info(getBasicAuthHeader());
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		headers.add("authorization", getBasicAuthHeader());
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<OAuth2AccessToken> resp = rest.postForEntity(TOKEN_REQUEST_URI, entity, OAuth2AccessToken.class);
		if (!resp.getStatusCode().equals(HttpStatus.OK)) {
			throw new RuntimeException(resp.toString());
		}
		OAuth2AccessToken t = resp.getBody();
		log.info("accessToken={}", JsonMapper.toJson(t));
		log.info("the response, access_token: " + t.getValue() + "; token_type: " + t.getTokenType() + "; "
				+ "refresh_token: " + t.getRefreshToken() + "; expiration: " + t.getExpiresIn() + ", expired when:"
				+ t.getExpiration());
		return t.getValue();

	}

	/**
	 * 构建header
	 * 
	 * @return
	 */
	private String getBasicAuthHeader() {
		String auth = CLIENT_ID + ":" + CLIENT_SECRET;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		return authHeader;
	}

}
