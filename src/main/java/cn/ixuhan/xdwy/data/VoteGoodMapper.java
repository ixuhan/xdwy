package cn.ixuhan.xdwy.data;

import cn.ixuhan.xdwy.model.VoteGood;
import cn.ixuhan.xdwy.model.VoteGoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteGoodMapper {
    long countByExample(VoteGoodExample example);

    int deleteByExample(VoteGoodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoteGood record);

    int insertSelective(VoteGood record);

    List<VoteGood> selectByExample(VoteGoodExample example);

    VoteGood selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoteGood record, @Param("example") VoteGoodExample example);

    int updateByExample(@Param("record") VoteGood record, @Param("example") VoteGoodExample example);

    int updateByPrimaryKeySelective(VoteGood record);

    int updateByPrimaryKey(VoteGood record);
}