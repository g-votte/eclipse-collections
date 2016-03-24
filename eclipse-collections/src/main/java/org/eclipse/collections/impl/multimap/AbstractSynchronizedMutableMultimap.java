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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.tuple.Pair;

public abstract class AbstractSynchronizedMutableMultimap<K, V, C extends MutableCollection<V>>
        extends AbstractSynchronizedMultimap<K, V, C>
        implements MutableMultimap<K, V>
{
    protected AbstractSynchronizedMutableMultimap(MutableMultimap<K, V> newMultimap)
    {
        super(newMultimap, null);
    }

    protected AbstractSynchronizedMutableMultimap(MutableMultimap<K, V> newMultimap, Object newLock)
    {
        super(newMultimap, newLock);
    }

    public boolean put(K key, V value)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().put(key, value);
        }
    }

    public boolean add(Pair<K, V> keyValuePair)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().add(keyValuePair);
        }
    }

    public boolean remove(Object key, Object value)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().remove(key, value);
        }
    }

    public boolean putAllPairs(Pair<K, V>... pairs)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().putAllPairs(pairs);
        }
    }

    public boolean putAll(K key, Iterable<? extends V> values)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().putAll(key, values);
        }
    }

    public <KK extends K, VV extends V> boolean putAll(Multimap<KK, VV> multimap)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().putAll(multimap);
        }
    }

    public <R extends MutableMultimap<K, V>> R selectKeysValues(Predicate2<? super K, ? super V> predicate, R target)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().selectKeysValues(predicate, target);
        }
    }

    public <R extends MutableMultimap<K, V>> R rejectKeysValues(Predicate2<? super K, ? super V> predicate, R target)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().rejectKeysValues(predicate, target);
        }
    }

    public <R extends MutableMultimap<K, V>> R selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate, R target)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().selectKeysMultiValues(predicate, target);
        }
    }

    public <R extends MutableMultimap<K, V>> R rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate, R target)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().rejectKeysMultiValues(predicate, target);
        }
    }

    public <K2, V2, R extends MutableMultimap<K2, V2>> R collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function, R target)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().collectKeysValues(function, target);
        }
    }

    public <V2, R extends MutableMultimap<K, V2>> R collectValues(Function<? super V, ? extends V2> function, R target)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().collectValues(function, target);
        }
    }

    @Override
    protected MutableMultimap<K, V> getDelegate()
    {
        return (MutableMultimap<K, V>) super.getDelegate();
    }
}
