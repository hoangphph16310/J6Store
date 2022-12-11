package com.example.j6jav;

import com.example.j6jav.entity.Account;
import com.example.j6jav.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Bean
    //            Mã hóa MK
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Cung cấp nguồn dữ liệu đăng nhập(người sd)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(username -> {
           try {
               // Tạo user thông qua username
               Account user = accountService.findById(username);
               System.out.println(user);
               //Lấy password và mã hóa
               String password = passwordEncoder.encode(user.getPassWord());
               //Lấy vai trò của người dùng đưa vào mảng đẻ tạo userDetail
               String [] roles =user.getAuthorities().stream()
                       .map(er-> er.getRole().getId())
                       .collect(Collectors.toList()).toArray(new String[0]);
               //Thông qua withUser để tạo userDetail và trả về
               return User.withUsername(username).password(password).roles(roles).build();
           }catch (NoSuchElementException e){
                throw new UsernameNotFoundException(username+ "not found");
           }
        });
    }
    //                phân quyền sử dụng và hình thức đăng nhập
    @Override
    protected void configure(HttpSecurity http) throws Exception{
            //Vô hiệu hóa
        http.csrf().disable();
        //phân quyền sử dụng
        http.authorizeHttpRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/**").hasAnyAuthority("STAF","DIRE")
                .antMatchers("rest/authorities").hasRole("DIRE")
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success",false)
                .failureUrl("/security/login/error");

//        http.exceptionHandling()
//                .accessDeniedHandler("/security/unauthoried");
        http.exceptionHandling()
                        .accessDeniedHandler(new AccessDeniedHandlerImpl());

        http.logout()
                .logoutUrl("/security/logoff")
                .logoutSuccessUrl("/security/logoff/success");

    }
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }

}
