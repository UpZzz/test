package com.test_springboot_mybatis.tttt.controller;

import com.alibaba.fastjson.JSONObject;
import com.test_springboot_mybatis.tttt.config.TokenConfig;
import com.test_springboot_mybatis.tttt.constarts.Constant;
import com.test_springboot_mybatis.tttt.entity.Administrator;
import com.test_springboot_mybatis.tttt.entity.User;
import com.test_springboot_mybatis.tttt.mapper.AdministratorMapper;
import com.test_springboot_mybatis.tttt.service.RedisService;
import com.test_springboot_mybatis.tttt.utils.GetJsonObjectUtils;
import com.test_springboot_mybatis.tttt.utils.JwtTokenUtil;
import com.test_springboot_mybatis.tttt.utils.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/user")
@CrossOrigin
public class userController {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Autowired
    private TokenConfig tokenConfig;

    @Autowired
    private RedisService redisService;

    /*
    * 可以用@ReponseBody注入bean的方式
    * 也可以用GetJsonObjectUtils封装的方法，获取出JSON串，再解析出参数。
    * */
    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json")
    public Response userLogin(@RequestBody User user){
//        JSONObject jsonObject = GetJsonObjectUtils.getParam(request);
//        String username = jsonObject.get("username").toString();
//        String password = jsonObject.get("password").toString();
        String username = user.getUsername();
        String password = user.getPassword();
        Administrator administrator = administratorMapper.selectByUsername(username);
        if(password.equals(administrator.getPwd())){
            String accessToken = JwtTokenUtil.getInstance()
                    .setIssuer(tokenConfig.getIssuer())
                    .setSecret("223355a")
                    .setSubObject(username)
                    .generateToken();
            // 每次登录的时候吧token放到 redis，用于只能一个账号同时在线
            System.out.println(accessToken);
            redisService.set(Constant.JWT_USER_NAME+username,accessToken);
            // 每次登录先删除需要重新登录的标记
            redisService.delete(Constant.JWT_USER_LOGIN_BLACKLIST+username);
            Map<String, Object> map = new HashMap<>(2);
            map.put("token", accessToken);
            return Response.success("正确", map);
        }else{
            return Response.error("密码错误");
        }
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Response info(@RequestParam("accessToken") String accessToken){
        System.out.println(accessToken);
        Map<String,Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        String[] a = {"admin"};
        map.put("roles", a);
        return Response.success(map);
    }

    @ApiOperation(value = "用户登出",notes = "用户登出的接口")
    @GetMapping("/logout")
    public Response<String> logout(HttpServletRequest request){
        String accessToken = null;
        //String refreshToken = null;
        // 退出时，不管成功还是失败都要退出
        try {
            accessToken = request.getHeader(Constant.ACCESS_TOKEN);
            //refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
            if (StringUtils.isBlank(accessToken)){
                System.out.println("token为空");
            }

            return Response.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.success();
    }

    @GetMapping("/findAll")
    public Response findAll(){
        List<Administrator> administrators = administratorMapper.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "success");
        map.put("data", administrators);
        return Response.success(map);
    }

    // 分页查询
    @PostMapping("/query")
    public Response query(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        List<Administrator> administrators = administratorMapper.findByPage(page*pageSize, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "success");
        map.put("total", administratorMapper.count());
        map.put("data", administrators);
        return Response.success(map);
    }

    @GetMapping("deleteById")
    public Response deleteById(@RequestParam("id") String id){
        int result = administratorMapper.deleteByPrimaryKey(Integer.parseInt(id));
        if(result == 1){
            Map<String, Object> map = new HashMap<>();
            map.put("status", 200);
            map.put("message", "success");
            return Response.success(map);
        }else{
            return Response.error();
        }
    }

    @PostMapping("saveOrUpdate")
    public Response saveOrUpdate(@RequestParam("id") String id, @RequestParam("username") String username, @RequestParam("pwd") String pwd, @RequestParam("tel") String tel){
        if(id != ""){
            // 说明已经有该管理员，之后执行更新操作
            Administrator administrator = administratorMapper.selectByPrimaryKey(Integer.parseInt(id));
            administrator.setUsername(username);
            administrator.setTel(tel);
            administrator.setPwd(pwd);
            if(administratorMapper.updateByPrimaryKey(administrator) == 1){
                Map<String, Object> map = new HashMap<>();
                map.put("status", 200);
                map.put("message", "success");
                return Response.success(map);
            }else{
                return Response.error();
            }
        }else{
            // 说明前台正在执行插入操作
            Administrator administrator = new Administrator();
            administrator.setUsername(username);
            administrator.setPwd(pwd);
            administrator.setCategory(0);
            administrator.setTel(tel);
            administrator.setEmail("5151");
            if(administratorMapper.insert(administrator) == 1){
                Map<String, Object> map = new HashMap<>();
                map.put("status", 200);
                map.put("message", "success");
                return Response.success(map);
            }else{
                return Response.error();
            }
        }
    }

    @PostMapping(value = "selectByUsername")
    public Response selectByUsername(@RequestParam("username") String searchName){
        Administrator administrator = administratorMapper.selectByUsername(searchName);
        List<Administrator> administrators = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "success");
        if(administrator!=null){
            administrators.add(administrator);
        }
        map.put("data", administrators);
        return Response.success(map);

    }

}
