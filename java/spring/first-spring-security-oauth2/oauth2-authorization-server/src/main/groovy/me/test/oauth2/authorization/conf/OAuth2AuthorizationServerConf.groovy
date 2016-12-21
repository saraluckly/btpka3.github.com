package me.test.oauth2.authorization.conf

import me.test.oauth2.authorization.MyOAuth2Properties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.*
import org.springframework.core.io.ResourceLoader
import org.springframework.jdbc.datasource.init.DatabasePopulator
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

import javax.sql.DataSource

/**
 *
 * 通过 AuthorizationServerSecurityConfiguration 仅仅过滤以下 URL:
 *      /oauth/token
 *      /oauth/token_key
 *      /oauth/check_token
 *
 * 注意 :  虽然 OAuth2AuthorizationServerConfiguration 可提供默认的配置，但是有以下缺陷：
 *      1. 默认只能从 application.yml 中获取一个 client 身份信息，就意味着只能有一个第三方应用。
 * 故不要使用，自己明确提供 AuthorizationServerConfigurer/AuthorizationServerConfigurerAdapter
 */
@Configuration
@EnableAuthorizationServer
//@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class OAuth2AuthorizationServerConf extends AuthorizationServerConfigurerAdapter {


    @Autowired
    ResourceLoader resourceLoader

    @Autowired
    TokenStore tokenStore

//    @Autowired
//    ClientDetailsService clientDetailsService

    @Autowired
    DataSource dataSource

    @Autowired
    private MyOAuth2Properties myOAuth2Props


    @Autowired
//    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager; // 人：Resource Owner



    // -------------------------- 定义 spring beans
    @Bean
    MyOAuth2Properties myOAuth2Properties() {
        return new MyOAuth2Properties()
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 如果集群模式下部署，则这里必须明确指明签名和验证签名的key。
        return new JwtAccessTokenConverter()
    }

    @Bean
    public TokenStore tokenStore(DataSource dataSource) {
//        TokenStore tokenStore = new JwtTokenStore(jwtAccessTokenConverter())
//        return tokenStore

        // 先创建所需的表结构
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScripts(resourceLoader.getResource("classpath:schema.sql"))
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);


        TokenStore tokenStore = new JdbcTokenStore(dataSource)
        return tokenStore
    }

//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }

    @Bean
    public ApprovalStore approvalStore(TokenStore tokenStore) {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }


    // AuthorizationServerEndpointsConfigurer#userApprovalHandler 已经智能配置好了
//    @Bean
//    @Lazy
//    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public UserApprovalHandler userApprovalHandler() throws Exception {
////        SparklrUserApprovalHandler handler = new SparklrUserApprovalHandler();
////        handler.setApprovalStore(approvalStore());
////        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
////        handler.setClientDetailsService(clientDetailsService);
////        handler.setUseApprovalStore(true);
////        return handler;
//
//        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
//        handler.setClientDetailsService(clientDetailsService);
//        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
//        handler.setClientDetailsService(clientDetailsService);
//        return handler;
//
////        DefaultUserApprovalHandler handler = new DefaultUserApprovalHandler();
////        return handler;
//
//    }

    // --------------------------
//    @Autowired
//    private AuthorizationServerProperties properties;


//    @Autowired
//    private ClientDetailsService clientDetailsService;


//    @Autowired
//    private UserApprovalHandler userApprovalHandler;





    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()

        clients.jdbc(dataSource)
                //.passwordEncoder()

                // 刚启动就注册一个
                .withClient(myOAuth2Props.client.id)
                .resourceIds(myOAuth2Props.resource.id)
                .secret(myOAuth2Props.client.secret)
                .scopes(myOAuth2Props.client.scopes)
                .authorizedGrantTypes(myOAuth2Props.client.authorizedGrantTypes)
                //.redirectUris()
                .authorities(myOAuth2Props.client.authorities)
                //.accessTokenValiditySeconds()
                //.refreshTokenValiditySeconds()
                //.additionalInformation()
                //.autoApprove()


    }

    // AuthorizationServerEndpointsConfiguration#authorizationEndpoint() 已经配置好了
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(tokenStore)
        //.userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);  // 启用 ResourceOwnerPasswordTokenGranter
        //.pathMapping("/oauth/confirm_access","/your/controller") // 可以修改映射路径。
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {

        // 通过 application.yml : "security.oauth2.authorization.realm"
        oauthServer.realm(myOAuth2Props.auth.realm)
            .tokenKeyAccess('isFullyAuthenticated()')
            .checkTokenAccess('isFullyAuthenticated()');
    }

}















