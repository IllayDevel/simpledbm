/**
 * DO NOT REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Contributor(s):
 *
 * The Original Software is SimpleDBM (www.simpledbm.org).
 * The Initial Developer of the Original Software is Dibyendu Majumdar.
 *
 * Portions Copyright 2005-2014 Dibyendu Majumdar. All Rights Reserved.
 *
 * The contents of this file are subject to the terms of the
 * Apache License Version 2 (the "APL"). You may not use this
 * file except in compliance with the License. A copy of the
 * APL may be obtained from:
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the APL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the APL, the GPL or the LGPL.
 *
 * Copies of GPL and LGPL may be obtained from:
 * http://www.gnu.org/licenses/license-list.html
 */
package org.simpledbm.rss.api.wal;

import java.nio.ByteBuffer;

import org.simpledbm.common.api.exception.SimpleDBMException;
import org.simpledbm.common.util.mcat.MessageInstance;

/**
 * Base class for all exceptions thrown by the Log implementations.
 * 
 * @author Dibyendu Majumdar
 * @since 10-Jun-2005
 */
public class LogException extends SimpleDBMException {

    private static final long serialVersionUID = 1L;

    public LogException(ByteBuffer bb) {
        super(bb);
    }

    public LogException(MessageInstance m, Throwable arg1) {
        super(m, arg1);
    }

    public LogException(MessageInstance m) {
        super(m);
    }
}
