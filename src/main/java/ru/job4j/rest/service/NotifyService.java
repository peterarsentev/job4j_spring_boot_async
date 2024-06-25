package ru.job4j.rest.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class NotifyService {

    @Async("notificationPool")
    public void asyncOperation() {
        System.out.println("fire asyncOperation: " + Thread.currentThread().getName());
    }

    @Async("reportPool")
    public void asyncOperationByReportPool() {
        System.out.println("fire asyncOperation: " + Thread.currentThread().getName());
    }

    @Async
    public Future<List<String>> report(Long userId) throws InterruptedException {
        System.out.println("fire asyncOperation: " + Thread.currentThread().getName());
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(List.of("one", "two", "three"));
    }
}
