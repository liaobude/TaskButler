/*
 * DatabaseHandler.java
 * 
 * Copyright 2012 Jonathan Hasenzahl, James Celona, Dhimitraq Jorgji
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Creates SQLite table for storing tasks to a database.
 * @author Dhimitraq Jorgji, Jonathan Hasenzahl
 */

package edu.worcester.cs499summer2012.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "TaskButler.db";

	// tasks table name
	public static final String TABLE_TASKS = "tasks";

	// tasks Table Columns names
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name"; //data type: TEXT
	public static final String KEY_COMPLETION = "completion"; //data type: INTEGER, indirectly DATETIME as Unix Time
	public static final String KEY_PRIORITY = "priority"; //data type: INTEGER, 2=URGENT, 1=REGULAR,0=TRIVIAL 
	public static final String KEY_CATEGORY = "category"; //data type: INTEGER
	public static final String KEY_CREATION_DATE = "creationDate"; //data type: INTEGER, indirectly DATETIME as Unix Time
	public static final String KEY_MODIFICATION_DATE = "modificationDate"; //data type: INTEGER, indirectly DATETIME as Unix Time
	public static final String KEY_DUE_DATE = "dueDate"; //data type: INTEGER, indirectly DATETIME as Unix Time
	public static final String KEY_FINAL_DUE_DATE = "finalDueDate"; //data type: INTEGER, indirectly DATETIME as Unix Time
	public static final String KEY_NOTES = "notes"; //data type: TEXT can be null


	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Table
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_NAME + " TEXT,"
				+ KEY_COMPLETION + " INTEGER,"
				+ KEY_PRIORITY + " INTEGER,"
				+ KEY_CATEGORY + " INTEGER,"
				+ KEY_CREATION_DATE + " INTEGER,"
				+ KEY_MODIFICATION_DATE + " INTEGER,"
				+ KEY_DUE_DATE + " INTEGER,"
				+ KEY_FINAL_DUE_DATE + " INTEGER,"
				+ KEY_NOTES + " TEXT" + ")";

		db.execSQL(CREATE_TASKS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed just for testing purposes, will change to copy over old database later on
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

		// Create tables again
		onCreate(db);
	}
}
