# 线性结构和非线性结构

## 线性结构

1.  线性结构是常用的数据结构， 其特点是**数据元素之间存在一对一的线性关系**
2. 线性结构有两种不同的存储结构： **顺序存储结构和链式存储结构**
3. 顺序存储结构的表叫做顺序表， 顺序表里的元素是连续的
4. 链式存储结构的表叫做链表，链表里的元素不一定是连续的，元素节点中存放数据元素和相邻元素的地址信息
5. 常用的线性结构：数组、队列、链表、栈

## 非线性结构

1.常见的非线性结构：二维数组、多维数组、广义表、树结构、图结构



***



# 稀疏数组和队列

##  稀疏数组

1. 当一个数组中， 大部分元素是0时， 或者为同一个值的数组时， 可以使用稀疏数组来保存该数组

2. 稀疏数组的处理方法

   2.1. 记录数组一共有几行几列，一共有多少个不同的值

   2.2. 把具有不同值的行列以及值记录在一个小规模的数组中，从而缩小程序的规模

3. 稀疏数组的转换思路

​       3.1 二维数组转换为稀疏数组的思路

​            3.1.1 遍历原始二维数组， 得到行列数，以及有效值sum

​            3.1.2 创建一个稀疏数组sparse[sum+1, 3]

​            3.1.3 在第一行记录下原始数组的行列以及sum

​            3.1.4 将二维数组的有效值填充到稀疏数组中

​        3.2 稀疏数组转为二维数组的思路

​            3.2.1 先读取稀疏数组的第一行，根据第一行的数据，创建二维数组, 例如chess[11,11]

​             3.2.2 读取稀疏数组的后几行，赋值给二维数组即可

4. 相关代码：[稀疏矩阵代码](https://github.com/anon-wt/StructureAlgorithm/tree/master/src/main/java/com/study/structure/array)


## 队列

1.  基本概念
   1. 队列是有序列表，可以使用**数组**和**链表**来实现
   2. 队列特点：**先进先出**

2. 数组模拟队列思路

      队列是有序列表，如果要使用数组方式存储队列数据，则队列声明如下

   * maxArrSize 数组最大容量
   * front 数组的前端下标，头数据的前一位下标
   * rear 数组的后端下班，尾数据的当前下标
   * front = rear 数据为空 rear = maxArrSize -1 数据满 当插入一个数据时 front + 1, 当获取一个数据时 rear-1

3. [队列代码](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/queue/ArrQueueDemo.java)

   

## 环形队列

1. 重新定义：front指向队列头数据， 默认值为0

2. 重新定义：rear 指向队列尾数据后面一位，默认值为0

3. 重新定义：maxSize 最大数组长度，队列最大容量maxSize -1， 有一个作为标志位

4. 重新定义：是否为满： (rear+1) % maxSize = front 例: rear = 3, front = 0, maxSize = 4

5. 重新定义：判断为空 front == rear

6. 重新定义： 有效容量：(rear + maxSize - front) % maxSize  例：front = 0 rear=3, maxSize = 4 

7. [环形队列代码](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/queue/CircleArrQueueDemo.java)

***

# 链表

## 单链表

1.链表是以节点的方式存储， 链表是**链式存储**

2.每个节点包含**data域**和**next域**， next 指向下一个节点

3.链表的各个节点**不一定是顺序存储**

4.链表分为**带头节点链表**和**不带头节点链表**

5.代码：[单链表代码](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/linkedlist/SingleLinkedList.java)

6.面试题

* 求单链表中有效节点的个数， 代码如下：

  ```java
      public int getLength() {
          HeroNode node = headHeroNode;
          int length = 0;
          while (node.next != null) {
              length ++;
              node = node.next;
          }
          return length;
      }
  ```

* 求单链表中倒数第k个节点 ， 代码如下：

  ```java
  public HeroNode getLastIndexNode(int index) {
          // 1. 获得节点个数
          int length = getLength();
  
          if (index <= 0 || index > length) {
              System.out.printf("out of index: %d\n", index);
              throw new RuntimeException("out of index");
          }
  
          HeroNode node = headHeroNode.next;
          for (int i = 0; i < length - index; i ++) {
              node = node.next;
          }
          return node;
      }
  
  ```

* 单链表的反转， 代码如下：

  ```java
      public void reserveNode() {
          if (headHeroNode.next == null || headHeroNode.next.next == null) {
              return;
          }
          HeroNode head = new HeroNode(0, "");
          HeroNode curr = headHeroNode.next;
          HeroNode next;
          while (curr != null) {
              next = curr.next;
              curr.next = head.next;
              head.next = curr;
              curr = next;
          }
          headHeroNode.next = head.next;
      }
  ```

  

* 从尾到头打印单链表， 代码如下：

  ```java
      public void print() {
          // 打印链表
          Stack<HeroNode> heroNodes = new Stack<HeroNode>();
          HeroNode curr = headHeroNode.next;
          while (curr != null) {
              heroNodes.push(curr);
              curr = curr.next;
          }
  
          while (heroNodes.size() > 0) {
              System.out.println(heroNodes.pop());
          }
      }
      // 不推荐， 破坏了原有的链表结构
      public void print2() {
          reserveNode();
          HeroNode curr = headHeroNode.next;
          while (curr != null) {
              System.out.println(curr);
              curr = curr.next;
          }
      }
  ```

  

* 合并两个有序的单链表， 合并后的单链表依然有序， 代码如下：

  ```java
  public void union(SingleHeroNode singleHeroNode) {
      HeroNode curr = singleHeroNode.headHeroNode.next;
          HeroNode next;
          while (curr != null) {
              next = curr.next;
              addByOrder(curr);
              curr = next;
          }
      }
  
  ```

## 双向链表

1. 和单向链表对比：
   * 单向链表的查找方向只能是一个方向， 双向链表可以向前向后两个方向查找
   * 单项链表不能进行自我删除，需要提供一个temp:被删除节点的前一个， 双向链表可以进行自我删除

2. 链表的增删改查

   * **遍历** 和单项链表一样， 只是可以向前也可以向后遍历
   * **添加**（默认添加到最后）
     - 先找到双向链表的最后一个节点
     - temp.next = newHeroNode; newHeroNode.pre = tmep

   * **修改**思路和单项链表一样
   * **删除**
     - 因为是双向链表，所以我们可以实现自我删除莫格节点
     - 直接找到对应节点比如temp
     - temp.pre.next = temp.next
     - temp.next.pre = temp.pre

   * **排序添加** 和单项链表略微相同， 在其之上稍微修改即可

3. 代码如下：[双向表代码](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/linkedlist/DoubleLinkedList.java)

## 单向环形链表

1. 单向环形链表创建

   * 先创建第一个节点，让first指向该节点，并形成环形
   * 随后每创建一个节点，都将该节点加入到该环形队列中即可

2.  单项环形链表遍历：

   * 创建一个辅助变量curr 让curr 指向first ,然后 curr = curr.getNext ,直到 curr.getNext = first 结束

3. Josepfu 问题

   设编号为1，2，... n的n个人围成一圈，约定标号为k（1 <= k <= n）的人,从1开始报数，数到m的人出列，他的下一位继续从1开始报数， 数到m出列，以此类推，直到最后一个人出列位置，请输出出列标号顺序

   例如： n =5 , k =1, m =2  其出列顺序为： 2->4->1->5->3

2. Josepfu问题解题思路

   * 创建环形单向链表

   * 创建一个辅助变量helper,指向环形链表的最后一个节点
   * 在让小孩报数前，先让first和helper移动k-1次
   * 当小孩报数时， 让first和helper均移动m-1次
   * 这时就可以让first 指向的节点出队列： first = first.getNext(); helper.setNext() = first;

3. 代码:[单向环形表代码](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/linkedlist/Josepfu.java)

# 栈

## 基本原理以及实现

1. 栈是**先入后出**的有序列表

2. 栈是线性表中，元素的**插入和删除都在线性表的同一端**的线性表，允许元素插入和删除的一端叫做栈顶（top）另一端为栈底（bottom）

3. 由栈的定义可知，最先放入栈的元素在栈底，最后放入栈的元素在栈顶，而删除却恰恰相反，最先删除的是最后放入的，最后删除的是第一个放入的

4. 栈的应用场景：
   * 子程序的调用，在跳往子程序前，会将下一个命令的地址存放在栈中，直到子程序执行完毕，再将地址取出以回到程序中
   * 处理递归调用，和子程序调用类似，只是在存放下一个命令地址外也会将参数，区域变量等数据存放在栈中
   * 表达式的转换与求值
   * 二叉树的遍历
   * 图形的深度优先（depth-first）搜索法

5. 定义栈思路
   * 用数组定义栈
   * 定义一个变量为top 初始化为-1 push top ++; arr[top] = data; pop top --;

6. 代码 
   [数组栈](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/stack/ArrStackDemo.java)
   [链表栈](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/stack/LinkedStackDemo.java)


## 利用栈进行表达式计算

1. 实现步骤：

   1. 通过一个索引index来遍历表达式
   2. 如果我们发现是一个数字，就直接放入数字栈，如果我们发现是一个符号则分以下情况
      - 如果当前的符号栈为空，直接入栈
      - 如果当前的符号栈不为空，则与符号栈里的符号进行比较
        * 如果当前符号栈优先级小于等于栈内的符号，则从数栈pop出两个数字，从符号栈poo出一个符号，进行运算， 将运算的结果push数栈，并将当前的符号入符号栈
        * 如果当前的符号优先级大于栈内的符号

   3. 当扫描完毕后，再从数栈和符号栈pop出数字和符号进行运算
   4. 最后数栈中只有一个结果就是表达式的结果

2. 代码：
   [表达式计算](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/stack/ExpressionDemo.java)

   

## 前缀、中缀、后缀表达式

1. 前缀表达式

   * 前缀表达式： 又名波兰表达式， 前缀表达式的运算符位于操作数之前，
   * 前缀表达式的求值：从右至左扫描表达式，当遇到数字时，压入数字栈，遇到运算符时，弹出栈顶的两个数，用运算符对他们进行计算，重复以上过程， 直至表达式最左侧，最后运算符得到的值则为表达式结果

   * 例如：(3+4)*5-6的前缀表达式为：- * + 3 4 5 6 求值过程如下：
     * 从右至左扫描，将6 5 4 3 压入栈
     * 遇到+ 符号， 将栈顶两个元素弹出 3 ，4   然后进行计算 3+4 = 7  ,将7压入栈中
     * 遇到* 符号， 将栈顶两个元素弹出7，5   然后进行计算 7* 5 = 35， 将35压入栈中
     * 遇到- 符号， 将栈顶两个元素弹出 35 ，6 然后进行计算35 -6 = 29 即最终结果

2. 中缀表达式

   * 中缀表达式，常见的表达式： 如(3+4)*5-6
   * 中缀表达式是我们人所熟悉的表达式，但是对于计算机来说不好操作。因此在计算结果时，往往将表达式转化为其他表达式（一般转化为后缀表达式）

3. 后缀表达式，

   * 又称为逆波兰表达式，与前缀表达式相似，只是运算符在操作数之后。
   * 后缀表达式求值，从左向右扫描， 遇到数值时，将其压入栈中，遇到运算符时，则弹出栈中两个元素，用运算符对其进行计算，并将结果入栈， 重复以上过程，直到表达式最右端。最后运算出的值即为表达式的结果
   * 例如：(3+4)*5-6的前缀表达式为：3 4 + 5 * 6 - 求值过程如下：
        * 从左至右扫描，将3，4压入堆栈
        * 遇到+符号，弹出栈顶两个元素4 3  对其进行计算 3+4 = 7 ,将7 压入栈中
        * 将5入栈
        * 遇到* 符号， 弹出栈顶两个元素5 7 对其进行计算 7 * 5 = 35，将35 压入栈中
        * 将6入栈
        * 遇到-符号，弹出栈顶两个元素 6 35 对其进行计算  35 - 5 = 29 即为最终结果

4. 中缀表达式转为后缀表达式

   1. 初始化两个栈，运算符栈s1 和存贮中间结果栈 s2

   2. 从左至右扫描中缀表达式

   3. 遇到操作数值时，将其压入s2

   4. 遇到运算符时，比较其与栈内的运算符的比较优先级

      4.1 如果s1为空，或者s1栈顶运算符是“(”,则直接将该运算符直接插入到s1中

      4.2 否则，若优先级比栈顶元素优先级高，则直接将该运算符插入到s1中

      4.3 否则将s1中的元素弹出直接压入s2中，重复以上4比较步骤，与s1中的新栈顶元素比较

   5. 遇到括号时

      5.1 遇到“(”括号时，直接将括号压入s1中

      5.2 遇到“)”括号时，依次弹出s1中的运算符，压入s2，直到遇到"(",并将这一对括号舍弃

   6. 重复以上2-5步骤，直到表达式最右侧
   7. 将s1中的剩余符号依次压入s2中
   8. 依次弹出s2中的元素并输出， 结果的逆序则为中缀表达式的后缀表达式
5. [逆波兰表达式计算](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/structure/stack/PolandNotation.java)

# 递归

## 递归基本概念

1. 递归需要遵守的基本规则

* 执行一个方法时，就创建一个新的受保护的独立空间（栈空间）
* 方法的局部变量是独立的，不会相互影响
* 递归必须向退出递归的条件逼近，否则就是无限递归，死循环了
* 当一个方法执行完毕，或者遇到return,就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕。

2. 递归能解决的问题

* 各种数学问题，皇后问题，汉诺塔问题，阶乘问题，迷宫问题，球和篮子问题
* 各种算法中也会用到递归，比如快排，归并排序，二分查找，分治算法等
* 将用栈解决的问题 -》 递归代码更为简洁

## 迷宫问题解法

* 用i,j表示从地图的那个位置出发（1，1）
* 如果小球达到map(6,5) 位置，则说明通路找到
* 约定当map(i,j)为0时，表示该点未走过，当为1时，表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
* 在走迷宫时， 需要确定一个策略下-》右-》上-》左，如果哦该店走不通过，则回溯
* 代码如下： [迷宫问题](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/recursion/MigongTest.java)

## 八皇后问题解法

* 第一个皇后放在第一行第一列
* 第二个皇后放在第二行第一列，然后判断是否ok,如果不ok ，继续放在第二行第二列，第三列，依次吧所有列放完，找到合适的位置
* 继续第三个皇后，还是第一列第二列...第八列，也能放在一个不冲突的位置，算是找到一个正确解
* 当找到一个正确解时，回退到上一个栈，就开始回溯，即将第一个皇后放在第一列的所有解都找到
* 然后回头在把第一个皇后放在第二列.重复1-4，直到找到所有解
* 代码如下： [八皇后问题](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/recursion/Queue8.java)



# 排序算法

## 排序的分类

1. 内部排序：指将需要处理的所有数据都加载到内部存储器进行排序
   * 插入排序
     * **直接插入排序**
     * 希尔排序
   * 选择排序
     * **简单选择排序**
     * 堆排序
   * 交换排序
     * **冒泡排序**
     * 快速排序
   * 归并排序
   * 基数排序
2. 外部排序：数据量过大，无法全部加载到内部时，需要借助**外部存储**(如：文件存储)进行排序

## 算法的时间复杂度

1.  算法的时间复杂度：对测量一个程序执行时间的方法

   * 事后统计的方法

     1.  想要对设计的算法进行运行性能的评测，需要实际运行该算法
     2. 所得时间的统计量过度依赖该计算机的硬件和软件等环境

   * 事前统计的方法

     通过分析某个算法的**时间复杂度**来判断哪个算法更优越

2. 时间频度： 一个算法花费的时间和算法中语句的执行次数有关，哪个算法的执行次数越多，它所耗费的时间就越多。一个算法中的语句执行次数称为语句频度或事件频度记为T(n)

3. 时间复杂度：

   * 一般情况下，算法中的基本操作语句的重复执行次数是问题规模n的某个函数，用T(n)表示，若有某个辅助函数f(n)，使得当n趋近于无穷大时,T(n)/f(n)的极限值为不等于零的常数，则称f(n)是T(n)的同数量级函数，记为T(n)=O(f(n))，称为O(f(n))为算法的渐进时间复杂度，简称时间复杂度
   * T(n)不同，但时间复杂度可能相同，如：T(n)=n^2+2n+6与T(n)=3n^2+2n+2的T(n)不同，但是时间复杂度相同，记为O(n^2)
   * **计算时间复杂度的方法**：
     1. 用常数1代替运行时间中的所有加法常数
     2. 修改后的运行次数函数中，只保留最高阶
     3. 去除最高阶的系数

4. 常见的时间复杂度

   * 常数阶(O(1))

     ```java
     int i = 1;
     int j = 2;
     i ++;
     j ++;
     int m = i + j
     ```

     无论执行多少次，只要没有循环等复杂结构，那算法的时间复杂度则为O(1)

     如上述代码中，其消耗的时间不会随着某个变量的增长而增长，那么无论这段代码有多长，哪怕有十几万行，其算法复杂度永远为O(1)

   * 对数阶(O($\log_2 n$))

     ```java
     int i = 1;
     while(i < n) {
         i = i * 2;
     };
     ```

     在while循环里，每次都以**i*2**（乘法），i距离n越来越近，假设循环x次后，i 就大于n了，这时循环退出，即2的x次方=n, 那么x=$\log_2 n$,也就是说，循环$\log_2 n$次后，循环就结束了，因此代码的时间复杂度额为O($\log_2 n$), O($\log_2 n$) 这个2 是根据代码变化的，如果i*3, 则O($\log_3 n$)

     如果N=a^x(a>0, a!=1),即a的x次方等于N（a>0且a!=1）那么数字x叫做以a为底N的对数, 

     记作：x= $\log_a N$，其中a叫做对数的底数，N叫做真数，x叫做以a为底N的对数

   * 线性阶(O(n))

     ```JAVA
     for(int i = 0; i<n; i++) {
         j=i;
         j++;
     }
     ```

     在该for循环中，代码执行n遍，因此其消耗的 时间跟随n的变化而变化，所以此类代码可以使用O(n)来代表其时间复杂度

   * 线性对数阶(O($n\log_2 n$))

     ```
     for(int m = 1; m<n; m++) {
         i=1;
         while(i < n) {
             i = i * 2;
         };
     }
     ```

   ​       线性对数阶很容易理解，时间复杂度为O($\log_2 n$)的代码，循环n次，其时间复杂度则为n * O($\log_2 n$), 即O($n\log_2 n$)

   * 平方阶(O(n^2))

   ```JAVA
   for(int X = 0; X<n; X++) {
       for(int i = 0; i<n; i++) {
           j=i;
           j++;     
       }
   }
   ```

   ​     平方阶O(n^2),更容易理解了，如果把O(n)的代码再循环嵌套一边，它的 时间复杂度则为(O(n^2)，这类代码其实就是嵌套了两次的n循环，他的时间复杂度为O(n*n), 

   ​    如果将外层循环改成m,则它的 时间复杂度为O(n\*m)

   * 立方阶(O(n^3))

     参考平方阶(O(n^2)) 理解

   * k次方阶(O(n^k))

     参考平方阶(O(n^2)) 理解

   * 指数阶(O(2^n))

     ```
     int Fibonacci(int number)
     {
         if (number <= 1) return number;
         return Fibonacci(number - 2) + Fibonacci(number - 1);
     }
     ```

     表示一个算法的性能会随着输入数据的每次增加而增大两倍，典型的方法就是裴波那契数列的递归计算实现

     

5. 说明:常见的算法时间复杂度，有小到大的排列顺序为 O(1) < O($\log_2 n$) < O(n) < O($n\log_2 n$)<   O(n^2) < O(n^3) < O(n^k) < O(2^n) 随着问题n 的不断增大，时间复杂度不断增大，执行效率逐渐变低，我们尽量避免指数阶的算法

6. 平均时间复杂度，和最坏时间复杂度

   * 平均时间复杂度：所有可能输入的实例，以等概率的情况出现，该算法的运行时间

   * 最坏时间复杂度：最坏情况下时间复杂度

   * 平均时间复杂度和最坏时间复杂度是否一致和算法有关，如下图：

     | 排序算法 | 平均时间      | 最坏时间      | 稳定度 | 额外空间    | 备注                             |
     | -------- | ------------- | ------------- | ------ | ----------- | -------------------------------- |
     | 冒泡     | O(n^2)        | O(n^2)        | 稳定   | O(1)        | n小时较好                        |
     | 交换     | O(n^2)        | O(n^2)        | 不稳定 | O(1)        | n小时较好                        |
     | 选择     | O(n^2)        | O(n^2)        | 不稳定 | O(1)        | n小时较好                        |
     | 插入     | O(n^2)        | O(n^2)        | 稳定   | O(1)        | n小时较好                        |
     | 基数     | O($\log_R B$) | O($\log_R B$) | 稳定   | O(n)        | B:真数(0-9), <br />R基数(个十百) |
     | shell    | O($n\logn$)   | O(n^s) 1<s<2  | 不稳定 | O(1)        | s是所选分组                      |
     | 快速     | O($n\logn$)   | O(n^2)        | 稳定   | O($n\logn$) | n较大时好                        |
     | 归并     | O($n\logn$)   | O($n\logn$)   | 不稳定 | O(1)        | n较大时好                        |
     | 堆       | O($n\logn$)   | O($n\logn$)   | 不稳定 | O(1)        | n较大时好                        |

## 算法空间复杂度介绍

1. 简单介绍 

* 算法空间复杂度：为该算法所消耗的存储空间，也是问题n的函数，是一个算法在运算过程中，临时占用存储空间的一个度量值，有的算法需要占用的临时工作单元数与解决问题的规模n有关，随着你的增大而增大，当你较大时，占用过多的存储空间，比如：快排和归并排序算法

* 在做算法分析时，主要讨论的是**时间复杂度**，从用户的角度看，更加看中的是程序的运行速度，一些缓存产品(redis,memcache)和算法(本质是用空间换时间)

  

## 冒泡排序

1. 基本介绍
   * 通过对待序列从前往后(从下角标较小的元素开始)依次比较相邻的值，若发现逆序则交换，使值较大元素逐渐从前往后移动，就像水底下的气泡一样，逐渐往上移动
   * 在排序过程中，各元素不断接近自己的位置，**如果一趟比较下来没有任何交换，说明后面序列有序**，因此在排序过程中设置一个标志flag，判断元素是否进行过交换， 从而减少不必要的比较
   * 两次for 循环，时间复杂度为O(n^2)， 8万数据耗时大概50秒

2. 代码如下； [冒泡算法](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/sort/BubbleSort.java)

## 选择排序

1. 基本介绍

   选择排序属于内部排序，在欲排序的数据中，按照指定的规则，选出某一元素，然后再依规定交换位置后，达到交换的目的

2. 选择排序的思想
   * 第一次中从数组中选择arr[0]~arr[n-1]中选出一个最小值，然后与arr[0]交换位置
   * 第一次中从数组中选择arr[1]~arr[n-1]中选出一个最小值，然后与arr[1]交换位置
   * 依次类推，总共执行n-1次，得到一个排序从小到大的有序序列

3. 代码： [选择排序算法](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/sort/SelectSort.java)
4. 两次for 循环，时间复杂度为O(n^2),  但是耗时比冒泡选择法更少，8万数据耗时大概3秒

## 插入排序

1. 基本介绍

   插入排序属于内部排序，对于欲排序的元素以插入的方式寻找该元素的合适位置，以达到排序的目的

2. 插入排序思路

   * 把n个带排序的 元素，看成一个有序表和 一个无序表
   * 开始时，有序表中只包含一个元素，无序表中包含n-1个元素
   * 排序过程中每次从无序表中取出第一个元素，将其与有序表中依次进行比较，将其插入到有序表中恰当的位置，从而形成一个新的有序表
   * 时间复杂度为O(n^2), 8万数据耗时大概5秒

3. 代码如下： [插入排序算法](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/sort/InsertSort.java)

# shell排序

1. 基本介绍

   ​        对于插入排序， 当需要插入的数较小时，后移的次数明显增多，对效率影响很大，shell 排序也是一种插入排序，它是在简单插入排序经过改进之后的一个更高的版本， 也成为缩小增量排序

2. 基本思想

   ​        把记录按下标的一定增量分组，对每组使用直接插入排序算法排序，随着增量的不断减少，每组包含的关键词越来越多，当增量减值1，整个文件被分成了一组，算法便终止

3. 两种方法：

   * [交换shell排序算法](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/sort/ShellSort.java)
   * [移位shell排序算法](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/sort/ShellSort.java)

# 快速排序

1. 基本介绍

   快速排序是对冒泡排序的一种改进

2. 基本思想

   通过一趟排序将排序的数据分割为独立的两部分，其中一部分的所有数据都比另外的一部分的所有数据都要小，然后再按此方案对这狼部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列

3. 代码

   [快速排序算法](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/sort/QuickSort.java)

