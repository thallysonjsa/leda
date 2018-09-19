package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int probe = 0;
			int index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
			while (probe <= table.length) {
				if (this.table[index] == null || this.table[index] == deletedElement) {
					table[index] = element;
					elements++;
					break;
				} else {
					probe++;
					index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
				}
				COLLISIONS++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && search(element) != null) {
			int probe = 0;
			int index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
			while (probe < table.length && table[index] != null) {
				if (table[index].equals(element) && table[index] != null) {
					table[index] = deletedElement;
					elements--;
				} else {
					probe++;
					index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (element != null) {
			int probe = 0;
			int index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
			while (probe < table.length && table[index] != null) {
				if (table[index].equals(element) && table[index] != null) {
					return element;
				} else {
					probe++;
					index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
				}
			}
		}
		return null;

	}

	@Override
	public int indexOf(T element) {
		int probe = 0;
		int index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		if (element != null) {
			while (probe <= table.length) {
				if (table[index].equals(element)) {
					return index;
				} else {
					probe++;
					index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
				}
			}
		}
		return -1;
	}

}
