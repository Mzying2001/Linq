# Linq

简易的Linq

### 示例

```java
import com.mzying2001.linq.*;

public class Test {
    public static void main(String[] args) {

        //要查询的数组
        Integer[] arr = {54, 32, 63, 25, 67, 12, 43, 74, 234, 563, 112};

        //使用Linq，获取arr中的偶数并将每个数乘3.3
        for (var item :
                Linq.from(arr).where(i -> i % 2 == 0).select(i -> i * 3.3)) {
            //此处item类型是Double
            System.out.println(item);
        }

    }
}

/*
    运行结果：
    178.2
    105.6
    39.599999999999994
    244.2
    772.1999999999999
    369.59999999999997
 */
```

