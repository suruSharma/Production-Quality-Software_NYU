package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.utils.StopwatchUtils.StopwatchState;

/**
 * This class contains the implementations of methods that are required to use a
 * stop watch. An instance of the IStopwatcImpl cab be created by passing the correct ID.
 * An object of this class cannot be created without an ID.
 *
 */
public class IStopwatchImpl implements IStopwatch {

  private String id;
  private StopwatchState stopwatchState;
  private Object lock = new Object();
  private long lastLapTime = 0;
  private List<Long> lapTimes = new ArrayList<Long>();

  /**
   * Parameterized constructor that takes the ID of the stop watch as a parameter
   * 
   * @param id
   */
  public IStopwatchImpl(String id) {
    this.id = id;
    stopwatchState = StopwatchState.START;
  }

  /**
   * This method is used to calculate the total lap time.
   * @return
   */
  private long calcTotalLapTime() {
    long totalLapTime = 0;
    for (Long lapTime : lapTimes) {
      totalLapTime += lapTime;
    }
    return totalLapTime;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof IStopwatch)) {
      return false;
    } else {
      IStopwatch stopwatch = (IStopwatch) o;
      return this.getId().equals(stopwatch.getId());
    }
  }

  /**
   * This method is used to fetch the current System time.
   * @return
   */
  private long getCurrentTime() {
    return System.currentTimeMillis();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public List<Long> getLapTimes() {
    synchronized (lock) {
      return lapTimes;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id.hashCode());
  }

  @Override
  public void lap() {
    synchronized (lock) {
      if (stopwatchState != StopwatchState.RUNNING) {
        throw new IllegalStateException("Stopwatch is not running!");
      }
      lapTimes.add(getCurrentTime() - calcTotalLapTime() - lastLapTime);
    }

  }
  
  @Override
  public void reset() {
    synchronized (lock) {
      if (stopwatchState == StopwatchState.RUNNING) {
        stop();
      }

      lapTimes.clear();
      stopwatchState = StopwatchState.START;
    }

  }

  @Override
  public void start() {
    synchronized (lock) {
      if (stopwatchState == StopwatchState.RUNNING) {
        throw new IllegalStateException("An instance of stopwatch is already running");
      } else if (stopwatchState == StopwatchState.START) {
        lastLapTime = getCurrentTime();
      }
      stopwatchState = StopwatchState.RUNNING;
    }

  }

  @Override
  public void stop() {
    synchronized (lock) {
      lap();
      stopwatchState = StopwatchState.STOP;
    }

  }

  @Override
  public String toString() {
    return "ID : " + id + " : ";
  }

}
