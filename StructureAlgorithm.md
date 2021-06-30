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

3. 迷宫问题解法
   * 用i,j表示从地图的那个位置出发（1，1）
   * 如果小球达到map(6,5) 位置，则说明通路找到
   * 约定当map(i,j)为0时，表示该点未走过，当为1时，表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
   * 在走迷宫时， 需要确定一个策略下-》右-》上-》左，如果哦该店走不通过，则回溯
   * 代码如下： [迷宫问题](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/recursion/MigongTest.java)

4. 八皇后问题解法
   * 第一个皇后放在第一行第一列
   * 第二个皇后放在第二行第一列，然后判断是否ok,如果不ok ，继续放在第二行第二列，第三列，依次吧所有列放完，找到合适的位置
   * 继续第三个皇后，还是第一列第二列...第八列，也能放在一个不冲突的位置，算是找到一个正确解
   * 当找到一个正确解时，回退到上一个栈，就开始回溯，即将第一个皇后放在第一列的所有解都找到
   * 然后回头在把第一个皇后放在第二列.重复1-4，直到找到所有解
   * 代码如下： [八皇后问题](https://github.com/anon-wt/StructureAlgorithm/blob/master/src/main/java/com/study/algorithm/recursion/Queue8.java)

