package com.chenparty.util;

/**
 * 
 * @author winterme {@link} https://github.com/winterme
 * 
 */
public class Link<T> { // Link����ⲿ����

	/*---------------ʹ���ڲ��� ����Ϊ Node ��------------------*/
	public class Node { // Node ��ֻ��Link����ã����ڴ洢����
		// ��ǰ���������
		private Object data;
		// ��һ��Ԫ��
		private Node next;

		// ��дtoString ����
		public String toString() {
			return (String) this.data;
		}

		// ���췽��
		public Node(T object) {
			this.data = object;
		}

		// ����ڵ��ϵ(��ӽڵ�)
		// ��һ�ε��õ�ʱ�� this = Link.root Ҳ���Ǹ��ڵ�
		// ��һ�ε��õ�ʱ�� this = Link.root.next Ҳ���ǵ�һ���ӽڵ� ����һ��node�������Լ��ķ�����ʱ�� thisָ��ı�
		// �ĸ�����ø÷������ĸ����� this
		// ��һ�ε��õ�ʱ�� this = Link.root.next.next Ҳ���ǵڶ����ӽڵ�
		public void addNode(Node node) {
			if (this.next == null) {
				this.next = node;
			} else {
				this.next.addNode(node);
			}
		}

		// ���ԣ���������
		public void testAdd(Node root, Node node) {
			if (root.next == null) {
				root.next = node;
			} else {
				testAdd(root.next, node);
			}
		}

		// ��һ�ε��õ�ʱ�� this = Link.root Ҳ���Ǹ��ڵ�
		// ��һ�ε��õ�ʱ�� this = Link.root.next
		// �����ڵ�
		public void eachNode() {
			Link.this.resultArr[Link.this.foot++] = (T)this.data;
			if (this.next != null) {
				this.next.eachNode();
			}
		}

		// �����ڵ�(���ǲ��Է�������������)
		public void eachNode(T[] arr, Node root, Integer foot) {
			arr[foot++] = (T)root.data;
			if (root.next != null) {
				eachNode(arr, root.next, foot);
			}
		}

		// ����������������
		public T searchObj(int index) {
			if (Link.this.foot++ == index) {
				return (T)this.data;
			} else {
				return (T)this.next.searchObj(index);
			}
		}

		// ɾ�����ݣ�ͨ������ɾ��
		public void delete(int index, Node node) {
			if(Link.this.foot==index){
				node.next = this.next;
			}else{
				Link.this.foot++;
				this.next.delete(index, this);
			}
		}

		/**
		 * ɾ�����ݣ�ͨ������ɾ��
		 * @param object ��ǰҪɾ���Ķ���
		 * @param node ��ǰ���ýڵ�ĸ��ڵ�
		 * ���objectƥ��ɹ�����ôֱ�Ӱѵ�ǰ�ڵ�ĸ��ڵ��next,Ҳ���ǵ�ǰ�ڵ㣬�滻�ɵ�ǰ�ڵ��next ��ɾ�����
		 */
		public void delete(T object, Node node) {
			if(this.data.equals(object)){
				node.next = this.next;
			}else{
				this.next.delete(object, this);
			}
		}

		// �ж������Ƿ���ָ��������
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

		// ��ָ�����������ݽ����޸�
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

	/*-------------------����ΪLink��-----------------------*/
	// ������ڵ�
	private Node root;
	// �����������ÿ���һ���ڵ��Զ���һ������ת�������ʱ��ȷ����С
	private Integer count = 0;
	// ���巵������
	private T[] resultArr;
	// ����ű�
	private Integer foot = 0;

	// ���Ԫ��
	public boolean addEle(T object) {
		if (object == null) {
			return false;
		}
		Node node = new Node(object);
		if (this.root == null) {
			// ������ڵ�Ϊnull������û�� Node ��,��ѵ�һ���ڵ�����Ϊ���ڵ�
			this.root = node;
		} else {
			// ����Ѿ����ڸ��ڵ㣬����� Node ��addNode����
			// this.root.addNode(node);
			this.root.testAdd(root, node);
		}
		count++;
		return true;
	}

	// ��ȡLink�ĳ���
	public Integer getSize() {
		return this.count;
	}

	// �ж��Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.count == 0 && this.root == null;
	}

	// ��ȡ�������ݣ�toArray ����
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

	// ͨ��������ȡ����
	public T get(int index) {
		if (index > this.count - 1) {
			return null;
		}
		this.resultArr = (T[])this.toArray();
		return this.resultArr[index];
	}

	// ͨ��������ȡ����
	public T get2(int index) {
		if (index > this.count - 1) {
			return null;
		}
		this.foot = 0;
		return (T)this.root.searchObj(index);
	}

	// ɾ�����ݣ�ͨ������ɾ������
	public void remove(int index) {
		if(index>this.count-1){
			
		}else{
			if (index == 0) {
				this.root = this.root.next;
			}
			// �˴�footӦ��1��ʼ����ΪΪ0��ʱ��ɾ�����漰 Node �������
			this.foot = 1;
			// ע���� root.next
			this.root.next.delete(index, this.root);
			this.count--;
		}
	}

	// ɾ�����ݣ�ͨ������ɾ������
	public void remove(T object) {
		// �����ж����������Ƿ���Ҫɾ������
		if (this.containsNode(object)) {
			// ��� root��data����Ҫɾ������
			if (this.root.data.equals(object)) {
				// ��ֱ�ӽ� root �滻�� root.next��ok
				this.root = this.root.next;
			} else {
				// ������ǵ�һ��
				// ����Ĳ�����Ҫɾ���Ķ���� ��ǰ���ýڵ�ĸ��ڵ�
				this.root.next.delete(object, this.root);
			}
		}
		this.count--;
	}

	// �ж������Ƿ���ָ��������
	public boolean containsNode(T search) {
		if (search == null || this.root == null) {
			return false;
		} else {
			return this.root.searchStr(search);
		}
	}

	// �޸�����
	public void set(int index, T object) {
		if (index > this.count - 1 || index < 0) {
			return;
		}
		this.foot = 0;
		this.root.editVal(index, object);
	}

	

}

