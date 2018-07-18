/**   
 * @Title: JwtTokenUtils.java 
 * @Package com.seed.springboot.common.security 
 * @version V1.0   
 */
package com.seed.springboot.common.security;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.seed.springboot.common.SeedProperties;
import com.seed.springboot.common.security.JwtProperties.Prefix;
import com.seed.springboot.common.security.JwtProperties.Token;
import com.seed.springboot.common.utils.RedisUtils;
import com.seed.springboot.common.utils.lang.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: JwtTokenUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 上午10:02:42
 * 
 */
@Component
@Slf4j
public class JwtTokenUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SeedProperties seedProperties;

	public JwtProperties getJwt() {
		return seedProperties.getJwt();
	}

	public Token getToken() {
		return getJwt().getToken();
	}

	public Prefix getPrefix() {
		return getJwt().getPrefix();
	}

	public int getExpireIn() {
		return getJwt().getExpireIn();
	}

	/**
	 * 获取用户名
	 *
	 * @param token
	 *            Token
	 * @return String
	 */
	public String getUsernameFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return claims != null ? claims.getSubject() : null;
	}

	/**
	 * 获取过期时间
	 *
	 * @param token
	 *            Token
	 * @return Date
	 */
	public Date getExpiredFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return claims != null ? claims.getExpiration() : null;
	}

	/**
	 * 获得 Claims
	 *
	 * @param token
	 *            Token
	 * @return Claims
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(getJwt().getSecret()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			log.warn("getClaimsFromToken exception", e);
			claims = null;
		}
		return claims;
	}

	/**
	 * 计算过期时间
	 *
	 * @return Date
	 */
	private Date generateExpired() {
		return new Date(System.currentTimeMillis() + getJwt().getExpireIn() * 1000);
	}

	/**
	 * 判断 Token 是否过期
	 *
	 * @param token
	 *            Token
	 * @return Boolean
	 */
	private Boolean isTokenExpired(String token) {
		Date expirationDate = getExpiredFromToken(token);
		return expirationDate.before(new Date());
	}

	/**
	 * 生成 Token
	 *
	 * @param userDetails
	 *            用户信息
	 * @return String
	 */
	public String generateToken(UserDetails userDetails) {
		String token = Jwts.builder().setSubject(userDetails.getUsername()).setExpiration(generateExpired())
				.signWith(SignatureAlgorithm.HS512, getJwt().getSecret()).compact();

		String key = getPrefix().getAuth() + ":" + userDetails.getUsername();
		redisUtils.setExpire(key, token, getJwt().getExpireIn());
		log.info("[JwtTokenUtils] generateToken key:{}, time:{}", key, getJwt().getExpireIn());
		putUserDetails(userDetails);
		return token;
	}

	/**
	 * 验证 Token
	 *
	 * @param token
	 *            Token
	 * @return Boolean
	 */
	public Boolean validateToken(String token) {
		final String username = getUsernameFromToken(token);
		String key = getPrefix().getAuth() + ":" + username;
		String redisToken = redisUtils.get(key);
		return StringUtils.isNotEmpty(token) && !isTokenExpired(token) && token.equals(redisToken);
	}

	/**
	 * 移除 Token
	 *
	 * @param token
	 *            Token
	 */
	public void removeToken(String token) {
		final String username = getUsernameFromToken(token);
		String key = getPrefix().getAuth() + ":" + username;
		redisUtils.del(key);
		delUserDetails(username);
	}

	/**
	 * 获得用户信息 Json 字符串
	 *
	 * @param token
	 *            Token
	 * @return String
	 */
	public String getUserDetailsString(String token) {
		final String username = getUsernameFromToken(token);
		String key = getPrefix().getUser() + ":" + username;
		return redisUtils.get(key);
	}

	/**
	 * 存储用户信息
	 *
	 * @param userDetails
	 *            用户信息
	 */
	private void putUserDetails(UserDetails userDetails) {
		String key = getPrefix().getUser() + ":" + userDetails.getUsername();
		redisUtils.setExpire(key, new Gson().toJson(userDetails), getJwt().getExpireIn());
	}

	/**
	 * 删除用户信息
	 *
	 * @param username
	 *            用户名
	 */
	private void delUserDetails(String username) {
		String key = getPrefix().getUser() + ":" + username;
		redisUtils.del(key);
	}

}
