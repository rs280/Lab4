
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
	public String getMake() {
		return makeName;
	}

	public String getModel() {
		return modelName;
	}

	public String getYear() {
		return year;
	}

	// Get Automotive Base Price
	public double getPrice() {
		return basePrice;
	}

	// Get OptionSet (by index value)

	/** get optionSet object by index
	 * @param OptionSetIndex optionSet index
	 * @return OptionSet object if found and null if not */
	private OptionSet getOptionSet(int OptionSetIndex) {
		OptionSet optionSetObject = null;
		try {
			// System.out.println(OptionSetIndex); Testing purposes only
			optionSetObject = optionSetList.get(OptionSetIndex);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return optionSetObject;
	}

	public int length() {
		return optionSetList.size();
	}

	/** Get the optionSet option name choice by name
	 * @param optionSetName optionSet name
	 * @return null if not found and option name if found */
	public String getOptionSetChoiceName(String optionSetName) {
		String returnValue = null;
		Option optionObject = null;
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
	public Double getOptionSetChoicePrice(String optionSetName) {
		Double returnValue = null;
		Option optionObject = null;
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
	private Option getOptionSetChoiceByIndex(int optionSetIndex) throws AutoException {
		Option returnValue = null;
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
	public String getOptionSetOptionName(String optionSetName, String optionName) {
		String returnValue = null;
		OptionSet optionSetObject = findOptionSet(optionSetName);
		if (optionSetObject != null) {
			// First check reserved attributes then the get optionSet option if not reserved
			if (!isOptionSetReserved(optionSetObject)) {
				Option optionObject = optionSetObject.findOption(optionName);
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
	public Double getOptionSetOptionPrice(String optionSetName, String optionName) {
		Double returnValue = null;
		OptionSet optionSetObject = findOptionSet(optionSetName);
		if (optionSetObject != null) {
			// First check reserved attributes then the get optionSet option if not reserved
			if (!isOptionSetReserved(optionSetObject)) {
				Option optionObject = optionSetObject.findOption(optionName);
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
	public boolean isOptionSetReserved(OptionSet optionSetObject) {
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
	public boolean isOptionSetNameReserved(String optionSetName) {
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
	private OptionSet findOptionSet(String optionSetName) {
		OptionSet optionSetObject = null;
		int optionSetIndex = findOptionSetIndex(optionSetName);
		if (optionSetIndex != -1) {
			optionSetObject = getOptionSet(optionSetIndex);
		}
		return optionSetObject;
	}

	private Option findOptionSetOption(int OptionSetIndex, String optionName) {
		Option optionObject = null;
		OptionSet optionSetObject = getOptionSet(OptionSetIndex);
		if (optionSetObject != null) {
			optionObject = optionSetObject.findOption(optionName);
		}
		return optionObject;
	}

	/** find the optionSet by name and Option by name
	 * @param OptionSetName optionSet name
	 * @return -1 if not found and option set index position if found **/
	public int findOptionSetIndex(String optionSetName) {
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
	public int findOptionSetOptionIndex(int OptionSetIndex, String optionName) {
		int returnIndex = 0;
		OptionSet optionSetObject = getOptionSet(OptionSetIndex);
		if (optionSetObject != null) {
			returnIndex = optionSetObject.findOptionIndex(optionName);
			//System.out.println(returnIndex); testing purposes only
		}
		return returnIndex;
	}

	/** Add new optionSet
	 * @param OptionSetName optionSet name
	 * @return -1 on failure and index position on success
	 * @throws AutoException **/
	public int addOptionSet(String OptionSetName) throws AutoException {
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
	public int addOptionSetOption(int optionSetIndex, String optionName, double optionPrice) {
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
	public void setMake(String name) {
		makeName = name;
	}

	public void setModel(String name) {
		modelName = name;
	}

	public void setYear(String name) {
		year = name;
	}

	// Set Base Price
	public void setPrice(double price_) {
		basePrice = price_;
	}

	public boolean setOptionSetName(String optionSetName, String nameNew) {
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
	public boolean setOptionSetOptionNameReserved(String optionSetName, String optionName) {
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
	public boolean setOptionSetOptionName(String optionSetName, String optionName, String nameNew) {
		boolean returnValue = false;
		// First set reserved attributes then the optionSet option if not reserved
		if (!setOptionSetOptionNameReserved(optionSetName, nameNew)) {
			OptionSet optionSetObject = findOptionSet(optionSetName);
			if (optionSetObject != null) {
				// not reserved option set name
				Option optionObject = optionSetObject.findOption(optionName);
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
	public boolean setOptionSetOptionPrice(String optionSetName, String optionName, double priceNew) {
		boolean returnValue = false;
		// First set reserved attributes then the optionSet option if not reserved
		if (!setOptionSetOptionNameReserved(optionSetName, Double.toString(priceNew))) {
			OptionSet optionSetObject = findOptionSet(optionSetName);
			if (optionSetObject != null) {
				// not reserved option set name
				Option optionObject = optionSetObject.findOption(optionName);
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

	public boolean setOptionSetChoiceByIndex(int optionSetIndex, int optionIndex) throws AutoException {
		boolean returnValue = false;
		try {
			optionSetOptionChoice.set(optionSetIndex, optionIndex);
			returnValue = true;
		} catch (IndexOutOfBoundsException e) {
			throw new exception.AutoException(800);
		}
		return returnValue;
	}

	public boolean setOptionSetChoice(String optionSetName, String optionName) {
		boolean returnValue = false;
		int optionSetIndex, optionIndex;
		try {
			optionSetIndex = findOptionSetIndex(optionSetName);
			optionIndex = findOptionSetOptionIndex(optionSetIndex, optionName);
			// System.out.println(optionIndex); Testing purposes only
			setOptionSetChoiceByIndex(optionSetIndex, optionIndex);
			returnValue = true;
		} catch (exception.AutoException e) {
			// nothing
		}
		return returnValue;
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
		stringBufferObject.append("Year Make Model: ").append(getYear()).append(" ").append(getMake()).append(" ")
			.append(getModel()).append(" with Base Price: $").append(getPrice());
		stringBufferObject.append(System.getProperty("line.separator"));
		for (i = 0; i < n; i++) {
			stringBufferObject.append(optionSetList.get(i).toString()).append(System.getProperty("line.separator"));
		}
		return stringBufferObject.toString();
	}

	
}
