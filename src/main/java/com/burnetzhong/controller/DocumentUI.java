package com.burnetzhong.controller;

import com.burnetzhong.domain.Swagger;
import com.burnetzhong.repo.SwaggerDocRedisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Project: zhcore
 *
 * @Comments
 * @Author Zhong Han
 * @Created Date 2017/4/6
 */

@RestController
@RequestMapping(path = "/api")
public class DocumentUI {

    /*@Autowired
    private SwaggerDocRepo swaggerDocRepo;*/

    @Autowired
    private SwaggerDocRedisRepo swaggerDocRedisRepo;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Swagger> getSwaggers(){
       /* List<Swagger> swaggers = (List<Swagger>) swaggerDocRepo.findAll();
        return swaggers;*/
        try {
            return swaggerDocRedisRepo.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/{basePath}", method = RequestMethod.GET)
    public Swagger getByBasePath(@PathVariable  String basePath){
        try {
            /*if(!basePath.startsWith("/")){
                basePath = "/" + basePath;
            }*/
            Swagger swagger = swaggerDocRedisRepo.findByBasePath(basePath);
            return swagger;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
