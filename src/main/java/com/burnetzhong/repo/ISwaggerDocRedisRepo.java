package com.burnetzhong.repo;

import com.burnetzhong.domain.Swagger;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ISwaggerDocRedisRepo {

    public void save(String json);

    public void save(Swagger swagger);


    public Swagger findByBasePath(String basePath) throws InvocationTargetException, IllegalAccessException;


    public List<Swagger> get()throws InvocationTargetException, IllegalAccessException;
}
