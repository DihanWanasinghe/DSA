package Queue;

public class Queue {
    private int size ;
    private  int [] queueArray;
    private int front = 0;
    private int last = 0;
    private int count = 0;

    public Queue(int size) {
        this.size = size;
        this.queueArray = new int[size];
    }

    public void enqueue(int value){
        if( count == size){
             throw new IllegalStateException("Queue is full");
        }
        queueArray[last] = value;
        last = (last + 1) % size;
        count++;

    }
    public int  dequeue(){
        if(count == 0){
            throw new IllegalStateException("Queue is empty");
        }
        int value = queueArray[front];
        front = (front + 1) % size;
        count--;

        return  value;
    }


//without count version - not mine

//    public class CircularQueueNoCount {
//        private int[] queue;
//        private int front, rear, capacity;
//
//        public CircularQueueNoCount(int size) {
//            capacity = size + 1; // +1 because one slot is unused
//            queue = new int[capacity];
//            front = 0;
//            rear = 0;
//        }
//
//        public void enqueue(int value) {
//            if (isFull()) {
//                throw new IllegalStateException("Queue is full");
//            }
//            queue[rear] = value;
//            rear = (rear + 1) % capacity;
//        }
//
//        public int dequeue() {
//            if (isEmpty()) {
//                throw new IllegalStateException("Queue is empty");
//            }
//            int value = queue[front];
//            front = (front + 1) % capacity;
//            return value;
//        }
//
//        public boolean isEmpty() {
//            return front == rear;
//        }
//
//        public boolean isFull() {
//            return (rear + 1) % capacity == front;
//        }
//    }

}
