package test;

import AbstractFactory.ComputerFactory;
import AbstractFactory.PCFactory;
import AbstractFactory.ServerFactory;
import beans.Computer;

public class ComputerClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Computer pc = ComputerFactory.getComputer(new PCFactory("2GB", "256GB", "2.4 GHz"));
		Computer server = ComputerFactory.getComputer(new ServerFactory("16 GB","1 TB","2.9 GHz"));
		
		System.out.println("AbstractFactory PC Config:: "+"RAM=" + pc.getRAM() + ", HDD="+pc.getHDD()+", CPU="+pc.getCPU());
		System.out.println("AbstractFactory server Config:: "+"RAM=" + server.getRAM() + ", HDD="+server.getHDD()+", CPU="+server.getCPU());
	}

}
