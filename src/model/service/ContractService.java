package model.service;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}                       

	public void processContract(Contract contract, Integer months) {
		
		double amount;
		
		for(int i = 1; i <= months; i++) {
			
		amount = contract.getTotalValue() / months;
			
		amount += onlinePaymentService.paymentFee(amount);
		amount += onlinePaymentService.interest(amount, i);
		
		contract.addInstallment(new Installment(contract.getDate().plusMonths(i), amount));
		}
		
		System.out.println("Parcelas: ");
		for(Installment i: contract.getInstallment()) {
			
			System.out.println(i.getDueDate() + " - " + String.format("%.2f", i.getAmount()));
		}
		
	}

}
