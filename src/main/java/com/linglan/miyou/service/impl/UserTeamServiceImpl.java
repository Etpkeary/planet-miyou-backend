package com.linglan.miyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linglan.miyou.model.domain.UserTeam;
import com.linglan.miyou.service.UserTeamService;
import com.linglan.miyou.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author LingLan
* @description 针对表【user_team(用户队伍关系表)】的数据库操作Service实现
* @createDate 2022-11-29 14:10:31
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




