package AbstractFactory;

import beans.Computer;

public class ComputerFactory {
	public static Computer getComputer(AbstractComputerFactory factory) {
		return factory.createComputer();
	}
}
