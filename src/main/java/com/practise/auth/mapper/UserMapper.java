package com.practise.auth.mapper;

import com.practise.auth.dto.UserDTO;
import com.practise.auth.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
   public UserDTO fromUsertoUserDto(User user)
   {
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
       return userDTO;
   }
   public User fromUserDto(UserDTO userDTO)
   {
       User user=new User();
       BeanUtils.copyProperties(userDTO,user);
       return user;
   }

}
