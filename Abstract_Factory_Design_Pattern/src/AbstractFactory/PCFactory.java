package AbstractFactory;

import beans.Computer;
import beans.PC;

public class PCFactory implements AbstractComputerFactory {
	
	private String RAM;
	private String HDD;
	private String CPU;
	
	public PCFactory(String RAM, String HDD, String CPU) {
		this.RAM = RAM;
		this.HDD = HDD;
		this.CPU = CPU;
	}

	@Override
	public Computer createComputer() {
		// TODO Auto-generated method stub
		return new PC(RAM, HDD, CPU);
	}

}
