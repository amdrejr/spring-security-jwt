package com.amdrejr.springsecurityjwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true)
public class MethodSecurityConfig  {
}
// PrePostMethodSecurityConfiguration, SecuredMethodSecurityConfiguration, or Jsr250MethodSecurityConfiguration instead
