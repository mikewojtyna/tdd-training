package pro.buildmysoftware.training.tdd.operations;

import java.util.Collection;

public interface AccountingService {
	/**
	 * Returns all operations in the system.
	 *
	 * @return a collection of all operations
	 * @throws ServiceNotAvailableException if result cannot be returned,
	 *                                      because service is unavailable
	 */
	Collection<BankOperation> allOperations() throws ServiceNotAvailableException;
}
