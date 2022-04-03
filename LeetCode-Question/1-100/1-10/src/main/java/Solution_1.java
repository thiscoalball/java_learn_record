import javax.swing.text.html.HTML;
import java.util.*;

public class Solution_1 {

    public int[] twoSum(int[] nums, int target) {
        int index = 0;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (index = i + 1; index < nums.length; index++) {
                if (target == nums[i] + nums[index]) {
                    result[0] = i;
                    result[1] = index;
                    return result;
                }
            }
        }
        return result;
    }
    //利用哈希查找的时间复杂度
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //如果存在一个答案值为 目标值-当前值的   也就是   target = x + y;
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
