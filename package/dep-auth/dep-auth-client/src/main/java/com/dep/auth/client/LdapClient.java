package com.dep.auth.client;

import java.io.IOException;
import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 * @program: dep-auth
 * @author: Tom Xiao
 * @create: 2023-11-19 14:34
 **/
public class LdapClient {

    private static String BASE_DN;

    private static String LDAP_USER_DN;

    private static String LDAP_PASSWORD;

    private static String URL;

    private static String PEOPLE_BASE_DN;

    private static LdapContext ctx = null;

    private static final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

    private static final Control[] CONN_CTLS = null;

    public LdapClient(String baseDn, String ldapUserDn, String ldapPassword, String url, String peopleBaseDn) {
        BASE_DN = baseDn;
        LDAP_USER_DN = ldapUserDn;
        LDAP_PASSWORD = ldapPassword;
        URL = url;
        PEOPLE_BASE_DN = peopleBaseDn;
    }

    public static void peopleLdapConnect() {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
        env.put(Context.PROVIDER_URL, URL + PEOPLE_BASE_DN);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, LDAP_USER_DN);
        env.put(Context.SECURITY_CREDENTIALS, LDAP_PASSWORD);
        try {
            ctx = new InitialLdapContext(env, CONN_CTLS);
            System.out.println("Connection to ldap server successfully");
        } catch (AuthenticationException e) {
            System.out.println("Failed to connect to ldap server: ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Failed to connect to ldap server: ");
            e.printStackTrace();
        }
    }

    public static String getUserDN(String username) throws NamingException, IOException {
        String userDn = "";
        peopleLdapConnect();
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> en = ctx.search("", "uid=" + username, constraints);
            if (en == null || !en.hasMoreElements()) {
                System.out.println("User is not found in the ldap server.");
            }
            while (en != null && en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj instanceof SearchResult) {
                    SearchResult si = (SearchResult) obj;
                    userDn = si.getNameInNamespace();
                } else {
                    System.out.println(obj);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to search for user: ");
            e.printStackTrace();
        }
        return userDn;
    }

    public static boolean addUser(String username, String password) throws NamingException {
        try {
            peopleLdapConnect();
            BasicAttributes attrsbu = new BasicAttributes();
            BasicAttribute objclassSet = new BasicAttribute("objectclass");
            objclassSet.add("inetOrgPerson");
            objclassSet.add("organizationalPerson");
            objclassSet.add("person");
            objclassSet.add("top");
            attrsbu.put(objclassSet);
            attrsbu.put("cn", username);
            attrsbu.put("sn", username);
            attrsbu.put("uid", username);
            attrsbu.put("userPassword", password);
            String uid = "uid=" + username;
            ctx.createSubcontext(uid, attrsbu);
            ctx.close();
            return true;
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static NamingEnumeration getAllUsers() throws NamingException {
        String searchFilter = "(objectClass=inetOrgPerson)";
        String[] reqAtt = {"cn", "sn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = ctx.search(BASE_DN, searchFilter, controls);


        SearchResult result = null;
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            String name = attr.get("cn").get(0).toString();
            System.out.println(attr.get("cn"));
            System.out.println(attr.get("sn"));
        }

    }

    public static boolean updateUserPassword(String username, String oldPassword, String newPassword) throws NamingException {
        if (!authenticate(username, oldPassword)) {
            return false; // Old password is incorrect, can't change to new password
        }

        try {
            peopleLdapConnect();
            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", newPassword));
            ctx.modifyAttributes("uid=" + username, mods);
            System.out.println("success");
            ctx.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean authenticate(String username, String password) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
        env.put(Context.PROVIDER_URL, URL);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + "," + PEOPLE_BASE_DN);
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            ctx = new InitialLdapContext(env, CONN_CTLS);
            ctx.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
