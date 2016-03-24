/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag.sorted.mutable;

import java.io.Serializable;
import java.util.Comparator;

import net.jcip.annotations.GuardedBy;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.sortedbag.ImmutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.multimap.SynchronizedMultimapSerializationProxy;
import org.eclipse.collections.impl.multimap.bag.AbstractSynchronizedMutableBagIterableMultimap;

public class SynchronizedMutableSortedBagMultimap<K, V>
        extends AbstractSynchronizedMutableBagIterableMultimap<K, V>
        implements MutableSortedBagMultimap<K, V>, Serializable
{
    private static final long serialVersionUID = 1L;

    public SynchronizedMutableSortedBagMultimap(MutableSortedBagMultimap<K, V> newMultiMap)
    {
        super(newMultiMap);
    }

    public SynchronizedMutableSortedBagMultimap(MutableSortedBagMultimap<K, V> newMultimap, Object newLock)
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
    public static <K, V, M extends Multimap<K, V>> SynchronizedMutableSortedBagMultimap<K, V> of(M multimap)
    {
        if (multimap == null)
        {
            throw new IllegalArgumentException("cannot create a SynchronizedMutableMultimap for null");
        }

        return new SynchronizedMutableSortedBagMultimap<K, V>(Multimaps.mutable.sortedBag.withAll(multimap));
    }

    /**
     * This method will take a Multimap and wrap it directly in a SynchronizedMutableMap.
     * Additionally, a developer specifies which lock to use with the collection.
     */
    public static <K, V, M extends Multimap<K, V>> SynchronizedMutableSortedBagMultimap<K, V> of(M multimap, Object lock)
    {
        if (multimap == null)
        {
            throw new IllegalArgumentException("cannot create a SynchronizedMutableMultimap for null");
        }

        return new SynchronizedMutableSortedBagMultimap<K, V>(Multimaps.mutable.sortedBag.withAll(multimap), lock);
    }

    public MutableSortedBag<V> replaceValues(K key, Iterable<? extends V> values)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().replaceValues(key, values);
        }
    }

    public MutableSortedBag<V> removeAll(Object key)
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

    public MutableSortedBagMultimap<K, V> newEmpty()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().newEmpty().asSynchronized();
        }
    }

    public MutableSortedBag<V> get(K key)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().get(key);
        }
    }

    public Comparator<? super V> comparator()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().comparator();
        }
    }

    public MutableSortedBagMultimap<K, V> toMutable()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().toMutable();
        }
    }

    public ImmutableSortedBagMultimap<K, V> toImmutable()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().toImmutable();
        }
    }

    public MutableBagMultimap<V, K> flip()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().flip();
        }
    }

    public MutableSortedBagMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().selectKeysValues(predicate);
        }
    }

    public MutableSortedBagMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().rejectKeysValues(predicate);
        }
    }

    public MutableSortedBagMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().selectKeysMultiValues(predicate);
        }
    }

    public MutableSortedBagMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
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

    public <V2> MutableListMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().collectValues(function);
        }
    }

    public MutableSortedBagMultimap<K, V> asSynchronized()
    {
        return this;
    }

    @Override
    @GuardedBy("getLock()")
    protected MutableSortedBagMultimap<K, V> getDelegate()
    {
        return (MutableSortedBagMultimap<K, V>) super.getDelegate();
    }
}
