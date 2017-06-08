package cn.ixuhan.xdwy.data;

import cn.ixuhan.xdwy.model.VcommentGood;
import cn.ixuhan.xdwy.model.VcommentGoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VcommentGoodMapper {
    long countByExample(VcommentGoodExample example);

    int deleteByExample(VcommentGoodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VcommentGood record);

    int insertSelective(VcommentGood record);

    List<VcommentGood> selectByExample(VcommentGoodExample example);

    VcommentGood selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VcommentGood record, @Param("example") VcommentGoodExample example);

    int updateByExample(@Param("record") VcommentGood record, @Param("example") VcommentGoodExample example);

    int updateByPrimaryKeySelective(VcommentGood record);

    int updateByPrimaryKey(VcommentGood record);
}