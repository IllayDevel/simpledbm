package org.simpledbm.database.api;

import java.util.ArrayList;

import org.simpledbm.rss.api.st.Storable;
import org.simpledbm.typesystem.api.Row;
import org.simpledbm.typesystem.api.TypeDescriptor;

/**
 * A TableDefinition holds information about a table, such as its name, container ID,
 * types and number of columns, etc..
 * 
 * @author dibyendumajumdar
 */
public interface TableDefinition extends Storable {

	/**
	 * Adds an Index to the table definition. Only one primay index is allowed.
	 * 
	 * @param containerId Container ID for the new index. 
	 * @param name Name of the Index Container
	 * @param columns Array of Column identifiers - columns to be indexed
	 * @param primary A boolean flag indicating that this is the primary index or not
	 * @param unique A boolean flag indicating whether the index should allow only unique values
	 */
	public abstract void addIndex(int containerId, String name, int[] columns,
			boolean primary, boolean unique);

	/**
	 * Gets the Database to which this Table is associated
	 * @return Database
	 */
	public abstract Database getDatabase();

	/**
	 * Gets the Container ID associated with the table.
	 * @return Container ID
	 */
	public abstract int getContainerId();

	/**
	 * Returns the Table's container name.
	 * @return Container name
	 */
	public abstract String getName();

	/**
	 * Returns an array of type descriptors that represent column types for a row in this table.
	 * @return Array of TypeDescriptor objects
	 */
	public abstract TypeDescriptor[] getRowType();

	/**
	 * Returns an array of IndexDefinition objects associated with the table.
	 * @return ArrayList of IndexDefinition objects
	 */
	public abstract ArrayList<IndexDefinition> getIndexes();

	/**
	 * Constructs an empty row for the table.
	 * @return Row
	 */
	public abstract Row getRow();

	/**
	 * Constructs an row for the specified Index. Appropriate columns from the
	 * table are copied into the Index row.
	 *  
	 * @param index The Index for which the row is to be constructed
	 * @param tableRow The table row
	 * @return An initialized Index Row
	 */
	public abstract Row getIndexRow(IndexDefinition index, Row tableRow);

}