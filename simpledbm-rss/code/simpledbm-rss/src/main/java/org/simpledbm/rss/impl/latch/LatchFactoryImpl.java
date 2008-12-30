/***
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *    Project: www.simpledbm.org
 *    Author : Dibyendu Majumdar
 *    Email  : d dot majumdar at gmail dot com ignore
 */
package org.simpledbm.rss.impl.latch;

import java.util.Properties;

import org.simpledbm.rss.api.latch.Latch;
import org.simpledbm.rss.api.latch.LatchFactory;
import org.simpledbm.rss.api.platform.Platform;

/**
 * A factory for creating Latches.
 */
public final class LatchFactoryImpl implements LatchFactory {

	public LatchFactoryImpl(Platform platform, Properties properties) {
	}
	
    /* (non-Javadoc)
     * @see org.simpledbm.common.latch.LatchFactory#newReadWriteLatch()
     */
    public Latch newReadWriteLatch() {
        return new ReadWriteLatch();
    }

    /* (non-Javadoc)
     * @see org.simpledbm.common.latch.LatchFactory#newReadWriteUpdateLatch()
     */
    public Latch newReadWriteUpdateLatch() {
        return new NewReadWriteUpdateLatch();
    }

    /* (non-Javadoc)
     * @see org.simpledbm.common.latch.LatchFactory#newLatch()
     */
    public Latch newLatch() {
        return new NewReadWriteUpdateLatch();
    }

}
