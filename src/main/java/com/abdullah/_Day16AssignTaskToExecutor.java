package com.abdullah;

import java.time.LocalTime;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _Day16AssignTaskToExecutor {
	public static void main(String[] args) throws InterruptedException {
		new _Day16AssignTaskToExecutor().assignToExecutorService();
	}
	private void assignToExecutorService() throws InterruptedException {
		var callableList = Stream.generate(() -> (Callable<String>) () -> {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e.getLocalizedMessage());
			}
			return "Callable Task executed successfully "+ LocalTime.now();
		}).limit(50).collect(Collectors.toList());
		var executorService = Executors.newCachedThreadPool();
		executorService.invokeAll(callableList).forEach(result -> {
			try {
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException e) {
				System.out.println(e.getLocalizedMessage());
			}
		});
		shutDown(executorService);
	}

	private void shutDown(ExecutorService executorService) {
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
	}
}
