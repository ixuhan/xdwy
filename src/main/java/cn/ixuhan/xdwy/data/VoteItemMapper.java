package cn.ixuhan.xdwy.data;

import cn.ixuhan.xdwy.model.VoteItem;
import cn.ixuhan.xdwy.model.VoteItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteItemMapper {
    long countByExample(VoteItemExample example);

    int deleteByExample(VoteItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoteItem record);

    int insertSelective(VoteItem record);

    List<VoteItem> selectByExample(VoteItemExample example);

    VoteItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoteItem record, @Param("example") VoteItemExample example);

    int updateByExample(@Param("record") VoteItem record, @Param("example") VoteItemExample example);

    int updateByPrimaryKeySelective(VoteItem record);

    int updateByPrimaryKey(VoteItem record);
}