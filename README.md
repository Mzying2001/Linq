# Linq

Java 实现的 Linq

### 示例1

```java
//要查询的数组
int[] arr = {54, 32, 63, 25, 67, 12, 43, 74, 234, 563, 112};

//使用Linq，获取arr中的偶数，排序后将每个数乘3.1
var temp = Linq.from(arr)
               .where(i -> i % 2 == 0)
               .orderBy(i -> i)
               .select(i -> i * 3.1);

//输出结果
System.out.println(temp);
```

运行结果：

```
[37.2, 99.2, 167.4, 229.4, 347.2, 725.4]
```

### 示例2

```java
//测试数据
Team[] teams = new Team[]{
        //        id    time  score   name
        new Team(1001,   39,    7,   "小组1"),
        new Team(1002,   25,    8,   "小组2"),
        new Team(1003,   34,   10,   "小组3"),
        new Team(1004,   40,    8,   "小组4"),
        new Team(1005,   41,    9,   "小组5"),
        new Team(1006,   40,    8,   "小组6"),
        new Team(1007,   40,    9,   "小组7"),
};

//对名单进行排序：
//先按照分数降序排序，分数相同则按照时间升序排序，都相同则按照小组id排序
var temp = Linq.from(teams)
               .orderByDescending(t -> t.score)
               .thenBy(t -> t.time)
               .thenBy(t -> t.id)
               .select(t -> t.name)
               .toList();

//输出排序后的名单
for (int i = 0; i < temp.size(); i++) {
    System.out.printf("第%d名: %s\n", i + 1, temp.get(i));
}
```

运行结果：

```
第1名: 小组3
第2名: 小组7
第3名: 小组5
第4名: 小组2
第5名: 小组4
第6名: 小组6
第7名: 小组1
```

