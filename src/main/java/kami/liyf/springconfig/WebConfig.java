package kami.liyf.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@Configuration()
@ComponentScan(value = "kami.liyf.controller",useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class))
@PropertySource("classpath:init.properties")
@Import(WebSocketConfig.class)
public class WebConfig implements WebMvcConfigurer {

    /**
     * html视图解析器
     * @return InternalResourceViewResolver
     */
    @Bean
    public FreeMarkerViewResolver htmlViewResolver(){
        FreeMarkerViewResolver htmlViewResolver = new FreeMarkerViewResolver();
        htmlViewResolver.setPrefix("/WEB-INF/views/html/");
        htmlViewResolver.setSuffix(".html");
        htmlViewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        htmlViewResolver.setContentType("text/html;charset=UTF-8");
        return htmlViewResolver;
    }

    /**
     * freemarker视图解析器
     * @return FreeMarkerViewResolver
     */
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setPrefix("/WEB-INF/views/ftl/");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
        freeMarkerViewResolver.setOrder(2);
        return freeMarkerViewResolver;
    }

    /**
     * jsp视图解析器
     * @return InternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
        jspViewResolver.setViewClass(JstlView.class);
        jspViewResolver.setPrefix("/WEB-INF/views/jsp/");
        jspViewResolver.setSuffix(".jsp");
        jspViewResolver.setOrder(Ordered.LOWEST_PRECEDENCE);
        return jspViewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 静态资源配置
     * @param registry 资源配置注册
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }*/
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/welcome");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //通过重写configurePathMatch方法不再忽略路径中.符号后面的内容
        configurer.setUseSuffixPatternMatch(false);
    }
}
