package pro.buildmysoftware.training.tdd.operations;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class BankOperationsClient {
	private final AccountingService accountingService;

	public BankOperationsClient(AccountingService accountingService) {
		this.accountingService = accountingService;
	}

	public Collection<BankOperation> filter(double threshold) {
		try {
			return accountingService.allOperations().stream()
				.filter(bankOperation -> bankOperation
					.getValue() >= threshold)
				.collect(Collectors.toList());
		}
		catch (ServiceNotAvailableException e) {
			return Collections.emptyList();
		}
	}
}
