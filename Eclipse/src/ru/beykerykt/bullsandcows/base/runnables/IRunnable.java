package ru.beykerykt.bullsandcows.base.runnables;

public interface IRunnable extends Runnable {

	public void start();

	public void stop();

	public String getName();

}