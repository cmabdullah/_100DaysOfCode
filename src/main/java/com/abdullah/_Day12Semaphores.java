package com.abdullah;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
public class _Day12Semaphores {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Runnable runnable = Downloader.INSTANCE::downloadData;
		for (int i = 0; i < 25; i++) {
			executorService.execute(runnable);
		}
	}
}

enum Downloader {
	INSTANCE;
	private Semaphore semaphore = new Semaphore(2, true);
	public void downloadData() {
		try {
			semaphore.acquire();
			download();
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	public void download() {
		System.out.println("Downloading data from network......");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}