package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {

  private static List<IStopwatch> stopWatches = new ArrayList<IStopwatch>();
  private static Object lock = new Object();

  /**
   * Creates and returns a new IStopwatch object
   * 
   * @param id
   *          The identifier of the new object
   * @return The new IStopwatch object
   * @throws IllegalArgumentException
   *           if <code>id</code> is empty, null, or already taken.
   */
  public static IStopwatch getStopwatch(String id) {
    if (id == null || id == "") {
      throw new IllegalArgumentException("ID is null or blank");
    }
    
    synchronized (lock) {
      IStopwatchImpl stopwatch = new IStopwatchImpl(id);
      if(stopWatches.contains(stopwatch)){
        throw new IllegalArgumentException("This stopwatch already exists");
      }
      stopWatches.add(stopwatch);
      return stopwatch;
    }
  }

  /**
   * Returns a list of all created stopwatches
   * 
   * @return a List of al creates IStopwatch objects. Returns an empty list if
   *         no IStopwatches have been created.
   */
  public static List<IStopwatch> getStopwatches() {
    return stopWatches;
  }
}
