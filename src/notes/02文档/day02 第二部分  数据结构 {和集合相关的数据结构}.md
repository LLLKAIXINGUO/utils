# day02 第二部分  数据结构 {和集合相关的数据结构}

#### 数据结构——栈         ：先进后出  {入口和出口在同一侧}

#### 数据结构——队列      ：先进先出{入口和出口不在同一侧}

#### 数据结构——数组       ： 查询快        增删慢

#### 数据结构——链表{单向、双向}        ： 查询慢      增删快

#### 数据机构 ——红黑树

#### 

元素进入到栈中 ———— 入栈 （压栈）

元素从栈中出去 ———— 出栈  （弹栈）



**数组：**

* 查询快：数组的地址是连续的，我们通过数组的首地址，可以找到数组，通过数组的索引可以快速查找某一个元素
* 增删慢：数组的长度是固定的，我们想要增加/删除一个元素，必须创建一个新数组，把源数组中的数据复制过来

**链表**

* 查询慢：链表中的地址不是连续的，每次查询元素，都必须从头开始查询
* 增删快：链表结构，增加/删除一个元素，对链表的整体结构没有影响，所以增删快

链表中的每一个元素也称为一个节点，一个节点包含了一个数据源（存储数据），两个指针域（存储地址）



**单向链表：**链表中只有一条链子，不能保证元素的顺序（存储元素和取出元素的顺序可能不一致） 【无序】

**双向链表：**链表中有两条链子，有一条链子是专门记录元素的顺序，是一个有序的集合                     【有序】

**二叉树：分支不能超过两个**

**排序树（查找树）：在二叉树的基础上，元素是有顺序的**【左子树小，右子树大】

**平衡树：左孩子和右孩子相等**

**不平衡树：左孩子和右孩子不相等**

**红黑树： 特点：趋近于平衡树，==查询的速度非常的快==，==查询叶子节点最大次数和最小次数不能超过二倍==**

**红黑树的约束**

1. ==节点可以是红色的或者是黑色的==
2. ==根节点是黑色的==
3. ==叶子节点（空节点）是黑色的==
4. ==每个红色的节点的子节点都是黑色的==
5. ==任何一个节点到其每一个叶子节点的所有路径上黑色节点树相同==

