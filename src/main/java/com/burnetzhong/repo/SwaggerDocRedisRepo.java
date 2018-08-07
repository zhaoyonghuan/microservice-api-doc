package com.burnetzhong.repo;

import com.burnetzhong.domain.Swagger;
import com.burnetzhong.util.RedisTemplateFacade;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Repository
public class SwaggerDocRedisRepo implements ISwaggerDocRedisRepo{

    @Autowired
    private RedisTemplateFacade redisTemplateFacade;


    @Override
    public void save(String json) {
    }

    public void save(Swagger swagger)  {
        /*Map<String,String> map = new HashMap<String,String>();
        BeanUtils.populate(swagger,map);*/
       // swagger.setBasePath("/basePath");
        String json = new Gson().toJson(swagger);

        redisTemplateFacade.getRedisTemplate().opsForHash().put("apiPath",swagger.getInfo().getTitle(),json);

    }

    public Swagger findByBasePath(String basePath) throws InvocationTargetException, IllegalAccessException {

        String json = (String)redisTemplateFacade.getRedisTemplate().opsForHash().get("apiPath",basePath);
        /*Swagger swagger = new Swagger();
        BeanUtils.populate(swagger,map);*/
        Swagger swagger = new Gson().fromJson(json,Swagger.class);
        return swagger;
    }

    public List<Swagger> get() throws InvocationTargetException, IllegalAccessException {

        List<Swagger> list = new ArrayList<>();
        Map<String,Object> map = redisTemplateFacade.getRedisTemplate().opsForHash().entries("apiPath");
        Set<String> set = map.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()){
            String key = (String) it.next();
            /*Swagger s = new Swagger();
            BeanUtils.populate(s,map);*/
            String json = (String)map.get(key);
            Swagger swagger = new Gson().fromJson(json,Swagger.class);
            list.add(swagger);
        }
        return list;
    }




}
