package io.distributed.unicorn.lock.lease;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.redisson.api.RFuture;

import io.doraemon.distributed.LeaseLockFuture;

public class LeaseLockFutureImpl<T> implements LeaseLockFuture<T> {
	private RFuture<T> future;
	public LeaseLockFutureImpl(RFuture<T> future) {
		this.future = future;
	}
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return future.cancel(mayInterruptIfRunning);
	}
	@Override
	public boolean isCancelled() {
		return future.isCancelled();
	}
	@Override
	public boolean isDone() {
		return future.isDone();
	}
	@Override
	public T get() throws InterruptedException, ExecutionException {
		return future.get();
	}
	@Override
	public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return future.get(timeout, unit);
	}
	@Override
	public <U> CompletionStage<U> thenApply(Function<? super T, ? extends U> fn) {
		return future.thenApply(fn);
	}
	@Override
	public <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
		return future.thenApplyAsync(fn);
	}
	@Override
	public <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) {
		return future.thenApplyAsync(fn, executor);
	}
	@Override
	public CompletionStage<Void> thenAccept(Consumer<? super T> action) {
		return future.thenAccept(action);
	}
	@Override
	public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action) {
		return future.thenAcceptAsync(action);
	}
	@Override
	public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor) {
		return future.thenAcceptAsync(action, executor);
	}
	@Override
	public CompletionStage<Void> thenRun(Runnable action) {
		return future.thenRun(action);
	}
	@Override
	public CompletionStage<Void> thenRunAsync(Runnable action) {
		return future.thenRunAsync(action);
	}
	@Override
	public CompletionStage<Void> thenRunAsync(Runnable action, Executor executor) {
		return future.thenRunAsync(action, executor);
	}
	@Override
	public <U, V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,
			BiFunction<? super T, ? super U, ? extends V> fn) {
		return future.thenCombine(other, fn);
	}
	@Override
	public <U, V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,
			BiFunction<? super T, ? super U, ? extends V> fn) {
		return future.thenCombineAsync(other, fn);
	}
	@Override
	public <U, V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,
			BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) {
		return future.thenCombineAsync(other, fn, executor);
	}
	@Override
	public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other,
			BiConsumer<? super T, ? super U> action) {
		return future.thenAcceptBoth(other, action);
	}
	@Override
	public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,
			BiConsumer<? super T, ? super U> action) {
		return future.thenAcceptBothAsync(other, action);
	}
	@Override
	public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,
			BiConsumer<? super T, ? super U> action, Executor executor) {
		return future.thenAcceptBothAsync(other, action, executor);
	}
	@Override
	public CompletionStage<Void> runAfterBoth(CompletionStage<?> other, Runnable action) {
		return future.runAfterBoth(other, action);
	}
	@Override
	public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action) {
		return future.runAfterBothAsync(other, action);
	}
	@Override
	public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) {
		return future.runAfterBothAsync(other, action, executor);
	}
	@Override
	public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn) {
		return future.applyToEither(other, fn);
	}
	@Override
	public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn) {
		return future.applyToEitherAsync(other, fn);
	}
	@Override
	public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn,
			Executor executor) {
		return future.applyToEitherAsync(other, fn, executor);
	}
	@Override
	public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
		return future.acceptEither(other, action);
	}
	@Override
	public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action) {
		return future.acceptEitherAsync(other, action);
	}
	@Override
	public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action,
			Executor executor) {
		return future.acceptEitherAsync(other, action, executor);
	}
	@Override
	public CompletionStage<Void> runAfterEither(CompletionStage<?> other, Runnable action) {
		return future.runAfterEither(other, action);
	}
	@Override
	public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action) {
		return future.runAfterEitherAsync(other, action);
	}
	@Override
	public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) {
		return future.runAfterEitherAsync(other, action, executor);
	}
	@Override
	public <U> CompletionStage<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn) {
		return future.thenCompose(fn);
	}
	@Override
	public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) {
		return future.thenComposeAsync(fn);
	}
	@Override
	public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn,
			Executor executor) {
		return future.thenComposeAsync(fn, executor);
	}
	@Override
	public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn) {
		return future.exceptionally(fn);
	}
	@Override
	public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) {
		return future.whenComplete(action);
	}
	@Override
	public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action) {
		return future.whenCompleteAsync(action);
	}
	@Override
	public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) {
		return future.whenCompleteAsync(action, executor);
	}
	@Override
	public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
		return future.handle(fn);
	}
	@Override
	public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
		return future.handleAsync(fn);
	}
	@Override
	public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
		return future.handleAsync(fn, executor);
	}
	@Override
	public CompletableFuture<T> toCompletableFuture() {
		return future.toCompletableFuture();
	}
	@Override
	public boolean isSuccess() {
		return future.isSuccess();
	}
	@Override
	public Throwable cause() {
		return future.cause();
	}
	@Override
	public T getNow() {
		return future.getNow();
	}
	@Override
	public T join() {
		return future.join();
	}
	@Override
	public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
		return future.await(timeout, unit);
	}
	@Override
	public boolean await(long timeoutMillis) throws InterruptedException {
		return future.await(timeoutMillis);
	}
	@Override
	public LeaseLockFuture<T> sync() throws InterruptedException {
		future.sync();
		return this;
	}
	@Override
	public LeaseLockFuture<T> syncUninterruptibly() {
		future.syncUninterruptibly();
		return this;
	}
	@Override
	public LeaseLockFuture<T> await() throws InterruptedException {
		future.await();
		return this;
	}
	@Override
	public LeaseLockFuture<T> awaitUninterruptibly() {
		future.awaitUninterruptibly();
		return this;
	}
	@Override
	public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
		return future.awaitUninterruptibly(timeout, unit);
	}
	@Override
	public boolean awaitUninterruptibly(long timeoutMillis) {
		return future.awaitUninterruptibly(timeoutMillis);
	}
	@Override
	public void onComplete(BiConsumer<? super T, ? super Throwable> action) {
		future.onComplete(action);
	}
	
}
