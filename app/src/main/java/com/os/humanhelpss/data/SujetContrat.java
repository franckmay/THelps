package com.os.humanhelpss.data;

import android.provider.BaseColumns;

public class SujetContrat {

    public  static final String TABLE_NAME = "sujet";

    public static final class SujetEntry implements BaseColumns {

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_SUJET_NAME = "nom";
        public static final String COLUMN_SUJET_PRENOM = "prenom";
        public static final String COLUMN_SUJET_AGE = "age";
        public static final String COLUMN_SUJET_TITRE = "titre";
        public static final String COLUMN_SUJET_DESCRIPTION = "description";
        public static final String COLUMN_SUJET_IMAGE = "image";


    }
}
