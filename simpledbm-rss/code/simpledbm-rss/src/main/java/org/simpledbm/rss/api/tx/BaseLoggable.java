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
 *    Email  : dibyendu@mazumdar.demon.co.uk
 */
package org.simpledbm.rss.api.tx;

import java.nio.ByteBuffer;

import org.simpledbm.rss.api.pm.PageId;
import org.simpledbm.rss.api.wal.Lsn;
import org.simpledbm.rss.util.Dumpable;
import org.simpledbm.rss.util.TypeSize;

/**
 * Provides a default implementation of the Loggable interface. Most log records should
 * extend this class. One point to note is that this class includes the methods
 * defined by the {@link Compensation} interface.
 * <p>
 * Originally the {@link Loggable} interface used to be an abtract class and contained the
 * default implementation. However, that approach caused problems with the inheritance
 * hierarchy - clients were unable to insert common base classes between their implementation
 * of various Loggable types and the base class. By separating the concrete inheritance
 * from the interface hierarchy, it is now possible for clients to implement their own
 * concrete class hierarchy.
 * 
 * @author Dibyendu Majumdar
 * @since 06-Nov-2005
 */
public abstract class BaseLoggable implements Loggable, Dumpable {
	
	/**
	 * Loggable object typecode, used to ensure that the correct type can be
	 * instantiated when the log record is retrieved from persistent storage.
	 * The typecode is set by the LoggableFactory that is responsible for creating
	 * the log record.
	 */
	private short typecode = -1;

	/**
	 * Points to the previous log record generated by the transaction.
	 */
	private Lsn prevTrxLsn = new Lsn();
	
	/**
	 * Points to the next record that should be undone.
	 * Note that this is not specified by Loggable interface - it is part of the
	 * Compensation interface. 
	 */
	private Lsn undoNextLsn = new Lsn();
	
	/**
	 * Transaction that created the log record. Automatically set when the
	 * log record is inserted by the transaction.
	 */
	private TransactionId trxId = new TransactionId();
	
	/**
	 * When the formatting of a new page is being logged, the page type
	 * must be saved so that the correct type of page can be fixed in
	 * the buffer pool during recovery.
	 */
	private short pageType = -1;
	
	/**
	 * Page to which this log is related. Most log records are related to pages.
	 */
	private PageId pageId = new PageId();
	
	/**
	 * The TransactionalModule that generated this log. Also responsible for
	 * redoing/undoing the effects of this log record.
	 */
	private short moduleId = -1;

	/**
	 * Inherited from LogRecord (not-persistent)
	 */
	private Lsn lsn = new Lsn();
	
	public static final int SIZE = (Lsn.SIZE * 2) +
		TransactionId.SIZE + PageId.SIZE + TypeSize.SHORT * 3;
	
	public final int getTypecode() {
		return typecode;
	}

	public final void setTypecode(int typecode) {
        assert typecode <= Short.MAX_VALUE;
		this.typecode = (short) typecode;
	}

	public final Lsn getLsn() {
		return lsn;
	}

	public final void setLsn(Lsn lsn) {
		this.lsn = lsn;
	}

	public final int getModuleId() {
		return moduleId;
	}

	public final void setModuleId(int moduleId) {
        assert moduleId <= Short.MAX_VALUE;
		this.moduleId = (short) moduleId;
	}

	public final PageId getPageId() {
		return pageId;
	}

	public final void setPageId(int pageType, PageId pageId) {
        assert pageType <= Short.MAX_VALUE;
		this.pageType = (short) pageType;
		this.pageId = pageId;
	}

	public final int getPageType() {
		return pageType;
	}

	public final Lsn getPrevTrxLsn() {
		return prevTrxLsn;
	}

	public final void setPrevTrxLsn(Lsn prevTrxLsn) {
		this.prevTrxLsn = prevTrxLsn;
	}

	public final TransactionId getTrxId() {
		return trxId;
	}

	public final void setTrxId(TransactionId trxId) {
		this.trxId = trxId;
	}

	public final Lsn getUndoNextLsn() {
		return undoNextLsn;
	}

	public final void setUndoNextLsn(Lsn undoNextLsn) {
		this.undoNextLsn = undoNextLsn;
	}

	public int getStoredLength() {
		return SIZE;
	}

	public void retrieve(ByteBuffer bb) {
		typecode = bb.getShort();
		prevTrxLsn = new Lsn();
		prevTrxLsn.retrieve(bb);
		undoNextLsn = new Lsn();
		undoNextLsn.retrieve(bb);
		trxId = new TransactionId();
		trxId.retrieve(bb);
		pageType = bb.getShort();
		pageId = new PageId();
		pageId.retrieve(bb);
		moduleId = bb.getShort();
	}

	public void store(ByteBuffer bb) {
		bb.putShort(typecode);
		prevTrxLsn.store(bb);
		undoNextLsn.store(bb);
		trxId.store(bb);
		bb.putShort(pageType);
		pageId.store(bb);
		bb.putShort(moduleId);
	}

	public abstract void init(); 
	
	public StringBuilder appendTo(StringBuilder sb) {
		sb.append("Typecode=").append(typecode).append(", prevTrxLsn=");
		prevTrxLsn.appendTo(sb).append(", undoNextLsn=");
		undoNextLsn.appendTo(sb).append(", trxId=");
		trxId.appendTo(sb).append(", pageType=").append(pageType).append(", pageId=");
		pageId.appendTo(sb).append(", moduleId=").append(moduleId);
		return sb;
	}
	
	@Override
	public String toString() {
		return appendTo(new StringBuilder()).toString();
	}

}
