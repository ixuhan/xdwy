package cn.ixuhan.xdwy.data;

import cn.ixuhan.xdwy.model.VoteComment;
import cn.ixuhan.xdwy.model.VoteCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteCommentMapper {
    long countByExample(VoteCommentExample example);

    int deleteByExample(VoteCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoteComment record);

    int insertSelective(VoteComment record);

    List<VoteComment> selectByExample(VoteCommentExample example);

    VoteComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoteComment record, @Param("example") VoteCommentExample example);

    int updateByExample(@Param("record") VoteComment record, @Param("example") VoteCommentExample example);

    int updateByPrimaryKeySelective(VoteComment record);

    int updateByPrimaryKey(VoteComment record);
}