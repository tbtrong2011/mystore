package com.mymoney.note.database;
import java.util.ArrayList;
import java.util.List;

import com.aigovn.item.ItemMail;
import com.google.android.gms.plus.People;
import com.mymoney.note.item.ItemMoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "NoteDatabase.db";
    private static SQLiteDatabase db;
 
    private static DatabaseHandler instance;
	private static final Object lockObject = new Object();

	/*---------------------------------------------------------------------------
	 * Define some information for table
	 */
	private static final String TABLE_MONEY = "money";
	private static final String TABLE_NOTE = "note";
	private static final String TABLE_TOPIC = "topic";
	private static final String TABLE_SETTING = "setting";
	private static final String TABLE_MONEY_TOPIC = "money_topic";
	private static final String TABLE_NOTE_TOPIC = "note_topic";
	private static final String TAG = "Database";
	
	// Common column names in table
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_TITLE = "title";
	private static final String COLUMN_VALUE = "value";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_STATUS = "status";
	private static final String COLUMN_DATE = "date";
	private static final String COLUMN_TYPE = "type";
	private static final String COLUMN_ADDRESS = "address";
	private static final String COLUMN_LONGITUDE = "longitude";
	private static final String COLUMN_LATITUDE = "latitude";
	private static final String COLUMN_NOTIFY_STATUS = "notify_status";
	private static final String COLUMN_NOTIFY_DATE = "notify_date";
	private static final String COLUMN_MONEY_ID = "money_id";
	private static final String COLUMN_NOTE_ID = "note_id";
	private static final String COLUMN_TOPIC_ID = "topic_id";
	private static final String COLUMN_NAME= "name";


	private static final String CREATE_TABLE_MONEY = "CREATE TABLE " + TABLE_MONEY + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_TITLE + " TEXT,"
			+ COLUMN_VALUE + " REAL,"
			+ COLUMN_DESCRIPTION + " TEXT,"
			+ COLUMN_STATUS + " INTEGER,"
			+ COLUMN_DATE + " INTEGER,"
			+ COLUMN_TYPE + " INTEGER,"
			+ COLUMN_ADDRESS + " TEXT,"
			+ COLUMN_LONGITUDE + " TEXT,"
			+ COLUMN_LATITUDE + " TEXT,"
			+ ")";
	private static final String CREATE_TABLE_NOTE = "CREATE TABLE " + TABLE_NOTE + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_TITLE + " TEXT,"
			+ COLUMN_VALUE + " REAL,"
			+ COLUMN_DESCRIPTION + " TEXT,"
			+ COLUMN_STATUS + " INTEGER,"
			+ COLUMN_DATE + " INTEGER,"
			+ COLUMN_NOTIFY_STATUS + " INTEGER,"
			+ COLUMN_NOTIFY_DATE + " INTEGER,"
			+ COLUMN_ADDRESS + " TEXT,"
			+ COLUMN_LONGITUDE + " TEXT,"
			+ COLUMN_LATITUDE + " TEXT,"
			+ ")";
	private static final String CREATE_TABLE_TOPIC = "CREATE TABLE " + TABLE_TOPIC + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_NAME + " TEXT,"
			+ COLUMN_STATUS + " INTEGER,"
			+ ")";
	private static final String CREATE_TABLE_SETTING = "CREATE TABLE " + TABLE_SETTING + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_NAME + " TEXT,"
			+ COLUMN_VALUE + " TEXT,"
			+ ")";
	private static final String CREATE_TABLE_MONEY_TOPIC = "CREATE TABLE " + TABLE_MONEY_TOPIC + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_MONEY_ID + " INTEGER,"
			+ COLUMN_TOPIC_ID + " INTEGER,"
			+ COLUMN_DATE + " INTEGER,"
			+ ")";
	private static final String CREATE_TABLE_NOTE_TOPIC = "CREATE TABLE " + TABLE_NOTE_TOPIC + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_NOTE_ID + " INTEGER,"
			+ COLUMN_TOPIC_ID + " INTEGER,"
			+ COLUMN_DATE + " INTEGER,"
			+ ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	try{
			db.execSQL(CREATE_TABLE_MONEY);
			db.execSQL(CREATE_TABLE_NOTE);
			db.execSQL(CREATE_TABLE_TOPIC);
			db.execSQL(CREATE_TABLE_SETTING);
			db.execSQL(CREATE_TABLE_MONEY_TOPIC);
			db.execSQL(CREATE_TABLE_NOTE_TOPIC);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONEY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPIC);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONEY_TOPIC);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE_TOPIC);
 
        // Create tables again
        onCreate(db);
    }

    //Open database
    public void openDatabase() {
		try {
			db = this.getWritableDatabase();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    //Close database
    public void closeDatabase() {
		try {
			db.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /*---------------------------------------------------------------------------
	 * Implement CRUD
	 */
    public long addMoney(ItemMoney item) {
    	synchronized (lockObject) {
	    	openDatabase();

	        ContentValues values = new ContentValues();
	        values.put(COLUMN_TITLE, item.getTitle());
	        values.put(COLUMN_VALUE, item.getValue());
	        values.put(COLUMN_DESCRIPTION, item.getDescription());
	        values.put(COLUMN_STATUS, item.getStatus());
	        values.put(COLUMN_DATE, item.getDate());
	        values.put(COLUMN_TYPE, item.getType());
	        values.put(COLUMN_ADDRESS, item.getAddress());
	        values.put(COLUMN_LONGITUDE, item.getLongitude());
	        values.put(COLUMN_LATITUDE, item.getLatitude());

	        // Inserting Row
	        long todo_id =  db.insert(TABLE_MONEY, null, values);
	        closeDatabase();
	        return todo_id;
    	}
    }

	public int updateMoney(ItemMoney item) {
		synchronized (lockObject) {
			openDatabase();

			ContentValues values = new ContentValues();
			values.put(COLUMN_TITLE, item.getTitle());
			values.put(COLUMN_VALUE, item.getValue());
			values.put(COLUMN_DESCRIPTION, item.getDescription());
			values.put(COLUMN_STATUS, item.getStatus());
			values.put(COLUMN_DATE, item.getDate());
			values.put(COLUMN_TYPE, item.getType());
			values.put(COLUMN_ADDRESS, item.getAddress());
			values.put(COLUMN_LONGITUDE, item.getLongitude());
			values.put(COLUMN_LATITUDE, item.getLatitude());

			// Update Row
			int count = db.update(TABLE_MONEY, values, COLUMN_ID + "=" + item.getId(), null);
			closeDatabase();
			return count;
		}
	}

	public int deleteMoney(long id) {
		synchronized (lockObject) {
			openDatabase();
			int count = db.delete(TABLE_MONEY, COLUMN_ID + "="+ id,null);
			closeDatabase();
			return count;
		}
	}

	public ItemMoney getMoney(long id){
		synchronized (lockObject) {
			openDatabase();
			ItemMoney item = new ItemMoney();
			Cursor cursor = db.query(TABLE_MONEY, null, COLUMN_ID + "=" + id, null, null, null, null, null);
			if (cursor != null) cursor.moveToFirst();

			item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
			item.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
			item.setValue(cursor.getDouble(cursor.getColumnIndex(COLUMN_VALUE)));
			item.setType(cursor.getShort(cursor.getColumnIndex(COLUMN_TYPE)));
			item.setDate(cursor.getLong(cursor.getColumnIndex(COLUMN_DATE)));
			item.setStatus(cursor.getShort(cursor.getColumnIndex(COLUMN_STATUS)));
			item.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
			item.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
			item.setLatitude(cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE)));
			item.setLongitude(cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE)));

			closeDatabase();
			return item;
		}
	}

	public List<ItemMoney> getMoney(String keyWord,
									short status,
									short type,
									long startDate,
									long endDate,
									String topics,
									int pageIndex,
									int pageSize){
		String _selectQuery = "" ;
		ArrayList<String> params = new ArrayList<String>();
		boolean isHaveCondition = false;
		if(!keyWord.isEmpty()){
			isHaveCondition = true;
			_selectQuery += "(" + COLUMN_TITLE + " like '%?%'" + " or " + COLUMN_DESCRIPTION + " like '%?%'" + ")";
		}

		if(isHaveCondition) _selectQuery += " and ";
		if(status != -1) {
			isHaveCondition = true;
			_selectQuery += COLUMN_STATUS + "=?";
		}

		if(isHaveCondition) _selectQuery += " and ";
		if(type != -1){
			isHaveCondition = true;
			_selectQuery += COLUMN_TYPE + "=?";
		}

		if(isHaveCondition) _selectQuery += " and ";
		if(startDate != -1){
			isHaveCondition = true;
			_selectQuery += COLUMN_DATE + ">=?";
		}

		if(isHaveCondition) _selectQuery += " and ";
		if(endDate != -1){
			isHaveCondition = true;
			_selectQuery += COLUMN_DATE + "<=?";
		}

		if(isHaveCondition) _selectQuery += " and ";
		if(!topics.isEmpty()){
			isHaveCondition = true;
			_selectQuery += COLUMN_ID + "=" + COLUMN_MONEY_ID + " and " + COLUMN_TOPIC_ID + " in (?)";
		}

		String selectQuery = "SELECT  * FROM " + TABLE_MONEY
				+ " ORDER BY " + COLUMN_DATE + " DESC "
				+ " LIMIT ? " + " OFFSET ? ";
		if(_selectQuery.equals("")){
			selectQuery += " WHERE " + _selectQuery;
		}


		synchronized (lockObject) {
			openDatabase();
			params.add(String.valueOf(pageSize));
			params.add(String.valueOf(pageIndex * pageSize));
			if(!keyWord.isEmpty()) params.add(keyWord);
			if(status != -1) params.add(String.valueOf(status));
			if(type != -1) params.add(String.valueOf(type));
			if(startDate != -1) params.add(String.valueOf(startDate));
			if(endDate != -1) params.add(String.valueOf(endDate));
			if(!topics.isEmpty()) params.add(topics);

			List<ItemMoney> itemList = new ArrayList<ItemMoney>();
			Cursor cursor = null;
			try {
				cursor = db.rawQuery(selectQuery, params.toArray(new String[params.size()]));

				if (cursor != null && cursor.moveToFirst()) {
					do {
						ItemMail _item = new ItemMail();
						_item.set_id(cursor.getInt(cursor.getColumnIndex(_COLUMN_ID)));
						_item.setMailID(cursor.getLong(cursor.getColumnIndex(_COLUMN_MAIL_ID)));
						_item.setUserID(cursor.getLong(cursor.getColumnIndex(_COLUMN_USER_ID)));
						_item.setFromAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_FROM_ADDRESS)));
						_item.setToAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_TO_ADDRESS)));
						_item.setCcAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_CC_ADDRESS)));
						_item.setDesType(cursor.getString(cursor.getColumnIndex(_COLUMN_DESTINATION_TYPE)));
						_item.setSubject(cursor.getString(cursor.getColumnIndex(_COLUMN_SUBJECT)));
						_item.setBody(cursor.getString(cursor.getColumnIndex(_COLUMN_BODY)));
						_item.setReceiveDate(cursor.getString(cursor.getColumnIndex(_COLUMN_RECEIVE_DATE)));
						_item.setProcessDate(cursor.getString(cursor.getColumnIndex(_COLUMN_PROCESS_DATE)));
						_item.setFromAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_FROM_ADDRESS)));
						_item.setReceiveTimeStamp(cursor.getString(cursor.getColumnIndex(_COLUMN_RECEIVE_DATE)));
						_item.setProcessTimeStamp(cursor.getString(cursor.getColumnIndex(_COLUMN_PROCESS_DATE)));
						_item.setStatus(cursor.getInt(cursor.getColumnIndex(_COLUMN_STATUS)));
						_item.setType(cursor.getInt(cursor.getColumnIndex(_COLUMN_TYPE)));
						mailList.add(_item);
					}
					while (cursor.moveToNext());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (cursor != null) {
					cursor.close();
				}
			}
			closeDatabase();
			return itemList;
		}
	}

    
    /*
     * Get a single mail
     * @param: id => id of mail
     */
    public ItemMail getMail(int id){
    	synchronized (lockObject) {
    		openDatabase();
    		ItemMail _item = new ItemMail();
    		Cursor cursor = _db.query(TABLE_MAIL, null, _COLUMN_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            
            _item.set_id(cursor.getInt(cursor.getColumnIndex(_COLUMN_ID)));
            _item.setMailID(cursor.getLong(cursor.getColumnIndex(_COLUMN_MAIL_ID)));
            _item.setUserID(cursor.getLong(cursor.getColumnIndex(_COLUMN_USER_ID)));
            _item.setFromAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_FROM_ADDRESS)));
            _item.setToAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_TO_ADDRESS)));
            _item.setCcAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_CC_ADDRESS)));
            _item.setDesType(cursor.getString(cursor.getColumnIndex(_COLUMN_DESTINATION_TYPE)));
            _item.setSubject(cursor.getString(cursor.getColumnIndex(_COLUMN_SUBJECT)));
            _item.setBody(cursor.getString(cursor.getColumnIndex(_COLUMN_BODY)));
            _item.setReceiveDate(cursor.getString(cursor.getColumnIndex(_COLUMN_RECEIVE_DATE)));
            _item.setProcessDate(cursor.getString(cursor.getColumnIndex(_COLUMN_PROCESS_DATE)));
            _item.setFromAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_FROM_ADDRESS)));
            _item.setReceiveTimeStamp(cursor.getString(cursor.getColumnIndex(_COLUMN_RECEIVE_DATE)));
            _item.setProcessTimeStamp(cursor.getString(cursor.getColumnIndex(_COLUMN_PROCESS_DATE)));
            _item.setStatus(cursor.getInt(cursor.getColumnIndex(_COLUMN_STATUS)));
            _item.setType(cursor.getInt(cursor.getColumnIndex(_COLUMN_TYPE)));
            
            closeDatabase();
            return _item;
    	}
    }
    
    /*
     * Get multiple mail and include paging
     * @param: pageIndex => index page
     * 		   pageSize => number record in one page
     * 		   type => type of mail (0: inbox, 1:outbox, 2:draft, 3:trash)
     */
    public List<ItemMail> getMail(int pageIndex, int pageSize, int type){
    	synchronized (lockObject) {
    		openDatabase();
    		List<ItemMail> mailList = new ArrayList<ItemMail>();
    	    String selectQuery = "SELECT  * FROM " + TABLE_MAIL 
    	    						+ " LIMIT ? " + " OFFSET ? "
    	    						+ " WHERE " + _COLUMN_TYPE + " = ?" ;
    	 
    	    Cursor cursor = null;
    	    try {
    	    	cursor = _db.rawQuery(selectQuery, new String[] { String.valueOf(pageSize), String.valueOf(pageIndex * pageSize), String.valueOf(type) });

				if (cursor != null && cursor.moveToFirst()) {
					do {
						ItemMail _item = new ItemMail();
						_item.set_id(cursor.getInt(cursor.getColumnIndex(_COLUMN_ID)));
			            _item.setMailID(cursor.getLong(cursor.getColumnIndex(_COLUMN_MAIL_ID)));
			            _item.setUserID(cursor.getLong(cursor.getColumnIndex(_COLUMN_USER_ID)));
			            _item.setFromAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_FROM_ADDRESS)));
			            _item.setToAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_TO_ADDRESS)));
			            _item.setCcAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_CC_ADDRESS)));
			            _item.setDesType(cursor.getString(cursor.getColumnIndex(_COLUMN_DESTINATION_TYPE)));
			            _item.setSubject(cursor.getString(cursor.getColumnIndex(_COLUMN_SUBJECT)));
			            _item.setBody(cursor.getString(cursor.getColumnIndex(_COLUMN_BODY)));
			            _item.setReceiveDate(cursor.getString(cursor.getColumnIndex(_COLUMN_RECEIVE_DATE)));
			            _item.setProcessDate(cursor.getString(cursor.getColumnIndex(_COLUMN_PROCESS_DATE)));
			            _item.setFromAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_FROM_ADDRESS)));
			            _item.setReceiveTimeStamp(cursor.getString(cursor.getColumnIndex(_COLUMN_RECEIVE_DATE)));
			            _item.setProcessTimeStamp(cursor.getString(cursor.getColumnIndex(_COLUMN_PROCESS_DATE)));
			            _item.setStatus(cursor.getInt(cursor.getColumnIndex(_COLUMN_STATUS)));
			            _item.setType(cursor.getInt(cursor.getColumnIndex(_COLUMN_TYPE)));
			            mailList.add(_item);
					}
					while (cursor.moveToNext());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (cursor != null) {
					cursor.close();
				}
			}
            
            closeDatabase();
            return mailList;
    	}
    }
    
    /*
     * Search mail
     * @param: keyword => keyword to search
     * 		   type => type of mail (0: inbox, 1:outbox, 2:draft, 3:trash)
     */
    public List<ItemMail> searchMail(String keyword, int type){
    	synchronized (lockObject) {
    		openDatabase();
    		List<ItemMail> mailList = new ArrayList<ItemMail>();
    	    String selectQuery = "SELECT  * FROM " + TABLE_MAIL 
    	    						+ " WHERE (" + _COLUMN_SUBJECT + " LIKE %" + keyword + "%"
    	    						+ " OR " + _COLUMN_BODY + " LIKE %" + keyword + "%)" 
    	    						+ " AND " + _COLUMN_TYPE + " = " + type ;
    	 
    	    Cursor cursor = null;
    	    try {
    	    	cursor = _db.rawQuery(selectQuery, null);

				if (cursor != null && cursor.moveToFirst()) {
					do {
						ItemMail _item = new ItemMail();
						_item.set_id(cursor.getInt(cursor.getColumnIndex(_COLUMN_ID)));
			            _item.setMailID(cursor.getLong(cursor.getColumnIndex(_COLUMN_MAIL_ID)));
			            _item.setUserID(cursor.getLong(cursor.getColumnIndex(_COLUMN_USER_ID)));
			            _item.setFromAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_FROM_ADDRESS)));
			            _item.setToAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_TO_ADDRESS)));
			            _item.setCcAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_CC_ADDRESS)));
			            _item.setDesType(cursor.getString(cursor.getColumnIndex(_COLUMN_DESTINATION_TYPE)));
			            _item.setSubject(cursor.getString(cursor.getColumnIndex(_COLUMN_SUBJECT)));
			            _item.setBody(cursor.getString(cursor.getColumnIndex(_COLUMN_BODY)));
			            _item.setReceiveDate(cursor.getString(cursor.getColumnIndex(_COLUMN_RECEIVE_DATE)));
			            _item.setProcessDate(cursor.getString(cursor.getColumnIndex(_COLUMN_PROCESS_DATE)));
			            _item.setFromAddress(cursor.getString(cursor.getColumnIndex(_COLUMN_FROM_ADDRESS)));
			            _item.setReceiveTimeStamp(cursor.getString(cursor.getColumnIndex(_COLUMN_RECEIVE_DATE)));
			            _item.setProcessTimeStamp(cursor.getString(cursor.getColumnIndex(_COLUMN_PROCESS_DATE)));
			            _item.setStatus(cursor.getInt(cursor.getColumnIndex(_COLUMN_STATUS)));
			            _item.setType(cursor.getInt(cursor.getColumnIndex(_COLUMN_TYPE)));
			            mailList.add(_item);
					}
					while (cursor.moveToNext());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (cursor != null) {
					cursor.close();
				}
			}
            
            closeDatabase();
            return mailList;
    	}
    }
    
}
