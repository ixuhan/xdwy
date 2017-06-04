package cn.ixuhan.xdwy.data;

import cn.ixuhan.xdwy.model.VoteItems;
import cn.ixuhan.xdwy.model.VoteItemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteItemsMapper {
    long countByExample(VoteItemsExample example);

    int deleteByExample(VoteItemsExample example);

    int deleteByPrimaryKey(String id);

    int insert(VoteItems record);

    int insertSelective(VoteItems record);

    List<VoteItems> selectByExample(VoteItemsExample example);

    VoteItems selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VoteItems record, @Param("example") VoteItemsExample example);

    int updateByExample(@Param("record") VoteItems record, @Param("example") VoteItemsExample example);

    int updateByPrimaryKeySelective(VoteItems record);

    int updateByPrimaryKey(VoteItems record);
}