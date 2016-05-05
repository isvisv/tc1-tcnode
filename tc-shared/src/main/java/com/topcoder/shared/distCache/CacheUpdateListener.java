package com.topcoder.shared.distCache;

/**
 * @author orb
 * @version  $Revision: 31146 $
 */
public interface CacheUpdateListener {
    /**
     *
     * @param value
     */
    public void valueUpdated(CachedValue value);

    /**
     *
     */
    public void clear();
}
