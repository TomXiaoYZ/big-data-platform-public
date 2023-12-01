package com.dep.auth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dep.auth.client.LdapClient;
import com.dep.auth.common.AuthException;
import com.dep.auth.common.ErrorCode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.directory.Attributes;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: dep-auth
 * @author: Tom Xiao
 * @create: 2023-12-01 15:21
 **/
public class LdapService {

    @Value("${ldap.base-dn}")
    private static String BASE_DN;

    @Value("${ldap.user-dn}")
    private static String LDAP_USER_DN;

    @Value("${ldap.password}")
    private static String LDAP_PASSWORD;

    @Value("${ldap.url}")
    private static String URL;

    @Value("${ldap.user.base-dn}")
    private static String PEOPLE_BASE_DN;

    private final LdapClient ldapClient = new LdapClient(
            BASE_DN, LDAP_USER_DN, LDAP_PASSWORD, URL, PEOPLE_BASE_DN
    );

    private JWTService jwtService;


}
