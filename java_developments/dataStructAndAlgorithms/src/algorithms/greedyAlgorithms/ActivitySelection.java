package algorithms.greedyAlgorithms;

/*
主要涉及到以下几个方面的内容：

①什么是活动选择问题---粗略提下，详细请参考《算法导论》
②活动选择问题的DP(Dynamic programming)求解--DP求解问题的思路
③活动选择问题的贪心算法求解
④为什么这个问题可以用贪心算法求解？
⑤动态规划与贪心算法的一些区别与联系
⑥活动选择问题的DP求解的JAVA语言实现以及时间复杂度分析
⑦活动选择问题的Greedy算法JAVA实现和时间复杂度分析
⑧一些有用的参考资料

①活动选择问题

给定N个活动，以及它们的开始时间和结束时间，求N个活动中，最大兼容的活动个数。比如：

活动 i:          1     2     3      4.....
开始时间 si:     1      3     0      5....
结束时间 fi:     4      5     6       7.....

活动1的开始时间s(1)=1，结束时间f(1)=4，它与活动2是不兼容的。因为，活动1还没有结束，活动2就开始了(s(2) < f(1))。
活动2 与 活动4 是兼容的。因为，活动2的进行区间是[3,5) 而活动4的进行区间是[5,7)
目标是：在N个活动中，找出最大兼容的活动个数。

②活动选择问题的DP(Dynamic programming)求解

1）建模
活动 i 用 a(i)来表示，开始时间用 s(i)表示，结束时间用 f(i)表示，所有活动的集合为S
定义一个合适的子问题空间，设 S(i,j) 是与 a(i) 和 a(j)兼容的活动集合。S(i,j)={a(k), a(k) belongs to S: f(i)<=s(k)<f(k)<=s(j)}

2）问题一般化(不是很理解)
这里第一个活动和最后一个活动有点特殊。为了完整表示问题，构造两个虚拟的活动: a(0) 和 a(n+1)
其中,s(0)=f(0)=0，s(n+1)=f(n+1)=Integer.MAX_VALUE
于是，S=S(0,n+1)，从N个活动中找出最大兼容的活动，就转化成了求解 S(0,n+1)集合中包含的最多元素个数

3）子问题分析
假设所有的活动都按结束时间递增排序。子问题空间就是 从S(i,j)中选择最大兼容活动子集，即max{S(i,j)}

max{S(i,j)}表示与 a(i) a(j) 兼容的最大活动集合。称为为S(i,j)的解

假设 a(k)是 S(i,j)的解包含的一个活动。S(i,j)就分解为 max{S(i,k)} + max{S(k,j)}+1

从这里可以看到，将原问题分解成了两个子问题。原问题就是：求解与活动 a(i) a(j) 兼容的最大活动个数，即max{S(i,j)}

而子问题则是：max{S(i,k)}  和  max{S(k,j)}

设A(i,j)就是S(i,j)的解。那么，A(i,j)=A(i,k) U A(k,j) U {a(k)}

A(0,n+1)就是我们所求的整个问题的最优解。

4）子问题的 选择个数 分析

设c[i,j]为S(i,j)中最大兼容子集中的活动数，S(i,j)为空集时，c[i,j]=0，这是显而易见的。因为S(i,j)中都没有活动嘛，更别谈什么兼容活动了呀。

若 i>=j，c[i,j]=0。这个也很好理解，因为它不符合常识。因为，我们假设活动是以结束时间来递增排序的，在S(i,j)中，是f(i)<s(j)的。那 i 就不会大于 j

毕竟一个活动它不可能 即在 某个活动之前结束，又在该活动之后开始。哈哈。。。。。

前面提到 ：假设 a(k)是 S(i,j)的解包含的一个活动。S(i,j)就分解为 max{S(i,k)} + max{S(k,j)}+1

这意味着，求S(i,j)的最优解，就需要知道 S(i,k) 和 S(k,j) 的最优解。那关键是怎么知道 S(i,k) 和 S(k,j) 的最优解呢？

答案是：一个 一个 地尝试。k 的取值范围是 (i,j)，遍历(i,j)内所有的值，计算 S(i,k) 和 S(k,j)的解。就可以找到S(i,j)的最优解了。

 因此，当S(i,j)不为空时，c[i,j] = max{c[i,k] + c[k,j] + 1}  其中, k belongs to (i,j)  a(k) belongs to S(i,j)

下面，就是DP中的状态转移方程(递归表达式)，根据它，就可以写代码实现了。
c[i][j] = 0 , S(i,j) is a empty set
          max(c[i][k] + c[k][j] + 1), i<k<j, S(i,j) is not a empty set.
从上面分析可以看出：原问题分解成了两个子问题，要解决原问题，一共有 j-i+1中选择，然后一 一遍历求出所有的选择。这就是动态规划的特点，先分析最优子问题，然后再做选择。

③活动选择问题的贪心算法求解

所谓贪心算法，就是每次在做选择时，总是先选择具有相同特征的那个解，即“贪心”解。在这里，“贪心”的那个解则是： 结束时间最早的那个活动

具体步骤是怎样的呢？

第一步：先对活动按照结束时间进行排序。因为我们总是优先选择结束时间最早的活动的嘛。排序之后，方便选择嘛。。。

第二步：按照贪心原则 选中一个活动，然后排除 所有与该活动 有冲突的活动。

第三步：继续选择下一个活动。其实，第二步与第三步合起来就是：每次都选结束时间最早的活动，但是后面选择的活动不能与前面选择的活动有冲突。

从这里可以看出，贪心算法是在原问题上先做贪心选择，然后得到一个子问题，再求解子问题。（求解子问题的过程，就是一个不断贪心选择的过程）

 

④为什么这个问题可以用贪心算法求解？

看了贪心算法之后，就会有疑问？凭什么这样选就能得到最优解啊？或者说，这样做到底对不对？

别急嘛，我们可以用数学来证明这样做是正确的。而且从这个证明过程中，可以窥出动态规划与贪心算法的区别。

对于活动选择问题而言：当可用贪心算法解时，贪心的效率要比动态规划高。为什么要高呢？后面再详细讲。

这个证明具体可参考《算法导论》上的证明。它的大致证明过程就是：

当选择了贪心解时(结束时间最小的活动)，也是将原问题划分成了两个子问题，但是其中一个子问题是空的，而我们只需要考虑另一个非空的子问题就可以了。

具体而言就是：假设 a(m) 是 S(i,j)中具有最早结束时间的那个活动，那按照我们的贪心选择，我们肯定会选择a(m)的嘛。选了a(m)之后，就将问题分解成了两个子问题:S(i,m) 和 S(m,j)。前面提到，活动是按结束时间排序了的，而现在a(m)又是最早结束的活动，因为，S(i,m)就是个空集，而我们只需要考虑S(m,j)

但是，这里有个重大的疑问还未解决---凭什么说 a(m) 就是 S(i,j)的最优解中的活动呢？或者说凭什么 活动m 就是最大兼容活动集合中的活动？

这里就用到经常用来证明贪心算法正确性的一个技巧---剪枝。关于这个技巧，可参考一篇博文：漫谈算法（一）如何证明贪心算法是最优

对于活动选择问题，咱就来简要证明下吧。。。其实还是《算法导论》中讲的证明，只不过我又复述一遍罢了。

慢着，我们要证明的是啥？再说一遍：凭什么说 a(m) 就是 S(i,j)的最优解中的活动呢？，我们证明的就是：a(m)是S(i,j)的最优解中的元素，即a(m)是S(i,j)最大兼容活动子集中的活动。

设A(i,j)是S(i,j)的最大兼容活动子集---也就是说，在所有与 活动a(i) 和 活动a(j) 相兼容的活动中，A(i,j)含有的活动个数最多。

将A(i,j)中的活动按结束时间递增排序。设a(k)是A(i,j)中的第一个活动。若a(k)=a(m)，那没话说了。a(m)就是a(k)嘛，那a(m)肯定在A(i,j)中噻

若a(k) != a(m)，这说明A(i,j)中的第一个元素(活动)不是a(m)。那我们可以运用剪枝思想，剪掉A(i,j)中的第一个活动a(k)，再把活动a(m)贴到A(i,j)里面去。

这样，A(i,j)中的活动个数还是没有变化---少了个a(k)，加了个a(m)啊

那么，可能你就会问了，凭什么能把 a(m)贴到 A(i,j)里面去啊？？？？？我们可以这样想想：a(k)是A(i,j)中的第一个活动，那为什么a(k)可以在A(i,j)中呢？

废话！上面带下划线且加粗的的都说了假设 a(k)是A(i,j)中的第一个活动了啊！！

其实，这不是本质 ，本质就是：a(k)是与 a(i) 和 a(j)兼容的活动啊，而且没有和A(i,j)中的其他活动冲突啊！因为，S(i,j)的解 就是求与 a(i) 和 a(j)兼容的一组活动啊，而A(i,j)就是这样的一组活动且它是最大的(活动个数最多)，能够放在A(i,j)中的活动，它一定是与a(i) 和 a(j) 兼容的。

那么，再回到a(m)，a(m)同样也具有 ”本质“ 中提到的两个性质：❶a(m)是与a(i) 和 a(j) 兼容的活动  ❷a(m)没有与A(i,j)中其他活动冲突。

下面来说明下为什么 a(m)没有与A(i,j)中其他活动冲突？因为a(k)是没有与A(i,j)中的其他活动冲突的，而a(m)又是S(i,j)中结束时间最早的活动

故：，完成时间：f(m)<f(k) ，a(m)都比a(k)更早完成，而a(k)都没有与A(i,j)中的其他活动冲突，那a(m)就更不可能与A(i,j)中的其他活动冲突了。

整个思路就是：在证明中先考察一个全局最优解，然后证明可以对该解加以修改(比如运用“剪枝”技巧)，使其采用贪心选择(将贪心的那个选择贴上去)，这个选择将原问题变成一个相似的、但更小的问题。

终于完成了证明。好累。

 

⑤动态规划与贪心算法的一些区别与联系

这里只针对活动选择问题作一下比较。其他的我也不懂。

a)动态规划是先分析子问题，再做选择。而贪心算法则是先做贪心选择，做完选择后，生成了子问题，然后再去求解子问题。

b)从 a) 中可以看出，动态规划是自底向上解决问题，而贪心算法则是自顶向下解决问题。

c)动态规划每一步可能会产生多个子问题，而贪心算法每一步只会产生一个子问题。（比如这里的贪心算法产生了“二个”子问题，但是其中一个是空的。）


*/
import java.util.ArrayList;
import java.util.Arrays;

public class ActivitySelection {

	public static void main(String[] args) {
		int[] start = { 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12 };
		int[] finish = { 5, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16 }; // finish已按照升序排序
		// int[] start = { 12, 2, 8, 8, 6, 5, 3, 5, 0, 3, 1 };
		// int[] finish = { 16, 14, 12, 11, 10, 9, 9, 7, 6, 5, 5 };
		ArrayList<Integer> index = new ArrayList<Integer>();

		index = maxCompatiableActivity_1(start, finish, start.length);
		System.out.println("最大兼容活动个数为：" + index.size());
		System.out.println("具体的活动为第" + index.toString() + "个活动");
		System.out.println("--------------------------------------------------");
		index = maxCompatiableActivity_2(start, finish, start.length);
		System.out.println("最大兼容活动个数为：" + index.size());
		System.out.println("具体的活动为第" + index.toString() + "个活动");
		System.out.println("--------------------------------------------------");
		index = maxCompatiableActivity_21(start, finish, start.length);
		System.out.println("最大兼容活动个数为：" + index.size());
		System.out.println("具体的活动为第" + index.toString() + "个活动");
		System.out.println("--------------------------------------------------");		
	}

	/*
	 * 方法一： 动态规划算法 DP时间复杂度与问题的个数以及每个问题的选择数 有关。 比如这里的 S(i,j)一共大约有N^2个， 因为
	 * 1=<j<=N， 1=<i<j ， 这里求和大约是 (N^2)/2（对于S(i,j) i>j没有实际意义嘛）,每个S(i,j)一共有 j-i+1种
	 * 选择 故时间复杂度为O(N^3)
	 */
	public static ArrayList<Integer> maxCompatiableActivity_1(int[] start, int[] finish, int n) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		int[] s = new int[n + 2];
		int[] f = new int[n + 2];

		s[0] = f[0] = Integer.MIN_VALUE;
		s[n + 1] = s[n + 1] = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			s[i] = start[i - 1];
			f[i] = finish[i - 1];
		}

		int[][] c = new int[n + 2][n + 2]; // 记录多少个活动
		int[][] position = new int[n + 2][n + 2];// 记录参加由哪些活动
		for (int j = 0; j <= n + 1; j++) {
			for (int i = n + 1; i >= j; i--) {
				c[i][j] = 0; // if i>=j, Sij is a empty set
			}
		}

		int maxTemp = 0;
		for (int j = 1; j <= n + 1; j++) {
			for (int i = 0; i < j; i++) {
				for (int k = i + 1; k < j; k++) {
					if (s[k] >= f[i] && f[k] <= s[j]) {
						// 如果Sij不为空
						if (c[i][k] + c[k][j] + 1 > maxTemp) {
							maxTemp = c[i][k] + c[k][j] + 1;
							position[i][j] = k;
						}
					}
				}
				c[i][j] = maxTemp;
			}
		}

		for (int i : position[0]) {
			if (i > 0) {
				index.add(i);
			}
		}

		// for (int[] i : position) {
		// System.out.println(Arrays.toString(i) + '\n');
		// }
		// System.out.println("--------------------");
		// for (int[] i : c) {
		// System.out.println(Arrays.toString(i) + '\n');
		// }

		return index;
	}

	// 贪心算法：迭代非递归
	public static ArrayList<Integer> maxCompatiableActivity_2(int[] start, int[] finish, int n) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		index.add(1);
		int k = 0;
		for (int i = 1; i < n; i++) {
			if (start[i] >= finish[k]) {
				index.add(i + 1);
				k = i;
			}
		}
		return index;
	}

	// 贪心算法：递归
	public static ArrayList<Integer> maxCompatiableActivity_21(int[] start, int[] finish, int n) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		index.add(1);
		recursive(start, finish, 0, n, index);
		return index;
	}

	private static void recursive(int[] start, int[] finish, int i, int n,ArrayList<Integer> index) {
		for(int j = i;j<n;j++){
			if (start[j] >= finish[i]) {
				index.add(j+1);
				recursive(start, finish, j, n, index);
				break;
			}
		}
	}
}
