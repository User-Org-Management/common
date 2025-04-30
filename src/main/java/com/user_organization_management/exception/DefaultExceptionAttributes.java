package com.user_organization_management.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.Map;

@Component
public class DefaultExceptionAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String , Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        errorAttributes.put("success", Boolean.FALSE);
        errorAttributes.put("status", errorAttributes.get("error"));
        errorAttributes.put("error", errorAttributes.get("message"));
        errorAttributes.put("details", Collections.singletonList(errorAttributes.get("message")));
        errorAttributes.remove("exception");
        errorAttributes.remove("path");
        errorAttributes.remove("trace");
        return errorAttributes;
    }
}
