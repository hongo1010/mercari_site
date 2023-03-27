package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/html/**" ,"/css/**", "/js/**", "/fonts/**");
	}

	/**
	 * @param http
	 * @return ログイン成功時は商品一覧、ログイン失敗時はログイン画面に遷移.
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// ログインしていなくても遷移できる場所
		http.authorizeHttpRequests().requestMatchers("/register", "/register/insert", "/login", "/login/loginuser", "/list", "/edit", "/edit/uodate", "/detail").permitAll().anyRequest()
				.authenticated();

		http.formLogin() // ログイン時の設定
				.loginPage("/login") // ログイン画面
				.loginProcessingUrl("/login/loginuser") // ログイン情報の送信先URL
				.failureUrl("/login/error=true") // ログイン失敗後のパス
				.defaultSuccessUrl("/list", true) // ログイン成功後リダイレクト先
				.usernameParameter("username") // 認証時に使用するユーザ名のリクエストパラメータ名(今回はメールアドレスを使用)
				.passwordParameter("password");// 認証時に使用するパスワードのリクエストパラメータ名

		http.logout() // ログアウトの設定
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //ログアウト後の遷移先
				.logoutSuccessUrl("/list") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
				.deleteCookies("JSESSIONID") // ログアウト後Cookieに保存されているsessionIDを削除
				.invalidateHttpSession(true); // ログアウト後sessionを削除

		return http.build();
	}
	
	// パスワードハッシュ化
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
