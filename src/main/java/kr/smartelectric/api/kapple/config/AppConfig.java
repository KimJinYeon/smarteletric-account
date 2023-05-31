package kr.smartelectric.api.kapple.config;

import kr.smartelectric.api.kapple.interceptor.CustomJwtInterceptor;
import kr.smartelectric.api.kapple.interceptor.FirebaseJwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {
    private final CustomJwtInterceptor customJwtInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(customJwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/signup")
                .excludePathPatterns("/email/validation");
    }
}
