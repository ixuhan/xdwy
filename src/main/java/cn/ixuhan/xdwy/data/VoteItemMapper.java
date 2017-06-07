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

    /**
     * 计算总投票数
     * @param voteId
     * @return
     */
    int sumRealAndFakeCount(int voteId);

    /**
     * 更新投票真实记录
     * @param count 需要新增的数目
     * @param vItemId 选项Id
     */
    void updateVoteItemRealCount(@Param("count") int count, @Param("id") int vItemId);

    int change_count(int Operate,int Count_Num,int Which);
}