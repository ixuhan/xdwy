import cn.ixuhan.xdwy.service.VoteItemService;
import cn.ixuhan.xdwy.service.VoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Hank.
 * User: Hank
 * Date: 2016/11/8 0008
 * Time: 18:34
 * Des: 数据库测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"}) //加载配置
public class test01 {
    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteItemService voteItemService;

    @Test
    public void test1(){
        System.out.println(voteService.getMaxVoteItemCount(1));
        Map<String, Integer> parameterMap = new HashMap<String, Integer>();
        parameterMap.put("Operate", 1);
        parameterMap.put("Count_Num", 1);
        parameterMap.put("Which", 1);

        System.out.println(voteItemService.change_count(1, 1, 1));
        System.out.println("nihao");
    }

}
