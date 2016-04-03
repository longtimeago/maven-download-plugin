package com.googlecode.download.maven.plugin.internal.cache;

/**
 * Cache index.
 * @author Paul Polishchuk
 * @since 1.3.1
 */
interface Index {

    /**
     * Adds given path to the index using url parameter as a key.
     * @param url index key
     * @param path index value
     */
    void put(String url, String path);

    /**
     * Check if a path associated with the url key in the index.
     * <p>Use this method before actually trying to get value.
     * @param url index key
     * @return true if some path associated with given key
     */
    boolean contains(String url);

    /**
     * Gets stored value by the key.
     * @param url index key
     * @return path by given url key; never NULL
     * @throws IllegalStateException in case key is not found
     */
    String get(String url);
}
