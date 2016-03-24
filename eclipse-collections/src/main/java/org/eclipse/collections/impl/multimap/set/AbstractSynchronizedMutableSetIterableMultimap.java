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

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetIterableMultimap;
import org.eclipse.collections.api.set.MutableSetIterable;
import org.eclipse.collections.impl.multimap.AbstractSynchronizedMutableMultimap;

public abstract class AbstractSynchronizedMutableSetIterableMultimap<K, V>
        extends AbstractSynchronizedMutableMultimap<K, V, MutableSetIterable<V>>
        implements MutableSetIterableMultimap<K, V>

{
    protected AbstractSynchronizedMutableSetIterableMultimap(MutableMultimap<K, V> newMultimap)
    {
        super(newMultimap);
    }

    protected AbstractSynchronizedMutableSetIterableMultimap(MutableMultimap<K, V> newMultimap, Object newLock)
    {
        super(newMultimap, newLock);
    }
}
