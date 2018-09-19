package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

   protected DoubleLinkedList<T> list;
   protected int size;

   public QueueDoubleLinkedListImpl(int size) {
      this.size = size;
      this.list = new DoubleLinkedListImpl<T>();
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (element != null) {
         if (isFull()) {
            throw new QueueOverflowException();
         } else {
            this.list.insert(element);
            this.size--;
         }
      }
   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      T retorno = null;
      if (isEmpty()) {
         throw new QueueUnderflowException();
      } else {
         retorno = ((DoubleLinkedListImpl<T>) list).getHead().getData();
         size++;
         this.list.removeFirst();
      }
      return retorno;
   }

   @Override
   public T head() {
      return ((DoubleLinkedListImpl<T>) list).getHead().getData();
   }

   @Override
   public boolean isEmpty() {
      return list.isEmpty();
   }

   @Override
   public boolean isFull() {
      return this.size == 0;
   }

}
