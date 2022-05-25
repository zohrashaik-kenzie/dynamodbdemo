package filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterClass2 implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("FilterClass2 Request URI is: " + req.getRequestURI());
        chain.doFilter(request, response);
        System.out.println("FilterClass 2 Response Status Code is: " + res.getStatus());
    }

    @Bean
    public FilterRegistrationBean<FilterClass2> filter()
    {
        FilterRegistrationBean<FilterClass2> bean = new FilterRegistrationBean<>();

        System.out.println("Hit the filter registration bean");

        bean.setFilter(new FilterClass2());
        bean.addUrlPatterns("/songs/*");  // or, use `setUrlPatterns()`

        return bean;
    }
}