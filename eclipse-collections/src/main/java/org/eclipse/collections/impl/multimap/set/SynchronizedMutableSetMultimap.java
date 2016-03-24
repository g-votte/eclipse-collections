/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set;

import java.io.Serializable;

import net.jcip.annotations.GuardedBy;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.multimap.SynchronizedMultimapSerializationProxy;

public class SynchronizedMutableSetMultimap<K, V>
        extends AbstractSynchronizedMutableSetIterableMultimap<K, V>
        implements MutableSetMultimap<K, V>, Serializable
{
    private static final long serialVersionUID = 1L;

    public SynchronizedMutableSetMultimap(MutableSetMultimap<K, V> newMultiMap)
    {
        super(newMultiMap);
    }

    public SynchronizedMutableSetMultimap(MutableSetMultimap<K, V> newMultimap, Object newLock)
    {
        super(newMultimap, newLock);
    }

    protected Object writeReplace()
    {
        return new SynchronizedMultimapSerializationProxy<K, V>(this.getDelegate());
    }

    /**
     * This method will take a Multimap and wrap it directly in a SynchronizedMutableMultimap.
     */
    public static <K, V, M extends Multimap<K, V>> SynchronizedMutableSetMultimap<K, V> of(M multimap)
    {
        if (multimap == null)
        {
            throw new IllegalArgumentException("cannot create a SynchronizedMutableMultimap for null");
        }

        return new SynchronizedMutableSetMultimap<K, V>(Multimaps.mutable.set.withAll(multimap));
    }

    /**
     * This method will take a Multimap and wrap it directly in a SynchronizedMutableMap.
     * Additionally, a developer specifies which lock to use with the collection.
     */
    public static <K, V, M extends Multimap<K, V>> SynchronizedMutableSetMultimap<K, V> of(M multimap, Object lock)
    {
        if (multimap == null)
        {
            throw new IllegalArgumentException("cannot create a SynchronizedMutableMultimap for null");
        }

        return new SynchronizedMutableSetMultimap<K, V>(Multimaps.mutable.set.withAll(multimap), lock);
    }

    public MutableSet<V> replaceValues(K key, Iterable<? extends V> values)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().replaceValues(key, values);
        }
    }

    public MutableSet<V> removeAll(Object key)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().removeAll(key);
        }
    }

    public void clear()
    {
        synchronized (this.lock)
        {
            this.getDelegate().clear();
        }
    }

    public MutableSetMultimap<K, V> newEmpty()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().newEmpty().asSynchronized();
        }
    }

    public MutableSet<V> get(K key)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().get(key);
        }
    }

    public MutableSetMultimap<K, V> toMutable()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().toMutable();
        }
    }

    public ImmutableSetMultimap<K, V> toImmutable()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().toImmutable();
        }
    }

    public MutableSetMultimap<V, K> flip()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().flip();
        }
    }

    public MutableSetMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().selectKeysValues(predicate);
        }
    }

    public MutableSetMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().rejectKeysValues(predicate);
        }
    }

    public MutableSetMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().selectKeysMultiValues(predicate);
        }
    }

    public MutableSetMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().rejectKeysMultiValues(predicate);
        }
    }

    public <K2, V2> MutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().collectKeysValues(function);
        }
    }

    public <V2> MutableBagMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().collectValues(function);
        }
    }

    public MutableSetMultimap<K, V> asSynchronized()
    {
        return this;
    }

    @Override
    @GuardedBy("getLock()")
    protected MutableSetMultimap<K, V> getDelegate()
    {
        return (MutableSetMultimap<K, V>) super.getDelegate();
    }
}
