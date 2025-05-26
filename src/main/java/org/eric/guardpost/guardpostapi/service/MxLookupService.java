package org.eric.guardpost.guardpostapi.service;

import org.springframework.stereotype.Service;

import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.Attributes;
import javax.naming.NamingException;

import java.util.Hashtable;

@Service
public class MxLookupService {

    public MxLookupService() {}

    public boolean hasMxRecord(String domain){

        if (domain == null) return false;

        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext ctx = new InitialDirContext(env);
            Attributes attrs = ctx.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");

            return attr != null && attr.size() > 0;

        } catch (NamingException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
