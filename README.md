# Linq

简易的Linq

### 示例

```java
import com.mzying2001.linq.*;

public class Test {
    public static void main(String[] args) {

        //要查询的数组
        int[] arr = {54, 32, 63, 25, 67, 12, 43, 74, 234, 563, 112};

        //使用Linq，获取arr中的偶数，排序后将每个数乘3.1
        var temp = Linq.from(arr).where(i -> i % 2 == 0).orderBy(i -> i).select(i -> i * 3.1);

        //输出结果，此处item类型是Double
        for (var item : temp) {
            System.out.println(item);
        }

    }
}

/*
    运行结果：
    37.2
    99.2
    167.4
    229.4
    347.2
    725.4
 */
```

