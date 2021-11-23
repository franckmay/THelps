package com.os.humanhelpss.data;

import android.provider.BaseColumns;

public class HelpContrat {

    public static final String TABLE_NAME = "lecon";

    public static final class HelpEntry implements BaseColumns {
        //TaskEntry class for deifning column names of Task table
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_LECON_TITRE = "titre";
        public static final String COLUMN_LECON_DESCRIPTION = "description";
        public static final String COLUMN_LECON_MONTANT = "montant";

    }
}
