package sample;

import java.util.List;

public interface Explorable<E> {
	
	public List<E> getNextSteps(E e);
	public E getStart();
	public E getGoal();
	public boolean isArrived(E e);

}
