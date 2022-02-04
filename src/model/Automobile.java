//============================================================================
// Name        : Assignment 1
// Author      : Branden Lee
// Date        : 4/24/2018
// Description : Automobile class for the KBB website application
//============================================================================
package model;

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.*;

import exception.AutoException;

/** @class Automobile
 *        The generic automobile container containing possible option sets and
 *        options. \n
 *        Also contains option choices. **/
public class Automobile implements java.io.Serializable {
	private static final long serialVersionUID = 1362422403381823640L;
	private String makeName, modelName, year;
	private double basePrice; // double is not an exact decimal.
	ArrayList<OptionSet> optionSetList;
	ArrayList<Integer> optionSetOptionChoice;
	ArrayList<String> optionSetNameReserved;

	/* Constructor */
	public Automobile() {
		init();
		optionSetList = new ArrayList<OptionSet>();
		optionSetOptionChoice = new ArrayList<Integer>();
	}

	public Automobile(int size) {
		init();
		optionSetList = new ArrayList<OptionSet>(size);
		optionSetOptionChoice = new ArrayList<Integer>(size);
	}

	public void init() {
		makeName = "";
		modelName = "";
		year = "";
		basePrice = 0;
		/* These option set names are reserved for automobile attribute names. */
		optionSetNameReserved = new ArrayList<String>();
		optionSetNameReserved.add("Make");
		optionSetNameReserved.add("Model");
		optionSetNameReserved.add("Year");
		optionSetNameReserved.add("Retail Price");
	}

	/* Getter */
	// Get Name of Automotive
	public synchronized String getMake() {
		return makeName;
	}

	public synchronized String getModel() {
		return modelName;
	}

	public synchronized String getYear() {
		return year;
	}

	// Get Automotive Base Price
	public synchronized double getPrice() {
		return basePrice;
	}

	// Get OptionSet (by index value)

	/** get optionSet object by index
	 * @param OptionSetIndex optionSet index
	 * @return OptionSet object if found and null if not */
	private synchronized OptionSet getOptionSet(int OptionSetIndex) {
		OptionSet optionSetObject = null;
		try {
			optionSetObject = optionSetList.get(OptionSetIndex);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Intentional ArrayIndexOutOfBoundsException from getOptionSet");
			//e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Intentional Exception from getOptionSet");
			//e.printStackTrace();
		}
		return optionSetObject;
	}

	public synchronized int length() {
		return optionSetList.size();
	}

	/** Get the optionSet option name choice by name
	 * @param optionSetName optionSet name
	 * @return null if not found and option name if found */
	public synchronized String getOptionSetChoiceName(String optionSetName) {
		String returnValue = null;
		OptionSet.Option optionObject = null;
		int optionSetIndex;
		try {
			optionSetIndex = findOptionSetIndex(optionSetName);
			optionObject = getOptionSetChoiceByIndex(optionSetIndex);
		} catch (exception.AutoException e) {
			// nothing
		}
		if (optionObject != null) {
			returnValue = optionObject.getName();
		}
		return returnValue;
	}

	/** Get the optionSet option price choice by name
	 * @param optionSetName optionSet name
	 * @return null if not found and option price if found */
	public synchronized Double getOptionSetChoicePrice(String optionSetName) {
		Double returnValue = null;
		OptionSet.Option optionObject = null;
		int optionSetIndex;
		try {
			optionSetIndex = findOptionSetIndex(optionSetName);
			optionObject = getOptionSetChoiceByIndex(optionSetIndex);
		} catch (exception.AutoException e) {
			// nothing
		}
		if (optionObject != null) {
			returnValue = optionObject.getPrice();
		}
		return returnValue;
	}

	/** Get the optionSet option choice by index
	 * @param optionSetIndex optionSet index
	 * @return null if not found and option object if found */
	private synchronized OptionSet.Option getOptionSetChoiceByIndex(int optionSetIndex) throws AutoException {
		OptionSet.Option returnValue = null;
		int optionIndex;
		try {
			optionIndex = optionSetOptionChoice.get(optionSetIndex).intValue();
			returnValue = getOptionSet(optionSetIndex).getOption(optionIndex);
		} catch (IndexOutOfBoundsException e) {
			throw new exception.AutoException(800);
		} catch (NullPointerException e) {
			throw new exception.AutoException(801);
		}
		return returnValue;
	}

	/** Get the optionSet option name by name
	 * @param optionSetName optionSet name
	 * @param optionName option name
	 * @return null if reserved or not found and option name if found */
	public synchronized String getOptionSetOptionName(String optionSetName, String optionName) {
		String returnValue = null;
		OptionSet optionSetObject = findOptionSet(optionSetName);
		if (optionSetObject != null) {
			// First check reserved attributes then the get optionSet option if not reserved
			if (!isOptionSetReserved(optionSetObject)) {
				OptionSet.Option optionObject = optionSetObject.findOption(optionName);
				if (optionObject != null) {
					returnValue = optionObject.getName();
				}
			}
		}
		return returnValue;
	}

	/** Get the optionSet option price by name
	 * @param optionSetName optionSet name
	 * @param optionName option name
	 * @return null if reserved or not found and option price if found */
	public synchronized Double getOptionSetOptionPrice(String optionSetName, String optionName) {
		Double returnValue = null;
		OptionSet optionSetObject = findOptionSet(optionSetName);
		if (optionSetObject != null) {
			// First check reserved attributes then the get optionSet option if not reserved
			if (!isOptionSetReserved(optionSetObject)) {
				OptionSet.Option optionObject = optionSetObject.findOption(optionName);
				if (optionObject != null) {
					returnValue = optionObject.getPrice();
				}
			}
		}
		return returnValue;
	}

	/** Is the automobile attributes from the reserved attribute list
	 * @param optionSetObject optionSet object
	 * @param optionName option name (automobile attribute value)
	 * @return true if reserved and false in not */
	public synchronized boolean isOptionSetReserved(OptionSet optionSetObject) {
		boolean returnValue = false;
		if (optionSetObject != null) {
			if (isOptionSetNameReserved(optionSetObject.getName())) {
				returnValue = true;
			}
		}
		return returnValue;
	}

	/** Is the optionSet name from the reserved optionSet name list
	 * @param optionSetName optionSet name
	 * @return true if reserved and false in not */
	public synchronized boolean isOptionSetNameReserved(String optionSetName) {
		boolean returnValue = false;
		if (optionSetNameReserved.contains(optionSetName)) {
			returnValue = true;
		}
		return returnValue;
	}

	/* Find */
	/** find optionSet object by name
	 * @param optionSetName optionSet name
	 * @return OptionSet object if found and null if not */
	private synchronized OptionSet findOptionSet(String optionSetName) {
		OptionSet optionSetObject = null;
		int optionSetIndex = findOptionSetIndex(optionSetName);
		if (optionSetIndex != -1) {
			optionSetObject = getOptionSet(optionSetIndex);
		}
		return optionSetObject;
	}

	private synchronized OptionSet.Option findOptionSetOption(int OptionSetIndex, String optionName) {
		OptionSet.Option optionObject = null;
		OptionSet optionSetObject = getOptionSet(OptionSetIndex);
		if (optionSetObject != null) {
			optionObject = optionSetObject.findOption(optionName);
		}
		return optionObject;
	}

	/** find the optionSet by name and Option by name
	 * @param OptionSetName optionSet name
	 * @return -1 if not found and option set index position if found **/
	public synchronized int findOptionSetIndex(String optionSetName) {
		int returnValue, i, n;
		returnValue = -1;
		n = optionSetList.size();
		for (i = 0; i < n; i++) {
			if (optionSetList.get(i).getName().equals(optionSetName)) {
				returnValue = i;
				break;
			}
		}
		return returnValue;
	}

	/** find the optionSet by name and Option by name
	 * @param OptionSetIndex optionSet index
	 * @param optionName option name
	 * @return -1 if not found and option index position if found **/
	public synchronized int findOptionSetOptionIndex(int OptionSetIndex, String optionName) {
		int returnIndex = -1;
		OptionSet optionSetObject = getOptionSet(OptionSetIndex);
		if (optionSetObject != null) {
			returnIndex = optionSetObject.findOptionIndex(optionName);
		}
		return returnIndex;
	}

	/** Add new optionSet
	 * @param OptionSetName optionSet name
	 * @return -1 on failure and index position on success
	 * @throws AutoException **/
	public synchronized int addOptionSet(String OptionSetName) throws AutoException {
		if (OptionSetName.equals("")) {
			throw new exception.AutoException(802);
		}
		int returnValue = -1;
		if (!isOptionSetNameReserved(OptionSetName)) {
			if (optionSetList.add(new OptionSet(OptionSetName, 10))) {
				returnValue = optionSetList.size() - 1;
				optionSetOptionChoice.add(0);
			}
		}
		return returnValue;
	}

	/** Add option by optionSet Index
	 * @post option added
	 * @param optionSetIndex optionSet index
	 * @param optionName name of added option
	 * @param optionPrice price of added option
	 * @return -1 if option set reserved and option index position if option
	 *         added **/
	public synchronized int addOptionSetOption(int optionSetIndex, String optionName, double optionPrice) {
		int indexReturn = -1;
		OptionSet optionSetObject = getOptionSet(optionSetIndex);
		if (optionSetObject != null) {
			// First set reserved attributes then the optionSet option if not reserved
			if (!setOptionSetOptionNameReserved(optionSetObject.getName(), optionName)) {
				indexReturn = optionSetObject.addOption(optionName, optionPrice);
			}
		}
		return indexReturn;
	}

	/* Setter */
	/** Set make name
	 * @post make name set
	 * @param name make name **/
	public synchronized void setMake(String name) {
		makeName = name;
	}

	public synchronized void setModel(String name) {
		modelName = name;
	}

	public synchronized void setYear(String name) {
		year = name;
	}

	// Set Base Price
	public synchronized void setPrice(double price_) {
		basePrice = price_;
	}

	public synchronized boolean setOptionSetName(String optionSetName, String nameNew) {
		System.out.println("Method: setOptionSetName, update optionSet=" + optionSetName + " to " + nameNew);
		boolean returnValue = false;
		OptionSet optionSetObject = findOptionSet(optionSetName);
		if (optionSetObject != null) {
			optionSetObject.setName(nameNew);
			returnValue = true;
		}
		return returnValue;
	}

	/** Sets the automobile attributes from the reserved attribute list
	 * @param optionSetObject optionSet object
	 * @param optionName option name (automobile attribute value)
	 * @return true if reserved and false in not */
	public synchronized boolean setOptionSetOptionNameReserved(String optionSetName, String optionName) {
		boolean returnValue = false;
		if (optionSetNameReserved.contains(optionSetName)) {
			returnValue = true;
		}
		if (optionSetName.equals("Make")) {
			setMake(optionName);
		} else if (optionSetName.equals("Model")) {
			setModel(optionName);
		} else if (optionSetName.equals("Year")) {
			setYear(optionName);
		} else if (optionSetName.equals("Retail Price")) {
			setPrice(Double.parseDouble(optionName));
		}
		return returnValue;
	}

	/** Sets the option name in an option set by name
	 * @param optionSetObject optionSet name
	 * @param optionName option name
	 * @param optionName option new name (automobile attribute value)
	 * @return true if reserved or option set and false in not found */
	public synchronized boolean setOptionSetOptionName(String optionSetName, String optionName, String nameNew) {
		System.out.println("Method: setOptionSetOptionName, optionSet=" + optionSetName + " update option=" + optionName
			+ " to " + nameNew);
		boolean returnValue = false;
		// First set reserved attributes then the optionSet option if not reserved
		if (!setOptionSetOptionNameReserved(optionSetName, nameNew)) {
			OptionSet optionSetObject = findOptionSet(optionSetName);
			if (optionSetObject != null) {
				// not reserved option set name
				OptionSet.Option optionObject = optionSetObject.findOption(optionName);
				if (optionObject != null) {
					optionObject.setName(nameNew);
					returnValue = true;
				}
			}
		} else {
			returnValue = true;
		}
		return returnValue;
	}

	/** Sets the option price in an option set by name
	 * @param optionSetObject optionSet name
	 * @param optionName option name
	 * @param optionName option new price (automobile attribute value)
	 * @return true if reserved or option set and false in not found */
	public synchronized boolean setOptionSetOptionPrice(String optionSetName, String optionName, double priceNew) {
		boolean returnValue = false;
		// First set reserved attributes then the optionSet option if not reserved
		if (!setOptionSetOptionNameReserved(optionSetName, Double.toString(priceNew))) {
			OptionSet optionSetObject = findOptionSet(optionSetName);
			if (optionSetObject != null) {
				// not reserved option set name
				OptionSet.Option optionObject = optionSetObject.findOption(optionName);
				if (optionObject != null) {
					optionObject.setPrice(priceNew);
					returnValue = true;
				}
			}
		} else {
			returnValue = true;
		}
		return returnValue;
	}

	public synchronized boolean setOptionSetChoiceByIndex(int optionSetIndex, int optionIndex) throws AutoException {
		boolean returnValue = false;
		try {
			optionSetOptionChoice.set(optionSetIndex, optionIndex);
			returnValue = true;
		} catch (IndexOutOfBoundsException e) {
			throw new exception.AutoException(800);
		}
		return returnValue;
	}

	public synchronized boolean setOptionSetChoice(String optionSetName, String optionName) {
		System.out.println("Method: setOptionSetChoice, choose optionSet=" + optionSetName + " option=" + optionName);
		boolean returnValue = false;
		int optionSetIndex, optionIndex;
		try {
			optionSetIndex = findOptionSetIndex(optionSetName);
			optionIndex = findOptionSetOptionIndex(optionSetIndex, optionName);
			setOptionSetChoiceByIndex(optionSetIndex, optionIndex);
			returnValue = true;
		} catch (exception.AutoException e) {
			// nothing
		}
		return returnValue;
	}

	/* print() and toString() */
	public synchronized void print() {
		System.out.print(toString());
	}

	public synchronized String toString() {
		StringBuffer stringBufferObject;
		int i, n;
		n = length();
		stringBufferObject = new StringBuffer("");
		stringBufferObject.append("Year Make Model: ").append(getYear()).append(" ").append(getMake()).append(" ")
			.append(getModel()).append(" with Base Price: $").append(getPrice());
		stringBufferObject.append(System.getProperty("line.separator"));
		for (i = 0; i < n; i++) {
			stringBufferObject.append(optionSetList.get(i).toString()).append(System.getProperty("line.separator"));
		}
		return stringBufferObject.toString();
	}

	protected class OptionSet implements java.io.Serializable {
		private static final long serialVersionUID = 5846223453457830887L;
		ArrayList<Option> optionList;
		private String optionSetName;

		/* Constructor */
		protected OptionSet() {
			init();
			optionList = new ArrayList<Option>();
		}

		protected OptionSet(String name, int size) {
			init();
			optionSetName = name;
			optionList = new ArrayList<Option>(12);
		}

		protected void init() {
			optionSetName = "";
		}

		/* Getter */
		protected String getName() {
			return optionSetName;
		}

		/* get option by option index */
		protected Option getOption(int optionIndex) throws AutoException {
			Option optionObject = null;
			try {
				optionObject = optionList.get(optionIndex);
			} catch (IndexOutOfBoundsException e) {
				throw new exception.AutoException(803);
			}
			return optionObject;
		}

		protected int length() {
			return optionList.size();
		}

		/** Find option by name
		 * @param optionName option name
		 * @return null if not found and option object if found */
		protected Option findOption(String optionName) {
			Option optionObject = null;
			for (int i = 0; i < optionList.size(); i++) {
				try {
					if (optionList.get(i).getName().equals(optionName)) {
						optionObject = optionList.get(i);
					}
				} catch (NullPointerException e) {
					/* According to Carnegie Mellon University Software Engineering Institute You
					 * should not catch a null pointer exception. BUT WE WILL FOR THE SAKE OF THE
					 * ASSIGNMENT! */
					break;
				}
			}
			return optionObject;
		}

		/** Find option by name
		 * @param optionName option name
		 * @return -1 if not found and position index if found */
		protected int findOptionIndex(String optionName) {
			int returnValue, i, n;
			returnValue = -1;
			n = optionList.size();
			for (i = 0; i < n; i++) {
				try {
					if (optionList.get(i).getName().equals(optionName)) {
						returnValue = i;
						break;
					}
				} catch (NullPointerException e) {
					/* According to Carnegie Mellon University Software Engineering Institute You
					 * should not catch a null pointer exception. BUT WE WILL FOR THE SAKE OF THE
					 * ASSIGNMENT! */
					break;
				}
			}
			return returnValue;
		}

		/* Setter */
		protected void setName(String name) {
			optionSetName = name;
		}

		/** add an option
		 * @param optionName option name
		 * @param optionPrice option price **/
		protected int addOption(String optionName, double optionPrice) {
			optionList.add(new Option(optionName, optionPrice));
			return optionList.size();
		}

		/* print() and toString() */
		public void print() {
			System.out.print(toString());
		}

		public String toString() {
			StringBuffer stringBufferObject;
			int i, n;
			n = length();
			stringBufferObject = new StringBuffer("");
			stringBufferObject.append(getName()).append(": ");
			for (i = 0; i < n; i++) {
				try {
					stringBufferObject.append(getOption(i).toString());
				} catch (AutoException e) {
					// nothing
				}
				if (i < n - 1) {
					stringBufferObject.append(", ");
				}
			}
			return stringBufferObject.toString();
		}

		protected class Option implements java.io.Serializable {
			private static final long serialVersionUID = 2272307185575003314L;
			private String optionName;
			private double price;

			/* Constructor */
			protected Option() {
				optionName = "";
				price = 0;
			}

			protected Option(String name, double price_) {
				optionName = name;
				price = price_;
			}

			/* Getter */
			protected String getName() {
				return optionName;
			}

			protected double getPrice() {
				return price;
			}

			/* Setter */
			protected void setName(String name) {
				optionName = name;
			}

			protected void setPrice(double price_) {
				price = price_;
			}

			/* print() and toString() */
			public void print() {
				System.out.print(toString());
			}

			public String toString() {
				StringBuffer stringBufferObject;
				stringBufferObject = new StringBuffer("");
				stringBufferObject.append(getName()).append(" for $").append(getPrice());
				return stringBufferObject.toString();
			}
		}
	}
}
