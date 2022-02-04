
package driver;

// import model.Automobile;
import adapter.*;

public class Driver {

	public static void main(String[] args) {
		CreateAuto buildAutoInterface = new CreateAuto();
		buildAutoInterface.init(); // only call this once
		// Build Automobile Object from a file.
		String FordZTW2021AutomobileKey = buildAutoInterface.buildAuto("FordZTW2021.txt");
		if (FordZTW2021AutomobileKey != null) {
			// Print attributes before serialization
			buildAutoInterface.printAuto(FordZTW2021AutomobileKey);
			// update an options set's name
			buildAutoInterface.updateOptionSetName(FordZTW2021AutomobileKey, "Color", "Colors");
			// update an options set's option price
			buildAutoInterface.updateOptionPrice(FordZTW2021AutomobileKey, "Power Moonroof", "present", 595);
			// choose a transmission option
			buildAutoInterface.setOptionChoice(FordZTW2021AutomobileKey, "Brakes/Traction Control", "ABS with Advance Track");
			// Serialize the object
			buildAutoInterface.serialize(FordZTW2021AutomobileKey, "FordZTW2021.data");
		} else {
			System.out.println("Could not build automobile");
		}
		// Deserialize the object and read it into memory.
		String FordZTW2021AutomobileKey2 = buildAutoInterface.deserialize("FordZTW2021.data");
		// Print new attributes
		if (FordZTW2021AutomobileKey2 != null) {
			// Print attributes after deserialization
			buildAutoInterface.printAuto(FordZTW2021AutomobileKey2);
			// Print transmission choice
			String optionName = buildAutoInterface.getOptionChoice(FordZTW2021AutomobileKey, "Transmission");
			if (optionName != null) {
				System.out.println("\nTransmission choices: " + optionName);
			}
			// Print transmission choice price
			Double optionPrice = buildAutoInterface.getOptionChoicePrice(FordZTW2021AutomobileKey, "Transmission");
			if (optionName != null) {
				System.out.println("\nTransmission choice price: $" + optionPrice);
			}
		} else {
			System.out.println("could not deserialize automobile");
		}

		//Testing with TeslaModelY
		String TeslaModelY2022AutomobileKey = buildAutoInterface.buildAuto("TeslaModelY2022.txt");
		if (TeslaModelY2022AutomobileKey != null) {
			// Print attributes before serialization
			buildAutoInterface.printAuto(TeslaModelY2022AutomobileKey);
			// update an options set's name
			buildAutoInterface.updateOptionSetName(TeslaModelY2022AutomobileKey, "Color", "Colors");
			// update an options set's option price
			buildAutoInterface.updateOptionPrice(TeslaModelY2022AutomobileKey, "Trim", "Brushed Aluminum trim", 2000);
			// choose a transmission option
			buildAutoInterface.setOptionChoice(TeslaModelY2022AutomobileKey, "Tier", "Full Self-Driving Capability");
			// Serialize the object
			buildAutoInterface.serialize(TeslaModelY2022AutomobileKey, "TeslaModelY2022.data");
		} else {
			System.out.println("Could not build automobile");
		}
		// Deserialize the object and read it into memory.
		String TeslaModelY2022AutomobileKey2 = buildAutoInterface.deserialize("TeslaModelY.data");
		// Print new attributes
		if (TeslaModelY2022AutomobileKey2 != null) {
			// Print attributes after deserialization
			buildAutoInterface.printAuto(TeslaModelY2022AutomobileKey2);
			// Print transmission choice
			String optionName = buildAutoInterface.getOptionChoice(TeslaModelY2022AutomobileKey, "Colors");
			if (optionName != null) {
				System.out.println("\n Color choice: " + optionName);
			}
			// Print transmission choice price
			Double optionPrice = buildAutoInterface.getOptionChoicePrice(TeslaModelY2022AutomobileKey, "Colors");
			if (optionName != null) {
				System.out.println("\n Color choice price: $" + optionPrice);
			}
		} else {
			System.out.println("could not deserialize automobile");
		}
		// Testing with Camry 2020
		String ToyotaCamry2020AutomobileKey = buildAutoInterface.buildAuto("Camry2020.txt");
		if (ToyotaCamry2020AutomobileKey != null) {
			// Print attributes before serialization
			buildAutoInterface.printAuto(ToyotaCamry2020AutomobileKey);
			// update an options set's name
			buildAutoInterface.updateOptionSetName(ToyotaCamry2020AutomobileKey, "Color", "Colors");
			// update an options set's option price
			buildAutoInterface.updateOptionPrice(ToyotaCamry2020AutomobileKey, "Accesories", "Cargo Tray", 59);
			// choose a transmission option
			buildAutoInterface.setOptionChoice(ToyotaCamry2020AutomobileKey, "Engine", "All-Wheel Drive");
			// Serialize the object
			buildAutoInterface.serialize(ToyotaCamry2020AutomobileKey, "ToyotaCamry2020.data");
		} else {
			System.out.println("Could not build automobile");
		}
		// Deserialize the object and read it into memory.
		String ToyotaCamry2020AutomobileKey2 = buildAutoInterface.deserialize("ToyotaCamry2020.data");
		// Print new attributes
		if (ToyotaCamry2020AutomobileKey2 != null) {
			// Print attributes after deserialization
			buildAutoInterface.printAuto(ToyotaCamry2020AutomobileKey2);
			// Print transmission choice
			String optionName = buildAutoInterface.getOptionChoice(ToyotaCamry2020AutomobileKey, "Colors");
			if (optionName != null) {
				System.out.println("\n Color choice: " + optionName);
			}
			// Print transmission choice price
			Double optionPrice = buildAutoInterface.getOptionChoicePrice(ToyotaCamry2020AutomobileKey, "Colors");
			if (optionName != null) {
				System.out.println("\n Color choice price: $" + optionPrice);
			}
		} else {
			System.out.println("could not deserialize automobile");
		}
	}

}
