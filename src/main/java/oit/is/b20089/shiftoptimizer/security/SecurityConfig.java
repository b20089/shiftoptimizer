package oit.is.b20089.shiftoptimizer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import oit.is.b20089.shiftoptimizer.service.CustomUserDetailsService;
import oit.is.b20089.shiftoptimizer.service.EmployeeService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserDetailsService userDetailsService;

  @Autowired
  EmployeeService employeeService;

  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  // @Bean
  // public UserDetailsService userDetailsService() {
  //   return new CustomUserDetailsService(employeeService); // employeeServiceは適切なインスタンスに置き換える
  // }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }
}
