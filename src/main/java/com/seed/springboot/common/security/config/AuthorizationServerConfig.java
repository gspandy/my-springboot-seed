/**   
 * @Title: AuthorizationServerConfig.java 
 * @Package com.seed.springboot.common.security.config 
 * @version V1.0   
 */
package com.seed.springboot.common.security.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.seed.springboot.common.utils.exception.oauth.CustomOAuth2Exception;

/**
 * @ClassName: AuthorizationServerConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月10日 下午5:31:06
 * 
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisConnectionFactory connectionFactory;
	
//	@Autowired  
//    private AuthorizationEndpoint authorizationEndpoint;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@PostConstruct  
    public void init() {  
		//自定义授权页面
//        authorizationEndpoint.setUserApprovalPage("forward:/oauth/my_approval_page");  
//        authorizationEndpoint.setErrorPage("forward:/oauth/my_error_page");  
    }  
	
	@Bean
	public RedisTokenStore tokenStore() {
		return new RedisTokenStore(connectionFactory);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("vuejs")
			.secret("vuejs")
			.authorizedGrantTypes("password", "authorization_code", "refresh_token")
			.redirectUris("https://www.baidu.com")
			.scopes("all")
			.and()
			.withClient("client")
			.secret("client")
			.authorizedGrantTypes("client_credentials")
			.authorities("client_role")
			.scopes("all");
	}
	
	//自定义异常
	@Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                OAuth2Exception oAuth2Exception;
                if (e instanceof InvalidGrantException) {
                    oAuth2Exception = new InvalidGrantException("用户名或密码错误", e);
                }else if(e instanceof InternalAuthenticationServiceException) {
                    oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
                }else{
                    oAuth2Exception = new UnsupportedResponseTypeException("服务内部错误", e);
                }
                return ResponseEntity
                        .status(oAuth2Exception.getHttpErrorCode())
                        .body(new CustomOAuth2Exception(oAuth2Exception.getMessage(),e));
            }
        };
    }
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).userDetailsService(userDetailsService);
		endpoints.exceptionTranslator(webResponseExceptionTranslator());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//这里主要是开启客户端登录方式
		security.allowFormAuthenticationForClients();
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
}
