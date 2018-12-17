package pro.buildmysoftware.training.tdd.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class BankOperationsClientTest {

	private BankOperationsClient client;
	private AccountingService accountingService;

	@BeforeEach
	void beforeEach() {
		accountingService = Mockito.mock(AccountingService.class);
		client = new BankOperationsClient(accountingService);
	}

	// @formatter:off
	@DisplayName(
		"given operation value threshold equal to 1000.00,"+
		"when call filter method on client,"+
		"then only operations with value >= 1000.00 are selected"
	)
	@Test
	// @formatter:on
	void test0() throws Exception {
		// given
		// all operations in the system
		BankOperation operation0 = operationWithValue(500.00);
		BankOperation operation1 = operationWithValue(1000.00);
		BankOperation operation2 = operationWithValue(1500.00);
		// configure the external accounting service to return all
		// operations
		accountingService = Mockito.mock(AccountingService.class);
		Mockito.when(accountingService.allOperations())
			.thenReturn(Arrays
				.asList(operation0, operation1, operation2));
		// client using the configured accounting service
		client = new BankOperationsClient(accountingService);
		double threshold = 1000.00;

		// when
		Collection<BankOperation> selectedOperations = client
			.filter(threshold);

		// then
		assertThat(selectedOperations)
			.containsOnly(operation1, operation2);
	}

	// @formatter:off
	@DisplayName(
		"when accounting service throws exception, " +
		"then client returns an empty collection")
	@Test
	// @formatter:on
	void test1() throws Exception {
		// given
		Mockito.when(accountingService.allOperations())
			.thenThrow(new ServiceNotAvailableException("service " + "is unavailable"));

		// when
		Collection<BankOperation> operations = client
			.filter(anyThreshold());

		// then
		assertThat(operations).isEmpty();
	}

	private double anyThreshold() {
		return 0;
	}

	private BankOperation operationWithValue(double value) {
		return new BankOperation(value);
	}
}
