package applicantion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre com os dados do contrato: ");
		System.out.print("Numero: ");
		int numero = sc.nextInt();
		sc.nextLine();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date =  LocalDate.parse(sc.nextLine(), dtf);
		System.out.print("Valor do contrato: ");
		double amount = sc.nextDouble();
		System.out.println("Némero de parcelas: ");
		int months = sc.nextInt();
		
		Contract contract = new Contract(numero, date, amount);
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, months);
		
		sc.close();
	}

}
