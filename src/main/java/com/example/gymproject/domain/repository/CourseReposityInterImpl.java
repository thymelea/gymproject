package com.example.gymproject.domain.repository;

import com.example.gymproject.domain.Course;
import com.example.gymproject.domain.specific.CourseDao;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CourseReposityInterImpl implements CourseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> findCoursrByUserId(Map<String,Object> dataMap) {
        StringBuffer sb=new StringBuffer();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowTime=simpleDateFormat.format(new Date().getTime());
        List<Course> list=new ArrayList<>();
        String userId=(String)dataMap.get("userId");
        String state=(String)dataMap.get("state");
        String adminid=(String)dataMap.get("adminid");
        String uId="";
        if(!StringUtils.isEmpty(state)){
            uId=",u.fid ufid, coll.fid collid,uc.startdate,uc.enddate ";
        }
        sb.append("select co.fid,co.cname,co.state"+uId+" " +
                "FROM fcourse co ");
        if(!StringUtils.isEmpty(state)){
            sb.append("INNER JOIN ucrelation uc ON co.fid=uc.fcourseId ");
            sb.append("INNER JOIN fuser u on u.fid=uc.fuserId ");
            sb.append("INNER JOIN college coll on coll.fid = uc.collid ");
        }
        sb.append("where 1 = 1 ");
        if(!StringUtils.isEmpty(userId)){
            sb.append(" and u.fid = :userId ");
        }
        sb.append(" and co.adminid = :adminid ");
//        sb.append(" and co.startdate > :nowTime and co.enddate < :nowTime");
        Query query = entityManager.createNativeQuery(sb.toString());

        if(!StringUtils.isEmpty(userId)){
            query.setParameter("userId", userId);
        }
        query.setParameter("adminid", adminid);
        //nowTime
//        query.setParameter("nowTime", nowTime);
        List objs = query.getResultList();
        if(objs!=null){
            for(Object obj:objs){
                Object[] o = (Object[]) obj;
                Course course=new Course();
                course.setFid(String.valueOf(o[0]));
                course.setName(String.valueOf(o[1]));
                course.setState(String.valueOf(o[2]));
                if(!StringUtils.isEmpty(state)){
                    course.setUuId(String.valueOf(o[3]));
                    course.setCollid(String.valueOf(o[4]));
                    String startDate="";
                    if(o[5]!=null){
                        startDate= ((Timestamp) o[5]).getTime()+"";
                        startDate= startDate.substring(0,10);
                    }
                    course.setStartDate(startDate);
                    String endDate="";
                    if(o[6]!=null){
                        endDate= ((Timestamp) o[6]).getTime()+"";
                        endDate= endDate.substring(0,10);
                    }
                    course.setEndDate(endDate);
                }
                list.add(course);
            }
        }
        return list;
    }
}
