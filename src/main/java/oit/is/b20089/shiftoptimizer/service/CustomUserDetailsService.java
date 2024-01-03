package oit.is.b20089.shiftoptimizer.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import oit.is.b20089.shiftoptimizer.model.Employee;

public class CustomUserDetailsService implements UserDetailsService {

  private final EmployeeService employeeService;

  public CustomUserDetailsService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // EmployeeServiceを使用して従業員情報を取得
    Employee employee = employeeService.getEmployeeByName(username);

    if (employee == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    // UserDetailsオブジェクトを構築して返す
    return org.springframework.security.core.userdetails.User
        .withUsername(username)
        .password("$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e") // パスワードは全員同じとのこと
        .roles("EMPLOYEE") // ロールを設定
        .build();
  }
}
