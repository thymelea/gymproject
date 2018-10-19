package com.example.gymproject.web;

import com.example.gymproject.domain.College;
import com.example.gymproject.domain.Course;
import com.example.gymproject.domain.Relation;
import com.example.gymproject.domain.User;
import com.example.gymproject.service.*;
import com.example.gymproject.util.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class GymController {

    private static final Logger logger= LoggerFactory.getLogger(GymController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private CollegeService collegeService;


    /**
     * 查询所有用户或根据用户id查用户或根据教练id查教练的用户
     * @param response
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = {"/findUser"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String getUser(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        ObjectMapper mapper = new ObjectMapper();
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        List<User> users=null;
        String jsonlist="{\"msg\":\"false\",\"code\":\"9\"}";
        if(StringUtils.isEmpty(json)) {
            logger.info("查所有用户");
            return jsonlist;
        }
            logger.info("查-->"+json);
                Map<String,Object> data = Utils.paramToMap(json);
                String token=(String)data.get("token");
                String userId=(String)data.get("userId");
                String adminId=(String)data.get("adminId");
                if(!StringUtils.isEmpty(userId)&&StringUtils.isEmpty(adminId)){
                    User user= userService.getUserById(userId,token);
                    if(user==null){
                        return jsonlist;
                    }else {
                        users.add(user);
                    }
                }else if(StringUtils.isEmpty(userId)&&!StringUtils.isEmpty(adminId)){
                    users=userService.getUserByAdmin(adminId);
                }else {
                    users=userService.getUserByAdmin(token);
                }

        try {
            if(users!=null){
                jsonlist = mapper.writeValueAsString(users);
            }
        } catch (JsonProcessingException e) {
            logger.error("对象转json异常");
            e.printStackTrace();
        }
        return jsonlist;
    }
    @RequestMapping(value = {"/findCourse"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String getCourse(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        String jsonlist="{\"msg\":\"111\",\"code\":\"9\"}";
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        try {
        ObjectMapper mapper = new ObjectMapper();
            String userId="";
            String state="";
            String token="";
        if(!StringUtils.isEmpty(json)){
            logger.info(json);
            Map<String,Object> data = Utils.paramToMap(json);;
            userId=(String)data.get("userId");
            state=(String)data.get("state");
            token=(String)data.get("token");
        }
        Map<String,Object> dataMap=new HashMap<>();
        dataMap.put("userId",userId);//用户id
        dataMap.put("state",state);//查询已约还是未约（0，已约；）
        dataMap.put("adminid",token);
        List<Course> courses=courseService.getCourseList(dataMap);
        List<String> uId=new ArrayList<>();
        List<String> coll=new ArrayList<>();
        for(Course course:courses){
            uId.add(course.getUuId());
            coll.add(course.getCollid());
        }
            List<College> collegeList=collegeService.findCollIdin(coll);
            List<User> userList=userService.findUIdIn(uId);
            for(Course course:courses){
                if(collegeList!=null&&collegeList.size()>0)
                for(College college:collegeList){
                    if(course.getCollid().equals(college.getFid())){
                        course.setDateCollege(college);
                    }
                }
                if(userList!=null&&userList.size()>0)
                for(User user:userList){
                    if(course.getUuId().equals(user.getFid())){
                        course.setDateUser(user);
                    }
                }
            }

            jsonlist = mapper.writeValueAsString(courses);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("对象转json异常");
        }
        return jsonlist;
    }

    @RequestMapping(value = {"/findStore"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String getCollege(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        String jsonlist="{\"msg\":\"111\",\"code\":\"9\"}";
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String storeId="";
            String name="";
            String address="";
            String token="";
            if(!StringUtils.isEmpty(json)){
                logger.info(json);
                Map<String,Object> data = Utils.paramToMap(json);
                token=(String)data.get("token");
                storeId=(String)data.get("storeId");//根据门店ID查，可不传
                name=(String)data.get("name");//根据门店名字查，可不传
                address=(String)data.get("address");//根据门店地址查，可不传
            }
            List<College> college=collegeService.findCollege(storeId,name,address,token);
            jsonlist = mapper.writeValueAsString(college);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("对象转json异常");
        }
        return jsonlist;
    }

    //*******************************

    /**
     * 添加学员用户
     * @param response
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addUser(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info("填用户"+json);
                Map<String,Object> data = Utils.paramToMap(json);
                String adminid=(String)data.get("token");
                String name=(String)data.get("name");
                if(StringUtils.isEmpty(name)){
                    return "{\"msg\":\"请输入用户名\",\"code\":\"8\"}";
                }
                if(userService.findUserByNameAndAdminId(name,adminid)!=null){
                logger.error("添加失败，用户已存在");
                return "{\"msg\":\"用户已存在,更换用户名\",\"code\":\"6\"}";
                 }
                String sex=(String)data.get("sex");
                String yearold=(String)data.get("yearold");
                String weight=(String)data.get("weight");
                String height=(String)data.get("height");
                String phone=(String)data.get("phone");
                User user=new User();
                
                user.setFid(UUID.randomUUID().toString());
                user.setAdminId(adminid);
                user.setName(name);
                user.setSex(sex);
                user.setYearold(yearold);
                user.setWeight(weight);
                user.setHeight(height);
                user.setPhone(phone);
                user.setState("0");
                userService.addUser(user);
                return "{\"msg\":\"恭喜你成功\",\"code\":\"0\"}";
        }
    }

    /**
     * 添加课程
     * @param response
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = {"/addCourse"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addCourse(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info("填课程"+json);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Map<String,Object> data = Utils.paramToMap(json);
                String adminid=(String)data.get("token");
                String collegeid=(String)data.get("collegeid");
                String cname=(String)data.get("name");
                Course course=new Course();
                course.setFid(UUID.randomUUID().toString());
                course.setAdminid(adminid);
                course.setCollegeId(collegeid);
                course.setName(cname);
                course.setState("0");
                courseService.save(course);
                return "{\"msg\":\"不容易啊！有成功了\",\"code\":\"0\"}";
        }
    }
    @RequestMapping(value = {"/addStore"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addStore(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info(json);
            Map<String,Object> data = Utils.paramToMap(json);
            String adminid=(String)data.get("token");
            String name=(String)data.get("name");
            String address=(String)data.get("address");
            College college=new College();
            college.setFid(UUID.randomUUID().toString());
            college.setAdminid(adminid);
            college.setCollegename(name);
            college.setAddress(address);
            collegeService.save(college);
            return "{\"msg\":\"success\",\"code\":\"0\"}";
        }
    }
    /**
     * 为用户约课程
     * @param response
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = {"/delCourseForUser"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String delCourseForUser(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info("删除约课"+json);
                Map<String,Object> data = Utils.paramToMap(json);
                String relid=(String)data.get("relid");//id
                relationService.delRe(relid);
                return "{\"msg\":\"success\",\"code\":\"0\"}";

        }
    }
    /**
     * 为用户约课程
     * @param response
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = {"/addCourseForUser"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addCourseForUser(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info(json);
            Map<String,Object> data = Utils.paramToMap(json);
            String userId=(String)data.get("userId");
            String courseId=(String)data.get("courseId");
            String startdate=(String)data.get("startdate");
            String enddate=(String)data.get("enddate");
            String collegeid=(String)data.get("collegeid");
            String relid=(String)data.get("relid");
            Relation relation=null;
            if(StringUtils.isEmpty(relid)) {
                relation=new Relation();
                relid=UUID.randomUUID().toString();
            }else {
                relation=relationService.findRe(relid);
            }
            relation.setFid(relid);
            if(!StringUtils.isEmpty(courseId))
                relation.setCourseId(courseId);
            if(!StringUtils.isEmpty(userId))
                relation.setUserId(userId);
            if(!StringUtils.isEmpty(startdate))
                relation.setStartDate(startdate);
            if(!StringUtils.isEmpty(enddate))
                relation.setEndDate(enddate);
            if(!StringUtils.isEmpty(collegeid))
                relation.setCollid(collegeid);
            relationService.save(relation);
            return "{\"msg\":\"success\",\"code\":\"0\"}";

        }
    }
    /**
     * 删除用户
     * @param response
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = {"/deleteUser"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String deleteUser(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info("删除"+json);
            Map<String,Object> data = Utils.paramToMap(json);
            String userId=(String)data.get("userId");
            userService.delete(userId);
            return "{\"msg\":\"success\",\"code\":\"0\"}";

        }
    }


}
