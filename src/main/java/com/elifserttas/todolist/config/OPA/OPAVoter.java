package com.elifserttas.todolist.config.OPA;

import com.elifserttas.todolist.command.RoleCommand;
import com.elifserttas.todolist.command.UsersCommand;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class OPAVoter implements AccessDecisionVoter<Object> {

    private String opaUrl;

    public OPAVoter(String opaUrl) {
        this.opaUrl = opaUrl;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object obj, Collection<ConfigAttribute> attrs) {

        if (!(obj instanceof FilterInvocation)) {
            return ACCESS_ABSTAIN;
        }

        List<ConfigAttribute> collect = attrs.stream().collect(Collectors.toList());
        for (ConfigAttribute configAttribute : collect) {
            String attribute = configAttribute.toString();
            if(attribute.equals("permitAll")){
                return ACCESS_GRANTED;
            }
        }


        FilterInvocation filter = (FilterInvocation) obj;
        Map<String, String> headers = new HashMap<String, String>();

        for (Enumeration<String> headerNames = filter.getRequest().getHeaderNames(); headerNames.hasMoreElements();) {
            String header = headerNames.nextElement();
            headers.put(header, filter.getRequest().getHeader(header));
        }

        String[] path = filter.getRequest().getRequestURI().replaceAll("^/|/$", "").split("/");

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("auth", authentication.getPrincipal().toString());
        input.put("method", filter.getRequest().getMethod());
        input.put("path", path);
        input.put("headers", headers);
        if(authentication.getPrincipal() instanceof  UsersCommand){
            List<String> collect1 = ((UsersCommand) authentication.getPrincipal()).getRoles().stream().map(RoleCommand::getRole)
                    .collect(Collectors.toList());
            if( collect1.contains("ADMIN")){
              input.put("isadmin",true);
          }
        }

        RestTemplate client = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<>(new OPADataRequest(input));
        OPADataResponse response = client.postForObject(this.opaUrl, request, OPADataResponse.class);



        if (!response.getResult()) {
            return ACCESS_DENIED;
        }

        return ACCESS_GRANTED;
    }

}