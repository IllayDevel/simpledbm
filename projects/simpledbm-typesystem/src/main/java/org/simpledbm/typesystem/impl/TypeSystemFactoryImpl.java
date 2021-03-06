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
package org.simpledbm.typesystem.impl;

import java.nio.ByteBuffer;
import java.util.Properties;

import org.simpledbm.common.api.platform.PlatformObjects;
import org.simpledbm.common.util.mcat.Message;
import org.simpledbm.common.util.mcat.MessageType;
import org.simpledbm.typesystem.api.DictionaryCache;
import org.simpledbm.typesystem.api.RowFactory;
import org.simpledbm.typesystem.api.TableDefinition;
import org.simpledbm.typesystem.api.TypeDescriptor;
import org.simpledbm.typesystem.api.TypeFactory;
import org.simpledbm.typesystem.api.TypeSystemFactory;

/**
 * TypeSystemFactory is the entry point for external clients to obtain access to
 * the type system interfaces.
 * 
 * @author dibyendumajumdar
 */
public class TypeSystemFactoryImpl implements TypeSystemFactory {

    PlatformObjects po;
    static final Message outOfRange = new Message('T', 'Y', MessageType.ERROR,
            13, "Value {0} is outside the range ({1}, {2})");

    public TypeSystemFactoryImpl(Properties properties, PlatformObjects po) {
        this.po = po;
    }

    public TypeFactory getDefaultTypeFactory() {
        return new DefaultTypeFactory();
    }

    public RowFactory getDefaultRowFactory(TypeFactory typeFactory) {
        return new GenericRowFactory(typeFactory, new SimpleDictionaryCache());
    }

    public RowFactory getDefaultRowFactory(TypeFactory typeFactory,
            DictionaryCache dictionaryCache) {
        return new GenericRowFactory(typeFactory, dictionaryCache);
    }

    public TableDefinition getTableDefinition(PlatformObjects po,
            TypeFactory typeFactory, RowFactory rowFactory, ByteBuffer bb) {
        return new TableDefinitionImpl(po, typeFactory, rowFactory, bb);
    }

    public TableDefinition getTableDefinition(PlatformObjects po,
            TypeFactory typeFactory, RowFactory rowFactory, int containerId,
            String name, TypeDescriptor[] rowType) {
        return new TableDefinitionImpl(po, typeFactory, rowFactory,
                containerId, name, rowType);
    }
}
