package scut.carson_ho.androidinterview.AlgorithmLearning.Exam_6;

/**
 * Created by Carson_Ho on 17/10/21.
 */

public class UnidirectionalLinkedList<T> {

    /**
     * 设置结点结构
     */
    // a. 结点结构
    private class Node<T>{
        private T data;
        private Node<T> next ;
        public Node(T data){
            this.data = data;
        }
    }

    // b. 头结点
    public Node<T> first;
    // c. 当前结点
    private Node<T> currentNode;
    // d. 链表长度
    public int size;

    /**
     * 构造函数
     * 作用：初始化结点
     */
    public UnidirectionalLinkedList(){
        currentNode = first = null;
        size = 0;
    }

    /**
     * 1. 添加结点
     * 内容：在头 / 尾 添加结点 & 在特定位置插入结点
     */

    // a. 在链表头部加入1个结点
    // 即，把新加入的结点设置为第一结点
    public void addFirstNode(T data){

        // 1. 将需添加的内容封装成结点
        Node<T> newNode = new Node<T>(data);
        // 2. 将新添加结点的指针域指向旧第1个结点
        newNode.next = first;
        // 3. 将新添加结点设置为第1个结点
        first = newNode;
        // 4. 链表长度+1
        size++;
    }

    // b. 在链表尾部加入1个结点
    // 即，把新加入的结点设置为最后结点
    public void addNode(T data){
        // 1. 检查当前链表是否为空
        if (isEmpty()){
            addFirstNode(data);
            return;
        }
        // 2. 把当前指针定位到最后一个结点
        locateNode(size-1);
        // 3. 将需添加的内容封装成结点
        currentNode.next = new Node<T>(data);
        // 4. 链表长度+1
        size++;
    }

    // c. 在链表中插入结点
    public T insertNode(int index, T data) {
        // 1. 检查当前链表是否为空
        if (isEmpty()){
            addFirstNode(data);
            return null;
        }

        // 2. 把当前指针定位到需插入的结点位置
        locateNode(index);
        // 3. 将需添加的内容封装成结点
        Node<T> insertNode = new Node<T>(data);
        // 4. 把需插入结点位置的下1个结点 赋给 插入的结点
        insertNode.next = currentNode.next;
        // 5. 把插入结点 赋给 需插入的结点的位置
        currentNode.next = insertNode;
        // 6. 链表长度+1
        size++;
        // 7. 返回插入结点的数据
        return insertNode.data;
    }


    /**
     * 2. 删除结点
     * 内容：删除第1个结点 & 删除特定位置的结点
     */
    // a. 删除第1个结点，并返回该结点数据
    public T removeFirstNode()  {
        // 1. 检查当前链表第一个结点是否为空
        if (first == null){
            try {
                throw new Exception("链表为空！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 2. 获取被删除结点的数据
        T temp = first.data;
        // 3. 将第2个结点设置为第1个结点
        first = first.next;
        // 4. 链表长度减1
        size--;

        // 5. 返回被删除结点的数据
        return temp;
    }


    // b. 删除特定位置的结点,并将里面的数据返回
    public T removeNode(int index)  {
        // 1. 检查当前链表是否为空
        if (isEmpty()){
            try {
                throw new Exception("链表为空！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 2. 把当前指针（currentNode）定位到 需删除结点（index）的前1个结点
        locateNode(index-1);
        // 3. 获取被删除结点的数据
        T temp = currentNode.next.data;
        // 4. 将需删除结点（index）的前1个结点 的下1个结点 设置为 需删除结点（index）的下1个结点
        currentNode.next = currentNode.next.next;
        // 5. 链表长度减1
        size--;
        // 6. 返回被删除结点的数据
        return temp;
    }

    /**
     * 3. 获取特定位置的结点
     * 内容：将当前指针（currentNode）定位到所需结点位置、根据索引位置获取结点数据
     */

    // a. 将当前指针（currentNode）定位到所需结点位置
    private void locateNode(int index){
        // 1. 判断指针是否越界
        if (index <0 && index >size){
            try {
                throw new IndexOutOfBoundsException("参数越界！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int i = 0;
        // 2. 通过遍历链表，寻找索引index所指结点
        for(currentNode = first; currentNode.next != null && i < index; i++){
            currentNode = currentNode.next;
        }
    }

    // b. 根据索引位置获取结点数据
    public T getNode(int index)  {
        // 1. 判断链表是否为空
        if (isEmpty()){
            try {
                throw new Exception("链表为空！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 2. 把当前指针（currentNode）定位到 所需索引位置（index）
        locateNode(index);
        // 3. 返回当前指针的数据，即所需索引位置的数据
        return currentNode.data;
    }

    /**
     * 检查当前链表是否为空
     */
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else {
            return false;
        }
    }


    public static void main(String[] args){
        // 1. 创建链表 & 加入结点数据
        UnidirectionalLinkedList<Integer> list = new UnidirectionalLinkedList<Integer>();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.addNode(5);

        // 2. 输出当前链表数据
        System.out.println("链表数据如下：");
        for (int i = 0; i < list.size;i++){

            try {

                System.out.print(list.getNode(i)+" ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------------------");


        // 3. 获得某个位置结点的数据
        System.out.println("位置3的数据是：" + list.getNode(3));


        System.out.println("-----------------------");


        // 4. 插入结点：在位置4插入，数据 = 66
        System.out.println("在位置4插入的data："+list.insertNode(3,66));
        System.out.println("插入后：");
        for (int i = 0; i < list.size;i++){
            try {
                System.out.print(list.getNode(i)+" ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("-----------------------");

        // 5. 删除结点
        System.out.println("删除index为3的data："+list.removeNode(3));
        System.out.println("删除后：");
        for (int i = 0; i < list.size;i++){
            try {
                System.out.print(list.getNode(i)+" ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
