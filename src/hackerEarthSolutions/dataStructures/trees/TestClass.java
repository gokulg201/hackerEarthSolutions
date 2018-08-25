//$Id$
package hackerEarthSolutions.dataStructures.trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
 
public class TestClass {
	FastScanner in;
	PrintWriter out;
 
	Random rnd = new Random(123);
 
	class Node {
		long priority = rnd.nextLong();
		int value, sum, sz;
		boolean needReverse;
		Node left, right;
 
		Node(int value) {
			this.value = value;
			this.sz = 1;
			this.sum = value;
		}
 
		@Override
		public String toString() {
			return "Node [priority=" + priority + ", value=" + value + ", sum="
					+ sum + ", sz=" + sz + ", needReverse=" + needReverse + "]";
		}
 
		void updateValues() {
			sum = value + sum(left) + sum(right);
			sz = 1 + sz(left) + sz(right);
		}
 
	}
 
	Node merge(Node f, Node s) {
		if (f == null || s == null) {
			return f == null ? s : f;
		}
		upd(f);
		upd(s);
		if (f.priority < s.priority) {
			f.right = merge(f.right, s);
			f.updateValues();
			return f;
		} else {
			s.left = merge(f, s.left);
			s.updateValues();
			return s;
		}
	}
 
	Node[] split(Node node, int leftSize) {
		if (leftSize == 0) {
			return new Node[] { null, node };
		}
		upd(node);
		if (sz(node.left) >= leftSize) {
			Node[] tmp = split(node.left, leftSize);
			node.left = tmp[1];
			node.updateValues();
			return new Node[] { tmp[0], node };
		}
		Node[] tmp = split(node.right, leftSize - sz(node.left) - 1);
		node.right = tmp[0];
		node.updateValues();
		return new Node[] { node, tmp[1] };
	}
 
	int sz(Node n) {
		if (n == null) {
			return 0;
		}
		return n.sz;
	}
 
	int sum(Node n) {
		if (n == null) {
			return 0;
		}
		return n.sum;
	}
 
	void upd(Node n) {
		if (n.needReverse) {
			Node tmp = n.left;
			n.left = n.right;
			n.right = tmp;
			n.needReverse = false;
			if (n.left != null) {
				n.left.needReverse = !n.left.needReverse;
			}
			if (n.right != null) {
				n.right.needReverse = !n.right.needReverse;
			}
		}
	}
 
	Node reverse(Node n, int from, int to) {
		Node[] tmp = split(n, from);
		Node[] tmp2 = split(tmp[1], to - from + 1);
		tmp2[0].needReverse = true;
		return merge(tmp[0], merge(tmp2[0], tmp2[1]));
	}
 
	int getSum(Node n, int size) {
		if (n == null) {
			return 0;
		}
		upd(n);
		if (size >= sz(n)) {
			return sum(n);
		}
		if (size <= 0) {
			return 0;
		}
		int res = getSum(n.left, size);
		size -= sz(n.left);
		if (size > 0) {
			res += n.value;
		}
		res += getSum(n.right, size - 1);
		return res;
	}
 
	ArrayList<Integer>[] g;//Paths given
	int[] h;//height of vertices
	int[] sz;//size of vertices
	int[][] up;
	final int MAX = 20;
 
	int[] q;// ????
 
	void go(int vv, int par) {
		int n = sz.length;
		q = new int[n];
		q[0] = vv;
		int qIt = 0, qSz = 1;
		up[0][vv] = vv;
		h[vv] = 0;
		while (qIt < qSz) {
			int v = q[qIt++];
			int p = up[0][v];
			for (int i = 1; i < MAX; i++) {
				up[i][v] = up[i - 1][up[i - 1][v]];
			}
			for (int to : g[v]) {
				if (to == p) {
					continue;
				}
				h[to] = h[v] + 1;
				q[qSz++] = to;
				up[0][to] = v;
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			int v = q[i];
			sz[v] = 1;
			for (int to : g[v]) {
				if (to != up[0][v]) {
					sz[v] += sz[to];
				}
			}
		}
	}
 
	int[] a;
 
	int lca(int x, int y) {
		if (h[x] < h[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		for (int i = MAX - 1; i >= 0; i--) {
			if (h[up[i][x]] >= h[y]) {
				x = up[i][x];
			}
		}
		for (int i = MAX - 1; i >= 0; i--) {
			if (up[i][x] != up[i][y]) {
				x = up[i][x];
				y = up[i][y];
			}
		}
		if (x == y) {
			return x;
		}
		return up[0][x];
	}
 
	int goUp(int v, int h) {
		for (int i = MAX - 1; i >= 0; i--) {
			if (((1 << i) & h) != 0) {
				v = up[i][v];
			}
		}
		return v;
	}
 
	int[] endOfPath;
	int[] startOfPath;
	Node[] pathNode;
 
	void _go2(int vv, int pp) {
		startOfPath[vv] = vv;
		endOfPath[vv] = vv;
		for (int v : q) {
			startOfPath[v] = v;
			int p = up[0][v];
			for (int to : g[v]) {
				if (to == p) {
					continue;
				}
				if (sz[to] * 2 >= sz[v]) {
					endOfPath[to] = endOfPath[v];
				} else {
					endOfPath[to] = to;
				}
			}
		}
		for (int i = q.length - 1; i >= 0; i--) {
			int v = q[i];
			int p = up[0][v];
			for (int to : g[v]) {
				if (to == p) {
					continue;
				}
				if (sz[to] * 2 >= sz[v]) {
					startOfPath[v] = startOfPath[to];
				}
			}
			int root = endOfPath[v];
			pathNode[root] = merge(pathNode[root], new Node(a[v]));
		}
	}
 
	int getSumPathUp(int v, int cnt) {
		if (cnt == 0) {
			return 0;
		}
		int maxH = h[startOfPath[v]], minH = h[endOfPath[v]];
		int hereSize = Math.min(cnt, h[v] - minH + 1);
		int result = 0;
		if (hereSize < cnt) {
			result += getSumPathUp(up[0][endOfPath[v]], cnt - hereSize);
		}
		Node node = pathNode[endOfPath[v]];
		result += getSum(node, maxH - h[v] + hereSize)
				- getSum(node, maxH - h[v]);
		return result;
	}
 
	int getSum(int v, int u) {
		int lca = lca(u, v);
		return getSumPathUp(v, h[v] - h[lca] + 1)
				+ getSumPathUp(u, h[u] - h[lca]);
	}
 
	void generatePath(int v, int cnt, ArrayList<Integer> allV,
			ArrayList<Node> leftPart, ArrayList<Node> rightPart,
			ArrayList<Node> mainPart, ArrayList<Integer> sizes) {
		if (cnt == 0) {
			return;
		}
		int maxH = h[startOfPath[v]], minH = h[endOfPath[v]];
		int hereSize = Math.min(cnt, h[v] - minH + 1);
		Node node = pathNode[endOfPath[v]];
		sizes.add(hereSize);
		allV.add(endOfPath[v]);
		Node[] tmp = split(node, maxH - h[v]);
		Node[] tmp2 = split(tmp[1], hereSize);
		leftPart.add(tmp[0]);
		rightPart.add(tmp2[1]);
		mainPart.add(tmp2[0]);
		generatePath(up[0][endOfPath[v]], cnt - hereSize, allV, leftPart,
				rightPart, mainPart, sizes);
	}
 
	void reverse(int v, int u) {
		int lca = lca(v, u);
		if (u != lca) {
			int tmp = goUp(u, h[u] - h[lca] - 1);
			if (endOfPath[tmp] != tmp) {
				tmp = v;
				v = u;
				u = tmp;
			}
		}
		ArrayList<Integer> allVFirst = new ArrayList<>();
		ArrayList<Integer> sizesFirst = new ArrayList<>();
		ArrayList<Node> leftPartFirst = new ArrayList<>();
		ArrayList<Node> rightPartFirst = new ArrayList<>();
		ArrayList<Node> mainPartFirst = new ArrayList<>();
		generatePath(v, h[v] - h[lca] + 1, allVFirst, leftPartFirst,
				rightPartFirst, mainPartFirst, sizesFirst);
		ArrayList<Integer> allVSecond = new ArrayList<>();
		ArrayList<Integer> sizesSecond = new ArrayList<>();
		ArrayList<Node> leftPartSecond = new ArrayList<>();
		ArrayList<Node> rightPartSecond = new ArrayList<>();
		ArrayList<Node> mainPartSecond = new ArrayList<>();
		generatePath(u, h[u] - h[lca], allVSecond, leftPartSecond,
				rightPartSecond, mainPartSecond, sizesSecond);
		Node node = null;
		for (Node x : mainPartFirst) {
			node = merge(node, x);
		}
		for (int i = mainPartSecond.size() - 1; i >= 0; i--) {
			Node x = mainPartSecond.get(i);
			x.needReverse = !x.needReverse;
			node = merge(node, x);
		}
		node.needReverse = !node.needReverse;
		for (int i = 0; i < mainPartFirst.size(); i++) {
			Node[] tmp = split(node, sizesFirst.get(i));
			pathNode[allVFirst.get(i)] = merge(leftPartFirst.get(i),
					merge(tmp[0], rightPartFirst.get(i)));
			node = tmp[1];
		}
		for (int i = mainPartSecond.size() - 1; i >= 0; i--) {
			Node[] tmp = split(node, sizesSecond.get(i));
			tmp[0].needReverse = !tmp[0].needReverse;
			pathNode[allVSecond.get(i)] = merge(leftPartSecond.get(i),
					merge(tmp[0], rightPartSecond.get(i)));
			node = tmp[1];
		}
	}
 
	void solve() {
		int n = in.nextInt();
		int qq = in.nextInt();
		a = new int[n];
		g = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
			g[i] = new ArrayList<>();
		}
		for (int i = 0; i + 1 < n; i++) {
			int fr = in.nextInt() - 1, to = in.nextInt() - 1;
			g[fr].add(to);
			g[to].add(fr);
		}
		h = new int[n];
		h[0] = -1;
		sz = new int[n];
		up = new int[MAX][n];
		go(0, 0);// Something like to initiate things
		pathNode = new Node[n];
		startOfPath = new int[n];
		endOfPath = new int[n];
		_go2(0, 0);
		for (int q = 0; q < qq; q++) {
			if (in.next().equals("R")) {
				int u = in.nextInt() - 1, v = in.nextInt() - 1;
				reverse(u, v);
			} else {
				int u = in.nextInt() - 1, v = in.nextInt() - 1;
				out.println(getSum(u, v));
			}
		}
	}
 
	void run() {
		try {
			in = new FastScanner(new File("object.in"));
			out = new PrintWriter(new File("object.out"));
 
			solve();
 
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	void runIO() {
 
		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);
 
		solve();
 
		out.close();
	}
 
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
 
		public FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
 
		public FastScanner(InputStream f) {
			br = new BufferedReader(new InputStreamReader(f));
		}
 
		String next() {
			while (st == null || !st.hasMoreTokens()) {
				String s = null;
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (s == null)
					return null;
				st = new StringTokenizer(s);
			}
			return st.nextToken();
		}
 
		boolean hasMoreTokens() {
			while (st == null || !st.hasMoreTokens()) {
				String s = null;
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (s == null)
					return false;
				st = new StringTokenizer(s);
			}
			return true;
		}
 
		int nextInt() {
			return Integer.parseInt(next());
		}
 
		long nextLong() {
			return Long.parseLong(next());
		}
 
		double nextDouble() {
			return Double.parseDouble(next());
		}
	}
 
	public static void main(String[] args) {
		new TestClass().runIO();
	}
}
