package com.chenparty.util;

/**
 * 
 * @author winterme {@link} https://github.com/winterme
 * 
 */
public class Link<T> { // Link类给外部调用

	/*---------------使用内部类 以下为 Node 类------------------*/
	public class Node { // Node 类只给Link类调用，用于存储数据
		// 当前保存的数据
		private Object data;
		// 下一个元素
		private Node next;

		// 重写toString 方法
		public String toString() {
			return (String) this.data;
		}

		// 构造方法
		public Node(T object) {
			this.data = object;
		}

		// 处理节点关系(添加节点)
		// 第一次调用的时候 this = Link.root 也就是根节点
		// 第一次调用的时候 this = Link.root.next 也就是第一个子节点 又是一个node，调用自己的方法的时候 this指向改变
		// 哪个类调用该方法，哪个就是 this
		// 第一次调用的时候 this = Link.root.next.next 也就是第二个子节点
		public void addNode(Node node) {
			if (this.next == null) {
				this.next = node;
			} else {
				this.next.addNode(node);
			}
		}

		// 测试，方便理解的
		public void testAdd(Node root, Node node) {
			if (root.next == null) {
				root.next = node;
			} else {
				testAdd(root.next, node);
			}
		}

		// 第一次调用的时候 this = Link.root 也就是根节点
		// 第一次调用的时候 this = Link.root.next
		// 遍历节点
		public void eachNode() {
			Link.this.resultArr[Link.this.foot++] = (T)this.data;
			if (this.next != null) {
				this.next.eachNode();
			}
		}

		// 遍历节点(还是测试方法，方便理解的)
		public void eachNode(T[] arr, Node root, Integer foot) {
			arr[foot++] = (T)root.data;
			if (root.next != null) {
				eachNode(arr, root.next, foot);
			}
		}

		// 根据索引查找数据
		public T searchObj(int index) {
			if (Link.this.foot++ == index) {
				return (T)this.data;
			} else {
				return (T)this.next.searchObj(index);
			}
		}

		// 删除数据，通过索引删除
		public void delete(int index, Node node) {
			if(Link.this.foot==index){
				node.next = this.next;
			}else{
				Link.this.foot++;
				this.next.delete(index, this);
			}
		}

		/**
		 * 删除数据，通过数据删除
		 * @param object 当前要删除的对象
		 * @param node 当前调用节点的父节点
		 * 如果object匹配成功，那么直接把当前节点的父节点的next,也就是当前节点，替换成当前节点的next 就删除完成
		 */
		public void delete(T object, Node node) {
			if(this.data.equals(object)){
				node.next = this.next;
			}else{
				this.next.delete(object, this);
			}
		}

		// 判断里面是否有指定的数据
		public boolean searchStr(T search) {
			if (this.data.equals(search)) {
				return true;
			} else {
				if (this.next != null) {
					return this.next.searchStr(search);
				} else {
					return false;
				}
			}
		}

		// 对指定索引的数据进行修改
		public void editVal(int index, T val) {
			if (Link.this.foot++ == index) {
				this.data = val;
			} else {
				if (this.next != null) {
					this.next.editVal(index, val);
				}
			}
		}

	}

	/*-------------------以下为Link类-----------------------*/
	// 定义根节点
	private Node root;
	// 定义计数器，每添加一个节点自动加一，方便转成数组的时候确定大小
	private Integer count = 0;
	// 定义返回数组
	private T[] resultArr;
	// 定义脚标
	private Integer foot = 0;

	// 添加元素
	public boolean addEle(T object) {
		if (object == null) {
			return false;
		}
		Node node = new Node(object);
		if (this.root == null) {
			// 如果根节点为null，代表还没有 Node 类,则把第一个节点设置为根节点
			this.root = node;
		} else {
			// 如果已经存在根节点，则调用 Node 的addNode函数
			// this.root.addNode(node);
			this.root.testAdd(root, node);
		}
		count++;
		return true;
	}

	// 获取Link的长度
	public Integer getSize() {
		return this.count;
	}

	// 判断是否为空
	public boolean isEmpty() {
		return this.count == 0 && this.root == null;
	}

	// 获取所有内容，toArray 方法
	public Object[] toArray() {
		if (this.count == 0) {
			return resultArr;
		}
		this.foot = 0;
		resultArr = (T[])new Object[count];
		// root.eachNode(resultArr, root, 0);
		root.eachNode();
		return resultArr;
	}

	// 通过索引获取数据
	public T get(int index) {
		if (index > this.count - 1) {
			return null;
		}
		this.resultArr = (T[])this.toArray();
		return this.resultArr[index];
	}

	// 通过索引获取数据
	public T get2(int index) {
		if (index > this.count - 1) {
			return null;
		}
		this.foot = 0;
		return (T)this.root.searchObj(index);
	}

	// 删除数据，通过索引删除数据
	public void remove(int index) {
		if(index>this.count-1){
			
		}else{
			if (index == 0) {
				this.root = this.root.next;
			}
			// 此处foot应从1开始，因为为0的时候删除不涉及 Node 类的事情
			this.foot = 1;
			// 注意是 root.next
			this.root.next.delete(index, this.root);
			this.count--;
		}
	}

	// 删除数据，通过数据删除数据
	public void remove(T object) {
		// 首先判断链表里面是否含有要删的数据
		if (this.containsNode(object)) {
			// 如果 root的data就是要删的数据
			if (this.root.data.equals(object)) {
				// 则直接将 root 替换成 root.next就ok
				this.root = this.root.next;
			} else {
				// 如果不是第一个
				// 传入的参数是要删除的对象和 当前调用节点的父节点
				this.root.next.delete(object, this.root);
			}
		}
		this.count--;
	}

	// 判断里面是否有指定的数据
	public boolean containsNode(T search) {
		if (search == null || this.root == null) {
			return false;
		} else {
			return this.root.searchStr(search);
		}
	}

	// 修改数据
	public void set(int index, T object) {
		if (index > this.count - 1 || index < 0) {
			return;
		}
		this.foot = 0;
		this.root.editVal(index, object);
	}

	

}

