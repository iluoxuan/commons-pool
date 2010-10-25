/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.pool2.impl;

import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.KeyedObjectPoolFactory;
import org.apache.commons.pool2.KeyedPoolableObjectFactory;

/**
 * A factory for creating {@link StackKeyedObjectPool} instances.
 *
 * @see StackKeyedObjectPool
 * @see KeyedObjectPoolFactory
 *
 * @author Rodney Waldhoff
 * @version $Revision$ $Date$
 * @since Pool 1.0
 */
public class StackKeyedObjectPoolFactory<K,V> implements KeyedObjectPoolFactory<K,V> {
    /**
     * Create a new StackKeyedObjectPoolFactory with the default configuration..
     *
     * @param factory the KeyedPoolableObjectFactory used by created pools.
     * @see StackKeyedObjectPool#StackKeyedObjectPool(KeyedPoolableObjectFactory)
     */
    public StackKeyedObjectPoolFactory(KeyedPoolableObjectFactory<K,V> factory) {
        this(factory,new StackObjectPoolConfig());
    }

    /**
     * Create a new StackKeyedObjectPoolFactory, with a user defined configuration.
     *
     * @param factory the KeyedPoolableObjectFactory used by created pools.
     * @param config the {@link StackObjectPoolConfig} used to configure the pool.
     * @see StackKeyedObjectPool#StackKeyedObjectPool(KeyedPoolableObjectFactory, StackObjectPoolConfig)
     */
    public StackKeyedObjectPoolFactory(KeyedPoolableObjectFactory<K,V> factory, StackObjectPoolConfig config) {
        if (factory == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        if (config == null) {
            throw new IllegalArgumentException("config must not be null");
        }
        _factory = factory;
        this.config = config;
    }

    /**
     * Create a StackKeyedObjectPool with current property settings.
     * 
     * @return a new StackKeyedObjectPool with the configured factory, maxSleeping and initialCapacity
     */
    public KeyedObjectPool<K,V> createPool() {
        return new StackKeyedObjectPool<K,V>(_factory,this.config);
    }

    /** 
     * KeyedPoolableObjectFactory used by StackKeyedObjectPools created by this factory
     */
    private final KeyedPoolableObjectFactory<K,V> _factory;

    /** 
     * The {@link StackObjectPoolConfig} used to configure the pool.
     */
    private StackObjectPoolConfig config;

    /**
     * Returns the KeyedPoolableObjectFactory used by StackKeyedObjectPools created by this factory
     * 
     * @return factory setting for created pools
     * @since 1.5.5
     */
    public synchronized KeyedPoolableObjectFactory<K,V> getFactory() {
        return _factory;
    }

    /**
     * Returns the maximum number of idle instances in each keyed pool for StackKeyedObjectPools created by this factory
     * 
     * @return maxSleeping setting for created pools
     * @since 1.5.5
     */
    public synchronized int getMaxSleeping() {
        return this.config.getMaxSleeping();
    }

    /**
     * Sets the maximum number of idle instances in each keyed pool for StackKeyedObjectPools created by this factory
     *
     * @param maxSleeping
     * @since 2.0
     */
    public synchronized void setMaxSleeping(int maxSleeping) {
        this.config.setMaxSleeping(maxSleeping);
    }

    /**
     * Returns the initial capacity of StackKeyedObjectPools created by this factory.
     * 
     * @return initial capacity setting for created pools
     * @since 1.5.5
     */
    public synchronized int getInitialCapacity() {
        return this.config.getInitIdleCapacity();
    }

    /**
     * Sets initial size of the StackKeyedObjectPools created by this factory.
     *
     * @param initIdleCapacity
     * @since 2.0
     */
    public synchronized void setInitIdleCapacity(int initIdleCapacity) {
        this.config.setInitIdleCapacity(initIdleCapacity);
    }

}
