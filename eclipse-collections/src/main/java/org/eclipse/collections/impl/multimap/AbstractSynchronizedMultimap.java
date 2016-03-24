/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap;

import java.util.Collection;

import net.jcip.annotations.GuardedBy;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;

public abstract class AbstractSynchronizedMultimap<K, V, C> implements Multimap<K, V>
{
    protected final Object lock;
    @GuardedBy("this.lock")
    protected final Multimap<K, V> delegate;

    protected AbstractSynchronizedMultimap(Multimap<K, V> newMultimap, Object newLock)
    {
        if (newMultimap == null)
        {
            throw new IllegalArgumentException("Cannot create a AbstractSynchronizedMultimap on a null multimap");
        }

        this.delegate = newMultimap;
        this.lock = newLock == null ? this : newLock;
    }

    protected Object getLock()
    {
        return this.lock;
    }

    @Override
    public boolean equals(Object obj)
    {
        synchronized (this.lock)
        {
            return this.delegate.equals(obj);
        }
    }

    @Override
    public int hashCode()
    {
        synchronized (this.lock)
        {
            return this.delegate.hashCode();
        }
    }

    @Override
    public String toString()
    {
        synchronized (this.lock)
        {
            return this.delegate.toString();
        }
    }

//    /**
//     * Creates the collection of values for a single key.
//     * <p>
//     * Collections with weak, soft, or phantom references are not supported.
//     * Each call to {@code createCollection} should create a new instance.
//     * <p>
//     * The returned collection class determines whether duplicate key-value
//     * pairs are allowed.
//     *
//     * @return an empty collection of values
//     */
//    protected abstract C createCollection();

    public boolean isEmpty()
    {
        synchronized (this.lock)
        {
            return this.delegate.isEmpty();
        }
    }

    public boolean notEmpty()
    {
        synchronized (this.lock)
        {
            return this.delegate.notEmpty();
        }
    }

    public void forEachValue(Procedure<? super V> procedure)
    {
        synchronized (this.lock)
        {
            this.delegate.forEachValue(procedure);
        }
    }

    public void forEachKey(Procedure<? super K> procedure)
    {
        synchronized (this.lock)
        {
            this.delegate.forEachKey(procedure);
        }
    }

    public void forEachKeyValue(Procedure2<K, V> procedure)
    {
        synchronized (this.lock)
        {
            this.delegate.forEachKeyValue(procedure);
        }
    }

    public void forEachKeyMultiValues(Procedure2<K, ? super Iterable<V>> procedure)
    {
        synchronized (this.lock)
        {
            this.delegate.forEachKeyMultiValues(procedure);
        }
    }

    public int size()
    {
        synchronized (this.lock)
        {
            return this.delegate.size();
        }
    }

    public int sizeDistinct()
    {
        synchronized (this.lock)
        {
            return this.delegate.sizeDistinct();
        }
    }

    public boolean containsKey(Object key)
    {
        synchronized (this.lock)
        {
            return this.delegate.containsKey(key);
        }
    }

    public boolean containsValue(Object value)
    {
        synchronized (this.lock)
        {
            return this.delegate.containsValue(value);
        }
    }

    public boolean containsKeyAndValue(Object key, Object value)
    {
        synchronized (this.lock)
        {
            return this.delegate.containsKeyAndValue(key, value);
        }
    }

    public RichIterable<K> keysView()
    {
        synchronized (this.lock)
        {
            return this.delegate.keysView();
        }
    }

    public Bag<K> keyBag()
    {
        synchronized (this.lock)
        {
            return this.delegate.keyBag();
        }
    }

    public RichIterable<RichIterable<V>> multiValuesView()
    {
        synchronized (this.lock)
        {
            return this.delegate.multiValuesView();
        }
    }

    public RichIterable<V> valuesView()
    {
        synchronized (this.lock)
        {
            return this.delegate.valuesView();
        }
    }

    public RichIterable<Pair<K, RichIterable<V>>> keyMultiValuePairsView()
    {
        synchronized (this.lock)
        {
            return this.delegate.keyMultiValuePairsView();
        }
    }

    public RichIterable<Pair<K, V>> keyValuePairsView()
    {
        synchronized (this.lock)
        {
            return this.delegate.keyValuePairsView();
        }
    }

    public MutableMap<K, RichIterable<V>> toMap()
    {
        synchronized (this.lock)
        {
            return this.delegate.toMap();
        }
    }

    public <R extends Collection<V>> MutableMap<K, R> toMap(Function0<R> collectionFactory)
    {
        synchronized (this.lock)
        {
            return this.delegate.toMap(collectionFactory);
        }
    }

    protected Multimap getDelegate()
    {
        return this.delegate;
    }
}
