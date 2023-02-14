package com.linglan.miyou.service;

import com.linglan.miyou.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linglan.miyou.model.domain.User;
import com.linglan.miyou.model.domain.dto.TeamQuery;
import com.linglan.miyou.model.domain.request.TeamJoinRequest;
import com.linglan.miyou.model.domain.request.TeamQuitRequest;
import com.linglan.miyou.model.domain.request.TeamUpdateRequest;
import com.linglan.miyou.model.domain.vo.TeamUserVO;

import java.util.List;

/**
* @author LingLan
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2022-11-29 14:10:24
*/
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     * @param team
     * @param loginuser
     * @return
     */
    Long addTeam(Team team, User loginuser);

    /**
     * 搜索队伍
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 更新队伍
     * @param teamUpdateRequest
     * @param loginUser
     * @return
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);

    /**
     * 加入队伍
     * @param teamJoinRequest
     * @return
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

    /**
     * 退出队伍
     * @param teamQuitRequest
     * @param loginUser
     * @return
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    /**
     * 删除（解散）队伍
     * @param id
     * @param loginUser
     * @return
     */
    boolean deleteTeam(long id, User loginUser);
}
