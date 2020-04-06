package com.btitsolutions.driverstraining.Utilities;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.btitsolutions.driverstraining.Models.ChoiceModel;
import com.btitsolutions.driverstraining.Models.EvaluationCategoryModel;
import com.btitsolutions.driverstraining.Models.EvaluationTypeModel;
import com.btitsolutions.driverstraining.Models.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DriversTrainingDB";
    private static final String TABLE_EVALUATION_CATEGORY = "tblEvaluationCategory";
    private static final String TABLE_EVALUATION_TYPE = "tblEvaluationType";
    private static final String TABLE_QUESTION = "tblQuestion";
    private static final String TABLE_CHOICE = "tblChoice";

    private static final String KEY_CODE = "code";
    private static final String KEY_CATEGORY_CODE = "categoryCode";
    private static final String KEY_NAME = "name";

    private static final String KEY_ID = "Id";
    private static final String KEY_QUESTION_ID = "QuestionId";
    private static final String KEY_CATEGORY_ID = "CategoryId";
    private static final String KEY_TYPE_ID = "TypeId";
    private static final String KEY_QUESTION_TEXT = "QuestionText";
    private static final String KEY_CHOICE_TEXT = "ChoiceText";
    private static final String KEY_IS_ANSWER = "IsAnswer";

    Context _context;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVALUATION_CATEGORY_TABLE = "CREATE TABLE " + TABLE_EVALUATION_CATEGORY + "("
        + KEY_CODE + " TEXT PRIMARY KEY, "
        + KEY_NAME + " TEXT" + ")";

        db.execSQL(CREATE_EVALUATION_CATEGORY_TABLE);

        String CREATE_EVALUATION_TYPE_TABLE = "CREATE TABLE " + TABLE_EVALUATION_TYPE + "("
                + KEY_CODE + " TEXT PRIMARY KEY, "
                + KEY_CATEGORY_CODE + " TEXT, "
                + KEY_NAME + " TEXT" + ")";

        db.execSQL(CREATE_EVALUATION_TYPE_TABLE);

        String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION + "("
                + KEY_ID + " TEXT PRIMARY KEY, "
                + KEY_CATEGORY_ID + " TEXT, "
                + KEY_TYPE_ID + " TEXT, "
                + KEY_QUESTION_TEXT + " TEXT )";

        db.execSQL(CREATE_QUESTION_TABLE);

        String CREATE_CHOICE_TABLE = "CREATE TABLE " + TABLE_CHOICE + "("
                + KEY_ID + " TEXT PRIMARY KEY, "
                + KEY_QUESTION_ID + " TEXT, "
                + KEY_CHOICE_TEXT + " TEXT, "
                + KEY_IS_ANSWER + " TEXT" + ")";

        db.execSQL(CREATE_CHOICE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVALUATION_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVALUATION_TYPE);
        onCreate(db);
    }

    //for Evaluation Category table
    public void addEvaluationCategory(EvaluationCategoryModel evaluationCategoryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CODE, evaluationCategoryModel.getCode());
        values.put(KEY_NAME, evaluationCategoryModel.getName());

        db.insert(TABLE_EVALUATION_CATEGORY, null, values);
        db.close();
    }

    public EvaluationCategoryModel getEvaluationCategory(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EVALUATION_CATEGORY, new String[] { KEY_CODE,
                        KEY_NAME }, KEY_CODE + "=?",
                new String[] { String.valueOf(code) }, null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        EvaluationCategoryModel contact = new EvaluationCategoryModel(cursor.getString(0), cursor.getString(1));
        return contact;
    }

    public List<EvaluationCategoryModel> getAllEvaluationCategories() {
        List<EvaluationCategoryModel> evaluationCategoryModels = new ArrayList<>();

        String selectQuery = "SELECT code, name FROM " + TABLE_EVALUATION_CATEGORY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                EvaluationCategoryModel evaluationCategoryModel = new EvaluationCategoryModel();
                evaluationCategoryModel.setCode(cursor.getString(0));
                evaluationCategoryModel.setName(cursor.getString(1));

                evaluationCategoryModels.add(evaluationCategoryModel);
            } while (cursor.moveToNext());
        }

        return evaluationCategoryModels;
    }

    public int getEvaluationCategoriesCount() {
        String countQuery = "SELECT * FROM " + TABLE_EVALUATION_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateEvaluationCategory(EvaluationCategoryModel evaluationCategoryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CODE, evaluationCategoryModel.getCode());
        values.put(KEY_NAME, evaluationCategoryModel.getName());

        return db.update(TABLE_EVALUATION_CATEGORY, values, KEY_CODE + " = ?",
                new String[]{String.valueOf(evaluationCategoryModel.getCode())});
    }

    public void deleteEvaluationCategory(EvaluationCategoryModel evaluationCategoryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVALUATION_CATEGORY, KEY_CODE + " = ?",
                new String[] { evaluationCategoryModel.getCode() });

        db.close();
    }

    //for Evaluation Type table
    public void addEvaluationType(EvaluationTypeModel evaluationTypeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CODE, evaluationTypeModel.getCode());
        values.put(KEY_CATEGORY_CODE, evaluationTypeModel.getCategoryCode());
        values.put(KEY_NAME, evaluationTypeModel.getName());

        db.insert(TABLE_EVALUATION_TYPE, null, values);
        db.close();
    }

    public EvaluationTypeModel getEvaluationType(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EVALUATION_TYPE, new String[] { KEY_CODE,
                        KEY_CATEGORY_CODE, KEY_NAME }, KEY_CODE + "=?",
                new String[] { String.valueOf(code) }, null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        EvaluationTypeModel contact = new EvaluationTypeModel(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        return contact;
    }

    public List<EvaluationTypeModel> getAllEvaluationTypes(String categoryCode) {
        List<EvaluationTypeModel> evaluationTypeModels = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_EVALUATION_TYPE + " WHERE " + KEY_CATEGORY_CODE + " = " + categoryCode + " OR " + KEY_CATEGORY_CODE + " = 2";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                EvaluationTypeModel evaluationTypeModel = new EvaluationTypeModel();
                evaluationTypeModel.setCode(cursor.getString(0));
                evaluationTypeModel.setCategoryCode(cursor.getString(1));
                evaluationTypeModel.setName(cursor.getString(2));

                evaluationTypeModels.add(evaluationTypeModel);
            } while (cursor.moveToNext());
        }

        return evaluationTypeModels;
    }

    //for Question table
    public void addQuestion(QuestionModel questionModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, questionModel.getID());
        values.put(KEY_CATEGORY_ID, questionModel.getCategoryID());
        values.put(KEY_TYPE_ID, questionModel.getTypeID());
        values.put(KEY_QUESTION_TEXT, questionModel.getQuestionText());

        db.insert(TABLE_QUESTION, null, values);
        db.close();
    }

    public List<QuestionModel> getQuestions(int numberOfQuestions, int categoryID, int typeID) {
        List<QuestionModel> questionModels = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_QUESTION + " WHERE " + KEY_CATEGORY_ID + " = " + categoryID + " AND " + KEY_TYPE_ID + "=" + typeID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int counter = 0;
            do {
                QuestionModel questionModel = new QuestionModel();
                questionModel.setID(Integer.parseInt(cursor.getString(0)));
                questionModel.setCategoryID(Integer.parseInt(cursor.getString(1)));
                questionModel.setTypeID(Integer.parseInt(cursor.getString(2)));
                questionModel.setQuestionText(cursor.getString(3));

                questionModels.add(questionModel);
                counter = counter + 1;
            } while (cursor.moveToNext() && counter < numberOfQuestions);
        }

        return questionModels;
    }

    public int getQuestionCount() {
        String countQuery = "SELECT * FROM " + TABLE_QUESTION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        return cursor.getCount();
    }

    public int getQuestionCount(int categoryCode) {
        try{
            String countQuery = "SELECT * FROM " + TABLE_QUESTION + " WHERE " + KEY_CATEGORY_ID + "='" + categoryCode + "' OR " + KEY_CATEGORY_ID + "='2'";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            //cursor.close();

            return cursor.getCount();
        }
        catch(Exception ex){
            Toast.makeText(_context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public int getQuestionCount(int categoryID, int typeID) {
        String countQuery = "SELECT * FROM " + TABLE_QUESTION + " WHERE " + KEY_CATEGORY_ID + "='" + categoryID + "' AND " + KEY_TYPE_ID + "='" + typeID + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        return cursor.getCount();
    }

    //for Choice table
    public void addChoice(ChoiceModel choiceModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, choiceModel.getID());
        values.put(KEY_QUESTION_ID, choiceModel.getQuestionID());
        values.put(KEY_CHOICE_TEXT, choiceModel.getChoiceText());
        values.put(KEY_IS_ANSWER, choiceModel.getIsAnswer());

        db.insert(TABLE_CHOICE, null, values);
        db.close();
    }

    public List<ChoiceModel> getChoices(int questionID) {
        List<ChoiceModel> choiceModels = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_CHOICE + " WHERE " + KEY_QUESTION_ID + " = " + questionID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ChoiceModel choiceModel = new ChoiceModel();
                choiceModel.setID(Integer.parseInt(cursor.getString(0)));
                choiceModel.setQuestionID(Integer.parseInt(cursor.getString(1)));
                choiceModel.setChoiceText(cursor.getString(2));

                if(cursor.getString(3).equals("1")){
                    choiceModel.setAnswer(true);
                }
                else{
                    choiceModel.setAnswer(false);
                }

                choiceModels.add(choiceModel);
            } while (cursor.moveToNext());
        }

        return choiceModels;
    }

    public int getChoiceCount() {
        String countQuery = "SELECT * FROM " + TABLE_CHOICE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        return cursor.getCount();
    }
}