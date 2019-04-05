package sample;

public interface Explorable<E> {
	
	public E[] GetNextSteps(E e);
	public E GetStart();
	public boolean IsArrived(E e);

}
