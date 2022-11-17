package mapper;

import org.apache.ibatis.annotations.Param;
import org.yyyy.Users;

import java.util.List;

public interface UserMapper {
    //查询用户全部信息
    List<Users> getAll();
    //根据主键查询用户id
    Users getById(Integer id);
    //根据主键id更新信息
    int update(Users users);
    //根据用户名模糊查询
   List<Users> getByName(String name);
    //增加用户
    int addOneUser (Users users);
    //根据主键删除用户
    int delOneUser(Integer id);
    //模糊用户名查询和地址查询
    List<Users> getByNameOrAddress(
            @Param("columnName")
            String columnName,
            @Param("columnValue")
            String columnValue
    );


}
