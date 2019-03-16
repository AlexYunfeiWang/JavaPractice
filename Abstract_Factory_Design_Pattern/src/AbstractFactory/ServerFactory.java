package AbstractFactory;

import beans.Computer;
import beans.Server;

public class ServerFactory implements AbstractComputerFactory {
	private String RAM;
	private String HDD;
	private String CPU;
	
	public ServerFactory(String RAM, String HDD, String CPU) {
		this.RAM = RAM;
		this.HDD = HDD;
		this.CPU = CPU;
	}

	@Override
	public Computer createComputer() {
		// TODO Auto-generated method stub
		return new Server(RAM, HDD, CPU);
	}

}
