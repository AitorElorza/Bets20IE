package iterator;

import java.util.Iterator;
import java.util.Vector;

import domain.Event;



public class ExtendedIteratorEvents implements ExtendedIterator {

	private Vector<Event> events;
	private int position =0;

	public ExtendedIteratorEvents(Vector<Event> events2) {
		this.events = events2;
	}
	
	@Override
	public boolean hasNext() {

		return position<events.size();
	}

	@Override
	public Object next() {
		Object o = events.get(position);
		position++;
		return o;
	}

	@Override
	public Object previous() {
		Object o=events.get(position);
		position--;
		return o;

	}



	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return position>-1;
	}

	@Override
	public void goFirst() {
		position =0;

	}

	@Override
	public void goLast() {
		
		position =events.size()-1;
	

	}

}
